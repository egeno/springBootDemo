      var $dg;
		var $grid ;
		
		$(function(){
			 $dg = $("#dg");
			 $grid=$dg.datagrid({
				width : 'auto',
				height : $(this).height(),
				url :  "version/ios/findAllIosVersionPage",
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
				              {field : 'versionKey',title : '版本键',align : 'center'},
				              {field : 'versionValue',title : '版本值',align : 'center'},
				              {field : 'modifyTime',title : '修改时间',align : 'center'},
				              /*{field : 'updateUrl',title : '访问URL',align : 'center'},
				              {field : 'createTime',title : '创建时间',align : 'center'},
				              
				              {field : 'isValid',title : '是否有效',align : 'center',
				            	  formatter:function(value,row){
				            		  if("1"==row.isValid)
										return "<font color=green>是<font>";
				            		  else
				            			return "<font color=red>否<font>";  
									},
									editor:{type:'checkbox',options:{on:'1',off:'0'}}
				              },*/
				              ] ],toolbar:'#tb'
			});
		});	 		
function add(){
			
			parent.$.modalDialog({
				title : "添加版本",
				width : 400,
				height : 230,
				href: "version/ios/addIosVersion",
				
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
			width : 400,
			height :230,
			href : "version/ios/editIosVersion?id=" + row.id ,	
			
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
			    		url : 'version/ios/delVersion',
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

