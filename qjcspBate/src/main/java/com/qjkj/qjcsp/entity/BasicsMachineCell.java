package com.qjkj.qjcsp.entity;

import java.util.Date;

public class BasicsMachineCell {
    private String cellId;

    private Long machineId;

    private Short rowNum;

    private Short colNum;
    
    private Integer cellNum;

    private String cellStatus;

    private String itemId;

    
    public Integer getCellNum() {
		return cellNum;
	}

	public void setCellNum(Integer cellNum) {
		this.cellNum = cellNum;
	}

	public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId == null ? null : cellId.trim();
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Short getRowNum() {
        return rowNum;
    }

    public void setRowNum(Short rowNum) {
        this.rowNum = rowNum;
    }

    public Short getColNum() {
        return colNum;
    }

    public void setColNum(Short colNum) {
        this.colNum = colNum;
    }

    public String getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(String cellStatus) {
        this.cellStatus = cellStatus == null ? null : cellStatus.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

}