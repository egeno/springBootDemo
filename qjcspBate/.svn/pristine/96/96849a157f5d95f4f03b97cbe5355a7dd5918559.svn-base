
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
		url : "statistics/financial/QueryReconciliation",
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
		    {field : 'machineName',title : '柜子名称',align : 'center'},
     		{field : 'orderNum',title : '订单号',align : 'center'},
     		{field : 'modeNum',title : '下单方式',align : 'center', formatter:function(value, row, index){
     			var json = {
             			0: "设备端",
             			1: "安卓端",
             			2: "IOS端",
             			3: "微信端",
             		};

         			return json[row.modeNum] || "未知方式";
         		}},
         	{field : 'mobile',title : '用户名(手机号)',align : 'center', formatter:function(value, row, index){
     			return row.mobile?row.mobile || row.mobile:"";
     		}},
         	{field : 'orderTime',title : '下单时间',align : 'center', formatter:function(value, row, index){
     			return new Date(row.orderTime).format("yyyy-MM-dd hh:mm:ss");
     		}},
     		{field : 'goodsName',title : '商品名称',align : 'center', formatter:function(value, row, index){
     			return row.goodsName?row.goodsName || row.goodsName:"";
     		}},
     		{field : 'unitPrice',title : '单品价格',align : 'center', formatter:function(value, row, index){
     			return row.unitPrice?row.unitPrice || row.unitPrice:"";
     		}},
         	{field : 'totalMoney',title : '订单总金额',align : 'center', formatter:function(value, row, index){
     			return row.totalMoney?parseFloat(row.totalMoney).toFixed(2) || row.totalMoney:"";
     		}},
     		{field : 'discountMoney',title : '优惠金额',align : 'center',formatter:function(value, row, index){
     			return row.discountMoney?parseFloat(row.discountMoney).toFixed(2) || row.discountMoney:"";
     		}},
     		{field : 'realMoney',title : '实付金额',align : 'center',formatter:function(value, row, index){
     			return row.realMoney?parseFloat(row.realMoney).toFixed(2) || row.realMoney:0.00;
     		}},
     		
     		{field : 'refoundMoney',title : '已退款金额',align : 'center',formatter:function(value, row, index){
     			return row.refoundMoney?parseFloat(row.refoundMoney).toFixed(2) || row.refoundMoney:0.00;
     		}},
     		{field : 'actualMoney',title : '公司实收金额',align : 'center',formatter:function(value, row, index){
     			return row.actualMoney?parseFloat(row.actualMoney).toFixed(2) || row.actualMoney:0.00;
     		}},
     		
         	{field : 'payTime',title : '付款时间',align : 'center', formatter:function(value, row, index){
     			return row.payTime? new Date(row.payTime).format("yyyy-MM-dd hh:mm:ss"): "";
     		}},
         	{field : 'payMode',title : '支付方式',align : 'center', formatter:function(value, row, index){
         		var json = {
     				0: "闪付",
         			1: "支付宝",
         			2: "微信支付",
         		    3: "余额支付"
         		};
         		return json[row.payMode] || "未支付";
     		}},
         	{field : 'orderStatus',title : '总订单状态',align : 'center', formatter:function(value, row, index){
         		var statusJson = {
         			0: "待付款",
         			1: "已支付(待取货)",
         			2: "已取消",	
         		};
     			return statusJson[row.orderStatus] || "未知状态";
     		}},
     		
     		{field : 'orderChildStatus',title : '子订单状态',align : 'center', formatter:function(value, row, index){
         		var statusJson = {
         			0: "待付款",
         			1: "已支付(待取货)",
         			2: "已取消",
         			3: "已取货(待评论)",
         			4: "取货超时",
         			5: "待退款",
         			6: "交易关闭",
         			7: "完成",
         			8: "退款失败",
         			9: "退款成功",
         			10:"设备故障",
         		};
     			return statusJson[row.orderStatus] || "未知状态";
     		}},
     		{field : 'orderType',title : '订单类型',align : 'center', formatter:function(value, row, index){
         		var statusJson = {
         			0: "预订",
         			1: "零售",
         		};
     			return statusJson[row.orderType] || "未知类型";
     		}}
     		/*{field : 'backNo',title : '加盟商开户行',align : 'center'},
     		{field : 'cardName',title : '加盟商开户名称',align : 'center'},
         	{field : 'cardNo',title : '加盟商账号',align : 'center'}*/
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
                    url:"statistics/paymentStatement/findOrderChild?orderNum="+row.orderNum,
                    fitColumns:true,
                    singleSelect:true,
                    loadMsg:"正在加载,请稍后...",
                    width: 1600,
                    //height:'auto',
                    autoRowHeight:true,
                    columns:[[{
                        field:'childNum',
                        title:'子订单号',
                        width:100,
                        align : 'center'
                    },{
                        field:'identifyingCode',
                        title:'取货验证码',
                        width:50,
                        align : 'center'
                    },{
                        field:'beginTime',
                        title:'取餐开始时间',
                        width:100,
                        align : 'center',
                        formatter:function(value, row, index){
                 			return row.beginTime? new Date(row.beginTime).format("yyyy-MM-dd hh:mm:ss"): "";}
                    },{
                        field:'endTime',
                        title:'取餐截止时间',
                        width:100,
                        align : 'center',
                        formatter:function(value, row, index){
                 			return row.endTime? new Date(row.endTime).format("yyyy-MM-dd hh:mm:ss"): "";}
                    },{
                        field:'checkState',
                        title:'验证状态',
                        width:50,
                        align : 'center'
                    },{
                        field:'checkTime',
                        title:'验证时间',
                        width:100,
                        align : 'center',
                        formatter:function(value, row, index){
                 			return row.checkTime? new Date(row.checkTime).format("yyyy-MM-dd hh:mm:ss"): "";}
                    },{
                        field:'isdel',
                        title:'是否删除',
                        width:50,
                        align : 'center'
                    },{
                        field:'refundMoney',
                        title:'退款金额',
                        width:50,
                        align : 'center'
                    },{
                        field:'refundTime',
                        title:'预订退款截止时间',
                        width:100,
                        align : 'center',
                        formatter:function(value, row, index){
                 			return row.refundTime? new Date(row.refundTime).format("yyyy-MM-dd hh:mm:ss"): "";}
                    }
            ]],
            view: detailview,  
     		detailFormatter:function(index,row){  
                return '<div style="padding:2px"><table class="dddv"></table></div>'; 
            },
    	            /* 根据子订单获得订单明细*/
    	            onExpandRow: function(index1,row){//子表，注意此时行索引为index1                                  
    	                var dddv = $(this).datagrid('getRowDetail',index1).find('table.dddv');
    	                dddv.datagrid({
    	                    url:"statistics/financial/findOrderDetail?orderChildNum="+row.childNum,
    	                    fitColumns:true,
    	                    singleSelect:true,
    	                    loadMsg:"正在加载,请稍后...",
    	                    width: 800,
    	                    //height:'auto',
    	                    autoRowHeight:true,
    	                    columns:[[{
    	                                field:'goodsName',
    	                                title:'名称',
    	                                width:50,
    	                                align : 'center'
    	                            },{
    	                                field:'retailPrice',
    	                                title:'单价',
    	                                width:50,
    	                                align : 'center'
    	                            },{
    	                                field:'goodsNum',
    	                                title:'数量',
    	                                width:50,
    	                                align : 'center'
    	                            },{
    	                                field:'price',
    	                                title:'金额',
    	                                width:50,
    	                                align : 'center'
    	                            }
    	                    ]],	
    	    	            onResize:function(){
    	    	            ddv.datagrid('fixDetailRowHeight',index1);
                            $('#dg').datagrid('fixDetailRowHeight',index);
    	    	            },
    	    	            onLoadSuccess:function(){
    	    	                setTimeout(function(){
    	    	                ddv.datagrid('fixDetailRowHeight',index1);//在加载成功时，获取父列表的明细高度，使其适应明显点开后的高度，注意此时的行索引为index1
                                ddv.datagrid('fixRowHeight',index1);//在加载成功时，获取子明细点开后父列表的列高，防止在超过加载成功前的高度时，出现垂直方向的滑动条
                                $('#dg').datagrid('fixDetailRowHeight',index);//在加载成功时，获取爷爷列表的明细高度，使其适应前面父列表和子列表的高度变化，注意此时的行索引为index
                                $('#dg').datagrid('fixRowHeight',index);//在加载成功时，获取父列表的明细点开后爷爷列表的高度，防止在超过加载成功前的高度时，出现垂直方向的滑动条
    	    	                },0);
    	    	            }		
    	                }//三级数据
    	                );
    	                ddv.datagrid('fixDetailRowHeight',index1);
                        ddv.datagrid('fixRowHeight',index1);
                        $('#dg').datagrid('fixDetailRowHeight',index);
                        $('#dg').datagrid('fixRowHeight',index);
    	            }//三级		
            ,	
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
	var orderNum=$.trim($('#orderNum').val());
	var modeNum =$('#modeNum').combobox('getValue');
	var mobile=$.trim($('#mobile').val());
	var startDate = $('#startDate').datetimebox('getValue') + ""; 
	var endDate = $('#endDate').datetimebox('getValue') + ""; 
	var orderStatus =$('#orderStatus').combobox('getValue');
	var orderChildStatus =$('#orderChildStatus').combobox('getValue');
	var orderType =$('#orderType').combobox('getValue');
	var payMode =$('#payMode').combobox('getValue');
//	alert(companyName+"+++"+orderNum+"+++"+modeNum+"+++"+mobile+"+++"+startDate+"+++"+endDate+"+++"+orderStatus);
	$("#dg").datagrid("reload",{
		/*search_companyId:companyId,*/
		/*search_companyName:companyName,*/
		search_orderNum:orderNum,
		search_modeNum:modeNum,
		search_mobile:mobile,
		search_startDate:startDate,		
		search_endDate:endDate,		
		search_orderStatus:orderStatus,
		search_orderChildStatus:orderChildStatus,
		search_orderType:orderType,
		search_payMode:payMode
		
	});
}

function exportOrderList(){
	var orderNum=$.trim($('#orderNum').val());
	var modeNum =$('#modeNum').combobox('getValue');
	var mobile=$.trim($('#mobile').val());
	var startDate = $('#startDate').datetimebox('getValue') + ""; 
	var endDate = $('#endDate').datetimebox('getValue') + ""; 
	var orderStatus =$('#orderStatus').combobox('getValue');
	var orderChildStatus =$('#orderChildStatus').combobox('getValue');
	var orderType =$('#orderType').combobox('getValue');
	var payMode =$('#payMode').combobox('getValue');
	location.href=urls['ctx']+"/statistics/financial/orderListExport?orderNum="+orderNum+
	    			"&modeNum="+modeNum+"&mobile="+mobile+"&startDate="+startDate+"&endDate="+endDate+
	    			"&orderStatus="+orderStatus+"&orderChildStatus="+orderChildStatus+"&orderType="+orderType+"&payMode="+payMode;/*+"&companyId="+companyId*/

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



