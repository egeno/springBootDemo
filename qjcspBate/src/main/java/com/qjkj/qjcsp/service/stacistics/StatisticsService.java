package com.qjkj.qjcsp.service.stacistics;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.mapper.StatisticsMapper;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import com.qjkj.qjcsp.util.UserUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class StatisticsService {

	@Autowired
	private StatisticsMapper statisticsMapper;

	Map<String, Object> map = null;

	// private List<Integer> rowspanList;//合并单元格
	private List<List<Integer>> rowspanList;
	private List<Map<String, Object>> resultList;// 结果数据

	/**
	 * 每日商品供销统计报表
	 * 
	 * @author panmingke
	 * @date 2016-01-18
	 */
	public Map<String, Object> findAllGoodsSupplyReport(String psdate, int pageNumber, int pageSize, int companyId,
			String foodName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("psdate", psdate);
		// UserUtils userUtils = new UserUtils();
		// param.put("companyId", userUtils.getCompanyId());
		param.put("companyId", companyId);
		param.put("group", "goods_id");
		param.put("foodName", foodName);
		Long total = statisticsMapper.findAllSupllyCount(param);
		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);

		// 全部商品及模型供销统计
		resultList = new ArrayList<Map<String, Object>>();
		rowspanList = new ArrayList<List<Integer>>();
		if (total != 0) {
			// 所有商品的供销总计
			List<Map<String, Object>> goodsList = statisticsMapper.findAllSupllyByGoods(param);
			if (pageNumber == 1) {
				getAllSupplyData(statisticsMapper.findAllSupllyByModel(param), "全部商品");
			}
			param.put("list", goodsList);
			getSupplyData(goodsList, statisticsMapper.findAllSupllyByGoodsAndModel(param));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("psdate", psdate);
		map.put("rowspan", rowspanList);
		map.put("rows", resultList);
		map.put("total", total);
		return map;
	}

	/**
	 * 每日商品供销统计报表导出 每日销售统计报表
	 * 
	 * @author panmingke
	 * @date 2016-01-18
	 */
	public void findAllGoodsSupplyExport(HttpServletResponse response, String psdate, int companyId, String foodName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("psdate", psdate);
		// UserUtils userUtils = new UserUtils();
		// param.put("companyId", userUtils.getCompanyId());
		try {
			param.put("foodName", URLDecoder.decode(foodName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.put("companyId", companyId);
		param.put("offset", null);
		param.put("limit", null);

		// 全部商品及模型供销统计
		resultList = new ArrayList<Map<String, Object>>();
		rowspanList = new ArrayList<List<Integer>>();
		// List<List<Integer>> rows
		// 所有商品的供销总计
		List<Map<String, Object>> goodsList = statisticsMapper.findAllSupllyByGoods(param);
		if (goodsList != null && goodsList.size() > 0) {
			getAllSupplyData(statisticsMapper.findAllSupllyByModel(param), "全部商品");
			param.put("list", null);
			getSupplyData(goodsList, statisticsMapper.findAllSupllyByGoodsAndModel(param));
		}

		String tableName = "每日商品供销统计报表";
		Integer[] sheets = { 150, 100, 100, 100, 100, 100 };
		String[] titles = { "商品名称", "类型", "供货份数", "销售份数", "未售份数", "清货份数" };
		String[] keys = { "name", "modName", "supplyNum", "soldNum", "unsoldNum", "clearNum" };
		ExportExcelUtil.exportExcel(response, tableName, sheets, titles, rowspanList, keys, resultList);
	}

	// 每日全部柜子或商品总计[模型分组]
	private void getAllSupplyData(List<Map<String, Object>> list, String name) {
		int supplyNum = 0;
		int unsoldNum = 0;
		int soldNum = 0;
		int clearNum = 0;
		Map<String, Object> allmap = null;
		// 全部模型总计
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			if (map != null) {
				map.put("name", name);
				resultList.add(map);

				if (map.get("supplyNum") != null) {
					supplyNum += Integer.parseInt(map.get("supplyNum").toString());
				}
				if (map.get("unsoldNum") != null) {
					unsoldNum += Integer.parseInt(map.get("unsoldNum").toString());
				}
				if (map.get("soldNum") != null) {
					soldNum += Integer.parseInt(map.get("soldNum").toString());
				}
				if (map.get("clearNum") != null) {
					clearNum += Integer.parseInt(map.get("clearNum").toString());
				}
			}
		}
		// 全部柜子总计
		allmap = new HashMap<String, Object>();
		if (map != null) {
			allmap.put("id", map.get("id"));
			allmap.put("name", name);
			allmap.put("modName", "全天");
			allmap.put("supplyNum", supplyNum);
			allmap.put("unsoldNum", unsoldNum);
			allmap.put("soldNum", soldNum);
			allmap.put("clearNum", clearNum);
			resultList.add(allmap);
		}
		// rowspanList.add(resultList.size());//第一个合并的单元格
	}

	// 柜子或商品和模型分组
	private void getSupplyData(List<Map<String, Object>> keyList, List<Map<String, Object>> valList) {
		int num = 1;
		Map<String, Object> allmap = null;
		for (int i = 0; i < keyList.size(); i++) {
			map = keyList.get(i);
			num = 1;
			for (int j = 0; j < valList.size(); j++) {
				allmap = valList.get(j);
				if (map.get("id").equals(allmap.get("id"))) {
					num++;
					resultList.add(allmap);
				}
			}
			map.put("modName", "全天");
			resultList.add(map);
		}
	}

	/**
	 * 每日设备供销统计报表
	 * 
	 * @author panmingke
	 * @date 2016-01-18
	 */
	public Map<String, Object> findAllMachineSupplyReport(String psdate, int pageNumber, int pageSize, int companyId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("psdate", psdate);
		// UserUtils userUtils = new UserUtils();
		// param.put("companyId", userUtils.getCompanyId());
		param.put("companyId", companyId);
		param.put("group", "machine_id");
		Long total = statisticsMapper.findAllSupllyCount(param);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);

		// 全部设备及模型供销统计
		resultList = new ArrayList<Map<String, Object>>();
		rowspanList = new ArrayList<List<Integer>>();
		if (total != 0) {
			// 所有柜子的供销总计
			List<Map<String, Object>> machineList = statisticsMapper.findAllSupllyByMachine(param);
			if (pageNumber == 1) {
				getAllSupplyData(statisticsMapper.findAllSupllyByModel(param), "全部柜子");
			}
			param.put("list", machineList);
			getSupplyData(machineList, statisticsMapper.findAllSupllyByMachineAndModel(param));
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("psdate", psdate);
		map.put("rowspan", rowspanList);
		map.put("rows", resultList);
		map.put("total", total);
		return map;
	}

	/**
	 * 每日设备供销统计报表导出
	 * 
	 * @author panmingke
	 * @date 2016-01-18
	 */
	public void findAllMachineSupplyExport(HttpServletResponse response, String psdate, int companyId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("psdate", psdate);
		// UserUtils userUtils = new UserUtils();
		// param.put("companyId", userUtils.getCompanyId());
		param.put("companyId", companyId);
		param.put("offset", null);
		param.put("limit", null);

		// 全部设备及模型供销统计
		resultList = new ArrayList<Map<String, Object>>();

		rowspanList = new ArrayList<List<Integer>>();

		// 所有柜子的供销总计
		List<Map<String, Object>> machineList = statisticsMapper.findAllSupllyByMachine(param);
		if (machineList != null && machineList.size() > 0) {
			getAllSupplyData(statisticsMapper.findAllSupllyByModel(param), "全部柜子");
			param.put("list", null);
			getSupplyData(machineList, statisticsMapper.findAllSupllyByMachineAndModel(param));
		}

		String tableName = "每日柜子供销统计报表";
		Integer[] sheets = { 150, 100, 100, 100, 100, 100 };
		String[] titles = { "柜子名称", "类型", "供货份数", "销售份数", "未售份数", "清货份数" };
		String[] keys = { "name", "modName", "supplyNum", "soldNum", "unsoldNum", "clearNum" };
		ExportExcelUtil.exportExcel(response, tableName, sheets, titles, rowspanList, keys, resultList);
	}

	/**
	 * 每日设备供销统计图表
	 * 
	 * @author panmingke
	 * @date 2016-01-18
	 */
	public JSONObject findAllMachineSupplyChart(String psdate, int pageNumber, int pageSize, int companyId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("psdate", psdate);
		// UserUtils userUtils = new UserUtils();
		// param.put("companyId", userUtils.getCompanyId());
		param.put("companyId", companyId);
		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		JSONObject jsonObject = new JSONObject();
		JSONArray datas = new JSONArray();
		List<Map<String, Object>> machineList = statisticsMapper.findAllSupllyByMachine(param);
		if (machineList != null && machineList.size() > 0) {
			JSONArray categories = new JSONArray();
			JSONArray supplyNum = new JSONArray();
			JSONArray unsoldNum = new JSONArray();
			JSONArray soldNum = new JSONArray();
			JSONArray clearNum = new JSONArray();
			for (int i = 0; i < machineList.size(); i++) {
				map = machineList.get(i);
				categories.add(map.get("name"));
				supplyNum.add(map.get("supplyNum"));
				unsoldNum.add(map.get("unsoldNum"));
				soldNum.add(map.get("soldNum"));
				clearNum.add(map.get("clearNum"));
			}

			jsonObject.put("categories", categories);

			JSONObject date = new JSONObject();
			date.put("name", "供货");
			date.put("data", supplyNum);
			datas.add(date);

			date = new JSONObject();
			date.put("name", "已售");
			date.put("data", soldNum);
			datas.add(date);

			date = new JSONObject();
			date.put("name", "未售");
			date.put("data", unsoldNum);
			datas.add(date);

			date = new JSONObject();
			date.put("name", "清货");
			date.put("data", clearNum);
			datas.add(date);
		}

		JSONObject result = new JSONObject();
		result.put("psdate", psdate);
		result.put("msc", jsonObject);
		result.put("snum", datas);
		return result;
	}
}