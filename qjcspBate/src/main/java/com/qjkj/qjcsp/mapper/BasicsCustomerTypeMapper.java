package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsCustomerType;
@MyBatisRepository
public interface BasicsCustomerTypeMapper {
    int deleteByPrimaryKey(Long customerTypeId);

    int insert(BasicsCustomerType record);

    int insertSelective(BasicsCustomerType record);

    BasicsCustomerType selectByPrimaryKey(Long customerTypeId);

    int updateByPrimaryKeySelective(BasicsCustomerType record);

    int updateByPrimaryKey(BasicsCustomerType record);
}