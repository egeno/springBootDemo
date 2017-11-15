package com.qjkj.qjcsp.entity.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单设备评价bean
 * @author yehx
 * @date 2016年1月5日  上午10:36:25
 */
public class OrderDeviceEvaluate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long orderId;//订单id
	private String  orderNum;//订单号
	private Long machineId;//设备id
	private String  deviceCode;//设备硬件id
	private Long companyId;//公司id
	private Date    orderTime;//下单时间
	private Date    evaluateTime;//评价时间
	private Integer    evaluateScore;//评价分数
	private String  evaluateMemo;//评价描述
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getEvaluateTime() {
		return evaluateTime;
	}
	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	public Integer getEvaluateScore() {
		return evaluateScore;
	}
	public void setEvaluateScore(Integer evaluateScore) {
		this.evaluateScore = evaluateScore;
	}
	public String getEvaluateMemo() {
		return evaluateMemo;
	}
	public void setEvaluateMemo(String evaluateMemo) {
		this.evaluateMemo = evaluateMemo;
	}
	
}
