package com.qjkj.qjcsp.service.order.app;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.order.Goods;
import com.qjkj.qjcsp.entity.order.OrderComment;
import com.qjkj.qjcsp.entity.order.OrderDeviceEvaluate;
import com.qjkj.qjcsp.mapper.TblGoodsEvaluateMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderPickDetailMapper;
import com.qjkj.qjcsp.mapper.order.app.OrderAppMapper;
import com.qjkj.qjcsp.service.refund.RefundOrderService;

import net.sf.json.JSONObject;

@Service
public class OrderAppService {

	@Autowired
	private OrderAppMapper orderAppMapper;
	
	@Autowired
	private RefundOrderService refundOrderService;

	@Autowired
	private TblGoodsEvaluateMapper tblGoodsEvaluateMapper;
	@Autowired
	private TblItemDetailMapper tblItemDetailMapper;
	@Autowired
	private TblOrderPickDetailMapper tblOrderPickDetailMapper;
	
	/**
	 * 2.3.14. 根据登录用户用户id和订单状态获取相应条件订单列表
	 * 
	 * @return
	 */
	public Map<String, Object> getOrdersByStatus(@SuppressWarnings("rawtypes") Map map) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		List<Map<String, Object>> listMap = orderAppMapper.getOrdersByStatus(map);
		// 判断这个请求是否来自退款和售后的请求
		if ("5".equals(map.get("orderStatus"))) {
			// 遍历查找这个集合
			if (listMap != null && listMap.size() > 0) {
				for (int i = 0; i < listMap.size(); i++) {
					Map<String, Object> mapm = listMap.get(i);
					// 根据订单号去退款表中查找，判断是否有被审核过
					String orderStatus = orderAppMapper.getOrderStatusForRefund((String) mapm.get("orderNum"));
					if (orderStatus != null) {
						mapm.put("orderStatus", orderStatus);
					}
				}
			}
			// 查找这个用户订单状态为1的这些订单号
			List<Map<String, Object>> listMapStatus = orderAppMapper.getCustomerOrders(map);
			if (listMapStatus != null && listMapStatus.size() > 0) {
				for (int i = 0; i < listMapStatus.size(); i++) {
					Map<String, Object> mapm = listMapStatus.get(i);
					// 根据订单号去设备故障表中查找，判断是否有订单存在
					Long orderChildId = orderAppMapper.getOrderStatusForAlam(Long.valueOf((String) mapm.get("orderChildId")));
					if (orderChildId != null) {
						mapm.put("orderStatus", "10");
						listMap.add(mapm);
					}
				}
			}
		}else{
		// 判断这个请求是查询全部
//		if ("-1".equals(map.get("orderStatus"))) {
			// 遍历查找这个集合
			if (listMap != null && listMap.size() > 0) {
				//存放要删除的mapm
				List<Map<String, Object>> temp=new ArrayList<Map<String, Object>>();
				for (int i = 0; i < listMap.size(); i++) {
					Map<String, Object> mapm = listMap.get(i);
					// 根据订单号去退款表中查找，判断是否有被审核过
					String orderStatus = (String) mapm.get("orderStatus");
					String orderType = (String) mapm.get("orderType");
					String orderId = (String) mapm.get("orderId");
					
					// 如果订单已支付并且是预订订单，修改状态为已预订
					if ("1".equals(orderStatus) && "0".equals(orderType)) {
						//预定:0,1,2,3,13,14显示 xlk start
						/*mapm.put("orderStatus", "11");*/
						// 如果订单已支付并且是预订订单，修改状态为已支付
						mapm.put("orderStatus", "1");
						//预定:0,1,2,3,13,14显示 xlk end
						List<Map<String, Object>> listOrderChild = orderAppMapper
								.getOrderChildByOrderId(Long.parseLong(orderId));
						String getorderStatus = "";
						// 将查询到的所有子订单状态组装成字符串
						for (int j = 0; j < listOrderChild.size(); j++) {
							getorderStatus += (String) listOrderChild.get(j).get("orderChildStatus") + ",";
						}
						
						/*// 判断是否存在已取货并且不存在已付款状态
						if (getorderStatus.indexOf("3") != -1 && getorderStatus.indexOf("1") == -1) {
							mapm.put("orderStatus", "7");
						}*/
						//预定:0,1,2,3,13,14显示 xlk start
						// 判断是否存在已退款并且不存在已付款状态
						/*if (getorderStatus.indexOf("9") != -1 && getorderStatus.indexOf("1,") == -1) {
							mapm.put("orderStatus", "7");
						}*/
						//判断是否存在取餐超时并且不存在待取餐状态
						/*if(getorderStatus.indexOf("4") != -1&&getorderStatus.indexOf("1,") == -1){
							mapm.put("orderStatus", "4");
						}*/
						//预定:0,1,2,3,13,14显示 xlk end
						//Gekko判断是否存在取餐故障并且不存在待取餐状态
						if(getorderStatus.indexOf("10") != -1&&getorderStatus.indexOf("1,") == -1){
							mapm.put("orderStatus", "10");
						}
						// 判断是否存在已取货并且不存在已付款状态 xlk
						if (getorderStatus.indexOf("3") != -1 && getorderStatus.indexOf("1,") == -1) {
							mapm.put("orderStatus", "3");
						}
						//查询待评价时,如果订单状态有待取餐状态,则移除
					if(getorderStatus.indexOf("1,")!=-1&&"3".equals(map.get("orderStatus"))){
						temp.add(mapm);
					}
						
					}else if("1".equals(orderStatus) && "1".equals(orderType)){
						List<Map<String, Object>> listOrderChild = orderAppMapper
								.getOrderChildByOrderId(Long.parseLong(orderId));
						mapm.put("orderStatus", listOrderChild.get(0).get("orderChildStatus"));	
					}
					//判断故障订单的所有菜品是否已取 xlk
					if(mapm.get("orderStatus").equals("10")){
						//查询该订单下单品状态不等于3的数量
						int count2=tblItemDetailMapper.selectCountByOrderId(Long.valueOf(orderId));
						//如果所有菜品已取,订单状态为14(故障取餐,不能评价)
						if(count2==0){
							mapm.put("orderStatus", "14");
							/*//根据orderNum查找该订单下全部记录与故障取餐记录的差
							int count=tblOrderPickDetailMapper.selectCountByOrderNum((String) mapm.get("orderNum"));
						*/
							//查找改订单下故障取餐的数量
							int count=tblOrderPickDetailMapper.selectAlarmCountByOrderNum((String) mapm.get("orderNum"));
							if(count>0){
								mapm.put("orderStatus", "10");
							}
						}
					}
					//订单状态为故障的,不显示在待评价列表
					/*if(mapm.get("orderStatus").equals("10")&&"3".equals(map.get("orderStatus"))){
						temp.add(mapm);
					}*/
					//预定:0,1,2,3,13,14显示 xlk start
					//预定订单状态为设备故障时,改为已支付 xlk
					if(mapm.get("orderStatus").equals("10")&&"0".equals(orderType)){
						mapm.put("orderStatus", "1");
					}
					//预定:0,1,2,3,13,14显示 xlk end
					//判断订单的所有菜品是否已取 xlk
					if(mapm.get("orderStatus").equals("3")){
						int count2=tblItemDetailMapper.selectCountByOrderId(Long.valueOf(orderId));
						if(count2!=0){
							//预定:0,1,2,3,13,14显示 xlk start
							/*mapm.put("orderStatus", "11");*/
							mapm.put("orderStatus", "1");
							//预定:0,1,2,3,13,14显示 xlk end
						}
					}
					//判断改订单是否已评价 xlk
				int count=tblGoodsEvaluateMapper.getCountByOrderId(Long.valueOf(orderId));
				if(count>0){
					mapm.put("orderStatus","13");
				}
				//查询待评价时,把已评价的放入temp中,遍历完成后删除
				if("3".equals(map.get("orderStatus"))&&"13".equals(mapm.get("orderStatus"))){
					temp.add(mapm);
				}
				
				}
				//遍历删除
				for(Map<String, Object> map2:temp){
					listMap.remove(map2);
				}
			}
		}
		returnContent.put("returnCode", "1");
		returnContent.put("returnContent", listMap);
		return returnContent;
	}

	/**
	 * 2.3.13. 根据登录用户用户id和订单状态获取订单分状态数量
	 * 
	 * @param customerId
	 * @return
	 *
	 */
	@Transactional
	public Map<String, Object> getOrderCountByStatus(int customerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据用户id,得到待付款数量
		String readyPayCount = orderAppMapper.getReadyPayCount(customerId);
		// 待取餐数量
		String readyPickCount = orderAppMapper.getReadyPickCount(customerId);
		// 待评价数量
		String readyCommentCount = orderAppMapper.getReadyCommentCount(customerId);
		// 用户订单数量
		String orderAmountNum = orderAppMapper.getOrderAmountNum(customerId);
		// 售后/退款数量
		String readyDealCount = refundOrderService.getRefundOrderList(customerId+"")+"";
		// 查找这个用户订单状态为1的这些订单号
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("customerId", customerId);
		List<Map<String, Object>> listMapStatus = orderAppMapper.getCustomerOrders(map2);
		if (listMapStatus != null && listMapStatus.size() > 0) {
			for (int i = 0; i < listMapStatus.size(); i++) {
				Map<String, Object> mapm = listMapStatus.get(i);
				// 根据订单号去设备故障表中查找，判断是否有订单存在
//				Long orderChildId = orderAppMapper.getOrderStatusForAlam(Long.valueOf((String) mapm.get("orderChildId")));
//				if (orderChildId != null) {
//					readyDealCount = String.valueOf(Integer.valueOf(readyDealCount) + 1);
//				}
			}
		}

		map.put("readyPayCount", readyPayCount);
		map.put("readyPickCount", readyPickCount);
		map.put("readyCommentCount", readyCommentCount);
		map.put("readyDealCount", readyDealCount);
		map.put("orderAmountNum", orderAmountNum);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("returnContent", map);
		map1.put("returnCode", "1");
		return map1;
	}
	
	@Transactional
	public void commitOrderComment(String orderNum,Long customerId,String orderGrade,String orderComment,List<Goods>goods ){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orderNum", orderNum);
		map.put("customerId", customerId);
		//根据订单号和客户id得到订单明细表
		List<OrderComment> orderComs =orderAppMapper.getOrderInfo(map);
		//遍历订单明细表，根据不同的商品id设置不同的评分
		for(OrderComment comment:orderComs){
			comment.setEvaluateTime(new Date());
			//遍历传过来的商品
			for(Goods good:goods){
				//商品id
				if(good.getGoodsId().equals(comment.getGoodsId())){
					//商品的评论
					comment.setEvaluateMemo(good.getCommentContent());
					//商品的评分
					comment.setEvaluateScore(Integer.valueOf(good.getItemGrade()));
				}
			}
		}
		
		//将数据插入评价表中
	    orderAppMapper.insertOrderComment(orderComs);
	    //根据订单号得到订单的一些基本信息
	    OrderDeviceEvaluate orderDeviceEvaluate = orderAppMapper.getOrderDeviceEvaluate(orderNum);
	    orderDeviceEvaluate.setEvaluateTime(new Date());
	    orderDeviceEvaluate.setEvaluateMemo(orderComment);
	    orderDeviceEvaluate.setEvaluateScore(Integer.valueOf(orderGrade));
	    //将订单设备评价信息插入到表中
	    orderAppMapper.insertOrderDeviceEvaluate(orderDeviceEvaluate);
	    //修改订单状态：设置为7 完成
	    orderAppMapper.afterCommentUpdateOrderStatus(orderNum);
	}
	
	/**
	 * 2.3.22.	用户订单删除
	 * @author yehx
	 * @date 2016年1月13日
	 *
	 */
	@Transactional
	public void customerOrderDelete(Map map){
		//删除总订单信息
		orderAppMapper.customerOrderDelete(map);
		//按订单号获取相应的订单id
		List<Map<String, Object>> list= orderAppMapper.getAllOrderIdByOrderNums(map);
		Map<String, Object> map2=new HashMap<String, Object>();
		map2.put("orderIds", list);
		//通过订单id去假删除对应的子订单数据
		orderAppMapper.customerChildOrderDelete(map2);
	}
	
	/**
	 * 实时订单
	 * @param res
	 * @return
	 */
	public Map<String, Object> getRealTimeOrders(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (res.has("userId") && res.has("specialRoleNum") && res.has("timeType") &&
				StringUtils.isNoneBlank(res.getString("userId"), res.getString("specialRoleNum"), res.getString("timeType"))){
			/*用户id*/
			Long userId = res.getLong("userId");
			/*用户类型*/
			String specialRoleNum = res.getString("specialRoleNum");
			/*模型id*/
//			Long areaModelId = null;
//			if (res.has("areaModelId")){
//				areaModelId = res.getLong("areaModelId");
//			}
			/*模型类型*/
			String timeType = res.getString("timeType");
			/*设备id*/
			Long machineId = null;
			if (res.has("machineId")){
				machineId = res.getLong("machineId");
			}
			returnData = queryRealTimeOrders(userId, specialRoleNum, timeType, machineId);
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}
		
		return returnData;
	}
	
	/**
	 * 获取实时订单
	 * @param userId
	 * @param specialRoleNum
	 * @param areaModelId
	 * @param machineId
	 * @return
	 */
	private Map<String, Object> queryRealTimeOrders(Long userId, String specialRoleNum, String timeType, Long machineId){
		Map<String, Object> returnData = null;
		/*查找订单*/
		List<Map<String, Object>> tblOrderList = null;
		if (StringUtils.equals(specialRoleNum, "2") || StringUtils.equals(specialRoleNum, "3")){
			/*2：补货人员,3：商户*/
			tblOrderList = orderAppMapper.queryRealTimeTblOrder(userId, specialRoleNum, machineId);
		} else if (StringUtils.equals(specialRoleNum, "4")){
			 /*4：众包商*/
			tblOrderList = orderAppMapper.queryRealTimeTblOrderByMerSys(userId, machineId);
		}
		returnData = getRealTimeGoodsList(userId, specialRoleNum, timeType, machineId, tblOrderList);
		
		return returnData;
	}
	
	/**
	 * 补货人员、商户、众包商获取商品列表
	 * @param userId
	 * @param specialRoleNum
	 * @param areaModelId
	 * @param machineId
	 * @param tblOrderList
	 * @return
	 */
	private Map<String, Object> getRealTimeGoodsList(Long userId, String specialRoleNum, String timeType, 
			Long machineId, List<Map<String, Object>> tblOrderList){
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (tblOrderList != null && tblOrderList.size() > 0){
			/*是否是商户*/
			boolean flag = Boolean.FALSE;
			if (StringUtils.equals(specialRoleNum, "3")){
				flag = Boolean.TRUE;
			}
			List<Map<String, Object>> returnContent = new ArrayList<Map<String,Object>>();
			
			/*待优化*/
			for (Map<String, Object> tblOrder : tblOrderList){
				Map<String, Object> content = new HashMap<String, Object>();
				BigDecimal orderTotalPrice = BigDecimal.valueOf(0);
				/*商品列表*/
				List<Map<String, Object>> goodsList = null;
				if (flag){
					/*商户*/
					//零售
					if((tblOrder.get("orderType").toString()).equals("1")){
						goodsList = orderAppMapper.queryRealTimeGoodsListByMer(userId, (Long)tblOrder.get("orderId"), timeType);
						if(goodsList!=null){
							for (Map<String, Object> orderDetail : goodsList){
								orderTotalPrice = orderTotalPrice.add((BigDecimal)orderDetail.get("retailPrice"));
							}	
						}
					//预订
					}else if((tblOrder.get("orderType").toString()).equals("0")){
						goodsList = orderAppMapper.queryRealTimeGoodsListByBooking(userId, (Long)tblOrder.get("orderId"), timeType);
						if(goodsList!=null){
							for (Map<String, Object> orderDetail : goodsList){
								orderTotalPrice = orderTotalPrice.add((BigDecimal)orderDetail.get("retailPrice"));
							}	
						}
					}
				} else{
					/*补货人员、众包商*/
					//零售
                     if((tblOrder.get("orderType").toString()).equals("1")){
                    	 goodsList = orderAppMapper.queryRealTimeGoodsList((Long)tblOrder.get("orderId"), timeType);
                    	 if(goodsList!=null){
 							for (Map<String, Object> orderDetail : goodsList){
 								orderTotalPrice = orderTotalPrice.add((BigDecimal)orderDetail.get("retailPrice"));
 							}	
 						}
                    	 
                    	//预订
                    	 
                     }else if((tblOrder.get("orderType").toString()).equals("0")){
                    	 goodsList=orderAppMapper.queryRealTimeGoodsListBooking((Long)tblOrder.get("orderId"), timeType);
                    	 if(goodsList!=null){
 							for (Map<String, Object> orderDetail : goodsList){
 								orderTotalPrice = orderTotalPrice.add((BigDecimal)orderDetail.get("retailPrice"));
 							}	
 						}
                     }
				}
				if (goodsList !=null && goodsList.size() > 0){
					content.put("orderNum", tblOrder.get("orderNum").toString());
					content.put("machineName", tblOrder.get("machineName").toString());
					content.put("orderTotalPrice", orderTotalPrice.toString());//tblOrder.get("orderTotalPrice").toString()
					content.put("goodsList", goodsList);
					
					returnContent.add(content);
				}
			}
			if (returnContent.size() > 0){
				returnData.put("returnCode", "1");
				returnData.put("returnContent", returnContent);
			} else {
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "暂无订单");
			}
		} else {
			returnData.put("returnCode", "2");
			returnData.put("returnContent", "暂无订单");
		}
		return returnData;
	}
	
}
