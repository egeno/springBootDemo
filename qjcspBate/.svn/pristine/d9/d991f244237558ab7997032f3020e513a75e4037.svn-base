$(function(){
	//加载要评价的菜品
	//checkOpenId();
	getSayEvaluationGoodsWX();
	//评价星级
	$('.start img').click(function(){
		$(this).parents(".goods_main").addClass("starTrue");
		$(this).parent().find('img').attr("src","../img/img/star1.png").removeClass("good");;
		var index=$(this).index();
		for(var i=0;i<=index;i++){
			$(this).parent().find('img').eq(i).attr("src","../img/img/star2.png").addClass("good");
		}
		$(".publishBtn").css("background","rgba(255,132,32,1)");
		console.log("我点击了");
	});
	//评价内容文本框滑出
	$('.goods_title').click(function(){
		$(this).parent().find('.textbox_main').slideDown();
	});
	$('.textbox').click(function(){
		$(this).text("");
	});
	//发表评价
	$('.publishBtn').click(function(){
		publish();
	})
})


function getSayEvaluationGoodsWX(){
	var url= apiurl +"api/wechat/getSayEvaluationGoodsWX";
	var orderNum = localStorage.getItem("orderNum");
	//var orderNum="10160727132724194013";
	var jsonData={"orderNum":orderNum};
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(jsonData),
		dataType:'json',
		success:function(data){
			console.log("获取待评价列表:"+JSON.stringify(data.returnContent));
			var list=data.returnContent;
			for(var i=0;i<list.orderChilds[0].orderDetails.length;i++){
				$('.goodsEvaluate_main_box').append("<li class='goods_main' lang="+list.orderChilds[0].orderDetails[i].orderDetailId+"><div class='goods_title' lang="+list.orderChilds[0].orderDetails[i].goodsId+"><p class='goodsName'>"+list.orderChilds[0].orderDetails[i].goodsName+"</p><p class='start'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'></p></div><div class='textbox_main'><textarea onkeyup='textbox(this);' class='textbox'>请写下您的感受吧，对他人很有帮助的哦！</textarea><p class='textbox_len'>还能输入<span class='len'>100</span>个字</p><p class='over_len'>超过字数限制</p></div></li>");
			}
			$('.goodsEvaluate_main_box').attr("lang",list.orderChilds[0].orderChildId);
			$('.goodsEvaluate_main_box').attr("machineId",list.machineId);
			$('.goodsEvaluate_main_box').attr("orderId",list.orderId);
			$('.over_len').hide();
			$('.textbox_main').hide();
		},error:function(data){
			console.log("获取列表失败:"+JSON.stringify(data));
		}
	});
}
//字数限制
function textbox(obj){
   var curLength=$(obj).val().length;
   if(curLength>100)
   {
        var num=$(obj).val().substr(0,100);
        $(obj).val(num);
        $(obj).parent().find('.textbox_len').hide();
        $(obj).parent().find(".over_len").show();
   }
   else
   {
   		$(obj).parent().find('.textbox_len').show();
   		$(obj).parent().find(".over_len").hide();
        $(obj).parent().find(".textbox_len").find('.len').text(100-$(obj).val().length);
   }
}

function publish(){
	var startTrue=true;
	for(var i=0;i<$('.goods_main').length;i++){
		if(!$('.goods_main').eq(i).hasClass("starTrue")){
			startTrue=false;
		}
	}
	if(startTrue){
		var url = apiurl + 'api/wechat/commitEvaluationGoodsWX';
		var machineId = $('.goodsEvaluate_main_box').attr("machineId");
		var orderNum = localStorage.getItem("orderNum");
		var orderChilds=[];//子订单详情
		var orderDetails=[];//订单明细
		var orderChildId=$(".goodsEvaluate_main_box").attr("lang");
		var orderDetailId;//订单明细id
		var goodsId;//商品id
		var goodsEvaluateScore;//菜品评价分数
		var goodsEvaluateContent;//菜品评价内容
		for(var i=0;i<$('.goods_main').length;i++){
			orderDetailId=$('.goods_main').eq(i).attr("lang");
			goodsId=$('.goods_title').eq(i).attr("lang");
			goodsEvaluateScore=$('.goods_main').eq(i).find('.good').length;
			goodsEvaluateContent=$('.goods_main').eq(i).find('.textbox').val();
			if(goodsEvaluateContent=="请写下您的感受吧，对他人很有帮助的哦！"){
				goodsEvaluateContent="";
			}
			orderDetails.push({"orderDetailId":orderDetailId,"goodsId":goodsId,"goodsEvaluateScore":goodsEvaluateScore,"goodsEvaluateContent":goodsEvaluateContent});
		}
		orderChilds.push({"orderChildId":orderChildId,"orderDetails":orderDetails});
		var jsonData = {"machineId":machineId,"orderNum":orderNum,"orderChilds":orderChilds};
		console.log("给后台的数据："+jsonData);
		$.ajax({
			type:"post",
			url:url,
			data:$.toJSON(jsonData),
			dataType:'json',
			success:function(data){
				if(data.returnCode==0){
					console.log("提交发表失败，请不要发表不适当内容 的评价:"+JSON.stringify(data.returnContent));
					var str=JSON.stringify(data.returnContent);
					alert(str);
				}
				if(data.returnCode==1){
					console.log("提交成功返回值:"+JSON.stringify(data.returnContent));
					$('.success').show();
					$(".publishBtn").addClass("disabled");
					$(".publishBtn").css("background","#fec497");
					setTimeout(function(){
						window.location.href="orderinfo.html";
					},3000)
				}
			},error:function(data){
				console.log("失败了:"+JSON.stringify(data));
			}
		});
	}else{
		alert("还有未评价的菜品哦");
	}
}