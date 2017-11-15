package com.qjkj.qjcsp.web.api.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.order.common.OrderCancelApiService;
import com.qjkj.qjcsp.service.order.common.OrderCommitApiService;
import com.qjkj.qjcsp.service.order.common.OrderGetOrderStatusApiService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/*
 * 类名:OrderController
 * 版本号：V1.1
 * 日期：2015-03-23
 * 订单相关通用api
 */

@RestController
@RequestMapping("/api/common/")
public class OrderCommonController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderCommonController.class);
	
	@Autowired
	private OrderCommitApiService orderCommitApiService;
	@Autowired
	private OrderCancelApiService orderCancelApiService;
	@Autowired
	private OrderGetOrderStatusApiService orderGetOrderStatusApiService;
	
	/**
	 * 订单提交
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/commitOrder", method = RequestMethod.POST)
	public Map<String, Object> commitOrder(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "commitOrder");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = orderCommitApiService.commitOrder(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.error("订单提交异常", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
	
	/**
	 * 取消订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public Map<String, Object> cancelOrder(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "cancelOrder");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = orderCancelApiService.cancelOrder(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.error("取消订单异常", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
	/**
	 * 设备获取订单状态
	 */
	@RequestMapping(value="getOrderStatus",method=RequestMethod.POST)
	public Map<String,Object> getOrderStatus(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getOrderStatus");
		String postJson = RequestData.getRequestPostJson(request);
		try 
		{
			JSONObject res = JSONObject.fromObject(postJson);
			String orderNum = res.getString("orderNum");
			if(orderNum != null && !orderNum.isEmpty())
			{
				Map<String,Object> returnData = orderGetOrderStatusApiService.getOrderStatus(orderNum);
				
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			}else{
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}	

		  }catch (Exception e) {
			  logger.error("设备id号错误", e);
			  json.put("returnCode", "0");
			  json.put("returnContent", "服务器错误");
		  }
		return json;
	}
	
}
