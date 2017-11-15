package com.qjkj.qjcsp.web.api.app;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.machine.WeiXinAccessService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("api/app/")
public class WeiXinAccessController {
	
	@Autowired
	private WeiXinAccessService weiXinAccessService;

	@RequestMapping("getHistoryMachineId")
	public Map<String,Object> getHistoryMachineId(HttpServletRequest request){
		String jsonStr=RequestData.getRequestPostJson(request);
		JSONObject res= JSONObject.fromObject(jsonStr);
		Map<String,Object> returnContent=weiXinAccessService.getHistoryMachineId(res);
		returnContent.put("requestMethod", "getHistoryMachineId");
		return returnContent;
		}
}
