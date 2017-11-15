window.onload=function(){
   var ints=document.getElementsByTagName("input");
   var past=ints[0];
   var pass=ints[1];
   var pass2=ints[2];   
   var ap=document.getElementsByTagName("p");
   var past_p=ap[0];
   var pass_p=ap[1];
   var pass2_p=ap[2];


  //1 数字，不分大小写，字母 下划线。
  //2 最大长度16 最小长度2
  past.onfocus=function(){
     past_p.style.display="block";
     past_p.innerHTML='请输入当前密码';
  };

  past.onblur=function(){
    var m=findstr(past.value,past.value[0]);
    //1 密码不能为空
    if(past.value==""){
          past_p.innerHTML='请输入密码';
    }
    //2 长度为6位
    else if(past.value.length<6||past.value.length>6){
        past_p.innerHTML='密码长度应该在6位';
    }else{
        past_p.innerHTML='成功';
    }
  }

  pass.onfocus=function(){
     pass_p.style.display="block";
     pass_p.innerHTML='请输入6位数字密码';
  };

  pass.onblur=function(){
    var m=findstr(pass.value,pass.value[0]);
    //1 密码不能为空
    if(pass.value==""){
          pass_p.innerHTML='请输入密码';
    }
    
    //2 不能全数字，不能全字母
    else if(m==pass.value.length){
     pass_p.innerHTML='不能全使用相同的字符';
    }
    //3 长度为6位
    else if(pass.value.length<6||pass.value.length>6){
        pass_p.innerHTML='密码长度应该在6位';
    }else{
        pass_p.innerHTML='成功';
    }
  }
  pass2.onfocus=function(){
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
        if($(".pstpwd").val()=="" || $(".newpwd").val()=="" || $(".new2pwd").val()==""){
            $(".btn").css("background","rgba(255,132,32,0.5)");
        }else{
            $(".btn").css("background","rgba(255,132,32,1)");
        }
    });
    $("input").focus(function() {
        $(this).css("border"," 1px solid #ffc18f");
    });
    $(".know").click(function() {
      $(".pop-up1").hide();
      $(".gray").hide();
    });
});