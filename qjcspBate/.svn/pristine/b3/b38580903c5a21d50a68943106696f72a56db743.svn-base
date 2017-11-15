<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setBufferSize(402800);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>余额充值查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="/layout/script.jsp"%>
<style type="text/css">
.easyui-textbox {
	height: 20px;
	width: 167px;
	line-height: 16px;
	border-radius: 3px 3px 3px 3px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>views/pick/css/tuikuan.css">
</head>
<body>
	<div class="table" id="Partition"></div>
	<div id="tool" style="height: 55px;">

		<div class="action cle" style="margin-top: 5px;">
			<div class="lf">
				订单编号：<input type="text" class="easyui-textbox" id="orderNum"
					name="orderNum">
			</div>
			<div class="lf">
				用户名：<input type="text" class="easyui-textbox" id="userName"
					name="userName">
			</div>
			<div class="lf">
				手机号：<input type="text" class="easyui-textbox" id="mobile"
					name="mobile">
			</div>
			
		</div>
		<div class="action cle" style="margin-top: 5px;">
			<div class="lf">
				支付类型： <select id="modeNum" name="modeNum" class="easyui-combobox"
					data-options="editable:false" style="width: 170px; height: 25px;">
					<option value="">全部</option>
					<option value="0">闪付</option>
					<option value="1">支付宝</option>
					<option value="2">微信</option>
				</select>
			</div>
			<div class="lf">
				订单状态： <select id="orderStatus" name="orderStatus"
					class="easyui-combobox" data-options="editable:false"
					style="width: 170px; height: 25px;">
					<option value="">全部</option>
					<option value="0">待付款</option>
					<option value="7">已支付</option>
					<option value="2">已取消</option>
				</select>
			</div>
				<div>
					<a href="javascript:void(0);"
					class="easyui-linkbutton" iconcls="icon-search"
					onclick="searchfo()" style="margin-left: 30px">查询</a>
			</div>
			
			
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>views/recharge/js/recharge.js"></script>
</body>
</html>