package com.qjkj.qjcsp.entity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


public class TblGoodsIngredientsType {
    private Long ingredientsTypeId;

    private String ingredientsTypeName;

    private Long goodsId;

    private String isdel; 
    
    //成分对象
//    private  List<TblGoodsIngredients> ingredientsDetails;
//    private  List<Map<String, Object>> ingredientsDetails;

//    public List<Map<String, Object>> getIngredientsDetails() {
//		return ingredientsDetails;
//	}
//
//	public void setIngredientsDetails(List<Map<String, Object>> ingredientsDetails) {
//		this.ingredientsDetails = ingredientsDetails;
//	}

	public Long getIngredientsTypeId() {
        return ingredientsTypeId;
    }

    public void setIngredientsTypeId(Long ingredientsTypeId) {
        this.ingredientsTypeId = ingredientsTypeId;
    }

    public String getIngredientsTypeName() {
        return ingredientsTypeName;
    }

    public void setIngredientsTypeName(String ingredientsTypeName) {
        this.ingredientsTypeName = ingredientsTypeName == null ? null : ingredientsTypeName.trim();
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }
}