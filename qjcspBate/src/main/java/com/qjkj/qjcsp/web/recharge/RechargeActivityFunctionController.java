package com.qjkj.qjcsp.web.recharge;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.TblRechargeActivitySun;
import com.qjkj.qjcsp.service.recharge.RechargeActivityPreferencesService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateUtils;

@Controller
@RequestMapping("rechargeactivity")
public class RechargeActivityFunctionController {
	
	private Logger logger=LoggerFactory.getLogger(RechargeActivityFunctionController.class);
	private static final String PAGE_SIZE = "10";
	@Autowired
	private RechargeActivityPreferencesService rechargeActivityService;
	
	@RequestMapping("rechargeActivityMain")
	public String rechargeActivityMain(){
		Date date=new Date();
		Map<String,Object>map1=new HashMap<String,Object>();
		map1.put("offset", 0);
		map1.put("limit", 999999999);
		List<Map<String,Object>> list=rechargeActivityService.findAllrechargeActivityList(map1);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Date endTime=DateUtils.formatDate(map.get("endTime").toString());
			Long activityId=(Long) map.get("activityId");
			if (date.after(endTime)) {
				rechargeActivityService.updateActivityEffectSymbol(activityId);
			}
		}
		return "rechargeactivity/rechargeActivityMain";
	}
	
	@RequestMapping("findAllrechargeActivityList")
	@ResponseBody
	public Map<String,Object> findAllrechargeActivityList(HttpServletRequest req,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "rechargeStartTime", defaultValue = "") String rechargeStartTime,
			@RequestParam(value = "rechargeEndTime", defaultValue = "") String rechargeEndTime
			){
		Map<String,Object>map1=new HashMap<String,Object>();
		map1.put("offset", (pageNumber - 1) * pageSize);
		map1.put("limit", pageSize);
		map1.put("rechargeStartTime", rechargeStartTime);
		map1.put("rechargeEndTime", rechargeEndTime);
		Long total = rechargeActivityService.findByCount(map1);
		List<Map<String,Object>> lists=rechargeActivityService.findAllrechargeActivityList(map1);
		Map<String,Object> map=new HashMap<String,Object>();
		if(lists!=null){
			map.put("total",total);
		}else{
			map.put("total", 0);
		}
		map.put("rows", lists);
		return map;
	}
	
	@RequestMapping("/addrechargeActivityMain")
	public String adddiscountActivityMain(){
		return "rechargeactivity/addrechargeactivity";
	}
	@RequestMapping("/addrechargeActivitySave")
	@ResponseBody
	public Map<String,Object> addrechargeActivitySave(TblRechargeActivitySun rechargeActivitySun){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Date date = new Date();
			//int companyId = Constants.getCurrendUser().getCompanyId();
			int createUserId = Constants.getCurrendUser().getUserId();
			rechargeActivitySun.setCreateUserId(Long.valueOf(createUserId));
			rechargeActivitySun.setCreateTime(date);
			//
			rechargeActivityService.insertRechargeActivity(rechargeActivitySun);
			map.put("message", "新增充值优惠活动成功");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增充值优惠活动成功失败：" + e.getMessage());
			map.put("message", "新增充值优惠活动成功失败");
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}
		return map;
		
		
	}
	
	@RequestMapping("/editrechargeActivityMain")
	public String editrechargeActivityMain(){
		return "rechargeactivity/editrechargeactivity";
	}
	
	@RequestMapping("/editrechargeActivitySave")
	@ResponseBody
	public Map<String,Object> editrechargeActivitySave(TblRechargeActivitySun rechargeActivitySun){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			Date date = new Date();
			//int companyId = Constants.getCurrendUser().getCompanyId();
			int createUserId = Constants.getCurrendUser().getUserId();
			rechargeActivitySun.setModUserId(Long.valueOf(createUserId));
			rechargeActivitySun.setLastModifyTime(date);
			// 新增分区操作
			rechargeActivityService.updateRechargeActivity(rechargeActivitySun);
			map.put("message", "修改充值优惠活动成功");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改充值优惠活动失败：" + e.getMessage());
			map.put("message", "修改充值优惠活动失败");
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("/deleteRechargeActivity")
	@ResponseBody
	public Map<String,Object> deleteRechargeActivity(@RequestParam("activityId")String activityId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			rechargeActivityService.deleteRechargeActivity(Long.valueOf(activityId));
			map.put("message", "删除充值优惠活动成功");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除充值优惠活动失败：" + e.getMessage());
			map.put("message", "删除充值优惠活动失败");
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}
		return map;
	}
	
}
