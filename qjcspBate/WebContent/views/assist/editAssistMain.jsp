<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		var type = ${assist.type};
		$("#type").val(type);	
		$("#editForm").form({
			url :"weixinassist/editAssist",
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
		
		
	$("#content").keyup(function() {
			var len = $(this).val().length;
			if (len > 199) {
				$(this).val($(this).val().substring(0, 200));
			}
			var num = 200 - len;
			$("#word").text(num);
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="editForm" method="post">
		    <input name="id" id="id"  type="hidden" value="${assist.id}"/>
			 <table>
			 	<tr>
			 		<th>互动图片</th>
				 	<td>
				 		<div id="imageDiv" class="gallery" >
				 			<img 
				 				<c:if test="${empty assist.picurl}">src="<%=path %>/uploadImage/bg.png"</c:if>
				 				<c:if test="${!empty assist.picurl}">src="<%=path %>${assist.picurl}"</c:if>
				 				name="image1" id="image1"/>
				 		</div>
			 			<input type="hidden" name="picurl" id="picurl"  value="${assist.picurl}" />
				 	</td>
				 	<td colspan="2">
			 			<input type="file" id="file" name="imageFile">
			 				<b class="red">(请上传尺寸为360*240的图片) </b>
			 		</td>
				 </tr>
				 <tr>
				    <th><b class="red">*</b>微信响应字段</th>
					<td><input name="code" id="code"  validType="length[1,20]" maxLength="20" placeholder="请输入微信响应字段" class="easyui-textbox easyui-validatebox" type="text" value="${assist.code}" required="required" /></td>
					<th>父类Id</th>
				 	<td><input name="pid" id="pid" validType="length[1,20]" maxLength="20" placeholder="请输入微信互动父类Id" class="easyui-textbox easyui-validatebox" value="${assist.pid}" type="text"/></td>
				 </tr>
				  <tr>
				 	<th><b class="red">*</b>标题</th>
				 	<td><input name="title" id="title" validType="length[1,50]" maxLength="50"  placeholder="请输入标题" class="easyui-textbox easyui-validatebox" style="width: 170px;" value="${assist.title}" required="required"/></td>
				 	<th><b class="red">*</b>互动类型</th>
					<td>
						<select id="type" class="easyui-combobox" name="type" editable="false" style="width:170px;" data-options="required:true">
							<option value="1">文本消息</option>
							<option value="0">图文消息</option>
						</select>
					</td>	
				 </tr>
				<tr>
					 	<th>跳转URL</th>
				 	<td colspan="3"><input name="url" id="url" validType="length[1,100]" maxLength="100" style="width: 300px; placeholder="请输入微信互动跳转URL" class="easyui-textbox easyui-validatebox" value="${assist.url}" type="text"/>
				 	<b class="red">(外网地址请加上'http://'头部或'https://',项目地址直接写路径) </b></td>
				</tr>
				 <tr>
				    <th>内容</th>
					<td colspan="3">
						<textarea class="easyui-textbox" id="content" name="content"  style="width: 300px;height: 68px;resize:none;" >${assist.content}</textarea>
						<em id="word">字数限制：200</em>
					</td>
				 </tr>
			 </table>
		</form>
	</div>
</div>
