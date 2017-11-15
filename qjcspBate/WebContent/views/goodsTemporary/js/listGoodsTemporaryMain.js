$(function(){
	//$("#temporaryDate").datebox("setValue",getNowTime());
	$('#Partition').datagrid({
		url:"goods/temporary/selectGoodsSaleCount",
		width:'auto',
		title:'商品预订零售数量统计',
		fit:true,
		toolbar:'#tool',
		rownumbers:true,
		singleSelect:true,
		striped:'true',
		scrollbarSize:0,/*滚动条所占的宽度*/
		fitColumns:true,
		//pagination:true,/*分页*/
		//pageSize:10,
		//pageList:[10,20,30,40,50],
		//pageNumber:1,
		//beforePageText:'第',
		//afterPageText:'页-共{pages}页',
		//displayMsg:'当前显示{from}-{to}  共{total}条记录',
		queryParams:{
			machineId:$("#machineId").combobox('getValue'), 
			areaModelId:$("#areaModelId").combobox("getValue"),
			temporaryDate:$("#temporaryDate").datebox("getValue"),
		},
		columns: [
	        [
	        {field:'goodsName',/*数据库需要的字段*/title:'商品名称',align:'center',width:100,fitColumns: true,},
	        {field:'ydCount',/*数据库需要的字段*/title:'预订数量',align:'center',width:100,fitColumns: true,},
	        {field:'lsCount',/*数据库需要的字段*/title:'零售数量',align:'center',width:100,fitColumns: true,},
			{field:'sumCount',/*数据库需要的字段*/title:'总数量',align:'center',width:100,fitColumns: true,},
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
	var machineId=$("#machineId").combobox('getValue');
	var areaModelId=$('#areaModelId').combobox('getValue');
	var temporaryDate=$('#temporaryDate').datebox('getValue');
	
	$("#Partition").datagrid("load",{
		machineId:machineId,
		areaModelId:areaModelId,
		temporaryDate:temporaryDate
	});
}

/*导出*/
function exportExcel(){
	var machineId=$("#machineId").combobox('getValue');
	var areaModelId=$('#areaModelId').combobox('getValue');
	var temporaryDate=$('#temporaryDate').datebox('getValue');
	location.href=urls['ctx']+"/goods/temporary/goodsSaleCountExport?machineId="+machineId+"&areaModelId="+areaModelId+"&temporaryDate="+temporaryDate;
};

function getNowTime(){
	var d = new Date()
	var vYear = d.getFullYear()
	var vMon = d.getMonth() + 1
	var vDay = d.getDate()
	s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay);
	return s;
}