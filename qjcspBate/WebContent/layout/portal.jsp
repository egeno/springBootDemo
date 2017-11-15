<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.qjkj.qjcsp.util.DateUtils"%>
<%@ include  file="/views/include/ctxPathInclude.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>全家综合管理平台</title>
		<link rel="stylesheet" type="text/css" href="<%=path%>/views/main/css/style.css" />
		<script type="text/javascript" src="<%=path%>/views/main/js/jquery.min.js"></script>
		<!--[if lte IE 8]>
		<link rel="stylesheet" type="text/css" href="<%=path%>/views/main/css/indexIE8.css" />
		
		<![endif]-->
		<script type="text/javascript">
			$(document).ready(function(){ 
			 if (screen.width < 1500){
				 $('.content .contentRight').css('width','100%');
				 $('.accoun').css('width','100%');
				 $('.personal').css('width','55%');
				 $('.out').css('right','20%');
				 $('.system').css('width','44%').css('background','url(<%=path%>/views/main/img/system500.jpg) no-repeat');
				 $('.message').css('width','auto').css('background','url(<%=path%>/views/main/img/bg1150.jpg) no-repeat center top');
				 
		     }
		     if (1500 < screen.width && screen.width < 1700){
				 $('.content .contentRight').css('width','100%');
				 $('.accoun').css('width','97%');
				 $('.personal').css('width','48%');
				 $('.out').css('right','20%');
				 $('.system').css('width','52%').css('background','url(<%=path%>/views/main/img/system700.png) no-repeat');
				 $('.message').css('width','auto').css('background','url(<%=path%>/views/main/img/bg1380.png) no-repeat center top');
				 
		     }
			});
		</script>
		<script>
		
			function logOut()
			{
				window.parent.location.href = "../login";
			}
			
		</script>
	</head>
	<body onload="runtime()">
		<div class="total">
			<div class="content">
				<div class="contentRight">
					<div class="accoun">
						<div class="personal">
							<span class="name">${shiroUser.account}</span>
							<em></em>
							<p>您好，欢迎登录全家综合管理平台！</p>
							<a href="#" onclick="logOut();"><img  class="out" src="<%=path%>/views/main/img/out.png" /></a>
						</div>
						<div class="system">
							<h1>系统信息</h1>
							<p>启动时间：<span><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(application.getAttribute("tomcatStartTime")) %></span></p>
							<p>运行时长：<span id="txt"></span></p>
						</div>
					</div>
					<div class="message">
					</div>
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
	        var p = (_D.body && _D.body.clientWidth || _self.Html.offsetWidth) / screen.width;
	        return p > 1 ? 1 : p < 0 ? 0 : p;
	    };
	    _self.changePage = function () {
	        _self.Html.setAttribute("style", "font-size:" + _self.widthProportion() * 100 + "px !important");
	    }
	    _self.changePage();
	    setInterval(_self.changePage, 100);
	})(document);
	var temp=0;
	function runtime(){
		<% String tomcat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(application.getAttribute("tomcatStartTime"));
		//String nowdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DateUtils.getNowDate());
		
		//String tomcat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(application.getAttribute("tomcatStartTime"));
		//String nowdate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DateUtils.getNowDate());
		
		Long nowdatetime=DateUtils.getNowDate().getTime();
		Date tomcatDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tomcat);
		Long tomcatTime=tomcatDate.getTime();
		%>
		<%-- var tomcat1;
		tomcat1='<%=tomcat%>';
		var converted = Date.parse(tomcat1); 
	    var tomcatdate = new Date(converted); 
		var nowdatestr='<%=nowdate%>';
		var nowconverted = Date.parse(nowdatestr);
	    var nowdate = new Date(nowconverted); --%>
	    var nowdatetime='<%=nowdatetime%>';
	    if(temp!=0){
	    	nowdatetime=0;
	    }
		temp=parseInt(temp)+parseInt(nowdatetime);
		temp=checktime(temp);
		 var diff = parseInt(temp) - parseInt('<%=tomcatTime%>');
		var diffSeconds = Math.floor(diff / 1000 % 60);
		var diffMinutes = Math.floor(diff / (60 * 1000) % 60);
		var diffHours = Math.floor(diff / (60 * 60 * 1000) % 24);
		var diffDays = Math.floor(diff / (24 * 60 * 60 * 1000));
		var str = diffDays + "天" + diffHours + "时" + diffMinutes + "分" + diffSeconds + "秒";
		 document.getElementById('txt').innerHTML = str;
		 t=setTimeout('runtime()', 1000);
		}
	function checktime(time){
		time=parseInt(time)+parseInt(1000);
		return time
	}
</script>