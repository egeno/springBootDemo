$(document).ready(function(){
	localStorage.removeItem("newGoodsList");
	//checkOpenId();
	//if(sessionStorage.getItem('openid')!=null&& localStorage.getItem("openid")!=""){
		//map();
	//}
	//重组数组
	carobj=[];
	localStorage.setItem("carobj",JSON.stringify(carobj));
	//重组数组
	newGoodsList=[];
	localStorage.setItem("newGoodsList",JSON.stringify(newGoodsList));
	localStorage.removeItem("selectMachineId");
	localStorage.removeItem("selectCompanyId");
	localStorage.removeItem("selectMachineName");
	localStorage.removeItem("selectCompanyId");
	//头部地址的显示与隐藏
	var orderType=1;//1表示零售
	localStorage.setItem("orderType",orderType);
	$(window).scroll(function(){
		if($(this).scrollTop() > 2)
		{
			$('.topTitle').css({'display':'block',"z-index":'9999'});
		}
		else
		{
			$('.topTitle').css({'display':'none',"z-index":'9999'});
		}
	})
	/*接口获取banner图片地址*/
	//var locationName = '西湖';
	var url = apiurl + 'api/wechat/getAppAdPicturesWX';
	console.log("URL："+url);
	var jsonData = {"adPicType":1};
	$.ajax({
		type:"post",
		url:url,
		data:$.toJSON(jsonData),
		dataType:'json',
		success:function(data)
		{
			console.log("后台返回数据："+JSON.stringify(data.returnContent));
			var img=data.returnContent;
			$.each(img,function(v,k){
				var k=k.adPicUrl;
				$(".main_image").find("li").eq(v).find("img").attr("src",apiurl+k);
			});
		},error:function(data){
			console.log("后台返回数据："+JSON.stringify(data.returnContent));
		}
	});
	
	/*接口获取banner图片地址*/
	/*判断图片地址是否为空*/
	/*banner*/
	var bannerimg = $(".main_image").find("li").find("img");
	$.each(bannerimg,function(v,k){
		if($(k).attr("src")==""){
			$(k).attr("src","../img/image/net_err_banner.png");
		}
	})
	/*banner*/
	/*判断图片地址是否为空*/
	
	if(localStorage.getItem("companyId")!=null && localStorage.getItem("companyId")!=""){
		getMachinesByGPSLocation(localStorage.getItem("companyId"));
		localStorage.setItem("companyId","");
	}else{
		getMachinesByGPSLocation("");
	}
	//getMachinesByGPSLocation("");
	//getCompanyListByLocation();
	click();
	//点击进去商品详情页
	$(".index_cabinet li").click(function(){
		var machineId = $(this).attr("name");
		localStorage.setItem("machineId",machineId);   //machineId:设备Id
		var takePlace = $(this).find(".palce").attr("lang");  
		localStorage.setItem("takePlace",takePlace);   //takePlace:取餐地址
		var distance = $(this).find('.distance i').first().attr('juli');
		localStorage.setItem("distance",distance);//距离
		var grade = $(this).find(".stars").html();
		localStorage.setItem("grade",grade);
		var surplus=$(this).find(".surplus").find("i:first").text();
		localStorage.setItem("surplus",surplus);
		window.location.href = 'order.html';
	});
});
//根据地名获取商户列表
function getCompanyListByLocation()
{
	var district = localStorage.getItem('district');
	//var locationName = '西湖';
	var url = apiurl + 'api/wechat/getCompanyListByLocationWX';
	console.log("URL："+url);
	var jsonData = {"locationName":district};
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(jsonData),
		dataType:'json',
		success:function(data)
		{
			console.log("后台返回数据："+JSON.stringify(data.returnContent));
			if(data.returnCode=="1"){
				var returnContent = eval(data.returnContent);
				var companyId;
				for(var i=0;i<returnContent.length;i++)
				{
					companyId =returnContent[i].companyId;  //companyId：商户id;
					console.log(companyId);
				}
				getMachinesByGPSLocation(companyId);
			}
		},
		error:function(data)
		{
			console.log('失败原因:'+ JSON.stringify(data.returnContent));
		}
	});
}
//根据定位罗列设备列表
function getMachinesByGPSLocation(companyId)
{
	var companyId = localStorage.getItem('companyId');  //companyId:商户Id
	var longitude = localStorage.getItem('lng');  //longitude：经度
	var latitude =  localStorage.getItem('lat');  //latitude：纬度
	var url = apiurl + 'api/wechat/getMachinesByGPSLocationWX'; 
	console.log("URL："+url); 
	if(companyId==null||companyId==""||companyId=="undefined"||companyId==undefined){
		companyId="";
	}
	
	var jsonData = {"companyId":companyId,"longitude":longitude,"latitude":latitude};
	//var jsonData = {"companyId":companyId,'longitude':'120.126465','latitude':'30.287865'}
	console.log("传给后台的数据：" + JSON.stringify(jsonData));
	$.ajax({
		type:"post",
		url:url,
		async:false,
		data:$.toJSON(jsonData),
		dataType:'json',
		success:function(data)
		{
			console.log("index后台返回数据："+JSON.stringify(data.returnContent));
			var arr = eval(data.returnContent);
			for(var i=0;i<arr.length;i++)
			{
				if(arr[i].distance>=1000){
					var distance = (arr[i].distance/1000).toFixed(2); 
					$('.index_cabinet ul').append("<li name="+ arr[i].machineId +"><div class='cabinet_left'><img src="+ apiurl+arr[i].machineIcon +" /></div><div class='cabinet_right'><h3>"+ arr[i].machineName +"</h3><span class='index_info'><span class='stars' lang="+ arr[i].grade +"></span><span class='sales'>月售<i>" + arr[i].monthSaleAmount + "</i></span><span class='palce' lang='"+arr[i].address+"'>" + arr[i].companyName + "</span><span class='surplus'><img src='../img/img/surplus.png' /><i>"+ arr[i].effectCapacity +"</i><i>份</i></span><span class='distance'><img src='../img/img/distance.png' /><i juli='"+arr[i].distance+"'>"+ distance +"</i><i>km</i></span></span></div></li>")
				}else{
					$('.index_cabinet ul').append("<li name="+ arr[i].machineId +"><div class='cabinet_left'><img src="+ apiurl+arr[i].machineIcon +" /></div><div class='cabinet_right'><h3>"+ arr[i].machineName +"</h3><span class='index_info'><span class='stars' lang="+ arr[i].grade +"></span><span class='sales'>月售<i>" + arr[i].monthSaleAmount + "</i></span><span class='palce' lang='"+arr[i].address+"'>" + arr[i].companyName + "</span><span class='surplus'><img src='../img/img/surplus.png' /><i>"+ arr[i].effectCapacity +"</i><i>份</i></span><span class='distance'><img src='../img/img/distance.png' /><i juli='"+arr[i].distance+"'>"+ arr[i].distance +"</i><i>m</i></span></span></div></li>")
				}
				
				var grade = $('.stars').eq(0).attr('lang');   //grade:评分数值
				for(var j=0;j<grade.length;j++)
				{
					//根据分值添加星星个数
					if(grade == 0)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star1.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' />")
					}
					else if(grade == 0.5)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star3.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' />")
					}
					else if(grade == 1)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star2.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' />")
					}
					else if(grade == 1.5)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star2.png' /><img src='../img/img/star3.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' />")
					}
					else if(grade == 2)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' />")
					}
					else if(grade == 2.5)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star3.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' />")
					}
					else if(grade == 3)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star1.png' /><img src='../img/img/star1.png' />")
					}
					else if(grade == 3.5)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star3.png' /><img src='../img/img/star1.png' />")
					}
					else if(grade == 4)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star1.png' />")
					}
					else if(grade == 4.5)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star3.png' />")
					}
					else if(grade == 5)
					{
						$('.index_cabinet li').eq(i).find('.stars').append("<img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star2.png' /><img src='../img/img/star2.png' />")
					}
				}
			}
			
			
		},
		error:function(data)
		{
			console.log('失败原因:' + JSON.stringify(data.returnContent));
		}
	});
	/*机柜*/
	var jiguiimg=$(".cabinet_left").find("img");
	$.each(jiguiimg,function(v,k){
		if($(k).attr("src")==apiurl){
			$(k).attr("src","../img/image/moren.png");
		}
	})
	/*机柜*/
}

//居中


/*function setDiv_Center(obj){
    var left=($(window).width()-obj.width())/2-11.719;
    obj.offset({left:left});
}*/


/*文字居中*/
/*function center()
{
	var width = ($(".indexTitle").width()+23.438)/2;
	console.log("width::::::"+width);
	$(".indexTitle").css({"margin-left":-(width/60)+"rem"});
}*/
function click()
{
	$(".indexNav ul li").eq(0).click(function(){
		window.location.href = '../view/Nooutput.html';
	})
	$(".indexNav ul li").eq(1).click(function(){
		localStorage.setItem("pick_index","0");
		window.location.href = '../view/Ordertab.html';
	})
	$(".indexNav ul li").eq(2).click(function(){
		$("html,body").animate({scrollTop:$(".index_cabinet").offset().top-$(".xuangui").height()},1000);
	})
	$(".main_image ul li").eq(1).click(function(){
		window.location.href = '../view/bannerdetail.html';
	})
	$(".main_image ul li").eq(3).click(function(){
		window.location.href = '../view/bannerdetail.html';
	})
	$(".indexTitle").click(function(){
		window.location.href = '../view/changeSearch.html';
	})
	$(".topTitle").click(function(){
		window.location.href = '../view/changeSearch.html';
	})
}