<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	
    <title>商品信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	
	<script type="text/javascript" src="<%=basePath%>views/goodsInfo/js/listGoodsInfoMain.js"></script>
  </head>
  <body>
  	<!-- <div class="well well-small" style="margin-left: 5px;margin-top: 5px">
		<span class="badge">提示</span>
		<p>
			在此你可以对<span class="label-info"><strong>商品信息</strong></span>进行编辑!
		</p>
	</div> -->
    <div id="tb" style="padding:10px;height:auto">
		<div style="margin-bottom:5px">
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>

				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit();">编辑</a>

				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="disabled();">删除</a>
		
		        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editgoods();">编辑菜品食材</a>
				
				&nbsp;&nbsp;&nbsp;商品名称：
			<input type="text" placeholder="请输入查询的商品名称" class="easyui-textbox" id="foodName" name="foodName" style="width: 150px;height: 25px;">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" onclick="searchfo()">查询</a>
		</div>
	</div>
  		<table id="dg" title="商品信息"></table>
  </body>
</html>
