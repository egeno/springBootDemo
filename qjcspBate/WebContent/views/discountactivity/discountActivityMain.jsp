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
		<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-add' plain='true' onclick="addnew()">新增</a>
		<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-edit' plain='true' onclick="eadit()">修改</a>
	</div>
</div>
<script type="text/javascript" src="<%=basePath%>views/discountactivity/js/discountActivity.js"></script>
<script type="text/javascript" src="<%=basePath%>views/discountactivity/js/discountActivity_dialog.js"></script>
</body>
</html>