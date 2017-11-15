package com.qjkj.qjcsp.entity;

import java.util.Date;

public class BasicsAreaModel {
    private Long areaModelId;

    private Long companyId;

    private String areaModelName;

    private String areaModelType;

    private Integer areaModelRow;

    private Integer areaModelColumn;

    private String areaModelMemo;

    private String areaModelStatus;
    
    private String timeType;

    private String isdel;

    private Date createTime;

    private Date lastModTime;

    private Long createUserId;

    private Long modUserId;

    public Long getAreaModelId() {
        return areaModelId;
    }

    public void setAreaModelId(Long areaModelId) {
        this.areaModelId = areaModelId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getAreaModelName() {
        return areaModelName;
    }

    public void setAreaModelName(String areaModelName) {
        this.areaModelName = areaModelName == null ? null : areaModelName.trim();
    }

    public String getAreaModelType() {
        return areaModelType;
    }

    public void setAreaModelType(String areaModelType) {
        this.areaModelType = areaModelType == null ? null : areaModelType.trim();
    }

    public Integer getAreaModelRow() {
        return areaModelRow;
    }

    public void setAreaModelRow(Integer areaModelRow) {
        this.areaModelRow = areaModelRow;
    }

    public Integer getAreaModelColumn() {
        return areaModelColumn;
    }

    public void setAreaModelColumn(Integer areaModelColumn) {
        this.areaModelColumn = areaModelColumn;
    }

    public String getAreaModelMemo() {
        return areaModelMemo;
    }

    public void setAreaModelMemo(String areaModelMemo) {
        this.areaModelMemo = areaModelMemo == null ? null : areaModelMemo.trim();
    }

    public String getAreaModelStatus() {
        return areaModelStatus;
    }

    public void setAreaModelStatus(String areaModelStatus) {
        this.areaModelStatus = areaModelStatus == null ? null : areaModelStatus.trim();
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModTime() {
        return lastModTime;
    }

    public void setLastModTime(Date lastModTime) {
        this.lastModTime = lastModTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getModUserId() {
        return modUserId;
    }

    public void setModUserId(Long modUserId) {
        this.modUserId = modUserId;
    }

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
    
}