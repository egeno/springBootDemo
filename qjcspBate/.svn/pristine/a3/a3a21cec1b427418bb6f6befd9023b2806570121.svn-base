package com.qjkj.qjcsp.web.refund;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qjkj.qjcsp.entity.OrderRefund;
import com.qjkj.qjcsp.entity.RefundSearch;
import com.qjkj.qjcsp.entity.TblOrderRefund;
import com.qjkj.qjcsp.service.refund.OrderRefundService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 预定订单线下退款管理
 *
 * @author cjw
 * @date 2016-04-14
 */
@Controller
@RequestMapping("orderRefund")
public class OrderRefundController {
	private List<List<Integer>>  rowspanList;
	private static Logger logger = LoggerFactory.getLogger(OrderRefundController.class);

	private static final String PAGE_SIZE = "10";

	@Autowired
	private OrderRefundService orderRefundService;
	/**
	 * 跳转到运维人员预审页面
	 *
	 * @author cjw
	 */
	@RequestMapping("/operateRefund")
	public String operateRefund(Model model) {
		List<TblOrderRefund> tblOrderRefunds = orderRefundService.getCompanyIdAndName();
		model.addAttribute("data", tblOrderRefunds);
		return "refund/orderRefundOperate";
	}
    /*运维人员预审查询*/
	@RequestMapping("/findAllRefundList")
	@ResponseBody
	public Map<String, Object> findAllRefundList(HttpServletRequest req, HttpServletResponse res,
			RefundSearch refundSearch,
//			@RequestParam("companyId") Integer companyId,
			// @RequestParam("orderNum") String orderNum,
			// @RequestParam("refundStartTime") String refundStartTime,
			// @RequestParam("refundEndTime") String refundEndTime,
			// @RequestParam("verifyStatus") String verifyStatus,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {


		refundSearch.setOffset((pageNumber-1)*pageSize);
		refundSearch.setLimit(pageSize);
		long total = orderRefundService.findRefundCount(refundSearch);
		List<OrderRefund> orderRefunds = orderRefundService.findAllRefundList(refundSearch);
		Map<String, Object> returnJson = new HashMap<String, Object>();
		returnJson.put("rows", orderRefunds);
		returnJson.put("total", total);
		return returnJson;
	}
	@RequestMapping("/orderRefundOperatePass")
	@ResponseBody
	public Map<String, Object> orderRefundOperatePass(OrderRefund orderRefund)
	{
		int userId=Constants.getCurrendUser().getUserId();
		orderRefund.setBusinessUserId(Long.valueOf(userId));
		orderRefund.setBusinessCheckTime(DateFormat.dateFormatYMDHMS(new Date()));
		Map<String, Object> returnJson=new HashMap<String, Object>();
		try {
			// 运维人员预审通过
			orderRefundService.orderRefundOperatePass(orderRefund);
			returnJson.put("message", "预审通过成功");
			returnJson.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("预审通过失败：" + e.getMessage());
			returnJson.put("message", "预审通过失败");
			returnJson.put("status", Boolean.FALSE);
		}

		return returnJson;
	}
	
	@RequestMapping("orderRefundOperateRejectMain")
	public String orderRefundBusinessOutMain() {

		return "refund/businessout";
	}
	@RequestMapping("/orderRefundOperateReject")
	@ResponseBody
	public Map<String, Object> orderRefundOperateReject(OrderRefund orderRefund)
	{
		int userId=Constants.getCurrendUser().getUserId();
		orderRefund.setBusinessUserId(Long.valueOf(userId));
		orderRefund.setBusinessCheckTime(DateFormat.dateFormatYMDHMS(new Date()));
		Map<String, Object> returnJson=new HashMap<String, Object>();
		try {
			// 运维人员预审不通过
			orderRefundService.orderRefundOperateReject(orderRefund);
			returnJson.put("message", "预审不通过成功");
			returnJson.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("预审不通过失败：" + e.getMessage());
			returnJson.put("message", "预审不通过失败");
			returnJson.put("status", Boolean.FALSE);
		}

		return returnJson;
	}
	
	/**
	 * 退款确认  查询
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/orderRefundFinance")
	public String orderRefundFinance(Model model) {
		List<TblOrderRefund> tblOrderRefunds = orderRefundService.getCompanyIdAndName();
		model.addAttribute("data", tblOrderRefunds);
		return "refund/orderRefundFinance";
		//return "refund/orderRefundOperate";
	}
	//财务人员预审退款列表
	@RequestMapping("/findAllRefundFinanceList")
	@ResponseBody
	public Map<String, Object> findAllRefundFinanceList(HttpServletRequest req, 
			HttpServletResponse res,
			RefundSearch refundSearch,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize)
	{
		refundSearch.setOffset((pageNumber-1)*pageSize);
		refundSearch.setLimit(pageSize);
		Long count = orderRefundService.findAllRefundFinanceCount(refundSearch);
		List<OrderRefund> orderRefunds=orderRefundService.findAllRefundFinanceList(refundSearch);
		Map<String, Object> returnJson=new HashMap<String, Object>();
		returnJson.put("rows", orderRefunds);
		returnJson.put("total", count);
		return returnJson;
	}
	
	@RequestMapping("/orderRefundFinanceConfirmMain")
	public String orderRefundFinanceConfirmMain()
	{

		return "refund/confirmFinance";
		
	}
	@RequestMapping("/orderRefundFinanceConfirm")
	@ResponseBody
	public Map<String, Object> orderRefundFinanceConfirm(OrderRefund orderRefund)
	{
		Map<String, Object> returnJson=new HashMap<String, Object>();
		int userId = Constants.getCurrendUser().getUserId();
		orderRefund.setFinanceUserId(Long.valueOf(userId));
		orderRefund.setFinanceCheckTime(DateFormat.dateFormatYMDHMS(new Date()));
		try {
			// map1.put("", value)
			// 运维人员预审通过
			//在退款表中插入财务修改人员
		orderRefundService.orderRefundFinanceConfirm(orderRefund);
			returnJson.put("message", "确认通过成功");
			returnJson.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("确认通过失败：" + e.getMessage());
			returnJson.put("message", "确认通过失败");
			returnJson.put("status", Boolean.FALSE);
		}

		return returnJson;
	}
	
	/**
	 * 运维人员导出数据 
	 * @author yehx
	 * @date 2016年1月25日
	 *
	 */
	@RequestMapping("/businessExport")
	public void businessExport(HttpServletResponse response, RefundSearch refundSearch) {
		rowspanList = new ArrayList<List<Integer>>();
		List<Map<String, Object>> orderRefundLists = orderRefundService
				.findAllExportRefundBusinessList(refundSearch);
		String tableName = "运营人员预审报表";
		Integer[] sheets = {180, 240, 240, 200, 100, 100,120, 100, 120, 200, 100,100,100,200};
		                   //  订单号码  子订单号码 下单时间 支付方式 退款金额 退款状态 手机号 退款申请日期 预审人员 预审结果 财务确认人员 财务确认时间 
	// 下单时间order_time 支付方式pay_mode 退款金额tbl_order_child.refund_money 退款状态business_check_result 手机号mobile 退款申请日期refund_apply_time 预审人员business_user_id 预审结果business_check_result 财务确认人员finance_user_id 财务确认时间finance_check_time 
		String[] titles = { "商户名称","订单号码","订单子号码", "下单时间","支付方式 ","退款金额","是否设备故障","退款状态","手机号", "退款申请日期",  "预审人员", "预审结果", "财务确认人员", "财务确认时间" };
		String[] keys = { "companyName","orderNum","orderChildNum", "orderTime","payMode","refundMoney","alarmId","orderStatus","mobile", "refundApplyTime", 
				"businessUserName", "businessCheckResult", "financeUserName", "financeCheckTime" };
		ExportExcelUtil.exportExcel(response, tableName, sheets, titles, rowspanList, keys, orderRefundLists);
	}
	
	/**
	 * 财务倒出
	 * @param response
	 * @param refundSearch
	 */
	@RequestMapping("/financeExport")
	public void financeExport(HttpServletResponse response, RefundSearch refundSearch) {
		rowspanList = new ArrayList<List<Integer>>();
		List<Map<String, Object>> orderRefundLists = orderRefundService.findAllExportRefundFinanceList(refundSearch);
		String tableName = "财务人员确认报表";
		Integer[] sheets = { 180, 100, 220, 200, 100, 140, 220, 100, 100, 100 ,100,200};

		String[] titles = { "商户名称", "设备号", "订单号码", "下单时间","支付方式 ","手机号", "退款申请日期", "退款状态", "预审人员", "预审结果", "财务确认人员", "财务确认时间" };
		String[] keys = { "companyName", "machineId", "orderNum", "orderTime","payMode","mobile", "refundApplyTime", "orderStatus",
				"businessUserName", "businessCheckResult", "financeUserName", "financeCheckTime" };
		ExportExcelUtil.exportExcel(response, tableName, sheets, titles, rowspanList, keys, orderRefundLists);
	}

	
	
	
	
	
}
