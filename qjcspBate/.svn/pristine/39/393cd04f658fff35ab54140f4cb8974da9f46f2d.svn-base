$(function(){
	var date=new Date();
	$("#sellStartTime").datebox("setValue",getZeroTime());
	$("#sellEndTime").datebox("setValue",getToDayTime());
	//var companyIdVal=$("#companyId").val();
	$('#Partition').datagrid({
		url:"goodsSellReport/findAllGoodsSellReport",
		width:'auto',
		title:'菜品销售统计',
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
        	sellStartTime:$("#sellStartTime").datebox("getValue"),
        	sellEndTime:$("#sellEndTime").datebox("getValue"),
        	foodName:$("#foodName").val(),
		},
		columns: [  
	        [
	        {field:'goodsName',/*数据库需要的字段*/title:'商品名称',align:'center',width:100,fitColumns: true,},
	        {field:'unitPrice',/*数据库需要的字段*/title:'单价',align:'center',width:100,fitColumns: true,},
	        {field:'putGoodsCount',/*数据库需要的字段*/title:'补货数',align:'center',width:100,fitColumns: true,},
			{field:'orderCount',/*数据库需要的字段*/title:'下单数',align:'center',width:100,fitColumns: true,},
			{field:'sellCount',/*数据库需要的字段*/title:'销售数',align:'center',width:100,fitColumns: true,},
			{field:'sellMoney',/*数据库需要的字段*/title:'销售金额',align:'center',width:100,fitColumns: true,},
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
	var companyId=$("#companyId").combobox('getValue');
	var sellStartTime=$('#sellStartTime').datebox('getValue');
	var sellEndTime=$('#sellEndTime').datebox('getValue');
	var foodName=$("#foodName").val();
	$("#Partition").datagrid("load",{
		companyId:companyId,
		sellStartTime:sellStartTime,
		sellEndTime:sellEndTime,
		foodName:foodName
	});
}

/*导出*/
function exportExcel(){
	var companyId=$("#companyId").combobox('getValue');
	var sellStartTime=$('#sellStartTime').datebox('getValue');
	var sellEndTime=$('#sellEndTime').datebox('getValue');
	var foodName=$("#foodName").val();
	location.href=urls['ctx']+"/goodsSellReport/goodsSellReportExport?companyId="+companyId+"&sellStartTime="+sellStartTime+"&sellEndTime="+sellEndTime+"&foodName="+encodeURI(encodeURI(foodName));
};



function getToDayTime(){
	var d = new Date()
	var vYear = d.getFullYear()
	var vMon = d.getMonth() + 1
	var vDay = d.getDate()
	var h = d.getHours(); 
	var m = d.getMinutes(); 
	var se = d.getSeconds(); 
	s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay);
	return s;
}

function getZeroTime(){
	var d = new Date()
	var vYear = d.getFullYear()
	var vMon = d.getMonth() + 1
	var vDay = d.getDate()
	s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay);
	return s;
}