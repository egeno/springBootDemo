<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		$("#form").form({
			url :"shiro/persistenceShiroMerUserEdit", //编辑商户管理用户
 			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				var checkSpan = $('#checkSpan');
				var nameSpan = $('#nameSpan');
				var accountSpan = $('#accountSpan');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				if(checkSpan.html()=="该手机号已存在"){
					isValid = false;
					parent.$.messager.progress('close');
				}
				if(nameSpan.html()=="该用户名已存在"){
					isValid = false;
					parent.$.messager.progress('close');
				}
				if(accountSpan.html()=="该登录账号已存在"){
					isValid = false;
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
		
		$("#userMemo").keyup(
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
	
	function check(){
        var userMobile = $('#userMobile').val();
        $.ajax({
        url: "shiro/check",
        type: "post",
        data:"userMobile="+userMobile,
        dataType:"text",
        success:function(text){
            if(text!=""){
                var checkSpan = $('#checkSpan');
                checkSpan.text(text);
            }
            else{
                var checkSpan = $('#checkSpan');
                checkSpan.text("");
            }
        }
        })
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="form" method="post">
			<input name="userId" id="userId"  type="hidden"/>
			 <table style="width: 100%;">
				<!--  <tr>
				    <th><b class="red">*</b>用户编码</th>
					<td style="width: 240px;"><input name="userCode" id="userCode" validType="length[2,16]" required="required" placeholder="请输入用户编码" class="easyui-textbox easyui-validatebox" type="text"/></td>
				</tr> -->
				 <tr>
					<th><b class="red">*</b>用户姓名</th>
					<td><input name="userName" id="userName" validType="length[2,16]" required="required" class="easyui-textbox easyui-validatebox" type="text" /><a id="nameSpan" style="color: red;font-size:12px"></a></td>
				 </tr>
				 <tr>
				    <th><b class="red">*</b>登录账号</th>
					<td><input name="userAccount" id="userAccount" validType="account[6,16]" required="required" class="easyui-textbox easyui-validatebox" type="text" /><a id="accountSpan" style="color: red;font-size:12px"></a></td>
				</tr>
				 <tr>
					<th><b class="red">*</b>手机号</th>
					<td>
						<input name="userMobile" id="userMobile" validtype="mobile" type="text" class="easyui-textbox easyui-validatebox" required="required" />
						<a id="checkSpan" style="color: red;font-size:12px"></a>
					</td>
				 </tr>
				 <tr>
					<th>用户电话</th>
					<td><input id="userTel" name="userTel" validtype="phoneRex" type="text" class="easyui-textbox easyui-validatebox"/></td>
				</tr>
				 <tr>
					<th>电子邮箱</th>
					<td>
						<input id="userEmail" name="userEmail" validType="email" type="text" class="easyui-textbox easyui-validatebox"/>
					</td>
				 </tr>
			 </table>
		</form>
	</div>
</div>
