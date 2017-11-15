package com.qjkj.qjcsp.mapper;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;

@MyBatisRepository
public interface PreissueOrderMapper {

	int getOrderCountbyCustomerId(@Param("customerId") Long customerId, @Param("orderStatus")String orderStatus);
}
