function addIos(){
	parent.$.modalDialog({
		title : "添加IOS二维码",
		width : 400,
		height : 260,
		href : "code/addIosCode",
		onLoad:function(result, textStatus){
			 parent.$.modalDialog.handler.find("#file").uploadify({
		        swf: 'static/swf/uploadify.swf',  //FLash文件路径
		        buttonText: '上传文件',                                 //按钮文本
		        buttonImg:null ,
		        cancelImage:  'static/img/uploadify-cancel.png' ,
		        debug: false,
		        method: 'post',//和后台交互的方式：post/get
		        uploader:  'code/uploadImage',                   //处理文件上传Action
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
				//parent.$.modalDialog.openner= $dg;
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
function deleteIos(){
	parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){
	    if (r){  
	    	$.post('code/iosdelete',function(rsp){
	    		if(rsp.status){
	    			window.location.href=window.location.href;
	    			//$dg.datagrid('reload');
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
}
function addAndroid(){
	parent.$.modalDialog({
		title : "添加安卓二维码",
		width : 400,
		height : 260,
		href : "code/addAndroidCode",
		onLoad:function(result, textStatus){
			 parent.$.modalDialog.handler.find("#file").uploadify({
		        swf: 'static/swf/uploadify.swf',  //FLash文件路径
		        buttonText: '上传文件',                                 //按钮文本
		        buttonImg:null ,
		        cancelImage:  'static/img/uploadify-cancel.png' ,
		        debug: false,
		        method: 'post',//和后台交互的方式：post/get
		        uploader:  'code/uploadImage',                   //处理文件上传Action
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
				//parent.$.modalDialog.openner= $dg;
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
function deleteAndroid(){
	parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){
	    if (r){  
	    	$.post('code/androiddelete',function(rsp){
	    		if(rsp.status){
	    			window.location.href=window.location.href;
	    			//$dg.datagrid('reload');
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
}