<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>商品预订零售数量统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<script type="text/javascript" src="<%=basePath%>views/goodsTemporary/js/listGoodsTemporaryMain.js"></script>
</head>
<body>
<div class="table" id="Partition"></div>
<div id="tool" >
	<div class="action cle">
		<div class="lf">
			设备：
			<select class="easyui-combobox" data-options="required:true" id="machineId" name="machineId" style="width: 170px;height: 25px;">
				<option value="">--全部设备--</option>
				<c:forEach items="${machine}" var="machine">
						<option value="${machine.machineId}">${machine.machineName}</option>
	  	  		</c:forEach>
			</select>
			模型：
			<select class="easyui-combobox" data-options="required:true" id="areaModelId" name="areaModelId" style="width: 170px;height: 25px;">
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
			日期：
			<input type="text" class= "easyui-datebox" id="temporaryDate" name="temporaryDate" style="width: 120px;height: 25px;" editable="false" value="${selectTime}"> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" onclick="searchfo()">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-excel" style="margin-left: 30px;" onclick="exportExcel();">导出Excel</a>
			<a href="goodsRetail/goodsRetailMain" class="easyui-linkbutton" style="margin-left: 30px;" onclick="searchfo()">返回</a>
		</div>
	</div>
	<table id="dg" title="商品预订零售数量统计表"></table>
</div>
</body>
</html>