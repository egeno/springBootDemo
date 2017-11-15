package com.qjkj.qjcsp.util.weixin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintUtil {

    //向请求端发送返回数据  
    public void print(HttpServletRequest req, HttpServletResponse rsp, String content){  
        try{  
        	rsp.getWriter().print(content);  
        	rsp.getWriter().flush();  
        	rsp.getWriter().close();  
        }catch(Exception e){  
              
        }  
    }  
}
