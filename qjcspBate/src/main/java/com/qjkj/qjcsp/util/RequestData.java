
package  com.qjkj.qjcsp.util;

import java.io.BufferedReader;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RequestData {
	
	private static Logger logger = LoggerFactory.getLogger(RequestData.class);

	/**
	 * 获取Post请求参数，json格式字符串
	 * 
	 * @param Code
	 * @param parentCode
	 * @return
	 */
	public static String getRequestPostJson(ServletRequest request) {
		try
		{
			request.setCharacterEncoding("UTF-8");
			
		}
		catch(Exception e)
		{
			System.out.println(e);;
		}
		String json = "";
		
		try {
		    BufferedReader reader = request.getReader();
		    String line = null;
		    while ((line = reader.readLine()) != null){
		    	json =json + line;
		    }
		    json = java.net.URLDecoder.decode(json,"utf-8");  
		    logger.debug("getRequestPostJson "+ json);

		  } catch (Exception e) { 
			  logger.debug("getRequestPostJson faild");
          }
		return json;
	}
	
}
