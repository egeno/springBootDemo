		var $dg;
		var $grid ;
		
		$(function(){
			 $dg = $("#dg");
			 $grid=$dg.datagrid({
				width : 'auto',
				height : $(this).height(),
				url :  "weixinassist/findAssistPage",
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
				              {field : 'picurl',title : '微信互动图标',align : 'center',
				            	  formatter:function(value,row,index){
				            		  if(value!=null && value!=""){
				            			  return '<img src="' +baseUrls.ctx+ value + '" width="50" height="50" />' 
				            		  }else{
				            			  return '<img src="' +baseUrls.ctx+ '/uploadImage/bg.png" width="50" height="50" />'
				            		  }
					             		 
					            	  }
				              },
				              {field : 'code',title : '微信响应字段',align : 'center'},
				              {field : 'title',title : '微信互动标题',align : 'center',width:100},
				              {field : 'type',title : '类型',align : 'center',
				            	  formatter:function(value,row){
									if(row.type == '0'){						
										return "图文消息";				
									}else {
										return "文本消息";
									}					
								}},
				              {field : 'url',title : '跳转URL',align : 'center',width:100},
				              
				              {field : 'content',title : '微信互动内容',align : 'center',width:150},
				              ] ],toolbar:'#tb'
			});
		});	 
		function add(){
			
			parent.$.modalDialog({
				title : "添加微信互动",
				width : 650,
				height : 500,
				href : "weixinassist/addAssistMain",
				onLoad:function(result, textStatus){
					 parent.$.modalDialog.handler.find("#file").uploadify({
				        swf: 'static/swf/uploadify.swf',  //FLash文件路径
				        buttonText: '上传文件',                                 //按钮文本
				        buttonImg:null ,
				        cancelImage:  'static/img/uploadify-cancel.png' ,
				        debug: false,
				        method: 'post',//和后台交互的方式：post/get
				        uploader:  'weixinassist/uploadImage',                   //处理文件上传Action
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
				        	parent.$.modalDialog.handler.find('#picurl').attr('value',result.imageUrl) ;
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

		function edit(){
			var row = $dg.datagrid('getSelected');
			if(row){
				parent.$.modalDialog({
					title : "编辑微信互动",
					width : 700,
					height : 600,
					href : "weixinassist/editAssistMain?id=" + row.id ,
					onLoad:function(result, textStatus){
						
						 parent.$.modalDialog.handler.find("#file").uploadify({
						        swf: 'static/swf/uploadify.swf',  //FLash文件路径
						        buttonText: '上传文件',                                 //按钮文本
						        buttonImg:null ,
						        cancelImage:  'static/img/uploadify-cancel.png' ,
						        debug: false,
						        method: 'post',//和后台交互的方式：post/get
						        uploader: 'weixinassist/uploadImage',                   //处理文件上传Action
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
						        	parent.$.modalDialog.handler.find('#picurl').attr('value',result.imageUrl) ;
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
		function disabled(){
			var row = $dg.datagrid('getSelected');
			if(row){
				if(row.state != -1){
					$.messager.confirm('提示','确定删除该微信互动信息?', function(r){
						if (r){
						 	$.ajax({
					    		url : 'weixinassist/delAssist',
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
					parent.$.messager.alert('info',"该微信互动已经删除!") ;
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
