package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblGoodsIngredients;

@MyBatisRepository
public interface TblGoodsIngredientsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblGoodsIngredients record);

    int insertSelective(TblGoodsIngredients record);

    TblGoodsIngredients selectByPrimaryKey(Long id);
    
    int selectByName(@Param("ingredientsName") String ingredientsName,@Param("goodsId") Long goodsId,@Param("id") Long id);

    int updateByPrimaryKeySelective(TblGoodsIngredients record);

    int updateByPrimaryKey(TblGoodsIngredients record);

	List<TblGoodsIngredients> selectByTypeId(Long ingredientsTypeId);

	int updateIngredients(TblGoodsIngredients record);	
	int delIngredients(Long Id);
	int delIngredient(Long ingredientsTypeId);
	
	
	
}