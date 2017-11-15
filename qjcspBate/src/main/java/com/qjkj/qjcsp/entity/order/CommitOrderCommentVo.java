package com.qjkj.qjcsp.entity.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommitOrderCommentVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderNum;//订单编号
	private String customerId;//用户id
	private String orderGrade;//订单评分
	private String orderComment;//订单内容
	private String orderItemCommentList;//
	
	//private List<Goods>orderItemCommentList=new ArrayList<Goods>();//订单明细评论
	
	
public String getOrderItemCommentList() {
		return orderItemCommentList;
	}
	public void setOrderItemCommentList(String orderItemCommentList) {
		this.orderItemCommentList = orderItemCommentList;
	}
	//	public List<Goods> getOrderItemCommentList() {
//		return orderItemCommentList;
//	}
//	public void setOrderItemCommentList(List<Goods> orderItemCommentList) {
//		this.orderItemCommentList = orderItemCommentList;
//	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getOrderGrade() {
		return orderGrade;
	}
	public void setOrderGrade(String orderGrade) {
		this.orderGrade = orderGrade;
	}
	public String getOrderComment() {
		return orderComment;
	}
	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CommitOrderCommentVo [orderNum=" + orderNum + ", customerId=" + customerId + ", orderGrade="
				+ orderGrade + ", orderComment=" + orderComment + ", orderItemCommentList=" + orderItemCommentList
				+ "]";
	}
		
	
}
