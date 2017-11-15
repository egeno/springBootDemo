package com.qjkj.qjcsp.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomerOrderDis;
import com.qjkj.qjcsp.entity.TblCustomerOrderDisSun;
@MyBatisRepository
public interface TblCustomerOrderDisMapper {
	int insert(TblCustomerOrderDis record);

    int insertSelective(TblCustomerOrderDisSun tblCustomerOrderDisSun);
    
    public List<TblCustomerOrderDisSun>  findAllOrderDisList(TblCustomerOrderDisSun tblCustomerOrderDisSun);
    
    public int getCount();
    
    public void updateorderdis(TblCustomerOrderDisSun tblCustomerOrderDisSun);
    
    BigDecimal findDiscountMoney();
}