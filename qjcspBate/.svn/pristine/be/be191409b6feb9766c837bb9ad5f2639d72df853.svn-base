package com.qjkj.qjcsp.service.wechatapi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.util.DateUtils;

import net.sf.json.JSONObject;

/**
 * @author carpeYe 2016-01-21 2.3.3.获取当前用户的基本信息
 */
@Service
public class CustomerBaseInfoWeChatService {

	@Autowired
	private TblCustomerMapper tblCustomerMapper;
	@Autowired
	private WeChatInterceptorService weChatInterceptorService;

	public Map<String, Object> getCustomerBaseInfoWX(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		// 获取customerId
		String openId = (String) res.get("openId");
		// 当app传入的参数customerId不为空或者不为空字符串时
		if (StringUtils.isNoneBlank(openId)) {
			Map<String, Object> map = weChatInterceptorService.weChatInterceptor(openId);
			if (!map.isEmpty()) {
				return map;
			}
			// 调用根据customerId调用tblCustomerMapper方法查询客户表中余额，积分，优惠券
			Map<String, Object> customer = tblCustomerMapper.findCustomerInfoByCustomerWeiXin(openId);
			if (customer != null) {
				String systemTime = DateUtils.formatString(new Date());
				customer.put("systemTime", systemTime);
			}
			// returnType "1" 为成功 ,"0" 失败
			returnContent.put("returnCode", "1");
			returnContent.put("returnContent", customer);
		} else {
			// returnType "1" 为成功 ,"0" 失败
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "微信id参数异常");
		}
		return returnContent;
	}
}
