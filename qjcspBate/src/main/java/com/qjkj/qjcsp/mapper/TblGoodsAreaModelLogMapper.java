package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblGoodsAreaModelLog;
@MyBatisRepository
public interface TblGoodsAreaModelLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblGoodsAreaModelLog record);

    int insertSelective(TblGoodsAreaModelLog record);

    TblGoodsAreaModelLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblGoodsAreaModelLog record);

    int updateByPrimaryKey(TblGoodsAreaModelLog record);
}