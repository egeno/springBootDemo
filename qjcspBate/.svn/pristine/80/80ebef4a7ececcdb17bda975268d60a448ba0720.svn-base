 window.onload=function(){
   checkOpenId();
   var ints=document.getElementsByTagName("input");
   var pass=ints[0];
   var pass2=ints[1];   
   var ap=document.getElementsByTagName("p");
   var pass_p=ap[0];
   var pass2_p=ap[1];

  //1 数字，不分大小写，字母 下划线。
  //2 最大长度16 最小长度2

  pass.onfocus=function(){
     pass_p.style.display="block";
     pass2_p.style.display="none";
     pass_p.innerHTML='请输入6位数字密码';
  };

  pass.onblur=function(){
    var m=findstr(pass.value,pass.value[0]);
    //1 密码不能为空
    if(pass.value==""){
          pass_p.innerHTML='请输入密码';
    }
    
    //2 不能全数字，不能全字母
    /*else if(m==pass.value.length){
     pass_p.innerHTML='不能全使用相同的字符';
    }*/
    //3 长度为6位
    /*else if(pass.value.length<6||pass.value.length>6){
        pass_p.innerHTML='密码长度应该在6位';
    }else{
        pass_p.innerHTML='成功';
    }*/
  }
  pass2.onfocus=function(){
	  pass_p.style.display="none";
      pass2_p.style.display="block";
      
  }
  pass2.onblur=function(){
    if(pass2.value==""){
       pass2_p.innerHTML='请再次输入密码';
    }
    else if(pass2.value==pass.value){
        pass2_p.innerHTML='成功';
    }else{
        pass2_p.innerHTML='密码不一致';
    }
  }

}
 
 
function findstr(str,n){
 var stm=0;
 for(var i=0;i<str.length;i++){
     if(str.charAt(i)==n){
         stm++;
     }
 }
 return stm;
}



$(document).ready(function() {
    $("input").blur(function(){
        $("input").css("border"," 1px solid #d8d8d8");
        if($(".onepwd").val()=="" || $(".twopwd").val()==""){
            $(".btn").css("background","rgba(255,132,32,0.5)");
        }else{
            $(".btn").css("background","rgba(255,132,32,1)");
        }
    });
    $("input").focus(function() {
        $(this).css("border"," 1px solid #ffc18f");
    });
    $(document).keyup(function(event){ 
		var pass = $('.onepwd').val();
		var pass2 = $('.twopwd').val();
		if(pass.length == 6)
		{
			console.log("开始判断");
			if(/^\d+$/.test(pass))
			{
				console.log("全是数字");
				if(pass == pass2)
				{
					console.log("两次密码相同");
					$(".btn").click(function(){
						updatePayPassword(pass,pass2);
					})
				}
			}else
			{
				 $(".onets").show().text('请输入数字密码');
			}
		}
	})
    
});


function updatePayPassword(pass,pass2){
	var customerId= sessionStorage.getItem('customerId');
	var url = apiurl + 'api/app/updatePayPassword';
   	var jsonData={"customerId":customerId,"oldPassword":pass,"newPassword":pass2};
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
      			var flag = true;
      			localStorage.setItem("flag",flag);
      			window.location.href = '../view/mine.html';
      		}else{
      			console.log("不是第一次访问");
      		}
       	},
       	error:function(){ 
       		console.log("获取微信访问记录失败");
       	}
    });
}