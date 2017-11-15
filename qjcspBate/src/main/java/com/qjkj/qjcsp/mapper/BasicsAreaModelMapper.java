package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.viewmodel.AreaModelVO;



@MyBatisRepository
public interface BasicsAreaModelMapper {
	//根据模型ID查询模型明细
	/*List<BasicsAreaModel>  selectAreaModelList(Long areaModelId);*/
	List<Map<String, Object>> selectAreaModelList(@Param("areaModelId")Long areaModelId);//
	
	
	int deleteByPrimaryKey(Long areaModelId);

	int insert(BasicsAreaModel record);

	int insertSelective(BasicsAreaModel record);

	BasicsAreaModel selectByPrimaryKey(Long areaModelId);

	int updateByPrimaryKeySelective(BasicsAreaModel record);

	int updateByPrimaryKey(BasicsAreaModel record);

	List<BasicsAreaModel> selectAreaModelIdByCompanyId(@Param("companyId") Long companyId);

	int updateIsdel(Long areaModelId);

	List<Map<String, Object>> selectAreaModelByCompanyId(@Param("companyId") Long companyId);
	
	List<Map<String, Object>> selectAreaModelsByCompanyId(@Param("companyId") Long companyId);

	List<BasicsAreaModel> selectAreaModelByMachine(@Param("machineId") Long machineId);

	List<BasicsAreaModel> findAllModelByCompanyId(Long companyId);
	
	Integer findNumByCompanyIdAndTimeType(@Param("timeType")String timeType,@Param("companyId")Long companyId,@Param("areaModelType")String areaModelType);
    Long findByCount(Map map);
    
    /*  分区表关联分区模型表模型关联商品表
     *  map中属性：分区名称，格子数量，分区模型，商品名称，描述，状态
     *  author:yejianhui 2015-12-28
     */
    List<AreaModelVO> selectAreaAndModelByCompanyId(Map map);

	List<Map<String, Object>> selectTemplateNameAndPercent(@Param("companyId")long companyId, @Param("templateId")int templateId);
	List<Map<String, Object>> selectNewAddAreaModelInfo(@Param("companyId")long companyId);
	
	List<Map<String, Object>> selectAreaModel(@Param("companyId") Long companyId);

	List<Map<String, Object>> getAreaModelListByMahineIdName(@Param("companyId")long companyId, @Param("mId")String mId);

	String getAreaModelName(Long areaModelId);
	

	Integer getCountByCompanyIdAndAreaModelName(@Param("companyId")long companyId,@Param("areaModelName")String areaModelName,@Param("areaModelId")Long areaModelId);
	
	String getTimeTypeByAreaModelId(Long areaModelId);
	
	Map<String, String> getOperateTime(@Param("deviceCode")String deviceCode);


	List<BasicsAreaModel> selectAreaModelByMachineAlarm(@Param("machineId") Long machineId);
}