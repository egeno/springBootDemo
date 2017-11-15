//省市区三级级联下拉框
var $province = $('#provCode');
var $city = $('#cityCode');
var $machineZoneId = $('#machineZoneId');
//默认加载省份
function province(cid){
	$province.combobox({
		url:"address/findProvinceAllList",
		onLoadSuccess:function(){
			if(cid.length!=''){
				$province.combobox('setValue',cid);
			}
        },
		onSelect: function (data){
			city(data.id,data.name,"-1","");
		}
	});
}
//省份改变时加载城市
function city(id,name,cid,cname){
	$("#cityCode").val("全部");
	if(id != ""){
		$("#province").val(name);
		$city.combobox({
			url:'address/findCityAllList?id=' + id,
			onLoadSuccess:function(){
				if(cid != "-1"){
					$city.combobox('setValue',cid);
					$("#cityName").val(cname);
				}
	        },
			onSelect: function (data){		 
				$("#cityName").val(data.name);
			}
		});
	}else{
		$("#province").val("");
		$city.combobox('loadData',[{}]);//重置市
		$city.combobox('select', '');
	}
	//$("#cityName").val("");	
//	if(cid == "-1"){
//		$area.combobox('loadData',[{}]);//重置区
//		$area.combobox('select', '');
//	}
	//alert($("#cityName").val());
}
function machineZone(machineZoneIds,machineZoneName){
	var companyId;
	if($("#companyId").val()==null ||$("#companyId").val()==undefined){
		companyId='';
	}
	else{
		companyId=$("#companyId").val();
	}
	$machineZoneId.combobox({
		url:"machine/info/findGMachineZoneByUniversityId?companyId="+companyId,
		onLoadSuccess:function(){
			if(machineZoneIds!=''){
				$machineZoneId.combobox('setValue',machineZoneIds);
			}
        },
		onSelect: function (data){		 
			$("#areaName").val(data.machineZoneName);
		}
	});
}