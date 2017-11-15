$(function(){
	localStorage.setItem("tName",  $('#getTemplateName').val());
	/*加载左边的早中晚夜分类*/
	var getTemplateId = $('#getTemplateId').val();
	$.ajax({
		type:"post",
		url:"retail/modelList",
		data:"getTemplateId="+getTemplateId,
		dataType:'json',
		async:false,
		success:function(result){
			for(var i=0; i<result.length; i++){
				$('.modeAllot_tab ul').append("<li><div title='"+result[i].area_model_name+result[i].retail_percent+"%'><input class='mealsnum' type='hidden' value="+result[i].area_model_id+">"+result[i].area_model_name+"<em>"+result[i].retail_percent+"</em>%</div></li>");
				$('.modeAllot_main').append('<ul></ul>');
			}
			$('.modeAllot_main').find('ul').eq(0).css({'display':'block'});
			$('.modeAllot_main').height($('.modeAllot').height()-$('.modeAllot_tab').height()-$('.modeAllot_botm').height()-20+'px');
			/*切换早中晚夜类别时的事件*/
			//文字溢出时显示全称
			/*$('.modeAllot_tab li').hover(function(){
				$('.showname').empty();
				var tabname=$(this).find('div').text();
				$('.showname').text(tabname);
				$('.showname').show().css({'width':$('.modeTitle').width()-24});
			},function(){
				$('.showname').hide();
			})*/
			//cccc
			for(var i=0;i<$('.modeAllot_tab li').length;i++){
				$('.modeAllot_main ul').eq(0).addClass("now");
				$in=i;
				var mealvalue=$('.modeAllot_tab li').eq(i).find('input').attr('value'); //模型id
				var changeId = $('#changeId').text(); //页面上的值
				goodsData(mealvalue,changeId,$in);
				function goodsData(mealvalue,changeIdm,$in){
					
					$.ajax({
				        url: "retail/check",
				        type: "post",
				        data:"areaModelId=" + mealvalue +"&templateId=" + getTemplateId + "&changeId" + changeId,
				        dataType:'json',
				        async:false,
				        success:function(data){
				        	for(var i=0; i<data.length; i++){
				        		if(data.length>0){
				        			$('.modeAllot_main ul').eq($in).append("<li class='mealsnum2 cle'><span class='bor lf'></span><div class='mealName lf'>"+data[i].goods_name+"</div><input id='goodsId' type='hidden' value='"+data[i].goods_id+"'><input class='mealsnum' id='areaModelId' type='hidden' value='"+data[i].area_model_id+"'><div class='pct lr'><em>"+data[i].retail_percent+"</em>%</div></li>");
				        		}
				        	}
				        	/*因为append新加入的 li不在原本的 $('.modeAllot_main li') 集合里
							 * 因此新加入的没有被绑定事件 所以要再次调用布局和mask
							 * */

							mask();
				        	/*
							 *林智 2016/4/22
							 * 修改了设置比例未超过100时变红提示
							 */
							var botminp=$('.this').find('em').text();
							if(botminp<100){
								$('.modeAllot_botm').css({'color':'red'});
							}else{
								$('.modeAllot_botm').css({'color':'#090909'});
							}
							
							
							$('.modeRight_meal li').find('span').css({'background-color':'#2aa2ed'});//初始的右边餐品都是可选状态
							
							
							//已设置的比例
							var sum=$('.modeAllot_tab .now').find('em').text();
							$('.modeAllot_botm').find('em').text(sum);
							//$('.modeAllot_botm').find('em').remove();
							//$('.modeAllot_botm').html("已设置比例：<em>"+sum+"</em>");
							//设置比例达到100%确定按钮变色
							/*$('.modeAllot_botm').find('em').text(sum);
							if(sum==100){
								$('.modeAllot_botm').css({'color':'#333'});
								$('.SureBtn').css({'border':'2px solid #2aa2ed','color':'#2aa2ed','cursor':'pointer'});
							}else{
								$('.modeAllot_botm').css({'color':'red'});
								$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
							}*/
							var flag;
							var flag2;
							if($('.modeAllot_botm').find('em').text()==100||$('.modeAllot_botm').find('em').text()==0){
								for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
									if($('.modeAllot_tab').find('li').eq(i).find('em').text()==100){
										flag=true;
										break;
									}
								}
								if(flag){
									for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
										if($('.modeAllot_tab').find('li').eq(i).find('em').text()>0 && $('.modeAllot_tab').find('li').eq(i).find('em').text()<100){
											flag2=true;
											break;
										}
									}
									if(flag2){
										$.messager.show({
											title : '提示',
											msg : '有分类比例未达100%',
											timeout : 1500,
										});
									}else{
										$('.modeAllot_botm').css({'color':'#333'});
										$('.SureBtn').css({'border':'2px solid #2aa2ed','color':'#2aa2ed','cursor':'pointer'});
										flag=false;
									}
									
								}else{
									$('.modeAllot_botm').css({'color':'red'});
									$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
								}
							}else{
								$('.modeAllot_botm').css({'color':'red'});
								$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
							}
				        },
				       
					});
				}
        		/*if($('.modeAllot_tab li').eq(i).find('input').val()==$('.modeAllot_main ul').eq(0).find('#areaModelId').val()){*/
        			$('.modeAllot_tab li').eq(0).addClass('now');
        		/*}*/
			}
			/*mask删除对话框确定事件*/
        	$('.deleteName_sure').click(function(){
        		var goodsId = $('.modeAllot_main .now').find('input').val();
        		var nownum=$('.modeAllot_tab .now').find('em').text();
        		$(this).parents('.dialog').hide();
        		var farg;
        		var foodname=localStorage.getItem("foodname");
        		var foodnum;
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
        			$('.modeAllot_tab .now').find('em').text(parseInt(nownum)-parseInt(foodnum));
        			$('.modeAllot_botm').find('em').text(parseInt(nownum)-parseInt(foodnum));
        			$(".modeAllot_main ul.now li").eq(i).remove();
        			/*if(parseInt(nownum)-parseInt(foodnum)<100){
						$('.modeAllot_botm').css({'color':'red'});
						$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
					}else{
						$('.modeAllot_botm').css({'color':'#090909'});
						$('.SureBtn').css({'border':'2px solid #2aa2ed','color':'#2aa2ed','cursor':'pointer'});
					}*/
        			var flag;
        			var flag2;
        			if($('.modeAllot_botm').find('em').text()==100||$('.modeAllot_botm').find('em').text()==0){
        				for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
        					if($('.modeAllot_tab').find('li').eq(i).find('em').text()==100){
        						flag=true;
        						break;
        					}
        				}
        				if(flag){
        					for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
								if($('.modeAllot_tab').find('li').eq(i).find('em').text()>0 && $('.modeAllot_tab').find('li').eq(i).find('em').text()<100){
									flag2=true;
									break;
								}
							}
							if(flag2){
							}else{
								$('.modeAllot_botm').css({'color':'#333'});
								$('.SureBtn').css({'border':'2px solid #2aa2ed','color':'#2aa2ed','cursor':'pointer'});
								flag=false;
							}
        				}else{
        					$('.modeAllot_botm').css({'color':'red'});
        					$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
        				}
        			}else{
        				$('.modeAllot_botm').css({'color':'red'});
        				$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
        			}
        			
        		}
        		/*var changeId = $('#changeId').text(); //页面上的值
        		
        		var areaModelId = $('.modeAllot_main ul li').find(".mealsnum").val();
        		alert(areaModelId);
        		
        		var areaModelId = $('.modeAllot_tab .now').find('input').val();
        	
        		alert(goodsId);
        		
    			
    			
        		$.ajax({
        	        url: "retail/delete",
        	        type: "post",
        	        data:"code="+code,
        	        dataType:"text",
        	        success:function(text){
        	            if(text!=""){
        	                var checkSpan = $('#checkSpan');
        	                checkSpan.text(text);
        	            }
        	            else{
        	                var checkSpan = $('#checkSpan');
        	                checkSpan.text("");
        	            }
        	        }
                })*/
        		
        		
        		
        	});
			$('.modeAllot_tab li').click(function(){
				//切换时的效果状态bb
				$(this).addClass('now');
				$(this).siblings().removeClass('now');
				var $index=$(this).index();
				var number=$(this).find('em').text();
				$('.modeAllot_botm').find('em').text(number);//左边下面已设置比例赋值
				if(parseInt(number)<100){
					$('.modeAllot_botm').css({'color':'red'});
				}else{
					$('.modeAllot_botm').css({'color':'#090909'});
				}
				$('.modeAllot_main').find('ul').removeClass('now').hide();
				$('.modeAllot_main').find('ul').eq($index).addClass('now').show();
				var amId = $('.modeAllot_tab .now').find(".mealsnum").val();
				newMealsList(amId);
				/*var mealvalue=$(this).find('input').attr('value'); //模型id
				var changeId = $('#changeId').text(); //页面上的值
*/				/*$('.modeAllot_main ul.now').find(".mealsnum2").remove();/*先清空*/
				$.ajax({
			        url: "retail/check",
			        type: "post",
			        data:"areaModelId=" + mealvalue + "&templateId=" + getTemplateId + "&changeId" + changeId,
			        dataType:'json',
			        async:false,
			        success:function(data){
			        	for(var i=0; i<data.length; i++){
			        		/*$('.modeAllot_main .now').append("<li class='mealsnum2'><div><input class='mealsnum' type='hidden' value="+data[i].area_model_id+">"+data[i].goods_name+"<em>"+data[i].retail_percent+"</em></div></li>");*/
			        		/*$('.modeAllot_main ul.now').append("<li class='mealsnum2 cle'><span class='bor lf'></span><div class='mealName lf'>"+data[i].goods_name+"</div><input id='goodsId' type='hidden' value='"+data[i].goods_id+"'><input class='mealsnum' id='areaModelId' type='hidden' value='"+data[i].area_model_id+"'><div class='pct lr'><em>"+data[i].retail_percent*100+"</em>%</div></li>");*/
			        		//ddd
			        		
			        		/*localstorage.setItem('goodsId',goodsId);*/
			        		/*因为append新加入的 li不在原本的 $('.modeAllot_main li') 集合里
							 * 因此新加入的没有被绑定事件 所以要再次调用布局和mask
							 * */
							/*var liHeight=$('.modeAllot_main').height()*0.105;
							$('.modeAllot_main li').css({"height":liHeight});
							var lineH=$('.modeAllot_main li').height()*0.57;
							var marTop=$('.modeAllot_main li').height()*0.2;
							$('.mealName').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
							$('.pct').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
							$('.bor').height($('.modeAllot_main li').height());*/

							mask();
							//加载完后，对已添加的数据做修改后，再加载数据时，清除加载的数据，保留对已添加的数据进行修改后的值
							var len = $('.modeAllot_main ul.now').find('li').length;
			    			for(var j=0;j<len;j++)
			    			{
			    				$index=j;
			    				//mealsnum3为对已添加的数据进行修改后的样式
			    				var goodsId = $(".modeAllot_main ul.now .mealsnum3").eq(j).find("#goodsId").val();
			    				if(goodsId == data[i].goods_id)
			    				{
			    					//清除重新加载的数据
			    					$(".modeAllot_main ul.now .mealsnum2").eq(i).remove();
			    				}
			    			}
			        	}
			        	
			        	/*
						 *林智 2016/4/22
						 * 大到100%时确定按钮可用
						 */
			        	var flag;
			        	var flag2;
						if($('.modeAllot_botm').find('em').text()==100||$('.modeAllot_botm').find('em').text()==0){
							for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
								if($('.modeAllot_tab').find('li').eq(i).find('em').text()==100){
									flag=true;
									break;
								}
							}
							if(flag){
								for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
									if($('.modeAllot_tab').find('li').eq(i).find('em').text()>0 && $('.modeAllot_tab').find('li').eq(i).find('em').text()<100){
										flag2=true;
										break;
									}
								}
								if(flag2){
								}else{
									$('.modeAllot_botm').css({'color':'#333'});
									$('.SureBtn').css({'border':'2px solid #2aa2ed','color':'#2aa2ed','cursor':'pointer'});
									flag=false;
								}
							}else{
								$('.modeAllot_botm').css({'color':'red'});
								$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
							}
						}else{
							$('.modeAllot_botm').css({'color':'red'});
							$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
						}
						/*if($('.modeAllot_botm').find('em').text()==100){
							$('.modeAllot_botm').css({'color':'#333'});
							$('.SureBtn').css({'border':'2px solid #2aa2ed','color':'#2aa2ed','cursor':'pointer'});

						}else{
							$('.modeAllot_botm').css({'color':'red'});
							$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
						}*/
			        }
			    });
				//给左边当前的餐品列表赋value
				/*var mealvalue=$(this).find('input').attr('value');
				alert($('.modeAllot_main').find('.now').find('li').length);
				for(var i=0;i<$('.modeAllot_main').find('.now').find('li').length;i++){
					$('.modeAllot_main').find('.now').find('li').append("<input id='areaModelId' type='hidden' value='"+mealvalue+"'>")
				}*/
				
				
				
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
				
				
				
				
				/* 右边餐品列表点击的时候*/
				$('.modeRight_meal').find('li').on('click',function(){
					$this=$(this);
					modeRightMealClick($this);
				});
				
			});
		},
		Error:function(){
			alert('百分比分类加载出错了！');
		}
	});
	/*根据早中晚夜分类添加对应的ul*/
	$.each($('.modeAllot_tab li'), function() {
		/*$('.modeAllot_main').append("<ul></ul>");*/
	});
	
	/*$('.SureBtn').click(function(){
		alert("你好");
	})*/
	
	
	//修改模板时根据模板id获取零售列表
	/*$.ajax({
	    type:'post',      
	    url:'retail/getGoodsList',
	    async:true,
		success:function(data){
	    	$.each(data,function(i){
	    		//alert("查询到的gid:"+data[i].gid+"查询到的amid:"+data[i].amid+"name:"+data[i].gname);
				//var value=data[i].gid+","+data[i].amid;
				//将返回的数据拼写到页面
				$('.releaseRight_meal ul').append("<li><input id='gid' type='hidden' value='"+data[i].gid+"'/><input id='amid' type='hidden' value='"+data[i].amid+"'/><img src='"+urls['ctx'] +"/views/preissue/image/releaseLeftMealBg.png'><div>"+data[i].gname+"</div></li>");
				$('.modeAllot_main ul.now').append("<li class='cle'><input id='gid' type='hidden' value='"+data[i].gid+"'/><input id='amid' type='hidden' value='"+data[i].amid+"'/></li>");
			});
	    }
	});*/
	
	
	
	//加载右边的分类
	$.ajax({
		type:"post",
		url:"retail/goodsCategory",
		dataType:'json',
		async:false,
		success:function(data){
			for(var i=0; i<data.length; i++){
				$('.modeRight_tab ul').append("<li value='' class='data'><img src='static/images/releaseRightTabBg.png'><div>"+data[i].goods_category_name+"<input class='mealsnum' type='hidden' value="+data[i].goods_category_id+"></input></div></li>");
			}
			$('.modeRight_tab li').eq(0).addClass('tabActive');
			mealsList(0);
			
			/*餐品(全部,水果，零食，其他……)分类点击的时候*/
			$('.modeRight_tab li').click(function(){
				$(this).addClass('tabActive').end().siblings().removeClass('tabActive');
				$(this).find('img').attr('src',"static/images/tabActive.png");
				$(this).siblings().find('img').attr('src',"static/images/releaseRightTabBg.png");
				var goodsCategoryId = $(this).find(".mealsnum").val();
				$('.modeRight_meal ul').empty();/*先清空*/
				mealsList(goodsCategoryId);/*再重新加载*/
			});
			
			/*餐品(全部,水果，零食，其他……)分类布局*/
			$('.modeRight_tab li').width($('.modeRight_tab').width()/$('.modeRight_tab li').length);
			
			
			
		}
		
	});
	/*点击右边食品分类的时候切换状态,清空原有的内容,重新加载*/
	/*mealsList(goodsCategoryId);*/
	function mealsList(goodsCategoryId){
		var areaModelId = $('.modeAllot_tab .now').find(".mealsnum").val();
		if(areaModelId == undefined){
			areaModelId = 0;
		}
		$.ajax({
			type:"post",
			url:"retail/goodsDetail",
			data:"goodsCategoryId=" + goodsCategoryId + "&areaModelId=" + areaModelId,
			dataType:'json',
			async:false,
			success:function(data){
				if(data.length == 0){
					$('.modeRight_meal ul').append("无可用餐品");
				}
				else{
					for(var i=0; i<data.length; i++){
						$('.modeRight_meal ul').append("<li><span></span><input id='gid' type='hidden' value='"+data[i].goods_id+"'/><div title="+data[i].goods_name+">"+data[i].goods_name+"</div></li>");
						/*$('.modeAllot_main ul.now').append("<li class='cle'><input id='gid' type='hidden' value='"+data[i].goods_id+"'/></li>");*/
					}
				}
				/*右边的布局*/
				//ccc
				$('.modeRight_meal li').css({'height':$('.modeMain').height()*0.067+'px',"line-Height":$('.modeMain').height()*0.067+'px'});
				$('.modeRight_meal li span').height($('.modeRight_meal li').height());
				$.each($('.modeRight_meal li'), function(i, value) {
					if($(window).width()<1440){
						if($(this).find('div').text().length>7){
							$(this).find('div').css({'line-height':"15px",'marginTop':$('.modeRight_meal li').height()/2-15+'px'});
						}
					}else{
						if($(this).find('div').text().length>7){
							$(this).find('div').css({'line-height':"20px",'marginTop':$('.modeRight_meal li').height()/2-18+'px'});
						}
					}
				
				});	
				/* 右边餐品列表点击的时候*/
				$('.modeRight_meal').find('li').on('click',function(){
					$this=$(this);
					modeRightMealClick($this);
				});
			},
			Error:function(){
				alert('右边食品分类的时候出错了！');
			}
		});
	}
	function modeRightMealClick($this){
		$this.find('span').css({'background-color':'#dfdfdf','cursor':'auto'});//点击后此餐品不可选
		
		//如果有同名餐品就在左边对应分类列表下添加餐品
		var farg;
		var len = $('.modeAllot_main ul.now').find('li').length;
		var mealName = $this.find('div').text();
		/*var goodsCategoryId = $(this).find(".mealsnum").val();
		alert(goodsCategoryId);
		var gid = $('#gid').val();
		alert(gid);*/
		for(var i=0;i<len;i++)
		{
			var dishName = $(".modeAllot_main ul.now li").eq(i).find(".mealName").text();
			if( dishName == mealName || parseInt($('.modeAllot_botm').find('em').text())>=100)
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
			/*$.ajax({
			    type:'post',      
			    url:'goods/getGoodsList',
			    dataType:'json', 
			    data:{'goodsCategoryId':goodsCategoryId,'goodsId':modelId},
			    async:false, 
			    success:function(data){
			    	$.each(data,function(i){
			    		//alert("查询到的gid:"+data[i].gid+"查询到的amid:"+data[i].amid+"name:"+data[i].gname);
						//var value=data[i].gid+","+data[i].amid;
						//将返回的数据拼写到页面
						$('.releaseRight_meal ul').append("<li><input id='gid' type='hidden' value='"+data[i].gid+"'/><input id='amid' type='hidden' value='"+data[i].amid+"'/><img src='"+urls['ctx'] +"/views/preissue/image/releaseLeftMealBg.png'><div>"+data[i].gname+"</div></li>");
						$('.modeAllot_main ul.now').append("<li class='cle'><input id='gid' type='hidden' value='"+data[i].gid+"'/><input id='amid' type='hidden' value='"+data[i].amid+"'/></li>");
					});
			    }
			});	*/

			
			
			var mealvalue=$this.find('input').attr('value');
			var leftMealValue=$('.modeAllot_tab').find('.now').find('input').attr('value');
			$('.modeAllot_main ul.now').append("<li class='cle'><span class='bor lf'></span><div class='mealName lf'>"+mealName+"</div><input id='goodsId' type='hidden' value='"+mealvalue+"'><input id='areaModelId' type='hidden' value='"+leftMealValue+"'><div class='pct lr'><em>0</em>%</div></li>");
			
		}
		
		//如果左边没有选中（早中晚夜）的话，弹出提示框
		if($('.now').length<=0){
			$.messager.show({
				title : '提示',
				msg : '请选择左侧上方的餐类型再进行添加',
				timeout : 1500,
			});
		}
	
		/*因为append新加入的 li不在原本的 $('.modeAllot_main li') 集合里
		 * 因此新加入的没有被绑定事件 所以要再次调用布局和mask
		 * */
		/*var liHeight=$('.modeAllot_main').height()*0.105;
		$('.modeAllot_main li').css({"height":liHeight});
		var lineH=$('.modeAllot_main li').height()*0.57;
		var marTop=$('.modeAllot_main li').height()*0.2;
		$('.mealName').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
		$('.pct').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
		$('.bor').height($('.modeAllot_main li').height());*/

		mask();
	}
	
	
	function newMealsList(areaModelId){
		$('.modeRight_meal ul').empty();//先清空
		var goodsCategoryId = $('.modeRight_tab li').find(".mealsnum").val();
		
		$.ajax({
			type:"post",
			url:"retail/goodsDetail",
			data:"goodsCategoryId=" + goodsCategoryId + "&areaModelId=" + areaModelId,
			dataType:'json',
			async:false,
			success:function(data){
				if(data.length == 0){
					$('.modeRight_meal ul').append("无可用餐品");
				}
				else{
					for(var i=0; i<data.length; i++){
						$('.modeRight_meal ul').append("<li><span></span><input id='gid' type='hidden' value='"+data[i].goods_id+"'/><div title="+data[i].goods_name+">"+data[i].goods_name+"</div></li>");
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
					var len = $('.modeAllot_main ul.now').find('li').length;
					var mealName = $(this).find('div').text();
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
						var mealvalue=$(this).find('input').attr('value');
						var leftMealValue=$('.modeAllot_tab').find('.now').find('input').attr('value');
						/*$('.modeAllot_main ul.now').append("<li class='cle'><span class='bor lf'></span><div class='mealName lf'>"+mealName+"</div><div class='pct lr'><em>0</em>%</div></li>");*/
						$('.modeAllot_main ul.now').append("<li class='cle'><span class='bor lf'></span><div class='mealName lf'>"+mealName+"</div><input id='goodsId' type='hidden' value='"+mealvalue+"'><input id='areaModelId' type='hidden' value='"+leftMealValue+"'><div class='pct lr'><em>0</em>%</div></li>");
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
					/*var liHeight=$('.modeAllot_main').height()*0.105;
					$('.modeAllot_main li').css({"height":liHeight});
					var lineH=$('.modeAllot_main li').height()*0.57;
					var marTop=$('.modeAllot_main li').height()*0.2;
					var maskTop=$('.modeAllot_main li').height()*0.22;
					$('.mealName').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
					$('.pct').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
					$('.bor').height($('.modeAllot_main li').height());*/
					mask();
				});
			},
			Error:function(){
				alert('右边食品分类的时候出错了！');
			}
		});
	}
	
	
	
	
	
	
	
	

	
/*布局
 ---------------------------------------start*/
	$('.modeName_input').find('input').val('');
	var modeRightWidth=$('.modeTop').width()-$('.modeLeft').width()-10;
	/*var modeRightHeight=$('.modeTop').height();*/
	var modeMainHei = $(".modeMain").height();
	$('.modeTop').height(modeMainHei-70);
	var modeRightHeight = $('.modeTop').height();
	$('.modeRight').width(modeRightWidth).height(modeRightHeight-2);
	$('.modeAllot').height($('.modeTop').height()-88);
	$('.modeAllot_main').height($('.modeAllot').height()-$('.modeAllot_tab').height()-$('.modeAllot_botm').height());
	$('.modeRight_meal').height($('.modeRight').height()-$('.modeRight_tab').height()+7);
	
	/*var liHeight=$('.modeAllot_main').height()*0.105;
	$('.modeAllot_main li').css({"height":liHeight});
	var lineH=$('.modeAllot_main li').height()*0.57;
	var marTop=$('.modeAllot_main li').height()*0.2;
	$('.mealName').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
	$('.pct').css({'height':lineH,'margin-top':marTop,'line-height':lineH+'px'});
	$('.bor').height($('.modeAllot_main li').height());*/
	
	/*下面的布局*/
	var sureBtnH=$('.modeBotm').height()*0.583;
	var sureBtnW=$('.modeBotm').width()*0.085;
	var backW=$('.modeBotm').width()*0.076;
	$('.SureBtn').css({'height':sureBtnH+'px','width':sureBtnW+'px','line-Height':sureBtnH+'px','margin-top':$('.modeBotm').height()*0.166+'px'});
	$('.back').css({'height':sureBtnH+'px','width':backW+'px','line-Height':sureBtnH+'px','top':$('.modeBotm').height()*0.166+'px'});
	
	mask();
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
		}else{
			$('.dialog').hide();
	   		$('.deleteName').parent().show();
	   		$('.mealAllot').parent().attr('value','');
	   		var foodname=$(this).parents('.mask').siblings('.mealName').text();
	   		localStorage.setItem("foodname",foodname);
	   		$('.deleteName').find('.foodName').text(foodname);
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
		var pct=localStorage.getItem("pct");
		var farg;
		var allsum=0;
		var leftname=localStorage.getItem("name");
		var inp=Math.ceil($('.mealAllot .inpText').find('input').val());
		var nowinp=$('.modeAllot_tab .now').find('em').text();
		var len = $('.modeAllot_main ul.now').find('li').length;
		var percentCheck=$("#percentCheck").val();
		/*
		 *林智 2016/4/22
		 * 修改了设置比例超过100时不能添加
		 */
		var botminp=$('.modeAllot_botm').find('em').text();
		if(botminp<100){
			$('.modeAllot_botm').css({'color':'red'});
		}else{
			$('.modeAllot_botm').css({'color':'#090909'});
		}
		var reg=/^[0-9]*[1-9][0-9]*$/;
		var re = new RegExp(reg);
		if(parseInt(percentCheck)<0){
			 $.messager.show({
 				title : '提示',
 				msg : '商品比率不能为负数',
 				timeout : 3000,
 			});
			 $('.mealAllot').parent().attr('value','');
		}else if (percentCheck.match(re) == null) {
			 $.messager.show({
 				title : '提示',
 				msg : '请输入正整数',
 				timeout : 3000,
 			});
			 $('.mealAllot').parent().attr('value','');
		}
		//百分比达到100的时候确定按钮才能使用
		else if((parseInt(inp)-parseInt(pct))+parseInt(nowinp)<=100){
			$(this).parents('.dialog').hide();
			for(var i=0;i<len;i++)
			{
				//去除class样式
				$(".modeAllot_main ul.now li").eq(i).removeClass("mealsnum2");
				//加上mealsnum3样式，标记为重新修改已添加的数据
				$(".modeAllot_main ul.now li").eq(i).addClass("mealsnum3");
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
			$('.mealAllot').parent().attr('value','');
			$('.modeAllot_tab .now').find('em').text(allsum);//给对应的(早中晚夜)赋值比例
			$('.modeAllot_botm').find('em').text(allsum);//给左边下面的已设置比例赋值
			
			//$('.modeAllot_botm').find('em').remove();
			//$('.modeAllot_botm').html("已设置比例：<em>"+allsum+"</em>%");//给左边下面的已设置比例赋值
			
			/*
			 *林智 2016/4/22
			 * 修改了设置比例未超过100时变红提示
			 */
			/*if($('.modeAllot_botm').find('em').text()==100){
				$('.modeAllot_botm').css({'color':'#333'});
				$('.SureBtn').css({'border':'2px solid #2aa2ed','color':'#2aa2ed','cursor':'pointer'});

			}else{
				$('.modeAllot_botm').css({'color':'red'});
				$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
			}*/
			var flag;
			var flag2;
			if($('.modeAllot_botm').find('em').text()==100||$('.modeAllot_botm').find('em').text()==0){
				for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
					if($('.modeAllot_tab').find('li').eq(i).find('em').text()==100){
						flag=true;
						break;
					}
				}
				if(flag){
					for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
						if($('.modeAllot_tab').find('li').eq(i).find('em').text()>0 && $('.modeAllot_tab').find('li').eq(i).find('em').text()<100){
							flag2=true;
							break;
						}
					}
					if(flag2){
					}else{
						$('.modeAllot_botm').css({'color':'#333'});
						$('.SureBtn').css({'border':'2px solid #2aa2ed','color':'#2aa2ed','cursor':'pointer'});
						flag=false;
					}
				}else{
					$('.modeAllot_botm').css({'color':'red'});
					$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
				}
			}else{
				$('.modeAllot_botm').css({'color':'red'});
				$('.SureBtn').css({'border':'2px solid #b3b3b3','color':'#b3b3b3','cursor':'auto'});
			}
			
		}
		else{
			$.messager.show({
				title : '提示',
				msg : '比例不能超过100%',
				timeout : 1500,
			});
			
		}
	});
	/*点击左上角修改模板的时候*/
	$('.change').click(function(){
		/*var checkSpan = $('#checkSpan');
		checkSpan.style.visibility="hidden";*/
		
		if($('.mealAllot').parent().attr('value')=='show'){
			$.messager.show({
 				title : '提示',
 				msg : '您的商品还未分配完成',
 				timeout : 3000,
 			});
		}else{
			document.getElementById("checkSpan").style.visibility="hidden";//隐藏
			$('.dialog').hide();
			$('.modeName').parent().show();
			$('.mealAllot').parent().attr('value','');
		}
		/*var changeId = $('#changeId').val();
		var template = $('#templateName').val();
		
		
		$('.modeName').parent().show();
		$('.modeName_input').find('input').val('');
		
		$.ajax({
			url : "retail/change",
			type : "post",
			data : "changeId=" + changeId + "&template=" + template,
			dataType : "text",
			success : function(text) {
				if (text != "") {
					var checkSpan = $('#checkSpan');
					checkSpan.text(text);
					
				} else {
					$('.modeName').parents('.dialog').hide();
					$('.modeTitle').find('.modeTitle_name').text(modename);
				}
			}
		})*/
		
	})
	/*点击修改模板弹窗的确定按钮时*/
	$('.modeName_sure').click(function(){
		
		var template = $('#templateName').val(); //小文本框内的值
		if(template.length > 10){
			$.messager.show({
				title : '提示',
				msg : '输入长度太长',
				timeout : 1500,
			});
		}
		else{
			var changeId = $('#changeId').text(); //页面上的值
			var getTemplateId = $('#getTemplateId').val();
			var modename=$('.modeName_input').find('input').val();
			if(modename.length>0){
				$.ajax({
					url : "retail/change",
					type : "post",
					data : "changeId=" + changeId + "&template=" + template + "&getTemplateId=" + getTemplateId,
					async:false,
					dataType : "text",
					success : function(text) {
						if(text == "模板名已存在"){
							$.messager.show({
								title : '提示',
								msg : text,
								timeout : 1500,
							});
						}
						else{
							//document.getElementById("checkSpan").style.visibility="visible";//显示
							//var checkSpan = $('#checkSpan');
							//checkSpan.text(text);
							$.messager.show({
								title : '提示',
								msg : text,
								timeout : 1500,
							});
							$('.modeName').parents('.dialog').hide();
							$('.modeTitle').find('.modeTitle_name').text(modename);
						}
					}
				});
			}
			else{
				$.messager.show({
					title : '提示',
					msg : '请填写模板名称',
					timeout : 1500,
				});
			}
		}
		
	});
	/*餐品列表分类 */
	$('.modeRight_tab li').width($('.modeRight_tab').width()/$('.modeRight_tab li').length);
	
	/*所有对话框取消按钮*/
	$('.btnConcle').click(function(){
		$(this).parents('.dialog').hide().attr('value','');
		var tId = $('#getTemplateId').val(); //获取页面传过来的模板Id
		var tempId = $('#changeId').text(); //获取页面的模版Id
		var tIdBoolean = false;
		var tempIdBoolean = false;
		if(tId != "" && tId != null){
			tIdBoolean = true;
		}
		if(tempId != "" && tempId != null){
			tempIdBoolean = true;
		}
		if(tIdBoolean || tempIdBoolean){
			if(tId != ""){
				$('.modeTitle').find('.modeTitle_name').text(localStorage.getItem("tName"));
			}
		}
		else{
			window.location.href=urls['ctx']+"/goodsTemplate/toGoodsTemplate";
		}
		
		/*window.location.href="goodsTemplate/toGoodsTemplate";*/
		/*window.history.go(-1);*/
		
		/*var modename=$('.modeName_input').find('input').val();
		$('.modeTitle').find('.modeTitle_name').text(modename);*/
	});
	$('.concle').click(function(){
		$(this).parents('.dialog').hide();
	});
})


function mask(){
	/**零售分配左边列表经过*/
	$('.modeAllot_main li').hover(function(){
		$(this).find(".mask").remove();
		$(this).append("<div class='mask'><div class='mask_main'><div class='detel'><img src='static/images/delete.png' alt='删除' /></div><div class='update'><img src='static/images/update.png' alt='更新' /></div></div></div>");
		//$('.mask_main').css({'margin-top':marTop,'line-height':lineH+'px'});
	},function(){
		$(this).find(".mask").remove();
	});
}

function queding(){
	var temId = $('#changeId').text();
	var flag;
	var flag2;
	if(temId.length>0){
		var issues = new Array();
		$(".modeAllot_main ul li").each(function(i){
			var goodsId = $(this).find('#goodsId').val();
			var areaModelId=$(this).find('#areaModelId').val();
			var percent=$(this).find('em').text();
			issues.push({templateName: temId,goodsId: goodsId,areaModelId: areaModelId,retailPercent:percent});
		});
		/*alert(JSON.stringify(issues));*/
		if(issues.length>0){
			if($('.modeAllot_botm').find('em').text()==100||$('.modeAllot_botm').find('em').text()==0){
				for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
					if($('.modeAllot_tab').find('li').eq(i).find('em').text()==100){
						flag=true;
						break;
					}
				}
				if(flag){
					for(var i=0;i<$('.modeAllot_tab').find('li').length;i++){
						if($('.modeAllot_tab').find('li').eq(i).find('em').text()>0 && $('.modeAllot_tab').find('li').eq(i).find('em').text()<100){
							flag2=true;
							break;
						}
					}
					if(flag2){
						$.messager.show({
							title : '提示',
							msg : '有分类比例未达100%',
							timeout : 1500,
						});
					}else{
						$.ajax({
						    type: "POST",
						    url: "retail/saveIssueList",
						    data: JSON.stringify(issues),//将对象序列化成JSON字符串  
						    dataType:"json",
						    contentType : 'application/json;charset=utf-8', //设置请求头信息  
						    success: function(data){
						    	if(data){
					    		$.messager.show({
									title : '提示',
										msg : '发布成功',
										timeout : 1500,
									});
					    		setTimeout(function(){
					    			window.location.href=urls['ctx']+"/goodsTemplate/toGoodsTemplate";
					    		}, 1500);
								}
						    }
						});
						flag=false;
					}
				}else{
					$.messager.show({
						title : '提示',
						msg : '比例达到100%才能发布',
						timeout : 1500,
					});
				}
			}
			else{
				$.messager.show({
					title : '提示',
					msg : '比例达到100%才能发布',
					timeout : 1500,
				});
			}
		}else{
			$.messager.show({
				title : '提示',
				msg : '请选择商品后再进行发布',
				timeout : 1500,
			});
		}
	}else{
		$.messager.show({
			title : '提示',
			msg : '模板不能为空',
			timeout : 1500,
		});
	}
}
