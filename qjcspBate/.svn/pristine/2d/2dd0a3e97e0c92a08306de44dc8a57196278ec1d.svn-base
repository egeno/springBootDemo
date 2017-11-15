package com.qjkj.qjcsp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblTemporaryRetail;

@MyBatisRepository
public interface TblTemporaryRetailMapper {
	int deleteByPrimaryKey(Long temporaryId);

	int insert(TblTemporaryRetail record);

	int insertSelective(TblTemporaryRetail record);

	TblTemporaryRetail selectByPrimaryKey(Long temporaryId);

	int updateByPrimaryKeySelective(TblTemporaryRetail record);

	int updateByPrimaryKey(TblTemporaryRetail record);

	/**
	 * 删除预定格子表信息
	 */
	void delByOrderDetailId(@Param("orderDetailId") Long orderDetailId);

	/**
	 * 根据子订单Id删除表信息
	 */
	void delByOrderChildId(@Param("orderChildId") Long orderChildId);

	/**
	 * 删除预定格子表信息根据订单id
	 */
	void delByOrderId(@Param("orderId") Long orderId);
	/**
	 * 更新预定格子表信息isdel为‘Y’根据订单id
	 */
	void updByOrderId(@Param("orderId") Long orderId);
	

	int getCount(@Param("issueId") Long issueId, @Param("goodsId") Long goodsId);

	TblTemporaryRetail getTemporaryRetailByReplenishment(@Param("machineId") Long machineId,
			@Param("areaModelId") Long areaModelId, @Param("cellNum") Integer cellNum,
			@Param("temporaryDate") Date temporaryDate);

	int delTemporaryRetailByReplenishment(@Param("machineId") Long machineId, @Param("areaModelId") Long areaModelId,
			@Param("cellNum") Integer cellNum, @Param("temporaryDate") Date temporaryDate);

	/**
	 * 查询预订份数
	 */
	List<Map<String, Object>> queryTemporaryRetailSaleCount(@Param("temporaryDate") String temporaryDate,
			@Param("machineId") Long machineId, @Param("areaModelId") Long areaModelId,
			@Param("companyId") Long companyId);
	
	/**
	 * 查询预订份数
	 */
	List<Map<String, Object>> queryTemporaryRetailSaleCount1(@Param("temporaryDate") String temporaryDate,
			@Param("machineId") Long machineId, @Param("areaModelId") Long areaModelId,
			@Param("companyId") Long companyId);

	int getRetailNum(long machineId);
	
	/**
	 * 设备有效份数
	 */
	int getEffectiveNum(@Param("machineId")Long machineId);
	
	int getOrderCellNum(@Param("machineId")Long machineId, @Param("areaModelId")Long areaModelId, 
			@Param("temporaryDate") Date temporaryDate);
	/**
	 * Gekko 注：删除7天前的的临时表数据
	 */
	void delExpireTemporary();
	void delExpireTemporary1();
	
	Integer temporaryRetailCount(@Param("goodsId") Long goodsId);
}