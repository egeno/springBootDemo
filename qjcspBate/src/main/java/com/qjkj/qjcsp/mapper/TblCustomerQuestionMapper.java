package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomerQuestion;
@MyBatisRepository
public interface TblCustomerQuestionMapper {
    int deleteByPrimaryKey(Long questionId);

    int insert(TblCustomerQuestion record);

    int insertSelective(TblCustomerQuestion record);

    TblCustomerQuestion selectByPrimaryKey(Long questionId);

    int updateByPrimaryKeySelective(TblCustomerQuestion record);

    int updateByPrimaryKey(TblCustomerQuestion record);
}