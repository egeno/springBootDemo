package com.qjkj.qjcsp.service.order.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.mapper.TblOrderMapper;

@Component
@Transactional
public class OrderGetOrderStatusApiService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderGetOrderStatusApiService.class);
	
	@Autowired
	private TblOrderMapper tblOrderMapper; 
	
	/**
	 * 设备获取订单状态
	 * @param deviceCode,orderNum 
	 * @return
	 */
	public Map<String,Object> getOrderStatus(String orderNum) 
	{
		TblOrder tblOrder =  tblOrderMapper.searchOrder(orderNum);
		
		Map<String,Object> returnData = new HashMap<String, Object>();

		//查找到该订单
		if (tblOrder != null) {
			returnData.put("returnCode", "1");
			returnData.put("returnContent", tblOrder.getOrderStatus());
			
		}
		//未查到订单
		else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "未查到订单");
		}
		return returnData;
	}

	 
	
}

