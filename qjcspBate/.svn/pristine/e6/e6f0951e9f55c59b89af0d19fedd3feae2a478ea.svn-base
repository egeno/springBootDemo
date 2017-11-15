$(document).ready(function(){
	checkOpenId();
	var province = localStorage.getItem("province");
	$(".btn").click(function(){
		//window.location.href="Selectcity.html";
		var mobileNum = $('.phone').val();
		console.log(mobileNum);
		if(!(/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/.test(mobileNum)))
		{
			$(".testPhone").show();
		}
		else
		{
			$(".testPhone").hide();
			//意见反馈
			var url = apiurl + 'api/wechat/commitFeedBackWX';
		    console.log("URL"+url);
		    var feedBackContent =$(".textbox").val();
		    var jsonData = {"mobileNum":mobileNum,"feedBackContent":feedBackContent};
			 $.ajax({  
			        type:'post',      
			        url:url,  
			        data:$.toJSON(jsonData),
			        dataType:'json', 
			        async:false, 
			        success:function(data)
			        {
			        	var data=data.returnContent;
			        	console.log("接口成功反馈:"+data);
			        	window.location.href="login.html";
			        },
			        error:function(data){
			        	var data=data.returnContent;
			        	console.log("接口失败反馈:"+data);
			        }
			 })
		}
	})
})