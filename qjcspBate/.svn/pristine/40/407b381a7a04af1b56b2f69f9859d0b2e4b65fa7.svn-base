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
			url :"advertisement/addAdvertisement",
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
						title :"提示",
						msg : result.message,
						timeout : 1000 * 2
					});
				}else{
					parent.$.messager.show({
						title :"提示",
						msg : result.message,
						timeout : 1000 * 2
					});
				}
			}
		});
		
		$("#goodsMemo").keyup(
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
		<form id="addForm" method="post">
			 <table>
			 	<tr>
				 	<th><b class="red">*</b>广告图片</th>
				 	<td>
				 		<div id="imageDiv" class="gallery" >
				 			<img src="<%=basePath %>uploadImage/banner.png" name="image1" id="image1" />
				 		</div>
			 			<input type="hidden" name="adPicUrl" id="goodsIcon" value="" />
				 	</td>
				 	<td colspan="2">
			 			<input type="file" id="file" name="imageFile">
			 		</td>
				 </tr>
				 <tr>
				    <th>图片名称</th>
					<td><input name="adPicName" id="adPicName" placeholder="请输入图片名称" class="easyui-textbox easyui-validatebox" type="text" /></td>
					<th><b class="red">*</b>图片用途</th>
				 	<td>
				 		<select id="adPicType" name="adPicType" class="easyui-combobox" style="width:170px;" data-options="required:true">
							<option value="1">Banner条图片</option>
						</select>
				 	</td>
				 </tr>
				 <tr>
				 	<th>图片链接</th>
				 	<td><input name="adHrefUrl" id="adHrefUrl" validType="length[1,50]" placeholder="请输入图片链接" class="easyui-textbox easyui-validatebox" type="text"/></td>
				 	<th>图片顺序</th>
				 	<td><input name="picSortNum" id="picSortNum" validType="length[1,1]" maxLength="1" placeholder="请输入图片顺序" class="easyui-textbox easyui-validatebox" type="text"/></td>
				 </tr>
				  <tr>
				    <th>描述</th>
					<td colspan="3">
						<textarea class="easyui-textbox" id="goodsMemo" name="adPicDesc" style="width: 300px;height: 68px;resize:none;" ></textarea>
						<em id="word">字数限制：100</em>
					</td>
				 </tr>
			 </table>
		</form>
	</div>
</div>