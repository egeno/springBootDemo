package com.qjkj.qjcsp.web.api.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.recharge.RechargeOrderStatusService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 充值订单状态查询接口(APP)
 * carpeYe 2016-03-09
 */
@RestController
@RequestMapping("api/app")
public class RechargeOrderStatusController {

	@Autowired
	private RechargeOrderStatusService rechargeOrderStatusService;
	@RequestMapping("/getRechargeOrderStatus")
	public Map<String,Object> getRechargeOrderStatus(HttpServletRequest request){
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject req = JSONObject.fromObject(postJson);
		Map<String,Object> returnJson=rechargeOrderStatusService.getRechargeOrderStatus(req);
		returnJson.put("requestMethod", "getRechargeOrderStatus");
		return returnJson;
	}
}
