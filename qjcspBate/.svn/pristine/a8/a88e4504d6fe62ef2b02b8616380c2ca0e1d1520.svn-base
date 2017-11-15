package com.qjkj.qjcsp.web.api.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.recharge.RechargeActivityService;
/**
 * 充值面额查询接口(APP)
 * @author carpeYe 2016-03-09
 *
 */
@RestController
@RequestMapping("api/app")
public class RechargeActivityController {
	@Autowired
	private RechargeActivityService rechargeActivityService;
	@RequestMapping("/getRechargeActivity")
	public Map<String,Object> getRechargeActivity(){
		Map<String, Object> returnJson=rechargeActivityService.getRechargeActivity();
		returnJson.put("requestMethod", "getRechargeActivity");
		return returnJson;
	}

}
