package com.qjkj.qjcsp.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsMachineCell;
import com.qjkj.qjcsp.entity.OrderByUserMap;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;

@MyBatisRepository
public interface TblOrderChildMapper {
	/*//根据订单ID查找订单明细
	List<TblOrder> selectByOrder(Long orderId);*/
	
	int deleteByPrimaryKey(Long orderChildId);

	int insert(TblOrderChild record);

	int insertSelective(TblOrderChild record);

	TblOrderChild selectByPrimaryKey(Long orderChildId);

	int updateByPrimaryKeySelective(TblOrderChild record);

	int updateByPrimaryKey(TblOrderChild record);

	/**
	 * 根据总订单修改所有的子订单状态
	 */
	void updateByOrderId(@Param("orderId") Long orderId, @Param("orderChildStatus") String orderChildStatus);

	List<TblOrderChild> selectByOrderId(Long orderId);

	/**
	 * 根据总订单ID获取子订单的取餐时间和退款截止时间
	 */
	List<Map<String, Object>> getOrderChildByOrderId(@Param("orderId") Long orderId);

	/**
	 * 根据子订单的orderChildNum获取子订单的取餐时间和退款截止时间
	 */
	List<Map<String, Object>> getOrderChildByOrderNum(@Param("orderChildNums") List<String> orderChildNums);

	/**
	 * 根据childorderId修改子订单状态
	 */
	void updateChildOrderStatusByChildorderId(@Param("refundMoney") BigDecimal refundMoney,
			@Param("childorderId") Long childorderId);

	/**
	 * 根据子订单orderNum修改子订单状态为待退款
	 */
	void updateChildOrderStatusByOrderChildNumsList(@Param("orderChildNums") List<String> orderChildNums);

	void updateChildOrderStatusByOrderId(@Param("refundMoney") BigDecimal refundMoney, @Param("orderId") Long orderId);

	TblOrderChild selectByDeviceCode(@Param("machineId") String machineId, @Param("idCode") String idCode);

	List<BasicsMachineCell> getBasicsMachineCells(Map<String, Object> map);

	List<Map<String, Object>> getRefundOrderList(@Param("customerId") Long customerId);

	List<Map<String,Object>> getOrderChildByOrderNumAndrefundReasonOrderChildStatus(@Param("orderNum")String orderNum,@Param("refundReasonOrderChildStatus")String refundReasonOrderChildStatus,@Param("orderChildNum") String orderChildNum);

	List<Map<String, Object>> getOrderDetailsByOrderNum(@Param("orderChildNum") String orderChildNum);

	int updateOrderChildStatusByOrderChildId(@Param("orderChildId")Long orderChildId, @Param("orderChildStatus")String orderChildStatus);
	
	int updateTakenOrderChild(@Param("orderChildList")List<TblOrderChild>orderChildList);
	
	String getTakingGoodsInfo(Long orderId);
	List<Map<String, Object>> queryOrderChildbyOrderNum(String orderNum);
	//按时间和已完成订单类型查询待结算订单  from 子订单（按用户id分组） (待结算)
	List<OrderByUserMap> selectByPendingSettlements(@Param("startTime") String startTime);
	int updateSignOrderChild(@Param("orderChildList")List<TblOrderChild>orderChildList);
	//(结算)end

	int updateAlarmOrder(String orderChildId);

	//根据订单ID查找子订单状态为3的 xlk
	List<TblOrderChild> getOrderChildListByOrderId(Long orderId);
}