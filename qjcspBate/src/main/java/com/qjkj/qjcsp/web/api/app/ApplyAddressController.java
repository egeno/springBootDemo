package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.join.ApplyAddressService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("api/app")
public class ApplyAddressController {
	@Autowired
	private ApplyAddressService applyAddressService;
	
	@RequestMapping("/applyAddress")
	public Map<String, Object> applyAddress(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(postJson);
		Map<String, Object> content=new HashMap<String, Object>();
		content.put("requestMethod", "applyAddress");
		try {
			content = applyAddressService.applyAddress(res);
		} catch (Exception e) {
			content.put("returnCode", "0");
			content.put("returnContent", "服务器错误");
			return content;
		}
	
		return content;
	}
}
