package com.qjkj.qjcsp.web.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.core.shiro.ShiroUser;
import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.entity.ModelCleanGoodsVo;
import com.qjkj.qjcsp.service.machine.ModelCleanGoodsService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UserUtils;
@Controller
@RequestMapping("modelcleangoods")
public class BasicsModelCleanGoodsController {
	private static Logger logger = LoggerFactory.getLogger(BasicsModelCleanGoodsController.class);

	private static final String PAGE_SIZE = "10";
	
	@Autowired
	private ModelCleanGoodsService modelCleanGoodsService;
	
	@RequestMapping("/modelCleanGoodsMain")
	public String modelCleanGoodsMain(Model model){
		UserUtils u = new UserUtils();
		// 判断是否是全家科技
		Boolean flag = u.isUserofQJKJ();
		ShiroUser currendUser = Constants.getCurrendUser();
		// 得到登录用户的公司id
		int companyId = currendUser.getCompanyId();
		// 用作传参准备
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		map.put("companyId", companyId);
		//得到公司的id和公司名称
		List<CompanyPo> listsCom=new ArrayList<CompanyPo>();
		listsCom=modelCleanGoodsService.getCompanyList(map);
		CompanyPo companyPo=listsCom.get(0);
		//根据该公司得到该公司下的模型
		List<ModelCleanGoodsVo> listsModel=new ArrayList<ModelCleanGoodsVo>();
		listsModel=modelCleanGoodsService.getAreaModel(companyPo.getCompanyId());
		model.addAttribute("data", listsCom);
		model.addAttribute("dataModel", listsModel);
		return "modelcleangoods/modelCleanGoodsMain";
	}
	
	/**
	 * 
	 * @author yehx
	 * @date 2016年2月14日
	 * @return
	 *
	 */
	@RequestMapping("/getAreaModel")
	@ResponseBody
	public List<ModelCleanGoodsVo> getAreaModel(
			@RequestParam("companyId")int companyId){
		List<ModelCleanGoodsVo> listsModel=new ArrayList<ModelCleanGoodsVo>();
		listsModel=modelCleanGoodsService.getAreaModel(companyId);
		return listsModel;
	}
  
	@RequestMapping(value = "/findAllModelCleanGoodsList")
	@ResponseBody
	public Map<String, Object> findAllModelCleanGoodsList(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		String companyId = req.getParameter("companyId");
		String areaModelId = req.getParameter("areaModelId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (pageNumber - 1) * pageSize);
		map.put("limit", pageSize);
//		map.put("areaName", areaName);
		map.put("companyId", companyId);
		map.put("areaModelId", areaModelId);
		Map<String, Object> map2 = new HashMap<String, Object>();
		int count = modelCleanGoodsService.findAllModelCleanGoodsListCount(map);
		List<ModelCleanGoodsVo> machineAreaPoList = modelCleanGoodsService.findAllModelCleanGoodsList(map);
		map2.put("rows", machineAreaPoList);
		map2.put("total", count);
		return map2;
		
	}
	@RequestMapping("addModelcleangoodsMain")
	public String addModelcleangoodsMain(Model model){
		UserUtils u = new UserUtils();
		// 判断是否是全家科技
		Boolean flag = u.isUserofQJKJ();
		ShiroUser currendUser = Constants.getCurrendUser();
		// 得到登录用户的公司id
		int companyId = currendUser.getCompanyId();
		// 用作传参准备
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		map.put("companyId", companyId);
		//得到公司的id和公司名称
		List<CompanyPo> listsCom=new ArrayList<CompanyPo>();
		listsCom=modelCleanGoodsService.getCompanyList(map);
		CompanyPo companyPo=listsCom.get(0);
		//根据该公司得到该公司下的模型
		List<ModelCleanGoodsVo> listsModel=new ArrayList<ModelCleanGoodsVo>();
		listsModel=modelCleanGoodsService.getAreaModel(companyPo.getCompanyId());
		model.addAttribute("dataCompany", listsCom);
		model.addAttribute("dataModel", listsModel);
		return "modelcleangoods/addModelcleangoods";
	}
	
	@RequestMapping(value = "/addModelcleangoodsSave")
	@ResponseBody
	public Map<String, Object> addModelcleangoodsSave(ModelCleanGoodsVo modelCleanGoodsVo,HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//首先判断是否是每天还是每周
			if(modelCleanGoodsVo.getType().equals("allDay")){
				//首先判断该清货时间是否存在
				int count =modelCleanGoodsService.getCount(modelCleanGoodsVo);
				if(count>0){
					map.put("message", "该清货补货时间已经存在,新增失败,请换个商户或者模型");
					map.put("status", Boolean.FALSE);
					return map;
				}
				modelCleanGoodsVo.setDateStr(null);
			}else{
				//首先判断是否有每天已经存在了
				//首先判断该清货时间是否存在
				int count =modelCleanGoodsService.checkHasallDaygetCount(modelCleanGoodsVo);
				if(count>0){
					map.put("message", "该清货补货时间已经存在,新增失败,请换个商户或者模型");
					map.put("status", Boolean.FALSE);
					return map;
				}
				
			}
			
			
//			String[] s = req.getParameterValues("dateStr");
//			System.out.println(Arrays.toString(s));
			// 新增分区操作
			
			modelCleanGoodsService.addModelcleangoodsSave(modelCleanGoodsVo);
			map.put("message", "新增清货补货时间管理成功");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增清货补货时间管理失败：" + e.getMessage());
			map.put("message", "新增清货补货时间管理失败");
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}
		return map;
		
	}
	@RequestMapping("/editModelcleangoodsMain")
	public String editModelcleangoodsMain(Model model,HttpServletRequest req){
		UserUtils u = new UserUtils();
		// 判断是否是全家科技
		Boolean flag = u.isUserofQJKJ();
		ShiroUser currendUser = Constants.getCurrendUser();
		// 得到登录用户的公司id
		int companyId = currendUser.getCompanyId();
		// 用作传参准备
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", flag);
		map.put("companyId", companyId);
		//得到公司的id和公司名称
		List<CompanyPo> listsCom=new ArrayList<CompanyPo>();
		listsCom=modelCleanGoodsService.getCompanyList(map);
//		CompanyPo companyPo=listsCom.get(0);
		int companyId2=Integer.valueOf(req.getParameter("companyId"));
		//根据该公司得到该公司下的模型
		List<ModelCleanGoodsVo> listsModel=new ArrayList<ModelCleanGoodsVo>();
		listsModel=modelCleanGoodsService.getAreaModel(companyId2);
		model.addAttribute("dataCompany", listsCom);
		model.addAttribute("dataModel", listsModel);
		return "modelcleangoods/editModelcleangoods";
	}
	
	
	@RequestMapping(value = "/editModelcleangoodsSave")
	@ResponseBody
	public Map<String, Object> editModelcleangoodsSave(ModelCleanGoodsVo modelCleanGoodsVo,HttpServletRequest res) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if("allDay".equals(modelCleanGoodsVo.getType())){
				modelCleanGoodsVo.setDateStr(null);
			}
			String areaModelId = res.getParameter("areaModelId");
			boolean flag = modelCleanGoodsService.editModelcleangoodsSave(modelCleanGoodsVo,areaModelId);
			if(flag){
				map.put("message", "修改成功");
				map.put("status", Boolean.TRUE);
			}else{
				map.put("message", "当前模型已有客户下单");
				map.put("status", Boolean.FALSE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改失败：" + e.getMessage());
			map.put("message", "修改失败");
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}

		return map;

	}
	@RequestMapping("/deleteModelcleangoods")
	@ResponseBody
	public Map<String,Object> deleteModelcleangoods(ModelCleanGoodsVo modelCleanGoodsVo,HttpServletRequest res){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String areaModelId = res.getParameter("areaModelId");
			
			Map<String, String> contentMap = modelCleanGoodsService
					.deleteModelcleangoods(modelCleanGoodsVo,areaModelId);
			if(contentMap.size() > 0){
				map.put("message", contentMap.get("content"));
				map.put("status", Boolean.FALSE);
			}else{
				map.put("message", "删除成功");
				map.put("status", Boolean.TRUE);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除失败：" + e.getMessage());
			map.put("message", "删除失败");
			map.put("status", Boolean.FALSE);
			throw new RuntimeException("出现SQL操作错误：" + e.getMessage());
		}
		return map;
	}
	@RequestMapping("/getdayDateStr")
	@ResponseBody
	public Map<String,Object> getdayDateStr(@RequestParam("areaModelId")Long areaModelId){
		Map<String,Object>map=new HashMap<String,Object>();
		//首先得到这个模型下时间段是否存在
		List<String>dayDateStr=new ArrayList<String>();
		dayDateStr=modelCleanGoodsService.getdayDateStr(areaModelId);
		map.put("dayDateStr", dayDateStr);
		return map;
	}
	@RequestMapping("/getEditDateStr")
	@ResponseBody
	public Map<String,Object> getEditDateStr(@RequestParam("areaModelId")Long areaModelId,@RequestParam("cleanId")Long cleanId){
		Map<String,Object>map=new HashMap<String,Object>();
		//首先得到这个模型下时间段是否存在
		List<String>dayDateStr=new ArrayList<String>();
		dayDateStr=modelCleanGoodsService.getEditDateStr(areaModelId,cleanId);
		map.put("dayDateStr", dayDateStr);
		return map;
	}
	
	
	
}
