
		var $dg;
		var $grid;
		$(function() {
			$("#panel").panel({   
				   width:'auto',   
				   height:$(this).height()  
			});
			$dg = $("#dg");
			$grid = $dg.datagrid({
				url : "appUser/findAppUserIntegral",
				width : 'auto',
				height :  $(this).height(),
				pagination:true,
				pageSize:20,
				pageList:[ 10, 20, 30, 40, 50 ],
				rownumbers:true,
				border:false,
				singleSelect:true,
				striped:true,
				columns : [ [ 
				             {field : 'customerName',title : '客户昵称',width : parseInt($(this).width()*0.2),align : 'left',editor : "text"},
				             {field : 'universityName',title : '所属学校',width : parseInt($(this).width()*0.2),align : 'left',editor : "text"},
				             {field : 'customerMobile',title : '手机号',width : parseInt($(this).width()*0.1),editor : "text"},
				             {field : 'createTimeStr',title : '注册时间',align : 'center',editor : "date"},
				             {field : 'customerIntegral',title : '当前积分',width : parseInt($(this).width()*0.1),align : 'left',editor : "text"},				             
				             ] ],toolbar:'#tb'
			});
		});
		
		//查询
		function getInfo(){
			var startDate = $('#startDate').datebox('getValue');
			var endDate = $('#endDate').datebox('getValue');
			var universityName = $('#universityName').val();
			var customerName = $('#customerName').val();
			var customerMobile = $('#customerMobile').val();
			var condition = {};
			$.ajax({
				url:"appUser/findAppUserIntegral",
				data:"search_startDate=" + startDate + "&search_endDate=" + endDate + "&search_universityName=" + universityName + "&search_customerName=" + customerName + "&search_customerMobile=" + customerMobile,
				success:function(){
					condition["search_startDate"] = startDate;
					condition["search_endDate"] = endDate;
					condition["search_universityName"] = universityName;
					condition["search_customerName"] = customerName;
					condition["search_customerMobile"] = customerMobile;
					$('#dg').datagrid('reload', condition); 
				}
			});
		}
		
		//修改积分
		function updateIntegral() {
			var row = $dg.datagrid('getSelected');
			if (row) {
				parent.$.modalDialog({
					title : "修改积分",
					width : 360,
					height : 275,
					href : "appUser/IntegralEdit",
					onLoad:function(){
						var f = parent.$.modalDialog.handler.find("#form");
						f.form("load", row);
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
		
		function getToDayTime(){
			var d = new Date()
			var vYear = d.getFullYear()
			var vMon = d.getMonth() + 1
			var vDay = d.getDate()
			var h = d.getHours(); 
			var m = d.getMinutes(); 
			var se = d.getSeconds(); 
			s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay)+" "+(h<10 ? "0"+ h : h)+":"+(m<10 ? "0" + m : m)+":"+(se<10 ? "0" +se : se);
			return s;
		}

		function getZeroTime(){
			var d = new Date()
			var vYear = d.getFullYear()
			var vMon = d.getMonth() + 1
			var vDay = d.getDate()
			s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay)+" "+"00:"+"00:"+"00";
			return s;
		}
		
		