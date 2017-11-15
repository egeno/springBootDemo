package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsUserMachine;

@MyBatisRepository
public interface BasicsUserMachineMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BasicsUserMachine record);

    int insertSelective(BasicsUserMachine record);

    BasicsUserMachine selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BasicsUserMachine record);

    int updateByPrimaryKey(BasicsUserMachine record);
    
    List<BasicsUserMachine> getMaitenance(Long machineId);
    
    int delUserMachine(@Param("userId")Long userId, @Param("specialRoleNum")String specialRoleNum);
    
    int judgeExist(@Param("userId")Long userId, @Param("specialRoleNum")String specialRoleNum);
    
    int isExistbyDeviceCodeAndMobileNum(@Param("machineId")Long machineId, @Param("mobileNum")String mobileNum);
    
    List<BasicsUserMachine> findByMobileNumUserId(@Param("machineId")Long machineId, @Param("mobileNum")String mobileNum);

	int judgeByUserIdSpecialRoleNum(@Param("userId")String userId, @Param("result")String result);
	
	BasicsUserMachine queryUserbyDeviceCodeAndMobile(@Param("machineId")Long machineId, @Param("mobileNum")String mobileNum);
	
	List<Map<String,Object>> findMachineUserByUserId(@Param("userId")Long userId);
	
	List<Map<String,Object>> findmerchantNameListByMachineIds(@Param("map")List<Map<String,Object>> map);
    
}