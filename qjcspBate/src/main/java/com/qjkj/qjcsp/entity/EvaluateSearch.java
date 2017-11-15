package com.qjkj.qjcsp.entity;

public class EvaluateSearch {
	private String  evaluateStartTime;//退款申请开始时间
	private String  evaluateEndTime;//退款申请结束时间
	private String  verifyStatus;//审核状态 N:未通过 Y:已通过
	private Integer offset;//当前第几页
	private Integer limit;//一页几行
	public String getEvaluateStartTime() {
		return evaluateStartTime;
	}
	public void setEvaluateStartTime(String evaluateStartTime) {
		this.evaluateStartTime = evaluateStartTime;
	}
	public String getEvaluateEndTime() {
		return evaluateEndTime;
	}
	public void setEvaluateEndTime(String evaluateEndTime) {
		this.evaluateEndTime = evaluateEndTime;
	}
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
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
}
