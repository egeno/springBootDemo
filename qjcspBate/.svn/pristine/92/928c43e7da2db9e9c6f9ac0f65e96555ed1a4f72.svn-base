package com.qjkj.qjcsp.service.machine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblMachineAreaModelMostSale;
import com.qjkj.qjcsp.entity.TblMachineAreaModelMostSaleLog;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailNumMapper;
import com.qjkj.qjcsp.mapper.TblMachineAreaModelMostSaleLogMapper;
import com.qjkj.qjcsp.mapper.TblMachineAreaModelMostSaleMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.DateUtils;

@Service
@Transactional
public class MachineMostSaleService {
	@Autowired
	private TblMachineAreaModelMostSaleMapper tblMachineAreaModelMostSaleMapper;
	@Autowired
	private TblMachineAreaModelMostSaleLogMapper machineAreaModelMostSaleLogMapper;
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private BasicsMachineAreaModelMapper basicsMachineAreaModelMapper;
	@Autowired
	private TblGoodsRetailNumMapper goodsRetailNumMapper;
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;

	/**
	 * 根据公司Id获取分类模型对应的最高份数
	 * 
	 * @param machineId
	 */
	public List<Map<String, Object>> getMostSalsInfoByMachineId() {
		// List<Map<String, Object>> returnMap = new ArrayList<Map<String,
		// Object>>();
		/*
		 * Integer companyId = Constants.getCurrendUser().getCompanyId(); //
		 * 获取所有设备分配模型数据 List<Map<String, Object>> maps1 =
		 * tblMachineAreaModelMostSaleMapper.getAllMachineAreaModel(companyId);
		 * 
		 * // 循环查询取出已存的设备绑定模板数据，并移除相应的空的数据 for (int i = 0; i < maps1.size();
		 * i++) { Map<String, Object> map = maps1.get(i); Long machineId =
		 * (Long) map.get("machineId"); Long areaModelId = (Long)
		 * map.get("areaModelId"); // 设备所绑定的模板信息 List<Map<String, Object>> maps
		 * = tblMachineAreaModelMostSaleMapper.getMostSalsInfoByMachineId(
		 * companyId,areaModelId, machineId); // 如果已添加数据，添加到集合中并移除maps1中对应数据 if
		 * (maps.size() > 0) { maps1.remove(i); returnMap.add(maps.get(0)); } }
		 * returnMap.addAll(maps1);
		 */

		Integer companyId = Constants.getCurrendUser().getCompanyId();
		// 获取所有设备分配模型数据
		List<Map<String, Object>> maps1 = tblMachineAreaModelMostSaleMapper.getAllMachineAreaModel(companyId);
		List<Map<String, Object>> maps2 = tblMachineAreaModelMostSaleMapper
				.getMahcineAreaModelMostNumList((long) companyId);
		for (Map<String, Object> map : maps2) {
			for (Map<String, Object> map1 : maps1) {
				if (map.get("machineId").toString().equals(map1.get("machineId").toString())
						&& map.get("areaModelId").toString().equals(map1.get("areaModelId").toString())) {
					map1.put("mostSaleNum", map.get("mostSaleNum").toString());
				}
			}
		}
		return maps1;
	}

	public int getMostSaleCount(TblMachineAreaModelMostSale machineAreaModelMostSale) {
		return tblMachineAreaModelMostSaleMapper.getMostSaleCount(machineAreaModelMostSale);
	}

	/**
	 * 按设备id和模型ID循环修改最高零售数量
	 */
	public int updateMostSaleByIds(List<TblMachineAreaModelMostSale> machineAreaModelMostSales) {
		int i = 0;
		for (TblMachineAreaModelMostSale tblMachineAreaModelMostSale : machineAreaModelMostSales) {
			int count = tblMachineAreaModelMostSaleMapper.getMostSaleCount(tblMachineAreaModelMostSale);
			if (count > 0) {
				// 修改
				tblMachineAreaModelMostSaleMapper.updateMostSaleByIds(tblMachineAreaModelMostSale);
				Long id = tblMachineAreaModelMostSaleMapper.getByIds(tblMachineAreaModelMostSale);
				// 新增
				TblMachineAreaModelMostSaleLog record = new TblMachineAreaModelMostSaleLog();
				record.setMachineAreaModelMostSaleId(id);
				record.setOperateTime(new Date());
				record.setOperateType("2");
				record.setOperateUserId(Long.parseLong(Constants.getCurrendUser().getUserId().toString()));
				machineAreaModelMostSaleLogMapper.insertSelective(record);
			} else {
				// 新增
				tblMachineAreaModelMostSale.setCreateTime(new Date());
				tblMachineAreaModelMostSale
						.setCreateUserId(Long.parseLong(Constants.getCurrendUser().getUserId().toString()));
				tblMachineAreaModelMostSale.setIsdel("N");
				tblMachineAreaModelMostSale.setModTime(new Date());
				tblMachineAreaModelMostSale
						.setModUserId(Long.parseLong(Constants.getCurrendUser().getUserId().toString()));
				tblMachineAreaModelMostSaleMapper.insertSelective(tblMachineAreaModelMostSale);
				// 添加到日志表
				Long id = tblMachineAreaModelMostSaleMapper.getByIds(tblMachineAreaModelMostSale);
				TblMachineAreaModelMostSaleLog record = new TblMachineAreaModelMostSaleLog();
				record.setMachineAreaModelMostSaleId(id);
				record.setOperateTime(new Date());
				record.setOperateType("0");
				record.setOperateUserId(Long.parseLong(Constants.getCurrendUser().getUserId().toString()));
				machineAreaModelMostSaleLogMapper.insertSelective(record);
			}
			i++;
		}
		return i;
	}

	public Map<String, Object> getQuestionDate(List<TblMachineAreaModelMostSale> mostSales) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		if (mostSales.size() == 0) {
			returnContent.put("result", true);
			return returnContent;
		}
		Long machineId = mostSales.get(0).getMachineId();
		String nowDate = DateUtils.formatShortStr(new Date());
		List<Long> areaModelIds = new ArrayList<Long>();
		
		for (TblMachineAreaModelMostSale tblMachineAreaModelMostSale : mostSales) {
			Integer num = tblMachineAreaModelMostSaleMapper.getMostSaleNum(tblMachineAreaModelMostSale.getMachineId(),
					tblMachineAreaModelMostSale.getAreaModelId());
			
			if (num != null && num > tblMachineAreaModelMostSale.getMostSaleNum()) {
				areaModelIds.add(tblMachineAreaModelMostSale.getAreaModelId());
			}else{//最高份数增加
				Long areaModelId = tblMachineAreaModelMostSale.getAreaModelId();
				/* 预定分配格子数 */
				int orderCellNum = tblTemporaryRetailMapper.getOrderCellNum(machineId, areaModelId, DateFormat.dateFormatYYYYMMDD(new Date()));
				/* 设备有效份数 */
				int  effectiveNum = tblTemporaryRetailMapper.getEffectiveNum(machineId);
				if((effectiveNum - orderCellNum) < tblMachineAreaModelMostSale.getMostSaleNum()){
					String areaModelName=basicsAreaModelMapper.getAreaModelName(areaModelId);
					returnContent.put("result", false);
					returnContent.put("message", "修改失败，“"+areaModelName+"”剩余格子数小于最高份数，剩余格子数为："+(effectiveNum - orderCellNum));
					returnContent.put("status", "0");
					return returnContent;
				}
				
			}
		}
		if (areaModelIds.size() > 0) {
			StringBuffer content = new StringBuffer("您设置的零售最高份数的减少会对");
			List<String> questionDates = goodsRetailNumMapper.getQuestionDate(machineId, nowDate, areaModelIds);
			if (questionDates.size() > 0) {
				for (String questionDate : questionDates) {
					content.append("“" + questionDate + "”，");
				}
				content.append("日期的“商品零售发布”业务的“零售数量(份)”不一致，请到商品零售发布业务中修改成总数一致的，否则实际的零售发布总数不变。");
				returnContent.put("result", false);
				returnContent.put("message", content);
				return returnContent;
			}
			returnContent.put("result", true);
			return returnContent;
		}
		returnContent.put("result", true);
		return returnContent;
	}
}
