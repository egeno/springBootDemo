package com.qjkj.qjcsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblTemplateAreaModel;

@MyBatisRepository
public interface TblTemplateAreaModelMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TblTemplateAreaModel record);

	int insertSelective(TblTemplateAreaModel record);

	TblTemplateAreaModel selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TblTemplateAreaModel record);

	int updateByPrimaryKey(TblTemplateAreaModel record);

	List<Long> getAreaModelIdByTemplateId(@Param("templateId") Long templateId);

	int delByTemplateId(@Param("templateId") Long templateId);
	/**
	 * 按模板Id和模型Id的List去修改模板关联模型表的状态为1
	 * */
	int updateStatusByTemplateIdAndModelId(@Param("templateId") Long templateId,
			@Param("areaModelIds") List<Long> areaModelIds);
}