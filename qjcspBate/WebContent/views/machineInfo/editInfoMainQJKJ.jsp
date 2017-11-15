<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">

	$("#companyId").combotree({
		width:170,
		url:"merchant/findMainList",
		idFiled:'id',
	 	textFiled:'companyName',
	 	parentField:'pid',
			onSelect: function (record){
				$("#companyName").val(record.text);
			}
	 	
	});

	$("#modelId").combotree({
		width:170,
		url:"machine/model/findAllMachineModelList",
		idFiled:'id',
	 	textFiled:'name',
	 	parentField:'id'
	 	
	});

	$(function() {
		$("#machineStatus").combobox({
			editable:false
		});
		
		var companyId = ${basicsMachine.companyId};
		$("#companyId").val(companyId);
		
		var machineStatus = ${basicsMachine.machineStatus};
		$("#machineStatus").val(machineStatus);
		
		$("#editForm").form({
			url :"machine/info/editInfo",
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
		
		$("#machineMemo").keyup(
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
<script type="text/javascript" src="<%=basePath %>views/machineInfo/js/address.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="editForm" method="post">
		    <input name="machineId" id="machineId"  type="hidden" value="${basicsMachine.machineId}"/>
		 	<table>
				<tr>
					<th>设备图标</th>
				 	<td>
				 		<div id="imageDiv" class="gallery">
				 			<img src="<%=path %>${basicsMachine.machineIcon}" name="image1" id="image1" />
				 		</div>
				 		<input type="hidden" name="machineIcon" id="machineIcon" value="${basicsMachine.machineIcon}" />
				 	</td>
				 	<td colspan="2">
				 		<input type="file" id="file" name="imageFile">
				 	</td>
				 </tr>
				<tr>
					<th><b class="red">*</b>所属公司 </th>
					<td>
						<input name="companyId"  class="easyui-textbox" id="companyId" value="${basicsMachine.companyId}" type="text" required="required"/>
						<input name="companyName" id="companyName"  type="hidden"/>
					</td>
					<th><b class="red">*</b>设备名称</th>
					<td>
						<input name="machineName" id="machineName" validType="length[2,16]" placeholder="请输入设备名称" class="easyui-textbox easyui-validatebox" type="text" value="${basicsMachine.machineName}" required="required"/>
					</td>
			 	</tr>
				 <tr>
					<th><b class="red">*</b>设备型号</th>
					<td>
					    <input name="modelId" id="modelId" value="${basicsMachine.modelId}" type="hidden"/> 
					</td>
					<%-- <td><input name="modelId"  class="easyui-textbox" id="modelId" validType="length[2,16]" type="text" value="${basicsMachine.modelId}" disabled="disabled" required="required"/> --%>
					<th><b class="red">*</b>设备编号</th>
				 	<td><input name="deviceCode" id="deviceCode" validType="length[2,32]" placeholder="请输入设备硬件id" class="easyui-textbox easyui-validatebox" type="text" value="${basicsMachine.deviceCode}" disabled="disabled"/></td>
				 </tr>
				 <tr>
					<th><b class="red">*</b>温度误差</th>
					<td><input name="tolerance"  class="easyui-textbox" id="tolerance" placeholder="请输入允许温度误差" class="easyui-numberbox easyui-validatebox" type="text" value="${basicsMachine.tolerance}" required="required"/>
					<th><b class="red">*</b>控温目标温度</th>
				 	<td><input name="targetTemperature" id="targetTemperature" placeholder="请输入目标温度" class="easyui-numberbox easyui-validatebox" type="text" value="${basicsMachine.targetTemperature}" required="required"/></td>
				 </tr>
				<tr>
					<th><b class="red">*</b>所在省份</th>
					<td>
						<input name="provCode" id="provCode" value="${basicsMachine.provCode}" class="easyui-combobox" required="required" data-options="width:170,valueField:'id',textField:'name',editable:false"/>
						<input name="province" id="province" value="${basicsMachine.province}" type="hidden"/>
					</td>
					<th><b class="red">*</b>所在城市</th>
					<td>
						<input name="cityCode" id="cityCode" value="${basicsMachine.cityCode}" class="easyui-combobox" required="required" data-options="width:170,valueField:'id',textField:'name',editable:false"/>
						<input name="cityName" id="cityName" value="${basicsMachine.cityName}" type="hidden"/>
					</td>
				</tr>
				<tr>
					<th><b class="red">*</b>区域</th>
					<td>
						<input name="machineZoneId" id="machineZoneId" value="${basicsMachine.machineZoneId}" validType="length[2,30]" class="easyui-combobox" data-options="width:170,valueField:'machineZoneId',textField:'machineZoneName',editable:false" required="required"/>
					</td>
					<th><b class="red">*</b>详细地址</th>
				 	<td><input name="address" id="address" validType="length[2,30]" placeholder="请输入详细地址" class="easyui-textbox easyui-validatebox" type="text" value="${basicsMachine.address}" required="required"/></td>
				</tr>
				<tr>
					<th><b class="red">*</b>经 / 纬度</th>
					<td>
						<input id="longitude" name="longitude" placeholder="经度" class="easyui-numberbox easyui-validatebox" data-options="min:0,max:180,precision:10" value="${basicsMachine.longitude}" style="width: 77px;" required="required"/> / 
						<input id="latitude" name="latitude" placeholder="纬度" class="easyui-numberbox easyui-validatebox" data-options="min:0,max:90,precision:10" value="${basicsMachine.latitude}" style="width: 77px;" required="required"/>
					</td>
				 	<th><b class="red">*</b>是否启用</th>
					<td>
						<select id="machineStatus" class="easyui-combobox" name="machineStatus" style="width:170px;" data-options="required:true" >
							<option value="1" <c:if test="${basicsMachine.machineStatus == 1}">selected="selected"</c:if>>是</option>
							<option value="0" <c:if test="${basicsMachine.machineStatus == 0}">selected="selected"</c:if>>否</option>
						</select>
					</td>
				 </tr>
			 	<tr>
			 		<th>描述</th>
					<td colspan="3">
						<textarea class="easyui-textbox" id="machineMemo" name="machineMemo"  style="width: 300px;height: 68px;resize:none;" >${basicsMachine.machineMemo}</textarea>
						<em id="word">字数限制：100</em>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script type="text/javascript">
    //modelId('${basicsMachine.modelId}');
	 <c:if test="${not empty basicsMachine.provCode}">
		province('${basicsMachine.provCode}');
	</c:if>
	<c:if test="${not empty basicsMachine.machineZoneId}">
		machineZone('${basicsMachine.machineZoneId}');
	</c:if>
	<c:if test="${not empty basicsMachine.provCode}">
		city('${basicsMachine.provCode}','${basicsMachine.province}','${basicsMachine.cityCode}','${basicsMachine.cityName}');
	</c:if>
  /*   <c:if test="${not empty basicsMachine.provCode}">
		city('${basicsMachine.provCode}','${basicsMachine.province}','${basicsMachine.cityCode}','${basicsMachine.cityName}');
	</c:if>  */

	/* <c:if test="${not empty basicsMachine.cityCode}">
		area('${basicsMachine.cityCode}','${basicsMachine.cityName}','${basicsMachine.areaCode}','${basicsMachine.areaName}');
	</c:if> */
</script>