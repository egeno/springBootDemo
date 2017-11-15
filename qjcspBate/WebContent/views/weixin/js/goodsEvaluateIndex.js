var newGoodsList=JSON.parse(localStorage.getItem("newGoodsList"));//零售购物车数组
var objgoods;//零售数组中的对象
var carobj=JSON.parse(localStorage.getItem("carobj"));//预订购物车数组
var orderDetails=[];
var dateobj;//时间数组，商品数组
var goodobj;
var areaModelId=localStorage.getItem("selectAreaModelId");//分区模型Id
var _thisAreaIndex=localStorage.getItem("_thisAreaIndex");
var _thisTime=localStorage.getItem("_thisTime");//当前预订时间
var _thisDate=localStorage.getItem("_thisDate");//当前菜品日期
var _thisGoodsId=$(".head").attr("lang");
var _thisDateIndex=localStorage.getItem("_thisDateIndex");//当前日期的index
var _thisAllDate=localStorage.getItem("_thisAllDate");
var goodsSurplus=localStorage.getItem("goodsSurplus");//当日期模型下剩余份数
var initialgoodsSurplus=localStorage.getItem("allSurplus");//当前页剩余份数
var _thisIssueId=localStorage.getItem("_thisIssueId");
console.log("页面初始化时获取到的newGoodsList"+JSON.stringify(newGoodsList));
$(function(){
	checkOpenId();
	var goodsIcon=localStorage.getItem("goodsIcon");//商品图片
	var pageCurrent=1;//当前页
	var isdel=0;//是否查看有评价的内容（0代表查看所有，1代表只查看有内容的）
	var machineId=localStorage.getItem("machineId");//零售设备Id
	var selectMachineId=localStorage.getItem("selectMachineId");//预订设备Id
	var selectDate=localStorage.getItem("selectdate");//选择日期
	var goodsId=localStorage.getItem("goodsId");//商品id
	var orderType=localStorage.getItem("orderType");//订单类型（0预订，1零售）
	var imgsrc=0;//复选框变量
	localStorage.setItem("pageCurrent",pageCurrent);
	var date=localStorage.getItem("date");
	var startTime=localStorage.getItem("startTime");
	var endTime=localStorage.getItem("endTime");
	//页面初始化赋值
	$(".ingredients_box").empty();
	$(".head").attr("lang",goodsId);
	$(".goodImg").find("img").attr("src",goodsIcon);
	
	//判断是预订进来的还是零售进来的
	if(orderType=="1"){
		var wxJson={"machineId":machineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":pageCurrent,"isdel":isdel,"selectDate":"",areaModelId:""};
		getGoodsDetailsWX(wxJson,orderType);
		var elem_num=localStorage.getItem("elem_num");//父页面下单数
		//点击返回
		$(".back").click(function(){
			window.location.href = 'order.html?live';
		});
		//是否只查看有内容的评价
		$('.check').click(function(){
			if(imgsrc==0){
				$(this).attr('src',"../img/image/ctrue.png").addClass("ctrue");
				pageCurrent=localStorage.setItem("pageCurrent",1);//当前页
				var wxJson={"machineId":machineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":1,"isdel":1,"selectDate":"","areaModelId":""};
				showEvaluate(wxJson);
				imgsrc=1;
			}
			else{
				$(this).attr('src',"../img/image/cfalse.png").removeClass("ctrue");
				pageCurrent=localStorage.setItem("pageCurrent",1);//当前页
				var wxJson={"machineId":machineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":1,"isdel":0,"selectDate":"","areaModelId":""};
				showEvaluate(wxJson);
				imgsrc=0;
			}
		});
		//查看更多评价
		$(".showAll").click(function(){
			var pageCurrent=localStorage.getItem("pageCurrent",pageCurrent);
			if($('.check').hasClass("ctrue")){
				var wxJson={"machineId":machineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":pageCurrent,"isdel":1,"selectDate":"","areaModelId":""};
				evaluateDetailswx(wxJson);
			}else{
				var wxJson={"machineId":machineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":pageCurrent,"isdel":0,"selectDate":"","areaModelId":""};
				evaluateDetailswx(wxJson);
			}
		});
	}
	if(orderType=="0"){
		var wxJson={"machineId":selectMachineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":pageCurrent,"isdel":isdel,"selectDate":selectDate,"areaModelId":areaModelId};
		getGoodsDetailsWX(wxJson,orderType);
		//点击返回
		$(".back").click(function(){
			window.location.href = 'Nooutput.html?live';
		});
		//是否只查看有内容的评价
		$('.check').click(function(){
			if(imgsrc==0){
				$(this).attr('src',"../img/image/ctrue.png").addClass("ctrue");
				pageCurrent=localStorage.setItem("pageCurrent",1);//当前页
				var wxJson={"machineId":selectMachineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":1,"isdel":1,"selectDate":selectDate,"areaModelId":areaModelId};
				showEvaluate(wxJson);
				imgsrc=1;
			}
			else{
				$(this).attr('src',"../img/image/cfalse.png").removeClass("ctrue");
				pageCurrent=localStorage.setItem("pageCurrent",1);//当前页
				var wxJson={"machineId":selectMachineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":1,"isdel":0,"selectDate":selectDate,"areaModelId":areaModelId};
				showEvaluate(wxJson);
				imgsrc=0;
			}
		});
		//查看更多评价
		$(".showAll").click(function(){
			var pageCurrent=localStorage.getItem("pageCurrent",pageCurrent);
			if($('.check').hasClass("ctrue")){
				var wxJson={"machineId":selectMachineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":pageCurrent,"isdel":1,"selectDate":selectDate,"areaModelId":areaModelId};
				evaluateDetailswx(wxJson);
			}else{
				var wxJson={"machineId":selectMachineId,"goodsId":goodsId,"orderType":orderType,"pageCurrent":pageCurrent,"isdel":0,"selectDate":selectDate,"areaModelId":areaModelId};
				evaluateDetailswx(wxJson);
			}
		});
	
	}
	
	var falg=true;//订单加减变量
	var goodsName=$('.goodsName').text();//获取商品名
	localStorage.setItem("goodsName",goodsName);
	//点击结算
	$('.balance').click(function(){
		checkCustomerId();
		var customerId= sessionStorage.getItem('customerId');
		//var customerId= "2001";
   	   //传参
		if(customerId!=null && customerId.length>0){
			var allCount = $(".botton b").text();  //allCount:总价格
			var goodsList = [];
			$('.scroll li').each(function(){
				var goodsName = $(this).find('.dishName').text();  //goodsName:菜品名称
				var goodsPrice = $(this).find(".dishPrice").find("em").text();  //goodsPrice:菜品单价
				var goodsCount = $(this).find(".buyCount").text();   //goodsCount:下单份数
				var arrays = {"goodsName":goodsName,"goodsPrice":goodsPrice,"goodsCount":goodsCount,"goodsId":goodsId};
				goodsList.push(arrays);  //goodsList:商品列表
				var returnContent = {"allCount":allCount,"goodsList":goodsList}
				var data = JSON.stringify(returnContent);
				localStorage.setItem("data",data);
				console.log(data);
			})
			window.location.href = "../view/orderRetail.html";
		}
	});
	//滚动显示隐藏头部提示文字
	$(window).scroll(function(){
		if($(this).scrollTop() > 2)
		{
			$('.topTitle').css({'display':'block'});
		}
		else
		{
			$('.topTitle').css({'display':'none'});
		}
	})
	
	//购物车
	$(".enterCar").click(function(){
		if($('.car').parents('.dialog').hasClass("showCar"))
		{
			$('.car').parents('.dialog').removeClass("showCar");
		}
		else
		{
			if($('.allCount').text() > 0)
			{
				$('.car').parents('.dialog').addClass("showCar");
			}
		}
	});
	
	//清空购物车
	$('.delete').click(function(){
		var orderType=localStorage.getItem("orderType");//订单类型（0预订，1零售）
		elem_num=0;
		localStorage.setItem("elem_num",0);
		if(orderType==1){
			//重组数组
			newGoodsList=[];
			localStorage.setItem("newGoodsList",JSON.stringify(newGoodsList));
			$(".balance").hide();
			$(".go").show();
			var initialSurpleNum=localStorage.getItem("initialSurpleNum");
			$(".goodsSurpleNum").text(initialSurpleNum);
			window.location.reload();
		}else{
			//重组数组
			carobj=[];
			localStorage.setItem("carobj",JSON.stringify(carobj));
			$(".balance").hide();
			$(".go").show();
			localStorage.setItem("goodsSurplus",initialgoodsSurplus);
			window.location.reload();
		}
	});
	
	
	//购物车点击添加'+'图标加
	$('.scroll').on('click','.car_add',function(){
		var _this = $(this);
		if($(".buyCount").text()==0){
			$(".buyCount").text("0");
		}
		var elem_num = parseInt($(this).parent().find('.buyCount').text()) + 1;
		var carSurplus=parseInt(_this.parents("li").attr("data-surplus"))-1;
		var orderType=localStorage.getItem("orderType");//订单类型（0预订，1零售）
		add_min_car(_this,elem_num,carSurplus,orderType);
		if(orderType==1){
			showOrhide();
		}else{
			showhide();
		}
		
	})
	
	//购物车点击'-'的图标减
	$('.scroll').on('click','.car_min',function(){
		var _this = $(this);
		if($(".buyCount").text()==0){
			$(".buyCount").text("0");
		}
		var elem_num = parseInt($(this).parent().find('.buyCount').text()) - 1;
		var carSurplus=parseInt(_this.parents("li").attr("data-surplus"))+1;
		var orderType=localStorage.getItem("orderType");//订单类型（0预订，1零售）
		if(elem_num>=0){
			add_min_car(_this,elem_num,carSurplus,orderType);
		}
		if(orderType==1){
			showOrhide();
		}else{
			showhide();
		}
	})
	
	//订单列表点击'+'的图标添加
	$('.monery').on('click','.add',function(){
		var _this = $(this);
		if($(".head").find(".buyCount").text().length==0){
			$(".head").find(".buyCount").text("0");
		}
		var elem_num = parseInt($(".head").find(".buyCount").text()) + 1;
		var over=parseInt($('.start').find("em").text());//剩余份数
		$('.start').find("em").text(over-1);
		$('.scroll li').each(function(){
			if($(".head").attr('lang')==$(this).attr("lang")){
				$(this).attr("data-surplus",$('.start').find("em").text());//购物车中当前剩余份数
			}
		});
		var orderType=localStorage.getItem("orderType");//订单类型（0预订，1零售）
		add_min_order(_this,elem_num,orderType);
		var goodsName=localStorage.getItem("goodsName",goodsName);
		if(orderType==1){
			showOrhide();
		}else{
			showhide();
		}
	})
	
	//订单列点击'-'的图标减
	$('.monery').on('click','.min',function(){
		var _this = $(this);
		if($(".head").find(".buyCount").text().length==0){
			$(".buyCount").text("0");
		}
		var elem_num = parseInt($(".head").find(".buyCount").text()) - 1;
		console.log("菜品详情newGoodsList："+newGoodsList);
		var over=parseInt($('.start').find("em").text());//剩余份数
		$('.start').find("em").text(over+1);
		$('.scroll li').each(function(){
			if($(".head").attr('lang')==$(this).attr("lang")){
				$(this).attr("data-surplus",$('.start').find("em").text());//购物车中当前剩余份数
			}
		});
		var orderType=localStorage.getItem("orderType");//订单类型（0预订，1零售）
		if(elem_num>=0){
			add_min_order(_this,elem_num,orderType);
		}
		if(orderType==1){
			showOrhide();
		}else{
			showhide();
		}
	})
});



//初始化添加零售购物车列表
function carList(){
	for(var i=0;i<newGoodsList.length;i++){
		$('.scroll ul').append("<li lang="+newGoodsList[i].goodsId+"  data-surplus='"+newGoodsList[i].surplus+"'><span class='dishName'>"+newGoodsList[i].goodsName+"</span><span class='dishPrice'>&#165;<em>"+newGoodsList[i].goodsPrice+"</em></span><img src='../img/img_reserve/reduce.png' class='car_min'><span class='buyCount'>"+newGoodsList[i].elem_num+"</span><img src='../img/img_reserve/add-to.png' class='car_add'></li>")
	}
	//订单总数
	var sumNumber = totalCount($('.scroll li'));
	$('.allCount').text(sumNumber);
	//价格总数
	var sumPrice = totalPrice($('.scroll li'));
	$('.botton').find('b').text(sumPrice.toFixed(2));
	if(sumPrice.toFixed(2)>0){
		$(".balance").show();
		$(".go").hide();
	}
}

//初始化添加预订购物车列表
function reserveCarList(){
	$(".scroll").empty();
	if(carobj!=null){
		for(var i=0;i<carobj.length;i++){
			$(".scroll").append("<ul lang="+carobj[i].areaModelId+" date_areaIndex="+carobj[i].areaIndex+"><div class='reserve'><em class='date' date_index="+carobj[i].dateIndex+" date_day="+carobj[i].AllDate+">"+carobj[i].date+"</em><em class='time'>"+carobj[i].time+"</em></div></ul>");
		}
		for(var i=0;i<carobj.length;i++){
			for(var j=0;j<carobj[i].orderDetails.length;j++){
				if($(".scroll").find("ul").eq(i).attr("lang")==carobj[i].areaModelId){
					$(".scroll").find("ul").eq(i).append("<li lang="+carobj[i].orderDetails[j].goodsId+"><span class='dishName'>"+carobj[i].orderDetails[j].cargoodsName+"</span><span class='dishPrice'>&#165;<em>"+carobj[i].orderDetails[j].retailPrice+"</em></span><div class='AddMinBox'><img src='../img/img_reserve/reduce.png' class='car_min'><span class='buyCount'>"+carobj[i].orderDetails[j].goodsNum+"</span><img src='../img/img_reserve/add-to.png' class='car_add'></div></li>");
				}
			}
		}
	}
	//该日期列表内的份数是否在可预订份数内
	var nowNum=0;
	var nowUL=$(this);
	$(".scroll ul").each(function(){
		if($(this).find(".date").text()==_thisDate&&$(this).find(".time").text()==_thisTime){
			nowUL=$(this);
			for(var j=0;j<$(this).find("li").length;j++){
				//当前页菜品对应的日期列表下的下单总数
				nowNum+=parseInt($(this).find("li").eq(j).find(".buyCount").text());
			}
		}
	});
	if(nowNum==initialgoodsSurplus){
		$('.add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
		nowUL.find("li").find('.car_add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
	}
	//订单总数
	var sumNumber = totalCount($('.scroll li'));
	$('.allCount').text(sumNumber);
	//价格总数
	var sumPrice = totalPrice($('.scroll li'));
	$('.botton').find('b').text(sumPrice.toFixed(2));
	if(sumPrice.toFixed(2)>0){
		$(".balance").show();
		$(".go").hide();
	}
	showhide();
}


//购物车加减
function add_min_car(_this,elem_num,carSurplus,orderType){
	var now_goodsId=_this.parents("li").attr("lang");
	if(now_goodsId==$(".head").attr('lang')){
		if(orderType=="1"){
			//如果是本页的菜品就让他调原来的方法
			_this.parents("li").attr("data-surplus",carSurplus );
			var over=parseInt($('.start').find("em").text());//剩余份数
			var SurpleNum=localStorage.getItem("SurpleNum");
			if(elem_num==SurpleNum){
				$('.add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
				_this.parent("li").find('.car_add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
				if(_this.attr("class")=="car_min"){
					$('.start').find("em").text(over+1);
				}else{
					$('.start').find("em").text(over-1);
				}
			}else{
				if(_this.attr("class")=="car_min"){
					$('.start').find("em").text(over+1);
				}else{
					$('.start').find("em").text(over-1);
				}
			}
		}else{
			if(_this.attr("class")=="car_min"){
				$('.start').find("em").text(parseInt($(".goodsSurpleNum").text())+1);
			}else{
				$('.start').find("em").text(parseInt($(".goodsSurpleNum").text())-1);
			}
		}
		add_min_order(_this,elem_num,orderType);
	}else{
		//如果不是本页菜品
		if(elem_num>0){
			_this.parents("li").find(".buyCount").text(elem_num);
			_this.parents("li").attr("data-surplus",carSurplus);
		}else{
			if(_this.parents("ul").find("li").length<=1){
				_this.parents("ul").remove();
			}else{
				_this.parents("li").remove();
			}
		}
		if(orderType=="0"){
			if(_this.attr("class")=="car_min"){
				$('.start').find("em").text(parseInt($(".goodsSurpleNum").text())+1);
			}else{
				$('.start').find("em").text(parseInt($(".goodsSurpleNum").text())-1);
			}
		}
		//订单总数
		var sumNumber = totalCount($('.scroll li'));
		$('.allCount').text(sumNumber);
		//价格总数
		var sumPrice = totalPrice($('.scroll li'));
		$('.botton').find('b').text(sumPrice.toFixed(2));
		if(sumPrice.toFixed(2)<=0){
			$(".balance").hide();
			$(".go").show();
		}else{
			$(".balance").show();
			$(".go").hide();
		}
	}
	var orderType=localStorage.getItem("orderType");//订单类型（0预订，1零售）
	if(orderType=="1"){
		//每次重组数组
		newGoodsList=[];
		for(var i=0;i<$(".scroll").find("li").length;i++){
			var scrollGoodsId=$(".scroll").find("li").eq(i).attr("lang");
			var scrollElemNum=$(".scroll").find("li").eq(i).find(".buyCount").text();
			var scrollGoodsSurplus=$(".scroll").find("li").eq(i).attr("data-surplus");
			var scrollGoodsPrice=$(".scroll").find("li").eq(i).find(".dishPrice").find("em").text();
			var scrollGoodsName=$(".scroll").find("li").eq(i).find(".dishName").text();
			objgoods={"goodsId":scrollGoodsId,"elem_num":scrollElemNum,"retailPrice":scrollGoodsPrice,"goodsName":scrollGoodsName,"surplus":scrollGoodsSurplus};
			newGoodsList.push(objgoods);
		}
		localStorage.setItem("newGoodsList",JSON.stringify(newGoodsList));
	}else{
		carobj=[]; //购物车数组
		dateobj="";//时间数组，商品数组
		_thisIssueId=localStorage.getItem("_thisIssueId");
		for(var i=0;i<$(".scroll").find('ul').length;i++){//循环对应日期内的所有餐品
			orderDetails=[];
			var ulThis=$(".scroll").find('ul').eq(i);
			var date=ulThis.find(".date").text();
			var AllDate=ulThis.find(".date").attr("date_day");
			//取餐开始时间和结束时间截取
			var time=ulThis.find(".time").text();
			var lengTime=time.substring(time.length-12);
			console.log("lengTime"+lengTime);
			var finishTime=lengTime.substr(0,time.length-1);
			var splitTime=finishTime.split("-");
			var takenStartTime=AllDate+" "+splitTime[0]+":00";
			function getDate(strDate) {
	            var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
	             function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
	            return date;
	        }
			if(ulThis.attr("date_areaindex")==3||ulThis.attr("date_areaindex")=="3"){
				//如果是晚餐取餐截止时间加一天
				var Year=getDate(AllDate).getFullYear();
				var Months=getDate(AllDate).getMonth()+1;
				var Day=getDate(AllDate).getDate()+1;
				AllDate=Year+"-"+Months+"-"+Day;
				var takenEndTime=AllDate+" "+splitTime[1].substring(0,splitTime[1].length-1)+":00";
			}else{
				var takenEndTime=AllDate+" "+splitTime[1].substring(0,splitTime[1].length-1)+":00";
			}
			console.log("takenStartTime"+takenStartTime+",takenEndTime"+takenEndTime);
			
			var areaModelId=ulThis.attr("lang");
			var DateIndex=ulThis.find(".date").attr("date_index");
			var AreaIndex=ulThis.attr("date_areaIndex");
			for(var j=0;j<$(".scroll").find('ul').eq(i).find("li").length;j++){   //循环单份菜品信息
				var liThis=$(".scroll").find('ul').eq(i).find("li").eq(j);
				var cargoodsId=liThis.attr("lang");
				var cargoodsPrice=liThis.find(".dishPrice em").text();
				var cargoodsNum=liThis.find(".buyCount").text();
				var cargoodsName=liThis.find(".dishName").text();
				orderDetails.push({"goodsId":cargoodsId,"retailPrice":cargoodsPrice,"goodsNum":cargoodsNum,"cargoodsName":cargoodsName,"issueId":_thisIssueId});
			}
			dateobj={"date":date,"AllDate":AllDate,"time":time,"takenStartTime":takenStartTime,"takenEndTime":takenStartTime,"dateIndex":DateIndex,"areaIndex":AreaIndex,"areaModelId":areaModelId,"orderDetails":orderDetails};
			carobj.push(dateobj);
		}
		localStorage.setItem("carobj",JSON.stringify(carobj));
	}
}


//订单加减
function add_min_order(_this,elem_num,orderType){
	var nogoods=true;
	var over=parseInt($('.start').find("em").text());//剩余份数
	if(orderType=="1"){
		var SurpleNum=localStorage.getItem("SurpleNum");
	}
	else{
		var SurpleNum=initialgoodsSurplus;//当日期模型下剩余份数
	}
	var elem_goodsId = $(".head").attr('lang');
	var elem_goodsName=$(".head").find(".goodsName").text();
	var elem_Surplus=$(".head").find(".goodsSurpleNum").text();
	var elem_goodsPrice=$(".head").find(".goodsPrice").text();
	var orderType=localStorage.getItem("orderType");//订单类型（0预订，1零售）
	for(var i=0;i<$(".scroll").find("li").length;i++){
		var carGoodsId=$(".scroll").find("li").eq(i).attr("lang");
		var index=i;
		if(carGoodsId==elem_goodsId){
			nogoods=false;
			if(elem_num>SurpleNum){
				//如果大于剩余份数添加按钮变灰不能点击
				$('.add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
				$(".scroll").find("li").eq(i).find('.car_add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
				break;
			}else if(elem_num==0){
				$('.add').attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
				$(".scroll").find("li").eq(i).find('.car_add').attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
				$('.min').hide();
				$(".scroll").find("li").eq(i).find('.car_min').hide();
				$('.allCount').text(parseInt($(".allCount").text())-1);//购物车总份数
				if($('.allCount').text()==0){
					$('.allCount').hide();
				}
				$(".head").find(".buyCount").text("");
				localStorage.setItem("elem_num",elem_num);
				/*$(".scroll").find("li").eq(i).attr("data-surplus",elem_Surplus);//购物车中当前剩余份数
*/				var pice=parseFloat($('.goodsPrice').text());//商品单价
				var allPice=parseFloat($(".botton").find("b").text())-pice;//应付金额
				$('.botton').find('b').text(allPice.toFixed(2));//购物车总价钱
				if(allPice.toFixed(2)<=0){
					$(".balance").hide();
					$(".go").show();
				}else{
					$(".balance").show();
					$(".go").hide();
				}
				var nowPice=pice*elem_num;
				if(nowPice==0){
					if($(".scroll").find("li").eq(i).parents("ul").find("li").length<=1){
						$(".scroll").find("li").eq(i).parents("ul").remove();
					}else{$(".scroll").find("li").eq(i).remove();}
				}else{
					$(".scroll").find("li").eq(i).find('.dishPrice').find("em").text(pice*elem_num.toFixed(2));//购物车订单商品总价钱
				}
				if(orderType=="0"){
					//预订达到可售份数
					var nowNum=0;
					var nowUL=$(".scroll").find("li").eq(i).parent();
					for(var i=0;i<nowUL.find("li").length;i++){
						nowNum+=parseInt(nowUL.find("li").eq(i).find(".buyCount").text());
					}
					if(nowNum==SurpleNum){
						$('.add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
						nowUL.find("li").find('.car_add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
					}
				}
				break;
			}
			else if(elem_num>0 && elem_num<=SurpleNum){
				$('.add').attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
				$(".scroll").find("li").eq(i).find('.car_add').attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
				$(".scroll").find("li").eq(i).find('.car_add').attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
				$('.min').show();
				$(".scroll").find("li").eq(i).find('.car_min').show();
				$(".head").find('.buyCount').text(elem_num);//订单总份数
				localStorage.setItem("elem_num",elem_num);
				$(".scroll").find("li").eq(i).find('.buyCount').text(elem_num);//购物车中当前订单总份数
				if(orderType=="0"){
					localStorage.setItem("goodsSurplus",parseInt($(".goodsSurpleNum").text())-1);
				}
				/*$(".scroll").find("li").eq(i).attr("data-surplus",elem_Surplus);//购物车中当前剩余份数
*/				//订单总数
				var sumNumber = totalCount($('.scroll li'));
				$('.allCount').text(sumNumber);
				//价格总数
				var sumPrice = totalPrice($('.scroll li'));
				$('.botton').find('b').text(sumPrice.toFixed(2));
				if(sumPrice.toFixed(2)<=0){
					$(".balance").hide();
					$(".go").show();
				}else{
					$(".balance").show();
					$(".go").hide();
				}
				if(orderType=="1"){
					//零售达到可售份数
					if(elem_num==SurpleNum){
						$('.add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
						$(".scroll").find("li").eq(i).find('.car_add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
					}
				}else{
					//预订达到可售份数
					var nowNum=0;
					var nowUL=$(".scroll").find("li").eq(i).parent();
					for(var i=0;i<nowUL.find("li").length;i++){
						nowNum+=parseInt(nowUL.find("li").eq(i).find(".buyCount").text());
					}
					if(nowNum==SurpleNum){
						$('.add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
						nowUL.find("li").find('.car_add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
					}else{
						nowUL.find("li").find('.car_add').attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
					}
				}
				break;
			}
		}
	}
	//如果购物车内没有对应餐品则新增一条
	if(nogoods){
		if(orderType=="1"){
			$('.scroll').append("<ul><li lang="+elem_goodsId+" data-surplus='"+elem_Surplus+"'><span class='dishName'>"+elem_goodsName+"</span><span class='dishPrice'>&#165;<em>"+elem_goodsPrice+"</em></span><img src='../img/img_reserve/reduce.png' class='car_min'><span class='buyCount'>"+elem_num+"</span><img src='../img/img_reserve/add-to.png' class='car_add'></li></ul>");
			$(".head").find(".buyCount").text(elem_num);
			localStorage.setItem("elem_num",elem_num);
			$(".allCount").text(parseInt($(".allCount").text())+elem_num);
			$(".botton").find("b").text(elem_goodsPrice*elem_num.toFixed(2));
			if(elem_num<=0){
				$(".min").hide();
			}else{
				$('.min').show();
			}
		}else{
			var areaModelId=localStorage.getItem("selectAreaModelId");//分区模型Id
			var flagli=true;
			_thisGoodsId=$(".head").attr('lang');
			_thisAllDate=localStorage.getItem("_thisAllDate");
			var ulStr="<ul lang="+areaModelId+" date_areaIndex="+_thisAreaIndex+"><div class='reserve'><em class='date' date_index='"+_thisDateIndex+"' date_day='"+_thisAllDate+"'>"+_thisDate+"</em><em class='time'>"+_thisTime+"</em></div><li lang="+elem_goodsId+"><span class='dishName'>"+elem_goodsName+"</span><span class='dishPrice'>&#165;<em>"+elem_goodsPrice+"</em></span><div class='AddMinBox'><img src='../img/img_reserve/reduce.png' class='car_min'><span class='buyCount'>"+elem_num+"</span><img src='../img/img_reserve/add-to.png' class='car_add'></div></li></ul>";
			if($('.scroll').find("ul").length<=0){
				$('.scroll').append(ulStr);
			}else{
				$(".scroll ul").each(function(){
					var $this=$(this);
					if($this.attr("lang")==areaModelId&&$this.find(".date").text()==_thisDate){
						for(var i=0;i<$this.find("li").length;i++){
							if($this.find("li").eq(i).attr("lang")==_thisGoodsId){
								$this.find("li").eq(i).find(".buyCount").text(elem_num);//改变对应菜品份数
								localStorage.setItem("elem_num",elem_num);
								//如果是减到最后一份，那么移除购物车中的这份菜品
								if(_thisBuyCount<=0){
									$this.find("li").eq(i).remove();
									//如果购物车中对应日期中没有任何菜品了，就移除购物车中的日期对应的列表
									if($this.find("li").length<=0){
										$this.remove();
									}
								}
								flagli=false;
								break;
							}
							if(flagli){
								$this.append("<li lang="+elem_goodsId+"><span class='dishName'>"+elem_goodsName+"</span><span class='dishPrice'>&#165;<em>"+elem_goodsPrice+"</em></span><div class='AddMinBox'><img src='../img/img_reserve/reduce.png' class='car_min'><span class='buyCount'>"+elem_num+"</span><img src='../img/img_reserve/add-to.png' class='car_add'></div></li>");
								break;
							}
						}
					}else{
						$('.scroll').append(ulStr);
					}
				});
			}
			$(".head").find(".buyCount").text(elem_num);
			localStorage.setItem("elem_num",elem_num);
			$(".allCount").text(parseInt($(".allCount").text())+elem_num);
			$(".botton").find("b").text(elem_goodsPrice*elem_num.toFixed(2));
			if(elem_num<=0){
				$(".min").hied();
			}else{
				$('.min').show();
			}
		}
	}
	if(orderType=="1"){
		//每次重组数组
		newGoodsList=[];
		for(var i=0;i<$(".scroll").find("li").length;i++){
			var scrollGoodsId=$(".scroll").find("li").eq(i).attr("lang");
			var scrollElemNum=$(".scroll").find("li").eq(i).find(".buyCount").text();
			var scrollGoodsSurplus=$(".scroll").find("li").eq(i).attr("data-surplus");
			var scrollGoodsPrice=$(".scroll").find("li").eq(i).find(".dishPrice").find("em").text();
			var scrollGoodsName=$(".scroll").find("li").eq(i).find(".dishName").text();
			objgoods={"goodsId":scrollGoodsId,"elem_num":scrollElemNum,"goodsPrice":scrollGoodsPrice,"goodsName":scrollGoodsName,"surplus":scrollGoodsSurplus};
			newGoodsList.push(objgoods);
		}
		localStorage.setItem("newGoodsList",JSON.stringify(newGoodsList));
	}else{
		//每次重组数组
		carobj=[]; //购物车数组
		dateobj="";//时间数组，商品数组
		for(var i=0;i<$(".scroll").find('ul').length;i++){//循环对应日期内的所有餐品
			orderDetails=[];
			var ulThis=$(".scroll").find('ul').eq(i);
			var date=ulThis.find(".date").text();
			var AllDate=ulThis.find(".date").attr("date_day");
			//取餐开始时间和结束时间截取
			var time=ulThis.find(".time").text();
			var lengTime=time.substring(time.length-12);
			console.log("lengTime"+lengTime);
			var finishTime=lengTime.substr(0,time.length-1);
			var splitTime=finishTime.split("-");
			var takenStartTime=AllDate+" "+splitTime[0]+":00";
			function getDate(strDate) {
	            var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
	             function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
	            return date;
	        }
			if(ulThis.attr("date_areaindex")==3||ulThis.attr("date_areaindex")=="3"){
				//如果是晚餐取餐截止时间加一天
				var Year=getDate(AllDate).getFullYear();
				var Months=getDate(AllDate).getMonth()+1;
				var Day=getDate(AllDate).getDate()+1;
				AllDate=Year+"-"+Months+"-"+Day;
				var takenEndTime=AllDate+" "+splitTime[1].substring(0,splitTime[1].length-1)+":00";
			}else{
				var takenEndTime=AllDate+" "+splitTime[1].substring(0,splitTime[1].length-1)+":00";
			}
			console.log("takenStartTime"+takenStartTime+",takenEndTime"+takenEndTime);
			
			var areaModelId=ulThis.attr("lang");
			var DateIndex=ulThis.find(".date").attr("date_index");
			var AreaIndex=ulThis.attr("date_areaIndex");
			for(var j=0;j<$(".scroll").find('ul').eq(i).find("li").length;j++){   //循环单份菜品信息
				var liThis=$(".scroll").find('ul').eq(i).find("li").eq(j);
				var cargoodsId=liThis.attr("lang");
				var cargoodsPrice=liThis.find(".dishPrice em").text();
				var cargoodsNum=liThis.find(".buyCount").text();
				var cargoodsName=liThis.find(".dishName").text();
				orderDetails.push({"goodsId":cargoodsId,"retailPrice":cargoodsPrice,"goodsNum":cargoodsNum,"cargoodsName":cargoodsName,"issueId":_thisIssueId});
			}
			dateobj={"date":date,"AllDate":AllDate,"time":time,"takenStartTime":takenStartTime,"takenEndTime":takenStartTime,"dateIndex":DateIndex,"areaIndex":AreaIndex,"areaModelId":areaModelId,"orderDetails":orderDetails};
			carobj.push(dateobj);
		}
		localStorage.setItem("carobj",JSON.stringify(carobj));
	}
}


//零售显示隐藏下单总份数和去结算
function showOrhide()
{
	if($('.allCount').text()>0)
	{
		$(".allCount").css({"display":"block"});
		$('.botton a').css({"display":"block"});
		$('.botton .go').hide();
	}
	else
	{
		$(".allCount").hide();
		$('.botton a').hide();
		$('.botton .go').show();
	}
}

//预订显示隐藏下单总份数和去结算
function showhide(){
	if($('.allCount').text()>0)
	{
		$(".allCount").css({"display":"block"});
		$('.botton a').css({"display":"block"});
		$('.botton .go').hide();
	}
	else
	{
		$(".allCount").hide();
		$('.botton a').hide();
		$('.botton .go').css({"display":"block"});
	}
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

function getGoodsDetailsWX(wxJson,orderType){
	console.log("菜品详情页面wxJson"+JSON.stringify(wxJson));
	var url=apiurl +"api/wechat/getGoodsDetailsWX";
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(wxJson),
		dataType:'json',
		success:function(data){
			$(".start").find("img").attr("src","../img/img/star1.png");
			var dataContent=data.returnContent;
			console.log("data"+JSON.stringify(data));
			if(data.returnCode=="1"){
				localStorage.setItem("pageCount",dataContent.pageCount);//评价总页数
				$(".goodsPrice").text(dataContent.price);//菜品价格
				$(".goodsName").text(dataContent.goodsName);//菜品名称
				$(".tophead").find("em").text(dataContent.goodsName);//菜品名称
				if(orderType=="1"){
					$(".goodsSurpleNum").text(dataContent.goodsSurpleNum);//零售剩余份数
					localStorage.setItem("initialSurpleNum",dataContent.goodsSurpleNum);
					localStorage.setItem("SurpleNum",dataContent.goodsSurpleNum);
				}
				else{
					var goodsSurplus=localStorage.getItem("goodsSurplus");//当日期模型下剩余份数
					$(".goodsSurpleNum").text(goodsSurplus);//预订剩余份数
					localStorage.setItem("goodsSurplus",goodsSurplus);
				}
				//菜品评价等级
				var startNum= dataContent.goodsEvaluateScoreAvg
				for(var i=0;i<Math.floor(startNum);i++){
					$(".start").find("img").eq(i).attr("src","../img/img/star2.png");
				}
				if(Math.floor(startNum)==0){
					$(".start").find("img").attr("src","../img/img/star2.png");
				}
				//菜品描述
				if(dataContent.goodsMemo.length>0){
					$(".description").find("p").text(dataContent.goodsMemo);
				}
				//食材
				var ingredientsTypes=dataContent.ingredientsTypes;
				if(ingredientsTypes.length>0){
					$(".noIngredients").hide();
					for(var i=0;i<ingredientsTypes.length;i++){
						$(".ingredients_box").append("<div class='foodTitle'><h3>"+ingredientsTypes[i].ingredientsTypeName+"</h3><ul></ul></div>");
						for(var j=0;j<ingredientsTypes[i].ingredients.length;j++){
							$(".ingredients_box").find(".foodTitle").eq(i).find("ul").append("<li><span class='foodName'>"+ingredientsTypes[i].ingredients[j].ingredientsName+"</span><em class='grams'><i>"+ingredientsTypes[i].ingredients[j].ingredientsContent+"</i></em></li>");
						}
					}
				}
				//菜品评价内容
				var evaluateDetails=dataContent.evaluateDetails;
				var evaluateDetailsLength=evaluateDetails.length;
				if(evaluateDetailsLength<=3){
					$(".showAll").hide();
					$(".noEvaluate").hide();
					//$(".evaluate").css({"margin-bottom":"0.85rem"});
				}else{
					evaluateDetailsLength=3;
				}
				$(".evaluateTitle").find("em").text(dataContent.evaluateNum);//评价数
				if(dataContent.evaluateNum==0){
					/*$(".showAll").show();*/
					$(".noEvaluate").show();
					//$(".evaluate").css({"margin-bottom":"0.85rem"});
					/*$(".showAll").text("没有更多评价");
					$(".showAll").addClass("disabled");*/
				}
				for(var i=0;i<evaluateDetailsLength;i++){
					console.log(evaluateDetails[i].customerName);
					if(evaluateDetails[i].customerName==""||evaluateDetails[i].customerName.length<=0){
						var evaluateUserName="匿名用户";
					}else{
						evaluateUserName=evaluateDetails[i].customerName;
					}
					$(".evaluateList").append("<li><p><span class='listStart'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'></span><em class='userName'>"+evaluateUserName+"</em></p><p class='evaluateList_text'>"+evaluateDetails[i].goodsEvaluateContent+"</p><p class='evaluateList_info'><span class='evaluateList_time'>"+evaluateDetails[i].evaluateTime+"</span><span class='evaluateList_name'><img src='../img/img/evaluateList_name.png' class='tt'>"+evaluateDetails[i].goodsName+"</span></p></li>");
					//菜品评价分数
					var goodsEvaluateScore=evaluateDetails[i].goodsEvaluateScore;
					for(var j=0;j<Math.floor(goodsEvaluateScore);j++){
						$(".evaluateList").find("li").eq(i).find("img").eq(j).attr("src","../img/img/star2.png");
					}
					if(Math.floor(goodsEvaluateScore)==0){
						$(".evaluateList").find("li").eq(i).find("img").attr("src","../img/img/star2.png");
					}
				}
				//过滤用户名
				var reg=/^(.).+(.)$/g;//用户名中间显示*
				for(var i=0;i<$('.userName').length;i++){
					if($('.userName').text()==!"匿名用户"){
						$('.userName').eq(i).text($('.userName').eq(i).text().replace(reg,"$1***$2"));
					}
				}
				//页面初始化时显示以加入购物车的数量
				var elem_num=parseInt(localStorage.getItem("elem_num"));//父页面下单数
				if(elem_num>0){
					$(".buyCount").text(elem_num);
					localStorage.setItem("elem_num",elem_num);
					$(".min").css({"display":"block"});
					if(orderType=="1"){
						$(".goodsSurpleNum").text(parseInt($(".goodsSurpleNum").text())-elem_num);
					}
					if(elem_num>=parseInt(dataContent.goodsSurpleNum)){
						$('.add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
					}
				}
				//显示购物车列表
				if(orderType=="1"){
					carList();
					for(var i=0;i<$(".scroll ul").find("li").length;i++){
						var nowLi=$(".scroll ul").find("li").eq(i);
						if(parseInt(nowLi.attr("data-surplus"))==0){
							nowLi.find(".car_add").attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
						}
					}
					showOrhide();
				}else{
					reserveCarList();
					if(elem_num>0){
						//预订达到可售份数
						var nowNum=0;
						var nowUL;
						$(".scroll ul").each(function(){
							if($(this).find(".date").text()==_thisDate&&$(this).find(".time").text()==_thisTime){
								nowUL=$(this);
								for(var j=0;j<$(this).find("li").length;j++){
									//当前页菜品对应的日期列表下的下单总数
									nowNum+=parseInt($(this).find("li").eq(j).find(".buyCount").text());
								}
							}
						});
						if(nowNum==initialgoodsSurplus){
							$('.add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
							nowUL.find("li").find('.car_add').attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
						}else{
							nowUL.find("li").find('.car_add').attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
						}
					}
					showhide();
				}
				
			}
		}
	});
}

function evaluateDetailswx(wxJson){
	console.log("菜品评价wxJson"+JSON.stringify(wxJson));
	var url=apiurl +"api/wechat/getGoodsDetailsWX";
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(wxJson),
		dataType:'json',
		success:function(data){
			var dataContent=data.returnContent;
			if(data.returnCode=="1"){
				var pageCurrent=localStorage.getItem("pageCurrent");//当前页
				var pageCount=localStorage.getItem("pageCount");//评价总页数
				if(pageCurrent<=pageCount){
					if(pageCurrent==1){
						$(".evaluateList").empty();
						$(".showAll").show();
						/*$(".showAll").text("没有更多评价");
						$(".showAll").addClass("disabled");*/
					}
					if(pageCurrent==pageCount){
						$(".showAll").text("没有更多评价");
						$(".showAll").addClass("disabled");
					}
					//菜品评价内容
					var evaluateDetails=dataContent.evaluateDetails;
					var evaluateDetailsLength=evaluateDetails.length;
					$(".evaluateTitle").find("em").text(dataContent.evaluateNum);//评价数
					for(var i=0;i<evaluateDetailsLength;i++){
						if(evaluateDetails[i].customerName==""||evaluateDetails[i].customerName.length<=0){
							var evaluateUserName="匿名用户";
						}else{
							evaluateUserName=evaluateDetails[i].customerName;
						}
						$(".evaluateList").append("<li><p><span class='listStart'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'></span><em class='userName'>"+evaluateUserName+"</em></p><p class='evaluateList_text'>"+evaluateDetails[i].goodsEvaluateContent+"</p><p class='evaluateList_info'><span class='evaluateList_time'>"+evaluateDetails[i].evaluateTime+"</span><span class='evaluateList_name'><img src='../img/img/evaluateList_name.png' class='tt'>"+evaluateDetails[i].goodsName+"</span></p></li>");
						//菜品评价分数
						var goodsEvaluateScore=evaluateDetails[i].goodsEvaluateScore;
						for(var j=0;j<Math.floor(goodsEvaluateScore);j++){
							if(pageCurrent==1){
								$(".evaluateList").find("li").eq(i).find("img").eq(j).attr("src","../img/img/star2.png");
							}
							else{
								$(".evaluateList").find("li").eq(pageCurrent*10+i).find("img").eq(j).attr("src","../img/img/star2.png");
							}
						}
						if(Math.floor(goodsEvaluateScore)==0){
							if(pageCurrent==1){
								$(".evaluateList").find("li").eq(i).find("img").attr("src","../img/img/star2.png");
							}
							else{
								$(".evaluateList").find("li").eq(pageCurrent*10+i).find("img").attr("src","../img/img/star2.png");
							}
						}
						
					}
					//过滤用户名
					var reg=/^(.).+(.)$/g;//用户名中间显示*
					for(var i=0;i<$('.userName').length;i++){
						if($('.userName').text()==!"匿名用户"){
							$('.userName').eq(i).text($('.userName').eq(i).text().replace(reg,"$1***$2"));
						}
					}
					pageCurrent++;
					localStorage.setItem("pageCurrent",pageCurrent);
				}
				
			}
		}
	});
	
}

function showEvaluate(wxJson){
	console.log("菜品评价wxJson"+JSON.stringify(wxJson));
	var url=apiurl +"api/wechat/getGoodsDetailsWX";
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(wxJson),
		dataType:'json',
		success:function(data){
			$(".listStart").find("img").attr("src","../img/img/star1.png");
			var dataContent=data.returnContent;
			if(data.returnCode=="1"){
					var pageCurrent=localStorage.getItem("pageCurrent");//当前页
					var pageCount=localStorage.getItem("pageCount");//评价总页数
					//菜品评价内容
					var evaluateDetails=dataContent.evaluateDetails;
					var evaluateDetailsLength=evaluateDetails.length;
					$(".evaluateList").empty();
					//菜品评价内容
					var evaluateDetails=dataContent.evaluateDetails;
					var evaluateDetailsLength=evaluateDetails.length;
					$(".evaluateTitle").find("em").text(dataContent.evaluateNum);//评价数
					if(evaluateDetailsLength==0){
						/*$(".showAll").show();
						$(".showAll").text("没有更多评价");
						$(".showAll").addClass("disabled");*/
						$(".noEvaluate").show();
						$(".showAll").hide();
						//$(".evaluate").css({"margin-bottom":"0.85rem"});
					}/*else{
						$(".noEvaluate").hide();
					}*/
					for(var i=0;i<evaluateDetailsLength;i++){
						console.log(evaluateDetails[i].customerName);
						if(evaluateDetails[i].customerName==""||evaluateDetails[i].customerName.length<=0){
							var evaluateUserName="匿名用户";
						}else{
							evaluateUserName=evaluateDetails[i].customerName;
						}
						$(".evaluateList").append("<li><p><span class='listStart'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'><img src='../img/img/star1.png' alt='星星'></span><em class='userName'>"+evaluateUserName+"</em></p><p class='evaluateList_text'>"+evaluateDetails[i].goodsEvaluateContent+"</p><p class='evaluateList_info'><span class='evaluateList_time'>"+evaluateDetails[i].evaluateTime+"</span><span class='evaluateList_name'><img src='../img/img/evaluateList_name.png' class='tt'>"+evaluateDetails[i].goodsName+"</span></p></li>");
						//菜品评价分数
						var goodsEvaluateScore=evaluateDetails[i].goodsEvaluateScore;
						for(var j=0;j<Math.floor(goodsEvaluateScore);j++){
							if(pageCurrent==1){
								$(".evaluateList").find("li").eq(i).find("img").eq(j).attr("src","../img/img/star2.png");
							}
							else{
								$(".evaluateList").find("li").eq(pageCurrent*10+i).find("img").eq(j).attr("src","../img/img/star2.png");
							}
						}
						if(Math.floor(goodsEvaluateScore)==0){
							if(pageCurrent==1){
								$(".evaluateList").find("li").eq(i).find("img").attr("src","../img/img/star2.png");
							}
							else{
								$(".evaluateList").find("li").eq(pageCurrent*10+i).find("img").attr("src","../img/img/star2.png");
							}
						}
					}
					//过滤用户名
					var reg=/^(.).+(.)$/g;//用户名中间显示*
					for(var i=0;i<$('.userName').length;i++){
						if($('.userName').text()==!"匿名用户"){
							$('.userName').eq(i).text($('.userName').eq(i).text().replace(reg,"$1***$2"));
						}
					}
					if(pageCurrent<=pageCount){
						//$(".noEvaluate").hide();
						$(".showAll").text("查看全部评价");
						$(".showAll").removeClass("disabled");
						if(pageCurrent==pageCount){
							$(".showAll").text("没有更多评价");
							$(".showAll").addClass("disabled");
							//$(".noEvaluate").show();
						}
					}
					var pageCurrent=localStorage.getItem("pageCurrent");//当前页
					pageCurrent++;
					localStorage.setItem("pageCurrent",pageCurrent);
			}
		}
	});
}
