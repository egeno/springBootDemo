package com.qjkj.qjcsp.entity.viewmodel;

import com.qjkj.qjcsp.entity.BasicsGoods;

public class BasicsGoodsVo extends BasicsGoods {

	private String categoryName;
	
	private String createTimeStr;
	
	private String lastModTimeStr;
	
	private String creater;
	
	private String modifier;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getLastModTimeStr() {
		return lastModTimeStr;
	}

	public void setLastModTimeStr(String lastModTimeStr) {
		this.lastModTimeStr = lastModTimeStr;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
}
