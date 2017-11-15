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

<body onload="aa();">
<form id="outForm" method="post">
<table>
	<tr>
			
		 	<th>预审不通过原因</th>
			<td colspan="3"><textarea class="easyui-textbox" id="businessCheckComment" name="businessCheckComment"  style="width: 150px;height: 80px;resize:none;" ></textarea><em id="word">字数限制：100</em></td>
			<td style="display: none;"><input type="text" id="orderRefundId" name="orderRefundId" ></td>
	</tr>


</table>
</form>
<script type="text/javascript">
	
	$(function(){
		$("#").keyup(
		   function(){
		   var len = $(this).val().length;
		   if(len > 99){
		    $(this).val($(this).val().substring(0,100));
		   }
		   var num = 100 - len;
		   $("#word").text(num);
		   }
		);
	});

</script>
</body>
</html>