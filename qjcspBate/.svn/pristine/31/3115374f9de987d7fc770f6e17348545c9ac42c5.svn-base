package com.qjkj.qjcsp.web.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.AlarmReportSearch;
import com.qjkj.qjcsp.service.stacistics.AlarmReportService;
import com.qjkj.qjcsp.util.ExportExcelUtil;

@RequestMapping("alarmReport")
@Controller
public class AlarmController {
	private static final String PAGE_SIZE = "50";
	@Autowired
	private AlarmReportService alarmReportService;
	
	@RequestMapping("/alarmReportMain")
	public String alarmReportMain(){
		return "alarm/alarmReport";
	}
	@RequestMapping("/findAlarmReport")
	@ResponseBody
	public Map<String,Object> findAllGoodsSellReport(HttpServletRequest req, HttpServletResponse res,
			AlarmReportSearch alarmReportSearch,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		alarmReportSearch.setOffset((pageNumber - 1) * pageSize);
		alarmReportSearch.setLimit(pageSize);
		Map<String, Object> map =alarmReportService.findAlarmReport(alarmReportSearch);
		int count=alarmReportService.getAllAlarmReportCount(alarmReportSearch);
		map.put("total", count);
		return map;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/AlarmReportExport")
	@ResponseBody
	public void AlarmReportExport(HttpServletResponse response,AlarmReportSearch alarmReportSearch){
		Map<String, Object> content=alarmReportService.findAlarmReport(alarmReportSearch);
		List<Map<String,Object>> rows=null;
		List<List<Integer>> rowspans=null;
		if(content!=null&&content.size()>0){
			rows=(List<Map<String,Object>>)content.get("rows");
			List<Integer> machineRowspan=(List<Integer>)content.get("machineRowspan");
			machineRowspan.add(0);
			List<Integer> areaModelRowspan=(List<Integer>)content.get("areaModelRowspan");
			areaModelRowspan.add(1);
		    rowspans=new ArrayList<List<Integer>>();
			rowspans.add(machineRowspan);
			rowspans.add(areaModelRowspan);
		}
		if(rowspans==null||rows==null||rows.size()==0){
	rowspans = new ArrayList<List<Integer>>();
		}
		String title = "故障报表统计";
		String[] titles = {"设备名称","模型名称","订单号","故障取餐类型","商品名称","格子号","餐品单价","故障日期","维修员姓名","维修员手机","设备地址"};
		Integer[] sheets = {150,150,250,150,150,150,150,150,150,150,150};
		String[] keys = {"machineName","areaModelName","orderNum","deviceFaultSymbol","goodsName","cellNum","costPrice","alarmTime","repairName","repairPhone","machineAdress"};
		ExportExcelUtil.exportExcel(response, title, sheets, titles, rowspans, keys, rows);	
	}
	
	
}
