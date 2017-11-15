package com.qjkj.qjcsp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.core.shiro.CaptchaUsernamePasswordToken;
import com.qjkj.qjcsp.core.shiro.IncorrectCaptchaException;
import com.qjkj.qjcsp.core.shiro.LoginSourceEnum;
import com.qjkj.qjcsp.core.shiro.ShiroUser;
import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.service.company.CompanyService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.MD5Util;

/*
 * 类名:LoginController
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-19
 * 用户登陆验证Controller
 */
@Controller
public class LoginController {
	@Autowired
	private CompanyService companyService;
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * @function 跳转至系统登录页
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		// 得到公司的id和公司名称
		List<CompanyPo> lists = companyService.getCompanyIdAndName();
		model.addAttribute("data", lists);
		return "login/login";
	}

	/**
	 * @function 用户登录验证
	 * @param request
	 * @param userName
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param captcha
	 *            验证数字
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ReturnJson login(ServletRequest request, @RequestParam("username") String userName,
			@RequestParam("password") String password, @RequestParam("companyId") String companyId,
			@RequestParam("captcha") String captcha, Model model) {
		Subject subject = SecurityUtils.getSubject();
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		ReturnJson json = new ReturnJson();// 登录信息返回信息
		// System.out.println(isUserofQJKJ);
		// 使用shiro进行用户登录验证
		token.setUsername(userName);
		token.setPassword(MD5Util.getMD5String(password).toCharArray());
		token.setCaptcha(captcha);
		token.setSource(LoginSourceEnum.BACKGROUND.getValue());
		token.setRememberMe(true);
		token.setCompanyId(companyId);

		json.setTitle("登录提示");
		try {
			subject.login(token);
			json.setStatus(true);
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			logger.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
		} catch (UnknownAccountException ex) {
			logger.error(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
			json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		} catch (IncorrectCredentialsException ice) {
			json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
		} catch (LockedAccountException lae) {
			json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
		} catch (IncorrectCaptchaException e) {
			json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		} catch (AuthenticationException ae) {
			json.setMessage(Constants.AUTHENTICATION_EXCEPTION);
		} catch (Exception e) {
			json.setMessage(Constants.UNKNOWN_EXCEPTION);
		}
		return json;
	}

	@RequestMapping(value = "/checkSession", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkSession() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "1");
		//map.put("url", request.getRequestURL());
		return map;
	}

}
