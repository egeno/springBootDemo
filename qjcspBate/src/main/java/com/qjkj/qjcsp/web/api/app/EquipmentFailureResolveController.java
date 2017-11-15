package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.machine.EquipmentFailureResolveService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 
 * 设备故障处理
 */
@RestController
@RequestMapping("api/app")
public class EquipmentFailureResolveController {

	@Autowired
	private EquipmentFailureResolveService equipmentFailureResolve;
	
	@RequestMapping(value = "/equipmentFailureResolve", method = RequestMethod.POST)
	public Map<String, Object> equipmentFailureResolve(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String postJson = RequestData.getRequestPostJson(request);
		JSONObject res = JSONObject.fromObject(postJson);
		try{
			map=equipmentFailureResolve.processingequipment(res);
			return map;
		}
		catch (Exception e) {
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
			return map;
		}
		
		
	}
}
