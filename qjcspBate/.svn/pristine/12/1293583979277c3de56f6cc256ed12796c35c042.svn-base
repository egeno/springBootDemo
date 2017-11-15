<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$(function() {
		$("#form").form({
			url :"goods/persistenceGoodsModelEdit", //编辑、添加模型
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				var checkSpan = $('#checkSpan');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				if(checkSpan.html()=="该手机号已存在"){
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
		$("#areaModelMemo").keyup(
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
			<input name="areaModelId" id="areaModelId"  type="hidden"/>
			 <table>
				 <tr>
						<th><b class="red">*</b>模型名称</th>
						<td style="width: 350px;">
							<input class="easyui-textbox easyui-validatebox" type="text" name="areaModelName"  required="required" validType="length[1,4]" maxLength="4" id="areaModelName">
							<em id="addSpan"></em>
						</td>
					</tr>
					<tr>
						<th><b class="red">*</b>模型类型</th>
						<td>
							<select class="easyui-combobox toolbtn" name="areaModelType" id="areaModelType" style="width:170px;" required="required">
								<%-- <c:forEach items="${areaModeldata}" var="areaModel">
						  	 		<option value="${areaModel.areaModelId}">${areaModel.areaModelName }</option>
					  	  		</c:forEach> --%>
								<option value="1">加热</option>
								<option value="0">制冷</option>
								<option value="2">冷热一体</option>
							</select>
						</td>
					</tr>
					<%-- <tr>
						<th><b class="red">*</b>商品</th>
						<td>
							<select class="easyui-combobox" name="goodId" id="goodId" style="width:170px;" required="required">
								<c:forEach items="${goodsListsdata}" var="goods">
						  	 		<option value="${goods.goodId}">${goods.goodName}</option>
					  	  		</c:forEach>
							</select>
						</td>
					</tr> --%>
					
<!-- 					<tr>
					    <th><b class="red">*</b>适用层数</th>
					    <td><input class="easyui-textbox easyui-validatebox" type="text" name="areaModelRow"  required="required" validType='areaGoodNumCount'></td>
					</tr>
					<tr>
						<th><b class="red">*</b>适用列数</th>
						<td><input class="easyui-textbox easyui-validatebox" type="text" name="areaModelColumn"  required="required" validType='areaGoodNumCount'></td>
					</tr> -->
					<tr>              
					 	<th>描述</th>
						<td>
							<textarea class="easyui-textbox" id="areaModelMemo" name="areaModelMemo"  style="width: 170px;height: 68px;resize:none;" ></textarea>
							<em id="word">字数限制：100</em>
						</td>
					</tr>
			 </table>
		</form>
	</div>
</div>
