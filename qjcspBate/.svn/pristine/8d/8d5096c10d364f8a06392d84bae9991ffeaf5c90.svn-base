package com.qjkj.qjcsp.entity.enums;

public enum DataStatusEnum {

	NORMAL("正常数据","1"),
	INVALID("无效数据","-1") ;
	
	private String lable;
	private String value;
	
	private DataStatusEnum(String lable,String value){
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

	public static DataStatusEnum getDataStatusEnumByValue(String value){
		DataStatusEnum[] enums = DataStatusEnum.values();
		for(DataStatusEnum gradeEnum : enums){
			if(gradeEnum.getValue().equals(value)){
				return gradeEnum;
			}
		}
		return null;
	}
}
