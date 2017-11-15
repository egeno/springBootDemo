package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblAlarmInfo;
@MyBatisRepository
public interface TblAlarmInfoMapper {
    int deleteByPrimaryKey(Long alarmId);

    int insert(TblAlarmInfo record);

    int insertSelective(TblAlarmInfo record);

    TblAlarmInfo selectByPrimaryKey(Long alarmId);

    int updateByPrimaryKeySelective(TblAlarmInfo record);

    int updateByPrimaryKey(TblAlarmInfo record);

	int processingequipment(TblAlarmInfo record);

	TblAlarmInfo getAlarmCellNumByDeviceCode(Long machineId);

	int selectCountByalarmIdcheckUserId(TblAlarmInfo record);

	int selectCountByOrderChildId(Long orderChildId);
	
}