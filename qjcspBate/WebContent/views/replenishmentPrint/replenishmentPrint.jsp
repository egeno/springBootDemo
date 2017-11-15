<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>补货打印单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="/layout/script.jsp"%>
<script type="text/javascript" src="<%=basePath%>views/replenishmentPrint/js/replenishmentPrint.js"></script>
</head>
<body>
	<div id="tb" style="padding: 10px">
		<table cellpadding="5" cellspacing="0">
		<tr>
		<td>
		设备：
			<select class="easyui-combobox" data-options="required:true" id="machineId" name="machineId" style="width: 170px;height: 25px;">
				<option value="">--全部设备--</option>
				<c:forEach items="${machine}" var="machine">
						<option value="${machine.machineId}">${machine.machineName}</option>
	  	  		</c:forEach>
			</select>
			</td>
			<td>
			模型：
			<select class="easyui-combobox" data-options="required:true" id="areaModelId" name="areaModelId" style="width: 170px;height: 25px;">
				<option value="">--全部模型--</option>
				<c:forEach items="${areamodel}" var="areamodel">
						<option value="${areamodel.id}">${areamodel.name}</option>
	  	  		</c:forEach>
			</select>
			</td>
			<td>
			日期：
			<input type="text" class= "easyui-datebox" id="temporaryDate" name="temporaryDate" style="width: 120px;height: 25px;" editable="false"> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" onclick="searchfo()">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-excel" style="margin-left: 30px;" onclick="exportExcel();">导出Excel</a>	
		</td>
		</tr>
		</table>
	</div>
	<table id="dg" title="订单列表"></table>
</body>
</html>