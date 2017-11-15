package com.qjkj.qjcsp.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
* 类功能说明 TODO:自定义表单过滤，加入验证码
* 类修改者
* 修改日期
* 修改说明
*/

public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter
{
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha"; 
	
	public static final String DEFAULT_SOURCE_PARAM = "background" ;
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	
	private String soureParam = DEFAULT_SOURCE_PARAM ;
	
	public String getCaptchaParam()
	{
		return captchaParam;
	}
	public void setCaptchaParam(String captchaParam )
	{
		this.captchaParam = captchaParam;
	} 
	
	protected String getCaptcha(ServletRequest request )
	{
		return WebUtils.getCleanParam(request, getCaptchaParam());

	}
	

	public String getSoureParam() {
		return soureParam;
	}
	public void setSoureParam(String soureParam) {
		this.soureParam = soureParam;
	}
	
	protected String getSource(ServletRequest request )
	{
		return WebUtils.getCleanParam(request,this.getSoureParam());

	}

	
	
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response )
	{
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		String source = getSource(request) ;
		return new CaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha,source);
	}
}
