<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		$("#form").form({
			url :"shiro/resetPassword2", //重置密码
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
						msg : "修改成功，请重新登录",
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
			 <table>
				 <tr>
				    <!-- <th>用户编码</th>
					<td><input name="userCode" id="userCode" required="required" placeholder="请输入用户编码" class="easyui-textbox easyui-validatebox" type="text" disabled="disabled"/></td> -->
				 	<th>用户姓名</th>
					<td><input name="userName" id="userName"required="required" class="easyui-textbox easyui-validatebox" type="text" disabled="disabled"/></td>
				 </tr>
				 <tr>
				    <th>登录账号</th>
					<td><input name="userAccount" id="userAccount" required="required" class="easyui-textbox easyui-validatebox" type="text" disabled="disabled"/></td>
					<th><b class="red">*</b>登录密码</th>
					<td><input name="password" id="password" validType="account[6,16]" required="required" class="easyui-textbox easyui-validatebox" type="password"/></td>
				 </tr>
				 <tr>
					<th>手机号</th>
					<td><input name="userMobile" id="userMobile" required="required" type="text" class="easyui-textbox easyui-validatebox" disabled="disabled"/></td>
					<th>用户电话</th>
					<td><input id="userTel" name="userTel" required="required" type="text" class="easyui-textbox easyui-validatebox" disabled="disabled"/></td>
				 </tr>
				 <tr>
					<th>电子邮箱</th>
					<td colspan="3"><input id="userEmail" name="userEmail" required="required" type="text" class="easyui-textbox easyui-validatebox" disabled="disabled"/></td>
				 </tr>
				 <tr>
				    <th>备注</th>
					<td colspan="3">
						<textarea class="easyui-textbox" id="userMemo" name="userMemo" disabled="disabled" style="width: 300px;height: 80px;resize:none;" ></textarea>
					</td>
				 </tr>
			 </table>
		</form>
	</div>
</div>
