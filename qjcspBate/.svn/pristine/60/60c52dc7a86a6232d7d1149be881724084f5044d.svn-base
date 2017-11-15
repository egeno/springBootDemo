package com.qjkj.qjcsp.service.stacistics;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import com.qjkj.qjcsp.util.UserUtils;

@Component
@Transactional
public class PaymentStatementService {

	@Autowired
	private TblOrderMapper tblOrderMapper;

	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;
	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;

	/**
	 * 查询所有订单信息
	 * 
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Map<String, Object>> findOrdersPage(Map<String, Object> param, int pageNumber, int pageSize) {
		param.put("state", DataStatusEnum.NORMAL.getValue());

//		 UserUtils userUtils = new UserUtils();
//		 param.put("companyId", userUtils.getCompanyId());
		Long total = tblOrderMapper.findByCount(param);

		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order", "o.order_time");
		param.put("sort", Sort.Direction.DESC);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (total != 0) {
			list = tblOrderMapper.findByList(param);
			for(Map<String, Object> mapm:list){
				if(mapm.get("orderStatus").equals("5")){
					mapm.put("orderStatus", "1");
				}
			}
		}
		Page<Map<String, Object>> page = new PageImpl<Map<String, Object>>(list, pageRequest, total);

		return page;
	}

	/**
	 * 根据子订单号获取订单详情
	 * 
	 * @param request
	 * @param orderChildNum
	 * @return
	 */
	public Map<String, Object> findOrderDetail(ServletRequest request,
			@RequestParam("orderChildNum") String orderChildNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> orderDetailsList = tblOrderDetailMapper.queryOrderDetailsbyOrderChildNum(orderChildNum);
		map.put("rows", orderDetailsList);

		return map;
	}

	/**
	 * 导出订单
	 * 
	 * @param param
	 * @param response
	 */
	public void orderListExport(ServletRequest request, HttpServletResponse response) {
		String companyName=request.getParameter("companyName");
		// System.out.println("转码前商户名称："+companyName);
		try {
			companyName=URLDecoder.decode(companyName,"UTF-8");
//			companyName=new String(companyName.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   // System.out.println("转码后商户名称："+companyName);
	    
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId",request.getParameter("companyId"));
		param.put("companyName", companyName);
		param.put("companyId",request.getParameter("companyId"));
		param.put("orderNum", request.getParameter("orderNum"));
		param.put("orderType", request.getParameter("orderType"));
		//param.put("modeNum", request.getParameter("modeNum"));
		param.put("payMode", request.getParameter("payMode"));
		param.put("mobile", request.getParameter("mobile"));
		param.put("startDate", request.getParameter("startDate"));
		param.put("endDate", request.getParameter("endDate"));
		param.put("orderStatus", request.getParameter("orderStatus"));
		param.put("orderChildStatus", request.getParameter("orderChildStatus"));
		param.put("order", "o.order_time");
		param.put("sort", Sort.Direction.DESC);
		// 根据条件查询订单
		List<Map<String, Object>> resultList = tblOrderMapper.exportExcelList(param);
		for(Map<String, Object> mapm:resultList){
			if(mapm.get("orderStatus").equals("待退款")){
				mapm.put("orderStatus", "已支付");
			}
		}
		//System.out.println("导出方法查询长度"+resultList.size());
		// 需减去优惠金额订单列表
		List<Map<String, Object>> changeList = new ArrayList<Map<String, Object>>();
		// 无需减去优惠金额订单列表
		List<Map<String, Object>> noChangeList = new ArrayList<Map<String, Object>>();
		for (int i = resultList.size() - 1; i >= 0; i--) {
			Map<String, Object> tempMap = resultList.get(i);
			changeList.add(tempMap);
			resultList.remove(i);
			for (int j = resultList.size() - 1; j >= 0; j--) {
				if (StringUtils.equals(resultList.get(j).get("orderNum").toString(),
						tempMap.get("orderNum").toString())) {
					noChangeList.add(resultList.get(j));
					resultList.remove(j);
				}
			}
			i = resultList.size();
		}

		for (int i = 0; i < changeList.size(); i++) {
			Map<String, Object> changeMap = changeList.get(i);
			changeMap.put("realMoney", new BigDecimal(changeMap.get("realMoney").toString())
					.subtract(new BigDecimal(changeMap.get("discountMoney").toString())));
			changeList.set(i, changeMap);
		}
		for (int i = 0; i < noChangeList.size(); i++) {
			Map<String, Object> noChangeMap = noChangeList.get(i);
			noChangeMap.put("discountMoney", "0.00");
			noChangeList.set(i, noChangeMap);
		}
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		newList.addAll(changeList);
		newList.addAll(noChangeList);
		
		String title = "订单对账单";
		String[] titles = { "加盟商名称", "柜子名称", "总订单号", "子订单号", "下单方式", "订单类型", "用户名(手机号)", "单品名称", "单品编号", "单品数量", "单品价格",
				"已退款金额","公司实收金额","下单时间", "订单总金额", "优惠金额", "实付金额", "付款时间", "支付流水号", "支付方式", "总订单状态","子订单状态", "加盟商开户行", "加盟商开户名称", "加盟商账号" };
		Integer[] sheets = { 150, 100, 200, 200, 100, 100, 150, 150, 100, 100, 100, 200, 100, 100, 100, 200, 300, 100,
				150, 150, 150, 150 };
		String[] keys = { "companyName", "machineName", "orderNum", "orderChildNum", "modeName", "orderType", "mobile",
				"goodsName", "goodsId", "goodsNum", "price","refoundMoney","actualMoney", "orderTime", "totalMoney", "discountMoney", "realMoney",
				"payTime", "payNumber", "payMode", "orderStatus","orderChildStatus",  "backNo", "cardName", "cardNo" };
		List<List<Integer>> rows = new ArrayList<List<Integer>>();
		ExportExcelUtil.exportExcel(response, title, sheets, titles, rows, keys, newList);

	}

	/**
	 * 根据订单号获取子订单
	 * 
	 * @param request
	 * @param orderNum
	 * @return
	 */
	public Map<String, Object> findOrderChild(ServletRequest request, @RequestParam("orderNum") String orderNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> orderDetailsList = tblOrderChildMapper.queryOrderChildbyOrderNum(orderNum);
		map.put("rows", orderDetailsList);

		return map;
	}
}
