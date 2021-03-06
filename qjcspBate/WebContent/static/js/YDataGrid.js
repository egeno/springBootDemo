var YDataGrid = function(config){
		config = config || {};
		var dataGrid = config.dataGrid || {};
		//Actions
		var actionUrl =  config.action || {};
		var ids = config.ids || {};
		
		var Action = {
			'save': actionUrl.save ||'save.do',
			'getId': actionUrl.getId||'getId.do',
			'remove': actionUrl.remove||'delete.do'
		};
		
		//Grid DataList
		var gridId = ids.gridId ||"data-list";
		var Grid = $('#'+gridId);
		//Form
		var searchFormId = ids.searchFormId ||"searchForm";
		var listFormId = ids.listFormId || "listForm";
		var editFormId = ids.editFormId || "editForm";
		var Form = {
					search:$("#"+searchFormId),
					list:$("#"+listFormId),
					edit:$("#"+editFormId)
					};
		//Win 窗口
		var editWinId = ids.editWinId || "edit-win";
		var Win = { edit:$("#"+editWinId)};
		
		
		
		//处理函数
		var Handler = {
			//serach 查询事件
			search: function(callback){
				Events.refresh();
				//回调函数
				if(jQuery.isFunction(callback)){
					callback();
				}
				return false;	
			},
			//add按钮事件
			add: function(callback){
				Win.edit.dialog('open');
				//各浏览器$(window).height()不一样，先写死，暂时兼容ie\firefox\chrome
				$(".window:first").attr("style",$(".window:first").attr("style")+"top:100px;left:150px");
				$(".window-shadow:first").attr("style",$(".window-shadow:first").attr("style")+"top:100px;left:150px");
				
				Form.edit.resetForm();
				//回调函数
				if(jQuery.isFunction(callback)){
					callback();
				}
			},
			//edit 按钮事件
			edit:  function(callback){
				var record = Utils.getCheckedRows();
				if (Utils.checkSelectOne(record)){
					var data ={};
					var idKey = dataGrid.idField || 'id'; //主键名称
 					data[idKey] = (record[0][idKey]);
					artup.getById(Action.getId,data,function(result){
						Form.edit.form('load',result.data);
						Win.edit.dialog('open'); 
						//各浏览器$(window).height()不一样，先写死，暂时兼容ie\firefox\chrome
						$(".window:first").attr("style",$(".window:first").attr("style")+"top:100px;left:150px");
						$(".window-shadow:first").attr("style",$(".window-shadow:first").attr("style")+"top:100px;left:150px");
						
						//回调函数
						if(jQuery.isFunction(callback)){
							callback(result);
						}
					});
				}
			},
			//刷新Grid 数据 
			refresh: function(callback){
				var param = Form.search.serializeObject();
				var opt = Grid.datagrid("options");
				opt.pageNumber = 1;
				Grid.datagrid('reload',param);
				var p = Grid.datagrid('getPager');
				$(p).pagination('refresh',{
					pageNumber: 1
				});
				//回调函数
				if(jQuery.isFunction(callback)){
					callback();
				}
			},
			//删除记录
			remove: function(callback){
				var records = Utils.getCheckedRows();
				if (Utils.checkSelect(records)){
					$.messager.confirm("提示","确定删除?",function(r){  
					    if (r){
					    	var idKey = dataGrid.idField || 'id'; //主键名称
					    	var  data = $("input[name='"+idKey+"']", Form.list ).fieldSerialize(); //序列化字段
					   		artup.deleteForm(Action.remove,data,function(result){
								Events.refresh();
								//回调函数
								if(jQuery.isFunction(callback)){
									callback(result);
								}
							});
					    }  
					});
				}
			},//保存调用方法
			save: function(callback){
				if(Form.edit.form('validate') ){
					Form.edit.attr('action',Action.save);
					var parentId =$('#search_parentId').val();
					$("#edit_parentId").val(parentId);
					artup.saveForm(Form.edit,function(data){
							 Win.edit.dialog('close');
						     Events.refresh();
						     Form.edit.resetForm();
						     //回调函数
							if(jQuery.isFunction(callback)){
								callback(data);
							}
					});
				 }
			},
			//关闭按钮事件
			close : function (callback){
				$.messager.confirm("提示","确定关闭",function(r){  
				    if (r){  
				     	Win.edit.dialog('close');
				     	//回调函数
						if(jQuery.isFunction(callback)){
							callback(data);
						}
				    }
				});
			}
		};
		
		//Grid 工具类
		var Utils = {
			getCheckedRows : function(){
				return Grid.datagrid('getChecked');			
			},
			checkSelect : function(rows){//检查grid是否有勾选的行, 有返回 true,没有返回true
				var records =  rows;
				if(records && records.length > 0){
					return true;
				}
				artup.alert("警告","未选中记录",'warning');  
				return false;
				
			},
			checkSelectOne : function(rows){//检查grid是否只勾选了一行,是返回 true,否返回true
				var records = rows;
				if(!Utils.checkSelect(records)){
					return false;
				}
				if(records.length == 1){
					return true;
				}
				artup.alert("警告","只能选择一行记录",'warning');  
				return false;
			}
		};
		
		
		//自定义事件
		var evt= config.event || {};
		
		
		//默认事件
		var Events ={
			//serach 查询事件
			search: evt.search || Handler.search,
			//add按钮事件
			add: evt.add || Handler.add,
			//edit 按钮事件
			edit: evt.edit || Handler.edit,
			//刷新Grid 数据
			refresh: evt.refresh || Handler.refresh,
			//删除记录
			remove: evt.remove || Handler.remove,
			//保存调用方法
			save: evt.save || Handler.save,
			//关闭按钮事件
			close : evt.close ||  Handler.close
		};
		
		//按钮控制 btnType 用来控制按钮是否显示,后台根据授权控制是否显示
		var bar_add ={	
						id:'btnadd',
						text: '新增',
						iconCls:'icon-add',
						btnType:'add',
						handler: Events.add
					 };
		var bar_edit = {
							id:'btnedit',
							text:'编辑',
							iconCls:'icon-edit',
							btnType:'edit',
							handler: Events.edit
						};
		var bar_remove = { id:'btnremove',
						text:'删除',
						iconCls:'icon-remove',
						btnType:'remove',
						handler:Events.remove
					   };
		var toolbarConfig = [bar_add,bar_edit,bar_remove];
		var getToolbar = function (){
			var tbars = [];
			if (dataGrid.toolbar != undefined && dataGrid.toolbar.length > 0) {
				for ( var i = 0; i < dataGrid.toolbar.length; i++) {
					var bar = dataGrid.toolbar[i];
					if(!bar){
						continue;
					}
					if(bar.btnType=='add'){
						tbars.push({id:bar.id || bar_add.id,text:bar.text || bar_add.text ,iconCls: bar.iconCls || bar_add.iconCls, btnType: bar.btnType || bar_add.btnType,handler:bar.handler || bar_add.handler});
						continue;
					}
					if(bar.btnType=='edit'){
						tbars.push({id:bar.id || bar_edit.id,text:bar.text || bar_edit.text ,iconCls: bar.iconCls || bar_edit.iconCls,btnType: bar.btnType || bar_edit.btnType,handler:bar.handler || bar_edit.handler});
						continue;
					}
					if(bar.btnType=='remove'){
						tbars.push({id:bar.id || bar_remove.id,text:bar.text || bar_remove.text ,iconCls: bar.iconCls || bar_remove.iconCls,btnType: bar.btnType || bar_remove.btnType,handler:bar.handler || bar_remove.handler});
						continue;
					}
					tbars.push({id:bar.id,text:bar.text,iconCls:bar.iconCls,btnType: bar.btnType,handler:bar.handler,disabled:bar.disabled});
				}
			}else{
				tbars = toolbarConfig;
			}
			return tbars;
		};
		
		
	
		//初始化表格
		var initGrid = function(config){
			if(dataGrid.pagination ==null || dataGrid.pagination==undefined){
				dataGrid.pagination = true;
			}
			
			var dataconfig = {
				title: dataGrid.title || "数据列表",
				iconCls: dataGrid.iconCls || 'icon-save',
		//		height: dataGrid.height || 398,
				nowrap: true,
				autoRowHeight: true,
				striped: true,
				collapsible:true,
				remoteSort: false,
				pagination: dataGrid.pagination,
				rownumbers: dataGrid.rownumbers ||false,
				fitColumns:true,
				singleSelect:dataGrid.singleSelect || false,
				checkOnSelect:false,
				selectOnCheck:false,
				queryParams: Form.search.serializeObject(),
				sortName : dataGrid.sortName || 'id',
	   			sortOrder : dataGrid.sortOrder || 'asc',
				url: dataGrid.url,
				method: dataGrid.method || 'post',
				loadMsg: dataGrid.loadMsg || "正在加载……",
				idField: dataGrid.idField,
				columns: dataGrid.columns,
				toolbar: config.toolbar,
			    //数据加载前回调方法
				onBeforeLoad:function(){
					return  Form.search.form('validate');
				},
				//数据加载成功回调方法
				onLoadSuccess: dataGrid.onLoadSuccess || function(){
					Grid.datagrid('unselectAll');
					Grid.datagrid('uncheckAll');
				},
				//数据过滤
				loadFilter: dataGrid.loadFilter || function(data){
					return data;
				},
				
				onSelect: function(rowIndex, rowData){
					//选择一行
					var rows = Grid.datagrid('getRows');
					$.each(rows,function(i){
						if(i != rowIndex){
							Grid.datagrid('uncheckRow',i);
							Grid.datagrid('unselectRow',i);
						}
					});
					Grid.datagrid('checkRow',rowIndex);
					if(dataGrid.onSelect){
						dataGrid.onSelect(rowIndex, rowData);
					}
				},
				
				onClickCell: dataGrid.onClickCell || function(index,field,value){
				},
				onLoadError:function(){
					artup.alert('提示',"数据获取失败.",'error');
				}
			};
			
			Grid.datagrid(dataconfig);
			
		};
		//初始化Grid按钮 按钮控制
		var initTbar = function(){
			var tbars = getToolbar();
			config.toolbar = tbars;
			initGrid(config);
		};
		
							
		//初始话form
		var searchBtnId = ids.searchBtnId || "btn-search";
		var initForm = function(){
			if(Form.search && Form.search[0]){
				Form.search.find("#"+searchBtnId).click(Events.search); //保存事件
			}
		};
		
		
		var initWin = function(){
			if(Win.edit && Win.edit[0]){
				if(config.addValidation){
					checkWinValidation();  //窗口验证
				}
				
				//判断页面是否设置buttons，如果没有设置默认按钮
				var btns = Win.edit.attr("buttons");
				if(!btns){
					//设置 保存,关闭按钮
					Win.edit.dialog({
						buttons:[
							{
								text:"保存",
								handler:Events.save
							},{
								text:"关闭",
								handler:Events.close
							}
						]
					});
				}
				
				//Win.edit.find("#btn-submit").click(Events.save); //保存事件
				//Win.edit.find("#btn-close").click(Events.close);//关闭窗口
			}
		};
		
		
		//this 返回属性
		this.win = Win;
		this.form = Form;
		this.grid = Grid;
		this.utils = Utils;
		this.handler = Handler;
		this.dataGrid = dataGrid;
		
		//初始化方法
		this.init = function(){
			//config.noToolbar为true 构建无工具栏 列表
			if(config.noToolbar){
				initGrid(config);
			}else{
				initTbar();
			}
			initForm();
			initWin();
		};
		//调用初始化
		return this;
};