package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TblRecharge {
    private Long rechargeId;

    private String orderNum;

    private String modeNum;

    private Long customerId;

    private String mobile;

    private BigDecimal totalMoney;

    private BigDecimal realMoney;

    private BigDecimal activityMoney;
    
    private String orderStatus;

    private Date orderTime;

    private Date payTime;

    private String payOrderNum;

    private String isdel;
    //结余金额  用于结算统计 慎改
    private BigDecimal balanceAmount;
    //是否已经结算（标识） 同上
    private String balanceAmountSign;
    

    public Long getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getModeNum() {
        return modeNum;
    }

    public void setModeNum(String modeNum) {
        this.modeNum = modeNum == null ? null : modeNum.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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

    public BigDecimal getActivityMoney() {
        return activityMoney;
    }

    public void setActivityMoney(BigDecimal activityMoney) {
        this.activityMoney = activityMoney;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayOrderNum() {
        return payOrderNum;
    }

    public void setPayOrderNum(String payOrderNum) {
        this.payOrderNum = payOrderNum == null ? null : payOrderNum.trim();
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getBalanceAmountSign() {
		return balanceAmountSign;
	}

	public void setBalanceAmountSign(String balanceAmountSign) {
		this.balanceAmountSign = balanceAmountSign;
	}
}