	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%
		String easyuiThemeName="bootstrap";
		Cookie cookies[] =request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals("cookiesColor")) {
					easyuiThemeName = cookie.getValue();
					break;
				}
			}
		}
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		request.setAttribute("ctx",basePath);
	%>
	<base href="<%=basePath %>"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/themes/<%=easyuiThemeName %>/easyui.css">
	<script type="text/javascript" src="<%=basePath%>static/js/xheditor/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/xheditor/xheditor-1.1.14-zh-cn.min.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/common.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/selected.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>static/css/uploadify.css">
	<script type="text/javascript" src="<%=basePath%>static/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/datagrid-detailview.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/jqueryUtil.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/json2.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>static/js/package.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/base.js?v=11"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/YDataGrid.js"></script>

	<script type="text/javascript" src="<%=basePath%>static/js/FusionCharts/FusionCharts.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/FusionCharts/FusionCharts.jqueryplugin.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/FusionCharts/FusionChartsExportComponent.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery.jqprint-0.3.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/validate.js"></script>
	
	<style type="text/css">
		body {
		    font-family:helvetica,tahoma,verdana,sans-serif;
		    font-size:13px;
		    margin:0px 0px 0px 0px;
		    padding:0px 0px 0px 0px;
		}
		.red{
		font-family: "????";margin-right: 5px;font-weight: normal;color: red}
	</style>
	<script type="text/javascript">
	var urls= {
		'ctx':'<%=path%>'
	};
	
	var baseUrls= {
			'ctx':'<%=basePath%>'
	};
	//1800秒  30分钟
	var sessionTime=1800;
	var timer=setInterval(function(){
		if(sessionTime>0){
			sessionTime--;
		}
	}, 1000);
	
	var listen=function(e){
		console.log(sessionTime);
		if(sessionTime==0){
			$.ajax({
				async : true,
				cache : false,
				type : "GET",
				dataType:'json',
				url : "<%=path%>/checkSession",
				success : function(data) {
					if(data.code!="1"){
						document.onmousedown="";
						//document.removeEventListener("mousedown", listen, false);
						clearInterval(timer);
						$.messager.alert('提示:','由于您长时间未操作，用户信息被外星人掳走了，请您重新登录后再进行操作！','info',function(){
							top.location.reload();
						});
					}else{
						sessionTime=1800;
					}
				}
			});
		}
	}
	//IE不支持addEventListener 改用此方法 兼容IE
	document.onmousedown=listen;
	//document.addEventListener("mousedown", listen, false);
	</script>