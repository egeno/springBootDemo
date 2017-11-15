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
<form id="confirmForm" method="post">
<table>
	<tr>
			
		 	<th>财务确认退款备注</th>
			<td colspan="3"><textarea class="easyui-textbox" id="financeCheckComment" name="financeCheckComment"  style="width: 150px;height: 80px;resize:none;" ></textarea><span id="word">字数限制：100</span></td>
			<td style="display: none;"><input type="text" id="orderRefundId" name="orderRefundId" ></td>
	</tr>


</table>
</form>
<!-- <script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script> -->
<%-- <script type="text/javascript" src="<%=basePath%>views/machineArea/easyui/jquery.easyui.min.js"></script> --%>
<script type="text/javascript">
	
	$(function(){
		$("#areaMemo").keyup(
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
// 	  $.ajax({
// 		  url:"machineArea/getThisModelliveHowMuchCell",//判断这个模型下的这个菜品是否还有菜
// 		  type : 'POST',
// 		  dataType : 'json',
// 		  data :{"areaModelId":$("#areaModelId").val()},
// 		  success:function(data){
// 			  $("#hiddenAreaGoodNum").val(data.count);
// 		  }
		  
// 	  })
</script>
</body>
</html>