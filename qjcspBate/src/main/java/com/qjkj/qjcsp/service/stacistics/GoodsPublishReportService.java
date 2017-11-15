package com.qjkj.qjcsp.service.stacistics;

import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.GoodsPublishReportMapper;
import com.qjkj.qjcsp.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/23.
 */
@Service
public class GoodsPublishReportService {
	@Autowired
	private GoodsPublishReportMapper goodsPublishReportMapper;

	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;



	private List<Map<String, Object>> resultList;// 结果数据

	/**
	 *
	 * @param 发布日期
	 * @return 日发布商品统计
	 */
	public Map<String, Object> findGoodsPublishReportService(String psdate) {
		List<Integer> rowspanList=new ArrayList<Integer>();
		UserUtils userUtils = new UserUtils();
		// 获得商户ID
		Integer companyId = userUtils.getCompanyId();
		// 通过查询日期以及当前商户ID得到发布的商品ID
		List<Long> goodIds = findGoodsIdByPsdate(psdate, companyId);
		// 要返回的值
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> content = new ArrayList<Map<String, Object>>();
		for (Long goodsId : goodIds) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("psdate", psdate);
			param.put("companyId", companyId);
			param.put("goodsId", goodsId);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list = goodsPublishReportMapper.findReportBygoodsId(param);
			if (list.size() > 0) {
				content.addAll(list);
				for (Map<String, Object> map3 : list) {
					String goodsName = basicsGoodsMapper.selectByPrimaryKey(goodsId).getGoodsName();
					map3.put("goodsName", goodsName);
					map3.put("goodsId", goodsId);
				}
			}
			rowspanList.add(list.size());
		}
		map.put("rows", content);
		map.put("psdate", psdate);
		map.put("rowspan", rowspanList);
		return map;

	}

	/**
	 * 通过发布日期以及公司ID获取日发布统计报表的内容
	 */
	// private List<Map<String, Object>> goodsDailyPublishReport(String psdate,
	// Integer companyId) {
	// List<Map<String, Object>> content = new ArrayList<Map<String, Object>>();
	// List<Long> goodsIds=new ArrayList<Long>();
	// goodsIds= findGoodsIdByPsdate(psdate,companyId);
	// //需要合并的单元格的大小
	// rowspanList = new ArrayList<Integer>();
	//
	// for (Long goodsId : goodsIds) {
	// //查询参数
	// Map<String,Object> param=new HashMap<String, Object>();
	// param.put("psdate",psdate);
	// param.put("companyId",companyId);
	// param.put("goodsId",goodsId);
	// List<Map<String,Object>>
	// list=goodsPublishReportMapper.findReportBygoodsId(param);
	// if (list.size()>1)
	// {
	// content.addAll(list);
	// for (Map<String, Object> map : list) {
	// String
	// goodsName=basicsGoodsMapper.selectByPrimaryKey(goodsId).getGoodsName();
	// map.put("goodsName",goodsName);
	// map.put("goodsId",goodsId);
	// }
	// }
	// rowspanList.add(list.size());
	// }
	// return content;
	// }

	private List<Long> findGoodsIdByPsdate(String psdate, Integer companyId) {
		return goodsPublishReportMapper.findGoodsIdByPsdate(psdate, companyId);
	}

	public Map<String, Object> goodsPublishExport(String psdate) {
		return findGoodsPublishReportService(psdate);
	}
}
