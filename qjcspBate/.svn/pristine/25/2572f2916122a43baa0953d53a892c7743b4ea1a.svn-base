function searchfo(){
//	alert($("#refundStartTime").val());
//	alert(1);
	//公司
	var companyId=$("#companyId").val();
	//订单号
	var orderNum=$.trim($("#orderNum").val());
	//退款开始时间
	var refundStartTime=$('#refundStartTime').datebox('getValue');
	//退款结束时间
	var refundEndTime=$('#refundEndTime').datebox('getValue');
	//审核状态
	var verifyStatus=$("input[type='radio']:checked").val();
//    var obj = eval('('+str+')');
//    alert(obj);
	
	$("#Partition").datagrid("reload",{
		companyId:companyId,
		orderNum:orderNum,
		refundStartTime:refundStartTime,
		refundEndTime:refundEndTime,
		verifyStatus:verifyStatus
	});
	
	
}
/*财务确认*/
function confirm(){
	var row = $('#Partition').datagrid('getSelected');
	if(row){
		if(row.financeCheckTime!=null){
			parent.$.messager.show({
				title :"提示",
				msg :"已确认,不能重复确认!",
				timeout : 1000 * 2
			});
			return ;
	}
	$dialog = parent.$.modalDialog({ 
		title : "退款确认",
		width : 400,
		height : 250,
		closed: true,     
	    cache: false,
	    modal : true,
	   	href:"orderRefund/orderRefundFinanceConfirmMain",
	   	onLoad:function(){
	   		parent.$("#orderRefundId").val(row.orderRefundId);
//	   		alert(parent.$("#orderRefundId").val());
	   	},
	   	buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				parent.$("#confirmForm").form("submit",{
					url:"orderRefund/orderRefundFinanceConfirm",
//					onSubmit:function(){
//						
////						if(){
////							
////						}
//					},
					success:function(data){
						var result= eval("(" + data + ")");
						if(result.status){
							$dialog.dialog('close');
							$.messager.show({
								title : '提示',
								msg : result.message,
								timeout : 1000 * 2,
							});
							$("#Partition").datagrid("reload");
						}else{
							$.messager.show({
								title : '提示',
								msg : result.message,
								timeout : 1000 * 2,
							});
						}
						
					}
				});
				
				
			}
		}, 
			{
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$dialog.dialog('close');
				}
			}],
			 success : function(result) {

		 	}
			// });
		});
		$dialog.dialog('open');
	}else{
		parent.$.messager.show({
			title :"提示",
			msg :"请选择一行记录!",
			timeout : 1000 * 2
		});
		
	}
} 
/*导出*/
function excel(){
	//公司
	var companyId=$("#companyId").val();
	//订单号
	var orderNum=$.trim($("#orderNum").val());
	//退款开始时间
	var refundStartTime=$('#refundStartTime').datebox('getValue');
	//退款结束时间
	var refundEndTime=$('#refundEndTime').datebox('getValue');
	//审核状态
	var verifyStatus=$("input[type='radio']:checked").val();
	
	location.href=urls['ctx']+"/orderRefund/financeExport?companyId="+companyId+"&orderNum="+orderNum+"&refundStartTime="+refundStartTime+"&refundEndTime="+refundEndTime+"&verifyStatus="+verifyStatus;
	
};