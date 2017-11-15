package com.qjkj.qjcsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.Address;

@MyBatisRepository
public interface AddressMapper {

	List<Address> findProvinceAllList();
	
	List<Address> findCityAllList(@Param("provinceId")Long provinceId);
	
}
