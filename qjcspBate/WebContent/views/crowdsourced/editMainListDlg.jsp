<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	$(function() {
		$("#companyStatus").combobox({
			required:true,    
            editable:false, 		
		});
		
		/* $("#pid").combotree({
			url:"crowdsourced/findMainList",
			idFiled:'id',
		 	textFiled:'companyName',
		 	parentField:'pid',
		 	onSelect:function(node){
		 		$("#pCompanyName").val(node.text);
		 	}
		}); */
		
		
		/* var cityName = $('#cityName').val(); */
		/* var cityName = $('#cityCode').combobox('getValue');
		/* var province = $('#province').val(); */
		/* var province = $('#provCode').combobox('getValue'); */
		
		setTimeout(function(){
			var cityId = $('#cityCode').combobox('getValue');
			$("#companyName").combobox({
				url:"crowdsourced/findUniversityList?cityId=" + cityId,
				idFiled:'id',
			 	textFiled:'name',
			 	/* success:function(data){
			 		alert(data[0].id);
			 		$companyId.combobox('setValue',data.id);
		        } */
			 	onSelect: function (data){		 
					$("#companyId").val(data.id);
				}
			});
		
		},300);
		

		$("#form").form({
			url :"crowdsourced/persistenceCrowdsourcedDlg",
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
<script type="text/javascript" src="<%=basePath %>views/crowdsourced/js/address.js"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath %>static/css/demo.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/themes/bootstrap/easyui.css">
<!-- <script type="text/javascript" src="../../jquery.min.js"></script>
<script type="text/javascript" src="../../jquery.easyui.min.js"></script> -->



<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
		<input name="tempId" id="tempId"  type="hidden" value="<%=request.getParameter("tempId") %>"/>
		<form id="form" method="post">
			<input name="id" id="id"  type="hidden"/>
			<input name="universityid" id="universityid"  type="hidden"/>
			<input name="creater" id="creater"  type="hidden"/>
			 <table>
			 	<tr>
					<th><b class="red">*</b>所在省份</th>
					<td>
						<input name="provCode" id="provCode" class="easyui-combobox" required="required" data-options="width:170,valueField:'id',textField:'name',editable:false"/>
						<input name="province" id="province" type="hidden"/>
					</td>
					<th><b class="red">*</b>所在城市</th>
					<td>
						<input name="cityCode" id="cityCode" class="easyui-combobox" required="required" data-options="width:170,valueField:'id',textField:'name',editable:false"/>
						<input name="cityname" id="cityname" type="hidden"/>
					</td>
				 </tr>
				<tr>
					<th><b class="red">*</b>区/县</th>
					<!-- <td><input name="companyName" id="companyName" validType="length[0,30]" class="easyui-textbox easyui-validatebox" type="text" required="required" data-options="loader: btsloader,mode: 'remote',valueField: 'id',textField: 'name'"/></td>
					<td> -->
					<!-- <div style="margin:20px 0"></div> -->
					
					<td><input id="zonename" name="zonename" class="easyui-textbox easyui-validatebox" validType="length[0,30]" required="required"/></td>
				
					<!-- <input id="costId" name="costId" class="easyui-combobox" style="width:250px;" data-options="loader: btsloader,mode: 'remote',valueField: 'id',textField: 'name'" />  -->
					<!-- <th>父公司名称</th>
					<td>
						<input name="pid" class="easyui-textbox easyui-validatebox" id="pid" style="width: 170px;"/>
						<input name="pCompanyName" id="pCompanyName" type="hidden"/>
					</td> -->
					<th><b class="red">*</b>区域</th>
					<td><input id="universityname" name="universityname" class="easyui-textbox easyui-validatebox" validType="length[0,30]" required="required"/></td>
					
				</tr>
				
				<!--  <tr>
					<th>开户银行</th>
					<td><input id="backNo" name="backNo" type="text" validType="length[0,20]" class="easyui-textbox easyui-validatebox"/></td>
					<th>开户支行</th>
					<td><input id="backName" name="backName" type="text" validType="length[0,20]" class="easyui-textbox easyui-validatebox"/></td>
				 </tr>
				 <tr>
					<th>户名</th>
					<td><input id="cardName" name="cardName" type="text" validType="length[0,20]" class="easyui-textbox easyui-validatebox"/></td>
					<th>账户</th>
					<td><input id="cardNo" name="cardNo" type="text"  validType="length[0,20]" class="easyui-textbox easyui-validatebox"/></td>
				 </tr>
				 <tr>
					<th><b class="red">*</b>电话</th>
					<td><input id="tel" name="tel" type="text" required="required" validType="phoneRex" class="easyui-textbox easyui-validatebox"/></td>
					<th>传真</th>
					<td><input id="telautogram" name="telautogram" type="text" class="easyui-textbox easyui-validatebox"/></td>
				 </tr>
				 <tr>
					<th>电子邮箱</th>
					<td><input id="email" name="email" type="text" class="easyui-textbox easyui-validatebox"/></td>
					<th>经营时间</th>
					<td><input id="businessHours" name="businessHours" type="text" class="easyui-textbox easyui-validatebox"/></td>
				 </tr> -->
				 <tr>
					<th><b class="red">*</b>区域全拼</th>
					<td><input id="spellall" name="spellall" required="required" validType="length[0,20]" type="text" class="easyui-textbox easyui-validatebox"/></td>
					<th><b class="red">*</b>区域简拼</th>
					<td><input id="spellshort" name="spellshort" required="required" validType="length[0,10]" type="text" class="easyui-textbox easyui-validatebox"/></td>
				 </tr>
				 <tr>
				    <!-- <th>公司编号</th>
					<td><input name="companyCode" id="companyCode" type="text" class="easyui-textbox easyui-validatebox" /></td> -->
					
					<th>经 / 纬度</th>
					<td>
						<input id="longitude" name="longitude" placeholder="经度" class="easyui-numberbox easyui-validatebox" data-options="min:0,max:180,precision:10" style="width: 77px;"/> / 
						<input id="latitude" name="latitude" placeholder="纬度" class="easyui-numberbox easyui-validatebox" data-options="min:0,max:90,precision:10" style="width: 77px;"/>
					</td>
					<!-- <th>公司法人</th>
					<td><input id="corporation" name="corporation" type="text" validType="length[0,20]" class="easyui-textbox easyui-validatebox"/></td> -->
				 </tr>
				 <!-- <tr>
				 	<th><b class="red">*</b>所在区县</th>
					<td>
						<input name="areaCode" id="areaCode" class="easyui-combobox" required="required" data-options="width:170,valueField:'id',textField:'name',editable:false"/>
						<input name="areaName" id="areaName" type="hidden"/>
					</td>
					<th><b class="red">*</b>是否启用</th>
					<td>
						<select id="companyStatus" class="easyui-combobox" name="companyStatus" style="width:170px;" data-options="required:true" >
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>
				 </tr> -->
			 </table>
		</form>
	</div>
</div>
<script type="text/javascript" src="<%=basePath %>views/crowdsourced/js/fuzzyQuery.js"/>
<script type="text/javascript">
	province("");
</script>
	