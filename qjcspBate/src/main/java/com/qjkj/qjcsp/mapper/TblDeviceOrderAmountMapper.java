package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblDeviceOrderAmount;

@MyBatisRepository
public interface TblDeviceOrderAmountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblDeviceOrderAmount record);

    int insertSelective(TblDeviceOrderAmount record);

    TblDeviceOrderAmount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblDeviceOrderAmount record);

    int updateByPrimaryKey(TblDeviceOrderAmount record);
    
    int selectOrderAmountByMachineId(Long machineId);
    
    int deleteByMachineId(Long machineId);
    
}