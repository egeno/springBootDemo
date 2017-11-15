package com.qjkj.qjcsp.web.api.wechat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.preissue.SurplusPreissueNumService;
import com.qjkj.qjcsp.service.wechatapi.SurplusPreissueNumWeChatService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 
 * @author carpeYe 2016-04-13
 *   2.3.40.	获取剩余预定份数
 */
@RestController
@RequestMapping("api/wechat/")
public class SurplusPreissueNumWeChatController {
	private static Logger logger = LoggerFactory.getLogger(SurplusPreissueNumWeChatController.class);
	@Autowired
	private SurplusPreissueNumWeChatService surplusPreissueNumWeChatService;
	
	@RequestMapping("getSurplusPreissueNumWX")
	public Map<String,Object> getSurplusPreissueNumWX(HttpServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "getSurplusPreissueNumWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			 returnJson = surplusPreissueNumWeChatService.getSurplusPreissueNumWX(res);
		} catch (Exception e) {
			logger.error("获取剩余预定份数", e);
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
}
