package com.qjkj.qjcsp.mapper;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblDayMealsLog;

import net.sf.ezmorph.object.BigDecimalMorpher;
@MyBatisRepository
public interface TblDayMealsLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblDayMealsLog record);

    int insertSelective(TblDayMealsLog record);

    TblDayMealsLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblDayMealsLog record);

    int updateByPrimaryKey(TblDayMealsLog record);
    
    TblDayMealsLog findDayMealActivityStatusByCustomerId(@Param("customerId")Long customerId);
    public Map<String,Object> getOrderInfo(String orderNum);
    
    public String  getAreaModelStr(@Param("customerId")Object customerId,@Param("date")String date);
    
    public void insertDayMealLog(Map map);
    
    public int getCountIsY(@Param("customerId")Object customerId,@Param("date")String date);
    
    public void updateDayMealLog(@Param("customerId")Object customerId,@Param("date")String date,@Param("str")String str);
    
    public void updateDayMealLogY(@Param("customerId")Object customerId,@Param("date")String date,@Param("str")String str);
    
    public void insertTblluckymoney(@Param("customerId")Object customerId,@Param("customerMobile")Object customerMobile,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("money")BigDecimal money);
    
    public BigDecimal getTblDayMealsactivity();
    
    public String getTblDayAreaModel(String date);
    
}