package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;

public class TblOrderDetail {
    private Long orderDetailId;

    private Long orderChildId;

    private Long machineId;

    private Long companyId;

    private String orderDetailNum;

    private Long goodsId;

    private String goodsName;

    private Integer goodsNum;

    private BigDecimal price;
    
    private BigDecimal profit;

    private Long retailId;
    
    private BigDecimal costPrice;
    
    public Long getRetailId() {
		return retailId;
	}

	public void setRetailId(Long retailId) {
		this.retailId = retailId;
	}

	public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getOrderChildId() {
        return orderChildId;
    }

    public void setOrderChildId(Long orderChildId) {
        this.orderChildId = orderChildId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getOrderDetailNum() {
        return orderDetailNum;
    }

    public void setOrderDetailNum(String orderDetailNum) {
        this.orderDetailNum = orderDetailNum == null ? null : orderDetailNum.trim();
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
    
}