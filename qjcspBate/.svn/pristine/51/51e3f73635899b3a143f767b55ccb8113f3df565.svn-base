package com.qjkj.qjcsp.web.statistics;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.core.shiro.ShiroUser;
import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.entity.GoodsSellReportPo;
import com.qjkj.qjcsp.entity.GoodsSellReportSearch;
import com.qjkj.qjcsp.service.stacistics.GoodsSellReportService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import com.qjkj.qjcsp.util.UserUtils;

/**
 * 商品销售统计
 * @author yehx
 * @date 2016年1月25日  下午7:16:25
 */
@RequestMapping("goodsSellReport")
@Controller
public class GoodsSellReportController {
	private static Logger logger = LoggerFactory.getLogger(GoodsSellReportController.class);
	private static final String PAGE_SIZE = "10";
//	@Autowired
//	private CompanyService companyService;
	@Autowired
	private GoodsSellReportService goodsSellReportService;
	/**
	 * 跳转到商品统计页面
	 * @author yehx
	 * @date 2016年1月25日
	 * @return
	 *
	 */
	@RequestMapping("/goodsSellReportMain")
	public String goodsSellReportMain(Model model){
		UserUtils u = new UserUtils();
		// 判断是否是全家科技
		Boolean flag = u.isUserofQJKJ();
		ShiroUser currendUser = Constants.getCurrendUser();
		// 得到登录用户的公司id
		int companyId = currendUser.getCompanyId();
				// 用作传参准备
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		map.put("companyId", companyId);
		//得到公司的id和公司名称
	    List<CompanyPo> lists=goodsSellReportService.getCompanyIdAndName(map);
	    model.addAttribute("data", lists);
		return "statistics/goodssellreport/goodsSellReport";
	}
	@RequestMapping("/findAllGoodsSellReport")
	@ResponseBody
	public Map<String,Object> findAllGoodsSellReport(HttpServletRequest req, HttpServletResponse res,
			GoodsSellReportSearch goodsSellReportSearch,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		goodsSellReportSearch.setOffset((pageNumber - 1) * pageSize);
		goodsSellReportSearch.setLimit(pageSize);
		// 得到总共行数
		//int count = goodsSellReportService.findAllGoodsSellCount(goodsSellReportSearch);
		Map<String, Object> map = new HashMap<String, Object>();
		//得到商品销售报表
		List<GoodsSellReportPo> goodsSellReportList = goodsSellReportService.findAllGoodsSellReportList(goodsSellReportSearch);
		int count=goodsSellReportService.getAllGoodsSellReportCount(goodsSellReportSearch);
		map.put("total", count);
		map.put("rows", goodsSellReportList);
		return map;
	}
	/**
	 * 商品销售统计导出
	 * @author yehx
	 * @date 2016年1月27日
	 * @param goodsSellReportSearch
	 * @param response
	 *
	 */
	@RequestMapping("/goodsSellReportExport")
	public  void goodsSellReportExport(GoodsSellReportSearch goodsSellReportSearch,HttpServletResponse response){
		if (!StringUtils.isEmpty(goodsSellReportSearch.getFoodName())) {
			String fn="";
			try {
				fn = URLDecoder.decode(goodsSellReportSearch.getFoodName(),"UTF-8");
				goodsSellReportSearch.setFoodName(fn);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Map<String, Object>> goodsSellReportList = goodsSellReportService.goodsSellReportExport(goodsSellReportSearch);
		String tableName = "商品销售统计";
		Integer[] sheets = { 220, 100, 100, 200, 200, 100, 100, 100, 120, 200 };
		String[] titles = { "商品名称", "单价", "补货数", "下单数", "销售数", "销售金额"};
		String[] keys = {"goodsName", "unitPrice", "putGoodsCount", "orderCount", "sellCount", "sellMoney"};
		List<List<Integer>> rows=new ArrayList<List<Integer>>();
		ExportExcelUtil.exportExcel(response, tableName, sheets, titles, rows, keys, goodsSellReportList);
	}
}
