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
		
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/reset.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/index.css">
		<script type="text/javascript" src="<%=path%>/views/retail/js/retail.js"></script>
		<!-- <script type="text/javascript">
			window.load = function(){
				var sure = document.getElementById("sure");
				sure.click = function(){
					window.location.href = "/retail/sure?templateName = templateName";
				}
			}
			
		</script> -->
		<script type="text/javascript">
		$(function(){
			$("#templateName").val($("#getTemplateName").val());
		});
		</script>
		
	</head>
	<body>
		<div class="modeMain">
			<!--设置模板名称 start-->
			<div class="dialog">
				<div class="modeName dialogmain">
					<div class="modeName_top top">
						<div class="modeName_title tit">请设置模板名称 <input id="getTemplateName"  type="hidden" value="${templateName}"/><input id="getTemplateId"  type="hidden" value="${templateId}"/></div>
						<div class="modeName_input inp">模板名称： <input class="easyui-textbox easyui-validatebox" placeholder="请输入模板名称" id="templateName" name="templateName" type="text" style="width: 180" value="" validType="length[0,10]"/></div>
						<div><a id="checkSpan" style="color: red;font-size:12px"></a></div>
					</div>
					<div class="modeName_bottom bottom">
						<div class="modeName_concle btnConcle">取消</div>		
						<div class="modeName_sure  btnSure">确定</div>
						
					</div>
					
				</div>
			</div>
			<!--设置模板名称 end-->
			<div class="modeTop">
				<!--模板比例没有100%提示 start-->
				<div class="dialog none">
					<div class="remind dialogmain">
						<img src="<%=path%>/static/images/concle.png" alt="关闭" class="concle"/>
						<div class="remind_top top">
							<div class="remind_title tit">温馨提示</div>
							<div class="remind_text txt"><p>请设置完整的模板比例！</p><p>（完整比例为100%）</p></div>
						</div>
					</div>
				</div>
				<!--模板比例没有100%提示 end-->
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
						<p><span class='modeTitle_name' id="changeId" ></span><span class="change">修改</span></p>
					</div>
					<div class='showname'></div>
					<div class="modeAllot">
						<div class="modeAllot_tab">
							<ul class="cle">
								<!-- <li><div>早餐<em></em>%</div></li>
								<li><div>点心<em></em>%</div></li>
								<li><div>中餐<em></em>%</div></li>
								<li><div>晚餐<em></em>%</div></li>
								<li><div>夜宵<em></em>%</div></li> -->
							</ul>
						</div>
						<div class="modeAllot_main">
							<!-- <ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">特惠秘制双拼叉烧肉套餐</div>
									<div class="pct lr"><em>20</em>%</div>
								</li>
							</ul>
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">aaa</div>
									<div class="pct lr"><em>20</em>%</div>
								</li>
							</ul>
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">bbb</div>
									<div class="pct lr"><em>20</em>%</div>
								</li>
							</ul>
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">特惠秘制双拼叉烧肉套餐</div>
									<div class="pct lr"><em>20</em>%</div>
								</li>
							</ul>
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">ccc</div>
									<div class="pct lr"><em>20</em>%</div>
								</li>
							</ul>
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">ccc</div>
									<div class="pct lr"><em>20</em>%</div>
								</li>
							</ul>
							<ul>
								<li class="cle">
									<span class="bor lf"></span>
									<div class="mealName lf">ccc</div>
									<div class="pct lr"><em>20</em>%</div>
								</li>
							</ul> -->
						</div>
						<div class="modeAllot_botm">
							已设置比例：<em>0</em>%
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
								<div class="mealAllot_input inp"><span class="lf inpName">特惠秘制双拼叉烧肉套餐</span><span class="inpText"><input id="percentCheck" type="text"/>%</span></div>
							</div>
							<div class="mealAllot_bottom bottom">
								<div class="mealAllot_concle btnConcle">取消</div>		
								<div class="mealAllot_sure btnSure" id="sure">确定</div>
							</div>
						</div>
					</div>
					<div class="modeRight_tab">
						<ul class="cle">
							<li class=""><img src="<%=basePath%>/static/images/tabActive.png"><div>全部</div><input class='mealsnum' type='hidden' value=""></input></li>
							
							<%-- <li value="" class="data"><img src="<%=basePath%>/static/images/releaseRightTabBg.png"><div>套餐</div></li>
							<li value="" class="data tabActive"><img src="<%=basePath%>/static/images/tabActive.png"><div>主食</div></li>
							<li value="" class="data"><img src="<%=basePath%>/static/images/releaseRightTabBg.png"><div>零食</div></li>
							<li value="" class="data"><img src="<%=basePath%>/static/images/releaseRightTabBg.png"><div>饮料</div></li>
							<li value="" class="data"><img src="<%=basePath%>/static/images/releaseRightTabBg.png"><div>坚果</div></li> --%>
						</ul>
					</div>
					
					
					<div class="modeRight_meal">
						<ul class="cle">
							<!-- <li>
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
				<div class="back"><a href="goodsTemplate/toGoodsTemplate">返回</a></div>
			</div>
		</div>
	</body>
</html>