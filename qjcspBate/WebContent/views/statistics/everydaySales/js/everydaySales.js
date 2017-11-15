var $dg;
var $grid;
var psdate;
//var machineSex;
//var useOrNot;
$(function() {
	$("#panel").panel({
		   width:'auto',   
		   height:$(this).height()  
	});
	$dg = $("#dg");
	$dg.datagrid({
		url :  "statistics/everydaySales",
		queryParams:{
			'psdate':$('#psdate').datebox('getValue'),
			'machineName':$('#machineName').val(),
			'companyId':$("#companyId").combobox('getValue'),
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
			for(var i=0; i<data.rowspan.length; i++){
				$dg.datagrid('mergeCells',{
					index:cnum,
					field:'machineName',
					rowspan:data.rowspan[i]
				});
				cnum = cnum + data.rowspan[i];
			}
			var num = 0;
//			for(var i=0; i<data.rowspan.length; i++){
//				$dg.datagrid('mergeCells',{
//					index:num,
//					field:'machineSex',
//					rowspan:data.rowspan[i]
//				});
//				num = num + data.rowspan[i];
//			}
		},
		columns : [ [ 
		             {field : 'machineName',title : '柜子名称',width : parseInt($(this).width()*0.25),align : 'center',editor : "text"},
		            /* {field : 'machineSex',title : '男/女生楼',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},*/
		             {field : 'areaModelName',title : '时间段',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             //{field : 'areaModelStatus',title : '是否投入使用',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'operateCount',title : '补货数',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'orderCount',title : '订单数量',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},		             
		             {field : 'saleCount',title : '销售数',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'saleMoney',title : '销售金额',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'sellRate',title : '售卖率',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"}
		             ] ],toolbar:'#tb'
	});
	$(".datebox :text").attr("readonly","readonly");
});

var machineName;
function getData(){
	 psdate = $('#psdate').datebox('getValue');
	 machineName = $('#machineName').val();
	 var companyId = $("#companyId").combobox('getValue');
	 //machineSex = $("#machineSex").combobox('getValue');
	 //useOrNot = $('#useOrNot').combobox('getValue');
	$("#dg").datagrid("reload",{
		psdate:psdate,
		machineName:machineName,
		companyId:companyId,
		//machineSex:machineSex,
		//useOrNot:useOrNot
	});
}

function exportExcel(){
	psdate = $('#psdate').datebox('getValue');
	machineName = $('#machineName').val();
	var companyId=$("#companyId").combobox('getValue');
	//machineSex = $("#machineSex").combobox('getValue');
	//useOrNot = $('#useOrNot').combobox('getValue');
	location.href=urls['ctx']+"/statistics/everydaysExport?psdate="+psdate+"&machineName="+machineName+"&companyId="+companyId;
}