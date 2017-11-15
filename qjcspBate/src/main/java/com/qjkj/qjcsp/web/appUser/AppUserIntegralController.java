package com.qjkj.qjcsp.web.appUser;

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
import com.qjkj.qjcsp.entity.viewmodel.TblCustomerVo;
import com.qjkj.qjcsp.service.appUser.AppUserService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.Servlets;

@Controller
@RequestMapping(value="/appUser")
public class AppUserIntegralController {
	private static final String PAGE_SIZE = "10";
	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value="/mainList")
	public String mainList(){
		return "/appUser/appUserMaintenance";
	}
	@RequestMapping(value="/IntegralEdit")
	public String IntegralEdit(){
		return "/appUser/IntegralEdit";
	}
	@RequestMapping(value="/customerInfo")
	public String customerInfo(){
		return "/appUser/appUserCustomerInfo";
	}
	/**
	 * 查找APP用户积分信息
	 */
	@RequestMapping(value="/findAppUserIntegral")
	@ResponseBody
	public Map<String, Object> findAppUserIntegral(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		Map<String, Object> map = new HashMap<String, Object>();
		Page<TblCustomerVo> lists = appUserService.findAppUserIntegral(searchParams, pageNumber, pageSize);
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		return map;		
	}
	
	/**
	 * 修改积分
	 */
	@RequestMapping(value="/updateIntegral")
	@ResponseBody
	public HashMap<String, Object> updateIntegral(TblCustomerVo tblCustomerVo){		
		return Constants.getMessage(appUserService.updateIntegral(tblCustomerVo));
	}
	
}

