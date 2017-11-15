<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		$("#form").form({
			url :"appUser/updateIntegral", //修改积分
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
		<form id="form" method="post">
			<input name="userId" id="userId"  type="hidden"/>
			<input name="customerId" id="customerId" type="hidden" />
			 <table>
				 <tr>
				 	<th>客户昵称</th>
					<td><input name="customerName" id="customerName" class="easyui-textbox easyui-validatebox" type="text" readonly="readonly"/></td>
				 </tr>
				 <tr>
					<th>所属学校</th>
					<td><input name="universityName" id="universityName" class="easyui-textbox easyui-validatebox" type="text" readonly="readonly"/></td>
				 </tr>
				 <tr>
				 	 <th>手机号</th>
					<td><input name="customerMobile" id="customerMobile" required="required" class="easyui-textbox easyui-validatebox" type="text" readonly="readonly"/></td>
				 </tr>
				 <tr>
					<th>注册时间</th>
					<td><input name="createTimeStr" id="createTimeStr" type="text" class="easyui-textbox easyui-validatebox" readonly="readonly"/></td>
				 </tr>
				 <tr>
				 	<th><b class="red">*</b>当前积分</th>
					<td><input id="customerIntegral" name="customerIntegral" validType="length[1,8]" type="text" class="easyui-textbox easyui-validatebox"/></td>
				 </tr>
			 </table>
		</form>
	</div>
</div>
