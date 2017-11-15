package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsMachineAreaModelLog;
@MyBatisRepository
public interface BasicsMachineAreaModelLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BasicsMachineAreaModelLog record);

    int insertSelective(BasicsMachineAreaModelLog record);

    BasicsMachineAreaModelLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BasicsMachineAreaModelLog record);

    int updateByPrimaryKey(BasicsMachineAreaModelLog record);
}