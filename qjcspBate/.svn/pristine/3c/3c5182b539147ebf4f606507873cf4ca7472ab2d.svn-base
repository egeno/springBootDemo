package com.qjkj.qjcsp.web.api.wechat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsJoininfo;
import com.qjkj.qjcsp.service.wechatapi.BasicsJoininfoService;
import com.qjkj.qjcsp.util.RequestData;
import com.qjkj.qjcsp.web.BaseController;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("api/wechat")
public class HomeApiController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(HomeApiController.class);
	/**
	 * 2016/6/30 官网前端调用接口
	 * 
	 */

	@Autowired
	private BasicsJoininfoService basicsJoininfoService;

	
	/**
	 * 合作申请总接口
	 */
	@RequestMapping("addJoin")
	@ResponseBody
	public Map<String, Object> addJoin(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String postJson = RequestData.getRequestPostJson(request);
			JSONObject res = JSONObject.fromObject(postJson);
			map = basicsJoininfoService.addJoin(res);
		} catch (Exception e) {
			logger.error("报错信息：", e);
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误，申请失败");
		}
		return map;
	}
}
