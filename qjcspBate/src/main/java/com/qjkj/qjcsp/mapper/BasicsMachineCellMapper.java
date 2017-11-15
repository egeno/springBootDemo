package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsMachineCell;

@MyBatisRepository
public interface BasicsMachineCellMapper {
	int deleteByPrimaryKey(String cellId);

	int insert(BasicsMachineCell record);

	int insertSelective(BasicsMachineCell record);

	BasicsMachineCell selectByPrimaryKey(String cellId);

	int updateByPrimaryKeySelective(BasicsMachineCell record);

	int updateByPrimaryKey(BasicsMachineCell record);

	/**
	 * 2.2.7. 获取补货格子列表
	 * 
	 * @param deviceCode:设备Id
	 *            cellsNum：商品数量
	 */
	List<Map<String, Object>> getOperateCellsNum(@Param("deviceCode") String deviceCode,
			@Param("limit") Integer cellsNum);

	List<Map<String, Object>> getNotEmptyCells(@Param("machineId") Long machineId);

	int updateMachineCell(@Param("cellId") String cellId, @Param("itemId") String itemId,
			@Param("cellStatus") String cellStatus);

	Integer findCellCountByCellStatusIsNullByMachineId(Long machineId);

	int addMachineCellByList(List<BasicsMachineCell> basicsMachineList);

	int delMachineCellByMachineId(Long machineId);

	/**
	 * 商品有效份数
	 */
	int getUsefulGoodsNum(Map<String, Object> map);

	/**
	 * 得到所有的订单对应的所有格子id
	 */
	List<String> getUsefulGoodsItemId(Map<String, Object> map);

	/**
	 * 通过设备ID获取该设备所有的格子数
	 */
	int getMachineCellNum(@Param("machineId") Long machineId);

	List<Map<String, Object>> getAlarmCellNumByDeviceCode(@Param("orderChildId")Long orderChildId);

	List<Map<String, Object>> getAlarmCellNumByidCode(@Param("idCode")String idCode);
}