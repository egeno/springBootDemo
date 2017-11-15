package com.qjkj.qjcsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.viewmodel.CrowdsourcedTree;

@MyBatisRepository
public interface BasicsCrowdsourcedMapper {
	List<CrowdsourcedTree> findAllcrowdsourcedList(@Param("pid") Long pid);
	List<CrowdsourcedTree> findcrowdsourcedList();
	void insertSelective(CrowdsourcedTree crowdsourcedTree);
	int deleteCrowdsourcedbyId(Long companyId);
	CrowdsourcedTree selectCitynamebycityid(Long cityid);
	CrowdsourcedTree selectCityidbycityname(String cityname);
	int judgeCrowdsourcedExist(String universityid);
	void updateCrowdsourced(CrowdsourcedTree crowdsourcedTree);
    CrowdsourcedTree selectProvincebycityid(long cityid);
}
