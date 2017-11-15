package com.qjkj.qjcsp.web.api.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.goods.GoodsInfoService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 获取指定设备的可售菜品信息
 * @author carpeYe 2016-01-04
 *
 */
@RestController
@RequestMapping("api/common")
public class MachineAndAppController {

	private static Logger logger = LoggerFactory.getLogger(MachineAndAppController.class);
	@Autowired
	private GoodsInfoService goodsInfoService;
	/**
	 * 获取指定设备的可售菜品信息
	 * @param request
	 * @return map
	 */
/*	@RequestMapping(value="/getSaleGoodsList",method=RequestMethod.POST)
	public Map<String,Object> getSaleGoodsList(HttpServletRequest request){
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject req = JSONObject.fromObject(postJson);
		//调用业务层的getSaleGoodsList方法
		Map<String,Object> returnJson=goodsInfoService.getSaleGoodsList(req);
		returnJson.put("requestMethod", "getSaleGoodsList");
		return  returnJson;
	}*/
	
	/**
	 * 获取指定设备指定模型的可售菜品信息
	 * @param request
	 * @return map
	 */
	@RequestMapping(value="/getSalingGoodsList",method=RequestMethod.POST)
	public Map<String,Object> getSalingGoodsList(HttpServletRequest request){
		String postJson = RequestData.getRequestPostJson(request);
		Map<String, Object> returnJson = new HashMap<String, Object>();
		try{
			JSONObject req = JSONObject.fromObject(postJson);
				returnJson=goodsInfoService.getSalingGoodsList(req);
				returnJson.put("requestMethod", "getSalingGoodsList");
				return returnJson;			
		}
		catch (Exception e){
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
	
}
