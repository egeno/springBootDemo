package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomerRechargeLog;
@MyBatisRepository
public interface TblCustomerRechargeLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblCustomerRechargeLog record);

    int insertSelective(TblCustomerRechargeLog record);

    TblCustomerRechargeLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblCustomerRechargeLog record);

    int updateByPrimaryKey(TblCustomerRechargeLog record);
}