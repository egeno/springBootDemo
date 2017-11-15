package com.qjkj.qjcsp.web.weixin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.weixin.WeiXinValidateInfoService;
import com.qjkj.qjcsp.service.weixin.WeiXinWapService;
import com.qjkj.qjcsp.util.RequestData;
import com.qjkj.qjcsp.util.weixin.Constant;
import com.qjkj.qjcsp.util.weixinpay.config.Configure;
import com.qjkj.qjcsp.util.weixinpay.sign.Signature;
import com.qjkj.qjcsp.util.weixinpay.util.RandomStringGenerator;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "weixinwap")
public class WeiXinWapController{
	
	@Autowired
	private WeiXinWapService weiXinWapService;

	@Autowired
	private WeiXinValidateInfoService weiXinValidateInfoService;

	/**
	 * 服务器配置
	 * @param req
	 * @param rsp
	 */
	@RequestMapping(value = "/valid")
	@ResponseBody
	public void valid(HttpServletRequest req, HttpServletResponse rsp) {
		weiXinWapService.valid(req, rsp);
	}
	
	/**
	 * 首页
	 * @param req
	 * @param rsp
	 */
	@RequestMapping(value = "/index")
	public void index(HttpServletRequest req, HttpServletResponse rsp){
		weiXinWapService.index(req, rsp);
	}

	/**
	 * 支付页
	 * @param req
	 * @param rsp
	 * @return
	 */
	@RequestMapping(value="/pay")
	public String pay(HttpServletRequest req, HttpServletResponse rsp){
		 return weiXinWapService.pay(req, rsp);
	}
	
	/**
	 * JSConfig验证
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getSignMap", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSignMap(ServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getSignMap");
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String url = res.getString("url");
			Map<String, String> result = weiXinValidateInfoService.getSignMap(url);

			json.put("returnCode", "1");
			json.put("returnContent", result);
		} catch (Exception e) {
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}
	/**
	 * 获取openId
	 * @param req
	 * @param rsp
	 */
	@RequestMapping(value = "/getwxopenid")
	@ResponseBody
	public Map<String, Object> getwxopenid(HttpServletRequest req, HttpServletResponse rsp){
		return weiXinValidateInfoService.getWebAccessToken(req, rsp);
	}
	@RequestMapping(value = "/getPaySignMap", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPaySignMap(ServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getPaySignMap");
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String nonceStr = res.getString("nonceStr");
			String timeStamp = res.getString("timeStamp");
			String url = res.getString("url");
			Map<String, String> result = weiXinValidateInfoService.getPaySignMap(nonceStr, timeStamp, url);

			json.put("returnCode", "1");
			json.put("returnContent", result);
		} catch (Exception e) {
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}
	
	/**
	 * 获取微信预支付单号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPrepayId", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPrepayId(ServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getPrepayId");
		try {
			JSONObject res = JSONObject.fromObject(postJson); 
			String openid = res.getString("openid");
			String orderNum = res.getString("orderNum");
			String prepayId = weiXinValidateInfoService.getPrepayId(openid, orderNum);
			Map<String, Object> resultMap= new HashMap<String, Object>();
			if(prepayId!=null){
			resultMap = weiXinValidateInfoService.getJSPaySign(prepayId);
			json.put("returnCode", "1");
			json.put("returnContent", resultMap);
			}else{
				json.put("returnCode", "0");
				json.put("returnContent", "获取prepayId出错");
			}
//			json.put("returnCode", "1");
//			json.put("returnContent", prepayId);
		} catch (Exception e) {
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}
	
	/**
	 * 微信支付签名
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPaySign", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPaySign(ServletRequest request) {
		String postJson = RequestData.getRequestPostJson(request);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("requestMethod", "getPaySign");
		try {
			JSONObject res = JSONObject.fromObject(postJson);
			String prepayId = res.getString("prepayId");
			Map<String, Object> resultMap = weiXinValidateInfoService.getPaySign(prepayId);

			json.put("returnCode", "1");
			json.put("returnContent", resultMap);
		} catch (Exception e) {
			json.put("returnCode", "0");
			json.put("returnContent", "服务器错误");
		}
		return json;
	}
}
