$(function  () {
	checkOpenId();
	//获取短信验证码
	var validCode=true;
	$(".huoqu").click (function  () {
		var time=60;
		var code=$(this);
		if (validCode) {
			validCode=false;
			code.addClass("huoqu1");
		var t=setInterval(function  () {
			time--;
			code.html("重新获取"+"（"+time+"）"+"秒");
			if (time==0) {
				clearInterval(t);
			code.html("重新获取");
				validCode=true;
			code.removeClass("huoqu1");

			}
		},1000)
		}
	})
})

$(function  () {
	var mobileNum = localStorage.getItem('telphoneNum');
	var reg = /(\d{3})\d{4}(\d{4})/;
	$(".sendout span").text(mobileNum.replace(reg,"$1****$2"));
})
$(document).ready(function(){
	$(".huoqu").click(function(){
		sendVerifyCodeMessage();
	});
	$(".completee").click(function(){
		registLoginCodeVerify();
	})
})
function sendVerifyCodeMessage(){
	var mobileNum = localStorage.getItem('telphoneNum');
    var url = apiurl + 'api/app/sendVerifyCodeMessage';
    console.log(url);
    var jsonData={"mobileNum":mobileNum};
    $.ajax({
        type:'post',      
        url:url,  
        data:$.toJSON(jsonData),
        dataType:'json', 
        async:false,   
        success:function(data){
        	var returnCode = eval(data.returnCode);
        	var returnContent = eval(data.returnContent);
        	console.log("returnCode:" + JSON.stringify(returnCode) + ",returnContent:" + JSON.stringify(returnContent));
        	var nickName = returnContent.nickName;
        	localStorage.setItem("nickName",nickName);
    		$(".btn").click(function(){
        		registLoginCodeVerify(telphoneNum);
        	})
        	
        	
        },
        error:function(){
    		console.log("sendVerifyCodeMessage is wrong");
    	}
    });
	
}
function registLoginCodeVerify(){
	var mobileNum = localStorage.getItem('telphoneNum');
	var verifyCode = $('.obtainon_input').val();
	console.log("verifyCode:" + verifyCode);
	var url = apiurl + 'api/app/registLoginCodeVerify';
    console.log(url);
    var jsonData={"mobileNum":mobileNum,"verifyCode":verifyCode};
    $.ajax({
        type:'post',      
        url:url,  
        data:$.toJSON(jsonData),
        dataType:'json', 
        async:false,   
        success:function(data){
        	var returnCode = eval(data.returnCode);
        	var returnContent = eval(data.returnContent);
        	console.log("returnCode:" + JSON.stringify(returnCode) + ",returnContent:" + JSON.stringify(returnContent));
        	var customerId = returnContent.customerId;
        	sessionStorage.setItem("customerId",customerId);
        	window.location.href = '../view/setPwd.html';
        },
        error:function(){
    		console.log("registLoginCodeVerify is wrong");
    	}
    });
}




