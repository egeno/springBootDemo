<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include  file="include/ctxPathInclude.jsp"%>

<html>
  <head>
    <title>全家综合管理平台</title> 
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/layout/script.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=path%>/views/main/css/style.css" />
	<!--[if lte IE 8]>
		<link rel="stylesheet" type="text/css" href="<%=path%>/views/main/css/indexIE8.css" />
	<![endif]-->
	<link rel="shortcut icon" href="<%=path%>/static/images/favicon.ico" />
	<script type="text/javascript">
<%-- 	//30000   30秒查询后台一次是否session失效
		var timer = setInterval(function(){
		$.ajax({
			async : true,
			cache : false,
			type : "GET",
			url : "<%=path%>/checkSession",
			success : function(data) {
				if(data.code!="1"){
					clearInterval(timer);
					$.messager.alert('提示:','由于您长时间未操作，用户信息被外星人掳走了，请您重新登录后再进行操作！','info',function(){
						window.location.reload();
					});
					//alert("由于您长时间未操作，用户信息被外星人掳走了，请您重新登录后再进行操作！");
					//window.close();
					//window.open(localStorage.getItem("url"));
				}/* else{
					localStorage.setItem("url", data.url);
				} */
			}
		});
	}, 30000); --%>
	
	function logout() {
		$.messager.confirm("提示", "确认退出吗?",function(r){
			if(r){
				$.ajax({
					async : false,
					cache : false,
					type : "POST",
					url : "<%=path%>/logout",
					error : function() {
					},
					success : function(json) {
						location.replace("<%=path%>/login");
					}
				});
			}
		});
		
	}
	
	function refresh() {
		location.reload(true); 
	}
	
	$(function(){
			initMenu();
			if (jqueryUtil.isLessThanIe8()) {
				$.messager.show({
					title : '警告',
					msg : '您使用的浏览器版本太低！<br/>建议您使用谷歌浏览器来获得更快的页面响应效果！',
					timeout : 1000 * 30
				});
			}
		});
		function initMenu(){
			var $ma=$("#menuAccordion");
			$ma.accordion({animate:true,fit:true,border:false});
			$.post("<%=path%>/getMenu", function(rsp) {
				$.each(rsp,function(i,e){
					var menulist ="<div class=\"well well-small\">";
					if(e.child && e.child.length>0){
						$.each(e.child,function(ci,ce){
							var leaf = ce.child ;
							if(ce.child && ce.child.length > 0){
								menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"+ce.iconCls+"'\">"+ce.name+"</a><br/>";
								$.each(ce.child,function(mi,me){
									var effort=me.name+"||"+me.iconCls+"||"+me.url;
									menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" style=\"padding-left:20px;\" data-options=\"plain:true,iconCls:'"+me.iconCls+"'\" onclick=\"addTab('"+effort+"');\">"+me.name+"</a><br/>";
								}) ;
							}else{
								var effort=ce.name+"||"+ce.iconCls+"||"+ce.url;
								menulist+="<a href=\"javascript:void(0);\" class=\"easyui-linkbutton\" data-options=\"plain:true,iconCls:'"+ce.iconCls+"'\" onclick=\"addTab('"+effort+"');\">"+ce.name+"</a><br/>";
							}
						});
					}
					menulist+="</div>";
					$ma.accordion('add', {
			            title: e.name,
			            content: menulist,
						border:false,
			            iconCls: e.iconCls,
			            selected: false
			        });
				});
			}, "JSON").error(function() {
				$.messager.alert("提示", "获取菜单出错,请重新登陆!");
			});
		}
		
		$(function(){
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
		});
		
	</script>
	<style type="text/css">
	#menuAccordion a.l-btn span span.l-btn-text {
	    display: inline-block;
	    height: 14px;
	    line-height: 14px;
	    margin: 0px 0px 0px 10px;
	    padding: 0px 0px 0px 10px;
	    vertical-align: baseline;
	    width: 128px;
	}
	#menuAccordion 	a.l-btn span span.l-btn-icon-left {
	    background-position: left center;
	    padding: 0px 0px 0px 20px;
	}
	#menuAccordion .panel-header {
		background-color:#252e37;
	}
	#menuAccordion .panel-body {
		padding:5px;
		background-color:#384556;
	}
	#menuAccordion .panel-body .well{
		background-color:#384556;
		border:0 none;
	}
	#menuAccordion .panel-body .well .1-btn-left{
		background-color:#384556;
	}
	#menuAccordion span:focus{
		outline: none;
	}
	#menuAccordion {
		background-color:#384556;
	}
	.layout-panel-west .panel-header{
		background:#384556;
		border-color:#536071;
	}
	.layout-panel-west .panel-body{
		border-color:#536071;
	}
	.layout-panel-west .panel-title{
		color:#fff;
	}
	.layout-panel-west a.l-btn{
		color:#fff;
	}
	.layout-panel-west a:hover.l-btn{
		color:#444;
	}
	</style>
  </head>
 <body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;padding:10px;overflow: hidden;">
		<div class="total">
			<div class="top">
				<div class="logo">
					<img src="<%=path%>/views/main/img/logo.png" alt="logo" />
				</div>
				<div class="topRight">
					<ul>
						<li class="user">
							<a href="javascript:;">${shiroUser.account}</a>
							<ul class="userName">
								<li><a href="index.html" onclick="logout(true);">退出</a></li>
							</ul>
						</li>
						<li class="refresh"><a href="javascript:;" onclick="refresh();">刷新</a></li>
						<li class="info"><a href="javascript:;">消息</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'菜单'" style="width:210px; background:#000;">
		<div id="menuAccordion">
			<%-- <img src="<%=path%>/views/main/img/logo.png" alt="logo" /> --%>
		</div>
	</div> 
	<div data-options="region:'south',border:false" style="height:25px;background:#EEE;padding:5px;">
		<div class="foot"><p>© 2013-2016 quanjiakeji.com 版权所有 | 杭州全家科技服务有限公司 | Hangzhou Quanjia Technology Service Co.,LTD. | 浙ICP备15041905号-1  </p></div>
	</div>
	<div data-options="region:'center',plain:true,title:'欢迎使用'" style="overflow: hidden;"   href="<%=path%>/layout/center.jsp"></div>
</body>
</html>
