    /*新增*/
	function addnew(){
		$dialog = parent.$.modalDialog({ 
			title : "新增完善个人信息优惠活动",
			width : 480,
			height : 200,
			closed: true,     
	        cache: false,
	        modal : true,
	        href:"discountActivity/adddiscountActivityMain",/*引入分区页新增对话框页*/	
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$("#addForm").form("submit",{
						url:"discountActivity/adddiscountActivitySave",
						onSubmit:function(){
							if(parent.$('#addForm').form('validate')){
								var v = parent.$('#startTimeStr').datetimebox('getValue')
								var c = parent.$('#endTimeStr').datetimebox('getValue')
								if(v>=c){
									parent.$.messager.alert('提示信息','活动开始时间应该小于活动结束时间','warning');
									return false;
								}else{
									return true;
								}
								
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
							   $("#Partition").datagrid("reload");
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
	} 
	
	
	/*编辑*/
	function eadit(){
//		alert(1);
		//alert(1);
		var row = $('#Partition').datagrid('getSelected');
//		console.log(row);
		if(row){
			$dialog =parent.$.modalDialog({ 
				title : "修改完善个人信息优惠活动",
				width : 480,
				height : 250,
				closed: true,     
		        cache: false,
		        modal : true,
		        href:'discountActivity/editdiscountActivityMain',/*引入分区页编辑对话框页*/
				onLoad:function(){
					parent.$("#editstartTimeStr").datetimebox('setValue',row.startTimeStr);
					parent.$("#editendTimeStr").datetimebox('setValue',row.endTimeStr);
//					parent.$("#editareaModelId").combobox('setValue',row.areaModelId);
//					parent.$("#editareaModelId").val(row.areaModelId);
					//parent.$("#editareaModelId").attr("disabled","disabled");
					parent.$("#editdiscountMoney").val(row.discountMoney);
					parent.$("#editeffectSymbol").combobox('setValue',row.effectSymbol)
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$("#editForm").form("submit",{
							url:"discountActivity/editdiscountActivitySave",
							onSubmit:function(){
//								if(parent.$("#editareaModelId").val()==null){
//									parent.$.messager.alert('提示信息','模型不能为空','warning');
//									return false;
//								}
								if(parent.$('#editForm').form('validate')){
									var v = parent.$('#editstartTimeStr').datetimebox('getValue')
									var c = parent.$('#editendTimeStr').datetimebox('getValue')
									if(v>=c){
										parent.$.messager.alert('提示信息','活动开始时间应该小于活动结束时间','warning');
										return false;
									}else{
										return true;
									}
								}else{
									return false;
								}
//								
//								if(parent.$("#addSpan").text()!=''){
//									return false;
//								}
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
									$("#Partition").datagrid("reload");
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
	
