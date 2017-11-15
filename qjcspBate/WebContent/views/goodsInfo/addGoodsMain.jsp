<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function() {
		$("#goodsCategoryId").combotree({
			width:170,
			url:"goods/category/findCategoryAllList",
			idFiled:'id',
		 	textFiled:'name',
		 	parentField:'pid',
		 	onSelect:function(node){
		 		$("#categoryName").val(node.text);
		 	}
		});
		
		$("#goodsStatus").combobox({
			editable:false
		});
		
		$("#addForm").form({
			url :"goods/info/addGoods",
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
				 	<th>商品图标</th>
				 	<td>
				 		<div id="imageDiv" class="gallery" >
				 			<img src="<%=basePath %>uploadImage/bg.png" name="image1" id="image1" />
				 		</div>
			 			<input type="hidden" name="goodsIcon" id="goodsIcon" value="" />
			 		
				 	</td>
				 		
				 	<td colspan="2">
			 			<input type="file" id="file" name="imageFile">
			 	<b class="red">(请上传尺寸为251*228的图片) </b>
			 		</td>
				 </tr>
				 <tr>
				    <th><b class="red">*</b>商品名称</th>
					<td><input name="goodsName" id="goodsName"  validType="parenthese[1,27]" maxLength="27" placeholder="请输入商品名称" class="easyui-textbox easyui-validatebox" type="text" required="required" /></td>
					<!-- <th>商品编码</th>
				 	<td><input name="goodsCode" id="goodsCode" validType="length[1,10]" maxLength="10" placeholder="请输入商品编码" class="easyui-textbox easyui-validatebox" type="text"/></td>
				  -->
				 </tr>
				 <tr>
				 	<th><b class="red">*</b>成本价(元)</th>
				 	<td><input name="costPrice" id="costPrice" validType="length[1,10]" maxLength="10"  placeholder="请输入成本价" class="easyui-numberbox easyui-validatebox" style="width: 170px;" data-options="min:0,precision:2" required="required"/></td>
				 	<th><b class="red">*</b>零售价(元)</th>
				 	<td><input name="retailPrice" id="retailPrice" validType="length[1,10]" maxLength="10" placeholder="请输入零售价" class="easyui-numberbox easyui-validatebox" style="width: 170px;" data-options="min:0,precision:2" required="required"/></td>
				 </tr>
				<tr>
					 	<th>长(厘米)</th>
				 	<td><input name="longth" id="longth" validType="length[1,9]" maxLength="9" placeholder="请输入商品长度" class="easyui-textbox easyui-validatebox" type="text"/></td>
					 	<th>宽(厘米)</th>
				 	<td><input name="width" id="width" validType="length[1,9]" maxLength="9" placeholder="请输入商品宽度" class="easyui-textbox easyui-validatebox" type="text"/></td>
				</tr>
				<tr>
					 	<th>高(厘米)</th>
				 	<td><input name="hight" id="hight" validType="length[1,9]" maxLength="9" placeholder="请输入商品高度" class="easyui-textbox easyui-validatebox" type="text"/></td>
					 	<th><b class="red">*</b>分类名称</th>
					<td><input name="goodsCategoryId" validType="length[1,10]" maxLength="10" class="easyui-textbox" id="goodsCategoryId" type="text" required="required"/><input name="categoryName" id="categoryName"  type="hidden"/></td>				 		
				</tr>
				 <tr>
<!-- 				 	<th><b class="red">*</b>有效期(分钟)</th> -->
<!-- 				 	<td><input name="validTimes" id="validTimes" validType="length[1,9]" placeholder="请输入有效期" class="easyui-textbox easyui-validatebox" type="text" required="required"/></td> -->
					<th><b class="red">*</b>是否启用</th>
					<td>
						<select id="goodsStatus" class="easyui-combobox" name="goodsStatus" style="width:170px;" data-options="required:true">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>				
				 </tr>
				 <tr>
				    <th>商品描述</th>
					<td colspan="3">
						<textarea class="easyui-textbox" id="goodsMemo" name="goodsMemo"  style="width: 300px;height: 68px;resize:none;" ></textarea>
						<em id="word">字数限制：100</em>
					</td>
				 </tr>
			 </table>
		</form>
	</div>
</div>
