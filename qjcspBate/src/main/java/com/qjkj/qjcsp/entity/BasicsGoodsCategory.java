package com.qjkj.qjcsp.entity;

import java.util.Date;

public class BasicsGoodsCategory {
    private Long goodsCategoryId;

    private String goodsCategoryCode;

    private Long parentGoodsCategoryId;

    private String parentGoodsCategoryName;

    private Short level;

    private String isLastLayer;

    private Long companyId;

    private String goodsCategoryIcon;

    private String goodsCategoryName;

    private String goodsCategoryMemo;

    private Integer goodsCategorySort;

    private String goodsCategoryStatus;

    private String isdel;

    private Date createTime;

    private Date lastModTime;

    private Long createUserId;

    private Long modUserId;

    public Long getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(Long goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getGoodsCategoryCode() {
        return goodsCategoryCode;
    }

    public void setGoodsCategoryCode(String goodsCategoryCode) {
        this.goodsCategoryCode = goodsCategoryCode == null ? null : goodsCategoryCode.trim();
    }

    public Long getParentGoodsCategoryId() {
        return parentGoodsCategoryId;
    }

    public void setParentGoodsCategoryId(Long parentGoodsCategoryId) {
        this.parentGoodsCategoryId = parentGoodsCategoryId;
    }

    public String getParentGoodsCategoryName() {
        return parentGoodsCategoryName;
    }

    public void setParentGoodsCategoryName(String parentGoodsCategoryName) {
        this.parentGoodsCategoryName = parentGoodsCategoryName == null ? null : parentGoodsCategoryName.trim();
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public String getIsLastLayer() {
        return isLastLayer;
    }

    public void setIsLastLayer(String isLastLayer) {
        this.isLastLayer = isLastLayer == null ? null : isLastLayer.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getGoodsCategoryIcon() {
        return goodsCategoryIcon;
    }

    public void setGoodsCategoryIcon(String goodsCategoryIcon) {
        this.goodsCategoryIcon = goodsCategoryIcon == null ? null : goodsCategoryIcon.trim();
    }

    public String getGoodsCategoryName() {
        return goodsCategoryName;
    }

    public void setGoodsCategoryName(String goodsCategoryName) {
        this.goodsCategoryName = goodsCategoryName == null ? null : goodsCategoryName.trim();
    }

    public String getGoodsCategoryMemo() {
        return goodsCategoryMemo;
    }

    public void setGoodsCategoryMemo(String goodsCategoryMemo) {
        this.goodsCategoryMemo = goodsCategoryMemo == null ? null : goodsCategoryMemo.trim();
    }

    public Integer getGoodsCategorySort() {
        return goodsCategorySort;
    }

    public void setGoodsCategorySort(Integer goodsCategorySort) {
        this.goodsCategorySort = goodsCategorySort;
    }

    public String getGoodsCategoryStatus() {
        return goodsCategoryStatus;
    }

    public void setGoodsCategoryStatus(String goodsCategoryStatus) {
        this.goodsCategoryStatus = goodsCategoryStatus == null ? null : goodsCategoryStatus.trim();
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
}