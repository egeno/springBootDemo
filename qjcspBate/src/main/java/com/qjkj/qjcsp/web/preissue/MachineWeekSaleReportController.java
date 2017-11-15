package com.qjkj.qjcsp.web.preissue;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.preissue.MachineWeekSaleReportService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateUtils;
import com.qjkj.qjcsp.util.ExportExcelUtil;
/**
 * 柜子周菜品销售统计
 * @author carpeYe 2016-03-24
 *
 */
@Controller
@RequestMapping("machineWeekSaleReport")
public class MachineWeekSaleReportController {

	@Autowired
	private MachineWeekSaleReportService machineWeekSaleReportService;
	/*
	 * 加载主页面
	 * @param request
	 * @param psdate 当前时间
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex(HttpServletRequest request,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate) {
		request.setAttribute("psdate", getDate(psdate));
		request.setAttribute("ssdate", DateUtils.getMonday(psdate));
		request.setAttribute("esdate", DateUtils.getSunday(psdate));
		
		return "report/machineWeekSaleReport/machineWeekSaleReport";
	}

	/*
	 * 主页面异步加载数据
	 */
	@RequestMapping("/toLoadIndexData")
	@ResponseBody
	public Map<String, Object> toLoadIndexData(@RequestParam(value = "psdate", defaultValue = "") String psdate,
			@RequestParam(value = "machineName", defaultValue = "") String machineName,
			@RequestParam(value = "companyId", defaultValue = "") String companyId) {
		if (!StringUtils.isNoneBlank(companyId)) {
			companyId = Constants.getCurrendUser().getCompanyId()+"";
		} 
		return machineWeekSaleReportService.machineWeekSaleReport(companyId, machineName, psdate);
	}
	/*
	 * 导出功能 
	 * @param response
	 * @param psdate 日期
	 * @param machineName 设备名
	 * @param companyId 公司ID
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/machineWeekSaleReportExport")
	@ResponseBody
	public void machineWeekSaleReportExport(HttpServletResponse response,
			@RequestParam(value = "psdate", defaultValue = "") String psdate,
			@RequestParam(value = "machineName", defaultValue = "") String machineName,
			@RequestParam(value = "companyId", defaultValue = "") String companyId){
			if (!StringUtils.isNoneBlank(companyId)) {
			companyId = Constants.getCurrendUser().getCompanyId()+"";
		} 
			try {
				machineName = URLDecoder.decode(machineName,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Map<String, Object> content=machineWeekSaleReportService.machineWeekSaleReport(companyId, machineName, psdate);
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
		String title = "每日柜子销售数量";
		String[] titles = {"柜子名称","模型名称","菜名","销售数"};
		Integer[] sheets = {150,150,250,100};
		String[] keys = {"machineName","areaModelName","goodsName","goodsNum"};
		ExportExcelUtil.exportExcel(response, title, sheets, titles, rowspans, keys, rows);	
	}

	// 判断是否传来参数否则获取当前时间
	private String getDate(String psdate) {
		if (psdate.equals("null")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			psdate = sdf.format(new Date());
		}
		return psdate;
	}
}
