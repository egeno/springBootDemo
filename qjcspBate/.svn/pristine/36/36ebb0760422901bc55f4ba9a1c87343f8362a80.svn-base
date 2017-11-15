<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>每日柜子供销统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<script type="text/javascript" src="<%=basePath%>views/advertisement/js/advertisement.js"></script>
  </head>
  <body>
  	<div id="panel"  data-options="border:false">
  		<div id="tb" style="padding:5px">
	  		<table cellpadding="0" cellspacing="0" width="100%">
				<tr style="margin-top:30px">
					<td style="padding-left:20px">
						图片用途：
						<select id="picUsed" name="picUsed" class="toolbtn"  data-options="required:true">
							<option value="1">Banner条图片</option>
						</select>			
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图片名称:
						<input id="picName" name="machineName"  style="width: 150px;"/>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" style="margin-left:30px" onclick="getData();">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" style="margin-left:30px" onclick="add();">添加</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" style="margin-left:30px" onclick="edit();">修改</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" style="margin-left:30px" onclick="deleterow();">删除</a>
					</td>
				<tr>
					<td>
						
					</td>
			</table>
	  	</div>
		<table id="dg" title="广告图片列表"></table>
	</div>
  </body>
</html>
