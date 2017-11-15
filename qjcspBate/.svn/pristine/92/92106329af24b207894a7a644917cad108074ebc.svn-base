<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>菜品销售统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>views/refund/css/tuikuan.css"> --%>
	<script type="text/javascript" src="<%=basePath%>views/statistics/goodssellreport/js/goodsSellReport.js"></script>
</head>
<body>
<div class="table" id="Partition"></div>
<div id="tool" >
	<div class="action cle">
		<div class="lf">
			加盟商：
			<select class="easyui-combobox" data-options="required:true" id="companyId" name="companyId" style="width: 170px;height: 25px;">
				<c:forEach items="${data}" var="company">
						<option value="${company.companyId}">${company.companyName}</option>
	  	  		</c:forEach>
			</select>
			销售日期：
			<input type="text" class= "easyui-datebox begin" id="sellStartTime" name="sellStartTime" style="width: 120px;height: 25px;" editable="false"> 
			~
			<input type="text" class= "easyui-datebox end" id="sellEndTime" name="sellEndTime" style="width: 120px;height: 25px;" editable="false"> 
			商品名称：
			<input type="text" placeholder="请输入查询的商品名称" class="easyui-textbox" id="foodName" name="foodName" style="width: 150px;height: 25px;">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchfo();">查询</a>			
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel'" style="margin-left:30px" onclick="exportExcel();">导出Excel</a>
		</div>
	</div>
	<table id="dg" title="商品销售统计表"></table>
</div>
</body>
</html>