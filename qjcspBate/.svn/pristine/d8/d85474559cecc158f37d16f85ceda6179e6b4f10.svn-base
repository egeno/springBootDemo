<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include  file="/views/include/ctxPathInclude.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>全家综合管理平台</title>
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/css/login.css">
		<link rel="shortcut icon" href="<%=path%>/static/images/favicon.ico" />
		<script type="text/javascript" src="<%=path%>/static/js/jquery-1.7.2.min.js"></script> 
		<script type="text/javascript" src="<%=path%>/static/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/jquery.roundabout-1.0.min.js"></script> 
		<script type="text/javascript" src="<%=path%>/static/js/jquery.easing.1.3.js"></script> 
		<script type="text/javascript" src="<%=path%>/static/js/iphone.check.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/jquery-jrumble.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/jquery.tipsy.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/loginTest.js"></script> 
		<script type="text/javascript">		
		var path="<%=request.getContextPath()%>";
		$(document).ready(function(){ 
			$('#featured-area ul').roundabout({
				easing: 'easeOutInCirc',
				duration: 600
			});
			 if (screen.height < 1200){
				 $('.logo').css('margin-top','40.0313px');
	         }

		});
				</script> 
	</head>
	  
	<body class="background" onselectstart="return false ">
		
		<div class="main">
			<div class="logo"><img src="<%=path%>/static/images/logo.png"></div>
		<!--轮播-->
			<div id="featured-area"> 
				<ul> 
					<li class="big_box now">
						<a  id="boxHref0">
							<div class="cookietest" value="business" style="display: none;">运维</div>
							<img src="<%=path%>/static/images/label_operation.png" class="label">
							<img src="<%=path%>/static/images/label_operation.png" class="label2">
								<div class="box ">
									<div class="shu operation">
										<table>
											<tbody>
												<tr>
												<td class="name"><img src="<%=path%>/static/images/textjoin.png"></td>
												<td class="iput">
													<div class="nav nav1 lf" id="nav1">
														<p class="set1 set">区域1</p>
<!-- 														<p class="set1 set">区域2</p> -->
														<div class="new1">
														<p class="option1 option" value="1">区域</p>
<%-- 															<c:choose> --%>
<%-- 		                										<c:when test="${data ne null }"> --%>
<%-- 		                												<c:forEach items="${data}" var="item"> --%>
<%-- 		  							                                    <p class="option1 option" value="${item.companyId }">${item.companyName }</p> --%>
<%-- 	  	  			    											</c:forEach> --%>
<%-- 																</c:when> --%>
<%-- 															<c:otherwise> --%>
<!-- 																<option value="">全部</option> -->
<%-- 															</c:otherwise> --%>
<%-- 														</c:choose> --%>
														</div>
													</div>
													<span class="icon lf"><img src="<%=path%>/static/images/icon.png"></span>
													<div class="nav nav2 lf" id="nav2">
														<p class="set2 set"></p>
														<div class="new2">
															<c:choose>
		                										<c:when test="${data ne null }">
		                												<c:forEach items="${data}" var="item">
		  							                                    <p class="option2 option" title="${item.companyName }" value="${item.companyId }">
		  							                                    <c:if test="${fn:length(item.companyName)>10}">${fn:substring(item.companyName,0,10)}...</c:if>
		  							                                    <c:if test="${fn:length(item.companyName)<=10}">${item.companyName }</c:if>
		  							                                    </p>
	  	  			    											</c:forEach>
																</c:when>
															<c:otherwise>
																<option value="">全部</option>
															</c:otherwise>
														</c:choose>
															
														</div>
													</div>
												</td>
												</tr>
												<tr>
													<td class="name"><img src="<%=path%>/static/images/textuser.png"></td>
													<td class="iput aa"><input type="text" placeholder="请输入您的账户" style="width:265px;height:33px;border:1px solid #bfbfbf;border-radius: 5px;padding-left:15px;color:#bfbfbf" name="username" id="username" iscookie="true"></td>
												</tr>
												<tr>
													<td class="name"><img src="<%=path%>/static/images/textpass.png"></td>
													<td class="iput"><input type="password" placeholder="请输入您的密码" style="width:265px;height:33px;border:1px solid #bfbfbf;border-radius: 5px;padding-left:15px;color:#bfbfbf" name="password" id="password"></td>
												</tr>
												<tr>
													<td class="name"><img src="<%=path%>/static/images/textyan.png"></td>
													<td class="iput" >
														<input type="text" placeholder="请输入验证码" style="width:103px;height:33px;border:1px solid #bfbfbf;border-radius: 5px;padding-left:15px;color:#bfbfbf" name="captcha" id="captcha">
														<span class="validate"><img src="Kaptcha.jpg" id="KaptchaBusiness" class="ss"></span><a href="#" id="KaptchaBusinessa">换一张</a>
													</td>
												</tr>
											</tbody>
										</table>
										<div class="rember">
											<span class="check username"><span><img src="<%=path%>/static/images/nocheck.png"></span><label for="remberuser" style="font-size:14px;color:#000">记住用户名    </label></span>
											<span class="check password"><span><img src="<%=path%>/static/images/nocheck.png"></span><label for="remberpass" style="font-size:14px;color:#000">记住密码</label></span></p>
										</div>
										<a class="nopass" style="font-size:14px;">忘记密码?</a>
									</div>
								</div>
						</a>
					</li>
					<li class="big_box">
					<a id="boxHref1">
					    <div class="cookietest" value="qj" style="display: none;">全家</div>
						<img src="<%=path%>/static/images/label_family.png" class="label">
							<img src="<%=path%>/static/images/label_family.png" class="label2">
						<div class="box">
								<div class="shu family">
									<table>
										<tbody>
											<tr>
												<td class="name"><img src="<%=path%>/static/images/textuser.png"></td>
												<td class="iput"><input type="text" placeholder="请输入您的账户" style="width:265px;height:33px;border:1px solid #bfbfbf;border-radius: 5px;padding-left:15px;color:#bfbfbf" name="username" id="username"></td>
											</tr>
											<tr>
												<td class="name"><img src="<%=path%>/static/images/textpass.png"></td>
												<td class="iput"><input type="password" placeholder="请输入您的密码" style="width:265px;height:33px;border:1px solid #bfbfbf;border-radius: 5px;padding-left:15px;color:#bfbfbf" name="password" id="password"></td>
											</tr>
											<tr>
												<td class="name"><img src="<%=path%>/static/images/textyan.png"></td>
												<td class="iput" >
													<input type="text" placeholder="请输入验证码" style="width:103px;height:33px;border:1px solid #bfbfbf;border-radius: 5px;padding-left:15px;color:#bfbfbf" name="captcha" id="captcha">
													<span class="validate"><img src="Kaptcha.jpg" id="Kaptcha" class="ss"></span><a href="#" id="Kaptchaa">换一张</a>
												</td>
											</tr>
										</tbody>
									</table>
									<div class="rember">
										<span class="check username"><span><img src="<%=path%>/static/images/nocheck.png"></span><label for="remberuser" style="font-size:14px;color:#000">记住用户名    </label></span>
										<span class="check password"><span><img src="<%=path%>/static/images/nocheck.png"></span><label for="remberpass" style="font-size:14px;color:#000">记住密码</label></span></p>
									</div>
									<a class="nopass" style="font-size:14px;">忘记密码?</a>
								</div>
				        	</div>
					</a>
					</li>  
					<li class="big_box">
					<a  id="boxHref2">
					    <div class="cookietest" value="company" style="display: none;">商户</div>
						<img src="<%=path%>/static/images/label_join.png" class="label">
						<img src="<%=path%>/static/images/label_join.png" class="label2 look">
						<div class="box">
								<div class="shu">
									<table>
										<tbody>
											<tr>
											<td class="name"><img src="<%=path%>/static/images/textjoin.png"></td>
											<td class="iput">
												<div class="nav nav3 lf" id="nav3">
													<p class="set3 set">是的</p>
													<div class="new3">
													<p class="option3 option" value="2">区域</p>
<%-- 														<c:choose> --%>
<%-- 		                										<c:when test="${data ne null }"> --%>
<%-- 		                												<c:forEach items="${data}" var="item"> --%>
<%-- 		  							                                    <p class="option3 option" value="${item.companyId }">${item.companyName }</p> --%>
<%-- 	  	  			    											</c:forEach> --%>
<%-- 																</c:when> --%>
<%-- 															<c:otherwise> --%>
<!-- 																<option value="">全部</option> -->
<%-- 															</c:otherwise> --%>
<%-- 														</c:choose> --%>
													</div>
												</div>
												<span class="icon lf"><img src="<%=path%>/static/images/icon.png"></span>
												<div class="nav nav4 lf" id="nav4">
													<p class="set4 set">答复多福多寿</p>
													<div class="new4">
														<c:choose>
		                										<c:when test="${data ne null }">
		                												<c:forEach items="${data}" var="item">
		  							                                    <p class="option4 option" title="${item.companyName }" value="${item.companyId }">
		  							                                    <c:if test="${fn:length(item.companyName)>10}">${fn:substring(item.companyName,0,10)}...</c:if>
		  							                                    <c:if test="${fn:length(item.companyName)<=10}">${item.companyName }</c:if>
		  							                                    </p>
	  	  			    											</c:forEach>
																</c:when>
															<c:otherwise>
																<option value="">全部</option>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</tr>
											<tr>
												<td class="name"><img src="<%=path%>/static/images/textuser.png"></td>
												<td class="iput"><input type="text" placeholder="请输入您的账户" style="width:265px;height:33px;border:1px solid #bfbfbf;border-radius: 5px;padding-left:15px;color:#bfbfbf" name="username" id="username"></td>
											</tr>
											<tr>
												<td class="name"><img src="<%=path%>/static/images/textpass.png"></td>
												<td class="iput"><input type="password" placeholder="请输入您的密码" style="width:265px;height:33px;border:1px solid #bfbfbf;border-radius: 5px;padding-left:15px;color:#bfbfbf" name="password" id="password"></td>
											</tr>
											<tr>
												<td class="name"><img src="<%=path%>/static/images/textyan.png"></td>
												<td class="iput" >
													<input type="text" placeholder="请输入验证码" style="width:103px;height:33px;border:1px solid #bfbfbf;border-radius: 5px;padding-left:15px;color:#bfbfbf" name="captcha" id="captcha">
													<span class="validate"><img src="Kaptcha.jpg" id="KaptchaCompany" class="ss"></span><a href="#" id="KaptchaCompanya">换一张</a>
												</td>
											</tr>
										</tbody>
									</table>
									<div class="rember">
										<span class="check username"><span><img src="<%=path%>/static/images/nocheck.png"></span><label for="remberuser" style="font-size:14px;color:#000">记住用户名    </label></span>
										<span class="check password"><span><img src="<%=path%>/static/images/nocheck.png"></span><label for="remberpass" style="font-size:14px;color:#000">记住密码</label></span></p>
									</div>
									<a class="nopass" style="font-size:14px;">忘记密码?</a>
								</div>
				        	</div>
					</a>
					</li>  
				</ul> 
			</div>
			<div class="tab">
				<div class="btn one"><img src="<%=path%>/static/images/fous.png"></div>
				<div class="btn two"><img src="<%=path%>/static/images/no.png"></div>
				<div class="btn three"><img src="<%=path%>/static/images/no.png"></div>
			</div>
		<!--登录-->
			<div class="login_btn">
				<img src="<%=path%>/static/images/loginbtn.png">
			</div>
		</div>
		<div class="bg">
			<div class="alertMessage">
				<p id="message">账户名密码或验证错误！请重新输入</p>
				<p class="sure"><img src="<%=path%>/static/images/sure.png"></p>
			</div>
		</div>
		<script>
			$(function(){
				$('.big_box').eq(2).find('.shu').hide();//.removeClass('now');;
				$('.big_box').eq(0).find('.shu').show();//.addClass('now');
				$('.big_box').eq(1).find('.shu').hide();//.removeClass('now');;
				//$('.now .validate').click(function(){alert("sdadsadsa");});
				$('.nav').click(function(){
					$(this).siblings().find('div').slideUp();
				});
			});
			$('input').click(function(){
				$(".nav").siblings().find('div').slideUp();
			});
			//三个大登录框切换
			$('.btn').each(function(m)
			{
				$(this).click(function(){
					
					$('#boxHref'+m).click();
					$(".nav").siblings().find('div').slideUp();
					$(this).find('img').attr('src','<%=path%>/static/images/fous.png');
					$(this).siblings().find('img').attr('src','<%=path%>/static/images/no.png');
				});
			});
			$('.big_box').eq(2).click(function(){
				$(this).addClass('now');
				$(this).siblings().removeClass('now');
				//$('.now .validate').click(function(){alert("2");});
				$('.big_box').eq(2).find('.shu').show();
				$('.big_box').eq(2).siblings().find('.shu').hide();
				$('.big_box').eq(2).find('.label2').removeClass('look');
				$('.big_box').eq(2).siblings().find('.label2').addClass('look');
			});
			$('.big_box').eq(1).click(function(){
				$(this).addClass('now');
				$(this).siblings().removeClass('now');
				//$('.now .validate').click(function(){alert("1");});
				$('.big_box').eq(1).find('.shu').show();
				$('.big_box').eq(1).siblings().find('.shu').hide();
				$('.big_box').eq(1).find('.label2').removeClass('look');
				$('.big_box').eq(1).siblings().find('.label2').addClass('look');
			});
			$('.big_box').eq(0).click(function(){
				$(this).addClass('now');
				$(this).siblings().removeClass('now');
				//$('.now .validate').click(function(){alert("0");});
				$('.big_box').eq(0).find('.shu').show();
				$('.big_box').eq(0).siblings().find('.shu').hide();
				$('.big_box').eq(0).find('.label2').removeClass('look');
				$('.big_box').eq(0).siblings().find('.label2').addClass('look');
			});
			$('.big_box').each(function(i){
				$(this).click(function(){
//					$(".nav").siblings().find('div').slideUp();
					$('.btn').eq(i).find('img').attr('src','<%=path%>/static/images/fous.png').end().siblings().find('img').attr('src','<%=path%>/static/images/no.png');
				});
				
			});
			//复选框图片切换
			var a=0;
			$('.check').click(function(){
				if(a==0){
					$(this).find('img').attr('src','<%=path%>/static/images/checked.png');
					a++;
				}
				else{
					$(this).find('img').attr('src','<%=path%>/static/images/nocheck.png');
					a--;
				}
			});
		</script>
		
		<!--selection切换-->
		<script type="text/javascript">
	//		$(function(){
	//			$('.nav').click(function(){
	//				 var i=$(".nav").index(this)
	//				 $('.nav'+i).find('.set'+i).click(function(){
	//					var ul=$(".new"+i);
	//					if(ul.css("display")=="none"){
	//						ul.slideDown();
	//					}else{
	//						ul.slideUp();
	//					}
	//				});
	//				$('.set'+i).click(function(){
	//					var _name = $(this).attr("name");
	//					if( $("[name="+_name+"]").length > 1 ){
	//						$("[name="+_name+"]").removeClass("select"+i);
	//						$(this).addClass("select"+i);
	//					} else {
	//						if( $(this).hasClass("select"+i) ){
	//							$(this).removeClass("select"+i);
	//						} else {
	//							$(this).addClass("select"+i);
	//						}
	//					}
	//				});
	//				$(".nav"+i).find(".option"+i).click(function(){
	//					var li=$(this).text();
	//					$('.nav'+i).find('.set'+i).html(li);
	//					$(".new"+i).hide();
	//					$(".set"+i).removeClass("select"+i) ;   
	//				});
	//			});
	//			
	//		})
		</script>
		<script type="text/javascript">
			$(function(){
				$(".nav1 .set1").click(function(){	
					var ul=$(".new1");
					if(ul.css("display")=="none"){
						ul.slideDown();
					}else{
						ul.slideUp();
					}
				});
				$(".set1").click(function(){
					var _name = $(this).attr("name");
					if( $("[name="+_name+"]").length > 1 ){
						$("[name="+_name+"]").removeClass("select1");
						$(this).addClass("select1");
					} else {
						if( $(this).hasClass("select1") ){
							$(this).removeClass("select1");
						} else {
							$(this).addClass("select1");
						}
					}
				});
				$(".nav1 .set1").html($('.option1').eq(0).text()).attr("value",$('.option1').eq(0).attr("value"));;
				$(".nav1 .option1").click(function(){
					var li=$(this).text();
					var cva=$(this).attr("value");
					$(".nav1 .set1").html(li).attr("value",cva);
					$(".new1").hide();
					$(".set1").removeClass("select1") ;   
				});
			})
		</script>
		<script type="text/javascript">
			$(function(){
				$(".nav2 .set2").click(function(){
					var ul=$(".new2");
					if(ul.css("display")=="none"){
						ul.slideDown();
					}else{
						ul.slideUp();
					}
				});
				$(".set2").click(function(){
					var _name = $(this).attr("name");
					if( $("[name="+_name+"]").length > 1 ){
						$("[name="+_name+"]").removeClass("select2");
						$(this).addClass("select2");
					} else {
						if( $(this).hasClass("select2") ){
							$(this).removeClass("select2");
						} else {
							$(this).addClass("select2");
						}
					}
				});
				$(".nav2 .set2").html($('.option2').eq(0).text()).attr("value",$('.option2').eq(0).attr("value"));;
				$(".nav2 .option2").click(function(){
					var li=$(this).text();
					var cva=$(this).attr("value");
					$(".nav2 .set2").html(li).attr("value",cva);
					$(".new2").hide();
					$(".set2").removeClass("select2") ;   
				});
				
			})
		</script>
		<script type="text/javascript">
			$(function(){
				$(".nav3 .set3").click(function(){
					var ul=$(".new3");
					if(ul.css("display")=="none"){
						ul.slideDown();
					}else{
						ul.slideUp();
					}
				});
				$(".set3").click(function(){
					var _name = $(this).attr("name");
					if( $("[name="+_name+"]").length > 1 ){
						$("[name="+_name+"]").removeClass("select3");
						$(this).addClass("select3");
					} else {
						if( $(this).hasClass("select3") ){
							$(this).removeClass("select3");
						} else {
							$(this).addClass("select3");
						}
					}
				});
				$(".nav3 .set3").html($('.option3').eq(0).text()).attr("value",$('.option3').eq(0).attr("value"));;
				$(".nav3 .option3").click(function(){
					var li=$(this).text();
					var cva=$(this).attr("value");
					$(".nav3 .set3").html(li).attr("value",cva);
					$(".new3").hide();
					$(".set3").removeClass("select3") ;   
				});
			});
		</script>
		<script type="text/javascript">
		$(function(){
			$(".nav4 .set4").click(function(){
				var ul=$(".new4");
				if(ul.css("display")=="none"){
					ul.slideDown();
				}else{
					ul.slideUp();
				}
			});
			$(".set4").click(function(){
				var _name = $(this).attr("name");
				if( $("[name="+_name+"]").length > 1 ){
					$("[name="+_name+"]").removeClass("select4");
					$(this).addClass("select4");
				} else {
					if( $(this).hasClass("select4") ){
						$(this).removeClass("select4");
					} else {
						$(this).addClass("select4");
					}
				}
			});
			$(".nav4 .set4").html($('.option4').eq(0).text()).attr("value",$('.option4').eq(0).attr("value"));;
			$(".nav4 .option4").click(function(){
				var li=$(this).text();
				$(".nav4 .set4").html(li);
				var cva=$(this).attr("value");
				$(".nav4 .set4").html(li).attr("value",cva);
				$(".new4").hide();
				$(".set4").removeClass("select4") ;   
			});
		})
	</script>
	<script>
//  	var ss=0;
// 	$('.login_btn').click(function(){
// 		if(ss==0){
// 			$(".alertMessage").html()
// 			$('.bg').show();
// 			$('.sure').click(function(){
// 				$('.bg').hide();
// 			});
		//alert($('.now').find('.set').eq(1).attr("value"));
// 		alert($('.now .nav').find('p').eq(0).hasClass("set"));
//  		alert(ss)
// 		var usre=($('.now .username').find('img').attr("src"));
<%-- 		if(usre=="<%=path%>/static/images/checked.png"){ --%>
			
// 			alert(3);
// 		}else{
// 			alert(4);
// 		}
//  		}
//  	});
	 
//	自适应宽高
	var logotop=(125/1920)*$(window).width();
	var logowidth=(253/1920)*$(window).width();
	var logoheight=(125/1920)*$(window).width();
	$('.logo').css({'marginTop':logotop,'width':logowidth,'height':logoheight});
	
	var loginbtntop=(90/1920)*$(window).width();
	var loginbtnwidth=(233/1920)*$(window).width();
	var loginbtnheight=(30/1920)*$(window).width();
	$('.login_btn').css({'marginTop':loginbtntop,'width':loginbtnwidth,'height':loginbtnheight});
	
	var featuredtop=(70/1920)*$(window).width();
	$('#featured-area').css({'marginTop':featuredtop});

	</script>
</body>
</html>