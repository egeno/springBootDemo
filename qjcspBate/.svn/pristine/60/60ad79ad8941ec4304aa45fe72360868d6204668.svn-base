package com.qjkj.qjcsp.web.machine;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.GMachineZone;
import com.qjkj.qjcsp.entity.viewmodel.BasicsMachineVo;
import com.qjkj.qjcsp.service.machine.MachineInfoService;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.util.UploadImage;
import com.qjkj.qjcsp.web.BaseController;

/*
 * 类名:MachineInfoController
 * 版本号：V1.0
 * 日期：2015-12-24
 * 设备信息Controller
 */

@Controller
@RequestMapping("machine/info")
public class MachineInfoController extends BaseController{
	
	@Autowired
	private MachineInfoService machineInfoService;
	
	/**
	 * 设备信息跳转页 
	 * 判断当前登录用户是否属于全家科技公司
	 */
	@RequestMapping(value="/listMachineInfoMain")
	public String listMachineInfoMain(ServletRequest request){
		return machineInfoService.listMachineInfoMain();
	}
	
	
	/**
	 * 设备信息添加跳转页
	 * 判断当前登录用户是否属于全家科技公司
	 */
	@RequestMapping(value="/addInfoMain")
	public String addInfoMain(ServletRequest request){
		return machineInfoService.addInfoMain(request);
	}
	
	/**
	 * 设备信息编辑跳转页
	 * 判断当前登录用户是否属于全家科技公司 
	 */
	@RequestMapping(value="/editInfoMain")
	public String editInfoMain(ServletRequest request, Long machineId){
		return machineInfoService.editInfoMain(request, machineId);
	}
	
	/**
	 * 查询所有设备信息
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findInfoPage")
	@ResponseBody
	public Map<String,Object> findInfoPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<BasicsMachineVo> lists = machineInfoService.findInfoPage(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		
		return map ;
	}
	
	/**
	 * 上传设备信息图标
	 * @param request
	 * @param response
	 * @param imageFile
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadImage")
	@ResponseBody
	public void uploadImage(ServletRequest request, ServletResponse response,
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile) throws IOException {
		
		UploadImage.upload(request, response, imageFile, 251, 228, "machineIcon");
	}
	
	/**
	 * 添加设备信息
	 * @param request
	 * @param basicsMachineInfo
	 * @return
	 */
	@RequestMapping(value="/addInfo")
	@ResponseBody
	public Map<String, Object> addInfo(ServletRequest request, BasicsMachine basicsMachine){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = machineInfoService.addInfo(request, basicsMachine);
		} catch (Exception e) {
			map.put("message", "添加设备信息失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}
	
	/**
	 * 编辑设备信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/editInfo")
	@ResponseBody
	public Map<String, Object> editInfo(ServletRequest request, BasicsMachine basicsMachine){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = machineInfoService.editInfo(request, basicsMachine);
		} catch (Exception e) {
			map.put("message", "更新设备信息失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}
	
	/**
	 * 逻辑删除设备信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/delInfo")
	@ResponseBody
	public Map<String, Object> delInfo(ServletRequest request, Long machineId){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(machineInfoService.getAreaModelCountByMachineId(machineId)){
				map = machineInfoService.delInfo(request, machineId);
			}else{
				map.put("message", "设备下有模板,不能删除!");
				map.put("status", Boolean.FALSE);
			}
		} catch (Exception e) {
			map.put("message", "删除设备信息失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}
	
	/**
	 * 查找该登录用户的拥有设备
	 */
	@RequestMapping(value="/findAllMachinesList")
	@ResponseBody
	public Map<String, Object> findAllMachinesList(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BasicsMachine> lists = machineInfoService.findAllMachinesList(searchParams, pageNumber, pageSize);
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		return map;		
	}
	
	/**
	 * 查找维修员列表分配所拥有的设备
	 */
	@RequestMapping(value="/findMaintenanceMachinesList")
	@ResponseBody
	public Map<String, Object> findMaintenanceMachinesList(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		Map<String, Object> map = new HashMap<String, Object>();
		Page<BasicsMachine> lists = machineInfoService.findMaintenanceMachinesList(searchParams, pageNumber, pageSize);
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		return map;		
	}
	
	/**
	 * 查找该供货员所拥有设备
	 */
	@RequestMapping(value="/findEmployeeMachineList")
	@ResponseBody
	public List<BasicsMachine> findEmployeeMachineList(@RequestParam(value="user_id") Long userId,@RequestParam(value = "specialRoleNum", defaultValue = "") String specialRoleNum){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("specialRoleNum", specialRoleNum);
		return machineInfoService.findEmployeeMachineList(map);
	}
	
	/**
	 * 查找该维修员所拥有设备
	 */
	@RequestMapping(value="/findMantenanceMachineList")
	@ResponseBody
	public List<BasicsMachine> findMantenanceMachineList(@RequestParam(value="user_id") Long userId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("specialRoleNum", "1");
		return machineInfoService.findEmployeeMachineList(map);
	}
	
	@RequestMapping("/findGMachineZoneByUniversityId")
	@ResponseBody
	public List<GMachineZone> findGMachineZoneByUniversityId(@RequestParam(value="companyId") Long companyId){
		return machineInfoService.findGMachineZoneByUniversityId(companyId);
	}
	
	@RequestMapping("/addAreawin")
	public String addAreawin(){
		return "machineInfo/addArea";
	}
	
	@RequestMapping("/addArea")
	@ResponseBody
	public Map<String,Object> addArea(@RequestParam(value="areaName") String areaName){
		return machineInfoService.addArea(areaName);
	}
}
