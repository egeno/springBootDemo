<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <title>二维码</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<script type="text/javascript" src="<%=basePath%>views/code/js/code.js"></script>
  </head>
  <style>
	  table{border-right:1px solid #ccc;border-bottom:1px solid #ccc;}
	  td{border-left:1px solid #ccc;border-top:1px solid #ccc} 
	 .one td{height:30px;line-height: 30px;text-align: center}
	 .two td{height:150px;width:150px;text-align: center}
	 .three td{height:30px;line-height: 30px;width:150px;}
	 .three td a{text-align: center}
	</style>
  <body>
  	<div id="panel"  data-options="border:false">
  		<div id="tb" style="padding:5px">
	  		<table>
			<tr class='one'>
				<td>IOS</td>
				<td>Android</td>
			</tr>
			<tr class='two'>
				<td>
					<div id="imageDiv" class="gallery">
						<c:choose>
							<c:when test="${code.iosPic!=null&& code.iosPic!=''}">
								<img src="<%=basePath%>${code.iosPic}" style="width: 130px;height: 130px;">
							</c:when>
							<c:otherwise>
								暂无图片
							</c:otherwise>
						</c:choose>
					</div>
				</td>
				<td>
					<div id="imageDiv" class="gallery">
						<c:choose>
							<c:when test="${code.androidPic!=null&& code.androidPic!=''}">
								<img src="<%=basePath%>${code.androidPic}" style="width: 130px;height: 130px;">
							</c:when>
							<c:otherwise>
								暂无图片
							</c:otherwise>
						</c:choose>
					</div>
				</td>
			</tr>
			<tr class='three'>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:10px" onclick="addIos();">上传</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:5px" onclick="deleteIos();">删除</a>
				</td>
				<td>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:10px" onclick="addAndroid();">上传</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" style="margin-left:5px" onclick="deleteAndroid();">删除</a>
				</td>
			</tr>
		</table>
	  	</div>
	</div>
  </body>
</html>
