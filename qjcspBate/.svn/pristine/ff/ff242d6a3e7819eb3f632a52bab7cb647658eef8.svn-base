package com.qjkj.qjcsp.web.customerquestion;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.customerquestion.CustomerQuestionService;
import com.qjkj.qjcsp.util.Servlets;

/**
 * 用户反馈管理
 * 
 * @author wsk 2016年3月28日17:11:44
 *
 */
@Controller
@RequestMapping("customerQuestion")
public class CustomerQuestionController{
	private static final String PAGE_SIZE = "10";
	
	@Autowired
	private CustomerQuestionService customerQuestionService;
    
	@RequestMapping(value="/listCustomerQuestion")
	public String listCustomerQuestion(){
		
		return "customerQuestion/customerQuestion";
	}
	@RequestMapping(value="/findCustomerQuestion")
	@ResponseBody
    public Map<String, Object> findCustomerQuestion(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
    	
    	return customerQuestionService.findQuustion(request, pageNumber, pageSize);
    }
}
