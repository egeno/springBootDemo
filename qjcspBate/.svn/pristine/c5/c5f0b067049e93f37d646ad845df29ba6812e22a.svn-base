package com.qjkj.qjcsp.web.api.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.advertisement.AppAdPicturesService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("api/app")
public class AppAdPicturesController {

	@Autowired
	private AppAdPicturesService appAdPicturesService;

	@RequestMapping("/getAppAdPictures")
	public Map<String, Object> getAppAdPictures(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(postJson);
		Map<String, Object> content = appAdPicturesService.getAppAdPictures(res);
		content.put("requestMethod", "getAppAdPictures");
		return content;
	}
}
