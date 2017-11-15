<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include  file="../include/ctxPathInclude.jsp"%>

<script type="text/javascript">
	$(function() {
		$("#roleStatus").combobox({
			editable:false
		});
		
		$("#form").form({
			url :"userRole/saveRole",
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
					parent.$.modalDialog.openner.datagrid('reload');
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
		
		$("#roleMemo").keyup(
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
		<form id="form" method="post">
			<input name="roleId" id="roleId"  type="hidden"/>
			<input name="creater" id="creater"  type="hidden"/>
			 <table style="width: 100%;">
				 <tr>
				    <th><b class="red">*</b>角色名称</th>
					<td style="width: 360px;"><input name="roleName" id="roleName" validType="length[2,16]" placeholder="请输入角色名称" class="easyui-textbox easyui-validatebox" type="text" required="required"/></td>
				 </tr>
				 <tr>
				 <th><b class="red">*</b>角色状态</th>						
				 <td>
						<select id="roleStatus" class="easyui-combobox" name="roleStatus" style="width:170px;" data-options="required:true">
							<option value="1">开启</option>
							<option value="0">关闭</option>
						</select>
					</td>					 
				 </tr>
				 <tr>
				 	<th>描述</th>
					<td><textarea class="easyui-textbox" id="roleMemo" name="roleMemo"  style="width: 170px;height: 68px;resize:none;" ></textarea><em id="word">字数限制：100</em></td>
				 </tr>
			 </table>
		</form>
	</div>
</div>
