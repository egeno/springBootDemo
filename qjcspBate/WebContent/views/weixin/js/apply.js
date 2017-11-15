$(function(){
	checkOpenId();
	$('.finishBtn').click(function(){
		//判断页面中是否有未填写的内容
		var falg=0;
		for(var i=0;i<$('input').length;i++){
			if($('input').eq(i).val().length<=0){
				falg=1;
			}
		}
		//如果没有填写就给与提示or调后台申请
		if(falg==0){
			apply();
		}else{
			$(".message").fadeIn(500);
			setTimeout(function () {
		        $(".message").fadeOut(500);
		    },1000);
		}
	});
});
//申请机柜
function apply(){
	checkCustomerId();
	var customerId= sessionStorage.getItem('customerId');
	var customerName=$('.userName').find("input").val();
	var customerMobile=localStorage.getItem("mobileNum");
	var telephone=$('.tel_phone').find("input").val();
	var region=$('.area').find("input").val();
	var detailAddress=$('.info_address').find("input").val();
	if(customerId!=null && customerId.length>0){
		url="api/wechat/applyAddressWX";
		var jsonData={'customerId':customerId,'customerName':customerName,'customerMobile':customerMobile,'telephone':telephone,"region":region,"detailAddress":detailAddress};
		$.ajax({
			type:"post",
			url:apiurl+"api/wechat/weekPreissueGoodsWX",
			async:false,
			data:$.toJSON(jsonData),
			dataType:'json',
			success:function(data)
			{
				if(data.returnCode=="1"){
					window.location.href="index.html";
				}if(data.returnCode=="0"){
					$(".addressMag").fadeIn(500);
					setTimeout(function () {
				        $(".addressMag").fadeOut(500);
				    },1000);
				}
			}
		});
	}
}