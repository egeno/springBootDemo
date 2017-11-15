package com.qjkj.qjcsp.service.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.TblGoodsIngredientsMapper;
import com.qjkj.qjcsp.mapper.TblGoodsIngredientsTypeMapper;
import com.qjkj.qjcsp.entity.TblGoodsIngredients;
import com.qjkj.qjcsp.entity.TblGoodsIngredientsType;

@Service
public class TblGoodsEditIngredientsService {
	@Autowired
	private TblGoodsIngredientsMapper  tblGoodsIngredientsMapper;
	@Autowired
	private TblGoodsIngredientsTypeMapper  tblGoodsIngredientsTypeMapper;
	/**
	 * 查询商品分类信息
	 * @param request
	 * @param goodsIngredients
	 * @return
	 */
	public List<Map<String, Object>> findIngredients(Long goodsId) {
		
		List<TblGoodsIngredientsType> tblGoodsIngredientsTypeList = tblGoodsIngredientsTypeMapper
				.selectByGoodsId(Long.valueOf(goodsId));
		List<Map<String, Object>> tblGoodsIngredientsTypemapList = new ArrayList<Map<String, Object>>();
		for (TblGoodsIngredientsType tblGoodsIngredientsType : tblGoodsIngredientsTypeList) {
			Map<String, Object> tblGoodsIngredientsTypemap = new HashMap<String, Object>();
			tblGoodsIngredientsTypemap.put("goodsName",tblGoodsIngredientsType.getGoodsId());
			tblGoodsIngredientsTypemap.put("ingredientsTypeId",tblGoodsIngredientsType.getIngredientsTypeId());
			tblGoodsIngredientsTypemap.put("ingredientsTypeName", tblGoodsIngredientsType.getIngredientsTypeName());
			List<TblGoodsIngredients> tblGoodsIngredientsList = tblGoodsIngredientsMapper
					.selectByTypeId(tblGoodsIngredientsType.getIngredientsTypeId());
			List<Map<String, Object>> tblGoodsIngredientsmapList = new ArrayList<Map<String, Object>>();
			for (TblGoodsIngredients tblGoodsIngredients : tblGoodsIngredientsList) {
				Map<String, Object> tblGoodsIngredientsmap = new HashMap<String, Object>();
				tblGoodsIngredientsmap.put("Id",tblGoodsIngredients.getId());
				tblGoodsIngredientsmap.put("ingredientsName", tblGoodsIngredients.getIngredientsName());
				tblGoodsIngredientsmap.put("ingredientsContent", tblGoodsIngredients.getIngredientsContent());
				tblGoodsIngredientsmapList.add(tblGoodsIngredientsmap);
			}
			tblGoodsIngredientsTypemap.put("ingredients", tblGoodsIngredientsmapList);
			tblGoodsIngredientsTypemapList.add(tblGoodsIngredientsTypemap);
		}
		return tblGoodsIngredientsTypemapList;			
	}
	/**
	 * 添加商品分类信息
	 */
	public Map<String, Object> addIngredients(ServletRequest request, TblGoodsIngredients goodsIngredients) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			    tblGoodsIngredientsMapper.insertSelective(goodsIngredients);
				map.put("message", "添加商品分类信息成功!");
				map.put("status", Boolean.TRUE);
			
		} catch (Exception e) {
			map.put("message", "添加商品分类信息失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}
	/**
	 * 修改商品分类信息
	 * @param request
	 * @param goodsIngredients
	 * @return
	 */
	public Map<String, Object> editIngredients(ServletRequest request, TblGoodsIngredients goodsIngredients) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			 // char c='N';
				//goodsIngredients.setIsdel(c);
			    tblGoodsIngredientsMapper.updateIngredients(goodsIngredients);
				map.put("message", "修改商品分类信息成功!");
				map.put("status", Boolean.TRUE);
			
		} catch (Exception e) {
			map.put("message", "修改商品分类信息失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}
	
	public Map<String, Object> delIngredients(ServletRequest request, Long Id) {
		Map<String, Object> map = new HashMap<String, Object>();
	try {
		tblGoodsIngredientsMapper.delIngredients(Id);		
		map.put("message", "删除商品分类信息成功!");
		map.put("status", Boolean.TRUE);

	} catch (Exception e) {
		map.put("message", "删除商品分类信息失败,请联系管理人员!");
		map.put("status", Boolean.FALSE);
	}
		
		return null;
	}
}

