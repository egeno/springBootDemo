package com.qjkj.qjcsp.service.stacistics;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.mapper.BasicsCompanyMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.StatisticsMapper;
/**
 * 
 * @author carpeYe 2016-01-24
 *
 */
@Service
public class EverydaySalesReportService {

	
	
	private List<Integer> rowspanList;// 合并单元格
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private StatisticsMapper statisticsMapper;
	
	@Autowired
	
	private BasicsCompanyMapper basicsCompanyMapper;
	
	public Map<String, Object> everydaySalesReport(String seacrchDate,String machineName,String companyId) {
		List<Long> machineIds = findMachineIdsBycompanyId(machineName,companyId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (machineIds != null) {
			List<Map<String, Object>> content = everydaySales(machineIds, seacrchDate);
			map.put("rows", content);
			map.put("psdate", seacrchDate);
			map.put("rowspan", rowspanList);
		}
		return map;
	}
	
	public Map<String, Object> everydaySalesReport(String seacrchDate,String machineName,String companyId,String machineSex,String useOrNot) {
		List<Long> machineIds = findMachineIdsBycompanyId(machineName,companyId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (machineIds != null) {
			List<Map<String, Object>> content = everydaySales(machineIds, seacrchDate, machineSex, useOrNot);
			map.put("rows", content);
			map.put("psdate", seacrchDate);
			map.put("rowspan", rowspanList);
		}
		return map;
	}

	/**
	 * 导出数据
	 * 
	 * @param companyId
	 * @param seacrchDate
	 * @return
	 */
	public Map<String, Object> everydaysExport(String seacrchDate,String machineName,String companyId,String machineSex,String useOrNot) {
		return everydaySalesReport(seacrchDate,machineName,companyId,machineSex,useOrNot);
	}
	
	public Map<String, Object> everydaysExport(String seacrchDate,String machineName,String companyId) {
		return everydaySalesReport(seacrchDate,machineName,companyId);
	}

	/**
	 * 根据公司ID找machineIds
	 * 
	 * @param companyId
	 * @return list<machineId>
	 */
	private List<Long> findMachineIdsBycompanyId(String machineName,String companyId) {
		List<Long> machineIds = basicsMachineMapper.findEffectiveMachineIdByMachineName(machineName,Long.valueOf(companyId));
		return machineIds;
	}

	/**
	 * 找到所有符合要求的设备所有模型的补货数，销售数，销售金额
	 * 
	 * @param machineIds
	 * @param searchDate
	 * @param companyId
	 * @return
	 */
	private List<Map<String, Object>> everydaySales(List<Long> machineIds, String searchDate, String machineSex, String useOrNot) {
		List<Map<String, Object>> content = new ArrayList<Map<String, Object>>();
		DecimalFormat df=new DecimalFormat("0.00");
		rowspanList = new ArrayList<Integer>();
		int operateCount = 0;
		int saleCount = 0;
		int orderCount = 0;
		BigDecimal saleMoney=new BigDecimal(0) ;
		int i = 0;
		for (Long machineId : machineIds) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("machineId", machineId);
			params.put("searchDate", searchDate);
			/*params.put("machineSex", machineSex);
			params.put("useOrNot", useOrNot);*/
			List<Map<String, Object>> list = statisticsMapper.findEverydaySales(params);
			if (list.size() > 1) {
				content.addAll(list);
				for (Map<String, Object> map : list) {
					if(map.get("machineName").toString().indexOf("男") != -1){
						map.put("machineSex", "男");
					}
					else{
						map.put("machineSex", "女");
					}
				}
				operateCount += Integer.valueOf((list.get(list.size() - 1).get("operateCount") + ""));
				saleCount += Integer.valueOf(list.get(list.size() - 1).get("saleCount") + "");
				orderCount += Integer.valueOf(list.get(list.size() -1).get("orderCount") + "");
				saleMoney =saleMoney.add(new BigDecimal(list.get(list.size() - 1).get("saleMoney") + ""));
				i++;
				rowspanList.add(list.size());
			}
		}
		if (content.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("machineName", "");
			map.put("areaModelName", "合计");
			map.put("areaModelStatus", i);
			map.put("operateCount", operateCount);
			map.put("saleCount", saleCount);
			map.put("orderCount", orderCount);
			map.put("saleMoney", df.format(saleMoney));
			if(operateCount == 0){
				map.put("sellRate", "0.0%");
			}
			else{
				NumberFormat num = NumberFormat.getPercentInstance(); 
				num.setMaximumIntegerDigits(3); 
				num.setMaximumFractionDigits(2); 
				double result = (double)saleCount/(double)operateCount; 
				map.put("sellRate", num.format(result));
			}
			content.add(map);
		}
		return content;
	}
	
	private List<Map<String, Object>> everydaySales(List<Long> machineIds, String searchDate) {
		List<Map<String, Object>> content = new ArrayList<Map<String, Object>>();
		DecimalFormat df=new DecimalFormat("0.00");
		rowspanList = new ArrayList<Integer>();
		int operateCount = 0;
		int saleCount = 0;
		int orderCount = 0;
		BigDecimal saleMoney=new BigDecimal(0) ;
		int i = 0;
		for (Long machineId : machineIds) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("machineId", machineId);
			params.put("searchDate", searchDate);
			List<Map<String, Object>> list = statisticsMapper.findEverydaySales(params);
			if (list.size() > 1) {
				content.addAll(list);
				for (Map<String, Object> map : list) {
					if(map.get("machineName").toString().indexOf("男") != -1){
						map.put("machineSex", "男");
					}
					else{
						map.put("machineSex", "女");
					}
				}
				operateCount += Integer.valueOf((list.get(list.size() - 1).get("operateCount") + ""));
				saleCount += Integer.valueOf(list.get(list.size() - 1).get("saleCount") + "");
				orderCount += Integer.valueOf(list.get(list.size() -1).get("orderCount") + "");
				saleMoney =saleMoney.add(new BigDecimal(list.get(list.size() - 1).get("saleMoney") + ""));
				i++;
				rowspanList.add(list.size());
			}
		}
		if (content.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("machineName", "");
			map.put("areaModelName", "合计");
			map.put("areaModelStatus", i);
			map.put("operateCount", operateCount);
			map.put("saleCount", saleCount);
			map.put("orderCount", orderCount);
			map.put("saleMoney", df.format(saleMoney));
			content.add(map);
		}
		return content;
	}
	
	public List<CompanyPo> getCompanyIdAndName(){
		return basicsCompanyMapper.getCompanyIdAndName();
	}
}
