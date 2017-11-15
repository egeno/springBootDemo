$(function(){
	//var companyIdVal=$("#companyId").val();
	$('#Partition').datagrid({
		url:"orderRefund/findAllRefundList",
		width:'auto',
		title:'运营人员预审',
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
        	companyId:$("#companyId").val(),       
		},
		columns: [  
	        [
	        {field:'companyId',/*数据库需要的字段*/title:'商户ID',align:'center',width:100,hidden:true,fitColumns: true,},
	        {field:'orderRefundId',/*数据库需要的字段*/title:'退款id号',align:'center',width:100,hidden:true,fitColumns: true,},
	        {field:'companyName',/*数据库需要的字段*/title:'商户名称',align:'center',width:100,fitColumns: true,},
			{field:'machineId',/*数据库需要的字段*/title:'设备号',align:'center',width:100,hidden:true,fitColumns: true,},
			{field:'orderNum',/*数据库需要的字段*/title:'订单号码',align:'center',width:100,fitColumns: true,},
			{field:'orderChildNum',/*数据库需要的字段*/title:'子订单号码',align:'center',width:100,fitColumns: true,},
			{field:'orderTime',/*数据库需要的字段*/title:'下单时间',align:'center',fitColumns: true,},			
			{field:'payMode',/*数据库需要的字段*/title:'支付方式',align:'center',fitColumns: true,
				formatter:function(value,row){
					if(row.payMode=='1'){
						return "支付宝";
					}else if(row.payMode=='2'){
						return '微信';
						
					}else if(row.payMode=='3'){
						return "余额";
					}else if(row.payMode==null){
						return "优惠抵扣"
					}					
				}			
			},
			{field:'refundMoney',/*数据库需要的字段*/title:'退款金额',align:'center',width:100,fitColumns: true,},
			
			{field:'alarmId',/*数据库需要的字段*/title:'是否设备故障',align:'center',fitColumns: true,
				formatter:function(value,row){
					if(row.alarmId == 0){						
						return "否";				
					}else {
						return "是";
					}					
				}			
			},			
			{field:'orderStatus',/*数据库需要的字段*/title:'退款状态',align:'center',width:100,fitColumns: true,
				formatter:function(value,row){
          		  	if(row.financeCheckTime!=null){
          		  		return "退款成功";
          		  	}else if(row.businessCheckResult!=null){
          		  		if(row.businessCheckResult=='5'){
          		  			 return "退款中"
          		  			}else if(row.businessCheckResult=='-2'){
          		  				return "退款失败";
          		  		}else if(row.businessCheckResult=='1'){
          		  				return "退款中";
          		  			}
          		  		}
          		  	}
			},
			{field:'mobile',/*数据库需要的字段*/title:'手机号',align:'center',width:100,fitColumns: true,},
			{field:'refundApplyTime',/*数据库需要的字段*/title:'退款申请日期',align:'center',fitColumns: true,},
			
			{field:'businessUserName',/*数据库需要的字段*/title:'预审人员',align:'center',width:100,fitColumns: true,},
			{field:'businessCheckResult',/*数据库需要的字段*/title:'预审结果',align:'center',width:100,fitColumns: true,
				formatter:function(value,row){
//					alert(row.businessCheckResult);
          		  	if(row.businessCheckResult=='5'){
          		  		return "待审核";
          		  	}else if(row.businessCheckResult=='1'){
          		  		
          		  			 return "已通过"
          		  			
          		  		}else if(row.businessCheckResult=='-2'){
          		  				return "未通过";
          		  			}
          		  		}
			
			},
			{field:'financeUserName',/*数据库需要的字段*/title:'财务确认人员',align:'center',width:100,fitColumns: true,},
			{field:'financeCheckTime',/*数据库需要的字段*/title:'财务确认时间',align:'center',width:100,fitColumns: true,},
			]
        ],
          

	});
});
/*重置样式*/
$(function(){
	$('#Partition').datagrid('resize');
	$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
});