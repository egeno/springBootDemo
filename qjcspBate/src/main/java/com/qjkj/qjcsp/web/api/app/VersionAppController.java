package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.version.VersionAppService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 
 * 版本更新
 *
 */
@RestController
@RequestMapping("/api/app/")
public class VersionAppController {


	@Autowired
	private VersionAppService versionAppService;
	
	/**
	 * 安卓端查询是否有新版本
	 */
	@RequestMapping(value = "/getVersionByAndroid", method = RequestMethod.POST)
	public Map<String, Object> getVersionByAndroid(HttpServletRequest request) {
		Map<String, Object> returnResult = new HashMap<String, Object>();
		try {
			String postJson = RequestData.getRequestPostJson(request);
			JSONObject res = JSONObject.fromObject(postJson);
			returnResult = versionAppService.getVersionByAndroid(res);
			return returnResult;
		} catch (Exception e) {
			returnResult.put("returnCode", "0");
			returnResult.put("returnContent", "服务器错误");
			returnResult.put("requestMethod", "getVersionByAndroid");
			return returnResult;
		}
	}
}
