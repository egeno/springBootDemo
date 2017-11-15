package com.qjkj.qjcsp.mapper;

import java.util.List;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblGoodsAreaModel;
@MyBatisRepository
public interface TblGoodsAreaModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblGoodsAreaModel record);

    int insertSelective(TblGoodsAreaModel record);

    TblGoodsAreaModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblGoodsAreaModel record);

    int updateByPrimaryKey(TblGoodsAreaModel record);

	List<TblGoodsAreaModel> findGoodsIdByModelId(Long areaModelId);

	int deleteGoodsModelByAreaModelId(Long areaModelId);
	
	int deleteGoodsModelByGoodsId(Long goodsId);
}