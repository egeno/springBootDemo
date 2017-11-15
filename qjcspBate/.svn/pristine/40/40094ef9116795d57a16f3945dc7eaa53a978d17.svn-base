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
	<!-- 验证js -->
	<script type="text/javascript" src="<%=basePath%>views/modelcleangoods/js/modelcleangoods_dialog.js"></script>
	
</head>
<style>
	#companyId,#type,#nowtype{width:260px;height:30px}
	.easyui-timespinner{height:25px;width:255px;}
</style>

<body >
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow:;padding: 10px;">
	<form id="addForm" method="post">
	<table style="width: 100%;">
		<tr >
			<th style="width: 35%;"><b class="red">*</b>商户</th>
			<td>
				<select class="easyui-combobox toolbtn" name="companyId" id="companyId" required="required" style="width:170px;">
					<c:forEach items="${dataCompany}" var="company">
			  	 		<option value="${company.companyId}">${company.companyName}</option>
		  	  		</c:forEach>
				</select>
			</td>
	 	</tr>
		<tr>
		<th><b class="red">*</b>模型</th>
		<td>
			<select   class="easyui-combobox toolbtn" name="areaModelId" id="addareaModelId" required="required" style="width:170px;">
				<c:forEach items="${dataModel}" var="areaModel">
		  	 		<option value="${areaModel.areaModelId}">${areaModel.areaModelName}</option>
	  	  		</c:forEach>
			</select>
			<div class="areaModeTypeClass">
			<c:forEach items="${dataModel}" var="areaModel">
				<input type="hidden" name="${areaModel.areaModelId}" id="${areaModel.areaModelId}" value="${areaModel.areaModelType}">
	  	  	</c:forEach>
	  	  	</div>
		</td>
		</tr>
		<tr id="day">
			<th><b class="red">*</b>选择周期</th>
			<td>
				<select class="easyui-combobox toolbtn" name="type" id="type" style="width:170px;">
					<option value="allDay">每天</option>
					<!-- <option value="weekDay">每周</option> -->
				</select>
			</td>
		</tr>
		
		<tr id="dayDateStr" style="display: none">
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
		<!-- 
		<tr>
		   <th><b class="red">*</b>供货截止时间</th>
		   <td><input class="easyui-timespinner easyui-validatebox" type="text" name="supplyEndTimeStr"  required="required" missingMessage="输入项为必输项" id="supplyEndTimeStr" style="width:170px;"></td>
		</tr>
		<tr>
		  <th><b class="red">*</b>清货开始时间</th>
		  <td><input class="easyui-timespinner easyui-validatebox" type="text" name="cleanStartTimeStr"  required="required" missingMessage="输入项为必输项" id="cleanStartTimeStr" style="width:170px;"></td>
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
<!-- <script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script> -->
<%-- <script type="text/javascript" src="<%=basePath%>views/machineArea/easyui/jquery.easyui.min.js"></script> --%>
<script type="text/javascript">
$(function(){
	$("#companyId").combobox({
		editable:false
	});
	setTimeout(function(){
		var valueId = $("#addareaModelId").combobox("getValue");
		var areaModelType = $("#"+valueId).val();
		setMoudleType(areaModelType);
	},500);
	
});
	
	$('#companyId').combobox({   
	    onChange : function(){
// 	    	alert($("#companyId").combobox("getValue"));
	    	$.ajax({
				url:"modelcleangoods/getAreaModel",
				data:{companyId:$("#companyId").combobox("getValue")},
				dataType:'json',
				type:'post',
				success:function(data){
					$("#addareaModelId").combobox('loadData',[{}]);//重置市
					$("#addareaModelId").combobox('clear');
					if(data.length!=0){
						$("#addareaModelId").combobox({
							data:data,
							valueField:'areaModelId',
							textField:'areaModelName'
						});
						$("#addareaModelId").combobox('select','');//重置市
						//调整时间					
						$(".areaModeTypeClass").html('');
						var htmlstr = '';
						var itme;
						for(var i=0;i<data.length;i++){
							itme = data[i];
							htmlstr += '<input type="hidden" name="'+itme.areaModelId+'" id="'+itme.areaModelId+'" value="'+itme.areaModelType+'">';
						}
						$(".areaModeTypeClass").append(htmlstr);
					}
					clearMoudleType();
				}
			});
	    	$("#type").combobox("setValue","allDay"); 
	    	$("#dayDateStr").hide();
// 	    	$("<option value='" + data[i].areaModelId + "'>" + data[i].areaModelName + "</option>").appendTo($("#addareaModelId"));
	    } 
	});
	
	$('#type').combobox({   
	    onChange : function(){
	    	if($("#type").combobox("getValue")=='allDay'){
				$("#dayDateStr").hide();
			}else {
				if($("#addareaModelId").combobox("getValue")==null){
					$("#dayDateStr input:checkbox").attr({"checked":false,"disabled":false});
					$("#dayDateStr").show();	
				}else{
					$.ajax({
						url:"modelcleangoods/getdayDateStr",
			 			data:{areaModelId:$("#addareaModelId").combobox("getValue")},
			 			dataType:'json',
			 			type:'post',
			 			success:function(data){
			 				var dayDateStrs=data.dayDateStr;
			 				$("#dayDateStr input:checkbox").attr({"checked":false,"disabled":false});
			 				if(dayDateStrs.length>0){
			 					for(var i=0;i<dayDateStrs.length;i++){
			 						if(dayDateStrs[i]==null||dayDateStrs[i]==''){
			 							$("#dayDateStr").show();
			 							return ;
			 						}
			 						var arrays=dayDateStrs[i].split(",");
			 						for(var j=0;j<arrays.length;j++){
			 							$("#dayDateStr input:checkbox[value="+arrays[j]+"]").attr({"checked":true,"disabled":"disabled"});
			 						}
			 					}
			 				}
			 				$("#dayDateStr").show();	
		 				}
					});
					
				}
				
			}
	    	
	    } 
	});
	
// 	function changeType(){
// 		if($("#type").val()=='allDay'){
// 			$("#dayDateStr").hide();
// 		}else {
// 			if($("#addareaModelId").val()==null){
// 				$("#dayDateStr input:checkbox").attr({"checked":false,"disabled":false});
// 				$("#dayDateStr").show();	
// 			}else{
// 				$.ajax({
// 					url:"modelcleangoods/getdayDateStr",
// 		 			data:{areaModelId:$("#addareaModelId").val()},
// 		 			dataType:'json',
// 		 			type:'post',
// 		 			success:function(data){
// 		 				var dayDateStrs=data.dayDateStr;
// 		 				$("#dayDateStr input:checkbox").attr({"checked":false,"disabled":false});
// 		 				if(dayDateStrs.length>0){
// 		 					for(var i=0;i<dayDateStrs.length;i++){
// 		 						if(dayDateStrs[i]==null||dayDateStrs[i]==''){
// 		 							$("#dayDateStr").show();
// 		 							return ;
// 		 						}
// 		 						var arrays=dayDateStrs[i].split(",");
// 		 						for(var j=0;j<arrays.length;j++){
// 		 							$("#dayDateStr input:checkbox[value="+arrays[j]+"]").attr({"checked":true,"disabled":"disabled"});
// 		 						}
// 		 					}
// 		 				}
// 		 				$("#dayDateStr").show();	
// 	 				}
// 				});
				
// 			}
			
// 		}
// 	}
	$("#addareaModelId").combobox({
		onSelect:function(){
			$("#type").combobox("setValue","allDay")
//	 		$("#type").val("allDay"); 
	    	$("#dayDateStr").hide();
	    	$("#dayDateStr input:checkbox").attr({"checked":false,"disabled":false});
	    	var valueId = $("#addareaModelId").combobox("getValue");
	    	var areaModelType = $("#"+valueId).val();
	    	setMoudleType(areaModelType);
		}
		
	});
	
	function setMoudleType(areaModelType){
		//不同模型对应不同的默认时间段分别对应：早、中、休闲、晚
    	if(areaModelType=='0'){
    		$('#reserveTakeStartTime').val('03:00');
    		$('#reserveEndTime').val('08:00');
    		$('#reserveTakeEndTime').val('10:00');
    		$('#reserveCleanStartTime').val('10:00');
    		$('#reserveReplenishmentStartTime').val('03:00');
    		$('#reserveSupplyEndTime').val('09:30');
    		$('#retailTakeStartTime').val('08:00');
    		$('#retailTakeEndTime').val('10:00');
    		$('#retailCleanStartTime').val('10:00');
    		$('#retailReplenishmentStartTime').val('08:00');
    		$('#retailSupplyEndTime').val('09:30');
    	}else if(areaModelType=='1'){
    		$('#reserveTakeStartTime').val('10:00');//预定取餐开始时间
    		$('#reserveEndTime').val('11:30');//预定截止时间
    		$('#reserveTakeEndTime').val('13:30');//预定取餐截止时间
    		$('#reserveCleanStartTime').val('13:30');//预定清货开始时间
    		$('#reserveReplenishmentStartTime').val('10:00');//预定补货开始时间
    		$('#reserveSupplyEndTime').val('12:30');//预定补货截止时间
    		$('#retailTakeStartTime').val('11:30');//零售取餐开始时间
    		$('#retailTakeEndTime').val('13:30');//零售取餐截止时间
    		$('#retailCleanStartTime').val('13:30');//零售清货开始时间
    		$('#retailReplenishmentStartTime').val('11:30');//零售补货开始时间
    		$('#retailSupplyEndTime').val('12:30');//零售补货截止时间
    	}else if(areaModelType=='2'){
    		$('#reserveTakeStartTime').val('13:30');//预定取餐开始时间
    		$('#reserveEndTime').val('14:30');//预定截止时间
    		$('#reserveTakeEndTime').val('17:00');//预定取餐截止时间
    		$('#reserveCleanStartTime').val('17:00');//预定清货开始时间
    		$('#reserveReplenishmentStartTime').val('13:30');//预定补货开始时间
    		$('#reserveSupplyEndTime').val('17:00');//预定补货截止时间
    		$('#retailTakeStartTime').val('14:30');//零售取餐开始时间
    		$('#retailTakeEndTime').val('17:00');//零售取餐截止时间
    		$('#retailCleanStartTime').val('17:00');//零售清货开始时间
    		$('#retailReplenishmentStartTime').val('14:30');//零售补货开始时间
    		$('#retailSupplyEndTime').val('17:00');//零售补货截止时间
    	}else if(areaModelType=='3'){
    		$('#reserveTakeStartTime').val('17:00');//预定取餐开始时间
    		$('#reserveEndTime').val('17:00');//预定截止时间
    		$('#reserveTakeEndTime').val('03:00');//预定取餐截止时间
    		$('#reserveCleanStartTime').val('03:00');//预定清货开始时间
    		$('#reserveReplenishmentStartTime').val('17:00');//预定补货开始时间
    		$('#reserveSupplyEndTime').val('21:30');//预定补货截止时间
    		$('#retailTakeStartTime').val('17:00');//零售取餐开始时间
    		$('#retailTakeEndTime').val('03:00');//零售取餐截止时间
    		$('#retailCleanStartTime').val('03:00');//零售清货开始时间
    		$('#retailReplenishmentStartTime').val('17:00');//零售补货开始时间
    		$('#retailSupplyEndTime').val('21:30');//零售补货截止时间
    	}
	}
	
	function clearMoudleType(){
		$('#reserveTakeStartTime').val('');//预定取餐开始时间
		$('#reserveEndTime').val('');//预定截止时间
		$('#reserveTakeEndTime').val('');//预定取餐截止时间
		$('#reserveCleanStartTime').val('');//预定清货开始时间
		$('#reserveReplenishmentStartTime').val('');//预定补货开始时间
		$('#reserveSupplyEndTime').val('');//预定补货截止时间
		$('#retailTakeStartTime').val('');//零售取餐开始时间
		$('#retailTakeEndTime').val('');//零售取餐截止时间
		$('#retailCleanStartTime').val('');//零售清货开始时间
		$('#retailReplenishmentStartTime').val('');//零售补货开始时间
		$('#retailSupplyEndTime').val('');//零售补货截止时间
		$('#reserveClearancePromptTime').val('');
		$('#reserveReplenishmentPromptTime').val('');
		$('#reserveReplenishmentWarningTime').val('');
		$('#retailClearancePromptTime').val('');
		$('#retailReplenishmentPromptTime').val('');
		$('#retailReplenishmentWarningTime').val('');
	}
	
// 	function modelChange(){
// 		$("#type").combobox("setValue","allDay")
// // 		$("#type").val("allDay"); 
//     	$("#dayDateStr").hide();
//     	$("#dayDateStr input:checkbox").attr({"checked":false,"disabled":false});
// 	}
	
</script>
</body>
</html>
