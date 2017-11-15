package com.qjkj.qjcsp.entity.enums;

/**
 * 订单状态
 * 
 */
public enum OrderStatus {

	NO_PAY("0", "未支付"),
	
	NO_TAKED("1","已支付未取货"),
	CANCEL("2","已取消"),
	TAKED("3","已取货"),
	TIMEOUT("4","取货超时"),
	REFOUND("5","待退款"),
	CLOSE("6","交易关闭"),
	CLEAR("7","已完成"),
	OPERATION_NO_PASS("8","运维审核不通过"),
	FINANCE_PASS("9","财务确认通过"),
	TROUBLE("10","设备故障");
	
	
	
	
	
	public String value;
	
	public String show;
	
	private OrderStatus(String value, String show) {
		this.value = value;
		this.show = show;
	}
}
