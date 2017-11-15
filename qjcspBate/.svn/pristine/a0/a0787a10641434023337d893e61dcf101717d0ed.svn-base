package com.qjkj.qjcsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsMenu;

@MyBatisRepository
public interface BasicsMenuMapper {
	int deleteKey(Integer menuId);
    
    int deleteByPrimaryKey(Integer menuId);
    
    int deleteByPrimaryKeyList(@Param("list")List<BasicsMenu> list);

    int insert(BasicsMenu record);

    int insertSelective(BasicsMenu record);

    BasicsMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(BasicsMenu record);

    int updateByPrimaryKey(BasicsMenu record);
    
    List<BasicsMenu> queryMenuList();
    
    List<BasicsMenu> findAllFunctionList();
    
    List<BasicsMenu> findAllFunctionListsForMenu(@Param("pid")String pid,@Param("menuCategory")String menuCategory);
    
    List<BasicsMenu> findAllFunctionLists(String pid);
    
    List<BasicsMenu> findFunctionListForPermission(String menuCategory);
       
    int updateIsUsed(BasicsMenu record);
    
    int updateIsUseds(BasicsMenu record);
    
    List<BasicsMenu> findFunctionListsByPId(String pid);
}