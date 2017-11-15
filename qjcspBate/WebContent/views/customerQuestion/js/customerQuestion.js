var $dg;
var $grid;
$(function() {
	$("#panel").panel({
		   width:'auto',   
		   height:$(this).height()  
	});
	$dg = $("#dg");
	$dg.datagrid({
		url :  "customerQuestion/findCustomerQuestion",
		queryParams:{},
		width : 'auto',
		height : $(this).height(),
		border:false,
		singleSelect:true,
		striped:true,
		fit:false,
		fitColumns:true,
		pagination:true,
		pageSize:10,
		pageList:[ 10, 20, 30, 40, 50 ],
		border:true,
		cache:false,
		loadMsg:"正在加载,请稍后...",
		columns : [ [ 
		             {field : 'customermobile',title : '手机号',align : 'center',editor : "text"},
		             {field : 'customername',title : '用户名称',align : 'center',editor : "text"},
		             {field : 'createtime',title : '反馈日期',align : 'center',formatter:function(value, row, index){
		      			return new Date(row.createtime).format("yyyy-MM-dd hh:mm:ss");}},
		             {field : 'question',title : '反馈内容',align : 'center',editor : "text"}
		             ] ],toolbar:'#tb'
	});
//	$(".datebox :text").attr("readonly","readonly");
});


function getData(){
	var createtime = $('#createtime').datebox('getValue');
	var customermobile = $('#customermobile').val();
//	var number = $('#number').val();
//	 var typeid=$("#type").combobox('getValue');
	$("#dg").datagrid("reload",{
		search_createtime:createtime,
		search_customermobile:customermobile
	});
}

function exportExcel(){
	var psdate = $('#psdate').datebox('getValue');
	var machinename = $('#machinename').val();
//	var typeid=$("#type").combobox('getValue');
	location.href=urls['ctx']+"/statistics/machineReserveExport?psdate="+psdate+"&machinename="+machinename;
}