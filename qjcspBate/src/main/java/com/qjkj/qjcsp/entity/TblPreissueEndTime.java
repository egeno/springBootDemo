package com.qjkj.qjcsp.entity;

import java.util.Date;

public class TblPreissueEndTime {
    private Long preissueEndTimeId;

    private Date preissueEndTime;

    private String preissueEndTimeStr;
    
    private Long companyId;

    private Long createUserId;

    private Date createTime;

    private Long modUserId;

    private Date modTime;

    
    public String getPreissueEndTimeStr() {
		return preissueEndTimeStr;
	}

	public void setPreissueEndTimeStr(String preissueEndTimeStr) {
		this.preissueEndTimeStr = preissueEndTimeStr;
	}

	public Long getPreissueEndTimeId() {
        return preissueEndTimeId;
    }

    public void setPreissueEndTimeId(Long preissueEndTimeId) {
        this.preissueEndTimeId = preissueEndTimeId;
    }

    public Date getPreissueEndTime() {
        return preissueEndTime;
    }

    public void setPreissueEndTime(Date preissueEndTime) {
        this.preissueEndTime = preissueEndTime;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModUserId() {
        return modUserId;
    }

    public void setModUserId(Long modUserId) {
        this.modUserId = modUserId;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }
}