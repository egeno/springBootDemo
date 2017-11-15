<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html lang="en" class="bg">
<head>
	<meta charset="UTF-8">
	<title>lfenqumoblie_add_dialog</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
 	<script type="text/javascript" src="<%=basePath%>views/areaModel/js/fenqumoblie_dialog.js"></script> 
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
			<form id="addForm" method="post">
				<table style="width: 100%;">
					<tr>
						<th><b class="red" >*</b>分区模型名称</th>
						<td style="width: 350px;">
							<input type="text" name="areaModelName" validType="length[1,4]" maxLength="4" class="easyui-textbox easyui-validatebox" required="required">
						</td>
					</tr>
					<tr>
						<th><b class="red" >*</b>分区模型类别</th>
						<td>
							<select class="easyui-combobox easyui-validatebox" id="areaModelType" name="areaModelType" style="width:170px;" data-options="required:true" required="required">
								<option value="0">冷柜</option>
								<option value="1">热柜</option>
								<option value="2">冷热一体</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><b class="red" >*</b>时间段</th>
						<td>
							<select class="easyui-combobox easyui-validatebox" id="timeType" name="timeType" style="width:170px;" data-options="required:true" required="required">
								<option value="0">早餐</option>
								<option value="1">中餐</option>
								<option value="2">休闲</option>
								<option value="3">晚餐</option>
							</select>
						</td>
					</tr>
					<!-- <tr>
						<th><b class="red">*</b>适用行数</th>
						<td>
							<input type="text" name="areaModelRow" validType="account[1,16]" class="easyui-textbox easyui-validatebox" required="required">
						</td>
					</tr>
					<tr>
						<th><b class="red">*</b>适用列数</th>
						<td>
							<input type="text" name="areaModelColumn" validType="account[1,16]" class="easyui-textbox easyui-validatebox" required="required">
						</td>
					</tr> -->
					<tr>
					 	<th>分区模型描述</th>
						<td>
							<textarea class="easyui-textbox" id="areaModelMemo" name="areaModelMemo"  style="width: 170px;height: 68px;resize:none;" ></textarea>
							<em id="word">字数限制：100</em>
						</td>
					 </tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#areaModelType").combobox({
				editable:false
			});
			
			$("#areaModelMemo").keyup(
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