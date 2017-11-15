package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblIssue;
@MyBatisRepository
public interface TblIssueMapper {
    int deleteByPrimaryKey(Long issueId);

    int insert(TblIssue record);

    int insertSelective(TblIssue record);

    TblIssue selectByPrimaryKey(Long issueId);

    int updateByPrimaryKeySelective(TblIssue record);

    int updateByPrimaryKey(TblIssue record);
    
    List<Map<String,Object>> selectIssueByDateAndCompany(@Param("nowDate") String nowDate,@Param("areaModelId")Long areaModelId);
    
    int saveIssusList(List<TblIssue> issues);
    
    TblIssue selectByGidAndAmid(TblIssue issue);
    
    int insertSelectiveFormatDate(TblIssue record);
    List<Map<String,Object>> selectIssueByDateAndStore(@Param("storeNames")List<String> storeNames,@Param("companyId")Long companyId,@Param("selectDate")String selectDate,@Param("areaModelId")Long areaModelId);
    List<Map<String,Object>> selectIssueByDate(@Param("companyId")Long companyId,@Param("selectDate")String selectDate,@Param("areaModelId")Long areaModelId);
    
    List<Long> selectIssueByNames(@Param("storeNames")List<String> storeNames);
    
    List<Map<String, Object>> selectIsSelectIssueByDate(@Param("companyId")Long companyId,@Param("selectDate")String selectDate,@Param("areaModelId")Long areaModelId);
    
    int findSurplusPreissueNum(@Param("machineId")Long machineId,@Param("selectDate") String selectDate,@Param("areaModelId")Long areaModelId);
    
    int findPreissueByIssueIdAndPreissueDate(@Param("issueId")Long issueId);
    
    String findSupplyEndTimeByAreaModelId(@Param("areaModelId")Long areaModelId);
    
    
}