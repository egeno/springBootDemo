$(document).ready(function(){
	//checkOpenId();
	$(".history").hide(); //隐藏历史搜索
	$(".clearHistory").hide();
	localStorage.removeItem("newGoodsList");
	
	//点击'清除历史纪录'，显示弹窗
	$('.clearHistory').on('touchstart',function(){
		$('.dialog_clear').parent().show();
	});
	//点击'确定'按钮，清空历史记录
	$('.sure').on('touchstart',function(){
		$('.history').find('p').remove();
		$('.dialog_clear').parent().hide();
	});
	//点击'取消'按钮，关闭弹窗
	$('.cancel').on('touchstart',function(){
		$('.dialog_clear').parent().hide();
	});
	//自动获取地址
	$('.location').on('touchstart',function(){
		$('.location').find("span").text("定位中...");
		map("../view/index.html?live");
	});
	//模糊搜索
	$(".guessAdd").focus(function(){
		$(".change_search").css("width","4.6rem");
		$(".buttom").show();
		/*$(".history").show();
		var hitory_addr=localStorage.getItem('check_change_address')
		$(".history").append('<p>'+hitory_addr+'</p>');*/
		
	})
	$(".guessAdd").focus(function(){
		$(".history").hide();
		$(".history").find("p").remove();
	})
	//$(".guessAdd").change(function(){
	
	$('.buttom').click(function(){
		$(".history").hide();
		$(".history").find("p").remove();
		$('.guessAdd').val('');
		$('.location').show();
	})
	
	
	//切换地址页面——关键词提示输入
	    map() //存经纬度
		$('.guessAdd').on('keyup',function(){
		//console.log("aaaa");
			var city=localStorage.getItem("district");
			//var lat=localStorage.getItem("lat");
			//var lng=localStorage.getItem("lng");
			//var location=lat+','+lng;
			var locationName=$(".guessAdd").val();
			//var region="全国";
			//var location='30.288058,120.126408';
			//var jsonData = {"query":locationName,"region":"全国","location":location,"output":"json","ak":"a1F62NERECgnHtjcFHdNGBuaIUcdEPU5"};
			var jsonData = {"query":locationName,"page_size":"10","page_num":"0","scope":"1","region":city,"output":"json","ak":"a1F62NERECgnHtjcFHdNGBuaIUcdEPU5"};
			//var output='json';	
			//var ak='a1F62NERECgnHtjcFHdNGBuaIUcdEPU5';
			//var url = 'http://api.map.baidu.com/place/v2/suggestion?query=西湖&region=全国&location=30.288058,120.126408&output=json&ak=a1F62NERECgnHtjcFHdNGBuaIUcdEPU5';
		console.log("给后台的数据："+JSON.stringify(jsonData));
			$.ajax({
	    		//url : 'http://api.map.baidu.com/place/v2/suggestion',
				url : 'http://api.map.baidu.com/place/v2/search',
	    		type : 'POST',
	    		async : false, 
	    		cache :  false,
	    		dataType : 'jsonp',
	    		data : jsonData ,
	    		success : function(data){
	    			console.log("接口信息:"+JSON.stringify(data));
	    			var str = eval(data.results);
					if(data.status=="0"){
						$(".location").hide();
						$(".history").find("p").remove();
						$(".history").show();
						$.each(str,function(v,k){
							console.log(v,k);
							$(".history").append('<p cid="" lat='+k.location.lat+' lng='+k.location.lng+'>'+k.name+'</p>');
						})
						$(".history p").on('click',function(){
							   localStorage.removeItem(check_change_address)
								var check_change_address=$(this).text();
								localStorage.setItem('check_change_address',check_change_address);
							})
					}
	    		},error:function(data){
				console.log("接口信息:"+JSON.stringify(data));
			}
	    	}) ;
	})
	
	
/*	$('.guessAdd').on('keyup',function(){
		//console.log("aaaa");
		var url = apiurl + 'api/wechat/getCompanyListByLocationWX';
		var locationName=$(".guessAdd").val();
		var jsonData = {"locationName":locationName};
		console.log("给后台的数据："+JSON.stringify(jsonData));
		$.ajax({
			type:"post",
			url:url,
			data:$.toJSON(jsonData),
			dataType:'json',
			success:function(data)
			{
				console.log("接口信息:"+JSON.stringify(data));
				var str = eval(data.returnContent);
				if(data.returnCode=="1"){
					$(".location").hide();
					$(".history").find("p").remove();
					$(".history").show();
					$.each(str,function(v,k){
						console.log(v,k);
						$(".history").append('<p cid="'+k.companyId+'">'+k.companyName+'</p>');
					})
				}
			},error:function(data){
				console.log("接口信息:"+JSON.stringify(data));
			}
		})
	})*/
	
/*	$(".buttom").click(function(){
		//console.log("aaaa");
		var url = apiurl + 'api/wechat/getCompanyListByLocationWX';
		var locationName=$(".guessAdd").val();
		var jsonData = {"locationName":locationName};
		console.log("给后台的数据："+JSON.stringify(jsonData));
		$.ajax({
			type:"post",
			url:url,
			data:$.toJSON(jsonData),
			dataType:'json',
			success:function(data)
			{
				console.log("接口信息:"+JSON.stringify(data));
				var str = eval(data.returnContent);
				if(data.returnCode=="1"){
					$(".location").hide();
					$(".history").find("p").remove();
					$(".history").show();
					$.each(str,function(v,k){
						console.log(v,k);
						$(".history").append('<p cid="'+k.companyId+'">'+k.companyName+'</p>');
					})
				}
			},error:function(data){
				console.log("接口信息:"+JSON.stringify(data));
			}
		})
	})*/
	$(".history p").live("click",function(){
		var companyId = $(this).attr("cid");
		var lat = $(this).attr("lat");
		var lng = $(this).attr("lng");
		localStorage.removeItem("lat");
		localStorage.removeItem("lng");
		localStorage.setItem("lat",lat);
		localStorage.setItem("lng",lng);
		localStorage.setItem('companyId',companyId);  //companyId:商户Id
		window.location.href="../view/index.html";
	})
	/*GPS定位*/
	function map(address)
	{
		
		var address1=address;
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				var lng = r.point.lng;
				localStorage.setItem("lng",lng);  //经度
				var lat = r.point.lat;
				localStorage.setItem("lat",lat);  //纬度
				aa(lng,lat,address1);
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
		function aa(lng,lat,address1)
		{
			var point = new BMap.Point(lng,lat);
			var gc = new BMap.Geocoder();
			gc.getLocation(point, function(rs){
			   var addComp = rs.addressComponents;
			   $('.location').find("span").text(addComp.district+addComp.street+addComp.streetNumber);
			   var province = addComp.province;
			   var locationName = addComp.district+addComp.street+addComp.streetNumber;
			   localStorage.setItem("locationName",locationName);
			   localStorage.setItem("district",addComp.district);
			   localStorage.setItem("district",addComp.city);
			   localStorage.setItem("province",province);
			   localStorage.removeItem(check_change_address)
				var check_change_address=addComp.district+ addComp.street +addComp.streetNumber;
			   
				localStorage.setItem('check_change_address',check_change_address);
				   if(address1=='../view/index.html?live'){
				      window.location.href=address1;
				   }
			});
		}
	}
	
	/*GPS定位输入时调用*/
	/*function map2()
	{
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				var lng = r.point.lng;
				localStorage.setItem("lng",lng);  //经度
				var lat = r.point.lat;
				localStorage.setItem("lat",lat);  //纬度
				aa(lng,lat);
				//alert('您的位置：'+lng+','+lat);
				
				return (lat+','+lng);
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
			   $('.location').find("span").text(addComp.district+addComp.street+addComp.streetNumber);
			   var province = addComp.province;
			   var locationName = addComp.district+addComp.street+addComp.streetNumber;
			   localStorage.setItem("locationName",locationName);
			   localStorage.setItem("district",addComp.district);
			   localStorage.setItem("province",province);
			   //window.location.href="../view/index.html?live";
			});
		}
	}*/
	
	
	
});
