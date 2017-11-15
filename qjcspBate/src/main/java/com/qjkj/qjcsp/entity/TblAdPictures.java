package com.qjkj.qjcsp.entity;

import java.util.Date;

public class TblAdPictures {
    private Long adPicId;

    private String adPicType;

    private String adPicName;

    private String adPicDesc;

    private String adPicUrl;

    private String adHrefUrl;

    private String picSortNum;

    private Date createTime;

    private Long createUserId;

    private Date modifyTime;

    private Long modUserId;

    public Long getAdPicId() {
        return adPicId;
    }

    public void setAdPicId(Long adPicId) {
        this.adPicId = adPicId;
    }

    public String getAdPicType() {
        return adPicType;
    }

    public void setAdPicType(String adPicType) {
        this.adPicType = adPicType == null ? null : adPicType.trim();
    }

    public String getAdPicName() {
        return adPicName;
    }

    public void setAdPicName(String adPicName) {
        this.adPicName = adPicName == null ? null : adPicName.trim();
    }

    public String getAdPicDesc() {
        return adPicDesc;
    }

    public void setAdPicDesc(String adPicDesc) {
        this.adPicDesc = adPicDesc == null ? null : adPicDesc.trim();
    }

    public String getAdPicUrl() {
        return adPicUrl;
    }

    public void setAdPicUrl(String adPicUrl) {
        this.adPicUrl = adPicUrl == null ? null : adPicUrl.trim();
    }

    public String getAdHrefUrl() {
        return adHrefUrl;
    }

    public void setAdHrefUrl(String adHrefUrl) {
        this.adHrefUrl = adHrefUrl == null ? null : adHrefUrl.trim();
    }

    public String getPicSortNum() {
        return picSortNum;
    }

    public void setPicSortNum(String picSortNum) {
        this.picSortNum = picSortNum == null ? null : picSortNum.trim();
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModUserId() {
        return modUserId;
    }

    public void setModUserId(Long modUserId) {
        this.modUserId = modUserId;
    }
}