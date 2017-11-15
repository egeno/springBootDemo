package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.refund.OrderRefundService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/app")
public class OrderRefundAppController {

	@Autowired
	private OrderRefundService orderRefundService;

	/**
	 * 2.3.23. 用户发起订单退款申请
	 * 
	 * @param customerId:用户id
	 *            orderNum:订单号 orderChildNums:子订单号数组 refundComment:退款原因
	 */
	@RequestMapping(value = "/customerOrderRefundApply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> customerOrderRefundApply(HttpServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		try {
			String postJson = RequestData.getRequestPostJson(request);
			JSONObject jsonObject = JSONObject.fromObject(postJson);
			returnJson = orderRefundService.customerOrderRefundApply(jsonObject);
			returnJson.put("requestMethod", "customerOrderRefundApply");
			return returnJson;
		} catch (Exception e) {
			// TODO: handle exception
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
			returnJson.put("requestMethod", "customerOrderRefundApply");
			return returnJson;
		}
	}
}
