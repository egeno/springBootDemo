<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>首单优惠活动</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="/layout/script.jsp"%>
<script type="text/javascript" src="<%=basePath%>views/statistics/firstOrderDiscountActivity/js/listFirstOrderDiscountActivityMain.js"></script>
</head>
<body>
<!--   	<div class="well well-small" style="margin-left: 5px;margin-top: 5px"> -->
<!-- 		<span class="badge">提示</span> -->
<!-- 		<p> -->
<!-- 			在此你可以根据条件查询<span class="label-info"><strong>订单信息</strong></span> -->
<!-- 		</p> -->
<!-- 	</div> -->
	<div id="tb" style="padding: 10px">
		<table cellpadding="5" cellspacing="0">
			<tr>

				<td>
					<div style="text-align: center; vertical-align:middle;width:220px;">
						<span>订单号：</span><input name="orderNum" id="orderNum" placeholder="模糊搜索订单" type="text"/>					
					</div>
				</td>

				<td>
					<div style="text-align: center; vertical-align:middle;width:220px;">
						<span>手机号：</span><input name="mobile" id="mobile" placeholder="模糊搜索手机号" type="text"/>					
					</div>
				</td>
				<td>
					<div style="text-align: center; vertical-align:middle;width:190px;">
						<span>订单状态：</span>
				     	<select id="orderStatus" class="easyui-combobox" panelHeight="auto" style="width:110px;">   
						   <option value="-1" selected>全部</option>
						   <option value="0">待付款</option>   
						   <option value="1">已支付(待取货)</option>   
						   <option value="2">已取消</option>   
						   <option value="3">已取货(待评论)</option> 
						   <option value="4">取货超时</option> 
						   <option value="5">待退款</option> 
						   <option value="6">交易关闭</option> 
						   <option value="7">完成</option> 
						</select> 
				    </div>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<span>下单时间：</span><input id="startDate" type="text"/>至<input id="endDate" type="text"></input>
				</td>

				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:20px" iconCls="icon-search" onclick="searchOrderList()">查询</a>
<!-- 					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:20px" iconCls="icon-excel" onclick="exportOrderList()">导出</a> -->
				</td>
			</tr>
		</table>
	</div>
	<table id="dg" title="首单优惠活动订单列表"></table>
</body>
</html>
