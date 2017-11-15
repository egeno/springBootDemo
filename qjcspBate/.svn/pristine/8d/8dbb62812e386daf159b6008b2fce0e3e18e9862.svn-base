<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>零售商品日发布统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<script type="text/javascript" src="<%=basePath%>views/statistics/goodsPublishReport/js/goodsPublishReport.js"></script>
  </head>
  <body>
  	<div id="panel"  data-options="border:false">
  		<div id="tb" style="padding:5px">
	  		<table cellpadding="0" cellspacing="0" width="100%">
				<tr>

					<td style="padding-left:20px">
						发布日期：
						<input id="psdate" name="psdate" class="easyui-datebox" value="${psdate }" style="width: 150px;height: 25px;"/>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="getData();">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-excel'" style="margin-left:30px" onclick="exportExcel();">导出</a>

					</td>

				</tr>
			</table>
	  	</div>
		<table id="dg" title="零售商品日发布统计"></table>
	</div>
  </body>
</html>