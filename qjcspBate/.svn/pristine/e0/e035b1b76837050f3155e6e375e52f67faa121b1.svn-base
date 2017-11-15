<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setBufferSize(402800);
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>产品类型</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include  file="/layout/script.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/preissue/css/preIndex.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>views/preissue/css/reset.css">
<%-- 	<script type="text/javascript" src="<%=basePath%>views/preissue/js/jquery.json.js"></script>
	<script type="text/javascript" src="<%=basePath%>views/preissue/js/jquery.min.js"></script> --%>
	<script type="text/javascript" src="<%=basePath%>views/preissue/js/preIndex.js"></script>
</head>
<body>
<div class="preissueBox">
	<div class="preissueTop">
		<ul class="cle">
			<li class="Week"><div></div></li>
			<li class="Week"><div></div></li>
			<li class="Week"><div></div></li>
			<li class="Week"><div></div></li>
			<li class="Week"><div></div></li>
			<li class="Week"><div></div></li>
			<li class="Week"><div></div></li>
			<li class="lookWeek this"><div class='next-week'>查看下周</div><div class='last-week'>查看本周</div></li>
		</ul>
	</div>
	<div class="preissueBottom">
		<!-- 弹窗-->
		<div class='allDialog'>
			<%-- <div class="updateDialog dialog">
				<div class="updateDialog_main">
					<div class="mealNum"><span class="updateMealName">特惠秘制双拼叉烧肉套餐</span><input class='mealsnum' type="text" name="updateMealNum"/></div>
					<div class="no"><img src="<%=basePath%>views/preissue/image/indexno.png"></div><div class="yes"><img src="<%=basePath%>views/preissue/image/indexyes.png"></div>
				</div>
			</div> 
			<div class="updateSuccessDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/updateSuccess.png">
				</div>
			</div>
			<div class="updateFailDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/updateFail.png">
				</div>
			</div>--%>
			<div class="deleteDialog dialog">
				<div class="deleteDialog_main">
					<div class="deleteDialogText">是否要删除“<em>特惠秘制双拼叉烧肉套餐</em>”？</div>
					<div class="no"><img src="<%=basePath%>views/preissue/image/indexno.png"></div><div class="yes"><img src="<%=basePath%>views/preissue/image/indexyes.png"></div>
				</div>
			</div>
			<div class="deleteSuccessDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/deteleSuccessDialog.png" class="Success">
					<%-- <img src="<%=basePath%>views/preissue/image/close.png" class="close"> --%>
				</div>
			</div>
			<div class="deleteFailDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/deleteFail.png">
				</div>
			</div>
			<div class="inputDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/inputDialog.png">
					<img src="<%=basePath%>views/preissue/image/close.png" class="close">
				</div>
			</div>
			<div class="dateDialog dialog">
				<div class="dateDialog_main">
					<img src="<%=basePath%>views/preissue/image/close.png" class="close">
					<div class="weekBox">
						<div class="week">
							<div class="thisWeek">本周</div><div class="nextWeek">下周</div>
						</div>
						<ul class="dateWeek">
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
							<!--下周-->
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
							<li><div></div></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- 弹窗结束-->
		<div class='preissueBottomMain'>
		<div class='preissueBottomMain_content'>
			<div class='showname'></div>
			<div class="preissueBottomLeft">
				<!-- <div class="breakfast gray">早餐</div>
				<div class="lunch white">中餐</div>
				<div class="dinner gray">晚餐</div>
				<div class="supper white">夜宵</div> -->
			</div>
			<div class="preissueBottomContent">
				<%-- <div class="breakfastMeal mm">
					<div class="mm_main">
						<ul class="Meal_main">
							<li>
								<div class="mealName">
									<span class="name">特惠秘制双拼叉烧肉套餐</span>
									<span class="num"><em>2000</em>份</span>
								</div>
							</li> -->
						</ul>
					</div>
				</div> --%>
			</div>
		</div>
		</div>
		<div class="preissueBottomRight">
			<div class="releaseBtn">发布</div>
		</div>
	</div>
	
</div>
</div>
<!-- 发布页 -->
<div class="releaseMainDialog">
	<!-- 发布成功弹窗 -->
	<div class="releasemreleasemSuccess  dialog">
		<div class="releasemSuccess_main">
			<img src="<%=basePath%>views/preissue/image/releasemSuccess.png">
		</div>
	</div>
	
	
	<div class="releaseTop">
		<div class="releaseLeft">
			<div class='releaseLeft_top'>
				<img src="<%=basePath%>views/preissue/image/releaseDateDg.png">
				<div class="ChoiceDate">已选时间 :<span class="ChoiceDate_day"></span>&nbsp;&nbsp;(<span class="ChoiceDate_week"></span>)<span style="display: none;" class="realDate"></span></div>
			</div>
			<div class='releaseLeft_bottom'>
				<div class="releaseLeft_bottom_tab_main">
					<ul class="releaseLeft_bottom_tab cle">
						<!--<li class="activeli" value='0'><div>早餐</div></li>
						<li value='1'><div>中餐</div></li>
						<li value='2'><div>晚餐</div></li>
						<li value='3'><div>夜宵</div></li> -->
					</ul>
				</div>
				<div class='showname'></div>
				<div class="releaseLeftMeal">
					<%-- 
					<ul class="supper" value='3'>
						<li>
							<img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png">
							<div class="releaseLeftMeal_main">
								<span class='releaseLeftMeal_name'>啊啊啊飒飒大师</span>
								<span class='releaseLeftMeal_num'><em>200</em>份</span>
							</div>
						</li>
					</ul> --%>
				</div>
				<div class="releaseLeftClear"><div class="clearBtn">全部清空</div></div>
			</div>
		</div>
		<div class="releaseRight">
			<!--发布页弹窗-->
			<div class="releasemDeleteDialog dialog">
				<div class="deleteDialog_main">
					<div class="deleteDialogText">是否要删除“<em>特惠秘制双拼叉烧肉套餐</em>”？</div>
					<div class="no"><img src="<%=basePath%>views/preissue/image/indexno.png"></div><div class="yes"><img src="<%=basePath%>views/preissue/image/indexyes.png"></div>
				</div>
			</div>
			<%-- 
			点击修改 --%>
			<div class="releasemUpdateDialog dialog">
				<div class="updateDialog_main">
					<div class="mealNum"><span class="updateMealName">特惠秘制双拼叉烧肉套餐</span><input class='mealsnum' type="text" name="updateMealNum"/></div>
					<div class="no"><img src="<%=basePath%>views/preissue/image/indexno.png"></div><div class="yes"><img src="<%=basePath%>views/preissue/image/indexyes.png"></div>
				</div>
			</div>
			
			<div class="releasemUpdateSuccessDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/updateSuccess.png">
				</div>
			</div>
			<div class="releasemUpdateFailDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/updateFail.png">
				</div>
			</div>
			<div class="releasemDeleteSuccessDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/deteleSuccessDialog.png" class="Success">
					<%-- <img src="<%=basePath%>views/preissue/image/close.png" class="close"> --%>
				</div>
			</div>
			<div class="releasemDeleteFailDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/deleteFail.png">
				</div>
			</div>
			<div class="releasemInputDialog dialog">
				<div>
					<img src="<%=basePath%>views/preissue/image/inputDialog.png">
					<img src="<%=basePath%>views/preissue/image/close.png" class="close">
				</div>
			</div>
			<!--发布页弹窗结束-->
			<div class="releaseRight_tab">
				<ul class="cle">
					<li class="tabActive"><img src="<%=basePath%>views/preissue/image/tabActive.png"><div>全部</div></li>
				</ul>
			</div>
			<div class="releaseRight_meal">
				<ul class="cle">
					<%-- <li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>咳咳咳</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>啊啊啊肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li>
					<li><img src="<%=basePath%>views/preissue/image/releaseLeftMealBg.png"><div>特惠秘制双拼叉烧肉套餐</div></li> --%>
				</ul>
			</div>
		</div>
	</div>
	<div class="releaseBottom">
		<div class="releaseDialogBtn">发布</div>
		<div class="back">返回</div>
	</div>
</div>
</body>
</html>