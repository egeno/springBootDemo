package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblMachineAreaModelMostSaleLog;
@MyBatisRepository
public interface TblMachineAreaModelMostSaleLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblMachineAreaModelMostSaleLog record);

    int insertSelective(TblMachineAreaModelMostSaleLog record);

    TblMachineAreaModelMostSaleLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblMachineAreaModelMostSaleLog record);

    int updateByPrimaryKey(TblMachineAreaModelMostSaleLog record);
}