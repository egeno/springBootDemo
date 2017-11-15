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

import com.qjkj.qjcsp.entity.TblAndroidVersion;
import com.qjkj.qjcsp.entity.TblAndroidVersionVO;
import com.qjkj.qjcsp.service.version.VersionInfoAndroidService;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.web.BaseController;

@Controller
@RequestMapping("version/android")
public class VersionInfoAndroidController extends BaseController {
	@Autowired
	private VersionInfoAndroidService versionInfoAndroidService;
	/**
	 * 版本信息跳转页面
	 * @return
	 *  
	 */
	@RequestMapping(value = "/VersionInfoAndroidManage") 
	public String findVersionInfoAndroidManager(){

		return "/version/android/VersionInfoAndroidManager";
	}
	
	/**
	 *版本添加跳转页 
	 *
	 */
	@RequestMapping(value="/addAndroidVersion")
	public String addAndroidMain(){
		return "/version/android/addAndroidVersion";
	}
	
	/**
	 *版本编辑跳转页 
	 */
	@RequestMapping(value="/editAndroidVersion")
	public String editAndroidMain(ServletRequest request, Long id){
		TblAndroidVersion bg = versionInfoAndroidService.findAndroidVersion(request, id) ;
		request.setAttribute("tblAndroidVersion", bg);
		return "/version/android/editAndroidVersion";
	}
	
	
	/**
	 * 查询所有 版本信息
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findAllAndroidVersionPage")
	@ResponseBody
	public Map<String,Object> findAllAndroidVersionPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){	
		Map<String,Object> map = new HashMap<String,Object>() ;
		TblAndroidVersion tbl=new TblAndroidVersion();
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");		
		Page<TblAndroidVersion> lists = versionInfoAndroidService.findAllAndroidVersionPage(searchParams, pageNumber, pageSize) ;
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
	public Map<String, Object> addGoods(ServletRequest request, TblAndroidVersion tblAndroidVersion){
		return versionInfoAndroidService.addVersion(request, tblAndroidVersion);
	}
	
	
	/**
	 * 编辑版本信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/editVersion")
	@ResponseBody
	public Map<String, Object> editVersion(ServletRequest request, TblAndroidVersion tblAndroidVersion){
		return  versionInfoAndroidService.editAndroidVersion(request, tblAndroidVersion);
	}
	
	
	/**
	 * 逻辑删除版本信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/delVersion")
	@ResponseBody
	public Map<String, Object> delVersions(ServletRequest request, Long id){
		return versionInfoAndroidService.delVersions(request, id);
	}
}
