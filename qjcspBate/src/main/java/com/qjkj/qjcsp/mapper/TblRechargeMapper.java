package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblRecharge;

@MyBatisRepository
public interface TblRechargeMapper {
	int deleteByPrimaryKey(Long rechargeId);

	int insert(TblRecharge record);

	int insertSelective(TblRecharge record);

	TblRecharge selectByPrimaryKey(Long rechargeId);
	
	TblRecharge findTblRechargeByRechargeIdForUpdate(Long rechargeId);

	int updateByPrimaryKeySelective(TblRecharge record);

	int updateByPrimaryKey(TblRecharge record);

	public List<Map<String, Object>> findAllrechargeList(Map map);

	TblRecharge selectTblRechargeByOrderNum(String orderNum);

	List<Map<String, Object>> findTblRechargeByCustomerId(Long customerId);

	public List<Map<String, Object>> rechargeStatistics(Map map);

	Long selectAllRechargeAndRechargeShopLogCount(@Param("customerName") String customerName,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	List<Map<String, Object>> selectAllRechargeAndRechargeShopLog(@Param("customerName") String customerName,
			@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("offset") int offset,
			@Param("limit") int limit);

	Long findAllrechargeListCount(Map map);
	//Gekko 账户收支记录
	Long findReceiptAndExpenditureCount (Map map);
	List<Map<String,Object>> findReceiptAndExpenditure(Map map);
	
	//Gekko 统计结算使用 （根据用户id和时间查询未处理的订单）
	List<TblRecharge> findRechargeForOrderByCustomerId(@Param("customerId")Long customerId,@Param("startTime") String startTime);
	TblRecharge findlastRechargeForOrderByCustomerId(@Param("customerId")Long customerId);
	int updateSignRecharges(@Param("rechargeList")List<TblRecharge> rechargeList);
	public List<Map<String, Object>> findAllrechargeList1(Map map);
	
}