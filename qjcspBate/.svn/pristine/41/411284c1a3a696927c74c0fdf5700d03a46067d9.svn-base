	//checkCustomerId();
	var orderDetails=[];
	var carobj=[]; //购物车数组
	var dateobj;//时间数组，商品数组
	var goodobj;
	var _thisDateIndex=localStorage.getItem("_thisDateIndex");
	var _thisAreaIndex=localStorage.getItem("_thisAreaIndex");
	var longitude = localStorage.getItem('lng');  //longitude：经度
	var latitude =  localStorage.getItem('lat');  //latitude：纬度
	var _thisIssueId;
	//var customerId = sessionStorage.getItem('customerId');  //用户id
	var customerId = sessionStorage.getItem('2001');  //用户id
	//附近机柜
	$(document).ready(function(e) {
		$.ajax({
			async: false,
			cache: false,
			type: "POST",
			url: apiurl + "api/wechat/getPreissueHistoryMachineListWX ",
			data:$.toJSON({"longitude":longitude,"latitude":latitude,"customerId":customerId}),
			success: function(json) {
				if(localStorage.getItem("selectMachineId")==null&&localStorage.getItem("selectCompanyId")==null){
					//如果有历史机柜就显示历史机柜的第一个，如果没有历史机柜就显示附近机柜的第一个，如果两个都没有就暂无机柜
					if(json.historyMachineList.length>0){
						var history =json.historyMachineList[0].machineId;
						localStorage.setItem("selectMachineId",history);
						var companyId =json.historyMachineList[0].companyId;
						localStorage.setItem("selectCompanyId",companyId);
						areaTime();
						$(".sicann").find("span").hide();
						$("#demo_select_dummy").removeClass("disabled");
					}else if(json.nearbyMachineList.length>0){
						//var near = json.nearbyMachineList[json.nearbyMachineList.length-1].machineId;//到时候改回第一个！！！！！！
						var near = json.nearbyMachineList[0].machineId;
						localStorage.setItem("selectMachineId",near);
						var companyId =json.nearbyMachineList[0].companyId;
						localStorage.setItem("selectCompanyId",companyId);
						$(".sicann").find("span").hide();
						$("#demo_select_dummy").removeClass("disabled");
						areaTime();
					}else{
						$(".wenzi").find("em").text("该商户没有设备");
						localStorage.setItem("selectMachineId","");
						$(".sicann").find("span").show();
						$("#demo_select_dummy").addClass("disabled");
					}
					console.log("后台返回数据历史机柜："+JSON.stringify(json.historyMachineList));
					console.log("后台返回数据附近机柜："+JSON.stringify(json.nearbyMachineList));
					
				}else{
					areaTime();
				}
				if(json.historyMachineList.length>0){
					$(".fujin").css("display","block");
					for(i=0;i<json.historyMachineList.length;i++){
						var machineDistance= json.historyMachineList[i].machineDistance>999?json.historyMachineList[i].machineDistance.substring(0,json.historyMachineList[i].machineDistance.length-3)+"."+json.historyMachineList[i].machineDistance.substring(json.historyMachineList[i].machineDistance.length-3,json.historyMachineList[i].machineDistance.length)+"km":json.historyMachineList[i].machineDistance+"m";
						$(".fuj").append("<div class='sheb'><div class='sheba' lang='"+json.historyMachineList[i].companyId+"'><div class='shebaa' lang='"+json.historyMachineList[i].machineId+"'>"+json.historyMachineList[i].machineName+"</div>"+"<div class='wendu'>"+machineDistance+"</div></div>"+"<div class='shebb'>"+json.historyMachineList[i].address+"</div></div>");
					}
				}
				for(i=0;i<json.nearbyMachineList.length;i++){
					var machineDistance= json.nearbyMachineList[i].machineDistance>999?json.nearbyMachineList[i].machineDistance.substring(0,json.nearbyMachineList[i].machineDistance.length-3)+"."+json.nearbyMachineList[i].machineDistance.substring(json.nearbyMachineList[i].machineDistance.length-3,json.nearbyMachineList[i].machineDistance.length)+"km":json.nearbyMachineList[i].machineDistance+"m";
					$(".cayyy").append("<div class='sheb'><div class='sheba' lang='"+json.nearbyMachineList[i].companyId+"'><div class='shebaa' lang='"+json.nearbyMachineList[i].machineId+"'>"+json.nearbyMachineList[i].machineName+"</div>"+"<div class='wendu'>"+machineDistance+"</div></div>"+"<div class='shebb'>"+json.nearbyMachineList[i].address+"</div></div>");
				}
				if(localStorage.getItem("selectMachineName")!=null){
					$(".wenzi").find("em").text(localStorage.getItem("selectMachineName"));
				}else{
					//$(".wenzi").find("em").text($(".shebaa").eq(json.nearbyMachineList.length-1).text());//到时候改回第一个！！！！！！、
					if(json.historyMachineList.length>0||json.nearbyMachineList.length>0){
						$(".wenzi").find("em").text($(".shebaa").eq(0).text());//到时候改回第一个！！！！！！
						localStorage.setItem("takePlace",$(".shebb").eq(json.historyMachineList.length-1).text());
					}
				}
				
			}
		});
		preissueWeekWX();
		//设备切换
		$(".sheb").click(function(){
			num++;
			$(".zz").removeClass("xuanzzz");
			$(".zz").eq(0).addClass("xuanzzz");
			$(".heis").hide();
			$(".wenzi").find("em").text($(this).find(".shebaa").text());
			var machineId=$(this).find(".shebaa").attr("lang");
			localStorage.setItem("selectMachineId",machineId);
			var selectMachineName=$(this).find(".shebaa").text();
			localStorage.setItem("selectMachineName",selectMachineName);
			localStorage.setItem("takePlace",$(this).find(".shebb").text());
			/*显示餐品列表*/
			$(".heilan").show();
			$("#demo_cont_select").show();
			$("#bzk").show();
			$(".neir").show();
			$(".neiron").show();
			$(".heis").css("overflow","auto");
			/*显示餐品列表*/
			$("#demo_select").empty();
			areaTime();
			var selectdate=$(".xuanzzz").find(".p2").attr("lang");
     		var companyId=$(this).find(".sheba").attr("lang");
     		localStorage.setItem("selectCompanyId",companyId);
     		var areaModelId=localStorage.getItem("selectAreaModelId");
     		//清空页面值和购物车
     		carobj=[];
			localStorage.setItem("carobj",JSON.stringify(carobj));
			$(".scroll").empty();
			$(".gou").hide();
			$(".allCount").text("").hide();
			$(".allPrice").find("em").text("0.00");
     		weekPreissueGoodsWX(companyId,selectdate,areaModelId,machineId);
		});
		$(".heisee").click(function(){
			$(".heis").hide();
		});
	});
	
	function areaTime(){
		var selectMachineId=localStorage.getItem("selectMachineId");
		//获取模型时间
		$.ajax({
			type: "POST",
			async: false,
			url: apiurl + "api/wechat/preissueAreaModelNameWX",
			data:$.toJSON({"machineId":selectMachineId}),
			dataType:'json',
			success: function(json) {
				console.log(JSON.stringify(json.returnContent));
				if(json.returnCode=="1"){
					$("#demo_select_dummy").removeClass("disabled");
					for(i=0;i<json.returnContent.length;i++){
						if(json.returnContent[i].isDefault=='1'){
							localStorage.setItem("selectAreaModelId",json.returnContent[i].areaModelId);
							localStorage.setItem("areaID",json.returnContent[i].areaModelId);
							$("#demo_select").append("<option selected='selected' value='"+json.returnContent[i].areaModelId+"' data_index='"+json.returnContent[i].index+"'>"+json.returnContent[i].areaModelName+"(取餐时间:"+json.returnContent[i].takenStartTime.substring(0,5)+"-"+json.returnContent[i].takenEndTime.substring(0,5)+")"+"</option>");
						}else{
							$("#demo_select").append("<option value='"+json.returnContent[i].areaModelId+"' data_index='"+json.returnContent[i].index+"'>"+json.returnContent[i].areaModelName+"(取餐时间:"+json.returnContent[i].takenStartTime.substring(0,5)+"-"+json.returnContent[i].takenEndTime.substring(0,5)+")"+"</option>");
						}
					}
				}else if(json.returnCode=="2"){
					$(".sicann").find("span").show();
					$("#demo_select_dummy").addClass("disabled");
					sessionStorage.setItem('noarea',"0");
					/*alert("设备已被删除");*/
				}
			}
		});
	}
	
	function preissueWeekWX(){
		//获取预订日期
		$.ajax({
			type:"post",
			url:apiurl+"api/wechat/preissueWeekWX",
			data:$.toJSON({'companyId':localStorage.getItem("selectCompanyId")}),
			dataType:'json',
			success:function(data)
			{
				console.log("后台返回数据："+JSON.stringify(data.returnContent));
				
				var returnContent = eval(data.returnContent);
				console.log("后台返回数据1："+returnContent);
				$("#demo_select_dummy").attr("value",$("#demo_select").find("option[selected]").text());
				$("#demo_select_dummy").attr("data_id",$("#demo_select").val());
				$("#demo_select_dummy").attr("data_index",$("#demo_select").find("option[selected]").attr("data_index"));
				for(var i=0;i<returnContent[0].thisWeek.length;i++){
						$("#shijian").append("<div class='zz'><p class='p1'>"+returnContent[0].thisWeek[i].weekDate+"<img class='gou' src='../img/imagey/gouxuan.png'></p>"+"<p class='p2' lang='"+returnContent[0].thisWeek[i].date+"'>"+returnContent[0].thisWeek[i].date.substring(5,returnContent[0].thisWeek[i].date.length)+"</p></div>");
				}
				if(returnContent[1].nextWeek.length>0){
					$("#shijian").append("<div  class='nextWeek'>下周"+"</div>")
					for(var i=0;i<returnContent[1].nextWeek.length;i++){
						$("#shijian").append("<div class='zz'><p class='p1'>"+returnContent[1].nextWeek[i].weekDate+"<img class='gou' src='../img/imagey/gouxuan.png'></p>"+"<p class='p2' lang='"+returnContent[1].nextWeek[i].date+"'>"+returnContent[1].nextWeek[i].date.substring(5,returnContent[1].nextWeek[i].date.length)+"</p></div>");
					}
				}
				if(localStorage.getItem("_thisDateIndex")!=null&&localStorage.getItem("_thisAreaIndex")!==null){
					var _thisDateIndex=localStorage.getItem("_thisDateIndex");
					var _thisAreaIndex=localStorage.getItem("_thisAreaIndex");
					$(".zz").eq(_thisDateIndex).addClass("xuanzzz");
					var selectMachineId=localStorage.getItem("selectMachineId");
					//获取模型时间
					$.ajax({
						type: "POST",
						url: apiurl + "api/wechat/preissueAreaModelNameWX",
						data:$.toJSON({"machineId":selectMachineId}),
						dataType:'json',
						success: function(json) {
								$("#demo_select_dummy").attr("value","");
								$("#demo_select_dummy").val("");
								$("#demo_select").empty();
								for(i=0;i<json.returnContent.length;i++){
									$("#demo_select").append("<option value='"+json.returnContent[i].areaModelId+"' data_index='"+json.returnContent[i].index+"'>"+json.returnContent[i].areaModelName+"(取餐时间:"+json.returnContent[i].takenStartTime.substring(0,5)+"-"+json.returnContent[i].takenEndTime.substring(0,5)+")"+"</option>");
								}
								$("#demo_select").find("option").eq(_thisAreaIndex).attr("selected","selected");
								$("#demo_select_dummy").attr("value",$("#demo_select").find("option[selected]").text());
								$("#demo_select_dummy").attr("data_id",$("#demo_select").val());
								$("#demo_select_dummy").attr("data_index",$("#demo_select").find("option[selected]").attr("data_index"));
						}
					});
				}
				else{
					$(".zz").eq(0).addClass("xuanzzz");
				}
				//如果从Cabinetselection选择机柜页面进来的话就在调一次模型时间
				//多余了
				/*if(localStorage.getItem("Cabinetselection")=="Cabinetselection"){
					areaTime();
				}*/
				var nowDate=$(".xuanzzz").find(".p2").text();
				$(".bzk2").text($(".xuanzzz").find(".p1").text()+nowDate);
				var selectdate=$(".xuanzzz").find(".p2").attr("lang");
	     		localStorage.setItem("selectdate",selectdate);
	     		var companyId=localStorage.getItem("selectCompanyId");
	     		//var machineId=localStorage.getItem("machineId");
	     		//var areaModelId=localStorage.getItem("areaModelId");
	     		var customerId= sessionStorage.getItem('customerId');
	     		var areaModelId=localStorage.getItem("selectAreaModelId");
	     		var machineId=localStorage.getItem("selectMachineId");
	     		weekPreissueGoodsWX(companyId,selectdate,areaModelId,machineId);
			},
			error:function(data)
			{
				console.log('失败原因:'+ JSON.stringify(data.returnContent));
			}
		});
	}
	 //生成菜品
     function weekPreissueGoodsWX(companyId,selectdate,areaModelId,machineId){
    	var nowareaModelName= $("#demo_select").find("option[value='"+areaModelId+"']").text();
    	var nowareaModelId= $("#demo_select").find("option[value='"+areaModelId+"']").attr("value");
    	var data_index=$("#demo_select").find("option[value='"+areaModelId+"']").attr("data_index");
    	$("#demo_select_dummy").val(nowareaModelName);
    	$("#demo_select_dummy").attr("value",nowareaModelName);
		$("#demo_select_dummy").attr("data_id",nowareaModelId);
		$("#demo_select_dummy").attr("data_index",data_index);
		localStorage.setItem("_thisAreaIndex",data_index);
		if(companyId!=null&&areaModelId!=null&&machineId!=null&&machineId.length>0&&areaModelId!=null){
 		$.ajax({
 		type:"post",
 		url:apiurl+"api/wechat/weekPreissueGoodsWX",
 		data:$.toJSON({'companyId':companyId,'selectDate':selectdate,'areaModelId':areaModelId,'machineId':machineId}),
 		dataType:'json',
 		success:function(data)
 		{
 			$(".sicann").find("span").hide();
 			console.log("后台返回数据："+JSON.stringify(data.returnContent));
 			if(data.returnContent!="已经过了预定时间！"){
 				$(".neir-y").removeClass("neir-fixed");
 				$(".noFoods").hide();
 				$(".tabaa").empty();
	 			$(".bzk3").remove();
	 			$(".bzk2").show();
	 			if(data.returnContent=="该设备未关联商户！"){
	 				$(".nodivece").fadeIn(500);
 					setTimeout(function () {
 						$(".nodivece").fadeOut(500);
 				    },1000);
 					$(".neir-y").addClass("neir-fixed");
 					$(".sicann").find("span").show();
 					$("#demo_select_dummy").addClass("disabled");
 					$(".noFoods").show();
	 			}
	 			else if(data.returnContent.issueList==undefined){
	 				$(".neir-y").addClass("neir-fixed");
 	 				$(".noFoods").show();
 	 			}
	 			else if(data.returnContent.issueList.length==0){
	 				$(".neir-y").addClass("neir-fixed");
 	 				$(".noFoods").show();
 	 				$(".bzk3").show();
 	 				if(sessionStorage.getItem('noarea')=="0"){
 	 					$(".sicann").find("span").show();
 	 					$("#demo_select_dummy").addClass("disabled");
 	 				}
 	 			}
 				else{
 					var returnContent = eval(data.returnContent);
 					$(".neir-y").removeClass("neir-fixed");
 					for(var i=0;i<returnContent.issueList.length;i++){
 		 				$(".tabaa").append("<div class='zhuang' lang="+returnContent.issueList[i].goodsId+" data_issueId="+returnContent.issueList[i].issueId+"><div class='zhuang_z'><div class='zhuang_t'><img src='"+apiurl+""+returnContent.issueList[i].goodsIcon+"' class='goodsIcon'></div></div>"+
 		 								"<div class='zhuang_y'><div class='zhuang_zz'>"+returnContent.issueList[i].shortGoodsName+"</div><div class='zhuang_xx'>"+
 		 								"<div class='xing'><img src='../img/img/star1.png'></div><div class='xing'><img src='../img/img/star1.png'></div><div class='xing'>"+
 		 								"<img src='../img/img/star1.png'></div><div class='xing'><img src='../img/img/star1.png'></div><div class='xing'><img src='../img/img/star1.png'></div></div>"+
 		 								"<div class='zhuang_jg'><div class='zizi'><div class='ziziz'>"+returnContent.issueList[i].storeName+"</div>"+" <div class='zhuang_jg_zt'>￥<em>"+returnContent.issueList[i].retailPrice+"</em></div></div><div class='zhuang_anniu'><div  class='jianhao'><img src='../img/imagey/minus_red.png' class='min'></div>"+
 		 								"<div  class='zi'><em class='buyCount'>0</em></div><div id='a' class='jiahao'><img src='../img/imagey/plus_red.png' class='add'></div></div></div></div></div>");
 		 			}
 		 			$(".bzk").append("<p class='bzk3'>"+"可预订份数:<em>"+returnContent.surplusNum+"</em>份"+"</p>");
 		 			//评价分数
 		 			for(var i=0;i<returnContent.issueList.length;i++){
 		 				for(var j=0;j<Math.floor(returnContent.issueList[i].goodsGrade);j++){
 		 					$(".zhuang").eq(i).find(".xing").eq(j).find("img").attr("src","../img/img/star2.png");
 		 				}
 		 				if(Math.floor(returnContent.issueList[i].goodsGrade)==0){
 		 					$(".zhuang").eq(i).find(".xing").find("img").attr("src","../img/img/star2.png");
 		 				}
 		 			}
 		 			
 		 				
 		 			
 		 			$(".zhuang").find(".buyCount").hide();
 		 			$(".min").hide();
 				}
	 			//根据购物素组改变当前页菜品状态
	 			var selectdate=$(".xuanzzz").find(".p2").attr("lang");
	 			var nowDate=$(".xuanzzz").find(".p2").text();
	 			var selectTime=$("#demo_select_dummy").attr("value");
		 		var carobj=eval(localStorage.getItem("carobj",JSON.stringify(carobj)));
		 		if(carobj!=null&&carobj.length>0){//如果购物车内有当前日期页的菜品
		 			for(var i=0;i<carobj.length;i++){
		 				//页面数量显示
	 		 			if(nowDate==carobj[i].date && selectTime==carobj[i].time){//找到购物车内对应的菜品
	 		 				for(var k=0;k<carobj[i].orderDetails.length;k++){
	 	 		 				for(var j=0;j<$(".zhuang").length;j++){
	 	 		 					if($(".zhuang").eq(j).attr("lang")==carobj[i].orderDetails[k].goodsId){//改变对应的菜品的分数;
	 	 		 						$(".zhuang").eq(j).find(".buyCount").text(carobj[i].orderDetails[k].goodsNum);
	 	 		 						if(carobj[i].orderDetails[k].goodsId>0){
	 	 		 							$(".zhuang").eq(j).find(".min").show();
	 	 		 							$(".zhuang").eq(j).find(".buyCount").show();
	 	 		 						}
	 	 		 					}
	 	 		 				}
	 		 				}
	 		 			}
	 		 		}
	 		 		//购物车数量
		 		 	newCarList(carobj,nowDate,selectTime);
		 		}
		 		//如果页面进来发现已经达到可售份数那么按钮变灰
		 		var zhuangCountNum=0;
		 		$(".zhuang").each(function(){
		 			zhuangCountNum+=parseInt($(this).find(".buyCount").text());
		 		})
		 		if(zhuangCountNum==parseInt($(".bzk3").find("em").text())){
		 			$(".zhuang").find(".add").attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
		 		}
 	 			
				
 			}else{
 				$(".tabaa").empty();
	 			$(".bzk3").remove();
	 			$(".bzk2").hide();
	 			$(".noFoods").show();
	 			if($("#demo_select").find("option").length<=0){
	 				$(".sicann").find("span").show();
					$("#demo_select_dummy").addClass("disabled");
	 			}
	 			$(".neir-y").addClass("neir-fixed");
	 			$(".overTime").text(data.returnContent).fadeIn(500);
				setTimeout(function () {
					$(".overTime").fadeOut(500);
			    },1000);
	 			//根据购物素组改变当前页菜品状态
	 			var selectdate=$(".xuanzzz").find(".p2").attr("lang");
	 			var nowDate=$(".xuanzzz").find(".p2").text();
	 			var selectTime=$("#demo_select_dummy").attr("value");
 		 		var carobj=eval(localStorage.getItem("carobj",JSON.stringify(carobj)));
 		 		if(carobj!=null&&carobj.length>0){//如果购物车内有当前日期页的菜品
 		 			newCarList(carobj,nowDate,selectTime);
 		 		}
 			}
 			//订单加
 			$(".zhuang .add").on('click',function(e){
 				var _this = $(this);
 				var elem_num = parseInt($(this).parents(".zhuang").find(".buyCount").text()) + 1;//当前份数
 				if(elem_num>0){
 					$(this).parents(".zhuang").find(".min").show();
 					$(this).parents(".zhuang").find(".buyCount").show();
 				}
 				addMinOrder(_this,elem_num);
 				showOrhide();
 				$(".xuanzzz").find(".gou").show();
 				 e.stopPropagation();//阻止冒泡
 			});
 			//订单减
 			$(".zhuang .min").on('click',function(e){
 				var _this = $(this);
 				var elem_num = parseInt($(this).parents(".zhuang").find(".buyCount").text())-1;//当前份数
 				if(elem_num==0){
 					$(this).parents(".zhuang").find(".min").hide();
 					$(this).parents(".zhuang").find(".buyCount").hide();
 				}
 				if(elem_num>=0){
 					addMinOrder(_this,elem_num);
 				}
 				showOrhide();    
 				e.stopPropagation();//阻止冒泡
 			});
 			//购物车加
 			$(".car_add").die().live('click',function(){
 				var _this = $(this);
 				if($(".buyCount").text()==0){
 					$(".buyCount").text("0");
 				}
 				var elem_num = parseInt($(this).parent().find('.buyCount').text()) + 1;
 				addMinCar(_this,elem_num);
 				showOrhide();
 			});
 			//购物车减
 			$(".car_min").die().live('click',function(){
 				var _this = $(this);
 				if($(".buyCount").text()==0){
 					$(".buyCount").text("0");
 				}
 				var elem_num = parseInt($(this).parent().find('.buyCount').text()) - 1;
 				if(elem_num>=0){
 					addMinCar(_this,elem_num);
 				}
 				showOrhide();
 			});
 			//点击菜品,跳转详情页
 			$(".zhuang").click(function(){
 				var goodsIcon=$(this).find(".goodsIcon").attr("src");
 				localStorage.setItem("goodsIcon",goodsIcon);//菜品图片
 				var goodsId=$(this).attr("lang");
 				localStorage.setItem("goodsId",goodsId);//菜品id
 				var orderType=0;
 				localStorage.setItem("orderType",orderType);//跳转类型
 				var elem_num=parseInt($(this).find(".buyCount").text());
 				localStorage.setItem("elem_num",elem_num);//菜品份数
 				var  _thisAreaIndex=$("#demo_select_dummy").attr("data_index");//当前模型的index
 				localStorage.setItem("_thisAreaIndex",_thisAreaIndex);
 				var _thisTime=$("#demo_select_dummy").attr("value");//当前菜品预订时间
 				localStorage.setItem("_thisTime",_thisTime);
 				var _thisDate=$(".xuanzzz").find(".p2").text();//当前菜品日期
 				localStorage.setItem("_thisDate",_thisDate);
 				var _thisAllDate=$(".xuanzzz").find(".p2").attr("lang");//当前菜品完整日期
 				localStorage.setItem("_thisAllDate",_thisAllDate);
 				var _thisDateIndex=$(".xuanzzz").index(".zz");//当前日期的index
 				localStorage.setItem("_thisDateIndex",_thisDateIndex);
 				var allSurplus=parseInt($(".bzk3").find("em").text());
 				localStorage.setItem("selectAreaModelId",$("#demo_select_dummy").attr("data_id"));
 				var goodsAllCount=0;
 				$(".zhuang").each(function(){
 					goodsAllCount+=parseInt($(this).find(".buyCount").text());
 				});
 				var goodsSurplus=allSurplus-goodsAllCount;
 				localStorage.setItem("goodsSurplus",goodsSurplus);//当前页剩余份数
 				localStorage.setItem("allSurplus",allSurplus);//当前页剩余份数
 				var _thisIssueId=$(this).attr("data_issueId");
 				localStorage.setItem("_thisIssueId",_thisIssueId);//当前菜品的发布ID
 				window.location.href="goodsEvaluateIndex.html";
 			});
 			
 		},
 		error:function(data)
 		{
 			console.log('失败原因:'+ JSON.stringify(data.returnContent));
 		}
 	});
	}else{
		$(".noFoods").show();
		$(".neir-y").addClass("neir-fixed");
	}
   }   

//切换日期
$(".zz").die().live("click",function(){
	var index = $(this).index(".zz");
	localStorage.setItem("zzIndex",index);
	$(".zz").removeClass("xuanzzz");
	$(".zz").eq(index).addClass("xuanzzz");
	var selectdate=$(this).find(".p2").attr("lang");
	var nowDate=$(this).find(".p2").text();
	localStorage.setItem("selectdate",selectdate);
	var companyId=localStorage.getItem("selectCompanyId");
	var customerId= sessionStorage.getItem('customerId');
	var areaModelId=localStorage.getItem("selectAreaModelId");
	var machineId=localStorage.getItem("selectMachineId");
	$(".bzk2").text($(this).find(".p1").text()+nowDate);
	weekPreissueGoodsWX(companyId,selectdate,areaModelId,machineId);
});

$('.heiyan_y').click(function(){
	checkCustomerId();
	var customerId= sessionStorage.getItem('customerId');
	//var customerId="2001";
	if(customerId!=null && customerId.length>0){
		var allCount = $(".allPrice em").text();  //allCount:总价格
		var goodsList = [];
		$('.scroll li').each(function(){
			var goodsName = $(this).find('.dishName').text();  //goodsName:菜品名称
			var goodsPrice = $(this).find(".dishPrice").find("em").text();  //goodsPrice:菜品单价
			var goodsCount = $(this).find(".buyCount").text();   //goodsCount:下单份数
			var goodsId = $(this).attr("lang");
			var arrays = {"goodsName":goodsName,"goodsPrice":goodsPrice,"goodsCount":goodsCount,"goodsId":goodsId};
			goodsList.push(arrays);  //goodsList:商品列表
			var returnContent = {"allCount":allCount,"goodsList":goodsList}
			var data = JSON.stringify(returnContent);
			localStorage.setItem("data",data);
			console.log(data);
		})
		localStorage.setItem("orderType",0);
		window.location.href = "../view/orderRetail.html";
	}
});




/*---------------------------------------------------------------------------------------购物车----------------------------------------------------------------------------------*/
//购物车
$(".heiyan_z").click(function(e){
	e.stopPropagation()
	$("body").css("overflow-y","hidden");
//	$(".neir-y").css("overflow-y","hidden")
	if($('.car').parents('.dialog').hasClass("showCar"))
	{
		$('.car').parents('.dialog').removeClass("showCar");
		$("body").css("overflow-y","auto");
	}
	else
	{
		if(parseFloat($('.allPrice em').text())> 0)
		{
			$('.car').parents('.dialog').addClass("showCar");
			//$("body").css("overflow-y");
		}
	}
});

//清空购物车
$('.delete').click(function(){
	//重组数组
	carobj=[];
	localStorage.setItem("carobj",JSON.stringify(carobj));
	$(".balance").hide();
	$(".go").show();
	window.location.reload();
});

//订单加减
function addMinOrder(_this,elem_num){
	_this.parents(".zhuang").find(".buyCount").text(elem_num);//改变当前份数
	var _thisIssueId=_this.parents(".zhuang").attr("data_issueId");//当前菜品的发布ID
	var _thisGoodsId=_this.parents(".zhuang").attr("lang");//当前菜品的GoodsId
	var _thisTime=$("#demo_select_dummy").attr("value");//当前菜品预订时间
	var _thisDate=$(".xuanzzz").find(".p2").text();//当前菜品日期
	var _thisAllDate=$(".xuanzzz").find(".p2").attr("lang");//当前菜品日期
	var _thisDateIndex=$(".xuanzzz").index(".zz");//当前日期的index
	var _thisAreaIndex=$("#demo_select_dummy").attr("data_index");//当前模型的index
	var _thisGoodsName=_this.parents(".zhuang").find(".zhuang_zz").text();//当前菜品名称
	var _thisGoodsPrice=_this.parents(".zhuang").find(".zhuang_jg_zt em").text();//当前菜品单价
	var _thisBuyCount=_this.parents(".zhuang").find(".buyCount").text();//当前份数
	var nowSelectAreaModelId=$("#demo_select_dummy").attr("data_id");//当前菜品的AreaModelId
	carList(_this,_thisGoodsId,_thisTime,_thisDate,_thisGoodsName,_thisGoodsPrice,_thisBuyCount,nowSelectAreaModelId,_thisDateIndex,_thisAreaIndex,_thisAllDate);
	//订单总数
	var sumNumber = totalCount($('.scroll li'));
	$('.allCount').text(sumNumber);
	if(sumNumber>0){
		$('.allCount').show();
	}else{
		$('.allCount').hide();
	}
	//价格总数
	var sumPrice = totalPrice($('.scroll li'));
	$('.allPrice').find("em").text(sumPrice.toFixed(2));
	//存储数组
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
		dateobj={"date":date,"AllDate":AllDate,"time":time,"takenStartTime":takenStartTime,"takenEndTime":takenEndTime,"dateIndex":DateIndex,"areaIndex":AreaIndex,"areaModelId":areaModelId,"orderDetails":orderDetails};
		carobj.push(dateobj);
	}
	localStorage.setItem("carobj",JSON.stringify(carobj));
	console.log("carobj111"+JSON.stringify(carobj));
}

//购物车加减
function addMinCar(_this,elem_num){
	checkNum(_this.parents("ul"),_this);//是否大于剩余份数
	_this.parents("li").find(".buyCount").text(elem_num);
	var _thisgoodsId=_this.parents("li").attr("lang");//当前的菜品goodsId
	var _thisAreaModelId=_this.parents("ul").attr("lang");//当前菜品的areaModelId
	var _thisDate=_this.parents("ul").find(".date").text();//当前的菜品对应日期
	var _thisTime=_this.parents("ul").find(".time").text();//当前的菜品对应模型时间
	var zzDate=$(".xuanzzz").find(".p2").text();//当前页的日期
	var areaModelId=localStorage.getItem("selectAreaModelId");//当前页的areaModelId
	var carobj=localStorage.getItem("carobj");
	//如果购物车加减的菜品是当前显示页的菜品那么当前页的对应菜品份数变化
	$(".zhuang").each(function(){
		if($(this).attr("lang")==_thisgoodsId && zzDate==_thisDate && _thisAreaModelId==areaModelId){
			$(this).find(".buyCount").text(elem_num);
		    _thisIssueId=$(this).attr("data_issueId");//当前菜品的发布ID
			if(elem_num<=0){
				$(this).find(".buyCount").hide();
				$(this).find(".min").hide();
			}
		}
	});
	//如果份数减到零份
	if(elem_num<=0){
		if(_this.parents("ul").find("li").length<=1){
			$(".zz").each(function(){
				if($(this).find(".p2").text()==_thisDate){
					$(this).find(".gou").hide();
				}
			});
			_this.parents("ul").remove();
		}else{
			_this.parents("li").remove();
		}
	}
	
	//重组数组
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
	console.log("carobj222"+JSON.stringify(carobj));
	//订单总数
	var sumNumber = totalCount($('.scroll li'));
	$('.allCount').text(sumNumber);
	if(sumNumber>0){
		$('.allCount').show();
	}else{
		$('.allCount').hide();
	}
	//价格总数
	var sumPrice = totalPrice($('.scroll li'));
	$('.allPrice').find("em").text(sumPrice.toFixed(2));
}

//生成购物车列表
function carList(_this,_thisGoodsId,_thisTime,_thisDate,_thisGoodsName,_thisGoodsPrice,_thisBuyCount,nowSelectAreaModelId,_thisDateIndex,_thisAreaIndex,_thisAllDate){
		console.log(nowSelectAreaModelId);
		var flag=true;
		var flag2=true;
		var AddMin=_this.attr('class');
		if(AddMin=="add"){
			AddMin="car_add";
		}else{
			AddMin="car_min";
		}
		var ulStr="<ul lang="+nowSelectAreaModelId+" date_areaIndex="+_thisAreaIndex+"><div class='reserve'><em class='date' date_index="+_thisDateIndex+" date_day="+_thisAllDate+">"+_thisDate+"</em><em class='time'>"+_thisTime+"</em></div><li lang="+_thisGoodsId+"><span class='dishName'>"+_thisGoodsName+"</span><span class='dishPrice'>&#165;<em>"+_thisGoodsPrice+"</em></span><div class='AddMinBox'><img src='../img/img_reserve/reduce.png' class='car_min'><span class='buyCount'>"+_thisBuyCount+"</span><img src='../img/img_reserve/add-to.png' class='car_add'></div></li></ul>";
		if($( ".scroll:has(ul)").length>0){
			$(".scroll ul").each(function(){
				var $this=$(this);
				if($this.attr("lang")==nowSelectAreaModelId&&$this.find(".date").text()==_thisDate){
					for(var i=0;i<$this.find("li").length;i++){
						if($this.find("li").eq(i).attr("lang")==_thisGoodsId){
							$this.find("li").eq(i).find(".buyCount").text(_thisBuyCount);//改变对应菜品份数
							checkNum($this,$this.find("li").eq(i).find("."+AddMin));//是否大于可售份数
							//如果是减到最后一份，那么移除购物车中的这份菜品
							if(_thisBuyCount<=0){
								$this.find("li").eq(i).remove();
								//如果购物车中对应日期中没有任何菜品了，就移除购物车中的日期对应的列表
								if($this.find("li").length<=0){
									$this.remove();
									//改变左侧预订日期的 
									$('.zz').each(function(){
										if($(this).find(".p2").text()==_thisDate){
											$(this).find(".gou").hide();
										}
									});
								}
							}
							flag2=false;
						}
					}
					flag=false;
					if(flag2){
						$this.append("<li lang="+_thisGoodsId+"><span class='dishName'>"+_thisGoodsName+"</span><span class='dishPrice'>&#165;<em>"+_thisGoodsPrice+"</em></span><div class='AddMinBox'><img src='../img/img_reserve/reduce.png' class='car_min'><span class='buyCount'>"+_thisBuyCount+"</span><img src='../img/img_reserve/add-to.png' class='car_add'></div></li>");
						checkNum($this,$this.children().last().find(".car_add"));//是否大于可售份数
					}
				}
			});
			if(flag){
				//购物车日期排序
				var nowdate=_thisDateIndex;//当前要添加的日期
				var nowarea=_thisAreaIndex;//当前要添加的模型
				var scroll_ul=$(".scroll").find("ul");
				var insertIndex=scroll_ul.length;
				for(var i=0;i<scroll_ul.length;i++){
					var _thisULDate=scroll_ul.eq(i);
					var trueorfalse=i>0?nowdate>scroll_ul.eq(i-1).find(".date").attr("date_index"):true;
					var turefalse=nowdate<_thisULDate.find(".date").attr("date_index");
					if(trueorfalse&&turefalse){
						//如果要添加的日期大于当前比对的日期，那么在当前比对日期前添加
						insertIndex=i;
						break;
					}else if(nowdate==_thisULDate.find(".date").attr("date_index")){
						//如果要添加的日期和当前比对的日期相等，那么再比对模型顺序
						var areaflag=(i+1)<insertIndex?nowdate==scroll_ul.eq(i+1).find(".date").attr("date_index"):false;
						var areaflag2=nowarea<_thisULDate.attr("date_areaIndex");
						if(areaflag2){
							//如果要添加的模型index大于当前比对的模型index
							insertIndex=i;
							break;
						}else if(!areaflag){
							//不然就在当前对比车列表后面追加一条
							insertIndex=i+1;
							break;
						}else{
							continue;
						}
					}
				}
				if(insertIndex==scroll_ul.length){
					$(".scroll").append(ulStr);
					checkNum($(".scroll").find("ul").last(),$(".scroll").find("ul").last().find("li").find(".car_add"));//是否大于可售份数
				}else{
					$(".scroll").find("ul").eq(insertIndex).before(ulStr);
					checkNum($(".scroll").find("ul").eq(insertIndex),$(".scroll").find("ul").eq(insertIndex).find("li").find(".car_add"));//是否大于可售份数
				}
			}
		}else{
			console.log("date_areaIndex"+_thisAreaIndex+",date_index"+_thisDateIndex);
			$(".scroll").append(ulStr);
			checkNum($(".scroll").find("ul").last(),$(".scroll").find("ul").last().find("li").find(".car_add"));//是否大于可售份数
		}
}

//重新进页面时生成的购物车
function newCarList(carobj1,nowDate,selectTime){
	console.log("重新进页面时生成的购物车"+JSON.stringify(carobj1));
	$(".scroll").empty();
	for(var i=0;i<carobj1.length;i++){
		if(selectTime==carobj1[i].time){//找到购物车内对应的菜品
			for(var j=0;j<$('.zz').length;j++){
					if($('.zz').eq(j).find(".p2").text()==carobj1[i].date){
						$('.zz').eq(j).find(".p1").find("img").show();
					}
				}
		}
		console.log("areaIndex"+carobj1[i].areaIndex+",date_index"+carobj1[i].dateIndex);
		$(".scroll").append("<ul lang="+carobj1[i].areaModelId+" date_areaIndex="+carobj1[i].areaIndex+"><div class='reserve'><em class='date' date_index="+carobj1[i].dateIndex+" date_day="+carobj1[i].AllDate+">"+carobj1[i].date+"</em><em class='time'>"+carobj1[i].time+"</em></div></ul>");
	}
	for(var i=0;i<carobj1.length;i++){
		for(var j=0;j<carobj1[i].orderDetails.length;j++){
			if($(".scroll").find("ul").eq(i).attr("lang")==carobj1[i].areaModelId){
				$(".scroll").find("ul").eq(i).append("<li lang="+carobj1[i].orderDetails[j].goodsId+"><span class='dishName'>"+carobj1[i].orderDetails[j].cargoodsName+"</span><span class='dishPrice'>&#165;<em>"+carobj1[i].orderDetails[j].retailPrice+"</em></span><div class='AddMinBox'><img src='../img/img_reserve/reduce.png' class='car_min'><span class='buyCount'>"+carobj1[i].orderDetails[j].goodsNum+"</span><img src='../img/img_reserve/add-to.png' class='car_add'></div></li>");
			}
		}
	}
	//该日期列表内的份数是否在可预订份数内
	$(".scroll").find("ul").each(function(){
		var nowul=$(this);
		var ulGoodsNum=0;
		for(var i=0;i<nowul.find("li").length;i++){
			ulGoodsNum+=parseInt(nowul.find("li").eq(i).find(".buyCount").text());
		}
		if(ulGoodsNum>=parseInt($(".bzk3").find("em").text())){
			nowul.find("li").find(".car_add").attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
		}
	});
	//订单总数
	var sumNumber = totalCount($('.scroll li'));
	$('.allCount').text(sumNumber);
	if(sumNumber>0){
		$('.allCount').show();
	}else{
		$('.allCount').hide();
	}
	//价格总数
	var sumPrice = totalPrice($('.scroll li'));
	$('.allPrice').find("em").text(sumPrice.toFixed(2));
	showOrhide();
}

//剩余份数
function checkNum(nowul,_this){
	var nowCheckDate=$(".xuanzzz").find(".p2").text();//当前显示的是哪个日期的菜品
	var chekDate=nowul.find(".date").text();//当前要减的是哪一天的
	var countNum=0;
	nowul.find("li").each(function(){
		countNum+=parseInt($(this).find(".buyCount").text());
	});
	if(_this.attr('class')=="car_add"||_this.attr('class')=="add"){
		countNum+=1;
		if(countNum<parseInt($(".bzk3").find("em").text())){
			$('.add').attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
			$('.zhuang').each(function(){
				if(nowCheckDate==chekDate){
					$(this).find(".add").attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
				}
			})
			return true;
		}else{
			nowul.find(".car_add").attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
			$('.zhuang').each(function(){
				if(nowCheckDate==chekDate){
					$(this).find(".add").attr("src","../img/img_reserve/add-to1.png").addClass("disabled");
				}
			})
			return false;
		} 
	}
	if(_this.attr('class')=="car_min"||_this.attr('class')=="min")
	{
		countNum-1;
		nowul.find(".car_add").attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
		$('.zhuang').each(function(v,k){
			if(nowCheckDate==chekDate){
				$(k).find(".add").attr("src","../img/img_reserve/add-to.png").removeClass("disabled");
			}
		})
	}
	
}

//预订订单加减
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

//显示隐藏下单总份数和去结算
function showOrhide()
{
	if($('.allCount').text()>0)
	{
		$('.heiyan_y').show();
		$('.heiyan_ya').hide();
	}
	else
	{
		$(".allCount").find("em").text("0.00");
		$('.heiyan_y').hide();
		$('.heiyan_ya').show();
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

//生成购物车列表数组
function carobjArray(){
	
}