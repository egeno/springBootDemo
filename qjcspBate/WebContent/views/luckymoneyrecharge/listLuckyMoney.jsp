<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>红包充值</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/machineArea/css/fenqu.css"> --%>
	<script type="text/javascript">
		var path="<%=request.getContextPath()%>";
    </script>
</head>
<body >
  <div id="tb" style="padding: 10px"> 
	<table cellpadding="5" cellspacing="0">
	   <tr>
	      <td>
		      <span>手机号码：</span> <input id="mobile" name="mobile" type="text" >
	      </td>
	      <td>
	        <a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:20px" iconCls="icon-search" onclick="searchOrderList()">查询</a>
	      </td>
	   </tr>
	</table>
	 <table> 
	   <tr>
	      <td>
	        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addlucky();">添加</a>
	      </td>
	   </tr>
	</table>
   </div>
   <table id="dg" title="红包充值"></table>
  	
<script type="text/javascript" src="<%=basePath%>views/luckymoneyrecharge/js/luckymoney.js" ></script>
</body>
</html>