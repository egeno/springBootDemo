package com.qjkj.qjcsp.util;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 监听项目的启动情况，如果项目启动成功则new 一个date 并记录到application中 以便前台jsp的调用
 * @author carpeYe 2016-02-26
 *
 */
public class TomcatStartTimeListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		Date time=new Date();
		sce.getServletContext().setAttribute("tomcatStartTime", time);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		//System.out.println("ServletContextListener stop");		
	}

}
