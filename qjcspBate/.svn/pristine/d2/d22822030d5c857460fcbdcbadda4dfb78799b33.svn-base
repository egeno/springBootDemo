package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.goodsevaluate.GoodsEvaluateService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/api/app")
public class GoodsEvaluateController {
	public static Logger logger = LoggerFactory.getLogger(GoodsEvaluateController.class);

	@Autowired
	private GoodsEvaluateService goodsEvaluateService;

	@RequestMapping(value = "/getSayEvaluationGoods", method = RequestMethod.POST)
	public Map<String, Object> getSayEvaluationGoods(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		String str = RequestData.getRequestPostJson(request);
		try {
			JSONObject json = JSONObject.fromObject(str);
			String orderNum = json.getString("orderNum");
			if (StringUtils.isNotBlank(orderNum) && StringUtils.isNotEmpty(orderNum)) {
				returnJson = goodsEvaluateService.getSayEvaluationGoods(orderNum);
			} else {
				returnJson.put("returnCode", "0");
				returnJson.put("returnContent", "请求参数错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("获取可评价菜品列表出错:" + str);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		returnJson.put("requestMethod", "getSayEvaluationGoods");
		return returnJson;
	}
	
	@RequestMapping(value = "/commitEvaluationGoods", method = RequestMethod.POST)
	public Map<String, Object> commitEvaluationGoods(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "commitEvaluationGoods");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = goodsEvaluateService.commitEvaluationGoods(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			String message=e.getMessage();
			logger.warn("提交菜品评价错误"+ e+"postJson: "+postJson);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", message.equals("0")?"提交评价失败，请不要发表含有不适当内容的评价":"服务器错误");
		}
		return returnJson;
	}
	@RequestMapping(value = "/getEvaluationDetails", method = RequestMethod.POST)
	public Map<String, Object> getEvaluationDetails(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getEvaluationDetails");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = goodsEvaluateService.getEvaluationDetails(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.warn("获取评价详情"+ e+"postJson: "+postJson);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
	@RequestMapping(value = "/getGoodsDetails", method = RequestMethod.POST)
	public Map<String, Object> getGoodsDetails(ServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getGoodsDetails");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = goodsEvaluateService.getGoodsDetails(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			logger.warn("获取菜品详情"+ e+"postJson: "+postJson);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
}