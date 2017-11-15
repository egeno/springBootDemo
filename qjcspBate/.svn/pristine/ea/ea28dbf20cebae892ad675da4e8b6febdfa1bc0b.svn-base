package com.qjkj.qjcsp.web.api.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.advertisement.AppAdPicturesService;
import com.qjkj.qjcsp.service.wechatapi.WeChatAdPicturesService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("api/wechat")
public class WeChatAdPicturesController {

	@Autowired
	private WeChatAdPicturesService chatAdPicturesService;

	@RequestMapping("/getAppAdPicturesWX")
	public Map<String, Object> getAppAdPicturesWX(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(postJson);
		Map<String, Object> content = chatAdPicturesService.getAppAdPicturesWX(res);
		content.put("requestMethod", "getAppAdPicturesWX");
		return content;
	}
}
