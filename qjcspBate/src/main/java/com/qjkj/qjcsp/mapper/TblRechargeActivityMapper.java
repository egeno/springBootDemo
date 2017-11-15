package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblRechargeActivity;
import com.qjkj.qjcsp.entity.TblRechargeActivitySun;

@MyBatisRepository
public interface TblRechargeActivityMapper {
	void deleteByPrimaryKey(Long activityId);

	int insert(TblRechargeActivitySun record);

	int insertSelective(TblRechargeActivity record);

	TblRechargeActivity selectByPrimaryKey(Long activityId);

	int updateByPrimaryKeySelective(TblRechargeActivitySun record);

	int updateByPrimaryKey(TblRechargeActivity record);

	List<Map<String, Object>> selectAllRechargeActivity();

	public List<Map<String, Object>> findAllrechargeActivityList(Map map);

	TblRechargeActivity selectValidActivityByPrimaryKey(@Param("activityId") Long activityId);

	Long findByCount(Map<String, Object> map1);

	void updateActivityEffectSymbol(@Param("activityId") Long activityId);
}