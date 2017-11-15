package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblGoodsIngredientsType;

@MyBatisRepository
public interface TblGoodsIngredientsTypeMapper {
    int deleteByPrimaryKey(Long ingredientsTypeId);

    int insert(TblGoodsIngredientsType record);

    int insertSelective(TblGoodsIngredientsType record);

    TblGoodsIngredientsType selectByPrimaryKey(Long ingredientsTypeId);
    
    int  selectTypeByName(@Param("ingredientsTypeName") String ingredientsTypeName,@Param("goodsId") Long goodsId, @Param("ingredientsTypeId") Long ingredientsTypeId);

    int updateByPrimaryKeySelective(TblGoodsIngredientsType record);

    int updateByPrimaryKey(TblGoodsIngredientsType record);

	List<TblGoodsIngredientsType> selectByGoodsId(Long goodsId);
	
	
	
  //  Map<String, Object>  findgoodsIngredientsType(@Param("ingredients_type_id")Long Ingredients_type_id);
    
   
	
	//int updateIngredientsType(TblGoodsIngredientsType record);
	
	int delIngredientsType(Long ingredientsTypeId);

}