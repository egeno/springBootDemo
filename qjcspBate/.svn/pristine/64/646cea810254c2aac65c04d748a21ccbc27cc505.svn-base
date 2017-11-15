package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsCompany;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.CompanyPo;

@MyBatisRepository
public interface BasicsCompanyMapper {
	int deleteByPrimaryKey(Long companyId);

	int insert(BasicsCompany record);

	int insertSelective(BasicsCompany record);

	BasicsCompany selectByPrimaryKey(Long companyId);

	int updateByPrimaryKeySelective(BasicsCompany record);

	int updateByPrimaryKey(BasicsCompany record);

	Long findAllMainListByCount(Map<String, Object> param);

	List<BasicsCompany> findAllMainList(@Param("pid") Long pid);

	List<BasicsCompany> queryChildCategoryStatusbyPId(Long id);

	List<BasicsCompany> findMainList();

	List<BasicsCompany> queryChildCompanybyPId(Long companyId);

	int deleteCompanybyId(Long companyId);

	List<BasicsCompany> queryChildCompanyStatusbyPId(Long id);

	List<Map<String, Object>> getCompanyListByLocation(Map<String, Object> postData);

	public List<CompanyPo> getCompanyIdAndName();

	int updateParentCompanyName(@Param("id") Long id, @Param("companyName") String companyName);

	int judgeCompanyExist(String companyName);

	int newUpdateByPrimaryKeySelective(BasicsCompany record);

	List<Map<String, Object>> getAllCompany();

	int judgeCompanyIdExist(Long companyId);

	int updateIsdel(Long companyId);

	int judgeCompany(@Param("companyName")String companyName, @Param("pid")Long pid);

	int updateByCompanyId(BasicsCompany bc);
  
	Long getUniversityIdByCompanyId(@Param("companyId")Long companyId);



}