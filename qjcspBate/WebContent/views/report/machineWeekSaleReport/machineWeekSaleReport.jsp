<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>柜子周菜品销售统计</title>
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
	src="<%=basePath%>views/report/machineWeekSaleReport/js/machineWeekSaleReport.js"></script>
</head>
<body>
	<div id="panel" data-options="border:false">
		<div id="tb" style="padding: 5px">
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td style="padding-left: 20px">日期：<input id="psdate"
						name="psdate" class="easyui-datebox" value="${psdate}"
						style="width: 150px; height: 25px;" />&nbsp;周一：<input id="ssdate" disabled="disabled"
						name="ssdate" class="easyui-datebox" value="${ssdate}"
						style="width: 150px; height: 25px;" />
						&nbsp;周日：<input id="esdate" disabled="disabled"
						name="esdate" class="easyui-datebox" value="${esdate}"
						style="width: 150px; height: 25px;" /> &nbsp;&nbsp;
						设备名称： <input id="machineName" name="machineName"
						class="easyui-textbox" /> &nbsp;&nbsp;&nbsp;&nbsp;<%-- 公司名称：<select
						id="companyId" name="companyId" class="easyui-combobox"
						data-options="editable:false" style="width: 213px; height: 25px;">
							<c:choose>
								<c:when test="${companys != null }">
									<option value="-1">全部</option>
									<c:forEach items="${companys}" var="item">
										<option value="${item.companyId}">${item.companyName}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">全部</option>
								</c:otherwise>
							</c:choose>
					</select>--%> <a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="getData();">查询</a> <a
						href="javascript:void(0);" class="easyui-linkbutton"
						style="margin-left: 30px;" data-options="iconCls:'icon-excel'"
						onclick="exportExcel();">导出</a>
					</td>
				</tr>
				<!-- <tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;男/女生楼：<select
						id="machineSex" name="machineSex" class="easyui-combobox"
						data-options="editable:false" style="width: 150px; height: 25px;">
							<option value="">全部</option>
							<option value="男">男生</option>
							<option value="女">女生</option>
						</select> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否投入使用：<select
						id="useOrNot" name="useOrNot" class="easyui-combobox"
						data-options="editable:false" style="width: 100px; height: 25px;">
							<option value="">是否投入使用</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>						
					</td>
				</tr> -->
			</table>
		</div>
		<table id="dg" title="柜子周菜品销售统计"></table>
	</div>
</body>
</html>
