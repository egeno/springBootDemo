<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
%>
<script type="text/javascript">

	$(function() {
		
		var modelType = ${basicsMachineModel.modelType};
		
		$("#modelType").val(modelType);
		
		/* var options=document.getElenmentById("modelType").options;
		for(var i=0;i<options;i++){
		    if(modelType==options[i]){
		     options[i].selected==true
		   }
		 } */
		
		$("#modelStatus").combobox({
			editable:false
		});
		
		$("#modelType").combobox({
			editable:false
		});
		
		/* var modelType = ${basicsMachineModel.modelType};
		$("#modelType").val(modelType); */
		
		var modelStatus = ${basicsMachineModel.modelStatus};
		$("#modelStatus").val(modelStatus);
		
		$("#editForm").form({
			url :"machine/model/editModel",
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
			
	
			
</script>

<!-- <script type="text/javascript"> 
window.onload=function(){ 
  var osel=document.getElementById("modelType"); 
  var opts=osel.getElementsByTagName("option");
  
    opts[${basicsMachineModel.modelType}].selected=true;
  
} 
</script> 
 -->


<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="editForm" method="post">
		    <input name="modelId" id="modelId"  type="hidden" value="${basicsMachineModel.modelId}"/>
			 <table>
			 	<tr>
				    <th><b class="red">*</b>型号名称</th>
					<td><input name="modelName" id="modelName" validType="length[2,16]" placeholder="请输入设备型号名称" class="easyui-textbox easyui-validatebox" type="text" required="required" value="${basicsMachineModel.modelName}"/></td>
					<th><b class="red">*</b>设备类型</th>
					<!-- modelType -->
					<td colspan="3"><select id="modelType" class="easyui-combobox" name="modelType" style="width:170px;" data-options="required:true">
							<!-- <option value="0"  @(model.Sex==0?"selected":"")>男</option>
                                    <option value="1" @(model.Sex==1?"selected":"")>女</option> -->
							
							<option value="1" >加热</option>
							<option value="0" >制冷</option>
							<option value="2" >冷热一体</option>
						</select>
					</td>	
				 </tr>
				 <tr>
				 	<th><b class="red">*</b>层数</th>
				 	<td><input name="rowNum" id="rowNum" placeholder="请输入层数" class="easyui-numberbox easyui-validatebox" type="text" min="1" max="100" required="required" value="${basicsMachineModel.rowNum}" disabled="disabled"/></td>
				 	<th><b class="red">*</b>列数</th>
				 	<td><input name="columnNum" id="columnNum" placeholder="请输入列数" class="easyui-numberbox easyui-validatebox" type="text" min="1" max="100" required="required" value="${basicsMachineModel.columnNum}" disabled="disabled"/></td>
				 </tr>
				<tr>
					 	<th>温度误差</th>
				 	<td><input name="tolerance" id="tolerance" validType="length[1,10]" placeholder="请输入温度误差" class="easyui-textbox easyui-validatebox" type="text" value="${basicsMachineModel.tolerance}" required="required"/></td>
					 	<th>目标温度</th>
				 	<td><input name="targetTemperature" id="targetTemperature" validType="length[1,10]" placeholder="请输入控温目标温度" class="easyui-textbox easyui-validatebox" type="text"  onkeyup="value=value.replace(/[^\- \d.]/g,'')"   value="${basicsMachineModel.targetTemperature}" required="required"/></td>
				 </tr>
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
