package com.qjkj.qjcsp.web.api.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.refund.RefundOrderService;
import com.qjkj.qjcsp.service.wechatapi.RefundOrderWeChatService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 2.3.29.	售后/退款订单列表
 * @author carpeYe 2016-04-21
 *
 */
@RestController
@RequestMapping("api/wechat/")
public class RefundOrderWeChatController {
	@Autowired
	private RefundOrderWeChatService refundOrderWeChatService;
	@RequestMapping("refundOrderListWX")
	public Map<String,Object> refundOrderListWX(HttpServletRequest request){
		String jsonStr=RequestData.getRequestPostJson(request);
		JSONObject res=JSONObject.fromObject(jsonStr);
		Map<String,Object> map=refundOrderWeChatService.refundOrderListWX(res);
		map.put("requestMethod","refundOrderListWX");
		return map;
	}
	/**
	 * 2.3.30 售后、退款订单详情
	 * @param request
	 * @return
	 */
	@RequestMapping("refundOrderDetailWX")
	public Map<String,Object> refundOrderDetailWX(HttpServletRequest request){
		String jsonStr=RequestData.getRequestPostJson(request);
		JSONObject res=JSONObject.fromObject(jsonStr);
		Map<String,Object> map=refundOrderWeChatService.refundOrderDetailWX(res);
		map.put("requestMethod","refundOrderDetailWX");
		return map;
	}

}
