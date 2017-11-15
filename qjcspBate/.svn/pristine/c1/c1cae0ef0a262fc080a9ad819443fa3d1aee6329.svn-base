package com.qjkj.qjcsp.core.shiro;

public enum LoginSourceEnum {
	
	BACKGROUND("后台登录","background"),
	APP("app登录","app");
	
	private String lable;
	private String value;
	
	private LoginSourceEnum(String lable,String value){
		this.lable = lable ;
		this.value = value ;
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
	
	public static LoginSourceEnum getLoginSourceEnumByValue(String value){
		LoginSourceEnum[] enums = LoginSourceEnum.values();
		for(LoginSourceEnum gradeEnum : enums){
			if(gradeEnum.getValue().equals(value)){
				return gradeEnum;
			}
		}
		return null;
	}
}
