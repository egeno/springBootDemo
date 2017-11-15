package com.qjkj.qjcsp.service.preissue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.util.DateUtils;

/**
 * 柜子周菜品销售统计
 * 
 * @author carpeYe 2016-03-24
 *
 */
@Service
public class MachineWeekSaleReportService {

	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;
	// 前端合并设备的参数
	private List<Integer> machineRowspan;
	// 前端和并模型的参数
	private List<Integer> areaModelRowspan;

	/*
	 * 柜子周销售统计方法
	 */
	public Map<String, Object> machineWeekSaleReport(String companyId, String machineName, String date) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		// 根据设备名和公司Id获取对应到 设备
		List<BasicsMachine> basicsMachines = findMachinesByMachineNameAndCompanyId(machineName, companyId);
		if (basicsMachines != null && basicsMachines.size() > 0) {
			// 调用根据设备集合和时间获取相应的模型和模型对应的菜品名和销售数量
			List<Map<String, Object>> content = findBasicsAreaModelByMachineId(basicsMachines, date);
			returnData.put("rows", content);
			returnData.put("machineRowspan", machineRowspan);
			returnData.put("areaModelRowspan", areaModelRowspan);
		}
		return returnData;
	}

	/*
	 * 根据设备名和公司Id获取对应到 设备
	 */
	private List<BasicsMachine> findMachinesByMachineNameAndCompanyId(String machineName, String companyId) {
		return basicsMachineMapper.findEffectiveMachinesByMachineName(machineName, Long.valueOf(companyId));
	}

	/*
	 * 根据设备集合和时间获取相应的模型和模型对应的菜品名和销售数量
	 */
	private List<Map<String, Object>> findBasicsAreaModelByMachineId(List<BasicsMachine> basicsMachines, String date) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		/* 前端合并设备的参数*/
		machineRowspan = new ArrayList<Integer>();
		/* 前端和并模型的参数*/
		areaModelRowspan = new ArrayList<Integer>();
		/* 遍历集合*/
		for (BasicsMachine basicsMachine : basicsMachines) {
			/* 根据设备ID查找相应的模型*/
			List<BasicsAreaModel> basicsAreaModels = basicsAreaModelMapper
					.selectAreaModelByMachine(basicsMachine.getMachineId());
			if (basicsAreaModels != null && basicsAreaModels.size() > 0) {
				/* 将集合的尺寸放到前端合并设备的集合*/
				int num=0;
				for (BasicsAreaModel basicsAreaModel : basicsAreaModels) {
					/* 根据遍历的模型Id，设备Id，七天前时间，当前时间 查询模型对应的菜品和销售数量*/
					List<Map<String, Object>> saleGoods = tblOrderDetailMapper.findGoodsSale(
							basicsAreaModel.getAreaModelId(), basicsMachine.getMachineId(), DateUtils.getMonday(date),  DateUtils.getSunday(date));					
					/* 情况1：设备下有模型，模型下有菜品和销售数*/
					if (saleGoods != null && saleGoods.size() > 0) {
						areaModelRowspan.add(saleGoods.size());
						num=num+saleGoods.size();
						for (int i = 0; i < saleGoods.size(); i++) {
							Map<String, Object> saleGood = saleGoods.get(i);
							saleGood.put("machineName", basicsMachine.getMachineName());
							saleGood.put("areaModelName", basicsAreaModel.getAreaModelName());
							maps.add(saleGood);
						}
					} else {
						/* 情况2：设备下有模型，模型下无菜品以及销售数*/
						areaModelRowspan.add(1);
						num=num+1;
						Map<String, Object> saleGood = new HashMap<String, Object>();
						saleGood.put("machineName", basicsMachine.getMachineName());
						saleGood.put("areaModelName", basicsAreaModel.getAreaModelName());
						saleGood.put("goodsName", "");
						saleGood.put("goodsNum", "");
						maps.add(saleGood);
					}
				}
				machineRowspan.add(num);
			} else {
				/* 情况三：设备下无模型*/
				machineRowspan.add(1);
				areaModelRowspan.add(1);
				Map<String, Object> saleGood = new HashMap<String, Object>();
				saleGood.put("machineName", basicsMachine.getMachineName());
				saleGood.put("areaModelName", "");
				saleGood.put("goodsName", "");
				saleGood.put("goodsNum", "");
				maps.add(saleGood);
			}
		}
		return maps;
	}
	/*
	 * date 当前时间（String）
	 * formatString 七天前的时间(String)
	 */
	private String FormatDate(String date,int dateNum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formatString = null;
		try {
			Date now = sdf.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(now);
			calendar.add(Calendar.DATE, dateNum);
			now = calendar.getTime();
			formatString = sdf.format(now);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatString;
	}

}
