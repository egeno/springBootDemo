package com.qjkj.qjcsp.web.pick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.entity.pick.OrderPickSearch;
import com.qjkj.qjcsp.entity.pick.PickPo;
import com.qjkj.qjcsp.service.pick.OrderPickSearchService;
import com.qjkj.qjcsp.service.stacistics.GoodsSellReportService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UserUtils;

/**
 * 订单取餐记录查询
 * 
 * @author yehx
 * @date 2016年1月19日 下午5:36:28
 */
@Controller
@RequestMapping("pickSearch")
// @Scope("prototype")
public class OrderPickSearchController {
	private static Logger logger = LoggerFactory.getLogger(OrderPickSearchController.class);
	private static final String PAGE_SIZE = "10";
	@Autowired
	private OrderPickSearchService orderPickSearchService;
	
	
	@Autowired
	private GoodsSellReportService goodsSellReportService;

	@RequestMapping("/pickSearchMain")
	public String pickSearchMain(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserUtils u = new UserUtils();
		// 判断是否是全家科技
		Boolean flag = u.isUserofQJKJ();
		// flag=false;
		// 得到公司的id
		int companyId = Constants.getCurrendUser().getCompanyId();
		map.put("flag", flag);
		map.put("companyId", companyId);
		//得到公司的id和公司名称
	    List<CompanyPo> lists=goodsSellReportService.getCompanyIdAndName(map);
	    //得到第一个公司id
	    // 根据公司id得到该公司下设备
	    List<OrderPickSearch> orderPickSearchs=new ArrayList<OrderPickSearch>();
	    if(lists!=null&&lists.size()>0){
	    	companyId=lists.get(0).getCompanyId();
	  	    map.put("companyId", companyId);
	  	    orderPickSearchs= orderPickSearchService.getMachine(map);
	    }
		// lists= machineAreService.getMachineInfo(companyId);
		model.addAttribute("companyData", lists);
		model.addAttribute("data", orderPickSearchs);
		return "pick/pickSearch";
	}

	/**
	 * 查询取餐记录
	 * 
	 * @author yehx
	 * @date 2016年1月21日
	 * @param req
	 * @param res
	 * @param orderPickSearch
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 *
	 */
	@RequestMapping(value = "/findAllpickList")
	@ResponseBody
	public Map<String, Object> findAllpickList(HttpServletRequest req, HttpServletResponse res,
			OrderPickSearch orderPickSearch, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {
		orderPickSearch.setOffset((pageNumber - 1) * pageSize);
		orderPickSearch.setLimit(pageSize);
		// 判断是否是全家科技
//		orderPickSearch.setIsUserofQJKJ(new UserUtils().isUserofQJKJ());
		//orderPickSearch.setIsUserofQJKJ(false);
		//orderPickSearch.setCompanyId(Constants.getCurrendUser().getCompanyId());
		//orderPickSearch.setCompanyId(70);
		// orderPickSearch.set
		// 得到总共行数
		int count = orderPickSearchService.findAllPickCount(orderPickSearch);
		Map<String, Object> map = new HashMap<String, Object>();
		List<PickPo> orderPickList = orderPickSearchService.findAllpickList(orderPickSearch);
		// System.out.println(orderPickList);
		map.put("rows", orderPickList);
		map.put("total", count);
		return map;
	}
	@RequestMapping("getMachine")
	@ResponseBody
	public List<OrderPickSearch> getMachine(@RequestParam("companyId")String companyId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("companyId", Long.valueOf(companyId));
		return  orderPickSearchService.getMachine(map);
	}

}
