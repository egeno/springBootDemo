<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	
    <title>编辑商品分类信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>	
	<script type="text/javascript" src="<%=basePath%>views/goodsInfo/editIngredientsType/editIngredients.js"></script>
  </head>
	<body>
		<div id="bigbox">
		<div id="foodList">
			<p class="addFoodBox"><span>添加食材类型分类 </span></p>
			<ul class="foodList_main">
				<li class='cle main'>
					<div class="foodLeft">
						<div class="foodType">
							<label>食材类型:</label><input type="text" class="foodTypeName"/>
							<p class="addFoodContent"><span>添加 </span></p>
						</div>
						<div class="foodContent">
							<div class="foodContent_list">
								<ul>
									<li class="line">
										<p><label>成份:</label><input type="text" class="ingredientName"/><label>质量:</label><input type="text" class="grams"/><span class="delIngredient">删除</span></p>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="foodRight">
						<div class="delBox">删除</div>
					</div>
				</li>
			</ul>
		</div>
		</div>
	</body>
</html>









