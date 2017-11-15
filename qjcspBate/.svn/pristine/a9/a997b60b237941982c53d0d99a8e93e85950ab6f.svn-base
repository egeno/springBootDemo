package com.qjkj.qjcsp.web.goods;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.TblGoodsIngredientsType;
import com.qjkj.qjcsp.entity.TblMachineAreaModelMostSale;
import com.qjkj.qjcsp.service.goods.TblGoodsEditIngredientsService;
import com.qjkj.qjcsp.service.goods.TblGoodsEditIngredientstypeService;
import com.qjkj.qjcsp.util.RequestData;
import com.qjkj.qjcsp.web.BaseController;

@Controller
@RequestMapping("goods/ingredientsType")
public class TblGoodsEditIngredientsTypeController extends BaseController {
	@Autowired
	private TblGoodsEditIngredientstypeService goodsEditIngredientstypeService;
	@Autowired
	public TblGoodsEditIngredientsService goodsEditIngredientsService;

	/**
	 * 商品分类类型跳转页
	 */
	@RequestMapping(value = "/fenleiIngredientsType")
	public String editIngredients() {
		return "/goodsInfo/editIngredientsType/editIngredients";
	}

	/**
	 * 商品分类类型查询
	 */
	@RequestMapping(value = "/findIngredientsType")
	@ResponseBody
	public List<Map<String, Object>> findIngredientsType(ServletRequest request,
			TblGoodsIngredientsType goodsIngredientsType) {
		return goodsEditIngredientsService.findIngredients(goodsIngredientsType.getGoodsId());
	}

	/**
	 * 添加分类类型信息
	 */
	@RequestMapping("/addIngredientsType")
	@ResponseBody
	public Map<String, Object> addIngredientsType(HttpServletRequest req) {
		String str = RequestData.getRequestPostJson(req);
		JSONObject json = JSONObject.fromObject(str);
		return goodsEditIngredientstypeService.addIngredientsType(json);
	}

	/**
	 * 修改分类类型信息
	 */
	@RequestMapping(value = "/editIngredientsType")
	@ResponseBody
	public Map<String, Object> editIngredientsType(ServletRequest request,
			TblGoodsIngredientsType goodsIngredientsType) {
		return goodsEditIngredientstypeService.editIngredientsType(request, goodsIngredientsType);
	}

	/**
	 * 逻辑删除分类类型信息
	 */
	@RequestMapping(value = "/delIngredientsType")
	@ResponseBody
	public Map<String, Object> delIngredientsType(ServletRequest request, Long ingredientsTypeId) {
		return goodsEditIngredientstypeService.delIngredientsType(request, ingredientsTypeId);
	}                                                                      

}
