$(document).ready(function(){
	info();
	//getHistoryMachineId()
	$(".return").click(function(){
		window.location.href = '../view/index.html';
	})
	$(".person .edit").click(function(){
		window.location.href = '../view/infomation.html';
	})
	$(".set_up .edit").click(function(){
		window.location.href = '../view/verification.html';
	})
	$(".exit").click(function(){
		localStorage.clear();
		window.location.href = '../view/login.html';
	})
})


function getHistoryMachineId(){
	var url = apiurl + 'api/app/getHistoryMachineId';
   	var jsonData={"openId":"110"};
   	$.ajax({  
      	type:'post',      
      	url:url,  
      	data:$.toJSON(jsonData),
      	dataType:'json', 
      	async:false, 
      	success:function(data){
      		var returnContent = JSON.stringify(data.returnContent);
      		console.log(JSON.stringify(data));
      		if(returnContent = '""')
      		{
      			//window.location.href = '../view/address.html';
      		}else{
      			//alert("不是第一次访问");
      		}
       	},
       	error:function(){ 
       		console.log("获取微信访问记录失败");
       	}
    });
}

function info(){
	var nickName = localStorage.getItem('nickName');
	$(".name span").text(nickName);
	var mobileNum = localStorage.getItem('telphoneNum');
	var reg = /(\d{3})\d{4}(\d{4})/;
	$(".personInfo .telphone").find(".align").text(mobileNum.replace(reg,"$1****$2"));
	var sex = localStorage.getItem('sex');
	if(sex == null)
	{
		$(".sex .align").text("未设置");
	}else
	{
		$(".sex .align").text(sex);
	}	
	
	var place = localStorage.getItem('place');
	if(place == null)
	{
		$(".provence .align").text("未设置");
	}else
	{
		$(".provence .align").text(place);
	}	
	
	var universityName = localStorage.getItem('universityName');
	if(universityName == null)
	{
		$(".universityName .align").text("未设置");
	}else
	{
		$(".universityName .align").text(universityName);
	}	
	
	var grade = localStorage.getItem('grade');
	if(grade == null)
	{
		$(".grade .align").text("未设置");
	}else
	{
		$(".grade .align").text(grade);
	}	
	
	var dormitory = localStorage.getItem('dormitory');
	if(dormitory == null)
	{
		$(".dormitory .align").text("未设置");
	}else
	{
		$(".dormitory .align").text(dormitory);
	}	
	
	var studentCard = localStorage.getItem('studentCard');
	if(studentCard == null)
	{
		$(".studentCard .align").text("未设置");
	}else
	{
		$(".studentCard .align").text(studentCard);
	}	
	var flag = localStorage.getItem('flag');
	console.log("flag:"+flag);
	if(flag)
	{
		$(".set_up .edit span").text("修改");
		//flag = false;
	}else
	{
		$(".set_up .edit span").text("未设置");
	}
	
}
