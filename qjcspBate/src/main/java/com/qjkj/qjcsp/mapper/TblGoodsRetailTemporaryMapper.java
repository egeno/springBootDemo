package com.qjkj.qjcsp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblGoodsRetailTemporary;
@MyBatisRepository
public interface TblGoodsRetailTemporaryMapper {
    int deleteByPrimaryKey(Long temporaryId);

    int insert(TblGoodsRetailTemporary record);

    int insertSelective(TblGoodsRetailTemporary record);

    TblGoodsRetailTemporary selectByPrimaryKey(Long temporaryId);

    int updateByPrimaryKeySelective(TblGoodsRetailTemporary record);

    int updateByPrimaryKey(TblGoodsRetailTemporary record);
    
    /**
     * 根据模型id和设备id，当前时间,零售临时格子信息
     * @param machineId
     * @param areaModelId
     * @return TblGoodsRetailTemporary
     */
     List<Map<String, Object>> selectRetailTemporaryList(@Param("machineId") Long machineId,@Param("areaModelId") Long areaModelId,@Param("userId") Long userId);
     
     TblGoodsRetailTemporary getTemporaryRetailByReplenishment(@Param("machineId") Long machineId,@Param("areaModelId") Long areaModelId,@Param("cellNum")Integer cellNum,@Param("currentTimeFormat")Date currentTimeFormat );
     
     List<Map<String, Object>> getAllocatedMachineCell (@Param("machineId") Long machineId,@Param("areaModelId") Long areaModelId, 
    		 @Param("temporaryDate")Date temporaryDate, @Param("addCellNum")int addCellNum);
     
     int addGoodsRetailList(@Param("id")Long id, @Param("machineId") Long machineId,@Param("areaModelId") Long areaModelId, 
    		 @Param("temporaryDate")Date temporaryDate, @Param("goodsId")Long goodsId, 
    		 @Param("allocatedMachineCellList")List<Map<String, Object>> allocatedMachineCellList);
     
     int reduceGoodsRetail(@Param("id") Long id, @Param("addCellNum")int addCellNum);
     /**
      *得到当前id的补货数量
      */
     int getSupplyNumById(@Param("id") Long id);
     
     int delByGoodsRetailNumId(Long goodsRetailNumId);

	 int deleteByMachineId(Long machineId);
	 
	 Integer goodsRetailTemporaryCount(@Param("goodsId") Long goodsId);
}