$(function(){
	//var companyIdVal=$("#companyId").val();
	$('#Partition').datagrid({
		url:"modelcleangoods/findAllModelCleanGoodsList",
		width:'auto',
		title:'清货补货时间管理',
		fit:true,
		toolbar:'#tool',
		rownumbers:true,
		singleSelect:true,
		striped:'true',
		scrollbarSize:0,/*滚动条所占的宽度*/
		/*fitColumns:true,*/
		pagination:true,/*分页*/
		pageSize:10,
		pageList:[10,20,30,40,50],
		pageNumber:1,
		beforePageText:'第',
		afterPageText:'页-共{pages}页',
		displayMsg:'当前显示{from}-{to}  共{total}条记录',
		queryParams:{
			companyId:$("#companyId").val(),
			areaModelId:$("#areaModelId").val()
		},
		columns: [  
	        [
	        {field:'cleanId',/*数据库需要的字段*/title:'清货id',align:'center',width:100,hidden:true,fitColumns: false,},
	        {field:'companyId',/*数据库需要的字段*/title:'商户',align:'center',width:100,hidden:true,fitColumns: false,},
	        {field:'areaModelId',/*数据库需要的字段*/title:'模型id',align:'center',width:100,hidden:true,fitColumns: false,},
	        {field:'type',/*数据库需要的字段*/title:'类型',align:'center',width:100,hidden:true,fitColumns: false,},
	        {field:'areaModelName',/*数据库需要的字段*/title:'模型',align:'center',width:100,fitColumns: true,},
	        {field:'dateStr',/*数据库需要的字段*/title:'补货/清货周期',align:'center',width:100,fitColumns: true,},
			{field:'supplyEndTimeStr',/*数据库需要的字段*/title:'补货截止时间',align:'center',width:100,hidden:true,fitColumns: false,},
			{field:'cleanStartTimeStr',/*数据库需要的字段*/title:'清货开始时间',align:'center',width:100,hidden:true,fitColumns: false,},
			
			{field:'reserveTakeStartTimeStr',title:'预定取餐开始时间',align:'center',width:100,fitColumns: false,},
            {field:'reserveTakeEndTimeStr',title:'预定取餐截止时间',align:'center',width:100,fitColumns: false,},
			{field:'reserveEndTimeStr',title:'预定截止时间',align:'center',width:100,fitColumns: false, },
			{field:'reserveCleanStartTimeStr',title:'预定清货开始时间',align:'center',width:100,fitColumns: false },
			{field:'reserveReplenishmentStartTimeStr',title:'预定补货开始时间',align:'center',width:100,fitColumns: false,},
			{field:'reserveSupplyEndTimeStr',title:'预定补货截止时间',align:'center',width:100,fitColumns: false,},
			{field:'retailTakeStartTimeStr',title:'零售取餐开始时间',align:'center',width:100,fitColumns: false, },
			{field:'retailTakeEndTimeStr',title:'零售取餐截止时间',align:'center',width:100,fitColumns: false,},
			{field:'retailCleanStartTimeStr',title:'零售清货开始时间',align:'center',width:100,fitColumns: false, },
			{field:'retailReplenishmentStartTimeStr',title:'零售补货开始时间',align:'center',width:100,fitColumns: false, },
			{field:'retailSupplyEndTimeStr',title:'零售补货截止时间',align:'center',width:100,fitColumns: false, },
			{field:'reserveClearancePromptTimeStr',title:'预定清货提醒',align:'center',width:100,fitColumns: false,},
			{field:'reserveReplenishmentPromptTimeStr',title:'预定补货提示时间',align:'center',width:100,fitColumns: false, },
			{field:'reserveReplenishmentWarningTimeStr',title:'预定补货预警时间',align:'center',width:100,fitColumns: false, },
			{field:'retailClearancePromptTimeStr',title:'零售清货提醒',align:'center',width:100,fitColumns: false,},
			{field:'retailReplenishmentPromptTimeStr',title:'零售补货提示时间',align:'center',width:100,fitColumns: false,},
			{field:'retailReplenishmentWarningTimeStr',title:'零售补货预警时间',align:'center',width:100,fitColumns: false,},
			]
        ],

	});
});

function dateToStr(value){
	//var str='';
	//if(value){
	//    var date = new Date(value);
	//	var h = date.getHours();  
	//	var min = date.getMinutes();  
//		var sec = date.getSeconds();  
//		str = (h<10?('0'+h):h)+':'+(min<10?('0'+min):min)+':'+(sec<10?('0'+sec):sec);
	//	str = (h<10?('0'+h):h)+':'+(min<10?('0'+min):min);
	//}
    return value;  
}  

/*重置样式*/
$(function(){
	$('#Partition').datagrid('resize');
	$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
});