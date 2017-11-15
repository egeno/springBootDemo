package com.qjkj.qjcsp.entity;

public class TblGoodsAreaModel {
    private Long id;

    private Long areaModelId;

    private Long goodsId;

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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}