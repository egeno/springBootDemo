var objgoods;
var newGoodsList=[];
$(document).ready(function(){
	//checkOpenId();
	getCompanyDetailByMachineId();
	$('.orderList').empty();
	getSalingGoodsList();
	var hei = $(window).height() - $('.top').height();
	$('.main_visual').height(hei+5);
	var mainHei = $('.main_visual').height();
	var bottom = $('.botton').height()+15;
	$('.noscroll').height(mainHei - bottom);
	//头部赋值
	var takePlace = localStorage.getItem("takePlace");
	$('.top_right h3 span').text(takePlace);
	var distance =localStorage.getItem("distance"); //距离
	if(distance>=1000){
		$('.index_info').find(".distance em").eq(0).text((distance/1000).toFixed(2));
		$('.index_info').find(".distance em").eq(1).text("km");
	}else if(distance<1000){
		$('.index_info').find(".distance em").eq(0).text(distance);
		$('.index_info').find(".distance em").eq(1).text("m");
	}
	
	var surplus = localStorage.getItem("surplus");  //剩余份数
	$(".top").find(".surplus em").text(surplus);
	var grade = localStorage.getItem("grade");   //星际评分
	$('.index_info').find(".stars").html(grade);
	
	
	//商家电话
	$('.businessPhone').on('click',function(){
		$('.order_tel').parent().show();
	});
	
	$('.cancel').on('click',function(){
		$('.order_tel').parent().hide();
	});
	
	//购物车
	$(".enterCar").click(function(){
		if($('.car').parents('.dialog').hasClass("look"))
		{
			$('.car').parents('.dialog').removeClass("look");
		}
		else
		{
			if($('.allCount').text() > 0)
			{
				$('.car').parents('.dialog').addClass("look");
			}
		}
	});
	
	//清空购物车
	$('.delete').click(function(){
		//重组数组
		newGoodsList=[];
		localStorage.setItem("newGoodsList",JSON.stringify(newGoodsList));
		$(".balance").hide();
		$(".go").show();
		window.location.reload();
	});
	
	//点击'结算'
	$('.balance').click(function(){
		checkCustomerId();
		var customerId= sessionStorage.getItem('customerId');
   	   //传参
		if(customerId!=null && customerId.length>0){
			var allCount = $(".enterCar b").text();  //allCount:总价格
			var goodsList = [];
			$('.scroll li').each(function(){
				var goodsName = $(this).find('.dishName').text();  //goodsName:菜品名称
				var goodsPrice = $(this).find(".dishPrice").text();  //goodsPrice:菜品单价
				var goodsCount = $(this).find(".buyCount").text();   //goodsCount:下单份数
				var goodsId = $(this).attr("lang");
				var arrays = {"goodsName":goodsName,"goodsPrice":goodsPrice,"goodsCount":goodsCount,"goodsId":goodsId};
				goodsList.push(arrays);  //goodsList:商品列表
				var returnContent = {"allCount":allCount,"goodsList":goodsList}
				var data = JSON.stringify(returnContent);
				localStorage.setItem("data",data);
				console.log(data);
			})
			newGoodsList=[];
			localStorage.setItem("bugCarHtml","");
			for(var i=0;i<$(".scroll").find("li").length;i++){
				console.log(i);
				var scrollGoodsId=$(".scroll").find("li").eq(i).attr("lang");
				var scrollGoodsSurplus=$(".scroll").find("li").eq(i).attr("data-surplus");
				var scrollElemNum=$(".scroll").find("li").eq(i).find(".buyCount").text();
				var scrollGoodsPrice=$(".scroll").find("li").eq(i).find(".dishPrice").find("em").text();
				var scrollGoodsName=$(".scroll").find("li").eq(i).find(".dishName").text();
				objgoods={"goodsId":scrollGoodsId,"elem_num":scrollElemNum,"goodsPrice":scrollGoodsPrice,"goodsName":scrollGoodsName,"surplus":scrollGoodsSurplus};
				newGoodsList.push(objgoods);
			}
			localStorage.setItem("newGoodsList",JSON.stringify(newGoodsList));
			window.location.href = "../view/orderRetail.html";
		}
	});
	
	//购物车点击添加'+'图标加
	$('.scroll').on('click','li .add',function(){
		var _this = $(this);
		var elem_num = parseInt($(this).parent().find('.buyCount').text()) + 1;
		add_min_car(_this,elem_num);
		showOrhide()
		return false;
	})
	
	//购物车点击'-'的图标减
	$('.scroll').on('click','li .min',function(){
		var _this = $(this);
		var elem_num = parseInt($(this).parent().find('.buyCount').text()) - 1;
		if(elem_num>=0){
			add_min_car(_this,elem_num);
		}
		showOrhide()
	})
	
	//订单列表点击'+'的图标添加
	$('.orderList .add').click(function(e){
		var _this = $(this);
		var elem_num = parseInt($(this).siblings(".buyCount").text()) + 1;
		add_min_order(_this,elem_num);
		showOrhide();
		e.stopPropagation();//阻止冒泡
	})
	
	//订单列点击'-'的图标减
	$('.orderList .min').click(function(e){
		var _this = $(this);
		var elem_num = parseInt($(this).siblings(".buyCount").text()) - 1;
		if(elem_num>=0){
			add_min_order(_this,elem_num);
		}
		showOrhide();
		e.stopPropagation();//阻止冒泡
	})
	if($(".orderList").text()==""){
		$(".takeTime").hide();
	}
});


//获取指定设备的可售菜品信息
function getSalingGoodsList()
{
	var machineId = localStorage.getItem("machineId");
	var url = apiurl + 'api/wechat/getSalingGoodsListWX';
	var jsonData = {"machineId":machineId};
	console.log("给后台的数据："+JSON.stringify(jsonData));
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(jsonData),
		dataType:'json',
		success:function(data)
		{
			console.log("后台返回数据："+JSON.stringify(data.returnContent));
			var returnContent = data.returnContent;
			var takenEndingTime = returnContent.takenEndTime;
			localStorage.setItem("takenEndingTime",takenEndingTime);
			var takenStartingTime = returnContent.takenStartTime;
			if(takenStartingTime==""||takenStartingTime==undefined||takenStartingTime=="undefined"){
				/*$(".noTimeDialog").fadeIn(500);
				setTimeout(function () {
					$(".noTimeDialog").fadeOut(500);
			    },1000);*/
				$(".noFoods").show();
			}else{
				$(".noFoods").hide();
				if(data.returnContent.goodsList.length<=0){
					$(".noFoods").show();
				}
				localStorage.setItem("takenStartingTime",takenStartingTime);
				var endMonth = returnContent.takenEndTime.substring(5,7);  //月
				var endDay = returnContent.takenEndTime.substring(8,10);   //日
				var endTime = returnContent.takenEndTime.substring(11,16);  //时间点
				var takenEndTime = endMonth+ "月" + endDay+ "日" + " " +endTime;  //结束时间
				var startMonth = returnContent.takenStartTime.substring(5,7);  //月
				var startDay = returnContent.takenStartTime.substring(8,10);   //日
				var startTime = returnContent.takenStartTime.substring(11,16);  //时间点
				var takenStartTime = startMonth+ "月" + startDay+ "日" + " " +startTime;  //开始时间
				var takeTime ="取餐时间：" + takenStartTime + " - " +takenEndTime
				$(".takeTime span").text(takeTime)    //取餐时间
				
				var date = endMonth + "-" + endDay;
				var takeDate = "取餐时间："+startTime + "-" + endTime;
				localStorage.setItem("date",date);
				localStorage.setItem("startTime",startTime);
				localStorage.setItem("endTime",endTime);
				localStorage.setItem("takeDate",takeDate);
				console.log(date+takeDate);
				var goodsList = returnContent.goodsList;
				for(var i=0;i<goodsList.length;i++)
				{
					
					if(goodsList[i].goodsImage.length>0){
						var goodsImages=apiurl+goodsList[i].goodsImage;
					}else{
						var goodsImages="../img/image/no_meal_evaluate.png";
					}
//					$('.orderList').append("<dl lang=" + goodsList[i].goodsId + "><dt class='goodsImages'><img src="+goodsImages +"/></dt><dd class='goodsInfo'><h3 class='goodsName'>"+ goodsList[i].goodsName +"</h3><p><span class='goodsStars'></span><span class='goodsCount'>剩余:<em data-surplus=" + goodsList[i].goodsCount +">" + goodsList[i].goodsCount + "</em>份</span></p><span class='goodsPrice'>&#165;<em>"+ goodsList[i].goodsPrice +"</em></span></dd><div class='goodsBuy'><span class='min'>-</span><span class='buyCount'>0</span><span class='add'>+</span></div></dl>")
					$('.orderList').append('<dl lang="' + goodsList[i].goodsId + '"><dt class="goodsImages"><img src="'+goodsImages +'" /></dt> <dd class="goodsInfo"> <h3 class="goodsName">'+ goodsList[i].shortGoodsName +'</h3> <p class="xinn"> <span class="goodsStars"><img src="../img/img/star1.png"><img src="../img/img/star1.png"><img src="../img/img/star1.png"><img src="../img/img/star1.png"><img src="../img/img/star1.png"></span> </p> <p class="xin"> <span class="storeName">'+goodsList[i].goodsName+'</span> </p> <p class="xin"> <span class="goodsPrice">&#165;<em>'+ goodsList[i].goodsPrice +'</em></span> <span class="goodsCount">剩余:<em data-surplus="'+ goodsList[i].goodsCount +'">' + goodsList[i].goodsCount + '</em>份</span> </p> </dd> <div class="goodsBuy"> <span class="min">-</span> <span class="buyCount">0</span> <span class="add">+</span> </div> </dl>');
					var starstar="";
					for(var j=0;j<goodsList[i].goodsGrade;j++){
						$(".goodsStars").eq(i).find("img").eq(j).attr("src","../img/img/star2.png");
					}
					if(goodsList[i].goodsGrade==0||goodsList[i].goodsGrade==0.0){
						$(".goodsStars").eq(i).find("img").attr("src","../img/img/star2.png");
					}
				}
				//查看菜品详情
				$(".orderList").find("dl").click(function(){
					var goodsIcon=$(this).find(".goodsImages").find("img").attr("src");
					localStorage.setItem("goodsIcon",goodsIcon);
					var goodsId=$(this).attr("lang");
					localStorage.setItem("goodsId",goodsId);
					var orderType=1;
					localStorage.setItem("orderType",orderType);
					if($(this).find('.buyCount').text()==""){
						localStorage.setItem("elem_num","");//对应下单数
					}else{
						localStorage.setItem("elem_num",$(this).find('.buyCount').text());//对应下单数
					}
					newGoodsList=[];
					localStorage.setItem("bugCarHtml","");
					for(var i=0;i<$(".scroll").find("li").length;i++){
						console.log(i);
						var scrollGoodsId=$(".scroll").find("li").eq(i).attr("lang");
						var scrollGoodsSurplus=$(".scroll").find("li").eq(i).attr("data-surplus");
						var scrollElemNum=$(".scroll").find("li").eq(i).find(".buyCount").text();
						var scrollGoodsPrice=$(".scroll").find("li").eq(i).find(".dishPrice").find("em").text();
						var scrollGoodsName=$(".scroll").find("li").eq(i).find(".dishName").text();
						objgoods={"goodsId":scrollGoodsId,"elem_num":scrollElemNum,"goodsPrice":scrollGoodsPrice,"goodsName":scrollGoodsName,"surplus":scrollGoodsSurplus};
						newGoodsList.push(objgoods);
					}
					localStorage.setItem("newGoodsList",JSON.stringify(newGoodsList));
					window.location.href="goodsEvaluateIndex.html";
				});
				/*$("dt,.goodsName").click(function(){
					var goodsIcon=$(this).parents("dl").find(".goodsImages").find("img").attr("src");
					localStorage.setItem("goodsIcon",goodsIcon);
					var goodsId=$(this).parents("dl").attr("lang");
					localStorage.setItem("goodsId",goodsId);
					var orderType=1;
					localStorage.setItem("orderType",orderType);
					if($(this).parents('dl').find('.buyCount').text()==""){
						localStorage.setItem("elem_num","");//对应下单数
					}else{
						localStorage.setItem("elem_num",$(this).parents('dl').find('.buyCount').text());//对应下单数
					}
					newGoodsList=[];
					localStorage.setItem("bugCarHtml","");
					for(var i=0;i<$(".scroll").find("li").length;i++){
						console.log(i);
						var scrollGoodsId=$(".scroll").find("li").eq(i).attr("lang");
						var scrollGoodsSurplus=$(".scroll").find("li").eq(i).attr("data-surplus");
						var scrollElemNum=$(".scroll").find("li").eq(i).find(".buyCount").text();
						var scrollGoodsPrice=$(".scroll").find("li").eq(i).find(".dishPrice").find("em").text();
						var scrollGoodsName=$(".scroll").find("li").eq(i).find(".dishName").text();
						objgoods={"goodsId":scrollGoodsId,"elem_num":scrollElemNum,"goodsPrice":scrollGoodsPrice,"goodsName":scrollGoodsName,"surplus":scrollGoodsSurplus};
						newGoodsList.push(objgoods);
					}
					localStorage.setItem("newGoodsList",JSON.stringify(newGoodsList));
					window.location.href="goodsEvaluateIndex.html";
				});*/
				
				//页面之间的购物车联动
				if(localStorage.getItem("newGoodsList")!=null){
					newGoodsList=eval(localStorage.getItem("newGoodsList"));
					console.log("order页："+JSON.stringify(newGoodsList));
					var allgoodsPrice=0;//总价格
					var allCount=0;//总份数
					if(newGoodsList!=null&&newGoodsList.length>0){
						$(".go").hide();
						$(".balance").show();
						for(var i=0;i<newGoodsList.length;i++){
							for(var j=0;j<$('.orderList').find("dl").length;j++){
								if(newGoodsList[i].goodsId==$('.orderList').find("dl").eq(j).attr("lang")){
									if(newGoodsList[i].elem_num>0){
										$('.orderList').find("dl").eq(j).find(".buyCount").text(newGoodsList[i].elem_num).show();
										$('.orderList').find("dl").eq(j).find(".min").show();
										var goodsCount=$('.orderList').find("dl").eq(j).find(".goodsCount").find("em").text()-newGoodsList[i].elem_num;
										if(parseInt(newGoodsList[i].elem_num)>=parseInt($('.orderList').find("dl").eq(j).find(".goodsCount").find("em").attr("data-surplus"))){
											$('.orderList').find("dl").eq(j).find(".add").attr("class","disabled");
										}
										var _this = $('.orderList').find("dl").eq(j).find(".goodsBuy").find("span").eq(2);
										var elem_goodsName=$('.orderList').find("dl").eq(j).find(".goodsName").text();
										var elem_goodsPrice=$('.orderList').find("dl").eq(j).find(".goodsPrice").find("em").text();
										var elem_num=newGoodsList[i].elem_num;
										var elem_goodsId=$('.orderList').find("dl").eq(j).attr("lang");
										allgoodsPrice+=elem_num*elem_goodsPrice;
										//allCount+=elem_num;
										$('.orderList').find("dl").eq(j).find(".goodsCount").find("em").text(goodsCount);//剩余份数
										detail_and_car(_this,elem_goodsName,elem_goodsPrice,elem_num,elem_goodsId,goodsCount);
									}
								}
							}
							//buyCar(elem_goodsName,elem_goodsPrice,elem_num,elem_goodsId);
						}
						$(".botton").find(".allCount").show();
						$('.botton').find('b').text(allgoodsPrice.toFixed(2));  //总价格
						//$(".botton").find(".allCount").text(allCount).show();//总份数
					}
				}
				
				
				
				
			}
			
		},
		error:function(data)
		{
			console.log('失败原因:'+ JSON.stringify(data.returnContent));
		}
	})
}

function buyCar(elem_goodsName,elem_goodsPrice,elem_num,elem_goodsId){
	$('.scroll ul').append("<li lang="+elem_goodsId+"><span class='dishName'>"+elem_goodsName+"</span><span class='dishPrice'>&#165;<em>"+elem_goodsPrice+"</em></span><span class='min'>-</span><span class='buyCount'>"+elem_num+"</span><span class='add'>+</span></li>")
}

function getCompanyDetailByMachineId(){
	var machineId = localStorage.getItem("machineId");
	var url = apiurl + 'api/wechat/getCompanyDetailByMachineIdWX';
	var jsonData = {"machineId":machineId};
	console.log("给后台的数据："+JSON.stringify(jsonData));
	$.ajax({
		type:"post",
		url:url,
		data:$.toJSON(jsonData),
		dataType:'json',
		success:function(data)
		{
			console.log("商家信息:"+JSON.stringify(data));
			$('.bussinessTel').text(data.returnContent.companyTel);
			$('.bussinessTel').parent().attr("href","tel:"+data.returnContent.companyTel);
			$('.bussinessAddress').text(data.returnContent.address);
			$('.companyName').text(data.returnContent.companyName);
			if(data.returnContent.businessHours==""){
				$('.bussinessTime').hide();
			}else{
				$('.bussinessTime').find('em').text(data.returnContent.businessHours);
			}
			$('.businessInfo').attr("lang",data.returnContent.companyId);
		},error:function(){
		}
	});
}


//显示隐藏下单总份数和去结算
function showOrhide()
{
	if($('.allCount').text()>0)
	{
		$(".allCount").show();
		$('.botton a').show();
		$('.botton .go').hide();
	}
	else
	{
		$(".allCount").hide();
		$('.botton a').hide();
		$('.botton .go').show();
	}
}

//订单页面'加'和'减'操作
function add_min_order(_this,elem_num)  //elem_num:下单的份数
{
	var elem_array = get_array($('.scroll ul').children('li'));  //遍历购物车的li列表
	var elem_goodsName = _this.parents('dl').find('.goodsName').text();  //获取菜品名称
	var elem_goodsPrice = _this.parents('dl').find('.goodsPrice em').text();  //获取菜品单价
	var elem_goodsCount = _this.parents('dl').find('.goodsCount em').data('surplus');  //获取菜品总份
	localStorage.setItem("elem_goodsCount",elem_goodsCount);
	var elem_goodsId = _this.parents("dl").attr('lang');
	var is_exist = elem_array.filter(function (item,index,array) {  
		return $(item).find('.dishName').text() === elem_goodsName    //比较首页和购物车的菜名是否相同
	});
	var elem_carAdd = $(is_exist[0]).find('.add');
	var elem_indexDisabled = $(is_exist[0]).find('.disabled');
	if(elem_num <= 0)
	{
		_this.parent().find('.min,.buyCount').hide();   //隐藏订单页面的'减'图标和'数量'显示框
	}
	else if(elem_num > elem_goodsCount)
	{
		return;
	}
	else if(elem_num == elem_goodsCount)
	{
		if($(this).attr('class') == "add")
		{
			_this.parent().find('add').addClass('disabled').removeClass('add');
			elem_carAdd.addClass('disabled').removeClass('add');
		}
		else
		{
			_this.addClass('disabled').removeClass('add');
			elem_carAdd.addClass('disabled').removeClass('add');
		}
		
	}
	else
	{
		if($(this).attr('class') == "min")
		{
			_this.parent().find('.min,.buyCount').show();   //显示首页列表中'减'的图标和input框
			elem_indexDisabled.removeClass('disabled').addClass('add');  //将购物车里的'加'的标志变红 色 
			_this.removeClass('disabled').addClass('add');  //将首页里的'加'的标志变红色
		}
		else
		{
			elem_indexDisabled.removeClass('disabled').addClass('add');  //将购物车里的'加'的标志变红 色 
			_this.siblings('.disabled').removeClass('disabled').addClass('add'); 
			_this.parent().find('.min,.buyCount').show();
		}
		
	}
	_this.parents('dl').find('.buyCount').text(elem_num);  //显示下单份数
	_this.parents('dl').find('.goodsCount em').text(elem_goodsCount - elem_num);  //剩余菜品份数
	$('.botton').find('b').text((elem_num * elem_goodsPrice).toFixed(2));  //总价格
	order_and_car(_this,elem_goodsName,elem_goodsPrice,elem_num,elem_goodsId);
}

//购物车订单'加'和'减'操作
function add_min_car(_this,elem_num)
{
	var elem_array = get_array($('.orderList').find('dl'));  //遍历首页的li列表
	var elem_dishName = _this.parent('li').find('.dishName').text();  //获取菜品名称
	var elem_dishPrice = _this.parent('li').find('.dishPrice em').text();  //获取菜品单价
	var is_exist = elem_array.filter(function(item,index,array){
		return $(item).find('.goodsName').text() === elem_dishName;   //比较首页和购物车的菜名是否相同
	});
	var elem_goodsCount = $(is_exist[0]).find('.goodsCount em').data('surplus');  //获取菜品初始化时的剩余份数
	var elem_indexAdd = $(is_exist[0]).find('.add');
	var elem_indexDisabled = $(is_exist[0]).find('.disabled');
	if(elem_num <= 0)
	{
		$(is_exist[0]).find('.min,.buyCount').hide();   //隐藏订单页面的'减'图标和'数量'显示框
		_this.parent('li').remove();  //购物车列表中删除这条数据
		$('.car').parents('.dialog').removeClass("look");
		elem_indexAdd.removeClass('disabled').addClass('.add');  //将首页里的'加'的标志变红 色 
	}
	else if(elem_num > elem_goodsCount)
	{
		return;
	}
	else if(elem_num == elem_goodsCount)
	{
		_this.addClass('disabled').removeClass('add');
		elem_indexAdd.addClass('disabled').removeClass('add');
	}
	else
	{
		if(_this.attr('class') == "min")
		{
			elem_indexDisabled.removeClass('disabled').addClass('add');  //将首页里的'加'的标志变红 色 
			_this.siblings('.disabled').removeClass('disabled').addClass('add');  //将购物车里的'加'的标志变红色 
		}
	}
	
	_this.parent().find('.buyCount').text(elem_num);   //购物车的下单份数
	$(is_exist[0]).find('.buyCount').text(elem_num);   //首页的下单份数
	$(is_exist[0]).find('.goodsCount em').text(elem_goodsCount - elem_num);  //剩余菜品份数
	_this.parents('li').attr("data-surplus",elem_goodsCount - elem_num);
	$('.botton').find('b').text((elem_num * elem_dishPrice).toFixed(2));  //总价格

	//订单总数
	var sumNumber = totalCount($('.scroll li'));
	$('.botton').find('.allCount').text(sumNumber);
	
	//价格总数
	var sumPrice = totalPrice($('.scroll li'));
	$('.botton').find('b').text(sumPrice.toFixed(2));
}

//订单页关联购物车
function order_and_car(elem,elem_goodsName,elem_goodsPrice,elem_num,elem_goodsId)
{
	var _thisSurplus=elem.parents('dl').find('.goodsCount em').text();
	var elem_array = get_array($('.scroll ul').children('li'));
	var is_exist = elem_array.filter(function (item,index,array){
		return $(item).find('.dishName').text() === elem_goodsName;
	});
	var elem_goodsCount = localStorage.getItem('elem_goodsCount');
	
	if(is_exist.length<=0)
	{
		if(elem_goodsCount == 1)
		{
			$('.scroll ul').append("<li lang="+elem_goodsId+" data-surplus='"+_thisSurplus+"'><span class='dishName'>"+elem_goodsName+"</span><span class='dishPrice'>&#165;<em>"+elem_goodsPrice+"</em></span><span class='min'>-</span><span class='buyCount'>1</span><span class='disabled'>+</span></li>")
		}
		else
		{
			$('.scroll ul').append("<li lang="+elem_goodsId+" data-surplus='"+_thisSurplus+"'><span class='dishName'>"+elem_goodsName+"</span><span class='dishPrice'>&#165;<em>"+elem_goodsPrice+"</em></span><span class='min'>-</span><span class='buyCount'>1</span><span class='add'>+</span></li>")
		}
	}
	else
	{
		$(is_exist[0]).find(".buyCount").text(elem_num);
	}
	$('.scroll li').each(function(){
		if($(this).find(".buyCount").text() == 0)
		{
			$(this).remove();
		}
		if(elem_goodsId==$(this).attr("lang")){
			$(this).attr("data-surplus",_thisSurplus);
		}
	});
	
	//订单总数
	var sumNumber = totalCount($('.scroll li'));
	$('.allCount').text(sumNumber);
	
	//价格总数
	var sumPrice = totalPrice($('.scroll li'));
	$('.botton').find('b').text(sumPrice.toFixed(2));
	
}

//详情页购物车回显到订单页
function detail_and_car(elem,elem_goodsName,elem_goodsPrice,elem_num,elem_goodsId,goodsCount)
{
	var elem_goodsCount = elem.parents("dl").find(".goodsCount").find("em").text();
	console.log(elem_goodsName+"+++"+elem_goodsCount);
		if(elem_goodsCount == 0)
		{
			$('.scroll ul').append("<li lang="+elem_goodsId+" data-surplus="+goodsCount+"><span class='dishName'>"+elem_goodsName+"</span><span class='dishPrice'>&#165;<em>"+elem_goodsPrice+"</em></span><span class='min'>-</span><span class='buyCount'>"+elem_num+"</span><span class='disabled'>+</span></li>")
		}
		else
		{
			$('.scroll ul').append("<li lang="+elem_goodsId+" data-surplus="+goodsCount+"><span class='dishName'>"+elem_goodsName+"</span><span class='dishPrice'>&#165;<em>"+elem_goodsPrice+"</em></span><span class='min'>-</span><span class='buyCount'>"+elem_num+"</span><span class='add'>+</span></li>")
		}
	//订单总数
	var sumNumber = totalCount($('.scroll li'));
	$('.allCount').text(sumNumber);
	//价格总数
	var sumPrice = totalPrice($('.scroll li'));
	$('.botton').find('b').text(sumPrice.toFixed(2));
}




//订单总数量获取
function totalCount(number)
{
	var sumCount = 0;
	for(var i = 0;i < number.length; i++)
	{
		sumCount += parseInt($('.scroll li').eq(i).find('.buyCount').text());
	}
	return sumCount;
}

//订单总价钱获取
function totalPrice(price)
{
	var sumPrice = 0;
	for(var i = 0;i < price.length; i++)
	{
		sumPrice += parseFloat(($('.scroll li').eq(i).find('.dishPrice em').text())*$('.scroll li').eq(i).find('.buyCount').text());
	}
	return sumPrice;
}
//添加原生数组，以供迭代
function get_array(name)
{
	var new_array = [];
	for(var i = 0;i < name.length; i+=1)
	{
		new_array.push(name[i])
	}
	return new_array;
}
