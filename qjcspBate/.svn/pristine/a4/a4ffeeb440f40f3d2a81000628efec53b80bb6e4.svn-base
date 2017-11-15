//Gekko (已淘汰)
var $dg, $grid;

$(function(){
	 $dg = $("#dg");
	 $('#startDate').datetimebox({
		 required:true,
		 editable:false,
		 setValue:getToDayTime()
	 });
	 $('#startDate').datetimebox('setValue',getZeroTime());
			 
	 $('#endDate').datetimebox({
		 requried:true,
		 editable:false,
	 });
	 $('#endDate').datetimebox('setValue',getToDayTime());
	 
	 $grid = $dg.datagrid({//父表
		width : 'auto',
		height :  $(this).height(),
		url:"recharge/findAllrechargeList1",
		queryParams:{},
		rownumbers:true,
		fit:false,
		fitColumns:true,
		pagination:true,
		pageSize:10,
		pageList:[ 10, 20, 30, 40, 50 ],
		border:true,
		singleSelect:true,
		striped:true,
		cache:false,
		//nowrap:true,
		loadMsg:"正在加载,请稍后...",

		columns : [ [
		 	        {field:'orderNum',/*数据库需要的字段*/title:'订单号',align:'center',width:100,fitColumns: true,},
			        {field:'orderStatus',/*数据库需要的字段*/title:'订单状态',align:'center',width:100,fitColumns: true,},
			        {field:'modeNum',/*数据库需要的字段*/title:'支付类型',align:'center',width:100,fitColumns: true,},
			        {field:'userName',/*数据库需要的字段*/title:'用户名称',align:'center',width:100,fitColumns: true,},
					{field:'mobile',/*数据库需要的字段*/title:'手机号码',align:'center',width:100,fitColumns: true,},
					{field:'totalMoney',/*数据库需要的字段*/title:'总金额',align:'center',width:100,fitColumns: true,},
					{field:'realMoney',/*数据库需要的字段*/title:'实际充值金额',align:'center',width:100,fitColumns: true,},
					{field:'activityMoney',/*数据库需要的字段*/title:'优惠金额',align:'center',width:100,fitColumns: true,},
					{field:'orderTime',/*数据库需要的字段*/title:'订单生成时间',align:'center',width:100,fitColumns: true,},
					{field:'payTime',/*数据库需要的字段*/title:'支付时间',align:'center',width:100,fitColumns: true,},
					{field:'payOrderNum',/*数据库需要的字段*/title:'流水号',align:'center',width:100,fitColumns: true,},
					] ],
     		toolbar: '#tb',
     		view: detailview,  
     		detailFormatter:function(index,row){  
                return '<div style="padding:2px"><table class="ddv"></table></div>'; 
            },
            /*通过订单号查子订单*/
            onExpandRow: function(index,row){//子表，注意此时行索引为index                                  
                var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
                ddv.datagrid({
                    url:"recharge/findRechargeForOrderPage?rechargeId="+row.orderNum,
                    fitColumns:true,
                    singleSelect:true,
                    loadMsg:"正在加载,请稍后...",
                    width: 1000,
                    //height:'auto',
                    autoRowHeight:true,
                    columns:[[{
                        field:'orderChildNum',
                        title:'子订单号',
                        width:100,
                        align : 'center'
                    },{
                        field:'orderChildTotalMoney',
                        title:'订单总金额',
                        width:50,
                        align : 'center'
                    },{
                        field:'offsetAmount',
                        title:'订单抵消金额',
                        width:100,
                        align : 'center'
                    },{
                        field:'rechargeSurplusAmount',
                        title:'充值剩余金额',
                        width:100,
                        align : 'center'
                    },{
                        field:'rechargeTotalMoney',
                        title:'充值总金额',
                        width:50,
                        align : 'center'
                    },{
                        field:'createTime',
                        title:'创建时间',
                        width:100,
                        align : 'center',
                        formatter:function(value, row, index){
                 			return row.createTime? new Date(row.createTime).format("yyyy-MM-dd hh:mm:ss"): "";}
                    }
            ]],
            view: detailview,  
     		detailFormatter:function(index,row){  
                return '<div style="padding:2px"><table class="dddv"></table></div>'; 
            },
            onResize:function(){                 
                    $('#dg').datagrid('fixDetailRowHeight',index);                                      
            },
            onLoadSuccess:function(){
                setTimeout(function(){
                    $('#dg').datagrid('fixDetailRowHeight',index);//在加载父列表明细成功时，获取此时整个列表的高度，使其适应变化后的高度，此时的索引
                    $('#dg').datagrid('fixRowHeight',index);//防止出现滑动条
                },0);
            }
	            , }//二级数据
                );
                $('#dg').datagrid('fixDetailRowHeight',index);
                
            }//二级
	 }//一级
	 );
});

function searchOrderList(){

	var userName=$.trim($('#userName').val());
	var orderNum=$.trim($('#orderNum').val());
	var modeNum =$('#modeNum').combobox('getValue');
	var startDate = $('#startDate').datetimebox('getValue') + ""; 
	var endDate = $('#endDate').datetimebox('getValue') + ""; 
	alert("+++"+orderNum+"+++"+modeNum+"+++"+mobile+"+++"+startDate+"+++"+endDate);
	$("#dg").datagrid("reload",{
		search_userName:userName,
		search_orderNum:orderNum,
		search_modeNum:modeNum,
		search_startDate:startDate,	
		search_endDate:endDate
	});
}

function exportOrderList(){
	location.href=urls['ctx']+"/statistics/paymentStatement/orderListExport";

}


function updateList(){
	$.post("recharge/getByPendingSettlements", function(data) {
		if (data == 1) {
			parent.$.messager.show({
				title : "提示",
				msg : "更新成功",
				timeout : 1000 * 2
			});
		}else if(date==2){
			parent.$.messager.show({
				title : "提示",
				msg : "暂无清算数据",
				timeout : 1000 * 2
			});
		}else{
			parent.$.messager.show({
				title : "提示",
				msg : "更新异常",
				timeout : 1000 * 2
			});
		}
	}, "text");

}
function getToDayTime(){
	var d = new Date()
	var vYear = d.getFullYear()
	var vMon = d.getMonth() + 1
	var vDay = d.getDate()
	var h = d.getHours(); 
	var m = d.getMinutes(); 
	var se = d.getSeconds(); 
	s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay)+" "+(h<10 ? "0"+ h : h)+":"+(m<10 ? "0" + m : m)+":"+(se<10 ? "0" +se : se);
	return s;
}

function getZeroTime(){
	var d = new Date()
	var vYear = d.getFullYear()
	var vMon = d.getMonth() + 1
	var vDay = d.getDate()
	s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay)+" "+"00:"+"00:"+"00";
	return s;
}



