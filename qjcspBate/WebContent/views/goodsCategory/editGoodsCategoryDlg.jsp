<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
%>
<script type="text/javascript">
	$(function() {
		$("#pid").combotree({
			width:170,
			url:"goods/category/findCategoryAllList",
			idFiled:'id',
		 	textFiled:'name',
		 	parentField:'pid',
		 	onSelect:function(node){
		 		$("#pName").val(node.text);
		 	}
		});
		
		$("#status").combobox({
			editable:false
		});
		
		$("#form").form({
			url :"goods/category/persistenceCategoryDlg",
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
					parent.$.modalDialog.openner.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title : result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}else{
					parent.$.messager.show({
						title :  result.title,
						msg : result.message,
						timeout : 1000 * 2
					});
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<input name="tempId" id="tempId"  type="hidden" value="<%=request.getParameter("tempId") %>"/>
		<form id="form" method="post">
			<input name="id" id="id"  type="hidden" value=''/>
			<input name="creater" id="creater"  type="hidden"/>
			 <table style="width: 100%;">
				 <tr>
				   <!--  <th><b class="red">*</b>类别编号</th>
					<td style="width: 240px;">
						<input name="code" id="code" validType="numCode[1,8]" placeholder="请输入类别编号" type="text" class="easyui-textbox easyui-validatebox" required="required" />easyui-validatebox
					</td> -->
				</tr>
				 <tr>
				    <th><b class="red">*</b>分类名称</th>
					<td>
						<input name="name" id="name" validType="length[2,16]" maxLength="16"  placeholder="请输入分类名称" class="easyui-textbox easyui-validatebox" type="text" required="required"/>
					</td>
			     </tr>
			     <tr>
					<th>上级分类</th>
					<td>
						<input name="pid"  class="easyui-textbox" id="pid" type="text"/><input name="pName" id="pName"  type="hidden"/>
					</td>
				 </tr>
				 <tr>
					<th><b class="red">*</b>是否启用</th>
					<td><select id="status" class="easyui-combobox" name="status" style="width:170px;" data-options="required:true">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>
				 </tr>
			 </table>
		</form>
	</div>
</div>