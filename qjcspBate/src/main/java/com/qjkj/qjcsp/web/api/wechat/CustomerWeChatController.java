package com.qjkj.qjcsp.web.api.wechat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.service.customer.CustomerApiService;
import com.qjkj.qjcsp.service.machine.MachineApiService;
import com.qjkj.qjcsp.service.wechatapi.CustomerWeChatApiService;
import com.qjkj.qjcsp.service.weixin.WeiXinValidateInfoService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/*
 * 类名:CustomerAppController
 * 版本号：V1.0
 * 日期：2015-12-30
 * APP端与用户相关API
 */

@RestController
@RequestMapping("/api/wechat/")
public class CustomerWeChatController {

	private static Logger logger = LoggerFactory.getLogger(CustomerWeChatController.class);

	@Autowired
	private CustomerWeChatApiService customerWeChatApiService;
	@Autowired
	private WeiXinValidateInfoService weiXinValidateInfoService;

	/**
	 * 发送用户注册/登录手机验证码
	 * 微信登陆发送验证码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "sendVerifyCodeMessageWX", method = RequestMethod.POST)
	public Map<String, Object> sendVerifyCodeMessageWX(ServletRequest request,HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "sendVerifyCodeMessageWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String mobileNum = res.getString("mobileNum");
			String openId=res.getString("openId");
			if (StringUtils.isNoneBlank(mobileNum)) {
				Map<String, String> weChatMap=weiXinValidateInfoService.getUserInfoAndNickName(req, rsp, openId);
				Map<String, Object> returnData = customerWeChatApiService.sendVerifyCodeMessageWX(mobileNum, weChatMap.get("openId"), weChatMap.get("subscribeTime"), weChatMap.get("nickName"));	
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("发送验证码异常", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;

	}
	/**
	 * 发送商户登录手机验证码（商户）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loginUserCodeSend", method = RequestMethod.POST)
	public Map<String, Object> loginUserCodeSend(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "loginUserCodeSend");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String mobileNum = res.getString("mobileNum");
			if (mobileNum != null && !mobileNum.isEmpty()) {
				Map<String, Object> returnData = customerWeChatApiService.loginUserCodeSend(mobileNum);	
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("发送验证码异常", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;

	}

	/**
	 * 注册/登录手机验证码验证
	 * 微信注册验证
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "registLoginCodeVerifyWX", method = RequestMethod.POST)
	public Map<String, Object> registLoginCodeVerifyWX(ServletRequest request,HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "registLoginCodeVerifyWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {				
			JSONObject res = JSONObject.fromObject(postJson);
			String mobileNum = res.getString("mobileNum");
			String verifyCode = res.getString("verifyCode");
			String openId = res.getString("openId");
			if (mobileNum != null && !mobileNum.isEmpty() && verifyCode != null && !verifyCode.isEmpty()) {
				Map<String, String> weChatMap=weiXinValidateInfoService.getUserInfoAndNickName(req, rsp, openId);
				Map<String, Object> returnData = customerWeChatApiService.verifyMobileNumCodeWX(mobileNum, verifyCode,openId,weChatMap.get("subscribeTime"), weChatMap.get("nickName"));
				// IOS免测号码和验证码
				/*
				 * if("15837340848".equals(mobileNum) &&
				 * "888881".equals(idCode)) { Map<String,String> returnContent =
				 * new HashMap<String, String>(); returnContent.put("mobileNum",
				 * mobileNum); returnData.put("returnCode", "1");
				 * returnData.put("returnContent", returnContent); }
				 */
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("手机号验证码验证异常", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;

	}

	/**
	 * 登录手机验证码验证（商户）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "verifyLoginUserCodeSend", method = RequestMethod.POST)
	public Map<String, Object> verifyLoginUserCodeSend(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "verifyLoginUserCodeSend");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String mobileNum = res.getString("mobileNum");
			String verifyCode = res.getString("code");
			if (mobileNum != null && !mobileNum.isEmpty() && verifyCode != null && !verifyCode.isEmpty()) {
				Map<String, Object> returnData = customerWeChatApiService.verifyLoginUserCodeSend(mobileNum, verifyCode);
			
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("手机号验证码验证异常", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;

	}
	/**
	 * 根据APP用户id返回用户详细信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getCustomerDetailWX", method = RequestMethod.POST)
	public Map<String, Object> getCustomerDetailWX(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getCustomerDetailWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String customerId = res.getString("customerId");
			if (customerId != null && !customerId.isEmpty()) {
				Map<String, Object> returnData = customerWeChatApiService.getCustomerDetailWX(customerId);

				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("发送验证码异常", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;

	}

	/**
	 * 保存用户编辑后的用户信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveCustomerInfoWX", method = RequestMethod.POST)
	public Map<String, Object> saveCustomerInfoWX(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "saveCustomerInfoWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String nickName = res.getString("nickName");
			String customerId = res.getString("customerId");
			String mobileNum = res.getString("mobileNum");
			String sex = res.getString("sex");
			String place = res.getString("place");
			String schoolId = res.getString("universityName");
			String grade = res.getString("grade");
			String dormitory = res.getString("dormitory");
			String studentCard = res.getString("studentCard");

			if (StringUtils.isNoneBlank(customerId, mobileNum)) {
				Map<String, Object> returnData = customerWeChatApiService.saveCustomerInfoWX(customerId, nickName, mobileNum,
						sex, place, schoolId, grade, dormitory, studentCard);

				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("手机号验证码验证异常", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;

	}

	/**
	 * 提交意见建议
	 */
	@RequestMapping(value = "commitFeedBackWX", method = RequestMethod.POST)
	public Map<String, Object> commitFeedBackWX(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "commitFeedBackWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String mobileNum = res.getString("mobileNum");
			String feedBackContent = res.getString("feedBackContent");

			if (mobileNum != null && !mobileNum.isEmpty()) {
				Map<String, Object> returnData = customerWeChatApiService.commitFeedBackWX(mobileNum, feedBackContent);
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("请求参数错误", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;

	}
	/**
	 * 设置密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="updatePayPasswordWX",method=RequestMethod.POST)
	public Map<String,Object> updatePayPasswordWX(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "updatePayPasswordWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String customerId = res.getString("customerId");
			String oldPassword = res.getString("oldPassword");
			String newPassword = res.getString("newPassword");
			if(customerId != null && !customerId.isEmpty() && oldPassword != null && !oldPassword.isEmpty()){
				Map<String,Object> returnData = customerWeChatApiService.updatePayPasswordWX(customerId, oldPassword, newPassword);
				
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			}else{
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}	

		  }catch (Exception e) {
			  logger.error("发送验证码异常", e);
			  json.put("returnCode", "0");
			  json.put("returnContent", "服务器错误");
		  }
		return json;
		
	}

	/****
	 * 发短信给用户、忘记密码找回密码
	 * 
	 * @param request
	 **/
	@RequestMapping(value = "forgetPasswordSendMessageWX", method = RequestMethod.POST)
	public Map<String, Object> forgetPasswordSendMessageWX(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "forgetPasswordSendMessageWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String mobileNum = res.getString("mobileNum");
			Long customerId = res.getLong("customerId");
			if (mobileNum != null && !mobileNum.isEmpty() && customerId != null) {
				Map<String, Object> returnData = customerWeChatApiService.forgetPasswordSendMessageWX(mobileNum,customerId);
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			} else {
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("发送验证码异常", e);
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}

	/**
	 * 忘记密码验证码验证接口（APP）
	 */
	@RequestMapping(value="verifyForgetPasswordSendCodeWX",method=RequestMethod.POST)
	public Map<String,Object> verifyForgetPasswordSendCodeWX(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "verifyForgetPasswordSendCodeWX");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String customerId = res.getString("customerId");
			String code = res.getString("code");
			if(customerId != null && !customerId.isEmpty() && code != null && !code.isEmpty()){
				Map<String,Object> returnData = customerWeChatApiService.verifyForgetPasswordSendCodeWX(customerId, code);
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			}else{
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}	

		  } catch (Exception e) {
			  logger.error("手机号验证码验证异常", e);
			  json.put("returnCode", "0");
			  json.put("returnContent", "服务器错误");
		  }
		return json;
		
	}
	
	/**
	 * 支付3次密码错误锁定计时接口（APP）
	 */
	@RequestMapping(value="lockPasswordWX",method=RequestMethod.POST)
	public Map<String,Object> lockPasswordWX(ServletRequest request) {
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "lockPassword");
		String postJson = RequestData.getRequestPostJson(request);
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String customerId = res.getString("customerId");
			if(customerId != null && !customerId.isEmpty()){
				Map<String,Object> returnData = customerWeChatApiService.lockPasswordWX(customerId);
				json.put("returnCode", returnData.get("returnCode"));
				json.put("returnContent", returnData.get("returnContent"));
			}else{
				json.put("returnCode", "0");
				json.put("returnContent", "请求参数错误");
			}	

		  } catch (Exception e) {
			  logger.error("手机号验证码验证异常", e);
			  json.put("returnCode", "0");
			  json.put("returnContent", "服务器错误");
		  }
		return json;
		
	}
	
	public static final String POST_URL = "http://www.jianzhou.sh.cn/JianzhouSMSWSServer/http/sendBatchMessage";
	@RequestMapping(value="test",method=RequestMethod.POST)
	public static void readContentFromPost() throws IOException {
		// Post请求的url，与get不同的是不需要带参数
		URL postUrl = new URL(POST_URL);
		// 打开连接
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		// Output to the connection. Default is
		// false, set to true because post
		// method must write something to the
		// connection
		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// Set the post method. Default is GET
		connection.setRequestMethod("POST");
		// Post cannot use caches
		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		// This method takes effects to
		// every instances of this class.
		// URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。
		// connection.setFollowRedirects(true);

		// This methods only
		// takes effacts to this
		// instance.
		// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数
		connection.setInstanceFollowRedirects(true);
		// Set the content type to urlencoded,
		// because we will write
		// some URL-encoded content to the
		// connection. Settings above must be set before connect!
		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// 进行编码
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		// The URL-encoded contend

		//下面为http发送短信模式--------
		String content = "account=" + "jzyy408" + "&" + "password=" + "12344321" + "&" + "sendDateTime=" + "" + "&" + "destmobile=" + "18958077612" + "&"
				+ "msgText=" + URLEncoder.encode("验证码：456789【建周科技】", "UTF-8");

		System.out.println(content);
		out.writeBytes(content);

		out.flush();
		out.close(); // flush and close
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		System.out.println("=============================");
		System.out.println("Contents of post request");
		System.out.println("=============================");
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println("=============================");
		System.out.println("Contents of post request ends");
		System.out.println("=============================");
		reader.close();
		connection.disconnect();
	}
	
}
