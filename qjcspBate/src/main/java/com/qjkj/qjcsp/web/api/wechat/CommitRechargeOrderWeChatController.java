package com.qjkj.qjcsp.web.api.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.recharge.CommitRechargeOrderService;
import com.qjkj.qjcsp.service.wechatapi.CommitRechargeOrderWeChatService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 2.3.28.	充值订单提交接口(APP)
 * @author carpeYe
 *
 */
@RestController
@RequestMapping("api/wechat")
public class CommitRechargeOrderWeChatController {

	@Autowired
	private CommitRechargeOrderWeChatService commitRechargeOrderWeChatService;
	@RequestMapping("/commitRechargeOrderWX")
	public Map<String,Object> commitRechargeOrderWX(HttpServletRequest request){
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(postJson);
		Map<String,Object> returnJson= commitRechargeOrderWeChatService.commitRechargeOrderWX(res);
		returnJson.put("requestMethod", "commitRechargeOrderWX");
		return returnJson;
	}
}
