package com.qjkj.qjcsp.core.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
* 类功能说明 TODO:密码以及验证码Token
* 类修改者
* 修改日期
* 修改说明
* <p>Title: CaptchaUsernamePasswordToken.java</p>
*/

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken
{
	private static final long serialVersionUID = -3217596468830869181L;
	private String captcha;
	private String companyId;
	private String source  ;

	public String getCaptcha()
	{
		return captcha;
	}

	public void setCaptcha(String captcha )
	{
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken(String username, String password, boolean rememberMe,
			String host,String captcha,String source)
	{
		super(username, password, rememberMe, host);
		this.captcha = captcha; 
		this.source = source ;
	}

	public CaptchaUsernamePasswordToken()
	{
		super();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	
}
