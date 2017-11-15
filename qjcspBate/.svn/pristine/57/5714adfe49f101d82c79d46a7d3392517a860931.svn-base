$(function(){
	/*加载左边的早中晚夜分类*/
	
	/*var machineName = $('#changeId').text(); //柜子名称
	var machineId = $('.modeTitle_name').attr('value'); //柜子Id*/
	var machineNum = 0;
	var changeId = $('.modeTitle_name').attr('value'); //柜子Id*/
	//var booleanFlag = true;
	
	$.ajax({
		type:"post",
		url:"newTemplate/getAreaModelListByMahineIdName",
		data:"mId=" + changeId,
		dataType:'json',
		async:false,
		success:function(data){
			for(var i=0; i<data.length; i++){
				$('.modeAllot_tab ul').append("<li><div title='"+data[i].area_model_name+"'><input class='mealsnum' type='hidden' value="+data[i].area_model_id+">"+data[i].area_model_name+"</div></li>");
				//根据早中晚夜分类添加对应的ul
				$('.modeAllot_main').append("<ul></ul>");
			}
			$('.modeAllot_tab li').eq(0).addClass('now');
			$('.modeAllot_main ul').eq(0).addClass('now').show();
			/*切换早中晚夜类别时的事件*/
			modelChange(); 
			
			var amId = $('.modeAllot_tab .now').find(".mealsnum").val();
			getMachineNum(changeId, amId);
			
			/*
			 * 林智 2016.5.10修改文字溢出
			 * 
			$('.modeAllot_tab li').hover(function(){
				$('.showname').empty();
				var tabname=$(this).find('div').text();
				$('.showname').text(tabname);
				$('.showname').show().css({'width':$('.modeTitle').width()-24});
			},function(){
				$('.showname').hide();
			})*/
		},
		Error:function(){
			alert('分类加载出错了！');
		}
	});
	
	
	/*$('.modeAllot_tab li').click(function(){
		var goodsCategoryId = $(this).find(".mealsnum").val();
		alert(goodsCategoryId);
		$.ajax({
	        url: "newTemplate/machineNum",
	        type: "post",
	        data:"machineId=" + changeId + "&goodsCategoryId=" + goodsCategoryId,
	        async:true,
	        success:function(data){
	            machineNum = data;
	            $('.modeAllot_botm').find('em').text(machineNum);
	        }
	    })
	})*/
	
	//加载右边的分类
	$.ajax({
		type:"post",
		url:"newTemplate/goodsCategory",
		dataType:'json',
		async:false,
		success:function(data){
			
			for(var i=0; i<data.length; i++){
				$('.modeRight_tab ul').append("<li value='' class='data'><img src='static/images/releaseRightTabBg.png'><div>"+data[i].goods_category_name+"<input class='mealsnum' type='hidden' value="+data[i].goods_category_id+"></input></div></li>");
			}
			$('.modeRight_tab li').eq(0).addClass('tabActive');
			mealsList(0);
			
			//餐品(全部,水果，零食，其他……)分类布局
			$('.modeRight_tab li').width($('.modeRight_tab').width()/$('.modeRight_tab li').length);
			
			//餐品(全部,水果，零食，其他……)分类点击的时候
			$('.modeRight_tab li').click(function(){
				$(this).addClass('tabActive').end().siblings().removeClass('tabActive');
				$(this).find('img').attr('src',"static/images/tabActive.png");
				$(this).siblings().find('img').attr('src',"static/images/releaseRightTabBg.png");
				var goodsCategoryId = $(this).find(".mealsnum").val();
				$('.modeRight_meal ul').empty();//先清空
				mealsList(goodsCategoryId);//再重新加载
			});
			
		}
		
	});
	
	
	
	
	
	
	

	
/*布局
 ---------------------------------------start*/
	$('.modeName_input').find('input').val('');
	var modeRightWidth=$('.modeTop').width()-$('.modeLeft').width()-10;
	/*var modeRightHeight=$('.modeTop').height();*/
	var modeMainHei = $(".modeMain").height();
	$('.modeTop').height(modeMainHei-70);
	$('.choicebox').parent().height($('.modeTop').height());
	var modeRightHeight = $('.modeTop').height();
	$('.modeRight').width(modeRightWidth).height(modeRightHeight-2);
	$('.modeAllot').height($('.modeTop').height()-88);
	$('.modeAllot_main').height($('.modeAllot').height()-$('.modeAllot_tab').height()-$('.modeAllot_botm').height());
	var liHeight=$('.modeAllot_main').height()*0.105;
	$('.modeAllot_main li').css({"height":liHeight});
	var lineH=$('.modeAllot_main li').height()*0.57;
	var marTop=$('.modeAllot_main li').height()*0.2;
	var maskTop=$('.modeAllot_main li').height()*0.36;
	$('.mealName').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
	$('.pct').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
	$('.bor').height($('.modeAllot_main li').height());
	
	/*下面的布局*/
	var sureBtnH=$('.modeBotm').height()*0.583;
	var sureBtnW=$('.modeBotm').width()*0.085;
	var backW=$('.modeBotm').width()*0.076;
	$('.SureBtn').css({'height':sureBtnH+'px','width':sureBtnW+'px','line-Height':sureBtnH+'px','margin-top':$('.modeBotm').height()*0.166+'px'});
	$('.back').css({'height':sureBtnH+'px','width':backW+'px','line-Height':sureBtnH+'px','top':$('.modeBotm').height()*0.166+'px'});
	
	mask(lineH,maskTop);
/*布局
 ---------------------------------------end*/

	/*mask出现点击删除的时候*/
	$(document).on('click', '.detel', function () {
		if($('.mealAllot').parent().attr('value')=='show'){
			$.messager.show({
 				title : '提示',
 				msg : '您的商品还未分配完成',
 				timeout : 3000,
 			});
		}
		else{
			$('.dialog').hide();
	   		$('.deleteName').parent().show();
	   		$('.mealAllot').parent().attr('value','');
	   		var foodname=$(this).parents('.mask').siblings('.mealName').text();
	   		localStorage.setItem("foodname",foodname);
	   		$('.deleteName').find('.foodName').text(foodname.length>15?foodname.substring(0,15)+"...":foodname);
	   		$('.deleteName_text').attr("title",foodname);
		}
		
	});
	/*mask删除对话框确定事件*/
	$('.deleteName_sure').click(function(){
		var goodsId = $('.modeAllot_main .now').find('input').val();
		var nownum=$('.modeAllot_tab .now').find('em').text();
		$(this).parents('.dialog').hide();
		var farg;
		var foodnum;
		var foodname=localStorage.getItem("foodname");
		var len = $('.modeAllot_main ul.now').find('li').length;
		$(".modeRight_meal  li").each(function(){
			var str1=$(this).find('div').text();//文本框的值
		//	alert(str1);
			if(foodname==str1){
				$(this).find('span').css({'background-color':'#2aa2ed','cursor':'auto'});
				//  $('.modeRight_meal').find('li').eq(2).find('span').css({'background-color':'red','cursor':'auto'});
			}
			});
		for(var i=0;i<len;i++)
		{
			var dishName = $(".modeAllot_main ul.now li").eq(i).find(".mealName").text();
			if( dishName == foodname)
			{
				farg=true;
				break;
			}
		}
		if(farg)
		{
			foodnum=$(".modeAllot_main ul.now li").eq(i).find('.pct').find('em').text();
			$('.modeAllot_botm').find('em').text(parseInt($('.modeAllot_botm').find('em').text())+parseInt(foodnum));//aa
			$(".modeAllot_main ul.now li").eq(i).remove();
		}
	});
	/*mask出现点击修改的时候*/
	$(document).on('click', '.update', function () {
		if($('.mealAllot').parent().attr('value')=='show'){
			$.messager.show({
 				title : '提示',
 				msg : '您的商品还未分配完成',
 				timeout : 3000,
 			});
		}
		else{
			$('.dialog').hide();
	   		$('.mealAllot').parent().show().attr('value','show');
	   		var name=$(this).parents('.mask').siblings('.mealName').text();
	   		var pct=$(this).parents('.mask').siblings('.pct').find('em').text();
	   		 localStorage.setItem("name",name);
	   		 localStorage.setItem("pct",pct);
	   		$('.mealAllot').find('.inpName').text(name).attr('title',name);
	   		$('.mealAllot').find('input').val('');
		}
	});
	/*mask修改对话框确定事件*/
	$('.mealAllot_sure').click(function(){
		$(this).parents('.dialog').hide();
		var pct=localStorage.getItem("pct");
		var farg;
		var allsum=0;
		var leftname=localStorage.getItem("name");
		var inp=Math.ceil($('.mealAllot .inpText').find('input').val());
		var nowinp=$('.modeAllot_tab').find('em').text();
		var len = $('.modeAllot_main .now').find('li').length;
		var tempNum = localStorage.getItem("tempNum");
		for(var i=0;i<len;i++){
			allsum+=parseInt($(".modeAllot_main .now li").eq(i).find('.pct').find('em').text());//现在已设置的份数
		}
		
		if(parseInt(inp)>=0){
			var nowsum=(allsum-parseInt(pct))+parseInt(inp);
			if(nowsum<=tempNum){//这里需要重新设置最高份数
				for(var i=0;i<len;i++)
				{
					$index=i;
					var dishName = $(".modeAllot_main ul.now li").eq(i).find(".mealName").text();
					if( dishName == leftname)
					{
						farg=true;
						break;
					}
				}
				if(farg)
				{
					$(".modeAllot_main ul.now li").eq(i).find('.pct').find('em').text(inp);
				}
				$('.mealAllot').parent().attr('value','');
				$('.modeAllot_botm').find('em').text(tempNum-nowsum);//aa给左边下面的已设置比例赋值
			}
			else{
				alert('份数不能超过剩余份数');
				$('.mealAllot').parent().attr('value','');
			}
		}
		else{
			alert('不能为负数');
			$('.mealAllot').parent().attr('value','');
		}
		
		/*
		 *林智 2016/4/22
		 * 修改了设置比例超过100时不能添加
		 */
		/*if((parseInt(inp)-parseInt(pct))+parseInt(nowinp)<=100){
			for(var i=0;i<len;i++)
			{
				$index=i;
				var dishName = $(".modeAllot_main ul.now li").eq(i).find(".mealName").text();
				if( dishName == leftname)
				{
					farg=true;
					break;
				}
			}
			if(farg)
			{
				$(".modeAllot_main ul.now li").eq(i).find('.pct').find('em').text(inp);
			}
			//计算比例
			for(var i=0;i<len;i++){
				allsum+=parseInt($(".modeAllot_main ul.now li").eq(i).find('.pct').find('em').text());
			}
			$('.modeAllot_tab .now').find('em').text(allsum);//给对应的(早中晚夜)赋值比例
			$('.modeAllot_botm').find('em').text(allsum);//给左边下面的已设置比例赋值
			
		}
		else{
			/*$.messager.show({
				title : '提示',
				msg : '比例不能超过100%',
				timeout : 1500,
			});*/
			/*alert('比例不能超过100%');
		}*/
	});
	//点击左上角修改机柜的时候
	$('.change').click(function(){
		if($('.mealAllot').parent().attr('value')=='show'){
			$.messager.show({
 				title : '提示',
 				msg : '您的商品还未分配完成',
 				timeout : 3000,
 			});
		}else{
			$('.dialog').hide();
			$('.choicebox').parent().show();
			$('.mealAllot').parent().attr('value','');
			$('.choicebox').find('.choice').empty(); //先清空，再加载
			var changename=$('.modeTitle').find('.modeTitle_name').text();
			$.ajax({
				type:"get",
				url:"newTemplate/getMachine",
				dataType:'json',
				async:false,
				success:function(data){
					for(var i=0;i<data.length;i++){
						if(data[i].machineName==changename){
							$('.choicebox').find('.choice').append("<li class='active' title='"+data[i].machineName +"'>"+data[i].machineName+"<input class='mealsnum' type='hidden' value="+data[i].machineId+"></li>");
						}else{
							$('.choicebox').find('.choice').append("<li title='"+data[i].machineName +"'>"+data[i].machineName+"<input class='mealsnum' type='hidden' value="+data[i].machineId+"></li>");
						}
					}
					//点击机柜弹窗列表的时候
					$('.choicebox li').click(function(){
						$(this).addClass('active');
						$(this).siblings().removeClass('active');
					});
					
				},
			});
		}
		
	})
	
	//点击机柜弹窗的确定按钮时
	$('.choice_sure').click(function(){
		$('.modeAllot_tab').find('ul').empty();
		var mName=$('.popup').find('.active').text();
		var mId=$('.popup').find('.active').find('input').val();
		$('.modeTitle').find('.modeTitle_name').text(mName).attr('value',mId);
		/*var amId = $('.modeAllot_tab li').find(".mealsnum").val();*/
		
		
		/*$.ajax({
	        url: "newTemplate/machineNum",
	        type: "post",
	        data:"machineId=" + mId,
	        async:false,
	        success:function(data){
	            machineNum = data;
	            $('.modeAllot_botm').find('em').text(machineNum);
	        }
	    })*/
		$('.modeAllot_main ul').empty();
		$.ajax({
			type:"post",
			url:"newTemplate/getAreaModelListByMahineIdName",
			data:"mId=" + mId,
			dataType:'json',
			async:false,
			success:function(data){
				for(var i=0; i<data.length; i++){
					$('.modeAllot_tab ul').append("<li><div title='"+data[i].area_model_name+"'><input class='mealsnum' type='hidden' value="+data[i].area_model_id+">"+data[i].area_model_name+"</div></li>");
					//根据早中晚夜分类添加对应的ul
					$('.modeAllot_main').append("<ul></ul>");
				}
				$('.modeAllot_tab li').eq(0).addClass('now');
				$('.modeAllot_main ul').eq(0).addClass('now').show();
				/*切换早中晚夜类别时的事件*/
				modelChange();
				
				var amId2 = $('.modeAllot_tab .now').find(".mealsnum").val();
				
				getMachineNum(mId, amId2)
				/*
				 * 林智 2016.5.10修改文字溢出
				 * 
				$('.modeAllot_tab li').hover(function(){
					$('.showname').empty();
					var tabname=$(this).find('div').text();
					$('.showname').text(tabname);
					$('.showname').show().css({'width':$('.modeTitle').width()-24});
				},function(){
					$('.showname').hide();
				})*/
			},
			Error:function(){
				alert('分类加载出错了！');
			}
			
		});
		$('.modeRight_meal ul').empty();//先清空
		mealsList(0);
		$(this).parents('.dialog').hide();
		
	});
	function modelChange(){
		$('.modeAllot_tab li').click(function(){
			$('.modeRight_meal ul').empty();//先清空
			
			$('.modeRight_meal li').find('span').css({'background-color':'#2aa2ed'});//初始的右边餐品都是可选状态
			//切换时的效果状态
			$(this).addClass('now');
			$(this).siblings().removeClass('now');
			var $index=$(this).index();
			$('.modeAllot_main').find('ul').removeClass('now').hide();
			$('.modeAllot_main').find('ul').eq($index).addClass('now').show();
			var amId = $('.modeAllot_tab .now').find(".mealsnum").val();
			var goodsCategoryId = $(this).find(".mealsnum").val();
			
			var tempMachineId = $('.modeTitle_name').attr('value');
			
			if(newMealsList(amId) == false){
				localStorage.setItem("tempNum", 0);
			}
			else{
				getMachineNum(tempMachineId, goodsCategoryId);
			}
			
			
			
			/*$.ajax({
		        url: "newTemplate/machineNum",
		        type: "post",
		        data:"machineId=" + tempMachineId + "&goodsCategoryId=" + goodsCategoryId,
		        async:false,
		        success:function(data){
		            machineNum = data;
		            $('.modeAllot_botm').find('em').text(machineNum);
		        }
		    })*/
			//循环右边餐品判断是否有同名餐品
			var len = $('.modeRight_meal').find('li').length;
			var meals=[];
			for(var i=0;i<len;i++){
				var RightMealName=$('.modeRight_meal li').eq(i).find('div').text();
				meals.push(RightMealName);
			}
			for(var i=0;i<$(".modeAllot_main ul.now").find('li').length;i++){
				var modeAllotName=$(".modeAllot_main ul.now li").eq(i).find(".mealName").text();
				for(var j=0;j<meals.length;j++){
					if(meals[j]==modeAllotName){//如果有同名餐品，对应餐品不可选
						$('.modeRight_meal li').eq(j).find('span').css({'background-color':'#dfdfdf','cursor':'auto'});
					}
				}
			}
			
			
			//左边下面份数
			var allsum=0;
			for(var i=0;i<$('.modeAllot_main .now').find('li').length;i++){
				allsum+=parseInt($(".modeAllot_main .now").find('li').eq(i).find('.pct').find('em').text());//现在已设置的份数
			}
			var tempNum = localStorage.getItem("tempNum");
			$('.modeAllot_botm').find('em').text(tempNum-allsum);//aa最高份数需要重新获取
					
		});
	}
	
	
	/*点击左边早中晚分类的时候切换状态,清空原有的内容,重新加载*/
	function newMealsList(areaModelId){
		var booleanFlag = true;
		var goodsCategoryId = $('.modeRight_tab li').find(".mealsnum").val();
		
		$.ajax({
			type:"post",
			url:"newTemplate/goodsDetail",
			data:"goodsCategoryId=" + goodsCategoryId + "&mId=" + changeId + "&areaModelId=" + areaModelId,
			dataType:'json',
			async:false,
			success:function(data){
				if(data.code == 0){
					$('.modeRight_meal ul').append("无可用餐品");
					/*$('.modeAllot_botm').find('em').text(0);*/
					
					$.messager.show({
						title : '提示',
						msg : data.content,
						timeout : 1500,
					});
					booleanFlag=false;
				}
				else{
					if(data.content.length == 0){
						$('.modeRight_meal ul').append("无可用餐品");
					}
					else{
						for(var i=0; i<data.content.length; i++){
							$('.modeRight_meal ul').append("<li title='"+data.content[i].goods_name+"'><span></span><input id='gid' type='hidden' value='"+data.content[i].goods_id+"'/><div>"+data.content[i].goods_name+"</div></li>");
						}
					}
					
					/*右边的布局*/
					var modeMainHei = $(".modeMain").height();
					$('.modeTop').height(modeMainHei-70);
					$('.modeRight_meal').height($('.modeTop').height()-$('.modeRight_tab').height()+7);
					$('.modeRight_meal li').css({'height':$('.modeMain').height()*0.067+'px',"line-Height":$('.modeMain').height()*0.067+'px'});
					$('.modeRight_meal li span').height($('.modeRight_meal li').height());
					$.each($('.modeRight_meal li'), function(i, value) {
						if($(this).find('div').text().length>7){
							$(this).find('div').css({'line-height':"20px",'marginTop':$('.modeRight_meal li').height()/2-18+'px'});
						}
					
					});	
					
					/* 右边餐品列表点击的时候*/
					$('.modeRight_meal').find('li').on('click',function(){
						//如果有同名餐品就在左边对应分类列表下添加餐品
						var farg;
						var $this=$(this);
						var len = $('.modeAllot_main ul.now').find('li').length;
						var mealName = $(this).find('div').text();
						var machineId=$(".modeTitle_name").attr("value");
						$.post("newTemplate/checkGoodsToMachine",{'foodName':mealName,'machineId':machineId},function(data){
							if(data.status){
								for(var i=0;i<len;i++)
								{
									var dishName = $(".modeAllot_main ul.now li").eq(i).find(".mealName").text();
									if( dishName == mealName || parseInt($('.modeAllot_botm').find('em').text())<=0)
									{
										
										farg=true;
										break;
									}
								}
								if(farg)
								{
									console.log("不添加");
								}
								else
								{
									var mealvalue=$this.find('input').attr('value');
									var leftMealValue=$('.modeAllot_tab').find('.now').find('input').attr('value');
									/*$('.modeAllot_main ul.now').append("<li class='cle'><span class='bor lf'></span><div class='mealName lf'>"+mealName+"</div><div class='pct lr'><em>0</em>份</div></li>");*/
									$('.modeAllot_main ul.now').append("<li class='cle' title='"+mealName+"'><span class='bor lf'></span><div class='mealName lf'>"+mealName+"</div><input id='goodsId' type='hidden' value='"+mealvalue+"'><input id='areaModelId' type='hidden' value='"+leftMealValue+"'><div class='pct lr'><em>0</em>份</div></li>");
									$(this).find('span').css({'background-color':'#dfdfdf','cursor':'auto'});//点击后此餐品不可选
								}
								//如果左边没有选中（早中晚夜）的话，弹出提示框
								if($('.now').length<=0){
									/*$.messager.show({
										title : '提示',
										msg : '请选择左侧上方的餐类型再进行添加',
										timeout : 1500,
									});*/
									alert('请选择左侧上方的餐类型再进行添加');
								}
							
								/*因为append新加入的 li不在原本的 $('.modeAllot_main li') 集合里
								 * 因此新加入的没有被绑定事件 所以要再次调用布局和mask
								 * */
								var liHeight=$('.modeAllot_main').height()*0.105;
								$('.modeAllot_main li').css({"height":liHeight});
								var lineH=$('.modeAllot_main li').height()*0.57;
								var marTop=$('.modeAllot_main li').height()*0.2;
								var maskTop=$('.modeAllot_main li').height()*0.22;
								$('.mealName').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
								$('.pct').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
								$('.bor').height($('.modeAllot_main li').height());
								mask(lineH,maskTop);
							}else{
								$.messager.show({
									title : '提示',
									msg : data.message,
									timeout : 1500,
								});
							}
						},'JSON');
					});
				}			
			},
			Error:function(){
				alert('右边食品分类的时候出错了！');
			}
		});
		 return booleanFlag;
		/*getMachineNum(changeId, areaModelId);*/
	}
	
	
	/*点击右边食品分类的时候切换状态,清空原有的内容,重新加载*/
	function mealsList(goodsCategoryId){
		var areaModelId = $('.modeAllot_tab .now').find(".mealsnum").val(); 
		/*var areaModelId = $('.modeAllot_tab li').find(".mealsnum").val();*/
		if(areaModelId == undefined){
			return null;
		}
		$.ajax({
			type:"post",
			url:"newTemplate/goodsDetail",
			data:"goodsCategoryId=" + goodsCategoryId + "&mId=" + changeId + "&areaModelId=" + areaModelId,
			dataType:'json',
			async:false,
			success:function(data){
				if(data.code == 0){
					$('.modeRight_meal ul').append("无可用餐品");
					$('.modeAllot_botm').find('em').text(0);
					$.messager.show({
						title : '提示',
						msg : data.content,
						timeout : 1500,
					});
				}
				else{
					if(data.content.length == 0){
						$('.modeRight_meal ul').append("无可用餐品");
					}
					else{
						for(var i=0; i<data.content.length; i++){
							$('.modeRight_meal ul').append("<li title='"+data.content[i].goods_name+"'><span></span><input id='gid' type='hidden' value='"+data.content[i].goods_id+"'/><div>"+data.content[i].goods_name+"</div></li>");
						}
					}
					
					
					
					/*右边的布局*/
					var modeMainHei = $(".modeMain").height();
					$('.modeTop').height(modeMainHei-70);
					$('.modeRight_meal').height($('.modeTop').height()-$('.modeRight_tab').height()+7);
					$('.modeRight_meal li').css({'height':$('.modeMain').height()*0.067+'px',"line-Height":$('.modeMain').height()*0.067+'px'});
					$('.modeRight_meal li span').height($('.modeRight_meal li').height());
					$.each($('.modeRight_meal li'), function(i, value) {
						if($(this).find('div').text().length>7){
							$(this).find('div').css({'line-height':"20px",'marginTop':$('.modeRight_meal li').height()/2-18+'px'});
						}
					
					});	
					
					/* 右边餐品列表点击的时候*/
					$('.modeRight_meal').find('li').on('click',function(){
						//如果有同名餐品就在左边对应分类列表下添加餐品
						var farg;
						var $this=$(this);
						var len = $('.modeAllot_main ul.now').find('li').length;
						var mealName = $(this).find('div').text();
						var machineId=$(".modeTitle_name").attr("value");
						$.post("newTemplate/checkGoodsToMachine",{'foodName':mealName,'machineId':machineId},function(data){
							if(data.status){
								for(var i=0;i<len;i++)
								{
									var dishName = $(".modeAllot_main ul.now li").eq(i).find(".mealName").text();
									if( dishName == mealName || parseInt($('.modeAllot_botm').find('em').text())<=0)
									{
										
										farg=true;
										break;
									}
								}
								if(farg)
								{
									console.log("不添加");
								}
								else
								{
									var mealvalue=$this.find('input').attr('value');
									var leftMealValue=$('.modeAllot_tab').find('.now').find('input').attr('value');
									/*$('.modeAllot_main ul.now').append("<li class='cle'><span class='bor lf'></span><div class='mealName lf'>"+mealName+"</div><div class='pct lr'><em>0</em>份</div></li>");*/
									$('.modeAllot_main .now').append("<li class='cle' title='"+mealName+"'><span class='bor lf'></span><div class='mealName lf'>"+mealName+"</div><input id='goodsId' type='hidden' value='"+mealvalue+"'><input id='areaModelId' type='hidden' value='"+leftMealValue+"'><div class='pct lr'><em>0</em>份</div></li>");
									$(this).find('span').css({'background-color':'#dfdfdf','cursor':'auto'});//点击后此餐品不可选
								}
								//如果左边没有选中（早中晚夜）的话，弹出提示框
								if($('.now').length<=0){
									/*$.messager.show({
										title : '提示',
										msg : '请选择左侧上方的餐类型再进行添加',
										timeout : 1500,
									});*/
									alert('请选择左侧上方的餐类型再进行添加');
								}
							
								/*因为append新加入的 li不在原本的 $('.modeAllot_main li') 集合里
								 * 因此新加入的没有被绑定事件 所以要再次调用布局和mask
								 * */
								var liHeight=$('.modeAllot_main').height()*0.105;
								$('.modeAllot_main li').css({"height":liHeight});
								var lineH=$('.modeAllot_main li').height()*0.57;
								var marTop=$('.modeAllot_main li').height()*0.2;
								var maskTop=$('.modeAllot_main li').height()*0.22;
								$('.mealName').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
								$('.pct').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
								$('.bor').height($('.modeAllot_main li').height());
								mask(lineH,maskTop);
							}else{
								$.messager.show({
									title : '提示',
									msg : data.message,
									timeout : 1500,
								});
							}
						},'JSON');
					});
				}
			},
			Error:function(){
				alert('右边食品分类的时候出错了！');
			}
		});
	}
	
	//餐品列表分类 
	$('.modeRight_tab li').width($('.modeRight_tab').width()/$('.modeRight_tab li').length);
	//所有对话框取消按钮
	$('.btnConcle').click(function(){
		$(this).parents('.dialog').hide();
		$('.mealAllot').parent().attr('value','');
	});
	$('.concle').click(function(){
		$(this).parents('.dialog').hide();
		$('.mealAllot').parent().attr('value','');
	});
	//点击确定的时候
	$('.SureBtn').click(function(){
		
	});
})


function mask(lineH,maskTop){
	/**零售分配左边列表经过*/
	$('.modeAllot_main li').hover(function(){
		$(this).find(".mask").remove();
		$(this).append("<div class='mask'><div class='mask_main'><div class='detel'><img src='static/images/delete.png' alt='删除' /></div><div class='update'><img src='static/images/update.png' alt='更新' /></div></div></div>");
		$('.mask_main').css({'margin-top':maskTop,'line-height':lineH+'px'});
	},function(){
		$(this).find(".mask").remove();
	});
}

function queding(){
	
	var machineName = $('#changeId').text(); //柜子名称
	var machineId = $('.modeTitle_name').attr('value'); //柜子Id
	if(machineName.length>0){
		var issues = new Array();
		$(".modeAllot_main ul li").each(function(i){
			var goodsId = $(this).find('#goodsId').val(); //商品Id
			var areaModelId = $(this).find('#areaModelId').val(); //模型Id
			var goodsNum = $(this).find('em').text(); //商品数量
			var goodsName = $(this).find('.mealName').text(); //商品名称
			if(goodsNum>0){
				issues.push({machineId: machineId, goodsId: goodsId, goodsName: goodsName, goodsNum: goodsNum, areaModelId: areaModelId});
			}
			else{
				$.messager.show({
					title : '提示',
						msg : '0份不用发布',
						timeout : 1500,
				});
			}
		});
		if(issues.length>0){
			$.ajax({
			    type: "POST",
			    url: "newTemplate/saveIssueList",
			    data: JSON.stringify(issues),//将对象序列化成JSON字符串  
			    dataType:"json",
			    contentType : 'application/json;charset=utf-8', //设置请求头信息  
			    success: function(data){
			    	/*if(data){
			    		$.messager.show({
							title : '提示',
								msg : '添加成功',
								timeout : 1500,
						});
			    		setTimeout(function(){
			    			window.location.href="goodsTemplate/toGoodsTemplate";
			    		}, 1500);
					}
			    	else{
			    		$.messager.show({
							title : '提示',
								msg : '设备格子数量不够',
								timeout : 1500,
						});
			    	}*/
			    	
			    	if(data.judge == true){
			    		$.messager.show({
							title : '提示',
							msg : data.message,
							timeout : 1500,
						});
				    	setTimeout(function(){
			    			window.location.href=urls['ctx']+"/newTemplate/goodsRetail";
			    		}, 1500);
			    	}
			    	else{
			    		$.messager.show({
							title : '提示',
							msg : data.message,
							timeout : 1500,
						});
			    	}
			    
			    	
			    	
			    }
			});
		}/*else{
			$.messager.show({
				title : '提示',
				msg : '请选择商品后再进行发布',
				timeout : 1500,
			});
		}*/
	}/*else{
		$.messager.show({
			title : '提示',
			msg : '柜子名称不能为空',
			timeout : 1500,
		});
	}*/
}


function getMachineNum(machineId, areaModelId){
	$.ajax({
        url: "newTemplate/machineNum",
        type: "post",
        data:"machineId=" + machineId + "&areaModelId=" + areaModelId,
        async:false,
        success:function(data){
        	localStorage.setItem("tempNum", data);
            machineNum = data;
            $('.modeAllot_botm').find('em').text(machineNum);//aa
        }
    })
}