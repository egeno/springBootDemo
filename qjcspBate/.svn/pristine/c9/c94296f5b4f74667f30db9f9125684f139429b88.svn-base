package com.qjkj.qjcsp.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblOrder;

@MyBatisRepository
public interface BalancePayMapper {
	public BigDecimal getUserBalance(Long customerId);
	
	public void updateBalanceForStatus(@Param("orderNum")String orderNum,@Param("realMoney")BigDecimal realMoney);
	
	public void insertCustomerbalanceshoppinglog(@Param("realMoney")BigDecimal realMoney,@Param("customerId")Long customerId,@Param("orderNum")String orderNum);

	public int  checkHasUser(@Param("customerId")Long customerId,@Param("password")String password);
	
	public BigDecimal getUserRealMoney(String orderNum);
	
	public String getorderStauts(String orderNum);
	
	public TblOrder getTblOrder(String orderNum);
	
//	public void  updateCustomerVerifyCode(@Param("verifyCode")String verifyCode,@Param("orderNum")String orderNum);
    
	public void updateOrderChildStatus(@Param("orderId")Long orderId);
	
	List<Long> selectOrderChildByOrderid(@Param("orderId")Long orderId);
	
	public void  updateOrderChildCode(@Param("verifyCode")String verifyCode,@Param("orderChildId")Long orderChildId);
}
