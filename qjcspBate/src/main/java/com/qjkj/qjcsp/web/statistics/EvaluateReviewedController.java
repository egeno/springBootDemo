package com.qjkj.qjcsp.web.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.EvaluateSearch;
import com.qjkj.qjcsp.service.goodsevaluate.EvaluateReviewedService;
import com.qjkj.qjcsp.util.ExportExcelUtil;

/**
 * 评价内容审核管理
 *
 * @author xlk
 * @date 2016-08-19
 */
@Controller
@RequestMapping("evaluatereviewed")
public class EvaluateReviewedController {
	private List<List<Integer>>  rowspanList;
	private static final String PAGE_SIZE = "10";
	@Autowired
	private EvaluateReviewedService evaluateReviewedService;
	
	/**
	 * 跳转到评价内容审核管理页面
	 *
	 * @author xlk
	 */
	@RequestMapping("/evaluatereviewed")
	public String evaluatereviewed() {
		return "evaluatereviewed/evaluatereviewed";
	}
	
	/**
	 * 按条件查找评价内容
	 *
	 * @author xlk
	 */
	@RequestMapping("/findAllevaluatereviewedList")
	@ResponseBody
	public Map<String, Object> operateRefund(EvaluateSearch evaluateSearch,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {
		evaluateSearch.setOffset((pageNumber-1)*pageSize);
		evaluateSearch.setLimit(pageSize);
		Map<String, Object> returnJson = new HashMap<String, Object>();
		long total=evaluateReviewedService.selectCount(evaluateSearch);
		List<Map<String, Object>> tblGoodsEvaluate=evaluateReviewedService.findEvaluateList(evaluateSearch);
		returnJson.put("rows",tblGoodsEvaluate );
		returnJson.put("total", total);
		return returnJson;
	}
	
	/**
	 * 评论通过
	 *
	 * @author xlk
	 */
	@RequestMapping("/evaluatereviewedPass")
	@ResponseBody
	public Map<String, Object> evaluatereviewedPass(@RequestBody List<Integer> arrayObj)
	{
		Map<String, Object> returnJson=new HashMap<String, Object>();
		try {
			evaluateReviewedService.evaluatereviewedPass(arrayObj);
			returnJson.put("message", "成功");
			returnJson.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("message", "失败");
			returnJson.put("status", Boolean.FALSE);
		}

		return returnJson;
	}
	/**
	 * 评论不通过
	 *
	 * @author xlk
	 */
	@RequestMapping("/evaluatereviewedOut")
	@ResponseBody
	public Map<String, Object> evaluatereviewedOut(@RequestBody List<Integer> arrayObj)
	{
		Map<String, Object> returnJson=new HashMap<String, Object>();
		try {
			evaluateReviewedService.evaluatereviewedOut(arrayObj);
			returnJson.put("message", "成功");
			returnJson.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put("message", "失败");
			returnJson.put("status", Boolean.FALSE);
		}

		return returnJson;
	}
	/**
	 * 评论审核导出数据 
	 * @author xlk
	 * @date 2016年8月19日
	 *
	 */
	@RequestMapping("/evaluatereviewedExport")
	public void businessExport(HttpServletResponse response, EvaluateSearch evaluateSearch) {
		rowspanList = new ArrayList<List<Integer>>();
		List<Map<String, Object>> orderRefundLists = evaluateReviewedService
				.evaluatereviewedExport(evaluateSearch);
		String tableName = "评论审核报表";
		Integer[] sheets = {180, 100, 240, 240,100 , 100};
		String[] titles = { "客户手机号码","客户名称","菜品名称", "评价内容","评价时间 ","是否通过"};
		String[] keys = { "customerPhone","customerName","goodsName", "goodsEvaluateContent","createTime","isverify"};
		ExportExcelUtil.exportExcel(response, tableName, sheets, titles, rowspanList, keys, orderRefundLists);
	}
}
