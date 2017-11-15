package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.code.IOSTestSymbolService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 2.3.26. IOS测试标志位
 * 
 * @author carpeYe 2016-02-01
 *
 */
@RestController
@RequestMapping("api/app")
public class IOSTestSymbolController {

	@Autowired
	private IOSTestSymbolService iosTestSymbolService;
	@RequestMapping("/getIOSTestSymbol")
	public Map<String, Object> getIOSTestSymbol(HttpServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res=null;
		if(StringUtils.isNoneBlank(postJson)){
		 res = JSONObject.fromObject(postJson);
		}
		//Map<String, Object> returnContent = new HashMap<String, Object>();
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("IOSTestSymbol", Constants.IOS_SYMBOL);
		Map<String, Object> returnContent=iosTestSymbolService.IOSTestVersion(res);
		returnContent.put("requestMethod", "getIOSTestSymbol");
		return returnContent;
	}
}
