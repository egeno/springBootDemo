package com.qjkj.qjcsp.web.api.device;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.machine.VerifyChangeAreaModelService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 2.2.11.	验证切换模型
 * @author Administrator
 *
 */
@RestController
@RequestMapping("api/device/")
public class VerifyChangeAreaModelController {

	@Autowired
	private VerifyChangeAreaModelService verifyChangeAreaModelService;

	@RequestMapping("verifyChangeAreaModel")
	public Map<String, Object> verifyChangeAreaModel(HttpServletRequest request) {

		String jsonStr = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(jsonStr);
		Map<String, Object> returnContent = verifyChangeAreaModelService.verifyChangeAreaModel(res);
		returnContent.put("requestMethod", "verifyChangeAreaModel");
		return returnContent;
	}

}
