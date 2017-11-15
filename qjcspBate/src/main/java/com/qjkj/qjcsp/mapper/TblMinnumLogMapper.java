package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblMinnumLog;
@MyBatisRepository
public interface TblMinnumLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblMinnumLog record);

    int insertSelective(TblMinnumLog record);

    TblMinnumLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblMinnumLog record);

    int updateByPrimaryKey(TblMinnumLog record);
}