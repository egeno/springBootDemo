<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>故障报表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/alarm/css/tuikuan.css">
	<script type="text/javascript" src="<%=basePath%>views/alarm/js/alarmReport.js"></script>
</head>
<body>
<div class="table" id="Partition"></div>
<div id="tool" >
	<div class="action cle">
		<div class="lf">
			故障取餐类型：
			<select id="deviceFaultSymbol" class="easyui-combobox" panelHeight="auto" style="width:100px;">   
						   <option value="-1" selected>全部</option>
						   <option value="0">客户收到</option>   
						   <option value="1">客户未收</option>    
						</select> 
		</div>
		<div>
			故障日期：
			<input type="text" class= "easyui-datebox begin" id="sellStartTime" name="sellStartTime" style="width: 120px;height: 25px;" editable="false"> 
			~
			<input type="text" class= "easyui-datebox end" id="sellEndTime" name="sellEndTime" style="width: 120px;height: 25px;" editable="false"> 
			商户名称：
			<input type="text" placeholder="请输入查询的商户名称" class="easyui-textbox" id="shopName" name="shopName" style="width: 140px;height: 25px;">
			设备名称：
			<input type="text" placeholder="请输入查询的设备名称" class="easyui-textbox" id="machineName" name="machineName" style="width: 140px;height: 25px;">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" onclick="searchfo()">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-excel" onclick="exportExcel();">导出Excel</a>
		</div>
	</div>
	<table id="dg" title="故障统计表"></table>
</div>
</body>
</html>