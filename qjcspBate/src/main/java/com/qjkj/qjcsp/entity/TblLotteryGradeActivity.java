package com.qjkj.qjcsp.entity;

import java.util.Date;

public class TblLotteryGradeActivity {
    private String startTime;

    private String endTime;

    private Long createUserId;

    private Date createTime;

    private Long modUserId;

    private Date modTime;

    private String effectSymbol;
    
    private String createTimeStr;
    
    private String modTimeStr;
    
    private String createUserName;
    
    private String modUserName;
    

    public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getModUserName() {
		return modUserName;
	}

	public void setModUserName(String modUserName) {
		this.modUserName = modUserName;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getModTimeStr() {
		return modTimeStr;
	}

	public void setModTimeStr(String modTimeStr) {
		this.modTimeStr = modTimeStr;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

    public String getEffectSymbol() {
        return effectSymbol;
    }

    public void setEffectSymbol(String effectSymbol) {
        this.effectSymbol = effectSymbol == null ? null : effectSymbol.trim();
    }
}