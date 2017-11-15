package com.qjkj.qjcsp.web.statistics;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.stacistics.FirstOrderDiscountActivityService;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.web.BaseController;

/**
 * 类名:FirstOrderDiscountController
 * 版本号：V1.0
 * 日期：2016-02-26
 * 首单优惠活动统计Controller
 */
@Controller
@RequestMapping("statistics")
public class FirstOrderDiscountActivityController extends BaseController{

	@Autowired
	private FirstOrderDiscountActivityService firstOrderDiscountActivityService;
	
	@RequestMapping(value="listFirstOrderDiscountActivityMain")
	public String listFirstOrderDiscountActivityMain(){
		return "/statistics/firstOrderDiscountActivity/listFirstOrderDiscountActivityMain";
	}
	
	/**
	 * 查询所有信息
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findFirstOrderPage")
	@ResponseBody
	public Map<String, Object> findFirstOrderPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		Map<String ,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<Map<String, Object>> lists = firstOrderDiscountActivityService.findFirstOrderPage(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		
		return map ;
	}
	
}
