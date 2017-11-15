package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsMachine;

@MyBatisRepository
public interface StockInfoMapper {
	
	List<Map<String, Object>> findStockInfo(Map<String, Object> map);
	
	List<Map<String, Object>> getGoodsNum(long areamodelid);
	
	BasicsMachine findMachineByCode(String deviceCode);
	
	BasicsMachine findMachineByMachineId(String machineId);

}
