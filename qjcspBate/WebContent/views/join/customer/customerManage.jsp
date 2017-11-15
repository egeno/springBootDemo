<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>客户申请地址</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/layout/script.jsp"></jsp:include>
    <script type="text/javascript" src="<%=basePath%>views/join/customer/js/join.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>views/join/css/join.css"> 
</head>
<body>
     <!-- <div class="table" id="Partition"></div> -->
     <div id="tb" style="padding:10px;height:auto">
        <div class="lf">客户名称：<input type="text" class="name easyui-textbox" id="name" name="name" style="width: 213px;height: 25px;"> &nbsp;&nbsp;&nbsp;&nbsp;</div>
		<div class="lf">客户手机号：<input type="text" class="tel_phone easyui-textbox" id="telPhone" name="telPhone" style="width: 213px;height: 25px;"></div>
		<div style="margin-bottom:5px">
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" onclick="searchfo()">查询</a>	
			<!-- <a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:20px" iconCls="icon-excel" onclick="exportList()">导出</a>			 -->
		</div>
	</div>
  	<table id="dg" title="客户申请地址"></table>
 
</body>
</html>