package com.qjkj.qjcsp.util;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;


/**
 * 类名:Servlets
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-29
 * Http与Servlet工具类.
 */
public class Servlets {

	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParametersStartingWith(ServletRequest request, String prefix) 
	{
		@SuppressWarnings("rawtypes")
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		
		//遍历request中的所有参数
		while ((paramNames != null) && paramNames.hasMoreElements()) 
		{
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) 
			{
				String unprefixed = paramName.substring(prefix.length());//截取去前缀后的参数名
				String[] values = request.getParameterValues(paramName);
				
				if ((values == null) || (values.length == 0)) 
				{
				} 
				else if (values.length > 1) 
				{
					params.put(unprefixed, values);//存储经截取后的参数名和参数值
				} else {
					params.put(unprefixed, values[0].trim());
				}
			}
		}
		return params;
	}

}
