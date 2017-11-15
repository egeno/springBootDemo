package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TblRechargeActivity {
    private Long activityId;

    private Date starttime;

    private Date endtime;

    private BigDecimal money;

    private BigDecimal activityMoney;

    private String effectSymbol;

    private Date createTime;

    private Date lastModifyTime;

    private Long createUserId;

    private Long modUserId;

    private String isdel;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getActivityMoney() {
		return activityMoney;
	}

	public void setActivityMoney(BigDecimal activityMoney) {
		this.activityMoney = activityMoney;
	}

	public String getEffectSymbol() {
        return effectSymbol;
    }

    public void setEffectSymbol(String effectSymbol) {
        this.effectSymbol = effectSymbol == null ? null : effectSymbol.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
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

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }
}