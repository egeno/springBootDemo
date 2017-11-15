package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.company.CompanyApiService;
import com.qjkj.qjcsp.service.machine.MachineApiService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/*
 * 类名:DeviceAppController
 * 版本号：V1.0
 * 日期：2015-12-30
 * APP端与设备相关api
 */

@RestController
@RequestMapping("/api/app/")
public class MachineAppController {

	private static Logger logger = LoggerFactory.getLogger(MachineAppController.class);

	@Autowired
	private MachineApiService machineApiService;
	@Autowired
	private CompanyApiService companyApiService;

	/**
	 * 根据定位罗列设备列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMachinesByGPSLocation", method = RequestMethod.POST)
	public Map<String, Object> getMachinesByGPSLocation(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getMachinesByGPSLocation");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineApiService.getMachinesByGPSLocation(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}

	/**
	 * 根据设备id号获取商家详细信息
	 */
	@RequestMapping(value = "getCompanyDetailByMachineId", method = RequestMethod.POST)
	public Map<String, Object> getCompanyDetailByMachineId(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getCompanyDetailByMachineId");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String machineId = res.getString("machineId");
			if (machineId != null && !machineId.isEmpty()) {
				Map<String, Object> returnData = machineApiService.getCompanyDetailByMachineId(machineId);

				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("根据设备id号获取商家详细信息错误", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}

	/**
	 * 根据地名获取商户列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getCompanyListByLocation", method = RequestMethod.POST)
	public Map<String, Object> getCompanyListByLocation(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getCompanyListByLocation");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = companyApiService.getCompanyListByLocation(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.error("获取商户列表异常", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}

	/**
	 * 2.1.6.我的机柜
	 */
	@RequestMapping(value = "/getMyMachineList", method = RequestMethod.POST)
	public Map<String, Object> getMyMachineList(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getMyMachineList");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineApiService.getMyMachineList(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.error("获取商户列表异常", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}

	/**
	 * 根据用户Id获取对应的设备列表
	 */
	@RequestMapping("getMachineList")
	public Map<String, Object> getMachineList(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject jsonObject = JSONObject.fromObject(postJson);
		Map<String, Object> returnContent = new HashMap<String, Object>();
		try {
			returnContent = machineApiService.getMachineList(jsonObject);
		} catch (Exception e) {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "服务器异常");
		}
		returnContent.put("requestMethod", "getMachineList");
		return returnContent;
	}

	/**
	 * 根据用户ID获取对应的模型时间段
	 */
	@RequestMapping("getAreaModelList")
	public Map<String, Object> getAreaModelList(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject jsonObject = JSONObject.fromObject(postJson);
		Map<String, Object> returnContent = new HashMap<String, Object>();
		try {
			returnContent = machineApiService.getAreaModelList(jsonObject);
		} catch (Exception e) {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "服务器异常");
		}
		returnContent.put("requestMethod", "getAreaModelList");
		return returnContent;
	}

	/**
	 * 2.1.7.根据设备ID获取设备库存信息以及设备状况
	 */
	@RequestMapping(value = "/getMachineRertory", method = RequestMethod.POST)
	public Map<String, Object> getMachineRertory(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getMachineRertory");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineApiService.getMachineRertory(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.error("获取商户列表异常", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}

	/**
	 * 2.1.15.消息提示
	 */
	@RequestMapping(value = "/getMessage", method = RequestMethod.POST)
	public Map<String, Object> getMessage(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getMessage");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineApiService.getMessage(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.error("获取商户列表异常", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}

	@RequestMapping("/getPreissueHistoryMachineList")
	public Map<String,Object> getPreissueHistoryMachineList(HttpServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getPreissueHistoryMachineList");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineApiService.getPreissueMachineList(res);
			returnData.put("requestMethod", "getPreissueHistoryMachineList");
			return returnData;
		} catch (Exception e) {
			logger.error("常用设备和附近的设备异常", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
			return returnJson;
		}
	}
}
