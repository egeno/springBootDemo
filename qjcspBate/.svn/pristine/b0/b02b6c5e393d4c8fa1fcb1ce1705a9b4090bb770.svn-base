package com.qjkj.qjcsp.web.statistics;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.company.CompanyService;
import com.qjkj.qjcsp.service.stacistics.FinancialStatisticsservice;
import com.qjkj.qjcsp.service.system.UserRoleService;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.web.BaseController;

/**
 * 财务统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping("statistics/financial")
public class FinancialController extends BaseController {
	
	@Autowired
	private FinancialStatisticsservice financialStatisticsservice; 
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserRoleService userRoleService;
	
   /**
    * 财务统计跳转
    */
	@RequestMapping(value = "financialStatistics")
	public String financialStatistics(ServletRequest request) {
		request.setAttribute("companyList", companyService.getAllCompany());
		request.setAttribute("roleNameList", userRoleService.getRoleName(null));
		return "/statistics/financial/financialStatistics";
	} 
	
	/**
	 * 查询所有对账信息
	 */
	@RequestMapping(value = "/QueryReconciliation")
	@ResponseBody
	public Map<String, Object> QueryReconciliation(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {

		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page<Map<String, Object>> lists = financialStatisticsservice.QueryReconciliation(searchParams, pageNumber, pageSize);
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		return map;
	}
	
	
	/**
	 * 根据子订单号获取订单详情
	 * 
	 * @param request
	 * @param orderNum
	 * @return
	 */
	@RequestMapping(value = "/findOrderDetail")
	@ResponseBody
	public Map<String, Object> findOrderDetail(ServletRequest request,
			@RequestParam("orderChildNum") String orderChildNum) {
		return financialStatisticsservice.findOrderDetail(request, orderChildNum);
	}
	
	/**
	 * 导出Excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/orderListExport")
	@ResponseBody
	public void orderListExport(ServletRequest request, HttpServletResponse response) {
		financialStatisticsservice.orderListExport(request, response);
	}
	
}
