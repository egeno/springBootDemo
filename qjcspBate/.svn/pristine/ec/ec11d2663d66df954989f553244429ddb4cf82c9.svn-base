package com.qjkj.qjcsp.core.shiro;

import java.io.Serializable;

public  class ShiroUser implements Serializable
{
	private static final long serialVersionUID = -1748602382963711884L;
	private Integer userId;
	private String account;
	private Integer companyId;
	private Short userType;
	
	public ShiroUser(Integer userId, String account, Integer companyId, Short userType)
	{
		super();
		this.userId = userId;
		this.account = account;
		this.companyId = companyId;
		this.userType = userType;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	public String toString()
	{
		return account;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId )
	{
		this.userId = userId;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account )
	{
		this.account = account;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}
	    
}
