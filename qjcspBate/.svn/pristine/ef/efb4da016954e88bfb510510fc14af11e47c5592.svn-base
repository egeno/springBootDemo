$(function(){
	$('#Partition').datagrid({
		url:"recharge/findAllrechargeList",
		width:'auto',
		title:'余额充值查询',
		fit:true,
		toolbar:'#tool',
		rownumbers:true,
		singleSelect:true,
		striped:'true',
		scrollbarSize:0,/*滚动条所占的宽度*/
		fitColumns:true,
		pagination:true,/*分页*/
		pageSize:10,
		pageList:[10,20,30,40,50],
		pageNumber:1,
		beforePageText:'第',
		afterPageText:'页-共{pages}页',
		displayMsg:'当前显示{from}-{to}  共{total}条记录',
		queryParams:{
			 orderNum:$.trim($("#orderNum").val()),
			 userName:$.trim($("#userName").val()),
			 mobile:$.trim($("#mobile").val()),
			 
			 modeNum:$('#modeNum').combobox('getValue'),
			 orderStatus:$('#orderStatus').combobox('getValue')   
		},
		columns: [  
	        [
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
			]
        ],
          

	});
});
/*重置样式*/
$(function(){
	$('#Partition').datagrid('resize');
	$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
});

function searchfo(){
	var orderNum=$.trim($("#orderNum").val());
	var userName=$.trim($("#userName").val());
	var mobile=$.trim($("#mobile").val());
	var modeNum=$('#modeNum').combobox('getValue');
	var orderStatus=$('#orderStatus').combobox('getValue'); 
	$("#Partition").datagrid("load",{
		orderNum:orderNum,
		userName:userName,
		mobile:mobile,
		modeNum:modeNum,
		orderStatus:orderStatus,
	});
}