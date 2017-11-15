package com.qjkj.qjcsp.web.api.wechat;

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
import com.qjkj.qjcsp.service.wechatapi.CompanyWeChatApiService;
import com.qjkj.qjcsp.service.wechatapi.MachineWeChatApiService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/*
 * 类名:DeviceAppController
 * 版本号：V1.0
 * 日期：2015-12-30
 * APP端与设备相关api
 */

@RestController
@RequestMapping("/api/wechat/")
public class MachineWeChatController {

	private static Logger logger = LoggerFactory.getLogger(MachineWeChatController.class);

	@Autowired
	private MachineWeChatApiService machineWeChatApiService;
	@Autowired
	private CompanyWeChatApiService companyWeChatApiService;

	/**
	 * 根据定位罗列设备列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMachinesByGPSLocationWX", method = RequestMethod.POST)
	public Map<String, Object> getMachinesByGPSLocationWX(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getMachinesByGPSLocationWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineWeChatApiService.getMachinesByGPSLocationWX(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.error("获取设备列表异常", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}

	/**
	 * 根据设备id号获取商家详细信息
	 */
	@RequestMapping(value = "getCompanyDetailByMachineIdWX", method = RequestMethod.POST)
	public Map<String, Object> getCompanyDetailByMachineIdWX(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getCompanyDetailByMachineIdWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String machineId = res.getString("machineId");
			if (machineId != null && !machineId.isEmpty()) {
				Map<String, Object> returnData = machineWeChatApiService.getCompanyDetailByMachineIdWX(machineId);

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
	@RequestMapping(value = "/getCompanyListByLocationWX", method = RequestMethod.POST)
	public Map<String, Object> getCompanyListByLocationWX(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getCompanyListByLocationWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = companyWeChatApiService.getCompanyListByLocationWX(res);
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
	@RequestMapping(value = "/getMyMachineListWX", method = RequestMethod.POST)
	public Map<String, Object> getMyMachineListWX(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getMyMachineList");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineWeChatApiService.getMyMachineList(res);
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
	@RequestMapping("getMachineListWX")
	public Map<String, Object> getMachineListWX(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject jsonObject = JSONObject.fromObject(postJson);
		Map<String, Object> returnContent = new HashMap<String, Object>();
		try {
			returnContent = machineWeChatApiService.getMachineList(jsonObject);
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
	@RequestMapping("getAreaModelListWX")
	public Map<String, Object> getAreaModelListWX(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject jsonObject = JSONObject.fromObject(postJson);
		Map<String, Object> returnContent = new HashMap<String, Object>();
		try {
			returnContent = machineWeChatApiService.getAreaModelList(jsonObject);
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
	@RequestMapping(value = "/getMachineRertoryWX", method = RequestMethod.POST)
	public Map<String, Object> getMachineRertoryWX(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getMachineRertory");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineWeChatApiService.getMachineRertory(res);
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
	@RequestMapping(value = "/getMessageWX", method = RequestMethod.POST)
	public Map<String, Object> getMessageWX(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getMessage");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineWeChatApiService.getMessage(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.error("获取商户列表异常", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}

	@RequestMapping("/getPreissueHistoryMachineListWX")
	public Map<String,Object> getPreissueHistoryMachineListWX(HttpServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getPreissueHistoryMachineList");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineWeChatApiService.getPreissueMachineList(res);
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
