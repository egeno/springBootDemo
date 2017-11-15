package com.qjkj.qjcsp.web.api.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.refund.RefundOrderService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 2.3.29.	售后/退款订单列表
 * @author carpeYe 2016-04-21
 *
 */
@RestController
@RequestMapping("api/app/")
public class RefundOrderController {
	@Autowired
	private RefundOrderService refundOrderService;
	@RequestMapping("refundOrderList")
	public Map<String,Object> refundOrderList(HttpServletRequest request){
		String jsonStr=RequestData.getRequestPostJson(request);
		JSONObject res=JSONObject.fromObject(jsonStr);
		Map<String,Object> map=refundOrderService.refundOrderList(res);
		map.put("requestMethod","refundOrderList");
		return map;
	}
	/**
	 * 2.3.30 售后、退款订单详情
	 * @param request
	 * @return
	 */
	@RequestMapping("refundOrderDetail")
	public Map<String,Object> refundOrderDetail(HttpServletRequest request){
		String jsonStr=RequestData.getRequestPostJson(request);
		JSONObject res=JSONObject.fromObject(jsonStr);
		Map<String,Object> map=refundOrderService.refundOrderDetail(res);
		map.put("requestMethod","refundOrderDetail");
		return map;
	}

}
