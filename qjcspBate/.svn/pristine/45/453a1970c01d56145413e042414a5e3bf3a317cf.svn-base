		var $dg;
		var $grid ;
		
		$(function(){
			 $dg = $("#dg");
			 $grid=$dg.datagrid({
				width : 'auto',
				height : $(this).height()-80,
				url :  "machine/model/findModelPage",
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
				              /*{field : 'modelIcon',title : '设备型号图标',align : 'center',
				            	  formatter:function(value,row,index){
					             		 return '<img src="' +baseUrls.ctx+ value + '" width="50" height="50" />'
					            	  }
				              },*/
				              {field : 'modelName',title : '型号名称',align : 'center'},
				              {field : 'modelType',title : '设备类型',align : 'center',
				            	  formatter:function(value,row){
				            		  if("1"==row.modelType){
				            			  return "加热";
				            		  }	else if("0"==row.modelType){
				            			  return "制冷"; 
				            		  }else{
				            			 return "冷热一体";  
				            		  }				            			
									}
								/*	editor:{type:'checkbox',options:{on:'1',off:'0'}}*/
				              },
				              {field : 'rowNum',title : '层数',align : 'center'},
				              {field : 'columnNum',title : '列数',align : 'center'},
				              {field : 'tolerance',title : '温度误差',align : 'center'},
				              {field : 'targetTemperature',title : '目标温度',align : 'center'},
				              {field : 'modelMemo',title : '描述',align : 'center'},
				              {field : 'modelStatus',title : '是否启用',align : 'center',
				            	  formatter:function(value,row){
				            		  if("1"==row.modelStatus)
										return "<font color=green>是<font>";
				            		  else
				            			return "<font color=red>否<font>";  
									},
									editor:{type:'checkbox',options:{on:'1',off:'0'}}
				              },
				              {field : 'creater',title : '创建人',align : 'center'},
				              {field : 'createTimeStr',title : '创建时间',align : 'center'},
				              {field : 'modifier',title : '修改人',align : 'center'},
				              {field : 'lastModTimeStr',title : '最后修改时间',align : 'center'},
				              ] ],toolbar:'#tb'
			});
		});	 
		function add(){
			
			parent.$.modalDialog({
				title : "添加设备型号",
				width : 650,
				height : 320,
				href : "machine/model/addModelMain",
				onLoad:function(result, textStatus){
					 parent.$.modalDialog.handler.find("#file").uploadify({
				        swf: 'static/swf/uploadify.swf',  //FLash文件路径
				        buttonText: '上传文件',                                 //按钮文本
				        buttonImg:null ,
				        cancelImage:  'static/img/uploadify-cancel.png' ,
				        debug: false,
				        method: 'post',//和后台交互的方式：post/get
				        uploader:  'machine/model/uploadImage',                   //处理文件上传Action
				        auto:true,                                 //选择文件后是否自动上传，默认为true
				        multi: false,                                 //是否为多选，默认为true
				        removeCompleted: true,                       //是否完成后移除序列，默认为true
				        fileSizeLimit: '10MB',                       //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
				        fileTypeDesc: '请选择jpg png bmp图片文件',                 //文件描述
				        fileTypeExts: '*.jpg;*.png;*.bmp;',  //上传的文件后缀过滤器
				        fileObjName:'imageFile',
				        onQueueComplete: function (event, data){
				        	
				        },
				        onUploadStart: function(file) {

				        },
				        onUploadError: function (event, queueId, fileObj, errorObj) {
				        	parent.$.messager.show({
								title :"提示",
								msg : fileObj.name + "上传失败!",
								timeout : 1000 * 3
							});
				        },
				        onUploadSuccess: function (file, data, response) {
				        	var result = eval('(' + data + ')'); 
				        	
				        	if (result.status == false){
				        		alert(result.message);
				        		return false;
				        	}
				        	
				        	parent.$.modalDialog.handler.find('#image1').attr('src',baseUrls["ctx"]+result.imageUrl) ;
				        	parent.$.modalDialog.handler.find('#modelIcon').attr('value',result.imageUrl) ;
				        	parent.$.modalDialog.handler.find('#imageDiv').show() ;
				        }
				    });
				},
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
		
		function edit(row){
			var row = $dg.datagrid('getSelected');
			$.ajax({
				type:"post",
				url:"machine/model/findMachineCount",
				data:"modelId="+row.modelId,
				success:function(data){
					if(data != 0){
						parent.$.messager.show({
							title :"提示",
							msg :"该设备型号下有设备，请先删除设备",
							timeout : 1000 * 2
						});
					}
					else{
						if(row){
							parent.$.modalDialog({
								title : "编辑设备型号",
								width : 650,
								height : 320,
								href : "machine/model/editModelMain?modelId=" + row.modelId ,
								onLoad:function(result, textStatus){
									
									 parent.$.modalDialog.handler.find("#file").uploadify({
									        swf: 'static/swf/uploadify.swf',  //FLash文件路径
									        buttonText: '上传文件',                                 //按钮文本
									        buttonImg:null ,
									        cancelImage:  'static/img/uploadify-cancel.png' ,
									        debug: false,
									        method: 'post',//和后台交互的方式：post/get
									        uploader: 'machine/model/uploadImage',                   //处理文件上传Action
									        auto:true,                                 //选择文件后是否自动上传，默认为true
									        multi: false,                                 //是否为多选，默认为true
									        removeCompleted: true,                       //是否完成后移除序列，默认为true
									        fileSizeLimit: '10MB',                       //单个文件大小，0为无限制，可接受KB,MB,GB等单位的字符串值
									        fileTypeDesc: '请选择jpg png bmp图片文件',                 //文件描述
									        fileTypeExts: '*.jpg;*.png;*.bmp;',  //上传的文件后缀过滤器
									        fileObjName:'imageFile',
									        onQueueComplete: function (event, data){
									        	
									        },
									        onUploadStart: function(file) {
									        	
									        },
									        onUploadError: function (event, queueId, fileObj, errorObj) {
									        	parent.$.messager.show({
													title :"提示",
													msg : fileObj.name + "上传失败!",
													timeout : 1000 * 3
												});
									        },
									        onUploadSuccess: function (file, data, response) {
									        	var result = eval('(' + data + ')'); 
									        	
									        	if (result.status == false){
									        		alert(result.message);
									        		return false;
									        	}
									        	
									        	parent.$.modalDialog.handler.find('#image1').attr('src',baseUrls["ctx"]+result.imageUrl) ;
									        	parent.$.modalDialog.handler.find('#modelIcon').attr('value',result.imageUrl) ;
									        }
									    });

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
					
				}
			});
		}
		function disabled(){
			var row = $dg.datagrid('getSelected');
			if(row){
				if(row.state != -1){
					$.messager.confirm('提示','确定删除该设备型号?', function(r){
						if (r){
						 	$.ajax({
					    		url : 'machine/model/delModel',
					    		type : 'POST',
					    		async : false, 
					    		cache :  false,
					    		dataType : 'json',
					    		data : {"modelId":row.modelId} ,
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
					parent.$.messager.alert('info',"该设备型号已经删除!") ;
				}
			}else{
				parent.$.messager.show({
					title :"提示",
					msg :"请选择一行记录!",
					timeout : 1000 * 2
				});
			}
		}

		function addTab(_title,_url){
			var boxId = '#centerTabs'; 
			var mytabs = window.parent.$(boxId);
			if(mytabs.tabs('exists',_title) ){
				var tab = mytabs.tabs('getTab',_title);
				var index = mytabs.tabs('getTabIndex',tab);
				mytabs.tabs('select',index);
				if(tab && tab.find('iframe').length > 0){  
					 var _refresh_ifram = tab.find('iframe')[0]; 
					 var temp = _url.substring(18,_url.length) ;
				     _refresh_ifram.contentWindow.location.href=temp;  
			    } 
			}else{		
				var _content ="<iframe scrolling='auto' frameborder='0' src='"+_url+"' style='width:100%; height:100%'  ></iframe>";
				mytabs.tabs('add',{
					    title:_title,
					    content:_content,
					    closable:true});
				
			}
		}



	
