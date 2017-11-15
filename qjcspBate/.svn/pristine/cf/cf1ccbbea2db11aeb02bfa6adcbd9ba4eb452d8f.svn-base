/**
 * 
 */

			var $dg;
			var $temp;
			var $grid;
			$(function() {
				$dg = $("#dg");
				$grid=$dg.datagrid({
					url : "shiro/findAllComUser",
					width : 'auto',
					height :  $(this).height(),
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:true,
					striped:true,
					columns : [ [ {field : 'userId',title : '用户ID',align : 'center',align : 'left',editor : "text"},
					              /*{field : 'userCode',title : '用户编码',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},*/
					              {field : 'userAccount',title : '登录账号',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
					              {field : 'userName',title : '用户姓名',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
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
					              {field : 'userMobile',title : '手机号码',width : parseInt($(this).width()*0.1),editor : "text"},
					              {field : 'userTel',title : '电话号码',width : parseInt($(this).width()*0.1),editor : "text"},
					              {field : 'userEmail',title : '电子邮箱',width : parseInt($(this).width()*0.1),align : 'left',editor : {type:'validatebox',options:{required:true,validType:'email'}}},
					              {field : 'userMemo',title : '地址',align : 'center',editor : "text"}
					              ] ],toolbar:'#tb'
				});
				//搜索框
				$("#searchbox").searchbox({
					 menu:"#mm", 
					 prompt :'模糊查询',
				    searcher:function(value,name){   
				    	var str="{\"search_"+name+"\":\""+value+"\"}";
			            var obj = eval('('+str+')');
			            $dg.datagrid('reload',obj); 
				    }
				   
				});
			});
			//删除
			function delRows(){
				var row = $dg.datagrid('getSelected');
				if(row){
					parent.$.messager.confirm('提示框', '你确定要删除吗?',function(r){
						if(r)
						{
							var rowIndex = $dg.datagrid('getRowIndex', row);
							$dg.datagrid('deleteRow', rowIndex);
							$.ajax({
								url:"shiro/deleteUser",
								data: "userId="+row.userId,
								dataType:'json',
								success: function(rsp){
									parent.$.messager.show({
										title : rsp.title,
										msg : rsp.message,
										timeout : 1000 * 2
									});
								}
							});
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
				var row = $dg.datagrid('getSelected');
				if (row) {
					parent.$.modalDialog({
						title : "编辑商户普通用户",
						width : 650,
						height : 295,
						href : "shiro/comUserEdit",
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
			//弹窗增加
			function addRowsOpenDlg() {
				parent.$.modalDialog({
					title : "添加商户普通用户",
					width : 650,
					height : 330,
					href : "shiro/comUserAdd",
					buttons : [ {
						text : '保存',
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
			}