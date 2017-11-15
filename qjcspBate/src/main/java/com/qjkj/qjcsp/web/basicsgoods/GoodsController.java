package com.qjkj.qjcsp.web.basicsgoods;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.service.basicsareamodel.AreaModelService;
import com.qjkj.qjcsp.service.goods.GoodsInfoService;
import com.qjkj.qjcsp.service.machine.MachineService;
import com.qjkj.qjcsp.service.order.api.OrderDetailService;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import com.qjkj.qjcsp.web.BaseController;

@Controller
@RequestMapping("goods")
public class GoodsController extends BaseController {
	@Autowired
	private GoodsInfoService goodsService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private MachineService machineService;
	@Autowired
	private AreaModelService areaModelService;

	@RequestMapping("/getGoodsList")
	@ResponseBody
	public List<Map<String, Object>> getAreaModelList(BasicsGoods goods) {
		return goodsService.getGoodsList(goods);
	}

	@RequestMapping("/getAllGoodsList")
	@ResponseBody
	public List<Map<String, Object>> getAllGoodsList(Long goodsCategoryId) {
		return goodsService.getAllGoodsList(goodsCategoryId);
	}

	@RequestMapping("/toGoodPreNum")
	public String toFoodPreNum(HttpServletRequest request) {
		request.setAttribute("machine", machineService.getMachineList());
		request.setAttribute("areamodel", areaModelService.getAreaModelList());
		return "goodsPreNum/goodsPreNumList";
	}

	@RequestMapping("/selectFoodPreNum")
	@ResponseBody
	public Map<String, Object> selectFoodPreNum(@RequestParam("machineId") Long machineId,
			@RequestParam("areaModelId") Long areaModelId, @RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime, @RequestParam("foodName") String foodName,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {
		return orderDetailService.queryFoodsPreNum(startTime, endTime, machineId, areaModelId, foodName, pageNumber,
				pageSize);
	}

	@RequestMapping("/foodPreNumExportExcel")
	@ResponseBody
	public void exportExcel(ServletRequest request, HttpServletResponse response,
			@RequestParam("machineId") Long machineId, @RequestParam("areaModelId") Long areaModelId,
			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("foodName") String foodName) {
		Map<String, Object> map = new HashMap<String, Object>();
		String newFoodName = "";
		try {
			newFoodName = URLDecoder.decode(foodName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map = orderDetailService.queryFoodsPreNum(startTime, endTime, machineId, areaModelId, newFoodName, 1,
				999999999);
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("rows");
		String title = "菜品预订数量统计";
		String[] titles = { "商品名称", "预订数量" };
		Integer[] sheets = { 500, 500, };
		String[] keys = { "foodName", "preNum" };
		ExportExcelUtil.exportExcel2(response, title, sheets, titles, null, keys, list);
	}
}
