var $dg;
var $grid;
var psdate;
$(function() {
	$("#panel").panel({
		   width:'auto',   
		   height:$(this).height()  
	});
	$dg = $("#dg");
	$dg.datagrid({
		url :  "statistics/findAllGoodsSupplyReport",
		queryParams:{
			'psdate':$('#psdate').datebox('getValue'),
			companyId:$("#companyId").combobox('getValue'),
			foodName:$("#foodName").val()
		},
		width : 'auto',
		height :  $(this).height(),
		border:false,
		singleSelect:true,
		striped:true,
		fit:false,
		fitColumns:true,
		pagination:true,
		pageSize:10,
		pageList:[ 10, 20, 30, 40, 50 ],
		cache:false,
		onLoadSuccess:function(data){
			var cnum = 0;
			for(var i=0; i<data.rowspan.length; i++){
				$dg.datagrid('mergeCells',{
					index:cnum,
					field:'name',
					rowspan:data.rowspan[i]
				});
				cnum = cnum + data.rowspan[i];
			}
		},
		columns : [ [ 
		             {field : 'name',title : '商品名称',width : parseInt($(this).width()*0.25),align : 'center',editor : "text"},
		             {field : 'modName',title : '时间段',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'supplyNum',title : '供货份数',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'soldNum',title : '销售份数',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'unsoldNum',title : '未售份数',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'clearNum',title : '清货份数',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"}				             
		             ] ],toolbar:'#tb'
	});
	$(".datebox :text").attr("readonly","readonly");
});

function getData(){
	psdate = $('#psdate').datebox('getValue');
/*	$.post("statistics/findAllGoodsSupplyReport", {psdate:psdate ,companyId:$("#companyId").combobox('getValue'),foodName:$("#foodName").val()}, function(rsp) {
		if(rsp.length!=0){
			$dg.datagrid("loadData",rsp);*/
		$dg.datagrid("load", {'psdate':psdate,companyId:$("#companyId").combobox('getValue'),foodName:$("#foodName").val()});
	/*	 }else{
			 parent.$.messager.show({title :"提示",msg : "没有供销数据！",timeout : 1000 * 2});
		 }
	}, "JSON").error(function() {
		parent.$.messager.show({title :"提示",msg : "没有供销数据！",timeout : 1000 * 2});
	});*/
}

function exportExcel(){
	psdate = $('#psdate').datebox('getValue');
	var companyId=$("#companyId").combobox('getValue');
	var foodName=$("#foodName").val();
	location.href=urls['ctx']+"/statistics/goodsSupplyReportExport?psdate="+psdate+"&companyId="+companyId+"&foodName="+encodeURI(encodeURI(foodName));
}

