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

import com.qjkj.qjcsp.entity.BasicsJoininfo;
import com.qjkj.qjcsp.entity.BasicsJoininfoList;
import com.qjkj.qjcsp.service.join.JoinInfoService;

@Controller
@RequestMapping("join/joininfo")
public class JoinInfoController {
	private static final String PAGE_SIZE = "10";
	@Autowired
	private JoinInfoService joinInfoService;
	/**
	 * 跳转页
	 * @return
	 */
	@RequestMapping(value = "/franchiseInformation") 
	public String findJoinInfoWeiXinManager(){
		return "/join/joininfo/franchiseInformation";
	}
	/**
	 * 按条件查询
	 * @param req
	 * @param res
	 * @param tblCustomerApplyAddressList
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/findJoininfo")
	@ResponseBody
	public Map<String, Object> findCustomerList(HttpServletRequest req, HttpServletResponse res,
			BasicsJoininfoList basicsJoininfo, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {
		basicsJoininfo.setOffset((pageNumber - 1) * pageSize);
		basicsJoininfo.setLimit(pageSize);
		// 得到总共行数
		int count = joinInfoService.findJoinInfoCount(basicsJoininfo);
		Map<String, Object> map = new HashMap<String, Object>();
		List<BasicsJoininfo> orderList = joinInfoService.findJoinInfoList(basicsJoininfo);
		map.put("rows", orderList);
		map.put("total", count);
		return map;
	}
}
