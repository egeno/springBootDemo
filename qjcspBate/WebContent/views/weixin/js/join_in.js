/**
 * Created by Administrator on 2016/8/8.
 */
$(function(){
	//下拉框
	var cli=1
	   $('#joinTc').click(function(){
		   if (cli==1){
	        $('#joinTc .mytc_join').css('box-shadow','0 0 16px 0 #ccc').show()
		   }
		   if (cli==2){
			   setTimeout(function(){
			        $('#joinTc .mytc_join').css('box-shadow','0 0 16px 0 #ccc').hide()
			   },300)

			   }
		   cli++
		   if(cli==3){ 
			   cli=1
			   }
	    })
	
	    
	 //合作方式选择
	    $(".show_it").show();
	    $('.protocol dt').eq(0).show();
	    $('.mytc_join a').click(function(){
		$('.nav_list').text($(this).text());
		$('.joinType').attr('value',$(this).attr("value"));
		var index=$(this).index();
		$('.protocol dt').hide();
		$('.protocol dt').eq(index).show();
		$(".show_it").show();
	 })
	 
	/* $('.mytc_join a').click(function(){
		
		
	 })*/
	 
	 
	
	 $('#joinTc div a').hover(function(){
		 $(this).css('background','#efefef')
	 },function(){
		 $(this).css('background','#fff')
	 })
	
	
	
	  $('.hao').click(function(){
	            $('.tanchuan').hide()
	        })
	
})
$(function(){
	//checkOpenId();

    //验证号码
    $('.phone_user').blur(function(){
        if(!/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/.test($('.phone_user').val())){
            $('.phone_wrong').show()
        }else{
            $('.phone_wrong').hide()
        }
    })
    $('.phone_user').on('keyup',function(){
        if(/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/.test($('.phone_user').val())){
            $('.phone_wrong').hide()
        }
    })
    $('input').blur(function(){
    	if($('input').eq(0).val().length>0 && $(".phone_wrong").is(":hidden")){
    		$(".btn").css("background","#ff831f");
    		$(".btn").addClass("btnok");
    	}else{
    		$(".btn").css("background","rgba(255,132,32,0.5)");
    		$(".btn").removeClass("btnok");
        }
    })
    //姓名检测
/*    $('.your_name').blur(function(){
        if($('.your_name').val().length <=0) {
            $('.name_wrong').show()
        }else{
            $('.name_wrong').hide()
        }

    })*/

  /*  $('.your_name').on('keyup',function(){
        if($('.your_name').val().length >0) {
            $('.name_wrong').hide()
        }
    })*/
  /*  $(".btn").on("click",function() {
    	$('.phone_error').css('display','inline-block');
        setTimeout(function(){
            $('.phone_error').hide();
        },3000);
    })*/
    //申请加盟数据
    $(".btnok").live("click",function() {
        if(!/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/.test($('.phone_user').val())){
            $('.phone_error').css('display','inline-block');
            setTimeout(function(){
                $('.phone_error').hide();
            },3000)
        }else{
            var url= apiurl +"api/wechat/addJoin";
            var joinType = $('.joinType').attr('value');
            var applicant = $('.applicant').val();
            var applicantTel = $('.applicantTel').val();
            var content = $('.content').val();
            var jsonData={"applicant":applicant,"applicantTel":applicantTel,"joinType":joinType,"content":content};
            $.ajax({
                type:"post",
                url:url,
                data:$.toJSON(jsonData),
                dataType:'json',
               success:function(data)
                {
                    if(data.returnCode=="1"){
                    	$(".reter").text("恭喜您，申请成功！");
                     /* 成功 */ $('.tanchuan').show();
                     setTimeout(function(){
                     	window.location.href = '../view/index.html?live';
                     },2000)
                    }if(data.returnCode=="0"){
                    	$(".reter").text(data.returnContent);
                  /* 重复申请 */$('.tanchuan').show()
                }
                }
            });
        }
    })



})