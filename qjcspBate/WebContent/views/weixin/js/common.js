//var apiurl = 'http://192.168.1.146:8081/qjcspBate/';
//var apiurl = 'http://192.168.1.143:8080/qjcspBate/';
//var apiurl = 'http://192.168.1.43:8080/qjcspBate/';
//var apiurl = 'http://192.168.1.152:8080/qjcspBate/';
//var apiurl = 'http://192.168.1.194:8080/qjcspBate/';
//var apiurl = 'http://192.168.1.165:8080/qjcspBate/';
var apiurl = 'http://weixinwap.quanjiakeji.com/qjcspBate/';



$(document).ready(function(){
	//底部导航切换	
	$('.foot li').eq(0).on('touchstart',function(){
		window.location.href = '../view/index.html?live';
	});
	$('.foot li').eq(1).on('touchstart',function(){
		window.location.href = '../view/about.html?live;'
	});
	
	$('.foot li').eq(2).on('touchstart',function(){
		//window.location.href = '../view/testyz.html?live';
	});
	
	$('.foot li').eq(3).on('touchstart',function(){
		//window.location.href = '../view/testyz.html?live';
	});
	$('.foot li').eq(4).on('touchstart',function(){
 	    	window.location.href = '../view/login.html?live';
	});
	//商家电话
	$('.businessPhone').on('click',function(){
		$('.order_tel').parent().show();
	});
	$('.cancel').on('click',function(){
		$('.order_tel').parent().hide();
	});
	$('.gotoback').on('click',function(){
		window.location.href = '../view/index.html?live';
	});
	$('.gotoorder').on('click',function(){
		window.location.href = '../view/order.html?live';
	});
});