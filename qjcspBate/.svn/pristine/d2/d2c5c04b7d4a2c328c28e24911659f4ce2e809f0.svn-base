package com.qjkj.qjcsp.util;

import com.qjkj.qjcsp.core.shiro.ShiroUser;

/**
 * 用户相关工具类
 */
public class UserUtils {

	private ShiroUser user = Constants.getCurrendUser();

	/**
	 * 判断当前用户是否属于全家科技公司或平台超级用户
	 * @return true表示是；false表示否
	 */
	public boolean isUserofQJKJ(){
		if (user.getUserType() == Constants.SYSTEM_USERTYPE_MERCHANT_COMMON_USER  ||
				user.getUserType() == Constants.SYSTEM_USERTYPE_PLATFORM_ADMIN ||
				user.getUserType() == Constants.SYSTEM_USERTYPE_PLATFORM_COMMON_USER){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 获取当前用户id
	 * @return
	 */
	public Integer getUserId(){
		return user.getUserId();
	}
	
	/**
	 * 获取当前用户帐号
	 * @return
	 */
	public String getAccount(){
		return user.getAccount();
	}
	
	/**
	 * 获取当前用户所属公司
	 * @return
	 */
	public Integer getCompanyId(){
		return user.getCompanyId();
	}
	
	/**
	 * 获取当前用户类型
	 * @return
	 */
	public Short getUserType(){
		return user.getUserType();
	}
}
