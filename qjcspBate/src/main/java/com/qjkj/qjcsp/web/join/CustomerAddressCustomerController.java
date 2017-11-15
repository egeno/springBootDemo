package com.qjkj.qjcsp.web.join;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.TblCustomerApplyAddress;
import com.qjkj.qjcsp.entity.TblCustomerApplyAddressList;
import com.qjkj.qjcsp.service.join.ApplyAddressService;
import com.qjkj.qjcsp.util.Servlets;

@Controller
@RequestMapping("join/customer")
public class CustomerAddressCustomerController {
	
	private static final String PAGE_SIZE = "10";
    @Autowired
    private ApplyAddressService applyAddressService;
	/**
	 *  客户查询跳转页
	 */
	@RequestMapping(value = "/customerManage") 
	public String findJoinInfoWeiXinManager(){
		return "/join/customer/customerManage";
	}
	
	//查询所有
	/*@RequestMapping(value="/findAllcustomer")
	@ResponseBody
	public Map<String,Object> findCustomerAddress(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){	
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");		
		Page<TblCustomerApplyAddress> lists = applyAddressService.findCustomerAddress(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		return map ;
	}*/		
	//条件查询
	@RequestMapping(value = "/findCustomer")
	@ResponseBody
	public Map<String, Object> findCustomerList(HttpServletRequest req, HttpServletResponse res,
			TblCustomerApplyAddressList tblCustomerApplyAddressList, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {
		tblCustomerApplyAddressList.setOffset((pageNumber - 1) * pageSize);
		tblCustomerApplyAddressList.setLimit(pageSize);
		// 得到总共行数
		int count = applyAddressService.findCustomerCount(tblCustomerApplyAddressList);
		Map<String, Object> map = new HashMap<String, Object>();
		List<TblCustomerApplyAddress> orderList = applyAddressService.findCustomerList(tblCustomerApplyAddressList);
		map.put("rows", orderList);
		map.put("total", count);
		return map;
	}
	
	/**
	 * 导出Excel
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/JoinCustomerListExport")
	@ResponseBody
	public void JoinCustomerListExport(ServletRequest request, HttpServletResponse response) {
		applyAddressService.JoinCustomerListExport(request, response);
	}
	
}