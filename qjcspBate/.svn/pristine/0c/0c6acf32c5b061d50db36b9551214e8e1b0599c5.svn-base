package com.qjkj.qjcsp.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;


public class BaseController{

	 /**
	  * 所有ActionMap 统一从这里获取
	  * @return 
	  */
	public static final String SUCCESS ="success";  
	public static final String MSG ="msg"; 
	
	public static final String PAGE_SIZE = "10";
	
	public Map<String,Object> getRootMap(){
		Map<String,Object> rootMap = new HashMap<String, Object>();
		//添加url到 Map中
		ServletRequestAttributes attr = (ServletRequestAttributes) 
											RequestContextHolder.
											currentRequestAttributes(); 
		return rootMap;
	}
	
	public ModelAndView forword(String viewName,Map context){
		return new ModelAndView(viewName,context); 
	}
	
	public ModelAndView forword(String viewName){
		return new ModelAndView(viewName); 
	}
	
	public ModelAndView error(String errMsg){
		return new ModelAndView("/error/notFoundPage"); 
	}
	
	/**
	 *
	 * 提示成功信息
	 *
	 * @param message
	 *
	 */
	public Map<String, Object> sendSuccessMessage(String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		return result;
	}

	/**
	 *
	 * 提示失败信息
	 *
	 * @param message
	 *
	 */
	public Map<String, Object> sendFailureMessage(String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
		return result;
	}
}
