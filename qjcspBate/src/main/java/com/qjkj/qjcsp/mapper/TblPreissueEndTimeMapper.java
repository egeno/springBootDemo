package com.qjkj.qjcsp.mapper;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblPreissueEndTime;
@MyBatisRepository
public interface TblPreissueEndTimeMapper {
    int deleteByPrimaryKey(Long preissueEndTimeId);

    int insert(TblPreissueEndTime record);

    int insertSelective(TblPreissueEndTime record);

    TblPreissueEndTime selectByPrimaryKey(Long preissueEndTimeId);
    //wsk
    TblPreissueEndTime selectByCompanyId(Long companyId);

    int updateByPrimaryKeySelective(TblPreissueEndTime record);

    int updateByPrimaryKey(TblPreissueEndTime record);
    
    List<Map<String,Object>> selectPreissueEndTimeByCompanyId(@Param("companyId")Long companyId);
    
    Date queryPreissueEndTimeByCompanyId(@Param("companyId")Integer companyId);
    
    int findPreissueEndTimeNumByCompanyId(@Param("companyId")Long companyId);
}