var $dg;
var $grid;
var psdate;
$(function() {
	$("#panel").panel({
		   width:'auto',   
		   height:$(this).height()  
	});
	$dg = $("#dg");
	$dg.datagrid({
		url :  "advertisement/advertisement",
		width : 'auto',
		height :  $(this).height(),
		border:false,
		singleSelect:true,
		rownumbers:true,
		striped:true,
		fit:false,
		fitColumns:true,
		pagination:true,
		pageSize:10,
		pageList:[ 10, 20, 30, 40, 50 ],
		cache:false,
		columns : [ [
		             {field : 'adPicId',hidden:true,align : 'center',editor : "text"},
		             {field : 'adPicUrl',title : '商品图标',align : 'center',
		            	  formatter:function(value,row,index){
			             		 return '<img src="' +baseUrls.ctx+ value + '" width="50" height="50" />'
			            	  }
		              },
		              {field : 'adPicName',title : '图片名称',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'adPicType',title : '图片用途',width : parseInt($(this).width()*0.12),align : 'center',editor : "text",
		            	  formatter: function(value,row,index){
				 				if (row.adPicType==1){
									return 'Banner条图片';
								} else {
									return '';
								}
		            	  }
		              },
		             {field : 'adHrefUrl',title : '图片链接',width : parseInt($(this).width()*0.25),align : 'center',editor : "text"},
		             {field : 'picSortNum',title : '图片顺序',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"}	,
		             {field : 'adPicDesc',title : '图片描述',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"}			             
		             ] ],toolbar:'#tb'
	});
	$(".datebox :text").attr("readonly","readonly");
});

function getData(){
	var picUsed = $('#picUsed').val();
	var picName = $('#picName').val();
	$("#dg").datagrid("reload",{
		picUsed:picUsed,
		picName:picName
	});
}

function deleterow(){
	var row = $('#dg').datagrid('getSelected');
	if(row){
		parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){
		    if (r){  
		    	$.post('advertisement/delete',{'adPicId':row.adPicId},function(rsp){
		    		if(rsp.status){
		    			$dg.datagrid('reload');
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


function add(){
	
	parent.$.modalDialog({
		title : "添加图片广告",
		width : 650,
		height : 390,
		href : "advertisement/addPage",
		onLoad:function(result, textStatus){
			 parent.$.modalDialog.handler.find("#file").uploadify({
		        swf: 'static/swf/uploadify.swf',  //FLash文件路径
		        buttonText: '上传文件',                                 //按钮文本
		        buttonImg:null ,
		        cancelImage:  'static/img/uploadify-cancel.png' ,
		        debug: false,
		        method: 'post',//和后台交互的方式：post/get
		        uploader:  'advertisement/uploadImage',                   //处理文件上传Action
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
		        		//alert(result.message);
		        		return false;
		        	}
		        	//alert(result.imageUrl);
		        	parent.$.modalDialog.handler.find('#image1').attr('src',baseUrls["ctx"]+result.imageUrl) ;
		        	parent.$.modalDialog.handler.find('#goodsIcon').attr('value',result.imageUrl) ;
		        	parent.$.modalDialog.handler.find('#imageDiv').show() ;
		        }
		    });
		},
		buttons : [ {
			text : '确定',
			iconCls : 'icon-ok',
			handler : function(){
				parent.$.modalDialog.openner= $dg;
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
				title : "编辑图片广告",
				width : 650,
				height : 390,
				href : "advertisement/editPage?adPicId=" + row.adPicId ,
				onLoad:function(result, textStatus){
					
					 parent.$.modalDialog.handler.find("#file").uploadify({
					        swf: 'static/swf/uploadify.swf',  //FLash文件路径
					        buttonText: '上传文件',                                 //按钮文本
					        buttonImg:null ,
					        cancelImage:  'static/img/uploadify-cancel.png' ,
					        debug: false,
					        method: 'post',//和后台交互的方式：post/get
					        uploader: 'advertisement/uploadImage',                   //处理文件上传Action
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
					        	parent.$.modalDialog.handler.find('#goodsIcon').attr('value',result.imageUrl) ;
					        }
					    });

				},			
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : function(){
						parent.$.modalDialog.openner= $dg;
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










function exportExcel(){
	psdate = $('#psdate').datebox('getValue');
	location.href=urls['ctx']+"/statistics/machineSupplyReportExport?psdate="+psdate;
}



function seeChart(){
	var options = $dg.datagrid('getPager').data("pagination").options;
	var pageNumber = options.pageNumber;
	var pageSize = options.pageSize;
	psdate = $('#psdate').datebox('getValue');
	parent.$.modalDialog({
		title : "每日柜子供销图表",
		width : 1200,
		height : 700,
		href : urls["ctx"] + "/statistics/machineSupplyChart?psdate="+psdate+"&pageNumber="+pageNumber+"&pageSize="+pageSize,
		buttons : [ {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				parent.$.messager.progress('close');
				parent.$.modalDialog.handler.dialog('close');
				parent.$.modalDialog.openner=undefined ;
			}
		}]
	});
}