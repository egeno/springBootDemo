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
<body>
<script type="text/javascript">




</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
	 <form id="editForm" method="post">
		<table style="width: 100%;">
			<tr >
				<th><b class="red">*</b>活动开始时间：</th>
				<td style="width: 320px;">
					<input type="text" class= "easyui-datetimebox begin" id="editstartTimeStr" name="startTimeStr" required="required" style="width:170px;" editable="false"/>
				</td>
	 		</tr>
			<tr>
				<th><b class="red">*</b>活动结束时间:</th>
				<td>
					<input type="text" class= "easyui-datetimebox end" id="editendTimeStr" name="endTimeStr" required="required"  style="width:170px;" editable="false"/>
				</td>
			</tr>
	
			<tr>
				<th><b class="red">*</b>抵扣卷:</th>
				<td>
				<input type="text" class= "easyui-textbox easyui-validatebox" id="editdiscountMoney" name="discountMoney" required="required" validType='discountMoney'/>
				</td>
			</tr>
			<tr>
				<th><b class="red">*</b>活动有效标志</th>
				<td>
					<select   class="easyui-combobox toolbtn" name="effectSymbol" id="editeffectSymbol" required="required" style="width:170px;">
		  	 			<option value="1">有效</option>
		  	 			<option value="0">无效</option>
					</select>
				</td>
			
			</tr>
	</table>
</form>
<!-- <script type="text/javascript" src="../easyui/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script> -->
<%-- <script type="text/javascript" src="<%=basePath%>views/machineArea/js/fenqu_dialog.js"></script>
<script type="text/javascript" src="<%=basePath%>views/machineArea/easyui/jquery.min.js"></script> --%>
<script type="text/javascript">
	
	$(function(){

		$("#editcompanyId").combobox({
			editable:false
		});
		
		
	});
// 	$('#editcompanyId').combobox({   
// 	    onChange : function(){
// // 	    	alert($("#companyId").combobox("getValue"));
// 	    	$.ajax({
// 				url:"modelcleangoods/getAreaModel",
// 				data:{companyId:$("#editcompanyId").combobox("getValue")},
// 				dataType:'json',
// 				type:'post',
// 				success:function(data){
// 					$("#editareaModelId").html("");
// 					if(data.length !=0){
// 						for(var i=0;i<data.length;i++){
//  							$("<option value='" + data[i].areaModelId + "'>" + data[i].areaModelName + "</option>").appendTo($("#editareaModelId"));
// 						}
// 					}
					
// 				}
// 			});
	    	
// 	    } 
// 	}); 
// 	function editchangeType(){
// 		if($("#edittype").val()=='allDay'){
// 			$("#editdayDateStr").hide();
// 		}else {
// 			$("#editdayDateStr").show();
// 		}
// 	}
   
   
</script>
</div>
</div>
</body>
</html>
