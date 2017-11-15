package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblOrderMode;
@MyBatisRepository
public interface TblOrderModeMapper {
    int deleteByPrimaryKey(String modeNum);

    int insert(TblOrderMode record);

    int insertSelective(TblOrderMode record);

    TblOrderMode selectByPrimaryKey(String modeNum);

    int updateByPrimaryKeySelective(TblOrderMode record);

    int updateByPrimaryKey(TblOrderMode record);
}