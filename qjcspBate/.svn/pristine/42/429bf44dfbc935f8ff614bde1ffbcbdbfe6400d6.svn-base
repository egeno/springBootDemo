
		
		var $dg;
		var $temp;
		var $grid;
		$(function() {
			$dg = $("#dg");
			$grid=$dg.datagrid({
				url : "luckyMoney/findLuckyMoney",
				width : 'auto',
				height :  $(this).height(),
				pagination:true,
				rownumbers:true,
				border:false,
				singleSelect:true,
				striped:true,
		columns : [ [ {field : 'customerMobile',title : '手机号',align : 'center',editor : "text"},
		              {field : 'luckyMoney',title : '红包金额',width : parseInt($(this).width()*0.1),align : 'center',editor : "text"},
		              {field:'validStartDate',title:'红包有效开始时间',align:'center',width:120,formatter:function(value, row, index){
		       			return new Date(row.validStartDate).format("yyyy-MM-dd hh:mm:ss");
		       		}},
		              {field : 'validEndDate',title : '红包有效截止时间',width : 120,align : 'center',formatter:function(value, row, index){
			       			return new Date(row.validEndDate).format("yyyy-MM-dd hh:mm:ss");
			       		}},
		              ] ],toolbar:'#tb'
	});
});
/* 添加红包 */
function addlucky() {
	
	parent.$.modalDialog({
		title : "添加红包",
		width : 390,
		height : 255,
		href : "luckyMoney/addlucky",
		buttons : [ {
			text : '保存',
			iconCls : 'icon-ok',
			handler : function() {
				var str = parent.$("select option:checked").text(); // 下拉框选中项目的显示值
				parent.$.messager.confirm('提示框', '奖励该用户的是'+'  "'+str+'"',function(r){
					if(r)
					{
						parent.$.modalDialog.openner= $grid;
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
					}
				});
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				parent.$.modalDialog.handler.dialog('destroy');
				parent.$.modalDialog.handler = undefined;
			}
		}
		]
	});
		}
//查询
function searchOrderList(){
	var mobile=$.trim($('#mobile').val());
	$("#dg").datagrid("reload",{
		search_customerMobile:mobile,
	});
	
	
}