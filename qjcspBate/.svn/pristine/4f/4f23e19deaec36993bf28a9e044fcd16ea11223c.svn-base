<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>财务订单对账单统计</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="/layout/script.jsp"%>
<script type="text/javascript" src="<%=basePath%>views/statistics/financial/js/financial.js"></script>
<script type="text/javascript">
function getCompanyName(){
	var companyId=$('#companyId').val();
	$.ajax({
		type:"POST",
		url:"statistics/paymentStatement/getRoleName",
		data:{'companyId':companyId},
		dataType:'json',
		async:false,
		success: function(data){
			var companyName=document.getElementById("companyName");
			if(data.length==0){
				document.getElementById("companyName").innerHTML = "<option value='' selected>--暂无商户--</option>";
			}else{
				document.getElementById("companyName").innerHTML = "<option value='' selected>--全部--</option>";
				$.each(data,function(i){
					var a=new Option();
					a.value=data[i].roleName;
					a.text=data[i].roleName;
					companyName.options.add(a);
				});
			}
		}
	});
}
</script>
</head>
<body>
	<div id="tb" style="padding: 10px">
		<table cellpadding="5" cellspacing="0">
			<tr>
				<%-- <td>
					<div>
						<span>众包商：</span>
						<select id="companyId" panelHeight="auto" style="width:110px;" onchange="getCompanyName()">   
						   <option value="" selected>--全部--</option>
						   <c:forEach var="company" items="${companyList}">
						   	 <option value="${company.companyId}">${company.companyName}</option>
						   </c:forEach>
						</select> 
					</div>
				</td> --%>
				<%--<td>
					<div>
						<span>商户：</span>
						<select id="companyName" panelHeight="auto" style="width:110px;">   
						   <option value="" selected>--全部--</option>
						    <c:forEach var="roleName" items="${roleNameList}">
						   	 <option value="${roleName.roleName}">${roleName.roleName}</option>
						   </c:forEach>
						</select> 
					</div>
				</td> --%>
				<td>
					<div>
						<span>订单号码：</span><input name="orderNum" id="orderNum" placeholder="模糊搜索订单" type="text"/>					
					</div>
				</td>
				<td>
					<div style="text-align: center; vertical-align:middle;width:190px;">
						<span>下单方式：</span>
				     	<select id="modeNum" class="easyui-combobox" panelHeight="auto" style="width:110px;">   
						   <option value="-1" selected>全部</option>
						   <option value="0">设备端</option>   
						   <option value="1">安卓端</option>    
						   <option value="2">IOS端</option>   
						   <option value="3">微信端</option>  
						</select> 
					</div>
				</td>
				<td>
				<div style="text-align: center; vertical-align:middle;width:190px;">
						<span>支付方式：</span>
				     	<select id="payMode" class="easyui-combobox" panelHeight="auto" style="width:110px;">   
						   <option value="-1" selected>全部</option>
						   <option value="0">闪付</option>   
						   <option value="1">支付宝</option>    
						   <option value="2">微信支付</option>   
						   <option value="3">余额支付</option>  
						</select> 
					</div>
					</td>
				<td>
					<div>
						<span>用户名(手机号)：</span><input name="mobile" id="mobile" placeholder="模糊搜索用户" type="text"/>					
					</div>
				</td>
			</tr>
		</table>
		<table style="width: 100%;">
			<tr>
				<td style="width: 36%;">
					<span>下单时间：</span><input id="startDate" type="text" style="width: 150px;"/>至<input id="endDate" type="text" style="width: 150px;"></input>
				</td>
				 <td style="width: 16%;">
					<div style="text-align: center; vertical-align:middle;width:190px;">
						<span>总订单状态：</span>
				     	<select id="orderStatus" class="easyui-combobox" panelHeight="auto" style="width:110px;">   
						   <option value="-1" selected>全部</option>
						   <option value="0">待付款</option>   
						   <option value="1">已支付(待取货)</option>   
						   <option value="2">已取消</option>
						</select> 
				    </div>
				</td>
				<td style="width: 16%;">
					<div style="text-align: center; vertical-align:middle;width:190px;">
						<span>子订单状态：</span>
				     	<select id="orderChildStatus" class="easyui-combobox" panelHeight="auto" style="width:110px;">   
						   <option value="-1" selected>全部</option>
						   <option value="0">待付款</option>   
						   <option value="1">已支付(待取货)</option>   
						   <option value="2">已取消</option>   
						   <option value="3">已取货(待评论)</option> 
						   <option value="4">取货超时</option> 
						   <option value="5">待退款</option> 
						   <option value="6">交易关闭</option> 
						   <option value="7">完成</option> 
						   <option value="8">退款失败</option> 
						   <option value="9">退款成功</option> 
						   <option value="10">设备故障</option> 
						</select> 
				    </div>
				</td> 
				
				
				<!-- <td>
					<div style="text-align: center; vertical-align:middle;width:190px;">
						<span>订单状态：</span>
				     	<select id="orderStatus" class="easyui-combobox" panelHeight="auto" style="width:110px;">   
						   <option value="-1" selected>全部</option>
						   <option value="0">待付款</option>   
						   <option value="1">已支付(待取货)</option>   
						   <option value="2">已取消</option>   
						   <option value="3">已取货(待评论)</option> 
						   <option value="4">取货超时</option> 
						   <option value="5">待退款</option> 
						   <option value="6">交易关闭</option> 
						   <option value="7">完成</option> 
						</select> 
				    </div>
				</td> -->
				<td style="width: 14%;">
				<div style="text-align:center; vertical-align:middle;width:160px;">
						<span>订单类型：</span>
				     	<select id="orderType" class="easyui-combobox" panelHeight="auto" style="width:80px;">   
						   <option value="-1" selected>全部</option>
						   <option value="0">预订</option>   
						   <option value="1">零售</option>    
						</select> 
				    </div>
				</td>		
				<td  style="width: 18%;">
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="searchOrderList()">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-excel" onclick="exportOrderList()">导出</a>
				</td>
			</tr>
		</table>
	</div>
	<table id="dg" title="统计列表"></table>
</body>
</html>
