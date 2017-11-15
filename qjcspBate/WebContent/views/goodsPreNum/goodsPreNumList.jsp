<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品预订数量统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<script type="text/javascript" src="<%=basePath%>views/goodsPreNum/js/goodsPreNumList.js"></script>
</head>
<body>
<div class="table" id="Partition"></div>
<div id="tool" >
	<div class="action cle">
		<div class="lf">
			选择设备：
			<select class="easyui-combobox" data-options="required:true" id="machineId" name="machineId" style="width: 120px;height: 25px;">
				<option value="">--全部设备--</option>
				<c:forEach items="${machine}" var="machine">
						<option value="${machine.machineId}">${machine.machineName}</option>
	  	  		</c:forEach>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
			选择模型：
			<select class="easyui-combobox" data-options="required:true" id="areaModelId" name="areaModelId" style="width: 120px;height: 25px;">
				<option value="">--全部模型--</option>
				<c:forEach items="${areamodel}" var="areamodel">
						<c:if test="${areaModelId==areamodel.id}">
							<option value="${areamodel.id}" selected="selected">${areamodel.name}</option>
						</c:if>
						<c:if test="${areaModelId!=areamodel.id}">
						<option value="${areamodel.id}">${areamodel.name}</option>
						</c:if>
	  	  		</c:forEach>
			</select>
			&nbsp;&nbsp;&nbsp;&nbsp;
			商品名称：
			<input type="text" placeholder="请输入查询的商品名称" class="easyui-textbox" id="foodName" name="foodName" style="width: 150px;height: 25px;">
			&nbsp;&nbsp;&nbsp;&nbsp;
			开始日期：
			<input type="text" placeholder="请选择开始日期" class= "easyui-datebox begin" id="startTime" name="startTime" style="width: 120px;height: 25px;" editable="false"> 
			&nbsp;&nbsp;
			结束日期：
			<input type="text" placeholder="请选择结束日期" class= "easyui-datebox end" id="endTime" name="endTime" style="width: 120px;height: 25px;" editable="false"> 
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" onclick="searchfo()">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-excel"  onclick="exportExcel();">导出Excel</a>
		</div>
	</div>
	<table id="dg" title="商品预订数量统计表"></table>
</div>
</body>
</html>