var $dg;
var $grid;
$(function() {
	$("#panel").panel({
		   width:'auto',   
		   height:$(this).height()  
	});
	$dg = $("#dg");
	$dg.datagrid({
		url :  "statistics/findReserveList",
//		queryParams:{
//			'psdate':$('#psdate').datebox('getValue'),
//			'machineName':$('#machineName').val(),
//			'companyId':$("#companyId").combobox('getValue')
//		},
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
			var cnuma = 0;
			var cnumb = 0;
			for(var i=0; i<data.rowspana.length; i++){
				$dg.datagrid('mergeCells',{
					index:cnuma,
					field:'machinename',
					rowspan:data.rowspana[i]
				});
				cnuma = cnuma + data.rowspana[i];
				
			}
			for(var i=0; i<data.rowspanb.length; i++){
				
				$dg.datagrid('mergeCells',{
					index:cnumb,
					field:'areamodelname',
					rowspan:data.rowspanb[i]
				});
				cnumb = cnumb + data.rowspanb[i];
			}
		},
		columns : [ [ 
		             {field : 'machinename',title : '设备名',width : parseInt($(this).width()*0.25),align : 'center',editor : "text"},
		             {field : 'areamodelname',title : '模型名称',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'goodsname',title : '商品名',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'number',title : '份数',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"}
		             ] ],toolbar:'#tb'
	});
//	$(".datebox :text").attr("readonly","readonly");
});


function getData(){
	var psdate = $('#psdate').datebox('getValue');
	var machinename = $('#machinename').val();
//	var number = $('#number').val();
//	 var typeid=$("#type").combobox('getValue');
	$("#dg").datagrid("reload",{
		psdate:psdate,
		machinename:machinename
	});
}

function exportExcel(){
	var psdate = $('#psdate').datebox('getValue');
	var machinename = $('#machinename').val();
//	var typeid=$("#type").combobox('getValue');
	location.href=urls['ctx']+"/statistics/machineReserveExport?psdate="+psdate+"&machinename="+machinename;
}