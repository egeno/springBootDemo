package com.qjkj.qjcsp.web.join;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.TblWeixinLeague;
import com.qjkj.qjcsp.entity.TblWeixinLeagueList;
import com.qjkj.qjcsp.service.join.JoinInfoWeixinService;


@Controller
@RequestMapping("join/weixin")
public class JoinInfoWeiXinController {
	
	private static final String PAGE_SIZE = "10";
	@Autowired
	private JoinInfoWeixinService joinInfoWeixinService;
	
	/**
	 *  微信查询跳转页  
	 */
	
	@RequestMapping(value = "/joinManage") 
	public String findJoinInfoWeiXinManager(){
		return "/join/weixin/joinManage";
	}
	

	
	/*@RequestMapping("/findWeiXinList")
	@ResponseBody
	public Map<String, Object> addIngredientsType(HttpServletRequest req) {
		String str = RequestData.getRequestPostJson(req);
		JSONObject json = JSONObject.fromObject(str);
		System.out.println(json);
		return joinInfoWeixinService.findWeiXinList(json);
	}*/
	
	@RequestMapping(value = "/findWinxinLeague")
	@ResponseBody
	public Map<String, Object> findWinxinLeague(HttpServletRequest req, HttpServletResponse res,
			TblWeixinLeagueList tblWeixinLeague, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {
		tblWeixinLeague.setOffset((pageNumber - 1) * pageSize);
		tblWeixinLeague.setLimit(pageSize);
		// 得到总共行数
		int count = joinInfoWeixinService.findWeixinLeagueCount(tblWeixinLeague);
		Map<String, Object> map = new HashMap<String, Object>();
		List<TblWeixinLeague> orderList = joinInfoWeixinService.findWeixinLeagueList(tblWeixinLeague);
		map.put("rows", orderList);
		map.put("total", count);
		return map;
	}
	
}
	

	
	
	
	
	

