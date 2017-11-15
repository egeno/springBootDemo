package com.qjkj.qjcsp.web.api.wechat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.location.LocationAPPService;
import com.qjkj.qjcsp.service.wechatapi.LocationWeChatService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 第三种定位
 *
 */
@RestController
@RequestMapping("/api/wechat/")
public class LocationWeChatController {
	
	@Autowired
	private LocationWeChatService locationWeChatService;

	/**
	 * 获取所有城市列表
	 */
	@RequestMapping(value = "/getAllCitiesListWX", method = RequestMethod.POST)
	public Map<String, Object> getAllCitiesListWX() {
		Map<String, Object> returnResult = new HashMap<String, Object>();
		try {
			returnResult = locationWeChatService.getAllCitiesListWX();
			return returnResult;
		} catch (Exception e) {
			returnResult.put("returnCode", "0");
			returnResult.put("returnContent", "服务器错误");
			returnResult.put("requestMethod", "getAllCitiesListWX");
			return returnResult;
		}
	}
	
	/**
	 * 根据城市名称获取所有大学列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getAllUniversitiesListWX", method = RequestMethod.POST)
	public Map<String, Object> getAllUniversitiesListWX(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getAllUniversitiesListWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = locationWeChatService.getAllUniversitiesListWX(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		
		return returnJson;
	}
	
	/**
	 * 根据大学Id获取所有设备列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getAllMachinesListWX", method = RequestMethod.POST)
	public Map<String, Object> getAllMachinesListWX(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getAllMachinesListWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = locationWeChatService.getAllMachinesListWX(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		
		return returnJson;
	}
	
	/**
	 * 模糊搜索城市
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fuzzySearchCityWX", method = RequestMethod.POST)
	public Map<String, Object> fuzzySearchCity(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "fuzzySearchCityWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = locationWeChatService.fuzzySearchCityWX(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		
		return returnJson;
	}
	
	/**
	 * 模糊搜索大学
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/fuzzySearchUniversityWX", method = RequestMethod.POST)
	public Map<String, Object> fuzzySearchUniversityWX(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "fuzzySearchUniversityWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = locationWeChatService.fuzzySearchUniversityWX(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		
		return returnJson;
	}
}
