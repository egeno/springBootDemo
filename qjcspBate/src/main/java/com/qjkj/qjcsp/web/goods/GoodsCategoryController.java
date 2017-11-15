package com.qjkj.qjcsp.web.goods;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.viewmodel.CategoryTree;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.service.goods.GoodsCategoryService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.web.BaseController;

/*
 * 类名:GoodsCategoryController
 * 版本号：V1.0
 * 日期：2015-12-19
 * 商品分类Controller
 */

@Controller
@RequestMapping("goods/category")
public class GoodsCategoryController extends BaseController{

	@Autowired
	private GoodsCategoryService goodsCategoryService ;
	
	/**
	 * 商品分类跳转页
	 */
	@RequestMapping(value="/listGoodsCategoryMain")
	public String listCategoryMain(){
		return "/goodsCategory/listGoodsCategoryMain" ;
	}
	
	/**
	 * 商品添加、编辑跳转页
	 */
	@RequestMapping(value="editGoodsCategoryDlg")
	public String editGoodsCategoryDlg(){
		return "/goodsCategory/editGoodsCategoryDlg";
	}
	
	/**
	 * 查询所有分类信息
	 * @return
	 */
	@RequestMapping(value = "/findCategoryAllList")
	@ResponseBody
	public List<CategoryTree> findCategoryAllList() {
		return goodsCategoryService.findCategoryAllList();
	}
	
	/**
	 * 按照节点查询分类信息
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/findCategory")
	@ResponseBody
	public List<CategoryTree> findCategory(@RequestParam(value = "id", required = false) Long pid) {
		return goodsCategoryService.findCategory(pid);
	}
	
	/**
	 * 弹出框编辑
	 */
	@RequestMapping(value = "/persistenceCategoryDlg")
	@ResponseBody
	public ReturnJson persistenceCategoryDlg(CategoryTree categoryTree) {
		ReturnJson json = new ReturnJson();
//		if (categoryTree.getId() != null?false : (!goodsCategoryService.isRepeatCategoryTreeCode(categoryTree))) {
//			json.setMessage("类型编号不能重复");
//		} else {
			if (goodsCategoryService.persistenceCategoryDlg(categoryTree) == true) {
				json.setStatus(true);
				json.setMessage(Constants.POST_DATA_SUCCESS);
			} else {
				json.setMessage("请先禁用子分类！");
			}
//		}
		return json;
	}
	
	/**
	 * 逻辑删除商品分类
	 */
	@RequestMapping(value = "/deleteCategory")
	@ResponseBody
	public ReturnJson deleteCategory(@RequestParam(value = "id", required = false) String pid) {
		ReturnJson json = new ReturnJson();
		short flag = goodsCategoryService.deleteCategory(pid);
		if (flag == 0) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else if (flag == 1){
			json.setMessage("该分类存在正在使用的商品，无法删除！");
		}else if (flag == 2){
		json.setMessage("请先删除子分类！");
	}
		return json;
	}
	
	@RequestMapping("/getGoodsCateGoryList")
	@ResponseBody
	public List<Map<String, Object>> getGoodsCateGoryList() {
		List<Map<String, Object>> mList=goodsCategoryService.selectGoodsCategoryById();
		return mList;
	}
}
