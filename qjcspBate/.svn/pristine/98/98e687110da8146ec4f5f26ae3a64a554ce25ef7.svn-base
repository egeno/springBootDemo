<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品零售发布</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<%@ include  file="/layout/script.jsp"%>
		<link rel="stylesheet" type="text/css" href="<%=path%>/views/goodsRetail/css/form.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/views/goodsRetail/css/reset.css">
		<script src="<%=path%>/views/goodsRetail/js/jQuery-jcFrame.js" language="javascript" type="text/javascript"></script>
		
		<script type="text/javascript" src="<%=path%>/views/goodsRetail/js/form.js"></script>
		<script language="javascript" type="text/javascript">
		$(function(){
			// 调用
			setTimeout(function(){
				$("#jcFrame").jcFrame({
			        property : "rel",
			        speed : "fast"
			    });
			},2000);
		});
			var urls= {
				'ctx':'<%=path%>'
			};
			
			function jump(machineId, machineName, time, areaModelId){
				$.ajax({
					type:"post",
					url:urls["ctx"]+"/goodsRetail/whetherToJump",
					data:{machineId:machineId,machineName:machineName, areaModelId:areaModelId,replenishmentTime:time},
					dataType:'JSON',
					async:true,
					success:function(data){
						if (data.code == '0'){
							$.messager.show({
								title : '提示',
								msg : data.content,
								timeout : 5000,
							});
						} else {
							window.location.href=urls['ctx']+"/newTemplate/jump?machineId="+machineId+"&machineName="+encodeURI(encodeURI(machineName)+"&time="+time+"&areaModelId="+areaModelId);
						}
					}
				})
			}
		</script>
	</head>
	<body>
		<div class='modeMain'>
			<!--删除对话框 start-->
			<div class="dialog">
				<div class="del dialogmain">
					<div class="del_top top">
						<div class="del_title tit">温馨提示</div>
						<div class="del_input inp">是否要删除"<em></em>"</div>
					</div>
					<div class="del_bottom bottom">
						<div class="del_concle btnConcle">取消</div>		
						<div class="del_sure  btnSure">确定</div>
					</div>
				</div>
			</div>
			<!--删除对话框 stop-->
			<!--修改对话框 start-->
			<div class="dialog">
				<div class="revised dialogmain">
					<div class="revised_top top">
						<div class="revised_title tit">设置零售套餐数量</div>
						<!--<p><div class='lf'>最高可添加份数</div><div class='bigsum lr'><em>30</em>份</div></p>-->
						<!--<p class='revised_txt'><div class='lf'>最高可添加份数</div><div class='bigsum lr'><em>30</em>份</div></p>-->
						<div class='revised_txt cle'><div class='txt lf'>最高可添加份数</div><div class='bigsum lr'><em>30</em>份</div></div>
						<div class="revised_input inp cle"><div class='inpname'>秘制叉烧肉套餐</div><input type="text" class='lr'/></div>
					</div>
					<div class="revised_bottom bottom">
						<div class="revised_concle btnConcle">取消</div>		
						<div class="revised_sure  btnSure">确定</div>
					</div>
				</div>
			</div>
			<!--修改对话框 stop-->
			
			
			<!--上面 start-->
			<div class='top cle'>
 				<div class='time lf'><span></span></div>
				<div class='mode lf'>
					<div class='mdoeList'>
						<em></em><span><img src="<%=path%>/views/goodsRetail/image/upp.png" alt="向上箭头" /></span>
					</div>
					<div class='mdoeList_choice'>
					 <em></em>   
      				 <span></span>
						<%-- <img src="<%=path%>/views/goodsRetail/image/modelist.png"> --%>
					</div>
				</div>
				<div class="cupboard lf">
				  <div class="bd">
				    <div class="wp_roll jQ_roll"> <a  class="roll_lt jQ_plstRoLt"><img src="<%=path%>/views/goodsRetail/image/prev.png" alt="" /></a> <a  class="roll_rt jQ_plstRoRt"><img src="<%=path%>/views/goodsRetail/image/next.png" alt="" /></a>
				      <div class="roll_cnt">
				        <div class="plst_pt jQ_ptLst">
				          <ul id="jcSide">
				            <!-- <li class="select"><a rel="index" href="#D">首页</a></li>
					    	<li><a rel="about">JS代码大全</a></li>
					    	<li><a rel="photo">开源框架</a></li>
					    	<li><a rel="blog">jQuery特效</a></li>  -->
					    	
				          </ul>
				        </div>
				      </div>
				    </div>
				  </div>
				</div>
				<div class='btn lr'><div class='formBtn'>导出表单</div></div>
			</div>
			<!--上面 stop-->
			<!--柜子信息 start-->
			<div class='content'>
				<div class='content_title'>
					<ul class='cle'>
						<li style='width:10%'>机柜</li>
						<li style='width:45%'>餐品名称</li>
						<li style='width:45%'>零售数量(份)</li>
					</ul>
				</div>
				<div class='content_main'>			
					<div class='content_main_box' id="jcFrame">
 						<dl id='jcContent'>
 							
 						</dl>
					</div>
				</div> 
			</div>
			<!--柜子信息 stop-->
			<!--下面 start-->
			<div class='botm'>
				<div class='lookList'><a href='javascript:void(0);'>查看采购清单</a></div>
			</div>
			<!--下面 stop-->
		</div>
	</body>
</html>
<script type="text/javascript">
$(function(){
	$('#jcSide li').click(function(){
		$(this).addClass('select').siblings('li').removeClass('select');
	    var index = $(this).index();
		$("#jcConWraper").stop().animate({ "top":-301*index},500);	
	})
	
	
	var mainTop=0;
	var allTop;
	$('.content_main').on("mousewheel DOMMouseScroll", function (e) {
	    var guilen=$('.guilist').length;
	    var delta = (e.originalEvent.wheelDelta && (e.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
	                (e.originalEvent.detail && (e.originalEvent.detail > 0 ? -1 : 1));              // firefox
	    var mainH=$('.content_main').height();
	    if (delta > 0) {
	    	// 向上滚
	    	if(Math.floor(mainTop)<=0){
	        	mainTop=0;
	        	$('#jcSide').find('li').eq(0).addClass('select').siblings('li').removeClass('select');
				$("#jcConWraper").stop().animate({ "top":0},500);	
	    	}else{
	    		mainTop=mainTop-1;
	    		/*var allTop=0;
	    		$('#jcSide').find('li').eq(mainTop).addClass('select').siblings('li').removeClass('select');
	    		for(var i=0;i<mainTop;i++){
	    			allTop+=mainH;
	    		}*/

				$("#jcConWraper").stop().animate({ "top":-(mainTop*mainH)-2},500);	
	    	}
	        
	    } else if (delta < 0) {
	    	// 向下滚
	    	mainTop=mainTop+1;
	    	if(mainTop>=0 && mainTop<$('#jcConWraper').height()/mainH){
	    		allTop=0;
	    		$('#jcSide').find('li').eq(mainTop).addClass('select').siblings('li').removeClass('select');
	    			for(var i=0;i<mainTop;i++){
		    			allTop+=mainH;
		    	}
	    		
	    	}else{
	    		mainTop=$('#jcConWraper').height()/mainH;
	    	}
	    	if(allTop<=mainH*($('#jcConWraper').height()/mainH)){
    			$("#jcConWraper").stop().animate({ "top":-allTop-2},500);
    		}
	    	
	    	/*if(guilen==1){
    			var guiheight=$('.guilist').height();
    			if(guiheight>$('.content_main').height()){
    				allTop=guiheight-$('.content_main').height();
    			}
    			$("#jcConWraper").stop().animate({ "top":-allTop-2},500);	
    		} */
	        
	    }
	});
})
</script>