package com.qjkj.qjcsp.web.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsUserRole;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.MachineAreaPo;
import com.qjkj.qjcsp.entity.TblGoodsAreaModel;
import com.qjkj.qjcsp.entity.viewmodel.BasicsGoodsVo;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.service.goods.GoodsModelService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.Servlets;

@Controller
@RequestMapping(value="/goods")
public class GoodsModelController {
	
	@Autowired
	private GoodsModelService goodsModelService;
	
	private static final String PAGE_SIZE = "10";
	
	
	@RequestMapping(value="/goodsModel")
	public String goodsModel() {
		return "/goodsModel/goodsModel";
	}
	
	@RequestMapping(value="/addGoodsModel")
	public String addGoodsModel() {
		return "/goodsModel/addGoodsModel";
	}
	
	@RequestMapping(value="/editGoodsModel")
	public String editGoodsModel() {
		return "/goodsModel/editGoodsModel";
	}
	
	/**
	 * 得到所有分区信息
	 * @author yehx
	 * @date 2015年12月28日
	 *
	 */
	@RequestMapping(value = "/findAllareaList")
	@ResponseBody
	public Map<String, Object> findAllareaList(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "search", defaultValue = "") String search) {
		// 判断是否是全家科技
		String areaModelName = req.getParameter("areaModelName");
		int companyId = Constants.getCurrendUser().getCompanyId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (pageNumber - 1) * pageSize);
		map.put("limit", pageSize);
		map.put("areaModelName", areaModelName);
		map.put("companyId", companyId);
		map.put("search", search);
		Map<String, Object> map2 = new HashMap<String, Object>();
		int count = goodsModelService.findAllareaListCount(map);
		List<MachineAreaPo> machineAreaPoList = goodsModelService.findAllareaList(map);
		map2.put("rows", machineAreaPoList);
		map2.put("total", count);
		return map2;
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
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<BasicsGoodsVo> lists = goodsModelService.findGoodsPage(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		
		return map ;
	}
	
	/**
	 * 查询所有商品信息
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findNewGoodsPage")
	@ResponseBody
	public Map<String,Object> findNewGoodsPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<BasicsGoodsVo> lists = goodsModelService.findNewGoodsPage(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		
		return map ;
	}
	
	/**
	 * @function 根据模型id返回关联的商品id
	 * @return
	 */
	@RequestMapping(value = "/findGoodsIdByModelId")
	@ResponseBody
	public List<TblGoodsAreaModel> findGoodsIdByModelId(@RequestParam(value = "areaModelId", required = true) String areaModelId) {
		return goodsModelService.findGoodsIdByModelId(areaModelId);
	}
	
	/**
	 * @function 保存商品模型分配
	 * @return
	 */
	@RequestMapping(value = "/saveGoodsModelConfig")
	@ResponseBody
	public ReturnJson saveGoodsModelConfig(@RequestParam(value = "areaModelId", required = true) String areaModelId,@RequestParam(value = "roleIds", required = true) String roleIds) {
		return goodsModelService.saveGoodsModelConfig(areaModelId, roleIds);
	}
	
	/**
	 * 模型添加、编辑
	 */
	@RequestMapping(value="/persistenceGoodsModelEdit")
	@ResponseBody
	public HashMap<String, Object> persistenceGoodsModelEdit(BasicsAreaModel basicsAreaModel){		
		return Constants.getMessage(goodsModelService.persistenceGoodsModelEdit(basicsAreaModel));
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value="/delRow")
	@ResponseBody
	public HashMap<String, Object> delRow(@RequestParam(value="areaModelId") Long areaModelId){
		return goodsModelService.delRow(areaModelId);
	}
}
