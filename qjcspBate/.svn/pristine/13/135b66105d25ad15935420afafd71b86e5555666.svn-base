<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include  file="../include/ctxPathInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>权限编辑</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/layout/script.jsp"></jsp:include>
	<script type="text/javascript">
		var $role;
		var $user;
		var $userId;
		var $grid;
		
		$(function() {
			$("#panel").panel({   
				   width:'auto',   
				   height:$(this).height(),   
				   title: '角色分配',   
			});
			$user = $("#user");
			$user.datagrid({
				/* url : "shiro/findAllMerComUser", */
				url : "shiro/findAllComUser",
				width : 'auto',
				height : $(this).height()-120,
				pagination:true,
				rownumbers:true,
				singleSelect:true,
				striped:true,
				border:false,
				columns : [ [ {field : 'userId',title : '用户ID',align : 'center',align : 'left',editor : "text"},
				              /* {field : 'userCode',title : '用户编码',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"}, */
				              /* {field : 'companyName',title : '公司名称',align : 'center',editor : "text"}, */ 
				              {field : 'userType',title : '用户类型',align : 'center', formatter:function(value, row, index){
				             		var userTypeJson = {
				             			1:"平台超级管理员",
				             			2:"平台用户",
				             			3:"商户管理员用户",
				             			4:"商户普通用户"
				             		};
			             			return userTypeJson[row.userType || 4] || "未知方式";
			             	  }},
				              {field : 'userAccount',title : '用户登陆名',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
				              {field : 'userName',title : '姓名',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
				              {field : 'userMobile',title : '手机号',width : parseInt($(this).width()*0.1),editor : "text"},
				              {field : 'userTel',title : '用户电话',width : parseInt($(this).width()*0.1),editor : "text"},
				              {field : 'userEmail',title : '电子邮箱',width : parseInt($(this).width()*0.1),align : 'left',editor : {type:'validatebox',options:{required:true,validType:'email'}}},
				              {field : 'userMemo',title : '备注',align : 'center',editor : "text"}
				              ] ],toolbar:"#tbUser",onDblClickRow:getRoles
			});
			$role = $("#role");
			$grid = $role.datagrid({
					url : "userRole/findRolesList",
					width : 'auto',
					height : $(this).height()-120,
					pagination:false,
					border:false,
					rownumbers:true,
					singleSelect:false,
					striped:true,
					idField: 'roleId',
					columns : [ [ {field:'ck',checkbox:true},
					              /* {field : 'companyName',title : '公司名称',align : 'center',editor : "text"},  */
					              {field : 'roleName',title : '角色名称',width :  parseInt($(this).width()*0.1),align : 'center',editor : {type:'validatebox',options:{required:true}}},
					              {field : 'roleMemo',title : '角色描述',width :  parseInt($(this).width()*0.1),align : 'center',editor : "text"}
					              ] ],toolbar:"#tbRole",
					onLoadSuccess: function(){
						if($userId != null){
							 $.post("userRole/findBasicsUserRoleByUserId", {userId:$userId}, function(rsp) {
								 $role.datagrid("unselectAll");
							 if(rsp.length!=0){
								 $.each(rsp,function(i,e){
									 $role.datagrid("selectRecord",e.roleId);
								 });
							 }else{
								 parent.$.messager.show({
										title :"提示",
										msg : "该用户暂无角色！",
										timeout : 1000 * 2
									});
							 }
							}, "JSON").error(function() {
								 parent.$.messager.show({
										title :"提示",
										msg : "获取用户角色失败！",
										timeout : 1000 * 2
									});
							});
						}
					}
			});
		});
		
		 function saveUserRoles(){
			 var selectRow=$user.datagrid("getSelected");
			 var selectRows=$role.datagrid("getSelections");
			 var checkedRoleIds = [];
			 $.each(selectRows,function(i,e){
				 checkedRoleIds.push(e.roleId);
			 });
			 if(selectRow){
				 $.ajax({
						url:"userRole/saveUserRoleConfig",
						data: "userId="+selectRow.userId+"&roleIds="+checkedRoleIds,
						dataType:'json',
						success: function(rsp){
								parent.$.messager.show({
									title :rsp.title,
									msg :rsp.message,
									timeout : 1000 * 2
								});
						},
						error:function(){
							parent.$.messager.show({
								title :"提示",
								msg :"保存用户角色失败！",
								timeout : 1000 * 2
							});
						}

					});
			 }else{
				 parent.$.messager.show({
						title :"提示",
						msg :"请选择角色！",
						timeout : 1000 * 2
					});
			 }
		 }
		 function getRoles(rowIndex, rowData){
			 $userId = rowData.userId;
			 $.post("userRole/findBasicsUserRoleByUserId", {userId:rowData.userId}, function(rsp) {
				 console.log(rsp);
					 $role.datagrid("unselectAll");
				 if(rsp.length!=0){
					 $.each(rsp,function(i,e){
						 $role.datagrid("selectRecord",e.roleId);
					 });
				 }else{
					 parent.$.messager.show({
							title :"提示",
							msg : "该用户暂无角色！",
							timeout : 1000 * 2
						});
				 }
				}, "JSON").error(function() {
					 parent.$.messager.show({
							title :"提示",
							msg : "获取用户角色失败！",
							timeout : 1000 * 2
						});
				});
		}
		 function delRows(){
				var row = $role.datagrid('getSelected');
				if(row)
				{
					parent.$.messager.confirm('提示框', '你确定要删除吗?',function(r){
						if(r)
						{
							var rowIndex = $role.datagrid('getRowIndex', row);
							$role.datagrid('deleteRow', rowIndex);
							$.ajax({
								url:"userRole/delRole",
								data: "roleId="+row.roleId,
								dataType:'json',
								success: function(rsp){
									parent.$.messager.show({
										title : rsp.title,
										msg : rsp.message,
										timeout : 1000 * 2
									});
								}
							});
							$role.datagrid('reload');
						}
					});
				}else{
					parent.$.messager.show({
						title : "提示",
						msg : "请选择行数据!",
						timeout : 1000 * 2
					});
				}
			}
			//弹窗修改
			function updRowsOpenDlg() {
				var row = $role.datagrid('getSelected');
				if (row) {
					parent.$.modalDialog({
						title : "编辑角色",
						width : 480,
						height : 250,
						href : "userRole/roleEditDlg",
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load", row);
						},			
						buttons : [ {
							text : '编辑',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
								var f = parent.$.modalDialog.handler.find("#form");
								f.submit();
							}
						}, {
							text : '取消',
							iconCls : 'icon-cancel',
							handler : function() {
								parent.$.modalDialog.handler.dialog('destroy');
								parent.$.modalDialog.handler = undefined;
							}
						}
						]
					});
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			//弹窗增加
			function addRowsOpenDlg() {
				parent.$.modalDialog({
					title : "添加角色",
					width : 480,
					height : 250,
					href : "userRole/roleEditDlgForMerchant",
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find("#form");
							f.submit();
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							parent.$.modalDialog.handler.dialog('destroy');
							parent.$.modalDialog.handler = undefined;
						}
					}
					]
				});
			}
	</script>
  </head>
  <body>
  
   <div id="panel"  data-options="border:false">
		<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" title="" style="height: 82px; overflow: hidden; padding: 5px;">
			<div class="well well-small">
				<span class="badge">提示</span>
				<p>
					为用户分配角色，请<span class="label-info"><strong>双击用户</strong></span>查看所属角色！
					超级管理员默认拥有<span class="label-info"><strong>所有权限！</strong></span>
				</p>
			</div>
		</div>
			<div data-options="region:'west',split:true,border:true" style="width:800px;">
				<div id="tbUser" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:2px">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-config" plain="true" onclick="saveUserRoles();">保存设置</a>
							</td>
						</tr>
					</table>
				</div>
				<table id="user" title="用户"></table>
			</div>
			<div data-options="region:'center',border:true">
				<div id="tbRole" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:4px;padding-bottom:4px;">
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
							</td>
						</tr>
					</table>
				</div>
				<table id="role" title="角色"></table>
			</div>
		</div>
	</div>
  </body>
  </body>
</html>
