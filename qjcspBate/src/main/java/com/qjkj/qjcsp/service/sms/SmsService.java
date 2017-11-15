package com.qjkj.qjcsp.service.sms;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SmsService {
 
	//付款之后取餐提示
    public boolean tipForTakingGoods(String phoneNum, String address, String verifyCode, String closingTime){
    	String content = "尊敬的客户，您已经购买成功，为了保证菜品质量，请在" + closingTime + "之前，到" + address + "取餐，您的取货验证码是 " + verifyCode + ".";
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	return true;
    }
    
    //发送验证码，APP手机注册或设备的供货员短信验证
    public boolean sendVerifyCode(String phoneNum, String verifyCode, String closingTime){
    	String content = "尊敬的客户，您的手机验证码是"+verifyCode+"，有效时间截止到" + closingTime;
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	return true;
    }
    
    //取餐告警
    public boolean alarmTakingGoods(List<String> phoneList, String closingTime){
    	if(phoneList == null || phoneList.isEmpty()){
    		return false;
    	}
    	String phones = "";
    	for(String phone: phoneList){
    		phones = phones + phone + ",";
    	}
    	phones = phones.subSequence(0, phones.length() - 1).toString();
    	String content = "尊敬的客户，您取餐的截止时间是" + closingTime + "，超过这个时间菜品质量将无法保证，请尽快完成取餐；过期的菜品将会被清理，从而导致无法取餐。";
    	FirstXinXinInterface.sendSmsToUser(content, phones);
    	return true;
    }
    
    //菜品过期告警
    public boolean alarmExpiredDishes(String phoneNum, String address, int expiredNum){
    	String content = "过期菜品告警，" + address + "，请尽快清理，具体过期菜品请到管理系统查询。";
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	return true;
    }
    
  //菜品过期预警
    public boolean alarmExpiringDishes(String phoneNum, String address, String expiredNum){
    	String content = "过期菜品预警警，" + address + "有" + expiredNum + "份菜品快要过期，请尽快清理，具体过期菜品请到管理系统查询。";
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	return true;
    }
    
    //设备错误告警
    public boolean alarmDevice(String phoneNum, String address, String error){
    	
    	String content = "设备告警，"+ error +"，"+ address +"，请尽快处理。";
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	return true;
    }
    //补货预警
    public boolean alarmEarlyWarning(String phoneNum, String dishName, String address, int num){
    	String content = "补货预警，"+ address + "," + dishName +"还剩" + num + "份，请尽快处理。";
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	return true;
    }
    
    //分区预警
    public boolean areaWarning(Map<String,String> map){
    	for (Map.Entry<String, String> entry : map.entrySet()) {
    		    FirstXinXinInterface.sendSmsToUser(entry.getValue(),entry.getKey());
    		    //将短信内容
         }
    	return true;
    }
    
    //发送验证码，用于找回密码
    public boolean forgetPasswordSendMessage(String phoneNum, String verifyCode, String closingTime){
    	String content = "尊敬的客户，您的手机验证码是"+verifyCode+"，有效时间截止到" + closingTime;
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	return true;
    }
    
    //红包活动
    public boolean luckyMoneyMessage(String phoneNum, String luckyMoney){
    	String content = "恭喜您，通过活动抽奖获得一个"+luckyMoney+"元现金红包，红包已发送到您的账户中，请您查收。谢谢！";
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	
    	return true;
    }
    
    //取餐验证码
    public boolean sendTakingGoodsVerifyCode(String phoneNum, String address, String verifyCode){
    	String content = "尊敬的客户，您已经购买成功，请在对应取餐截止时间之前到"+address+"取餐。取餐验证码如下："+verifyCode+ "。";
    	FirstXinXinInterface.sendSmsToUser(content, phoneNum);
    	return true;
    }

}
