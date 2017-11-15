			var $dg;
			var $temp;
			var $grid;
			$(function() {
			$dg = $("#dg");
				$grid=$dg.datagrid({
					url : "preissueEndTime/toLoadData",
					width : 'auto',
					height : $(this).height(),
					border:false,
					singleSelect:true,
					striped:true,
					fit:false,
					fitColumns:true,					
					cache:false,
					onLoadSuccess:function(data){
						console.log(data);
					},
					columns : [ [ 
					              {field:'preissueEndTimeId',title : '',hidden:true,align : 'center',align : 'left'},
					              {field:'companyName',title : '公司名称',align : 'center',align : 'left',width : parseInt($(this).width()*0.3)},
					              {field:'preissueEndTime',title : '预定截止时间',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
					              {field:'createUserName',title : '创建人',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
					              {field:'createTime',title : '创建时间',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
					              {field:'modUserName',title : '修改人',width : parseInt($(this).width()*0.1),editor : "text"},
					              {field:'modTime',title : '修改时间',align : 'center',editor : "text"},
					     					              
					              ] ]
				});
			});
			
			
		   /* 新增
			function addnew(){
				$dialog = parent.$.modalDialog({ 
					title : "修改预定截止时间设置",
					width : 480,
					height : 200,
					closed: true,     
			        cache: false,
			        modal : true,
			        href:"preissueEndTime/addPreissueEndTimewin",引入新增对话框页	
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$("#addForm").form("submit",{
								url:"preissueEndTime/addPreissueEndTime",
								onSubmit:function(){	
									if(parent.$('#addForm').form('validate')){
										return true;
									}else{
										return false;
									}
								},
								success:function(data){
									var result= eval("(" + data + ")");
									if(result.status){
										$dialog.dialog('close');
										$.messager.show({
											title : '提示',
											msg : result.message,
											timeout : 1000 * 2,
										});
										$dg.datagrid('reload'); 
									}else{
										$.messager.show({
											title : '提示',
											msg : result.message,
											timeout : 1000 * 2,
										});
									}
									
								}
							});
							
							
						}
					}, 
					{
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							$dialog.dialog('close');
						}
					}],
					// onLoad:function(result, textStatus){
							
					// },
					 success : function(result) {
					 	$dialog.dialog('close');
					 	result = $.parseJSON(result);
		 		 		if (result.status) {
					 		parent.reload;
					 		parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
					 		parent.$.modalDialog.handler.dialog('close');
							parent.$.messager.show({
					 			title :"提示",
					 			msg : result.message,
					 			timeout : 1000 * 2
					 		});
				 		}else{
					 		parent.$.messager.show({
					 			title :"提示",
					 			msg : result.message,
					 			timeout : 1000 * 2
					 		});
					 	} 

				 	}
					// });
				});
				$dialog.dialog('open');
			} */
			function addnew(){
				$dialog = parent.$.modalDialog({
					title : "添加预定截止时间设置",
					width : 400,
					height : 150,
					closed: true,     
			        cache: false,
			        modal : true,
					href : "preissueEndTime/addPreissueEndTimewin",
					buttons : [ {
						text : '确定',
						iconCls : 'icon-ok',
						handler : function(){
							parent.$("#addForm").form("submit",{
								url :"preissueEndTime/addPreissueEndTime",							
								success : function(result) {
									//alert(result);
									//var result= eval("(" + result+ ")");
									//parent.$.messager.progress('close');
									result = $.parseJSON(result);
									if (result.status) {	
										$dialog.dialog('close');
										$grid.datagrid('reload');
										//refreshTab($('.tabs-selected').find('.tabs-title').text());
										self.location.reload();
										//parent.reload;					
										//parent.$.modalDialog.openner.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_datagrid这个对象，是因为role.jsp页面预定义好了
										//parent.$.modalDialog.handler.dialog('close');
										parent.$.messager.show({
											title :"提示",
											msg : result.message,
											timeout : 1000 * 2
										});
									}else{
										parent.$.messager.show({
											title :"提示",
											msg : result.message,
											timeout : 1000 * 2
										});
									}
								}
							});
						}
					}, {
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							$dialog.dialog('close');
							parent.$.messager.progress('close');
							parent.$.modalDialog.handler.dialog('close');
							parent.$.modalDialog.openner=undefined ;
						}
					}
					]
				});
				$dialog.dialog('open');
			}
			
			/*编辑*/
			function edit(){
//				alert(1);
				//alert(1);
				var row = $dg.datagrid('getSelected');
//				console.log(row);
				if(row){
					$dialog =parent.$.modalDialog({ 
						title : "修改预定截止时间设置",
						width : 400,
						height : 150,
						closed: true,     
				        cache: false,
				        modal : true,
				        href:'preissueEndTime/editPreissueEndTimewin',/*引入编辑对话框页*/
						onLoad:function(){
							parent.$("#preissueEndtime").timespinner('setValue',row.preissueEndTime);
							parent.$("#preissueEndTimeId").val(row.preissueEndTimeId);
							//parent.$("#endTime").datetimebox('setValue',row.endTime);
//							parent.$("#editareaModelId").combobox('setValue',row.areaModelId);
//							parent.$("#editareaModelId").val(row.areaModelId);
							//parent.$("#editareaModelId").attr("disabled","disabled");
							/*parent.$("#editdiscountMoney").val(row.discountMoney);*/
							//parent.$("#effectSymbol").combobox('setValue',row.effectSymbol)
						},
						buttons : [ {
							text : '保存',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$("#editForm").form("submit",{
									url:"preissueEndTime/editPreissueEndTime",
									onSubmit:function(){
//										if(parent.$("#editareaModelId").val()==null){
//											parent.$.messager.alert('提示信息','模型不能为空','warning');
//											return false;
//										}
										if(parent.$('#editForm').form('validate')){
											var preissueEndTime = parent.$('#preissueEndTime').timespinner('getValue')
											var preissueEndTimeId=parent.$('#preissueEndTimeId').val();
											//var c = parent.$('#endTime').datetimebox('getValue')
										}else{
											return false;
										}
//										
//										if(parent.$("#addSpan").text()!=''){
//											return false;
//										}
									},
									success:function(data){
										var result= eval("(" + data + ")");
										if(result.status){
											$dialog.dialog('close');
											$.messager.show({
												title : '提示',
												msg : result.message,
												timeout : 1000 * 2,
											});
											$dg.datagrid("reload");
											self.location.reload();
										}else{
											$.messager.show({
												title : '提示',
												msg : result.message,
												timeout : 1000 * 2,
											});
										}
										
									}
								});
							}
						}, {
							text : '取消',
							iconCls : 'icon-cancel',
							handler : function() {
								$dialog.dialog('close');
							}
						}],
					});
					$dialog.dialog('open');
				}else{
					parent.$.messager.show({
						title :"提示",
						msg :"请选择一行记录!",
						timeout : 1000 * 2
					});
				}
			}
			

