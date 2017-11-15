$('.line').validatebox({    
    required:true,
    validType:'minLength[1]',/*规则需重新定义*/
    missingMessage:'请输入行数',
    invalidMessage:'请输入有效行数',
});
$('.column').validatebox({    
    required:true,
    validType:'minLength[1]',/*规则需重新定义*/
    missingMessage:'请输入列数',
    invalidMessage:'请输入有效列数',
});
$('.fenquname').validatebox({    
    required:true,
    validType:"email",/*规则需重新定义*/
    missingMessage:'请输入分区名称',
    invalidMessage:'请输入有效分区名称',
});
  $('.mobliename').validatebox({    
    required:true,
    validType:'minLength[1]',/*规则需重新定义*/
    missingMessage:'请输入模型名称',
    invalidMessage:'请输入有效模型名称',
});
/*查询*/
	function searchfo(){
		var area_modelName=$('.Partitionname').val();
		var area_row=$('.Partitionnumber').val();
		var area_col=$('.Partitioncolumn').val();
		var area_modelType=$('#fittypes').val();
		var area_machineId=$('#equipment').val();
		//alert(area_modelType+","+area_machineId);
		$("#mobliedata").datagrid("reload",{
			area_modelName:area_modelName,
			area_row:area_row,
			area_col:area_col,
			area_modelType:area_modelType,
			area_machineId:area_machineId		
		});
	}
/*编辑*/
	function eadit(){
		var row = $('#mobliedata').datagrid('getSelected');
		console.log(row);
		if(row){
			$dialog = $('.editcontent').dialog({ 
				title : "修改分区模型",
				width : 480,
				height : 400,
				closed: true,     
		        cache: false,
		        modal : true,
		        href:'area/info/updateArea?areaId='+row.areaModelId,			
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						$("#editForm").form("submit",{
							url :"area/info/updateAreaByAreaId",
							onSubmit : function() {
								parent.$.messager.progress({
									title : '提示',
									text : '数据处理中，请稍后....'
								});
								var isValid = $(this).form('validate');
								if (isValid) {
									parent.$.messager.progress('close');
								}
								return isValid;
							},
							success : function(result) {
								result = $.parseJSON(result);								
								if (result.status) {	
									$dialog.dialog('close');
									$grid.datagrid('reload');
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
					}
				}
				]
			});
			$dialog.dialog('open');
		}
		else{
			parent.$.messager.show({
				title :"提示",
				msg :"请选择一行记录!",
				timeout : 1000 * 2
			});
		}

	}
/*保存*/
	function  save(){
		var rows=$('#CheckPartition').datagrid("getSelections");
		var a=$('#mobliedata').datagrid('getSelected');
		var trlen=$('.check').find('.datagrid-btable').find('tr.sh').length;
		var isChecked=$('.datagrid-header-check').find('input').is(':checked');
		/*var rowlen=$('.layout-split-center').find('.datagrid-row-selected.sh').length;*/
		var $tr=$('.layout-split-center').find('.datagrid-row-selected.sh');
		var machineIdss=[];
		for(var i=0;i<trlen;i++){
			machineIdss.push($tr.eq(i).find('div').eq(1).text());
		}
		var machineIdsss=[];
		for(var i=0;i<machineIdss.length;i++){
			if(machineIdss[i]!=''){
				machineIdsss.push(machineIdss[i]);
			}
		}
		if(machineIdss.length>0){
		if(a){
			var str="";
			for(var i=0;i<machineIdsss.length;i++)
			{
				var machineId=machineIdsss[i];
				str=str+machineId+",";
			}
			if(str==''){
				str='no,';
			}
			if(machineModels==''){
				machineModels='no-no';
			}
			str=str+""+modelId+"_"+machineModels;
			//alert(str);
			parent.$.messager.confirm("提示","确定要保存吗?",function(r){  
				if (r){  
					$.post("area/info/saveAreaModel", {'areaModel':str}, function(rsp) {
						if(rsp.status){
							$grid.datagrid('reload');
							$cgrid.datagrid('reload');
						}
						parent.$.messager.show({
							title : rsp.title,
							msg : rsp.message,
							timeout : 1000 * 2
						});
					}, "JSON").error(function() {
						parent.$.messager.show({
							title :"提示",
							msg :"提交错误了！",
							timeout : 1000 * 2
						});
					});
				}  
			});
		}else{
			parent.$.messager.show({
				title :"提示",
				msg :"请选择模型！",
				timeout : 1000 * 2
			});
		}
		}else{
			parent.$.messager.show({
				title :"提示",
				msg :"请选择符合条件的设备！",
				timeout : 1000 * 2
			});
		}
	}  
/*停用*/
	function detelnode(){
		var row = $('#mobliedata').datagrid('getSelected');
		if(row){
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){
			    if (r){  
			    	$.post('area/info/deleteAreaById',{'areaId':row.areaModelId},function(rsp){
			    		if(rsp.status){
							$grid.datagrid('reload');
						}
						parent.$.messager.show({
							title : rsp.title,
							msg : rsp.message,
							timeout : 1000 * 2
						});
					}, "JSON").error(function() {
						parent.$.messager.show({
							title :"提示",
							msg :"提交错误了！",
							timeout : 1000 * 2
						});
			    	});
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
	
	function addnew(){
		$dialog = parent.$.modalDialog({
			title : "添加分区模型",
			width : 480,
			height : 340,
			closed: true,     
	        cache: false,
	        modal : true,
			href : "area/info/addPage",
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function(){
					parent.$("#addForm").form("submit",{
						url :"area/info/addModel",
						
						success : function(result) {
							//var result= eval("(" + result+ ")");
							//parent.$.messager.progress('close');
							result = $.parseJSON(result);
							if (result.status) {	
								$dialog.dialog('close');
								$grid.datagrid('reload');
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

	
	