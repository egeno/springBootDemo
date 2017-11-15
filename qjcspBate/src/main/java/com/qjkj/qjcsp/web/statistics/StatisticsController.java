package com.qjkj.qjcsp.web.statistics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.core.shiro.ShiroUser;
import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.service.stacistics.GoodsSellReportService;
import com.qjkj.qjcsp.service.stacistics.StatisticsService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UserUtils;
import com.qjkj.qjcsp.web.BaseController;

import net.sf.json.JSONObject;

/*
 * 类名:StatisticsController
 * 版本号：V1.0
 * 日期：2016-01-18
 * 数据统计Controller
 */

@Controller
@RequestMapping("statistics")
public class StatisticsController extends BaseController {

	@Autowired
	private StatisticsService statisticsService;
	
	@Autowired
	private GoodsSellReportService goodsSellReportService;
	
	/**
	 * 每日设备供销统计报表-加载页面
	 */
	@RequestMapping(value="/machineSupplyReport")
	public String machineSupplyReport(ServletRequest request,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate){
		UserUtils u = new UserUtils();
		// 判断是否是全家科技
		Boolean flag = u.isUserofQJKJ();
		ShiroUser currendUser = Constants.getCurrendUser();
		// 得到登录用户的公司id
		int companyId = currendUser.getCompanyId();
				// 用作传参准备
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		map.put("companyId", companyId);
		//得到公司的id和公司名称
	    List<CompanyPo> lists=goodsSellReportService.getCompanyIdAndName(map);
	    request.setAttribute("data", lists);
		request.setAttribute("psdate", getDate(psdate));
		return "/statistics/supply/machineSupplyReport";
	}
	
	/**
	 * 每日设备供销统计报表-加载数据
	 */
	@RequestMapping(value="/findAllMachineSupplyReport")
	@ResponseBody
	public Map<String,Object> findAllMachineSupplyReport(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate){
		int companyId=Integer.valueOf(request.getParameter("companyId"));
		return statisticsService.findAllMachineSupplyReport(getDate(psdate), pageNumber, pageSize,companyId);
	}
	
	/**
	 * 每日设备供销统计报表-导出数据
	 */
	@RequestMapping(value="/machineSupplyReportExport")
	@ResponseBody
	public void machineSupplyReportExport(ServletRequest request,HttpServletResponse response,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate){
		int companyId=Integer.valueOf(request.getParameter("companyId"));
		statisticsService.findAllMachineSupplyExport(response,getDate(psdate),companyId);
	}
	
	/**
	 * 每日设备供销统计图表-加载页面
	 */
	@RequestMapping(value="/machineSupplyChart")
	public String machineSupplyChart(ServletRequest request,
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate){
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("psdate", getDate(psdate));
		request.setAttribute("companyId", Integer.valueOf(request.getParameter("companyId")));
		return "/statistics/supply/machineSupplyChart";
	}
	
	/**
	 * 每日设备供销统计图表-加载数据
	 */
	@RequestMapping(value="/findAllMachineSupplyChart")
	@ResponseBody
	public JSONObject findAllMachineSupplyChart(ServletRequest request,
			@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate){
		int companyId=Integer.valueOf(request.getParameter("companyId"));
		return statisticsService.findAllMachineSupplyChart(psdate, pageNumber, pageSize,companyId);
	}
	
	
	/*************************************每日商品供销统计*************************************/
	/**
	 * 每日设备供销统计报表-加载页面
	 * 
	 * 每日销售统计加载页面
	 */
	@RequestMapping(value="/goodsSupplyReport")
	public String goodsSupplyReport(ServletRequest request,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate){
		UserUtils u = new UserUtils();
		// 判断是否是全家科技
		Boolean flag = u.isUserofQJKJ();
		ShiroUser currendUser = Constants.getCurrendUser();
		// 得到登录用户的公司id
		int companyId = currendUser.getCompanyId();
				// 用作传参准备
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		map.put("companyId", companyId);
		//得到公司的id和公司名称
	    List<CompanyPo> lists=goodsSellReportService.getCompanyIdAndName(map);
	    request.setAttribute("data", lists);
		request.setAttribute("psdate", getDate(psdate));
		return "/statistics/supply/goodsSupplyReport";
	}
	
	/**
	 * 每日设备供销统计报表-加载数据
	 */
	@RequestMapping(value="/findAllGoodsSupplyReport")
	@ResponseBody
	public Map<String,Object> findAllGoodsSupplyReport(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate,
			@RequestParam(value = "foodName", defaultValue = "") String foodName){
		int companyId=Integer.valueOf(request.getParameter("companyId"));
		return statisticsService.findAllGoodsSupplyReport(getDate(psdate), pageNumber, pageSize,companyId,foodName);
	}
	
	/**
	 * 每日设备供销统计报表-导出数据
	 */
	@RequestMapping(value="/goodsSupplyReportExport")
	@ResponseBody
	public void goodsSupplyReportExport(ServletRequest request,HttpServletResponse response,
			@RequestParam(value = "psdate", defaultValue = "null") String psdate,
			@RequestParam(value = "foodName", defaultValue = "") String foodName){
		int companyId=Integer.valueOf(request.getParameter("companyId"));
		statisticsService.findAllGoodsSupplyExport(response,getDate(psdate),companyId,foodName);
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
