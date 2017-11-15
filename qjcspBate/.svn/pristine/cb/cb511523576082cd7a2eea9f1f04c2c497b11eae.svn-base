package com.qjkj.qjcsp.web.api.device;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.machine.StockGoodListService;
import com.qjkj.qjcsp.util.RequestData;
/**
 * 2.2.5.	获取补货商品列表
 * @author wsk 2016年4月20日19:38:29
 *
 */
@RestController
@RequestMapping("api/device/")
public class StockGoodListController {
	
	private static Logger logger = LoggerFactory.getLogger(EmployeeDeviceController.class);
	
	@Autowired
	private StockGoodListService stockGoodListService;  

		@RequestMapping(value="/getStockInfo",method=RequestMethod.POST)
		public Map<String, Object> getStockInfo(HttpServletRequest request){
			Map<String, Object> returnJson = new HashMap<String, Object>();
			
			try {
				String postJson = RequestData.getRequestPostJson(request);
				JSONObject res = JSONObject.fromObject(postJson);
				
				Map<String, Object> returnData = stockGoodListService.getStockGoodList(res);
				returnJson.put("returnCode", returnData.get("returnCode"));
				returnJson.put("returnContent", returnData.get("returnContent"));
			} catch (Exception e) {
				logger.error("获取补货商品列表异常", e);
				returnJson.put("returnCode", "0");
				returnJson.put("returnContent", "服务器错误");
			}
			returnJson.put("requestMethod", "getStockInfo");
			return returnJson;
			
		}

}
