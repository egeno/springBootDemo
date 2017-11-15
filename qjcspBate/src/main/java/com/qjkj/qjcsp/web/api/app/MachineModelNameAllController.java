package com.qjkj.qjcsp.web.api.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.service.machine.MachineModelNameAllService;
import com.qjkj.qjcsp.util.RequestData;

/**
 * 获得设备模型接口
 * @author wsk 
 * @date 2016年4月12日14:18:25
 */
@Controller
@RequestMapping("/api/app")
public class MachineModelNameAllController {
	
	@Autowired
	MachineModelNameAllService machineModelNameAllService;

	
	/*
	 * 根据设备id得到当前设备下的所有模型，及模型的清货补货时间
	 */
	@RequestMapping(value="/preissueAreaModelName",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> preissueAreaModelName(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			String str = RequestData.getRequestPostJson(req);
			JSONObject json = JSONObject.fromObject(str);
			
			String machineid = json.getString("machineId");
			
			if(machineid != null && !"".equals(machineid)){
				
				Map<String, Object> returnmap	= machineModelNameAllService.getModelAll(machineid);
				map.put("returnCode", returnmap.get("returnCode"));
				map.put("returnContent", returnmap.get("returnContent"));
				
		    }else{
		    	map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
			    return map;
		     }
		} catch (Exception e) {
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
		    return map;
		}
		
		return map;
	} 

}
