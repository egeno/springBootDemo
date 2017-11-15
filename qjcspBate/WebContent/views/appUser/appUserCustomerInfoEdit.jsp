<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		$("#form").form({
			url :"appUserInfo/persistenceAPPInfoEdit", //添加、编辑APP用户信息
 			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				/* var companyId = $("#companyId").combobox('getValue'); */
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				/* if($("#companyId").combobox('getValue')==""){
					document.getElementById("checkSpan").style.display = "block";
					isValid = false;
					parent.$.messager.progress('close');
				}*/
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
			<input name="customerId" id="customerId"  type="hidden"/>
			 <table>
				 <tr>
				    <th>客户姓名</th>
					<td><input name="customerName" id="customerName" validType="length[2,16]" maxLength="16" placeholder="请输入用户姓名" class="easyui-textbox easyui-validatebox" type="text"/></td>
				 </tr>
				 <tr>
					<th>密码</th>
					<td>
					<input id="customerPassword" name="customerPassword" validType="account[6,16]" maxLength="16" class="easyui-validatebox" type="password" />
					</td>
					<th>支付密码</th>
					<td><input name="payPassword" id="payPassword" validType="account[6,16]" maxLength="16" class="easyui-textbox easyui-validatebox" type="text"/></td>
				 </tr>
				 <tr>
				    <th>手机号</th>
					<td><input name="customerMobile" validtype="mobile" id="customerMobile" maxLength="11" class="easyui-textbox easyui-validatebox" type="text"/></td>
					<th>邮箱</th>
					<td><input name="customerEmail" id="customerEmail" validType="email" type="text" class="easyui-textbox easyui-validatebox" /></td>
				 </tr>
				 <tr>
				 	<th>全名</th>
					<td><input name="realName" id="realName" validType="length[2,16]" maxLength="16" type="text" class="easyui-textbox easyui-validatebox" /></td>
				    <th>性别</th>
					<td><input id="customerSex" name="customerSex" validType="length[1,1]" type="text" class="easyui-textbox easyui-validatebox" /></td>
				 </tr>
				 <tr>
				 	<th>QQ</th>
					<td><input id="customerQq" name="customerQq" validType="length[6,16]" maxLength="16" type="text" class="easyui-textbox easyui-validatebox" /></td>
				 	<th>微信</th>
					<td><input id="customerWeixin" name="customerWeixin" validType="length[2,16]" maxLength="16" type="text" class="easyui-textbox easyui-validatebox"/></td>
				 </tr>
				 <tr>
				 	<th>余额</th>
					<td><input name="customerMoney" id="customerMoney" validType="length[1,10]"  type="text" class="easyui-textbox easyui-validatebox" /></td>
				    <th>积分</th>
					<td><input id="customerIntegral" name="customerIntegral" validType="length[1,10]" type="text" class="easyui-textbox easyui-validatebox" /></td>
				 </tr>
				 <tr>
				 	<th>学校(公司id)</th>
					<td><input name="universityName" id="universityName" validType="length[1,10]" maxLength="10" type="text" class="easyui-textbox easyui-validatebox"/></td>
				    <th>寝室楼</th>
					<td><input id="buildingName" name="buildingName" validType="length[2,16]" maxLength="16" type="text" class="easyui-textbox easyui-validatebox"/></td>
				 </tr>
				 <tr>
				 	<th>年级</th>
					<td><input name="grade" id="grade" type="text" validType="length[1,10]" maxLength="10" class="easyui-textbox easyui-validatebox"/></td>
				    <th>学号</th>
					<td><input id="studentNum" name="studentNum" validType="length[2,16]" maxLength="16" type="text" class="easyui-textbox easyui-validatebox"/></td>
				 </tr>
				 <tr>
				 	<th>籍贯</th>
					<td><input name="nativePlace" id="nativePlace" validType="length[2,16]" maxLength="16" type="text" class="easyui-textbox easyui-validatebox"/></td>
				 </tr>
			 </table>
		</form>
	</div>
</div>
