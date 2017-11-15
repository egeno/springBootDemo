package com.qjkj.qjcsp.service.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.sound.midi.SysexMessage;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblGoodsIngredients;
import com.qjkj.qjcsp.entity.TblGoodsIngredientsType;
import com.qjkj.qjcsp.mapper.TblGoodsIngredientsMapper;
import com.qjkj.qjcsp.mapper.TblGoodsIngredientsTypeMapper;
@Component
@Transactional
public class TblGoodsEditIngredientstypeService {
    @Autowired
    private TblGoodsIngredientsTypeMapper tblGoodsIngredientsTypeMapper;
    @Autowired
	private TblGoodsIngredientsMapper  tblGoodsIngredientsMapper;
	
	/**
	 * 添加商品分类信息
	 */
    
	public Map<String, Object> addIngredientsType(JSONObject json) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {			
	
			List<Map<String,Object>> listMap = (List<Map<String, Object>>) json.get("list");
			TblGoodsIngredientsType  goodsIngredientsType=new TblGoodsIngredientsType();
			for(Map<String,Object> maps:listMap){
				String  ingredientsTypeName = maps.get("ingredientsTypeName").toString();
				goodsIngredientsType.setIngredientsTypeName(ingredientsTypeName);
				Long goodsId=Long.valueOf(maps.get("goodsId").toString());
				goodsIngredientsType.setGoodsId(goodsId);
				goodsIngredientsType.setIsdel("N");			
				TblGoodsIngredients  goodsIngredient=new TblGoodsIngredients();				
				List<Map<String,Object>>  listingredients=(List<Map<String, Object>>) maps.get("goodsIngredients");
				
				//查看当前名字有多少条数据
				//食材类型数量
				int typeNum = 0;
				//成分数量
				int num = 0;
				boolean flag=false;
				if(maps.get("ingredientsTypeId").toString().equals(""))
				{		
					String  ingredientsName=null;
					for(Map<String,Object> ingredientsMap:listingredients){
						  ingredientsName = ingredientsMap.get("ingredientsName").toString();
					    num = tblGoodsIngredientsMapper.selectByName(ingredientsName,goodsId,null);
						if("".equals(ingredientsMap.get("id").toString())){						
							if(num > 0){								
								flag=true;						
							}
						}
						
					}											
					typeNum = tblGoodsIngredientsTypeMapper.selectTypeByName(ingredientsTypeName,goodsId,null);
					if(typeNum > 0){
						map.put("message", "保存失败：食材类型"+ingredientsTypeName +" 已存在");
						map.put("status", Boolean.FALSE);
						return map;
					}else  if(flag){
						map.put("message", "保存失败：成分"+ingredientsName +" 已存在");
						map.put("status", Boolean.FALSE);
						return map;
				
					}else{
						tblGoodsIngredientsTypeMapper.insertSelective(goodsIngredientsType);
					}					
					Long typeid=goodsIngredientsType.getIngredientsTypeId();
					for(Map<String,Object> ingredientsMap:listingredients){
						ingredientsName = ingredientsMap.get("ingredientsName").toString();
						goodsIngredient.setGoodsId(goodsId);	
						goodsIngredient.setIngredientsTypeId(typeid);
						goodsIngredient.setIngredientsName(ingredientsName);
						goodsIngredient.setIngredientsContent(ingredientsMap.get("ingredientsContent").toString());
						goodsIngredient.setIsdel("N");
					    num = tblGoodsIngredientsMapper.selectByName(ingredientsName,goodsId,null);
						if("".equals(ingredientsMap.get("id").toString())){							
							if(num > 0){
								map.put("message", "保存失败：成分"+ingredientsName +" 已存在");
								map.put("status", Boolean.FALSE);
								return map;
							}
							tblGoodsIngredientsMapper.insertSelective(goodsIngredient);						
						}
					}		
				}else{  
					long typeId=Long.valueOf(maps.get("ingredientsTypeId").toString());	
					typeNum = tblGoodsIngredientsTypeMapper.selectTypeByName(ingredientsTypeName,goodsId,Long.valueOf(typeId));
					goodsIngredientsType.setIngredientsTypeId(typeId);
					if(typeNum >= 1){
						map.put("message", "保存失败：食材类型"+ingredientsTypeName +" 已存在");
						map.put("status", Boolean.FALSE);
						return map;
					}
					tblGoodsIngredientsTypeMapper.updateByPrimaryKey(goodsIngredientsType);
					for(Map<String,Object> ingredientsMap:listingredients){
						String ingredientsName = ingredientsMap.get("ingredientsName").toString();
						goodsIngredient.setGoodsId(goodsId);	
						goodsIngredient.setIngredientsTypeId(typeId);
						goodsIngredient.setIngredientsName(ingredientsName);
						goodsIngredient.setIngredientsContent(ingredientsMap.get("ingredientsContent").toString());
						goodsIngredient.setIsdel("N");
						//查看当前成分名称的数量
						
						if(ingredientsMap.get("id").toString().equals("")){
							int numNull = tblGoodsIngredientsMapper.selectByName(ingredientsName,goodsId,null);
							if(numNull > 0){
								map.put("message", "保存失败：成分"+ingredientsName +" 已存在");
								map.put("status", Boolean.FALSE);
								return map;
							}
							tblGoodsIngredientsMapper.insertSelective(goodsIngredient);						
						}else{    
							int numId = tblGoodsIngredientsMapper.selectByName(ingredientsName,goodsId,Long.valueOf(ingredientsMap.get("id").toString()));
//							int numIdd = tblGoodsIngredientsMapper.selectByName(ingredientsName,null);
//							TblGoodsIngredients tblGoodsIngredient = tblGoodsIngredientsMapper.selectByPrimaryKey(Long.valueOf(ingredientsMap.get("id").toString()));
							//名字相同并且大于一次的才添加
//							if(countNums > 0 && ingredientsName.equals(tblGoodsIngredient.getIngredientsName()) ){
							if(numId >= 1){
							    map.put("message", "保存失败：成分"+ingredientsName +" 已存在");
								map.put("status", Boolean.FALSE);
								return map;
							}
							Long id=Long.valueOf(ingredientsMap.get("id").toString());
							goodsIngredient.setId(id);
							tblGoodsIngredientsMapper.updateByPrimaryKey(goodsIngredient);
						}
					}		
				}	
						
			}		
			map.put("message", "保存成功");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			map.put("message", "操作失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 修改商品分类信息
	 * @param request
	 * @param goodsIngredients
	 * @return
	 */
	public Map<String, Object> editIngredientsType(ServletRequest request, TblGoodsIngredientsType goodsIngredientsType) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {			 
				tblGoodsIngredientsTypeMapper.updateByPrimaryKey(goodsIngredientsType);
				map.put("message", "修改商品分类信息成功!");
				map.put("status", Boolean.TRUE);
			
		} catch (Exception e) {
			map.put("message", "修改商品分类信息失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}
	/**
	 * 逻辑删除
	 * @param request
	 * @param ingredientsTypeId
	 * @return
	 */
	public Map<String, Object> delIngredientsType(ServletRequest request, Long ingredientsTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
	try {
		tblGoodsIngredientsMapper.delIngredient(ingredientsTypeId);
		tblGoodsIngredientsTypeMapper.delIngredientsType(ingredientsTypeId);		
		map.put("message", "删除商品分类信息成功!");
		map.put("status", Boolean.TRUE);
	} catch (Exception e) {
		map.put("message", "删除商品分类信息失败,请联系管理人员!");
		map.put("status", Boolean.FALSE);
	}		
		return map;
	}
	
	
}
