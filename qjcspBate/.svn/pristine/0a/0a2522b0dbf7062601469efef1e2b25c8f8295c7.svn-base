<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>补货清货记录列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
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
	<script type="text/javascript" src="<%=basePath%>views/operateRecording/js/operateRecording.js"></script>
</head>
<body>
	<div id="panel"  data-options="border:false">
  		<div id="tb" style="padding:5px">
	  		<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td style="padding-left:20px;width: 250px;" nowrap="nowrap">
						开始时间：
						<input class="easyui-datetimebox begin" data-options="editable:false" style="width: 170px;height: 25px;" id="startTime" name="startTime">
					</td>
					<td nowrap="nowrap">
						结束时间：
						<input class="easyui-datetimebox end" data-options="editable:false" style="width: 170px;height: 25px;" id="endTime" name="endTime">
					</td>
				</tr>
				<tr style="height: 32px;vertical-align: bottom;">
					<td style="padding-left:20px;width: 250px;" nowrap="nowrap">
						操作类型：
						<select id="operateType" name="operateType" class="easyui-combobox" data-options="editable:false" style="width:80px;height: 25px;">
							<option value="-1">全部</option>
							<option value="1">补货</option>
							<option value="2">清货</option>
						</select>
						<!-- &nbsp;
						<select id="operateMode" name="operateMode" class="easyui-combobox" data-options="editable:false" style="width:80px;height: 25px;">
							<option value="-1">全部</option>
							<option value="1">开窗</option>
							<option value="2">开门</option>
						</select>
					</td> -->
					<td nowrap="nowrap">
						选择设备：
						<select name="machineId" id="machineId" class="easyui-combobox" data-options="editable:false" style="width: 170px;height: 25px;">
							<option value="-1">全部</option>
							<c:if test="${not empty machine}">
							<c:forEach items="${machine}" var="status">
								<option value="${status.machineId}">${status.machineName}</option>
							</c:forEach>
							</c:if>
						</select>
					</td>
				</tr>
				<tr style="height: 32px;vertical-align: bottom;">
					<td style="padding-left:20px;width: 250px;" nowrap="nowrap">
						商品名称： <input id="goodsName" name="goodsName" type="text" class="easyui-textbox">
					</td>
					<td nowrap="nowrap">
						操作人员： <input id="playName" name="playName" type="text" class="easyui-textbox">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchfo()">查询</a>
					</td>
				</tr>
			</table>
	  	</div>
		<table id="dg" title="补货清货记录列表"></table>
	</div>
</body>
</html>