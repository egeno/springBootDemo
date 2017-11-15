<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function() {	
		$("#addForm").form({
			url :"code/addAndroid",
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
					//parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
					parent.$.messager.show({
						title :"提示",
						msg : result.message,
						timeout : 1000 * 2
					});
					refreshTab($('.tabs-selected').find('.tabs-title').text());
				}else{
					parent.$.messager.show({
						title :"提示",
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
		<form id="addForm" method="post">
			 <table style="width: 100%;">
			 	<tr>
				 	<td>
				 		<div id="imageDiv" class="gallery">
				 			<img src="<%=basePath %>uploadImage/qr_bg.jpg" name="image1" id="image1" style="width: 130px;height: 130px;" />
				 			<input type="hidden" name="androidPic" id="goodsIcon" value="" />
				 		</div>
				 	</td>
				 	<td>
			 			<input type="file" id="file" name="imageFile">
			 		</td>
				 </tr>
			 </table>
		</form>
	</div>
</div>