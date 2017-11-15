package com.qjkj.qjcsp.entity;

public class AlarmReportSearch {
	private long deviceFaultSymbol;
	private String sellStartTime;
	private String sellEndTime;
	private String shopName;
	private String machineName;
	private Integer offset;
	private Integer limit;
	public long getDeviceFaultSymbol() {
		return deviceFaultSymbol;
	}
	public void setDeviceFaultSymbol(long deviceFaultSymbol) {
		this.deviceFaultSymbol = deviceFaultSymbol;
	}
	public String getSellStartTime() {
		return sellStartTime;
	}
	public void setSellStartTime(String sellStartTime) {
		this.sellStartTime = sellStartTime;
	}
	public String getSellEndTime() {
		return sellEndTime;
	}
	public void setSellEndTime(String sellEndTime) {
		this.sellEndTime = sellEndTime;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "AlarmReportSearch [deviceFaultSymbol=" + deviceFaultSymbol + ", sellStartTime=" + sellStartTime
				+ ", sellEndTime=" + sellEndTime + ", shopName=" + shopName + ", machineName=" + machineName
				+ ", offset=" + offset + ", limit=" + limit + "]";
	}

	
}
