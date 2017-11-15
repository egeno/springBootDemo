package com.qjkj.qjcsp.web.api.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.appUser.CustomerBaseInfoService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 
 * @author carpeYe 2016-01-21 2.3.3.获取当前用户的基本信息
 */
@RestController
@RequestMapping("api/app")
public class CustomerBaseInfoController {

	@Autowired
	private CustomerBaseInfoService customerBaseInfoService;

	@RequestMapping(value = "/getCustomerBaseInfo", method = RequestMethod.POST)
	public Map<String, Object> getCustomerBaseInfo(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(postJson);
		Map<String, Object> customer = customerBaseInfoService.getCustomerBaseInfo(res);
		customer.put("requestMethod", "getCustomerBaseInfo");
		return customer;

	}

}
