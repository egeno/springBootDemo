package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsGoodsCategory;
import com.qjkj.qjcsp.entity.MachineAreaPo;
import com.qjkj.qjcsp.entity.viewmodel.BasicsGoodsVo;

@MyBatisRepository
public interface BasicsGoodsMapper {
	//查询商品
	List<Map<String,Object>> selectBasicsGoods(@Param("orderNum")  String orderNum);
	
	int deleteByPrimaryKey(Long goodsId);

	int insert(BasicsGoods record);

	int insertSelective(BasicsGoods record);

	BasicsGoods selectByPrimaryKey(Long goodsId);

	int updateByPrimaryKeySelective(BasicsGoods record);

	int updateByPrimaryKey(BasicsGoods record);

	List<Map<String, Object>> getGoodsList(BasicsGoods goods);

	List<Map<String, Object>> getAllGoodsList(@Param("companyId") Long companyId,
			@Param("goodsCategoryId") Long goodsCategoryId);

	/* 商品与模型关联列表查询 */
	List<MachineAreaPo> findAllareaList(Map map);

	int findAllareaListCount(Map map);

	Long findByCount(Map<String, Object> param);

	List<BasicsGoodsVo> findByList(Map<String, Object> param);

	List<Map<String, Object>> getClearGoodsList(@Param("deviceCode") String deviceCode,
			@Param("userName") String userName);

	int getGoodsCountbyCategoryId(Long goodsCategoryId);

	int deleteBasicsGoodsbyId(Long goodsId);

	/* List<Map> findGoodsVOByDeviceCodeAndPickTime(Map map); */

	List<Map<String, Object>> findGoodsVOByDeviceCodeAndPickTime2(Map map);

	List<BasicsGoods> findAllGoodsByCompanyId(long companyId);

	List<Map<String, Object>> selectGoodsDetail(@Param("companyId") long companyId,
			@Param("goodsCategoryId") int goodsCategoryId, @Param("mId") int mId, @Param("areaModelId") int areaModelId,
			@Param("time") String time);

	List<Map<String, Object>> selectGoodsDetailOld(@Param("companyId") long companyId,
			@Param("goodsCategoryId") int goodsCategoryId, @Param("areaModelId") int areaModelId);

	Integer checkGoodIsExisted(@Param("goodName") String goodName, @Param("goodId") Long goodId);
	
	
	List<Map<String,Object>> findAllReplementByMerchantNameAndCompanyId(@Param("merchantName")String merchantName,@Param("companyId")Long companyId,@Param("timeType")String timeType);
	
	int checkGoodsToMachine(@Param("foodName")String foodName,@Param("machineId")Long machineId,@Param("specialRoleNum")String specialRoleNum);
	
}