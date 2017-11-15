$(function(){
 	checkCustomerId();
	var customerId= sessionStorage.getItem('customerId');
	if(customerId!=null && customerId.length>0){
		/*var url = apiurl + 'api/wechat/getEvaluationDetailsWX';
		var orderNum=localStorage.getItem('orderNum');
		var jsonData={"orderNum":orderNum};
		$('.evaluateInfo_box li').remove();
		$.ajax({
			type:"post",
			url:url,
			data:$.toJSON(jsonData),
			dataType:'json',
			success:function(data)
			{
				console.log("评价详情后台返回参数："+JSON.stringify(data.returnContent));
				var star_length =eval(data.returnContent).length;
				var star =eval(data.returnContent);
				$.each(star,function(v,data){
					console.log(v,data);
					var ystar="";
					var hstar="";
					for(var i=0;i<data.goodsEvaluateScore;i++){
						ystar=ystar+"<img src='../img/img/star2.png' alt='星星'>";
					}
					for(var j=0;j<5-data.goodsEvaluateScore;j++){
						hstar=hstar+"<img src='../img/img/star1.png' alt='星星'>";
					}
					$('.evaluateInfo_box').append("<li class='info'><div class='info_title'><p class='goodsName'>"+data.goodsName+"</p><p class='start'>"+ystar+hstar+"</p></div><p class='text'>"+data.goodsEvaluateContent+"</p><p class='time'>"+data.evaluateTime+"</p></li>");
					
				})
				
			},
			error:function(data)
			{
				console.log('失败:'+  JSON.stringify(data.returnContent));
			}
		});*/
		var url = apiurl + 'api/wechat/getEvaluationDetailsWX';
		var orderNum=localStorage.getItem('orderNum');
		//alert(orderNum)
		var jsonData={"orderNum":orderNum};
		$('.evaluateInfo_box li').remove();
		$.ajax({
			url:url,
			type:'post',
			dataType:'json',
			data:$.toJSON(jsonData),
			success:function(data){
				console.log('后台返回数据：'+JSON.stringify(data.returnContent));
				if(data.returnCode=='1'){
					var star =eval(data.returnContent);
					$.each(star,function(index,value){
						
						var ystarScore=''
						var hstarScore=''
						for(var i=0;i<value.goodsEvaluateScore;i++){
							ystarScore=ystarScore+"<img src='../img/imgbig/star2x.png' alt='星星'>"
						}
						for(var i=0;i<5-value.goodsEvaluateScore;i++){
							hstarScore=hstarScore+"<img src='../img/imgbig/star2x2.png' alt='星星'>"
						}
						var check='';
						if(value.evaluateType=='N')  check='待审核'
						if(value.evaluateType=='Y')  check='审核通过'
						if(value.evaluateType=='C')  check='审核未通过'
						//var check=value.evaluateType=='N'?'待审核':value.evaluateType=='Y'?'审核通过':value.evaluateType=='c'?'审核未通过';
						//<div class="shop_contain"><span class="goodsName">'+value.goodsName+'</span></div><span class="white"></span>
						$('.evaluateInfo_box').append('<li class="info"><div class="info_title"><div class="shop_contain"><span class="goodsName">'+value.goodsName+'</span></div><span class="start white">'+hstarScore+ystarScore+'</span></div><p class="text">'+value.goodsEvaluateContent+'</p><p class="time"><span class="check">'+check+'</span><span class="date">'+value.evaluateTime+'</span></p></li>')
					})
					
				   setInterval(function(){
						for(var i=0;i<$('.goodsName').length;i++){
					
							if($('.goodsName').eq(i).html().length>13){
					
								$('.goodsName').eq(i).animate({
									'left':'-90px',
								},6000).animate({
									'left':'0px',
								},6000)
							}
						}
			       },1000)
					
					
				}else{
					console.log('失败原因：'+JSON.stringify(data.returnContent));
				}
				
			}
		})
	}
	
	$('.go_back').click(function(){
		window.location.href='orderinfo.html';
	})
})