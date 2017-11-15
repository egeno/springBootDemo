package com.qjkj.qjcsp.entity;

import java.util.Date;

public class TblMachineTemplateRetailDateLog {
    private Long id;

    private Long machineTemplateRetailDateId;

    private Long operateUserId;

    private String operateType;

    private Date operateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMachineTemplateRetailDateId() {
        return machineTemplateRetailDateId;
    }

    public void setMachineTemplateRetailDateId(Long machineTemplateRetailDateId) {
        this.machineTemplateRetailDateId = machineTemplateRetailDateId;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType == null ? null : operateType.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}