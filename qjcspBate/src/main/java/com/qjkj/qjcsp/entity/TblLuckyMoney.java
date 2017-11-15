package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TblLuckyMoney {
    private Long id;

    private BigDecimal luckyMoney;

    private String customerMobile;

    private Long customerId;

    private Date validStartDate;

    private Date validEndDate;

    private String luckyMoneyType;
    
    private String isUsed;

    public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLuckyMoney() {
        return luckyMoney;
    }

    public void setLuckyMoney(BigDecimal luckyMoney) {
        this.luckyMoney = luckyMoney;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile == null ? null : customerMobile.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(Date validStartDate) {
        this.validStartDate = validStartDate;
    }

    public Date getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
    }

    public String getLuckyMoneyType() {
        return luckyMoneyType;
    }

    public void setLuckyMoneyType(String luckyMoneyType) {
        this.luckyMoneyType = luckyMoneyType == null ? null : luckyMoneyType.trim();
    }
}