package com.qjkj.qjcsp.entity.pick;
/**
 * 取餐记录查询bean
 * @author yehx
 * @date 2016年1月20日  上午9:54:48
 */
public class OrderPickSearch {
	private Boolean isUserofQJKJ; //判断是否是全家科技
	private Integer companyId;//公司id
	private String orderNum;//订单编号
	private Integer goodsId;//菜品id
	private String  goodsName;//菜品名称
	private String  pickStartTime;//取餐开始时间
	private String  pickEndTime;//取餐结束时间
	private Integer machineId;//设备id
	private String  machineName;//设备名称
	private Integer offset;//当前第几页
	private Integer limit;//一页几行
	
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getPickStartTime() {
		return pickStartTime;
	}
	public void setPickStartTime(String pickStartTime) {
		this.pickStartTime = pickStartTime;
	}
	public String getPickEndTime() {
		return pickEndTime;
	}
	public void setPickEndTime(String pickEndTime) {
		this.pickEndTime = pickEndTime;
	}
	public Integer getMachineId() {
		return machineId;
	}
	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public Boolean getIsUserofQJKJ() {
		return isUserofQJKJ;
	}
	public void setIsUserofQJKJ(Boolean isUserofQJKJ) {
		this.isUserofQJKJ = isUserofQJKJ;
	}
	@Override
	public String toString() {
		return "OrderPickSearch [isUserofQJKJ=" + isUserofQJKJ + ", companyId=" + companyId + ", orderNum=" + orderNum
				+ ", goodsId=" + goodsId + ", goodsName=" + goodsName + ", pickStartTime=" + pickStartTime
				+ ", pickEndTime=" + pickEndTime + ", machineId=" + machineId + ", machineName=" + machineName
				+ ", offset=" + offset + ", limit=" + limit + "]";
	}
}
