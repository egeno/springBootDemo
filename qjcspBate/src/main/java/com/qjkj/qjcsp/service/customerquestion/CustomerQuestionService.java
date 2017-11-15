package com.qjkj.qjcsp.service.customerquestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblCustomerQuestion;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.util.Servlets;

/**
 * 客户反馈管理
 * 
 * @author wsk 2016年3月28日14:39:24
 *
 */
@Service
public class CustomerQuestionService {
	@Autowired
    TblCustomerMapper tblCustomerMapper; 
	
	/**
	 * 得到客户反馈的信息
	 * 
	 * @param customermobile 手机号
	 * @param createtime 反馈时间
	 * @return Map<String, Object>
	 */
	 
	public Map<String, Object> findQuustion(ServletRequest request,int pageNumber,int pageSize){
		//得到页面传来的值
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("offset", (pageNumber - 1) * pageSize);
		searchParams.put("limit", pageSize);
		
		Map<String,Object> returnmap = new HashMap<String, Object>();
		
		int count = tblCustomerMapper.getQuestionnum(searchParams);
		List<Map<String, Object>> lists = tblCustomerMapper.findCustomerQuestion(searchParams);
		
		returnmap.put("rows", lists);
		returnmap.put("total", count);
		
		return returnmap;
	}

}
