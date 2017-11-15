$(function(){
	//加载模板信息
	$.ajax({
		type:"post",
		url:urls["ctx"]+"/goodsRetail/getAllAreaModelList",
		async:true,
		dataType:'json',
		success:function(data){
			if (data.code == '0'){
				$.messager.show({
					title : '提示',
					msg : data.content,
					timeout : 5000,
				});
			} else{
				console.log(data.content);
				for (var i=0; i<data.content.length; i++){
					console.log(data.content[i].areaModelId);
					$('.mdoeList_choice').append("<li id='"+data.content[i].areaModelId+"' title='"+data.content[i].areaModelName+"'>"+data.content[i].areaModelName+"</li>");

				}
				localStorage.setItem("areaModelId", data.content[0].areaModelId);
				$('.mdoeList_choice').find('li').eq(0).addClass("now");
				var mdoename=$('.mdoeList_choice').find('.now').text();
				var mdoeid=$('.mdoeList_choice').find('.now').attr("id");
				$('.mdoeList').find('em').text(mdoename).attr('title',mdoename);
				getGoodsRetailDate(mdoeid);
				//头部模板切换点击事件
				var falg=0
				$('.mdoeList').click(function(){
					if(falg==0){
						$('.mdoeList_choice').fadeIn(400);
						falg=1;
						$(this).addClass('active');
						$(this).find('span img').attr('src',urls["ctx"]+'/views/goodsRetail/image/down.png');
						
					}
					else{
						$('.mdoeList_choice').fadeOut(300);
						falg=0;
						$(this).removeClass('active');
						$(this).find('span img').attr('src',urls["ctx"]+'/views/goodsRetail/image/upp.png');
					}
					
				});
				//点击其他地方收起模版
				$('.content').click(function(){
					$('.mdoeList_choice').fadeOut(300);
					falg=0;
					$('.mdoeList').removeClass('active');
					$('.mdoeList').find('span img').attr('src',urls["ctx"]+'/views/goodsRetail/image/upp.png');
				});
				$('.time').click(function(){
					$('.mdoeList_choice').fadeOut(300);
					falg=0;
					$('.mdoeList').removeClass('active');
					$('.mdoeList').find('span img').attr('src',urls["ctx"]+'/views/goodsRetail/image/upp.png');
				});
				$('.cupboard').click(function(){
					$('.mdoeList_choice').fadeOut(300);
					falg=0;
					$('.mdoeList').removeClass('active');
					$('.mdoeList').find('span img').attr('src',urls["ctx"]+'/views/goodsRetail/image/upp.png');
				});
				$('.btn').click(function(){
					$('.mdoeList_choice').fadeOut(300);
					falg=0;
					$('.mdoeList').removeClass('active');
					$('.mdoeList').find('span img').attr('src',urls["ctx"]+'/views/goodsRetail/image/upp.png');
				});
				$('.botm').click(function(){
					$('.mdoeList_choice').fadeOut(300);
					falg=0;
					$('.mdoeList').removeClass('active');
					$('.mdoeList').find('span img').attr('src',urls["ctx"]+'/views/goodsRetail/image/upp.png');
				});
				$('.mdoeList_choice').find('li').click(function(){
					$(this).addClass('now');
					$(this).siblings().removeClass('now');
					var modename=$(this).text();
					$('.mdoeList').find('em').text(modename).attr('title',modename);
					falg=0;
					$('.mdoeList_choice').fadeOut(300);
					$('.mdoeList').removeClass('active');
					$('.mdoeList').find('span img').attr('src',urls["ctx"]+'/views/goodsRetail/image/upp.png');
					//console.log($(this).attr("id"));
					//判断是早中晚那个时间段模板然后重新加载下面的柜子数据
					getGoodsRetailDate($(this).attr("id"));
					localStorage.removeItem("areaModelId");
					localStorage.setItem("areaModelId", $(this).attr("id"));
				});
			}
		}
	});
	
	
/*
 *布局 ------------------------
*/
	//下面的布局
	var sureBtnH=$('.botm').height()*0.583;
	var sureBtnW=$('.botm').width()*0.085;
	var backW=$('.modeBotm').width()*0.076;
	$('.lookList').css({'height':sureBtnH+'px','width':sureBtnW+'px','line-Height':sureBtnH+'px','margin-top':$('.botm').height()*0.166+'px'});
	//柜子信息
	$('.content').height($('.modeMain').height()-60-$('.botm').height()-45);
	$('.content_main').height($('.content').height()-$('.content_title').height());
	//判断是否超出 超出出现滚动条后重现分配布局
	if($('.content_main_box').height()>$('.content_main').height())
	{
		$('.guiName').css({'width':'10.1%'});
		$('.guiIfo').css({'width':"89.9%"});
		$('.foodName').css({'width':'50.65%'});
		$('.foodNum').css({'width':'49.35%'});
		$('.total').css({'width':"89.9%"});
		$('.total_name').css({'width':'50.65%'});
		$('.total_num').css({'width':'49.35%'});
	}
/*
 *布局 结束------------------------
*/
function guiacitve(){
	var guilist=[];
	for(var i=0;i<$('.guilist').length;i++){
		total=0;
		var guih=60*($('.guilist').eq(i).find('.guiIfo').length+1)//设置guiName的高度
		$('.guiName').eq(i).height(guih);
		guilist.push(guih);
		//总计
		if($('.guilist').eq(i).find('.guiIfo').length>0){
			for(var j=0;j<$('.guilist').eq(i).find('.guiIfo').length;j++){
				total+=parseInt($('.guilist').eq(i).find('.guiIfo').eq(j).find('.num').text());
				$('.guilist').eq(i).find('.total_num').find('em').text(total);
			}
		}else{
			$('.guilist').eq(i).find('.total_num').find('em').text("0");
		}
		
	}
	console.log(guilist);
	//获取滚动的高度，给对应的柜子高亮
//		$(".content_main").scroll(function() {
//			var a=0;
//			for(var i=0;i<$('.guilist').length;i++){
//				$in=i;
//				var scrollTop = $('.content_main').scrollTop();
//				if(scrollTop >= guilist[$in])
//				{
//					a+=guilist[$in];
//					/*for(var j=0;j<guilist.length;j++){
//						if(scrollTop>=guilist[j]){
//							alert(j);*/
//							$('.plst_pt').find('li').eq(j).addClass('active');
//							$('.plst_pt').find('li').eq(j).siblings().removeClass('active');
//						/*}
//					}*/
//					
//				}
//				
//				if(scrollTop>=$('.content_main_box').height()-$('.content_main').height()){
//					
//				}
//			}
//		});
	$(".content_main").scroll(function(){
		 var wst =$('.content_main').scrollTop();
	});
}
//获取补货时间
function getGoodsRetailDate(areaModelId){
	$('#jcSide').empty();
	$('#jcContent').empty();
	$.ajax({
		type:"post",
		url:urls["ctx"]+"/goodsRetail/getGoodsRetailDate",
		data:{areaModelId:areaModelId},
		dataType:'json',
		async:true,
		success:function(data){
			//console.log(data);
			if (data.code == '0'){
				$('.time').find('span').text('');
				$.messager.show({
					title : '提示',
					msg : data.content,
					timeout : 5000,
				});
			}else {
				$('.time').find('span').text(data.content);
//				getMachine(areaModelId,data.content);
				getMachineRetailNum(areaModelId,data.content);
				
			}
		}
	});
}
//删除商品
function delGoodsRetail(id){
	$.ajax({
		type:"post",
		url:urls["ctx"]+"/goodsRetail/delGoodsRetail",
		data:{id:id},
		async:true,
		success:function(){
			$.messager.show({
				title : '提示',
				msg : '删除成功',
				timeout : 5000,
			});
		}
	});
}

//加载头部设备
function getMachine(machineNameArray){
	$('#jcSide').empty();

	for(var i=0;i<machineNameArray.length;i++){
		$('#jcSide').append("<li class=''><a rel='"+i+"'>"+machineNameArray[i]+"</a></li>");
	}
	$('#jcSide').find('li').eq(0).addClass('select');
	//头部柜子经过的时候
	$('.plst_pt').find('li').hover(function(){
		var name=$(this).find('a').text();
		if(name.length>0){
			$(this).append("<div class='allname'>"+name+"</div>");
		}
	},function(){
		$(this).find('.allname').remove();
	})
	//头部柜子点击的时候
	$('.plst_pt').find('li').click(function(){
		$(this).addClass('select');
		$(this).siblings().removeClass('select');
	});
	prevnext();
	//点击锚点效果
	$('#jcSide li').click(function(){
		var allTOP=0;
		$(this).addClass('select').siblings('li').removeClass('select');
	    var index = $(this).index();
		for(var i=0;i<index;i++){
			allTOP+=$('.guiName').eq(i).height();
		}
		$("#jcConWraper").stop().animate({ "top":-allTOP},500);	
	})			

}

//加载设备零售商品
function getMachineRetailNum(areaModelId,replenishmentTime){
	var time = $('.time').find('span').text();
	$.ajax({
		type:"post",
		url:urls["ctx"]+"/goodsRetail/getMachineRetailNum",
		data:{areaModelId:areaModelId,replenishmentTime:replenishmentTime},
		dataType:'json',
		async:true,
		success:function(data){
			$('#jcContent').empty();
			if (data.content != null && data.content.length > 0){
				var machineNameArray = new Array();
				for(var i=0;i<data.content.length;i++){
					machineNameArray.push(data.content[i].machineName);
					$('#jcContent').append("<dt class='guilist select cle' name='"+i+"' id='"+i+"'><li class='guiName' id='"+i+"'><div class='guiName_box'><p title='"+data.content[i].machineName+"'>"+data.content[i].machineName+"</p><div onclick=\"jump(\'"+data.content[i].machineId+"\',\'"+data.content[i].machineName+"\',\'"+time+"\',\'"+areaModelId+"\')\"  class='addFood'>添加餐品</div></div></li></dt>");
					var goodsRetailList=data.content[i].goodsRetailList;
					if (goodsRetailList != null && goodsRetailList.length>0){
						var totalNum=0;
						for(var j=0;j<goodsRetailList.length;j++){
							totalNum+=goodsRetailList[j].goodsNum;
							$('#jcContent').find('.guilist').eq(i).append("<li class='guiIfo'><ul class='cle'><li class='foodName' id='"+goodsRetailList[j].id+"'>"+goodsRetailList[j].goodsName+"</li><li class='foodNum'><em class='num'>"+goodsRetailList[j].goodsNum+"</em><div class='operate'><em class='update'>修改</em><em class='delete'>删除</em></div></li></ul></li>");

						}
						$('.guilist').eq(i).append("<li class='total'><ul class='cle'><li class='total_name'>合计(份)</li><li class='total_num'><em>"+totalNum+"</em></li></ul></li>");
					}else {
						$('.guilist').eq(i).append("<li class='total'><ul class='cle'><li class='total_name'>合计(份)</li><li class='total_num'><em>"+0+"</em></li></ul></li>");
					}
					
				}
				
				guiacitve();
				$('#jcSide li').click(function(){
					var allTOP=0;
					$(this).addClass('select').siblings('li').removeClass('select');
				    var index = $(this).index();
					for(var i=0;i<index;i++){
						allTOP+=$('.guiName').eq(i).height();
					}
					$("#jcConWraper").stop().animate({ "top":-allTOP},500);	
				})
				setTimeout(function(){
					 $("#jcFrame").jcFrame({
				        property : "rel",
				        speed : "fast"
				    });
				},2000);
				
				//删除按钮点击的时候
				$('.foodNum').find('.delete').click(function(){
					$('.del').parent().show();
					var delFood=$(this).parents('.guiIfo').find('.foodName').text();
					$('.del').find('.del_input em').text(delFood);
					$('.del').find('.del_input').attr('title',delFood);
					var $delId=$(this).parents('.guiIfo').find('.foodName').attr("id");
					var $delgui=$(this).parents('.guilist').index();
					var $delindex=$(this).parents('.guiIfo').index();
					localStorage.setItem('$delgui',$delgui);
					localStorage.setItem('$delindex',$delindex);
					localStorage.setItem('$delId',$delId);
				});
				//删除确定按钮点击的时候
				$('.del').find('.del_sure').click(function(){
					$('.del').parent().hide();
					var $delgui=parseInt(localStorage.getItem('$delgui'));
					var $delindex=parseInt(localStorage.getItem('$delindex'));
					var $delId=parseInt(localStorage.getItem('$delId'));
					delGoodsRetail($delId);
					$('.guilist').eq($delgui).find('#'+$delId).parents('.guiIfo').remove();
					guiacitve();
					
				});
				
				getMachine(machineNameArray);
			}
			if (data.otherInfo != null && data.otherInfo.length > 0){
				$.messager.show({
					title : '提示',
					msg : data.otherInfo,
					height:'100%',
					timeout : 5000
				});
			}
		}
	});
}
//修改按钮点击的时候
$('.foodNum').find('.update').live("click",function(){
	var $this=$(this);
	$('.revised').parent().show();
	var $guiindex=$(this).parents('.guilist').index();
	var $index=$(this).parents('.guiIfo').index();
	var updateFood=$(this).parents('.guiIfo').find('.foodName').text();
	var $updateId=$(this).parents('.guiIfo').find('.foodName').attr("id");
	var updatenum=$(this).parents('.guiIfo').find('.num').text();
	$('.revised').find('.inpname').text(updateFood).attr("title",updateFood);
	$('.revised').find('input').val(updatenum);
	localStorage.setItem('$guiindex',$guiindex);
	localStorage.setItem('$index',$index);
	localStorage.setItem('$updateId',$updateId);
	getMostSaleNum($updateId,$this);
});
//修改确定按钮点击的时候
$('.revised').find('.revised_sure').live("click",function(){
	var $index=parseInt(localStorage.getItem('$index'));
	var $guiindex=parseInt(localStorage.getItem('$guiindex'));
	var $updateId=parseInt(localStorage.getItem('$updateId'));
	var sum=Math.ceil($(this).parents('.dialog').find('input').val());
	var name=$(this).find('.inpname').text();
	var oldTotalNum=$('.guilist').eq($guiindex).find('.total_num').find('em').text();
	if(sum>0){
		if(sum==$('.guilist').eq($guiindex).find('.guiIfo').eq($index-1).find('.num').text()){
			$.messager.show({
				title : '提示',
				msg : '您输入的份数和并未发生改变',
				timeout : 5000,
			});
		}
		else{
			$.ajax({
				type:"post",
				url:urls["ctx"]+"/goodsRetail/updateGoodsNum",
				data:{id:$updateId,newGoodsNum:sum},
				async:true,
				success:function(data){		
					if(data==1){
						$.messager.show({
							title : '提示',
							msg : '修改成功',
							timeout : 5000,
						});
						$('.guilist').eq($guiindex).find('.guiIfo').eq($index-1).find('.num').text(sum);
						$(".revised_sure").parents('.dialog').hide();
						//重新统计总计
						total=0;
						for(var i=0;i<$('.guilist').eq($guiindex).find('.guiIfo').length;i++){
							total+=parseInt($('.guilist').eq($guiindex).find('.guiIfo').eq(i).find('.num').text());
						}
						$('.guilist').eq($guiindex).find('.total_num').find('em').text(total);
					}else if(data==2){
						$.messager.show({
							title : '提示',
							msg : '当前数量超过设置的最高份数',
							timeout : 5000,
						});
						
					}else if(data==3){
						$.messager.show({
							title : '提示',
							msg : '剩余可分配数量不足',
							timeout : 5000,
						});
						
					}else if(data==4){
						flag=false;
						$.messager.show({
							title : '提示',
							msg : '此商品已补货，当前数量只能大于之前数量，不可减少！',
							timeout : 5000,
						});
						
					}
				}
			});
			
				
		}
	}else{
		$.messager.show({
			title : '提示',
			msg : '请输入大于0的整数',
			timeout : 5000,
		});
	}
});
//获取最高份数
function getMostSaleNum(id,$this){
	$.ajax({
		type:"post",
		url:urls["ctx"]+"/goodsRetail/getMostSaleNum",
		data:{id:id},
		async:true,
		success:function(data){
			var totalNum=$this.parents('.guilist').find('.total_num em').text();
			if(totalNum>data){
				$('.revised_txt').find('.bigsum  em').text(totalNum);
			}
			else{
				$('.revised_txt').find('.bigsum  em').text(data);
			}
		}
	});
}

//修改零售数量
function updateGoodsNum(id,sum){
	//console.log(id+"========"+sum);
	var flag;
	$.ajax({
		type:"post",
		url:urls["ctx"]+"/goodsRetail/updateGoodsNum",
		data:{id:id,newGoodsNum:sum},
		async:true,
		success:function(data){		
			if(data==1){
				flag=true;
				$.messager.show({
					title : '提示',
					msg : '修改成功',
					timeout : 5000,
				});
			}else if(data==2){
				flag=false;
				$.messager.show({
					title : '提示',
					msg : '当前数量超过设置的最高份数',
					timeout : 5000,
				});
				
			}else if(data==3){
				flag=false;
				$.messager.show({
					title : '提示',
					msg : '剩余可分配数量不足',
					timeout : 5000,
				});
				
			}else if(data==4){
				flag=false;
				$.messager.show({
					title : '提示',
					msg : '此商品已补货，当前数量只能大于之前数量，不可减少！',
					timeout : 5000,
				});
				
			}
		}
	});
	localStorage.setItem("flag",flag);
}
	
	//所有对话框取消按钮
	$(document).on('click', '.btnConcle', function () {
		$(this).parents('.dialog').hide();
	});
	$(document).on('click', '.concle', function () {
		$(this).parents('.dialog').hide();
	});
	//导出表单
	$('.formBtn').click(function(){
		alert('导出表单');
	});
	//导出表单
	$('.lookList').click(function(){
		var selectTime=$(".time").find("span").text();
		var areaModelId=localStorage.getItem("areaModelId");
		if(selectTime!=null&&selectTime!=""){
			window.location.href=urls['ctx']+"/goods/temporary/toGoodsTemporary?selectTime="+selectTime+"&areaModelId="+areaModelId;
		}
	});
	
	
})



function prevnext(){
	//列表自适应
	var ptListWrp = $('.jQ_ptLst'); //列表容器
	var valLstLiWth = $('.jQ_ptLst li').width(); //图片宽度（图片可能包括边框样式等，取Li宽度参与可视图片计算避免误差）
	var valImgLth = 1; //可见图数变量*
	var valpLstMg = 0; //边距变量*
	var pLstLesMg = 5; //临界间距
	//列表滚动
	var pLstRoLt = $('.jQ_plstRoLt'); //左滚动元素
	var pLstRoRt = $('.jQ_plstRoRt'); //右滚动元素
	var pLstImgLth = ptListWrp.find('li').length; //获取图片总数
	//宽度自适应方法
	function fnAutoWth(){
		//重置滚动
		$('.jQ_ptLst ul').css({'marginLeft':'0'});
		
		pLstRoLt.find('img').attr('src',urls["ctx"]+'/views/goodsRetail/image/prevNo.png');
		pLstRoLt.attr("class","roll_lt no");
		pLstRoRt.show();
		
		var pLstWrpWth = ptListWrp.width(); //获取当前可视区域宽度
		valImgLth = Math.floor(pLstWrpWth / valLstLiWth); ///当前可视图片数计算*
		//图片间距算法
		function fnpLstMg(){
			//间距 = 除去图片的宽度 /（图片数 + 1），并向上取整（避免小数误差）
			valpLstMg = Math.ceil((pLstWrpWth - valImgLth * valLstLiWth) / (valImgLth + 1));
		};
		//执行计算
		fnpLstMg();
		//间距临界值
		if(valpLstMg < pLstLesMg){
			valImgLth = valImgLth - 1; //当间距达到临界值，图片数-1
			fnpLstMg(); //重新计算间距
		};
		//可视图片数 >= 总图片数时，总图片数即可视图片数
		if(valImgLth >= (pLstImgLth+2)){
			valImgLth = pLstImgLth;
			fnpLstMg(); //重新计算间距
			//pLstRoRt.hide();
			pLstRoRt.find('img').attr('src',urls["ctx"]+'/views/goodsRetail/image/nextNo.png');
			pLstRoRt.attr("class","roll_rt no");
		};
		//使可视图不为0
		// if(valImgLth == 0){
		// valpLstMg = 5;
		// };
		//设置间距
		ptListWrp.find('li').css('margin-left',12);
	};
	//初始执行宽度自适应方法
	fnAutoWth();
	//动态变化宽度时执行方法
	$(window).resize(function(){
		fnAutoWth(); //宽度自适应方法
	});
	//滚动方法
	function fnPlstArr($this){
		var pLstRoWrp = ptListWrp.find('ul');
		var ptLstCssMg = parseInt(pLstRoWrp.css('margin-left')); //获取当前已滚动宽度
		var ptLstImgMg = parseInt(pLstRoWrp.find('li:first').css('margin-left')); //获取当前图片间距
		//向右滚动
		if($this.hasClass('jQ_plstRoRt')){
			//当点击右箭头时，列表向左滚动。需滚动的宽度 = 当前图片间距 + 图片宽度（取Li宽度）
			pLstRoWrp.not(':animated').animate({marginLeft: ptLstCssMg - (ptLstImgMg + valLstLiWth)},200,function(){
			//回调函数判断滚动之后的箭头状态
			var ptLstCurMg = parseInt(pLstRoWrp.css('margin-left')); //获取当前已滚动宽度
			var ptLstRoWth = (pLstImgLth - valImgLth) * (ptLstImgMg + valLstLiWth)+264;//计算非可视区域宽度
		
			//当已滚动宽度 = 非可视区宽度，即已滚动至最后一图
			if(ptLstCurMg + ptLstRoWth == 0){
				/*$this.hide(); //隐藏右箭头*/
				
				$this.find('img').attr('src', urls["ctx"]+'/views/goodsRetail/image/nextNo.png');
				$this.attr("class","roll_rt no");
			};
				pLstRoLt.show(); //一旦向右滚动，左箭头即显示
				pLstRoLt.find('img').attr('src', urls["ctx"]+'/views/goodsRetail/image/prev.png');
				pLstRoLt.attr("class","roll_lt jQ_plstRoLt");
				$('.jQ_plstRoLt').click(function(){
					fnPlstArr($(this));
				});
			});
		};
		//向左滚动
		if($this.hasClass('jQ_plstRoLt')){
			pLstRoWrp.not(':animated').animate({marginLeft: ptLstCssMg + (ptLstImgMg + valLstLiWth)},200,function(){
			//回调函数判断滚动之后的箭头状态
			var ptLstCurMg = parseInt(pLstRoWrp.css('margin-left')); //获取当前已滚动宽度
			var ptLstRoWth = (pLstImgLth - valImgLth - 1) * (ptLstImgMg + valLstLiWth);//计算非可视区域宽度
				//当已滚动宽度 = 0，即已滚动至最前一图
				if(ptLstCurMg == 0){
					/*$this.hide(); //隐藏左箭头*/
					$this.find('img').attr('src', urls["ctx"]+'/views/goodsRetail/image/prevNo.png');
					$this.attr("class","roll_lt no");
				};
					pLstRoRt.show(); //一旦向左滚动，右箭头即显示
					pLstRoRt.find('img').attr('src', urls["ctx"]+'/views/goodsRetail/image/next.png');
					pLstRoRt.attr("class","roll_rt jQ_plstRoRt");
				});
			};
		};
	//滚动事件绑定
	$('.jQ_plstRoLt, .jQ_plstRoRt').click(function(){
		fnPlstArr($(this));
	});
};
