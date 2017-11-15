package com.qjkj.qjcsp.web.version;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.TblIosVersion;
import com.qjkj.qjcsp.service.version.VersionInfoIosService;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.web.BaseController;

@Controller
@RequestMapping("version/ios")
public class VersionInfoIosController extends BaseController {
	@Autowired
	private VersionInfoIosService versionInfoIosService;
	/**
	 * 版本信息跳转页面
	 * @return
	 *  version/ios/VersionInfoIosManage
	 */
	@RequestMapping(value = "/VersionInfoIosManage") 
	public String findVersionInfoAndroidManager(){

		return "version/ios/VersionInfoIosManage";
	}
	
	/**
	 *版本添加跳转页 
	 *
	 */
	@RequestMapping(value="/addIosVersion")
	public String addAndroidMain(){
		return "version/ios/addIosVersion";
	}
	
	/**
	 *版本编辑跳转页 
	 */
	@RequestMapping(value="/editIosVersion")
	public String editAndroidMain(ServletRequest request, Integer id){
		TblIosVersion bg = versionInfoIosService.findIosVersion(request, id) ;
		request.setAttribute("tblIosVersion", bg);
		return "version/ios/editIosVersion";
	}
	
	
	/**
	 * 查询所有ios版本信息
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findAllIosVersionPage")
	@ResponseBody
	public Map<String,Object> findAllAndroidVersionPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){	
		Map<String,Object> map = new HashMap<String,Object>() ;
		TblIosVersion tbl=new TblIosVersion();
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");		
		Page<TblIosVersion> lists = versionInfoIosService.findAllIosVersionPage(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		return map ;
	}
	
	/**
	 * 添加版本信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/addVersion")
	@ResponseBody
	public Map<String, Object> addGoods(ServletRequest request, TblIosVersion tblIosVersion){
		return versionInfoIosService.addVersion(request, tblIosVersion);
	}
	
	
	/**
	 * 编辑版本信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/editVersion")
	@ResponseBody
	public Map<String, Object> editVersion(ServletRequest request, TblIosVersion tblIosVersion){
		return  versionInfoIosService.editIosVersion(request, tblIosVersion);
	}
	
	
	/**
	 * 逻辑删除版本信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/delVersion")
	@ResponseBody
	public Map<String, Object> delVersions(ServletRequest request, Integer id){
		return versionInfoIosService.delVersions(request, id);
	}
}

