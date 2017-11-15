<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/refund/css/tuikuan.css">
</head>
<body>
<div class="table" id="Partition"></div>
<div id="tool" >
	<div class="action cle">
		<div class="lf">商户:
			<select class="shop" name="companyId" data-options="required:true" id="companyId" onchange="changeModel()">
				<c:forEach items="${data}" var="company">
						<option value="${company.companyId}">${company.companyName}</option>
	  	  		</c:forEach>
			</select>
		</div>
		<div class="lf">模型:
			<select class="shop" name="areaModelId" data-options="required:true" id="areaModelId">
				<option value="">全部</option>
				<c:forEach items="${dataModel}" var="model">
						<option value="${model.areaModelId}">${model.areaModelName}</option>
	  	  		</c:forEach>
			</select>
		</div>
		<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-search' plain='true' onclick="searchfo()">查询</a>
	</div>
	<div class="a">
		<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-add' plain='true' onclick="addnew()">新增</a>
		<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-edit' plain='true' onclick="eadit()">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-remove' plain='true' onclick="detelnode()">删除</a>
	</div>
</div>

<script type="text/javascript" src="<%=basePath%>views/modelcleangoods/js/modelcleangoods.js"></script>
<script type="text/javascript" src="<%=basePath%>views/modelcleangoods/js/modelcleangoods_dialog.js"></script>
<script type="text/javascript">
function changeModel(){
	$.ajax({
		url:"modelcleangoods/getAreaModel",
		data:{companyId:$("#companyId").val()},
		dataType:'json',
		type:'post',
		success:function(data){
			$("#areaModelId").html("");
			$("#areaModelId").append("<option value=''>全部</option>");
			if(data.length !=0){
				for(var i=0;i<data.length;i++){
					$("<option value='" + data[i].areaModelId + "'>" + data[i].areaModelName + "</option>").appendTo($("#areaModelId"));
				}
			}
			
		}
	});
}

</script>

</body>
</html>