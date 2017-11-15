package com.qjkj.qjcsp.web.employee;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.service.employee.EmployeeService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.Servlets;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController {
	
	private static final String PAGE_SIZE = "10";
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value="/employeeMain")
	public String employeeMain(){
		return "/employee/employeeMain";
	}
	@RequestMapping(value="/mantenanceMain")
	public String mantenanceMain(){
		return "/mantenance/mantenanceMain";
	}
	@RequestMapping(value="/maintenanceWorkerList")
	public String maintenanceWorkerList(){
		return "/mantenance/maintenanceWorkerList";
	}
	
	/**
	 * 查找该登录用户的供货员
	 */
	@RequestMapping(value="/findAllEmployeeList")
	@ResponseBody
	public Map<String, Object> findAllEmployeeList(ServletRequest request,@RequestParam(value = "employee_type", defaultValue = "0") char employee_type,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Map<String,Object>> lists = employeeService.findAllEmployeeList(searchParams, pageNumber, pageSize);
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		return map;		
	}
	
	/**
	 * 供货员保存设置
	 */
	@RequestMapping(value="/saveEmployeeMachine")
	@ResponseBody
	public HashMap<String, Object> saveEmployeeMachine(@RequestParam(value="user_id") Long userId,
			@RequestParam(value="isCheckedIds")String isCheckedIds){		
		return Constants.getMessage(employeeService.saveEmployeeMachine(userId, isCheckedIds));
	}
	
	/**
	 * 查找该登录用户的维修员
	 */
	@RequestMapping(value="/findAllMaintenanceList")
	@ResponseBody
	public Map<String, Object> findAllMaintenanceList(ServletRequest request,@RequestParam(value = "employee_type", defaultValue = "0") char employee_type,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BasicsUsers> lists = employeeService.findAllMaintenanceList(searchParams, pageNumber, pageSize);
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		return map;		
	}
	
	/**
	 * 维修员保存设置
	 */
	@RequestMapping(value="/saveMaintenanceMachine")
	@ResponseBody
	public HashMap<String, Object> saveMaintenanceMachine(@RequestParam(value="user_id") Long userId,
			@RequestParam(value="isCheckedIds")String isCheckedIds){		
		return Constants.getMessage(employeeService.saveMaintenanceMachine(userId, isCheckedIds));
	}
	
	
}

