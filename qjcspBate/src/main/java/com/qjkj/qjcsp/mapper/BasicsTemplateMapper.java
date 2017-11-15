package com.qjkj.qjcsp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsTemplate;

@MyBatisRepository
public interface BasicsTemplateMapper {
	int deleteByPrimaryKey(Long templateId);

	int insert(BasicsTemplate record);

	int insertSelective(BasicsTemplate record);

	BasicsTemplate selectByPrimaryKey(Long templateId);

	int updateByPrimaryKeySelective(BasicsTemplate record);

	int updateByPrimaryKey(BasicsTemplate record);

	List<Map<String, Object>> getTemplateList(@Param("companyId") Integer companyId);

	int exist(@Param("template")String template, @Param("getTemplateId")String getTemplateId);

	/* int change(@Param("bt")BasicsTemplate bt,@Param("tem")String tem); */

	int change(@Param("changeId") String changeId, @Param("companyId") Integer companyId,
			@Param("userId") Integer userId, @Param("date") Date date, @Param("template") String template);

	List<BasicsAreaModel> modelList(String changeId);

	int delTemplate(@Param("templateId") Long templateId);

	int getTemplateId(String templateName);

	int newExist(String template);

	int updateByTemplateName(@Param("template")String template, @Param("getTemplateId")String getTemplateId);
}