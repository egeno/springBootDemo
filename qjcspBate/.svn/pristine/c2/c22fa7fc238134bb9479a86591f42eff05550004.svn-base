package com.qjkj.qjcsp.service.sms;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class FirstXinXinInterface {

	private final static String PWD = "C32D3B568F13811634ED1ADB0790";
	private final static String SIGN = "全家科技";
	private final static String URL = "http://sms.1xinxi.cn/asmx/smsservice.aspx?";
	private final static String USER_NAME = "13858127029";
	
	/**
	 * 转换返回值类型为UTF-8格式.
	 * @param is
	 * @return
	 */
	private static String convertStreamToString(InputStream is) {    
        StringBuilder sb1 = new StringBuilder();    
        byte[] bytes = new byte[4096];  
        int size = 0;  
        
        try {    
        	while ((size = is.read(bytes)) > 0) {  
                String str = new String(bytes, 0, size, "UTF-8");  
                sb1.append(str);  
            }  
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            try {    
                is.close();    
            } catch (IOException e) {    
               e.printStackTrace();    
            }    
        }    
        return sb1.toString();    
    }
	
	//立即发送
	public static String sendSmsToUser(String content, String phoneNum){
		return sendSmsToUserDelay(content,phoneNum,"");
	}
	
	//定时发送
    public static String sendSmsToUserDelay(String content, String phoneNum, String sendTime){
    			
 
			// 创建StringBuffer对象用来操作字符串
			StringBuffer sb = new StringBuffer(URL);

			// 向StringBuffer追加用户名
			sb.append("name=" + USER_NAME);

			// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
			sb.append("&pwd=" + PWD);

			// 向StringBuffer追加手机号码
			sb.append("&mobile=" + phoneNum);

			try {
				// 向StringBuffer追加消息内容转URL标准码
				sb.append("&content="+URLEncoder.encode(content,"UTF-8"));
				
				//追加发送时间，可为空，为空为及时发送
				sb.append("&stime=" + sendTime);
				
				//加签名
			    sb.append("&sign="+URLEncoder.encode(SIGN,"UTF-8"));
				
				//type为固定值pt  extno为扩展码，必须为数字 可为空
				sb.append("&type=pt&extno=");
				// 创建url对象
				//String temp = new String(sb.toString().getBytes("GBK"),"UTF-8");
				System.out.println("sb:"+sb.toString());
				URL url;
				url = new URL(sb.toString());
				// 打开url连接
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				// 设置url请求方式 ‘get’ 或者 ‘post’
				connection.setRequestMethod("POST");

				// 发送
				InputStream is;
				is = url.openStream(); 

				//转换返回值
				String returnStr = convertStreamToString(is);
				// 返回结果为‘0，20140009090990,1，提交成功’ 发送成功   具体见说明文档
				return returnStr;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
	}
}
