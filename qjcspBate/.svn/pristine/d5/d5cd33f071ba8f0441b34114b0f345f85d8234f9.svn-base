package com.qjkj.qjcsp.web.appUser;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.viewmodel.TblCustomerVo;
import com.qjkj.qjcsp.service.appUser.AppUserService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.Servlets;

@Controller
@RequestMapping(value="/appUserInfo")
public class AppUserCustomerInfoController {
	private static final String PAGE_SIZE = "10";
	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value="/customerInfo")
	public String customerInfo(){
		return "/appUser/appUserCustomerInfo";
	}
	
	@RequestMapping(value="/appUserCustomerInfoAdd")
	public String appUserCustomerInfoAdd(){
		return "/appUser/appUserCustomerInfoAdd";
	}
	
	@RequestMapping(value="/appUserCustomerInfoEdit")
	public String appUserCustomerInfoEdit(){
		return "/appUser/appUserCustomerInfoEdit";
	}
	
	/**
	 * 查找APP用户信息
	 */
	@RequestMapping(value="/findAPPUserInfo")
	@ResponseBody
	public Map<String, Object> findAPPUserInfo(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<TblCustomerVo> page = appUserService.findAPPUserInfo(searchParams, pageNumber, pageSize) ;
		map.put("rows", page.getContent());
		map.put("total", page.getTotalElements());
		return map;
	}
	
	/**
	 * 添加、编辑APP用户信息
	 */
	@RequestMapping(value="/persistenceAPPInfoEdit")
	@ResponseBody
	public HashMap<String, Object> persistenceAPPInfoEdit(TblCustomerVo tblCustomerVo){		
		return Constants.getMessage(appUserService.persistenceAPPInfoEdit(tblCustomerVo));
	}
	
	/**
	 * 删除用户信息
	 */
	@RequestMapping(value="/deleteUserInfo")
	@ResponseBody
	public HashMap<String, Object> deleteUserInfo(@RequestParam(value="customerId") Long customerId){
		return Constants.getMessage(appUserService.deleteUserByCustomerId(customerId));
	}
	
	/**
	 * 导出用户信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/exportSearchDaily")
	@ResponseBody
	public void exportSearchDaily(ServletRequest request, HttpServletResponse response){
		appUserService.exportSearchDaily(request, response);
	}

}

