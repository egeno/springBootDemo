var $dg;
var $grid;
$(function() {
	$("#panel").panel({
		   width:'auto',   
		   height:$(this).height()  
	});
	$dg = $("#dg");
	$dg.datagrid({
		url:urls["ctx"] +"/statistics/operateRecording",
		width : 'auto',
		height :  $(this).height(),
		border:false,
		singleSelect:true,
		striped:true,
		fit:false,
		rownumbers:true,
		fitColumns:true,
		pagination:true,
		pageSize:10,
		pageList:[ 10, 20, 30, 40, 50 ],
		columns : [ [ 
	              {field:'operateTime',title:'操作时间',align:'center',width:100,fitColumns: true},
	              {field:'operateType',title:'清货/补货类型',align:'center',width:100,fitColumns: true,
	            	  formatter: function(value,row,index){
	            		  if (row.operateType==1){
	            			  return '补货';
	            		  	} else {
	            		  		return '清货';
	            		  	}
	            	  }
	              },
	              {field:'machineName',title:'设备名称',align:'center',width:100,fitColumns: true},
	              {field:'goodsName',title:'菜品名称',align:'center',width:100,fitColumns: true}
	              ] ],toolbar:'#tb'
	});
});

function searchfo(){
	search_goodsName=$("#goodsName").val();
	search_operateType=$('#operateType').combobox('getValue');
	search_startTime=$("#startTime").datetimebox('getValue');
	search_endTime=$("#endTime").datetimebox('getValue');
	//search_operateMode=$('#operateMode').combobox('getValue');
	search_userName=$("#playName").val();
	search_machineId=$("#machineId").combobox('getValue');
	$dg.datagrid("reload",{
		search_goodsName:search_goodsName,
		search_operateType:search_operateType,
		search_startTime:search_startTime,
		search_endTime:search_endTime,
		//search_operateMode:search_operateMode,
		search_userName:search_userName,
		search_machineId:search_machineId
	});
}