$(document).ready(function() {
    var url = apiurl + 'api/app/getRechargeActivity';
    console.log(url);
    var jsonData={}
    $.ajax({
        type:"post",
        url:url,
        async:false,
        data:$.toJSON(jsonData),
        dataType:'json',
        success:function(data)
        {
            console.log("成功");
            var str=eval(data.returnContent);
            $.each(str,function(v,k){
                console.log(v,k)
                $("#mianzhi").append('<li class="mianzhili" activityId="'+str[v].activityId+'"> <p>'+str[v].totalMoney+'元</p> <span>售价'+str[v].realMoney+'元</span> </li>');
            })
        },
        error:function(data)
        {
            console.log('失败:'+  JSON.stringify(data.returnContent));
        }
    });
    $(".chooseone").click(function() {
        $(".chooseone").removeClass('choosed');
        $(this).addClass('choosed');
    });
    $(".mianzhili").click(function() {
        $(".mianzhili").removeClass('current');
        $(this).addClass('current');
    });
    click();
});
function click(){
    $(".butok").click(function() {
        var customerId = sessionStorage.getItem('customerId');
        console.log("用户ID",customerId)
        var payMode = $(".choosed").parent().attr('payMode');
        var activityId=$(".current").attr("activityId");
        console.log("payMode:",payMode,"activityId",activityId)
        var url = apiurl + 'api/app/commitRechargeOrder';
        console.log("url:",url);
        var jsonData={"customerId":customerId,"payMode":payMode,"activityId":activityId};
        $.ajax({
            type:"post",
            url:url,
            async:false,
            data:$.toJSON(jsonData),
            dataType:'json',
            success:function(data)
            {
                console.log("成功");
                console.log(data)
                localStorage.setItem("payMode",payMode);
                localStorage.setItem("activityId",activityId);

            },
            error:function(data)
            {
                console.log('失败:'+ JSON.stringify(data.returnContent));
            }
        });
    }); 
}
