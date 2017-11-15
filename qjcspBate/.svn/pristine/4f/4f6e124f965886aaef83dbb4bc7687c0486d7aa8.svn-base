//省市区三级级联下拉框
var $province = $('#provCode');
var $city = $('#cityCode');
var $companyName=$("#companyName");
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
	if(id != ""){
		$("#province").val(name);
		$city.combobox({
			url:'address/findCityAllList?id=' + id,
			onLoadSuccess:function(){
				if(cid != "-1"){
					$city.combobox('setValue',cid);
					$("#cityname").val(cname);
				}
	        },
			onSelect: function (data){		 
				$("#cityname").val(data.name);
				company(data.id,data.name);
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

function company(cityId,cityName){
	if(cityId!=''){
		$('#cityName').val(cityName);
		$companyName.combobox({
			url:'merchant/findUniversityList?cityId='+cityId,
			onLoadSuccess:function(data){
				//alert(JSON.stringify(data));
	        },
			onSelect: function (data){		 
				$("#companyName").val(data.name);
				$("#companyId").val(data.id);
			}
		});
	}
}
