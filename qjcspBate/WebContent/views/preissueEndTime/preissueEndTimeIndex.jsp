<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>预定截止时间</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<script type="text/javascript" src="<%=basePath%>views/preissueEndTime/js/preissueEndTime.js"></script>
  </head>
  <body>
  	<div id="panel"  data-options="border:false">
  		<div id="tb" style="padding:5px">
	  		<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td style="padding-left:20px">
						<c:choose>
					    <c:when test="${num>0}">										
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'"  onclick="edit();">修改</a>
						<!-- <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" style="margin-left:30px" onclick="remove();">删除</a> -->
						</c:when>
						<c:otherwise>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addnew();">添加</a>
						</c:otherwise>
						</c:choose>
					</td>

				</tr>
			</table>
	  	</div>
		<table id="dg" title="预定截止时间"></table>
	</div>
  </body>
</html>