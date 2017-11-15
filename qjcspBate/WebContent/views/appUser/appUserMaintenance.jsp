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
    <title>APP用户积分维护</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/layout/script.jsp"></jsp:include>
	<style type="text/css">
		.easyui-textbox{
			height: 20px;
			width: 167px;
			line-height: 16px;
		    border-radius: 3px 3px 3px 3px;
		    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
		    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
		}
	</style>
	<script type="text/javascript" src="<%=basePath%>views/appUser/js/appUserMaintenance.js"></script>
  </head>
  <body>
	<div id="panel"  data-options="border:false">
		<div id="tb" style="padding:5px">
	  		<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td style="padding-left:20px;width: 250px;" nowrap="nowrap">
						注册时间：
						<input class="easyui-datetimebox begin" data-options="editable:false" style="width: 170px;height: 25px;" id="startDate" name="startDate">
						 ~
						<input class="easyui-datetimebox end" data-options="editable:false" style="width: 170px;height: 25px;" id="endDate" name="endDate">
						&nbsp;&nbsp;&nbsp;&nbsp;
						手机号码： <input id="customerMobile" validtype="mobile" name="customerMobile" type="text" class="easyui-textbox">
					</td>
				</tr>
				<tr style="height: 32px;vertical-align: bottom;">
					<td style="padding-left:20px;width: 250px;" nowrap="nowrap">
						学校名称： <input id="universityName" name="universityName" validType="length[1,16]" maxLength="16" type="text" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;&nbsp;
						客户昵称： <input id="customerName" name="customerName" validType="length[2,16]" maxLength="16" type="text" class="easyui-textbox">
						&nbsp;&nbsp;
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="getInfo()">查询</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" class="easyui-linkbutton" iconCls='icon-edit' onclick="updateIntegral();">修改积分</a><br/>
					</td>
				</tr>
			</table>
	  	</div>
	  	<table id="dg" title="APP用户积分维护"></table>
	</div>
 </body>
</html>

