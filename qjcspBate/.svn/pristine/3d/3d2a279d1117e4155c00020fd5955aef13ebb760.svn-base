<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>分区模板维护</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/areaModel/css/fenqu.css">
</head>
<body>
<div class="fenqumoblie">
	<!--左边数据表-->
	<div region="west" split="true" style="width:864px;">
		<table id="mobliedata"></table>
		<div id="tool" >
			<div class="cle">
				<div class="lf toolbtn">分区模型名称 <input type="text" class="Partitionname" ></div>
				<div class="lf toolbtn">适用层数 <input type="text" class="Partitionnumber"></div>
				<div class="lf toolbtn">适用列数 <input type="text" class="Partitioncolumn"></div>
			</div>
			<div class="cle cc">
				<div class="lf toolbtn">适用类型
				<select id="fittypes" class="toolbtn" name="fittypes" data-options="required:true">
					<option value="-1">全部</option>
					<option value="0">制冷</option>
					<option value="1">加热</option>
					<option value="2">冷热一体</option>
				</select>
				</div>
				<div class="lf toolbtn">设备
				<select id="equipment" class=" toolbtn" name="equipment" data-options="required:true">
					<option value="-1">全部</option>
					<c:if test="${machine!=null}">
					<c:forEach items="${machine}" var="status">
						<option value="${status.machineId}">${status.machineName}</option>
					</c:forEach>
					</c:if>
				</select>
				</div>
				<div class="button lf">
					<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls="icon-search" plain='true' onclick="searchfo()">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls="icon-add" plain='true' onclick="addnew()">新增</a>
					<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls="icon-edit" plain='true' onclick="eadit()">修改</a>
					<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls="icon-save" plain='true' onclick="save()">保存</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain='true' onclick="detelnode()">删除</a>
				</div>
			</div>
		</div>
	</div>
	<!--右边数据表-->
	<div region="center" split="true" style="width:35%;" class='check'>
		<table id="CheckPartition"></table>
	</div>
</div>

<div class="addcontent"></div>
<div class="editcontent"></div>
<div class="tablemenu"></div>
<script type="text/javascript" src="<%=basePath%>views/areaModel/js/fenqumoblie.js"></script>
<script type="text/javascript" src="<%=basePath%>views/areaModel/js/fenqumoblie_dialog.js"></script>
</body>
</html>