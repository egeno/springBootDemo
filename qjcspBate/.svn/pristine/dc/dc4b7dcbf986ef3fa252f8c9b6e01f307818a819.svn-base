package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblMachineAreaModelMostSale;

@MyBatisRepository
public interface TblMachineAreaModelMostSaleMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TblMachineAreaModelMostSale record);

	int insertSelective(TblMachineAreaModelMostSale record);

	TblMachineAreaModelMostSale selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TblMachineAreaModelMostSale record);

	int updateByPrimaryKey(TblMachineAreaModelMostSale record);

	List<Map<String, Object>> getMostSalsInfoByMachineId(@Param("companyId") Integer companyId,
			@Param("areaModelId") Long areaModelId, @Param("machineId") Long machineId);

	List<Map<String, Object>> getAllMachineAreaModel(@Param("companyId") Integer companyId);

	int getMostSaleCount(TblMachineAreaModelMostSale record);

	/**
	 * 按设备id和模型ID循环修改最高零售数量
	 */
	int updateMostSaleByIds(TblMachineAreaModelMostSale record);

	Long getByIds(TblMachineAreaModelMostSale record);
	
	List<Map<String, Object>> getMahcineAreaModelMostNumList(@Param("companyId")Long companyId);
	
	Integer getMostSaleNum(@Param("machineId") Long machineId, @Param("areaModelId") Long areaModelId);
	
	TblMachineAreaModelMostSale getMostSaleByMachineIdAndAreaModelId (@Param("machineId")Long machineId, @Param("areaModelId")Long areaModelId);

	TblMachineAreaModelMostSale getNewMostSaleNum(@Param("machineId")Long machineId, @Param("areaModelId")Long areaModelId);
	
	int deleteByMachineIdAndAreaModelId(@Param("machineId")Long machineId, @Param("areaModelId")Long areaModelId);

}