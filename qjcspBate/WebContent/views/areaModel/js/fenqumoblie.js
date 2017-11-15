$('.fenqumoblie').layout({
	fit:true,
});
/*数据表*/
	var modelId;
	var machineModels;
	 $grid=$('#mobliedata').datagrid({
		url:urls["ctx"] + "/area/info/findAreasPage",
		title:'分区维护',
		fit:true,
		toolbar:'#tool',
		rownumbers:true,
		singleSelect:true,
		striped:'true',
		scrollbarSize:0,/*滚动条所占的宽度*/
		fitColumns:true,
		pagination:true,/*分页*/
		/*pageSize和pageList必须一起设置*/
		pageSize:10,
		pageList:[10,20,30,40,50],
		pageNumber:1,
		beforePageText:'第',
		afterPageText:'页-共{pages}页',
		displayMsg:'当前显示{from}-{to}  共{total}条记录',
		onClickRow:function(index,row){
			$('#CheckPartition').datagrid('clearChecked');
			$('.check').find('.datagrid-btable').find('tr').hide();//隐藏所有行
			$('.check').find('.datagrid-btable').find('tr').each(function(){
				$(this).removeClass('sh');
			});
			/*$('.datagrid-header-check').remove();*/
			var a=$('#mobliedata').datagrid('getSelected');
			//重新加载设备列表的数据
			modelId=a.areaModelId;
			var rows  = $('#CheckPartition').datagrid("getRows");
			for(var i=0;i<rows.length;i++){
				if(a.areaModelType==rows[i]["modelType"]/*&&a.areaModelRow==rows[i]["machineRow"]&&a.areaModelColumn==rows[i]["machineColumn"]*/){
					$('.check').find('.datagrid-btable').find('tr').eq(i).addClass('sh');
				}
			}
			$.post('area/info/findMachineId',{'areaId':a.areaModelId},function(data){
				machineModels=data;
				//alert(data);
     			var nums=data.split(',');
				var array =[];
				var arr=[];
				var rows  = $('#CheckPartition').datagrid("getRows");
				for(var i=0;i<rows.length;i++)
				{
					var a=rows[i]['machineId'];
					//alert(a);
					array.push(a);
				}
				for(var i=0;i<nums.length;i++){
					//alert(nums.length);
					$.each(array,function(index){
						if(nums[i].substring(nums[i].indexOf('-')+1,nums[i].length)==this){
							$('#CheckPartition').datagrid('selectRow', index);
						}
					});
				}
			
			});
			$('.check').find('.datagrid-btable').find('tr').each(function(){
				if($(this).hasClass('sh')){
					$(this).show();
				}

			});
		},
		columns:[[
		    {field:'areaModelId',/*数据库需要的字段*/hidden:true,title:'分区名称',align:'center',fitColumns:true,width:60,},
		    {field:'areaModelName',/*数据库需要的字段*/title:'分区模型名称',align:'center',fitColumns:true,width:150,},
			{field:'areaModelType',/*数据库需要的字段*/title:'适用类型',align:'center',fitColumns:true,width:100,formatter: function(value,row,index){
				if (row.areaModelType==1){
					return '加热';
				} else if(row.areaModelType==0) {
					return '制冷';
				} else{
					return '冷热一体';
				}
			 }
			},
//			{field:'timeType',/*数据库需要的字段*/title:'时间段',align:'center',fitColumns:true,width:150,formatter: function(value,row,index){
//				//alert(row.timeType);
//				if (row.timeType=='0'){
//					return '早餐(8:00~11:00)';
//				} else if(row.timeType=='1'){
//					return '中餐(12:00~13:30)';
//				}else if(row.timeType=='2'){
//					return '休闲(14:30~17:00)'
//				}else if(row.timeType=='3'){
//					return '晚餐(18:00~21:00)'
//				}else{
//					return ''
//				}
//			 }
//			},
			/*{field:'areaModelRow',数据库需要的字段title:'适用行数',align:'center',fitColumns:true,width:250,},
			{field:'areaModelColumn',数据库需要的字段title:'适用列数',align:'center',fitColumns:true,width:120,},*/
			{field:'areaModelMemo',/*数据库需要的字段*/title:'描述',align:'center',fitColumns:true,width:180,},
			{field:'areaModelStatus',/*数据库需要的字段*/title:'状态',align:'center',fitColumns:true,width:250,formatter: function(value,row,index){
				if (row.areaModelStatus==1){
					return '启动';
				} else {
					return '停止';
				}
			 }},
		]],
		/*数据表格头部菜单*/
		onHeaderContextMenu: function(e, field){
			e.preventDefault();
			if (!cmenu){
				createColumnMenu();
			}
			cmenu.menu('show', {
				left:e.pageX,
				top:e.pageY
			});
		}

	});
	/*数据表格头部菜单*/
	var cmenu;
	function createColumnMenu(){
		cmenu = $('.tablemenu').appendTo('body');
		cmenu.menu({
		onClick: function(item){
			if (item.iconCls == 'icon-ok'){
				$('#mobliedata').datagrid('hideColumn', item.name);
				cmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-empty'
				});
			} else {
				$('#mobliedata').datagrid('showColumn', item.name);
				cmenu.menu('setIcon', {
					target: item.target,
					iconCls: 'icon-ok'
				});
			}
		}
	});
	var fields = $('#Partition').datagrid('getColumnFields');
	for(var i=0; i<fields.length; i++){
		var field = fields[i];
		var col = $('#Partition').datagrid('getColumnOption', field);
		cmenu.menu('appendItem', {
			text: col.title,
			name: field,
			iconCls: 'icon-ok'
		});
	}
	}
/*重置样式*/
	$(function(){
	$('#mobliedata').datagrid('resize');
	$('#CheckPartition').datagrid('resize');
	$('.fenqumoblie').layout('panel', 'west').panel('resize');
	$('.fenqumoblie').layout('panel', 'center').panel('resize');
	});
/*数据表*/
	$cgrid=$('#CheckPartition').datagrid({
		url:urls["ctx"] + "/area/info/findMachinePage",/*表格数据页*/
		title:'分区',
		toolbar:'#checktool',
		fitColumns:true,/*自适应*/
		scrollbarSize:0,/*滚动条所占的宽度*/
		fit:true,
		checkOnSelect:true,
		onClickRow: function (rowIndex, rowData) {
		    $(this).datagrid('unselectRow', rowIndex);
		},
		columns:[[
			{title:'编号',sortable:true,checkbox:true,width:100,},
			{field:'machineId',hidden:true,width:100,title:'设备名称',align:"center",},
			{field:'machineName',width:100,title:'设备名称',align:"center",},
			{field:'address',width:100,title:'地址',align:"center",},
			{field:'modelType',hidden:true,width:100,title:'机器模式',align:"center",},
			{field:'machineRow',hidden:true,width:100,title:'行数',align:"center",},
			{field:'machineColumn',hidden:true,width:100,title:'列数',align:"center",},
			]],
		/*假数据*/

	});
	function addnew(){
		$dialog = $('.addcontent').dialog({ 
			title : "新增分区",
			width : 400,
			height : 300,
			closed: true,     
	        cache: false,
	        modal : true,
			onLoad:function(){/*对话框打开时*/
				$('#name').val("赋值");
			},			
			buttons : [ {
				text : '保存',
				iconcls : 'icon-save',
				handler : function() {
					parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find("#form");
					f.submit();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					// $dialog.dialog('destroy');
					$dialog.dialog('close');
				}
			}
			]
		});
		$dialog.dialog('open');
	} 
	function eadit(){
		$dialog = $('.editcontent').dialog({ 
			title : "修改分区",
			width : 400,
			height : 300,
			closed: true,     
	        cache: false,
	        modal : true,
			onLoad:function(){/*对话框打开时*/
				$('#name').val("赋值");
			},			
			buttons : [ {
				text : '保存',
				iconcls : 'icon-save',
				handler : function() {
					parent.$.modalDialog.openner= $grid;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find("#form");
					f.submit();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					// $dialog.dialog('destroy');
					$dialog.dialog('close');
				}
			}
			]
		});
		$dialog.dialog('open');
	}