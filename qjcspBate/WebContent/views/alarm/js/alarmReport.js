var $dg, $grid;
$(function(){
	 $dg = $("#Partition");
	var date=new Date();
	$("#sellStartTime").datebox("setValue",getZeroTime());
	$("#sellEndTime").datebox("setValue",getToDayTime());
	//var companyIdVal=$("#companyId").val();
	$grid=$dg.datagrid({
		url:"alarmReport/findAlarmReport",
		width:'auto',
		title:'故障统计表',
		fit:true,
		toolbar:'#tool',
		rownumbers:true,
		singleSelect:true,
		striped:true,
		//scrollbarSize:0,/*滚动条所占的宽度*/
		fitColumns:false,
		pagination:true,/*分页*/
		cache:true,
		pageSize:10,
		pageList:[50,100],
		pageNumber:1,
		beforePageText:'第',
		afterPageText:'页-共{pages}页',
		displayMsg:'当前显示{from}-{to}  共{total}条记录',
		loadMsg:"正在加载,请稍后...",
		queryParams:{
			deviceFaultSymbol:$("#deviceFaultSymbol").combobox('getValue'), 
        	sellStartTime:$("#sellStartTime").datebox("getValue"),
        	sellEndTime:$("#sellEndTime").datebox("getValue"),
        	shopName:$("#shopName").val(),
        	machineName:$("#machineName").val(),
		},
		
		columns: [  
	        [
	        {field:'machineName',/*数据库需要的字段*/title:'设备名称',align:'center',width:150,fitColumns: true,},
	        {field:'areaModelName',/*数据库需要的字段*/title:'模型名称',align:'center',width:50,fitColumns: true,},
	        {field:'orderNum',/*数据库需要的字段*/title:'订单号',align:'center',width:160,fitColumns: true,},
			{field:'deviceFaultSymbol',title:'故障取餐类型',align:'center',width:80,fitColumns: true,formatter:function(value, row, index){
     			var json = {
             			'0': "客户收到",
             			'1': "客户未收",
             		};
         			return json[row.deviceFaultSymbol] || "未知方式";
         		}},
			{field:'goodsName',/*数据库需要的字段*/title:'商品名称',align:'center',width:250,fitColumns: true,},
			{field:'cellNum',/*数据库需要的字段*/title:'格子号',align:'center',width:50,fitColumns: true,},
			{field:'costPrice',/*数据库需要的字段*/title:'餐品单价',align:'center',width:50,fitColumns: true,},
			{field:'alarmTime',/*数据库需要的字段*/title:'故障日期',align:'center',width:130,fitColumns: true, formatter:function(value, row, index){
     			return new Date(row.alarmTime).format("yyyy-MM-dd hh:mm:ss");
     		}},
			{field:'repairName',/*数据库需要的字段*/title:'维修员姓名',align:'center',width:80,fitColumns: true,},
			{field:'repairPhone',/*数据库需要的字段*/title:'维修员手机',align:'center',width:100,fitColumns: true,},
			{field:'machineAdress',/*数据库需要的字段*/title:'设备地址',align:'center',width:350,fitColumns: true,}
//			{field:'alarmName',/*数据库需要的字段*/title:'故障原因',align:'center',width:100,fitColumns: true,},
//			{field:'refundReason',/*数据库需要的字段*/title:'备注（退款原因）',align:'center',width:100,fitColumns: true,},
			]
	        
        ],
        onLoadSuccess:function(data){
			console.log(data);
			var cnum = 0;
			for(var i=0; i<data.machineRowspan.length; i++){
				$('#Partition').datagrid('mergeCells',{
					index:cnum,
					field:'machineName',
					rowspan:data.machineRowspan[i]
				});
				cnum = cnum + data.machineRowspan[i];
			}
			var num = 0;
			for(var i=0; i<data.areaModelRowspan.length; i++){
				$('#Partition').datagrid('mergeCells',{
					index:num,
					field:'areaModelName',
					rowspan:data.areaModelRowspan[i]
				});
				num = num + data.areaModelRowspan[i];
			}
		},

	});
	//$('.datagrid-cell-c1-goodsName').css({"text-overflow":ellipsis,"overflow":hidden,"white-space":nowrap}).attr("title",$(this).text());
});
/*重置样式*/
$(function(){
	$('#Partition').datagrid('resize');
	$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
	
});

function searchfo(){
	var item = $dg.datagrid('getRows');  
    if (item) {  
        for (var i = item.length - 1; i >= 0; i--) {  
            var index =$dg.datagrid('getRowIndex', item[i]);  
            $dg.datagrid('deleteRow', index);  
        }  
    } 
	var deviceFaultSymbol=$("#deviceFaultSymbol").combobox('getValue');
	var sellStartTime=$("#sellStartTime").datebox("getValue");
	var sellEndTime=$("#sellEndTime").datebox("getValue");
	var shopName=$("#shopName").val();
	var machineName=$("#machineName").val();
	$dg.datagrid("reload",{
		deviceFaultSymbol:deviceFaultSymbol,
		sellStartTime:sellStartTime,
		sellEndTime:sellEndTime,
		shopName:shopName,
		machineName:machineName
	});
}

/*导出*/
function exportExcel(){
	var deviceFaultSymbol=$("#deviceFaultSymbol").combobox('getValue');
	var sellStartTime=$("#sellStartTime").datebox("getValue");
	var sellEndTime=$("#sellEndTime").datebox("getValue");
	var shopName=$("#shopName").val();
	var machineName=$("#machineName").val();
	location.href=urls['ctx']+"/alarmReport/AlarmReportExport?deviceFaultSymbol="+deviceFaultSymbol+"&sellStartTime="+sellStartTime+"&sellEndTime="+sellEndTime+"&shopName="+encodeURI(encodeURI(shopName))+"&machineName="+encodeURI(encodeURI(machineName));
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