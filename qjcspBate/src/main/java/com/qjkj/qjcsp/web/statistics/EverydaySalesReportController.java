package com.qjkj.qjcsp.web.statistics;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.stacistics.EverydaySalesReportService;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import com.qjkj.qjcsp.web.BaseController;
/**
 * 
 * @author rsq
 * 柜子日销售统计Controller
 */
@Controller
@RequestMapping("statistics")
public class EverydaySalesReportController extends BaseController{

	@Autowired
	private EverydaySalesReportService everydaySalesReportService;
	
	@RequestMapping("/listEverydaySales")
	public String listEverydaySales(ServletRequest request,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate){
		request.setAttribute("psdate", getDate(psdate));
		request.setAttribute("companys", everydaySalesReportService.getCompanyIdAndName());
		return "statistics/everydaySales/everySalesReport";
	}
	
	
	
	@RequestMapping("/everydaySales")
	@ResponseBody
	public Map<String,Object> everydaySales(ServletRequest request,
			@RequestParam(value = "psdate", defaultValue = "") String psdate,
			@RequestParam(value = "machineName", defaultValue = "") String machineName,
			@RequestParam(value = "companyId", defaultValue = "") String companyId/*,
			@RequestParam(value = "machineSex", defaultValue = "") String machineSex,
			@RequestParam(value = "useOrNot", defaultValue = "") String useOrNot*/
			){
		//Integer companyId=Constants.getCurrendUser().getCompanyId();
		return everydaySalesReportService.everydaySalesReport(psdate,machineName,companyId);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/everydaysExport")
	@ResponseBody
	public void everydaysExport(ServletRequest request,HttpServletResponse response,
			@RequestParam(value = "psdate", defaultValue = "") String psdate,
			@RequestParam(value = "machineName", defaultValue = "") String machineName,
			@RequestParam(value = "companyId", defaultValue = "") String companyId/*,
			@RequestParam(value = "machineSex", defaultValue = "") String machineSex,
			@RequestParam(value = "useOrNot", defaultValue = "") String useOrNot*/){
		//Integer companyId=Constants.getCurrendUser().getCompanyId();
		/*String value = request.getParameter("machineSex"); 
        //拿到乱码反向查找 iso-8859-1 码表，获取原始数据，
        //在构造一个字符串让它去查找UTF-8 码表，已得到正常数据
        String machineSex = null;
		try {
			machineSex = new String (value.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
         //List<List<Integer>> rows
		
		//( response, tablename,  sheets,  titles,
		//List<List<Integer>> rows,  keys, List<Map<String, Object>> datas)
		
		
		
		Map<String,Object> everydaySalesMap=
				everydaySalesReportService.everydaysExport(psdate,machineName,companyId);
		List<Integer> rowsdate = null;
		
//		List<List<Integer>> rowsdate=null;
		
		List<Map<String,Object>> list = null;
		if(everydaySalesMap!=null && everydaySalesMap.size()>0){
			list=(List<Map<String, Object>>) everydaySalesMap.get("rows");
			rowsdate=(List<Integer>) everydaySalesMap.get("rowspan");	
		}
		String title = "每日柜子销售数量";
		String[] titles = {"柜子名称","时间段","补货数","订单数量","销售数","销售金额","售卖率"};
		Integer[] sheets = {150,100,150,100,100,100,150,100};
		String[] keys = {"machineName","areaModelName","operateCount","orderCount","saleCount","saleMoney","sellRate"};
		ExportExcelUtil.exportExcel2(response, title, sheets, titles, rowsdate, keys, list);
	}
	//判断是否传来参数否则获取当前时间
	private String getDate(String psdate){
		if(psdate.equals("null")){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			psdate = sdf.format(new  Date());
		}
		return psdate;
	}
}
