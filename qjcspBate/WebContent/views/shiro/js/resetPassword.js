/**
 * 
 */

			var $dg;
			var $temp;
			var $grid;
			$(function() {
				$dg = $("#dg");
				$grid=$dg.datagrid({
					url : "shiro/findUserInformation",
					width : 'auto',
					height :  $(this).height(),
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:true,
					striped:true,
					columns : [ [ {field : 'userId',title : '用户ID',align : 'center',align : 'left',editor : "text"},
					             /* {field : 'userCode',title : '用户编码',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},*/
					              {field : 'userAccount',title : '用户登陆名',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
					              {field : 'userName',title : '姓名',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
					              {field : 'password',title : '密码',align : 'center',editor : "text"},
					              {field : 'userType',title : '用户类型',align : 'center', formatter:function(value, row, index){
					             		var userTypeJson = {
					             			1:"平台超级管理员",
					             			2:"平台用户",
					             			3:"商户管理员用户",
					             			4:"商户普通用户"
					             		};
				             			return userTypeJson[row.userType || 4] || "未知方式";
				             	  }},
				             	  {field : 'companyName',title : '公司',align : 'center',editor : "text"},
					              {field : 'userMobile',title : '手机号',width : parseInt($(this).width()*0.1),editor : "text"},
					              {field : 'userTel',title : '用户电话',width : parseInt($(this).width()*0.1),editor : "text"},
					              {field : 'userEmail',title : '电子邮箱',width : parseInt($(this).width()*0.1),align : 'left',editor : {type:'validatebox',options:{required:true,validType:'email'}}},
					              {field : 'userMemo',title : '备注',align : 'center',editor : "text"}
					              ] ],toolbar:'#tb'
				});
			});
			
			//弹窗修改
			function resetPassword() {
				var row = $dg.datagrid('getSelected');
				row.password="";
				if (row) {
					parent.$.modalDialog({
						title : "密码重置",
						width : 650,
						height : 330,
						href : "shiro/resetPasswordEdit",
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load", row);
						},			
						buttons : [ {
							text : '编辑',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= $grid;
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
