package com.qjkj.qjcsp.web.goods;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.TblGoodsIngredients;
import com.qjkj.qjcsp.service.goods.TblGoodsEditIngredientsService;
import com.qjkj.qjcsp.web.BaseController;
@Controller
@RequestMapping("goods/ingredients")
public class TblGoodsEditIngredientsController extends BaseController{

	@Autowired
	public TblGoodsEditIngredientsService goodsEditIngredientsService;
/**
 * 商品食材分类信息跳转页
 */
	@RequestMapping(value="/fenleiIngredients")
	public String editIngredients(){
		return "/goodsInfo/editIngredientsType/editIngredients" ;
	}
/**
 * 查询商品食材分类信息
 */
	@RequestMapping(value="/findIngredients")
	@ResponseBody
	public List<Map<String, Object>> findIngredients(long goodsId){
		return goodsEditIngredientsService.findIngredients(goodsId);
	}
	
	/**
	 *添加商品成分信息
	 */
	@RequestMapping(value="/addIngredients")
	@ResponseBody
	public Map<String, Object> addIngredients(ServletRequest request, TblGoodsIngredients goodsIngredients){
		return goodsEditIngredientsService.addIngredients(request, goodsIngredients);
	}
	
	/**
	 * 修改商品成分信息
	 */
	@RequestMapping(value="/editIngredients")
	@ResponseBody
	public Map<String, Object> editIngredients(ServletRequest request, TblGoodsIngredients goodsIngredients){
		return goodsEditIngredientsService.editIngredients(request, goodsIngredients);
	}
	/**
	 * 逻辑删除商品成分信息
	 */
	@RequestMapping(value="/delIngredients")
	@ResponseBody
	public Map<String, Object> delIngredients(ServletRequest request, Long Id){
		return goodsEditIngredientsService.delIngredients(request, Id);
	}
	
	@RequestMapping(value="/jumpToEdit")
	public String jumpToEdit(ServletRequest request,Long goodsId,@RequestParam(value="goodsName",defaultValue="")String goodsName){
		String strGoodsName = null;
		try {
			//String name = request.getParameter("goodsName");
			//strGoodsName = new String (name.getBytes("iso-8859-1"), "UTF-8");
			strGoodsName=java.net.URLDecoder.decode(goodsName,"UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("goodsId", goodsId);
		request.setAttribute("goodsName", strGoodsName);
		return "ingredients/ingredients";
	}
	
	@RequestMapping("/getGoodsListInfo")
	@ResponseBody
	public Map<String, Object> getGoodsListInfo(Long goodsId){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Map<String, Object>> listmap=goodsEditIngredientsService.findIngredients(goodsId);
		map.put("list", listmap);
		return map;
	}
}
