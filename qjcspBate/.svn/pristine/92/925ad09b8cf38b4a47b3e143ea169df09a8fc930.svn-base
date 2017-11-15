package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.GCity;

@MyBatisRepository
public interface GCityMapper {
	int deleteByPrimaryKey(Long cityId);

	int insert(GCity record);

	int insertSelective(GCity record);

	GCity selectByPrimaryKey(Long cityId);

	int updateByPrimaryKeySelective(GCity record);

	int updateByPrimaryKey(GCity record);

	/**
	 * 获取所有城市列表
	 */
	List<Map<String, Object>> getAllCitiesList();
	/**
	 * 获取热门城市列表
	 */
	List<Map<String, Object>> getHotCitiesList();
	
	List<GCity> queryAllCitiesList();
	
	Long getCityIdByCityName(String cityName);
	
	List<Map<String, Object>> fuzzySearchCity(@Param("keywords")String keywords);
}