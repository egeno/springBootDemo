package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblLotteryGradeActivity;

@MyBatisRepository
public interface TblLotteryGradeActivityMapper {
    int insert(TblLotteryGradeActivity record);

    int insertSelective(TblLotteryGradeActivity record);

	int getHasCount();
	
	List<TblLotteryGradeActivity> findAllLuckyMoneyActivity(Map<String, Object> param);

	int updateSelective(TblLotteryGradeActivity tblLotteryGradeActivity);
	
	 /* wsk 得到活动信息*/
    TblLotteryGradeActivity findlotterygradeactivity();
}