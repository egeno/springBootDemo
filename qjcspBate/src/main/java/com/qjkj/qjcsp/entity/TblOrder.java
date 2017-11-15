package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TblOrder {
	
    private Long orderId;

    private String orderNum;

    private Long machineId;

    private Long companyId;

    private String mobile;

    private Long customerId;

    private String modeNum;

    private Date orderTime;

    private BigDecimal totalMoney;

    private BigDecimal realMoney;

    private String orderStatus;

    private Long discountRuleId;

    private BigDecimal discountMoney;

    private Date lastPayTime;

    private String payMode;

    private String payOrderNum;

    private String payModeOrderNum;

    private String accountNumber;

    private Date payTime;

    private String payStatus;

    private String payNumber;

    private Date compeleteTime;

    private String faildMsg;

    private String customerDelete;

    private String isdel;

    private String orderType;
    
    private String totalNum;
	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getModeNum() {
        return modeNum;
    }

    public void setModeNum(String modeNum) {
        this.modeNum = modeNum == null ? null : modeNum.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(BigDecimal realMoney) {
        this.realMoney = realMoney;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Long getDiscountRuleId() {
        return discountRuleId;
    }

    public void setDiscountRuleId(Long discountRuleId) {
        this.discountRuleId = discountRuleId;
    }

    public BigDecimal getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(BigDecimal discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Date getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(Date lastPayTime) {
        this.lastPayTime = lastPayTime;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode == null ? null : payMode.trim();
    }

    public String getPayOrderNum() {
        return payOrderNum;
    }

    public void setPayOrderNum(String payOrderNum) {
        this.payOrderNum = payOrderNum == null ? null : payOrderNum.trim();
    }

    public String getPayModeOrderNum() {
        return payModeOrderNum;
    }

    public void setPayModeOrderNum(String payModeOrderNum) {
        this.payModeOrderNum = payModeOrderNum == null ? null : payModeOrderNum.trim();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber == null ? null : payNumber.trim();
    }

    public Date getCompeleteTime() {
        return compeleteTime;
    }

    public void setCompeleteTime(Date compeleteTime) {
        this.compeleteTime = compeleteTime;
    }

    public String getFaildMsg() {
        return faildMsg;
    }

    public void setFaildMsg(String faildMsg) {
        this.faildMsg = faildMsg == null ? null : faildMsg.trim();
    }

    public String getCustomerDelete() {
        return customerDelete;
    }

    public void setCustomerDelete(String customerDelete) {
        this.customerDelete = customerDelete == null ? null : customerDelete.trim();
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

    
}