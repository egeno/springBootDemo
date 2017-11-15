package com.qjkj.qjcsp.service.alisms;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.taobao.api.DefaultTaobaoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 	Gekko 注：短信验证码，使用同一个签名，对同一个手机号码发送短信验证码，允许每分钟1条，累计每小时7条。
 *  短信通知，使用同一签名、同一模板，对同一手机号发送短信通知，允许每天50条（自然日）
 *
 */
public class AliDayu {
	private static Logger logger = LoggerFactory.getLogger(AliDayu.class);
	private final static String SECRET = "aa2f2030b7331e47e0565c4a6b174623";
	private final static String APPKEY = "23362591";
	private final static String URL = "https://eco.taobao.com/router/rest";
	/*公司签名*/
	private final static String SmsFreeSignName = "全家科技";
	/*短信类型固定值（必传）*/
	private final static String SmsType ="normal";
	
	//发短信
	public static String sendSmsToUser(String phoneNum,String ParamString,String TemplateCode ){
		
		
		try {
			TaobaoClient client = new DefaultTaobaoClient(URL,APPKEY,SECRET);
			
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setSmsType(SmsType);
			
			//加签名
			req.setSmsFreeSignName(SmsFreeSignName);
			
			//短信模板变量json样式的
			req.setSmsParamString(ParamString);
			
			//手机号码
			req.setRecNum(phoneNum);
			
			//短信模板ID
			req.setSmsTemplateCode(TemplateCode);
			
			//发送
			AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
			BizResult result =  response.getResult();
			if(result == null){
				String subCode = response.getSubCode() ;
				logger.warn("发送失败 phoneNum :" +phoneNum + " errorcode :" +response.getErrorCode() + " Template :" +TemplateCode );
				if(subCode!=null && subCode.equals("isv.BUSINESS_LIMIT_CONTROL")){
					logger.warn(" phoneNum  :"+phoneNum +" 超过了业务限制 ，短信一天超过50条 ，验证码 允许每分钟1条，累计每小时7条" );
				}else{
					// 非业务员限制 重发一次
					client.execute(req);
				}
			}
			if(result != null&& result.getSuccess() == true){
				logger.info("发送成功 phoneNum :" +phoneNum + " Template :" +TemplateCode );
			}	
		} catch (ApiException e) {
			logger.error("phoneNum "+phoneNum+"发送失败 ， 异常信息如下 :" + e.getMessage());
			//return false;
		}
		
		return null;
	}

	
	
	//发短信  测试
		public static boolean sendSmsToUser1(String phoneNum,String ParamString,String TemplateCode ){
			
			
			try {
				TaobaoClient client = new DefaultTaobaoClient(URL,APPKEY,SECRET);
				
				AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
				req.setSmsType(SmsType);
				
				//加签名
				req.setSmsFreeSignName(SmsFreeSignName);
				
				//短信模板变量json样式的
				req.setSmsParamString(ParamString);
				
				//手机号码
				req.setRecNum(phoneNum);
				
				//短信模板ID
				req.setSmsTemplateCode(TemplateCode);
				
				//发送
				AlibabaAliqinFcSmsNumSendResponse response = client.execute(req);
				BizResult result =  response.getResult();
				if(result == null){
					String subCode = response.getSubCode() ;
					logger.warn("发送失败 phoneNum :" +phoneNum + " errorcode :" +response.getErrorCode() + " Template :" +TemplateCode );
					if(subCode!=null && subCode.equals("isv.BUSINESS_LIMIT_CONTROL")){
						logger.warn(" phoneNum  :"+phoneNum +" 超过了业务限制 ，短信一天超过50条 ，验证码 允许每分钟1条，累计每小时7条" );
					}else{
						// 非业务员限制 重发一次
						client.execute(req);
					}
				}
				if(result != null&& result.getSuccess() == true){
					logger.info("发送成功 phoneNum :" +phoneNum + " Template :" +TemplateCode );
					return true;
				}else{
					return false;
				}	
			} catch (ApiException e) {
				logger.error("phoneNum "+phoneNum+"发送失败 ， 异常信息如下 :" + e.getMessage());
				return false;
			}
			
			//return null;
		}
	
	
	
	
}
