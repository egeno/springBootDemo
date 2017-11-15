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
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 
 * @author carpeYe 2016-04-13
 *   2.3.40.	获取剩余预定份数
 */
@RestController
@RequestMapping("api/app/")
public class SurplusPreissueNumController {
	private static Logger logger = LoggerFactory.getLogger(SurplusPreissueNumController.class);
	@Autowired
	private SurplusPreissueNumService surplusPreissueNumService;
	@RequestMapping("getSurplusPreissueNum")
	public Map<String,Object> getSurplusPreissueNum(HttpServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getSurplusPreissueNum");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			 returnJson = surplusPreissueNumService.getSurplusPreissueNum(res);
		} catch (Exception e) {
			logger.error("获取剩余预定份数", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
}
