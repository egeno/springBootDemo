package com.qjkj.qjcsp.entity;

import java.util.Date;

public class TblPendingOrder {
    private Long pendingId;

    private String pendingTypeNum;

    private String modeNum;

    private String orderType;

    private Long companyId;

    private Long orderId;

    private String orderNum;

    private Long orderChildId;

    private String payOrderNum;

    private Date orderTime;

    private Date lastPayTime;

    private Date beginTime;

    private Date endTime;

    public Long getPendingId() {
        return pendingId;
    }

    public void setPendingId(Long pendingId) {
        this.pendingId = pendingId;
    }

    public String getPendingTypeNum() {
        return pendingTypeNum;
    }

    public void setPendingTypeNum(String pendingTypeNum) {
        this.pendingTypeNum = pendingTypeNum == null ? null : pendingTypeNum.trim();
    }

    public String getModeNum() {
        return modeNum;
    }

    public void setModeNum(String modeNum) {
        this.modeNum = modeNum == null ? null : modeNum.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

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

    public Long getOrderChildId() {
        return orderChildId;
    }

    public void setOrderChildId(Long orderChildId) {
        this.orderChildId = orderChildId;
    }

    public String getPayOrderNum() {
        return payOrderNum;
    }

    public void setPayOrderNum(String payOrderNum) {
        this.payOrderNum = payOrderNum == null ? null : payOrderNum.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(Date lastPayTime) {
        this.lastPayTime = lastPayTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}