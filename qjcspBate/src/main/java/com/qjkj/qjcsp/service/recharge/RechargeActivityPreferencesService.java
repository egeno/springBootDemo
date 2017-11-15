package com.qjkj.qjcsp.service.recharge;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblRechargeActivitySun;
import com.qjkj.qjcsp.mapper.TblRechargeActivityMapper;

@Service
public class RechargeActivityPreferencesService {
	@Autowired
	private TblRechargeActivityMapper tblRechargeActivityMapper;
	
	public List<Map<String,Object>> findAllrechargeActivityList(Map map){
		
		return tblRechargeActivityMapper.findAllrechargeActivityList(map);
		
	}
	@Transactional	
	public void insertRechargeActivity(TblRechargeActivitySun rechargeActivitySun){
		tblRechargeActivityMapper.insert(rechargeActivitySun);
	}
	@Transactional
	public void updateRechargeActivity(TblRechargeActivitySun rechargeActivitySun){
		tblRechargeActivityMapper.updateByPrimaryKeySelective(rechargeActivitySun);
	}
	@Transactional
	public void deleteRechargeActivity(Long activityId){
		
		tblRechargeActivityMapper.deleteByPrimaryKey(activityId);
	}
	public Long findByCount(Map<String, Object> map1) {
		return tblRechargeActivityMapper.findByCount(map1);
	}
	
	public void updateActivityEffectSymbol(Long activityId){
		tblRechargeActivityMapper.updateActivityEffectSymbol(activityId);
	}
}
