package com.qjkj.qjcsp.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblItemDetail;
import com.qjkj.qjcsp.entity.TblOrderDetail;

@MyBatisRepository
public interface TblOrderDetailMapper {
	int deleteByPrimaryKey(Long orderDetailId);

	int insert(TblOrderDetail record);

	int insertSelective(TblOrderDetail record);

	TblOrderDetail selectByPrimaryKey(Long orderDetailId);

	int updateByPrimaryKeySelective(TblOrderDetail record);

	int updateByPrimaryKey(TblOrderDetail record);

	List<Map<String, Object>> findGoodsSale(@Param("areaModelId") Long areaModelId, @Param("machineId") Long machineId,
			@Param("beforeDate") String beforeDate, @Param("afterDate") String afterDate);

	List<TblOrderDetail> selectByOrderChildId(Long orderChildId);

	List<Map<String, Object>> findPreissueGoods(@Param("machineId") Long machineId,
			@Param("areaModelId") Long areaModelId);

	List<Map<String, Object>> getAllMoneyByOrderId(@Param("orderId") Long orderId);

	List<Map<String, Object>> getAllMoneyByChildOrderId(@Param("orderChildNums") List<String> orderChildNums);

	List<Map<String, Object>> findOrderDetailsByDeviceCodeAndOrderNum(@Param("orderNum") String orderNum,
			@Param("machineId") Long machineId);

	List<Map<String, Object>> findDetailByChildIdAndMachineId(@Param("orderChildId") String orderChildId,
			@Param("machineId") String machineId);

	List<Map<String, Object>> findDetailByChildNumAndMachineId(@Param("orderChildNum") String orderChildNum,
			@Param("machineId") String machineId);

	/* 根据子订单号获取订单详情 */
	List<Map<String, Object>> queryOrderDetailsbyOrderChildNum(String orderChildNum);

	// 多条件查询商品预订数量
	List<Map<String, Object>> queryFoodsPreNum(@Param("companyId") Long companyId, @Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("machineId") Long machineId,
			@Param("areaModelId") Long areaModelId, @Param("foodName") String foodName, @Param("offset") int offset,
			@Param("limit") int limit);

	Integer queryFoodsPreNumCount(@Param("companyId") Long companyId, @Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("machineId") Long machineId,
			@Param("areaModelId") Long areaModelId, @Param("foodName") String foodName);
	BigDecimal getSumMoneyByChildOrderId (@Param("orderChildId") Long orderChildId);
	
	Integer orderDetailCount(@Param("goodsId") Long goodsId);
}