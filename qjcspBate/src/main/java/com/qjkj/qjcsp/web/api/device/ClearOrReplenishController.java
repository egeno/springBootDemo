package com.qjkj.qjcsp.web.api.device;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.mapper.TblOperateRecordLogMapper;
import com.qjkj.qjcsp.service.machine.ClearOrReplenishService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("api/device/")
public class ClearOrReplenishController {
	
	@Autowired
	private ClearOrReplenishService clearOrReplenishService;
	
	@Autowired
	private TblOperateRecordLogMapper tblOperateRecordLogMapper;
	
	@RequestMapping("clearance")
	public Map<String,Object> clearance(HttpServletRequest request){
		String jsonStr=RequestData.getRequestPostJson(request);
		JSONObject res=JSONObject.fromObject(jsonStr);
		Map<String,Object> returnContent=clearOrReplenishService.clearance(res);
		returnContent.put("requestMethod", "clearance");
		return returnContent;
	}

	/**
	 * 补货
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/replenishment", method=RequestMethod.POST)
	public Map<String, Object> replenishment(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "replenishment");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = clearOrReplenishService.replenishment(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		
		return returnJson;
	}
	
	/**
	 * 判断当前模型预订是否结束
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/whetherOrNotTheReservationIsOver", method=RequestMethod.POST)
	public Map<String, Object> whetherOrNotTheReservationIsOver(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "whetherOrNotTheReservationIsOver");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			Map<String, Object> returnData = clearOrReplenishService.whetherOrNotTheReservationIsOver(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		
		return returnJson;
	}
	
	/**
	 * 当前模型清货时间点判断
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/whetherOrNotTheClearanceTime", method=RequestMethod.POST)
	public Map<String, Object> whetherOrNotTheClearanceTime(ServletRequest request){
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("requestMethod", "whetherOrNotTheClearanceTime");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			//暂使用 零售清货时间进行判断，
			Map<String, Object> returnData = clearOrReplenishService.whetherOrNotTheClearanceTime(res);
			returnJson.put("returnCode", returnData.get("returnCode"));
			returnJson.put("returnContent", returnData.get("returnContent"));
		} catch (Exception e) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "服务器错误");
		}
		return returnJson;
	}
}
