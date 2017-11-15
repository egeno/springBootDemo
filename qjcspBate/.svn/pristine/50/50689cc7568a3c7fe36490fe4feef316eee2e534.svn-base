<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.qjkj.qjcsp.util.weixinpay.util.RequestHandler" %>
<%@ page import="com.qjkj.qjcsp.util.weixinpay.util.ResponseHandler" %>   
<%@ page import="com.qjkj.qjcsp.util.weixinpay.client.ClientResponseHandler" %>    
<%@ page import="com.qjkj.qjcsp.util.weixinpay.client.TenpayHttpClient" %>
<%@ page import="com.qjkj.qjcsp.service.order.common.OrderBackCallService"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="com.qjkj.qjcsp.util.weixinpay.util.WeiXinPayOrderNumUtil"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

//System.out.println("dfdfddf");

//---------------------------------------------------------
//财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
//---------------------------------------------------------
//商户号
String partner = "1276235701";
				  
//支付方式  
int PAY_MODE_WEIXIN=2;
//密钥
String key = "zhejianghangzhouqjkj0151010luohl";

//创建支付应答对象
ResponseHandler resHandler = new ResponseHandler(request, response);
resHandler.setKey(key);

//判断签名
//System.out.println("叶建辉完成不可退订单1111111111111111111111微信"+resHandler.isTenpaySign());
if(resHandler.isTenpaySign()) {

			System.out.println("获取微信POST过来反馈信息");
			ServletContext context = request.getSession().getServletContext();
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
			OrderBackCallService orderBackCallService=(OrderBackCallService) WebApplicationContextUtils.getWebApplicationContext(application).getBean("orderBackCallService");
// 			System.out.println("out_trade_no:" + resHandler.getParameter("out_trade_no")+
// 					" trade_no:" + resHandler.getParameter("trade_no"));
			
			//获取加入微信商户订单号生成规则后的去生成规则订单号
			String out_trade_no = resHandler.getParameter("out_trade_no");
			if(null != out_trade_no && out_trade_no.indexOf(WeiXinPayOrderNumUtil.WEIXINPAY_ORDERNUM_UNFI_SUFFIX)>0)
			{
				out_trade_no = out_trade_no.substring(0, out_trade_no.indexOf(WeiXinPayOrderNumUtil.WEIXINPAY_ORDERNUM_UNFI_SUFFIX));
			}
			
			//处理业务开始
			//------------------------------
			orderBackCallService.notify(out_trade_no, resHandler.getParameter("transaction_id")
					,resHandler.getParameter("result_code") , resHandler.getParameter("time_end"),PAY_MODE_WEIXIN);
			//处理数据库逻辑
			//注意交易单不要重复处理
			//注意判断返回金额
			
			//------------------------------
			//处理业务完毕
			//------------------------------
			out.println("SUCCESS");
		}
		else{
				//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
				//System.out.println("查询验证签名失败或业务错误");
				//System.out.println("retcode:" + resHandler.getParameter("retcode")+
						//" retmsg:" + resHandler.getParameter("retmsg"));
				out.println("FAIL");
		}
	


%>

