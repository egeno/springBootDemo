package com.qjkj.qjcsp.mapper;

import java.util.List;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsSpecialRole;

@MyBatisRepository
public interface BasicsSpecialRoleMapper {
    int deleteByPrimaryKey(String specialRoleNum);

    int insert(BasicsSpecialRole record);

    int insertSelective(BasicsSpecialRole record);

    BasicsSpecialRole selectByPrimaryKey(String specialRoleNum);

    int updateByPrimaryKeySelective(BasicsSpecialRole record);

    int updateByPrimaryKey(BasicsSpecialRole record);
    
    List<BasicsSpecialRole> selectAll();
    
}