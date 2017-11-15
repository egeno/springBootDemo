
var username;
var password;
var captcha;
$(function(){
	getCookie();
	//回车登录
	$(document).keydown(function(e){
		 var ev = window.event|| e
		if(ev.keyCode == 13) {
			submit();
		}
	});
	$('.login_btn').click(function(e) {
		submit();
	});
	
	$('#KaptchaBusiness').click(     
			function() {
				var random=Math.floor(Math.random() * 100);
				$('#Kaptcha').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaBusiness').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaCompany').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();
		           });
	$('#KaptchaBusinessa').click(     
			function() {
				var random=Math.floor(Math.random() * 100);
				$('#Kaptcha').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaBusiness').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaCompany').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();
		           });
	
	$('#KaptchaCompany').click(     
			function() {
				var random=Math.floor(Math.random() * 100);
				$('#Kaptcha').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaBusiness').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaCompany').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();
		           });
	$('#KaptchaCompanya').click(     
			function() {
				var random=Math.floor(Math.random() * 100);
				$('#Kaptcha').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaBusiness').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaCompany').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();
		           });
	
	$('#Kaptcha').click(     
			function() {
				var random=Math.floor(Math.random() * 100);
				$('#Kaptcha').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaBusiness').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaCompany').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();
		           });
	$('#Kaptchaa').click(
			function() {
				var random=Math.floor(Math.random() * 100);
				$('#Kaptcha').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaBusiness').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();     
		           $('#KaptchaCompany').hide().attr('src','Kaptcha.jpg?' + random).fadeIn();
		           });
})

//表单提交
function submit(){
//	 alert($('.now .cookietest').attr("value"));
	var submit = true;
	username=$(".now input[name='username']").val();
	if(username==""){
		showError($(".now input[name='username']").attr("placeholder"), 500);
		submit = false;
		return false;
	}
	password=$(".now input[name='password']").val();
	if(password==""){
		showError($(".now input[name='password']").attr("placeholder"), 500);
		submit = false;
		return false;
	}
	captcha=$(".now input[name='captcha']").val();
	if(captcha==""){
		showError($(".now input[name='captcha']").attr("placeholder"), 500);
		submit = false;
		return false;
	}
	if (submit) {
//		hideTop();
//		loading('登陆中..', 1);
//		setTimeout("unloading()", 1000);
		setTimeout("Login()", 1000);
	}

}
//登录处理函数
function Login() {
	setCookie();
	var actionurl=path+"/login";
	var formData = new Object();
	formData.username=username;
	formData.password=password;
	formData.captcha=captcha;
	if(!$('.now .nav').find('p').eq(0).hasClass("set")){
		formData.companyId='1';
	}else{
		formData.companyId=$('.now').find('.set').eq(1).attr("value")
	}
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		url : actionurl,// 请求的action路径
		data : formData,
		dataType:'json',
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			if (data.status) {
				//loginsuccess();
				setTimeout("window.location.href='index.jsp'", 1000);
			} else {
				showError(data.message);
			}
		}
	});
}
function hideTop() {
	$('#alertMessage').animate({
		opacity : 0,
		right : '-20'
	}, 500, function() {
		$(this).hide();
	});
}

//加载信息
function loading(name, overlay) {
	$('body').append('<div id="overlay"></div><div id="preloader">' + name + '..</div>');
	if (overlay == 1) {
		$('#overlay').css('opacity', 0.1).fadeIn(function() {
			$('#preloader').fadeIn();
		});
		return false;
	}
	$('#preloader').fadeIn();
}

function unloading() {
	$('#preloader').fadeOut('fast', function() {
		$('#overlay').fadeOut();
	});
}
//显示错误提示
function showError(str) {
	$("#message").html(str)
	$('.bg').show();
	$('.sure').click(function(){
		$('.bg').hide();
	});

}

//设置cookie
function setCookie()
{
	
	var cookieKey=$('.now .cookietest').attr("value");
	var imgUserNameISchecked=$('.now .username').find('img').attr("src");
	var imgPassWordISchecked=$('.now .password').find('img').attr("src");
	//判断记住用户名的复选框是否被勾上了
	if(imgUserNameISchecked==path+"/static/images/checked.png"){
		$.cookie(cookieKey+"_"+"username", username, "/",24);
	}else{
		$.cookie(cookieKey+"_"+"username",null);
	}
	//判断记住密码的复选框是否被勾上了
	if(imgPassWordISchecked==path+"/static/images/checked.png"){
		$.cookie(cookieKey+"_"+"password", password, "/",24);
	}else{
		$.cookie(cookieKey+"_"+"password",null);
	}
	
	
//	if ($('#on_off').val() == '1') {
//		$("input[iscookie='true']").each(function() {
//			$.cookie(this.name, $("#"+this.name).val(), "/",24);
//			$.cookie("COOKIE_NAME","true", "/",24);
//		});
//	} else {
//		$("input[iscookie='true']").each(function() {
//			$.cookie(this.name,null);
//			$.cookie("COOKIE_NAME",null);
//		});
//	}
}
//读取cookie
function getCookie()
{
//	$('.big_box').eq(0).find('.username').find('img').attr("src","images/checked.png");
//	$('.big_box').eq(1).find('.password').find('img').attr("src","images/checked.png");
//	$('.big_box').eq(2).find('.password').find('img').attr("src","images/checked.png");
//	$('.big_box').eq(2).find('.username').find('img').attr("src","images/checked.png");
	
	//
	var BUSINESS_USERNAME=$.cookie("business"+"_"+"username");
	var QJ_USERNAME=$.cookie("qj"+"_"+"username");
	var COMPANY_USERNAME=$.cookie("company"+"_"+"username");
	
	var BUSINESS_PASSWORD=$.cookie("business"+"_"+"password");
	var QJ_PASSWORD=$.cookie("qj"+"_"+"password");
	var COMPANY_PASSWORD=$.cookie("company"+"_"+"password");
//	
//	var cookieKey=$('.now .cookietest').attr("value");
//	var COOKIE_USERNAME=$.cookie(cookieKey+"_"+"username");
//	var COOKIE_PASSWORD=$.cookie(cookieKey+"_"+"password");
	if(BUSINESS_USERNAME!=null){
		$(".big_box input[name='username']").eq(0).val(BUSINESS_USERNAME);
		$('.big_box').eq(0).find('.username').find('img').attr("src",path+"/static/images/checked.png");
//		$('.big_box').eq(0).find('.username').find('img').attr("src")
	}else{
		$('.big_box').eq(0).find('.username').find('img').attr("src",path+"/static/images/nocheck.png");
	}
	if(BUSINESS_PASSWORD!=null){
		$(".big_box input[name='password']").eq(0).val(BUSINESS_PASSWORD);
		$('.big_box').eq(0).find('.password').find('img').attr("src",path+"/static/images/checked.png");
	}else{
		$('.big_box').eq(0).find('.password').find('img').attr("src",path+"/static/images/nocheck.png");
	}
	
	
	if(QJ_USERNAME!=null){
		$(".big_box input[name='username']").eq(1).val(QJ_USERNAME);
		$('.big_box').eq(1).find('.username').find('img').attr("src",path+"/static/images/checked.png");
//		$('.big_box').eq(0).find('.username').find('img').attr("src")
	}else{
		$('.big_box').eq(1).find('.username').find('img').attr("src",path+"/static/images/nocheck.png");
	}
	if(QJ_PASSWORD!=null){
		$(".big_box input[name='password']").eq(1).val(QJ_PASSWORD);
		$('.big_box').eq(1).find('.password').find('img').attr("src",path+"/static/images/checked.png");
	}else{
		$('.big_box').eq(1).find('.password').find('img').attr("src",path+"/static/images/nocheck.png");
	}
	
	if(COMPANY_USERNAME!=null){
		$(".big_box input[name='username']").eq(2).val(COMPANY_USERNAME);
		$('.big_box').eq(2).find('.username').find('img').attr("src",path+"/static/images/checked.png");
//		$('.big_box').eq(0).find('.username').find('img').attr("src")
	}else{
		$('.big_box').eq(2).find('.username').find('img').attr("src",path+"/static/images/nocheck.png");
	}
	if(COMPANY_PASSWORD!=null){
		$(".big_box input[name='password']").eq(2).val(COMPANY_PASSWORD);
		$('.big_box').eq(2).find('.password').find('img').attr("src",path+"/static/images/checked.png");
	}else{
		$('.big_box').eq(2).find('.password').find('img').attr("src",path+"/static/images/nocheck.png");
	}
	
	
	
	
//	if(COOKIE_USERNAME!=null){
//		//$($("#"+this.name).val( $.cookie(this.name)));
//		$(".now input[name='username']").val(COOKIE_USERNAME);
//		$('.now .username').find('img').attr("src",path+"/static/images/checked.png")
//	}else{
//		$('.now .username').find('img').attr("src",path+"/static/images/nocheck.png")
//	}
//	if(COOKIE_PASSWORD!=null){
//		$(".now input[name='password']").val(COOKIE_PASSWORD);
//		$('.now .password').find('img').attr("src",path+"/static/images/checked.png")
//	}else{
//		$('.now .password').find('img').attr("src",path+"/static/images/nocheck.png")
//	}
	
	
//	var COOKIE_NAME=$.cookie("COOKIE_NAME");
//	if (COOKIE_NAME !=null) {
//		$("input[iscookie='true']").each(function() {
//			$($("#"+this.name).val( $.cookie(this.name)));
//		});
//		$("#on_off").attr("checked", true);
//		$("#on_off").val("1");
//	} 
//	else
//	{
//		$("#on_off").attr("checked", false);
//		$("#on_off").val("0");
//	}
}
