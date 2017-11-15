$(function(){
	//checkOpenId();
	var cityName=localStorage.getItem("selectCityName");
	var city=localStorage.getItem("city");
	var longitude=localStorage.getItem("lng");
	var latitude=localStorage.getItem("lat");
	$(".youz").text(city);
	getAllUniversitiesListWX(cityName,longitude,latitude);
	
	
	$("#txt").click(function(){
		$("#txt").val("");
	});
	
	
	$("#txt").keyup(function() {
		var txtvalue = $("#txt").val();
		var url = apiurl + 'api/wechat/fuzzySearchUniversityWX';
		var jsonData={'keywords':txtvalue};
		$.ajax({
			type:"post",
			url:url,
			async:false,
			dataType:'json',
			data:$.toJSON(jsonData),
			success:function(data)
			{
				if(data.returnCode=='1'){
					$(".content").empty();
					$(".allContent").empty();
					$(".dingwei").remove();
					console.log("后台返回数据："+JSON.stringify(data.returnContent));
					var returnContent = eval(data.returnContent);
					for (var j=0;j<returnContent.length;j++) {
						if(j==0){
							$('.content').append("<tr><td class='xiaoqu' style='margin-top: 1.9rem;' lang='"+returnContent[j].universityId+"'>"+returnContent[j].universityName+"</td></tr>");
						}else{
							$('.content').append("<tr><td class='xiaoqu' lang='"+returnContent[j].universityId+"'>"+returnContent[j].universityName+"</td></tr>");
						}
					}
					getClick();
				}else{
					console.log('失败原因:'+ JSON.stringify(data.returnContent));
				}
			},
			error:function(data)
			{
				console.log('失败原因:'+ JSON.stringify(data.returnContent));
			}
		});
	});
	
	
	



function getClick(){
	$(".xiaoqu").click(function(){
		localStorage.setItem("selectUniversityId",$(this).attr("lang"));
		localStorage.setItem("selectUniversityName",$(this).text());
		window.location.href='Cabinetselection.html';
	});
}

//2.2.35.	根据城市名称获取所有大学列表
function getAllUniversitiesListWX(cityName,longitude,latitude){
	var url = apiurl + 'api/wechat/getAllUniversitiesListWX';
	var jsonData = {"cityName":cityName,"longitude":longitude,"latitude":latitude};
	$.ajax({
		type:"post",
		url:url,
		async:false,
		dataType:'json',
		data:$.toJSON(jsonData),
		success:function(data)
		{
			if(data.returnCode=='1'){
				console.log("后台返回数据："+JSON.stringify(data.returnContent));
				var returnContent = eval(data.returnContent);
				for (var i=0;i<returnContent.length;i++) {
					if(returnContent[i].zoneName=='定位'){
						for (var j=0;j<returnContent[i].universities.length;j++) {
							$('.content').append("<tr><td class='xiaoqu' lang='"+returnContent[i].universities[j].universityId+"'>"+returnContent[i].universities[j].universityName+"</td></tr>");
						}
					}else{
						$(".allContent").append("<tr><td class='xiaoqi'>"+returnContent[i].zoneName+"</td></tr>");
						for (var j=0;j<returnContent[i].universities.length;j++) {
							$('.allContent').append("<tr><td class='xiaoqu' lang='"+returnContent[i].universities[j].universityId+"'>"+returnContent[i].universities[j].universityName+"</td></tr>");
						}
					}
				}
				getClick();
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
});