package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.order.api.HistoricalOrdersAndSalesService;
import com.qjkj.qjcsp.util.RequestData;

/**
 * 2.1.3.获取历史订单
 * @param request
 * @return
 */
@RestController
@RequestMapping("/api/app/")
public class HistoricalOrdersAndSalesController {
	private static Logger logger = LoggerFactory.getLogger(OrderAppController.class);
	@Autowired
	private HistoricalOrdersAndSalesService historicalOrdersAndSalesServicee;
	@RequestMapping(value = "/getSaleCompleteOrders", method = RequestMethod.POST)
	public Map<String, Object> getSaleCompleteOrders(ServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requestMethod","getSaleCompleteOrders");
		String str = RequestData.getRequestPostJson(request);
		try {		
			JSONObject res = JSONObject.fromObject(str);
			Map<String, Object> returnData = null;			
			returnData = historicalOrdersAndSalesServicee.getSaleCompleteOrders(res);
			map.put("returnCode", returnData.get("returnCode"));
			map.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
		}
		return map;
	}
	

	/**
	 * 2.1.4.菜品总销售数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getGoodsCompleteSale", method=RequestMethod.POST)
	public Map<String, Object> getGoodsCompleteSale(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getGoodsCompleteSale");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData=null;
			returnData= historicalOrdersAndSalesServicee.getGoodsCompleteSale(res);

			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
		
	}
	
}
