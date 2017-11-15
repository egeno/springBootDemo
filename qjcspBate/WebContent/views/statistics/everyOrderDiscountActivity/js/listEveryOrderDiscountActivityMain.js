
var $dg, $grid;

$(function(){
	 $dg = $("#dg");
	 $('#startDate').datetimebox({
		 required:true,
		 editable:false,
//		 setValue:getZeroTime()
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
		url : "statistics/findEveryOrderPage",
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
     		{field : 'orderNum',title : '订单号',align : 'center',width:100,},
         	{field : 'mobile',title : '手机号',align : 'center',width:60, formatter:function(value, row, index){
     			return row.mobile?row.mobile || row.mobile:"";
     		}},
         	{field : 'orderTime',title : '下单时间',align : 'center',width:70, formatter:function(value, row, index){
     			return new Date(row.orderTime).format("yyyy-MM-dd hh:mm:ss");
     		}},
         	{field : 'totalMoney',title : '订单总金额',align : 'center',width:50, formatter:function(value, row, index){
     			return row.totalMoney?parseFloat(row.totalMoney).toFixed(2) || row.totalMoney:"0.00";
     		}},
/*             		{field : '1',title : '总毛利',align : 'center'},
     		{field : '2',title : '优惠方式',align : 'center'},
     		{field : '3',title : '优惠活动主办方',align : 'center'},*/
     		{field : 'discountMoney',title : '优惠金额',align : 'center',width:50,formatter:function(value, row, index){
     			return row.discountMoney?parseFloat(row.discountMoney).toFixed(2) || row.discountMoney:"0.00";
     		}},
     		{field : 'realMoney',title : '实付金额',align : 'center',width:50,formatter:function(value, row, index){
     			return row.realMoney?parseFloat(row.realMoney).toFixed(2) || row.realMoney:"0.00";
     		}},
         	{field : 'payTime',title : '付款时间',align : 'center',width:50, formatter:function(value, row, index){
     			return row.payTime? new Date(row.payTime).format("yyyy-MM-dd hh:mm:ss"): "";
     		}},
     		{field : 'payNumber',title : '支付流水号',align : 'center',width:50,},
         	{field : 'payMode',title : '支付方式',align : 'center',width:50, formatter:function(value, row, index){
         		var json = {
     				0: "闪付",
         			1: "支付宝",
         			2: "微信支付"
         		};
     			return json[row.payMode] || "未知方式";
     		}},
         	{field : 'orderStatus',title : '订单状态',align : 'center',width:50, formatter:function(value, row, index){
         		var statusJson = {
         			0: "待付款",
         			1: "已支付(待取货)",
         			2: "已取消",
         			3: "已取货(待评论)",
         			4: "取货超时",
         			5: "待退款",
         			6: "交易关闭",
         			7: "完成",
         		};
     			return statusJson[row.orderStatus] || "未知状态";
     		}}
     		] ], toolbar: '#tb'
	 });
	 
});

function searchOrderList(){

	var orderNum=$.trim($('#orderNum').val());
	var mobile=$.trim($('#mobile').val());
	var startDate = $('#startDate').datetimebox('getValue') + ""; 
	var endDate = $('#endDate').datetimebox('getValue') + ""; 
	var orderStatus =$('#orderStatus').combobox('getValue');
//	alert(companyName+"+++"+orderNum+"+++"+modeNum+"+++"+mobile+"+++"+startDate+"+++"+endDate+"+++"+orderStatus);
	$("#dg").datagrid("reload",{
		search_orderNum:orderNum,
		search_mobile:mobile,
		search_startDate:startDate,		
		search_endDate:endDate,		
		search_orderStatus:orderStatus		
	});
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



