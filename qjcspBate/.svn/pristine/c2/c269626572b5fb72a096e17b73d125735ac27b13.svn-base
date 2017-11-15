package com.qjkj.qjcsp.web.api.app;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.balancepay.BalancePayService;
import com.qjkj.qjcsp.util.BalancePayVerifyUtils;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 余额支付接口
 * @author yehx
 * @date 2016年3月9日  上午9:12:18
 */
@RequestMapping("/api/app")
@Controller
public class BalancePayController {
	
	private static Logger logger = LoggerFactory.getLogger(BalancePayController.class);
	@Autowired
	private BalancePayService balancePayServcie;
	
	@RequestMapping(value="/balancePay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> balancePay(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			String str = RequestData.getRequestPostJson(req);
			JSONObject json = JSONObject.fromObject(str);
			//首先判断json数据中是否存在这些字符串
			if(!json.containsKey("customerId")||!json.containsKey("realMoney")||
			   !json.containsKey("orderNum")||!json.containsKey("payPassword")){
				map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
				map.put("requestMethod", "balancePay");
				return map;
			}
			String customerIdstr=json.getString("customerId");
			String realMoneystr = json.getString("realMoney");//实际支付金额
			String orderNum=json.getString("orderNum");//用户id
			String payPassword=json.getString("payPassword");//用户id
			if (orderNum != null && (!orderNum.isEmpty()) && realMoneystr != null 
				&& (!realMoneystr.isEmpty())&& customerIdstr != null && (!customerIdstr.isEmpty())
				&& payPassword != null &&(!payPassword.isEmpty())) {
				//首先判断支付密码是否正确
				Long customerId = Long.valueOf(customerIdstr);
				int count =balancePayServcie.checkHasUser(customerId,payPassword);
				if(count==0){
					logger.error("支付密码错误："+"customerIdstr:"+customerIdstr+"-----"+"realMoneystr:"+realMoneystr+"-----"
				+"orderNum:"+orderNum+"-----"+"payPassword:"+payPassword);
					map.put("returnCode", "0");
					map.put("returnContent", "支付密码不正确");
				}else{
					//首先判断支付金额的格式是否正确
					Boolean b=BalancePayVerifyUtils.balancePayVerifyUtils(realMoneystr);
					if(b){
						//余额支付接口
						BigDecimal bd=new BigDecimal(realMoneystr);
						map=balancePayServcie.balancePay(orderNum, bd,customerId);
					}else{
						map.put("returnCode", "0");
						map.put("returnContent", "金额有误");
						map.put("requestMethod", "balancePay");
						return map;
					}
					
					
					
				}
			} else {
				map.put("returnCode", "0");
				map.put("returnContent", "请求参数错误");
			}

		} catch (Exception e) {
			logger.error("余额支付出错：" + e.getMessage());
			e.printStackTrace();
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");

		}
		map.put("requestMethod", "balancePay");
		return map;
	}
	
}
