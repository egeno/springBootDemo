package com.qjkj.qjcsp.web.statistics;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.service.area.AreaAndModelService;
import com.qjkj.qjcsp.service.stacistics.OperateRecordingService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.web.BaseController;
/**
 * 
 * @author carpeYe 2016-01-22
 *  后台管理->数据统计->清货补货记录
 */
@Controller
@RequestMapping("statistics")
public class OperateRecordingController extends BaseController{
		
	@Autowired
    private AreaAndModelService areaAndModelService;
	
	@Autowired 
	private OperateRecordingService  operateRecordingService;
	
	@RequestMapping("/listOperateRecording")
	public String listOperateRecording(HttpServletRequest request){
		//获取当前登录用户所在公司的ID
		Integer companyId=Constants.getCurrendUser().getCompanyId();
		Map<String,Object> params=new HashMap<String, Object>();
		List<BasicsMachine> machines=areaAndModelService.findAllMachineByCompanyId((long)companyId,params);
		request.setAttribute("machine", machines);
		return "operateRecording/operateRecording";
	}
	
	@RequestMapping("/operateRecording")
	@ResponseBody
	public Map<String,Object> operateRecording(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) throws ParseException{
		//获取当前登录用户所在公司的ID
		Integer companyId=Constants.getCurrendUser().getCompanyId();
		//获取前台所有的条件
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("companyId", companyId);
		return operateRecordingService.operateRecording(searchParams, pageNumber, pageSize);
	}

}
