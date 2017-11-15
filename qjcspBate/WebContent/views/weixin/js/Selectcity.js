$(function(){
	//checkOpenId();
	// 百度地图API功能
	/*var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
	*/
//map 获取城市
	   map();
       //setDiv_Center($('.indexTitle')) //如果不能定位或位置不会移动就开启
       /*GPS定位*/
       function map()
       {
           var geolocation = new BMap.Geolocation();
           geolocation.getCurrentPosition(function(r){
               if(this.getStatus() == BMAP_STATUS_SUCCESS){
                   var mk = new BMap.Marker(r.point);
                   var lng = r.point.lng;
                   localStorage.setItem("lng",lng);  //经度
                   //localStorage.setItem("lng",120.126465);  //经度
                   var lat = r.point.lat;
                   localStorage.setItem("lat",lat);  //纬度
                   //localStorage.setItem("lat",30.287865);  //纬度
                   aa(lng,lat);
                   //aa(120.126465,30.287865);
                   //alert('您的位置：'+lng+','+lat);
               }
               else {
                   //alert('failed'+this.getStatus());
               }
           },{enableHighAccuracy: true})
           //关于状态码
           //BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
           //BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
           //BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
           //BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
           //BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
           //BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
           //BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
           //BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
           //BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
           // 百度地图API功能
           function aa(lng,lat)
           {
               var point = new BMap.Point(lng,lat);
               var gc = new BMap.Geocoder();
               gc.getLocation(point, function(rs){
                   var addComp = rs.addressComponents;
                   var city=addComp.city;
                  // localStorage.setItem("city",city);
                   $(".dwei_a").text(city);
               });
           }
       }
	
	
	
	
	//获取城市
/*	if(localStorage.getItem("city")!=''){
		//alert($(".dwei_a").text())
		$(".dwei_a").text(localStorage.getItem("city"));
	}else{
		function myFun(result){
			var cityName = result.name;
			//map.setCenter(cityName);
			//alert("当前定位城市:"+cityName);
			$(".dwei_a").text(cityName);
			}
			var myCity = new BMap.LocalCity();
			myCity.get(myFun);
	}
	*/
	
	
	
	
	getAllCitiesListWX();
	
	
	
	
	
	$(".dwei_a").click(function(){
		localStorage.setItem("selectCityName",$(this).text());
		window.location.href='Selectschool.html';
	});
	
	$(".remenaa").click(function(){
		localStorage.setItem("selectCityName",$(this).text());
		window.location.href='Selectschool.html';
	});
	
	$(".remenaaa").click(function(){
		localStorage.setItem("selectCityName",$(this).text());
		window.location.href='Selectschool.html';
	});
});

//2.2.34.	获取所有城市列表
function getAllCitiesListWX(){
	var url = apiurl + 'api/wechat/getAllCitiesListWX';
	$.ajax({
		type:"post",
		url:url,
		async:false,
		dataType:'json',
		success:function(data)
		{
			if(data.returnCode=='1'){
				console.log("后台返回数据："+JSON.stringify(data.returnContent));
				var returnContent = eval(data.returnContent);
				for(var i=0;i<returnContent.hotCities.length;i++)
				{
					$(".remena").append("<li class='remenaa' lang='"+returnContent.hotCities[i].cityId+"'>"+returnContent.hotCities[i].cityName+"</li>");
				}
				for (var j=0;j<returnContent.allCities.length;j++) {
					if(returnContent.allCities[j].cities.length!=0){
						//$(".addressul").append("<li><a href='javascript:;'>"+returnContent.allCities[j].initial+"</a></li>");
						 $(".addressul").append("<li><a href='#"+returnContent.allCities[j].initial+"'>"+returnContent.allCities[j].initial+"</a></li>");
						$(".cityTable").append("<tr><td class='caocao'><div class='city-list'><span class='ziti' id='"+returnContent.allCities[j].initial+"'>"+returnContent.allCities[j].initial+"</span></td></tr>");
					}
					for (var k=0;k<returnContent.allCities[j].cities.length;k++) {
						$(".cityTable").append("<tr><td class='wocao'><p class='remenaaa' lang='"+returnContent.allCities[j].cities[k].cityId+"'>"+returnContent.allCities[j].cities[k].cityName+"</p></td></tr>");
					}
					$(".cityTable").append("</div>");
				}
			}else{
				console.log('失败原因:'+ JSON.stringify(data.returnContent));
			}
		},
		error:function(data)
		{
			console.log('失败原因:'+ JSON.stringify(data.returnContent));
		}
	});
}
