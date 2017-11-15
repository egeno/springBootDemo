$(document).ready(function() {
	checkOpenId();
	$(".btn").addClass("disable");
    $(".ccheck").click(function() {
        var num=$(this).attr("num")
        if(num=="1"){
            $(this).addClass('cchecked');
            $(this).attr("num","2");
            $(".btn").removeClass("disable");
        }else {
            $(this).removeClass('cchecked');
            $(this).attr("num","1");
            $(".btn").addClass("disable");
        }
    });
    $(".hqyzm").click(function() {
        var count = 59;
        var countdown = setInterval(CountDown, 1000);
        function CountDown() {
            $(".hqyzm").text("重新获取("+ count+")" );
            $(".hqyzm").css({"bakground-color":"#ff8400"});
            if (count == 0) {
                $(".hqyzm").text("获取验证码" );
                $("#btn").val("Submit").removeAttr("disabled");
                clearInterval(countdown);
            }
            count--;
        }
    });
    $(".onepwd").focus(function(){
        $(".close").show();
    });
    $(".close").click(function(){
        $(".onepwd").val("");
    });
    $(".onepwd").blur(function(){
        setTimeout("$('.close').hide();",150)
    });
    check();
});

function check(){
	$(document).keyup(function(event){ 
		var telphoneNum = $('.onepwd').val();
		if(telphoneNum.length == 11)
		{
			if(!(/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/.test(telphoneNum)))
			{
				$(".onepwdbox").find(".testPhone").show();
				if($(".testPhone").text() == '手机号码错误,请重新输入!')
				{
					 $(".getCode").css('background', 'url(../images/work_yzmCX.png)');
					 $(".getCode").text("获取验证码");
				}
				$("#write").focus(function(){
		       		$(".getCode").css('background', 'url(../images/work_yzmCX2.png)');
		       	    $(".getCode").removeClass("disabled");
		       		$("#write").empty();
		       		$(".testPhone").hide();
	      		})
			}
			else
			{
				$(".hqyzm").css({"background":"#fd8432"});
				$(".hqyzm").click(function(){
					sendVerifyCodeMessage(telphoneNum);
				});
			}
		}
	}); 
}

function sendVerifyCodeMessage(telphoneNum){
    var url = apiurl + 'api/app/sendVerifyCodeMessage';
    console.log(url);
    var jsonData={"mobileNum":telphoneNum};
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
function registLoginCodeVerify(telphoneNum){
	var verifyCode = $('.twopwd').val();
	console.log("verifyCode:" + verifyCode);
	var url = apiurl + 'api/app/registLoginCodeVerify';
    console.log(url);
    var jsonData={"mobileNum":telphoneNum,"verifyCode":verifyCode};
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
        	localStorage.setItem("telphoneNum",telphoneNum);
        	sessionStorage.setItem("customerId",customerId);
        	window.location.href = '../view/login.html';
        },
        error:function(){
    		console.log("registLoginCodeVerify is wrong");
    	}
    });
}


