package com.qjkj.qjcsp.service.appUser;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblCustomerDiscountActivity;
import com.qjkj.qjcsp.entity.TblCustomerOrderDis;
import com.qjkj.qjcsp.mapper.TblCustomerDiscountActivityMapper;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.mapper.TblCustomerOrderDisMapper;

import net.sf.json.JSONObject;

/**
 * 2.3.27. 完善用户资料后首单优惠
 * 
 * @author carpeYe 2016-02-23
 *
 */
@Service
public class FirstOrderDiscountService {

	@Autowired
	private TblCustomerDiscountActivityMapper tblCustomerDiscountActivityMapper;
	@Autowired
	private TblCustomerOrderDisMapper TblCustomerOrderDisMapper;

	@Autowired
	private TblCustomerMapper tblCustomerMapper;

	public Map<String, Object> firstOrderDiscount(JSONObject json) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		Object obj = json.get("customerId");
		if (obj != null && !"".equals(obj.toString())) {
			returnContent = firstOrderAndOneOrder(obj.toString());
		} else {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "请求参数异常");
		}
		return returnContent;
	}

//	public Map<String, Object> firstOrderAndOneOrder(String id) {
//		Map<String, Object> returnContent = new HashMap<String, Object>();
//		long customerId = Long.valueOf(id);
//		// 完善资料首单活动的金额
//		BigDecimal discountMoney = tblCustomerDiscountActivityMapper.findValidActivity();
//		// 完善资料减免搞活动金额
//		BigDecimal money = TblCustomerOrderDisMapper.findDiscountMoney();
//
//		// Integer discountType=null;
//		// tblCustomerDiscountActivity 为 null 该时间段没有有效的活动
//		Map<String, Object> returnData = new HashMap<String, Object>();
//		/*
//		 * discountSymbol： 1为活动已开始，用户资料未完善 2为活动已开始，用户已完善资料但未参加该活动
//		 * 3为用户已完善资料并已参加该活动；
//		 * 
//		 */
//		Integer symbol = tblCustomerMapper.findActivitySymbolByCustomerId(customerId);
//		if (symbol != null) {
//			// 首单活动开始&&用户资料完善和未参加首单活动
//			if (symbol == 2 && discountMoney != null) {
//				returnData.put("discountSymbol", "2");
//				returnData.put("deductibleAmount", discountMoney.toString());
//				returnData.put("discountType", "0");
//				// 减免优惠活动开始
//			} else if (money != null) {
//				returnData.put("discountSymbol", "2");
//				returnData.put("deductibleAmount", money + "");
//				returnData.put("discountType", "1");
//			} else if (money == null && discountMoney == null) {
//				returnData.put("discountSymbol", "0");
//				returnData.put("deductibleAmount", "0");
//			} else {
//				returnData.put("discountSymbol", symbol + "");
//				returnData.put("deductibleAmount", "0");
//			}
//			returnContent.put("returnCode", "1");
//			returnContent.put("returnContent", returnData);
//		}
//
//		return returnContent;
//	}

	public Map<String, Object> firstOrderAndOneOrder(String id) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		/*long customerId = Long.valueOf(id);
		// 判断用户是否完善资料
		Integer symbol = tblCustomerMapper.findActivitySymbolByCustomerId(customerId);
		if(symbol==null){
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "用户不存在");
			return returnContent;
		}
		// 首单活动的金额
		BigDecimal discountMoney = tblCustomerDiscountActivityMapper.findValidActivity();
		// 减免搞活动金额
		Map<String, Object> returnData = new HashMap<String, Object>();
		BigDecimal money = TblCustomerOrderDisMapper.findDiscountMoney();
		//未完善资料&&首单活动开始
		if (symbol == 1 && discountMoney != null) {
			returnData.put("discountSymbol", symbol + "");
			returnData.put("deductibleAmount", "0");
		//完善资料并且没有参加首单活动&&首单活动开始
		} else if (symbol == 2 && discountMoney != null) {
			returnData.put("discountSymbol", symbol + "");
			returnData.put("deductibleAmount", discountMoney.toString());
			returnData.put("discountType", "0");
	   //完善资料并且已经参加了首单活动&&减免活动进行中
		} else if (symbol == 3 && money != null) {
			returnData.put("discountSymbol", "2");
			returnData.put("deductibleAmount", money.toString());
			returnData.put("discountType", "1");
		} else {
			returnData.put("discountSymbol", "0");
			returnData.put("deductibleAmount", "0");
		}*/
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("discountSymbol", "0");
		returnData.put("deductibleAmount", "0");
		returnContent.put("returnCode", "1");
		returnContent.put("returnContent", returnData);
		return returnContent;
	}

}
