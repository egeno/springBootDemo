package com.qjkj.qjcsp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.entity.ModelCleanGoodsVo;
@MyBatisRepository
public interface ModelCleanGoodsMapper {
	
	public List<CompanyPo> getCompanyList(Map map);
	
	public List<ModelCleanGoodsVo> getAreaModel(int companyId);
	
	List<ModelCleanGoodsVo> getTime(@Param("machineId")String machineId,@Param("modelId")long modelId);
	
	ModelCleanGoodsVo getTimeByModelId(@Param("modelId")long modelId);
	
	public int findAllModelCleanGoodsListCount(Map map);
	
	public List<ModelCleanGoodsVo> findAllModelCleanGoodsList(Map map);
	
	public void addModelcleangoodsSave(ModelCleanGoodsVo modelCleanGoodsVo);
	
	public void editModelcleangoodsSave(ModelCleanGoodsVo modelCleanGoodsVo);
	
	public void deleteModelcleangoods(ModelCleanGoodsVo modelCleanGoodsVo);
	
	public int getCount(ModelCleanGoodsVo modelCleanGoodsVo);
	
	public int checkHasallDaygetCount(ModelCleanGoodsVo modelCleanGoodsVo);
	
	public List<String> getdayDateStr(Long areaModelId);
	
	public List<String> getEditDateStr(@Param("areaModelId")Long deviceCode,@Param("cleanId")Long cleanId); 
    /*得到当前的模型下是否有用户下单*/
	public int getOrderNum(@Param("areaModelId")Long areaModelId);
	
	List<ModelCleanGoodsVo> queryModelCleanGoodsByModelId(Long areaModelId);
	
	 /*判断当前模型是否有设备*/
	 public int getAreaModeNum(@Param("areaModelId")Long areaModelId);
	 /*当前模型下的设备，模型数*/
	 public List<Integer> getMachineModeNum(@Param("areaModelId")Long areaModelId);
	 
	 String getSupplyEndTimeByAreaModelId(Long areaModelId);
	 
	 Map<String, String> getSupplyEndTimeAndCleanStartTimeByAreaModelId(Long areaModelId);
	
	 Date getEndTimeByAreaModelId(Long areaModelId);
	 
	 String getReserveEndTimeByAreaModelId(Long areaModelId);
}
