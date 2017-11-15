package com.qjkj.qjcsp.util.weixinpay.util;

import java.util.Date;


public class WeiXinPayOrderNumUtil {
	
	public static final String WEIXINPAY_ORDERNUM_UNFI_SUFFIX = "_wxp_";
	
	public static String generatePayOrderNumByOrderNum(String orderNum)
	{
		String payOrderNumNumberPart = String.valueOf((new Date()).getSeconds())+"_";
		String payOrderNumLetterPart = RandomStringGenerator.getRandomStringByLength(6);
		StringBuffer sb = new StringBuffer();
		
		sb.append(orderNum);
		sb.append(WEIXINPAY_ORDERNUM_UNFI_SUFFIX);
		sb.append(payOrderNumNumberPart);
		sb.append(payOrderNumLetterPart);
		
		return sb.toString();
	}
	
	public static void main(String args[])
	{
		String payOrderNum = generatePayOrderNumByOrderNum("151022154314445744");
		System.out.println(payOrderNum);
		System.out.println(payOrderNum.length());
		
		System.out.println(payOrderNum.substring(0, payOrderNum.indexOf(WEIXINPAY_ORDERNUM_UNFI_SUFFIX)));
	}
}
