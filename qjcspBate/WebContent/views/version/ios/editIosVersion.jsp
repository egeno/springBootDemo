<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		/* var isValid = ${tblAndroidVersion.id};
		$("#isValid").combobox({
			editable:false
		});
		$("#isValid").val(isValid); */
		
		$("#editForm").form({
			url :"version/ios/editVersion",
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				/* var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid; */
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
		
		$("#updateContent").keyup(
		   function(){
		   var len = $(this).val().length;
		   if(len > 149){
		    $(this).val($(this).val().substring(0,150));
		   }
		   var num = 150 - len;
		   $("#word").text(num);
		   }
		);
	});
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="editForm" method="post">
		    <input name="id" id="id"  type="hidden" value="${tblIosVersion.id}"/>
			 <table>
				<tr>
				    <th>版本的键</th>				    
				    <td><input name="versionKey" id="versionKey" validType="length[1,20]" placeholder="请输入版本键" maxlength=21 class="easyui-textbox easyui-validatebox" type="text" value="${tblIosVersion.versionKey}"/></td>				   
				 </tr> 
				<%--  <tr>
				    <th>更新内容</th>
					<td colspan="3">
						<textarea class="easyui-textbox" id="updateContent" name="updateContent"  style="width: 300px;height: 68px;resize:none;" >${tblAndroidVersion.updateContent}</textarea>
						<em id="word">字数限制：100</em>
					</td>
				 </tr> --%>
				 
				 <tr>
				    <th>版本的值</th>
				    
				    <td><input name="versionValue" id="versionValue" validType="length[0,1]" placeholder="请输入版本的值"  maxlength=2 class="easyui-textbox easyui-validatebox" type="text" value="${tblIosVersion.versionValue}"/></td>				   
				 </tr> 
				 
				<!--  <tr>  
				   <th><b class="red">*</b>是否有效</th>
					<td>
						<select id="isValid" class="easyui-combobox" name="isValid" style="width:170px;" data-options="required:true">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>	
				  </tr>  -->
				 
			 </table>
		</form>
	</div>
</div>
