package com.qjkj.qjcsp.core.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.qjkj.qjcsp.core.shiro.CaptchaUsernamePasswordToken;
import com.qjkj.qjcsp.core.shiro.IncorrectCaptchaException;
import com.qjkj.qjcsp.core.shiro.LoginSourceEnum;
import com.qjkj.qjcsp.core.shiro.ShiroUser;
import com.qjkj.qjcsp.entity.BasicsMenu;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.mapper.BasicsMenuMapper;
import com.qjkj.qjcsp.service.shiro.ShiroUserService;
import com.qjkj.qjcsp.util.Constants;

/*
 * 类名:MyShiroRealm
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-19
 * 使用apache中的shiro进行用户身份验证和权限控制，须自定义Realm
 */
public class MyShiroRealm extends AuthorizingRealm {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ShiroUserService shiroUserService;
	@Autowired
	private BasicsMenuMapper basicsMenuMapper;
	

	//shiro用户登录验证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		// 通过表单接收的用户名
		String username = token.getUsername();
		
		if (username != null && !"".equals(username) && doCaptchaValidate(token)) 
		{
			if (LoginSourceEnum.BACKGROUND.getValue().equals(token.getSource())) 
			{
				BasicsUsers validateUser = new BasicsUsers();
				
				validateUser.setCompanyId(Long.valueOf(token.getCompanyId()));
				validateUser.setUserAccount(token.getUsername());
				validateUser.setPassword(String.valueOf(token.getPassword()));
				
				BasicsUsers user = shiroUserService.shiroUserLoginValidate(validateUser);
				if (user != null) 
				{
					if(!Constants.PERSISTENCE_SYMBOL_Y.equals(user.getIsdel()))//判断是否被删除
					{
						Subject subject = SecurityUtils.getSubject();
						subject.getSession().setAttribute(Constants.SHIRO_USER,
								new ShiroUser(user.getUserId().intValue(), user.getUserAccount(),user.getCompanyId().intValue(),user.getUserType()));
						
						return new SimpleAuthenticationInfo(new ShiroUser(user.getUserId().intValue(), user.getUserAccount(),user.getCompanyId().intValue(),user.getUserType()),
								user.getPassword(), getName());
					}
					else//找不到用户
					{
						throw new UnknownAccountException();
					}
				}
			}
		}
		
		return null;
	}
	
	//用户授权验证
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
		String username = shiroUser.getAccount();
		
		if (username != null) 
		{
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 查询用户授权信息
			// 超级管理员默认拥有所有操作权限
			List<BasicsMenu> perList = basicsMenuMapper.queryMenuList();
			
			if (perList != null && perList.size() != 0) {
				for (BasicsMenu basicsMenu : perList) {
					info.addStringPermission(basicsMenu.getMyid());
				}
				return info;
			}
		}
		return null;
	}
	
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	// 验证码校验
	protected boolean doCaptchaValidate(CaptchaUsernamePasswordToken token) {
		String captcha = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
			throw new IncorrectCaptchaException("验证码错误！");
		}
		return true;
	}
}
