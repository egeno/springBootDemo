package com.qjkj.qjcsp.service.weixin;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblWeixinAssist;
import com.qjkj.qjcsp.entity.weixin.res.ResArticle;
import com.qjkj.qjcsp.entity.weixin.res.ResNewsMessage;
import com.qjkj.qjcsp.entity.weixin.res.ResTextMessage;
import com.qjkj.qjcsp.mapper.TblWeixinAssistMapper;
import com.qjkj.qjcsp.util.weixin.Constant;
import com.qjkj.qjcsp.util.weixin.MessageUtil;
import com.qjkj.qjcsp.util.weixin.PrintUtil;
import com.qjkj.qjcsp.util.weixin.SignUtil;

@Service
public class WeiXinWapService {
	
	private static Logger logger = LoggerFactory.getLogger(WeiXinWapService.class);
	
	@Autowired
	private WeiXinValidateInfoService weiXinValidateInfoService;
	@Autowired
	private TblWeixinAssistMapper tblWeixinAssistMapper;
	
	/**
	 * 服务器配置
	 * @param req
	 * @param rsp
	 */
	public void valid(HttpServletRequest req, HttpServletResponse rsp) {
		String echostr = req.getParameter("echostr");
		PrintUtil printUtil = new PrintUtil();
		if (null == echostr || echostr.isEmpty()) {
//			//删除菜单
//			deleteMenu();
//			createMenu();
			String respMessage = processRequest(req);
			printUtil.print(req, rsp, respMessage);
		} else {
			SignUtil signUtil = new SignUtil();
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
			if (signUtil.checkSignature(req, rsp)) {
				printUtil.print(req, rsp, echostr);
			} else {
				printUtil.print(req, rsp, "error");
			}
		}
	}
	  /**
     * 核心业务处理
     * @param request
     * @return
     */
    public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");

			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			ResTextMessage textMessage = new ResTextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().toString());
			textMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

//			String respContent = "";
//			List<ResArticle> articleList = new ArrayList<ResArticle>();

			// 文本消息
			if (msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");

//				
				if(!"".equals(content)){
					respMessage =sendActivityNews(fromUserName, toUserName,content);
				}else{
					textMessage.setContent("感谢您关注友吃友喝订餐,这里会给您提供最新的订餐资讯和公告！");
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				// 多图文消息
/*				else if ("3".equals(content)) {

					Article article1 = new Article();
					article1.setTitle("多图文消息No1");
					article1.setDescription("第一条");
					article1.setPicUrl("http://img2.imgtn.bdimg.com/it/u=2827792060,1787198666&fm=21&gp=0.jpg");
					article1.setUrl("http://www.baidu.com");

					Article article2 = new Article();
					article2.setTitle("多图文消息No2");
					article2.setDescription("第二条");
					article2.setPicUrl("http://img1.imgtn.bdimg.com/it/u=2727811638,1369925569&fm=21&gp=0.jpg");
					article2.setUrl("http://www.qq.com");

					Article article3 = new Article();
					article3.setTitle("多图文消息No3");
					article3.setDescription("第三条");
					article3.setPicUrl("http://img4.imgtn.bdimg.com/it/u=4237897281,3115045811&fm=21&gp=0.jpg");
					article3.setUrl("http://www.taobao.com");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());

					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}*/

				// 事件处理开始
			} else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");

				if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) {
/*					// 关注
					respContent = "感谢您关注全家科技,这里会给您提供最新的公司资讯和公告！\n";
					StringBuffer contentMsg = new StringBuffer();
					contentMsg.append("您还可以回复下列数字，体验相应服务").append("\n\n");
					contentMsg.append("1  活动推广").append("\n");
					contentMsg.append("2  并没有东西").append("\n");
//					contentMsg.append("3  多图文测试").append("\n");
					respContent = respContent + contentMsg.toString();*/
					
					respMessage = sendActivityNews(fromUserName, toUserName,"0");

				} else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消关注,用户接受不到我们发送的消息了，可以在这里记录用户取消关注的日志信息

				} else if (eventType.equals(Constant.EVENT_TYPE_CLICK)) {

					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					// 自定义菜单点击事件
					if (eventKey.equals("cutprice")) {
//						respContent = "天气预报菜单项被点击！";
						respMessage = sendActivityNews(fromUserName, toUserName,eventKey);
					} else if (eventKey.equals("12")) {
//						respContent = "公交查询菜单项被点击！";
					}
				}
//				textMessage.setContent(respContent);
//				respMessage = MessageUtil.textMessageToXml(textMessage);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
    
    /**
     * 创建活动图文消息
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public  String sendActivityNews(String fromUserName, String toUserName,String content) {
		try {
			String respMessage = null;
			TblWeixinAssist tblWeixinAssist = null;
			tblWeixinAssist = tblWeixinAssistMapper.selectByCode(content);
			if (tblWeixinAssist == null) {
				tblWeixinAssist = tblWeixinAssistMapper.selectByCode("0");
			}
			// 创建图文消息
			if ("0".equals(tblWeixinAssist.getType())) {
				ResNewsMessage newsMessage = new ResNewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().toString());
				newsMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);

				List<ResArticle> articleList = new ArrayList<ResArticle>();
				ResArticle article = new ResArticle();
				String redirectUri="";
				if(!"".equals(tblWeixinAssist.getUrl())){
					if (tblWeixinAssist.getUrl().indexOf("http://") != -1
							|| tblWeixinAssist.getUrl().indexOf("https://") != -1) {
						redirectUri = tblWeixinAssist.getUrl();
					} else {
						redirectUri = Constant.DOMAIN + tblWeixinAssist.getUrl();
					}
				}else{
					redirectUri= Constant.DOMAIN + "views/weixin/view/index.html";
				}
				String enCodeUrl = java.net.URLEncoder.encode(redirectUri, "utf-8");// snsapi_userinfo、snsapi_login
				String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constant.APPID
						+ "&redirect_uri=" + enCodeUrl
						+ "&response_type=code&scope=snsapi_userinfo&state=123&connect_redirect=1#wechat_redirect";

				article.setTitle(tblWeixinAssist.getTitle());
				article.setDescription(tblWeixinAssist.getContent());
				article.setPicUrl(Constant.DOMAIN + tblWeixinAssist.getPicurl());
				article.setUrl(url);
				// article.setTitle("微信版测试");
				// article.setDescription("(/= _ =)/~┴┴");
				// article.setPicUrl("http://p3.wmpic.me/article/2014/11/24/1416808040_KjneAqTm.jpg");
				// article.setPicUrl(Constant.DOMAIN+"uploadImage/activity/oneYuan.jpg");
				// article.setUrl(url);
				articleList.add(article);

				// 设置图文消息个数
				newsMessage.setArticleCount(articleList.size());
				// 设置图文消息包含的图文集合
				newsMessage.setArticles(articleList);
				// 将图文消息对象转换成xml字符串
				respMessage = MessageUtil.newsMessageToXml(newsMessage);
			} else if ("1".equals(tblWeixinAssist.getType())) {
				// 创建文本消息
				ResTextMessage textMessage = new ResTextMessage();
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(new Date().toString());
				textMessage.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
				textMessage.setFuncFlag(0);
				textMessage.setContent(new StringBuffer(tblWeixinAssist.getContent()).toString());
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			return respMessage;
		} catch (Exception e) {
			logger.debug("创建活动消息异常", e);
		}
    	return null;
    }
    
	/**
	 * 创建菜单
	 * @return
	 */
	public String createMenu() {
		try{
			String user_define_menu ="{\"button\": ["
						+ "{\"name\": \"订 餐\",\"sub_button\":"
							+ "["
								+ "{\"type\":\"view\",\"name\":\"预 订\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/Nooutput.html\"},"
								+ "{\"type\":\"view\",\"name\":\"零 售\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/index.html\"}"
							+ "]"
						+ "},{\"name\": \"订 单\",\"sub_button\":"
							+ "["
								+ "{\"type\":\"view\",\"name\":\"所有订单\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/Ordertab.html\"},"
								+ "{\"type\":\"view\",\"name\":\"待评价\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/Ordertab.html?pick_index=3\"},"
								+ "{\"type\":\"view\",\"name\":\"退款/售后\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/refundorsh.html\"}"
							+ "]"
						+ "},{\"name\": \"加 盟\",\"sub_button\":"
							+ "["
								+ "{\"type\":\"view\",\"name\":\"设备众包\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/index.html\"},"
								+ "{\"type\":\"view\",\"name\":\"设备租赁\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/index.html\"},"
								+ "{\"type\":\"view\",\"name\":\"设备合作\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/index.html\"},"
								+ "{\"type\":\"view\",\"name\":\"区域代理\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/index.html\"},"
								+ "{\"type\":\"view\",\"name\":\"公司柜申请\",\"url\":\""+ Constant.DOMAIN+"views/weixin/view/apply.html\"}"
							+ "]"
						+ "}"
					+ "]}"; 
					//"{\"button\":[{\"type\":\"click\",\"name\":\"活动推广\",\"key\":\"activity\",\"sub_button\":[{\"type\":\"click\",\"name\":\"呼朋唤友来砍价\",\"key\":\"cutprice\"}]}]}";
			// 此处改为自己想要的结构体，替换即可
			String access_token = weiXinValidateInfoService.getAccessToken();

			String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
			
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(user_define_menu.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println("创建菜单返回信息"+message);
		}catch (Exception e) {
			logger.debug("创建菜单异常", e);
		}
		return "创建菜单成功";
	}
	
	/**
	 * 删除菜单
	 * @return
	 */
	public String deleteMenu()
	   {
	       String access_token= weiXinValidateInfoService.getAccessToken();
	       String action = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+access_token;
	       try {
	          URL url = new URL(action);
	          HttpURLConnection http =   (HttpURLConnection) url.openConnection();   

	          http.setRequestMethod("GET");       
	          http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");   
	          http.setDoOutput(true);       
	          http.setDoInput(true);
	          System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
	          System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒

	          http.connect();
	          OutputStream os= http.getOutputStream();   
	          os.flush();
	          os.close();

	          InputStream is =http.getInputStream();
	          int size =is.available();
	          byte[] jsonBytes =new byte[size];
	          is.read(jsonBytes);
	          String message=new String(jsonBytes,"UTF-8");
	          return "删除菜单返回信息"+message;
	          } catch (Exception e) {
	              e.printStackTrace();
	          } 
	       return "删除菜单失败";  
	   }

	/**
	 * 首页
	 * @param req
	 * @param rsp
	 */
	public void index(HttpServletRequest req, HttpServletResponse rsp){
		try {
/*			Map<String, String> returnMap = weiXinValidateInfoService.getOpenIdAndNickName(req, rsp);
			String openId = returnMap.get("openId");
			此处需添加绑定手机号，用于同步APP。
			String url = Constant.DOMAIN+"/views/weixin/view/index.jsp?openId="+openId;*/
			String url = Constant.DOMAIN+"/views/weixin/view/index.jsp";
			rsp.sendRedirect(url);
		} catch (Exception e) {
			logger.debug("页面跳转控制异常", e);
		}
	}
	
	/**
	 * 支付页
	 * @param req
	 * @param rsp
	 * @return
	 */
	public String pay(HttpServletRequest req, HttpServletResponse rsp){
		String openid = weiXinValidateInfoService.getOpenIdAndNickName(req, rsp).get("openId");
		req.setAttribute("openid", openid);
		
		return "weixin/view/pay";
	}
}
