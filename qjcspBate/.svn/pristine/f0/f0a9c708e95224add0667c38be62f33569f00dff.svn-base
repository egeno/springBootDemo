package com.qjkj.qjcsp.service.wechatapi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.util.DateUtils;

@Component
@Transactional
public class OrderStatusWeChatApiService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderStatusWeChatApiService.class);
	
	@Autowired
	private TblOrderMapper tblOrderMapper; 
	
	/**
	 * 设备获取订单状态
	 * @param deviceCode,orderNum 
	 * @return
	 */
	public Map<String,Object> getOrderStatusWX(String orderNum) 
	{
		TblOrder tblOrder =  tblOrderMapper.searchOrder(orderNum);
		
		Map<String,Object> returnData = new HashMap<String, Object>();

		//查找到该订单
		if (tblOrder != null) {
			Map<String, String> map = new HashMap<String, String>();
			
			String systemTime=DateUtils.formatString(new Date());
			map.put("state", tblOrder.getOrderStatus()+"");
			map.put("systemTime", systemTime);
			
			returnData.put("returnCode", "1");
			returnData.put("returnContent",map);
			
		}
		//未查到订单
		else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "未查到订单");
		}
		return returnData;
	}

	 
	
}

