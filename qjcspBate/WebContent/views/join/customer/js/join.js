      var $dg;
		var $grid ;
		
		$(function(){
			 $dg = $("#dg");
			 $grid=$dg.datagrid({
				width : 'auto',
				height : $(this).height(),			
				url:"join/customer/findCustomer",
				queryParams:{},
				rownumbers:true,
				fit:false,
				fitColumns:true,
				pagination:true,
				pageSize:10,
				pageList:[ 10, 20, 30, 40, 50 ],
				border:false,
				singleSelect:true,
				striped:true,
				cache:false,
				nowrap:true,
				loadMsg:"正在加载,请稍后...",
				onLoadSuccess : function(data){
				},
				columns : [ [
                              {field : 'customerId',title : '客户id编号',align : 'center'},
				              {field : 'customerName',title : '客户名称',align : 'center'},
				              {field : 'customerMobile',title : '客户手机号',align : 'center'},
				              {field : 'telephone',title : '客户电话',align : 'center'},
				              {field : 'region',title : '地区',align : 'center'},
				              {field : 'detailAddress',title : '详细地址',align : 'center'},	
				              {field : 'createTime',title : '创建时间',align : 'center'},
				              ] ],toolbar:'#tb'
			});
		});	 
		
		
		/*重置样式*/
		$(function(){
			$('#dg').datagrid('resize');
			$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
		});
		function searchfo(){
			var customerName=$.trim($("#name").val());
			var telephone=$.trim($("#telPhone").val());			
			$("#dg").datagrid("load",{
				customerName:customerName,
				telephone:telephone				
			});
		}
		
		/**
		 * 导出Excel
		 */
		function exportList(){
			var customerName=$.trim($("#name").val());
			var telephone=$.trim($("#telPhone").val());	
			location.href=urls['ctx']+"/join/customer/JoinCustomerListExport?customerName="+customerName+"&telephone="+telephone;

		}	
		
		
		
/*function add(){
			
			parent.$.modalDialog({
				title : "添加版本",
				width : 650,
				height : 500,
				href: "version/android/addAndroidVersion",
				
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : function(){
						parent.$.modalDialog.openner= $grid;
						var f = parent.$.modalDialog.handler.find("#addForm");
						f.submit();
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						parent.$.messager.progress('close');
						parent.$.modalDialog.handler.dialog('close');
						parent.$.modalDialog.openner=undefined ;
					}
				}
				]
			}) ;
		}*/

	

