package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblTemplateAreaModelLog;
@MyBatisRepository
public interface TblTemplateAreaModelLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblTemplateAreaModelLog record);

    int insertSelective(TblTemplateAreaModelLog record);

    TblTemplateAreaModelLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblTemplateAreaModelLog record);

    int updateByPrimaryKey(TblTemplateAreaModelLog record);
}