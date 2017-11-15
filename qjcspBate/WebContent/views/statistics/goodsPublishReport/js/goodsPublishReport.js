var $dg;
var $grid;
var psdate;

$(function() {
	$("#panel").panel({
		   width:'auto',   
		   height:$(this).height()  
	});
	$dg = $("#dg");
	$dg.datagrid({
		url :  "collection/findGoodsPublishReport",
		queryParams:{
			'psdate':$('#psdate').datebox('getValue')
		},
		width : 'auto',
		height :  $(this).height(),
		border:false,
		singleSelect:true,
		striped:true,
		fit:false,
		fitColumns:true,


		cache:false,
		onLoadSuccess:function(data){
			var cnum = 0;
			for(var i=0; i<data.rowspan.length; i++){
				$dg.datagrid('mergeCells',{
					index:cnum,
					field:'goodsId',
					rowspan:data.rowspan[i]
				});
				cnum = cnum + data.rowspan[i];
			}
			var num=0;
			for(var i=0; i<data.rowspan.length; i++){
				$dg.datagrid('mergeCells',{
					index:num,
					field:'goodsName',
					rowspan:data.rowspan[i]
				});
				num = num + data.rowspan[i];
			}
		},
		columns : [ [ 
		             {field : 'goodsId',title : '商品ID',width : parseInt($(this).width()*0.25),align : 'center',editor : "text"},
		             {field : 'goodsName',title : '商品名称',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
					{field : 'areaModelName',title : '模型',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
					{field : 'publishNum',title : '发布数量',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"},
		             {field : 'publishTime',title : '发布时间',width : parseInt($(this).width()*0.12),align : 'center',editor : "text"}
		             ] ],toolbar:'#tb'
	});
	$(".datebox :text").attr("readonly","readonly");
});

function getData(){
	psdate = $('#psdate').datebox('getValue');

	$("#dg").datagrid("reload",{
		psdate:psdate
	});
}

function exportExcel(){
	psdate = $('#psdate').datebox('getValue');

	location.href=urls['ctx']+"/collection/goodsPublishExport?psdate="+psdate;
}