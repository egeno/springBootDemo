package com.qjkj.qjcsp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblOrderChild;

@MyBatisRepository
public interface GoodsMachineMapper {

	Long getMachineGoodsAvgSore(Long machineId);

	List<Map<String, Object>> getGoodsCommentsWithContentByMachineId(Long machineId);

	int selectEffectiveNum(@Param("goodsId") Long goodsId);

	Object selectEvaluateScore(@Param("machineId") Long machineId, @Param("goodsId") Long goodsId);

	List<TblOrderChild> selectByOrderIdOrderByBeginTime(Long orderId);

	List<Map<String, Object>> getClearCellNumList(Map<String, Object> map);

	Long getGoodsCommentsWithContentByPGM(Map<String, Object> map);

	List<Map<String, Object>> getGoodsCommentsWithMachineIdGoodsIdHasComment(Map map);

	Long getGoodsSurpleNum(Map map);

	List<Map<String, Object>> queryMachineByUser(@Param("userId") Long userId,
			@Param("specialRoleNum") String specialRoleNum);

	List<Map<String, Object>> queryMachineByMerSys(Long userId);

	List<Map<String, Object>> queryGoodsList(Long machineId);

	List<Map<String, Object>> queryGoodsListByMer(@Param("userId") Long userId, @Param("machineId") Long machineId);

	List<Map<String, Object>> getMachineInfo(@Param("companyId") Long companyId,
			@Param("specialRoleNum") Long specialRoleNum, @Param("userName") String userName,
			@Param("machineList") List<Map<String, Object>> machineList);

	List<Map<String, Object>> getGoodsReplenishment(@Param("machineId") Long machineId,
			@Param("timeType") String timeType, @Param("specialRoleNum") Long specialRoleNum,
			@Param("userName") String userName);
	
	List<Map<String, String>> GridNums(@Param("goodsId") Long goodsId,
			@Param("machineId") Long machineId,@Param("timeType") Long timeType,@Param("companyId") Long companyId);
}