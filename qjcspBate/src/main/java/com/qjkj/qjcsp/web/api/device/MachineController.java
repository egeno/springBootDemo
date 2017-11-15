package com.qjkj.qjcsp.web.api.device;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.machine.MachineApiService;
import com.qjkj.qjcsp.service.machine.MachineModelService;
import com.qjkj.qjcsp.service.machine.VerifyTakingCodeService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/*
 * 类名:MachineController
 * 版本号：V1.0
 * 日期：2015-01-04
 * 设备端与设备相关api
 */

@RestController
@RequestMapping("/api/device/")
public class MachineController {
	private static Logger logger = LoggerFactory.getLogger(MachineController.class);
	@Autowired
	private MachineApiService machineApiService;
	@Autowired
	private MachineModelService machineModelService;
	@Autowired
	private VerifyTakingCodeService verifyTakingCodeService;

	/**
	 * 取餐记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/takingGoodsRecording", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> takingGoodsRecording(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "takingGoodsRecording");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineApiService.takingGoodsRecording(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.warn("新增取餐记录异常"+ e+"postJson: "+postJson);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}

		return returnJson;
	}

	/**
	 * 2.2.5. 当前设备对应模型列表
	 * 
	 * @author yjg
	 * @date 2016年1月6日
	 * @return
	 *
	 */
	@RequestMapping(value = "/getModelList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getModelList(ServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requestMethod", "getModelList");
		try {
			String str = RequestData.getRequestPostJson(request);
			JSONObject json = JSONObject.fromObject(str);
			String deviceCode = json.getString("deviceCode");

			map.put("returnCode", "1");
			map.put("returnContent", machineModelService.findModelListByDeviceCode(deviceCode));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询设备模型列表失败=" + e.getMessage());
			map.put("returnCode", "0");
			map.put("returnContent", "查询设备模型列表");
		}
		return map;
	}

	/*
	 * 设备报警
	 */
	@RequestMapping(value = "alarmDevice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> alarmDevice(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "alarmDevice");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String deviceCode = res.getString("deviceCode");
			String alarmCode = res.getString("alarmCode");
			String orderChildId=null;
			String operator = null;
			String noExec = null;
			if(res.has("orderChildId")){
			     orderChildId = res.getString("orderChildId");
			     }
			if (res.has("operator")) {
				operator = res.getString("operator");
			}
			if (res.has("noExec")) {
				noExec = res.getString("noExec");
			}
			if (!StringUtils.isAnyBlank(deviceCode, alarmCode) && !"null".equals(alarmCode)) {
				Map<String, Object> returnData = machineApiService.alarmDevice(deviceCode, alarmCode, orderChildId,
						operator, noExec);
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				logger.warn("设备报警请求参数错误"+ postJson);
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.warn("设备报警服务器错误"+postJson);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}

	/*
	 * 验证取餐码
	 */
	@RequestMapping(value = "verifyTakingCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> verifyTakingCode(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "verifyTakingCode");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String deviceCode = res.getString("deviceCode");
			String idCode = res.getString("idCode");
			if (StringUtils.isNoneBlank(deviceCode, idCode)) {
				Map<String, Object> returnData = verifyTakingCodeService.verifyTakingCode(deviceCode, idCode);
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}
		} catch (Exception e) {
			logger.error("请求参数错误", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}

	/**
	 * 读取设备温度
	 */
	@RequestMapping(value = "/getTemperature", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTemperature(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getTemperature");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);

			Map<String, Object> returnData = machineApiService.getTemperature(res);
			json.put("returnCode", returnData.get("returnCode"));
			json.put("returnContent", returnData.get("returnContent"));

		} catch (Exception e) {
			logger.error("获取设备温度异常", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}

	/**
	 * 设备故障对应的格子
	 */
	@RequestMapping(value = "/getAlarmCellNumByDeviceCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAlarmCellNumByDeviceCode(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getAlarmCellNumByDeviceCode");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineApiService.getAlarmCellNumByDeviceCode(res);
			json.put("returnCode", returnData.get("returnCode"));
			json.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}
	/**
	 * 设备故障时,开门取餐,验证码对应的格子
	 */
	@RequestMapping(value = "/getAlarmCellNumByidCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAlarmCellNumByidCode(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getAlarmCellNumByidCode");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineApiService.getAlarmCellNumByidCode(res);
			json.put("returnCode", returnData.get("returnCode"));
			json.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}
	/**
	 * 故障时开门取餐
	 */
	/*@RequestMapping(value = "/machineTakeByAlarm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> machineTakeByAlarm(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "machineTakeByAlarm");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = machineApiService.machineTakeByAlarm(res);
			json.put("returnCode", returnData.get("returnCode"));
			json.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}*/
}
