<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>退款——运营人员预审</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/refund/css/tuikuan.css">
</head>
<body>
<div class="table" id="Partition"></div>
<div id="tool" >
	<div class="action cle">
		<div class="lf">商户:
			<select class="shop" name="shop" data-options="required:true" id="companyId">
				<c:forEach items="${data}" var="company">
	  					
						<option value="${company.companyId}">${company.companyName}</option>
		  				
	  	  		</c:forEach>
			</select>
		</div>
		<div class="lf">订单号:<input type="text" class="OrderNumber" id="orderNum" name="orderNum"></div>
		<div class="lf">
			退款申请时间:
			从 <input type="text" class= "easyui-datebox begin" id="refundStartTime" name="refundStartTime" editable="false"> 到 <input type="text" class= "easyui-datebox end" id="refundEndTime" name="refundEndTime" editable="false"> 
		</div>
	</div>
	<div class="a">
		审核状态:  全部<input type="radio" id="all" name="verifyStatus" value="" checked="checked">
				待审核<input type="radio" id="wait" name="verifyStatus" value="5">
				已通过<input type="radio" id="pass" name="verifyStatus" value="1">
				未通过<input type="radio" id="out" name="verifyStatus" value="-2">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" plain='true' onclick="searchfo()">查询</a>
	</div>
	<div class="a">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-ok" plain='true' onclick="pass()">预审通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-cancel" plain='true' onclick="out()">预审不通过</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-excel" plain='true' onclick="excel()">导出Excel</a>
	</div>
</div> 
<script type="text/javascript" src="<%=basePath%>views/refund/js/business.js"></script>
<script type="text/javascript" src="<%=basePath%>views/refund/js/business_dialog.js"></script> 
<%-- <script type="text/javascript" src="<%=basePath%>views/refund/js/finance.js"></script>
<script type="text/javascript" src="<%=basePath%>views/refund/js/finance_dialog.js"></script> --%>
</body>
</html>