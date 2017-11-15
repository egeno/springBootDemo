package com.qjkj.qjcsp.web.api.device;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.goods.GoodsInfoService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/device")
public class BasicsGoodsController {
	@Autowired
	private GoodsInfoService goodsService;

	@RequestMapping(value = "/getClearGoodsList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getClearGoodsList(HttpServletRequest request) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		try {
			String postJson = RequestData.getRequestPostJson(request);
			JSONObject res = JSONObject.fromObject(postJson);
			if (StringUtils.isEmpty(res.getString("deviceCode")) || StringUtils.isEmpty(res.getString("userId"))) {
				returnContent.put("returnCode", "0");
				returnContent.put("returnContent", "请求参数错误");
				return returnContent;
			} else {
				returnContent = goodsService.getClearGoodsList(res.getString("deviceCode"), res.getLong("userId"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "服务器错误");
		}
		return returnContent;
	}
}
