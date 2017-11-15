package com.qjkj.qjcsp.web.statistics;

import java.util.HashMap;
import java.util.List;
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
import com.qjkj.qjcsp.service.stacistics.PaymentStatementService;
import com.qjkj.qjcsp.service.system.UserRoleService;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.web.BaseController;

/*
 * 类名:PaymentStatementController
 * 版本号：V1.0
 * 日期：2016-01-20
 * 订单对账单Controller
 */

@Controller
@RequestMapping("statistics/paymentStatement")
public class PaymentStatementController extends BaseController {

	@Autowired
	private PaymentStatementService paymentStatementService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserRoleService userRoleService;

	/**
	 * 订单对账单跳转页
	 * 
	 * @return
	 */
	@RequestMapping(value = "listPaymentStatementMain")
	public String listPaymentStatementMain(ServletRequest request) {
		request.setAttribute("companyList", companyService.getAllCompany());
		request.setAttribute("roleNameList", userRoleService.getRoleName(null));
		return "/statistics/paymentStatement/listPaymentStatementMain";
	}

	@RequestMapping(value = "/getRoleName")
	@ResponseBody
	public List<Map<String, Object>> getRoleName(@RequestParam("companyId") Long companyId) {
		List<Map<String, Object>> list = userRoleService.getRoleName(companyId);
		return list;
	}

	/**
	 * 查询所有订单信息
	 * 
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/findOrdersPage")
	@ResponseBody
	public Map<String, Object> findOrdersPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {

		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page<Map<String, Object>> lists = paymentStatementService.findOrdersPage(searchParams, pageNumber, pageSize);
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
		return paymentStatementService.findOrderDetail(request, orderChildNum);
	}

	/**
	 * 导出订单
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/orderListExport")
	@ResponseBody
	public void orderListExport(ServletRequest request, HttpServletResponse response) {
		paymentStatementService.orderListExport(request, response);
	}

	/**
	 * 根据订单号获取子订单
	 * 
	 * @param request
	 * @param orderNum
	 * @return
	 */
	@RequestMapping(value = "/findOrderChild")
	@ResponseBody
	public Map<String, Object> findOrderChild(ServletRequest request, @RequestParam("orderNum") String orderNum) {
		return paymentStatementService.findOrderChild(request, orderNum);
	}
}
