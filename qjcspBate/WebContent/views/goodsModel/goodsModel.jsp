<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include  file="../include/ctxPathInclude.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>商品与模型关联管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/layout/script.jsp"></jsp:include>
	<script type="text/javascript">
		var $role;
		var $user;
		var $areaModelId;
		var $grid;
		
		$(function() {
			$("#panel").panel({   
				   width:'auto',   
				   height:$(this).height(),   
				   title: '商品与模型关联',   
			});
			$user = $("#user");
			$user.datagrid({
				url : "goods/findAllareaList",
				width : 'auto',
				height : $(this).height()-120,
				pagination:true,
				rownumbers:true,
				singleSelect:true,
				striped:true,
				border:false,
				columns : [ [ {field : 'areaModelId',title : '模型id',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
				              {field : 'areaModelName',title : '模型名称',align : 'center',align : 'left',editor : "text"},
				              {field : 'areaModelType',title : '模型类型',align : 'center',
				            	  formatter:function(value,row){
				            		      if("0"==row.areaModelType){
											return "制冷";
					            		  } else if("1"==row.areaModelType){
					            			  return "加热";
					            		  } else if("2"==row.areaModelType){
					            			  return "冷热一体";
					            		  }
				            		  
				            		 /*  if("1"==row.areaModelType){
										return "<font color=red>加热<font>";
				            		  }
				            		  else{
				            			return "<font color=green>制冷<font>";
				            		  } */
				            		  
									},
									/* editor:{type:'checkbox',options:{on:'1',off:'0'}} */
				              },
				              /* {field : 'areaModelType',title : '模型类型',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"}, */
/* 				              {field : 'areaModelRow',title : '适用层数',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
				              {field : 'areaModelColumn',title : '适用列数',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"}, */
				              {field : 'areaModelMemo',title : '描述',width : parseInt($(this).width()*0.1),editor : "text"}
				              ] ],toolbar:"#tbUser",onDblClickRow:getRoles
			});
			$role = $("#role");
			$grid = $role.datagrid({
					url : "goods/findNewGoodsPage",
					width : 'auto',
					height : $(this).height()-120,
					pagination:false,
					border:false,
					rownumbers:true,
					singleSelect:false,
					striped:true,
					idField: 'goodsId',
					columns : [ [ {field:'ck',checkbox:true},
					              {field : 'goodsIcon',title : '商品图标',align : 'center',
					            	  formatter:function(value,row,index){
						             		 return '<img src="' +baseUrls.ctx+ value + '" width="50" height="50" />'
						            	  }
					              },
					              {field : 'goodsName',title : '商品名称',align : 'center'},
					              {field : 'categoryName',title : '分类名称',align : 'center'}
					              ] ],toolbar:"#tbRole",
					onLoadSuccess: function(){
						if($areaModelId != null){
							 $.post("goods/findGoodsIdByModelId", {areaModelId:$areaModelId}, function(rsp) {
								 $role.datagrid("unselectAll");
							 if(rsp.length!=0){
								 $.each(rsp,function(i,e){
									 $role.datagrid("selectRecord",e.goodsId);
								 });
							 }else{
								 parent.$.messager.show({
										title :"提示",
										msg : "该模型暂无商品！",
										timeout : 1000 * 2
									});
							 }
							}, "JSON").error(function() {
								 parent.$.messager.show({
										title :"提示",
										msg : "获取模型商品列表失败！",
										timeout : 1000 * 2
									});
							});
						}
					}
			});
		});
		
		 function saveUserRoles(){
			 var selectRow=$user.datagrid("getSelected");
			 var selectRows=$role.datagrid("getSelections");
			 var checkedRoleIds = [];
			 $.each(selectRows,function(i,e){
				 checkedRoleIds.push(e.goodsId);
			 });
			 if(selectRow){
				 $.ajax({
						url:"goods/saveGoodsModelConfig",
						data: "areaModelId="+selectRow.areaModelId+"&roleIds="+checkedRoleIds,
						dataType:'json',
						success: function(rsp){
								parent.$.messager.show({
									title :rsp.title,
									msg :rsp.message,
									height:'100%',
									timeout : 1000 * 2
								});
						},
						error:function(){
							parent.$.messager.show({
								title :"提示",
								msg :"保存模型商品失败！",
								timeout : 1000 * 2
							});
						}

					});
			 }else{
				 parent.$.messager.show({
						title :"提示",
						msg :"请选择模型！",
						timeout : 1000 * 2
					});
			 }
		 }
		 function getRoles(rowIndex, rowData){
			 $areaModelId = rowData.areaModelId;
			 $.post("goods/findGoodsIdByModelId", {areaModelId:rowData.areaModelId}, function(rsp) {
				 	 console.log(rsp);
					 $role.datagrid("unselectAll");
				 if(rsp.length!=0){
					 $.each(rsp,function(i,e){
						 $role.datagrid("selectRecord",e.goodsId);
					 });
				 }else{
					 parent.$.messager.show({
							title :"提示",
							msg : "该模型暂无商品！",
							timeout : 1000 * 2
						});
				 }
				}, "JSON").error(function() {
					 parent.$.messager.show({
							title :"提示",
							msg : "获取模型失败！",
							timeout : 1000 * 2
						});
				});
		}
		 function delRows(){
				var row = $user.datagrid('getSelected');
				if(row)
				{
					parent.$.messager.confirm('提示框', '你确定要删除吗?',function(r){
						if(r)
						{
							var rowIndex = $user.datagrid('getRowIndex', row);
							$user.datagrid('deleteRow', rowIndex);
							$.ajax({
								url:"goods/delRow",
								data: "areaModelId="+row.areaModelId,
								dataType:'json',
								success: function(rsp){
									parent.$.messager.show({
										title : rsp.title,
										msg : rsp.message,
										timeout : 1000 * 2
									});
									$user.datagrid('reload');
								}
							});
							
						}
					});
				}else{
					parent.$.messager.show({
						title : "提示",
						msg : "请选择行数据!",
						timeout : 1000 * 2
					});
				}
			}
			//弹窗修改
			function updRowsOpenDlg() {
				var row = $user.datagrid('getSelected');
				if (row) {
					parent.$.modalDialog({
						title : "编辑模型",
						width : 500,
						height : 350,
						href : "goods/addGoodsModel",
						onLoad:function(){
							var f = parent.$.modalDialog.handler.find("#form");
							f.form("load", row);
						},			
						buttons : [ {
							text : '编辑',
							iconCls : 'icon-ok',
							handler : function() {
								parent.$.modalDialog.openner= $user;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
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
				parent.$.modalDialog({
					title : "添加模型",
					width : 500,
					height : 350,
					href : "goods/addGoodsModel",
					buttons : [ {
						text : '保存',
						iconCls : 'icon-ok',
						handler : function() {
							parent.$.modalDialog.openner= $user;//因为添加成功之后，需要刷新这个treegrid，所以先预定义好
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
			
			/* function searchfo(){
				var areaModelName=$.trim($("#searchbox").val());
				
				$("#panel").datagrid("load",{
					areaModelName:areaModelName,
					
				});
			} */
			
			function searchfo(){
				var search=$.trim($("#searchbox").val());
		        $.ajax({
		       	url : "goods/findAllareaList",
		        type: "post",
		        data:"search="+search,
		        dataType:"text",
		        success:function(text){
		        	$user.datagrid('reload',obj); 
		        	/* $user.datagrid('reload'); */
		        }
		        })
			}
	</script>
  </head>
  <body>
  
   <div id="panel"  data-options="border:false">
		<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" title="" style="height: 82px; overflow: hidden; padding: 5px;">
			<div class="well well-small">
				<span class="badge">提示</span>
				<p>
					为模型分配商品，请<span class="label-info"><strong>双击模型</strong></span>查看拥有商品！
				</p>
			</div>
		</div>
			<div data-options="region:'west',split:true,border:true" style="width:800px;">
				<div id="tbUser" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:2px">
<!-- 								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
 -->								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-config" plain="true" onclick="saveUserRoles();">保存设置</a>
							</td>
							<!-- <td style="padding-left:2px">
								模型名称：<input id="searchbox" type="text"/>
							</td>
							<td>
								<a href="javascript:void(0);" class="easyui-linkbutton toolbtn" iconCls='icon-search' plain='true' onclick="searchfo()">查询</a>
							</td> -->
						</tr>
					</table>
				</div>
				<!-- <div id="mm">
						<div name="userCode">用户编码</div>
						<div name="userAccount">用户登录名</div>
				</div> -->
				<table id="user" title="模型"></table>
			</div>
			<div data-options="region:'center',border:true">
				<div id="tbRole" style="padding:2px 0">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td style="padding-left:4px;padding-bottom:4px;">
								<!-- <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRowsOpenDlg();">添加</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updRowsOpenDlg();">编辑</a>
								<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRows();">删除</a> -->
							</td>
						</tr>
					</table>
				</div>
				<table id="role" title="商品"></table>
			</div>
		</div>
	</div>
  </body>
  </body>
</html>
