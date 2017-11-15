package com.qjkj.qjcsp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TblOrderRefund {
    private Long orderRefundId;

    private Long orderId;

    private String orderNum;
    
    private Long orderChildId;
    
    private String orderChildNum;

    private String refundReasonOrderChildStatus;
    
    private Long customerId;

    private String mobile;

    private Long machineId;

    private String refundComment;

    private Long companyId;

    private String companyName;//公司名称

    private Date orderTime;

    private Date refundApplyTime;

    private String refundStatus;//退款状态

    private Long businessUserId;

    private String businessUserName;//运维审核人员姓名

    private Date businessCheckTime;

    private Short businessCheckResult;

    private String businessCheckComment;

    private Long financeUserId;

    private String financeUserName;//财务人员姓名

    private Date financeCheckTime;

    private String financeCheckComment;

    private String payMode;//支付方式

    private BigDecimal refundMoney;
    
    
    
    public String getRefundReasonOrderChildStatus() {
		return refundReasonOrderChildStatus;
	}

	public void setRefundReasonOrderChildStatus(String refundReasonOrderChildStatus) {
		this.refundReasonOrderChildStatus = refundReasonOrderChildStatus;
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

	public String getOrderChildNum() {
		return orderChildNum;
	}

	public void setOrderChildNum(String orderChildNum) {
		this.orderChildNum = orderChildNum;
	}

	public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getPayMode() {
        return payMode;
    }

    public String getFinanceUserName() {
        return financeUserName;
    }

    public void setFinanceUserName(String financeUserName) {
        this.financeUserName = financeUserName;
    }

    public String getBusinessUserName() {
        return businessUserName;
    }

    public void setBusinessUserName(String businessUserName) {
        this.businessUserName = businessUserName;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public Long getOrderRefundId() {
        return orderRefundId;
    }

    public void setOrderRefundId(Long orderRefundId) {
        this.orderRefundId = orderRefundId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public String getRefundComment() {
		return refundComment;
	}

	public void setRefundComment(String refundComment) {
		this.refundComment = refundComment;
	}

	public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getRefundApplyTime() {
        return refundApplyTime;
    }

    public void setRefundApplyTime(Date refundApplyTime) {
        this.refundApplyTime = refundApplyTime;
    }

    public Long getBusinessUserId() {
        return businessUserId;
    }

    public void setBusinessUserId(Long businessUserId) {
        this.businessUserId = businessUserId;
    }

    public Date getBusinessCheckTime() {
        return businessCheckTime;
    }

    public void setBusinessCheckTime(Date businessCheckTime) {
        this.businessCheckTime = businessCheckTime;
    }

    public Short getBusinessCheckResult() {
        return businessCheckResult;
    }

    public void setBusinessCheckResult(Short businessCheckResult) {
        this.businessCheckResult = businessCheckResult;
    }

    public String getBusinessCheckComment() {
        return businessCheckComment;
    }

    public void setBusinessCheckComment(String businessCheckComment) {
        this.businessCheckComment = businessCheckComment == null ? null : businessCheckComment.trim();
    }

    public Long getFinanceUserId() {
        return financeUserId;
    }

    public void setFinanceUserId(Long financeUserId) {
        this.financeUserId = financeUserId;
    }

    public Date getFinanceCheckTime() {
        return financeCheckTime;
    }

    public void setFinanceCheckTime(Date financeCheckTime) {
        this.financeCheckTime = financeCheckTime;
    }

    public String getFinanceCheckComment() {
        return financeCheckComment;
    }

    public void setFinanceCheckComment(String financeCheckComment) {
        this.financeCheckComment = financeCheckComment == null ? null : financeCheckComment.trim();
    }
}