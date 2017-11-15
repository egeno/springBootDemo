$(function(){
    //添加食材类型
    $('.addFoodBox').live("click",function(){
        $('.foodList_main').append("<li class='cle main'><div class='foodLeft'><div class='foodType'><label>食材类型:</label><input type='text' class='foodTypeName'/><p class='addFoodContent'><span>添加</span></p></div><div class='foodContent'> <div class='foodContent_list'><ul><li class='line'><p><label>成分:</label><input type='text' class='ingredientName'/><label>质量:</label><input type='text' class='grams'/><span class='delIngredient'>删除</span></p> </li></ul></div></div></div><div class='foodRight'><div class='delBox'>删除</div></div></li>")
    });
    //删除整个食品类型
    $('.delBox').live("click",function(){
        /*var url="";
        var jsonData={"":};
        $.ajax({  
            type:'post',      
            url:url,  
            data:$.toJSON(jsonData),
            dataType:'json', 
            success:function(data){*/
                $(this).parents('.main').remove();
            /*},error:function(){
                
            }
        });*/
    });
    //删除主料成分及质量
    $('.delIngredient').live("click",function(){
        /*var url="";
        var jsonData={"":};
        $.ajax({  
            type:'post',      
            url:url,  
            data:$.toJSON(jsonData),
            dataType:'json', 
            success:function(data){*/
                $(this).parents('.line').remove();
            /*},error:function(){
                
            }
        });*/
    })
    //添加主料成分及质量
    $(".addFoodContent").live("click",function(){
        $(this).parents('.main').find('.foodContent_list ul').append("<li class='line'><p><label>成分:</label><input type='text' class='ingredientName'/><label>质量:</label><input type='text' class='grams'/><span class='delIngredient'>删除</span></p> </li>")
    })
})