 package com.qjkj.qjcsp.web.recharge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.OrderByUserMap;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.entity.viewmodel.TblCustomerVo;
import com.qjkj.qjcsp.service.order.common.OrderCommonService;
import com.qjkj.qjcsp.service.recharge.RechargeService;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import com.qjkj.qjcsp.util.Servlets;

@Controller
@RequestMapping("recharge")
public class RechargeController {
	private Logger logger=LoggerFactory.getLogger(RechargeController.class);
	private static final String PAGE_SIZE = "10";
	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private OrderCommonService orderCommonService;
	@RequestMapping("rechargeMain")
	public String rechargeMain(){
		return "recharge/rechargeMain";
	}
	
	@RequestMapping("findAllrechargeList")
	@ResponseBody
	public Map<String,Object> findAllrechargeList(HttpServletRequest req,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "orderNum", defaultValue = "") String orderNum,
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "orderStatus", defaultValue = "") String orderStatus,
			@RequestParam(value = "modeNum") String modeNum
			){
		Map<String,Object>map1=new HashMap<String,Object>();
		map1.put("offset", (pageNumber - 1) * pageSize);
		map1.put("limit", pageSize);
		map1.put("orderNum", orderNum);
		map1.put("userName", userName);
		map1.put("modeNum", modeNum);
		map1.put("orderStatus", orderStatus);
		List<Map<String,Object>> lists=rechargeService.findAllrechargeList(map1);
		Long total=rechargeService.findAllrechargeListCount(map1);
		Map<String,Object> map=new HashMap<String,Object>();
//		if(lists!=null){
//			map.put("total", lists.size());
//		}else{
//			map.put("total", 0);
//		}
		map.put("total", total);
		map.put("rows", lists);
		
		return map;
	}
	
	@RequestMapping("/rechargeStatisticsMain")
	public String rechargeStatisticsMain(){
		return "recharge/rechargeStatistics";
	}
	
	//充值统计
	@RequestMapping("rechargeStatistics")
	@ResponseBody
	public Map<String,Object> rechargeStatistics(HttpServletRequest req,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "rechargeStartTime", defaultValue = "") String rechargeStartTime,
			@RequestParam(value = "rechargeEndTime", defaultValue = "") String rechargeEndTime
			){
		Map<String,Object>map1=new HashMap<String,Object>();
		map1.put("offset", (pageNumber - 1) * pageSize);
		map1.put("limit", pageSize);
		map1.put("rechargeStartTime", rechargeStartTime);
		map1.put("rechargeEndTime", rechargeEndTime);
		List<Map<String,Object>> lists=rechargeService.rechargeStatistics(map1);
		List<Map<String,Object>> lists2=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String,Object>();
		if(lists==null||lists.get(0)==null||lists.size()==0){
			map.put("total", 0);
			map.put("rows", lists2);
		}else{
			map.put("total", lists.size());
			map.put("rows", lists);
		}
		return map;
	}
	//Gekko 账户收支明细
	@RequestMapping("/receiptAndExpenditure")
	public String receiptAndExpenditure(){
		return "recharge/receiptAndExpenditure";
	}
	@RequestMapping("/receiptAndExpenditurePage")
	@ResponseBody
	public Map<String,Object> receiptAndExpenditurePage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String,Object> map = new HashMap<String,Object>() ;
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("offset", (pageNumber - 1) * pageSize);
		searchParams.put("limit", pageSize);
		Long total=rechargeService.findReceiptAndExpenditureCount(searchParams);
		List<Map<String,Object>> lists = rechargeService.findReceiptAndExpenditure(searchParams) ;
		map.put("total", total);
		map.put("rows", lists);
		return map;
	}
	@RequestMapping("/receiptAndExpenditureExport")
	public  void receiptAndExpenditureExport(ServletRequest request,HttpServletResponse response){
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		List<Map<String,Object>> lists = rechargeService.findReceiptAndExpenditure(searchParams) ;
		if(lists==null||lists.get(0)==null||lists.size()==0){
			lists=new ArrayList<Map<String, Object>>();
		}
		List<List<Integer>> rows=new ArrayList<List<Integer>>();
		String tableName = "用户账户收支明细";
		Integer[] sheets = { 220, 100, 100,100,100,100,100,100,100,200};
		String[] titles = { "订单号", "子订单流水号", "用户ID","用户名","手机号码","订单状态","支付方式","订单类型","交易金额","付款时间"};
		String[] keys = {"orderNum", "orderChildNum", "customerId","customerName","mobile","orderStatus","payMode","orderType","totalMoney","payTime"};
		ExportExcelUtil.exportExcel(response, tableName, sheets, titles, rows, keys, lists);
	}
	//账户收支明细 end
	@RequestMapping("/rechargeStatisticsExport")
	public  void goodsSellReportExport(HttpServletResponse response,
			@RequestParam(value = "rechargeStartTime", defaultValue = "") String rechargeStartTime,
			@RequestParam(value = "rechargeEndTime", defaultValue = "") String rechargeEndTime){
		Map<String,Object>map1=new HashMap<String,Object>();
		map1.put("rechargeStartTime", rechargeStartTime);
		map1.put("rechargeEndTime", rechargeEndTime);
		List<Map<String, Object>> rechargeStatistics = rechargeService.rechargeStatistics(map1);
		if(rechargeStatistics==null||rechargeStatistics.get(0)==null||rechargeStatistics.size()==0){
			rechargeStatistics=new ArrayList<Map<String, Object>>();
		}
		List<List<Integer>> rows=new ArrayList<List<Integer>>();
		String tableName = "余额充值统计";
		Integer[] sheets = { 220, 100, 100};
		String[] titles = { "实际充值金额", "优惠金额", "总金额"};
		String[] keys = {"totalMoney", "realMoney", "activityMoney"};
		ExportExcelUtil.exportExcel(response, tableName, sheets, titles, rows, keys, rechargeStatistics);
	}
	
	
	/**
	 * 以下为结算记录相关
	 * 返回充值订单对应消费订单
	 * @return 页面VIEW
	 */
	@RequestMapping("listRechargeForOrderMain")
	public String listRechargeForOrderMain(){
		return "recharge/listRechargeForOrderMain";
	}
	@RequestMapping("findRechargeForOrderPage")
	@ResponseBody
	public Map<String,Object> findRechargeForOrderPage(ServletRequest request, @RequestParam("rechargeId") String rechargeId){
		return orderCommonService.findTblBalanceAmounts(request, rechargeId);
	}
	@RequestMapping("/getByPendingSettlements")
	@ResponseBody
	public String getByPendingSettlements(){
		int state=orderCommonService.updateSettlements();
		System.out.println(state+"%");
		return state+"";
	}
	@RequestMapping("findAllrechargeList1")
	@ResponseBody
	public Map<String,Object> findAllrechargeList1(HttpServletRequest req,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize
			){
		Map<String, Object> map1 =  Servlets.getParametersStartingWith(req, "search_");
		map1.put("offset", (pageNumber - 1) * pageSize);
		map1.put("limit", pageSize);
		List<Map<String,Object>> lists=rechargeService.findAllrechargeList1(map1);
		Long total=rechargeService.findAllrechargeListCount(map1);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", lists);
		return map;
	}
	/**
	 * 结算记录相关结束
	 */
}
