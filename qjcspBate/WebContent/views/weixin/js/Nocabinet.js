$(function(){
	checkOpenId();
	$("#txt").click(function(){
		$("#txt").val("");
	});
	
	
	$("#txt").keyup(function() {
		var txtvalue = $("#txt").val();
		var url = apiurl + 'api/wechat/fuzzySearchCityWX';
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
					$(".cityList").empty();
					console.log("后台返回数据："+JSON.stringify(data.returnContent));
					var returnContent = eval(data.returnContent);
					for(var i=0;i<returnContent.length;i++)
					{
						$(".cityList").append("<tr><td class='xiaoquu' lang='"+returnContent[i].cityId+"'>"+returnContent[i].cityName+"</td></tr>");
					}
					
					giveClick();
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
	
	
	$("#spanClick").click(function(){
		window.location.href='Selectcity.html';
	});
	
	function giveClick(){
		$(".xiaoquu").click(function(){
			localStorage.setItem("selectCityName",$(this).text());
			window.location.href='Selectschool.html';
		});
	}
});
