$(document).ready(function() {
	checkCustomerId();
    // 删除
	var takedItems=[];
    $(".delete").on('touchstart',function(){
        $(this).hide();
        $(".cancel").show();
        $(".check").show();
        $(".nocheck").hide();
        $(".check").each(function(){
        	if(!$(this).hasClass("nocheck")){
        		$(this).parents(".pro-li").css("width","6.4rem");
        		$(this).parents(".pro-li").removeClass("pro-liclick");
        		$(this).parents(".pro-li").find(".location").css({"width":"5rem","float":"right"});
        		$(this).parents(".pro-li").find(".right-titinf").css({"width":"3.2rem"});
        		$(this).parents(".pro-li").find(".location").find("em").css({"width":"3.5rem"});
        	}
        });
        $(".deletebut").show();
        $(".pro-li").removeClass("pro-liclick");
    });
    $(".cancel").on('touchstart',function(){
    	$(".cancel").hide();
    	$(".delete").show();
    	$(".check").hide();
    	$(".deletebut").hide();
    	$(".pro-li").css("width","6.4rem");
        $(".pro-li").addClass("pro-liclick");
        $(".right-titinf").css({"width":"4.2rem"});
        $(".location").css({"width":"6rem","float":"right"});
        $(".location").find("em").css({"width":"4.4rem"});
        return false; 
    });
    $('.check').live("click",function(){
        var num=$(this).attr("num")
        if(num=="1"){
            $(this).addClass('checked');
            $(this).attr("num","2");
        }else {
            $(this).removeClass('checked');
            $(this).attr("num","1");
        }
        var checknum=$('.checked').length;
        if(checknum>0){
        	$(".deletebutt").css("background","#ff831f");
        	$(".deletebutt").text("删除("+checknum+")");
        }else{
        	$(".deletebutt").css("background","rgba(255,132,32,0.5)");
            $(".deletebutt").text("删除");
        }
    });
    $('.pro-liclick').live("click",function(){
    	var index=$(this).index();
    	var takedItems=JSON.parse(localStorage.getItem('takedItems'));
    	orderNum=takedItems[index].orderNum;
    	orderStatus=takedItems[index].orderStatus;
    	localStorage.setItem("orderNum",orderNum);
    	localStorage.setItem("orderStatus",orderStatus);
    	var refundReasonOrderChildStatus=$(this).find(".location").attr("status");
    	localStorage.setItem("refundReasonOrderChildStatus",refundReasonOrderChildStatus);
        var TorderChildNum=$(this).find(".location").attr("onum");
        localStorage.setItem("TorderChildNum",TorderChildNum);
    	window.location.href = 'orderinfo.html';
    });
    var url = apiurl + 'api/wechat/refundOrderListWX';
    console.log("URL"+url);
    var customerId= sessionStorage.getItem('customerId');  //customerId:用户Id
    var jsonData = {"customerId":customerId,"orderStatus":"5"};
    $.ajax({  
        type:'post',
        url:url,  
        data:$.toJSON(jsonData),
        dataType:'json', 
        async:false, 
        success:function(data)
        {
        	var takedItems = JSON.stringify(data.returnContent);
            localStorage.setItem("takedItems",takedItems);
        	var takedItems = JSON.stringify(data.returnContent);
            localStorage.setItem("takedItems",takedItems);
            console.log("后台返回数据："+JSON.stringify(data.returnContent));
            var str = eval(data.returnContent);
            $(".pro-ul").eq(0).find("li").remove();
            $.each(str,function(i,k){
            	console.log(i,k);
                if(str[i].orderStatus=="5"){
				    $(".pro-ul").append('<li class="pro-li pro-liclick"> <p class="location" Status="'+str[i].refundReasonOrderChildStatus+'" oNum="'+str[i].orderChildNum+'"><img src="../img/image/locationimg.jpg" alt=""><em>'+str[i].address+'</em><span>退款中</span></p> <p class="check nocheck" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf"> <p class="date">'+str[i].orderTime+'</p> <p class="number">数量：'+str[i].goodsNum +'份</p> <p class="paymoney">共计：'+str[i].refundMoney+'元</p> </div> </li>');
				}else if(str[i].orderStatus=="8"){
				    $(".pro-ul").append('<li class="pro-li pro-liclick"> <p class="location" Status="'+str[i].refundReasonOrderChildStatus+'" oNum="'+str[i].orderChildNum+'"><img src="../img/image/locationimg.jpg" alt=""><em>'+str[i].address+'</em><span>退款失败</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf"> <p class="date">'+str[i].orderTime+'</p> <p class="number">数量：'+str[i].goodsNum +'份</p> <p class="paymoney">共计：'+str[i].refundMoney+'元</p> <a href="javascript:void(0);" class="zkefu">客服</a> </div> </li>');
				}else if(str[i].orderStatus=="9"){
				    $(".pro-ul").append('<li class="pro-li pro-liclick"> <p class="location" Status="'+str[i].refundReasonOrderChildStatus+'" oNum="'+str[i].orderChildNum+'"><img src="../img/image/locationimg.jpg" alt=""><em>'+str[i].address+'</em><span>退款成功</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf"> <p class="date">'+str[i].orderTime+'</p> <p class="number">数量：'+str[i].goodsNum +'份</p> <p class="paymoney">共计：'+str[i].refundMoney+'元</p> </div> </li>');
				}
            });
        },
        error:function(data)
        {
            console.log('失败原因:' + JSON.stringify(data.returnContent));
        }
    });
    $(".deletebut").click(function() {
        v_goodss=[];
        var takedItems=JSON.parse(localStorage.getItem('takedItems'));
        var checked_num=$(".checked").length;
        for(i=0;i<checked_num;i++){
            var newnum=$(".checked").eq(i).parent().index();
            v_goodss.push(takedItems[newnum]);
        }
        console.log(v_goodss);
        var url = apiurl + 'api/wechat/customerOrderDeleteWX';
        console.log("URL"+url);
        var customerId= sessionStorage.getItem('customerId');  //customerId:用户Id
        var jsonData = {"customerId":"1981","orderNumList":v_goodss};
        console.log("1981");
        $.ajax({  
            type:'post',      
            url:url,  
            data:$.toJSON(jsonData),
            dataType:'json', 
            async:false, 
            success:function(data)
            {
                console.log("后台返回数据："+JSON.stringify(data.returnContent));
                $(".checked").parent(".pro-li").remove();
            },
            error:function(data)
            {
                console.log('失败原因:' + JSON.stringify(data.returnContent));
            }
        });
        $(".cancel").hide();
        $(".delete").show();
        $(".deletebut").hide();
        $(".check").hide();
        $(".pro-li").css("width","6.4rem");
    });
    $(".zkefu").click(function(){
		console.log("aaa");
		$(".npop_up").parent().fadeIn();
		return false;
    })
    $(".ncancel").click(function(){
    	$(".npop_up").parent().fadeOut();  
    });
});