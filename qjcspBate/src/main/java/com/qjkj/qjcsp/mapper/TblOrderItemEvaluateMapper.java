package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblOrderItemEvaluate;
@MyBatisRepository
public interface TblOrderItemEvaluateMapper {
    int deleteByPrimaryKey(Long orderDetailId);

    int insert(TblOrderItemEvaluate record);

    int insertSelective(TblOrderItemEvaluate record);

    TblOrderItemEvaluate selectByPrimaryKey(Long orderDetailId);

    int updateByPrimaryKeySelective(TblOrderItemEvaluate record);

    int updateByPrimaryKey(TblOrderItemEvaluate record);
}