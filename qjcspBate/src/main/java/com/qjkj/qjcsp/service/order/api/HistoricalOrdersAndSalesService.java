package com.qjkj.qjcsp.service.order.api;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.TblOrderVo;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.order.app.OrderAppMapper;

@Service
public class HistoricalOrdersAndSalesService {
	@Autowired
	private TblOrderMapper tblOrderMapper;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;

	@Autowired
	private OrderAppMapper orderAppMapper;

	/**
	 * 获取历史订单
	 * @param res
	 * @return
	 */
	public Map<String, Object> getSaleCompleteOrders(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (res.has("userId") && res.has("specialRoleNum") && 
				StringUtils.isNoneBlank(res.getString("userId"), res.getString("specialRoleNum"))){
			/*用户id*/
			Long userId = res.getLong("userId");
			/*用户类型*/
			String specialRoleNum = res.getString("specialRoleNum");
			/*开始时间*/
			String startDate = null;
			if (res.has("startDate")) {
				startDate = res.getString("startDate");
			}
			/*结束时间*/
			String endDate = null;
			if (res.has("endDate")) {
				endDate = res.getString("endDate");
			}
			/*设备id*/
			Long machineId = null;
			if (res.has("machineId")) {
				machineId = res.getLong("machineId");
			}
			
			returnData = queryOrders(userId, specialRoleNum, startDate, endDate, machineId);
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}
		return returnData;
	}

	/**
	 * 获取订单
	 * @param userId
	 * @param specialRoleNum
	 * @param startDate
	 * @param endDate
	 * @param machineId
	 * @return
	 */
	private Map<String, Object> queryOrders(Long userId, String specialRoleNum,
			String startDate, String endDate, Long machineId){
		Map<String, Object> returnData = null;
		List<Map<String, Object>> tblOrderList = null;
		if (StringUtils.equals(specialRoleNum, "2") || StringUtils.equals(specialRoleNum, "3")) {
			/*2:补货人员,3:商户*/
 			tblOrderList = tblOrderMapper.queryOrderByUser(userId, specialRoleNum, startDate, endDate, machineId);
		} else if (StringUtils.equals(specialRoleNum, "4")) {
			/*4:众包商*/
			tblOrderList = tblOrderMapper.queryOrderByMerSys(userId, startDate, endDate, machineId);
		}
		returnData = getGoodsOrders(userId, specialRoleNum, startDate, endDate, machineId, tblOrderList);		
		return returnData;
	}

	/**
	 * 获取商品列表
	 * @param userId
	 * @param specialRoleNum
	 * @param startDate
	 * @param endDate
	 * @param machineId
	 * @param tblOrderList
	 * @return
	 */
	private Map<String, Object> getGoodsOrders(Long userId,String specialRoleNum, String startDate, String endDate,
			Long machineId, List<Map<String, Object>> tblOrderList) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (tblOrderList != null && tblOrderList.size() >= 0) {
			/*是否是商户*/
			boolean roleFlag = Boolean.FALSE;
			if (StringUtils.equals(specialRoleNum, "3")) {
				roleFlag = Boolean.TRUE;
			}
			/*订单数量*/
			int orderQuantity = 0;
			/*菜品总数量*/
			int totalDishAreaNum = 0;
			/*盈利  总销售额*/
			BigDecimal totalProfit = BigDecimal.valueOf(0);
			/*返回参数orderList*/
			List<Map<String, Object>> returnOrderList = new ArrayList<Map<String,Object>>();
			/*待优化*/
			/*遍历订单*/
			for (Map<String, Object> tblOrder : tblOrderList){
				List<Map<String, Object>> orderChildList = null;
				orderChildList = tblOrderMapper.queryOrderChild((Long)tblOrder.get("orderId"));
				/*该订单是否有订单明细*/
				boolean orderDetailFlag = Boolean.FALSE;
				/*遍历子订单*/
				for (Map<String, Object> orderChild : orderChildList){
					Map<String, Object> returnOrderMap = new HashMap<String, Object>();
					/*订单总价*/
					BigDecimal totalPrice = BigDecimal.valueOf(0);
					//String merchantName=null;
					List<Map<String, Object>> orderDetailList = null;
					if (roleFlag){
						/*商户*/
						orderDetailList = tblOrderMapper.queryOrderDetailByMer((Long)orderChild.get("orderChildId"), userId);
					} else{
						/*补货员、众包商*/
						orderDetailList = tblOrderMapper.queryOrderDetail((Long)orderChild.get("orderChildId"));
					}
					if (orderDetailList != null && orderDetailList.size() > 0){
						/*遍历订单明细*/
						for (Map<String, Object> orderDetail : orderDetailList){
							totalDishAreaNum += Integer.valueOf(orderDetail.get("dishAreaNum").toString());
							//盈利   总销售额
							//totalProfit = totalProfit.add((BigDecimal)orderDetail.get("profit"));
							if(!roleFlag){
								totalProfit = totalProfit.add((BigDecimal)orderDetail.get("profit"));
							}
							
							if(roleFlag){
								//订单总价  商户
								totalPrice = totalPrice.add(new BigDecimal(orderDetail.get("profit").toString()));
							}else{
								//订单总价  众包
								totalPrice = totalPrice.add(new BigDecimal(orderDetail.get("profits").toString()));
							}
							
						    //订单总价=商品单价乘以商品数量
							//totalPrice = totalPrice.multiply(new BigDecimal(orderDetail.get("dishAreaNum").toString()));
							//merchantName = orderDetail.get("companyName").toString();
						}
						returnOrderMap.put("orderNum", tblOrder.get("orderNum").toString());
						returnOrderMap.put("machineName", tblOrder.get("machineName"));
					/*	returnOrderMap.put("merchantName", merchantName);*/
						returnOrderMap.put("totalPrice", totalPrice.toString());
						/*returnOrderMap.put("orderStatus", tblOrder.get("orderStatus").toString());*/
						returnOrderMap.put("areaModelName", orderChild.get("areaModelName").toString());
						returnOrderMap.put("orderDetailList", orderDetailList);
						returnOrderList.add(returnOrderMap);
						if(roleFlag){
							//盈利   总销售额
							totalProfit = totalProfit.add((BigDecimal)totalPrice);
						}
						orderDetailFlag = Boolean.TRUE;
					}
				}
				if (orderDetailFlag){
					orderQuantity++;
				}
			}

			if (returnOrderList.size() > 0){
				Map<String, Object> returnContent = new HashMap<String, Object>();
				/*返回参数areaModelList*/
				List<Map<String, Object>> returnAreaModelList = new ArrayList<Map<String,Object>>();
				/*存储模型名称*/
				Set<String> areaModelNameSet = new HashSet<String>();
				/*returnOrderList根据areaModelName进行分组*/
				for (Map<String, Object> returnOrder : returnOrderList){
					if (!areaModelNameSet.contains(returnOrder.get("areaModelName").toString())){
						areaModelNameSet.add(returnOrder.get("areaModelName").toString());
					}
				}
				/*合并同一模型*/
				for (String areaModelName : areaModelNameSet){
					Map<String, Object> areaModelMap = new HashMap<String, Object>();
					areaModelMap.put("areaModelName", areaModelName);
					List<Map<String, Object>> orderList = new ArrayList<Map<String,Object>>();
					for (Map<String, Object> returnOrder : returnOrderList){
						if (StringUtils.equals(areaModelName, 
								returnOrder.get("areaModelName").toString())){
							orderList.add(returnOrder);
						}
					}
					areaModelMap.put("orderList", orderList);
					
					returnAreaModelList.add(areaModelMap);
				}
				returnContent.put("orderQuantity", orderQuantity+"");
				returnContent.put("totalDishAreaNum", totalDishAreaNum+"");
				returnContent.put("totalProfit", totalProfit.toString());
				returnContent.put("areaModelList", returnAreaModelList);
				
				returnData.put("returnCode", "1");
				returnData.put("returnContent", returnContent);
			} else{
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "暂无订单");
			}
		} else {
			returnData.put("returnCode", "2");
			returnData.put("returnContent", "暂无订单");
		}
		return returnData;
	}

	
	
	
	
	
	/**
	 * 菜品总销售
	 * @param res
	 * @return
	 */
	public Map<String, Object> getGoodsCompleteSale(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (res.has("userId") && res.has("specialRoleNum") && 
				StringUtils.isNoneBlank(res.getString("userId"), res.getString("specialRoleNum"))){
			/*用户id*/
			Long userId = res.getLong("userId");
			/*用户类型*/
			String specialRoleNum = res.getString("specialRoleNum");
			/*开始时间*/
			String startDate = null;
			if (res.has("startDate")) {
				startDate = res.getString("startDate");
			}
			/*结束时间*/
			String endDate = null;
			if (res.has("endDate")) {
				endDate = res.getString("endDate");
			}
			/*设备id*/
			Long machineId = null;
			if (res.has("machineId")) {
				machineId = res.getLong("machineId");
			}
			
			returnData = queryGoodsCompleteSale(userId, specialRoleNum, startDate, endDate, machineId);
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}
		return returnData;
	}

	/**
	 * 获取订单
	 * @param userId
	 * @param specialRoleNum
	 * @param startDate
	 * @param endDate
	 * @param machineId
	 * @return
	 */
	private Map<String, Object> queryGoodsCompleteSale(Long userId, String specialRoleNum, 
			String startDate, String endDate, Long machineId) {
		Map<String, Object> returnData = null;
		List<Map<String, Object>> tblOrderList = null;
		if (StringUtils.equals(specialRoleNum, "2") || StringUtils.equals(specialRoleNum, "3")) {
			/*2:补货人员,3:商户*/
			tblOrderList = tblOrderMapper.queryOrderByUser(userId, specialRoleNum, startDate, endDate, machineId);
		} else if (StringUtils.equals(specialRoleNum, "4")) {
			/*4:众包商*/
			tblOrderList = tblOrderMapper.queryOrderByMerSys(userId, startDate, endDate, machineId);
		}
		returnData = getGoodsList(userId, specialRoleNum, startDate, endDate, machineId, tblOrderList);
		
		return returnData;
	}

	
	
	
	
	/**
	 * 获取商品列表
	 * @param userId
	 * @param specialRoleNum
	 * @param startDate
	 * @param endDate
	 * @param machineId
	 * @param tblOrderList
	 * @return
	 */
	private Map<String, Object> getGoodsList(Long userId,String specialRoleNum, 
			String startDate, String endDate, Long machineId, 
			List<Map<String, Object>> tblOrderList) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (tblOrderList != null && tblOrderList.size() >= 0) {
			/*是否是商户*/
			boolean roleFlag = Boolean.FALSE;
			if (StringUtils.equals(specialRoleNum, "3")) {
				roleFlag = Boolean.TRUE;
			}
			/*菜品总数量*/
			int totalDishAreaNum = 0;
			/*盈利*/
			BigDecimal totalProfit = BigDecimal.valueOf(0);

			List<Map<String, Object>> returnOrderList = new ArrayList<Map<String,Object>>();
			/*待优化*/
			/*遍历订单*/
			for (Map<String, Object> tblOrder : tblOrderList){
				List<Map<String, Object>> orderChildList = null;
				orderChildList = tblOrderMapper.queryOrderChild((Long)tblOrder.get("orderId"));
				/*遍历子订单*/
				for (Map<String, Object> orderChild : orderChildList){
					Map<String, Object> returnOrderMap = new HashMap<String, Object>();
					List<Map<String, Object>> orderDetailList = null;
					if (roleFlag){
						/*商户 3*/
						orderDetailList = tblOrderMapper.queryOrderDetailByMer((Long)orderChild.get("orderChildId"), userId);
					} else{
						/*补货员、众包商*/
						orderDetailList = tblOrderMapper.queryOrderDetail((Long)orderChild.get("orderChildId"));
					}
					if (orderDetailList != null && orderDetailList.size() > 0){
						/*遍历订单明细*/
						for (Map<String, Object> orderDetail : orderDetailList){
							totalDishAreaNum += Integer.valueOf(orderDetail.get("dishAreaNum").toString());
							totalProfit = totalProfit.add((BigDecimal)orderDetail.get("profit"));
						}
						returnOrderMap.put("areaModelName", orderChild.get("areaModelName").toString());
						returnOrderMap.put("orderDetailList", orderDetailList);			
						returnOrderList.add(returnOrderMap);
					}
				}
			}

			if (returnOrderList.size() > 0){
				Map<String, Object> returnContent = new HashMap<String, Object>();
				List<Map<String, Object>> areaModelList = new ArrayList<Map<String,Object>>();
				/*模型名称*/
				Set<String> areaModelNameSet = new HashSet<String>();
				/*returnOrderList根据areaModelName进行分组*/
				for (Map<String, Object> returnOrder : returnOrderList){
					if (!areaModelNameSet.contains(returnOrder.get("areaModelName").toString())){
						areaModelNameSet.add(returnOrder.get("areaModelName").toString());
					}
				}
				/*合并同一模型*/
				for (String areaModelName : areaModelNameSet){
					Map<String, Object> areaModelMap = new HashMap<String, Object>();
					areaModelMap.put("areaModelName", areaModelName);
					
					List<Map<String, Object>> orderList = new ArrayList<Map<String,Object>>();
					for (Map<String, Object> returnOrder : returnOrderList){
						if (StringUtils.equals(areaModelName, 
								returnOrder.get("areaModelName").toString())){
							orderList.add(returnOrder);
						}
					}
					areaModelMap.put("orderList", orderList);
					
					areaModelList.add(areaModelMap);
				}
				/*合并同一模型下的相同商品*/
				List<Map<String, Object>> returnAreaModelList = new ArrayList<Map<String,Object>>(); 
				for (Map<String, Object> areaModel : areaModelList){
					List<Map<String, Object>> orderList = (List<Map<String, Object>>) areaModel.get("orderList");
					/*模型名称*/
					Set<String> goodsNameSet = new HashSet<String>();
					/*根据goodsName进行分组*/
					for (Map<String, Object> order : orderList){
						List<Map<String, Object>> orderDetailList = (List<Map<String, Object>>)order.get("orderDetailList");
						for (Map<String, Object> orderDetail : orderDetailList){
							if (!goodsNameSet.contains(orderDetail.get("goodsName").toString())){
								goodsNameSet.add(orderDetail.get("goodsName").toString());
							}
						}
					}
					/*合并同一商品*/
					Map<String, Object> areaModelMap = new HashMap<String, Object>();
					areaModelMap.put("areaModelName", areaModel.get("areaModelName"));
					List<Map<String, Object>> returnOrderDetailList = new ArrayList<Map<String,Object>>();
					for (String goodsName : goodsNameSet){
						Map<String, Object> areaDetailMap = new HashMap<String, Object>();
						areaDetailMap.put("goodsName", goodsName);
						/*商品数量*/
						int dishAreaNum = 0;
						for (Map<String, Object> order : orderList){
							List<Map<String, Object>> orderDetailList = (List<Map<String, Object>>)order.get("orderDetailList");
							for (Map<String, Object> orderDetail : orderDetailList){
								if (StringUtils.equals(goodsName, orderDetail.get("goodsName").toString())){
									dishAreaNum += Integer.valueOf(orderDetail.get("dishAreaNum").toString());
								}
							}
						}
						areaDetailMap.put("dishAreaNum", dishAreaNum);
						returnOrderDetailList.add(areaDetailMap);
					}
					areaModelMap.put("orderDetailList", returnOrderDetailList);
					returnAreaModelList.add(areaModelMap);
				}
					
				returnContent.put("totalDishAreaNum", totalDishAreaNum+"");
				returnContent.put("totalProfit", totalProfit.toString());
				returnContent.put("areaModelList", returnAreaModelList);
				
				returnData.put("returnCode", "1");
				returnData.put("returnContent", returnContent);
			} else{
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "暂无数据");
			}
		} else {
			returnData.put("returnCode", "2");
			returnData.put("returnContent", "暂无数据");
		}
		return returnData;
	}
}
