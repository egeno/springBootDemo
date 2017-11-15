package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.preissue.SurplusPreissueNumService;
import com.qjkj.qjcsp.service.university.UniversitiesListSerivice;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 城市大学接口 
 */

@RestController
@RequestMapping("api/app/")
public class UniversitiesListController {
	private static Logger logger = LoggerFactory.getLogger(UniversitiesListController.class);
	
	@Autowired
	private UniversitiesListSerivice universitiesListSerivice;
	
	@RequestMapping("getAllUniversitiesList")
	public Map<String,Object> getAllUniversitiesList(HttpServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getAllUniversitiesList");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			 returnJson = universitiesListSerivice.getSurplusPreissueNum(res);
		} catch (Exception e) {
			logger.error("获取剩余预定份数", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
}
