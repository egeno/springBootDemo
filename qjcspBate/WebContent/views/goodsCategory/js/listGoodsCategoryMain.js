	var $dg;
	var $grid;
	
	$(function() {
		 $dg = $("#dg");
		 $grid=$dg.treegrid({
			width : 'auto',
			height : $(this).height(),
			url : "goods/category/findCategory",
			rownumbers:true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			striped:true,
			border:true,
			idField: 'id',
			treeField: 'name',
			frozenColumns:[[
						    {field : 'name',title : '分类名称', align : 'left',
						          formatter:function(value){
						                   return '<span style="color:red">'+value+'</span>';
						           }
						    }
						]],
			columns : [ [ //{field:'ck',checkbox:true},
			              {field : 'pName',title : '父分类名称',align : 'center'},
			             /* {field : 'code',title : '类别编号',align : 'center'},*/
//			              {field : 'level',title : '层级',align : 'center'},
			              {field : 'status',title : '是否启用',align : 'center',
			            	  formatter:function(value,row){
			            		  if("1"==row.status)
									return "<font color=green>是<font>";
			            		  else
			            			return "<font color=red>否<font>";  
								},
								editor:{type:'checkbox',options:{on:'1',off:'0'}}
			              },
			              {field : 'creater',title : '创建人',align : 'center'},
			              {field : 'createtime',title : '创建时间',align : 'center'},
			              {field : 'modifier',title : '修改人',align : 'center'},
			              {field : 'modifytime',title : '最后修改时间',align : 'center'}
			              ] ],toolbar:'#tb'
		});
	});
	var flag=true;
	function endEdit(){
		var select = $dg.treegrid('getSelections');
		if(select){
			var nodes = $dg.treegrid('getData');
			checkedNodes(nodes);
		}
		return flag;
	}
	//遍历节点和子节点
	function checkedNodes(nodes){
		if(nodes){
			$.each(nodes,function(i,node){
				if(node){
					$dg.treegrid('endEdit', node.id);
					var temp=$dg.treegrid('validateRow', node.id);
					if(!temp){ flag= false; }
				}
				if(node.children){
					checkedNodes(node.children);
				}
			});
		}
		return flag;
	}
	
	function editNode(){
		var nodes = $dg.treegrid('getSelections');
		if(nodes==null||nodes==""){
			$.messager.alert("提示", "请选择行记录!");
		}else{
			$.each(nodes,function(i,node){
				if(node){
					$dg.treegrid('beginEdit', node.id);
				}
			});
		}
	}
	
	function removeDelNode(){
		var node = $dg.treegrid('getSelected');
		if(node){
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if (r){  
			    	$.post("goods/category/deleteCategory", {id:node.id}, function(rsp) {
						if(rsp.status){
							$dg.treegrid('remove', node.id);
						}
						parent.$.messager.show({
							title : rsp.title,
							msg : rsp.message,
							timeout : 1000 * 2
						});
					}, "JSON").error(function() {
						parent.$.messager.show({
							title :"提示",
							msg :"提交错误了！",
							timeout : 1000 * 2
						});
					});
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
	function saveNodes(){
		if(endEdit()){
			if ($dg.treegrid('getChanges').length) {
				var inserted = $dg.treegrid('getChanges', "inserted");
				var deleted = $dg.treegrid('getChanges', "deleted");
				var updated = $dg.treegrid('getChanges', "updated");
				
				var effectRow = new Object();
				if (inserted.length) {
					effectRow["inserted"] = JSON.stringify(inserted);
				}
				if (deleted.length) {
					effectRow["deleted"] = JSON.stringify(deleted);
				}
				if (updated.length) {
					effectRow["updated"] = JSON.stringify(updated);
				}
				$.post("goods/category/editGoodsCategoryDlg", effectRow, function(rsp) {
					if(rsp.status){
						$dg.datagrid('acceptChanges');
					}
					$.messager.alert(rsp.title, rsp.message);
				}, "JSON").error(function() {
					$.messager.alert("提示", "提交错误了！");
				});
			}
		}else{
			$.messager.alert("提示", "字段验证未通过!请查看");
		}
	}
	//增加并列项
	function addStandPlaceNode(){
		var temp=jqueryUtil.getRandTime();
		var node = $dg.treegrid('getSelected');
		if (node){
			$dg.treegrid('insert', {
				after: node.id,
				data: {
					id:temp,
					pid:node.pid,
					pName:node.pName,
					sort:node.sort+1,
					url:'javascript:void(0);',
					status:'add'
				}
			});
			$dg.treegrid('unselect', node.id);
			$dg.treegrid('select', temp);
			$dg.treegrid('beginEdit', temp);
		}else{
			$.messager.alert("提示", "请选择一行记录!");
		}
	}
	//增加子项
	function addSubitemNode(){
		var temp=jqueryUtil.getRandTime();
		var node = $dg.treegrid('getSelected');
		if (node){
			$dg.treegrid('insert', {
				after: node.id,
				data: {
					id:temp,
					pid:node.id,
					pName:node.name,
					sort:node.sort+1,
					url:'javascript:void(0);',
					status:'add'
				}
			});
			$dg.treegrid('unselect', node.id);
			$dg.treegrid('select', temp);
			$dg.treegrid('beginEdit', temp);
		}else{
			$.messager.alert("提示", "请选择一行记录!");
		}
	}
	//弹窗修改
	function updRowsOpenDlg() {
		var row = $dg.treegrid('getSelected');
		if (row) {
			parent.$.modalDialog({
				title : "编辑分类",
				width : 360,
				height : 240,
				href : "goods/category/editGoodsCategoryDlg",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form("load", row);
					/*parent.$.modalDialog.handler.find('#code').attr("readonly","true");*/
					
				},			
				buttons : [ {
					text : '编辑',
					iconcls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
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
		}else{
			parent.$.messager.show({
				title :"提示",
				msg :"请选择一行记录!",
				timeout : 1000 * 2
			});
		}
	}
	//弹窗增加
	function addRowsOpenDlg() {
		var row = $dg.treegrid('getSelected');
		if(row){
			if(row.status=="0"){
				parent.$.messager.show({
					title :"提示",
					msg :"请先启用!",
					timeout : 1000 * 2
				});
			}else{
				parent.$.modalDialog({
					title : "添加分类",
					width : 360,
					height : 240,
					href : "goods/category/editGoodsCategoryDlg",
					onLoad:function(){
						if(row){
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load", {"pid":row.id});
						}
					},	
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find("#form");
							f.submit();
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
		}else{
			parent.$.modalDialog({
				title : "添加分类",
				width : 360,
				height : 240,
				href : "goods/category/editGoodsCategoryDlg",
				onLoad:function(){
					if(row){
						var f = parent.$.modalDialog.handler.find("#form");
						f.form("load", {"pid":row.id});
					}
				},	
				buttons : [ {
					text : '保存',
					iconCls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find("#form");
						f.submit();
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
	}


