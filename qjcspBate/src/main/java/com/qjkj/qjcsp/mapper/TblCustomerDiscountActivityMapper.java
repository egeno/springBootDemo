package com.qjkj.qjcsp.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomerDiscountActivity;
@MyBatisRepository
public interface TblCustomerDiscountActivityMapper {
    int insert(TblCustomerDiscountActivity record);

    int insertSelective(TblCustomerDiscountActivity record);
    
    public List<TblCustomerDiscountActivity>  findAllDiscountActivityList(TblCustomerDiscountActivity tblCustomerDiscountActivity);

    public int getCount();
    
    public void updateDiscountActivity(TblCustomerDiscountActivity tblCustomerDiscountActivity);

    
    BigDecimal findValidActivity();

}