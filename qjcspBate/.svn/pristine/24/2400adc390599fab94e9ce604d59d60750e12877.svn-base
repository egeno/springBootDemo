package com.qjkj.qjcsp.core.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.Notification;

import com.qjkj.qjcsp.service.customer.CustomerApiService;

public class TimingPush {
	private static final Logger LOG = LoggerFactory.getLogger(TimingPush.class);
	@Autowired
	private CustomerApiService customerService;
	public static final String TITLE = "友吃有喝";
	public static final String MESSAGE = "吃饭时间到！还在犹豫点什么？美食等你来翻牌！点我点我点我 >>";
	public static final String MSG_CONTENT = "【温馨提示】餐食已全部安全着陆，小主可以来取餐啦！";
	// public static final String MSGTEXT = "【温馨提示】测试取餐，可以来取餐啦！";
	public static JPushClient jpushClient = null;
	// 友吃友喝
	private static final String appKey = "ed41372e8563f6593f77e573";
	private static final String masterSecret = "fee4ef75490bd8a5de5eead9";
	// 商户
	private static final String appKey1 = "bb48b75b4c521202f431c8f8";
	private static final String masterSecret1 = "71e342b8d42cf81e208b796a";
	// 众包商
	private static final String appKey2 = "8c63d63712bdbdef54fe24cf";
	private static final String masterSecret2 = "dee83c0417358a8f65d97c13";
    //地维
	private static final String appKey3 = "329456ee995e744550c84b93";
	private static final String masterSecret3 = "cf6f871ad691765df3f16a78";
	
	private static final long timeToLive = 3600;
	private static final long timeToLive1 = 0;
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat yyyyMMdddateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 推送提示1 ALERT 吃饭时间到！还在犹豫点什么？美食等你来翻牌！点我点我点我 >>
	 */
	public static void testSendPush1() {
		try {
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数
			// 3,设置为3表示最大连接次数如果3次连接不成功，就不推送信息
			jpushClient = new JPushClient(masterSecret, appKey);
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数 3设置为true表示推送生产环境，默认为开发环境，4
			// timeToLive 设置消息保留多长时间，设置为0表示消息不保留，默认不写是表示保留一天
			// jpushClient = new JPushClient(masterSecret, appKey,
			// true,timeToLive);
			// jpushClient = new JPushClient(masterSecret, appKey,3);
			// JPushClient jpush=new JPushClient(masterSecret, appKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PushPayload payload = buildPushObject_all_alias_alert();
		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result + "................................");
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
		} catch (APIRequestException e) {
			LOG.error(
					"Error response from JPush server. Should review and fix it. ",
					e);// 从JPush服务器错误响应。应该检查并修复它
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}

	/**
	 * 推送2 ALERT1【温馨提示】餐食已全部安全着陆，小主可以来取餐啦！	 
	 * @return
	 */
	public static void testSendPush2(List<String> customerIds) {
		List<String> alias = customerIds;
		if (alias.size() == 0) {
			return;
		}
		try {
			// jpushClient = new JPushClient(masterSecret, appKey,
			// true,timeToLive);
			jpushClient = new JPushClient(masterSecret, appKey);
			// jpushClient = new JPushClient(masterSecret, appKey,3);
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数 3设置为true表示推送生产环境，默认为开发环境，4
			// timeToLive 设置消息保留多长时间，设置为0表示消息不保留，默认不写是表示保留一天
			/*
			 * jpushClient = new JPushClient(masterSecret, appKey, true,
			 * timeToLive);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras(alias);
		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result + "................................");
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it",e);// 从JPush服务器错误响应。应该检查并修复它
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}

	/**
	 * 推送3 中餐和晚餐，休闲餐补货提醒
	 */
	// 众包商补货提醒 预订补货
	public void testSendPush3() {
		// 预订
		HashSet<Map<String, String>> listMap = customerService.getMachineInfoByModeId();
				//.getMachineInfoByZhongBao();
		List<String> alias1 = new ArrayList<String>();
		/* alias1.add("335"); */
		try {
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数
			// 3,设置为3表示最大连接次数如果3次连接不成功，就不推送信息
			// jpushClient = new JPushClient(masterSecret, appKey, 1);
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数 3设置为true表示推送生产环境，默认为开发环境，4
			// timeToLive 设置消息保留多长时间，设置为0表示消息不保留，默认不写是表示保留一天
			jpushClient = new JPushClient(masterSecret2, appKey2);
			// jpushClient = new JPushClient(masterSecret2, appKey2,
			// true,timeToLive);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 循环查询补货员的userid和需补货的地址，预订补货
		for (Map<String, String> listm : listMap) {
			String userId = listm.get("userId").toString();
			String address = listm.get("address").toString();
			String specialToleNum = listm.get("specialToleNum").toString();
			String yyyymmdd = yyyyMMdddateFormat.format(new Date());
			String retailStartTime = listm.get("retailStartTime").toString();
			retailStartTime = retailStartTime.substring(0, 5);
			String reserveStartTime = listm.get("reserveStartTime").toString();
			reserveStartTime = reserveStartTime.substring(0, 5);
			// 众包或补货员id
			if ("2".equals(specialToleNum)) {
				if (alias1.size() != 0) {
					alias1.clear();
				}
				alias1.add(userId);
				HashSet<String> h = new HashSet<String>(alias1);
				alias1.clear();
				alias1.addAll(h);
			} else if ("3".equals(specialToleNum)) {
				continue;
			} else {
				return;
			}
			String butx = "请到" + address + "补货," + "预订补货开始时间" + yyyymmdd + "日"
					+ reserveStartTime; // "+"零售补货开始时间"+yyyymmdd+"日"+retailStartTime+",
			PushPayload payload = buildPushObject_all_alias_alert_bh(alias1,
					butx);
			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result + "................................"
						+ alias1 + "众包预订");

				LOG.info("Got result - " + result);
			} catch (APIConnectionException e) {
				LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
			} catch (APIRequestException e) {
				LOG.error(
						"Error response from JPush server. Should review and fix it. ",
						e);// 从JPush服务器错误响应。应该检查并修复它
				LOG.info("HTTP Status: " + e.getStatus());
				LOG.info("Error Code: " + e.getErrorCode());
				LOG.info("Error Message: " + e.getErrorMessage());
				LOG.info("Msg ID: " + e.getMsgId());
			}
		}

		// 零售
		HashSet<Map<String, String>> listMapLs = customerService
				.getMachineInfoByRetailAll();
		List<String> alias2 = new ArrayList<String>();
		for (Map<String, String> listm : listMapLs) {
			String userId = listm.get("userId").toString();
			String address = listm.get("address").toString();
			String specialToleNum = listm.get("specialToleNum").toString();
			String yyyymmdd = yyyyMMdddateFormat.format(new Date());
			String retailStartTime = listm.get("retailStartTime").toString();
			retailStartTime = retailStartTime.substring(0, 5);
			String reserveStartTime = listm.get("reserveStartTime").toString();
			reserveStartTime = reserveStartTime.substring(0, 5);
			// 众包或补货员id
			if ("2".equals(specialToleNum)) {
				if (alias2.size() != 0) {
					alias2.clear();
				}
				alias2.add(userId);
				HashSet<String> h = new HashSet<String>(alias2);
				alias2.clear();
				alias2.addAll(h);
			} else if ("3".equals(specialToleNum)) {
				continue;
			} else {
				return;
			}
			// String butx="请补货111";
			String butx = "请到" + address + "补货," + "零售补货开始时间" + yyyymmdd + "日"
					+ retailStartTime;// ,"+"预订补货开始时间"+yyyymmdd+"日"+reserveStartTime;

			PushPayload payload = buildPushObject_all_alias_alert_bh(alias2,
					butx);

			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result + "................................"
						+ alias2 + "众包零售");

				LOG.info("Got result - " + result);
			} catch (APIConnectionException e) {
				LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
			} catch (APIRequestException e) {
				LOG.error(
						"Error response from JPush server. Should review and fix it. ",
						e);// 从JPush服务器错误响应。应该检查并修复它
				LOG.info("HTTP Status: " + e.getStatus());
				LOG.info("Error Code: " + e.getErrorCode());
				LOG.info("Error Message: " + e.getErrorMessage());
				LOG.info("Msg ID: " + e.getMsgId());
			}
		}
	}

	/**
	 * 推送4 中餐和晚餐，休闲餐补货提醒 预订补货
	 */
	// 商户补货提醒
	public void testSendPush4() {
		// 预订补货
		HashSet<Map<String, String>> listMap = customerService
				.getMachineInfoByModeId();
		List<String> alias1 = new ArrayList<String>();
		try {
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数
			// 3,设置为3表示最大连接次数如果3次连接不成功，就不推送信息
			// jpushClient = new JPushClient(masterSecret, appKey, 1);
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数 3设置为true表示推送生产环境，默认为开发环境，4
			// timeToLive 设置消息保留多长时间，设置为0表示消息不保留，默认不写是表示保留一天
			jpushClient = new JPushClient(masterSecret1, appKey1);
			// jpushClient = new JPushClient(masterSecret1, appKey1,
			// true,timeToLive);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 循环查询补货员的userid和需补货的地址，预订补货时间，零售补货时间
		for (Map<String, String> listm : listMap) {
			Map<String, String> customerMap = listm;
			String userId = customerMap.get("userId").toString();
			String specialToleNum = customerMap.get("specialToleNum")
					.toString();
			String address = customerMap.get("address").toString();
			String yyyymmdd = yyyyMMdddateFormat.format(new Date());
			String retailStartTime = customerMap.get("retailStartTime")
					.toString();
			retailStartTime = retailStartTime.substring(0, 5);
			String reserveStartTime = customerMap.get("reserveStartTime")
					.toString();
			reserveStartTime = reserveStartTime.substring(0, 5);
			// 商户id
			if ("3".equals(specialToleNum)) {
				if (alias1.size() != 0) {
					alias1.clear();
				}
				alias1.add(userId);
				HashSet<String> h = new HashSet<String>(alias1);
				alias1.clear();
				alias1.addAll(h);
				
			} else if ("2".equals(specialToleNum)) {
				continue;
			} else {
				return;
			}

			String butx = "请到" + address + "补货," + "预订补货开始时间" + yyyymmdd + "日"
					+ reserveStartTime; // ,"+"零售补货开始时间"+yyyymmdd+"日"+retailStartTime+"
			PushPayload payload = buildPushObject_all_alias_alert_bh(alias1,
					butx);
			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result + "................................"
						+ alias1 + "商户预订");

				LOG.info("Got result - " + result);
			} catch (APIConnectionException e) {
				LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
			} catch (APIRequestException e) {
				LOG.error(
						"Error response from JPush server. Should review and fix it. ",
						e);// 从JPush服务器错误响应。应该检查并修复它
				LOG.info("HTTP Status: " + e.getStatus());
				LOG.info("Error Code: " + e.getErrorCode());
				LOG.info("Error Message: " + e.getErrorMessage());
				LOG.info("Msg ID: " + e.getMsgId());
			}
		}

		// 零售补货
		HashSet<Map<String, String>> listMapLs = customerService
				.getMachineInfoByRetailAll();
		List<String> alias2 = new ArrayList<String>();
		for (Map<String, String> listm : listMapLs) {
			Map<String, String> customerMap = listm;
			String userId = customerMap.get("userId").toString();
			// String mobile=customerMap.get("mobile").toString();
			String specialToleNum = customerMap.get("specialToleNum")
					.toString();
			String address = customerMap.get("address").toString();
			String yyyymmdd = yyyyMMdddateFormat.format(new Date());
			String retailStartTime = customerMap.get("retailStartTime")
					.toString();
			retailStartTime = retailStartTime.substring(0, 5);
			String reserveStartTime = customerMap.get("reserveStartTime")
					.toString();
			reserveStartTime = reserveStartTime.substring(0, 5);
			// 商户id
			if ("3".equals(specialToleNum)) {
				if (alias2.size() != 0) {
					alias2.clear();
				}
				alias2.add(userId);
				HashSet<String> h = new HashSet<String>(alias2);
				alias2.clear();
				alias2.addAll(h);
			} else if ("2".equals(specialToleNum)) {
				continue;
			} else {
				return;
			}

			String butx = "请到" + address + "补货," + "零售补货开始时间" + yyyymmdd + "日"
					+ retailStartTime;// +","+"预订补货开始时间"+yyyymmdd+"日"+reserveStartTime;
			PushPayload payload = buildPushObject_all_alias_alert_bh(alias2,
					butx);
			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result + "................................"
						+ alias2 + "商户零售");

				LOG.info("Got result - " + result);
			} catch (APIConnectionException e) {
				LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
			} catch (APIRequestException e) {
				LOG.error(
						"Error response from JPush server. Should review and fix it. ",
						e);// 从JPush服务器错误响应。应该检查并修复它
				LOG.info("HTTP Status: " + e.getStatus());
				LOG.info("Error Code: " + e.getErrorCode());
				LOG.info("Error Message: " + e.getErrorMessage());
				LOG.info("Msg ID: " + e.getMsgId());
			}
		}

	}

	/**
	 * 推送5 商户 早餐补货提醒
	 */
	public void testSendPush5() {
		// 预订补货
		HashSet<Map<String, String>> listMap = customerService
				.getBreakfastAreaModelId();
		List<String> alias1 = new ArrayList<String>();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date = calendar.getTime();
		try {
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数
			// 3,设置为3表示最大连接次数如果3次连接不成功，就不推送信息
			// jpushClient = new JPushClient(masterSecret, appKey, 1);
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数 3设置为true表示推送生产环境，默认为开发环境，4
			// timeToLive 设置消息保留多长时间，设置为0表示消息不保留，默认不写是表示保留一天
			jpushClient = new JPushClient(masterSecret1, appKey1);
			// jpushClient = new JPushClient(masterSecret1, appKey1,
			// true,timeToLive);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 循环查询补货员的userid和需补货的地址，预订补货时间，零售补货时间
		for (Map<String, String> listm : listMap) {
			Map<String, String> customerMap = listm;
			String userId = customerMap.get("userId").toString();
			// String mobile=customerMap.get("mobile").toString();
			String address = customerMap.get("address").toString();
			String yyyymmdd = yyyyMMdddateFormat.format(date);
			String specialToleNum = customerMap.get("specialToleNum")
					.toString();
			String retailStartTime = customerMap.get("retailStartTime")
					.toString();
			retailStartTime = retailStartTime.substring(0, 5);
			String reserveStartTime = customerMap.get("reserveStartTime")
					.toString();
			reserveStartTime = reserveStartTime.substring(0, 5);
			// 商户 id
			if ("3".equals(specialToleNum)) {
				if (alias1.size() != 0) {
					alias1.clear();
				}
				alias1.add(userId);
				HashSet<String> h = new HashSet<String>(alias1);
				alias1.clear();
				alias1.addAll(h);
			} else if ("2".equals(specialToleNum)) {
				continue;
			} else {
				return;
			}
			String butx = "请到" + address + "补货," + "零售补货开始时间" + yyyymmdd + "日"
					+ retailStartTime + "," + "预订补货开始时间" + yyyymmdd + "日"
					+ reserveStartTime;
			PushPayload payload = buildPushObject_all_alias_alert_bh(alias1,
					butx);
			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result + "................................"
						+ alias1 + "商户早餐补货");

				LOG.info("Got result - " + result);
			} catch (APIConnectionException e) {
				LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
			} catch (APIRequestException e) {
				LOG.error(
						"Error response from JPush server. Should review and fix it. ",
						e);// 从JPush服务器错误响应。应该检查并修复它
				LOG.info("HTTP Status: " + e.getStatus());
				LOG.info("Error Code: " + e.getErrorCode());
				LOG.info("Error Message: " + e.getErrorMessage());
				LOG.info("Msg ID: " + e.getMsgId());
			}
		}

		// 零售补货
		HashSet<Map<String, String>> listMapLs = customerService
				.getBreakfastAreaRetal();
		List<String> alias2 = new ArrayList<String>();
		for (Map<String, String> listm : listMapLs) {
			Map<String, String> customerMap = listm;
			String userId = customerMap.get("userId").toString();
			// String mobile=customerMap.get("mobile").toString();
			String address = customerMap.get("address").toString();
			String yyyymmdd = yyyyMMdddateFormat.format(date);
			String specialToleNum = customerMap.get("specialToleNum")
					.toString();
			String retailStartTime = customerMap.get("retailStartTime")
					.toString();
			retailStartTime = retailStartTime.substring(0, 5);
			String reserveStartTime = customerMap.get("reserveStartTime")
					.toString();
			reserveStartTime = reserveStartTime.substring(0, 5);
			// 商户 id
			if ("3".equals(specialToleNum)) {
				if (alias2.size() != 0) {
					alias2.clear();
				}
				alias2.add(userId);
				HashSet<String> h = new HashSet<String>(alias2);
				alias2.clear();
				alias2.addAll(h);
			} else if ("2".equals(specialToleNum)) {
				continue;
			} else {
				return;
			}
			String butx = "请到" + address + "补货," + "零售补货开始时间" + yyyymmdd + "日"
					+ retailStartTime;// +","+"预订补货开始时间"+yyyymmdd+"日"+reserveStartTime;
			PushPayload payload = buildPushObject_all_alias_alert_bh(alias2,
					butx);
			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result + "................................"
						+ alias2 + "商户零售早餐补货");

				LOG.info("Got result - " + result);
			} catch (APIConnectionException e) {
				LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
			} catch (APIRequestException e) {
				LOG.error(
						"Error response from JPush server. Should review and fix it. ",
						e);// 从JPush服务器错误响应。应该检查并修复它
				LOG.info("HTTP Status: " + e.getStatus());
				LOG.info("Error Code: " + e.getErrorCode());
				LOG.info("Error Message: " + e.getErrorMessage());
				LOG.info("Msg ID: " + e.getMsgId());
			}
		}

	}

	/**
	 * 推送6 众包商 早餐补货提醒
	 */
	public void testSendPush6() {
		// 预订补货
		HashSet<Map<String, String>> listMap = customerService
				.getBreakfastAreaModelId();
		List<String> alias1 = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date date = calendar.getTime();

		try {
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数
			// 3,设置为3表示最大连接次数如果3次连接不成功，就不推送信息
			// jpushClient = new JPushClient(masterSecret, appKey, 1);
			// 参数说明1 masterSecret 固定参数，2 appKey 固定参数 3设置为true表示推送生产环境，默认为开发环境，4
			// timeToLive 设置消息保留多长时间，设置为0表示消息不保留，默认不写是表示保留一天
			jpushClient = new JPushClient(masterSecret2, appKey2);
			// jpushClient = new JPushClient(masterSecret2, appKey2,
			// true,timeToLive);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 循环查询补货员的userid和需补货的地址，预订补货时间，零售补货时间
		for (Map<String, String> listm : listMap) {
			Map<String, String> customerMap = listm;
			String userId = customerMap.get("userId").toString();
			// String mobile = customerMap.get("mobile").toString();
			String address = customerMap.get("address").toString();
			String yyyymmdd = yyyyMMdddateFormat.format(date);
			String specialToleNum = customerMap.get("specialToleNum")
					.toString();
			String retailStartTime = customerMap.get("retailStartTime")
					.toString();
			retailStartTime = retailStartTime.substring(0, 5);
			String reserveStartTime = customerMap.get("reserveStartTime")
					.toString();
			reserveStartTime = reserveStartTime.substring(0, 5);
			// 众包或补货员 id
			if ("2".equals(specialToleNum)) {
				if (alias1.size() != 0) {
					alias1.clear();
				}
				alias1.add(userId);
				HashSet<String> h = new HashSet<String>(alias1);
				alias1.clear();
				alias1.addAll(h);				
			} else if ("3".equals(specialToleNum)) {
				continue;
			} else {
				return;
			}
			String butx = "请到" + address + "补货," + "预订补货开始时间" + yyyymmdd + "日"
					+ reserveStartTime;// ,"+"零售补货开始时间"+yyyymmdd+"日"+retailStartTime+"
			PushPayload payload = buildPushObject_all_alias_alert_bh(alias1,
					butx);
			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result + "................................"
						+ userId + "众包商早餐补货预订");

				LOG.info("Got result - " + result);
			} catch (APIConnectionException e) {
				LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
			} catch (APIRequestException e) {
				LOG.error(
						"Error response from JPush server. Should review and fix it. ",
						e);// 从JPush服务器错误响应。应该检查并修复它
				LOG.info("HTTP Status: " + e.getStatus());
				LOG.info("Error Code: " + e.getErrorCode());
				LOG.info("Error Message: " + e.getErrorMessage());
				LOG.info("Msg ID: " + e.getMsgId());
			}
		}
		// 零售补货
		HashSet<Map<String, String>> listMapLs = customerService
				.getBreakfastAreaRetal();
		List<String> alias2 = new ArrayList<String>();
		for (Map<String, String> listm : listMapLs) {
			Map<String, String> customerMap = listm;
			String userId = customerMap.get("userId").toString();
			// String mobile = customerMap.get("mobile").toString();
			String address = customerMap.get("address").toString();
			String yyyymmdd = yyyyMMdddateFormat.format(date);
			String specialToleNum = customerMap.get("specialToleNum")
					.toString();
			String retailStartTime = customerMap.get("retailStartTime")
					.toString();
			retailStartTime = retailStartTime.substring(0, 5);
			String reserveStartTime = customerMap.get("reserveStartTime")
					.toString();
			reserveStartTime = reserveStartTime.substring(0, 5);
			// 众包或补货员 id
			if ("2".equals(specialToleNum)) {
				if (alias2.size() != 0) {
					alias2.clear();
				}
				alias2.add(userId);
				HashSet<String> h = new HashSet<String>(alias2);
				alias2.clear();
				alias2.addAll(h);

			} else if ("3".equals(specialToleNum)) {
				continue;
			} else {
				return;
			}
			String butx = "请到" + address + "补货," + "零售补货开始时间" + yyyymmdd + "日"
					+ retailStartTime;// +","+"预订补货开始时间"+yyyymmdd+"日"+reserveStartTime;
			PushPayload payload = buildPushObject_all_alias_alert_bh(alias2,
					butx);
			try {
				PushResult result = jpushClient.sendPush(payload);
				System.out.println(result + "................................"
						+ alias2 + "众包商早餐补货零售");

				LOG.info("Got result - " + result);
			} catch (APIConnectionException e) {
				LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
			} catch (APIRequestException e) {
				LOG.error(
						"Error response from JPush server. Should review and fix it. ",
						e);// 从JPush服务器错误响应。应该检查并修复它
				LOG.info("HTTP Status: " + e.getStatus());
				LOG.info("Error Code: " + e.getErrorCode());
				LOG.info("Error Message: " + e.getErrorMessage());
				LOG.info("Msg ID: " + e.getMsgId());
			}
		}
	}

	public static boolean testSendPush7(int number,String dayTime,String customerId) {
		try {
			if(number==1){
				jpushClient = new JPushClient(masterSecret3, appKey3);	//地维端
			}else if(number==2){
				jpushClient = new JPushClient(masterSecret, appKey);//客户端
			}else if(number==3){
				jpushClient = new JPushClient(masterSecret1, appKey1);//商户端
			}else if(number==4){
				jpushClient = new JPushClient(masterSecret2, appKey2);//众包端
			}			
			PushPayload payload = buildPushObject_all_alert_mess(dayTime,customerId);
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result + "..............................."
					+ "登陆");
			LOG.info("Got result - " + result);
			return true;
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);// 连接错误。应该稍后重试
			return false;
		} catch (APIRequestException e) {
			LOG.error(
					"Error response from JPush server. Should review and fix it. ",
					e);// 从JPush服务器错误响应。应该检查并修复它
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
		
	/**
	 * 推送1 推送消息：吃饭时间到！还在犹豫点什么？美食等你来翻牌！点我点我点我 >> 设置接收的平台：所有平台
	 * 
	 * @return
	 */

	public static PushPayload buildPushObject_all_alias_alert() {
       
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())// 设置接受的平台 all()
				.setAudience(Audience.all())// Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
				//.setAudience(Audience.alias(alias))
				.setNotification(Notification.alert(MESSAGE))
				.setOptions(Options.newBuilder()
						 .setTimeToLive(timeToLive).build())// 设置离线消息默认保留时间，timeToLive为0表示不保留，不写默认保留一天
						 .build();
				/*.setOptions(
						Options.newBuilder().setApnsProduction(true).build())*/ //设置推送环境		
				

	}

	/**
	 * 推送2使用 设置接收平台为：android和ios
	 * @param aliases
	 * @return
	 */
	/*
	 * public static PushPayload
	 * buildPushObject_ios_audienceMore_messageWithExtras(List<String> aliases)
	 * { return PushPayload.newBuilder() .setPlatform(Platform.android_ios())
	 * .setAudience(Audience.newBuilder()
	 * .addAudienceTarget(AudienceTarget.alias(aliases)).build())
	 * .setNotification(Notification.alert(MSGTEXT))//MSGTEXT MSG_CONTENT
	 * .setOptions(Options.newBuilder() .setApnsProduction(true) .build())
	 * .build(); }
	 */

	/*
	 * public static PushPayload
	 * buildPushObject_ios_audienceMore_messageWithExtras( List<String> aliases)
	 * { return PushPayload.newBuilder() .setPlatform(Platform.all())
	 * .setAudience(Audience.alias(aliases))//alias(aliases)
	 * .setNotification(Notification.alert(MSGTEXT)) .build();
	 * 
	 * 
	 * }
	 */
	/**
	 * 推送2使用 推送消息：【温馨提示】餐食已全部安全着陆，小主可以来取餐啦！ 设置接收平台为：所有平台
	 * 
	 * @param aliases
	 * @return
	 */
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(
			List<String> aliases) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(aliases))
				.setNotification(Notification.alert(MSG_CONTENT))
				.setOptions(
						Options.newBuilder()
						.setTimeToLive(timeToLive)
						.build())
						.build();
				/*.setOptions(
						Options.newBuilder().setApnsProduction(true).build())*/
				
	}

	/**
	 * 推送3 众包和商户补货提醒
	 * 
	 * @param aliases1
	 * @param butx
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert_bh(
			List<String> aliases1, String butx) {
		int time = 2;
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(aliases1))
				.setNotification(Notification.alert(butx))//Notification  APNS通知
				.setOptions(Options.newBuilder()
						.setTimeToLive(timeToLive).build())
						.build();
				/*.setOptions(Options.newBuilder()
						.setTimeToLive(timeToLive)//保留时间
				        .setApnsProduction(true)
				        .setBigPushDuration(time)
						.build())*/
				/*.build();*/

	}

	/**
	 * 推送4 商户补货
	 */
	/*
	 * 
	 * public static PushPayload
	 * buildPushObject_all_alias_alert_shbh(List<String> aliases1,String butx) {
	 * return PushPayload.newBuilder() .setPlatform(Platform.all())
	 * .setAudience(Audience.alias(aliases1))
	 * .setNotification(Notification.alert(butx)) .build();
	 * 
	 * }
	 */
	/**
	 * 登陆返回时间给APP
	 * @param dayTime
	 * @param customerId
	 * @return
	 */
	public static PushPayload buildPushObject_all_alert_mess(String dayTime,String customerId) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(customerId))//推给某一个客户
				.setMessage(Message.content(dayTime))//Message应用内消息
				.setOptions(
						Options.newBuilder().setTimeToLive(timeToLive1).build()) //消息保留时间  默认保留一天
				.build();

	}
	
	/*public static PushPayload buildPushObject_all_alert_messUser(String dayTime,String userId) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(userId))//推给某一个客户
				.setMessage(Message.content(dayTime))//Message应用内消息
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.setOptions(
						Options.newBuilder().setTimeToLive(timeToLive).build())  //消息保留时间  默认保留一天
				.build();

	}*/

	
	
	/*public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String dayTime,String userId) {  
        return PushPayload.newBuilder()  
                .setPlatform(Platform.android_ios())  
                .setAudience(Audience.all())   
                .setMessage(Message.newBuilder()  
                        .setMsgContent(dayTime)  
                        .build())  
                .build();  
    }  	*/
	/**
	 * 测试方法
	 */
	
  /*public static void main(String[] args) {
	System.out.println("测试推送消息开始：");
	  Date day=new Date(); SimpleDateFormat sdf = new
	  SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String startTime =
	  sdf.format(day); 
	  String id="347";
	  TimingPush.testSendPush7(3,startTime, id);
	for(int i=0;i<20;i++){
		List<String> li=new ArrayList<String>();
		li.add("17");
		TimingPush.testSendPush1(li);
	}
	 System.out.println("测试消息发送结束！");
	 }	 */
}
