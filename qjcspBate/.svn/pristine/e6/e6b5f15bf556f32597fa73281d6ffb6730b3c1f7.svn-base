package com.qjkj.qjcsp.mapper;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsUserRoleLog;
import com.qjkj.qjcsp.entity.BasicsUserRoleLogKey;

@MyBatisRepository
public interface BasicsUserRoleLogMapper {
    int deleteByPrimaryKey(BasicsUserRoleLogKey key);

    int insert(BasicsUserRoleLog record);

    int insertSelective(BasicsUserRoleLog record);

    BasicsUserRoleLog selectByPrimaryKey(BasicsUserRoleLogKey key);

    int updateByPrimaryKeySelective(BasicsUserRoleLog record);

    int updateByPrimaryKey(BasicsUserRoleLog record);

	int updateOperation0(@Param("userId")String userId, @Param("operationUserId")int operationUserId);

	int getCount(@Param("userId")Long userId, @Param("roleId")Long roleId);

	boolean updateOperation1(BasicsUserRoleLog basicsUserRoleLog);
}