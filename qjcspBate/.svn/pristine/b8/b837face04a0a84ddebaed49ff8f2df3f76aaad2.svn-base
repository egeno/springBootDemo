package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsGoodsCategory;
@MyBatisRepository
public interface BasicsGoodsCategoryMapper {
    int deleteByPrimaryKey(Long goodsCategoryId);

    int insert(BasicsGoodsCategory record);

    int insertSelective(BasicsGoodsCategory record);

    BasicsGoodsCategory selectByPrimaryKey(Long goodsCategoryId);

    int updateByPrimaryKeySelective(BasicsGoodsCategory record);

    int updateByPrimaryKey(BasicsGoodsCategory record);
    
    List<Map<String, Object>> selectGoodsCategoryById(Long companyId);
    
    List<Map<String, Object>> selectGoodsCategoryByCompanyId(Long companyId);
    
    List<BasicsGoodsCategory> findCategoryAllList(Long companyId);
    
    List<Map<String, Object>> findCategory(@Param("pid")Long pid, @Param("companyId")Long companyId);
    
    int getChildCategoryCountbyPId(@Param("pid")Long pid);
    
    int deleteCategorybyId(Long goodsCategoryId);
    
    List<BasicsGoodsCategory> queryChildCategoryStatusbyPId(@Param("pid")Long pid);

	List<Map<String, Object>> selectGoodsCategoryName(long companyId);
	/*添加时编号不能重复*/
	List<BasicsGoodsCategory> findCategoryNumByCode(@Param("code")String code,@Param("companyId")Long companyId);
	
}