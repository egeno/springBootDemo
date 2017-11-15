package com.qjkj.qjcsp.service.recharge;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.mapper.TblRechargeMapper;

@Service
public class RechargeService {
	@Autowired
	private TblRechargeMapper tblRechargeMapper;
	
	public List<Map<String,Object>> findAllrechargeList(Map map){
		return tblRechargeMapper.findAllrechargeList(map);
	}
	public Long findAllrechargeListCount(Map map)
	{
		return tblRechargeMapper.findAllrechargeListCount(map);
	}
	
	public List<Map<String,Object>> rechargeStatistics(Map map){
		return tblRechargeMapper.rechargeStatistics(map);
	}
	public Long findReceiptAndExpenditureCount(Map map){
		return tblRechargeMapper.findReceiptAndExpenditureCount(map);
	}
	public List<Map<String,Object>> findReceiptAndExpenditure(Map map){
		return tblRechargeMapper.findReceiptAndExpenditure(map);
	}
	
	/**
	 * 以下为结算记录相关
	 */
	public List<Map<String,Object>> findAllrechargeList1(Map map){
		return tblRechargeMapper.findAllrechargeList1(map);
	}
}
