package com.qjkj.qjcsp.web.api.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.recharge.RechargeOrderStatusService;
import com.qjkj.qjcsp.service.wechatapi.RechargeOrderStatusWeChatService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 充值订单状态查询接口(APP)
 * carpeYe 2016-03-09
 */
@RestController
@RequestMapping("api/wechat")
public class RechargeOrderStatusWeChatController {

	@Autowired
	private RechargeOrderStatusWeChatService rechargeOrderStatusWeChatService;
	@RequestMapping("/getRechargeOrderStatusWX")
	public Map<String,Object> getRechargeOrderStatus(HttpServletRequest request){
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject req = JSONObject.fromObject(postJson);
		Map<String,Object> returnJson=rechargeOrderStatusWeChatService.getRechargeOrderStatusWX(req);
		returnJson.put("requestMethod", "getRechargeOrderStatusWX");
		return returnJson;
	}
}
