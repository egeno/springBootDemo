$(function(){
	//var companyIdVal=$("#companyId").val();
	$('#Partition').datagrid({
		url:"discountActivity/findAllDiscountActivity",
		width:'auto',
		title:'完善用户资料后首单优惠',
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
		columns: [  
	        [
	        {field:'discountActivityId',/*数据库需要的字段*/title:'活动id',align:'center',width:100,hidden:true,fitColumns: true,},
	        {field:'startTimeStr',/*数据库需要的字段*/title:'活动开始时间',align:'center',width:100,fitColumns: true,},
	        {field:'endTimeStr',/*数据库需要的字段*/title:'活动结束时间',align:'center',width:100,fitColumns: true,},
	        {field:'discountMoney',/*数据库需要的字段*/title:'抵扣金额',align:'center',width:100,fitColumns: true,},
	        {field:'createTimeStr',/*数据库需要的字段*/title:'创建日期',align:'center',width:100,fitColumns: true,},
	        {field:'lastModTimeStr',/*数据库需要的字段*/title:'修改时间',align:'center',width:100,fitColumns: true,},
	        {field:'createUserName',/*数据库需要的字段*/title:'创建人',align:'center',width:100,fitColumns: true,},
			{field:'modUserName',/*数据库需要的字段*/title:'修改人',align:'center',width:100,fitColumns: true,},
			{field:'effectSymbol',/*数据库需要的字段*/title:'活动有效标志',align:'center',width:100,fitColumns: true,
				formatter:function(value,row){
//					alert(row.businessCheckResult);
          		  	if(row.effectSymbol=='1'){
          		  		return "有效";
          		  	}else {
          		  		return "无效";
          		  	}
          		  		
          		  	}
			
			},
			]
        ],
          

	});
});
/*重置样式*/
$(function(){
	$('#Partition').datagrid('resize');
	$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
});