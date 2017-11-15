package com.qjkj.qjcsp.entity;

import java.util.List;

public class OrderByUserMap {

	private Long customerId;
	private List<TblOrderChild> orderChilds;

	public List<TblOrderChild> getOrderChilds() {
		return orderChilds;
	}
	public void setOrderChilds(List<TblOrderChild> orderChilds) {
		this.orderChilds = orderChilds;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
}
