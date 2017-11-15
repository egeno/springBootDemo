package com.qjkj.qjcsp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblGoodsRetailNum;

@MyBatisRepository
public interface TblGoodsRetailNumMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TblGoodsRetailNum record);

	int insertSelective(TblGoodsRetailNum record);

	TblGoodsRetailNum selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TblGoodsRetailNum record);

	int updateByPrimaryKey(TblGoodsRetailNum record);

	/**
	 * 根据模型id和设备id，当前时间，得到零售数量
	 * 
	 * @param machineId
	 * @param areaModelId
	 * @return TblGoodsRetailNum
	 */
	List<TblGoodsRetailNum> selectByMachintIdAndAreaModelId(@Param("machineId") Long machineId,
			@Param("areaModelId") Long areaModelId);

	List<Map<String, Object>> queryRetailNumByMachineIdAndAreaModelId(@Param("machineId") Long machineId,
			@Param("areaModelId") Long areaModelId, @Param("replenishmentTime") Date replenishmentTime);

	Long getGoodsNum(Long machineId);

	int getRetailCellNum(@Param("machineId") Long machineId, @Param("areaModelId") Long areaModelId,
			@Param("replenishmentTime") Date replenishmentTime);

	int updateGoodsNum(@Param("machineId") Long machineId, @Param("areaModelId") Long areaModelId,
			@Param("replenishmentTime") Date replenishmentTime, @Param("goodsId") Long goodsId,
			@Param("addCellNum") int addCellNum);

	int updateGoodsNumById(@Param("id") Long id, @Param("addCellNum") int addCellNum);

	int deleteById(Long id);

	int getCountByMachineIdAndAreaModelId(@Param("machineId") Long machineId, @Param("areaModelId") Long areaModelId,
			@Param("replenishmentTime") Date replenishmentTime);

	int getGoosNum(@Param("machineId") String machineId, @Param("goodsCategoryId") String goodsCategoryId);

	List<String> getQuestionDate(@Param("machineId") Long machineId, @Param("nowDate") String nowDate,
			@Param("areaModelIds") List<Long> areaModelIds);

	List<Map<String, Object>> judgeUsed(@Param("areaModelId")String areaModelId, @Param("goodsId")List<String> goodsIdList, @Param("currentDate")String currentDate);

	List<Map<String, Object>> judgeUsedByAreaModelId(Long areaModelId);
}