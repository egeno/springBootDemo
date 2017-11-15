/*审核通过*/
function pass(){
	var row = $('#Partition').datagrid('getSelected');
	//alert(JSON.stringify(row));
	if(row){
		if(row.businessCheckResult!='5'){
			//alert(row.businessCheckResult);
			parent.$.messager.show({
				title :"提示",
				msg :"已预审,不能重复预审!",
				timeout : 1000 * 2
			});
			return ;
		}
		parent.$.messager.confirm("提示","确定要通过吗?",function(r){
		    if (r){ 
		    	 $.ajax({
			     		url : 'orderRefund/orderRefundOperatePass',
			     		type : 'POST',
			     		dataType : 'json',
			     		data : {"orderRefundId":row.orderRefundId} ,
			     		beforeSend : function(){
					     			parent.$.messager.progress({
					     				title : '提示',
					     				text : '预审中，请稍后....',
					     			});
					     		},
			     		success : function(data){
							if(data.status){
								parent.$.messager.progress('close');
								//$dialog.dialog('close');
								parent.$.messager.show({
									title : '提示',
									msg : data.message,
									timeout : 1000 * 2,
								});
								$("#Partition").datagrid("reload");
							}else{
								parent.$.messager.show({
									title : '提示',
									msg : data.message,
									timeout : 1000 * 2,
								});
								parent.$.messager.progress('close');
							}
			    	 	}
			    	 }) ;
			}
		});
	}else{
		parent.$.messager.show({
			title :"提示",
			msg :"请选择一行记录!",
			timeout : 1000 * 2
		});
	}
	

}

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
	
	$("#Partition").datagrid("load",{
		companyId:companyId,
		orderNum:orderNum,
		refundStartTime:refundStartTime,
		refundEndTime:refundEndTime,
		verifyStatus:verifyStatus
	});
	
	
}
/*审核不通过*/
function out(){
	var row = $('#Partition').datagrid('getSelected');
	if(row){
		if(row.businessCheckResult!='5'){
			parent.$.messager.show({
				title :"提示",
				msg :"已预审,不能重复预审!",
				timeout : 1000 * 2
			});
			return ;
	}
	$dialog = parent.$.modalDialog({ 
		title : "审核不通过",
		width : 400,
		height : 250,
		closed: true,     
	    cache: false,
	    modal : true,
	   	href:"orderRefund/orderRefundOperateRejectMain",
	   	onLoad:function(){
	   		parent.$("#orderRefundId").val(row.orderRefundId);
	   		//alert(parent.$("#orderRefundId").val());
	   	},
	   	buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				/*alert(parent.$("#businessCheckComment").val());
				return;*/
				if(parent.$("#businessCheckComment").val()==null||parent.$("#businessCheckComment").val()==''){
					$.messager.show({
						title : '提示',
						msg : '请输入不通过原因',
						timeout : 1000 * 2,
					});
					return;
				}	
				parent.$("#outForm").form("submit",{
					url:"orderRefund/orderRefundOperateReject",
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
	location.href=urls['ctx']+"/orderRefund/businessExport?companyId="+companyId+"&orderNum="+orderNum+"&refundStartTime="+refundStartTime+"&refundEndTime="+refundEndTime+"&verifyStatus="+verifyStatus;
}
	
	