package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.GUniversity;

@MyBatisRepository
public interface GUniversityMapper {
    int deleteByPrimaryKey(Long universityId);

    int insert(GUniversity record);

    int insertSelective(GUniversity record);

    GUniversity selectByPrimaryKey(Long universityId);

    int updateByPrimaryKeySelective(GUniversity record);

    int updateByPrimaryKey(GUniversity record);
    
    List<GUniversity> queryAllUniversitiesList();
    
    List<Map<String, Object>> queryZoneByCityName(String cityName);
    
    List<Map<String, Object>> queryUniversitiesByZoneAndCity(@Param("zoneName")String zoneName, @Param("cityId")String cityId);
    
    List<Map<String, Object>> queryUniversitiesByGPSLocation(@Param("longitude")double longitude, @Param("latitude")double latitude);
    
	List<Map<String, Object>> fuzzySearchUniversity(@Param("keywords")String keywords);
	
	String getCityNameByUniversityId(Long universityId);
}