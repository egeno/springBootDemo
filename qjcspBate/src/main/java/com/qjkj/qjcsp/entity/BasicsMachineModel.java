package com.qjkj.qjcsp.entity;

import java.util.Date;

public class BasicsMachineModel {
    private Integer modelId;

    private String modelName;

    private String modelIcon;

    private String modelType;

    private Integer rowNum;

    private Integer columnNum;

    private String tolerance;

    private String targetTemperature;

    private String modelMemo;

    private String modelStatus;

    private String isdel;

    private Date createTime;

    private Date lastModTime;

    private Long createUserId;
    
    //修改人
    private Long modUserId;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getModelIcon() {
        return modelIcon;
    }

    public void setModelIcon(String modelIcon) {
        this.modelIcon = modelIcon == null ? null : modelIcon.trim();
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType == null ? null : modelType.trim();
    }

    public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Integer getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(Integer columnNum) {
		this.columnNum = columnNum;
	}

	public String getTolerance() {
        return tolerance;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance == null ? null : tolerance.trim();
    }

    public String getTargetTemperature() {
		return targetTemperature;
	}

	public void setTargetTemperature(String targetTemperature) {
		this.targetTemperature = targetTemperature;
	}

	public String getModelMemo() {
        return modelMemo;
    }

    public void setModelMemo(String modelMemo) {
        this.modelMemo = modelMemo == null ? null : modelMemo.trim();
    }

    public String getModelStatus() {
        return modelStatus;
    }

    public void setModelStatus(String modelStatus) {
        this.modelStatus = modelStatus == null ? null : modelStatus.trim();
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public Date getcreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModTime() {
        return lastModTime;
    }

    public void setLastModTime(Date lastModTime) {
        this.lastModTime = lastModTime;
    }

    public Long getcreateUserId() {
        return createUserId;
    }

    public void setcreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getModUserId() {
        return modUserId;
    }

    public void setModUserId(Long modUserId) {
        this.modUserId = modUserId;
    }
}