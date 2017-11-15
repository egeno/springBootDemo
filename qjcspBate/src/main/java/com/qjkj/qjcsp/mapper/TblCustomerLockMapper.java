package com.qjkj.qjcsp.mapper;



import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomerLock;

@MyBatisRepository
public interface TblCustomerLockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblCustomerLock record);

    int insertSelective(TblCustomerLock record);

    TblCustomerLock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblCustomerLock record);

    int updateByPrimaryKey(TblCustomerLock record);

	TblCustomerLock selectByCustomerId(String customerId);

	int deleteByCustomerId(String customerId);

}