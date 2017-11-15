/**
 * 
 */

			var $dg;
			var $temp;
			var $grid;
			$(function() {
				$dg = $("#dg");
				$grid=$dg.datagrid({
					url : "appUserInfo/findAPPUserInfo",
					width : 'auto',
					height :  $(this).height(),
					pagination:true,
					rownumbers:true,
					border:false,
					singleSelect:true,
					striped:true,
					columns : [ [ {field : 'customerId',title : '用户ID',align : 'center', editor : "text"},
					              {field : 'customerName',title : '客户姓名',align : 'center',editor : "text"},
					              {field : 'customerPassword',title : '密码',align : 'center',editor : "text"},
					              {field : 'payPassword',title : '支付密码',align : 'center',editor : "text"},
				             	  {field : 'customerMobile',title : '手机号',align : 'center',editor : "text"},
					              {field : 'customerEmail',title : '邮箱',align : 'center',editor : "text"},
					              {field : 'realName',title : '全名',align : 'center',editor : "text"},
					              {field : 'customerSex',title : '性别',align : 'center',editor : "text"},
					              {field : 'customerQq',title : 'QQ',align : 'center',editor : "text"},
					              {field : 'customerWeixin',title : '微信',align : 'center',editor : "text"},
					              {field : 'customerMoney',title : '余额',align : 'center',editor : "text"},
					              {field : 'customerIntegral',title : '积分',align : 'center',editor : "text"},
					              {field : 'createTimeStr',title : '创建时间',align : 'center',editor : "text"},
					              {field : 'modifyTimeStr',title : '修改时间',align : 'center',editor : "text"},
					              /*{field : 'createUserId',title : '创建人',align : 'center',editor : "text"},
					              {field : 'modUserId',title : '修改人',align : 'center',editor : "text"},*/
					              {field : 'universityName',title : '地址',align : 'center',editor : "text"},
					              {field : 'buildingName',title : '职业',align : 'center',editor : "text"},
					              {field : 'grade',title : '口味',align : 'center',editor : "text"},
					              {field : 'studentNum',title : '年龄',align : 'center',editor : "text"},
					              {field : 'nativePlace',title : '籍贯',align : 'center',editor : "text"}
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
				if(row)
				{
					parent.$.messager.confirm('提示框', '你确定要删除吗?',function(r){
						if(r)
						{
							var rowIndex = $dg.datagrid('getRowIndex', row);
							$dg.datagrid('deleteRow', rowIndex);
							$.ajax({
								url:"appUserInfo/deleteUserInfo",
								data: "customerId="+row.customerId,
								dataType:'json',
								success: function(rsp){
									parent.$.messager.show({
										title : rsp.title,
										msg : rsp.message,
										timeout : 1000 * 2
									});
									$dg.datagrid('reload'); 
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
				console.log(row);
				if (row) {
					parent.$.modalDialog({
						title : "编辑APP用户信息",
						width : 600,
						height : 400,
						href : "appUserInfo/appUserCustomerInfoEdit",
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load", row);
						},			
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
					title : "添加APP用户信息",
					width : 600,
					height : 400,
					href : "appUserInfo/appUserCustomerInfoAdd",
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
			
			function exportDaily(){
				 var customerId=$.trim($('#searchbox').searchbox('getValue'));
				/* var customerName=$('#customerName').text().trim();*/
	             location.href=urls['ctx']+"/appUserInfo/exportSearchDaily?customerId="+encodeURI(encodeURI(customerId));
	             
//	             <!-- 这里不能用ajax请求，ajax请求无法弹出下载保存对话框 -->    
	        }