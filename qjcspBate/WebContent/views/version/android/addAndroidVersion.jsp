<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function() {
				
		$("#isValid").combobox({
			editable:false
		});
		
		$("#addForm").form({
			url :"version/android/addVersion",
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
		<form id="addForm" method="post">
			 <table>
			 	
				 <tr>
				    <th><b class="red">*</b>版本号</th>
				    
				    <td><input name="version" id="version" validType="length[1,10]" placeholder="请输入版本号" maxlength=11 class="easyui-textbox easyui-validatebox" type="text"/></td>				   
				 </tr> 				    
				 <tr>
				    <th>更新内容</th>
					<td colspan="4">
						<textarea class="easyui-textbox" id="updateContent" name="updateContent"  style="width: 300px;height: 78px;resize:none;" ></textarea>
						<em id="word">字数限制：150</em>
					</td>
				 </tr>
				 
				 <tr>
				    <th><b class="red"></b>访问URL</th>
				    
				    <td><input name="updateUrl" id="updateUrl" validType="length[1,100]" placeholder="请输URL" maxlength=101 class="easyui-textbox easyui-validatebox" type="text"/></td>				   
				 </tr> 				  
				  <tr>  
				   <th><b class="red">*</b>是否有效</th>
					<td>
						<select id="isValid" class="easyui-combobox" name="isValid" style="width:170px;" data-options="required:true">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>	
				  </tr> 	
				   <tr>  
				   <th><b class="red">*</b>APP类型</th>
					<td>
						<select id="appType" class="easyui-combobox" name="appType" style="width:170px;" data-options="required:true">
							<option value="0">订餐APP</option>
							<option value="1">众包商和补货人员APP</option>
							<option value="2">商户APP</option>
							<option value="3">维修人员APP</option>
						</select>
					</td>	
				  </tr> 			    				
			 </table>
		</form>
	</div>
</div>
