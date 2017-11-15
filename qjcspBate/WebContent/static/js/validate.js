$.extend($.fn.validatebox.defaults.rules, {
	    //验证汉字
	    CHS: {
	        validator: function (value) {
	            return /^[\u0391-\uFFE5]+$/.test(value);
	        },
	        message: '只能输入汉字'
	    },
	    //手机号码验证
	    mobile: {//value值为文本框中的值
	        validator: function (value) {
	            var reg = /^1[3|4|5|7|8|9]\d{9}$/;
	            return reg.test(value);
	        },
	        message: '输入手机号码格式不准确.'
	    },
	    parenthese:{
	    	 validator: function (value, param) {
		            if (value.length < param[0] || value.length > param[1]) {
		                $.fn.validatebox.defaults.rules.parenthese.message = '长度必须在' + param[0] + '至' + param[1] + '范围';
		                return false;
		            } else {
		            	//new RegExp("\（").test(value)
//		                if (!new RegExp(/^[\u4e00-\u9fa5|\w]+\([\u4e00-\u9fa5|\w]+\)$/).test(value)) {
		            	if (!new RegExp(/^[^ \f\n\r\t\v]+\([\u4e00-\u9fa5|\w]+\)$/).test(value)) {
//		            	    $.fn.validatebox.defaults.rules.parenthese.message = '不能包含特殊字符,示例：酸辣白菜(张三店)';
		            	    $.fn.validatebox.defaults.rules.parenthese.message = '可以包含特殊字符,示例：酸辣白菜(张三店)';
		                    return false;
		                }
		            	if(value.indexOf('(')>15 ||value.indexOf('(')==0){
		            		$.fn.validatebox.defaults.rules.parenthese.message = '菜品名称必须在1-15个字';
		                    return false;
		            	}
		            	if(value.length-value.indexOf('(')-2>10){
		            		$.fn.validatebox.defaults.rules.parenthese.message = '商户名字不能超过10个字';
		                    return false;
		            	}else {
		                    return true;
		                }
		            }
		        }, message: ''
	    },
	    //用户账号验证(只能包括 _ 数字 字母) 
	    account: {//param的值为[]中值
	        validator: function (value, param) {
	            if (value.length < param[0] || value.length > param[1]) {
	                $.fn.validatebox.defaults.rules.account.message = '长度必须在' + param[0] + '至' + param[1] + '范围';
	                return false;
	            } else {
	                if (!/^[\w]+$/.test(value)) {
	                    $.fn.validatebox.defaults.rules.account.message = '只能数字、字母、下划线组成.';
	                    return false;
	                } else {
	                    return true;
	                }
	            }
	        }, message: ''
	    },
	    phoneRex: {
	        validator: function(value){
	        var rex=/^1[3-8]+\d{9}$/;
	        //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	        //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
	        //电话号码：7-8位数字： \d{7,8
	        //分机号：一般都是3位数字： \d{3,}
	         //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/		 
	        var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	        if(rex.test(value)||rex2.test(value))
	        {
	          // alert('t'+value);
	          return true;
	        }else
	        {
	         //alert('false '+value);
	           return false;
	        }
	          
	        },
	        message: '请输入正确电话格式，如0571-88888888'
	    },
	    
	    addareaName:{
		    validator:function(value,param){
		    	var reg=/^[a-zA-Z]{1,2}$/;
				return reg.test(value);

			},

		message:'请输入大小写字母,长度不能超过2个字节'
	  },

	  
//	  areaGoodNumCount:{
//		  validator:function(value,param){
//			    var str=$("#"+param).text();
//			    var count=str.substring(1);
//				if(/^\d+$/.test(value)&& parseInt(value)<=parseInt(count)){
//					return true;
//			   		
//			   	}else{
//			   		$.fn.validatebox.defaults.rules.areaGoodNumCount.message = '只允许输入数字,且不能大于' + count;
//			   	}
//
//			},
//		message:''
//	  
//	  },
	  
	  areaGoodNumCount:{
		  validator:function(value,param){
			  var reg=/^\d{1,2}$/;
			  return reg.test(value);

			},
		message:'请输入1-2位的数字'
	  
	  },
	  numCode:{
		  validator: function (value, param) {
	            if (value.length < param[0] || value.length > param[1]) {
	                $.fn.validatebox.defaults.rules.numCode.message = '长度必须在' + param[0] + '至' + param[1] + '范围';
	                return false;
	            } else {
	                if (!/^[\d]+$/.test(value)) {
	                    $.fn.validatebox.defaults.rules.numCode.message = '请输入数字';
	                    return false;
	                } else {
	                    return true;
	                }
	            }
	        },message:''
	  
	  },
	  
	  discountMoney:{
		  validator:function(value,param){
			  var reg=/^\d+(\.\d+)?$/;
			  return reg.test(value);

			},
		message:'请输入数字或者小数'
	  
	  },
	})
	
function check(){
        var userMobile = $('#userMobile').val();
        $.ajax({
        url: "shiro/check",
        type: "post",
        data:"userMobile="+userMobile,
        dataType:"text",
        success:function(text){
            if(text!=""){
                var checkSpan = $('#checkSpan');
                checkSpan.text(text);
            }
            else{
                var checkSpan = $('#checkSpan');
                checkSpan.text("");
            }
        }
        })
    }
	
	function checkName(){
		var userName = $('#userName').val();
        $.ajax({
	        url: "shiro/checkName",
	        type: "post",
	        data:"userName="+userName,
	        dataType:"text",
	        success:function(text){
	            if(text!=""){
	                var nameSpan = $('#nameSpan');
	                nameSpan.text(text);
	            }
	            else{
	                var nameSpan = $('#nameSpan');
	                nameSpan.text("");
	            }
	        }
        })
	}
	
	function checkUser(){
		var userAccount = $('#userAccount').val();
        $.ajax({
	        url: "shiro/checkAccount",
	        type: "post",
	        data:"userAccount="+userAccount,
	        dataType:"text",
	        success:function(text){
	            if(text!=""){
	                var accountSpan = $('#accountSpan');
	                accountSpan.text(text);
	            }
	            else{
	                var accountSpan = $('#accountSpan');
	                accountSpan.text("");
	            }
	        }
        })
	}