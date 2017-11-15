$(document).ready(function(){
	checkOpenId();
	var province = localStorage.getItem("province");
	$(".sex em").click(function(){
		$(this).addClass("checked").siblings().removeClass("checked");
	})
	var nickName = localStorage.getItem("nickName");
	$(".nickName input").val(nickName).css({"color":"#4b4b4b"});
	$(".finish").click(function(){
		saveCustomerInfo();
	})
})
function check(){
	
}
function saveCustomerInfo(){
	checkCustomerId();
	var customerId = sessionStorage.getItem('customerId');
	var nickName = $(".nickName input").val();
	var mobileNum = localStorage.getItem("telphoneNum");
	var universityName = $(".universityName input").val();
	var place = $(".place input").val();
	var grade = $(".grade input").val();
	var dormitory = $(".dormitory input").val();
	var studentCard = $(".studentCard input").val();
	var sex = $(".sex em.checked").text();
	console.log("customerId:"+customerId+"nickName:"+nickName+"mobileNum:"+mobileNum+"universityName:"+universityName+"place:"+place+"grade:"+grade+"dormitory:"+dormitory+"studentCard:"+studentCard+"sex:"+sex);
	var url = apiurl + 'api/app/saveCustomerInfo';
    console.log(url);
    var jsonData={"customerId":customerId,"nickName":nickName,"mobileNum":mobileNum,"universityName":universityName,"place":place,"grade":grade,"dormitory":dormitory,"studentCard":studentCard,"sex":sex};
    $.ajax({
        type:'post',      
        url:url,  
        data:$.toJSON(jsonData),
        dataType:'json', 
        async:false,   
        success:function(data){
        	console.log('saveCustomerInfo is success');
        	window.location.href="mine.html";
        	localStorage.setItem("nickName",nickName);
        	localStorage.setItem("universityName",universityName);
        	localStorage.setItem("place",place);
        	localStorage.setItem("grade",grade);
        	localStorage.setItem("dormitory",dormitory);
        	localStorage.setItem("studentCard",studentCard);
        	localStorage.setItem("sex",sex);
        },
        error:function(){
    		console.log("saveCustomerInfo is wrong");
    	}
    });
}