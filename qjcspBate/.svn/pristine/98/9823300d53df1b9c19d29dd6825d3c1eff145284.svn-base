package com.qjkj.qjcsp.service.recharge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.mapper.TblRechargeMapper;

import net.sf.json.JSONObject;
/**
 * 充值订单状态查询接口(APP)
 * carpeYe 2016-03-09
 */
@Service
public class RechargeOrderStatusService {

	@Autowired
	private TblRechargeMapper tblRechargeMapper;

	public Map<String, Object> getRechargeOrderStatus(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		// 用户Id
		String customerId = res.getString("customerId");
		if (customerId != null && !"".equals(customerId)) {
			List<Map<String, Object>> tblRecharges = tblRechargeMapper
					.findTblRechargeByCustomerId(Long.valueOf(customerId));
			returnContent.put("returnCode", "1");
			returnContent.put("returnContent", tblRecharges);
		}else{
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "请求参数异常");
		}
		return returnContent;
	}
}
