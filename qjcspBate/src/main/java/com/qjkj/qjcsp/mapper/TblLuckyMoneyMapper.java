package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblLotteryGradeActivity;
import com.qjkj.qjcsp.entity.TblLuckyMoney;
@MyBatisRepository
public interface TblLuckyMoneyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblLuckyMoney record);

    int insertSelective(TblLuckyMoney record);

    TblLuckyMoney selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblLuckyMoney record);

    int updateByPrimaryKey(TblLuckyMoney record);
    
    List<Map<String, Object>> findLuckyMoneysByCustomerId(@Param("customerId")Long customerId);
    
    Integer findLuckyMoneysCountByCustomerId(@Param("customerId")Long customerId);

	Long findByCount(Map<String, Object> param);
	
	Long findByCount();

	
    int updateIsUsedById(@Param("luckMoneyId")Long luckMoneyId, @Param("isUsed")String isUsed);
	
	List<TblLuckyMoney> getNumByMobileAndType(String customerMobile);
	
	List<TblLuckyMoney> selectluckyMoney(Map<String, Object> map);
	
	int getluckycount(Map<String, Object> map);
}