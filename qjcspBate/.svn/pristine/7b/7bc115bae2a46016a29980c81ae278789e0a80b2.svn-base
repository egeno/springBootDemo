package com.qjkj.qjcsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.Address;
import com.qjkj.qjcsp.entity.GProvince;

@MyBatisRepository
public interface GProvinceMapper {
    int deleteByPrimaryKey(Long provinceId);

    int insert(GProvince record);

    int insertSelective(GProvince record);

    GProvince selectByPrimaryKey(Long provinceId);

    int updateByPrimaryKeySelective(GProvince record);

    int updateByPrimaryKey(GProvince record);

	List<GProvince> findProvinceList(@Param("i")int i, @Param("j")int j, @Param("searchName")String searchName);

	List<GProvince> findUniversityList(@Param("cityId")String cityId);

	int judgeCompanyNameExist(String companyName);


}