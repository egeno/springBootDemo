package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsSpecialRole;
import com.qjkj.qjcsp.entity.BasicsUserMachine;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.TblCustomer;

@MyBatisRepository
public interface BasicsUsersMapper {
	int deleteByPrimaryKey(Long userId);

	int insert(BasicsUsers record);

	int insertSelective(BasicsUsers record);

	BasicsUsers selectByPrimaryKey(Long userId);
    
    BasicsUsers selectByMobileNum(String userMobile);

	int updateByPrimaryKeySelective(BasicsUsers record);

	int updateByPrimaryKey(BasicsUsers record);

	long getCount(Map<String, Object> map);

	List<Map<String,Object>> findAllEmployeeList(Map<String, Object> map);

	List<BasicsUserMachine> findEmployeeMachineByPrimaryKey(Long userId);

	long getCountByMaintenance(Map<String, Object> map);

	List<BasicsUsers> findAllMaintenanceList(Map<String, Object> map);

	List<BasicsUsers> findUserIdByMobile(String mobileNum);

	String findUserAccount(Long createUserId);

	String findModUserAccount(Long modUserId);
	
	List<Map<String,Object>> findAllUserByCompanyId(@Param("companyId")Long companyId);
	
	BasicsUsers findAllUserByUserName(@Param("userName")String userName);

	Map<String, Object> getUserInfo(@Param("userId") Long userId);

	String getSpecialRoleNumByUserMobileNum(String mobileNum);
	
	//Gekko 根据设备id查询类型为specialRoleNum的 列表
	List<BasicsUsers> findUsersByMachineId(@Param("machineId")Long machineId,@Param("specialRoleNum")String specialRoleNum);
	
}