$(function(){
	//var companyIdVal=$("#companyId").val();
	$('#Partition').datagrid({
		url:"rechargeactivity/findAllrechargeActivityList",
		width:'auto',
		title:'充值优惠活动',
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
			rechargeStartTime:$("#rechargeStartTime").datetimebox('getValue'),  
			rechargeEndTime:$("#rechargeEndTime").datetimebox('getValue'),
			    
		},
		columns: [  
	        [
	        {field:'activityId',/*数据库需要的字段*/title:'活动id',align:'center',width:100,hidden:true,fitColumns: true,},
	        {field:'startTime',/*数据库需要的字段*/title:'活动开始时间',align:'center',width:100,fitColumns: true,},
	        {field:'endTime',/*数据库需要的字段*/title:'活动结束时间',align:'center',width:100,fitColumns: true,},
	        {field:'money',/*数据库需要的字段*/title:'满足条件金额',align:'center',width:100,fitColumns: true,},
	        {field:'activityMoney',/*数据库需要的字段*/title:'优惠金额',align:'center',width:100,fitColumns: true,},
	        {field:'createTime',/*数据库需要的字段*/title:'创建时间',align:'center',width:100,fitColumns: true,},
	        {field:'createUserName',/*数据库需要的字段*/title:'创建人',align:'center',width:100,fitColumns: true,},
	        {field:'lastModifyTime',/*数据库需要的字段*/title:'修改时间',align:'center',width:100,fitColumns: true,},
			{field:'modifyUserName',/*数据库需要的字段*/title:'修改人',align:'center',width:100,fitColumns: true,},
			{field:'effectSymbol',/*数据库需要的字段*/title:'活动状态',align:'center',width:100,fitColumns: true,},
			]
        ],
          

	});
});
/*重置样式*/
$(function(){
	$('#Partition').datagrid('resize');
	$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
});

