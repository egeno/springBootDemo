var placeOrderTime=localStorage.getItem("placeOrderTime");//下单时间
$(document).ready(function(){
	checkCustomerId();
	var customerId= sessionStorage.getItem('customerId');
	//var customerId="2003";
	if(customerId!=null && customerId.length>0){
		$('.choice').click(function(){
			$('.choice').find('span').removeClass('checked');
			$(this).find('span').addClass('checked');
		});
		var totalAmount=localStorage.getItem("totalAmount");
		var payMoney = totalAmount;
		$(".payMoney em").text(payMoney);   //支付金额赋值
		$(".money2").text(payMoney); 
		statusTimer = setInterval(function () {getOrderStatus()}, 1000);
		
		 $('.payBtn').click(function(){
			$(this).text("正在支付").addClass("disabled").css("background","rgba(255,132,32,0.5)");
			var url=apiurl +"weixinwap/getPrepayId";
			var openid=sessionStorage.getItem('openid');
			var orderNum =localStorage.getItem('orderNum'); 
			var jsondata={"openid":openid,"orderNum":orderNum};
			//预支订单号
			$.ajax({
				type:"post",
				url:url,
				data:$.toJSON(jsondata),
		        dataType:'json',
		        async: false,
				success:function(data){
					
					localStorage.setItem('prepay',JSON.stringify(data.returnContent));
					wxpay();
				},error:function(){
					$(".payBtn").text("确认支付").removeClass("disabled").css("background","#ff8400");
				}
			});
	      });
	}
});

//2.1.3.获取订单状态
function getOrderStatus(){
	var url = apiurl + 'api/wechat/getOrderStatusWX';
	var orderNum = localStorage.getItem("orderNum");
	var jsonData = {"orderNum":orderNum};
	var paySystemTime=0;
	var SystemTime;//系统时间
	console.log("给后台的数据："+JSON.stringify(jsonData));
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(jsonData),
		dataType:'json',
		success:function(data)
		{
			console.log(url);
			console.log("订单状态："+JSON.stringify(data.returnContent));
			var returnCode = data.returnCode;
			var returnContent = data.returnContent;
			$("input").click(function(){
				var paymode;
				for(var i=0;i<$("input").length;i++)
				{
					paymode = $(this).attr("name");
				}
			})
			//获取系统时间
			if(paySystemTime==0){
				SystemTime=returnContent.systemTime;
				sessionStorage.setItem('SystemTime',SystemTime);
				paySystemTime++;
			}
			
			if(returnCode == 1)
			{
				if(returnContent.state == 0)
				{
					console.log("待付款");
					clearInterval(statusTimer);
				}else if(returnContent.state == 1)
				{
					console.log("已支付(待取货)");
					clearInterval(statusTimer);
				}else if(returnContent.state == 2)
				{
					console.log("已取消");
					clearInterval(statusTimer);
				}else if(returnContent.state == 3)
				{
					console.log("已取货(待评论)");
					clearInterval(statusTimer);
				}else if(returnContent.state == 4)
				{
					console.log("取货超时");
					clearInterval(statusTimer);
				}else if(returnContent.state == 5)
				{
					console.log("待退款");
					clearInterval(statusTimer);
				}else if(returnContent.state == 6)
				{
					console.log("交易关闭");
					clearInterval(statusTimer);
				}else if(returnContent.state == 7)
				{
					console.log("完成");
					clearInterval(statusTimer);
				}else if(returnContent.state == 8)
				{
					console.log("退款失败");
					clearInterval(statusTimer);
				}else if(returnContent.state == 9)
				{
					console.log("退款成功");
					clearInterval(statusTimer);
				}else if(returnContent.state == 10)
				{
					console.log("设备故障");
					clearInterval(statusTimer);
				}else if(returnContent.state == 11)
				{
					console.log("已预订");
					clearInterval(statusTimer);
				}
			}
			else
			{
				console.log("下单未成功");
			}
		},
		error:function(data)
		{
			console.log('失败原因:'+ JSON.stringify(data.returnContent));
		}
	});
		//------------------------------------倒计时--------------------------------------------------------
		var PastTime=new Date(sessionStorage.getItem("SystemTime"))-new Date(placeOrderTime);//计算时间差
		console.log(new Date(sessionStorage.getItem("SystemTime")));
		console.log(new Date(placeOrderTime));
	    var PastMinute = Math.floor(PastTime/1000/60);
        var PastSecond =Math.floor((PastTime/1000)%60);
		var f=5-PastMinute;   //分钟
		var s=60-PastSecond;   //秒
		function run(){
			console.log("定时器");
			if(s == 0){
				if(f > 0){
					f=f *1 -1;
					s=60;
					s=s *1 -1;
				}else{
					window.clearInterval(intervalId);
					//$(".payBtn").text("确认支付").removeClass("disabled").css("background","#ff8400");
					window.location.href="index.html?love";
					return false;
				}
			}else{
				s=s *1 -1;
			}
			$(".payTime").text(f+"分"+s+"秒");
			console.log(f+"分"+s+"秒");
		}
		if(s!="0"||f!="0"){
			var intervalId=setInterval(run,1000);		
		}
}