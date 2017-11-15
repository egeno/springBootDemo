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

import com.qjkj.qjcsp.entity.viewmodel.TreeModel;
import com.qjkj.qjcsp.entity.BasicsMachineModel;
import com.qjkj.qjcsp.entity.viewmodel.BasicsMachineModelVo;
import com.qjkj.qjcsp.service.machine.MachineModelService;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.util.UploadImage;
import com.qjkj.qjcsp.web.BaseController;

/*
 * 类名:MachineModelController
 * 版本号：V1.0
 * 日期：2015-12-23
 * 设备类型Controller
 */

@Controller
@RequestMapping("machine/model")
public class MachineModelController extends BaseController{

	@Autowired
	private MachineModelService machineModelService;
	
	/**
	 * 设备型号跳转页
	 */
	@RequestMapping(value="/listMachineModelMain")
	public String listMachineModelMain(ServletRequest request){
		return "/machineModel/listMachineModelMain" ;
	}
	
	/**
	 * 设备型号添加跳转页
	 */
	@RequestMapping(value="/addModelMain")
	public String addModelMain(ServletRequest request){
		return "/machineModel/addModelMain";
	}
	
	/**
	 * 设备型号编辑跳转页 
	 */
	@RequestMapping(value="/editModelMain")
	public String editModelMain(ServletRequest request, Integer modelId){
		BasicsMachineModel bmm = machineModelService.findMachineModelbyId(request, modelId);
		request.setAttribute("basicsMachineModel", bmm);
		
		return "/machineModel/editModelMain";
	}
	
	/**
	 * 查询所有设备型号
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findModelPage")
	@ResponseBody
	public Map<String,Object> findModelPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<BasicsMachineModelVo> lists = machineModelService.findModelPage(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		
		return map ;
	}
	
	/**
	 * 上传设备型号图标
	 * @param request
	 * @param response
	 * @param imageFile
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadImage")
	@ResponseBody
	public void uploadImage(ServletRequest request, ServletResponse response,
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile) throws IOException {
		
		UploadImage.upload(request, response, imageFile, 251, 228, "modelIcon");
	}
	
	/**
	 * 添加设备型号
	 * @param request
	 * @param basicsMachineModel
	 * @return
	 */
	@RequestMapping(value="/addModel")
	@ResponseBody
	public Map<String, Object> addModel(ServletRequest request, BasicsMachineModel basicsMachineModel){
		return machineModelService.addModel(request, basicsMachineModel);
	}
	
	/**
	 * 编辑设备型号
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/editModel")
	@ResponseBody
	public Map<String, Object> editModel(ServletRequest request, BasicsMachineModel basicsMachineModel){
		return machineModelService.editModel(request, basicsMachineModel);
	}
	
	/**
	 * 逻辑删除设备型号
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/delModel")
	@ResponseBody
	public Map<String, Object> delModel(ServletRequest request, Integer modelId){
		return machineModelService.delModel(request, modelId);
	}
	
	/**
	 * 根据设备型号id查找设备型号
	 * @param request
	 * @param modelId
	 * @return
	 */
	@RequestMapping(value="/findMachineModelbyId")
	@ResponseBody
	public BasicsMachineModel findMachineModelbyId(ServletRequest request, Integer modelId){
		return machineModelService.findMachineModelbyId(request, modelId);
	}
	
	/**
	 * 查询当前可用所有设备型号
	 * @return
	 */
	@RequestMapping(value="/findAllMachineModelList")
	@ResponseBody
	public List<TreeModel> findAllMachineModelList(){
		return machineModelService.findAllMachineModelList();
	}
	
	/**
	 * 查找该设备型号下是否有设备
	 */
	@RequestMapping(value="/findMachineCount")
	@ResponseBody
	public int findMachineCount(@RequestParam(value="modelId") String modelId){
		int count = machineModelService.findMachineCount(modelId) ;
		return count;
	}
}
