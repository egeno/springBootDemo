package com.qjkj.qjcsp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.qjkj.qjcsp.entity.machinearea.MachineAreaCell;
public class MachineAreaPo extends BasicsAreaModel{
	private String companyName;//公司名称
	private Long machineId;//设备id
	private String deviceCode;//设备硬件id
	private String machineName;//设备名称
	private Long areaId;//分区id
	private String areaName;//分区名称
	private Integer areaGoodNum;//分区格子数量
	private String areaModelName;//分区模型名称
	private Long goodId;//商品id
	private String goodName;//商品名称
	private String areaMemo;//分区描述
	private String areaStatus;//分区状态
	private String isdel;//是否删除
	private Integer minNum;//预警数量
	private Date createTime;//创建日期
	private Date lastModTime;//最后修改日期
	private Long createUserId;//创建人
	private Long modUserId;//修改人
	private List<MachineAreaCell>machineAreaCells=new ArrayList<MachineAreaCell>();//一个分区对应多个格子
	
	


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public Long getMachineId() {
		return machineId;
	}


	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}


	public String getDeviceCode() {
		return deviceCode;
	}


	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}


	public String getMachineName() {
		return machineName;
	}


	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}


	public Long getAreaId() {
		return areaId;
	}


	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}


	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public Integer getAreaGoodNum() {
		return areaGoodNum;
	}


	public void setAreaGoodNum(Integer areaGoodNum) {
		this.areaGoodNum = areaGoodNum;
	}




	public String getAreaModelName() {
		return areaModelName;
	}


	public void setAreaModelName(String areaModelName) {
		this.areaModelName = areaModelName;
	}


	public Long getGoodId() {
		return goodId;
	}


	public void setGoodId(Long goodId) {
		this.goodId = goodId;
	}


	public String getGoodName() {
		return goodName;
	}


	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}


	public String getAreaMemo() {
		return areaMemo;
	}


	public void setAreaMemo(String areaMemo) {
		this.areaMemo = areaMemo;
	}


	public String getAreaStatus() {
		return areaStatus;
	}


	public void setAreaStatus(String areaStatus) {
		this.areaStatus = areaStatus;
	}


	public String getIsdel() {
		return isdel;
	}


	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}


	public Integer getMinNum() {
		return minNum;
	}


	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getLastModTime() {
		return lastModTime;
	}


	public void setLastModTime(Date lastModTime) {
		this.lastModTime = lastModTime;
	}


	public Long getCreateUserId() {
		return createUserId;
	}


	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}


	public Long getModUserId() {
		return modUserId;
	}


	public void setModUserId(Long modUserId) {
		this.modUserId = modUserId;
	}


	public List<MachineAreaCell> getMachineAreaCells() {
		return machineAreaCells;
	}


	public void setMachineAreaCells(List<MachineAreaCell> machineAreaCells) {
		this.machineAreaCells = machineAreaCells;
	}



}
