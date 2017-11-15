<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>零售商品设置</title>
<%@ include file="/layout/script.jsp"%>
<link href="<%=basePath%>views/goodsTemplate/css/new_file.css" rel="stylesheet" type="text/css" />
<script type="text/jscript" src="<%=basePath%>views/goodsTemplate/js/jquery.json.js"></script>
<!--[if IE]>
<style type="text/css">
.dialog {
	height: 100%;
	width: 100%;
	position: absolute;
	/*background:rgba(0,0,0,0.5);*/
    display:none;
	z-index: 999;
	background-color:#777777;
	opacity:0.57;
	filter:alpha(opacity=57);
}
</style>
<![endif]-->
</head>

<body>
	
	<div class="top">
		<div class="top_z">
			< <a href="preissue/preIssueIndex">切换预订页面</a>
		</div>
		<div class="top_y">零售商品设置</div>
	</div>

	<div class="content">
		<div class='loadmask'><div class='loadmask_msg'>正在分配，请稍候。。。</div></div>
		<div class="fenpei" id="btn8">
			
			<div class="fenpei_a">
				<div class="fenpei_aa">分配模板</div>

				<div class="fenpei_aaa">
					<!-- 放分配模板对话框的周期 -->
				</div>

				<div class="qd">
					<input name="" type="button" class="qd_a" value="取消" /> <input
						name="" type="button" class="qd_b" value="确定" />
				</div>
			</div>

		</div>
		<div class="btn4_tc">
			<div class="tc_1">
				<div class="tc_2">温馨提示</div>
				<div class="tc_3">
					<input type="hidden" class="modId"/>
					是否要删除<em class="modename"></em>？
					<em style="display: none;" class="realmodename"></em>
				</div>
				<div class="tc_4">
					<input name="" type="button" class="tc_5" value="取消" /> <input
						name="" type="button" class="tc_6" value="确定" />
				</div>
			</div>
		</div>
		<div class="dialog" id="btn4">
			
		</div>
		
		<div class="xiugai" id="btn6">
			<div class="xiugai1">
				<div class="xiugai2" value=''></div>
				<div class="xiugai3">
					<div class="xiugai3_a">
						<div class="xiugai3_aa">最高份数设置</div>
					</div>
					<div class="xiugai3_aaa">
						
					</div>
				</div>

				<div class="xiugai4">
					<div class="xiugai3_a">
						<div class="xiugai3_aa">模板设置</div>
					</div>
					<div class="xiugai3_aaa">
						<div class="xiugai3_aaaa">
							<div class="wenzi">
								<div class="wenzi_a" value='1'>
									周一：<span id="zysp" value=''></span>
								</div>
								<div class="wenzi_b">
									<a href="javascript:void(0);" id="xinqi" class="add">添加</a>/<a
										href="javascript:void(0);" class="delete">删除</a>
									<!--模板选择弹框-->
									<div class="xingqiyi info" id="xqy">
										<img src="<%=basePath%>views/goodsTemplate/img/concle.png"
											alt="" class="concle" />
										<div class="xingqiyi_a">
											<div class="xingqiyi_aa">
												<div class="name">A模板</div>
												<div class="name">B模板</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="wenzi">
								<div class="wenzi_a" value='2'>
									周二：<span id="zssp" value=''></span>
								</div>
								<div class="wenzi_b">
									<a href="javascript:void(0);" id="xinqis" class="add">添加</a>/<a
										href="javascript:void(0);" class="delete">删除</a>
									<!--模板选择弹框-->
									<div class="xingqisan info" id="xqs">
										<img src="<%=basePath%>views/goodsTemplate/img/concle.png"
											alt="" class="concle" />
										<div class="xingqiyi_a">
											<div class="xingqiyi_aa">
												<div class="name">A模板</div>
												<div class="name">C模板</div>
												<div class="name">B模板</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="wenzi">
								<div class="wenzi_a" value='3'>
									周三：<span id="zwsp" value=''></span>
								</div>
								<div class="wenzi_b">
									<a href="javascript:void(0);" id="xinqiw" class="add">添加</a>/<a
										href="javascript:void(0);" class="delete">删除</a>
									<!--模板选择弹框-->
									<div class="xingqiwu info" id="xqw">
										<img src="<%=basePath%>views/goodsTemplate/img/concle.png"
											alt="" class="concle" />
										<div class="xingqiyi_a">
											<div class="xingqiyi_aa">
												<div class="name">A模板</div>
												<div class="name">A模板</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="wenzi">
								<div class="wenzi_a" value='4'>
									周四：<span id="zrsp" value=''></span>
								</div>
								<div class="wenzi_b">
									<a href="javascript:void(0);" id="xinqir" class="add">添加</a>/<a
										href="javascript:void(0);" class="delete">删除</a>
									<!--模板选择弹框-->
									<div class="xingqiri info" id="xqr">
										<img src="<%=basePath%>views/goodsTemplate/img/concle.png"
											alt="" class="concle" />
										<div class="xingqiyi_a">
											<div class="xingqiyi_aa">
												<div class="name">A模板</div>
												<div class="name">A模板</div>
											</div>
										</div>
									</div>
								</div>
							</div>


							<div class="wenzi">
								<div class="wenzi_a" value='5'>
									周五：<span id="zesp" value=''></span>
								</div>
								<div class="wenzi_b">
									<a href="javascript:void(0);" id="xinqie" class="add">添加</a>/<a
										href="javascript:void(0);" class="delete">删除</a>
									<!--模板选择弹框-->
									<div class="xingqier info" id="xqe">
										<img src="<%=basePath%>views/goodsTemplate/img/concle.png"
											alt="" class="concle" />
										<div class="xingqiyi_a">
											<div class="xingqiyi_aa">
												<div class="name">A模板</div>
												<div class="name">A模板</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="wenzi">
								<div class="wenzi_a" value='6'>
									周六：<span id="zsisp" value=''></span>
								</div>
								<div class="wenzi_b">
									<a href="javascript:void(0);" id="xinqisi" class="add">添加</a>/<a
										href="javascript:void(0);" class="delete">删除</a>
									<!--模板选择弹框-->
									<div class="xingqisi info" id="xqsi">
										<img src="<%=basePath%>views/goodsTemplate/img/concle.png"
											alt="" class="concle" />
										<div class="xingqiyi_a">
											<div class="xingqiyi_aa">
												<div class="name">A模板</div>
												<div class="name">A模板</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="wenzi">
								<div class="wenzi_a" value='7'>
									周日：<span id="zlsp" value=''></span>
								</div>
								<div class="wenzi_b">
									<a href="javascript:void(0);" id="xinqiliu" class="add">添加</a>/<a
										href="javascript:void(0);" class="delete">删除</a>
									<!--模板选择弹框-->
									<div class="xingqiliu info" id="xql">
										<img src="<%=basePath%>views/goodsTemplate/img/concle.png"
											alt="" class="concle" />
										<div class="xingqiyi_a">
											<div class="xingqiyi_aa">
												<div class="name">A模板</div>
												<div class="name">A模板</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="wenzi_c">
					<input name="" type="button" class="wenzi_ca" value="取消" /> <input
						name="" type="button" class="wenzi_cb" value="确定" />
				</div>

			</div>
		</div>

		<div class="content_1">
			<div class="content_2">
				<div class="content_3">
					<div class="content_3a"></div>
					<div class="content_3aa">模板</div>
				</div>
				<div class="content_4">
					<a href="<%=basePath %>retail/retail"> <input onclick="window.location.href='<%=basePath %>retail/retail'" name="" type="button"
						class="bton" value="新建" />
					</a>
				</div>
			</div>

			<div class="zmuban">
				<!-- 左侧模板列表 -->
			</div>
		</div>
		<div class="content_y">
			<div class="content_y_a">机柜信息</div>
			<div class="gundong"></div>
		</div>
	</div>

	<script>
		function unique(arr) {
		    var result = [], hash = {};
		    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
		        if (!hash[elem]) {
		            result.push(elem);
		            hash[elem] = true;
		        }
		    }
		    return result;

		}
			$(function(){
				//加载左侧模板
				$.ajax({
					type:"post",
					url:"goodsTemplate/getTemplateList",
					async:false,
					dataType:'json',
					success:function(data){
						//console.log("左侧模板内容："+JSON.stringify(data));
					 	for(var i=0;i<data.length;i++){
							$('.zmuban').append("<div class='mub'>"+data[i].templateName+"<input id='templateId"+i+"' type='hidden' value='"+data[i].templateId+"'/><div class='mubMask' name='"+data[i].templateName+"' style='display:none;'><div class='muban3'><img src='<%=basePath%>views/goodsTemplate/img/delete.png'></div><div class='muban4'><img src='<%=basePath%>views/goodsTemplate/img/update.png'></div><div class='muban5'><img src='<%=basePath%>views/goodsTemplate/img/fenpei.png'></div></div></div>");
					 	}
						$(".mubMask .muban5").css("border","none");
					 	getChick();
					 	//点击左侧列表删除按钮
						$('.muban3').live("click",function() {
							$("#btn4").show();
							$(".btn4_tc").show();
							$("#btn2").show();
							var modename = $(this).parents('.mubMask').attr('name');
							var modeid = $(this).parents('.mub').find('input').attr('value');
							$('.tc_1').find('.modename').text("“"+modename+"”");
							$('.tc_1').find('.realmodename').text(modename);
							$('.tc_1').find('.modId').val(modeid);
						});
						
						//点击左侧列表修改按钮
						$('.muban4').click(function() {
							var modename = $(this).parents('.mubMask').attr('name');
							var modeid = $(this).parents('.mub').find('input').attr('value');
							$.ajax({
							    type: "POST",
							    url: "goodsTemplate/editTemplate",
							    data: {'templateId':modeid},  
							    dataType:'json',
							    success: function(results){
							    	 if(!results.result){
							    		 $.messager.show({
							 				title : '提示',
							 				msg : results.message,
							 				timeout : 3000,
							 			});
									}else{
										window.location.href=urls['ctx']+"/retail/jump?templateId="+modeid+"&templateName="+encodeURI(encodeURI(modename)); 
									} 
							    }
							});
						});
						
					 	//点击左侧分配按钮
						$('.muban5').click(function(){
							 
							var peimodename=$(this).parent().attr('name');
							var peitemplateId=$(this).parents('.mub').find('input').attr('value');
							//点击取值，哪些柜子几号柜已分配
							$.ajax({
								type:"post",
								url:"goodsTemplate/getAssignMachineTemplateList",
								data:{'templateId':peitemplateId},
								dataType:'json',
								async:true,
								success:function(data){
									//console.log("点击分配按钮的数据："+JSON.stringify(data));
									//alert(data.length);
									if(data.length>0){
										$('.fenpei').show();
										$('.fenpei_aaa').empty();
										for(var i=0;i<data.length;i++){
											$('.fenpei_aaa').append("<div class='fenpei_week'><ul><li class='guiname' value='"+data[i].machineId+"' title='"+data[i].machineName+"'>"+data[i].machineName+"</li><li class='week' value='1'><div class='weekMain' value=''><em>周一</em><input name='' type='checkbox' style='margin-left:15px;' value=''><div class='fenpeiMode'></div></div></li><li class='week' value='2'><div class='weekMain'  value=''><em>周二</em><input name='' type='checkbox' style='margin-left:15px;' value=''><div class='fenpeiMode'></div></div></li><li class='week' value='3'><div class='weekMain'  value=''><em>周三</em><input name='' type='checkbox' style='margin-left:15px;' value=''><div class='fenpeiMode'></div></div></li><li class='week' value='4'><div class='weekMain' value=''><em>周四</em><input name='' type='checkbox' style='margin-left:15px;' value=''><div class='fenpeiMode'></div></div></li><li class='week' value='5'><div class='weekMain' value=''><em>周五</em><input name='' type='checkbox' style='margin-left:15px;' value=''><div class='fenpeiMode'></div></div></li><li class='week' value='6'><div class='weekMain' value=''><em>周六</em><input name='' type='checkbox' style='margin-left:15px;' value=''><div class='fenpeiMode'></div></div></li><li class='week' value='7'><div class='weekMain' value=''><em>周日</em><input name='' type='checkbox' style='margin-left:15px;' value=''><div class='fenpeiMode'></div></div></li></ul></div>");
										
											for(var j=0;j<data[i].templateList.length;j++){
												///bbbb
												var $index=data[i].templateList[j].retailDate;
												//alert($index);
												var templateName=data[i].templateList[j].templateName;
												var templateId=data[i].templateList[j].templateId;
												$('.fenpei_week').eq(i).find('.week').eq($index-1).find('input').attr("checked","checked");
												$('.fenpei_week').eq(i).find('.week').eq($index-1).find('.fenpeiMode').text(templateName);
												$('.fenpei_week').eq(i).find('.week').eq($index-1).find('.weekMain').attr('value',templateId);
											}
										}
									}
									else{
										$.messager.show({
											title : '提示',
											msg : '该模板没有可被套用的设备',
											timeout : 3000,
										});
									}
									
									//模板名称经过的时候显示全称
									$('.fenpeiMode').hover(function(){
										var emname=$(this).text();
										if(emname.length>0){
											$(".fenpeiMode").attr("title",emname);
											//$(this).parent('.weekMain').append("<div class='tit'>"+emname+"</div>");
										}
									},function(){
										//$(this).parent('.weekMain').find('.tit').remove();
									});
									
									//分配模板对话框取消按钮
									$('.qd_a').click(function(){
										$(this).parents('.fenpei').hide();
									});
									//复选框勾选事件
									$('.fenpei_week').find('input').click(function(){
										if($(this).is(':checked')){
											$(this).parent().find('.fenpeiMode').text(peimodename);
											$(this).parent().attr('value',peitemplateId);
										}
										else{
											$(this).parent().find('.fenpeiMode').text('');
											$(this).parent().attr('value','');
										}
										
									});
									
									//分配模板对话框确定按钮
									$('.qd_b').click(function(){
										//setTimeout($('.loadmask').css({'display':'block'}),1000);
										$(this).parents('.fenpei').hide(); 
										var weekLen=$('.fenpei_aaa').find('.fenpei_week').length;
										var $week=$('.fenpei_aaa').find('.fenpei_week');
										var retailDates=[];
										for(var i=0;i<weekLen;i++){
											for(var j=0;j<$('.fenpei_week').eq(i).find('.week').length;j++){
												retailDates.push({'machineId':$week.eq(i).find('.guiname').attr('value'),'retailDate':j+1,'templateId':$week.eq(i).find('.week').eq(j).find('.weekMain').attr('value')});
												
											}
										}
										console.log("点击分配模板对话框确定按钮要穿的参数"+JSON.stringify(retailDates));
										/* //easyui等待框
										$.messager.progress({
											title:'请稍候',
											msg:'正在分配中，请稍候...'
										});  */
										//alert(JSON.stringify(retailDates));
										//点击确定传值
										$.ajax({
											type:"post",
											url:"goodsTemplate/editTemplateRetailDate",
											data: JSON.stringify(retailDates),//将对象序列化成JSON字符串  
											async:true,
											dataType:"json",
											contentType : 'application/json;charset=utf-8', //设置请求头信息  
											//beforeSend: function () {$('.loadmask').css({'display':'block'});},
										 	beforeSend: function(){
										 		//easyui等待框
										        $.messager.progress({
									                title:'请稍候',
									                msg:'正在分配中，请稍候...'
									            });
										    }, 
											success:function(data){
												$(this).parents('.fenpei').hide();
												if(data){
													window.location.reload();
												} 
											},
											error:function(){
												//关闭等待框
												 $(document).ready(function(){$.messager.progress('close');});
												 $.messager.show({
									 				title : '提示',
									 				msg : '数据传输出错了',
									 				timeout : 3000,
									 			});
											}
										});
									});
									
								},
								error:function(){
									console.log('分配加载出错了');
								}
							});
						});
					}
				});
				//先加载有哪些机柜
				$.ajax({
					type:"post",
					url:"goodsTemplate/getMachineList",
					async:false,
					success:function(data){
			
						var Str=eval(data);
	                    $.each(Str, function(n,machineName,machineId){
	                    	$('.gundong').append("<div class='gui' value='"+Str[n].machineId+"'><div class='gui_1'><div class='gui_1a'>"+Str[n].machineName+"</div><div class='gui_1aa'>丨<a href='javascript:void(0);' style=' color:#FFF;'>修改</a></div></div><div class='gui_2 number'><div class='gui_2a'><div class='gui_2aa'> 零售最高份数</div></div><div class='can'><table width='100%' border='0'><tbody></tbody></table></div></div><div class='gui_2 modeinfo'><div class='gui_2a'><div class='gui_2aa'>模板信息</div></div><div class='can'><table width='100%' border='0'><tbody></tbody></table></div></div></div>");
	                    }); 
						
					}
				});
				//在加载机柜里的最高份数
				 $.ajax({
					type:"post",
					url:"goodsTemplate/getMostSalsInfo",
					async:false,
					success:function(data){
						//重新取值生成数组machineArray;
		                var machineId=[];
		    			for(var i=0;i<data.length;i++){
		    				machineId.push(data[i].machineId);
		    			}
		    			var array=unique(machineId);
		    			var machineArray=[];
		    			for(var i=0;i<array.length;i++){
		    				var newArray=[];
		    				for(var j=0;j<data.length;j++){
		    					if(array[i]==data[j].machineId){
		    						newArray.push(data[j]);
		    					}
		    				}
		    				machineArray.push(newArray);
		    			}
		    			//console.log('最高份数'+JSON.stringify(machineArray));
		    			/* var array=[]; */
						for(var i=0;i<machineArray.length;i++){
							$arraylen=i;
							$.each($('.gui'),function(n){
								$guilen=n;
									for(var j=0;j<machineArray[$arraylen].length;j++){
										
										if($('.gui').eq($guilen).attr('value')==machineArray[$arraylen][j].machineId){
											//alert("areaModelId:"+machineArray[$arraylen][j].areaModelId+"areaModelName"+machineArray[$arraylen][j].areaModelName);
											$('.gundong').find('.gui').eq(n).find('.number .can').find('tbody').append("<tr><td class='dx'></td><td class='dd' value='"+machineArray[$arraylen][j].areaModelId+"'><p title='"+machineArray[$arraylen][j].areaModelName+"零售最高份数'><em>"+machineArray[$arraylen][j].areaModelName+"</em>零售最高份数： </p></td><td class='fen'><em>"+machineArray[$arraylen][j].mostSaleNum+"</em>份</td></tr>");
											/* array.push(machineArray[$arraylen][j].areaModelId); */
											//最高份数
											//$('.gundong').find('.gui').eq(n).find('.number .can').find('table').append("<tbody><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td class='dx'></td><td class='dd'>用餐时间最高份数： </td><td></td><td><em>"+data[i].mostSaleNum+"</em>份</td><tr><td class='dx'></td><td>最高份数： </td><td>&nbsp;</td><td><em>"+data[i].mostSaleNum+"</em>份</td><td>&nbsp;</td></tr><tr><td class='dx'></td><td>最高份数： </td><td>&nbsp;</td><td><em>"+data[i].mostSaleNum+"</em>份</td><td>&nbsp;</td></tr><tr><td class='dx'></td><td>最高份数： </td><td>&nbsp;</td><td><em>"+data[i].mostSaleNum+"<em>份</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody>");
										}
										
									}
							});
							
							//alert("最高份数："+data[i].mostSaleNum+"模型名："+data[i].areaModelName+"上面的设备id："+machineId+"属于什么设备的ID："+data[i].machineId);
							
						}
						
						for(var i=0;i<$('.gui').length;i++){
							$('.gundong').find('.gui').eq(i).find('.modeinfo .can').find('table').append("<tr><td value='' class='guiweek'>周一：<em value='1'></em></td><td value='' class='guiweek'>周二：<em value='2'></em></td></tr><tr><td value='' class='guiweek'>周三：<em value='3'></em></td><td value='' class='guiweek'>周四：<em value='4'></em></td></tr><tr><td value='' class='guiweek'>周五：<em value='5'></em></td><td value='' class='guiweek'>周六：<em value='6'></em></td></tr><tr><td value='' class='guiweek'>周日：<em value='7'></em></td><td>&nbsp;</td></tr>");
						}
						//鼠标经过显示全名
						/* $('.dd').hover(function(){
							var tabname=$(this).find('p').text();
							$(this).append("<div>"+tabname+"</div>")
						},function(){
							$(this).find('div').remove();
						}) */
						//加载机柜里的模板信息
						var modeInfoName=[];
						$.ajax({
							type:"post",
							url:'goodsTemplate/getTemplateInfo',
							async:false,
							success:function(data){
								//console.log('模板信息'+JSON.stringify(data));
								
								//加载对应模板信息
								var dataString=JSON.stringify(data);
								
								if(dataString!=''){
									for(var i=0;i<$('.gui').length;i++){
										$i=i;
										for(var j=0;j<data.length;j++){
											$j=j;
											
											if(parseInt($('.gui').eq(i).attr('value'))==data[j].machineId){
												
												for(var m=0;m<7;m++){
													if(parseInt($('.gui').eq($i).find(".modeinfo em").eq(m).attr('value'))==data[$j].retailDate){
														$('.gui').eq($i).find(".modeinfo em").eq(m).text(data[$j].templateName);
														
														$('.gui').eq($i).find(".modeinfo td").eq(m).attr('value',data[$j].templateId);
													}
													
												}
											}
										}
										
									}
								}
								//经过模板名称的时候显示全名
								$('.guiweek').find('em').hover(function(){
									var emname=$(this).text();
									$(this).attr("title",emname);
									//$(this).parent('.guiweek').append("<div class='tit'>"+emname+"</div>");
								},function(){
									//$(this).parent('.guiweek').find('.tit').remove();
								});
								
								
								//点击右侧机柜修改
								$('.gui_1aa').click(function(){
									$this=$(this);
									$('.xiugai3').find('.xiugai3_aaa').empty();
									$('#btn6').find('.xiugai2').attr('value',' ');
									$('#btn6').show();
									var guiName=$(this).parent().find('.gui_1a').text();
									var guiMachineId=$(this).parents('.gui').attr('value');
									$('#btn6').find('.xiugai2').text(guiName).attr('value',guiMachineId);
									var fenlen=$(this).parents('.gui').find('.number .can').find('tr').length;//早中晚餐的分类有几种
									var $tr=$(this).parents('.gui').find('.number .can').find('tr');
									var mostSale=[];
									for(var i=0;i<$(this).parents('.gui').find('.dd').length;i++){
										mostSale.push($(this).parents('.gui').find('.dd').eq(i).attr('value'));
									}
									console.log(JSON.stringify(mostSale));
									jsonData = {"machineId":guiMachineId};
									$.ajax({
										type:"POST",
										url:"goodsTemplate/getMachineCellNum",
										data:jsonData,
										async:false,
										success: function(data){
											localStorage.removeItem("machineCellNum");
											localStorage.setItem("machineCellNum",data);
										}
									});
									//最高份数
									///bbb
									for(var i=0;i<fenlen;i++){
										$('.xiugai3').find('.xiugai3_aaa').append("<div class='fenshu'><div class='fenshua'></div><div class='fenshuaa' value='"+mostSale[i]+"' title='"+$tr.eq(i).find('.dd em').text()+"零售最高份数'><em>"+$tr.eq(i).find('.dd em').text()+"</em>零售最高份数：</div><div class='fenshuaaa'><input name='' type='text' value="+$tr.eq(i).find('.fen em').text()+"></div></div>");
									}
									var modeInfoName=[];
									for(var i=0;i<7;i++){
										var template=$this.parents('.gui').find(".modeinfo em").eq(i).text();
										modeInfoName.push(template);
									}
									//console.log(JSON.stringify(modeInfoName));
									//周模板
									for(var i=0;i<7;i++){
										$('.xiugai4').find(".wenzi").eq(i).find("span").text(modeInfoName[i]);
									}
									var templateId=[];
									for(var i=0;i<7;i++)
									{
										$('.xiugai4').find(".wenzi_a").eq(i).find("span").attr('value',$this.parents('.gui').find('.guiweek').eq(i).attr('value'));
										
										templateId.push($('.xiugai4').find(".wenzi_a").eq(i).find("span").attr('value'));
									}
									localStorage.setItem("templateId",templateId);
								});
							}
						}); 
						
					}
				});
			});
			
			//自适应布局
			var hh = $(window).height() - 95;
			$('.content').height(hh);
			$('.content_1').height(hh);
			$('.content_y').height(hh);
			$('.gundong').height(hh - 55);
			$('.zmuban').height(hh-60);
			
			
			
			//关闭模板选择对话框
			$('.concle').click(function(){
				$(this).parent().hide();
			});
			
			//点击修改对话框中的添加按钮
			$('.add').click(function(){
				$('.info').hide();
				$(this).parent().find('.info').show();
				$add=$(this);
				$add.parent('.wenzi_b').find('.info').find('.xingqiyi_aa').empty();
				
				var machineId=$add.parents('.xiugai1').find('.xiugai2').attr('value');
				jsonData = {"machineId":machineId};
				$.ajax({
					type:"POST",
					url:"goodsTemplate/getConformTemplateList",
					data:jsonData,
					dataType:'json',
					success: function(data){
						//console.log('点击添加时获取的数据：'+JSON.stringify(data));
						for(var i=0;i<data.length;i++){
							$add.parent('.wenzi_b').find('.info').find('.xingqiyi_aa').append("<div class='name' value='"+data[i].templateId+"'>"+data[i].templateName+"</div>"); 
						}
						
						
						//判断是否是已选择的模板
						$add.parent().find('.name').css({'border-left-color':'#2aa2ed'});
						var modelen=$add.parent().find('.name').length;
						var modeSetName=$add.parents('.wenzi').find('.wenzi_a span').text();
						for(var i=0;i<modelen;i++){
							var havename=$add.parent().find('.name').eq(i).text();
							if(havename==modeSetName){
								$add.parent().find('.name').eq(i).css({'border-left-color':'#999'});
							}
						}
						//点击修改对话框的删除按钮
						$('.delete').click(function(){
							$(this).parents('.wenzi').find('.wenzi_a span').text('');
							$(this).parents('.wenzi').find('.wenzi_a span').attr('value','');
						});
						//点击修改对话框中的模板时
						$('.name').click(function(){
							var thisname=$(this).text();
							var thistemplateId=$(this).attr('value');
							$(this).parents('.wenzi').find('span').text(thisname);
							$(this).parents('.wenzi').find('span').attr('value',thistemplateId);
							$(this).css({'border-left-color':'#999'});
							$(this).siblings().css({'border-left-color':'#2aa2ed'});
						}); 
						
				     },
				     error:function(data){
				    	 console.log("aaa")
				     }
				}) 
				
			});
			//点击修改对话框的取消按钮
			$('.wenzi_ca').click(function(){
				$('.xiugai').hide();
				$('.info').hide();
			});
			//点击修改对话框的确定按钮
			$('.wenzi_cb').click(function(){
				//生成要传给后台的参数     最高份数信息数组
				var mostSales = new Array();
				var machineId=$(this).parents('.xiugai1').find('.xiugai2').attr('value');
				var fenlen=$('.xiugai1').find('.xiugai3 .xiugai3_aaa').find('.fenshu').length;
				var $fenshu=$('.xiugai1').find('.xiugai3 .xiugai3_aaa').find('.fenshu');
				var areaModelId=[];
				var mostSaleNum=[];
				var flag=true;
				for(var i=0;i<fenlen;i++){
					areaModelId.push($fenshu.eq(i).find('.fenshuaa').attr('value'));
					mostSaleNum.push($fenshu.eq(i).find('.fenshuaaa input').attr('value'));
					if($fenshu.eq(i).find('.fenshuaaa input').attr('value')==""){
						$.messager.show({
			 				title : '提示',
			 				msg : '份数不能为空',
			 				timeout : 3000,
			 			});
						flag=false;
						break;
					}
				}
				var machineCellNum=localStorage.getItem("machineCellNum");
				var reg=/^[0-9]*[1-9][0-9]*$/;
				var re = new RegExp(reg);
				for(var i=0;i<fenlen;i++){
					var setMostSealNum=mostSaleNum[i];
				    if(setMostSealNum==0){
						/* flag=false;
						 $.messager.show({
			 				title : '提示',
			 				msg : '最高份数不能为零，请设置商品最高份数',
			 				timeout : 3000,
			 			});
						break; */
						mostSales.push({'machineId':machineId,'areaModelId':areaModelId[i],'mostSaleNum':mostSaleNum[i]});
					}else if(parseInt(setMostSealNum)>parseInt(machineCellNum)){
						flag=false;
						 $.messager.show({
			 				title : '提示',
			 				msg : '亲，该设备商品最高份数不能超过'+machineCellNum+'份哦~',
			 				timeout : 3000,
			 			});
						break;
					}else if(parseInt(setMostSealNum)<0){
						flag=false;
						 $.messager.show({
			 				title : '提示',
			 				msg : '商品最高数量不能为负数',
			 				timeout : 3000,
			 			});
						break;
					}else if (setMostSealNum.match(re) == null) {
						flag=false;
						 $.messager.show({
			 				title : '提示',
			 				msg : '请输入正整数',
			 				timeout : 3000,
			 			});
						break;
					}
					else{
						mostSales.push({'machineId':machineId,'areaModelId':areaModelId[i],'mostSaleNum':mostSaleNum[i]});
					}
				}
				//console.log('mostSales'+JSON.stringify(mostSales));
				//alert('mostSales'+JSON.stringify(mostSales));
				if(flag){
					$.ajax({
					    type: "POST",
					    url: "goodsTemplate/realEditSaleNum",
					    data: JSON.stringify(mostSales),//将对象序列化成JSON字符串  
					    dataType:"json",
						async:false,
					    contentType : 'application/json;charset=utf-8', //设置请求头信息  
					    success: function(data){
					    	if(!data.result){
					    		 $.messager.confirm("操作提示", data.message,function(r){
					    			 if(r){
					    				 aa();
					    			 }
					    		 });
					    	}else{
					    		 aa();
					    	}
					    }
					});
					function aa(){
					//把生成的参数传给后台
				 	$.ajax({
					    type: "POST",
					    url: "goodsTemplate/editMostSale",
					    data: JSON.stringify(mostSales),//将对象序列化成JSON字符串  
					    dataType:"json",
						async:false,
					    contentType : 'application/json;charset=utf-8', //设置请求头信息  
					    success: function(data){
					    	if(data){
					    		var wenzilen=$('.xiugai4').find('.xiugai3_aaaa').find('.wenzi').length;
								var $wenzi=$('.xiugai4').find('.xiugai3_aaaa').find('.wenzi');
								var retailDates=[];
								for(var i=0;i<wenzilen;i++){
									retailDates.push({'machineId':machineId,'retailDate':$wenzi.eq(i).find('.wenzi_a').attr('value'),'templateId':$wenzi.eq(i).find('.wenzi_a').find('span').attr('value')});
								}
								//console.log(JSON.stringify(retailDates));
								//alert('retailDates'+JSON.stringify(retailDates));
								//传给后台模板信息
								$.ajax({
								    type: "POST",
								    url: "goodsTemplate/editTemplateRetailDate",
								    data: JSON.stringify(retailDates),//将对象序列化成JSON字符串  
								    dataType:"json",
									async:false,
								    contentType : 'application/json;charset=utf-8', //设置请求头信息  
								    success: function(data){
								    	if(data){
											$('.xiugai').hide();
											$('.info').hide();
											window.location.reload();
										}
								    }
								});
							}
					    }
					}); 
				 }
			 }
				/*生成要传给后台的参数  模板信息数组
				var retailDates = new Array();
				var $info=$('.xiugai1').find('.xiugai4 .xiugai3_aaa').find('.wenzi');
				var str=localStorage.getItem('templateId');
				var templateId=str.split(",");
				var retailDate=[];
				for(var i=0;i<7;i++){
					//templateId.push($('.xiugai4').find('.wenzi_a').eq(i).find('.span').attr('value'));
					retailDate.push($info.eq(i).find('.wenzi_a span').attr('value'));
				}
				for(var i=0;i<fenlen;i++){
					retailDates.push({'machineId':machineId,'templateId':templateId[i],'retailDate':retailDate[i]});
				}
				console.log('retailDates'+JSON.stringify(retailDates));*/
			});
			//点击修改对话框的删除按钮
			$('.delete').click(function(){
				$(this).parents('.wenzi').find('.wenzi_a span').text('');
				$(this).parents('.wenzi').find('.wenzi_a span').attr('value','');
			});
		</script>
</body>
</html>

<script type="text/javascript">
	function getChick(){
		$(".mub").hover(function(){
			$(".mubMask").hide();
			$(this).find(".mubMask").show();
			$(".mubMask .muban5").css("border","none");
		});
		/*左边列表离开的时候*/
		$(".mub").mouseleave(function(){
			$(".mubMask").hide();
		});
	}

	/*左边列表经过的时候*/
	$(".mub").hover(function(){
		$(".mubMask").hide();
		$(this).find(".mubMask").show();
		$(".mubMask .muban5").css("border","none");
	});
	/*左边列表离开的时候*/
	$(".mub").mouseleave(function(){
		$(".mubMask").hide();
	});
	
	//温馨提示对话框取消点击的时候
	$('.tc_5').click(function(){
		$('#btn4').removeClass('show');
		$('#btn4').hide();
		$(".btn4_tc").hide();
	});
	//温馨提示对话框确定点击的时候
	//删除的确认按钮
	$('.tc_6').click(function(){
		var name = $('.realmodename').text();
		var id= $(".modId").val();
		var len = $(".mub").length;
		$.ajax({
		    type: "POST",
		    url: "goodsTemplate/delTemplate",
		    data: {'templateId':id},  
		    dataType:'json',
		    success: function(results){
		    	 if(results.result){
		    		 $.messager.show({
		 				title : '提示',
		 				msg : results.message,
		 				timeout : 3000,
		 			});
		    		for(var i=0;i<len;i++)
		    		{
		    			var model = $(".mub").eq(i).find('.mubMask').attr('name')
		    			if(model == name)
		    			{
		    				$(".mub").eq(i).remove();
		    			}
		    		}
		    		$('#btn4').removeClass('show');
		    		$('#btn4').hide();
		    		$(".btn4_tc").hide();
				}else{
					 $.messager.show({
		 				title : '提示',
		 				msg : results.message,
		 				timeout : 3000,
		 			});
				} 
		    }
		});
	});
</script>