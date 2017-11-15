      var $dg;
		var $grid ;
		
		$(function(){
			 $dg = $("#dg");
			 $grid=$dg.datagrid({
				width : 'auto',
				height : $(this).height(),			
				url:"join/joininfo/findJoininfo",
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
                              {field : 'id',title : '客户编号',align : 'center'},
				              {field : 'applicant',title : '申请人',align : 'center'},
				              {field : 'applicantTel',title : '申请人电话',align : 'center'},
				              
				         /*     {field : 'joinType',title : '加盟类型',align : 'center'},*/
				              
				              {field : 'joinType',title : '加盟类型',align : 'center', formatter:function(value, row, index){
				           		var json = {
				       				0: "公司",
				           			1: "租赁",
				           			2: "合作",
				           		    3: "区域代理",
				           		    4: "商户接入",
				           		    5: "众包"
				           		};
				           		return json[row.joinType] || "未知";
				       		}},
		
				              {field : 'content',title : '内容',align : 'center'},
				              ] ],toolbar:'#tb'
			});
		});	 
		
		
		/*重置样式*/
		$(function(){
			$('#dg').datagrid('resize');
			$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
		});
		function searchfo(){
			var applicant=$.trim($("#name").val());
			var applicantTel=$.trim($("#telPhone").val());			
			$("#dg").datagrid("load",{
				applicant:applicant,
				applicantTel:applicantTel				
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
		
		
		


