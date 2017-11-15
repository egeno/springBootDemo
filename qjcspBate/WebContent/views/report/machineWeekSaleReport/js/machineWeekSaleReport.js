var $dg;
var $grid;
var psdate;
var machineSex;
var useOrNot;
$(function() {
	$("#panel").panel({
		   width:'auto',   
		   height:$(this).height()  
	});
	$dg = $("#dg");
	$dg.datagrid({
		url :  "machineWeekSaleReport/toLoadIndexData",
		queryParams:{
			'psdate':$('#psdate').datebox('getValue'),
			'machineName':$('#machineName').val(),
			//'companyId':$("#companyId").combobox('getValue'),
			//'machineSex':$("#machineSex").combobox('getValue'),
			//'useOrNot':$("#useOrNot").combobox('getValue')
		},
		width : 'auto',
		height : $(this).height(),
		border:false,
		singleSelect:true,
		striped:true,
		fit:false,
		fitColumns:true,
		
		cache:false,
		onLoadSuccess:function(data){
			console.log(data);
			var cnum = 0;
			for(var i=0; i<data.machineRowspan.length; i++){
				$dg.datagrid('mergeCells',{
					index:cnum,
					field:'machineName',
					rowspan:data.machineRowspan[i]
				});
				cnum = cnum + data.machineRowspan[i];
			}
			var num = 0;
			for(var i=0; i<data.areaModelRowspan.length; i++){
				$dg.datagrid('mergeCells',{
					index:num,
					field:'areaModelName',
					rowspan:data.areaModelRowspan[i]
				});
				num = num + data.areaModelRowspan[i];
			}
		},
		columns : [ [ 
		             {field : 'machineName',title : '柜子名称',width : parseInt($(this).width()*0.25),align : 'center',editor : "text"},
		             {field : 'areaModelName',title : '模型名称',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'goodsName',title : '商品名称',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'goodsNum',title : '销售数',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             ] ],toolbar:'#tb'
	});
	$(".datebox :text").attr("readonly","readonly");
});
$(".calendar-day").die().live( 'click', function() { 
	psdate= $('#psdate').datebox('getValue');
	location.href=urls['ctx']+"/machineWeekSaleReport/toIndex?psdate="+psdate;
//	$dg.datagrid({
//		url :  "machineWeekSaleReport/toIndex",
//		queryParams:{
//			'psdate':psdate
//		}
})

var machineName;
function getData(){
	 psdate = $('#psdate').datebox('getValue');
	 machineName = $('#machineName').val();
	 /*var companyId = $("#companyId").combobox('getValue');
	 machineSex = $("#machineSex").combobox('getValue');
	 useOrNot = $('#useOrNot').combobox('getValue');*/
	$("#dg").datagrid("reload",{
		psdate:psdate,
		machineName:machineName
/*		companyId:companyId,
		machineSex:machineSex,
		useOrNot:useOrNot*/
	});
}

function exportExcel(){
	psdate = $('#psdate').datebox('getValue');
	machineName = $('#machineName').val();
	//var companyId=$("#companyId").combobox('getValue');
	location.href=urls['ctx']+"/machineWeekSaleReport/machineWeekSaleReportExport?psdate="+psdate+"&machineName="+encodeURI(encodeURI(machineName));//+"&companyId="+companyId;
}