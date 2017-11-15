package com.qjkj.qjcsp.entity;

import java.util.Date;

public class TblCustomerIntegralLog {
    private Long id;

    private Long customerId;

    private Integer integral;

    private Integer oldIntegral;

    private Date createTime;

    private Long createUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getOldIntegral() {
        return oldIntegral;
    }

    public void setOldIntegral(Integer oldIntegral) {
        this.oldIntegral = oldIntegral;
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