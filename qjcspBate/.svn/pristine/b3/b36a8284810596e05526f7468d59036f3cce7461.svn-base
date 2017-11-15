var newGoodsList=JSON.parse(localStorage.getItem("newGoodsList"));
var childOrders=JSON.parse(localStorage.getItem("carobj"));
console.log("提交订单页获取到的newGoodsList"+JSON.stringify(newGoodsList));//零售购物车数组
console.log("提交订单页获取到的childOrders"+JSON.stringify(childOrders));//预定购物车数组
$(document).ready(function(){
	checkCustomerId();
	var customerId= sessionStorage.getItem('customerId');
	//var customerId="2003";
	if(customerId!=null && customerId.length>0){
		var openid= sessionStorage.getItem('openid')
		//var openid="oGxBNvxV2gXY1QJGHgJoDv2M_Fts";
		$(this).removeClass("disabled");
		reg();
		//生成数组
		var data = localStorage.getItem("data");
		console.log("data:"+data);
		var returnContent = JSON.parse(data);
		var allCount = returnContent.allCount;
		$('.shouldpay em').text(allCount);    //待支付赋值
		$('.orderMoney i').text(allCount);    //待支付赋值
		var payMoney = $('.orderMoney i').text();
		localStorage.setItem("payMoney",payMoney);
		var orderType=localStorage.getItem("orderType");
		if(orderType=="1"){
			$('.orderList p').remove();
			var date = localStorage.getItem("date");
			var takeDate = localStorage.getItem("takeDate");
			$('.orderList h1').append("<span>"+ date +"</span><span>("+ takeDate +")</span>");
			var goodsList = returnContent.goodsList;  //下单的商品列表
			console.log(goodsList);
			for(var i=0;i<goodsList.length;i++)
			{
				$('.orderList').append("<p lang="+ goodsList[i].goodsId +"><span class='goodsName'>"+ goodsList[i].goodsName +"</span><span class='goodsCount'>x"+  goodsList[i].goodsCount+"</span><span class='goodsPrice'><em>"+ goodsList[i].goodsPrice+"</em></span></p>")
			}
			
		}
		else{
			$('.orderList').empty();
			console.log("预订进到订单提交页生成订单列表："+JSON.stringify(childOrders));
			for(var i=0;i<childOrders.length;i++){
				$(".orderList").append("<ul lang="+childOrders[i].areaModelId+" date_areaIndex="+childOrders[i].areaIndex+"><div class='reserve'><em class='date' date_index="+childOrders[i].dateIndex+">"+childOrders[i].date+"</em><em class='time'>"+childOrders[i].time+"</em></div></ul>");
			}
			for(var i=0;i<childOrders.length;i++){
				for(var j=0;j<childOrders[i].orderDetails.length;j++){
					if($(".orderList").find("ul").eq(i).attr("lang")==childOrders[i].areaModelId){
						$(".orderList").find("ul").eq(i).append("<li lang="+childOrders[i].orderDetails[j].goodsId+"><span class='dishName'>"+childOrders[i].orderDetails[j].cargoodsName+"</span><span class='dishNum'>x"+childOrders[i].orderDetails[j].goodsNum+"</span><span class='dishPrice'>&#165;<em>"+childOrders[i].orderDetails[j].retailPrice+"</em></span></li>");
					}
				}
			}
			
		}
		//提交订单
		$('.orderSubmit').click(function(){
			$(this).addClass("disabled").css({"background":"gray"});
			var orderType=localStorage.getItem("orderType");
			var orderItemList = [];
			//var childOrders=[];
			$('.orderList p').each(function(){
				var goodsId = $(this).attr("lang");
				var retailPrice = $(this).find(".goodsPrice em").text().substring(1);
				var goodsNum = $(this).find(".goodsCount").text().substring(1);
				var arr = {"goodsId":goodsId,"goodsNum":goodsNum,"retailPrice":retailPrice};
				orderItemList.push(arr); 
			});
			var machineId = localStorage.getItem("machineId");//零售设备ID
			var selectMachineId=localStorage.getItem("selectMachineId");//预定设备ID
			var totalAmount = $('.orderMoney i').text();
			var telphoneNum = localStorage.getItem('telphoneNum');
			//var telphoneNum="15867194262";
			var takenStartTime = localStorage.getItem('takenStartingTime');
			var takenEndTime = localStorage.getItem('takenEndingTime');
			var customerId = sessionStorage.getItem("customerId");
			//var customerId = "2001";
			//var openId="oz6iDv2vWulDL5S7tWhGmD5C8EW8";	
			var openid=sessionStorage.getItem('openid');
			var jsonData = {"machineId":machineId,"totalAmount":totalAmount,"discountMoney":"","orderItemList":orderItemList,"mobileNum":telphoneNum,"customerId":customerId,"orderType":orderType,"commitMode":"3","takenStartTime":takenStartTime,"takenEndTime":takenEndTime,"luckyMoneyId":"","luckyMoney":"","openId":openid};
			var pjsonData={"machineId":selectMachineId,"totalAmount":totalAmount,"childOrders":childOrders,"discountMoney":"","orderItemList":orderItemList,"mobileNum":telphoneNum,"customerId":customerId,"orderType":orderType,"commitMode":"3","luckyMoneyId":"","luckyMoney":"","openId":openid,"customerId":customerId};
			if(orderType=="0"){
				preissueCommitOrder(pjsonData);
			}else{
				commitOrder(jsonData);
			}
		});
		//返回事件
		$(".backpave").click(function(){
			var orderType=localStorage.getItem("orderType");
			if(orderType=="1"){
				window.location.href = '../view/order.html?live';
			}else{
				window.location.href = '../view/Nooutput.html?live';
			}
		});
	}
});



//手机加密
function reg(){
	var telphoneNum = localStorage.getItem('telphoneNum');  //telphoneNum:手机号码
	//var telphoneNum="15867194262";
	console.log("手机号:"+telphoneNum);
	//var reg = /(\d{3})\d{4}(\d{4})/;
	$('.phone p').text(telphoneNum);
	var takePlace = localStorage.getItem("takePlace");      //takePlace:取餐地址
	$('.takePlace p').text(takePlace);
}


//预订订单提交
function preissueCommitOrder(pjsonData){
	console.log("零售提交给后台的数据："+JSON.stringify(pjsonData));
	var url = apiurl + 'api/wechat/preissueCommitOrderWX';
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(pjsonData),
		dataType:'json',
		success:function(data)
		{
			console.log(JSON.stringify(data));
			console.log("后台返回数据："+JSON.stringify(data.returnContent));
			if(data.returnCode=="2"){
				alert("订单商品份数不足，下单失败!");
			}
			else if(data.returnCode=="0"){
				alert(data.returnContent);
			}else if(data.returnContent=="未付款订单不能超过三个"){
				alert("未付款订单不能超过三个");
			}
			else if(data.returnCode == "4" || data.returnCode == "'4'"){
				//如果价格有变化
				$(".priceChange").parent('.dialog').fadeIn(500);
				setTimeout(function () {
					loadNewPrice();
			    },60000);
				$(".priceChange_know").click(function(){
					loadNewPrice();
				});
			}
			else if(data.returnCode=="1"){
				var orderNum = data.returnContent.orderNum;
				var placeOrderTime=data.returnContent.placeOrderTime//下单时间
				localStorage.setItem("orderNum",orderNum);
				localStorage.setItem("placeOrderTime",placeOrderTime);
				var totalAmount = $('.orderMoney i').text();
				localStorage.setItem("totalAmount", totalAmount);
				window.location.href = "pay.html";
			}
		},
		error:function(data)
		{
			console.log('失败原因:'+ JSON.stringify(data.returnContent));
		}
	})
}  

//零售订单提交
function commitOrder(jsonData)
{
	console.log("零售提交给后台的数据："+JSON.stringify(jsonData));
	var url = apiurl + 'api/wechat/commitOrderWX';
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(jsonData),
		dataType:'json',
		success:function(data)
		{
			console.log(JSON.stringify(data));
			console.log("后台返回数据："+JSON.stringify(data.returnContent));
			if(data.returnCode=="2"){
				alert("订单商品份数不足，下单失败!");
				$('.orderSubmit').removeClass("disabled");
			}
			else if(data.returnCode=="0"){
				alert(data.returnContent);
				$('.orderSubmit').removeClass("disabled");
			}else if(data.returnCode=="5"){
				alert(data.returnContent);
				$('.orderSubmit').removeClass("disabled");
			}
			else if(data.returnContent=="未付款订单不能超过三个"){
				alert("未付款订单不能超过三个");
				$('.orderSubmit').removeClass("disabled");
			}
			else if(data.returnCode=="1"){
				var orderNum = data.returnContent.orderNum;
				localStorage.setItem("orderNum",orderNum);
				var placeOrderTime=data.returnContent.placeOrderTime//下单时间
				localStorage.setItem("placeOrderTime",placeOrderTime);
				var returnCode = data.returnCode;
				console.log(returnCode);
				
				if(returnCode == "1" || returnCode == "'1'")
				{
					var totalAmount = $('.orderMoney i').text();
					localStorage.setItem("totalAmount", totalAmount);
					window.location.href = "pay.html";
				}
				//如果价格有变化
				if(returnCode == "4" || returnCode == "'4'"){
					$(".priceChange").parent('.dialog').fadeIn(500);
					setTimeout(function () {
						loadNewPrice();
				    },60000);
					$(".priceChange_know").click(function(){
						loadNewPrice();
					});
				}
			}
		},
		error:function(data)
		{
			console.log('失败原因:'+ JSON.stringify(data.returnContent));
		}
	})
}

function loadNewPrice(){
	$(".priceChange").parent('.dialog').fadeOut(500);
	location.reload();
}