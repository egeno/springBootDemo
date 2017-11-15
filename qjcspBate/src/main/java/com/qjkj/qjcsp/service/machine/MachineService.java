package com.qjkj.qjcsp.service.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.TblTemplateAreaModelMapper;
import com.qjkj.qjcsp.service.basicstemplate.TemplateAreaModelService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UserUtils;

@Service
@Transactional
public class MachineService {
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private MachineTemplateRetailDateService templateRetailDateService;
	@Autowired
	private TemplateAreaModelService templateAreaModelService;
	@Autowired
	private MachineAreaModelService machineAreaModelService;

	/**
	 * 根据当前登录账户的公司Id获取所属的设备id和设备名称
	 */
	public List<Map<String, Object>> getMachineList() {
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		UserUtils user=new UserUtils();
		if(user.getUserType()==3){
		return basicsMachineMapper.getMachineList(companyId);}
		else{
			return basicsMachineMapper.getMachineListByuserid(user.getUserId());
		}
	}

	/**
	 * 获取分配设备模型的list
	 */
	public List<Map<String, Object>> getAssignMachineTemplateList(Long templateId) {
		// 首先获取模板所绑定的模型Id
		List<Long> areaModelIds = templateAreaModelService.getAreaModelIdByTemplateId(templateId);
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		// 获取公司所属的设备
		List<Map<String, Object>> machineList = basicsMachineMapper.getMachineList(companyId);
		// 定义筛选后的machineList集合，并循环查出设备对应的模型，筛选出符合条件的设备
		List<Map<String, Object>> lastList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < machineList.size(); i++) {
			Map<String, Object> map = machineList.get(i);
			Long machineId = (Long) map.get("machineId");
			List<Long> areaModelIds1 = machineAreaModelService.getAreaModelIdByMachineId(machineId);
			if (areaModelIds.containsAll(areaModelIds1) && areaModelIds.size() == areaModelIds1.size()
					&& areaModelIds.size() != 0 && areaModelIds1.size() != 0) {
				lastList.add(map);
			}
		}
		// 获取本周的每天对应的模板数据
		List<Map<String, Object>> templateInfo = templateRetailDateService.getTemplateInfo();

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		// 循环设备list
		for (Map<String, Object> map : lastList) {
			String machineId = map.get("machineId").toString();
			Map<String, Object> outMap = new HashMap<String, Object>();
			outMap.put("machineId", machineId);
			outMap.put("machineName", map.get("machineName"));
			// 设备下所有的模板集合
			List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
			// 循环模板list，将属于设备的所有模板信息添加到list1集合中
			for (Map<String, Object> map2 : templateInfo) {
				Map<String, Object> map3 = new HashMap<String, Object>();
				String machineId2 = map2.get("machineId").toString();
				if (machineId.equals(machineId2)) {
					map3.put("machineId", machineId);
					map3.put("templateId", map2.get("templateId").toString());
					map3.put("retailDate", map2.get("retailDate").toString());
					map3.put("templateName", map2.get("templateName").toString());
					list1.add(map3);
				}
			}
			outMap.put("templateList", list1);
			returnList.add(outMap);
		}
		return returnList;
	}
}
