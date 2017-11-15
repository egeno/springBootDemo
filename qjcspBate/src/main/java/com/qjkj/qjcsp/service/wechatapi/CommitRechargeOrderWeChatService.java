package com.qjkj.qjcsp.service.wechatapi;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblPendingOrder;
import com.qjkj.qjcsp.entity.TblRecharge;
import com.qjkj.qjcsp.entity.TblRechargeActivity;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.mapper.TblPendingOrderMapper;
import com.qjkj.qjcsp.mapper.TblRechargeActivityMapper;
import com.qjkj.qjcsp.mapper.TblRechargeMapper;

import net.sf.json.JSONObject;

/**
 * 2.3.28. 充值订单提交接口(APP)
 * 
 * @author carpeYe
 *
 */
@Service
public class CommitRechargeOrderWeChatService {

	@Autowired
	private TblRechargeMapper tblRechargeMapper;
	@Autowired
	private TblPendingOrderMapper tblPendingOrderMapper;
	@Autowired
	private TblCustomerMapper tblCustomerMapper;
	@Autowired
	private TblRechargeActivityMapper tblRechargeActivityMapper;

	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> commitRechargeOrderWX(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		// 用户Id
		String customerId = res.getString("customerId");
		// 支付方式 1:支付宝 ，2：微信
		String payMode = res.getString("payMode");
		// 充值活动Id
		String activityId = res.getString("activityId");
		TblRechargeActivity tblRechargeActivity = null;
		if (StringUtils.isNoneBlank(customerId, activityId, payMode)) {
			tblRechargeActivity = tblRechargeActivityMapper.selectValidActivityByPrimaryKey(Long.valueOf(activityId));
			if (tblCustomerMapper.selectByPrimaryKey(Long.valueOf(customerId)) == null) {
				returnContent.put("returnCode", "0");
				returnContent.put("returnContent", "用户不存在");
			} else if (!"1".equals(payMode) && !"2".equals(payMode)) {
				returnContent.put("returnCode", "0");
				returnContent.put("returnContent", "支付方式不正确");
			} else if (tblRechargeActivity == null) {
				returnContent.put("returnCode", "0");
				returnContent.put("returnContent", "你加参加的活动不存在");
			} else {
				TblCustomer tblCustomer=tblCustomerMapper.selectByPrimaryKey(Long.valueOf(customerId));
				String orderNum = getOrderNum();
				Date orderTime = new Date();
				Long time = orderTime.getTime() + 5 * 60 * 1000;
				Date lastPayTime = new Date(time);
				// 充值订单
				TblRecharge tblRecharge = new TblRecharge();
				tblRecharge.setActivityMoney(tblRechargeActivity.getActivityMoney());
				tblRecharge.setCustomerId(Long.valueOf(customerId));
				tblRecharge.setOrderNum(orderNum);
				tblRecharge.setRealMoney(tblRechargeActivity.getMoney());
				tblRecharge.setTotalMoney(tblRechargeActivity.getActivityMoney().add(tblRechargeActivity.getMoney()));
				tblRecharge.setModeNum(payMode);
				tblRecharge.setOrderStatus(OrderStatus.NO_PAY.value);
				tblRecharge.setOrderTime(orderTime);
				tblRecharge.setMobile(tblCustomer.getCustomerMobile());
				tblRechargeMapper.insertSelective(tblRecharge);
				// 待处理订单
				TblPendingOrder tblPendingOrder = new TblPendingOrder();
				tblPendingOrder.setOrderNum(orderNum);
				tblPendingOrder.setPendingTypeNum("4");
				tblPendingOrder.setOrderId(tblRechargeMapper.selectTblRechargeByOrderNum(orderNum).getRechargeId());
				tblPendingOrder.setOrderTime(orderTime);
				tblPendingOrder.setLastPayTime(lastPayTime);
				tblPendingOrderMapper.insertSelective(tblPendingOrder);
				Map<String, Object> returnData = new HashMap<String, Object>();
				returnData.put("orderNum", orderNum);
				returnData.put("money", tblRechargeActivity.getMoney());
				
				Map<String,String> customer=tblCustomerMapper.findCustomerByCustomerId(Long.valueOf(customerId));
				returnData.put("payPwdCheck", customer.get("payPwdCheck"));
				returnContent.put("returnCode", "1");
				returnContent.put("returnContent", returnData);
			}
		} else {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "请求参数异常");
		}
		return returnContent;

	}

	private String getOrderNum() {
		return new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + RandomStringUtils.randomNumeric(3);
	}
}
