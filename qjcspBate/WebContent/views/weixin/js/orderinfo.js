$(document).ready(function(){
	var TisRefun=1;
	var orderStatus;
	checkCustomerId();
    var s,f;
	var orderChildNum=[];
	var orderNum=localStorage.getItem('orderNum');
	if(localStorage.getItem('refundReasonOrderChildStatus').length>0 && localStorage.getItem('TorderChildNum').length>0){
		var refundReasonOrderChildStatus=localStorage.getItem('refundReasonOrderChildStatus');
		var TorderChildNum=localStorage.getItem('TorderChildNum');
		var url = apiurl + 'api/wechat/refundOrderDetailWX';
		var jsonData = {"orderNum":orderNum,"refundReasonOrderChildStatus":refundReasonOrderChildStatus,"orderChildNum":TorderChildNum};
		$.ajax({
	        type:'post',      
	        url:url,
	        data:$.toJSON(jsonData),
	        dataType:'json',
	        async:false,
	        success:function(data)
	        {
	        	 console.log("后台返回数据："+JSON.stringify(data.returnContent));
		            $(".orderList").remove();
		            var str = eval(data.returnContent);
		            orderStatus=str.orderStatus;
		        	console.log(orderStatus);
		        	var totalRefundMoney=data.returnContent.totalRefundMoney;
		            var data=eval(data.returnContent.orderChildList);
		            console.log('系统时间'+str.nowTime+',下单时间：'+str.placeOrderTime);
		            $.each(data,function(v,str){
		            	$(".orderListbox").append('<div class="orderList"> <h1> <span></span> <span></span> <span></span> </h1></div>');
		            	var nordernum='{"orderChildNum":'+str.orderChildNum+'}'
		            	orderChildNum.push(nordernum);
		            	var datetime =str.takenEndTime.substr(5,5);
		                $(".orderList").eq(v).find("span").eq(0).text(datetime);
		                var dateinfor=str.areaModelName+" (取餐时间："+str.takenStartTime.substr(11,5)+"-"+str.takenEndTime.substr(11,5)+")";
		                $(".orderList").eq(v).find("span").eq(1).text(dateinfor);
		                //var $(".orderList")=$(".orderList").eq(v);
		                for(var i=0;i<str.orderDetailList.length;i++){
		                	var listli=str.orderDetailList[i];
		                	$(".orderList").eq(v).append('<p><span class="goodsName">'+listli.goodsName+'</span><span class="goodsCount">x'+listli.goodsNum+'</span><span class="goodsPrice">¥<em>'+listli.itemFee+'</em></span></p>')
		                }
		            });
		            $(".orderList").find("p").eq(0).css("border","none");
		            $(".phoneNumber").text(str.mobile);
		            $(".orderNumber").text(str.orderNum);
		            $(".orderTime").text(str.orderTime);
		            $(".orderAmount").text(str.orderChildNum);
		            $(".ordertotal").find("i").text(str.totalRefundMoney);
		            $(".takeAddress").text(str.address);
		            $(".totalMoney").find("em").text(totalRefundMoney);
		            $(".discount_down").hide();
		            $(".discount_total").find("em").text(str.realMoney);
		            $(".discount_WaitPay").hide();
		            $(".discount_refundMoney").hide();
		            localStorage.setItem('refundReasonOrderChildStatus',"");
		    		localStorage.setItem('orderChildNum',"");
	        },
	        error:function(data){
	        	 console.log("失败返回数据："+JSON.stringify(data.returnContent));
	        }
		});
	}else{
		var url = apiurl + 'api/wechat/getOrderDetailWX';
	    console.log("URL"+url);
	    var jsonData = {"orderNum":orderNum};
	    $.ajax({
	        type:'post',      
	        url:url,  
	        data:$.toJSON(jsonData),
	        dataType:'json', 
	        async:false, 
	        success:function(data)
	        {
	            console.log("后台返回数据："+JSON.stringify(data.returnContent));
	            $(".orderList").remove();
	            var str = eval(data.returnContent);
	            orderStatus=str.orderStatus;
	        	console.log(orderStatus);
	            var data=eval(data.returnContent.orderChilds)
	            console.log('系统时间'+str.nowTime+',下单时间：'+str.placeOrderTime)
	            /*获取系统时间和下单时间*/
	            var arr= str.nowTime.split(/[- :]/);
                var	date1 = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]);
                var arr2= str.placeOrderTime.split(/[- :]/);
                var	date2 = new Date(arr2[0], arr2[1]-1, arr2[2], arr2[3], arr2[4], arr2[5]);
	            var date3=date1.getTime()-date2.getTime(); 
	            var minute = Math.floor(date3/1000/60);
	            var second =(date3/1000)%60;
	            sessionStorage.setItem('minute',minute);
	        	sessionStorage.setItem('second',second);
	            /*获取系统时间和下单时间*/
	            $.each(data,function(v,str){
	            	$(".orderListbox").append('<div class="orderList"> <h1> <span style="margin-left:0.35rem"></span> <span></span> <span></span> </h1></div>');
	            	var nordernum='{"orderChildNum":'+str.orderChildNum+'}'
	            	orderChildNum.push(nordernum);
	            	var datetime =str.takenStartTime.substr(5,5);
	                $(".orderList").eq(v).find("span").eq(0).text(datetime);
	                var dateinfor=str.areaModelName+" (取餐时间："+str.takenStartTime.substr(11,5)+"-"+str.takenEndTime.substr(11,5)+")";
	                $(".orderList").eq(v).find("span").eq(1).text(dateinfor);
	                //var $(".orderList")=$(".orderList").eq(v);
	                for(var i=0;i<str.orderDetails.length;i++){
	                	var listli=str.orderDetails[i];
	                	$(".orderList").eq(v).append('<p><span class="goodsName">'+listli.goodsName+'</span><span class="goodsCount">x'+listli.dishAreaNum+'</span><span class="goodsPrice">¥<em>'+listli.itemFee+'</em></span></p>')
	                }
	                $(".orderList").eq(v).append('<p><span>取餐码</span><span class="reducepick fr" style="margin-left: 3.8rem;"><em>'+str.identifyingCode+'</em></span></p>');
	                if(str.orderChildStatus==14){
	                	$(".orderList").eq(v).find("span").eq(2).text("已取餐");
	                }else{
	                	$(".orderList").eq(v).find("span").eq(2).text("待取餐");
	                }
	        		$(".orderList").eq(v).find("span").eq(2).css({"color":"#fd8432","margin-left":"0.35rem"});
	            });
	            $(".orderList").find("p").eq(0).css("border","none");
	            $(".phoneNumber").text(str.mobileNum);
	            $(".orderNumber").text(str.orderNum);
	            $(".orderTime").text(str.placeOrderTime);
	           
	            $(".orderAmount").text(str.totalNum);
	            $(".ordertotal").find("i").text(str.totalFee);
	            $(".takeAddress").text(str.address);

	            localStorage.setItem("orderType",str.orderType);
	          
	            $(".discount_down").find("em").text(str.discountMoney);
	            $(".discount_total").find("em").text(str.realMoney);
	            $(".discount_WaitPay").hide();
	            $(".discount_refundMoney").hide();
	            var nowtime=str.nowTime;
	            nowtime=new Date(nowtime);
	            $.each(data,function(v,data){
	            	var preissueEndTime=data.preissueEndTime;
	            	preissueEndTime=new Date(preissueEndTime);
	            	console.log("nowTime:"+nowtime+",preissueEndTime:"+preissueEndTime);
	            	var orderChildStatus=data.orderChildStatus;
	            	if (orderChildStatus==4){
	            		TisRefun=0;
	            	}else if(orderChildStatus==1){
	            		
			            /*if(data.isRefund==1&&nowtime<preissueEndTime){
			            	TisRefun=0;
			            }*/
	            		if(data.isRefund==1){
	            			if(nowtime<preissueEndTime){
	            				TisRefun=0;
	            			}               
	            		}else if(data.isRefund==0){
	            			if(nowtime<preissueEndTime){
	            				$('refund').show();
	            			}else if(nowtime>preissueEndTime){
	            				$('.refund').hide();
	            			}
	            		}
	            	}
	            });
	        },
	        error:function(data)
	        {
	            console.log('失败原因:' + JSON.stringify(data.returnContent));
	        }
	    });
	}
    var minute =sessionStorage.getItem('minute');
	var second=sessionStorage.getItem('second');
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
				localStorage.setItem('orderStatus',orderStatus=2);
				window.location.href="orderinfo.html";
				return false;
			}
		}else{
			s=s *1 -1;
		}
		console.log(f+"分"+s+"秒");
	}
	if((s!="0"||f!="0")&&(s>0 || f>0)){
		setInterval(function(){
			if(s == 0){
				if(f > 0){
					f=f *1 -1;
					s=60;
					s=s *1 -1;
				}else{
					localStorage.setItem('orderStatus',orderStatus=2);
					window.location.href="orderinfo.html";
					return false;
				}
			}else{
				s=s *1 -1;
			}
			$(".payTime").find("em").text(f+"分"+s+"秒");
		},1000);
	}
	/*倒计时*/
    //点击已经评价。。跳转评价页面
    $(".daipingjia").live('click',function(){
    	window.location.href = 'goodsEvaluate.html';
    });
    $(".pingjia").live('click',function(){
    	window.location.href = 'goodsEvaluateInfo.html';
    });
    if(orderStatus=="0"){
    	//待付款
    	$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".Payment").removeClass("none");
		$(".Payment").show();
		$(".orderList").find("span").eq(2).text("");
		$(".discount_WaitPay").show();
		$(".returnMain").attr("dname","待付款");
		$(".orderList").find("p:last").hide();
    }
    else if(orderStatus=="1"){
    	//待取货
    	$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".wait").removeClass("none");
		$(".wait").show();
		$(".returnMain").attr("dname","待取货");
		if(TisRefun==0){
			$(".discount_refundMoney").show();
		}else if(TisRefun==1){
			$(".discount_refundMoney").hide();
		}
    }
    else if(orderStatus=="2"||orderStatus=="6"){
    	//订单取消  和 关闭交易
    	$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".cancel").removeClass("none");
		$(".cancel").show();
    }
    else if(orderStatus=="3"){
    	//待评价
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".finish").removeClass("none");
		$(".finish").show();
		$(".linktitle").find("em").text("给个评价吧！");
		$(".linktitle").addClass("daipingjia");
		$(".orderList").find("span").eq(2).text("");
		$(".returnMain").attr("dname","待评价");
	}else if(orderStatus=="4"){
	//订单超时
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".over").removeClass("none");
		$(".over").show();
		$(".discount_refundMoney").show();
		$(".orderList").find("span").eq(2).text("");
		$(".returnMain").attr("dname","订单超时");
	}else if(orderStatus=="5"){
		//待退款
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".ing ").removeClass("none");
		$(".ing ").show();
		$(".orderList").find("span").eq(2).text("");
		$(".returnMain").attr("dname","待退款");
	}else if(orderStatus=="7"){
		//完成
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".finish").removeClass("none");
		$(".finish").show();
		$(".linktitle").hide();
		$(".orderList").find("span").eq(2).text("");
		$(".returnMain").attr("dname","完成");
	}else if(orderStatus=="8"){
		//退款失败
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".success").removeClass("none");
		$(".fail").show();
		$(".orderList").find("span").eq(2).text("");
		$(".returnMain").attr("dname","退款失败");
	}else if(orderStatus=="9"){
	//退款成功
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".fail").removeClass("none");
		$(".success").show();
		$(".orderList").find("span").eq(2).text("");
	}else if(orderStatus=="10"){
	//机器故障
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".repair").removeClass("none");
		$(".repair").show();
		$(".orderList").find("span").eq(2).text("");
		$(".returnMain").attr("dname","机器故障");
		
	}else if(orderStatus=="11"){
	//已预订
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".finish").removeClass("none");
		$(".reserved").show();
		$(".linktitle").hide();
		$(".discount_refundMoney").show();
		$(".returnMain").attr("dname","已预订");
	}else if(orderStatus=="13"){
    	//已评价
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".finish").removeClass("none");
		$(".finish").show();
		$(".linktitle").find("em").text("已评价！");
		$(".linktitle").addClass("pingjia");
		$(".orderList").find("span").eq(2).text("");
	}else if(orderStatus=="14"){
		$(".returnFoodResult").children("div").hide();
		$(".returnFoodReason").hide();
		$(".returnFoodReason").next("div").hide();
		$(".finishgz").removeClass("none");
		$(".finishgz").show();
		//$(".orderList").find("span").eq(2).text("");
		$(".returnMain").attr("dname","待评价");
		$(".linktitle").hide();
		$(".lineTitle").hide();
	}
    //退款
    $(".refund").click(function(){
    	$(".tuikuan").parent().show();
    });
    $(".tuikuan").find(".yesNo").find("span").eq(0).click(function(){
    	console.log("0.00.");
    	var url = apiurl + 'api/wechat/customerOrderRefundApplyWX';
        console.log("URL"+url);
        var customerId = sessionStorage.getItem('customerId'); 
        var orderNum = localStorage.getItem('orderNum');
        var refundComment=$(".returnMain").attr("dname");
        var orderChildNums=orderChildNum;
        var jsonData = {"customerId":customerId,"orderNum":orderNum,"orderChildNums":orderChildNums,"refundComment":refundComment};
        $.ajax({  
            type:'post',      
            url:url,  
            data:$.toJSON(jsonData),
            dataType:'json', 
            async:false, 
            success:function(data)
            {
            	if(data.returnCode=="1"){
            		console.log(data)
                	window.location.href = '../view/Ordertab.html';
            	}else{
            		alert(data.returnContent);	
            	}
            },error:function(data){
            	alert(data.returnContent);
            }
        });
    })
    //取消订单
    $(".cancelOrder").click(function(){
    	$(".dialog_cancel").parent().show();
    });
    $(".dialog_cancel").find(".yesNo").find("span").eq(0).click(function(){
    	console.log("是");
    	var url = apiurl + 'api/wechat/cancelOrderWX';
        console.log("URL"+url);
        var orderNum = localStorage.getItem('orderNum');
        var orderType=localStorage.getItem("orderType");
        var jsonData = {"orderNum":orderNum,"orderType":orderType};
        $.ajax({  
            type:'post',      
            url:url,  
            data:$.toJSON(jsonData),
            dataType:'json', 
            async:false, 
            success:function(data)
            {
            	if(data.returnCode=="1"){
            		console.log(data)
                	window.location.href = '../view/Ordertab.html';
            	}else{
            		alert(data.returnContent);	
            	}
            },error:function(data){
            	alert(data.returnContent);
            }
        });
    })
    $(".yesNo").find("span").eq(1).click(function(){
    	$(".dialog_box").parent().hide();
    });
    $(".yesNo").find("span").eq(3).click(function(){
    	$(".dialog_box").parent().hide();
    });
    $(".pay").click(function(){
    	var totalAmount=$(".totalMoney").find("em").text();
    	localStorage.setItem("totalAmount",totalAmount);
    	window.location.href = '../view/pay.html';
    })
    
});
