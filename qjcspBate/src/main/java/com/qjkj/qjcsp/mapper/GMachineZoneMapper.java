package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.GMachineZone;
@MyBatisRepository
public interface GMachineZoneMapper {
    int deleteByPrimaryKey(Long machineZoneId);

    int insert(GMachineZone record);

    int insertSelective(GMachineZone record);

    GMachineZone selectByPrimaryKey(Long machineZoneId);

    int updateByPrimaryKeySelective(GMachineZone record);

    int updateByPrimaryKey(GMachineZone record);
    
    List<GMachineZone> queryMachineZoneByUniId(Long universityId);
    
    List<Map<String, Object>> queryMachineByMachineZoneId(Long machineZoneId);
    
    GMachineZone findGMachineZoneBymachineZoneIds(@Param("machineZoneIds")Long machineZoneIds);

	List<GMachineZone> findGMachineZoneByuniversityid(@Param("universityId")Long universityId );
}