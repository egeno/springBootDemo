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
//�Ƹ�֧ͨ��֪ͨ����̨֪ͨ��ʾ�����̻����մ��ĵ����п�������
//---------------------------------------------------------
//�̻���
String partner = "1276235701";
				  
//֧����ʽ  
int PAY_MODE_WEIXIN=2;
//��Կ
String key = "zhejianghangzhouqjkj0151010luohl";

//����֧��Ӧ�����
ResponseHandler resHandler = new ResponseHandler(request, response);
resHandler.setKey(key);

//�ж�ǩ��
//System.out.println("Ҷ������ɲ����˶���1111111111111111111111΢��"+resHandler.isTenpaySign());
if(resHandler.isTenpaySign()) {

			System.out.println("��ȡ΢��POST����������Ϣ");
			ServletContext context = request.getSession().getServletContext();
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
			OrderBackCallService orderBackCallService=(OrderBackCallService) WebApplicationContextUtils.getWebApplicationContext(application).getBean("orderBackCallService");
// 			System.out.println("out_trade_no:" + resHandler.getParameter("out_trade_no")+
// 					" trade_no:" + resHandler.getParameter("trade_no"));
			
			//��ȡ����΢���̻����������ɹ�����ȥ���ɹ��򶩵���
			String out_trade_no = resHandler.getParameter("out_trade_no");
			if(null != out_trade_no && out_trade_no.indexOf(WeiXinPayOrderNumUtil.WEIXINPAY_ORDERNUM_UNFI_SUFFIX)>0)
			{
				out_trade_no = out_trade_no.substring(0, out_trade_no.indexOf(WeiXinPayOrderNumUtil.WEIXINPAY_ORDERNUM_UNFI_SUFFIX));
			}
			
			//����ҵ��ʼ
			//------------------------------
			orderBackCallService.notify(out_trade_no, resHandler.getParameter("transaction_id")
					,resHandler.getParameter("result_code") , resHandler.getParameter("time_end"),PAY_MODE_WEIXIN);
			//�������ݿ��߼�
			//ע�⽻�׵���Ҫ�ظ�����
			//ע���жϷ��ؽ��
			
			//------------------------------
			//����ҵ�����
			//------------------------------
			out.println("SUCCESS");
		}
		else{
				//����ʱ�����ؽ��δǩ������¼retcode��retmsg��ʧ�����顣
				//System.out.println("��ѯ��֤ǩ��ʧ�ܻ�ҵ�����");
				//System.out.println("retcode:" + resHandler.getParameter("retcode")+
						//" retmsg:" + resHandler.getParameter("retmsg"));
				out.println("FAIL");
		}
	


%>

