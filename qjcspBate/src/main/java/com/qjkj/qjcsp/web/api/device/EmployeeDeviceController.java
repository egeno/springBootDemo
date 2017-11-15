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
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.employee.EmployeeApiService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/*
 * 类名:EmployeeController
 * 版本号：V1.0
 * 日期：2015-01-15
 * 供货维修员相关api
 */

@RestController
@RequestMapping("/api/device/")
public class EmployeeDeviceController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeDeviceController.class);

	@Autowired
	private EmployeeApiService employeeApiService;
	
	/**
	 * 维修人员或补货员手机验证码发送
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/verifyEmployeeCodeSend", method=RequestMethod.POST)
	public Map<String, Object> verifyEmployeeCodeSend(ServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "verifyEmployeeCodeSend");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = employeeApiService.verifyEmployeeCodeSend(res);
			json.put("returnCode", returnData.get("returnCode"));
			json.put("returnContent", returnData.get("returnContent"));
		  } catch (Exception e) {
			  logger.error("维修人员或补货员手机验证码发送异常", e);
			  json.put("returnCode", "0");
			  json.put("returnContent", "服务器错误");
		  }
		return json;
	}
	
	/**
	 * 维修人员/补货员验证
	 */
	@RequestMapping(value="/verifyEmployeeCode",method = RequestMethod.POST)  
	public Map<String,Object> verifyEmployeeCode(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "verifyEmployeeCode");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String deviceCode = res.getString("deviceCode");
			String mobileNum = res.getString("mobileNum");
			String code = res.getString("code");
			
			if(!StringUtils.isAnyBlank(deviceCode,mobileNum,code)){
				Map<String,Object> returnData = employeeApiService.verifyEmployeeCode(deviceCode,mobileNum,code);
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			}
			else{
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		  } catch (Exception e) {
			  /*report an error*/
			  logger.error("验证雇员异常", e);
			  json.put("returnCode", "0");
			  json.put("returnContent", "服务器错误");
		  }
		return json;
	}
}
