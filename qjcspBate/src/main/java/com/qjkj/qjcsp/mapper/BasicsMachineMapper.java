package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.AlarmReportSearch;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.viewmodel.BasicsMachineVo;

@MyBatisRepository
public interface BasicsMachineMapper {
	int deleteByPrimaryKey(Long machineId);

	int insert(BasicsMachine record);

	int insertSelective(BasicsMachine record);

	BasicsMachine selectByPrimaryKey(Long machineId);

	int updateByPrimaryKeySelective(BasicsMachine record);

	int updateByPrimaryKey(BasicsMachine record);

	Long findByCount(Map<String, Object> param);

	/* 查询所有设备信息 */
	List<BasicsMachineVo> findByList(Map<String, Object> param);

	int deleteBasicsMachinebyId(Long machineId);

	List<BasicsMachine> selectMachineByCompanyId(Map<String, Object> map);

	List<Map<String, String>> getMachinesByGPSLocation(Map<String, Object> postData);
	
	/*-- 获取附近的设备单位（米） */
	List<Map<String, Object>> getPreissueMachineList(Map<String, Object> postData);
	
	List<Map<String,Object>> getPreissueHistoryMachineList(Map<String, Object> postData);

	Long getMachineIdByDeviceCode(String deviceCode);

	BasicsMachine selectByDeviceCode(String deviceCode);

	/**
	 * 得到设备id和公司id
	 * 
	 * @author yehx
	 * @date 2016年1月5日
	 * @return
	 *
	 */
	public Map<String, Object> getMachineIdAndCompanyId(Map map);

	/**
	 * 插入取餐记录
	 * 
	 * @author yehx
	 * @date 2016年1月5日
	 * @param map
	 *
	 */
	public void insertOrderPickDetails(Map map);

	/**
	 * 修改订单，单品，格子的状态
	 * 
	 * @author yehx
	 * @date 2016年1月14日
	 * @param orderNum
	 *
	 */
	public void updateOrderItemCellStatus(String orderChildId);

	/**
	 * 删除订单处理表中的数据
	 * 
	 * @author yehx
	 * @date 2016年1月29日
	 * @param orderNum
	 *
	 */
	public void deleteOrderPendingForOrderChildId(Long orderChildId);

	Long getAreaModelbyDeviceCode(String deviceCode);

	Long getAreaModelIdByMachineId(Long machineId);

	Long getCount(Map<String, Object> map);

	Long getMaintenanceCount(Map<String, Object> map);

	List<BasicsMachine> findAllMachinesList(Map<String, Object> map);

	List<BasicsMachine> findMaintenanceMachinesList(Map<String, Object> map);

	List<BasicsMachine> findEmployeeMachineList(Map<String, Object> map);
	
	Long getAreaModelCountByMachineId(Long machineId);

	/*
	 * 根据公司的Id获取到所有未删除有效的设备Id
	 */
	List<Long> findEffectiveMachineIdByMachineName(@Param("machineName") String machineName,
			@Param("companyId") Long companyId);

	List<BasicsMachine> findEffectiveMachinesByMachineName(@Param("machineName") String machineName,
			@Param("companyId") Long companyId);

	int updateAreaModelIdIfDifferent(@Param("machineId") Long machineId, @Param("areaModelId") Long areaModelId);

	List<BasicsMachine> findBasicMachinesByAreaModelId(@Param("companyId") Long companyId,
			@Param("areaModelId") Long areaModelId);

	Map<String, String> getDeviceSalesInfo(@Param("machineId") Long machineId, @Param("itemStatus") String itemStatus,
			@Param("orderChildStatus") String orderChildStatus);

	int findMachineModelCountByModelId(Integer modelId);

	Map<String, Object> getTemperaturebyDeviceCode(String deviceCode);

	Long findAreaModelIdByDeviceCode(@Param("deviceCode") String deviceCode);

	/*
	 * 得到当前设备的所有模型 wsk
	 */
	List<Map<String, Object>> selectModelAllById(Long machineId);

	BasicsMachine selectByMachineId(Long machineId);

	List<Map<String, Object>> getMachineList(@Param("companyId") Integer companyId);

	String getDeviceCodeByMachineId(Long machineId);

	int getMachineNum(long machineId);

	int getMachineNum2(@Param("machineId") String machineId, @Param("companyId") long companyId,
			@Param("goodsCategoryId") String goodsCategoryId);

	int getTotalCellNumByMachineId(Long machineId);

	List<Map<String, Object>> queryMachineInfoByUserId(@Param("userId") Long userId);
	
	List<Map<String, Object>> queryMachineInfoByUserIdTwo(@Param("userId") Long userId,@Param("specialRoleNum") Long specialRoleNum);

	int getCellValidNumByMachineId(@Param("machineId") Long machineId);

	int getCellNotNullNum(@Param("machineId") Long machineId);

	int getCellNotNullNumByUserName(@Param("machineId") Long machineId, @Param("companyId") Long companyId,
			@Param("userName") String userName);

	List<Map<String, Object>> findMachineListByCompanyId(@Param("companyId") Long companyId,
			@Param("userId") Long userId, @Param("specialRoleNum") Long specialRoleNum);

	Map<String, Object> findMachineInfoByIds(@Param("companyId") Long companyId, @Param("machineId") Long machineId,
			@Param("userName") String userName, @Param("userId") Long userId,
			@Param("specialRoleNum") Long specialRoleNum);

	List<Map<String, Object>> getFoodList(@Param("userName") String userName, @Param("machineId") Long machineId,
			@Param("specialRoleNum") Long specialRoleNum);

	List<String> getAlarmCode(@Param("machineId") Long machineId);

	List<String> getAlarmName(@Param("alarmCode") String [] alarmCode);

	Map<String, Object> selectMachineInfo(@Param("machineId") Long machineId);

	List<String> getAlarmMachineForMP(@Param("userId") Long userId);

	List<String> getAlarmMachineIsN(@Param("userId") Long userId, @Param("companyId") Long companyId,
			@Param("userName") String userName);

	List<String> getAlarmMachineIsC(@Param("userId") Long userId, @Param("companyId") Long companyId,
			@Param("userName") String userName);
	
	List<String> getAlarmMachineIsNForMerchant(@Param("userId") Long userId, @Param("companyId") Long companyId);

	List<String> getAlarmMachineIsCForMerchant(@Param("userId") Long userId, @Param("companyId") Long companyId);

	List<Map<String, Object>> isCleanGoods(@Param("companyId") Long companyId, @Param("userName") String userName);

	List<Map<String, Object>> issupplyGoods(@Param("companyId") Long companyId, @Param("userName") String userName);
	//根据商户ID获取对应的设备
	List<Map<String, Object>> getMachineListByuserid(@Param("userId") Integer userId);
    //根据设备ID查询模型ID	
	List<Map<String, String>>getAreaModelId(@Param("machineId") Long machineId);
    //根据模型ID查询零售清货开始时间
	Map<String, String> getRetailCleanStartTime(@Param("areaModelId") Long areaModelId);//@Param("machineId") Long machineId

	List<BasicsMachine> findEffectiveMachinesByMachineNameAlarm(AlarmReportSearch alarmReportSearch);

		
}