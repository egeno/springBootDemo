package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.qjkj.qjcsp.util.DateUtils;

public class TblBalanceAmount {
    private Long id;

    private Long rechargeId;

    private String rechargeOrderNum;

    private Long orderChildId;

    private String orderChildNum;

    private BigDecimal orderChildTotalMoney;

    private BigDecimal offsetAmount;

    private BigDecimal rechargeSurplusAmount;

    private BigDecimal rechargeTotalMoney;

    private Long createUserId;

    private Date createTime;
    
    private Long customerId;
    
    private String isdel;
    public TblBalanceAmount(){
    	
    }
    public TblBalanceAmount(Long rechargeId, String rechargeOrderNum, Long orderChildId, String orderChildNum,
			BigDecimal orderChildTotalMoney, BigDecimal offsetAmount, BigDecimal rechargeSurplusAmount,
			BigDecimal rechargeTotalMoney, Long customerId) {
		super();
		this.rechargeId = rechargeId;
		this.rechargeOrderNum = rechargeOrderNum;
		this.orderChildId = orderChildId;
		this.orderChildNum = orderChildNum;
		this.orderChildTotalMoney = orderChildTotalMoney;
		this.offsetAmount = offsetAmount;
		this.rechargeSurplusAmount = rechargeSurplusAmount;
		this.rechargeTotalMoney = rechargeTotalMoney;
		this.customerId = customerId;
		this.createTime = DateUtils.getNowDate();
		System.out.println("充值订单:"+rechargeId+"子订单号:"+orderChildId+"订单消费金额:"+orderChildTotalMoney
				+ "抵消金额："+offsetAmount+"充值结余："+rechargeSurplusAmount+"充值总金额"+rechargeTotalMoney);
	}
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getRechargeOrderNum() {
        return rechargeOrderNum;
    }

    public void setRechargeOrderNum(String rechargeOrderNum) {
        this.rechargeOrderNum = rechargeOrderNum == null ? null : rechargeOrderNum.trim();
    }

    public Long getOrderChildId() {
        return orderChildId;
    }

    public void setOrderChildId(Long orderChildId) {
        this.orderChildId = orderChildId;
    }

    public String getOrderChildNum() {
        return orderChildNum;
    }

    public void setOrderChildNum(String orderChildNum) {
        this.orderChildNum = orderChildNum == null ? null : orderChildNum.trim();
    }

    public BigDecimal getOrderChildTotalMoney() {
        return orderChildTotalMoney;
    }

    public void setOrderChildTotalMoney(BigDecimal orderChildTotalMoney) {
        this.orderChildTotalMoney = orderChildTotalMoney;
    }

    public BigDecimal getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(BigDecimal offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    public BigDecimal getRechargeSurplusAmount() {
        return rechargeSurplusAmount;
    }

    public void setRechargeSurplusAmount(BigDecimal rechargeSurplusAmount) {
        this.rechargeSurplusAmount = rechargeSurplusAmount;
    }

    public BigDecimal getRechargeTotalMoney() {
        return rechargeTotalMoney;
    }

    public void setRechargeTotalMoney(BigDecimal rechargeTotalMoney) {
        this.rechargeTotalMoney = rechargeTotalMoney;
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

    public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }
}