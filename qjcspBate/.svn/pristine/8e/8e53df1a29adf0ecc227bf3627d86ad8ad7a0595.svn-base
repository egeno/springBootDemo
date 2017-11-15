
var $dg, $grid;

$(function(){
	 $dg = $("#dg");
	 $('#startDate').datetimebox({
		 required:true,
		 editable:false,
		 setValue:getToDayTime()
	 });
	 $('#startDate').datetimebox('setValue',getZeroTime());
			 
	 $('#endDate').datetimebox({
		 requried:true,
		 editable:false,
	 });
	 $('#endDate').datetimebox('setValue',getToDayTime());
	 /*replenishmentPrint/replenishmentPrintList*/
	 $grid = $dg.datagrid({
		 url:'replenishmentPrint/replenishmentPrintList',
			width:'auto',
			height:'560',
			title:'补货打印单',
			rownumbers:true,
			fit:false,
			fitColumns:true,
			pagination:true,
			pageSize:10,
			pageList:[ 10, 20, 30, 40, 50 ],
			border:true,
			singleSelect:true,
			striped:true,
			cache:false,
			//nowrap:true,
			loadMsg:"正在加载,请稍后...",
			/*fit:true,
			toolbar:'#tool',
			rownumbers:true,
			singleSelect:true,
			striped:'true',
			scrollbarSize:0,滚动条所占的宽度
			fitColumns:true,
			pagination:true,//分页
			pageSize:10,
			pageList:[10,20,30,40,50],
			pageNumber:1,
			beforePageText:'第',
			afterPageText:'页-共{pages}页',
			displayMsg:'当前显示{from}-{to}  共{total}条记录',*/
			queryParams:{
				machineId:$("#machineId").combobox('getValue'), 
				areaModelId:$("#areaModelId").combobox("getValue"),
				temporaryDate:$("#temporaryDate").datebox("getValue"),
			},
			columns: [
		        [
		        {field:'machineName',/*数据库需要的字段*/title:'设备名',align:'center',width:100,fitColumns: true,},
		        {field:'address',/*数据库需要的字段*/title:'设备地址',align:'center',width:100,fitColumns: true,},
		        {field:'cellNum',/*数据库需要的字段*/title:'格子号',align:'center',width:100,fitColumns: true,},
				{field:'goodsName',/*数据库需要的字段*/title:'商品名',align:'center',width:100,fitColumns: true,},
				]
	        ],
		 
	 })
	 
});
/*重置样式*/
$(function(){
	$("#dg").datagrid('resize');
	$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
});

function searchfo(){

	var machineId=$('#machineId').combobox('getValue');
	var areaModelId=$('#areaModelId').combobox('getValue');
	var temporaryDate =$('#temporaryDate').datebox('getValue');
	$("#dg").datagrid("reload",{
		
		machineId:machineId,
		areaModelId:areaModelId,
		temporaryDate:temporaryDate
	});
}

function exportExcel(){

	var machineId=$('#machineId').combobox('getValue');
	var areaModelId=$('#areaModelId').combobox('getValue');
	var temporaryDate =$('#temporaryDate').datebox('getValue');
	location.href=urls['ctx']+"/replenishmentPrint/replenishmentPrintListExport?machineId="+machineId+"&areaModelId="+areaModelId+"&temporaryDate="+temporaryDate;

}

function getToDayTime(){
	var d = new Date()
	var vYear = d.getFullYear()
	var vMon = d.getMonth() + 1
	var vDay = d.getDate()
	var h = d.getHours(); 
	var m = d.getMinutes(); 
	var se = d.getSeconds(); 
	s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay)+" "+(h<10 ? "0"+ h : h)+":"+(m<10 ? "0" + m : m)+":"+(se<10 ? "0" +se : se);
	return s;
}

function getZeroTime(){
	var d = new Date()
	var vYear = d.getFullYear()
	var vMon = d.getMonth() + 1
	var vDay = d.getDate()
	s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay)+" "+"00:"+"00:"+"00";
	return s;
}
