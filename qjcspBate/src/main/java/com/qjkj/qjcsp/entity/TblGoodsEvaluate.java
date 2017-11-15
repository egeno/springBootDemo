package com.qjkj.qjcsp.entity;

import java.util.Date;

public class TblGoodsEvaluate {
    private Long id;

    private Long goodsId;

    private Long machineId;

    private Long orderId;

    private Long orderChildId;

    private Long orderDetailId;

    private Short goodsEvaluateScore;

    private String goodsEvaluateContent;

    private Date createTime;

    private String isverify;
    public String getIsverify() {
		return isverify;
	}

	public void setIsverify(String isverify) {
		this.isverify = isverify==null?null:isverify.trim();;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderChildId() {
        return orderChildId;
    }

    public void setOrderChildId(Long orderChildId) {
        this.orderChildId = orderChildId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Short getGoodsEvaluateScore() {
        return goodsEvaluateScore;
    }

    public void setGoodsEvaluateScore(Short goodsEvaluateScore) {
        this.goodsEvaluateScore = goodsEvaluateScore;
    }

    public String getGoodsEvaluateContent() {
        return goodsEvaluateContent;
    }

    public void setGoodsEvaluateContent(String goodsEvaluateContent) {
        this.goodsEvaluateContent = goodsEvaluateContent == null ? null : goodsEvaluateContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}