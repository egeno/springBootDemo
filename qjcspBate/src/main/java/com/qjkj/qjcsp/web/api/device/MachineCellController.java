package com.qjkj.qjcsp.web.api.device;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.machine.MachineCellService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/api/device")
public class MachineCellController {
	@Autowired
	private MachineCellService machineCellService;
	/**
	 * 2.2.7.	获取补货格子列表
	 * @param deviceCode:设备Id cellsNum：商品数量
	 * */
	@RequestMapping(value = "/getOperateCellsNum", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getOperateCellsNum(HttpServletRequest request) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		try {
			String postJson = RequestData.getRequestPostJson(request);
			JSONObject req = JSONObject.fromObject(postJson);
			returnJson = machineCellService.getOperateCellsNum(req);
			returnJson.put("requestMethod", "getOperateCellsNum");
			return returnJson;
		} catch (Exception e) {
			// TODO: handle exception
			returnJson.put("requestMethod", "getOperateCellsNum");
			returnJson.put("returnContent", "服务器错误");
			return returnJson;
		}
	}
}
