package com.qjkj.qjcsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsUserRole;

@MyBatisRepository
public interface BasicsUserRoleMapper {
    
	int deleteUserRoleRelByUserId(Long userId);
	
	int deleteUserRoleRelByRoleId(Long roleId);

    int insert(BasicsUserRole basicsUserRole);
    
    List<BasicsUserRole> findBasicsUserRoleByUserId(Long userId);
    
    int findRoleIdByUserId(@Param("userId")int userId, @Param("specialRoleNum")String specialRoleNum);
    
    List<Long> findBasicsUserRoleByRoleId(Long roleId);
    
    //查看当前用户是否是商户
    String getSpecialRoleNum(@Param("userId")Long userId,@Param("machineId")Long machineId);
    
    
    String getSpecialRoleNumByUserId(@Param("userId")Long userId);
}