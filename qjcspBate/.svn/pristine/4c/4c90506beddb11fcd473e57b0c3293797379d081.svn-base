<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>取餐记录查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<style type="text/css">
		.easyui-textbox{
			height: 20px;
			width: 167px;
			line-height: 16px;
		    border-radius: 3px 3px 3px 3px;
		    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
		    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
		}
	</style>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/pick/css/tuikuan.css">
</head>
<body>
<div class="table" id="Partition"></div>
<div id="tool" style="height: 85px;">
	<div class="action cle">
		<div class="lf">
			取餐时间：
			<input type="text" class= "easyui-datebox begin" data-options="editable:false" style="width: 100px;height: 25px;" id="pickStartTime" name="pickStartTime"> 
			 ~
			<input type="text" class= "easyui-datebox end" data-options="editable:false" style="width: 100px;height: 25px;" id="pickEndTime" name="pickEndTime"> 
		</div>
		<div class="lf">菜品名称：<input type="text" class="easyui-textbox" id="goodsName" name="goodsName"></div>
	</div>
	<div class="action cle" style="margin-top: 5px;">
	    <div class="lf">
	    	&nbsp;&nbsp;&nbsp;&nbsp;加盟商：
			<select id="companyId" name="companyId" class="easyui-combobox" data-options="editable:false" style="width: 213px;height: 25px;">
				 <c:choose>
	               	<c:when test="${companyData ne null }">
	               		<c:forEach items="${companyData}" var="item">
							<option value="${item.companyId }">${item.companyName}</option>
			    		</c:forEach>
					</c:when>
				</c:choose>	
			</select>
	    </div>
		<div class="lf">
			设备名称：
			<select id="machineId" name="machineId" class="easyui-combobox" data-options="editable:false" style="width: 170px;height: 25px;">
				 <c:choose>
	               	<c:when test="${data ne null }">
	               	  <option value="">全部</option>
	               		<c:forEach items="${data}" var="item">
							<option value="${item.machineId }">${item.machineName }</option>
			    		</c:forEach>
					</c:when>
					<c:otherwise>
						<option value="">全部</option>
					</c:otherwise>
				</c:choose>	
			</select>
		</div>
	   </div>
	   <div class="action cle" style="margin-top: 5px;">
		<div class="lf">订单编号：<input type="text" class="easyui-textbox" id="orderNum" name="orderNum" style="width: 213px;height: 25px;"></div>
		<div class="lf">
			&nbsp;&nbsp;
			<a href="javascript:void(0);" class="easyui-linkbutton" iconcls="icon-search" onclick="searchfo()">查询</a>
		</div>
    </div>
</div>
<script type="text/javascript" src="<%=basePath%>views/pick/js/pick.js"></script>
<script type="text/javascript" src="<%=basePath%>views/pick/js/pick_dialog.js"></script>
<script type="text/javascript">
$('#companyId').combobox({    
    onChange : function(){
    	$("#machineId").combobox('clear');
		$("#machineId").combobox('loadData',[{}]);
		var companyId=$("#companyId").combobox('getValue');
    	$.ajax({
			url:"pickSearch/getMachine",
			data:{companyId:companyId},
			dataType:'json',
			type:'post',
			success:function(data){
//					alert(data[]);
				if(data.length!=0){
					$("#machineId").combobox({
						data:data,
						valueField:'machineId',
						textField:'machineName'
					});
					$("#machineId").combobox('select','全部');
				}else{
					$("#machineId").combobox('select','全部');
				}
			}
		});
    	
    }  
});
	

</script>

</body>
</html>