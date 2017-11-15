$(function(){
	$('#Partition').datagrid({
		url:"recharge/rechargeStatistics",
		width:'auto',
		title:'余额充值统计',
		fit:true,
		toolbar:'#tool',
		rownumbers:true,
		singleSelect:true,
		striped:'true',
		scrollbarSize:0,/*滚动条所占的宽度*/
		fitColumns:true,
		pagination:true,/*分页*/
		pageSize:10,
		pageList:[10,20,30,40,50],
		pageNumber:1,
		beforePageText:'第',
		afterPageText:'页-共{pages}页',
		displayMsg:'当前显示{from}-{to}  共{total}条记录',
		queryParams:{
			rechargeStartTime:$("#rechargeStartTime").datetimebox('getValue'),  
			rechargeEndTime:$("#rechargeEndTime").datetimebox('getValue'),    
		},
		columns: [  
	        [
	        {field:'realMoney',/*数据库需要的字段*/title:'实际充值金额',align:'center',width:100,fitColumns: true,},
	        {field:'activityMoney',/*数据库需要的字段*/title:'优惠金额',align:'center',width:100,fitColumns: true,},
	        {field:'totalMoney',/*数据库需要的字段*/title:'总金额',align:'center',width:100,fitColumns: true,},
			]
        ],
          

	});
});
/*重置样式*/
$(function(){
	$('#Partition').datagrid('resize');
	$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
});

function searchfo(){
	var rechargeStartTime=$("#rechargeStartTime").datetimebox('getValue');  
	var rechargeEndTime=$("#rechargeEndTime").datetimebox('getValue');  
	$("#Partition").datagrid("load",{
		rechargeStartTime:rechargeStartTime,
		rechargeEndTime:rechargeEndTime,

	});
}

/*导出*/
function exportExcel(){
	var rechargeStartTime=$("#rechargeStartTime").datetimebox('getValue');  
	var rechargeEndTime=$("#rechargeEndTime").datetimebox('getValue');
	location.href=urls['ctx']+"/recharge/rechargeStatisticsExport?rechargeStartTime="+rechargeStartTime+"&rechargeEndTime="+rechargeEndTime;
}