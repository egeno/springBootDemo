package com.qjkj.qjcsp.web.api.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.appUser.FirstOrderDiscountService;
import com.qjkj.qjcsp.service.wechatapi.FirstOrderDiscountWeChatService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 2.3.27. 完善用户资料后首单优惠
 * 
 * @author carpeYe 2016-02-23
 *
 */
@RestController
@RequestMapping("api/wechat")
public class FirstOrderDiscountWeChatController {
	@Autowired
	private FirstOrderDiscountWeChatService firstOrderDiscountWeChatService;

	@RequestMapping("/firstOrderDiscountWX")
	public Map<String, Object> firstOrderDiscountWX(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(postJson);
		Map<String, Object> content = firstOrderDiscountWeChatService.firstOrderDiscountWX(res);
		content.put("requestMethod", "firstOrderDiscountWX");
		return content;
	}

}
