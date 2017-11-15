<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>评价内容审核管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>views/refund/css/tuikuan.css"> --%>
</head>
<body>
<div class="table" id="Partition"></div>
<div id="tool" >
	<div class="action cle">
		<div class="lf">
			评论提交时间:
			从 <input type="text" class= "easyui-datebox begin" id="evaluateStartTime" name="evaluateStartTime" editable="false"> 到 <input type="text" class= "easyui-datebox end" id="evaluateEndTime" name="evaluateEndTime" editable="false"> 
		</div>
	</div>
	<div class="a">
	<span>审核状态: </span>
				     	<select id="verifyStatus" class="easyui-combobox" panelHeight="auto" style="width:100px;">   
						   <option value="" selected>全部</option>
						   <option value="N">待审核</option>   
						   <option value="Y">已通过</option>    
						   <option value="C">未通过</option>   
						</select> 
		<!-- 审核状态:  全部<input type="radio" id="all" name="verifyStatus" value="" checked="checked">
				待审核<input type="radio" id="wait" name="verifyStatus" value="N">
				已通过<input type="radio" id="pass" name="verifyStatus" value="Y">
				未通过<input type="radio" id="out" name="verifyStatus" value="C"> -->
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain='true' onclick="searchfo()">查询</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-ok" plain='true' onclick="pass()">评论通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain='true' onclick="out()">评论不通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-excel" plain='true' onclick="excel()">导出Excel</a>
	</div>
</div> 
<script type="text/javascript" src="<%=basePath%>views/evaluatereviewed/js/evaluatereviewed.js"></script> 
</body>
</html>