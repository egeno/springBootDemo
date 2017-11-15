package com.qjkj.qjcsp.service.wechatapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.TblCustomerMapper;

@Transactional
@Service
public class WeChatInterceptorService {
	@Autowired
	private TblCustomerMapper customerMapper;

	/**
	 * 按照openid查询customer表，进行接口拦截
	 * 
	 * @param openId
	 */
	public Map<String, Object> weChatInterceptor(String openId) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		int count = customerMapper.findCustomerByCustomerWeiXin(openId);
		if (count == 0) {
			returnContent.put("returnCode", "5");
			returnContent.put("returnContent", "请注册后再进行操作");
		}
		return returnContent;
	}
}
