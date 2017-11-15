package com.qjkj.qjcsp.service.order.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.OrderByUserMap;
import com.qjkj.qjcsp.entity.TblBalanceAmount;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblPendingOrder;
import com.qjkj.qjcsp.entity.TblRecharge;
import com.qjkj.qjcsp.entity.enums.ItemStatusEnum;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.TblBalanceAmountMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.TblPendingOrderMapper;
import com.qjkj.qjcsp.mapper.TblRechargeMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.service.alisms.AliSmsService;
import com.qjkj.qjcsp.util.VerifyCode;

@Service
public class OrderCommonService {
	private static Logger logger = LoggerFactory.getLogger(OrderCommonService.class);
	
	@Autowired
	private TblOrderMapper tblOrderMapper;
	
	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;
	
	@Autowired
	private TblPendingOrderMapper tblPendingOrderMapper;
	
	@Autowired
	private TblItemDetailMapper tblItemDetailMapper;
	
	@Autowired
	private TblRechargeMapper rechargeMapper;
	
	@Autowired
	private TblBalanceAmountMapper balanceAmountMapper;
	
	@Autowired
	private TblOrderDetailMapper orderDetailMapper;
	
	@Autowired
	private BasicsAreaModelMapper  BasicsAreaModelMapper;
	
	
	@Autowired
	private AliSmsService aliSmsService;
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;
	
	
	/**
	 * 未付款时取消订单解锁单品
	 * @param orderId
	 */
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void cancelNoPaidOrderAndUnlockItem(Long orderId){
		/*首单优惠活动*/
		//cancelFirstOrder(orderNum);
		/*取消订单*/
		cancelNoPaidOrder(orderId);
		/*解锁单品*/
		unlockItemNoPaid(orderId);

	}
	
	/**
	 * 取餐超时更新订单解锁单品
	 * @param orderChildId
	 */
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void updateTakenTimeoutOrderAndUnlockItem(Long orderChildId){
		TblOrderChild toc = tblOrderChildMapper.selectByPrimaryKey(orderChildId);
		if (!"10".equals(toc.getOrderChildStatus() + "")) {
			logger.warn("轮询:取餐超时");
			logger.warn("子订单ID:"+toc.getOrderChildId()+"....订单开始时间:"+toc.getBeginTime()+".....订单结束时间:"+toc.getEndTime()+".....当前系统时间:"+new Date());
			/*更新订单*/
			updateTakenTimeoutOrder(orderChildId);
		
		/*解锁单品*/
		
		unlockItemTakenTimeout(orderChildId);
		}
	}
	
	/**
	 * 取餐完成更新订单更新单品
	 * @param orderChildId
	 */
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void updateTakenOrderAndUpdateItem(Long orderChildId){
		/*更新订单*/
		updateTakenOrder(orderChildId);
		/*更新单品、格子*/
		//updateTakenItem(orderChildId);
	}
	
	/**
	 * 未付款时取消订单
	 * @param orderId
	 */
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void cancelNoPaidOrder(Long orderId){
		/*更新父订单以及子订单的状态为已取消*/
		tblOrderMapper.cancelUnpaidOrder(orderId, OrderStatus.CANCEL.value);
		/*删除该待处理订单*/
		tblPendingOrderMapper.deleteByOrderId(orderId);
//		/* Gekko 修改临时表的格子信息为'Y' */
//		tblTemporaryRetailMapper.updByOrderId(orderId);
		//删除预定临时表
		tblTemporaryRetailMapper.delByOrderId(orderId);
	}
	
	/**
	 * 取餐超时更新订单
	 * @param orderChildId
	 */
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void updateTakenTimeoutOrder(Long orderChildId){
		/*更新子订单的状态为取餐超时*/
		tblOrderChildMapper.updateOrderChildStatusByOrderChildId(orderChildId, OrderStatus.TIMEOUT.value);
		/*删除该待处理订单*/
		tblPendingOrderMapper.deleteByOrderChildId(orderChildId);
	}
	
	/**
	 * 取餐完成更新订单
	 * @param orderChildId
	 */
	@Transactional(rollbackFor={RuntimeException.class, Exception.class})
	public void updateTakenOrder(Long orderChildId){
		TblOrderChild toc = tblOrderChildMapper.selectByPrimaryKey(orderChildId);
		//如果子订单状态是10,则不改变子订单状态
		if (!"10".equals(toc.getOrderChildStatus() + "")) {
		/*更新子订单的状态为已取餐*/
		tblOrderChildMapper.updateOrderChildStatusByOrderChildId(orderChildId, OrderStatus.TAKED.value);
		}	/*删除该待处理订单*/
		tblPendingOrderMapper.deleteByOrderChildId(orderChildId);
	}
	
	/**
	 * 未付款时解锁单品
	 * @param orderId
	 */
	public void unlockItemNoPaid(Long orderId){
		logger.warn("未付款时解锁单品"+"orderId:..."+orderId+"....系统时间:"+new Date());
		/*解锁单品*/
		tblItemDetailMapper.unlockItemByOrderId(orderId, ItemStatusEnum.UNSOLD.getValue());
	}
	
	/**
	 * 取餐超时解锁单品
	 * @param orderChildId
	 */
	public void unlockItemTakenTimeout(Long orderChildId){
		/*解锁单品*/
		logger.warn("取餐超时解锁单品"+"orderChildId:..."+orderChildId+"....系统时间:"+new Date());
		tblItemDetailMapper.unlockItemByOrderChildId(orderChildId, ItemStatusEnum.UNSOLD.getValue());
	}
	
	/**
	 * 取餐完成更新单品、格子
	 * @param orderChildId
	 */
//	public void updateTakenItem(Long orderChildId){
//		/*更新单品、格子*/
//		tblItemDetailMapper.updateItemByOrderChildId(orderChildId, ItemStatusEnum.TOOK.getValue(), "0");
//	}
	public void updateTakenItem(String itemId){
		/*更新单品、格子*/
		tblItemDetailMapper.updateItemByitemId(itemId, ItemStatusEnum.TOOK.getValue(), "0");
	}
	
	/**
	 * 判断当前时间是否在取餐截止时间之前
	 * true：是，false：否
	 * @param endTime
	 * @return
	 */
	public boolean isBeforeEndTime(Date endTime){
		/*当前时间*/
		Date currentTime = new Date();
		Boolean flag = Boolean.FALSE;
		if (endTime.after(currentTime)){
			flag = Boolean.TRUE;
		}
		
		return flag;
	} 
	
	/**
	 * 判断当前时间是否在支付截止时间之前
	 * true：是，false：否
	 * @param lastPayTime
	 * @return
	 */
	public boolean isBeforeLastPayTime(Date lastPayTime){
		/*当前时间*/
		Date currentTime = new Date();
		Boolean flag = Boolean.FALSE;
		if (lastPayTime.after(currentTime)){
			flag = Boolean.TRUE;
		}
		
		return flag;
	} 
	
	/**
	 * 待付款订单付款后处理
	 * @param to
	 */
	public void changePaidOrderToTakenOrder(TblOrder to){
		/*删除该待处理订单*/
		tblPendingOrderMapper.deleteByOrderId(to.getOrderId());
		/*获取该订单下的所有子订单*/
		List<TblOrderChild> orderChildList = tblOrderChildMapper.selectByOrderId(to.getOrderId());
		List<TblPendingOrder> takenOrderList = new ArrayList<TblPendingOrder>();
		/*遍历子订单列表*/
		for (TblOrderChild orderChild : orderChildList){
			/*新增待取餐处理订单*/
			TblPendingOrder pendingOrder = new TblPendingOrder();
			pendingOrder.setPendingTypeNum("3");
			pendingOrder.setModeNum(to.getModeNum());
			pendingOrder.setOrderType(to.getOrderType());
			pendingOrder.setCompanyId(to.getCompanyId());
			pendingOrder.setOrderId(to.getOrderId());
			pendingOrder.setOrderNum(to.getOrderNum());
			pendingOrder.setOrderChildId(orderChild.getOrderChildId());
			pendingOrder.setOrderTime(to.getOrderTime());
			pendingOrder.setLastPayTime(to.getLastPayTime());
			pendingOrder.setBeginTime(orderChild.getBeginTime());
			pendingOrder.setEndTime(orderChild.getEndTime());
			
			takenOrderList.add(pendingOrder);
		}
		tblPendingOrderMapper.changePaidOrderToTakenOrder(takenOrderList);
		/*更新子订单*/
		updateTakenOrderChild(to, orderChildList);
	}
	
	/**
	 * 付款成功后更新子订单验证码以及子订单状态
	 * @param to
	 * @param orderChildList
	 */
	public void updateTakenOrderChild(TblOrder to, List<TblOrderChild> orderChildList){
		boolean flag = Boolean.FALSE;
		if (orderChildList == null || orderChildList.size() == 0){
			/*获取该订单下的所有子订单*/
			orderChildList = tblOrderChildMapper.selectByOrderId(to.getOrderId());
		}
		List<TblOrderChild> newOrderChildList = new ArrayList<TblOrderChild>();
		/*遍历子订单列表*/
		if (StringUtils.equals(to.getModeNum(), "0")){
			//设备端
			for (TblOrderChild orderChild : orderChildList){
				orderChild.setOrderChildStatus(OrderStatus.NO_TAKED.value);
				
				newOrderChildList.add(orderChild);
			}
			/*更新所有子订单*/
			tblOrderChildMapper.updateTakenOrderChild(newOrderChildList);
		} else {
			//移动端
			for (TblOrderChild orderChild : orderChildList){
				orderChild.setIdentifyingCode(VerifyCode.createVerifyCode());
				orderChild.setOrderChildStatus(OrderStatus.NO_TAKED.value);
				
				BasicsAreaModel basicsAreaModel = BasicsAreaModelMapper.selectByPrimaryKey(orderChild.getAreaModelId());
				String TimeType = basicsAreaModel.getTimeType();
				//当前为中餐模型
				if("1".equals(TimeType)){
					flag = Boolean.TRUE;
				}
				
				newOrderChildList.add(orderChild);
			}
			/*更新所有子订单*/
			tblOrderChildMapper.updateTakenOrderChild(newOrderChildList);
			/*发送验证码*/
			sendTakingGoodsVerifyCodeMessage(to.getOrderNum(),to.getMobile(), to.getOrderId(),to.getOrderType(),flag);
		}
	}
	
	/**
	 * 付款完成后发送取餐验证码
	 * @param mobile
	 * @param orderId
	 */
	public void sendTakingGoodsVerifyCodeMessage(String orderNum,String mobile, Long orderId,String orderType,Boolean flag){
		String address = tblOrderMapper.getAddressByOrderId(orderId);
		String verifyCode = tblOrderChildMapper.getTakingGoodsInfo(orderId);
		/*发送短信*/
		if("0".equals(orderType)){//预定订单提交
			aliSmsService.sendReserveTakingGoodsVerifyCode(orderNum,mobile, address, verifyCode,flag);
		
		}else if("1".equals(orderType)){//零售订单提交
			aliSmsService.sendRetailTakingGoodsVerifyCode(orderNum,mobile, address, verifyCode);
	
		}
		
	} 
	
	/**
	 * 首单优惠活动，取消首单后再次下单仍可优惠
	 * @param orderNum
	 */
/*	private void cancelFirstOrder(String orderNum){
		TblCustomerDisActLog tblCustomerDisActLog = tblCustomerDisActLogMapper.queryDisLogByOrderNum(orderNum);
		if (tblCustomerDisActLog != null){
			删除首单记录，更新首单优惠标志
			if (tblCustomerDisActLogMapper.deleteDisLog(orderNum) > 0){
				tblCustomerMapper.updateDiscountUsed(tblCustomerDisActLog.getCustomerId(), "0");
			}
		}
	}*/
	
	/**
	 * 以下为结算记录相关
	 * 更新结算记录 
	 */
	public int updateSettlements(){
		List<OrderByUserMap> orderMap = null;
		//结算状态  0 挂了   1 成功  2 还没数据  记得返回
		int state=1;
		try {
			Calendar now = Calendar.getInstance();
			//now.add(Calendar.DAY_OF_MONTH, -30);//提前30天
	        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now.getTime());
			orderMap = tblOrderChildMapper.selectByPendingSettlements(startTime);
			if(orderMap==null){
				//暂无结算数据
				return 2;
			}
			// 存储该方法的生成的结算记录（便于批量添加）
			List<TblBalanceAmount> amounts = new ArrayList<TblBalanceAmount>();
			for (OrderByUserMap orderByUserMap : orderMap) {
				// 三个循环的循环计数
				int i = 0, j = 0;
				BigDecimal amount;// 用于转换结余金额
				// 当前循环中的用户id
				Long customerId = orderByUserMap.getCustomerId();
				// 该用户的一段时间的未结算子订单
				List<TblOrderChild> orderChilds = orderByUserMap.getOrderChilds();
				// 该用户的的未结算充值记录
				List<TblRecharge> recharges = rechargeMapper.findRechargeForOrderByCustomerId(1305l, null);
				// 该用户上一次结算的最后一条记录
				TblRecharge recharge = rechargeMapper.findlastRechargeForOrderByCustomerId(1305l);
				/**
				 * 判断上次结算的最后一条该用户的充值记录是否还剩余结算金额
				 */
				if (recharge != null) {
					if (recharge.getBalanceAmount() != null) {
						int flag = recharge.getBalanceAmount().compareTo(BigDecimal.ZERO);
						/**
						 * 只有结算金额大于0时，把该条记录插入本次待结算充值记录的前面
						 */
						if (1 == flag) {
							if(recharges==null){
								recharges=new ArrayList<TblRecharge>();
								recharges.add(recharge);
							}else{
								recharges.add(0, recharge);
							}
						}
					}
				}
				if(recharges==null){
					continue;
				}
				while (i < recharges.size()) {
					// 把本次循环的recharges.get(i)取出，方便操作
					while (j < orderChilds.size()) {
						// 把本次循环的order取出，方便操作
						TblOrderChild order = orderChilds.get(j);
						/**
						 * 根据子订单id计算子订单总金额 为null则跳过该次循环
						 */
						BigDecimal orderCoin = orderDetailMapper.getSumMoneyByChildOrderId(order.getOrderChildId());
						if (orderCoin == null) {
							j++;
							continue;
						} else {
							order.setRefundMoney(orderCoin);
						}
						/**
						 * 如果该充值记录的结算金额等于null表示是一个新的充值记录
						 * 否则把充值总金额（包含赠送）赋予待结算金额getSumMoneyByChildOrderId
						 */
						if (recharges.get(i).getBalanceAmount() == null) {
							recharges.get(i).setBalanceAmount(recharges.get(i).getTotalMoney());
						}
						/**
						 * 判断三种情况
						 */
						int key = recharges.get(i).getBalanceAmount().compareTo(order.getRefundMoney());
						j++;
						if (1 == key) {
							// 当 结算金额 > 子订单总金额 时
							amount = recharges.get(i).getBalanceAmount().subtract(order.getRefundMoney());
							recharges.get(i).setBalanceAmount(amount);
							amounts.add(new TblBalanceAmount(recharges.get(i).getRechargeId(),
									recharges.get(i).getOrderNum(), order.getOrderChildId(), order.getOrderChildNum(),
									order.getRefundMoney(), order.getRefundMoney(), amount,
									recharges.get(i).getTotalMoney(), customerId));
							continue;
						} else if (0 == key) {
							// 当 结算金额 = 子订单总金额 时
							amount = recharges.get(i).getBalanceAmount().subtract(order.getRefundMoney());
							recharges.get(i).setBalanceAmount(amount);
							amounts.add(new TblBalanceAmount(recharges.get(i).getRechargeId(),
									recharges.get(i).getOrderNum(), order.getOrderChildId(), order.getOrderChildNum(),
									order.getRefundMoney(), order.getRefundMoney(), amount,
									recharges.get(i).getTotalMoney(), customerId));
							i++;
							break;
						} else if (-1 == key) {
							// 当 结算金额 < 子订单总金额 时
							// amount需要下个充值记录来补充子订单未抵消的金额
							amount = order.getRefundMoney().subtract(recharges.get(i).getBalanceAmount());
							amounts.add(new TblBalanceAmount(recharges.get(i).getRechargeId(),
									recharges.get(i).getOrderNum(), order.getOrderChildId(), order.getOrderChildNum(),
									order.getRefundMoney(), recharges.get(i).getBalanceAmount(), BigDecimal.ZERO,
									recharges.get(i).getTotalMoney(), customerId));
							recharges.get(i).setBalanceAmount(BigDecimal.ZERO);
							int k = 0;
							while ((i + k + 1) < recharges.size()) {
								recharges.get(i + k + 1);
								if (recharges.get(i + k + 1).getBalanceAmount() == null) {
									recharges.get(i + k + 1).setBalanceAmount(recharges.get(i + k + 1).getTotalMoney());
								}
								BigDecimal amount1=BigDecimal.ZERO;
								int key1 = recharges.get(i + k + 1).getBalanceAmount().compareTo(amount);
								if (1 == key1) {
									// 当 结算金额 > 子订单总金额 时
									amount1=amount;
									amount = recharges.get(i + k + 1).getBalanceAmount().subtract(amount);
									recharges.get(i).setBalanceAmount(amount);
									amounts.add(new TblBalanceAmount(recharges.get(i + k + 1).getRechargeId(),
											recharges.get(i + k + 1).getOrderNum(), order.getOrderChildId(),
											order.getOrderChildNum(), order.getRefundMoney(), amount1,
											amount, recharges.get(i + k + 1).getTotalMoney(), customerId));
									i += k++;
									break;
								} else if (0 == key1) {
									// 当 结算金额 = 子订单总金额 时
									amount1=amount;
									amount = recharges.get(i + k + 1).getBalanceAmount().subtract(amount);
									recharges.get(i + k + 1).setBalanceAmount(amount);
									amounts.add(new TblBalanceAmount(recharges.get(i + k + 1).getRechargeId(),
											recharges.get(i + k + 1).getOrderNum(), order.getOrderChildId(),
											order.getOrderChildNum(), order.getRefundMoney(),amount1,
											amount, recharges.get(i + k + 1).getTotalMoney(), customerId));
									i += k++;
									break;
								} else if (-1 == key1) {
									// 当 结算金额 < 子订单总金额 时
									amount = amount.subtract(recharges.get(i + k + 1).getBalanceAmount());
									amounts.add(new TblBalanceAmount(recharges.get(i + k + 1).getRechargeId(),
											recharges.get(i + k + 1).getOrderNum(), order.getOrderChildId(),
											order.getOrderChildNum(), order.getRefundMoney(),
											recharges.get(i + k + 1).getBalanceAmount(), BigDecimal.ZERO,
											recharges.get(i + k + 1).getTotalMoney(), customerId));
									recharges.get(i + k + 1).setBalanceAmount(BigDecimal.ZERO);
									// 找下一个充值记录来补充
									continue;
								}
								k++;
							}
							break;
						}
					}
					if (j == orderChilds.size()) {
						break;
					}
				}
				// 统计好修改状态的

				rechargeMapper.updateSignRecharges(recharges.subList(0,i+1));
				recharge=recharges.get(i);
				recharge.setBalanceAmountSign("1");
				rechargeMapper.updateByPrimaryKey(recharge);
				tblOrderChildMapper.updateSignOrderChild(orderChilds);
				balanceAmountMapper.insertList(amounts);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			state=0;
		}
		return state;
	} 
	public Map<String, Object> findTblBalanceAmounts (ServletRequest request, @RequestParam("rechargeId") String rechargeId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> orderDetailsList = balanceAmountMapper.findAllBalanceAmountList(rechargeId);
		System.out.println(orderDetailsList);
		map.put("rows", orderDetailsList);
		return map;
		
	}
	/**
	 * 结算记录相关结束
	 */
	//Gekko 借宝地一用，以下方法用于删除临时表内前7天的数据
	//delete from '表' where datediff(curdate(), '日期字段')>=7
	public void delExpireTemporary(){
		tblTemporaryRetailMapper.delExpireTemporary();
		tblTemporaryRetailMapper.delExpireTemporary1();
	}
	
}

