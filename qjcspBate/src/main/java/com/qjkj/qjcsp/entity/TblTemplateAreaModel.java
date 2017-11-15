package com.qjkj.qjcsp.entity;

public class TblTemplateAreaModel {
    private Long id;

    private Long areaModelId;

    private Long templateId;

    private Integer status;
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaModelId() {
        return areaModelId;
    }

    public void setAreaModelId(Long areaModelId) {
        this.areaModelId = areaModelId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}