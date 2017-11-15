	var $dg;
	var $grid;
	
	$(function() {
		 $dg = $("#dg");
		 $grid=$dg.datagrid({
			url : "crowdsourced/findAllMainList",
			width : 'auto',
			height :  $(this).height(),
			pagination:true,
			rownumbers:true,
			border:false,
			fit:false,
			nowrap:true,
			fitColumns: true,
			singleSelect:true,
			striped:true,
			
			columns : [ [{field : 'universityid',title : '众包商id', align : 'center'}, 
			             {field : 'universityname',title : '区域名称',width:50,fitColumns: true, align : 'center'},
			               {field : 'zonename',title : '区/县',width:50,fitColumns: true,align : 'center'},
			              {field : 'spellall',title : '区域全拼',width:50,fitColumns: true,align : 'center'},
			              {field : 'spellshort',title : '区域简拼',width:50,fitColumns: true,align : 'center'},
			              {field : 'provCode',title : '省份id',align : 'center'},
			              {field : 'province',title : '省份',width:50,fitColumns: true,align : 'center'},
			              {field : 'cityCode',title : '城市id',align : 'center'},
			              {field : 'cityname',title : '城市',width:50,fitColumns: true,align : 'center'},
			              {field : 'longitude',title : '经度',width:100,fitColumns: true,align : 'center'},
			              {field : 'latitude',title : '纬度',width:100,fitColumns: true,align : 'center'}
			              ] ],toolbar:'#tb'
		});
		 $dg.datagrid('hideColumn','universityid');
		 $dg.datagrid('hideColumn','provCode');
		 $dg.datagrid('hideColumn','cityCode');
		 
	});
	var flag=true;
	function endEdit(){
		var select = $dg.datagrid('getSelections');
		if(select){
			var nodes = $dg.datagrid('getData');
			checkedNodes(nodes);
		}
		return flag;
	}
	//遍历节点和子节点
	function checkedNodes(nodes){
		if(nodes){
			$.each(nodes,function(i,node){
				if(node){
					$dg.datagrid('endEdit', node.id);
					var temp=$dg.datagrid('validateRow', node.id);
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
		var nodes = $dg.datagrid('getSelections');
		if(nodes==null||nodes==""){
			$.messager.alert("提示", "请选择行记录!");
		}else{
			$.each(nodes,function(i,node){
				if(node){
					$dg.datagrid('beginEdit', node.id);
				}
			});
		}
	}
	
	function removeDelNode(){
		var node = $dg.datagrid('getSelected');
		if(node){
			parent.$.messager.confirm("提示","确定要删除记录吗?",function(r){  
			    if (r){  
			    	$.post("crowdsourced/delcrowdsourced", {id:node.universityid}, function(rsp) {
						if(rsp.status){
							$dg.datagrid('reload');
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
			if ($dg.datagrid('getChanges').length) {
				var inserted = $dg.datagrid('getChanges', "inserted");
				var deleted = $dg.datagrid('getChanges', "deleted");
				var updated = $dg.datagrid('getChanges', "updated");
				
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
				$.post("crowdsourced/editMainListDlg", effectRow, function(rsp) {
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
		var node = $dg.datagrid('getSelected');
		if (node){
			$dg.datagrid('insert', {
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
			$dg.datagrid('unselect', node.id);
			$dg.datagrid('select', temp);
			$dg.datagrid('beginEdit', temp);
		}else{
			$.messager.alert("提示", "请选择一行记录!");
		}
	}
	//增加子项
	function addSubitemNode(){
		var temp=jqueryUtil.getRandTime();
		var node = $dg.datagrid('getSelected');
		if (node){
			$dg.datagrid('insert', {
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
			$dg.datagrid('unselect', node.id);
			$dg.datagrid('select', temp);
			$dg.datagrid('beginEdit', temp);
		}else{
			$.messager.alert("提示", "请选择一行记录!");
		}
	}
	//弹窗修改
	function updRowsOpenDlg() {
		var row = $dg.datagrid('getSelected');
		if (row) {
			parent.$.modalDialog({
				title : "编辑众包商",
				width : 700,
				height : 450,
				href : "crowdsourced/editMainListDlg",
				onLoad:function(){
					var f = parent.$.modalDialog.handler.find("#form");
					f.form("load", row);
					localStorage.setItem("companyname", row.companyname);
				},			
				buttons : [ {
					text : '编辑',
					iconcls : 'icon-ok',
					handler : function() {
						parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个datagrid，所以先预定义好
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
		var row = $dg.datagrid('getSelected');
		if(row){
			if(row.companyStatus=="0"){
				parent.$.messager.show({
					title :"提示",
					msg :"请先启用!",
					timeout : 1000 * 2
				});
			}else{
				parent.$.modalDialog({
					title : "添加众包商",
					width : 700,
					height : 450,
					href : "crowdsourced/editMainListDlg",
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
							parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个datagrid，所以先预定义好
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
				title : "添加众包商",
				width : 700,
				height : 450,
				href : "crowdsourced/editMainListDlg",
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
						parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个datagrid，所以先预定义好
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