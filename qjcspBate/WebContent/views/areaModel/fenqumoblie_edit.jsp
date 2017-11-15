<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>fenqumoblie_edit_dialog</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<script type="text/javascript" src="<%=basePath%>views/areaModel/js/fenqumoblie_dialog.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 10px;">
			<form id="editForm" method="post">
				<table style="width: 100%;">
					<tr>
						<th>模型名称</th>
						<td style="width: 350px;">
							<input type="text" name="areaModelId" value="${area.areaModelId}" hidden="true" class="fenquname">
							<input type="text" name="areaModelName" value="${area.areaModelName}" validType="length[1,4]" maxLength="4" class="easyui-textbox easyui-validatebox" required="required" >
						</td>
					</tr>
					<tr>
						<th>分区模型</th>
						<td>
							<select class="easyui-combobox toolbtn" name="areaModelType" disabled="disabled" style="width:170px;" data-options="required:true" id="areaModelType">
								<c:choose>
									<c:when test="${area.areaModelType==1}">
										<option value="1" selected="selected">加热</option>
										<option value="0" >制冷</option>
										<option value="2" >冷热一体</option>
									</c:when>
									<c:when test="${area.areaModelType==2}">
										<option value="1" >加热</option>
										<option value="0" >制冷</option>
										<option value="2" selected="selected">冷热一体</option>
									</c:when>
									<c:otherwise >
										<option value="1" >加热</option>
										<option value="0" selected="selected">制冷</option>
										<option value="2" s>冷热一体</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
					</tr>
					<%-- <tr>
						<th>适用行数</th>
						<td>
							<input type="text" name="areaModelRow" value="${area.areaModelRow}" disabled="disabled" validType="account[1,16]" class="easyui-textbox easyui-validatebox" required="required">
						</td>
					</tr>
					<tr>
						<th>适用列数</th>
						<td>
							<input type="text" name="areaModelColumn" value="${area.areaModelColumn}" disabled="disabled" validType="account[1,16]" class="easyui-textbox easyui-validatebox" required="required">
						</td>
					</tr> --%>
					<tr>
					 	<th>分区描述</th>
						<td>
							<textarea class="easyui-textbox" id="areaModelMemo" name="areaModelMemo"  style="width: 170px;height: 68px;resize:none;" >${area.areaModelMemo}</textarea>
							<em id="word">字数限制：100</em>
						</td>
					 </tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#areaModelType").combobox({
				editable:false
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
	</script>
</body>
</html>