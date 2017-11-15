package com.qjkj.qjcsp.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblOrder;

@MyBatisRepository
public interface StokGoodListMapper {
	
	List<Map<String, Object>> selectStokGoodList(@Param("machineId") Long machineId,@Param("areaModelId") Long areaModelId,@Param("userId") Long userId);
    
	List<Map<String, String>> selectCellBymachineId(@Param("machineId") Long machineId);
	
	int getCellByCellNum(@Param("machineId") Long machineId,@Param("cellNum") Long cellNum,@Param("userId") Long userId);
}
