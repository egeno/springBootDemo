<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include  file="../include/ctxPathInclude.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>零售新建模板</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<jsp:include page="/layout/script.jsp"></jsp:include>
		
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/reset2.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/cupboard.css">
		<script type="text/javascript" src="<%=path%>/views/template/js/cupboard.js"></script> 
		
	</head>
	<body>
		<div class="modeMain">
			<!--选择机柜弹窗 start-->
			<div class="dialog none">
				<div class='choicebox dialogmain'>
					<div class="popup">
						<div class="jigui">选择机柜</div>
						<ul class="choice">
<!-- 							<li>女生A寝室111</li>
							<li>女生B寝室</li>
							<li>女生C寝室</li>
							<li>女生E寝室</li>
							<li>女生D寝室</li>
							<li>女生F寝室</li>
							<li>女生G寝室</li>
							<li>女生H寝室</li>
							<li>女生I寝室</li>
							<li>女生J寝室</li>
							<li>女生K寝室</li>
							<li>女生L寝室</li>
							<li>女生M寝室</li>
							<li>女生N寝室</li>
 -->							
						</ul>
						<div class="bottom">
							<div class="choice_concle btnConcle">取消</div>
							<div class="choice_sure btnSure">确定</div>
						</div>
					</div>
				</div>
			</div>
			<!--选择机柜弹窗 end-->
			<div class="modeTop">
				<!--删除弹窗 start-->
				<div class="dialog none">
					<div class="deleteName dialogmain">
						<div class="deleteName_top top">
							<div class="deleteName_title tit">温馨提示</div>
							<div class="deleteName_text inp">是否要删除“<em class="foodName">特惠秘制双拼叉烧肉套餐</em>”？</div>
						</div>
						<div class="deleteName_bottom bottom">
							<div class="deleteName_concle btnConcle">取消</div>		
							<div class="deleteName_sure  btnSure">确定</div>
						</div>
					</div>
				</div>
				<!--删除弹窗 end-->
				
				
				
				<!--模板选择 start-->
				<div class="modeLeft">
					<div class="modeTitle">
						<img src="<%=path%>/static/images/titlebg.png" alt="标题背景" class="titlebg">
						<p><span class='modeTitle_name' id="changeId" value="${machineId}">${machineName}</span><span class="change">修改</span></p>
					</div>
					<div class='showname'></div>
					<div class="modeAllot">
						<div class="modeAllot_tab">
							<ul class="cle">
								<!-- <li><div>早餐<em></em></div></li>
								<li><div>点心<em></em></div></li>
								<li><div>中餐<em></em></div></li>
								<li><div>晚餐<em></em></div></li>
								<li><div>夜宵<em></em></div></li>  -->
							</ul>
						</div>
						<div class="modeAllot_main">
							<!-- <ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">特惠秘制双拼叉烧肉套餐</div>
									<div class="pct lr"><em>10</em>份</div>
								</li> 
							</ul>
							
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">aaa</div>
									<div class="pct lr"><em>20</em>份</div>
								</li> 
							</ul>
							
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">bbb</div>
									<div class="pct lr"><em>15</em>份</div>
								</li>
							</ul>
							
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">特惠秘制双拼叉烧肉套餐</div>
									<div class="pct lr"><em>12</em>份</div>
								</li> 
							</ul>
							
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">ccc</div>
									<div class="pct lr"><em>2</em>份</div>
								</li>
							</ul> -->
						</div>
						<div class="modeAllot_botm">
							剩余可添加餐品数量：<em>0</em>份
						</div>
					</div>
				</div>
				<!--模板选择 end-->
				
				
				<!--餐品列表 start-->
				<div class="modeRight">
					<div class="dialog none">
						<div class="mealAllot dialogmain">
							<div class="mealAllot_top top">
								<div class="mealAllot_title tit">请设置套餐比例</div>
								<div class="mealAllot_input inp"><span class="lf inpName">特惠秘制双拼叉烧肉套餐</span><span class="inpText"><input type="text"/>份</span></div>
							</div>
							<div class="mealAllot_bottom bottom">
								<div class="mealAllot_concle btnConcle">取消</div>		
								<div class="mealAllot_sure btnSure" id="sure">确定</div>
							</div>
						</div>
					</div>
					<div class="modeRight_tab">
						<ul class="cle">
							<li class=""><img src="<%=path%>/static/images/tabActive.png"/><div>全部</div><input class='mealsnum' type='hidden' value=""></input></li>
							
							<%-- <li value="" class="data"><img src="<%=path%>/static/images/releaseRightTabBg.png" alt="" /><div>套餐</div></li>
							<li value="" class="data"><img src="<%=path%>/static/images/releaseRightTabBg.png" alt="" /><div>主食</div></li>
							<li value="" class="data"><img src="<%=path%>/static/images/releaseRightTabBg.png" alt="" /><div>零食</div></li>
							<li value="" class="data"><img src="<%=path%>/static/images/releaseRightTabBg.png" alt="" /><div>饮料</div></li>
							<li value="" class="data"><img src="<%=path%>/static/images/releaseRightTabBg.png" alt="" /><div>坚果</div></li> --%> 
						</ul>
					</div>
					
					
					<div class="modeRight_meal">
						<ul class="cle">
							<!--<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>粑粑味的巧克力</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>睡觉撒就啥都看</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>香酥米糕01</div>
							</li>
							<li>
								<span></span>
								<input id="gid" type="hidden" value="">
								<input id="amid" type="hidden" value="">
								<div>特惠秘制双拼叉烧肉套餐</div>
							</li> -->
						</ul>
					</div>
				</div>
				<!--餐品列表 end-->
			</div>
			<div class="modeBotm">
				<div class="SureBtn" onclick="queding()">确定</div>
				<div class="back"><a href="newTemplate/goodsRetail">返回</a></div>
			</div>
		</div>
	</body>
</html>