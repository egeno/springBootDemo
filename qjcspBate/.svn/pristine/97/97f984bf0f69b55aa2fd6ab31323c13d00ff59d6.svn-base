package com.qjkj.qjcsp.web.area;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.viewmodel.AreaModelVO;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.service.area.AreaAndModelService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.web.BaseController;

/**
 * 设备关联分区方案维护
 * 
 * @author carpeYe 2015-21-28
 *
 */
@Controller
@RequestMapping("area/info")
public class AreaAndModelController extends BaseController {

	// machine业务层的注入
	@Autowired
	private AreaAndModelService areaAndModelService;

	/**
	 * 1、设备关联分区方案维护主页面 2、根据companyId从业务层获取到所有该公司的设备
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/listAreaAndModelInfo")
	public String listAreaAndModelInfo(HttpServletRequest req) {
		// 获取当前登录用户所在公司的ID
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		// System.out.println("公司的ID"+companyId);
		// 传入公司ID从业务层获取设备的集合
		Map<String, Object> params = new HashMap<String, Object>();
		List<BasicsMachine> machines = areaAndModelService.findAllMachineByCompanyId((long) companyId, params);
		// 向areaAndModel页面传值
		req.setAttribute("machine", machines);
		return "areaModel/areaAndModel";
	}

	/**
	 * 根据控制层传入的公司CompanyId查询 分区名称，格子数量，分区模型，商品名称，描述，状态
	 * 
	 * @param request
	 * @param pageNumber
	 *            分页的页数
	 * @param pageSize
	 *            每个页面条数
	 * @return
	 */
	@RequestMapping("/findAreasPage")
	@ResponseBody
	public Map<String, Object> findAreasPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize) {
		// 获取当前登录用户所在公司的ID
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		// 获取前台所有的条件
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "area_");
		// System.out.println(searchParams.get("modelName"));
		// System.out.println(searchParams.get("row"));
		// System.out.println("yejianhui"+searchParams.get("modelName"));
		// System.out.println("yejianhui"+request.getParameter("area_machineId"));
		return areaAndModelService.findAllAreaAndModelByCompanyId((long) companyId, searchParams, pageNumber, pageSize);
	}

	/**
	 * 根据CompanyId 查找所有该工程的设备的MachineId，machineName，ddress
	 * 
	 * @return map
	 */
	@RequestMapping("/findMachinePage")
	@ResponseBody
	public Map<String, Object> findAllMachineByCompanyId(HttpServletRequest request) {
		// 获取当前登录用户所在公司的ID
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		// 传入公司ID从业务层获取设备的集合
		List<BasicsMachine> machines = areaAndModelService.findAllMachineByCompanyId((long) companyId, searchParams);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", machines);
		return map;
	}

	/**
	 * 根据前台页面单机获取的AreaId异步传入控制层进行业务处理 return map
	 */
	@RequestMapping("/findMachineId")
	@ResponseBody
	public String findMachineId(long areaId) {
		return areaAndModelService.findMachineIdByAreaId(areaId);

	}

	/**
	 * 返回到增加页面
	 * 
	 */
	@RequestMapping("/addPage")
	public String addPage() {
		return "/areaModel/fenqumoblie_add";
	}

	@RequestMapping("/updateArea")
	public String updateArea(HttpServletRequest req, String areaId) {
		// 获取当前登录用户所在公司的ID
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		// 获取该公司下所有的商品
		// List<BasicsGoods>
		// bg=areaAndModelService.findAllGoodsBycompanyId((long)companyId);
		// 获取该公司下所有的分区模型
		// List<BasicsAreaModel>
		// bam=areaAndModelService.findAllModelByCompanyId((long)companyId);
		// 根据分区模型ID查找分区模型
		BasicsAreaModel ba = areaAndModelService.findAreaByAreaId(Long.valueOf(areaId));
		// req.setAttribute("goods", bg);
		// req.setAttribute("models", bam);
		req.setAttribute("area", ba);
		return "/areaModel/fenqumoblie_edit";
	}

	/**
	 * 异步删除
	 * 
	 * @param pid //分区模型Id
	 * @return
	 */
	@RequestMapping("/deleteAreaById")
	@ResponseBody
	public ReturnJson deleteAreaById(@RequestParam(value = "areaId", required = false) long pid) {
		return areaAndModelService.deleteAreaById(pid);
	}

	/**
	 * 异步保存
	 */
	@RequestMapping("/saveAreaModel")
	@ResponseBody
	public ReturnJson saveMachineModel(String areaModel) {
		return areaAndModelService.saveMachineModel(areaModel) ;
	}

	/**
	 * 增加分区模型
	 * 
	 * @param req
	 * @param ba
	 * @return
	 */
	@RequestMapping("/addModel")
	@ResponseBody
	public Map<String, Object> addModel(HttpServletRequest req, BasicsAreaModel ba) {
		return areaAndModelService.addModel(ba);
	}

	/**
	 * 修改分区
	 * 
	 * @param ba
	 * @return
	 */
	@RequestMapping("/updateAreaByAreaId")
	@ResponseBody
	public Map<String, Object> updateArea(BasicsAreaModel ba) {
		ba.setModUserId((long) Constants.getCurrendUser().getUserId());
		ba.setLastModTime(new Date());
		return areaAndModelService.updateAreaById(ba);
	}
}
