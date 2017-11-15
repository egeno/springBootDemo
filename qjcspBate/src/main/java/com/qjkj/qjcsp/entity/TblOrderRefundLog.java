package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TblOrderRefundLog {
    private Long refundLogId;

    private Long customerId;

    private BigDecimal customerMoney;

    private BigDecimal refundMoney;

    private String orderNum;

    private Date createTime;

    private Long createUserId;

    public Long getRefundLogId() {
        return refundLogId;
    }

    public void setRefundLogId(Long refundLogId) {
        this.refundLogId = refundLogId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getCustomerMoney() {
        return customerMoney;
    }

    public void setCustomerMoney(BigDecimal customerMoney) {
        this.customerMoney = customerMoney;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
}