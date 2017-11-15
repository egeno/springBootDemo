$(function(){
	$('#Partition').datagrid({
		url:"pickSearch/findAllpickList",
		width:'auto',
		title:'取餐记录查询',
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
        	companyId:$("#companyId").combobox('getValue'), 
		},
		
		columns: [  
	        [
	        {field:'machineName',/*数据库需要的字段*/title:'设备名称',align:'center',width:100,fitColumns: true,},
	        {field:'orderNum',/*数据库需要的字段*/title:'订单编号',align:'center',width:100,fitColumns: true,},
	        {field:'pickTime',/*数据库需要的字段*/title:'取餐时间',align:'center',width:100,fitColumns: true,},
			{field:'goodsName',/*数据库需要的字段*/title:'菜品名称',align:'center',width:100,fitColumns: true,},
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
	var orderNum=$.trim($("#orderNum").val());
	var goodsName=$.trim($("#goodsName").val());
	var pickStartTime=$('#pickStartTime').datebox('getValue');
	var pickEndTime=$('#pickEndTime').datebox('getValue');
	var machineId=$("#machineId").combobox('getValue');
	var companyId=$("#companyId").combobox('getValue');
	if(machineId=="全部"){
		machineId="";
	}
	$("#Partition").datagrid("load",{
		orderNum:orderNum,
		goodsName:goodsName,
		pickStartTime:pickStartTime,
		pickEndTime:pickEndTime,
		machineId:machineId,
		companyId:companyId
	});
}