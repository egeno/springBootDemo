

/*$(function(){
	$('#dg').datagrid({
		url:"join/weixin/findWeiXinList",
		width:'auto',
		title:'微信加盟管理',
		fit:true,
		toolbar:'#tool',
		rownumbers:true,
		singleSelect:true,
		striped:'true',
		scrollbarSize:0,滚动条所占的宽度
		fitColumns:true,
		pagination:true,分页
		pageSize:10,
		pageList:[10,20,30,40,50],
		pageNumber:1,
		beforePageText:'第',
		afterPageText:'页-共{pages}页',
		displayMsg:'当前显示{from}-{to}  共{total}条记录',
		queryParams:{
        	id:$("#companyId").combobox('getValue'), 
		},
		
		columns: [  
	        [
	        {field:'name',数据库需要的字段title:'加盟人名称',align:'center',width:100,fitColumns: true,},
	        {field:'telPhone',数据库需要的字段title:'加盟人手机号',align:'center',width:100,fitColumns: true,},
	        {field:'content',数据库需要的字段title:'申请内容',align:'center',width:100,fitColumns: true,},
			
			]
        ],
          

	});
});
重置样式
$(function(){
	$('#dg').datagrid('resize');
	$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
});

function searchfo(){
	var name=$.trim($("#name").val());
	var telPhone=$.trim($("#telPhone").val());
	
	$("#dg").datagrid("load",{
		name:name,
		telPhone:telPhone
	});
}
*/



var $dg;
		var $grid ;
		
		$(function(){
			 $dg = $("#dg");
			 $grid=$dg.datagrid({
				width : 'auto',
				height : $(this).height(),			
				url:"join/weixin/findWinxinLeague",
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
                              {field : 'id',title : '客户id编号',align : 'center'},
				              {field : 'name',title : '客户名称',align : 'center'},				          
				              {field : 'telPhone',title : '客户电话',align : 'center'},
				              {field : 'content',title : '申请内容',align : 'center'},				             
				              ] ],toolbar:'#tb'
			});
		});	 
		
		
		/*重置样式*/
		$(function(){
			$('#dg').datagrid('resize');
			$('.datagrid-cell-group').eq(0).css({'text-align':'center'});
		});
		function searchfo(){
			var name=$.trim($("#name").val());
			var telPhone=$.trim($("#telPhone").val());			
			$("#dg").datagrid("load",{
				name:name,
				telPhone:telPhone				
			});
		}

