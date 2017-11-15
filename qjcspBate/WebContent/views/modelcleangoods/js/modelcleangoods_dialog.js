/*查询*/
	
function searchfo(){
		//alert(1);
		//分区名称
		var companyId=$("#companyId").val();

		//格子数量
//		var areaGoodNum=$.trim($("#areaGoodNum").val());
		//设备ID
		var areaModelId=$("#areaModelId").val();
//        var obj = eval('('+str+')');
//        alert(obj);
		
		$("#Partition").datagrid("load",{
			companyId:companyId,
			areaModelId:areaModelId
			
		});
	}	


    /*新增*/
	function addnew(){
		$dialog = parent.$.modalDialog({ 
			title : "新增清货补货时间",
			width : 480,
			height : 600,
			closed: true,     
	        cache: false,
	        modal : true,
	        href:"modelcleangoods/addModelcleangoodsMain",/*引入分区页新增对话框页*/	
	        onLoad:function(){
	        	
	        },
			buttons : [ {
				text : '保存',
				iconCls : 'icon-ok',
				handler : function() {
					parent.$("#addForm").form("submit",{
						url:"modelcleangoods/addModelcleangoodsSave",
						onSubmit:function(){
//							if(parent.$("#addareaModelId").val()==null){
//								parent.$.messager.alert('提示信息','模型不能为空','warning');
//								return false;
//							}
							
							if(parent.$("#type").combobox("getValue")=='weekDay'){
								//首先得到所有的复选框
								var dateStr=parent.$("#dayDateStr input:checkbox:enabled");
								if(dateStr.length==0){
									parent.$.messager.alert('提示信息','该清货补货时间已经存在,请换个商户或者模型','warning');
									return false;
								}
//								 var dateStr = parent.document.getElementsByName('dateStr');
								  var str="";
							       for(var i = 0; i < dateStr.length; i++){
							         if(dateStr[i].checked){
							        	 str+=dateStr[i].value+",";
							         }
							        
							        }
							    if(str==""){
							    	parent.$.messager.alert('提示信息','请选择每周的具体哪几天','warning');
									return false;
							    }
								
							}
							if(parent.$('#addForm').form('validate')){
								//var reserveEndTime = parent.$('#reserveEndTime').timespinner('getValue')
								//var reserveTakeEndTime = parent.$('#reserveTakeEndTime').timespinner('getValue')
								//if(reserveEndTime>=reserveTakeEndTime){
									//parent.$.messager.alert('提示信息','预定截止时间应该小于预定取餐截止时间','warning');
								//	return false;
								//}else{
									return true;
								//}
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
		var row = $('#Partition').datagrid('getSelected');
//		console.log(row);
		if(row){
			$dialog =parent.$.modalDialog({ 
				title : "修改清货补货时间",
				width : 480,
				height : 600,
				closed: true,     
		        cache: false,
		        modal : true,
		        href:'modelcleangoods/editModelcleangoodsMain?companyId='+row.companyId,/*引入分区页编辑对话框页*/
				onLoad:function(){
					parent.$("#editcleanId").val(row.cleanId);
					
					parent.$("#editcompanyId").combobox('setValue',row.companyId);
					parent.$("#editcompanyId").combobox('disable');
					parent.$("#editareaModelId").combobox('setValue',row.areaModelId);
//					parent.$("#editareaModelId").val(row.areaModelId);
					//parent.$("#editareaModelId").attr("disabled","disabled");
					parent.$("#editareaModelId").combobox('disable');
					parent.$("#edittype").combobox('setValue',row.type?row.type:'allDay');
//					parent.$("#edittype").val(row.type);
					parent.$("#hiddens").val(row.type?row.type:'allDay');
					parent.$("#edittype").combobox('disable');
					
					if(row.type=='weekDay'){
						parent.$("#editdayDateStr").show();
						var dateStr=row.dateStr;
						var value = new Array();
						value=dateStr.split(",");
						for(var i=0;i<value.length;i++){
							parent.$("#editdayDateStr input:checkbox[value="+value[i]+"]").attr("checked",'true');
						}
						//异步请求得到这个模型下是否有数据
						$.ajax({
							url:"modelcleangoods/getEditDateStr",
							data:{areaModelId:row.areaModelId,cleanId:row.cleanId},
					 		dataType:'json',
					 		type:'post',
					 		success:function(data){
				 				var dayDateStrs=data.dayDateStr;
//				 				$("#dayDateStr input:checkbox").attr({"checked":false,"disabled":false});
				 				if(dayDateStrs.length>0){
				 					for(var i=0;i<dayDateStrs.length;i++){
				 						if(dayDateStrs[i]==null||dayDateStrs[i]==''){
//				 							parent.$("#editdayDateStr").show();
				 							return ;
				 						}
				 						var arrays=dayDateStrs[i].split(",");
				 						for(var j=0;j<arrays.length;j++){
				 							parent.$("#editdayDateStr input:checkbox[value="+arrays[j]+"]").attr({"checked":true,"disabled":"disabled"});
				 						}
				 					}
				 				}
			 				}
							
						});
					}
//					parent.$("#editsupplyEndTimeStr").val(row.supplyEndTimeStr);
//					parent.$("#editcleanStartTimeStr").val(row.cleanStartTimeStr);
					parent.$("#reserveTakeStartTime").val(row.reserveTakeStartTimeStr);
					parent.$("#reserveEndTime").val(row.reserveEndTimeStr);
					parent.$("#reserveTakeEndTime").val(row.reserveTakeEndTimeStr);
					parent.$("#reserveClearancePromptTime").val(row.reserveClearancePromptTimeStr);
					parent.$("#reserveCleanStartTime").val(row.reserveCleanStartTimeStr);
					parent.$("#reserveReplenishmentPromptTime").val(row.reserveReplenishmentPromptTimeStr);
					parent.$("#reserveReplenishmentStartTime").val(row.reserveReplenishmentStartTimeStr);
					parent.$("#reserveReplenishmentWarningTime").val(row.reserveReplenishmentWarningTimeStr);
					parent.$("#reserveSupplyEndTime").val(row.reserveSupplyEndTimeStr);
					parent.$("#retailTakeStartTime").val(row.retailTakeStartTimeStr);
					parent.$("#retailTakeEndTime").val(row.retailTakeEndTimeStr);
					parent.$("#retailClearancePromptTime").val(row.retailClearancePromptTimeStr);
					parent.$("#retailCleanStartTime").val(row.retailCleanStartTimeStr);
					parent.$("#retailReplenishmentPromptTime").val(row.retailReplenishmentPromptTimeStr);
					parent.$("#retailReplenishmentStartTime").val(row.retailReplenishmentStartTimeStr);
					parent.$("#retailReplenishmentWarningTime").val(row.retailReplenishmentWarningTimeStr);
					parent.$("#retailSupplyEndTime").val(row.retailSupplyEndTimeStr);
					
				},
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
//						var v = parent.$('#editsupplyEndTimeStr').timespinner('getValue')
						parent.$("#editForm").form("submit",{
							url:"modelcleangoods/editModelcleangoodsSave?areaModelId="+row.areaModelId,
							onSubmit:function(){
//								if(parent.$("#editareaModelId").val()==null){
//									parent.$.messager.alert('提示信息','模型不能为空','warning');
//									return false;
//								}
								if(parent.$('#editForm').form('validate')){
//									var v = parent.$('#editsupplyEndTimeStr').timespinner('getValue')
//									var c = parent.$('#editcleanStartTimeStr').timespinner('getValue')
//									if(v>=c){
//										parent.$.messager.alert('提示信息','供货截止时间应该小于清货开始时间','warning');
//										return false;
//									}else{
//										return true;
//									}
									return true;
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
	
	/*删除*/
	function detelnode(){
		var row = $('#Partition').datagrid('getSelected');
		if(row){
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){
			    if (r){ 
			    	 $.ajax({
				     		url : 'modelcleangoods/deleteModelcleangoods',
				     		type : 'POST',
				     		dataType : 'json',
				     		data : {"cleanId":row.cleanId,"areaModelId":row.areaModelId},
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
//									var grid = $('#Partition');  
//									var options = grid.datagrid('getPager').data("pagination").options;
//									var total = options.total;
//									var curr = options.pageNumber;//当前第几页 
//									var pageSize=options.pageSize;
//									var pageNumber=curr;
//									var currpageCount=total-(curr-1)*pageSize;//当前页有几条数据
//									if(currpageCount==1){
//										if(curr!=1){
//											pageNumber=curr-1;
//											
//										}else{
//											pageNumber=1
//										}
//									}
//									//grid.datagrid('getPager').data("pagination").options.page=pageNumber;
//									//$("#Partition").datagrid("load", {page:pageNumber});
//								
////									$("#Partition").datagrid("load",{page:pageNumber});
//									if(pageNumber==1){
//										$("#Partition").datagrid("load", {page:pageNumber});
//									}else{
//										grid.datagrid('getPager').data("pagination").options.page=pageNumber;
//										$("#Partition").datagrid("reload");
//									}
									
									
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