<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
</head>
<body>
<div class="table" id="Partition"></div>
<div id="tool" >
	<div class="a">
	    <div class="lf">
			活动时间：
			<input type="text" class= "easyui-datetimebox begin" data-options="editable:false" style="width: 150px;height: 25px;" id="rechargeStartTime" name="rechargeStartTime"> 
			 ~
			<input type="text" class= "easyui-datetimebox end" data-options="editable:false" style="width: 150px;height: 25px;" id="rechargeEndTime" name="rechargeEndTime"> 
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" onclick="searchfo()">查询</a>
		</div>
		<div>
			<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-add' plain='true' onclick="addnew()">新增</a>
			<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-edit' plain='true' onclick="eadit()">修改</a>
			<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-remove' plain='true' onclick="detelnode()">删除</a>
		</div>
		
	</div>
</div>
<script type="text/javascript" src="<%=basePath%>views/rechargeactivity/js/rechargeActivity.js"></script>
<script type="text/javascript" src="<%=basePath%>views/rechargeactivity/js/rechargeActivity_dialog.js"></script>
</body>
</html>