		var $role;
		var $user;
		var $user_id;
		var $specialRoleNum;
		$(function() {
			$("#panel").panel({   
				   width:'auto',   
				   height:$(this).height(),   
				   title: '供货员分配',   
			});
			$user = $("#user");
			$usergrid=$user.datagrid({
				queryParams: {
			        employee_type: '1',
			    },
				url : "employee/findAllEmployeeList",
				width : 'auto',
				height : $(this).height()-128,
				pagination:true,
				rownumbers:true,
				singleSelect:true,
				striped:true,
				border:false,
				columns : [ [ {field : 'userId',title : '编号',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
				              {field : 'specialRoleNum',title : '用户角色',hidden:true, width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
				              {field : 'userName',title : '姓名',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},
				              {field : 'userMobile',title : '电话',width : parseInt($(this).width()*0.1),editor : {type:'validatebox',options:{required:true}}},
				              ] ],toolbar:"#tbUser",onDblClickRow:getMachines
			});
			$role = $("#role");
			$role.datagrid({
					url : "machine/info/findAllMachinesList",
					width : 'auto',
					height : $(this).height()-128,
					pagination:true,
					border:true,
					rownumbers:true,
					singleSelect:false,
					striped:true,
					idField: 'machineId',
					columns : [ [ {field:'ck',checkbox:true},
					              {field : 'machineName',title : '设备名称',width : parseInt($(this).width()*0.1),align : 'center',editor : "text"},
					              {field : 'deviceCode',title : '设备编号',width :  parseInt($(this).width()*0.1),align : 'center',editor : {type:'validatebox',options:{required:true}}}
					              ] ],toolbar:"#tbRole",
					onLoadSuccess: function(){   
						if($user_id != null){
							$.post("machine/info/findEmployeeMachineList", {'user_id':$user_id,'specialRoleNum':$specialRoleNum}, function(rsp) {
								$role.datagrid("unselectAll");
								if(rsp.length!=0){
									$.each(rsp,function(i,e){
										$role.datagrid("selectRecord",e.machineId);
									});
								}else{
									parent.$.messager.show({
										title :"提示",
										msg : "该供货员暂不服务于任何设备！",
										timeout : 1000 * 2
									});
								}
							}, "JSON").error(function() {
								parent.$.messager.show({
									title :"提示",
									msg : "获取设备失败！",
									timeout : 1000 * 2
								});
							});
						}
					}

			});
			
			//搜索框
			$("#searchbox").searchbox({ 
				 menu:"#mm", 
				 prompt :'模糊查询',
			    searcher:function(value,name){   
			    	var str="{\"search_"+name+"\":\""+value+"\",\"employee_type\":\"1\"}";
		            var obj = eval('('+str+')');
		            $user.datagrid('reload',obj); 
		       
			    }
			   
			});
			//搜索框
			$("#searchbox2").searchbox({ 
				 menu:"#mm2", 
				 prompt :'模糊查询',
			    searcher:function(value,name){   
			    	var str="{\"search_"+name+"\":\""+value+"\"}";
		            var obj = eval('('+str+')');
		            $role.datagrid('reload',obj); 
			    }
			   
			});
		});
		
		 function saveEmployeeMachine(){
				 var selectRow=$user.datagrid("getSelected");
				 var selectRows=$role.datagrid("getSelections");
				 var isCheckedIds=[];
				 $.each(selectRows,function(i,e){
					 isCheckedIds.push(e.deviceCode);
				 });
				 if(selectRow){
					 $.ajax({
							url:"employee/saveEmployeeMachine",
							data: "user_id="+selectRow.userId+"&isCheckedIds="+isCheckedIds,
							dataType:'json',
							success: function(rsp){
									parent.$.messager.show({
										title :rsp.title,
										msg :rsp.message,
										timeout : 1000 * 2
									});
							},
							error:function(){
								parent.$.messager.show({
									title :"提示",
									msg :"保存设置失败！",
									timeout : 1000 * 2
								});
							}
						});
				 }else{
					 parent.$.messager.show({
							title :"提示",
							msg :"请选择一行记录！",
							timeout : 1000 * 2
						});
				 }
			 }
		 function getMachines(rowIndex, rowData){
			 $user_id = rowData.userId,
			 $specialRoleNum=rowData.specialRoleNum,
			 $.post("machine/info/findEmployeeMachineList", {user_id:rowData.userId,'specialRoleNum':rowData.specialRoleNum}, function(rsp) {
					 $role.datagrid("unselectAll");
				 if(rsp.length!=0){
					 $.each(rsp,function(i,e){
						 $role.datagrid("selectRecord",e.machineId);
					 });
				 }else{
					 parent.$.messager.show({
							title :"提示",
							msg : "该供货员暂不服务于任何设备！",
							timeout : 1000 * 2
						});
				 }
				}, "JSON").error(function() {
					 parent.$.messager.show({
							title :"提示",
							msg : "获取设备失败！",
							timeout : 1000 * 2
						});
				});
		}
		
