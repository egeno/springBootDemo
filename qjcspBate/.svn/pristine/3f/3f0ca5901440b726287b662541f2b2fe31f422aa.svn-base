package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsMachineAreaModel;

@MyBatisRepository
public interface BasicsMachineAreaModelMapper {
	int deleteByPrimaryKey(Long id);

	int insert(BasicsMachineAreaModel record);

	int insertSelective(BasicsMachineAreaModel record);

	BasicsMachineAreaModel selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(BasicsMachineAreaModel record);

	int updateByPrimaryKey(BasicsMachineAreaModel record);

	List<Long> getAreaModelIdByMachineId(@Param("machineId") Long machineId);

	List<BasicsMachineAreaModel> findByAreaModelId(long areaModelId);

	BasicsMachineAreaModel findByMachineId(Map map);
	
	List<Map<String,Object>> findMachineAreaModel(@Param("machineId")Long machineId);
	
	List<Map<String, Object>> queryMachineByAreaModelId(Long areaModelId);
	
	List<Map<String,Object>> findAreaModelsByMachineIds(@Param("map")List<Map<String,Object>> map);
}