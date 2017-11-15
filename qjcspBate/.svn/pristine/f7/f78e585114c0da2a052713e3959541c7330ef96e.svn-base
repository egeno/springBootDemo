$(function(){
	$('#Partition').datagrid({
		url:"evaluatereviewed/findAllevaluatereviewedList",
		width:'auto',
		title:'评价内容审核管理',
		fit:true,
		toolbar:'#tool',
		rownumbers:true,
		singleSelect:false,
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
		/*queryParams:{
        	companyId:$("#companyId").val(),       
		},*/
		columns: [  
	        [
	         {field:'evaluateId',/*数据库需要的字段*/title:'评价单Id',align : 'center',editor : "text",hidden:true,},
	        {field:'customerName',/*数据库需要的字段*/title:'客户名称',align : 'center',editor : "text"},
	        {field:'customerPhone',/*数据库需要的字段*/title:'客户手机号码',align : 'center',editor : "text"},
	        {field:'goodsName',/*数据库需要的字段*/title:'菜品名称',align : 'center',editor : "text"},
			{field:'goodsEvaluateContent',/*数据库需要的字段*/title:'评价内容',align : 'center',editor : "text"},
			{field:'createTime',/*数据库需要的字段*/title:'评价时间',align : 'center',editor : "text",
				},
			{field:'isverify',/*数据库需要的字段*/title:'审核状态',align : 'center',editor : "text",
				formatter:function(value,row){
					if(row.isverify == 'C'){						
						return "未通过";				
					}else if(row.isverify=='Y'){
						return "已通过";
					}else{
						return "待审核";
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
function searchfo(){
	//评论开始时间
	var evaluateStartTime=$('#evaluateStartTime').datebox('getValue');
	//评论结束时间
	var evaluateEndTime=$('#evaluateEndTime').datebox('getValue');
	//审核状态
	//var verifyStatus=$("input[type='radio']:checked").val();
	var verifyStatus=$('#verifyStatus').combobox('getValue');
	$("#Partition").datagrid("load",{
		evaluateStartTime:evaluateStartTime,
		evaluateEndTime:evaluateEndTime,
		verifyStatus:verifyStatus
	});
}

/*审核通过*/
function pass(){
	var row = $('#Partition').datagrid('getSelections');
	if(row.length>0){
		var i = 0;  
		var arrayObj =[];
        for(i;i<row.length;i++){  
        	/*if(row[i].isverify!='N'){
    			parent.$.messager.show({
    				title :"提示",
    				msg :"选中的有已经审核的,不需要重复提交!",
    				timeout : 1000 * 2
    			});
    			return ;
    		}*/
            arrayObj.push(row[i].evaluateId);
        } 
		parent.$.messager.confirm("提示","确定要通过吗?",function(r){
		    if (r){ 
		    	 $.ajax({
			     		url : 'evaluatereviewed/evaluatereviewedPass',
			     		type : 'POST',
			     		dataType : 'json',
						contentType : 'application/json;charset=utf-8', 
			     		data : JSON.stringify(arrayObj) ,
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
			msg :"请至少选择一行记录!",
			timeout : 1000 * 2
		});
	}
	
}
/*审核不通过*/
function out(){
	var row = $('#Partition').datagrid('getSelections');
	if(row.length>0){
		var i = 0;  
		var arrayObj =[];
        for(i;i<row.length;i++){  
        	/*if(row[i].isverify!='N'){
    			parent.$.messager.show({
    				title :"提示",
    				msg :"选中的有已经审核的,不需要重复提交!",
    				timeout : 1000 * 2
    			});
    			return ;
    		}*/
            arrayObj.push(row[i].evaluateId);
        } 
		parent.$.messager.confirm("提示","确定不通过吗?",function(r){
		    if (r){ 
		    	 $.ajax({
			     		url : 'evaluatereviewed/evaluatereviewedOut',
			     		type : 'POST',
			     		dataType : 'json',
						contentType : 'application/json;charset=utf-8', 
			     		data : JSON.stringify(arrayObj) ,
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
			msg :"请至少选择一行记录!",
			timeout : 1000 * 2
		});
	}
	
}

/*导出*/
function excel(){
	//评论开始时间
	var evaluateStartTime=$('#evaluateStartTime').datebox('getValue');
	//评论结束时间
	var evaluateEndTime=$('#evaluateEndTime').datebox('getValue');
	//审核状态
	var verifyStatus=$("input[type='radio']:checked").val();
	location.href=urls['ctx']+"/evaluatereviewed/evaluatereviewedExport?evaluateStartTime="+evaluateStartTime+"&evaluateEndTime="+evaluateEndTime+"&verifyStatus="+verifyStatus;
}