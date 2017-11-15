package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.GHotCity;

@MyBatisRepository
public interface GHotCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GHotCity record);

    int insertSelective(GHotCity record);

    GHotCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GHotCity record);

    int updateByPrimaryKey(GHotCity record);
    
    int addOrUpdateHotCity(Long cityId);
}