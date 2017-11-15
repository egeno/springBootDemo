package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblRetail;
@MyBatisRepository
public interface TblRetailMapper {
    int deleteByPrimaryKey(Long retailId);

    int insert(TblRetail record);

    int insertSelective(TblRetail record);

    TblRetail selectByPrimaryKey(Long retailId);

    int updateByPrimaryKeySelective(TblRetail record);

    int updateByPrimaryKey(TblRetail record);

	List<TblRetail> selectByTemplateId(int templateId);

	int judgeExist(int templateId);

	void deleteByTemplateId(@Param("templateId")Long templateId);
	
	List<Map<String, Object>> getByTemplateId(String templateId);

	List<Map<String, Object>> selectGoodsList(@Param("templateId")String templateId, @Param("areaModelId")String areaModelId);

	int realDeleteByTemplateId(Integer templateId);
	
	List<Map<String, Object>> queryRetailByTemplateIdAndModelId(@Param("templateId")Long templateId, @Param("areaModelId")Long areaModelId,
			                                                    @Param("goodIds")List<Long> goodIds);

	List<Map<String, Object>> judgeUsed(@Param("areaModelId")String areaModelId, @Param("goodsId")String goodsId);

	List<Map<String, Object>> judgeUsedByAreaModelId(Long areaModelId);
	
	int checkGoodsIsUseInTemplate(@Param("areaModelId")String areaModelId, @Param("goodsId")List<String> goodsId);

}