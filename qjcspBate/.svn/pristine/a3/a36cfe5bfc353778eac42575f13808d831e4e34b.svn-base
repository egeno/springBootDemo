<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path=request.getContextPath();
%>
<script type="text/javascript" charset="utf-8">
	function logout(b) {
	
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

	var userInfoWindow;
	function showUserInfo() {
		userInfoWindow = $('<div/>').window({
			modal : true,
			title : '当前用户信息',
			width : 350,
			height : 300,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			//href : 'user/showUserInfo',
			onClose : function() {
				$(this).window('destroy');
			}
		});
	}
</script>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a> 
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-logout">注销</a>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="showUserInfo();">个人信息</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 120px;">
			<div onclick="changeTheme('default');">default</div>
			<div onclick="changeTheme('gray');">gray</div>
			<div onclick="changeTheme('cupertino');">cupertino</div>
			<div onclick="changeTheme('dark-hive');">dark-hive</div>
			<div onclick="changeTheme('pepper-grinder');">pepper-grinder</div>
			<div onclick="changeTheme('sunny');">sunny</div>
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="loginAndRegDialog.dialog('open');">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="logout();">重新登录</div>
	<div onclick="logout(true);">退出系统</div>
</div>
