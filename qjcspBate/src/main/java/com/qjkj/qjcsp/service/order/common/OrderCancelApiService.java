package com.qjkj.qjcsp.service.order.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.TblCustomerDisActLog;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblOrderDetail;
import com.qjkj.qjcsp.entity.TblPendingOrder;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.TblPendingOrderMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;

import net.sf.json.JSONObject;

@Component
@Transactional
public class OrderCancelApiService {

	private static final Logger logger = LoggerFactory.getLogger(OrderCancelApiService.class);

	@Autowired
	private OrderCommonService orderCommonService;

	@Autowired
	private TblOrderMapper tblOrderMapper;
	@Autowired
	private TblPendingOrderMapper tblPendingOrderMapper;
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;
	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;
	@Autowired
	private TblItemDetailMapper tblItemDetailMapper;

	/**
	 * 取消订单
	 * 
	 * @param res
	 * @return
	 */
	public Map<String, Object> cancelOrder(JSONObject res) {
		
		Map<String, Object> returnData = new HashMap<String, Object>();
		/* 订单号 */
		String orderNum = res.getString("orderNum");
		/* 提交方式 0：设备端，1：APP端 */
		String commitMode = null;
		/* 订单类型 0：预定，1：零售 */
		String orderType = res.getString("orderType");

		if (StringUtils.isNoneBlank(orderNum, orderType)) {
			TblOrder tblOrder = tblOrderMapper.selectByOrderNum(orderNum);
			if (tblOrder != null && OrderStatus.NO_PAY.value.equals(tblOrder.getOrderStatus())) {
				commitMode = tblOrderMapper.selectByOrderNum(orderNum).getModeNum().toString();
				logger.warn("接口取消订单"+"orderNum: "+orderNum+"orderType:"+orderType+"system time:"+new Date());
				// 零售订单取消
				if (StringUtils.equals(orderType, "1")) {
					if (StringUtils.equals(commitMode, "0")) {
						logger.warn("设备端零售取消订单"+"orderNum: "+orderNum+"orderType:"+orderType+"system time:"+new Date());
						/* 设备端取消订单 */
						Map<String, Object> data = cancelDeviceOrder(orderNum);
						returnData.put("returnCode", data.get("returnCode"));
						returnData.put("returnContent", data.get("returnContent"));
					} else {
						logger.warn("APP端零售取消订单"+"orderNum: "+orderNum+"orderType:"+orderType+"system time:"+new Date());
						/* APP端取消订单 */
						Map<String, Object> map = retailCancelOrderAndUnlockItem(orderNum);
						returnData.put("returnCode", map.get("returnCode"));
						returnData.put("returnContent", map.get("returnContent"));
					}
				} else {
					logger.warn("预定订单取消"+"orderNum: "+orderNum+"orderType:"+orderType+"system time:"+new Date());
					// 0预定订单取消
					Map<String, Object> map = reserveCancelOrderAndUnlockItem(orderNum);
					returnData.put("returnCode", map.get("returnCode"));
					returnData.put("returnContent", map.get("returnContent"));
				}
			}else{
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "订单的状态为不可取消!");
			}
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}
		return returnData;
	}

	/**
	 * 零售取消订单，解锁单品
	 * 
	 * @param deviceCode
	 * @param orderNum
	 */
	public Map<String, Object> retailCancelOrderAndUnlockItem(String orderNum) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		// /*首单优惠活动*/
		// cancelFirstOrder(orderNum);

		/* 修改订单状态为已取消 */
		tblOrderMapper.updateOrderStatus(orderNum, OrderStatus.CANCEL.value, new Date());
		/* 修改子订单状态为取消 */
		TblOrder tblOrder = tblOrderMapper.selectByOrderNum(orderNum);
		if (tblOrder != null) {
			tblOrderChildMapper.updateByOrderId(tblOrder.getOrderId(), OrderStatus.CANCEL.value);
			returnData.put("returnCode", "1");
			returnData.put("returnContent", "CANCEL_SUCCESS");
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "订单号错误");
		}

		retailUnlockItem(orderNum);

		return returnData;
	}

	/**
	 * 预定取消订单，解锁单品
	 * 
	 * @param deviceCode
	 * @param orderNum
	 * @return
	 */
	public Map<String, Object> reserveCancelOrderAndUnlockItem(String orderNum) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		/* 首单优惠活动 */
		// cancelFirstOrder(orderNum);

		/* 修改订单状态为已取消 */
		tblOrderMapper.updateOrderStatus(orderNum, OrderStatus.CANCEL.value, new Date());

		TblOrder tblOrder = tblOrderMapper.selectByOrderNum(orderNum);
		/* 修改子订单状态为取消 */
		if (tblOrder != null) {
			tblOrderChildMapper.updateByOrderId(tblOrder.getOrderId(), OrderStatus.CANCEL.value);

			List<TblOrderChild> list = tblOrderChildMapper.selectByOrderId(tblOrder.getOrderId());
//			/* Gekko 修改临时表的格子信息为'Y' */
//			tblTemporaryRetailMapper.updByOrderId(tblOrder.getOrderId());
			 for(int i =0; i < list.size(); i++ ){
			 TblOrderChild tblOrderChild = list.get(i);
			
			 List<TblOrderDetail> details =
			 tblOrderDetailMapper.selectByOrderChildId(tblOrderChild.getOrderChildId());
			 for(int k =0; k < details.size(); k++){
			 TblOrderDetail tblOrderDetail = details.get(k);
			 /*删除临时表的格子信息*/
			 tblTemporaryRetailMapper.delByOrderDetailId(tblOrderDetail.getOrderDetailId());
			 }
			 }
			// 删除待处理订单
			reserveUnlockItem(orderNum);
			returnData.put("returnCode", "1");
			returnData.put("returnContent", "CANCEL_SUCCESS");
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "订单号错误");
		}
		return returnData;
	}

	/**
	 * 设备端取消订单
	 * 
	 * @param orderNum
	 * @return
	 */
	private Map<String, Object> cancelDeviceOrder(String orderNum) {
		Map<String, Object> data = new HashMap<String, Object>();
		/* 获取订单状态 */
		String orderStatus = tblOrderMapper.getOrderStatusbyOrderNum(orderNum);
		/* 未付款直接解锁单品，已付款则退款后解锁单品 */
		if (StringUtils.equals(orderStatus, OrderStatus.NO_PAY.value)) {
			/* 修改订单状态为已取消 */
			tblOrderMapper.updateOrderStatus(orderNum, OrderStatus.CANCEL.value, new Date());
			/* 修改子订单状态为取消 */
			TblOrder tblOrder = tblOrderMapper.selectByOrderNum(orderNum);
			tblOrderChildMapper.updateByOrderId(tblOrder.getOrderId(), OrderStatus.CANCEL.value);
			retailUnlockItem(orderNum);
			data.put("returnCode", "1");
			data.put("returnContent", "CANCEL_SUCCESS");
		} else {
			//
			// if (orderCommonService.refundOrder(orderNum) == Boolean.TRUE){
			// data.put("returnCode", "1");
			// data.put("returnContent", "CANCEL_SUCCESS");
			// }else {
			/* 保存到待退款列表 */
			saveRefundOrder(orderNum);
			data.put("returnCode", "1");
			data.put("returnContent", "CANCEL_FAILED");
			// }
		}

		return data;
	}

	/**
	 * 删除待处理预定订单
	 * 
	 * @param deviceCode
	 * @param orderNum
	 */
	public void reserveUnlockItem(String orderNum) {
		// /*解锁单品*/
		// tblItemDetailMapper.unlockItembyOrderNum(orderNum, "0");
		/* 删除该待处理订单 */
		tblPendingOrderMapper.deletebyOrderNum(orderNum);
	}

	/**
	 * 删除待处理零售订单 ，解锁单品
	 * 
	 * @param deviceCode
	 * @param orderNum
	 */
	public void retailUnlockItem(String orderNum) {

		TblOrder tblOrder = tblOrderMapper.selectByOrderNum(orderNum);
		/* 解锁单品 */
		orderCommonService.unlockItemNoPaid(tblOrder.getOrderId());
		/* 删除该待处理订单 */
		tblPendingOrderMapper.deletebyOrderNum(orderNum);
	}

	/**
	 * 已支付订单主动取消保存到待退款列表
	 */
	private void saveRefundOrder(String orderNum) {
		/* 根据订单号获取订单信息 */
		TblOrder tblOrder = tblOrderMapper.selectByOrderNum(orderNum);
		/* 该订单已支付 */
		if (StringUtils.equals(tblOrder.getOrderStatus(), OrderStatus.NO_TAKED.value)) {
			/* 保存已取消待退款订单 */
			TblPendingOrder tblPendingOrder = new TblPendingOrder();
			tblPendingOrder.setCompanyId(tblOrder.getCompanyId());
			tblPendingOrder.setOrderId(tblOrder.getOrderId());
			tblPendingOrder.setOrderNum(orderNum);
			tblPendingOrder.setPendingTypeNum("2");
			// 支付订单号//
			tblPendingOrder.setOrderTime(tblOrder.getOrderTime());
			tblPendingOrder.setLastPayTime(tblOrder.getLastPayTime());

			tblPendingOrderMapper.insertSelective(tblPendingOrder);
		}
	}

	/**
	 * 首单优惠活动，取消首单后再次下单仍可优惠
	 * 
	 * @param orderNum
	 */
	private void cancelFirstOrder(String orderNum) {
		// TblCustomerDisActLog tblCustomerDisActLog =
		// tblCustomerDisActLogMapper.queryDisLogByOrderNum(orderNum);
		// if (tblCustomerDisActLog != null){
		// /*删除首单记录，更新首单优惠标志*/
		// if (tblCustomerDisActLogMapper.deleteDisLog(orderNum) > 0){
		// tblCustomerMapper.updateDiscountUsed(tblCustomerDisActLog.getCustomerId(),
		// "0");
		// }
		// }
	}
	// /**
	// * 设备端取消订单
	// * @param orderNum
	// * @return
	// */
	// private Map<String, Object> cancelDeviceOrder(String orderNum){
	// Map<String, Object> data = new HashMap<String, Object>();
	// /*获取订单状态*/
	// String orderStatus = tblOrderMapper.getOrderStatusbyOrderNum(orderNum);
	// /*未付款直接解锁单品，已付款则退款后解锁单品*/
	// if (StringUtils.equals(orderStatus, OrderStatus.NO_PAY.value)) {
	// orderCommonService.unlockItem(orderNum);
	// data.put("returnCode", "1");
	// data.put("returnContent", "CANCEL_SUCCESS");
	// }else {
	// if (orderCommonService.refundOrder(orderNum) == Boolean.TRUE){
	// data.put("returnCode", "1");
	// data.put("returnContent", "CANCEL_SUCCESS");
	// }else {
	// /*保存到待退款列表*/
	// saveRefundOrder(orderNum);
	// data.put("returnCode", "1");
	// data.put("returnContent", "CANCEL_FAILED");
	// }
	// }
	//
	// return data;
	// }
	//
	// /**
	// * 已支付订单主动取消保存到待退款列表
	// */
	// private void saveRefundOrder(String orderNum){
	// /*根据订单号获取订单信息*/
	// TblOrder tblOrder = tblOrderMapper.queryOrderByOrderNum(orderNum);
	// /*该订单已支付*/
	// if (StringUtils.equals(tblOrder.getOrderStatus(),
	// OrderStatus.NO_TAKED.value)){
	// /*保存已取消待退款订单*/
	// TblPendingOrder tblPendingOrder = new TblPendingOrder();
	// tblPendingOrder.setCompanyId(tblOrder.getCompanyId());
	// tblPendingOrder.setOrderId(tblOrder.getOrderId());
	// tblPendingOrder.setOrderNum(orderNum);
	// tblPendingOrder.setPendingTypeNum("2");
	// //支付订单号//
	// tblPendingOrder.setOrderTime(tblOrder.getOrderTime());
	// tblPendingOrder.setLastPayTime(tblOrder.getLastPayTime());
	//
	// tblPendingOrderMapper.insertSelective(tblPendingOrder);
	// }
	// }
}
