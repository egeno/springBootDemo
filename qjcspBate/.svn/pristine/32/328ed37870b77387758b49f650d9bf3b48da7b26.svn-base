package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblAndroidVersion;
import com.qjkj.qjcsp.entity.TblAndroidVersionVO;
@MyBatisRepository
public interface TblAndroidVersionMapper {
	/*根据ID删除版本信息*/
	int deleteByPrimaryKey(Long id);
    /*添加一条数据*/
	//int insert(TblAndroidVersion record);
    /*添加一条版本信息*/
	int insertSelective(TblAndroidVersion record);
    /*根据版本id查找版本信息*/
	TblAndroidVersion selectByPrimaryKey(Long id);
    /*编辑版本信息*/
	int updateByPrimaryKeySelective(TblAndroidVersion record);
    /*修改版本信息*/
	//int updateByPrimaryKey(TblAndroidVersion record);
    /*安卓端查询是否有新版本*/
	TblAndroidVersion getVersionByAndroid(String appType);
    /*查询返回条数*/
    Long findByCount(Map<String, Object> param);
    /*查询所有android版本信息*/
    List<TblAndroidVersion> findAllTblAndroidVersion(Map<String, Object> param);
}