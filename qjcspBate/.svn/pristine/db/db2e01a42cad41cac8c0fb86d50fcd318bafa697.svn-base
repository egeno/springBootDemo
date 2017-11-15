function checkOpenId(){
	if(sessionStorage.getItem('openid')==null){
		var openid="";
		sessionStorage.setItem('openid',openid);
	}else{
		var openid=sessionStorage.getItem('openid');  
	}
	var apiurl = 'http://weixinwap.quanjiakeji.com/qjcspBate/';
	var openid=sessionStorage.getItem('openid');
	var access_code=getQueryString('code'); 
	localStorage.setItem("code",access_code);
	if (openid==""){  
	    if (access_code==null)  
	    {     
	        var fromurl=location.href; 
	        console.log(fromurl);
	        var url='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxced7bcad3136f113&redirect_uri='+encodeURIComponent(fromurl)+'&response_type=code&scope=snsapi_base&state=STATE%23wechat_redirect&connect_redirect=1#wechat_redirect';  
	        location.href=url;  
	    }  
	    else  
	    {     
	        $.ajax({  
	            type:'get',  
	            url:apiurl+'/weixinwap/getwxopenid',   
	            async:false,  
	            cache:false,  
	            data:{code:access_code},  
	            dataType:'json',  
	            success:function(result){                   
	                    if (result!=null && result.hasOwnProperty('openid') && result.openid!=""){  
	                    	sessionStorage.setItem('openid',result.openid);  
	                    	localStorage.setItem('key',result.access_token);
	                    	//alert("openid"+result.openid);  
	                    }   
	                    else  
	                    {  
	                      //alert('微信身份识别失败 \n '+result);   
	                      location.href=fromurl;  
	                    }  
	                }  
	            });      
	    }  
	} 

}
function checkCustomerId(){
	
	if(sessionStorage.getItem('openid')==null){
		var openid="";
		sessionStorage.setItem('openid',openid);
	}else{
		var openid=sessionStorage.getItem('openid');  
	}
	
	if(sessionStorage.getItem('customerId')==null){
		var customerId="";
		sessionStorage.setItem('customerId',customerId);
	}else{
		var customerId= sessionStorage.getItem('customerId');
	}
	
	if (customerId==''){  
        var access_code=getQueryString('code'); 
        localStorage.setItem("code",access_code);
        if (openid==""){  
            if (access_code==null)  
            {     
                var fromurl=location.href; 
                console.log(fromurl);
                var url='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxced7bcad3136f113&redirect_uri='+encodeURIComponent(fromurl)+'&response_type=code&scope=snsapi_base&state=STATE%23wechat_redirect&connect_redirect=1#wechat_redirect';  
                location.href=url;  
            }  
            else  
            {     
                $.ajax({  
                    type:'get',  
                    url:apiurl+'/weixinwap/getwxopenid',   
                    async:false,  
                    cache:false,  
                    data:{code:access_code},  
                    dataType:'json',  
                    success:function(result){                   
                            if (result!=null && result.hasOwnProperty('openid') && result.openid!=""){  
                            	sessionStorage.setItem('openid',result.openid);  
                            	localStorage.setItem('key',result.access_token);
                            	//alert("openid"+result.openid);
                            	getlogininfo(result.openid);  
                            }   
                            else  
                            {  
                              //alert('微信身份识别失败 \n '+result);   
                              location.href=fromurl;  
                            }  
                        }  
                    });      
            }  
        }else{  
           if (customerId=='' && openid!='')  {
        	   var   openid=sessionStorage.getItem('openid');
               getlogininfo(openid);    
            }
        }  
	}
}

		
function getlogininfo(openid){     
	var jsonData= {"openId":openid};
    $.ajax({  
       type:'post',  
       url: apiurl + 'api/wechat/getCustomerBaseInfoWX',  
       data:$.toJSON(jsonData),
       dataType:'json',  
       async:false,  
       cache:false,                 
       success: function (result) {                     
           console.log("result:"+JSON.stringify(result));    
    	   if (result.returnCode=='1'){  
    		   	   sessionStorage.setItem('customerId',result.returnContent.customerId);  
            	   localStorage.setItem('username',result.returnContent.customerName);  
            	   localStorage.setItem('telphoneNum',result.returnContent.telphoneNum);
            	   console.log("result.returnCode"+result.returnCode);
            	   sessionStorage.setItem('attentionTime',result.returnContent.attentionTime);  
               }
               else if(result.returnCode=="5"){
                   window.location.href = "bind.html";
               }
               else{  
            	   console.log("msg"+result.return_msg); 
               }  
       }  
    });  
}  

function getQueryString(name) {  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
    var r = location.search.substr(1).match(reg);  
    if (r != null) return unescape(decodeURI(r[2])); return null;  
}  
/*function getCookie() {
	
		 var url = location.search; //获取url中"?"符后的字串
		   var theRequest = new Object();
		   if (url.indexOf("?") != -1) {
		      var str = url.substr(1);
		      strs = str.split("%26");
		      theRequest[strs[0].split("=")[0]]=(strs[0].split("=")[1]);
		   }
		activityId=theRequest["activityId"];
		
		c_openId="openId";
		if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(c_openId + "=");
			if (c_start != -1) {
				c_start = c_start + c_openId.length + 1;
				c_end = document.cookie.indexOf(";", c_start);
				if (c_end == -1)
					c_end = document.cookie.length;
				openId=unescape(document.cookie.substring(c_start, c_end));//通过截取字符串，获取到openId。
				window.location.href = "joinActivity.jsf?openId="+openId+"&activityId="+activityId;//获取openId之//后再跳转到指定页面，把该openId放置到浏览器链接中。然后后台可以随时的从链接中确定是该用户在操作。
			}
		}
		window.location.href = "joinActivity.jsf?activityId="+activityId;
	}
*/
