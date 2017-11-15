<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <title>权限编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/layout/script.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>views/employee/js/employeeMain.js"></script>
  </head>
  <body>
  
   <div id="panel"  data-options="border:false">
		<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" title="" style="height: 82px; overflow: hidden; padding: 5px;">
			<div class="well well-small">
				<span class="badge">提示</span>
				<p>
					为供货员分配设备，请<span class="label-info"><strong>双击用户</strong></span>查看所属设备！
				</p>
			</div>
		</div>
			<div data-options="region:'west',split:true,border:true" style="width:800px;">
				<div id="tbUser" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:2px">
									<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-config" plain="true" onclick="saveEmployeeMachine();">保存设置</a>
							</td>
							<td style="padding-left:2px">
								<input id="searchbox" type="text"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="mm">
						<div name="userName">姓名</div>
						<div name="userMobile">电话</div>
						<div name="userId">编号</div>
				</div>
				<table id="user" title="供货员"></table>
			</div>
			<div data-options="region:'center',border:true">
				<div id="tbRole" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:4px;padding-bottom:4px;">
								<input id="searchbox2" type="text"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="mm2">
						<div name="deviceCode">设备编号</div>
				</div>
				<table id="role" title="设备"></table>
			</div>
		</div>
	</div>
  </body>
  </body>
</html>
