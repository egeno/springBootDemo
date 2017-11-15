$(function(){
	//checkOpenId();
	getAllMachinesListWX();
	
	
	map();
	function map()
	{
	    var geolocation = new BMap.Geolocation();
	    geolocation.getCurrentPosition(function(r){
	        if(this.getStatus() == BMAP_STATUS_SUCCESS){
	            var mk = new BMap.Marker(r.point);
	            var lng = r.point.lng;
	            //localStorage.setItem("lng",lng);  //经度
	            //localStorage.setItem("lng",120.126465);  //经度
	            var lat = r.point.lat;
	            //localStorage.setItem("lat",lat);  //纬度
	            //localStorage.setItem("lat",30.287865);  //纬度
	            aa(lng,lat);
	        }
	        else {
	            //alert('failed'+this.getStatus());
	        }
	    },{enableHighAccuracy: true})
	    function aa(lng,lat)
	    {
	        var point = new BMap.Point(lng,lat);
	        var gc = new BMap.Geocoder();
	        gc.getLocation(point, function(rs){
	            var addComp = rs.addressComponents;
	            var city=addComp.city;       
	        	$(".youz").text(city);	
	            localStorage.setItem("city",city);
	        });
	    }
	}
	

});

//2.2.36.	根据大学Id获取所有设备列表
function getAllMachinesListWX(){
	var selectUniversityId=localStorage.getItem("selectUniversityId");
	var selectUniversityName=localStorage.getItem("selectUniversityName");
	var url = apiurl + 'api/wechat/getAllMachinesListWX';
	var jsonData={'universityId':selectUniversityId};
	$.ajax({
		type:"post",
		url:url,
		async:false,
		dataType:'json',
		data:$.toJSON(jsonData),
		success:function(data)
		{
			if(data.returnCode=='1'){
				$(".zjgs").find("span").text(selectUniversityName);
				console.log("后台返回数据："+JSON.stringify(data.returnContent));
				var returnContent = eval(data.returnContent);
				//$(".youz").text(returnContent.cityName);				
				if(returnContent.machineZones==undefined){
				
					$(".guizi").show();
				}else{
		
					for (var i=0;i<returnContent.machineZones.length;i++) {
						$(".neir-z").append("<div style='font-size:12px;overflow: hidden;text-overflow: ellipsis; white-space: nowrap;'>"+returnContent.machineZones[i].machineZoneName+"</div>");
						$(".taba").append("<li class='tabaa' id='tabaa"+i+"'>");
						for (var j=0;j<returnContent.machineZones[i].machines.length;j++) {
							$("#tabaa"+i).append("<div class='zhuang' data_companyId="+returnContent.machineZones[i].machines[j].companyId+"><img src='../img/imagey/fangz.png' /><span lang='"+returnContent.machineZones[i].machines[j].machineId+"'>"+returnContent.machineZones[i].machines[j].machineName+"</span></div>");
						}
						$(".taba").append("</li>");
						$(".neir-z div").eq(0).addClass("xuanzzz");
						$(".guizii").hide();
					}
					giveClass();
					getCompanyIdAndMachineId();
				}
			}else{
				console.log('失败原因:'+ JSON.stringify(data.returnContent));
				if(localStorage.getItem("city")!=''){
					var city=localStorage.getItem("city");
					$(".youz").text(city);
				
				}
			}
		},
		error:function(data)
		{
			console.log('失败原因:'+ JSON.stringify(data.returnContent));
		}
	});
}

function giveClass(){
	$(".neir-z div").mouseenter(function () {
	}).click(function () {
		var $this = $(this);
		var index = $this.index();
		$(".neir-z div").removeClass("xuanzzz");
		$(".neir-z div").eq(index).addClass("xuanzzz");
	});
}
function getCompanyIdAndMachineId(){
	$(".zhuang").click(function(){
		localStorage.setItem("selectCompanyId",$(this).attr("data_companyId"));
		localStorage.setItem("selectMachineId",$(this).find("span").attr('lang'));
		localStorage.setItem("selectMachineName",$(this).find("span").text());
		localStorage.setItem("Cabinetselection","Cabinetselection");
		window.location.href='Nooutput.html?live';
	});
}
