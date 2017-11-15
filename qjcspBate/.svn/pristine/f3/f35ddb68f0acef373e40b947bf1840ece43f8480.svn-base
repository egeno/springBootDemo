package com.qjkj.qjcsp.web.statistics;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.stacistics.EveryOrderDiscountActivityService;
import com.qjkj.qjcsp.web.BaseController;

/**
 * 类名:EveryOrderDiscountActivityController
 * 版本号：V1.0
 * 日期：2016-02-26
 * 每单优惠活动统计Controller
 */
@Controller
@RequestMapping("statistics")
public class EveryOrderDiscountActivityController extends BaseController{
	
    @Autowired
	private EveryOrderDiscountActivityService everyOrderDiscountActivityService;
    
    @RequestMapping(value="listEveryOrderDiscountActivityMain")
	public String listFirstOrderDiscountActivityMain(){
		return "/statistics/everyOrderDiscountActivity/listEveryOrderDiscountActivityMain";
	}
    
    
    /**
	 * 查询所有信息
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findEveryOrderPage")
	@ResponseBody
	public Map<String, Object> findFirstOrderPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		return everyOrderDiscountActivityService.findFirstOrderPage(request,pageNumber, pageSize) ;
	}
}
