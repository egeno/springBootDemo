package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblMachineTemplateRetailDateLog;
@MyBatisRepository
public interface TblMachineTemplateRetailDateLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblMachineTemplateRetailDateLog record);

    int insertSelective(TblMachineTemplateRetailDateLog record);

    TblMachineTemplateRetailDateLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblMachineTemplateRetailDateLog record);

    int updateByPrimaryKey(TblMachineTemplateRetailDateLog record);
}