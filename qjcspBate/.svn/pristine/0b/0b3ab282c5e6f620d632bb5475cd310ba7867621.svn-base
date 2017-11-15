<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		$("#form").form({
			url :"shiro/persistenceShiroMerUserEdit", //添加商户管理用户
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
		
		$("#companyId").combobox({
			width:170,
			url:"shiro/findAllUnselectCompany",
			editable:false,
			valueField:'id',
		    textField:'name'
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
	
	/* function check(){
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
    } */
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<form id="form" method="post">
			<input name="userId" id="userId"  type="hidden"/>
			 <table>
				 <tr>
				    <!-- <th><b class="red">*</b>用户编码</th>
					<td><input name="userCode" id="userCode" validType="length[2,16]" required="required" placeholder="请输入用户编码" class="easyui-textbox easyui-validatebox" type="text"/></td> -->
				 	<th><b class="red">*</b>用户姓名</th>
					<td><input name="userName" id="userName" validType="length[2,16]" required="required" class="easyui-textbox easyui-validatebox" type="text" onblur="checkName()"/><a id="nameSpan" style="color: red;font-size:12px"></a></td>
					
					<th><b class="red">*</b>登录账号</th>
					<td><input name="userAccount" id="userAccount" validType="account[6,16]" required="required" class="easyui-textbox easyui-validatebox" type="text" onblur="checkUser()"/><a id="accountSpan" style="color: red;font-size:12px"></a></td>
					
				 </tr>
				 <tr>
					<th><b class="red">*</b>密码</th>
					<td>
						<input id="password" name="password" validType="account[6,16]" class="easyui-textbox easyui-validatebox" required="required" type="password" />
					</td>
					<th><b class="red">*</b>所属公司</th>
					<td>
						<input id="companyId" class="easyui-textbox" name="companyId" style="width: 70px;" type="text" required="required"/>
					</td>
				 </tr>
				 <tr>
					<th><b class="red">*</b>手机号码</th>
					<td><input name="userMobile" id="userMobile" type="text" class="easyui-textbox easyui-validatebox" validtype="mobile" required="required" onblur="check()"/><a id="checkSpan" style="color: red;font-size:12px"></a></td>
					<th>用户电话</th>
					<td><input id="userTel" name="userTel" type="text" class="easyui-textbox easyui-validatebox" validtype="phoneRex"/></td>
				 </tr>
				 <tr>
				 	<th>电子邮箱</th>
					<td><input id="userEmail" name="userEmail" type="text" class="easyui-textbox easyui-validatebox" validType="email"/></td>
				 </tr>
				 <tr>
				 	<th>描述</th>
					<td colspan="3">
						<textarea class="easyui-textbox" id="userMemo" name="userMemo"  style="width: 300px;height: 68px;resize:none;" ></textarea>
						<em id="word">字数限制：100</em>
					</td>
				 </tr>
			 </table>
		</form>
	</div>
</div>
