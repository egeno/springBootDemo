<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script> 
<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
<script type="text/javascript">
	$(function(){
		setTimeout(function(){
			$.post("statistics/findAllMachineSupplyChart", {psdate:'${psdate }',pageNumber:'${pageNumber}',pageSize:'${pageSize}' ,companyId:'${companyId}'}, function(rsp) {
				if(rsp.length!=0){
					$('#container').highcharts({
				        chart: {type: 'column'},
				        credits: {enabled:false},
				        title: {text: '每日柜子供销统计'},
				        subtitle: {text: rsp.psdate },
				        xAxis: rsp.msc,
				        yAxis: {
				            min: 0,
				            title: {
				                text: '数量'
				            }
				        },tooltip: {
				            headerFormat: '<span style="font-size:12px">{point.key}</span><table style="width:100px;font-size:14px">',
				            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
				                '<td style="padding:0"><b>{point.y} 份</b></td></tr>',
				            footerFormat: '</table>',
				            shared: true,
				            useHTML: true
				        },plotOptions: {
				            column: {
				                pointPadding: 0.2,
				                borderWidth: 0
				            }
				        },series: rsp.snum
				    });
				 }
			},'JSON');
		},300);
	});
</script>
<div id="container" style="min-width:800px;height:620px"></div>