package com.qjkj.qjcsp.entity.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDeviceEvaluateRes {
	private String comGrade;//菜品评分
	private String gradeCount;//商品评价数量
	private List<Map<String,Object>> goodsCommentList=new ArrayList<Map<String,Object>>();
	
	public String getGradeCount() {
		return gradeCount;
	}
	public void setGradeCount(String gradeCount) {
		this.gradeCount = gradeCount;
	}
	public String getComGrade() {
		return comGrade;
	}
	public void setComGrade(String comGrade) {
		this.comGrade = comGrade;
	}
	
	public List<Map<String, Object>> getGoodsCommentList() {
		return goodsCommentList;
	}
	public void setGoodsCommentList(List<Map<String, Object>> goodsCommentList) {
		this.goodsCommentList = goodsCommentList;
	}
	@Override
	public String toString() {
		return "OrderDeviceEvaluateRes [comGrade=" + comGrade + ", gradeCount=" + gradeCount + ", goodsCommentList="
				+ goodsCommentList + "]";
	}
	
	
	

}
