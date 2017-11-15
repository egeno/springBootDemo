package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblOperateRecordLog;

@MyBatisRepository
public interface TblOperateRecordLogMapper {
	int deleteByPrimaryKey(Long recordId);

	int insert(TblOperateRecordLog record);

	int insertSelective(TblOperateRecordLog record);

	TblOperateRecordLog selectByPrimaryKey(Long recordId);

	int updateByPrimaryKeySelective(TblOperateRecordLog record);

	int updateByPrimaryKey(TblOperateRecordLog record);

	List<Map<String, Object>> findOpetateRecord(Map<String, Object> map);

	Long findOpetateRecordCount(Map<String, Object> map);
}