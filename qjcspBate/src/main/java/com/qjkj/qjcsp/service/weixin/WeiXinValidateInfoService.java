package com.qjkj.qjcsp.service.weixin;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.util.weixin.Constant;
import com.qjkj.qjcsp.util.weixin.ConvertXMLAndMap;
import com.qjkj.qjcsp.util.weixin.JSSignUtil;
import com.qjkj.qjcsp.util.weixinpay.config.Configure;
import com.qjkj.qjcsp.util.weixinpay.sign.Signature;
import com.qjkj.qjcsp.util.weixinpay.util.RandomStringGenerator;

import net.sf.json.JSONObject;

@Service("WeiXinValidateInfoService")
public class WeiXinValidateInfoService {

	private static Logger logger = LoggerFactory.getLogger(WeiXinValidateInfoService.class);
	
	@Autowired
	private TblOrderMapper tblOrderMapper;
	
	// 获得基础支持ACCESS_TOKEN
	public String getAccessToken() {

		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constant.APPID
				+ "&secret=" + Constant.APPSECRET;

		String accessToken = null;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			JSONObject demoJson = JSONObject.fromObject(message);
			accessToken = demoJson.getString("access_token");//取出json中key为access_token的value，返回的是value

//			System.out.println(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	// 获得网页授权ACCESS_TOKEN
	public JSONObject getWebAccessToken(HttpServletRequest req, HttpServletResponse rsp) {
		
		rsp.reset();
		
		String code = req.getParameter("code");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constant.APPID + "&secret="
				+ Constant.APPSECRET + "&code=" + code + "&grant_type=authorization_code";

		JSONObject webAccessJson = null;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			webAccessJson = JSONObject.fromObject(message);

//			System.out.println(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return webAccessJson;
	}

	// 获得用户信息
	public JSONObject getUserInfo(HttpServletRequest req, HttpServletResponse rsp, String token, String openId) {

		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openId
				+ "&lang=zh_CN";

		JSONObject userInfoJson = null;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			 userInfoJson = JSONObject.fromObject(message);
			
//			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfoJson;
	}
	// 获取用户基本信息(UnionID机制)
		public JSONObject getCgiUserInfo(HttpServletRequest req, HttpServletResponse rsp, String token, String openId) {

//			String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openId
//					+ "&lang=zh_CN";
			String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + token + "&openid=" + openId+"&lang=zh_CN";
			JSONObject userInfoJson = null;
			try {
				URL urlGet = new URL(url);
				HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

				http.setRequestMethod("GET"); // 必须是get方式请求
				http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);
				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
				System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

				http.connect();

				InputStream is = http.getInputStream();
				int size = is.available();
				byte[] jsonBytes = new byte[size];
				is.read(jsonBytes);
				String message = new String(jsonBytes, "UTF-8");

				 userInfoJson = JSONObject.fromObject(message);
				
				System.out.println(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return userInfoJson;
		}
	// 获得基础支持jsapiTicket
	public static String getTicket(String accessToken) {

		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";

		String ticket = null;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			JSONObject demoJson = JSONObject.fromObject(message);
			ticket = demoJson.getString("ticket");

//			System.out.println(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}
	
	//JSConfig验证
	public Map<String, String> getSignMap(String url) {

		String jsapiTicket = getTicket(getAccessToken());
		Map<String, String> signMap = JSSignUtil.sign(jsapiTicket, url);

		return signMap;
	}
	//支付页面JSConfig验证
	public Map<String, String> getPaySignMap(String nonceStr, String timeStamp, String url) {

		String jsapiTicket = getTicket(getAccessToken());
		Map<String, String> signMap = JSSignUtil.sign(jsapiTicket, nonceStr, timeStamp, url);

		return signMap;
	}
	
	//获取网页授权后的openId和nickName
	public Map<String, String> getOpenIdAndNickName(HttpServletRequest req, HttpServletResponse rsp) {
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			JSONObject webAccessJson = getWebAccessToken(req, rsp);
			String accessToken = webAccessJson.getString("access_token");
			String openId = webAccessJson.getString("openid");
			
			//JSONObject userInfoJson = getUserInfo(req, rsp, accessToken, openId);
			JSONObject userInfoJson = getCgiUserInfo(req, rsp, getAccessToken(), openId);
			String nickName = URLEncoder.encode(userInfoJson.getString("nickname"), "UTF-8");
			String subscribe_time = URLEncoder.encode(userInfoJson.getString("subscribe_time"), "UTF-8");
			
			returnMap.put("openId", openId);
			returnMap.put("nickName", nickName);
			Long subscribeTime = Long.parseLong(subscribe_time);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date(subscribeTime*1000L));
			returnMap.put("subscribeTime", date);
		}catch (Exception e) {
			logger.debug("获取网页授权异常", e);
		}

		return returnMap;
	}
	//Gekko 根据openId和网页token获取用户详细信息
		public Map<String, String> getUserInfoAndNickName(HttpServletRequest req, HttpServletResponse rsp,String openId) {
			Map<String, String> returnMap = new HashMap<String, String>();
			try {
				//JSONObject userInfoJson = getUserInfo(req, rsp, accessToken, openId);
				JSONObject userInfoJson = getCgiUserInfo(req, rsp, getAccessToken(), openId);
				//String nickName = URLEncoder.encode(userInfoJson.getString("nickname"), "UTF-8");
				String nickName = userInfoJson.getString("nickname");
				String subscribe_time = URLEncoder.encode(userInfoJson.getString("subscribe_time"), "UTF-8");
				
				returnMap.put("openId", openId);
				returnMap.put("nickName", nickName);
				Long subscribeTime = Long.parseLong(subscribe_time);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(new Date(subscribeTime*1000L));
				returnMap.put("subscribeTime", date);
			}catch (Exception e) {
				logger.debug("获取网页授权异常", e);
			}

			return returnMap;
		}
	
	//获取prepayId
	public  String getPrepayId(String openid, String orderNum) {
		try {
			//获取订单信息//
			TblOrder tblOrder = tblOrderMapper.searchOrder(orderNum);
			//订单金额
			BigDecimal bdmTotalFee = tblOrder.getRealMoney().multiply(new BigDecimal(100));//微信中支付金额以分为单位，所以需要*100
			String goodsDescribe = null;
			if (StringUtils.equals(tblOrder.getOrderType(), "0")){
				goodsDescribe = "预订商品";
			} else if (StringUtils.equals(tblOrder.getOrderType(), "1")) {
				goodsDescribe = "零售商品";
			}
			
			Map<String, Object> contentMap = new TreeMap<String, Object>();
	        contentMap.put("appid", Configure.getMachineAppID()); // 公众账号 ID
	        contentMap.put("trade_type","JSAPI"); // 交易类型
	        contentMap.put("mch_id", Configure.getMachineMchID()); // 商户号
	        contentMap.put("body", goodsDescribe); // 商品描述
	        contentMap.put("out_trade_no",orderNum); // 商户订单号
	        contentMap.put("total_fee",bdmTotalFee.intValue()); // 订单总金额
	        contentMap.put("spbill_create_ip",InetAddress.getLocalHost().getHostAddress()); // 订单生成的机器IP
	        contentMap.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32)); // 随机字符串                
	        contentMap.put("notify_url",Constant.DOMAIN+"payNotifyUrl.jsp"); // 通知地址
	        contentMap.put("openid",openid); // 微信的用户标识
	        String sign = Signature.getSign(contentMap);
	        contentMap.put("sign", sign);

	        ConvertXMLAndMap cxtm = new ConvertXMLAndMap();
	        String postData = cxtm.getXMLfromMapInISO(contentMap);
			
			Map<String, Object> resultMap = getData(Configure.UNIFIEDORDER_API, postData);
//		    System.out.println(resultMap);
		    return resultMap.get("prepay_id").toString();
		}catch (Exception e) {
			logger.debug("获取prepayId异常", e);
		}
		return null;
	}
	
	//app支付签名
	public Map<String, Object> getPaySign(String prepayId) {
		try {
			Map<String, Object> wxPayParamMap = new TreeMap<String, Object>();
	        wxPayParamMap.put("appId", Configure.getAppid());
	        wxPayParamMap.put("nonceStr", RandomStringGenerator.getRandomStringByLength(32));
	        wxPayParamMap.put("package", "prepay_id="+prepayId);
	        wxPayParamMap.put("signType", "MD5");
	        wxPayParamMap.put("timeStamp", Long.toString(System.currentTimeMillis() / 1000));      
	        String paySign = Signature.getSign(wxPayParamMap);
	        wxPayParamMap.put("paySign", paySign);
	        
	        return wxPayParamMap;
		}catch (Exception e) {
			logger.debug("获取paySign异常", e);
		}
        
		return null;
	}
	//js支付签名
		public Map<String, Object> getJSPaySign(String prepayId) {
			try {
				Map<String, Object> wxPayParamMap = new TreeMap<String, Object>();
		        wxPayParamMap.put("appId", Configure.getMachineAppID());
		        wxPayParamMap.put("nonceStr", RandomStringGenerator.getRandomStringByLength(32));
		        wxPayParamMap.put("package", "prepay_id="+prepayId);
		        wxPayParamMap.put("signType", "MD5");
		        wxPayParamMap.put("timeStamp", Long.toString(System.currentTimeMillis() / 1000));      
		        String paySign = Signature.getSign(wxPayParamMap);
		        wxPayParamMap.put("paySign", paySign);
		        
		        return wxPayParamMap;
			}catch (Exception e) {
				logger.debug("获取paySign异常", e);
			}
	        
			return null;
		}
	public Map<String, Object> getData(String url, String data) {
		try {
			byte[] dataByte=data.getBytes();
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	
			http.setRequestMethod("POST"); 
			http.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
			http.setRequestProperty("Content-Length", String.valueOf(dataByte.length));
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			
			DataOutputStream out = new DataOutputStream(http  
                    .getOutputStream());  
            out.writeBytes(data); //写入请求的字符串  
            out.flush();  
            out.close();  
	
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String res = new String(jsonBytes, "UTF-8");
	
	        ConvertXMLAndMap cxtm = new ConvertXMLAndMap();
			Map<String, Object> resultMap = cxtm.getMapfromXML(res);
			
			return resultMap;
		}catch (Exception e) {
			logger.debug("发送签名数据异常",	 e);
		}
		return null;
	}

}
