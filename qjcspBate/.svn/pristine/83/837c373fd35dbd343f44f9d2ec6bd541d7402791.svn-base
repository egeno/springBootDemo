package com.qjkj.qjcsp.util.weixinpay.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.io.Resource;

import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.FileUtility;
import com.qjkj.qjcsp.util.PayNotifyConstants;
import com.qjkj.qjcsp.util.weixinpay.config.Configure;
import com.qjkj.qjcsp.util.weixinpay.protocol.CancelOrderReqData;
import com.qjkj.qjcsp.util.weixinpay.protocol.RefundData;
import com.qjkj.qjcsp.util.weixinpay.protocol.ScanPayQueryReqData;
import com.qjkj.qjcsp.util.weixinpay.protocol.UnifiedOrderReqData;
import com.qjkj.qjcsp.util.weixinpay.service.CancelOrderService;
import com.qjkj.qjcsp.util.weixinpay.service.RefundService;
import com.qjkj.qjcsp.util.weixinpay.service.ScanPayQueryService;
import com.qjkj.qjcsp.util.weixinpay.service.UnifiedOrderService;
import com.qjkj.qjcsp.util.weixinpay.sign.Signature;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/* *
 *类名：AlipayFunction
 *功能：支付宝接口公用函数类
 *详细：该类是请求、通知返回两个文件所调用的公用函数核心处理文件，不需要修改
 *版本：3.3
 *日期：2012-08-14
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class WeiXinpayCore {

	public static Map<String, String> prePay(String out_trade_no, String total_amount, String placeOrderTime,
			String subject) {

		String strResult = "";
		Map<String, String> returnContent = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			// SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss");
			String time_expire = sdf
					.format(DateFormat.dateFormatYYYYMMDDHHMMSS(placeOrderTime).getTime() + 5 * 60 * 1000);
			// String time_expire= sdf.format(System.currentTimeMillis()+
			// 5*60*1000);
			// System.out.println(time_expire);
			UnifiedOrderReqData unifiedOrderReqData = new UnifiedOrderReqData("订餐", "", out_trade_no,
					(int) (Float.valueOf(total_amount) * 100), "", PayNotifyConstants.WEIXINPAY_NOTIFY_IP, "",
					time_expire, "", PayNotifyConstants.WEIXINPAY_NOTIFY_URL, "NATIVE");
			UnifiedOrderService unifiedOrderService = new UnifiedOrderService();
			strResult = unifiedOrderService.request(unifiedOrderReqData);
			strResult = strResult.replaceAll("<xml>", "<map>");
			strResult = strResult.replaceAll("</xml>", "</map>");
			XStream xstream = new XStream(new DomDriver());
			xstream.registerConverter(new PojoMapConverter());
			Map<String, Object> resultMap = (Map<String, Object>) xstream.fromXML(strResult);
			String code_url = (String) resultMap.get("code_url");
			String return_code = (String) resultMap.get("return_code");
			// 支付二维码由客户端生成
			/*
			 * String wxPayQrImgFileName = "wx" + Util.getNowFullDateString() +
			 * RandomStringGenerator.getRandomStringByLength(3) + ".png"; String
			 * wxPayQrImgFilePath = Configure.getContextPath() +
			 * "uploadImage\\qRcode\\"; QRCodeGenerator.encoderQRCode(code_url,
			 * wxPayQrImgFilePath + wxPayQrImgFileName, "PNG");
			 */
			// System.out.println("strResult:" + strResult);
			// System.out.println("weixinpaySatus"+return_code);
			if (null != return_code && return_code.equals("SUCCESS")) {
				returnContent = new HashMap<String, String>();
				returnContent.put("qrCode", code_url);
				returnContent.put("orderNum", out_trade_no);
				returnContent.put("placeOrderTime", placeOrderTime);
				return returnContent;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return returnContent;
	}

	public static Map<String, String> queryPay(String out_trade_no, String commitMode) {

		String strResult = "";
		Map<String, String> returnContent = null;
		ScanPayQueryReqData scanPayQueryReqData = null;
		try {
			if (Constants.COMMIT_ORDER_DEVICE.equals(commitMode)) {
				scanPayQueryReqData = new ScanPayQueryReqData("", out_trade_no, Configure.getMachineAppID(),
						Configure.getMachineMchID());
			} else {
				scanPayQueryReqData = new ScanPayQueryReqData("", out_trade_no, Configure.getAppid(),
						Configure.getMchid());
			}
			ScanPayQueryService scanPayQueryService = new ScanPayQueryService();
			strResult = scanPayQueryService.request(scanPayQueryReqData);
			strResult = strResult.replaceAll("<xml>", "<map>");
			strResult = strResult.replaceAll("</xml>", "</map>");
			XStream xstream = new XStream(new DomDriver());
			xstream.registerConverter(new PojoMapConverter());
			Map<String, Object> resultMap = (Map<String, Object>) xstream.fromXML(strResult);
			String trade_state = (String) resultMap.get("trade_state");
			String return_code = (String) resultMap.get("return_code");
			String time_end = (String) resultMap.get("time_end");
			

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date payTime = sdf.parse(time_end);

			//System.out.println("支付结果查询strResult:" + strResult);
			if (null != return_code && return_code.equals("SUCCESS")) {
				if (null != trade_state) {
					/*
					 * SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭
					 * REVOKED—已撤销（刷卡支付） USERPAYING--用户支付中
					 * PAYERROR--支付失败(其他原因，如银行返回失败)
					 */
					if ("SUCCESS".equals(trade_state)) // 支付成功
					{
						String cash_fee=(String)resultMap.get("cash_fee");
						returnContent = new HashMap<String, String>();
						String transaction_id = (String) resultMap.get("transaction_id");
						returnContent.put("tradeNo", transaction_id);
						returnContent.put("orderNum", out_trade_no);
						returnContent.put("tradeStatus", "TRADE_FINISHED");
						returnContent.put("tradeDate", DateFormat.dateFormatYMDHMS(payTime));
						returnContent.put("cash_fee", cash_fee);
						returnContent.put("flag", Constants.SYMBOL_SUCCESS);
					} else {
						returnContent = new HashMap<String, String>();
						returnContent.put("tradeStatus", "WAIT_BUYER_SCAN");
						returnContent.put("flag", Constants.SYMBOL_WAIT);
					}
				}
			} else {
				returnContent = new HashMap<String, String>();
				returnContent.put("flag", Constants.SYMBOL_FAILURE);
			}
		} catch (Exception e) {
		}
		return returnContent;
	}

	/*
	 * 撤销订单 out_trade_no 订单编号
	 */
	public static Map<String, String> cancelOrder(String out_trade_no) {

		String strResult = "";
		Map<String, String> returnContent = new HashMap<String, String>();
		try {
			CancelOrderReqData cancolOrderReqData = new CancelOrderReqData(out_trade_no);
			CancelOrderService cancolOrderService = new CancelOrderService();
			strResult = cancolOrderService.request(cancolOrderReqData);
			strResult = strResult.replaceAll("<xml>", "<map>");
			strResult = strResult.replaceAll("</xml>", "</map>");
			XStream xstream = new XStream(new DomDriver());
			xstream.registerConverter(new PojoMapConverter());
			Map<String, Object> resultMap = (Map<String, Object>) xstream.fromXML(strResult);
			String result_code = (String) resultMap.get("result_code");
			String return_code = (String) resultMap.get("return_code");

			// System.out.println("支付结果查询strResult:" + strResult);
			if (null != return_code && return_code.equals("SUCCESS")) {
				if (null != result_code) {
					/*
					 * SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭
					 * REVOKED—已撤销（刷卡支付） USERPAYING--用户支付中
					 * PAYERROR--支付失败(其他原因，如银行返回失败)
					 */
					if ("SUCCESS".equals(result_code)) // 支付成功
					{
						returnContent.put("code", "result_code");
						return returnContent;
					} else {
						returnContent.put("code", "result_code");
						return returnContent;
					}
				}
				return returnContent;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return returnContent;
	}

	/*
	 * 微信端申请退款 out_trade_no 订单号 total_fee 单位“分”
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean refund(String out_trade_no, BigDecimal total_fees) {
		boolean flag = false;
		String xmlStr = "";
		InputStream instream = null;
		String total_fee = total_fees.multiply(new BigDecimal(100)).intValue() + "";
		// 将Map转换成String
		String reuqestXml = MapToString(out_trade_no, total_fee);
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			// 将微信的秘钥证书转化成输入流
			instream = WeiXinpayCore.class.getResourceAsStream(Configure.getCertLocalPath());// 放退款证书的路径
			keyStore.load(instream, Configure.getMachineMchID().toCharArray());
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Configure.getMachineMchID().toCharArray())
					.build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpPost httpPost = new HttpPost(Configure.REFUND_API);// 退款接口
			StringEntity reqEntity = new StringEntity(reuqestXml);
			reqEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
				String text;
				while ((text = bufferedReader.readLine()) != null) {
					xmlStr += text;
				}
					xmlStr = xmlStr.replaceAll("<xml>", "<map>");
					xmlStr = xmlStr.replaceAll("</xml>", "</map>");
					XStream xstream = new XStream(new DomDriver());
					xstream.registerConverter(new PojoMapConverter());
					Map<String, Object> resultMap = (Map<String, Object>) xstream.fromXML(xmlStr);
					String return_code = (String) resultMap.get("return_code");
					if (return_code != null && return_code.equals("SUCCESS")) {
						flag = true;
						// return flag;
					} else {
						flag = false;
						// return flag;
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			try {
				instream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	private static String getRequestXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	private static String createSign(String charSet, SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + "zhejianghangzhouqjkj0151010luohl");
		String sign = MD5Util.MD5Encode(sb.toString(), charSet).toUpperCase();
		return sign;
	}

	private static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 16; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}

	private static String MapToString(String out_trade_no, String total_fee) {
		SortedMap<Object, Object> params = new TreeMap<Object, Object>();
		params.put("appid", Configure.getMachineAppID());
		params.put("mch_id", Configure.getMachineMchID());
		params.put("nonce_str", CreateNoncestr());
		// params.put("transaction_id", "1007550573201601152706324724");
		params.put("out_trade_no", out_trade_no);
		params.put("out_refund_no", out_trade_no);
		// System.out.println("我靠"+total_fee);
		params.put("total_fee", total_fee);
		params.put("refund_fee", total_fee);
		params.put("op_user_id", Configure.getMchid());
		// String sign = Signature.getSign(new WeiXinpayCore().toMap());
		String sign = createSign("utf-8", params);
		params.put("sign", sign);
		String reuqestXml = getRequestXml(params);
		return reuqestXml;
	}
}
