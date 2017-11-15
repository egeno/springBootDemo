      var $dg;
		var $grid ;
		
		$(function(){
			 $dg = $("#dg");
			 $grid=$dg.datagrid({
				width : 'auto',
				height : $(this).height(),
				url :  "version/android/findAllAndroidVersionPage",
				queryParams:{},
				rownumbers:true,
				fit:false,
				fitColumns:true,
				pagination:true,
				pageSize:10,
				pageList:[ 10, 20, 30, 40, 50 ],
				border:false,
				singleSelect:true,
				striped:true,
				cache:false,
				nowrap:true,
				loadMsg:"正在加载,请稍后...",
				onLoadSuccess : function(data){
				},
				columns : [ [
                              {field : 'id',title : '版本id编号',align : 'center'},
				              {field : 'version',title : '版本号',align : 'center'},
				              {field : 'updateContent',title : '更新内容',align : 'center'},
				              {field : 'updateUrl',title : '访问URL',align : 'center'},
				              {field : 'createTime',title : '创建时间',align : 'center'},
				              {field : 'modifyTime',title : '修改时间',align : 'center'},
				              {field : 'isValid',title : '是否有效',align : 'center',
				            	  formatter:function(value,row){
				            		  if("1"==row.isValid)
										return "<font color=green>是<font>";
				            		  else
				            			return "<font color=red>否<font>";  
									},
									editor:{type:'checkbox',options:{on:'1',off:'0'}}
				              },
				              {field : 'appType',title : 'APP类别',align : 'center',
				            	  formatter:function(value,row){
				            		  if("0"==row.appType)
										return "订餐APP";
				            		  else if("1"==row.appType)
				            			return "众包商和补货人员APP";  
				            		  else if("2"==row.appType)
					            			return "商户APP";  
				            		  else if("3"==row.appType)
					            			return "维修人员APP";
									}
				              },
				              ] ],toolbar:'#tb'
			});
		});	 		
function add(){
			
			parent.$.modalDialog({
				title : "添加版本",
				width : 650,
				height : 500,
				href: "version/android/addAndroidVersion",
				
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : function(){
						parent.$.modalDialog.openner= $grid;
						var f = parent.$.modalDialog.handler.find("#addForm");
						f.submit();
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.messager.progress('close');
						parent.$.modalDialog.handler.dialog('close');
						parent.$.modalDialog.openner=undefined ;
					}
				}
				]
			}) ;
		}

	

function edit(){
	var row = $dg.datagrid('getSelected');
	if(row){
		parent.$.modalDialog({
			title : "编辑版本",
			width : 650,
			height : 500,
			href : "version/android/editAndroidVersion?id=" + row.id ,
			onLoad:function(){
				if(row.appType=="0"){
				   parent.$("#appType").combobox('setValue',0)
				}else if(row.appType=="1"){
					parent.$("#appType").combobox('setValue',1)
				}else if(row.appType=="2"){
					parent.$("#appType").combobox('setValue',2)
				}else if(row.appType=="3"){
					parent.$("#appType").combobox('setValue',3)
				}
				
				if(row.isValid=="0"){
					parent.$("#isValid").combobox('setValue',0)
				}else{
					parent.$("#isValid").combobox('setValue',1)
				}
				
				
			},
			
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function(){
					parent.$.modalDialog.openner= $grid;
					var f = parent.$.modalDialog.handler.find("#editForm");
					f.submit();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					parent.$.messager.progress('close');
					parent.$.modalDialog.handler.dialog('close');
					parent.$.modalDialog.openner=undefined ;
				}
			}
			]
		}) ;
	}else{
		parent.$.messager.show({
			title :"提示",
			msg :"请选择一行记录!",
			timeout : 1000 * 2
		});
	}
}


function disabled(){
	var row = $dg.datagrid('getSelected');
	if(row){
		if(row.state != -1){
			$.messager.confirm('提示','确定删除该版本信息吗?', function(r){
				if (r){
				 	$.ajax({
			    		url : 'version/android/delVersion',
			    		type : 'POST',
			    		async : false, 
			    		cache :  false,
			    		dataType : 'json',
			    		data : {"id":row.id} ,
			    		beforeSend : function(){
			    			parent.$.messager.progress({
			    				title : '提示',
			    				text : '数据删除中，请稍后....'
			    			});
			    		},
			    		onClose : function(){
			    			
			    		},
			    		success : function(result, textStatus){
			    			if(textStatus == 'success'){
			    				parent.$.messager.progress('close');
			    				if(result.status){
			    					$grid.datagrid('reload'); 
			    				}
			    				parent.$.messager.show({
		    	    				title :"提示",
		    	    				msg :result.message,
		    	    				timeout : 1000 * 3
		    	    			});
			    				return ;
			    			}
			    		}
			    	}) ;
				}
			}) ;
		}else{
			parent.$.messager.alert('info',"该版本已经删除!") ;
		}
	}else{
		parent.$.messager.show({
			title :"提示",
			msg :"请选择一行记录!",
			timeout : 1000 * 2
		});
	}
}

