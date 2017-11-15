package com.qjkj.qjcsp.web.goods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.util.UploadImage;
import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.viewmodel.BasicsGoodsVo;
import com.qjkj.qjcsp.service.goods.GoodsInfoService;
import com.qjkj.qjcsp.web.BaseController;

/*
 * 类名:GoodsInfoController
 * 版本号：V1.0
 * 日期：2015-12-22
 * 商品信息Controller
 */

@Controller
@RequestMapping("goods/info")
public class GoodsInfoController extends BaseController{

	@Autowired
	private GoodsInfoService goodsInfoService;
	
	/**
	 * 商品信息跳转页
	 */
	@RequestMapping(value="/listGoodsInfoMain")
	public String listGoodsInfoMain(){
		return "/goodsInfo/listGoodsInfoMain" ;
	}

	/**
	 *商品添加跳转页 
	 *
	 */
	@RequestMapping(value="/addGoodsMain")
	public String addGoodsMain(){
		return "/goodsInfo/addGoodsMain";
	}
	
	/**
	 *商品编辑跳转页 
	 */
	@RequestMapping(value="/editGoodsMain")
	public String editGoodsMain(ServletRequest request, Long goodsId){
		BasicsGoods bg = goodsInfoService.findBasicsGoods(request, goodsId) ;
		request.setAttribute("basicsGoods", bg);
		return "/goodsInfo/editGoodsMain";
	}
	
	/**
	 * 查询所有商品信息
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findGoodsPage")
	@ResponseBody
	public Map<String,Object> findGoodsPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize,@RequestParam(value="foodName",defaultValue="") String foodName){
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("foodName", foodName);
		
		Page<BasicsGoodsVo> lists = goodsInfoService.findGoodsPage(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		
		return map ;
	}
	
	/**
	 * 上传商品图标
	 * @param request
	 * @param response
	 * @param imageFile
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadImage")
	@ResponseBody
	public void uploadImage(ServletRequest request, ServletResponse response,
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile) throws IOException {
		
		UploadImage.upload(request, response, imageFile, 251, 228, "goodsIcon");
	}
	
	/**
	 * 添加商品信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/addGoods")
	@ResponseBody
	public Map<String, Object> addGoods(ServletRequest request, BasicsGoods basicsGoods){
		return goodsInfoService.addGoods(request, basicsGoods);
	}
	
	/**
	 * 编辑商品信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/editGoods")
	@ResponseBody
	public Map<String, Object> editGoods(ServletRequest request, BasicsGoods basicsGoods){
		return goodsInfoService.editGoods(request, basicsGoods);
	}
	
	/**
	 * 逻辑删除商品信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/delGoods")
	@ResponseBody
	public Map<String, Object> delGoods(ServletRequest request, Long goodsId){
		return goodsInfoService.delGoods(request, goodsId);
	}
}
