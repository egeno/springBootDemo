<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html lang="en" class="bg">
<head>
	<meta charset="UTF-8">
	<title>fenqu_add_dialog</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
</head>
<style>
		#companyId,#type,#nowtype{width:260px;height:30px}
		.easyui-timespinner{height:25px;width:255px;}
		
		</style>

<body >
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
	<form id="addForm" method="post">
	<table style="width: 100%;">
		<tr >
		<th><b class="red">*</b>区域名称：</th>
		<td style="width: 320px;">
			<input type="text"  validType="length[2,16]" class="easyui-textbox easyui-validatebox" id="areaName"  name="areaName" required="required"  style="width:170px;" editable="true"/>
		</td>
	 </tr>	
</table>
</form>
<script type="text/javascript">
</script>
</div>
</div>
</body>
</html>
