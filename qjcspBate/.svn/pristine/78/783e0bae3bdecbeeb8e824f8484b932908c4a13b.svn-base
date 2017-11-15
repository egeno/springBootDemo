$(document).ready(function(){
	sessionStorage.setItem('customerId',2003);
	//checkCustomerId();
	//商家电话
	$('.phone').on('click',function(){
		$('.order_tel').parent().show();
	});
	$('.cancel').on('click',function(){
		$('.order_tel').parent().hide();
	});
	$(".myOrder").click(function(){
		localStorage.setItem("pick_index","0");
		window.location.href = 'Ordertab.html';
	})
	$(".opinion").click(function(){
		window.location.href = '../view/advice.html';
	})
	$(".aboutUs").click(function(){
		window.location.href="about.html";
	})
	reg();
	var url = apiurl + 'api/wechat/getOrderCountByStatusWX';
    console.log("URL"+url);
    var customerId= sessionStorage.getItem('customerId');  //customerId:用户Id
    var jsonData = {"customerId":customerId,};
    var username= localStorage.getItem('username');
    if(username==null||username==""){
    	username="匿名用户";
    }
    var attentionTime= sessionStorage.getItem('attentionTime');
    console.log(username,attentionTime);
    $.ajax({  
	    type:'post',      
	    url:url,  
	    data:$.toJSON(jsonData),
	    dataType:'json', 
	    async:false, 
	    success:function(data)
	    {
	    	var str=eval(data.returnContent);
	    	console.log("后台返回数据："+JSON.stringify(data.returnContent));
	    	var readyPayCount=str.readyPayCount;
	    	var readyPickCount=str.readyPickCount;
	    	var readyCommentCount=str.readyCommentCount;
	    	var readyDealCount=str.readyDealCount;
	    	if(readyPayCount>0){
	    		$(".orderStatus .waitPay").find(".knum").text(readyPayCount);
	    	}
	    	if(readyPickCount>0){
	    		$(".orderStatus .waitTake").find(".knum").text(readyPickCount);
	    	}
	    	if(readyCommentCount>0){
	    		$(".orderStatus .waitEvaluate").find(".knum").text(readyCommentCount);
	    	}
	    	if(readyDealCount>0){
	    		$(".orderStatus .waitRefund").find(".knum").text(readyDealCount);
	    	}
	    	if(username==null||username==""){
	    		username="匿名用户";
	        }
	    	$(".status").text(username);
	    	if(attentionTime==null||attentionTime==""){
	    		$(".tel").hide();
	    	}else{
	    		$(".tel").find("em").text(attentionTime);
	    	}
	    },
		error:function(data)
		{
			console.log('失败原因:' + JSON.stringify(data.returnContent));
		}
    });
    $('.waitPay').on('touchstart',function(){
    	localStorage.setItem("pick_index","1");
    	setInterval(function(){window.location.href = 'Ordertab.html';},500);
	});
	
	$('.waitTake').on('touchstart',function(){
		localStorage.setItem("pick_index","2");
		setInterval(function(){window.location.href = 'Ordertab.html';},500);
	});
	
	$('.waitEvaluate').on('touchstart',function(){
		localStorage.setItem("pick_index","3");
		setInterval(function(){window.location.href = 'Ordertab.html';},500);
	});
	
	$('.waitRefund').on('touchstart',function(){
		setInterval(function(){window.location.href = 'refundorsh.html';},500);
	});
});
function reg(){
	var telphoneNum = localStorage.getItem('telphoneNum');
	var nickName = localStorage.getItem('nickName');
    if(nickName==null||nickName==""){
    	nickName="匿名用户";
    }
	$(".status").text(nickName);
}
