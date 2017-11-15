$package('artup');
var artup={
	/*Json 工具类*/
	isJson:function(str){
		var obj = null; 
		try{
			obj = artup.paserJson(str);
		}catch(e){
			return false;
		}
		var result = typeof(obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length; 
		return result;
	},
	paserJson:function(str){
		return eval("("+str+")"); 
	},
	/*弹出框*/
	alert:function(title, msg, icon, callback){
		$.messager.alert(title,msg,icon,callback);
	},
	/*弹出框*/
	confirm:function(title, msg,callback){
		$.messager.confirm(title,msg,callback);
	},
	show:function(title, msg,timeout,showType){
		$.messager.show({
			title:title,
			msg:msg,
			timeout:5000,
			showType:'slide'
		});
	},
	progress:function(title,msg){
		 var win = $.messager.progress({  
            title: title ||'Please waiting',  
            msg: msg ||'Loading data...'  
         }); 
	},
	closeProgress:function(){
		$.messager.progress('close');
	},
	/*重新登录页面*/
	toLogin:function(){
		window.top.location= urls['msUrl']+"/login.shtml";
	},
	checkLogin:function(data){//检查是否登录超时
		if(data!=null && data.logoutFlag!=undefined && data.logoutFlag!=null && data.logoutFlag){
			artup.closeProgress();
			artup.alert('提示',"登录超时,点击确定重新登录.",'error',artup.toLogin);
			return false;
		}
		return true;
	},
	ajaxSubmit:function(form,option){
		form.ajaxSubmit(option);
	},
	ajaxJson: function(url,option,callback){
		$.ajax({url:url,
			    type:'POST',
			    dataType:'json',
			 	data:option,
			 	success:function(data){
			 		//坚持登录
			 		if(!artup.checkLogin(data)){
			 			return false;
			 		}		 	
			 		if($.isFunction(callback)){
			 			callback(data);
			 		}
			 	},
			 	error:function(response, textStatus, errorThrown){
			 		try{
			 			artup.closeProgress();
			 			var data = $.parseJSON(response.responseText);
				 		//检查登录
				 		if(!artup.checkLogin(data)){
				 			return false;
				 		}else{
					 		artup.alert('提示', data.msg || "请求出现异常,请联系管理员",'error');
					 	}
			 		}catch(e){
			 			alert(e);
			 			artup.alert('提示',"请求出现异常,请联系管理员1",'error');
			 		}
			 	},
			 	complete:function(){
			 	
			 	}
		});
	},
	submitForm:function(form,callback,dataType){
			var option =
			{
			 	type:'post',
			 	dataType: dataType||'json',
			 	success:function(data){
			 		if($.isFunction(callback)){
			 			callback(data);
			 		}
			 	},
			 	error:function(response, textStatus, errorThrown){
			 		try{
			 			artup.closeProgress();
			 			var data = $.parseJSON(response.responseText);
				 		//检查登录
				 		if(!artup.checkLogin(data)){
				 			return false;
				 		}else{
					 		artup.alert('提示', data.msg || "请求出现异常,请联系管理员",'error');
					 	}
			 		}catch(e){
			 			alert(e);
			 			artup.alert('提示',"请求出现异常,请联系管理员1",'error');
			 		}
			 	},
			 	complete:function(){
			 	
			 	}
			 };
			 artup.ajaxSubmit(form,option);
	},
	saveForm:function(form,callback){
		if(form.form('validate')){
	//		artup.progress('Please waiting','Save ing...');
			//ajax提交form
			artup.submitForm(form,function(data){
		//		artup.closeProgress();
			 	if(data.success){
			 		if(callback){
				       	callback(data);
				    }else{
			       		artup.alert('提示','保存成功.','info');
			        } 
		        }else{
		       	   artup.alert('提示',"哲作失败",'error');  
		        }
			});
		 }
	},
	/**
	 * 
	 * @param {} url
	 * @param {} option {id:''} 
	 */
	getById:function(url,option,callback){
	//	artup.progress();
		artup.ajaxJson(url,option,function(data){
	//		artup.closeProgress();
			if(data.success){
				if(callback){
			       	callback(data);
			    }
			}else{
				artup.alert('提示',data.msg,'error');  
			}
		});
	},
	deleteForm:function(url,option,callback){
		artup.progress();
		artup.ajaxJson(url,option,function(data){
				artup.closeProgress();
				if(data.success){
					if(callback){
				       	callback(data);
				    }
				}else{
					artup.alert('提示',result.msg,'error');  
				}
		});
	},
	
	
	//////////////tab开启关闭公共方法
	addTab:function(_title,_url){
		var boxId = '#centerTabs'; 
		var mytabs = window.parent.$(boxId);
		if(mytabs.tabs('exists',_title) ){
			var tab = mytabs.tabs('getTab',_title);
			var index = mytabs.tabs('getTabIndex',tab);
			mytabs.tabs('select',index);
			if(tab && tab.find('iframe').length > 0){  
				 var _refresh_ifram = tab.find('iframe')[0];  
			     _refresh_ifram.contentWindow.location.href=_url;  
		    } 
		}else{		
			var _content ="<iframe scrolling='auto' frameborder='0' src='"+_url+"' style='width:100%; height:100%'  ></iframe>";
			mytabs.tabs('add',{
				    title:_title,
				    content:_content,
				    closable:true});
			
		}
	},
	closeCurTab : function() {
		var tab = parent.$('#centerTabs');
		var curTab = tab.tabs('getSelected');
		var index = tab.tabs('getTabIndex', curTab);
		tab.tabs('close', index);
	}
	
}

/* 自定义密码验证*/
$.extend($.fn.validatebox.defaults.rules, {  
    equals: {  
        validator: function(value,param){  
            return value == $(param[0]).val();  
        },  
        message: 'Field do not match.'  
    }  
});  

/*表单转成json数据*/
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}


//为Date()对象添加format()方法
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/* easyui datagrid 添加和删除按钮方法*/
$.extend($.fn.datagrid.methods, {  
    addToolbarItem: function(jq, items){  
        return jq.each(function(){  
            var toolbar = $(this).parent().prev("div.datagrid-toolbar");
            for(var i = 0;i<items.length;i++){
                var item = items[i];
                if(item === "-"){
                    toolbar.append('<div class="datagrid-btn-separator"></div>');
                }else{
                    var btn=$("<a href=\"javascript:void(0)\"></a>");
                    btn[0].onclick=eval(item.handler||function(){});
                    btn.css("float","left").appendTo(toolbar).linkbutton($.extend({},item,{plain:true}));
                }
            }
            toolbar = null;
        });  
    },
    removeToolbarItem: function(jq, param){  
        return jq.each(function(){  
            var btns = $(this).parent().prev("div.datagrid-toolbar").children("a");
            var cbtn = null;
            if(typeof param == "number"){
                cbtn = btns.eq(param);
            }else if(typeof param == "string"){
                var text = null;
                btns.each(function(){
                    text = $(this).data().linkbutton.options.text;
                    if(text == param){
                        cbtn = $(this);
                        text = null;
                        return;
                    }
                });
            } 
            if(cbtn){
                var prev = cbtn.prev()[0];
                var next = cbtn.next()[0];
                if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
                    $(prev).remove();
                }else if(next && next.nodeName == "DIV"){
                    $(next).remove();
                }else if(prev && prev.nodeName == "DIV"){
                    $(prev).remove();
                }
                cbtn.remove();    
                cbtn= null;
            }                        
        });  
    }                 
});

	function StringBuffer() {
		this._strs = new Array();
		return this;
	}
	StringBuffer.prototype.append = function(str) {
		return this._strs.push(str);
	};
	StringBuffer.prototype.toString = function() {
		return this._strs.join("");
	};
	