<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
		String easyuiThemeName="bootstrap";
		Cookie cookies[] =request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals("cookiesColor")) {
					easyuiThemeName = cookie.getValue();
					break;
				}
			}
		}
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		request.setAttribute("ctx",basePath);
	%>
	<base href="<%=basePath %>"/>
	<style>
body {
	font-family: "黑体", "microsoft yahei";
	margin: 0 auto;
	color: #000;
	-moz-text-size-adjust: none;
	-webkit-text-size-adjust: none;
	text-size-adjust: none;
	-webkit-overflow-scrolling: touch;
	/*padding:10px;*/
	
}


img {
	border: 0 none;
}


ul,
li {
	list-style-type: none;
}

input,
textarea {
	outline: none;
	background: none;
	border: 0 none;
	border-radius: 0;
}

a {
	text-decoration: none;
}

em,i{
	font-style: normal;
}

a,input,img{
	outline:none;
}
.cle:after{visibility:hidden; display:block; font-size:0; content:'\20'; clear:both; height:0;}
.clear { clear: both; height: 0; font-size: 0; line-height: 0; overflow: hidden; }
.cle:after, .clearfix:after, .clear_f:after, .cle_float:after { visibility: hidden; display: block; font-size: 0; content: '\20'; clear: both; height: 0; }
.cle, .clearfix, .clear_f, .cle_float { *zoom: 1 }
.lf { float: left; }
.lr { float: right; }
.clearfix { clear: both; }


#bigbox{margin-top:10px;}
#foodList{width:860px;margin: 0 auto;position:relative;}
#foodList .main{border:1px solid #c9dae4;    padding: 15px 5px 5px 5px;}
.foodList_main .main{margin-bottom: 10px;}
#foodList input{
	height: 22px;
    width: 120px;
    line-height: 16px;
    border-radius: 3px 3px 3px 3px;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    transition: border 0.2s linear 0s, box-shadow 0.2s linear 0s;
    font-size: 13px;
    border: 1px solid #D4D4D4;
    vertical-align: middle;
}
.main{position:relative;}
.goodsName{margin-right:10px;}
.addFoodBox{font-size:16px;padding:0 5px ;background:#f7fafc;height:50px;line-height: 50px;border-radius: 4px;border:1px solid #c9dae4;margin-bottom: 15px;}
.addFoodBox span,.addFoodContent span{    
	background: url('<%=basePath%>views/ingredients/image/edit_add.png') no-repeat left;
    width: 100px;
    display: inline-block;
    padding-left: 20px;
    cursor: pointer;
}
.addFoodBox span{float:right;width:60px;font-size:14px;}
.foodLeft{ position: relative;width: 92%;}
.foodType{float:left;width:40%;}
.foodContent{float:left;}
.addFoodContent{line-height:20px;margin-left:10px}
.foodRight{
	width: 8%;
    text-align: center;
    position: absolute;
    top: 0;
    right: 0;
    height: 100%;
    background:#f7fafc;
    border-left:1px solid #c9dae4;
}
.foodRight .delBox{height:50px;;line-height:50px;width:100%;position: absolute; top:50%;margin-top:-25px;}
.foodContent_list li{
	margin-bottom: 10px;
}
.foodContent_list input{margin-right: 15px;}
.delBox{cursor: pointer;}
.delIngredient{cursor: pointer;background: url('<%=basePath%>views/ingredients/image/edit_remove.png') no-repeat left;width: 70px;
    display: inline-block;
    padding-left: 20px;
    cursor: pointer;}	
	</style>
	<script type="text/javascript" src="<%=basePath%>views/ingredients/js/jquery.json.js"></script>
	<script>
	$(function(){
			reday();			
		    //添加食材类型
		    $('.addFoodBox span').unbind("click").click(function(e){ 
		        $('.foodList_main').append("<li class='cle main'><div class='foodLeft'><div class='foodType'><label>食材类型:</label><input type='text' class='foodTypeName' validType='length[1,10]' maxLength='10'/><span class='addFoodContent'><span>添加成分</span></p></div><div class='foodContent'> <div class='foodContent_list'><ul><li class='line'><p><label>成分:</label><input type='text' class='ingredientName' maxlength='10'/><label>质量:</label><input type='text' class='grams' maxlength='10'/><span class='delIngredient'>删除</span></p> </li></ul></div></div></div><div class='foodRight'><div class='delBox'>删除</div></div></li>")
		    });
		    //删除整个食品类型
		    $('.delBox').die().live("click", function(){
		    	var $this=$(this);
		    	var ingredientsTypeId=$(this).parents('.main').attr('lang');
		    	//id为空说明是页面新增的，删除时不用调用后台的方法
		    	if(ingredientsTypeId == undefined){
		    		$this.parents('.main').remove();
		    	}else{
			        var url="goods/ingredientsType/delIngredientsType?ingredientsTypeId="+ingredientsTypeId;
			        //var jsonData={"ingredientsTypeId":ingredientsTypeId};
			        $.ajax({  
			            type:'post',      
			            url:url,  
			           // data:$.toJSON(jsonData),
			            dataType:'json', 
			            success:function(data){
			                $this.parents('.main').remove();
			            },error:function(){
			            	alert("删除失败");
			            }
			        });
		    	}
		    })
		    //删除主料成分及质量
		    $('.delIngredient').die().live("click",function(){
		    	var $this=$(this);
		    	var Id=$(this).parents(".line").attr('lang');
		    	//id为空说明是页面新增的，删除时不用调用后台的方法
		    	if(Id == undefined){
		    		$this.parents('.line').remove();
		    	}else{
			        var url="goods/ingredients/delIngredients?Id="+Id;
			        $.ajax({  
			            type:'post',      
			            url:url,  
			            dataType:'json', 
			            success:function(data){
			            	$this.parents('.line').remove();
			            },error:function(){		                
			           		alert("删除失败");
			            }
			        });
		    	}
		    })
		    
		   
		    //添加主料成分及质量
		    $(".addFoodContent span").die().live("click",function(){
		        $(this).parents('.main').find('.foodContent_list ul').append("<li class='line'><p><label>成分:</label><input type='text' class='ingredientName'  maxlength='10'/><label>质量:</label><input type='text'  class='grams'  maxlength='10'/><span class='delIngredient'>删除</span></p> </li>")
		    }) 
		})
		/*
		 *内容设置完毕后提交内容
		 */
		function save(){
			var falg;
			var falga=true;
			var goodsIngredientsType=new Array();  //总
			for(var i=0;i<$('.foodList_main').find('.main').length;i++){
				var $this=$('.foodList_main').find('.main').eq(i);
				var ingredientsTypeId=$this.attr('lang');
				if(ingredientsTypeId == undefined){
					ingredientsTypeId ='';
				}
				var ingredientsTypeName=$this.find('.foodTypeName').attr('value');
				//非空验证
				if($.trim(ingredientsTypeName) == ""){
					   alert("类型名称不能为空!");
					   falg=false;
					   localStorage.setItem("falg",falg);
					   return false;
				}
				var goodsId="${goodsId}";
				var goodsIngredients = new Array();  //每条
				for(var j=0;j<$this.find('.line').length;j++){
					var id=$this.find('.line').eq(j).attr('lang');
					if(id==undefined){
						id ='';
					}
					var ingredientsName=$this.find('.line').eq(j).find('.ingredientName').attr('value');
					//非空验证
					if($.trim(ingredientsName) == ""){
						   alert("成分名称不能为空!");
						   falg=false;
						   localStorage.setItem("falg",falg);
						   return false;
					}
					
					var ingredientsContent=$this.find('.line').eq(j).find('.grams').attr('value');
					//非空验证
					if($.trim(ingredientsContent) == ""){
						   alert("内容不能为空!");
						   falg=false;
						   localStorage.setItem("falg",falg);
						   return false;
					}
					goodsIngredients.push({"id":id,"ingredientsName":ingredientsName,"ingredientsContent":ingredientsContent});
				}
				goodsIngredientsType.push({"ingredientsTypeId":ingredientsTypeId,"ingredientsTypeName":ingredientsTypeName,"goodsId":goodsId,'goodsIngredients':goodsIngredients});
			}
			
			 for(var i=0;i<goodsIngredientsType.length;i++){
				for(var j=goodsIngredientsType.length-1;j>i;j--){
					if(goodsIngredientsType[i].ingredientsTypeName==goodsIngredientsType[j].ingredientsTypeName){
						falga=false;										
						parent.$.messager.show({
							title :"提示",
							msg : "食材类型名称"+goodsIngredientsType[i].ingredientsTypeName+"重复了",
							timeout : 1000 * 3
					 	});					
			 }											
		}
	} 
				
			if(falga){ 
			var url="goods/ingredientsType/addIngredientsType";
			$.ajax({
	            type:'post',    
	            url:url,
	            data:$.toJSON({"list":goodsIngredientsType}),
	            dataType:'json',
	            async: false,
	            beforeSend : function(){
	    			parent.$.messager.progress({
	    				title : '提示',
	    				text : '数据处理中，请稍后....'
	    			});
	    		},
	            success:function(data){
					result = data;
	 				if (result.status) {
						parent.$.messager.show({
							title :"提示",
							msg : result.message,
							timeout : 1000 * 3
						});
					}else{
						parent.$.messager.show({
							title :"提示",
							msg : result.message,
							timeout : 1000 * 3
						});
					} 
	 				falg=result.status;
	 				localStorage.setItem("falg",falg);
	            },error:function(){
	            	parent.$.messager.show({
	    				title :"提示",
	    				msg :"保存失败!",
	    				timeout : 1000 * 3
	    			});
	            	falg=false;
	 				localStorage.setItem("falg",falg);
	            }
	        }); 
			
		}
	 } 
	
/**
 * 页面加载时候查询出原有的数据
 */
function reday(){
	var goodsName="${goodsName}";
	var goodsId="${goodsId}";
	$(".goodsName").text(goodsName);
	$.post("goods/ingredients/getGoodsListInfo",{'goodsId':goodsId},function(data){
		for(var i=0;i<data.list.length;i++){  			
	    	$('.foodList_main').append("<li class='cle main' lang="+data.list[i].ingredientsTypeId+"><div class='foodLeft' lang="+data.list[i].goodsId+"><div class='foodType'><label>食材类型:</label><input type='text' class='foodTypeName' validType='length[1,10]' maxLength='10'/><span class='addFoodContent'><span>添加成分</span></span></div><div class='foodContent'> <div class='foodContent_list'><ul></ul></div></div></div><div class='foodRight'><div class='delBox'>删除</div></div></li>")
	    	$('.foodList_main').find('.main').eq(i).find('.foodTypeName').val( data.list[i].ingredientsTypeName);
	    	for(var j=0;j<data.list[i].ingredients.length;j++){
	    		$('.main').eq(i).find('.foodContent_list ul').append("<li class='line' lang="+data.list[i].ingredients[j].Id+"><p><label>成分:</label><input type='text' class='ingredientName'   validType='length[1,10]' maxLength='10'/><label>质量:</label><input type='text' class='grams' validType='length[1,10]' maxLength='10'/><span class='delIngredient'>删除</span></p></li>");
	    		$('.main').eq(i).find('.ingredientName').eq(j).val(data.list[i].ingredients[j].ingredientsName);
	    		$('.main').eq(i).find('.grams').eq(j).val(data.list[i].ingredients[j].ingredientsContent);
	    	}
	    }
	},'JSON');
}

	</script>
	</head>
	
		<div id="bigbox">
		<div id="foodList">
			<p class="addFoodBox">商品名：<em class="goodsName"></em><span>添加分类</span></p>
			<ul class="foodList_main">
				
			</ul>
		</div>
<!-- 		<button onclick="save()">提交</button> -->
		</div>
		
		