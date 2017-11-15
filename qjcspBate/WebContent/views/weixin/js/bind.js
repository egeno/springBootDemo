checkOpenId();
//倒计时
var count=60;
function time(){
    var countdown = setInterval(CountDown, 1000);
    function CountDown() {
        $(".codeBtn").text("重新获取("+ count+")" );
        if (count == 0) {
            $(".codeBtn").text("获取验证码" );
            clearInterval(countdown);
            count = 60;
            $('.codeBtn').removeClass("disabled");
            $(".codeBtn").css("background","#ff9919");

        }
        count--;
    }
}
$(function(){
	$('.codeBtn').click(function(){
		$(this).css("background","gainsboro");
		$(this).addClass('disabled');
		$('.testPhone').text("");
		$('.testCode').text("");
		
		//手机号验证
		var mobileNum=$('.mobileNum').val();
		if(mobileNum.length == 0){
			$('.testPhone').text("手机号码不能为空！");
			$('.codeBtn').removeClass("disabled");
			$(".codeBtn").css("background","#ff9919");
		}
		else if(mobileNum.length == 11)
		{
			if(!(/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/.test(mobileNum))){
				$('.testPhone').text("手机号码错误,请重新输入!");
				$('.codeBtn').removeClass("disabled");
				$(".codeBtn").css("background","#ff9919");
			}else{
				//发送短信验证
				var code=localStorage.getItem("code");
				var url=apiurl +"api/wechat/sendVerifyCodeMessageWX?code="+code;
				var openid=sessionStorage.getItem('openid');  
				var jsonData = {"mobileNum":mobileNum,"openId":openid};

				$.ajax({
					type: 'post',
					url: url,
					data: $.toJSON(jsonData),
					dataType: 'json',
					async:false, 
					success: function(data) {
						//验证码倒计时
						time();
						console.log("验证码："+JSON.stringify(data.returnContent));
						
						
					},error:function(){
						console.log();
					}
				});
			}
		}else{
			$('.testPhone').text("手机号码错误,请重新输入!");
			$('.codeBtn').removeClass("disabled");
			$(".codeBtn").css("background","#ff9919");
		}
	});

	//确定事件
	$('.sure').click(function(){
		count=0;
		$(".codeBtn").text("获取验证码" );
		//手机验证码验证
		var mobileNum=$('.mobileNum').val();
		var verifyCode=$('.code').val();
		var openid=sessionStorage.getItem('openid');
		var url=apiurl +"api/wechat/ registLoginCodeVerifyWX";
		var jsonData = {"verifyCode":verifyCode,"mobileNum":mobileNum,"openId":openid};
			$.ajax({
				type: 'post',
				url: url,
				data: $.toJSON(jsonData),
				dataType: 'json',
				async:false, 
				success: function(data) {
					
					if(data.returnCode=="1"){
						sessionStorage.setItem("customerId",data.returnContent.customerId);
						localStorage.setItem("mobileNum",mobileNum);
						console.log("customerId:"+data.returnContent.customerId);
						window.location.href = "index.html";
					}
					else{
						$('.testCode').text(data.returnContent);
					}
				},error:function(){
					console.log("验证失败:"+data.returnContent);
				}
			});
	});
})
