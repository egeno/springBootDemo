package com.qjkj.qjcsp.service.order.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;

import net.sf.json.JSONObject;

/**
 * 订单待取餐明细
 * @author carpeYe 2016-01-06
 *
 */
 @Service
public class OrderReadyPickDetailService {
	 
	private static Logger logger = LoggerFactory.getLogger(OrderReadyPickDetailService.class);
	 @Autowired
	 private TblOrderDetailMapper tblOrderDetailMapper;
	 
	 @Autowired
	 private TblItemDetailMapper tblItemDetailMapper;
	 
	 @Autowired
	 private TblOrderMapper tblOrderMapper;
	 @Autowired
	 private BasicsMachineMapper basicsMachineMapper;
	 @Autowired
	 private TblOrderChildMapper tblOrderChildMapper;
	 /*
	  * 订单待取餐明细
	  */
	public Map<String,Object> getOrderReadyPickDetail(JSONObject res){
		 Map<String, Object> returnJson=new HashMap<String, Object>();
		 String deviceCode=res.getString("deviceCode");
		 String orderNum=res.getString("orderNum");	 
		 if(StringUtils.isNoneBlank(deviceCode,orderNum)){
			 try {
				quickPay(res);//闪付
				Long machineId=basicsMachineMapper.getMachineIdByDeviceCode(deviceCode);
				//List<Map> itemList= tblItemDetailMapper.findItemDetailBydeviceCodeAndOrderNum(map);
				List<Map<String,Object>> returnContent=tblOrderDetailMapper.findOrderDetailsByDeviceCodeAndOrderNum(orderNum,machineId);
				for (Map<String,Object> map2 : returnContent) {
					List<Map<String,Object>> itemList= tblItemDetailMapper.findItemDetailBydeviceCodeAndOrderNum(Long.valueOf(map2.get("orderDetailId").toString()),machineId);
					map2.put("itemList", itemList);
				}
				returnJson.put("returnCode", "1");
				returnJson.put("returnContent", returnContent);
			} catch (Exception e) {
				logger.error("订单待取餐明细",e);
				returnJson.put("returnCode", "0");
				returnJson.put("returnContent", "查询订单待取餐明细发生异常"+e.getMessage());
			}
		 }else{
			 returnJson.put("returnCode", "0");
			 returnJson.put("returnCode", "请求参数错误");
		 }
		 return returnJson;
	 }
	 
	 private void quickPay(JSONObject res){
		 if (res.has("payMode")){
			 Object payMode = res.get("payMode");
			 if(StringUtils.equals("0", String.valueOf(payMode))){
				 //更新订单为已支付状态
				 TblOrder order = tblOrderMapper.selectByOrderNum(res.get("orderNum").toString());
				 if(order != null && order.getOrderStatus().equals(OrderStatus.NO_PAY.value)){
					//支付流水号
				    Object tradeNo = res.get("tradeNo");
					order.setPayNumber(tradeNo.toString());
					//支付状态
					order.setOrderStatus(OrderStatus.NO_TAKED.value);
					//支付时间
					order.setPayTime(new Date());
					tblOrderMapper.updateByPrimaryKeySelective(order);
					
					List<TblOrderChild> tblOrderChilds=
							tblOrderChildMapper.selectByOrderId(order.getOrderId());
					TblOrderChild tblOrderChild=tblOrderChilds.get(0);
					tblOrderChild.setOrderChildStatus(OrderStatus.NO_TAKED.value);
					tblOrderChildMapper.updateByPrimaryKeySelective(tblOrderChild);
					
				 }
			 }
		 }
	 }
}
