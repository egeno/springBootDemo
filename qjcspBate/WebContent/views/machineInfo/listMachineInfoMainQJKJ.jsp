<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	
    <title>设备信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	
	<script type="text/javascript" src="<%=basePath%>views/machineInfo/js/listMachineInfoMainQJKJ.js"></script>
  </head>
  <body>
	<div id="tb" style="padding:10px;height:auto">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td style="padding-left:2px">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
				</td>
				<td style="padding-left:2px">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit();">编辑</a>
				</td>
				<td style="padding-left:2px">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="disabled();">删除</a>
				</td>
				<td style="padding-left:2px">
					<input id="searchbox" type="text"/>
				</td>
			</tr>
		</table>
	</div>
	<div id="search">
			<div name="companyName">公司名称</div>
	</div>
  		<table id="dg" title="设备信息"></table>
  </body>
</html>
