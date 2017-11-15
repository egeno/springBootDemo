package com.qjkj.qjcsp.web.api.device;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.qjkj.qjcsp.service.order.app.OrderReadyPickDetailService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 	订单待取餐明细
 * @author carpeYe 2015-05-05
 *
 */
@RestController
@RequestMapping("api/device")
public class OrderReadyPickDetailController {

	@Autowired
	private OrderReadyPickDetailService orderReadyPickDetailService;
	/*
	 * 订单待取餐明细
	 * @return map
	 */
	@RequestMapping(value="/getOrderReadyPickDetail",method=RequestMethod.POST)
	public Map<String,Object> getOrderReadyPickDetail(HttpServletRequest request){
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject req = JSONObject.fromObject(postJson);
		Map<String,Object> returnJson=orderReadyPickDetailService.getOrderReadyPickDetail(req);
		returnJson.put("requestMethod", "getOrderReadyPickDetail");
		return returnJson;
	}
}
