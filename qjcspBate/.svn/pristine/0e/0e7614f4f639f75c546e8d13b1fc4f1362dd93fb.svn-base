package com.qjkj.qjcsp.mapper;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblPrideGrade;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface TblPrideGradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblPrideGrade record);
    
    int insert2(TblPrideGrade record);

    int insertSelective(TblPrideGrade record);

    Long findAllPrideGradeCount();
    
    Long findIdByPrideName(String prideName);

    TblPrideGrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblPrideGrade record);

    int updateByPrimaryKey(TblPrideGrade record);
    
    int updateByPrideName(TblPrideGrade record);

    List<Map<String,Object>>  findAllPrideGrade(Map<String,Object> map);

	void deleteByPrideName(String prideName);
	//wsk
	List<TblPrideGrade> selectpridegrades();

	Long getCountByPrideNameAndId(Map<String,Object> map);
}