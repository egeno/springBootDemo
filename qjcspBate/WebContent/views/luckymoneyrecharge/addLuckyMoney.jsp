<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>红包添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/machineArea/css/fenqu.css">
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
			<form id=form method="post">
				<table >
				  <tr>
				      <th><b class="red">*</b>手机号</th>
				      <td>
				       <input name="mobile" id="mobile" validtype="mobile" maxLength="11" placeholder="请输入手机号" type="text" class="easyui-textbox easyui-validatebox" required="required" onblur="check()"/>
						<a id="checkSpan" style="color: red;font-size:12px"></a>
				      </td>
				   </tr>
				   <tr>
				      <th><b class="red">*</b>奖励等级</th>
				      <td>
				          <select id="id" name="id" style="width: 180;height: 25">
				            <c:forEach items="${data}" var="d">
						  	 		<option value="${d.id}">${d.prideName}</option>
					  	  	</c:forEach>
				          </select>
				      </td>
				  </tr>
				   <tr>
				      <th>有效天数</th>
				      <td>                                                                                 
				          <input name="effectiveday" id="effectiveday" required="required" placeholder="请输有效天数"  class="easyui-textbox easyui-validatebox" type="text" validType='pInt'>
				      </td>
				  </tr>
				</table>
			</form>
		</div>
	</div>
<script type="text/javascript">
$(function() {
	$("#form").form({
		url :"luckyMoney/saveLuckyMoney", //编辑、添加平台用户
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
</body>
</html>