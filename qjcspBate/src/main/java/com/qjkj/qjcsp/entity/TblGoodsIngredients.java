package com.qjkj.qjcsp.entity;

public class TblGoodsIngredients {
    private Long id;

    private Long goodsId;

    private Long ingredientsTypeId;

    private String ingredientsName;

    private String ingredientsContent;

    private String isdel;

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

    public Long getIngredientsTypeId() {
        return ingredientsTypeId;
    }

    public void setIngredientsTypeId(Long ingredientsTypeId) {
        this.ingredientsTypeId = ingredientsTypeId;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName == null ? null : ingredientsName.trim();
    }

    public String getIngredientsContent() {
        return ingredientsContent;
    }

    public void setIngredientsContent(String ingredientsContent) {
        this.ingredientsContent = ingredientsContent == null ? null : ingredientsContent.trim();
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }
}