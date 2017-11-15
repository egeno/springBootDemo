var count_down=[];
var nowul=0;
var timeb;
var timea;
$(document).ready(function() {
	//checkCustomerId();
	sessionStorage.setItem('customerId','2003');
    // tab点击
    var pick_index=localStorage.getItem("pick_index");
    $('.nav-tabul li').eq(pick_index).addClass('current').siblings().removeClass('current');
    $('.productinfo').addClass('none');
    $('.productinfo').eq(pick_index).removeClass('none');
    //$('.productinfo').eq(pick_index).removeClass("none").siblings(".productinfo").addClass("none");
    var a=pick_index;
    if(a>0){
        tabclick(a);
    } 
    var takedItems=[];
    var v_goodss=[];
    $(".nav-tabul li").click(function() {
        $(".deletebut").hide();
        $(".cancel").hide();
        $(".nav-tabul li").removeClass('current');
        $(this).addClass('current');
        var a=$(this).index();
        console.log(a);
        $(".productinfo").addClass('none');
        $(".productinfo").eq(a).removeClass('none');
        tabclick(a);
    });
    //评价按钮跳转
    $('.daipingjia').live("click",function(){
        var orderNum=$(this).parent(".right-titinf").attr("ordernum");
        var snum=$(this).siblings(".number").attr("snum");
        localStorage.setItem("snum",snum);
        localStorage.setItem("orderNum",orderNum);
        window.location.href = 'goodsEvaluate.html';
        return false;
    });
    //付款按钮跳转
    $('.daifukuan').live("click",function(){
        var orderNum=$(this).parent(".right-titinf").attr("ordernum");
        var totalAmount=$(this).siblings(".paymoney").attr("money");
        localStorage.setItem("orderNum",orderNum);
        localStorage.setItem("totalAmount",totalAmount);
        window.location.href = 'pay.html';
        return false;
    });
    //跳转到订单详情页面
    $('.pro-liclick').live("click",function(){
        var index=$(this).index();
        var takedItems=JSON.parse(localStorage.getItem('takedItems'));
        orderNum=takedItems[index].orderNum;
        localStorage.setItem("orderNum",orderNum);
        localStorage.setItem('refundReasonOrderChildStatus',"");
		localStorage.setItem('TorderChildNum',"");
        window.location.href = 'orderinfo.html';
    });
    // 已取消、已超时变色
    var num=$(".location span").length;
    for (var i=0;i<num;i++){
        var txt=$(".location span").eq(i).text()
        if(txt=="已取消"||txt=="已超时"){
            $(".location span").eq(i).css("color","#808080");
        }
    }
    
   
    $('.del1').click(function(){
    	
       if($('.pro-ul1 li').length==0){
    	   $('.delete').hide()
       }else{
    	   $('.delete').show()
       }
 
    })
    
      $('.del2').click(function(){
    	
       if($('.pro-ul2 li').length==0){
    	   $('.delete').hide()
       }else{
    	   $('.delete').show()
       }
  
    })
      $('.del3').click(function(){
    	
       if($('.pro-ul3 li').length==0){
    	   $('.delete').hide()
       }else{
    	   $('.delete').show()
       }
  
    })
    
      $('.del4').click(function(){
    	
       if($('.pro-ul4 li').length==0){
    	   $('.delete').hide()
       }else{
    	   $('.delete').show()
       }
  
    })
    
    
    // 删除
    $(".delete").click(function() {
        $(this).hide();
        $(".cancel").show();
        $(".deletebut").show();
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
        
    });
    $(".cancel").click(function() {
        $(this).hide();
        $(".delete").show();
        $(".deletebut").hide();
        $(".check").hide();
		$(".pro-li").css("width","6.4rem");
        $(".pro-li").addClass("pro-liclick");
        $(".right-titinf").css({"width":"4.2rem"});
        $(".location").css({"width":"6rem","float":"right"});
        $(".location").find("em").css({"width":"4.4rem"});
    });
    $('.check').live("click",function(){
    //$(".check").click(function() {
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
        var customerId = sessionStorage.getItem('customerId');  //customerId:用户Id
        var jsonData = {"customerId":customerId,"orderNumList":v_goodss};
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
                $(".pro-li").css("width","6.4rem");
                $(".pro-li").addClass("pro-liclick");
                $(".right-titinf").css({"width":"4.2rem"});
                $(".location").css({"width":"6rem","float":"right"});
                $(".location").find("em").css({"width":"4.4rem"});
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
    var url = apiurl + 'api/wechat/getOrdersByStatusWX';
    console.log("URL"+url);
    var customerId = sessionStorage.getItem('customerId');  //customerId:用户Id
    var jsonData = {"customerId":customerId,"orderStatus":"-1"};
    takedItems=[];
    $.ajax({  
        type:'post',      
        url:url,  
        data:$.toJSON(jsonData),
        dataType:'json', 
        async:false, 
        success:function(data)
        {
        	count_down=[];
            var takedItems = JSON.stringify(data.returnContent);
            localStorage.setItem("takedItems",takedItems);
            console.log("后台返回数据："+JSON.stringify(data.returnContent));
            var str = eval(data.returnContent);
            $(".pro-ul").eq(0).find("li").remove();
            $.each(str,function(i,k){
                if(str[i].orderStatus=="0"){
                    console.log('系统时间'+str[i].systemTime+',下单时间：'+str[i].orderDate)
                    /*获取系统时间和下单时间*/
                    var arr= str[i].systemTime.split(/[- :]/);
                    var	date1 = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]); 
                    var arr2= str[i].orderDate.split(/[- :]/);
                    var	date2 = new Date(arr2[0], arr2[1]-1, arr2[2], arr2[3], arr2[4], arr2[5]);
                    var date3=date1.getTime()-date2.getTime(); 
                    var minute = Math.floor(date3/1000/60);
                    var second =(date3/1000)%60;
                    var countdown=minute+'/'+second;
                    count_down.push(countdown);
                    if(minute>=5){
                    	localStorage.setItem('orderStatus',2);
                    	$(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已取消</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else{
                    	$(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>待付款</span></p> <p class="check nocheck" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor">'+'5分'+'0秒后订单自动取消</p> <a href="javascript:void(0);" class="buttom daifukuan">付款</a> </div> </li>');
                    }                 
                }else if(str[i].orderStatus=="1"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已支付</span></p> <p class="check nocheck" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor">请根据短信提示按时取餐</p> </div> </li>');
                }else if(str[i].orderStatus=="2"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已取消</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="3"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>待评价</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> <a href="javascript:void(0);" class="buttom daipingjia">评价</a></div> </li>');
                }else if(str[i].orderStatus=="4"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已超时</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="5"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>待退款</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="6"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>交易关闭</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="7"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>完成</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="8"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>退款失败</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="9"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>退款成功</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="10"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>设备故障</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="11"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已预订</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="13"){
                    $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已评价</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }else if(str[i].orderStatus=="14"){
                	 $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>取餐完成</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                }
                localStorage.setItem("count_down",count_down);
            });
        },
        error:function(data)
        {
            console.log('失败原因:' + JSON.stringify(data.returnContent));
        }
    });
    localStorage.getItem("count_down");
    console.log("本地存储时间："+localStorage.getItem("count_down"));
    $.each(count_down,function(v,k){
    	console.log(v,k);
    	var str=k.split("/")
    	var minute=str[0];
    	var second=str[1];
    	if(second>0){
        	var s=60-second;   //秒
        	var f=5-minute-1;  //分钟
        }else{
        	var f=5-minute;  //分钟
        	var s=00;
        }
        if(s>0 && f>0){
        	run(s,f);
        }
        /*倒计时*/
        function run(s,f){
    		if(s == 0){
    			if(f >= 0){
    				f=f *1 -1;
    				s=60;
    				s=s *1 -1;
    			}else{
    				return false;
    			}
    		}else{
    			s=s *1 -1;
    		}
    		console.log(f+"分"+s+"秒");
    	}
    	if((s!="0"||f!="0")&&(s>0 || f>0)){
    		var timeb=setInterval(function(){
    			if(s == 0){
    				if(f > 0){
    					f=f *1 -1;
    					s=60;
    					s=s *1 -1;
    				}else{
    					localStorage.setItem('orderStatus',2);
    					//window.location.href="orderinfo.html";
    					$(".pro-ul").eq(nowul).find(".dy-infor").eq(v).text("");
    					$(".pro-ul").eq(nowul).find(".dy-infor").eq(v).siblings(".daifukuan").hide();
    		    		$(".pro-ul").eq(nowul).find(".location").eq(v).find("span").text("已取消"); 
    					return false;
    				}
    			}else{
    				s=s *1 -1;
    			}
    			$(".dy-infor").eq(v).text(f+"分"+s+"秒后订单自动取消")
    		},1000);
    	}
//    	else{
//    		$(".pro-ul").eq(nowul).find(".dy-infor").eq(v).text("请根据短信提示按时取餐");
//    		$(".pro-ul").eq(nowul).find(".dy-infor").eq(v).submit(".daifukuan").hide();
//    		$(".pro-ul").eq(nowul).parents(".location").find("span").text("已取消"); 
//    	}
    })
  //右上侧 删除 消失显示
    if(localStorage.getItem("pick_index")==0){
    	if($('.pro-ul1 li').length==0){
    	    $('.delete').hide()
    	}else{
    	    $('.delete').show()
    	}
    }
});


function tabclick(a){
    var pick_index=localStorage.getItem("pick_index");
    var customerId = sessionStorage.getItem('customerId');  //customerId:用户Id
    if(pick_index =="1"){
        a=1;
        localStorage.setItem("pick_index","");
    }else if(pick_index =="2"){
        a=2;
        localStorage.setItem("pick_index","");
    }else if(pick_index =="3"){
        a=3;
        localStorage.setItem("pick_index","");
    }
    if(a=="0"){
        //全部
    	nowul=0;
        var url = apiurl + 'api/wechat/getOrdersByStatusWX';
        console.log("URL"+url);
        var jsonData = {"customerId":customerId,"orderStatus":"-1"};
        takedItems=[];
        $.ajax({  
            type:'post',      
            url:url,  
            data:$.toJSON(jsonData),
            dataType:'json', 
            async:false, 
            success:function(data)
            {
            	clearInterval(timea);
            	clearInterval(timeb);
            	count_down=[];
                //console.log("后台返回数据："+JSON.stringify(data.returnContent));
                var str = eval(data.returnContent);
                var takedItems = JSON.stringify(data.returnContent);
                localStorage.setItem("takedItems",takedItems);
                $(".pro-ul").eq(0).find("li").remove();
                $.each(str,function(i,k){
                    if(str[i].orderStatus=="0"){
                        console.log('系统时间'+str[i].systemTime+',下单时间：'+str[i].orderDate)
                        /*获取系统时间和下单时间*/
                        var arr= str[i].systemTime.split(/[- :]/);
	                    var	date1 = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]);
	                    var arr2= str[i].orderDate.split(/[- :]/);
	                    var	date2 = new Date(arr2[0], arr2[1]-1, arr2[2], arr2[3], arr2[4], arr2[5]);
                        var date3=date1.getTime()-date2.getTime();
                        var minute = Math.floor(date3/1000/60);
                        var second =(date3/1000)%60;
                        var countdown=minute+'/'+second;
                        count_down.push(countdown);
                        if(minute>=5){
                        	localStorage.setItem('orderStatus',2);
                        	$(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已取消</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                        }else{
                        	$(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>待付款</span></p> <p class="check nocheck" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor">'+'5分'+'00秒后订单自动取消</p> <a href="javascript:void(0);" class="buttom daifukuan">付款</a> </div> </li>');
                        }  
                        /*获取系统时间和下单时间*/
                    }else if(str[i].orderStatus=="1"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已支付</span></p> <p class="check nocheck" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor">请根据短信提示按时取餐</p> </div> </li>');
                    }else if(str[i].orderStatus=="2"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已取消</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="3"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>待评价</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p><a href="javascript:void(0);" class="buttom daipingjia">评价</a></div> </li>');
                    }else if(str[i].orderStatus=="4"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已超时</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="5"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>待退款</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="6"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>交易关闭</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="7"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>完成</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="8"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>退款失败</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="9"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>退款成功</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="10"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>设备故障</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="11"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已预订</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="13"){
                        $(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已评价</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else if(str[i].orderStatus=="14"){
                       	$(".pro-ul").eq(0).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>待评价</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> <a href="javascript:void(0);" class="buttom daipingjia">评价</a></div> </li>');
                    }
                });
            },
            error:function(data)
            {
                console.log('失败原因:' + JSON.stringify(data.returnContent));
            }
        });
    }else if(a=="1"){
//      待付款
    	 nowul=1;
         var url = apiurl + 'api/wechat/getOrdersByStatusWX';
         console.log("URL"+url);
         var jsonData = {"customerId":customerId,"orderStatus":"0"};
         takedItems=[];
         $.ajax({  
            type:'post',      
            url:url,  
            data:$.toJSON(jsonData),
            dataType:'json', 
            async:false, 
            success:function(data)
            {
            	clearInterval(timea);
            	clearInterval(timeb);
            	count_down=[];
                console.log("后台返回数据："+JSON.stringify(data.returnContent));
                var takedItems = JSON.stringify(data.returnContent);
                localStorage.setItem("takedItems",takedItems);
                $(".pro-ul").eq(1).find("li").remove();
                var str = eval(data.returnContent);
                $.each(str,function(i,k){
                	console.log('系统时间'+str[i].systemTime+',下单时间：'+str[i].orderDate)
                    /*获取系统时间和下单时间*/
                    var arr= str[i].systemTime.split(/[- :]/);
                    var	date1 = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]);
                    var arr2= str[i].orderDate.split(/[- :]/);
                    var	date2 = new Date(arr2[0], arr2[1]-1, arr2[2], arr2[3], arr2[4], arr2[5]);
                    var date3=date1.getTime()-date2.getTime(); 
                    var minute = Math.floor(date3/1000/60);
                    var second =(date3/1000)%60;
                    var countdown=minute+'/'+second;
                    count_down.push(countdown);
                    if(minute>=5){
                    	localStorage.setItem('orderStatus',2);
                    	$(".pro-ul").eq(1).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已取消</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p> </div> </li>');
                    }else{
                    	$(".pro-ul").eq(1).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>待付款</span></p> <p class="check nocheck" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor">'+'5分'+'0秒后订单自动取消</p> <a href="javascript:void(0);" class="buttom daifukuan">付款</a> </div> </li>');
                    }
                    /*获取系统时间和下单时间*/
                });
            },
            error:function(data)
            {
                console.log('失败原因:' + JSON.stringify(data.returnContent));
            }
         });
         if(a==1){
         	if($('.pro-ul2 li').length==0){
         	    $('.delete').hide()
         	}else{
         	    $('.delete').show()
         	}
         }
    }else if(a=="2"){
//      待取货
    	clearInterval(timea);
    	clearInterval(timeb);
    	 count_down=[];
         var url = apiurl + 'api/wechat/getOrdersByStatusWX';
         console.log("URL"+url);
         var jsonData = {"customerId":customerId,"orderStatus":"1"};
         takedItems=[];
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
                $(".pro-ul").eq(2).find("li").remove();
                console.log("后台返回数据："+JSON.stringify(data.returnContent));
                var str = eval(data.returnContent);
                $.each(str,function(i,k){
                    $(".pro-ul").eq(2).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>已支付</span></p> <p class="check nocheck" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor">请根据短信提示按时取餐</p> </div> </li>');
                });
            },
            error:function(data)
            {
                console.log('失败原因:' + JSON.stringify(data.returnContent));
            }
         });
         if(a==2){
         	if($('.pro-ul3 li').length==0){
         	    $('.delete').hide()
         	}else{
         	    $('.delete').show()
         	}
         }
    }else if(a=="3"){
//      待评价
    	clearInterval(timea);
    	clearInterval(timeb);
    	 count_down=[];
         var url = apiurl + 'api/wechat/getOrdersByStatusWX';
         console.log("URL"+url);
         var jsonData = {"customerId":customerId,"orderStatus":"3"};
         takedItems=[];
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
                $(".pro-ul").eq(3).find("li").remove();
                console.log("后台返回数据："+JSON.stringify(data.returnContent));
                var str = eval(data.returnContent);
                $.each(str,function(i,k){
                    $(".pro-ul").eq(3).append('<li class="pro-li pro-liclick"> <p class="location"><img src="../img/image/address_img.png" alt=""><em>'+str[i].address+'</em><span>待评价</span></p> <p class="check" num="1"></p> <div class="leftimg"> <img src="'+apiurl+str[i].machineIcon+'" alt=""> </div> <div class="right-titinf" orderNum='+str[i].orderNum+'> <p class="date">'+str[i].orderDate+'</p> <p class="number" snum="'+str[i].itemCount+'">数量：'+str[i].itemCount +'份</p> <p class="paymoney" money="'+str[i].totalFee+'">共计：'+str[i].totalFee+'元</p> <p class="dy-infor"></p><a href="javascript:void(0);" class="buttom daipingjia">评价</a></div> </li>');
                });
            },
            error:function(data)
            {
                console.log('失败原因:' + JSON.stringify(data.returnContent));
            }
         });
         if(a==3){
         	if($('.pro-ul4 li').length==0){
         	    $('.delete').hide()
         	}else{
         	    $('.delete').show()
         	}
         }
    }
    localStorage.getItem("count_down");
    console.log("本地存储时间："+localStorage.getItem("count_down"));
    $.each(count_down,function(v,k){
    	console.log(v,k);
    	var str=k.split("/")
    	var minute=str[0];
    	var second=str[1];
    	if(second>0){
        	var s=60-second;   //秒
        	var f=5-minute-1;  //分钟
        }else{
        	var f=5-minute;  //分钟
        	var s=00;
        }
        if(s>0 && f>0){
        	run(s,f);
        }
        /*倒计时*/
        function run(s,f){
    		if(s == 0){
    			if(f >= 0){
    				f=f *1 -1;
    				s=60;
    				s=s *1 -1;
    			}else{
    				return false;
    			}
    		}else{
    			s=s *1 -1;
    		}
    		console.log(f+"分"+s+"秒");
    	}
    	if((s!="0"||f!="0")&&(s>0 || f>0)){
    		var timea=setInterval(function(){
    			if(s == 0){
    				if(f > 0){
    					f=f *1 -1;
    					s=60;
    					s=s *1 -1;
    				}else{
    					localStorage.setItem('orderStatus',2);
    					//window.location.href="orderinfo.html";
    					$(".pro-ul").eq(nowul).find(".dy-infor").eq(v).text("");
    					$(".pro-ul").eq(nowul).find(".dy-infor").eq(v).siblings(".daifukuan").hide();
    		    		$(".pro-ul").eq(nowul).find(".location").eq(v).find("span").text("已取消"); 
    					return false;
    				}
    			}else{
    				s=s *1 -1;
    			}
    			$(".pro-ul").eq(nowul).find(".dy-infor").eq(v).text(f+"分"+s+"秒后订单自动取消");
    		},1000);
    	}
    })
}




