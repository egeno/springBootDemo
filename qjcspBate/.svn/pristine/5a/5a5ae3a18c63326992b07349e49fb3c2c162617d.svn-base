package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomerDisActLog;
@MyBatisRepository
public interface TblCustomerDisActLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblCustomerDisActLog record);

    int insertSelective(TblCustomerDisActLog record);

    TblCustomerDisActLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblCustomerDisActLog record);

    int updateByPrimaryKey(TblCustomerDisActLog record);
    
    int deleteDisLog(String orderNum);
    
    Long findByCount(Map<String,Object> param);
    
    List<Map<String, Object>> findByList(Map<String, Object> param);
    
    TblCustomerDisActLog queryDisLogByOrderNum(String orderNum);
    
    
}