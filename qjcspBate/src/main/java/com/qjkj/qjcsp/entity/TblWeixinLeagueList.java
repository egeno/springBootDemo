package com.qjkj.qjcsp.entity;

public class TblWeixinLeagueList extends TblWeixinLeague{
	private Integer offset;//当前第几页
	private Integer limit;//一页几行
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
