package com.qjkj.qjcsp.mapper;

import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomerInfo;

@MyBatisRepository
public interface TblCustomerInfoMapper {
    int deleteByPrimaryKey(Long customerId);

    int insert(TblCustomerInfo record);

    int insertSelective(TblCustomerInfo record);

    TblCustomerInfo selectByPrimaryKey(Long customerId);

    int updateByPrimaryKeySelective(TblCustomerInfo record);

    int updateByPrimaryKey(TblCustomerInfo record);
    
    int insertId(Long customerId);
    
    public Map<String,String> selectByPrimaryKeyMap(Long customerId);
}