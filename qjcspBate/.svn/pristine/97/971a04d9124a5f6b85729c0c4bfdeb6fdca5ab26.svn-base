package com.qjkj.qjcsp.mapper;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblVerifyCode;

@MyBatisRepository
public interface TblVerifyCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblVerifyCode record);

    int insertSelective(TblVerifyCode record);

    TblVerifyCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblVerifyCode record);

    int updateByPrimaryKey(TblVerifyCode record);

    TblVerifyCode getCountByCodeMobileNum(@Param("mobileNum")String mobileNum, @Param("code")String code);

	TblVerifyCode selectByCodeMobileNum(@Param("mobileNum")String mobileNum, @Param("code")String code);
	
	int deleteByMobile(@Param("mobileNum")String mobileNum);
	
	int deleteVerifyCodeByMobileAndType(@Param("mobileNum")String mobileNum, @Param("codeType")String codeType);
	
	TblVerifyCode selectByMobileNumCode(@Param("mobileNum")String mobileNum, @Param("verifyCode")String verifyCode,
			@Param("codeType")String codeType);
}