package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.QAbstractAuditable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.order.app.PreissueCommitOrderService;
import com.qjkj.qjcsp.util.RequestData;
import com.qjkj.qjcsp.web.api.common.OrderCommonController;

/*
 * 类名:OrderController
 * 版本号：V1.1
 * 日期：2016-4-13
 * 预定订单提交
 * wsk
 */

@RestController
@RequestMapping("/api/app")
public class PreissueCommitOrderController {
	private static Logger logger = LoggerFactory.getLogger(OrderCommonController.class);
	
	@Autowired
	private PreissueCommitOrderService preissueCommitOrdService;
	
	@RequestMapping(value = "/preissueCommitOrder", method = RequestMethod.POST)
	public Map<String, Object> commitOrder(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "preissueCommitOrder");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = preissueCommitOrdService.PreissueCommitOrder(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			    if("1".equals(e.getMessage())){
			    	logger.error("订单提交异常", e);
					returnJson.put("returnCode", "0");
					returnJson.put("returnContent", "传入时间格式错误");
			    }else{
			    	logger.error("订单提交异常", e);
					returnJson.put("returnCode", "0");
					returnJson.put("returnContent", "服务器错误");
			    }
				
		}
		return returnJson;
	}
}
