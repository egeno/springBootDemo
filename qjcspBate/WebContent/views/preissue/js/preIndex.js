var userSelectDate;
$(function(){
	var mealsLen=0;
	/*取消？确定？*/
	$('.no').click(function(){
		$(this).parents('.dialog').hide();
	});
	yes();
	
	/*------首页------*/
	indexDate();/*首页日期*/
	dateDialog();/*点击发布时的日期弹窗*/	

	indexMeals(mealsLen);/*首页餐品列表生成*/
	/*for(var j=0;j<$('.mm').eq($index).find('ul li').length;j++){
		$('.mm').eq($index).find('.bg').attr("src",urls['ctx'] +"/views/preissue/image/overmealbg2.png");
	}*/
//	layout(mealsLen);/*初始化布局样式*/
//	mealDialog();/*点击早中晚宴的效果,及弹窗效果*/
	/*点击关闭*/
	$('.close').click(function(){
		$(this).parents('.dialog').hide();
	});
	/*删除餐品*/
	var mealName=localStorage.getItem("mealName")
	$('.deleteDialog').find('.deleteDialogText em').html(mealName).attr('title',mealName).end().find('.yes').click(function(){
		var issueId=localStorage.getItem("issueId");
		deleteDialog(issueId,userSelectDate);
	});
	function deleteDialog(issueId,userSelectDate)
	{
		var jsonData={"issueId":issueId,"userSelectDate":userSelectDate};				
		$.ajax({  
		    type:'post',      
		    url:'preissue/deleteIssue',
		    dataType:'json', 
		    data:jsonData,
		    async:false, 
		    success:function(data){
		    	if(data.status==true){
		    		$('.dialog').hide();
		    		$('.deleteSuccessDialog').show();
		    		$(".yes").parents('.dialog').hide();

		    		setTimeout(function(){
		    			$(".deleteSuccessDialog").hide();
		    			$meal.hide().empty();},1000
		    			);//定时器 					    			
		    	}else{
		    		//删除失败
		    		$('.dialog').hide();
		    		$('.deleteFailDialog').show();
		    		setTimeout(function(){$(".deleteFailDialog").hide();},1000);//定时器 
		    	}
		    }
	   });
	}
	/*------发布------*/
	/*点击发布*/
	$('.releaseDialogBtn').click(function(){
		var issues = new Array();  
		var date=$('.releaseLeft_top').find('.realDate').html();
		$(".releaseLeftMeal li").each(function(i){
			var gid=$(this).find('#gid').val();
			var amid=$(this).find('#amid').val();
			//var num=$(this).find('em').text();
			issues.push({goodsId: gid,areaModelId: amid,issueTime:date});
		});
		if(issues.length>0){
			$.ajax({
			    type: "POST",
			    url: "issue/saveIssueList",
			    data: JSON.stringify(issues),//将对象序列化成JSON字符串  
			    dataType:"json",
			    contentType : 'application/json;charset=utf-8', //设置请求头信息  
			    success: function(data){
			    	if(data){
			    		$('.dialog').hide();
						$('.releasemreleasemSuccess').show();
						setTimeout(alertMessage, 1500);
					}
			    }
			});
		}else{
			$.messager.show({
				title : '提示',
				msg : '请选择商品后再进行发布',
				timeout : 1500,
			});
		}
	});
	/*点击返回*/
	$('.back').click(function(){
		$('.releaseMainDialog').hide();
		$('.preissueBox').show();
	});
	
	/*//如果没有选择左侧的餐类型就添加商品，则提醒用户
	$('.releaseRight_meal').find('li').live("click",function(){
		//如果没有选择左侧的餐类型就添加商品，则提醒用户
		if(modelId==""){
			$.messager.show({
				title : '提示',
				msg : '请选择左侧上方的餐类型再进行添加',
				timeout : 1500,
			});
		}
	});*/
	
});
function indexMeals(mealsLen,$Week){
	var issueId = localStorage.getItem("issueId");
	//var jsonData={"deviceCode":deviceCode,"idCode":code};
	var now=systemNowDate();
	var jsonDatas={"date":CurentTime(now)};
	 $.ajax({  
		    type:'post',      
		    url:'preissue/loadDataIndex',
		    dataType:'json', 
		    data:jsonDatas,
		    async:false, 
		    success:function(data){
		    	mealsLen=data.length;
				for(var i=0;i<data.length;i++){
					var issue=data[i];
					if(issue[issue.length-1].areaModelName>5){
						$('.preissueBottomLeft').append("<div  value='"+i+"' title='"+issue[issue.length-1].areaModelName+"'>"+issue[issue.length-1].areaModelName+"</div>");
						$('.preissueBottomLeft').find('div').eq(i).css({'box-sizing':'border-box','padding-top':'50px','line-height':'18px'});
					}else{
				 		$('.preissueBottomLeft').append("<div  value='"+i+"' title='"+issue[issue.length-1].areaModelName+"'>"+issue[issue.length-1].areaModelName+"</div>");
				 	}
					$('.preissueBottomLeft div:even').addClass('gray');
					$('.preissueBottomLeft div:odd').addClass('white');
					//添加分类餐品
					$('.preissueBottomContent').append("<div class='mm' value='"+i+"'><div class='mm_main'><ul class='Meal_main'></ul></div></div>");
					
					for(var j=0;j<issue.length-1;j++){
//						$('.mm').eq(i).find('ul').append("<li><img class='bg' src="+urls['ctx'] +"/views/preissue/image/mealbg.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+issue[j].goodsName+"</span><span class='num'><em>"+issue[j].issueNum+"</em>份</span></div>");
						if($('.nowday').hasClass('timeout')){
							var goodsName=issue[j].goodsName.length>15?issue[j].goodsName.substring(0,15)+"...":issue[j].goodsName;
							$('.mm').eq(i).find('ul').append("<li title='"+issue[j].goodsName+"'><img class='bg' src="+urls['ctx'] +"/views/preissue/image/overmealbg2.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+goodsName+"</span></div>");
						}else{
							var goodsName=issue[j].goodsName.length>15?issue[j].goodsName.substring(0,15)+"...":issue[j].goodsName;
							$('.mm').eq(i).find('ul').append("<li title='"+issue[j].goodsName+"'><img class='bg' src="+urls['ctx'] +"/views/preissue/image/mealbg2.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+goodsName+"</span></div>");
						}
						
					}				
				}
		    }
	 });
	 /*
	 * 林智 2016.5.10修改文字溢出
	 * */
	 /*$('.preissueBottomLeft').find('div').hover(function(){
		 	
		var name=$(this).text();
		var index=$(this).attr('value');
		alert(index);
		if(name.length>0){
			$('.mm').eq(index).append("<div class='allname'>"+name+"</div>");
			$('.allname').width($('.mm_main').width()-33);
		}
	 },function(){
		 	$(this).css({'box-sizing':'border-box','padding-top':'45px','line-height':'none'});
	 });*/
	 //ccc
	 $('.Week').click(function(){
		 var selectDate=$(this).find('div').text();
		    //alert(selectDate);
		 	//$(this).find('.weekDate').empty();
	    	$(this).css({'color':"#fff",'background':"url("+urls['ctx'] +"/views/preissue/image/nowday.png)",'backgroundSize':'cover'}).end().find('div').css({'borderRight':'none'});
	    	$(this).siblings().css({'color':"#090909",'background':"",'backgroundSize':'cover'}).end().find('div').css({'borderRight':'1px solid #d9d9d9'});
	    	$('.preissueBottomLeft').empty();
	    	$('.preissueBottomContent').empty();
	    	$Week=$(this);
	    	selectDate=selectDate.substring(0,10);
	    	userSelectDate=selectDate;
	    	var jsonData={"date":selectDate};
	    	$.ajax({  
			    type:'post',      
			    url:'preissue/loadDataIndex',
			    dataType:'json', 
			    data:jsonData,
			    async:false, 
			    success:function(data){
			    	mealsLen=data.length;
					for(var i=0;i<data.length;i++){
						var issue=data[i];
						if(issue[issue.length-1].areaModelName>5){
							$('.preissueBottomLeft').append("<div  value='"+i+"'>"+issue[issue.length-1].areaModelName+"</div>");
							$('.preissueBottomLeft').find('div').eq(i).css({'box-sizing':'border-box','padding-top':'50px','line-height':'18px'});
						}else{
					 		$('.preissueBottomLeft').append("<div  value='"+i+"'>"+issue[issue.length-1].areaModelName+"</div>");
					 	}
						$('.preissueBottomLeft div:even').addClass('gray');
						$('.preissueBottomLeft div:odd').addClass('white');
						//添加分类餐品
						$('.preissueBottomContent').append("<div class='mm' value='"+i+"'><div class='mm_main'><ul class='Meal_main'></ul></div></div>");
						if($Week.hasClass('timeout')){
							for(var j=0;j<issue.length-1;j++){
								var goodsName=issue[j].goodsName.length>15?issue[j].goodsName.substring(0,15)+"...":issue[j].goodsName;
//								$('.mm').eq(i).find('ul').append("<li><img class='bg' src="+urls['ctx'] +"/views/preissue/image/overmealbg.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+issue[j].goodsName+"</span><span class='num'><em>"+issue[j].issueNum+"</em>份</span></div>");
								$('.mm').eq(i).find('ul').append("<li title='"+issue[j].goodsName+"'><img class='bg' src="+urls['ctx'] +"/views/preissue/image/overmealbg2.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+goodsName+"</span></div>");
							}
				    	}
						else{
							for(var j=0;j<issue.length-1;j++){
								var goodsName=issue[j].goodsName.length>15?issue[j].goodsName.substring(0,15)+"...":issue[j].goodsName;
//								$('.mm').eq(i).find('ul').append("<li><img class='bg' src="+urls['ctx'] +"/views/preissue/image/mealbg.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+issue[j].goodsName+"</span><span class='num'><em>"+issue[j].issueNum+"</em>份</span></div>");
								$('.mm').eq(i).find('ul').append("<li title='"+issue[j].goodsName+"'><img class='bg' src="+urls['ctx'] +"/views/preissue/image/mealbg2.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+goodsName+"</span></div>");
							}
		
						}
					}
					/*
					 * 林智 2016.5.10修改文字溢出
					 * */
					/*$('.preissueBottomLeft').find('div').hover(function(){
						var name=$(this).text();
						var index=$(this).attr('value');
						alert(index);
						if(name.length>0){
							$('.mm').eq(index).append("<div class='allname'>"+name+"</div>");
							$('.allname').width($('.mm_main').width()-33);
						}
					 },function(){
							$('.allname').remove();
					 });*/
			    }
	    	});
	    	 layout(mealsLen);
	    	 if(!$Week.hasClass('timeout')){
	    		 mealDialog();
	    	 }
	    	 $.each($('.mealName'), function(i, value) {
	    			if($(this).find('.name').text().length<7){
	    				$(this).find('.name').css({'marginTop':'30px'});
	    			}
	    	});
	    });
	 layout(mealsLen);
	 mealDialog();
	 $.each($('.mealName'), function(i, value) {
			if($(this).find('.name').text().length<7){
				$(this).find('.name').css({'marginTop':'30px'});
			}
	});
	/*------发布------*/
	/*点击发布*/
	/*$('.releaseDialogBtn').click(function(){
		$(".releaseLeftMeal li").each(function(i){
			var gid=$(this).find('#gid').val();
			var amid=$(this).find('#amid').val();
			var num=$(this).find('.num em').text();
			alert(gid+"份数:"+num);
		});
		//$('.releasemreleasemSuccess').show();
		//跳转查询页面
		//setTimeout(alertMessage,2000);
	});
	点击返回
	$('.back').click(function(){
		$('.releaseMainDialog').hide();
		$('.preissueBox').show();
	});*/
	
	$.each($('.mealName'), function(i, value) {
		if($(this).find('.name').text().length<7){
			$(this).find('.name').css({'marginTop':'30px'});
		}
	})
}
//全部清空事件
function bindClear(){
	/*全部清空*/
	$('.releaseLeftClear').click(function(){
		var $li=$(".releaseLeftMeal").find('.look').find('li');
		for(var i=0;i<$li.length;i++){
			var issueId=$li.eq(i).find('#issueId').val();
			var gid=$li.eq(i).find('#gid').val();
			var amid= $li.eq(i).find('#amid').val();
			if(issueId==undefined){
				$li.eq(i).remove();
				//重新加载右侧可选商品
				getFoodsList(goodsCategoryId,modelId);
			}else{
				 $.ajax({
					    type:'post',      
					    url:'issue/delIssue',
					    data:{'issueId':issueId,'goodsId':gid},
					    dataType:'json', 
					    async:false, 
					    success:function(data){
					    	if(data.result){
					    		//页面移除商品
								$li.eq(i).remove();
								//重新加载右侧可选商品
								getFoodsList(goodsCategoryId,modelId);
					    		$('.releasemDeleteDialog').hide();
					    		$.messager.show({
									title : '提示',
									msg : data.message,
									timeout : 1000,
								});
					    	}else{
					    		$.messager.show({
									title : '提示',
									msg : data.message,
									timeout : 1000,
								});
					    	}
					    }
				 });
			}
		}
	});
}

//删除事件
function bindDel(){
	var $liindex=$('.look').attr('value');
	var $ul=$('.releaseLeftMeal ul').eq($liindex);
	/*发布页左边的列表经过的时候*/
	$ul.find('li').hover(function(){
		$thisli=$(this);
/*--------------------加载了两遍？*/
		$thisli.find('.mask').remove();
		//$thisli.append("<div class='mask'><img  class='maskbg' src="+urls['ctx'] +"/views/preissue/image/indexmask.png><div class='releaseDelete'><img src="+urls['ctx'] +"/views/preissue/image/delete.png></div><div class='releaseUpdate'><img src="+urls['ctx'] +"/views/preissue/image/update.png></div></div>");
		$thisli.append("<div class='mask delMask'><img  class='maskbg' src="+urls['ctx'] +"/views/preissue/image/delmask.png><div class='releaseDelete'><img src="+urls['ctx'] +"/views/preissue/image/delete.png></div></div>");
		var mealNum=$thisli.find('.releaseLeftMeal_num em').html();
		var mealName=$thisli.find('.releaseLeftMeal_name').html()
		
		/*点击删除修改出现的对话框*/
		$('.releaseLeftMeal .mask').width($ul.find('li').width()-10);
		$thisli.find('.releaseDelete').click(function(){
			$('.releasemDeleteDialog').show().find('.deleteDialogText em').html(mealName).attr("title",mealName);
		});
		
	},function(){
		$thisli.find('.mask').remove();
	});
}

function alertMessage(){
	//跳转到商品订单发布查询页面
	window.location.href=urls['ctx']+"/preissue/preIssueIndex";
}


function getIssueByDate(date){
	var jsonData={"date":date};
	var mealsLen=0;
	 $.ajax({  
		    type:'post',      
		    url:'preissue/loadDataIndex',
		    dataType:'json', 
		    data:jsonData,
		    async:false, 
		    success:function(data){
		    	mealsLen=data.length;
				for(var i=0;i<data.length;i++){
					var issue=data[i];
					$('.preissueBottomLeft').append("<div  value='"+i+"'>"+issue[issue.length-1].areaModelName+"</div>");
					$('.preissueBottomLeft div:even').addClass('gray');
					$('.preissueBottomLeft div:odd').addClass('white');
					//添加分类餐品
					$('.preissueBottomContent').append("<div class='mm' value='"+i+"'><div class='mm_main'><ul class='Meal_main'></ul></div></div>");
					for(var j=0;j<issue.length-1;j++){
						//$('.mm').eq(i).find('ul').append("<li><img class='bg' src="+urls['ctx'] +"/views/preissue/image/mealbg.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+issue[j].goodsName+"</span><span class='num'><em>"+issue[j].issueNum+"</em>份</span></div>");
						$('.mm').eq(i).find('ul').append("<li><img class='bg' src="+urls['ctx'] +"/views/preissue/image/mealbg2.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+issue[j].goodsName+"</span></div>");
					}
				}
		    }
	 });
	 return mealsLen;
}

function CurentTime(date)
{ 
    
   
    var year = date.getFullYear();       //年
    var month = date.getMonth() + 1;     //月
    var day = date.getDate();            //日
   
/*      var hh = now.getHours();            //时
    var mm = now.getMinutes();*/          //分
   
    var clock = year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day + " ";
   
   /* if(hh < 10)
        clock += "0";
       
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm;*/ 
    return(clock); 
}

function layout(mealsLen){
	var preissueHeight=$('.preissueBox').parent().height();
	var preissueWidth=$('.preissueBox').parent().width();
	var MealWidth=preissueWidth-135-$('.preissueBottomRight').width();
	var preissueContentHeight=preissueHeight-60-20;
	if(mealsLen<5){
		var MealHeight=preissueContentHeight/mealsLen;
		var MealMainHeight=MealHeight-30;
		$('.preissueBottomLeft div').height(MealHeight).css('lineHeight',MealHeight+'px');
		$('.preissueBottomContent .mm').height(MealHeight-20);
	}
	$('.preissueBottomMain').height(preissueContentHeight).width($('.preissueBottom').width()-$('.preissueBottomRight').width()-2);
	$('.preissueBottomRight').height(preissueContentHeight);
	

	$('.mm').width(MealWidth+10);
	$(".preissueBottomMain_content").width($('.mm').width()+100);
	var releaseLineHeight=$('.releaseBtn').height();
	$('.releaseBtn').css({'line-height':releaseLineHeight+'px'});
	
	/*早中晚夜餐单的布局*/
	var Meal_mainLiWidth=$('.mm_main').width()*0.1566;
	$('.mask').width(Meal_mainLiWidth)
	$('.mm li').width(Meal_mainLiWidth);/*餐单列的宽度*/
	sorll();
	if(screen.width<=1368){
		var Meal_mainLiWidth=$('.mm').width()*0.1929;
		$('.mask').width(Meal_mainLiWidth)
		$('.mm li').width(Meal_mainLiWidth);/*餐单列的宽度*/
		sorll();
		$('.Meal_main').css({'marginTop':'0px'});

	}
	if(screen.width<=1300){
		var top=$('.mm').height()*0.10;
		$('.Meal_main').css({'marginTop':top});
	}
	if(screen.height<=700){
		$('.Meal_main li').height(70);
		$('.mask').height(60);
	}
	if(screen.width<=1662 && screen.width>=1550){
		$('.mealName .name').css({'marginLeft':'15px','fontSize':'12px'});
	}
	if(screen.width<=1448 && screen.width>=1380){
		$('.mealName .name').css({'marginLeft':'4px'});
	}
	if(screen.width<=1368){
		$('.Meal_main').css({'marginTop':'0px'});
		$('.mealName .name').css({'marginLeft':'10px'});
		//$('.mm ul li').css({'background':'none'}).append("<img class='bg' src="+urls['ctx'] +"/views/preissue/image/mealbg.png>");
		$('.mm ul li').css({'background':'none'}).append("<img class='bg' src="+urls['ctx'] +"/views/preissue/image/mealbg2.png>");
	}
}

/*出现滚动条*/
function sorll(){
	var top=$('.mm').height()*0.12;

	/*如果超过长度就出现滚动条*/
	for(var i=0;i<$('.mm').length;i++){
		var mealswidth=$('.Meal_main li').width()*$('.mm').eq(i).find('.Meal_main').find('li').length+220;
		$('.mm').eq(i).find('.Meal_main').css({'width':mealswidth,'marginTop':top});
		
		if($('.mm').eq(i).find('.Meal_main').width()>$('.Meal_main').parent().width()){
			$('.mm').eq(i).css({'overflow':'auto'});
		}
	}

}

function yes()
{
	$('.updateDialog').find('.yes').click(function(){
		$(this).parents('.dialog').hide();
		var issueId = localStorage.getItem("issueId");
		var $num=$(this).parents(".updateDialog_main").find('input').val();
		//alert($num);
		//$meal.find('.num em').text($num);
		//alert(issueId+'-'+$num);
		//alert($num);
		//$meal.find('.num em').text($num);
		if($num>0){
			var jsonData={"issueId":issueId,"issueNum":$num};
			$.ajax({  
			    type:'post',      
			    url:'preissue/updateIssue',
			    dataType:'json', 
			    data:jsonData,
			    async:false, 
			    success:function(data){
			    	//refreshTab($('.tabs-selected').find('.tabs-title').text());
			    	if(data.status==true){
			    		$('.dialog').hide();
			    		$('.updateSuccessDialog').show();
			    		setTimeout(function(){$(".updateSuccessDialog").hide();
			    		$meal.find('.num em').text($num);},1000);//定时器
			    		
			    	}
			    	else{
			    		$('.dialog').hide();
			    		$('.updateFailDialog').show();
			    		setTimeout(function(){$(".updateFailDialog").hide();},1000);//定时器 	
			    	}
			    }
			});
		}
		else{
			$('.dialog').hide();
			$('.inputDialog').show();
			$('.inputDialog .close').click(function(){
				$('.inputDialog').hide();
			});
			setTimeout(function(){$(".inputDialog").hide();},1000);//定时器 
			
		}
	});
}

function mealDialog(){
	$('.Meal_main').find('li').hover(function(){
		$meal=$(this);
			if($(this).hasClass('over')==false){
				//$(this).find('.mealName').append("<div class='mask'><img  class='maskbg' src="+urls['ctx'] +"/views/preissue/image/indexmask.png><div class='indexDelete'><img src="+urls['ctx'] +"/views/preissue/image/delete.png></div><div class='indexUpdate'><img src="+urls['ctx'] +"/views/preissue/image/update.png></div></div>");
				$(this).find('.mealName').append("<div class='mask delMask'><img  class='maskbg' src="+urls['ctx'] +"/views/preissue/image/delmask.png><div class='indexDelete indexDel'><img src="+urls['ctx'] +"/views/preissue/image/delete.png></div></div>");
				var mealNum=$(this).find('.num em').html();
				var mealName=$(this).find('.name').html();
				//取值
				localStorage.setItem("mealName",mealName);
				$('.mask').width($('.mm li').width()-10);
				/*$(this).find('.indexDelete').click(function(){
					var issueId=$(this).parents(".mealName").find('input').val();
					var mmvalue=$(this).parents('.mm').attr('value');
					
					
					$('.deleteDialog').show().find('.deleteDialogText em').html(mealName).end().find('.yes').click(function(){
						alert(userSelectDate);
						deleteDialog(issueId,userSelectDate);
					});
					
					
				});*/
			
			$(this).find('.indexUpdate').click(function(){
				   var issueId=$(this).parents(".mealName").find('input').val();
				   localStorage.setItem("issueId",issueId);
				   //alert(issueId);
					$('.updateDialog').show().find('input').val(mealNum).end().find('.updateMealName').html(mealName);
					/*
					$('.updateDialog').find('.yes').click(function(){								
						var $num=$(this).parent().find('input').val();
						//$meal.find('.num em').text($num);
						alert(issueId+'-'+$num);
						//alert($num);
						//$meal.find('.num em').text($num);
						if($num>0){
							var jsonData={"issueId":issueId,"issueNum":$num};
							$.ajax({  
							    type:'post',      
							    url:'preissue/updateIssue',
							    dataType:'json', 
							    data:jsonData,
							    async:false, 
							    success:function(data){
							    	//refreshTab($('.tabs-selected').find('.tabs-title').text());
							    	if(data.status==true){
							    		$('.updateSuccessDialog').show();
							    		setTimeout(function(){$(".updateSuccessDialog").hide();
							    		$meal.find('.num em').text($num);},1000);//定时器
							    		
							    	}
							    	else{
							    		$('.updateFailDialog').show();
							    		setTimeout(function(){$(".updateFailDialog").hide();},1000);//定时器 	
							    	}
							    }
							});
						}
						else{
							$('.inputDialog').show();
				    		$('.inputDialog .close').click(function(){
				    			$('.inputDialog').hide();
				    		});
				    		setTimeout(function(){$(".inputDialog").hide();},3000);//定时器 
							
						}
					});*/
				});
			}
		},function(){
			if($(this).hasClass('over')==false){
				$(this).find(".mask").remove();
			}
	});
	
	$(document).on('click', '.indexDelete', function () {
		$('.deleteDialogText').find('em').text();
		var issueId=$(this).parents(".mealName").find('input').val();
		localStorage.setItem("issueId",issueId);
		var foodname=$(this).parents(".mealName").find('.name').text();
		$('.dialog').hide();
		$('.deleteDialog').show();
		$('.deleteDialogText').find('em').text(foodname);
		$('.deleteDialogText').attr("title",foodname);
	});
	
}

function releasemMealDialog(a){
	var getgid="";
	var getamid="";
	/*发布页右边的列表点击的的时候*/
	$('.releaseRight_meal').find('li').live("click",function(){
		//获取食品所对应的modelid和goodsid
		getgid=$(this).find('#gid').val();
		getamid=$(this).find('#amid').val();
		//alert("点击时获取到的食品id:"+getgid+"点击时获取到的模型id:"+getamid);
		var farg;
		if(modelId!=""){
			var rightMealName=$(this).find('div').text();
			//$('.releasemUpdateDialog').show().find('input').val('').end().find('.updateMealName').html(rightMealName);
			$('.releaseRight .dialog').height($('.releaseLeft').height());
		}
		
		
		/*如果有同名的餐品就改变左侧对应的数量，如果没有同名的就在左侧添加*/
//		$('.releasemUpdateDialog').find('.yes').click(function(){
//			var LeftMealName=$(this).siblings(".mealNum").find('.updateMealName').text();
//			var LeftMealNum=$(this).siblings(".mealNum").find('input').val();
			var LeftMealName=$(this).find("div").text();
			var $liindex=$('.look').attr('value');
			var $ul=$('.releaseLeftMeal ul').eq($liindex);
//			if(LeftMealNum>0){
				for(var i=0;i<$ul.find('li').length;i++){
					if($ul.find('li').eq(i).find('.releaseLeftMeal_name').text()==LeftMealName){
						farg=true;
						break;
					}
				}
				if(farg)
				{
//					$ul.find('li').eq(i).find('.releaseLeftMeal_num em').html(LeftMealNum);
					//alert('同');
				}
				else
				{
					//如果没有选择左侧的餐类型就添加商品，则提醒用户
					if(modelId==""){
						$.messager.show({
							title : '提示',
							msg : '请选择左侧上方的餐类型再进行添加',
							timeout : 1500,
						});
					}else{
						$(this).remove();	
						$ul.append("<li title='"+LeftMealName+"'><input id='gid' type='hidden' value='"+getgid+"'/><input id='amid' type='hidden' value='"+getamid+"'/><img src="+urls['ctx'] +"/views/preissue/image/releaseLeftMealBg.png><div class='releaseLeftMeal_main'><span class='releaseLeftMeal_name'>"+LeftMealName+"</span></div></li>");
					}
					//if(LeftMealNum!=0){
						//alert("接收到的食品id:"+getgid+"接收到的模型id:"+getamid);
						//$ul.append("<li><input id='gid' type='hidden' value='"+getgid+"'/><input id='amid' type='hidden' value='"+getamid+"'/><img src="+urls['ctx'] +"/views/preissue/image/releaseLeftMealBg.png><div class='releaseLeftMeal_main'><span class='releaseLeftMeal_name'>"+LeftMealName+"</span><span class='releaseLeftMeal_num'><em>"+LeftMealNum+"</em>份</span></div></li>");
					//}
				}
//			}
//			else{
//				$('.releasemInputDialog').show();
//	    		$('.releasemInputDialog .close').click(function(){
//	    			$('.releasemInputDialog').hide();
//	    		});
//	    		setTimeout(function(){$(".releasemInputDialog").hide();},3000);//定时器 
//			}
				/*发布页左边的列表经过的时候*/
				$ul.find('li').hover(function(){
					$thisli=$(this);
	/*--------------------加载了两遍？*/
					$thisli.find('.mask').remove();
					//$thisli.append("<div class='mask'><img  class='maskbg' src="+urls['ctx'] +"/views/preissue/image/indexmask.png><div class='releaseDelete'><img src="+urls['ctx'] +"/views/preissue/image/delete.png></div><div class='releaseUpdate'><img src="+urls['ctx'] +"/views/preissue/image/update.png></div></div>");
					$thisli.append("<div class='mask delMask'><img  class='maskbg' src="+urls['ctx'] +"/views/preissue/image/delmask.png><div class='releaseDelete'><img src="+urls['ctx'] +"/views/preissue/image/delete.png></div></div>");
					var mealNum=$thisli.find('.releaseLeftMeal_num em').html();
					var mealName=$thisli.find('.releaseLeftMeal_name').html()
					
					/*点击删除修改出现的对话框*/
					$('.releaseLeftMeal .mask').width($ul.find('li').width()-10);
					$thisli.find('.releaseDelete').click(function(){
						$('.releasemDeleteDialog').show().find('.deleteDialogText em').html(mealName).attr("title",mealName);
					});
					/*发布页左边删除的时候*/
					/*$('.releasemDeleteDialog .yes').click(function(){
//						$.ajax({  
//						    type:'post',      
//						    url:'preissue/deleteIssue',
//						    dataType:'json', 
//						    data:jsonData,
//						    async:false, 
//						    success:function(data){
//						    	if(data.status==true){
						    		$thisli.remove();
						    		$('.releasemDeleteDialog').hide();
//						    		$('.releasemDeleteSuccessDialog').show();
//						    		setTimeout(function(){
//						    			$(".releasemDeleteSuccessDialog").hide();
//						    			$meal.hide().empty();},1000
//						    			);//定时器 					    			
//						    	}else{
//						    		//删除失败
//						    		$('.releasemDeleteFailDialog').show();
//						    		setTimeout(function(){$(".releasemDeleteFailDialog").hide();},1000);//定时器 
//						    	}
//						    }
//						});
					});*/
					/*更新*/
					$thisli.find('.releaseUpdate').click(function(){
						$('.releasemUpdateDialog').show().find('input').val(mealNum).end().find('.updateMealName').html(mealName);
					});
					/*发布页左边修改的时候*/
					$('.releasemUpdateDialog .yes').click(function(){
						var LeftMealName=$(this).siblings(".mealNum").find('.updateMealName').text();
						var LeftMealChangeNum=$(this).siblings(".mealNum").find('input').val();
						if(LeftMealChangeNum>0){
							for(var i=0;i<$('.releaseLeftMeal li').length;i++){
								if($('.releaseLeftMeal li').eq(i).find('.releaseLeftMeal_name').text()==LeftMealName){
									$('.releaseLeftMeal li').eq(i).find('.releaseLeftMeal_num em').text(LeftMealChangeNum);
								}
							}
//							$.ajax({  
//							    type:'post',      
//							    url:'preissue/updateIssue',
//							    dataType:'json', 
//							    data:jsonData,
//							    async:false, 
//							    success:function(data){
//							    	//refreshTab($('.tabs-selected').find('.tabs-title').text());
//							    	if(data.status==true){
//							    		$('.releasemUpdateSuccessDialog').show();
//							    		setTimeout(function(){$(".releasemUpdateSuccessDialog").hide();},1000);//定时器 	
//							    	}
//							    	else{
//							    		$('.releasemUpdateFailDialog').show();
//							    		setTimeout(function(){$(".releasemUpdateFailDialog").hide();},1000);//定时器 	
//							    	}
//							    }
//							});
						}else{
							$('.releasemInputDialog').show();
				    		$('.releasemInputDialog .close').click(function(){
				    			$('.releasemInputDialog').hide();
				    		});
				    		setTimeout(function(){$(".releasemInputDialog").hide();},3000);//定时器 
						}
						
					});
					
					},
					function(){
						$(this).find(".mask").remove();
				});
				

			bindClear();
//		});
		
		
	});
	/*发布页右边类别的切换*/
	$('.releaseRight_tab li').click(function(){
		$(this).addClass('tabActive').end().siblings().removeClass('tabActive');
		
		$(this).find('img').attr('src',urls['ctx'] +"/views/preissue/image/tabActive.png");
		$(this).siblings().find('img').attr('src',urls['ctx'] +"/views/preissue/image/releaseRightTabBg.png");
		
	});
	
}

function indexDate(){
	var cells = $('.Week div');
    var clen = cells.length;
    var currentFirstDate;
    var formatDate = function(date){
    	var year= date.getFullYear();
        var month = (date.getMonth()+1)+'月';
        var day = date.getDate();
        var realMonth=date.getMonth()+1;
        var fullMonth="";
        if(realMonth<10){
        	fullMonth='0'+realMonth;
        }else{
        	fullMonth=realMonth;
        }
        var fullDate='';
        if(day<10){
        	fullDate='0'+day;
        }else{
        	fullDate=day;
        }
        var week =['星期日','星期一','星期二','星期三','星期四','星期五','星期六'][date.getDay()];
        return "<span class='none'>"+year+'-'+fullMonth+'-'+fullDate+"</span>"+month+'<span>'+day+'</span>'+'日'+'<br/>'+week;
    };
    var addDate= function(date,n){       
        date.setDate(date.getDate()+n);
        return date;
    };
    var setDate = function(date){
    	/*var defineDate=date;*/
        var week = date.getDay()-1;
        date = addDate(date,week*-1);
        //yejianhui
        var currentDate;
        var nowDate;
        var preissueEndTime;
        $.ajax({  
		    type:'post',      
		    url:'preissue/getCurrentTime',
		    dataType:'json', 
		    //data:jsonData,
		    async:false, 
		    success:function(data){
		    	nowDate = new Date(data.message);
	    		currentDate=nowDate.getDate();
		    	if(data.status==true){
		    		preissueEndTime=new Date(data.title);
		    		//alert(data.message+'--'+data.title);
		    	}else{
		    		$.messager.show({
						title : '提示',
						msg : data.title,
						timeout : 1500,
					});
		    	}
		    	
		    }
		});
        currentFirstDate = new Date(date);
        for(var i = 0;i<clen;i++){
        	/*如果今天的日期和填进去的日期相同那么就高亮显示*/
        	//$('.Week').eq(i).append("<div class='weekDate none'>"+CurentTime(date)+"</div>");
        	/*alert(date+','+preissueEndTime);*/
        	if(date.getDate()==currentDate){
        		cells[i].innerHTML = formatDate(i==0 ? date : addDate(date,0));
        		$('.Week').eq(i).addClass('nowday');
        		$('.Week').eq(i).css({'color':"#fff",'background':"url("+urls['ctx'] +"/views/preissue/image/nowday.png)",'backgroundSize':'cover'});       		
            	$('.Week').eq(i).find('div').css({'borderRight':'none'});
            	userSelectDate=$('.Week').eq(i).find('div').text();
            	userSelectDate=userSelectDate.substring(0,10);
            	var $index=i;
            	/*点击本周的时候让该亮的亮起了*/
        		$('.last-week').click(function(){
	       			$('.Week').eq($index).css({'color':"#fff",'background':"url("+urls['ctx'] +"/views/preissue/image/nowday.png)",'backgroundSize':'cover'});       		
            		$('.Week').eq($index).find('div').css({'borderRight':'none'});  
            		
           		});
        		if(nowDate>preissueEndTime){
        			$('.Week').eq(i).addClass('timeout');
        		}
        	}
        	else if(date.getDate()<nowDate.getDate()){/*如果是过去的时间那么给它一个名为timeout的class*/
        		cells[i].innerHTML = formatDate(i==0 ? date : addDate(date,0));/*把日期一个一个填进去*/
        		$('.Week').eq(i).addClass('timeout');
        		//ccc
        	}
        	else{
        		cells[i].innerHTML = formatDate(i==0 ? date : addDate(date,0));/*把日期一个一个填进去*/
        	}
        	

        	addDate(date,1)
        }
    };
    
    /*本周？下周？*/
    $('.last-week').click(function(){
    	//$('.Week').find('.weekDate').empty();
      setDate(addDate(currentFirstDate,-7));
      
      $(this).hide();
      $('.next-week').show();
      $('.lookWeek').addClass('this');
      for(var i=0;i<=7;i++){
			$('.Week').eq(i).css({'color':"#090909",'background':"",'backgroundSize':'cover'});       		
			$('.Week').eq(i).find('div').css({'borderRight':'1px solid #d9d9d9'});
			
      }
      
    });
     
    $('.next-week').click(function(){
    	//$('.Week').find('.weekDate').empty();
        setDate(addDate(currentFirstDate,7));
        $('.Week').removeClass('timeout');
      $('.last-week').show();
      $(this).hide();
      $('.lookWeek').removeClass('this');
      /*点击下周的时候所有的不亮*/
      for(var i=0;i<=7;i++){
 			$('.Week').eq(i).css({'color':"#090909",'background':"",'backgroundSize':'cover'});       		
 			$('.Week').eq(i).find('div').css({'borderRight':'1px solid #d9d9d9'});
       }
      //点击下周的时候默认显示星期一
      $('.Week').eq(0).css({'color':"#fff",'background':"url("+urls['ctx'] +"/views/preissue/image/nowday.png)",'backgroundSize':'cover'});       		
      $('.Week').eq(0).find('div').css({'borderRight':'none'});
      
	  	var selectDate=$('.Week').eq(0).find('div').text();
		$('.preissueBottomLeft').empty();
		$('.preissueBottomContent').empty();
		$Week=$('.Week').eq(0);
		selectDate=selectDate.substring(0,10);
		userSelectDate=selectDate;
		var jsonData={"date":selectDate};
		$.ajax({  
		    type:'post',      
		    url:'preissue/loadDataIndex',
		    dataType:'json', 
		    data:jsonData,
		    async:false, 
		    success:function(data){
		    	mealsLen=data.length;
				for(var i=0;i<data.length;i++){
					var issue=data[i];
					if(issue[issue.length-1].areaModelName>5){
						$('.preissueBottomLeft').append("<div  value='"+i+"'>"+issue[issue.length-1].areaModelName+"</div>");
						$('.preissueBottomLeft').find('div').eq(i).css({'box-sizing':'border-box','padding-top':'50px','line-height':'18px'});
					}else{
				 		$('.preissueBottomLeft').append("<div  value='"+i+"'>"+issue[issue.length-1].areaModelName+"</div>");
				 	}
					$('.preissueBottomLeft div:even').addClass('gray');
					$('.preissueBottomLeft div:odd').addClass('white');
					//添加分类餐品
					$('.preissueBottomContent').append("<div class='mm' value='"+i+"'><div class='mm_main'><ul class='Meal_main'></ul></div></div>");
					for(var j=0;j<issue.length-1;j++){
						$('.mm').eq(i).find('ul').append("<li><img class='bg' src="+urls['ctx'] +"/views/preissue/image/mealbg2.png><div class='mealName'><input class='mealsnum' type='hidden' value="+issue[j].issueId+"></input><span class='name'>"+issue[j].goodsName+"</span></div>");
					}
	
				}
		    }
		});
		layout(mealsLen);
		if(!$Week.hasClass('timeout')){
			 mealDialog();
		}
		 $.each($('.mealName'), function(i, value) {
			if($(this).find('.name').text().length<7){
				$(this).find('.name').css({'marginTop':'30px'});
			}
		});
 	});
    setDate(systemNowDate());  
}

//aaaaa
function dateDialog(){
	
	$('.releaseBtn').click(function(){
		var preIssreEndTime;
		 $.ajax({
			    type:'post',      
			    url:'preissueEndTime/getPreissueEndTime',
			    dataType:'json', 
			    async:false, 
			    success:function(data){
			    	if(data.result){
				    	preIssreEndTime=data.data;
						$('.dateDialog').show();
			    	}else{
			    		 $.messager.show({
			 				title : '提示',
			 				msg : data.data,
			 				timeout : 3000,
			 			});
			    	}
			    }
		 });
		
		/*发布日期*/
	    var dateWeek = $('.dateWeek div');
	    var dateWeekClen = dateWeek.length;
	    var currentFirstDate;
	    var formatDate = function(date){
	    	var year=date.getFullYear();
	        var month = (date.getMonth()+1)+'月';
	        var day = date.getDate();
	        var realMonth=date.getMonth()+1;
	        var fullMonth="";
	        if(realMonth<10){
	        	fullMonth='0'+realMonth;
	        }else{
	        	fullMonth=realMonth;
	        }
	        var fullDate='';
	        if(day<10){
	        	fullDate='0'+day;
	        }else{
	        	fullDate=day;
	        }
	        var week =['周日','周一','周二','周三','周四','周五','周六'][date.getDay()];
	        return "<span class='none'>"+year+'-'+fullMonth+'-'+fullDate+"</span>"+month+'<span>'+day+'</span>'+'日'+'<br/>'+week;
	    };
	    var addDate= function(date,n){       
	        date.setDate(date.getDate()+n);
	        return date;
	    };
	    var systemNowDate=function(){
	    	var systemNowDate;
	    	$.ajax({  
			    type:'post',      
			    url:'preissue/getCurrentTime',
			    dataType:'json', 
			    //data:jsonData,
			    async:false, 
			    success:function(data){
			    	//alert(JSON.stringify(data));
			    	if(data.status==true){
			    		systemNowDate = new Date(data.message);
			    	}	
			    }
			});
	    	return systemNowDate;
	    };
	    var setDate = function(date){
	        var week = date.getDay()-1;
	        date = addDate(date,week*-1);
	        currentFirstDate = new Date(date);
	        var currentDate;
	        var nowDate;
	        $.ajax({  
			    type:'post',      
			    url:'preissue/getCurrentTime',
			    dataType:'json', 
			    //data:jsonData,
			    async:false, 
			    success:function(data){
			    	//alert(JSON.stringify(data));
			    		nowDate = new Date(data.message);
			    		currentDate=nowDate.getDate();
			    	
			    }
			});
	        var hms=nowDate.getHours()+""+nowDate.getMinutes()+""+nowDate.getSeconds();
	        for(var i = 0;i<dateWeekClen;i++){
	        	if(date.getDate()==currentDate){
	        		var $index=i;
	        		dateWeek[i].innerHTML = formatDate(i==0 ? date : addDate(date,0));
	        			/*
		        		if(hms>preIssreEndTime){*/
		        			$('.dateWeek li').eq(i).addClass('nouser');
		    	       /* }else{
		    	        	$('.dateWeek li').eq(i).css({'color':"#fff",'background':"url("+urls['ctx'] +"/views/preissue/image/nowday.png)",'backgroundSize':'cover'});       		
			        		$('.dateWeek li').eq(i).find('div').css({'borderRight':'none'});
		    	        }*/
	        	}else if(date<nowDate){/*如果是过去的时间那么灰掉不能点击*/
	        		dateWeek[i].innerHTML = formatDate(i==0 ? date : addDate(date,0));
	        		$('.dateWeek li').eq(i).addClass('nouser');
	        	}
	        	else{
	        		dateWeek[i].innerHTML = formatDate(i==0 ? date : addDate(date,0));
	        		if(parseInt(hms)>parseInt(preIssreEndTime)){
	        			$('.dateWeek li').eq($index+1).addClass('nouser');
	        			$('.dateWeek li').eq($index+2).css({'color':"#fff",'background':"url("+urls['ctx'] +"/views/preissue/image/nowday.png)",'backgroundSize':'cover'});   /高亮/ 		
	        			$('.dateWeek li').eq($index+2).find('div').css({'borderRight':'none'});
	    	       }else{
	    	    	   $('.dateWeek li').eq($index+1).css({'color':"#fff",'background':"url("+urls['ctx'] +"/views/preissue/image/nowday.png)",'backgroundSize':'cover'});   /高亮/ 		
	    	    	   $('.dateWeek li').eq($index+1).find('div').css({'borderRight':'none'});
	    	       }
	             }
	        	addDate(date,1)
	        }
	        
	        
	    };
	    setDate(systemNowDate());
	});
	
	releaseLayout();
}

//定义全局变量。餐食类型---早餐，午餐，晚餐等
var modelId="";
var goodsCategoryId="";
function getFoodsList(goodsCategoryId,modelId){
	var date=$('.releaseLeft_top').find('.realDate').html();
	//每次加载食品时清空之前的数据
	$('.releaseRight_meal ul').empty();
	var farg;
	if(modelId==""){
		 $.ajax({
			    type:'post',      
			    url:'goods/getAllGoodsList',
			    data:{'goodsCategoryId':goodsCategoryId},
			    dataType:'json', 
			    async:false, 
			    success:function(data){
			    	$.each(data,function(i){
			    		//alert("查询到的gid:"+data[i].gid+"查询到的amid:"+data[i].amid+"name:"+data[i].gname);
						//var value=data[i].gid+","+data[i].amid;
						//将返回的数据拼写到页面
						$('.releaseRight_meal ul').append("<li title='"+data[i].gname+"'><input id='gid' type='hidden' value='"+data[i].gid+"'/><input id='amid' type='hidden' value='"+data[i].amid+"'/><img src='"+urls['ctx'] +"/views/preissue/image/releaseLeftMealBg.png'><div>"+data[i].gname+"</div></li>");
					});
			    }
		 });
	}else{
	 $.ajax({
		    type:'post',      
		    url:'goods/getGoodsList',
		    dataType:'json', 
		    data:{'goodsCategoryId':goodsCategoryId,'goodsId':modelId,'goodsMemo':date},
		    async:false, 
		    success:function(data){
		    	console.log("右边菜："+JSON.stringify(data));
		    	$.each(data,function(i){
		    		//alert("查询到的gid:"+data[i].gid+"查询到的amid:"+data[i].amid+"name:"+data[i].gname);
					//var value=data[i].gid+","+data[i].amid;
					//将返回的数据拼写到页面
		    		for(var j=0;j<$('.releaseLeftMeal .look').find('li').length;j++){
		    			//alert("左边菜："+$('.releaseLeftMeal .look').find('li').eq(j).find('#gid').attr('value')+"右边菜："+data[i].gid);
						if($('.releaseLeftMeal .look').find('li').eq(j).find('#gid').attr('value')==data[i].gid){
							farg=true;
							break;
						}
					}
					if(farg)
					{
						farg=false;
					}
					else
					{
						$('.releaseRight_meal ul').append("<li title='"+data[i].gname+"'><input id='gid' type='hidden' value='"+data[i].gid+"'/><input id='amid' type='hidden' value='"+data[i].amid+"'/><img src='"+urls['ctx'] +"/views/preissue/image/releaseLeftMealBg.png'><div>"+data[i].gname+"</div></li>");
						
					}
		    	});
		    	
		    }
	 });
	}
	releasemMealDialog();
}


function getAllFoodsList(){
	//每次加载食品时清空之前的数据
	$('.releaseRight_meal ul').empty();
	 $.ajax({
		    type:'post',      
		    url:'goods/getAllGoodsList',
		    dataType:'json', 
		    async:false, 
		    success:function(data){
		    	$.each(data,function(i){
		    		//alert("查询到的gid:"+data[i].gid+"查询到的amid:"+data[i].amid+"name:"+data[i].gname);
					//var value=data[i].gid+","+data[i].amid;
					//将返回的数据拼写到页面
					$('.releaseRight_meal ul').append("<li title='"+data[i].gname+"'><input id='gid' type='hidden' value='"+data[i].gid+"'/><input id='amid' type='hidden' value='"+data[i].amid+"'/><img src='"+urls['ctx'] +"/views/preissue/image/releaseLeftMealBg.png'><div>"+data[i].gname+"</div></li>");
				});
		    }
	 });
	releasemMealDialog();
}

function releaseLayout(){
	$('.dateDialog').find('li').click(function(){
		$('.releaseLeft_bottom_tab').empty();
		$('.releaseRight_tab ul').find('.data').remove();
		$('.releaseLeftMeal').empty();
		/*如果不是过期的时间那么可以跳到发布页*/
		if($(this).hasClass('nouser')==false){
			modelId="";//初始化midelId
			goodsCategoryId="";
			$(this).parents('.dateDialog').hide();
			$('.preissueBox').hide();
			$('.releaseMainDialog').show();
			//跳转到发布页面后，默认查询全部商品
			getAllFoodsList();
		}
		/*发布页日期显示*/
		var choiceday=$(this).find('div').html();
		var choicedays =$(this).find('div').text();
		choicedays=choicedays.substring(0,10);
		var choicedate=choiceday.substring(0,choiceday.length-6);
		var choiceweek=choiceday.substr(choiceday.length-2,2);
		$('.releaseLeft_top').find('.ChoiceDate_day').html(choicedate);
		$('.releaseLeft_top').find('.ChoiceDate_week').html(choiceweek);
		$('.releaseLeft_top').find('.realDate').html(choicedays);
		/*设置tab区域的高度*/
		var tabHeight=$('.releaseLeft').height()-$('.releaseLeft_top').height();
		$('.releaseLeft_bottom').height(tabHeight-7);
		var tabLiHeight=50/tabHeight*tabHeight;
		var tabDivHeight=tabLiHeight*0.7;
		$('.releaseLeft_bottom_tab li div').css({"height":tabDivHeight,'lineHeight':tabDivHeight+'px','marginTop':tabLiHeight*0.15+'px'})
			/*发布页左边点击早中晚夜的生成和切换*/
			//生成
			var meals =[];
			var ids=[];
			$.post("areaModel/getAreaModelList",function(data){
					$.each(data,function(i){
						//将返回集合转换为数组
						meals[i]=data[i].name;
						ids[i]=data[i].id;
					});
					var mealsLen=meals.length;
					for(var i=0;i<mealsLen;i++)
					{
						$('.releaseLeft_bottom_tab').append("<li class value="+i+" title='"+meals[i]+"'><div>"+meals[i]+"</div></li>");
						$('.releaseLeftMeal').append("<ul value="+i+"></ul>");
					}
					var tabLiWidth=$('.releaseLeft_bottom_tab_main').width()/4;
					$('.releaseLeft_bottom_tab').find('li').width(tabLiWidth);
					//$('.releaseLeft_bottom_tab').find('li').height(50);
					$('.releaseLeft_bottom_tab').find('li').css({"line-height":"50px","height":tabLiHeight});
					$('.releaseLeft_bottom_tab').width(tabLiWidth*mealsLen);
					/*
					 * 林智 2016.5.10修改文字溢出
					 * */
					/*$('.releaseLeft_bottom_tab li').hover(function(){
						$('.showname').empty();
						var tabname=$(this).find('div').text();
						$('.showname').text(tabname);
						$('.showname').show().css({'width':$('.releaseLeft_top').width()-24});
					},function(){
						$('.showname').hide();
					})*/
					//切换
					$('.releaseLeft_bottom_tab li').click(function(){
						var $in=$(this).attr('value');
						$(this).addClass('activeli').siblings().removeClass('activeli');
						$('.releaseLeftMeal ul').eq($in).addClass('look').siblings().removeClass('look');
						var $index=$(this).index();
						var selectModelId=ids[$index];
						var date=$('.releaseLeft_top').find('.realDate').html();
						var dataPost={'selectDate':date,'areaModelId':selectModelId};
						$('.releaseLeftMeal').find('ul').find(".getData").remove();
						//获取已发布的数据
						 $.ajax({
							    type:'post', 
							    url:'preissue/getIsSelectGoodsList',
							    data:dataPost,
							    dataType:'json',
							    async:false, 
							    success:function(data){
							    	//循环赋值
							    	for(var i=0;i<data.length;i++){
							    		$('.releaseLeftMeal').find('ul').eq($index).append("<li class='getData' title='"+data[i].goodsName+"'><input id='issueId' type='hidden' value='"+data[i].issueId+"'/><input id='gid' type='hidden' value='"+data[i].goodsId+"'><input id='amid' type='hidden' value=''><img src="+urls['ctx'] +"/views/preissue/image/releaseLeftMealBg.png><div class='releaseLeftMeal_main'><span class='releaseLeftMeal_name'>"+data[i].goodsName+"</span></div></li>");
							    	}
							    	//绑定全部清除事件
							    	bindClear();
							    	
							    	//绑定删除事件
							    	bindDel();
						    	}
							});
					});
					
					/*发布页选定餐品显示*/
					var releaseLeftMealHeight=tabHeight-$('.releaseLeft_bottom_tab').height();
					$('.releaseLeftMeal').height(releaseLeftMealHeight-80);
					/*右边的布局*/
					var releaseRightWidth=$('.releaseTop').width()-$('.releaseLeft').width();
					$('.releaseRight').width(releaseRightWidth-10);
					
					$('.releaseLeft_bottom_tab li').click(function() {
						//绑定事件，并赋值给全局变量modelId
						var index=$(this).val();
						modelId=ids[index];
						//异步查询食品数据
						getFoodsList(goodsCategoryId,modelId);
						
						$.each($('.releaseRight_meal li'), function(i, value) {
							if($(this).find('div').text().length<7){
								$(this).find('div').css({'line-height':"70px",'marginTop':'0px'});
							}
						})
						
					});
				},'JSON');
			/*右边餐品分类tab自适应布局*/
			$.post("goods/category/getGoodsCateGoryList",function(result){
		    	var data=[];
		    	var ids=[];
				$.each(result,function(i){
					//将返回集合转换为数组
					data[i]=result[i].name;
					ids[i]=result[i].id;
				});
				var dataLen=data.length;
				//每次加载前清空ul标签下的数据
				//$('.releaseRight_tab ul').find('.data').empty();
				//添加一个全部菜品
				//$('.releaseRight_tab ul').append("<li value=''><img src='"+urls['ctx'] +"/views/preissue/image/releaseRightTabBg.png'><div>全部</div></li>");
				for(var i=0;i<dataLen;i++){
					//添加分类
					$('.releaseRight_tab ul').append("<li value="+ids[i]+" class='data'><img src="+urls['ctx'] +"/views/preissue/image/releaseRightTabBg.png><div>"+data[i]+"</div></li>");
				}
				$('.releaseRight_tab li').width($('.releaseRight').width()/$('.releaseRight_tab li').length);
				
				$('.releaseRight_tab li').eq(0).addClass('tabActive').end().eq(0).find('img').attr('src',urls['ctx'] +'/views/preissue/image/tabActive.png');;
				$('.releaseRight_tab li').click(function() {
					$(this).addClass('tabActive').siblings().removeClass('tabActive');
					$(this).find('img').attr('src',urls['ctx'] +'/views/preissue/image/tabActive.png');
					$(this).siblings().find('img').attr('src',urls['ctx'] +'/views/preissue/image/releaseRightTabBg.png');
					//如果选择全部，则将默认值0转换成空字符串到后台查询
					goodsCategoryId=$(this).val()==0?"":$(this).val();
					//异步查询食品数据
					getFoodsList(goodsCategoryId,modelId);
					
					$.each($('.releaseRight_meal li'), function(i, value) {
						if($(this).find('div').text().length<7){
							$(this).find('div').css({'line-height':"70px",'marginTop':'0px'});
						}
					})
				});
			},'JSON');
				
		/*右边餐品列表自适应布局*/
		
		
		/*右边餐品分类tab自适应布局*/
//		 $.ajax({  
//			    type:'post',      
//			    url:'',
//			    dataType:'json', 
//			    async:false, 
//			    success:function(data){
//			    	var data=['全部','套餐','饮料','水果','其他','零食','小吃','卤味','坚果'];
//					var dataLen=data.length;
//					for(var i=0;i<dataLen;i++){
//						//添加分类
//						$('.releaseRight_tab ul').append("<li><img src="+urls['ctx'] +"/views/preissue/image/releaseRightTabBg.png><div>"+data[i]+"</div></li>");
//					}
//					$('.releaseRight_tab li').width($('.releaseRight').width()/dataLen);
//					$('.releaseRight_tab li').eq(0).addClass('tabActive').end().eq(0).find('img').attr('src',urls['ctx'] +'/views/preissue/image/tabActive.png');;
//					$('.releaseRight_tab li').click(function() {
//						$(this).addClass('tabActive').siblings().removeClass('tabActive');
//						$(this).find('img').attr('src',urls['ctx'] +'/views/preissue/image/tabActive.png');
//						$(this).siblings().find('img').attr('src',urls['ctx'] +'/views/preissue/image/releaseRightTabBg.png');
//					});
//			    }
//		 });
		
		var releaseRightHeight=$('.releaseTop').height()-$('.releaseBottom').height()+10;
		$('.releaseRight').height(releaseRightHeight);
		$.each($('.releaseRight_meal li'), function(i, value) {
			if($(this).find('div').text().length<7){
				$(this).find('div').css({'line-height':"70px",'marginTop':'0px'});
			}
		})
	});
	releasemMealDialog();
}


/*发布页左边删除的时候*/
$(document).on('click','.releasemDeleteDialog .yes',function(){
	//后台查询出来的issueId
	var issueId= $thisli.find('#issueId').val();
	var gid= $thisli.find('#gid').val();
	var amid= $thisli.find('#amid').val();
	var foodName=$(this).parents('.dialog').find('.deleteDialogText em').text();
	//alert(foodName);
	//判断数据是否是后台传递过来的，如果是undefined说明是临时添加，可以直接清空，否则需要按issueId去后台删除数据
	if(issueId==undefined){
		$thisli.remove();
		$('.releaseRight_meal ul').append("<li title='"+foodName+"=><input id='gid' type='hidden' value='"+gid+"'><input id='amid' type='hidden' value='"+amid+"'><img src='"+urls['ctx'] +"/views/preissue/image/releaseLeftMealBg.png'><div>"+foodName+"</div></li>")
		//往右侧食品列表添加数据
		$('.releasemDeleteDialog').hide();
	}else{
		 $.ajax({
			    type:'post',      
			    url:'issue/delIssue',
			    data:{'issueId':issueId,'goodsId':gid},
			    dataType:'json', 
			    async:false, 
			    success:function(data){
			    	if(data.result){
			    		//页面移除商品
						$thisli.remove();
						//重新加载右侧可选商品
						getFoodsList(goodsCategoryId,modelId)
			    		$('.releasemDeleteDialog').hide();
			    		$.messager.show({
							title : '提示',
							msg : data.message,
							timeout : 1500,
						});
			    	}else{
			    		$.messager.show({
							title : '提示',
							msg : data.message,
							timeout : 1500,
						});
			    	}
			    }
		 });
	}
});

var systemNowDate=function(){
	var systemNowDate;
	$.ajax({  
	    type:'post',      
	    url:'preissue/getCurrentTime',
	    dataType:'json', 
	    //data:jsonData,
	    async:false, 
	    success:function(data){
	    	//alert(JSON.stringify(data));
	    		systemNowDate = new Date(data.message);
	    }
	});
	return systemNowDate;
};