<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>APP用户信息查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/layout/script.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>views/appUser/js/appUserCustomerInfo.js"></script>
  </head>
  <body>
		<div id="tb" style="padding:2px 0">
			<table cellpadding="0" cellspacing="0">
				<tr>
				<td style="padding-left:2px">
						<input id="searchbox" type="text"/>
				</td>
				<td>
					<div>
						<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:30px" iconCls="icon-excel" onclick="exportDaily();">导出</a>
					</div>
				</td>
				</tr>
			</table>
		</div>
		<div id="mm">
				<div name="customerId" id="customerId">用户ID</div>
				<div name="customerName" id="customerName">客户姓名</div>
		</div>
  		<table id="dg" title="APP用户信息维护"></table>
  </body>
</html>
