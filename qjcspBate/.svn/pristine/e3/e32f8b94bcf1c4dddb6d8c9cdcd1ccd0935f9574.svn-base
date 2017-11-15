package com.qjkj.qjcsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsMenu;
import com.qjkj.qjcsp.entity.BasicsRoleMenu;

@MyBatisRepository
public interface BasicsRoleMenuMapper {
	int deleteRoleMenuRelByRoleId(Long roleId);
	
	int deleteRoleMenuRelByMenuId(@Param("list")List<BasicsMenu> list);

    int insert(BasicsRoleMenu basicsRoleMenu);
    
    List<BasicsRoleMenu> findBasicsRoleMenuByRoleId(Long roleId);
    
    int judgeIsExist(List<BasicsMenu> list);
}