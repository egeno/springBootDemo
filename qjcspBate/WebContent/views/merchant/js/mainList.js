	var $dg;
	var $grid;
	
	$(function() {
		 $dg = $("#dg");
		 $grid=$dg.treegrid({
			width : 'auto',
			height : $(this).height(),
			url : "merchant/findAllMainList",
			rownumbers:true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			striped:true,
			border:true,
			idField: 'id',
			treeField: 'companyName',
			frozenColumns:[[
						    {field : 'companyName',title : '公司名称', align : 'left',
						          formatter:function(value){
						                   return '<span style="color:red">'+value+'</span>';
						           }
						    }
						]],
			columns : [ [ 
			               {field : 'companyAlias',title : '公司简称',align : 'center'},
			              {field : 'companyPrefix',title : '公司前缀',align : 'center'},
			            /*  {field : 'companyCode',title : '公司编号',align : 'center'},*/
			              {field : 'businessHours',title : '经营时间',align : 'center'},
			              {field : 'companyStatus',title : '是否启用',align : 'center',
			            	  formatter:function(value,row){
			            		  if("1"==row.companyStatus){
									return "<font color=green>是<font>";
			            		  }
			            		  else{
			            			return "<font color=red>否<font>";
			            		  }
								},
								editor:{type:'checkbox',options:{on:'1',off:'0'}}
			              },
			              {field : 'corporation',title : '法人',align : 'center'},
			              {field : 'cardNo',title : '账户',align : 'center'},
			              {field : 'backNo',title : '开户行',align : 'center'},
			              {field : 'backName',title : '支行',align : 'center'},
			              {field : 'cardName',title : '户名',align : 'center'},
			              {field : 'tel',title : '电话',align : 'center'},
			              {field : 'province',title : '省份',align : 'center'},
			              {field : 'cityName',title : '城市',align : 'center'},
			              /*{field : 'areaName',title : '区县',align : 'center'},*/
			              {field : 'address',title : '地址',align : 'center'},
			              {field : 'longitude',title : '经度',align : 'center'},
			              {field : 'latitude',title : '纬度',align : 'center'}
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
			    	$.post("merchant/delMerchant", {id:node.id}, function(rsp) {
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
				$.post("merchant/editMainListDlg", effectRow, function(rsp) {
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
					pName:node.companyName,
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
				title : "编辑商户",
				width : 700,
				height : 450,
				href : "merchant/editMainListDlg",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form("load", row);
					localStorage.setItem("companyName", row.companyName);
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
			if(row != null){
				parent.$.messager.show({
					title :"提示",
					msg :"不能添加子分类！",
					timeout : 1000 * 2
				});
			}else{
				parent.$.modalDialog({
					title : "添加商户",
					width : 700,
					height : 450,
					href : "merchant/editMainListDlg",
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
				title : "添加商户",
				width : 700,
				height : 450,
				href : "merchant/editMainListDlg",
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