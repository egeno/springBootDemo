package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblMachineTemplateRetailDate;

@MyBatisRepository
public interface TblMachineTemplateRetailDateMapper {
	int deleteByPrimaryKey(Long id);

	int insert(TblMachineTemplateRetailDate record);

	int insertSelective(TblMachineTemplateRetailDate record);

	TblMachineTemplateRetailDate selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TblMachineTemplateRetailDate record);

	int updateByPrimaryKey(TblMachineTemplateRetailDate record);

	/**
	 * 通过公司Id获取模板数据
	 */
	List<Map<String, Object>> getTemplateInfo(@Param("companyId") Integer companyId);

	/**
	 * 通过设备ID和零售日期删除数据
	 */
	int deleteByMachineIdAndRetailDate(@Param("machineId") Long machineId, @Param("retailDate") Integer retailDate);

	/**
	 * 按设备ID和零售日期获取数量
	 */
	int getCountByMachineAndRetailDate(@Param("machineId") Long machineId, @Param("retailDate") Integer retailDate);

	/**
	 * 通过设备Id和零售日期修改绑定的templateId
	 */
	int updateTemplateByMachineAndRetailDate(@Param("machineId") Long machineId,
			@Param("retailDate") Integer retailDate, @Param("templateId") Long templateId);

	/**
	 * 通过Id获取模板信息
	 */
	List<Map<String, Object>> getTemplateInfoById(@Param("templateId") Long templateId);

	int getCount(@Param("templateId") Long templateId);
	
	Long getByMachineIdAndRetailDate(@Param("machineId") Long machineId, @Param("retailDate") Integer retailDate);
	
	Long getTemplateIdByMachineIdAndRetailDate(@Param("machineId") Long machineId, @Param("retailDate") Integer retailDate);
}