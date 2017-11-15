<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include  file="/views/include/ctxPathInclude.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>全家综合管理平台</title>
		<link rel="stylesheet" type="text/css" href="<%=path%>/views/main/css/style.css" />
		<script type="text/javascript" src="<%=path%>/views/main/js/jquery.min.js"></script>
	</head>
	<body>
		<div class="total">
			
			<div class="top">
				<div class="logo">
					<img src="<%=path%>/views/main/img/logo.png" alt="logo" />
				</div>
				<div class="topRight">
					<ul>
						<li class="user"><a href="javascript:;">admin@163.com</a></li>
						<li class="refresh"><a href="javascript:;">刷新</a></li>
						<li class="info">
							<a href="javascript:;">消息</a>
							<ul class="userName">
								<li><a href="index.html">退出</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="content">
				<div class="contentLeft">
					<div class="menu"><img src="<%=path%>/views/main/img/menu.png" /></div>
				</div>
				<div class="contentRight">
					<div class="note">欢迎使用！</div>
					<div class="nav"></div>
					<div class="accoun">
						<div class="personal">
							<span class="name">admin@163.com</span>
							<em></em>
							<p>您好，欢迎登录全家综合管理平台！</p>
							<a href="index.html"><img  class="out" src="<%=path%>/views/main/img/out.png" /></a>
						</div>
						<div class="system">
							<h1>系统信息</h1>
							<p>启动时间：<span>2016-01-21&nbsp;&nbsp;&nbsp;&nbsp;15：12：50</span></p>
							<p>运行时长：<span>1天20时3分50秒</span></p>
						</div>
					</div>
					<div class="message">
					</div>
					<div class="foot"><p>© 2013-2015 sao.so 版权所有 | 杭州全家科技有限公司 | Hangzhou TY Technology Co. LTD | 沪ICP备12016045号-4 </p></div>
				</div>
			</div>
			
			
		</div>
	</body>
</html>


<script type="text/javascript">
	(function (_D) {
	    var _self = {};
	    _self.Html = _D.getElementsByTagName("html")[0];
	    _self.widthProportion = function () {
	        var p = (_D.body && _D.body.clientWidth || _self.Html.offsetWidth) / 1920;
	        return p > 1 ? 1 : p < 0 ? 0 : p;
	    };
	    _self.changePage = function () {
	        _self.Html.setAttribute("style", "font-size:" + _self.widthProportion() * 100 + "px !important");
	    }
	    _self.changePage();
	    setInterval(_self.changePage, 100);
	})(document);
	
	$(".contentLeft").toggle
	(
		function()
		{
			$(".contentLeft").stop().animate({"width":"2.4rem"});
			$(".contentRight").stop().animate({"width":"16.8rem"});
			$(".nav,.accoun,.message").stop().animate({"width":"16.4rem"});
			$(".system").stop().animate({"width":"10.12rem"});
		},
		function()
		{
			$(".contentLeft").stop().animate({"width":"0.45rem"});
			$(".contentRight").stop().animate({"width":"18.75rem"});
			$(".nav,.accoun,.message").stop().animate({"width":"18.35rem"});
			$(".system").stop().animate({"width":"12.11rem"});
		}
	);
	
	$(".user").toggle
	(
		function()
		{
			$(".userName").slideDown("fast");
			$(this).css({"background":"url(<%=path%>/views/main/img/user.png) no-repeat center #fff"});
			$(this).css({"background-size":"contain"});
			$(this).find("a").css({"color":"#369fe1"});
		},
		function(){
			$(".userName").slideUp("fast");
			$(this).css({"background":"url(<%=path%>/views/main/img/user2.png) no-repeat center #2aa2ed"});
			$(this).css({"background-size":"contain"});
			$(this).find("a").css({"color":"#fff"});
		}
	)
</script>