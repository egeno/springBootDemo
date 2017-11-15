package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.EvaluateSearch;
import com.qjkj.qjcsp.entity.TblGoodsEvaluate;

@MyBatisRepository
public interface TblGoodsEvaluateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblGoodsEvaluate record);

    int insertSelective(TblGoodsEvaluate record);

    TblGoodsEvaluate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblGoodsEvaluate record);

    int updateByPrimaryKey(TblGoodsEvaluate record);

	int getCountByOrderId(Long orderId);

	List<TblGoodsEvaluate> selectByorderId(Long orderId);

	List<TblGoodsEvaluate> selectBygoodsId(Long goodsId);
	
	int selectCountBygoodsId(Map<String, Object> map3);

	int findgoodscountbygoods(Map<String, Object> map);

	List<TblGoodsEvaluate> selectEvaluateByGoodsId(Map<String, Object> map2);

	Long selectCount(EvaluateSearch evaluateSearch);

	List<Map<String, Object>> findEvaluateList(EvaluateSearch evaluateSearch);

	void evaluatereviewedPass(@Param("arrayObj") List<Integer> arrayObj);

	List<Map<String, Object>> evaluatereviewedExport(EvaluateSearch evaluateSearch);

	void evaluatereviewedOut(@Param("arrayObj") List<Integer> arrayObj);

}