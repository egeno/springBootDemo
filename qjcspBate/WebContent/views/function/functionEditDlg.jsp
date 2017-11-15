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
			url:"<%=path%>/function/findAllFunctionList",
			idFiled:'id',
		 	textFiled:'name',
		 	parentField:'pid',
		 	onSelect:function(node){
		 		$("#pname").val(node.text);
		 	}
		});
		$("#iconCls").combobox({
			width:170,
			data:$.iconData,
			formatter: function(v){
				return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
			}
		});
		var tempId=$("#tempId").val();
		if(tempId=="F"){
			$("#pid").combotree("disable");
		}
		$("#form").form({
			url :"<%=path%>/function/persistenceFunctionDig",
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
		$("#description").keyup(
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
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<input name="tempId" id="tempId"  type="hidden" value="<%=request.getParameter("tempId") %>"/>
		<form id="form" method="post">
			<input name="menuId" id="menuId"  type="hidden"/>
			<input name="creater" id="creater"  type="hidden"/>
			<input name="status" id="status"  type="hidden"/>
			<input name="state" id="state"  type="hidden"/>
			 <table>
				 <tr>
				    <th>程序名称</th>
					<td>
						<input name="name" id="name" placeholder="请输入程式名称" validType="length[0,10]" maxLength="10" class="easyui-textbox easyui-validatebox" type="text"/>
					</td>
					<th>父程序名称</th>
					<td>
						<input name="pid"  class="easyui-textbox" id="pid" type="text"/><input name="pname" id="pname"  type="hidden"/>
					</td>
					
				 </tr>
				 <tr>
				    <th>排列顺序</th>
					<td>
						<input name="sort" id="sort" type="text" class="easyui-numberbox easyui-validatebox" data-options="min:0,max:99999,required:true" required="required"/>
					</td>
					<th>程序图标</th>
					<td>
						<input id="iconCls" name="iconCls" type="text"/>
					</td>
				 </tr>
				  <tr>
				    <th>程序路径</th>
					<td>
						<input id="url" name="url" type="text" class="easyui-textbox easyui-validatebox" required="required"/>
					</td>
					<th>程序编码</th>
					<td>
						<input id="myid" name="myid" type="text" class="easyui-textbox easyui-validatebox" required="required"/>
					</td>
				 </tr>
				 <tr>
					<th>程序类型</th>
					<td>
						<select id="type" class="easyui-combobox" name="type" style="width:170px;" data-options="required:true">
							<option value="F">菜单</option>
							<option value="O">操作</option>
						</select>
					</td>
					<th>功能类型</th>
					<td>
						<select id="menuCategory" class="easyui-combobox" name="menuCategory" style="width:170px;" data-options="required:true">
							<option value="0">平台</option>
							<option value="1">商户</option>
							<option value="">共用</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>是否启用</th>
					<td colspan="3"><select id="isused" class="easyui-combobox" name="isused" style="width:170px;" data-options="required:true">
							<option value="Y">是</option>
							<option value="N">否</option>
						</select>
					</td>
				</tr>
				 <tr>
					<th>描述</th>
					<td colspan="3">
						<textarea class="easyui-textbox" id="description" name="description"  style="width: 300px;height: 68px;"></textarea>
						<em id="word">字数限制：100</em>
					</td>
				</tr>
			 </table>
		</form>
	</div>
</div>
