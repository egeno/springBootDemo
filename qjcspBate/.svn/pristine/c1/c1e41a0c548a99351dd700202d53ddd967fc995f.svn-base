package com.qjkj.qjcsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsJoininfo;
@MyBatisRepository
public interface BasicsJoininfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BasicsJoininfo record);

    int insertSelective(BasicsJoininfo record);

    BasicsJoininfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BasicsJoininfo record);

    int updateByPrimaryKey(BasicsJoininfo record);
    

	/**
	 * 查询手机号是否已经申请过合作
	 * */
	int checkIsSend(@Param("applicantTel") String applicantTel, @Param("joinType") String joinType);
	
	
	
	List<BasicsJoininfo> findJoinInfoList(BasicsJoininfo basicsJoininfo);
	 int findJoinInfoCount(BasicsJoininfo basicsJoininfo);
}