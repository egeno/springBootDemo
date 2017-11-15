package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;

public class TblOrderVo extends TblOrder {
	private Long userId;
	/*//订单菜品总份数
    private String totalNum;*/
    //分区模型ID
    private Long  areaModelId;
    //用户类型
    private Integer specialRroleNum;
    private String machineName;
	private String merchantName;
	private Long customerId;
//	//订单数量
//	private Integer orderNums;
//	//盈利
//	private  BigDecimal   profit;
	
	
	public Long getUserId() {
		return userId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getAreaModelId() {
		return areaModelId;
	}
	public void setAreaModelId(Long areaModelId) {
		this.areaModelId = areaModelId;
	}
	public Integer getSpecialRroleNum() {
		return specialRroleNum;
	}
	public void setSpecialRroleNum(Integer specialRroleNum) {
		this.specialRroleNum = specialRroleNum;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
}
