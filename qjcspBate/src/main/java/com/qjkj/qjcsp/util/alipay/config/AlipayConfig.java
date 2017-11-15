package com.qjkj.qjcsp.util.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	// 异步通知url
//	public static String notify_url = "http://localhost:8080/machine-project/app/pay/callback/alipay";
	//public static String notify_url = "http://123.57.250.243/machine-project/app/pay/callback/alipay";
	// 卖家id
	public static String seller_id = "bjjzhc@yeah.net"; // qxd23746
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088811663673375";
	// 安全校验码
	public static String key = "c70rbwc6kyvx2z0u3z72kh3s729a7uty";
	
	// 商户私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMUhEeElTjOGi4CclZRsxBqo7D/t+ewvRco0Eok+L7dYEX/OAq9FT0WGdLTFGCpgXCvRzVarlle+o6x5YucS8rFdMFTh42bL1ppgAbIFd03I6jQnT4qD7jzR4r/27iJaTgASmyzPirrTjK/pR2/wyjqlb0erLO6B3hkLfY2ZBzZXAgMBAAECgYEAvato+ZkcWiU9enyuy0WvlfssfQJrcp21Z62cnCKSZLU++RptqNhmQn3xMrFk4/kdFihiit7D5B8QenW8/JpVmTHlJdkdbP8kPDGEsPX5ieVHTPs6eIHMZyokhHiYND5Q7xpABWZxlEhysmWIol08YrVzRuafxN4MQ93/00trUxkCQQD2mSZyG//J0YGmQ3p2DpB03iQ5z7VlC5UPVIoamVfP+IijUgwASnwq+8ToAI9V/+BNfN9WTZU8+i9wSU+RvfntAkEAzKUXZFyRWoLGaOA/oN9ZAswTCRBNXxNWNUufcJrxAlRGewzuOv+mbFrg3NTtQW09vickppPg9QVbj2x5QZQY0wJAMaU/kjWE15KwMVLgcqgX+Q9H58OjV0ZvqfhxHU3F2f7FM2wvMlfMi7LIP5TtRnkPJUqAhBV2eCI+LrLzq/m9OQJBAIbJUc/jfEUOD1pyINZ3ZOwHt53no940ITq3FUuYcZbkJQvU52/qhBWoAaTRhXbrWZhNtcEVczxOjBC6FF9SCMsCQGsWQbS3PjY3pbTqlA2EDl/g3/JtkXhdjVG1DfBvp2qNIh0xeZnPXZO55A1mkrHv05gLsIwKjfP6ceI3OsBgWbw=";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\alipay-log.txt";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";
	
	//public static String notify_url="http://218.108.50.106:8022/qjcsp/notify_url.jsp";

}
