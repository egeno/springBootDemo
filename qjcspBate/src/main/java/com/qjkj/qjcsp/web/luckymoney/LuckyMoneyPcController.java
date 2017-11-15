package com.qjkj.qjcsp.web.luckymoney;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.luckymoney.LuckyMoneyPcService;
import com.qjkj.qjcsp.util.Servlets;

/**
 * 
 * @author wsk 2016年3月30日21:24:20
 *
 */
@Controller
@RequestMapping("luckyMoney")
public class LuckyMoneyPcController {
	
	@Autowired
	private LuckyMoneyPcService luckyMoneyService;
	
	private static final String PAGE_SIZE = "10";
	/*
	 * 跳转红包充值主页
	 */
	@RequestMapping(value="/listLuckyMoney")
	public String listluckyMoney(){
		return "luckymoneyrecharge/listLuckyMoney";
	}
	
	/*
	 * 查询红包
	 */
	@RequestMapping(value="/findLuckyMoney")
	@ResponseBody
	public Map<String,Object> findluckyMoney(HttpServletRequest req, HttpServletResponse res,ServletRequest request,
		    @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		        //得到页面传来所有属性
				Map<String, Object> map = Servlets.getParametersStartingWith(request, "search_");
				
				map.put("offset", (pageNumber-1) * pageSize);
				map.put("limit", pageSize);
		
		return luckyMoneyService.findluckyMoney(map);
	}
	/*
	 * 跳转红包添加页面并且得到奖励等级
	 */
	@RequestMapping(value="/addlucky")
	public String addlucky(HttpServletRequest req){
		req.setAttribute("data", luckyMoneyService.findprideGrade());
		return "luckymoneyrecharge/addLuckyMoney";
	}
	
	/*
	 * 添加红包
	 */
	@RequestMapping(value="/saveLuckyMoney")
	@ResponseBody
	public  Map<String, Object> saveLuckyMoney(HttpServletRequest res) throws ParseException{
		Map<String, Object> map = null;
		try {
			map = luckyMoneyService.saveLuckyMoney(res);
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			map.put("status", false);
			map.put("message", "后台错误");
		}
		
		return map;
	}

}
