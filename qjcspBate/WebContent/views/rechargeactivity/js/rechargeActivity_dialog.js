    /*新增*/
	function addnew(){
		$dialog = parent.$.modalDialog({ 
			title : "新增充值优惠活动",
			width : 480,
			height : 250,
			closed: true,     
	        cache: false,
	        modal : true,
	        href:"rechargeactivity/addrechargeActivityMain",/*引入分区页新增对话框页*/	
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$("#addForm").form("submit",{
						url:"rechargeactivity/addrechargeActivitySave",
						onSubmit:function(){
							if(parent.$('#addForm').form('validate')){
								var v = parent.$('#rechargeStartTime').datetimebox('getValue')
								var c = parent.$('#rechargeEndTime').datetimebox('getValue')
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
				title : "修改充值优惠活动",
				width : 480,
				height :300,
				closed: true,     
		        cache: false,
		        modal : true,
		        href:'rechargeactivity/editrechargeActivityMain',/*引入分区页编辑对话框页*/
				onLoad:function(){
					parent.$("#activityId").val(row.activityId);
					parent.$("#editrechargeStartTime").datetimebox('setValue',row.startTime);
					parent.$("#editrechargeEndTime").datetimebox('setValue',row.endTime);
//					parent.$("#editareaModelId").combobox('setValue',row.areaModelId);
//					parent.$("#editareaModelId").val(row.areaModelId);
					//parent.$("#editareaModelId").attr("disabled","disabled");
					
					parent.$("#editmoney").val(row.money);
					parent.$("#editactivityMoney").val(row.activityMoney);
					if(row.effectSymbol=="有效"){
					   parent.$("#editeffectSymbol").combobox('setValue',1)
					}else{
						parent.$("#editeffectSymbol").combobox('setValue',0)
					}
					
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$("#editForm").form("submit",{
							url:"rechargeactivity/editrechargeActivitySave",
							onSubmit:function(){
//								if(parent.$("#editareaModelId").val()==null){
//									parent.$.messager.alert('提示信息','模型不能为空','warning');
//									return false;
//								}
								if(parent.$('#editForm').form('validate')){
									var v = parent.$('#editrechargeStartTime').datetimebox('getValue')
									var c = parent.$('#editrechargeEndTime').datetimebox('getValue')
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
	//查询
	function searchfo(){
		var rechargeStartTime=$("#rechargeStartTime").datetimebox('getValue');
		var rechargeEndTime=$("#rechargeEndTime").datetimebox('getValue');
		$("#Partition").datagrid("load",{
			rechargeStartTime:rechargeStartTime,
			rechargeEndTime:rechargeEndTime,
		});
	}
	
	
	/*删除*/
	function detelnode(){
		var row = $('#Partition').datagrid('getSelected');
		if(row){
			if(row.effectSymbol=='有效'){
				parent.$.messager.show({
					title :"提示",
					msg :"活动有效,不能被删除!",
					timeout : 1000 * 2
				});
				return ;
			}
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){
			    if (r){ 
			    	 $.ajax({
				     		url : 'rechargeactivity/deleteRechargeActivity',
				     		type : 'POST',
				     		dataType : 'json',
				     		data : {"activityId":row.activityId} ,
				     		beforeSend : function(){
						     			parent.$.messager.progress({
						     				title : '提示',
						     				text : '数据删除中，请稍后....',
						     			});
						     		},
				     		success : function(data){
								if(data.status){
									parent.$.messager.progress('close');
									//$dialog.dialog('close');
									parent.$.messager.show({
										title : '提示',
										msg : data.message,
										timeout : 1000 * 2,
									});
									$("#Partition").datagrid("reload");
									
								}else{
									parent.$.messager.show({
										title : '提示',
										msg : data.message,
										timeout : 1000 * 2,
									});
									parent.$.messager.progress('close');
								}
				    	 	}
				    	 }) ;
				}
			});
		}else{
			parent.$.messager.show({
				title :"提示",
				msg :"请选择一行记录!",
				timeout : 1000 * 2
			});
		}
	}
