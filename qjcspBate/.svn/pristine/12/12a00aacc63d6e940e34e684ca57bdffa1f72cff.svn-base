package com.qjkj.qjcsp.web.api.wechat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.machine.WeiXinAccessService;
import com.qjkj.qjcsp.service.wechatapi.WeiXinAccessWeChatService;
import com.qjkj.qjcsp.service.wechatapi.WeiXinLeagueService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("api/wechat/")
public class WeiXinAccessWeChatController {

	@Autowired
	private WeiXinAccessWeChatService weiXinAccessWeChatService;
	@Autowired
	private WeiXinLeagueService weiXinLeagueService;

	@RequestMapping("getHistoryMachineIdWX")
	public Map<String, Object> getHistoryMachineIdWX(HttpServletRequest request) {
		String jsonStr = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(jsonStr);
		Map<String, Object> returnContent = weiXinAccessWeChatService.getHistoryMachineIdWX(res);
		returnContent.put("requestMethod", "getHistoryMachineIdWX");
		return returnContent;
	}

	@RequestMapping("addWeiXinLeagueInfoWX")
	public Map<String, Object> addWeiXinLeagueInfoWX(HttpServletRequest request) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		try {
			String jsonStr = RequestData.getRequestPostJson(request);
			JSONObject res = JSONObject.fromObject(jsonStr);
			returnContent = weiXinLeagueService.addWeiXinLeagueInfoWX(res);
			returnContent.put("requestMethod", "addWeiXinLeagueInfoWX");
			return returnContent;
		} catch (Exception e) {
			// TODO: handle exception
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "服务器错误");
			return returnContent;
		}
	}
}
