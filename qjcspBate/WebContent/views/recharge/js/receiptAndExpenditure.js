/**
 * Gekko 
 * 账户收支明细
 */
var $dg, $grid;

$(function(){
	 $dg = $("#dg");
	 $('#startTime').datetimebox({
		 required:true,
		 editable:false,
		 setValue:getToDayTime()
	 });
	 $('#startTime').datetimebox('setValue',getZeroTime());
			 
	 $('#endTime').datetimebox({
		 requried:true,
		 editable:false,
	 });
	 $('#endTime').datetimebox('setValue',getToDayTime());
	 
	 $grid = $dg.datagrid({//父表
		width : 'auto',
		height :  $(this).height(),
		url:"recharge/receiptAndExpenditurePage",
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
						{
							field : 'orderNum',/* 数据库需要的字段 */
							title : '订单号',
							align : 'center',
							width : 100,
							fitColumns : true,
						},
						{
							field : 'orderChildNum',/* 数据库需要的字段 */
							title : '子订单流水号',
							align : 'center',
							width : 100,
							fitColumns : true,
						},
						{
							field : 'customerId',
							title : '用户id',
							align : 'center',
							width : 100,
							fitColumns : true,
						},
						{
							field : 'customerName',
							title : '用户id',
							align : 'center',
							width : 100,
							fitColumns : true,
						},
						{
							field : 'mobile',/* 数据库需要的字段 */
							title : '手机号码',
							align : 'center',
							width : 100,
							fitColumns : true,
						},
						{
							field : 'orderStatus',
							title : '订单状态',
							align : 'center',
							width : 100,
							fitColumns : true,
						},
						{
							field : 'payMode',
							title : '支付方式',
							align : 'center',
							width : 100,
							fitColumns : true,
						},
						{
							field : 'orderType',
							title : '订单类型',
							align : 'center',
							width : 100,
							fitColumns : true,
						},
						{
							field : 'totalMoney',/* 数据库需要的字段 */
							title : '交易金额',
							align : 'center',
							width : 100,
							fitColumns : true,
						},
						{
							field : 'payTime',
							title : '付款时间',
							align : 'center',
							formatter : function(value, row, index) {
								return row.payTime ? new Date(row.payTime)
										.format("yyyy-MM-dd hh:mm:ss") : "";
							}
						}, ] ],
     		toolbar: '#tb'
	 }
	 );
});

function searchOrderList(){
	var customerId=$.trim($('#customerId').val());
	var orderNum=$.trim($('#orderNum').val());
	var orderType =$('#orderType').combobox('getValue');
	var orderStatus =$('#orderStatus').combobox('getValue');
	var startTime = $('#startTime').datetimebox('getValue') + ""; 
	var endTime = $('#endTime').datetimebox('getValue') + ""; 
	//alert("+++"+orderNum+"+++"+modeNum+"+++"+mobile+"+++"+startDate+"+++"+endDate);
	$("#dg").datagrid("reload",{
		search_customerId:customerId,
		search_orderNum:orderNum,
		search_orderType:orderType,
		search_orderStatus:orderStatus,
		search_startTime:startTime,	
		search_endTime:endTime
	});
}

function exportOrderList(){
	var customerId=$.trim($('#customerId').val());
	var orderNum=$.trim($('#orderNum').val());
	var orderType =$('#orderType').combobox('getValue');
	var orderStatus =$('#orderStatus').combobox('getValue');
	var startTime = $('#startTime').datetimebox('getValue') + ""; 
	var endTime = $('#endTime').datetimebox('getValue') + ""; 
	location.href=urls['ctx']+"/recharge/receiptAndExpenditureExport?search_customerId="+customerId+"&search_orderNum="+orderNum+"&search_orderType="+orderType+"&search_orderStatus="+orderStatus+
			"&search_startTime="+startTime+"&search_endTime="+endTime;
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



