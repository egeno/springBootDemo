package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblAndroidVersion;
import com.qjkj.qjcsp.entity.TblIosVersion;
@MyBatisRepository
public interface TblIosVersionMapper {
	/*根据ID删除版本信息*/
    int deleteByPrimaryKey(Integer id);
    
   // int insert(TblIosVersion record);
    /*添加版本信息*/
    int insertSelective(TblIosVersion record);
    /*根据版本id查找版本信息*/
    TblIosVersion selectByPrimaryKey(Integer id);
    /*编辑版本信息*/
    int updateByPrimaryKeySelective(TblIosVersion record);

    //int updateByPrimaryKey(TblIosVersion record);
    
    /*查询返回条数*/
    Long findByCount(Map<String, Object> param);
    
    TblIosVersion selectIosVersionByVersionKey(String versionKey);
    
    /*查询所有android版本信息*/
    List<TblIosVersion> findAllTblIosVersion(Map<String, Object> param);
}