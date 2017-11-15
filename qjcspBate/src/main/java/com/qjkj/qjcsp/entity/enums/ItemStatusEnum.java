package com.qjkj.qjcsp.entity.enums;

public enum ItemStatusEnum {
	UNSOLD("未售","0"),
	SOLD("已售","1"),
	CLEAR("已清货","2"),
	TOOK("已取货","3");
	
	private String lable;
	private String value;
	
	private ItemStatusEnum(String lable, String value) {
		this.lable = lable;
		this.value = value;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
