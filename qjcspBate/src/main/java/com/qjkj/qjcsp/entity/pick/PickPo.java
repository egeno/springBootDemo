package com.qjkj.qjcsp.entity.pick;

public class PickPo {
	private Integer orderPickId;
	private String machineName;
	private String orderNum;
	private String pickTime;
	private String goodsName;
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getPickTime() {
		return pickTime;
	}
	public void setPickTime(String pickTime) {
		this.pickTime = pickTime;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public Integer getOrderPickId() {
		return orderPickId;
	}
	public void setOrderPickId(Integer orderPickId) {
		this.orderPickId = orderPickId;
	
	}
	@Override
	public String toString() {
		return "PickPo [orderPickId=" + orderPickId + ", machineName=" + machineName + ", orderNum=" + orderNum
				+ ", pickTime=" + pickTime + ", goodsName=" + goodsName + "]";
	}
	

}
