<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		
		$("#modelStatus").combobox({
			editable:false
		});
		
		$("#modelType").combobox({
			editable:false
		});
		
		$("#addForm").form({
			url :"machine/model/addModel",
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				
				if (result.status) {
					parent.reload;
					parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title :"提示",
						msg : result.message,
						timeout : 1000 * 2
					});
				}else{
					parent.$.messager.show({
						title :"提示",
						msg : result.message,
						timeout : 1000 * 2
					});
				}
			}
		});
		
		$("#modelMemo").keyup(
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
	
	function judgeRow(){
		var rowNum = $("#rowNum").val();
		if(rowNum > 15 || rowNum < 1){
		    alert("请输入1~15之间的层数");
	    }
	} 
	
	function judgeColumn(){
		var columnNum = $("#columnNum").val();
		if(columnNum > 10 || columnNum < 1){
		    alert("请输入1~10之间的列数");
	    }
	} 
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="addForm" method="post">
			 <table>
				 <tr>
				    <th><b class="red">*</b>型号名称</th>
				    
				   <!--  <td><input name="versionKey" id="versionKey" validType="length[1,20]" placeholder="请输入版本键"  maxlength=21 class="easyui-textbox easyui-validatebox" type="text"/></td>				    -->
				    				    
					<td><input name="modelName" id="modelName" validType="length[2,16]" placeholder="请输入设备型号名称" class="easyui-textbox easyui-validatebox" type="text" /></td>
					<th><b class="red">*</b>设备类型</th>
					<td colspan="3"><select id="modelType" class="easyui-combobox" name="modelType" style="width:170px;" data-options="required:true">
							<option value="1">加热</option>
							<option value="0">制冷</option>
							<option value="2">冷热一体</option>
						</select>
					</td>
				 </tr>
				 <tr>
				 	<th><b class="red">*</b>层数</th>
				 	<td><input name="rowNum" id="rowNum" placeholder="请输入层数" class="easyui-numberbox easyui-validatebox" min="1" max="100" type="text" required="required" /></td>
				 	<th><b class="red">*</b>列数</th>
				 	<td><input name="columnNum" id="columnNum" placeholder="请输入列数" class="easyui-numberbox easyui-validatebox" min="1" max="100" type="text" required="required"/></td>
				 </tr>
				<tr>
					 	<th>温度误差</th>
				 	<td><input name="tolerance" id="tolerance" validType="account[1,16]" placeholder="请输入温度误差" class="easyui-textbox easyui-validatebox" type="text" required="required"/></td>
					 	<th>目标温度</th>
				 	<!-- <td><input name="targetTemperature" id="targetTemperature" validType="account[1,16]" placeholder="请输入控温目标温度" class="easyui-textbox easyui-validatebox"  onkeyup="value=value.replace(/[^\- \d.]/g,'')" type="text" required="required"/></td> -->
				         <td><input name="targetTemperature" id="targetTemperature" validType="length[1,16]" placeholder="请输入控温目标温度" class="easyui-textbox easyui-validatebox" type="text"  onkeyup="value=value.replace(/[^\- \d.]/g,'')"  required="required"/></td>
				 </tr>
				 
				 <!-- </tr> -->
				<tr>
					<th>是否启用</th>
					<td colspan="3"><select id="modelStatus" class="easyui-combobox" name="modelStatus" style="width:170px;" data-options="required:true">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>	
				</tr>
				 <tr>
				 	<th>描述</th>
					<td colspan="3"><textarea class="easyui-textbox" id="modelMemo" name="modelMemo"  style="width: 300px;height: 68px;resize:none;" ></textarea><em id="word">字数限制：100</em></td>
				 </tr>
			 </table>
		</form>
	</div>
</div>
