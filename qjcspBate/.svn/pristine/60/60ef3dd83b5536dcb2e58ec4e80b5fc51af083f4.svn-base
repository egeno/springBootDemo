package com.qjkj.qjcsp.mapper.order.app;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.OrderNum;
import com.qjkj.qjcsp.entity.order.OrderComment;
import com.qjkj.qjcsp.entity.order.OrderDeviceEvaluate;

@MyBatisRepository
public interface OrderAppMapper {
	
	/**
	 * 2.3.14.	根据登录用户用户id和订单状态获取相应条件订单列表
	 * @param map
	 * @return
	 *
	 */
	public List<Map<String,Object>> getOrdersByStatus(Map map);
	
	public List<Map<String,Object>> getCustomerOrders(Map map);
	
	
	public Long  getOrderStatusForAlam(Long orderChildId);
	
	public String getOrderStatusForRefund(String orderNum);
	/**
	 * 根据customerid获取该用户所有的订单数据
	 * */
	public List<Map<String, Object>> getAllOrderByCustomerId(Long customerid);
	/**
	 * 根据orderId获取所有子订单
	 * */
	public List<Map<String, Object>> getOrderChildByOrderId(Long orderId);
	
	
	/**
	 * 根据用户id得到待付款数量
	 * @param customerId
	 * @return
	 *
	 */
	public String getReadyPayCount(int customerId);
	
	/**
	 * 根据用户id得到待取餐数量
	 * @param customerId
	 * @return
	 *
	 */
	public String getReadyPickCount(int customerId);
	/**
	 * 根据用户id得到待评价数量
	 * @param customerId
	 * @return
	 *
	 */
	public String getReadyCommentCount(int customerId);
	/**
	 * 根据用户id得到售后/退款数量
	 * @param customerId
	 * @return
	 *
	 */
	public String getReadyDealCount(int customerId);
	public String getOrderAmountNum(int customerId);
	
	
	

	/**
	 * 插入评论，对账
	 * @author yehx
	 * @date 2016年1月4日
	 * @param orderComment
	 *
	 */
	public void insertOrderComment(List<OrderComment> orderComments);
	/**
	 * 通过订单号得到订单的一些信息
	 * @author yehx
	 * @date 2016年1月5日
	 * @return
	 *
	 */
	public OrderDeviceEvaluate getOrderDeviceEvaluate(String orderNum);
	/**
	 * 将订单评价信息插入到设备评价表中
	 * @author yehx
	 * @date 2016年1月5日
	 * @param deviceEvaluate
	 *
	 */
	public void insertOrderDeviceEvaluate(OrderDeviceEvaluate deviceEvaluate);
	/**
	 * 评论结束之后，修改订单状态
	 * @author yehx
	 * @date 2016年1月14日
	 * @param OrderNum
	 *
	 */
	public void afterCommentUpdateOrderStatus(String OrderNum);
	/**
	 * 通过用户id和订单号得到订单id,手机号，设备id,设备硬件id
	 * 公司id，下单时间
	 * 
	 */
	public List<OrderComment> getOrderInfo(Map map);
	

	public void customerOrderDelete(Map map);
	
	public List<Map<String, Object>> getAllOrderIdByOrderNums(Map map);
	
	public void customerChildOrderDelete(Map map);
	
	/*2：补货人员,3：商户*/
	List<Map<String, Object>> queryRealTimeTblOrder(@Param("userId")Long userId, @Param("specialRoleNum")String specialRoleNum,
			@Param("machineId")Long machineId);
	
	 /*4：众包商*/
	List<Map<String, Object>> queryRealTimeTblOrderByMerSys(@Param("userId")Long userId, @Param("machineId")Long machineId);
	//实时订单 补货人员，众包商零售
	List<Map<String, Object>> queryRealTimeGoodsList(@Param("orderId")Long orderId, @Param("timeType")String timeType);
	//实时订单 补货人员， 众包商预订
	List<Map<String, Object>> queryRealTimeGoodsListBooking(@Param("orderId")Long orderId, @Param("timeType")String timeType);
	
	
	
	//实时订单  商户零售
	List<Map<String, Object>> queryRealTimeGoodsListByMer(@Param("userId")Long userId, @Param("orderId")Long orderId, @Param("timeType")String timeType);
	//实时订单  商户预订
	List<Map<String, Object>> queryRealTimeGoodsListByBooking(@Param("userId")Long userId, @Param("orderId")Long orderId, @Param("timeType")String timeType);
	
	
	//获取历史订单
	List<Map<String, Object>> queryTblOrderGoods(@Param("userId")Long userId, @Param("specialRoleNum")String specialRoleNum,@Param("machineId")Long machineId);
	
	
	
	
	List<Map<String, Object>> queryGoodsComplete(@Param("userId")Long userId, @Param("specialRoleNum")String specialRoleNum,@Param("startDate")String startDate,
			@Param("endDate")String endDate,@Param("machineId")Long machineId);
	
	
	List<Map<String, Object>> queryGoodsComplete2(@Param("userId")Long userId, @Param("startDate")String startDate,
			@Param("endDate")String endDate,@Param("machineId")Long machineId);
	                                         
	
	//List<Map<String, Object>> queryGoodsTblOrderByMerSys(@Param("userId")Long userId);
	
	
	
	
}
