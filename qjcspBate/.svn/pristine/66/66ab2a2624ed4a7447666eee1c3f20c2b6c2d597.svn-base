package com.qjkj.qjcsp.web.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.TblMachineAreaModelMostSale;
import com.qjkj.qjcsp.entity.TblMachineTemplateRetailDate;
import com.qjkj.qjcsp.service.basicstemplate.TemplateService;
import com.qjkj.qjcsp.service.machine.MachineCellService;
import com.qjkj.qjcsp.service.machine.MachineMostSaleService;
import com.qjkj.qjcsp.service.machine.MachineService;
import com.qjkj.qjcsp.service.machine.MachineTemplateRetailDateService;

@Controller
@RequestMapping("/goodsTemplate")
public class TemplateController {

	@Autowired
	private TemplateService templateService;
	@Autowired
	private MachineService machineService;
	@Autowired
	private MachineMostSaleService machineMostSaleService;
	@Autowired
	private MachineTemplateRetailDateService machineTemplateRetailDateService;
	@Autowired
	private MachineCellService machineCellService;

	@RequestMapping("/toGoodsTemplate")
	public String toGoodsTemplate() {
		return "goodsTemplate/index";
	}

	/**
	 * 获取模板id和模板名
	 */
	@RequestMapping("/getTemplateList")
	@ResponseBody
	public List<Map<String, Object>> getTemplateList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = templateService.getTemplateList();
		return list;
	}

	/**
	 * 获取设备id和设备名称
	 */
	@RequestMapping("/getMachineList")
	@ResponseBody
	public List<Map<String, Object>> getMachineList() {
		return machineService.getMachineList();
	}

	/**
	 * 获取最高销售数量
	 */
	@RequestMapping("/getMostSalsInfo")
	@ResponseBody
	public List<Map<String, Object>> getMostSalsInfo() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = machineMostSaleService.getMostSalsInfoByMachineId();
		return list;
	}

	/**
	 * 获取模板信息
	 */
	@RequestMapping("/getTemplateInfo")
	@ResponseBody
	public List<Map<String, Object>> getTemplateInfo() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = machineTemplateRetailDateService.getTemplateInfo();
		return list;
	}

	/**
	 * 获取分配设备模型的list
	 */
	@RequestMapping(value = "/getAssignMachineTemplateList", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getAssignMachineTemplateList(Long templateId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = machineService.getAssignMachineTemplateList(templateId);
		return list;
	}

	/**
	 * 编辑设备套用模板
	 */
	@RequestMapping("/editTemplateRetailDate")
	@ResponseBody
	public boolean updateTemplateRetailDate(@RequestBody List<TblMachineTemplateRetailDate> retailDates) {
		try {
			machineTemplateRetailDateService.updateTemplateRetailDate(retailDates);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	/**
	 * 按设备id和模型ID循环修改最高零售数量
	 */
	@RequestMapping("/editMostSale")
	@ResponseBody
	public boolean updateMostSale(@RequestBody List<TblMachineAreaModelMostSale> mostSales) {
		try {
			machineMostSaleService.updateMostSaleByIds(mostSales);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@RequestMapping("/getConformTemplateList")
	@ResponseBody
	public List<Map<String, Object>> getConformTemplateList(Long machineId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = templateService.getOkTemplateList(machineId);
		return list;
	}

	@RequestMapping("/delTemplate")
	@ResponseBody
	public Map<String, Object> delTemplate(Long templateId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = templateService.delTemplate(templateId);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("result", false);
			map.put("message", "删除失败");
		}
		return map;
	}

	@RequestMapping("/editTemplate")
	@ResponseBody
	public Map<String, Object> editTemplate(Long templateId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = templateService.editTemplate(templateId);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("result", false);
			map.put("message", "修改失败");
		}
		return map;
	}

	@RequestMapping("/getMachineCellNum")
	@ResponseBody
	public Integer getMachineCellNum(Long machineId) {
		return machineCellService.getMachineCellNum(machineId);
	}

	@RequestMapping("/realEditSaleNum")
	@ResponseBody
	public Map<String, Object> realEditSaleNum(@RequestBody List<TblMachineAreaModelMostSale> mostSales) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = machineMostSaleService.getQuestionDate(mostSales);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("result", false);
			map.put("message", "服务器异常");
		}
		return map;
	}
}
