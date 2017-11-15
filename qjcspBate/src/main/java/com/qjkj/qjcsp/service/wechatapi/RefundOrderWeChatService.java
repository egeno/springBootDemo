package com.qjkj.qjcsp.service.wechatapi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;

import net.sf.json.JSONObject;

/**
 * 2.3.29. 售后/退款订单列表; 2.3.30. 售后/退款订单详情
 * 
 * @author carpeYe 2016-04-21
 *
 */
@Service
public class RefundOrderWeChatService {

	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;

	public Map<String, Object> refundOrderListWX(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
		/* 获取APP端传入顾客Id */
		String customerId = res.getString("customerId");
		if (StringUtils.isNoneBlank(customerId)) {
			List<Map<String, Object>> refundOrderList = tblOrderChildMapper
					.getRefundOrderList(Long.valueOf(customerId));
			for (int i = refundOrderList.size()-1; i>=0; i--) {
				/* 获取总订单 */
				Map<String, Object> refundOrder = refundOrderList.get(i);
				/* 获取用户订单申请退款前的订单的状态 */
				String refundReasonOrderChildStatus = refundOrder.get("refundReasonOrderChildStatus").toString();
				/*
				 * 1.如果 refundReasonOrderChildStatus==取餐超时直接将map取出存入returnData中，
				 * 并在refundOrderList中删除此map
				 */
				if (refundReasonOrderChildStatus.equals(OrderStatus.TIMEOUT.value)) {
					returnData.add(refundOrder);
					refundOrderList.remove(i);

				} else {
					String orderNum = refundOrder.get("orderNum").toString();
					BigDecimal refundMoney = new BigDecimal("0");
					Integer goodsNum=0;
					for (int j = refundOrderList.size()-1; j >=0; j--) {
						if (refundOrderList.get(j).get("orderNum").equals(orderNum)) {
							BigDecimal money = new BigDecimal(refundOrderList.get(j).get("refundMoney").toString());
							refundMoney = refundMoney.add(money);
							goodsNum=goodsNum+Integer.valueOf(refundOrderList.get(j).get("goodsNum").toString());
							refundOrderList.remove(j);
						}
					}
					refundOrder.put("refundMoney", refundMoney + "");
					refundOrder.put("goodsNum", goodsNum + "");
					returnData.add(refundOrder);
				}
				i=refundOrderList.size();
			}
			returnContent.put("returnCode", "1");
			returnContent.put("returnContent", returnData);
		} else {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "请求参数异常！");
		}
		return returnContent;
	}

	/*
	 * 售后/退款订单详情
	 */
	public Map<String, Object> refundOrderDetailWX(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		Map<String, Object> returnData = new HashMap<String, Object>();
		String orderNum = res.getString("orderNum");
		String refundReasonOrderChildStatus = res.getString("refundReasonOrderChildStatus");
		String orderChildNum=res.getString("orderChildNum");
		if (StringUtils.isNoneBlank(orderNum, refundReasonOrderChildStatus,orderChildNum)) {
			/*当前refundReasonOrderChildStatus为取餐超时*/
			if(refundReasonOrderChildStatus.equals(OrderStatus.NO_TAKED.value)){
				orderChildNum=null;
			}
			/* 获取这个总订单下的所有子订单 */
			List<Map<String, Object>> orders = tblOrderChildMapper
					.getOrderChildByOrderNumAndrefundReasonOrderChildStatus(orderNum, refundReasonOrderChildStatus,orderChildNum);
			if(orders.size()!=0){
				returnData.put("orderNum", orders.get(0).get("orderNum"));
				returnData.put("mobile", orders.get(0).get("mobile"));
				returnData.put("address", orders.get(0).get("address"));
				returnData.put("orderTime", orders.get(0).get("orderTime"));
				returnData.put("totalFee", orders.get(0).get("totalFee"));
				returnData.put("refundComment", orders.get(0).get("refundComment"));
				returnData.put("orderStatus", orders.get(0).get("orderStatus"));
				BigDecimal refundMoney = new BigDecimal("0");
				Integer goodsNum=0;
				/* 遍历所有总订单下所有子订单 */
				for (int i = 0; i < orders.size(); i++) {
					/* 获取子订单下所有订单明细 */
					List<Map<String, Object>> map = tblOrderChildMapper
							.getOrderDetailsByOrderNum(orders.get(i).get("orderChildNum").toString());
					//计算总退款金额
					refundMoney = refundMoney.add(new BigDecimal(orders.get(i).get("refundMoney").toString()));
					for (Map<String, Object> map2 : map) {
						goodsNum=goodsNum+Integer.valueOf(map2.get("goodsNum").toString());
					}
					/*计算菜品的数量*/
					
					/*订单明细存入子订单中*/
					orders.get(i).put("orderDetailList", map);
				}
				returnData.put("orderChildNum", goodsNum);
				returnData.put("totalRefundMoney", refundMoney + "");
				returnData.put("orderChildList", orders);
				returnContent.put("returnCode", "1");
				returnContent.put("returnContent", returnData);
			}else{
				returnContent.put("returnCode", "0");
				returnContent.put("returnContent", "请求参数有误！");
			}
			
		} else {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "请求参数异常！");
		}
		return returnContent;
	}
	
	/*获取退款的数量*/
	public Integer getRefundOrderList(String customerId) {
		//Map<String, Object> returnContent = new HashMap<String, Object>();
		List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
		/* 获取APP端传入顾客Id */
		//String customerId = res.getString("customerId");
		if (StringUtils.isNoneBlank(customerId)) {
			List<Map<String, Object>> refundOrderList = tblOrderChildMapper
					.getRefundOrderList(Long.valueOf(customerId));
			for (int i = refundOrderList.size()-1; i>=0; i--) {
				/* 获取总订单 */
				Map<String, Object> refundOrder = refundOrderList.get(i);
				/* 获取用户订单申请退款前的订单的状态 */
				String refundReasonOrderChildStatus = refundOrder.get("refundReasonOrderChildStatus").toString();
				/*
				 * 1.如果 refundReasonOrderChildStatus==取餐超时直接将map取出存入returnData中，
				 * 并在refundOrderList中删除此map
				 */
				if (refundReasonOrderChildStatus.equals(OrderStatus.TIMEOUT.value)) {
					returnData.add(refundOrder);
					refundOrderList.remove(i);

				} else {
					String orderNum = refundOrder.get("orderNum").toString();
					for (int j = refundOrderList.size()-1; j >=0; j--) {
						if (refundOrderList.get(j).get("orderNum").equals(orderNum)) {
							refundOrderList.remove(j);
						}
					}
					returnData.add(refundOrder);
				}
				i=refundOrderList.size();
			}			
		} 
		/*统计待退款的订单的数量*/
		Integer index=0;
		for (int i = 0; i < returnData.size(); i++) {
			if(returnData.get(i).get("orderStatus").equals(OrderStatus.REFOUND.value)){
				index++;
			}
		}
		return index;
	}

}
