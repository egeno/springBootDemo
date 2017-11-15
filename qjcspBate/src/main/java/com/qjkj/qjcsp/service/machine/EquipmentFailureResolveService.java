package com.qjkj.qjcsp.service.machine;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblAlarmInfo;
import com.qjkj.qjcsp.mapper.TblAlarmInfoMapper;

import net.sf.json.JSONObject;

/**
 * 
 * 设备故障处理
 */
@Service
public class EquipmentFailureResolveService {

	@Autowired
	private TblAlarmInfoMapper tblAlarmInfoMapper;
//	@Autowired
//	private BasicsMachineMapper basicsMachineMapper;
	
	public Map<String, Object> processingequipment(JSONObject res) {
		Map<String, Object> map = new HashMap<String, Object>();
		TblAlarmInfo record=new TblAlarmInfo();
		Long checkUserId = res.getLong("userId");
		Long alarmId = res.getLong("alarmId");
		String operateType = res.getString("operateType");
		String isdel=null;
		if(operateType.equals("0")){
			isdel="C";
		}
		else if(operateType.equals("1")){
			isdel="Y";
		}
		else{
			isdel=null;
		}
	
		if((StringUtils.isAnyBlank(operateType))||(isdel==null)||(alarmId==0)||(checkUserId==0)){
			map.put("returnCode", "2");
			map.put("returnContent", "参数错误");
			return map;
		}
		TblAlarmInfo machineId=tblAlarmInfoMapper.selectByPrimaryKey(alarmId);
		if(machineId==null){
			map.put("returnCode", "2");
			map.put("returnContent", "没有此故障");
			return map;
		}
		record.setAlarmId(alarmId);
		record.setIsdel(isdel);
		record.setCheckUserId(checkUserId);
		record.setMachineId(machineId.getMachineId());
		int count=tblAlarmInfoMapper.selectCountByalarmIdcheckUserId(record);
		if(count>0){
			map.put("returnCode", "2");
			map.put("returnContent", "该设备已经有人赶去维修啦");
			return map;
		}
		record.setCheckTime(new Date());
		tblAlarmInfoMapper.processingequipment(record);
		map.put("returnCode", "1");
		map.put("returnContent", "成功");
		return map;
	}


}
