function toggle()
{
	$(".packet").toggle(function(){
		$(this).find(".left img").addClass("show");
	},function(){
		$(this).find(".left img").removeClass("show");
	})
}
