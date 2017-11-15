package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsUserTypes;

@MyBatisRepository
public interface BasicsUserTypesMapper {
    int deleteByPrimaryKey(Short userTypeId);

    int insert(BasicsUserTypes record);

    int insertSelective(BasicsUserTypes record);

    BasicsUserTypes selectByPrimaryKey(Short userTypeId);

    int updateByPrimaryKeySelective(BasicsUserTypes record);

    int updateByPrimaryKey(BasicsUserTypes record);
}