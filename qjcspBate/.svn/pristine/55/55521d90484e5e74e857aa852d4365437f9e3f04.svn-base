package com.qjkj.qjcsp.mapper;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsLoginRecord;

@MyBatisRepository
public interface BasicsLoginRecordMapper {
	int deleteByPrimaryKey(Long id);

	int insert(BasicsLoginRecord record);

	int insertSelective(BasicsLoginRecord record);

	BasicsLoginRecord selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(BasicsLoginRecord record);

	int updateByPrimaryKey(BasicsLoginRecord record);

	BasicsLoginRecord selectByCustomerId(@Param("customerId")Long id);

	BasicsLoginRecord selectByUserId(@Param("userId")Long id);
}