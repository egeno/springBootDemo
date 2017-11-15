package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.AlarmReportSearch;

@MyBatisRepository
public interface AlarmMapper {

	  int selectAlarmReportCount(AlarmReportSearch alarmReportSearch); 
	  
	  List<Map<String, Object>> selectAlarmAll(AlarmReportSearch alarmReportSearch);

	List<Map<String, Object>> selectAlarmAllExport(AlarmReportSearch alarmReportSearch);
	
}
