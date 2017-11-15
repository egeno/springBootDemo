package com.qjkj.qjcsp.web.api.wechat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.recharge.RechargeActivityService;
import com.qjkj.qjcsp.service.wechatapi.RechargeActivityWeChatService;
/**
 * 充值面额查询接口(APP)
 * @author carpeYe 2016-03-09
 *
 */
@RestController
@RequestMapping("api/wechat")
public class RechargeActivityWeChatController {
	@Autowired
	private RechargeActivityWeChatService rechargeActivityWeChatService;
	@RequestMapping("/getRechargeActivityWX")
	public Map<String,Object> getRechargeActivityWX(){
		Map<String, Object> returnJson=rechargeActivityWeChatService.getRechargeActivityWX();
		returnJson.put("requestMethod", "getRechargeActivityWX");
		return returnJson;
	}

}
