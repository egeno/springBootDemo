package com.qjkj.qjcsp.mapper;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsRoleMenuLog;

@MyBatisRepository
public interface BasicsRoleMenuLogMapper {
    int deleteByPrimaryKey(BasicsRoleMenuLog key);

    int insert(BasicsRoleMenuLog record);

    int insertSelective(BasicsRoleMenuLog record);

    BasicsRoleMenuLog selectByPrimaryKey(BasicsRoleMenuLog key);

    int updateByPrimaryKeySelective(BasicsRoleMenuLog record);

    int updateByPrimaryKey(BasicsRoleMenuLog record);

	boolean updateOperation0(String roleId);

	int getCount(@Param("roleId")String roleId,@Param("menuId")String menuId);

	boolean updateOperation1(BasicsRoleMenuLog record);
}