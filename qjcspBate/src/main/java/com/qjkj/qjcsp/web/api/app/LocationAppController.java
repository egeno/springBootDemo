package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.location.LocationAPPService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 第三种定位
 *
 */
@RestController
@RequestMapping("/api/app/")
public class LocationAppController {
	
	@Autowired
	private LocationAPPService locationAPPService;

	/**
	 * 获取所有城市列表
	 */
	@RequestMapping(value = "/getAllCitiesList", method = RequestMethod.POST)
	public Map<String, Object> getAllCitiesList() {
		Map<String, Object> returnResult = new HashMap<String, Object>();
		try {
			returnResult = locationAPPService.getAllCitiesList();
			return returnResult;
		} catch (Exception e) {
			returnResult.put("returnCode", "0");
			returnResult.put("returnContent", "服务器错误");
			returnResult.put("requestMethod", "getAllCitiesList");
			return returnResult;
		}
	}
	
	/**
	 * 根据城市名称获取所有大学列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getAllUniversitiesList", method = RequestMethod.POST)
	public Map<String, Object> getAllUniversitiesList(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getAllUniversitiesList");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = locationAPPService.getAllUniversitiesList(res);
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
	@RequestMapping(value="/getAllMachinesList", method = RequestMethod.POST)
	public Map<String, Object> getAllMachinesList(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getAllMachinesList");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = locationAPPService.getAllMachinesList(res);
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
	@RequestMapping(value="/fuzzySearchCity", method = RequestMethod.POST)
	public Map<String, Object> fuzzySearchCity(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "fuzzySearchCity");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = locationAPPService.fuzzySearchCity(res);
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
	@RequestMapping(value="/fuzzySearchUniversity", method = RequestMethod.POST)
	public Map<String, Object> fuzzySearchUniversity(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "fuzzySearchUniversity");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = locationAPPService.fuzzySearchUniversity(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		
		return returnJson;
	}
}
