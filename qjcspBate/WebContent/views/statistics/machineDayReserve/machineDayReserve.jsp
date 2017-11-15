<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>用户购买频次统计</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="/layout/script.jsp"%>
<style type="text/css">
.easyui-textbox {
	height: 20px;
	width: 167px;
	line-height: 16px;
	border-radius: 3px 3px 3px 3px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
	transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
}
</style>
<script type="text/javascript"
	src="<%=basePath%>views/statistics/machineDayReserve/js/machineDayReserve.js"></script>
</head>
<body>
	<div id="panel" data-options="border:false">
		<div id="tb" style="padding: 5px">
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td style="padding-left: 20px">
					            预定日期： <input id="psdate"
						name="psdate" class="easyui-datebox" value="${psdate}"
						style="width: 150px; height: 25px;" />
						设备名称： <input id="machinename" name="machinename"
						class="easyui-textbox" /> 
					</select> <a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="getData();">查询</a> <a
						href="javascript:void(0);" class="easyui-linkbutton"
						style="margin-left: 30px;" data-options="iconCls:'icon-excel'"
						onclick="exportExcel();">导出</a>
					</td>
				</tr>
			</table>
		</div>
		<table id="dg" title="设备日预定数量统计"></table>
	</div>
</body>
</html>
