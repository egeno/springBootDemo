package com.qjkj.qjcsp.web.statistics;

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

import com.qjkj.qjcsp.service.basicsareamodel.AreaModelService;
import com.qjkj.qjcsp.service.machine.MachineService;
import com.qjkj.qjcsp.service.replenishmentprint.ReplenishmentPrintService;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import com.qjkj.qjcsp.util.UserUtils;

@RequestMapping("replenishmentPrint")
@Controller
public class ReplenishmentPrintController {
	private static final String PAGE_SIZE = "10";
	@Autowired
	private ReplenishmentPrintService replenishmentPrintService;
	@Autowired
	private MachineService machineService;
	@Autowired
	private AreaModelService areaModelService;
	
	@RequestMapping("/replenishmentPrintMain")
	public String replenishmentPrintMain(HttpServletRequest request) {
		request.setAttribute("machine", machineService.getMachineList());
		request.setAttribute("areamodel", areaModelService.getAreaModelList());
		return "replenishmentPrint/replenishmentPrint";
	}
	
	@RequestMapping("/replenishmentPrintList")
	@ResponseBody
	public Map<String, Object> replenishmentPrintList(String temporaryDate, Long machineId, Long areaModelId,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		Map<String ,Object> map = new HashMap<String,Object>() ;
		Map<String, Object> searchParams =new HashMap<String,Object>() ;
		/*if(machineId==null){
			machineId=(Long) machineService.getMachineList().get(0).get("machineId");
		}*/
		searchParams.put("temporaryDate", temporaryDate);
		searchParams.put("machineId", machineId);
		searchParams.put("areaModelId", areaModelId);
		   
		Page<Map<String, Object>> lists = replenishmentPrintService.selectGoodsSaleCount(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		
		return map ;
	}
	
	@RequestMapping("/replenishmentPrintListExport")
	@ResponseBody
	public void replenishmentPrintListExport(ServletRequest request, HttpServletResponse response,
			String temporaryDate, Long machineId, Long areaModelId) {
		List<Integer> rowsdate = null;
		Map<String, Object> searchParams =new HashMap<String,Object>() ;
//		if(machineId==null){
//			machineId=(Long) machineService.getMachineList().get(0).get("machineId");
//		}
		UserUtils userUtils = new UserUtils();
		searchParams.put("temporaryDate", temporaryDate);
		searchParams.put("machineId", machineId);
		searchParams.put("areaModelId", areaModelId);		
		searchParams.put("companyId",userUtils.getCompanyId());
		List<Map<String, Object>> list = replenishmentPrintService.replenishmentPrintListExport(searchParams);
		for(Map<String, Object> map:list){
			String str=(String) map.get("address");
			String str1="  "+map.get("cellNum")+"号格子";
			if(str.length()>12&&str.length()<24){
				str=str.substring(0, 12)+"\n"+str.substring(12, str.length());
			}else if(str.length()>24){
				str=str.substring(0, 12)+"\n"+str.substring(12, 24)+"\n"+str.substring(24, str.length());
			}
			str=str+str1;
			map.remove("cellNum");
			map.put("address", str);
		}
		String title = "补货打印单";
		String[] titles = { "设备名称", "设备地址", "商品名" };
		Integer[] sheets = { 300, 300,  300 };
		String[] keys = { "machineName", "address",  "goodsName" };
		ExportExcelUtil.exportExceld(response, title, sheets, titles, rowsdate, keys, list);
	}
}
