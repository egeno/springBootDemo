package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;
import java.sql.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TblOrderChild {
    private Long orderChildId;

    private String orderChildNum;

    private Long orderId;

    private Long areaModelId;
    
    private Date orderTime;
    
    private Long machineId;
    
	private String orderChildStatus;

    private String isdel;

    private String identifyingCode;

    private Date beginTime;

    private Date endTime;

    private String checkState;

    private Date checkTime;
    
    private Date orderPreissueLastRefundTime;
    
    private BigDecimal refundMoney;
    //是否已经结算（标识） 
    private String balanceAmountSign;
    
  //时间段类型
    private String timeType;
    //补货截止时间
    private Time supplyEndTime;
    
    
    public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getSupplyEndTime() {	
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");	
		 Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(supplyEndTime);  
	        calendar.add(Calendar.HOUR_OF_DAY, -1); 
	        Date supplyEnd=calendar.getTime();
	        String time=sdf.format(supplyEnd);
		return time;
	}
	public void setSupplyEndTime(Time supplyEndTime) {
		this.supplyEndTime = supplyEndTime;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

    public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	public Date getOrderPreissueLastRefundTime() {
		return orderPreissueLastRefundTime;
	}

	public void setOrderPreissueLastRefundTime(Date orderPreissueLastRefundTime) {
		this.orderPreissueLastRefundTime = orderPreissueLastRefundTime;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Long getOrderChildId() {
        return orderChildId;
    }

    public void setOrderChildId(Long orderChildId) {
        this.orderChildId = orderChildId;
    }

    public String getOrderChildStatus() {
		return orderChildStatus;
	}

	public void setOrderChildStatus(String orderChildStatus) {
		this.orderChildStatus = orderChildStatus;
	}

	public String getOrderChildNum() {
        return orderChildNum;
    }

    public void setOrderChildNum(String orderChildNum) {
        this.orderChildNum = orderChildNum == null ? null : orderChildNum.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAreaModelId() {
        return areaModelId;
    }

    public void setAreaModelId(Long areaModelId) {
        this.areaModelId = areaModelId;
    }



    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public String getIdentifyingCode() {
        return identifyingCode;
    }

    public void setIdentifyingCode(String identifyingCode) {
        this.identifyingCode = identifyingCode == null ? null : identifyingCode.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState == null ? null : checkState.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

	public String getBalanceAmountSign() {
		return balanceAmountSign;
	}

	public void setBalanceAmountSign(String balanceAmountSign) {
		this.balanceAmountSign = balanceAmountSign;
	}
}