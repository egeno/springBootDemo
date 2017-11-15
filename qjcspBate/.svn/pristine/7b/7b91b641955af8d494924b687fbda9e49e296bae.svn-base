<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>订单管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="/layout/script.jsp"%>
<script type="text/javascript" src="<%=basePath%>views/recharge/js/listRechargeForOrderMain.js"></script>
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
					<div style="text-align: center; vertical-align:middle;width:230px;">
						<span>充值订单号：</span><input name="orderNum" id="orderNum" placeholder="模糊搜索订单" type="text"/>					
					</div>
				</td>
				<td>
				<div style="text-align: center; vertical-align:middle;width:190px;">
						<span>支付方式：</span>
				     	<select id="modeNum" name="modeNum" class="easyui-combobox" panelHeight="auto" style="width:100px;">   
						   <option value="-1" selected>全部</option>
						   <option value="0">闪付</option>   
						   <option value="1">支付宝</option>    
						   <option value="2">微信支付</option>   
						</select> 
					</div>
					</td>
				<td>
					<div style="text-align: center; vertical-align:middle;width:230px;">
						<span>用户名：</span><input name="userName" id="userName" placeholder="模糊搜索用户" type="text"/>					
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
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:20px" iconCls="icon-search" onclick="searchOrderList()">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:20px" iconCls="icon-excel" onclick="exportOrderList()">导出</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:20px" iconCls="icon-search" onclick="updateList()">更新数据</a>
				</td>
			</tr>
		</table>
	</div>
	<table id="dg" title="订单列表"></table>
</body>
</html>
