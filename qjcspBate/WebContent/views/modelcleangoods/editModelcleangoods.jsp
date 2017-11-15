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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/machineArea/css/fenqu.css">
</head>
<style>
	#companyId,#type,#nowtype{width:260px;height:30px}
	.easyui-timespinner{height:25px;width:255px;}
</style>
<body>
<script type="text/javascript">

// $("#editgoodId").combobox({
// 	onBeforeLoad:function(){
// 		alert(1);
// 	},
// 	onSelect:function(){
// 		return false;
// 	}
	
// });


</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow:;padding: 10px;">
	<form id="editForm" method="post">
	<table style="width: 100%;">
	    <tr style="display:none">
	    	<td class="tt">清货id</td><td><input type="text" name="cleanId" id="editcleanId"></td>
	    </tr>
		<tr>
			<th style="width: 35%;"><b class="red">*</b>商户</th>
			<td style="width: 320px;">
				<select class="easyui-combobox toolbtn" name="companyId" id="editcompanyId" required="required">
					<c:forEach items="${dataCompany}" var="company">
			  	 		<option value="${company.companyId}">${company.companyName}</option>
		  	  		</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><b class="red">*</b>模型</th>
			<td>
				<select  class="easyui-combobox toolbtn" name="areaModelId" id="editareaModelId" required="required" >
					<c:forEach items="${dataModel}" var="areaModel">
			  	 		<option value="${areaModel.areaModelId}">${areaModel.areaModelName}</option>
		  	  		</c:forEach>
				</select>
			</td>
		</tr>
		<tr id="day">
			<th><b class="red">*</b>选择周期</th>
			<td>
				<select  class="easyui-combobox toolbtn" name="type" id="edittype" required="required">
					<option value="allDay">每天</option>
					<!-- <option value="weekDay">每周</option> -->
				</select>
				<input name="type" type="hidden" id="hiddens"/>
			</td>
		</tr>
		<tr id="editdayDateStr" style="display: none">
			<td colspan = "2"  style="font-size: 13px;">
				<span style="margin-left: 20px;">
					<input name='dateStr' type='checkbox' value='周一'/> 周一
								</span>
								<span style="margin-left: 10px;">
									<input name='dateStr' type='checkbox' value='周二'/> 周二 
								</span>
								<span style="margin-left: 10px;">
									<input name='dateStr' type='checkbox' value='周三'/> 周三
								</span>
								<span style="margin-left: 10px;">
									<input name='dateStr' type='checkbox' value='周四'/> 周四
								</span>
								<span style="margin-left: 10px;">
									<input name='dateStr' type='checkbox' value='周五'/> 周五
								</span>
								<span style="margin-left: 10px;">
									<input name='dateStr' type='checkbox' value='周六'/> 周六
								</span>
								<span style="margin-left: 10px;">
									<input name='dateStr' type='checkbox' value='周日'/> 周日
								</span>
			</td>
		</tr>
		<!-- <tr>
		   <th><b class="red">*</b>供货截止时间</th><td><input class="easyui-timespinner easyui-validatebox" type="text" name="supplyEndTimeStr"  required="required" id="editsupplyEndTimeStr"></td>
		</tr>
		<tr>
			 <th><b class="red">*</b>清货开始时间</th><td><input class="easyui-timespinner easyui-validatebox" type="text" name="cleanStartTimeStr"  required="required" id="editcleanStartTimeStr"></td>
		</tr>
		 -->
		<tr><th><b class="red">*</b>预定取餐开始时间</th><td><input type="text" readonly="readonly" name="reserveTakeStartTimeStr" required="required" id="reserveTakeStartTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>预定取餐截止时间</th><td><input  type="text" readonly="readonly" name="reserveTakeEndTimeStr"  required="required" id="reserveTakeEndTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>预定截止时间</th><td><input type="text" readonly="readonly" name="reserveEndTimeStr"  required="required" id="reserveEndTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>预定清货开始时间</th><td><input  type="text" readonly="readonly"  name="reserveCleanStartTimeStr"  required="required" id="reserveCleanStartTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>预定补货开始时间</th><td><input  type="text" readonly="readonly"  name="reserveReplenishmentStartTimeStr"  required="required" id="reserveReplenishmentStartTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>预定补货截止时间</th><td><input  type="text" readonly="readonly"  name="reserveSupplyEndTimeStr"  required="required" id="reserveSupplyEndTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>零售取餐开始时间</th><td><input  type="text" readonly="readonly"  name="retailTakeStartTimeStr" required="required" id="retailTakeStartTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>零售取餐截止时间</th><td><input  type="text" readonly="readonly"  name="retailTakeEndTimeStr" required="required" id="retailTakeEndTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>零售清货开始时间</th><td><input  type="text" readonly="readonly"  name="retailCleanStartTimeStr"  required="required" id="retailCleanStartTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>零售补货开始时间</th><td><input  type="text" readonly="readonly"  name="retailReplenishmentStartTimeStr"  required="required" id="retailReplenishmentStartTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th><b class="red">*</b>零售补货截止时间</th><td><input  type="text" readonly="readonly"  name="retailSupplyEndTimeStr" required="required" id="retailSupplyEndTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th>预定清货提醒</th><td><input  type="text" readonly="readonly"  name="reserveClearancePromptTimeStr" id="reserveClearancePromptTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th>预定补货提示时间</th><td><input  type="text" readonly="readonly"  name="reserveReplenishmentPromptTimeStr" id="reserveReplenishmentPromptTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th>预定补货预警时间</th><td><input  type="text" readonly="readonly"  name="reserveReplenishmentWarningTimeStr" id="reserveReplenishmentWarningTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th>零售清货提醒</th><td><input  type="text" readonly="readonly"  name="retailClearancePromptTimeStr" id="retailClearancePromptTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th>零售补货提示时间</th><td><input  type="text" readonly="readonly"  name="retailReplenishmentPromptTimeStr" id="retailReplenishmentPromptTime" class="easyui-textbox easyui-validatebox"></td></tr>
		<tr><th>零售补货预警时间</th><td><input  type="text" readonly="readonly"  name="retailReplenishmentWarningTimeStr" id="retailReplenishmentWarningTime" class="easyui-textbox easyui-validatebox"></td></tr>
	</table>
	</form>
	</div>
</div>
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
</body>
</html>
