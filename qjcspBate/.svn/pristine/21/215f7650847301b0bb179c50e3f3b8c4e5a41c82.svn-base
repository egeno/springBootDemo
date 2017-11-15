package com.qjkj.qjcsp.service.stacistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.AlarmReportSearch;
import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.mapper.AlarmMapper;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;

@Service
public class AlarmReportService {
	@Autowired
	private AlarmMapper alarmMapper;
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	// 前端合并设备的参数
	private List<Integer> machineRowspan;
	// 前端和并模型的参数
	private List<Integer> areaModelRowspan;

	public int getAllAlarmReportCount(AlarmReportSearch alarmReportSearch) {
		
		return alarmMapper.selectAlarmReportCount(alarmReportSearch);
	}

	public Map<String, Object> findAlarmReport(AlarmReportSearch alarmReportSearch) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		// 根据设备名和公司Id获取对应到 设备
		List<BasicsMachine> basicsMachines = findMachinesByMachineNameAndCompanyId(alarmReportSearch);
		if (basicsMachines != null && basicsMachines.size() > 0) {
			// 调用根据设备集合和时间获取相应的模型和模型对应的菜品名和销售数量
			List<Map<String, Object>> content = findBasicsAreaModelByMachineId(basicsMachines, alarmReportSearch);
			
			returnData.put("rows", content);
			returnData.put("machineRowspan", machineRowspan);
			returnData.put("areaModelRowspan", areaModelRowspan);
		}
		return returnData;
	}
	private List<BasicsMachine> findMachinesByMachineNameAndCompanyId(AlarmReportSearch alarmReportSearch) {
		return basicsMachineMapper.findEffectiveMachinesByMachineNameAlarm(alarmReportSearch);
	}
	
	private List<Map<String, Object>> findBasicsAreaModelByMachineId(List<BasicsMachine> basicsMachines, AlarmReportSearch alarmReportSearch) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		/* 前端合并设备的参数*/
		machineRowspan = new ArrayList<Integer>();
		/* 前端和并模型的参数*/
		areaModelRowspan = new ArrayList<Integer>();
		int machineNum=0;
		List<Map<String, Object>> saleGoods =new ArrayList<Map<String, Object>>();
		if(alarmReportSearch.getOffset()!=null){
		/* 根据遍历的模型Id，设备Id，七天前时间，当前时间 查询模型对应的菜品和销售数量*/
			saleGoods	= alarmMapper.selectAlarmAll(alarmReportSearch);	}
		else{saleGoods = alarmMapper.selectAlarmAllExport(alarmReportSearch);
		}
		/* 遍历集合*/
		for (BasicsMachine basicsMachine : basicsMachines) {
			/* 根据设备ID查找相应的模型*/
			List<BasicsAreaModel> basicsAreaModels = basicsAreaModelMapper
					.selectAreaModelByMachineAlarm(basicsMachine.getMachineId());
				/* 将集合的尺寸放到前端合并设备的集合*/
				int modelNum=0;
				for (BasicsAreaModel basicsAreaModel : basicsAreaModels) {
					
						for (int i = 0; i < saleGoods.size(); i++) {
							Map<String, Object> saleGood = saleGoods.get(i);
							//设备相等,
							if(basicsMachine.getMachineName().equals(saleGood.get("machineName"))){
								
								
								//模型相等
								 if(basicsAreaModel.getAreaModelName().equals(saleGood.get("areaModelName"))){
									 modelNum ++;
									machineNum++;
									 maps.add(saleGood);
									 
								 }
							}
							
						}
						if(modelNum!=0){
						areaModelRowspan.add(modelNum);}
					
					modelNum=0;
				}
				machineRowspan.add(machineNum);
				machineNum = 0;
			 
			
		}
		return maps;
	}
	
	
	
}
