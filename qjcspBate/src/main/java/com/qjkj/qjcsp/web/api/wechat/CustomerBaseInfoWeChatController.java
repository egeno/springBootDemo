package com.qjkj.qjcsp.web.api.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.appUser.CustomerBaseInfoService;
import com.qjkj.qjcsp.service.wechatapi.CustomerBaseInfoWeChatService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 
 * @author carpeYe 2016-01-21 2.3.3.获取当前用户的基本信息
 */
@RestController
@RequestMapping("api/wechat")
public class CustomerBaseInfoWeChatController {

	@Autowired
	private CustomerBaseInfoWeChatService customerBaseInfoWeChatService;

	@RequestMapping(value = "/getCustomerBaseInfoWX", method = RequestMethod.POST)
	public Map<String, Object> getCustomerBaseInfoWX(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(postJson);
		Map<String, Object> customer = customerBaseInfoWeChatService.getCustomerBaseInfoWX(res);
		customer.put("requestMethod", "getCustomerBaseInfoWX");
		return customer;
	}

}
