package com.qjkj.qjcsp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderVo;

@MyBatisRepository
public interface TblOrderMapper {
	//查询订单明细列表
	List<Map<String, Object>> selectListOrderDetail(@Param("orderNum") String orderNum);
	
	int deleteByPrimaryKey(Long orderId);

	int insert(TblOrder record);

	int insertSelective(TblOrder record);

	TblOrder selectByPrimaryKey(Long orderId);

	int updateByPrimaryKeySelective(TblOrder record);

	int updateByPrimaryKey(TblOrder record);
	
	/**
	 * 统计订单数量
	 * */
	Long findByCount(Map<String, Object> param);
	
	/**
	 * 统计订单list
	 * */
	List<Map<String, Object>> findByList(Map<String, Object> param) ;
	/**
	 * 财务统计列表
	 */
	List<Map<String, Object>> findByfinancial(Map<String, Object> param) ;
	/**
	 * 订单列表导出报表方法
	 * */
	List<Map<String, Object>> exportExcelList(Map<String, Object> param) ;

	// 查询订单明细
	TblOrder selectByOrderNum(String orderNum);
	
	/**
	 * 根据订单号得到订单明细
	 * */
	TblOrder selectOrderDetailsByOrderNum(String orderNum);

	/**
	 * 根据用户ID和订单编号获取订单类型
	 * */
	Map<String, Object> getOrderTypeAndorderId(@Param("customerId") Long customerId, @Param("orderNum") String orderNum);
	//通过orderId修改订单状态
	void updateOrderStatusByOrderId(@Param("orderId") Long orderId);
    
    TblOrder selectByDeviceCode(@Param("deviceCode")String deviceCode, @Param("identifyingCode")String identifyingCode);
    
    /**
	 * 修改父订单状态
	 * */
    void updateOrderStatus(@Param("orderNum")String orderNum,
    		@Param("orderStatus")String orderStatus, @Param("compeleteTime")Date compeleteTime);
    /**
	 * 得到订单状态
	 * */
    String getOrderStatusbyOrderNum(String orderNum);
    
    TblOrder searchOrder(@Param("orderNum")String orderNum);
    
    int cancelUnpaidOrder(@Param("orderId")Long orderId, @Param("orderStatus")String orderStatus);
    
    Long getOrderIdbyOrderNum(String orderNum);
    
    TblOrder queryOrderByOrderId(Long orderId);
    
    String getAddressByOrderId(Long orderId);

	List<Map<String, Object>> selectByMachineId(String machineId);
	
	//根据用户ID和用户类型查询订单详细
	List<Map<String, Object>> queryOrderByUser(@Param("userId")Long userId, @Param("specialRoleNum")String specialRoleNum,@Param("startDate")String startDate,
			@Param("endDate")String endDate,@Param("machineId")Long machineId);
	
	List<Map<String, Object>> queryOrderByMerSys(@Param("userId")Long userId,@Param("startDate")String startDate, @Param("endDate")String endDate,@Param("machineId")Long machineId);
	
	List<Map<String, Object>> queryOrderChild(@Param("orderId")Long orderId);
	
	List<Map<String, Object>> queryOrderDetail(@Param("orderChildId")Long orderChildId);
	
	List<Map<String, Object>> queryOrderDetailByMer(@Param("orderChildId")Long orderChildId, @Param("userId")Long userId);

	//根据设备ID查询订单编号和子订单ID
	Map<String, Object> selectOrderByMachineId(@Param("machineId")Long machineId);

	//根据验证码查询子订单ID和订单Num
	Map<String, Object> selectOrderByIdCode(@Param("idCode")String idCode);
	
	Long selectorderIdByorderChildId(@Param("orderChildId")Long orderChildId);
	//查询总订单下子订单状态为5的数量
	int selectCountByorderId(@Param("orderId")Long orderId);
}