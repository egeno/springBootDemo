package com.qjkj.qjcsp.web.api.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.recharge.CommitRechargeOrderService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 2.3.28.	充值订单提交接口(APP)
 * @author carpeYe
 *
 */
@RestController
@RequestMapping("api/app")
public class CommitRechargeOrderController {

	@Autowired
	private CommitRechargeOrderService commitRechargeOrderService;
	@RequestMapping("/commitRechargeOrder")
	public Map<String,Object> commitRechargeOrder(HttpServletRequest request){
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(postJson);
		Map<String,Object> returnJson= commitRechargeOrderService.commitRechargeOrder(res);
		returnJson.put("requestMethod", "commitRechargeOrder");
		return returnJson;
	}
}
