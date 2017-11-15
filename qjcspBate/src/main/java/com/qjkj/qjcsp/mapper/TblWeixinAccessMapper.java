package com.qjkj.qjcsp.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblWeixinAccess;

@MyBatisRepository
public interface TblWeixinAccessMapper {
    int deleteByPrimaryKey(Long weixinAccessId);

    int insert(TblWeixinAccess record);

    int insertSelective(TblWeixinAccess record);

    TblWeixinAccess selectByPrimaryKey(Long weixinAccessId);

    int updateByPrimaryKeySelective(TblWeixinAccess record);

    int updateByPrimaryKey(TblWeixinAccess record);
    
    Map<String,Object> selectByOpenId(@Param("openId")String openId);
    /*wsk*/
    TblWeixinAccess findByOpenId(String openId);
}