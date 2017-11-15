package com.qjkj.qjcsp.web.goods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

import com.qjkj.qjcsp.service.basicsareamodel.AreaModelService;
import com.qjkj.qjcsp.service.goods.GoodsTemporaryService;
import com.qjkj.qjcsp.service.machine.MachineService;
import com.qjkj.qjcsp.util.ExportExcelUtil;

@Controller
@RequestMapping("goods/temporary")
public class GoodsTemporaryController {
	@Autowired
	private GoodsTemporaryService goodsTemporaryService;
	@Autowired
	private MachineService machineService;
	@Autowired
	private AreaModelService areaModelService;

	@RequestMapping("/toGoodsTemporary")
	public String toGoodsTemporary(HttpServletRequest request, @RequestParam("selectTime") String selectTime,
			@RequestParam("areaModelId") Long areaModelId) {
		request.setAttribute("machine", machineService.getMachineList());
		request.setAttribute("areamodel", areaModelService.getAreaModelList());
		request.setAttribute("selectTime", selectTime);
		request.setAttribute("areaModelId", areaModelId);
		return "goodsTemporary/listGoodsTemporaryMain";
	}

	@RequestMapping("/selectGoodsSaleCount")
	@ResponseBody
	public List<Map<String, Object>> selectGoodsSaleCount(String temporaryDate, Long machineId, Long areaModelId) {
		List<Map<String, Object>> list = goodsTemporaryService.selectGoodsSaleCount(temporaryDate, machineId,
				areaModelId);
		List<Map<String, Object>> list1 = goodsTemporaryService.selectGoodsSaleCount1(temporaryDate, machineId,
				areaModelId);
		list.addAll(list1);
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(i).get("goodsId") == list.get(j).get("goodsId")) {
					list.remove(j);
				}
			}
		}
		/*ArrayList<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> s : list) {
			if (Collections.frequency(result, s) < 1)
				result.add(s);
		}*/
		return list;
	}

	@RequestMapping("/goodsSaleCountExport")
	@ResponseBody
	public void goodsSaleCountExport(ServletRequest request, HttpServletResponse response,
			@RequestParam(value = "machineId", defaultValue = "") Long machineId,
			@RequestParam(value = "areaModelId", defaultValue = "") Long areaModelId,
			@RequestParam(value = "temporaryDate", defaultValue = "") String temporaryDate) {
		List<Integer> rowsdate = null;
		List<Map<String, Object>> list = goodsTemporaryService.selectGoodsSaleCount(temporaryDate, machineId,
				areaModelId);
		List<Map<String, Object>> list1 = goodsTemporaryService.selectGoodsSaleCount1(temporaryDate, machineId,
				areaModelId);
		list.addAll(list1);
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(i).get("goodsId") == list.get(j).get("goodsId")) {
					list.remove(j);
				}
			}
		}
		String title = "菜品预订零售数量统计";
		String[] titles = { "商品名称", "预订数量", "零售数量", "合计" };
		Integer[] sheets = { 300, 300, 300, 300 };
		String[] keys = { "goodsName", "ydCount", "lsCount", "sumCount" };
		ExportExcelUtil.exportExcel2(response, title, sheets, titles, rowsdate, keys, list);
	}
}
