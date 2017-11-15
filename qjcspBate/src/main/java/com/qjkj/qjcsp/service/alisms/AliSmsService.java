package com.qjkj.qjcsp.service.alisms;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.service.order.common.OrderCancelApiService;

@Component
@Transactional
public class AliSmsService {
	private static final Logger logger = LoggerFactory.getLogger(AliSmsService.class);
	//红包活动
	private String luckyMoneyMessage = "SMS_8340765";
	//预定取餐提醒 （不加可以预定休闲餐和晚餐）
	private String sendTakingGoodsVerifyCode = "SMS_10617830";
	//预定取餐提醒
	private String sendReserveTakingGoodsVerifyCode = "SMS_10692485";
	//零售取餐提醒
	private String sendRetailTakingGoodsVerifyCode = "SMS_11300247";
	//设备错误告警
	private String alarmDevice = "SMS_8330725";
	//补货预警
	private String alarmEarlyWarning = "SMS_8345622";
    //菜品过期警告
//	private String alarmExpiredDishes = "SMS_8281038";
	//取餐警告
	private String alarmTakingGoods = "SMS_8290697";
	//付款之后取餐提示
//	private String tipForTakingGoods = "SMS_8281036";
	//发送验证码
	private String verifyCode_sms = "SMS_8275783";
//	//取餐提示
//	private String sendTakingGoodsVerifyCodePrompt = "SMS_8440265";
	//清货前15-20分钟提醒
	private String clearanceRemind = "SMS_11340521";
	//补货前提醒（补货员）
	private String replenishmentRemind = "SMS_9700340";
	//补货后提醒（提醒用户可以购买）
	private String sendCustomerReplenishment = "SMS_9650323";
	//发送验证码，APP手机注册
	private String sendVerifyCode = "SMS_8275783";
	//发送验证码，设备的供货员短信验证
	private String sendMachineVerifyCode = "SMS_11510607";
	//早餐提醒
	private String breakfastRemind = "SMS_10250709";
	//午餐提示
	private String lunchRemind = "SMS_10370301"; 
	//补货前提醒 
	private String ReplenishmentStartTimeRemind = "SMS_10682346";
	
	//付款之后取餐提示
//    public boolean tipForTakingGoods(String phoneNum, String address, String verifyCode, String closingTime){
//    	String SmsParam ="{'address':'"+address+"','verifyCode':'"+verifyCode+"',"+"'closingTime':'"+closingTime+"'}";
//     	AliDayu.sendSmsToUser(phoneNum, SmsParam, tipForTakingGoods);
//    	
//    	return true;
//    }
    
    //发送验证码，APP手机注册或设备的供货员短信验证
    public boolean sendVerifyCode(String phoneNum, String verifyCode, String closingTime){
    	String SmsParam ="{'verifyCode':'"+verifyCode+"',"+"'closingTime':'"+closingTime+"'}";
    	boolean flag=AliDayu.sendSmsToUser1(phoneNum, SmsParam, sendVerifyCode);    	
    	return flag;
    }
    //发送验证码，设备的供货员短信验证
    public boolean sendMachineVerifyCode(String phoneNum, String verifyCode, String closingTime){
    	String SmsParam ="{'verifyCode':'"+verifyCode+"',"+"'closingTime':'"+closingTime+"'}";
    	AliDayu.sendSmsToUser(phoneNum, SmsParam, sendMachineVerifyCode);
    	
    	return true;
    }
    
    //取餐警告
    public boolean alarmTakingGoods(List<String> phoneList, String closingTime){
    	if(phoneList == null || phoneList.isEmpty()){
    		return false;
    	}
    	String phones = "";
    	for(String phone: phoneList){
    		phones = phones + phone + ",";
    	}
    	phones = phones.subSequence(0, phones.length() - 1).toString();
    	String SmsParam ="{'closingTime':'"+closingTime+"'}";
    	AliDayu.sendSmsToUser(phones, SmsParam, alarmTakingGoods);
    	
    	return true;
    }
    
  /*  //菜品过期警告
    public boolean alarmExpiredDishes(String phoneNum, String address, int expiredNum){
    	String SmsParam ="{'address':'"+address+"'}";
    	AliDayu.sendSmsToUser(phoneNum, SmsParam, alarmExpiredDishes);
    	return true;
    }*/
    
  /*//菜品过期预警
    public boolean alarmExpiringDishes(String phoneNum, String address, String expiredNum){
    	String content = "过期菜品预警警，" + address + "有" + expiredNum + "份菜品快要过期，请尽快清理，具体过期菜品请到管理系统查询。";
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	return true;
    }*/
    
    //设备错误告警
    public boolean alarmDevice(String phoneNum, String address, String error){ 
    	logger.warn("phoneNum:"+phoneNum+"address:"+address+"error:"+error+"systemdate:"+new Date());
    	String SmsParam ="{'address':'"+address+"',"+"'error':'"+error+"'}";
    	AliDayu.sendSmsToUser(phoneNum, SmsParam, alarmDevice);
    	
    	return true;
    }
    //补货预警
    public boolean alarmEarlyWarning(String phoneNum, String dishName, String address, int num){
     	String SmsParam ="{'dishName':'"+dishName+"','address':'"+address+"',"+"'num':'"+num+"'}";
     	AliDayu.sendSmsToUser(phoneNum, SmsParam, alarmEarlyWarning);    	
    	
     	return true;
    }
    
   /* //分区预警
    public boolean areaWarning(Map<String,String> map){
    	for (Map.Entry<String, String> entry : map.entrySet()) {
    		    FirstXinXinInterface.sendSmsToUser(entry.getValue(),entry.getKey());
    		    //将短信内容
         }
    	return true;
    }*/
    
    //发送验证码，用于找回密码
    public boolean forgetPasswordSendMessage(String phoneNum, String verifyCode, String closingTime){
    	String SmsParam ="{'verifyCode':'"+verifyCode+"',"+"'closingTime':'"+closingTime+"'}";
    	boolean flag=AliDayu.sendSmsToUser1(phoneNum, SmsParam, verifyCode_sms);   	
    	return flag;
    }
    
    //红包活动
    public boolean luckyMoneyMessage(String phoneNum, String luckyMoney){
    	String SmsParam ="{'luckyMoney':'"+luckyMoney+"'}";
    	AliDayu.sendSmsToUser(phoneNum, SmsParam, luckyMoneyMessage);
    	
    	return true;
    }
    
    //预定取餐提醒
    public boolean sendReserveTakingGoodsVerifyCode(String orderNum,String phoneNum, String address, String verifyCode,Boolean flag){
    	String SmsParam ="{'orderNum':'"+orderNum+"','address':'"+address+"',"+"'verifyCode':'"+verifyCode+"'}";
    	if(flag){//加可以预定休闲餐和晚餐
    		AliDayu.sendSmsToUser(phoneNum, SmsParam, sendReserveTakingGoodsVerifyCode);
    	}else{
    		AliDayu.sendSmsToUser(phoneNum, SmsParam, sendTakingGoodsVerifyCode);
    	}    	    	
    	return true;
    }
    //零售取餐提醒
    public boolean sendRetailTakingGoodsVerifyCode(String orderNum,String phoneNum, String address, String verifyCode){
    	String SmsParam ="{'orderNum':'"+orderNum+"','address':'"+address+"',"+"'verifyCode':'"+verifyCode+"'}";
    	AliDayu.sendSmsToUser(phoneNum, SmsParam, sendRetailTakingGoodsVerifyCode);
    	
    	return true;
    }
    
//    //取餐提示
//    public boolean  sendTakingGoodsVerifyCodePrompt(String phoneNum,String verifyCode){
//    	String SmsParam ="{'verifyCode':'"+verifyCode+"'}";
//    	AliDayu.sendSmsToUser(phoneNum, SmsParam, sendTakingGoodsVerifyCodePrompt);
//    	
//    	return true;
//    }
    //清货前15-20分钟提醒
    public boolean  sendClearanceRemind(String phoneNum,String address,String verifyCode,String endTime){
    	String SmsParam ="{'address':'"+address+"','endTime':'"+endTime+"',"+"'verifyCode':'"+verifyCode+"'}";
    	AliDayu.sendSmsToUser(phoneNum, SmsParam, clearanceRemind);
    	return true;
    }
    //补货前提醒(补货员)
    public boolean  sendReplenishmentRemind(String phoneNum,String address,String endTime){
    	String SmsParam ="{'address':'"+address+"',"+"'endTime':'"+endTime+"'}";
    	String result = AliDayu.sendSmsToUser(phoneNum, SmsParam, replenishmentRemind);

    	return true;
    }
    //补货完成提醒（提醒用户可以购买）
    public boolean  sendCustomerReplenishment(String phoneNum,String areaModelName){
    	String SmsParam ="{'areaModelName':'"+areaModelName+"'}";
    	AliDayu.sendSmsToUser(phoneNum, SmsParam, sendCustomerReplenishment);
    	
    	return true;
    }
    //早餐提醒
    public boolean  sendBreakfastRemind(String phoneNum){
    	AliDayu.sendSmsToUser(phoneNum, null, breakfastRemind);
    	
    	return true;
    }
    //中餐提醒
    public boolean  sendLunchRemind(String phoneNum){
    	String result =  AliDayu.sendSmsToUser(phoneNum, null, lunchRemind);
		System.out.println(result);
		return true;
    }
  //补货开始时间前提醒
    public boolean  sendrReplenishmentStartTimeRemind(String phoneNum,String address,String reserveStartTime,String retailStartTime){
    	String SmsParam ="{'address':'"+address+"','reserveReplenishmentStartTime':'"+reserveStartTime+"',"+"'retailReplenishmentStartTime':'"+reserveStartTime+"'}";
    	AliDayu.sendSmsToUser(phoneNum, SmsParam, ReplenishmentStartTimeRemind);
    	
    	return true;
    }
    
}
