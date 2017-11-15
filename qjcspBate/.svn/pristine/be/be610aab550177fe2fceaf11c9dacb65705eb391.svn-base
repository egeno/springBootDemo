		var $dg;
		var $grid ;
		$(function(){
			 $dg = $("#dg");
			 $grid=$dg.datagrid({
				width : 'auto',
				height : $(this).height(),
				url :  "machine/info/findInfoPage",
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
				             {field : 'companyName',title : '所属公司',align : 'center'},
				             {field : 'machineIcon',title : '设备图标',align : 'center',
				            	formatter:function(value,row,index){
				            		return '<img src="' +baseUrls.ctx+ value + '" width="57" height="64" style="margin: 2px;" />'
					          	}
				              },
				              {field : 'machineName',title : '设备名称',align : 'center'},
				              {field : 'deviceCode',title : '设备编号',align : 'center'},
				              {field : 'modelType',title : '设备类型',align : 'center',
				            	  formatter:function(value,row){
				            		  if("1"==row.modelType){
										return "加热";
				            		  } else if("0"==row.modelType){
				            			  return "制冷";
				            		  } else {
				            			  return "冷热一体";
				            		  }
				            		  
									},
									/*editor:{type:'checkbox',options:{on:'1',off:'0'}}*/
			              },
				              {field : 'machineStatus',title : '是否启用',align : 'center',
				            	  formatter:function(value,row){
				            		  if("1"==row.machineStatus)
										return "<font color=green>是<font>";
				            		  else
				            			return "<font color=red>否<font>";  
									},
									editor:{type:'checkbox',options:{on:'1',off:'0'}}
				              },
				              {field : 'machineRow',title : '层数',align : 'center'},
				              {field : 'machineColumn',title : '列数',align : 'center'},
				              {field : 'tolerance',title : '温度误差',align : 'center'},
				              {field : 'targetTemperature',title : '控温目标温度',align : 'center'},
				              {field : 'province',title : '省份',align : 'center'},
				              {field : 'cityName',title : '城市',align : 'center'},
				              {field : 'areaName',title : '区域',align : 'center'},
				              {field : 'address',title : '详细地址',align : 'center'},
				              {field : 'latitude',title : '纬度',align : 'center'},
				              {field : 'longitude',title : '经度',align : 'center'},
				              {field : 'machineMemo',title : '描述',align : 'center'}
				              ] ],toolbar:'#tb'
			});
			 
			//搜索框
			$("#searchbox").searchbox({ 
				 menu:"#search", 
				 prompt :'模糊查询',
			    searcher:function(value,name){   
			    	var str="{\"search_"+name+"\":\""+value+"\"}";
		            var obj = eval('('+str+')');
		            $dg.datagrid('reload',obj); 
			    }
			});
		});	 
		function add(){
			
			parent.$.modalDialog({
				title : "添加设备信息",
				width : 650,
				height : 500,
				href : "machine/info/addInfoMain",
				onLoad:function(result, textStatus){
					 parent.$.modalDialog.handler.find("#file").uploadify({
				        swf: 'static/swf/uploadify.swf',  //FLash文件路径
				        buttonText: '上传文件',                                 //按钮文本
				        buttonImg:null ,
				        cancelImage:  'static/img/uploadify-cancel.png' ,
				        debug: false,
				        method: 'post',//和后台交互的方式：post/get
				        uploader:  'machine/info/uploadImage',                   //处理文件上传Action
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
				        	parent.$.modalDialog.handler.find('#machineIcon').attr('value',result.imageUrl) ;
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
					title : "编辑设备信息",
					width : 650,
					height : 550,
					href : "machine/info/editInfoMain?machineId=" + row.machineId ,
					onLoad:function(result, textStatus){
						
						 parent.$.modalDialog.handler.find("#file").uploadify({
						        swf: 'static/swf/uploadify.swf',  //FLash文件路径
						        buttonText: '上传文件',                                 //按钮文本
						        buttonImg:null ,
						        cancelImage:  'static/img/uploadify-cancel.png' ,
						        debug: false,
						        method: 'post',//和后台交互的方式：post/get
						        uploader: 'machine/info/uploadImage',                   //处理文件上传Action
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
						        	parent.$.modalDialog.handler.find('#machineIcon').attr('value',result.imageUrl) ;
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
					$.messager.confirm('提示','确定删除该设备信息?', function(r){
						if (r){
						 	$.ajax({
					    		url : 'machine/info/delInfo',
					    		type : 'POST',
					    		async : false, 
					    		cache :  false,
					    		dataType : 'json',
					    		data : {"machineId":row.machineId} ,
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
					parent.$.messager.alert('info',"该设备信息已经删除!") ;
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



	
