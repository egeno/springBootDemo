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
		url :  "statistics/findAllMachineSupplyReport",
		queryParams:{
			'psdate':$('#psdate').datebox('getValue'),
			companyId:$("#companyId").combobox('getValue')
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
			console.log(data);
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
		             {field : 'name',title : '柜子名称',width : parseInt($(this).width()*0.25),align : 'center',editor : "text"},
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
	
	$dg.datagrid("load", {'psdate':psdate,'companyId':$("#companyId").combobox('getValue')});
	
	/*$.post(urls['ctx']+"statistics/findAllMachineSupplyReport", {psdate:psdate ,companyId:$("#companyId").combobox('getValue')}, function(rsp) {
		if(rsp.length!=0){
			$dg.datagrid("loadData",rsp);
			$dg.datagrid("load", {'psdate':psdate,companyId:$("#companyId").combobox('getValue')});
		 }else{
			 parent.$.messager.show({title :"提示",msg : "没有供销数据！",timeout : 1000 * 2});
		 }
	}, "JSON").error(function() {
		parent.$.messager.show({title :"提示",msg : "没有供销数据！",timeout : 1000 * 2});
	});*/
}

function exportExcel(){
	psdate = $('#psdate').datebox('getValue');
	var companyId=$("#companyId").combobox('getValue');
	location.href=urls['ctx']+"/statistics/machineSupplyReportExport?psdate="+psdate+"&companyId="+companyId;
}

function seeChart(){
	var options = $dg.datagrid('getPager').data("pagination").options;
	var pageNumber = options.pageNumber;
	var pageSize = options.pageSize;
	psdate = $('#psdate').datebox('getValue');
	var companyId=$("#companyId").combobox('getValue');
	parent.$.modalDialog({
		title : "每日柜子供销图表",
		width : 1200,
		height : 700,
		href : urls["ctx"] + "/statistics/machineSupplyChart?psdate="+psdate+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&companyId="+companyId,
		buttons : [ {
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				parent.$.messager.progress('close');
				parent.$.modalDialog.handler.dialog('close');
				parent.$.modalDialog.openner=undefined ;
			}
		}]
	});
}