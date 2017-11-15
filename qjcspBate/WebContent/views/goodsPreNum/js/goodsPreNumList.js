$(function(){
	//$("#temporaryDate").datebox("setValue",getNowTime());
	$('#Partition').datagrid({
		url:"goods/selectFoodPreNum",
		width:'auto',
		title:'商品预订数量统计',
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
			machineId:$("#machineId").combobox('getValue'), 
			areaModelId:$("#areaModelId").combobox("getValue"),
			startTime:$("#startTime").datebox("getValue"),
			endTime:$("#endTime").datebox("getValue"),
			endTime:$("#endTime").datebox("getValue"),
			foodName:$("#foodName").val(),
		},
		columns: [
	        [
	        {field:'foodName',/*数据库需要的字段*/title:'商品名称',align:'center',width:100,fitColumns: true,},
	        {field:'preNum',/*数据库需要的字段*/title:'预订数量',align:'center',width:100,fitColumns: true,},
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
	var startTime=$('#startTime').datebox('getValue');
	var endTime=$('#endTime').datebox('getValue');
	var foodName=$("#foodName").val();
	var flag=true;
	if(startTime!=""&&endTime==""){
		flag=false;
		 $.messager.show({
			title : '提示',
			msg : '请选择结束时间再进行查询',
			timeout : 3000,
		});
	}else if(endTime!=""&&startTime==""){
		flag=false;
		 $.messager.show({
			title : '提示',
			msg : '请选择开始时间再进行查询',
			timeout : 3000,
		});
	}else if(parseInt(endTime.replace(/-/g,""))<parseInt(startTime.replace(/-/g,""))){
		flag=false;
		 $.messager.show({
			title : '提示',
			msg : '开始时间不能晚于结束时间~',
			timeout : 3000,
		});
	}
	if(flag){
		$("#Partition").datagrid("load",{
			machineId:machineId,
			areaModelId:areaModelId,
			startTime:startTime,
			endTime:endTime,
			foodName:foodName
		});
	}
}

/*导出*/
function exportExcel(){
	var machineId=$("#machineId").combobox('getValue');
	var areaModelId=$('#areaModelId').combobox('getValue');
	var startTime=$('#startTime').datebox('getValue');
	var endTime=$('#endTime').datebox('getValue');
	var foodName=$("#foodName").val();
	location.href=urls['ctx']+"/goods/foodPreNumExportExcel?machineId="+machineId+"&areaModelId="+areaModelId+"&startTime="+startTime+"&endTime="+endTime+"&foodName="+encodeURI(encodeURI(foodName));
};

function getNowTime(){
	var d = new Date()
	var vYear = d.getFullYear()
	var vMon = d.getMonth() + 1
	var vDay = d.getDate()
	s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay);
	return s;
}