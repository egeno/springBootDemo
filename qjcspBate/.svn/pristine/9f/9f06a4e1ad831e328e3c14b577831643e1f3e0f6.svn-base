package com.qjkj.qjcsp.web.discountactivity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.GoodsSellReportPo;
import com.qjkj.qjcsp.entity.GoodsSellReportSearch;
import com.qjkj.qjcsp.entity.TblCustomerDiscountActivity;
import com.qjkj.qjcsp.service.discountactivity.CustomerDiscountActivityService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.web.statistics.GoodsSellReportController;

@Controller
@RequestMapping("discountActivity")
public class CustomerDiscountActivityController {
	
	private static Logger logger = LoggerFactory.getLogger(CustomerDiscountActivityController.class);
	private static final String PAGE_SIZE = "10";
	@Autowired
	private CustomerDiscountActivityService customerDiscountActivityService;
	
	@RequestMapping("/discountActivityMain")
	public String discountActivityMain(){
		return "discountactivity/discountActivityMain";
	}
	
	@RequestMapping("/findAllDiscountActivity")
	@ResponseBody
	public Map<String,Object> findAllGoodsSellReport(HttpServletRequest req, HttpServletResponse res,
			TblCustomerDiscountActivity tblCustomerDiscountActivity,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		tblCustomerDiscountActivity.setOffset((pageNumber - 1) * pageSize);
		tblCustomerDiscountActivity.setLimit(pageSize);
		// 得到总共行数
		//int count = goodsSellReportService.findAllGoodsSellCount(goodsSellReportSearch);
		Map<String, Object> map = new HashMap<String, Object>();
		//得到商品销售报表
		List<TblCustomerDiscountActivity> tblCustomerDiscountActivitys = customerDiscountActivityService.findAllDiscountActivityList(tblCustomerDiscountActivity);
//		System.out.println(goodsSellReportList);
		if(tblCustomerDiscountActivitys!=null){
			map.put("total", tblCustomerDiscountActivitys.size());
		}else{
			map.put("total", 0);
		}
		
		
		map.put("rows", tblCustomerDiscountActivitys);
		
		return map;
	}
	@RequestMapping("/adddiscountActivityMain")
	public String adddiscountActivityMain(){
		return "discountactivity/adddiscountActivity";
	}
	
	@RequestMapping("/adddiscountActivitySave")
	@ResponseBody
	public Map<String,Object> adddiscountActivitySave(TblCustomerDiscountActivity tblCustomerDiscountActivity){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//首先判断是否已经有这个活动优惠的活动了
			int count=customerDiscountActivityService.getHasCount();
			if(count>0){
				map.put("message", "新增失败,已经有活动优惠存在了");
				map.put("status", Boolean.FALSE);
				return map;
			}
			
			
			Date date = new Date();
			//int companyId = Constants.getCurrendUser().getCompanyId();
			int createUserId = Constants.getCurrendUser().getUserId();
			tblCustomerDiscountActivity.setEffectSymbol("1");
			tblCustomerDiscountActivity.setCreateUserId(Long.valueOf(createUserId));
			tblCustomerDiscountActivity.setModUserId(Long.valueOf(createUserId));
			tblCustomerDiscountActivity.setCreateTime(date);
			tblCustomerDiscountActivity.setLastModTime(date);
			// 新增分区操作
			customerDiscountActivityService.insertDiscountActivity(tblCustomerDiscountActivity);
			map.put("message", "新增完善用户资料后首单优惠成功");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增完善用户资料后首单优惠失败：" + e.getMessage());
			map.put("message", "新增完善用户资料后首单优惠失败");
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}
		return map;
	}
	
	/**
	 * 
	 * @author yehx
	 * @date 2016年2月23日
	 * @return
	 *
	 */
	@RequestMapping("editdiscountActivityMain")
	public String editdiscountActivityMain(){
		return "discountactivity/editdiscountActivity";
	}
	
	@RequestMapping("editdiscountActivitySave")
	@ResponseBody
	public Map<String,Object> editdiscountActivitySave(TblCustomerDiscountActivity tblCustomerDiscountActivity){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			Date date = new Date();
			//int companyId = Constants.getCurrendUser().getCompanyId();
			int createUserId = Constants.getCurrendUser().getUserId();
			tblCustomerDiscountActivity.setModUserId(Long.valueOf(createUserId));
			tblCustomerDiscountActivity.setLastModTime(date);
			// 新增分区操作
			customerDiscountActivityService.updateDiscountActivity(tblCustomerDiscountActivity);
			map.put("message", "修改完善用户资料后首单优惠成功");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改完善用户资料后首单优惠失败：" + e.getMessage());
			map.put("message", "修改完善用户资料后首单优惠失败");
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}
		return map;
	}
	
}
