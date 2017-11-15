package com.qjkj.qjcsp.entity;

import java.util.Date;

public class TblRetail {
    private Long retailId;

    private Long templateId;
    
    private String templateName;

    private Long areaModelId;

    private Long goodsId;

    private Double retailPercent;

    private Date createTime;

    private Long createUserId;

    private Date modTime;

    private Long modUserId;

    private String isdel;

    public Long getRetailId() {
        return retailId;
    }

    public void setRetailId(Long retailId) {
        this.retailId = retailId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getAreaModelId() {
        return areaModelId;
    }

    public void setAreaModelId(Long areaModelId) {
        this.areaModelId = areaModelId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Double getRetailPercent() {
        return retailPercent;
    }

    public void setRetailPercent(Double retailPercent) {
        this.retailPercent = retailPercent;
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

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public Long getModUserId() {
        return modUserId;
    }

    public void setModUserId(Long modUserId) {
        this.modUserId = modUserId;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
    
}