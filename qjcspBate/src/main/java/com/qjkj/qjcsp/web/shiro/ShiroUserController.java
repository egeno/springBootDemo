package com.qjkj.qjcsp.web.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Page;


import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.viewmodel.TreeModel;
import com.qjkj.qjcsp.service.shiro.ShiroUserService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.Servlets;

/**
 * 用户权限管理中心不同角色用户管理和密码重置
 * @author sx
 *
 */

@Controller
@RequestMapping(value="/shiro")
public class ShiroUserController {
	private static Logger logger = LoggerFactory.getLogger(ShiroUserController.class);
	private static final String PAGE_SIZE = "10";
	public static final String  succFlag = "1"; 
	
	@Autowired
	private ShiroUserService shiroUserService;
	
	@RequestMapping(value="/plaUser")
	public String plaUser() {
		return "/shiro/plaUser";
	}
	
	@RequestMapping(value="/plaUserEdit")
	public String plaUserEdit() {
		return "/shiro/plaUserEdit";
	}
	
	@RequestMapping(value="/plaUserAdd")
	public String plaUserAdd() {
		return "/shiro/plaUserAdd";
	}
	
	@RequestMapping(value="/comUser")
	public String comUser() {
		return "/shiro/comUser";
	}
	
	@RequestMapping(value="/comUserEdit")
	public String comUserEdit() {
		return "/shiro/comUserEdit";
	}
	
	@RequestMapping(value="/comUserAdd")
	public String comUserAdd() {
		return "/shiro/comUserAdd";
	}
	
	@RequestMapping(value="/merUser")
	public String merUser() {
		return "/shiro/merUser";
	}
	
	@RequestMapping(value="/merUserEdit")
	public String merUserEdit() {
		return "/shiro/merUserEdit";
	}
	
	@RequestMapping(value="/merUserAdd")
	public String merUserAdd() {
		return "/shiro/merUserAdd";
	}
	
	@RequestMapping(value="/resetPassword")
	public String resetPassWord() {
		return "/shiro/resetPassword";
	}
	
	@RequestMapping(value="/resetPasswordEdit")
	public String resetPasswordEdit() {
		return "/shiro/resetPasswordEdit";
	}
	
	/**
	 * 查找所有平台用户
	 */
	@RequestMapping(value="/findAllPlaUser")
	@ResponseBody
	public Map<String, Object> findAllPlaUser(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<BasicsUsers> page = shiroUserService.findAllPlaUser(searchParams, pageNumber, pageSize) ;
		map.put("rows", page.getContent());
		map.put("total", page.getTotalElements());
		return map;
	}
	
	/**
	 * 平台用户管理编辑
	 */
	@RequestMapping(value="/persistenceShiroPlaUserEdit")
	@ResponseBody
	public HashMap<String, Object> persistenceShiroPlaUserEdit(BasicsUsers basicsUsers){		
		return shiroUserService.persistenceShiroPlaUserEdit(basicsUsers);
	}
	
	/**
	 * 密码重置
	 */
	@RequestMapping(value="/resetPassword2")
	@ResponseBody
	public HashMap<String, Object> resetPassword2(BasicsUsers basicsUsers){		
		return Constants.getMessage(shiroUserService.resetPassword2(basicsUsers));
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public HashMap<String, Object> deleteUser(@RequestParam(value="userId") Long userId){
		if(shiroUserService.isDeleteUserAsLoginUser(userId))
		{
			HashMap<String, Object> map = new HashMap<String, Object>(); 
			map.put("message","不能删除当前登录用户！");
			return map;
		}
		else if(!shiroUserService.whetherContextDevice(userId)){
			HashMap<String, Object> map = new HashMap<String, Object>(); 
			map.put("message","请先取消该用户与设备关联！");
			return map;
		}
		else{
			return Constants.getMessage(shiroUserService.deleteUserByuserId(userId));
		}
	}
	
	/**
	 * 查找所有商户普通用户
	 */
	@RequestMapping(value="/findAllComUser")
	@ResponseBody
	public Map<String, Object> findAllComUser(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<BasicsUsers> page = shiroUserService.findAllComUser(searchParams, pageNumber, pageSize) ;
		map.put("rows", page.getContent());
		map.put("total", page.getTotalElements());
		return map;
	}
	
	/**
	 * 商户角色管理下用户查询
	 */
	@RequestMapping(value="/findAllMerComUser")
	@ResponseBody
	public Map<String, Object> findAllMerComUser(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<BasicsUsers> page = shiroUserService.findAllMerComUser(searchParams, pageNumber, pageSize) ;
		map.put("rows", page.getContent());
		map.put("total", page.getTotalElements());
		return map;
	}
	
	/**
	 * 商户普通用户管理添加、编辑
	 */
	@RequestMapping(value="/persistenceShiroComUserEdit")
	@ResponseBody
	public HashMap<String, Object> persistenceShiroComUserEdit(BasicsUsers basicsUsers){		
		return shiroUserService.persistenceShiroComUserEdit(basicsUsers);
	}
	
	/**
	 * 查找所有商户管理用户
	 */
	@RequestMapping(value="/findAllMerUser")
	@ResponseBody
	public Map<String, Object> findAllMerUser(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<BasicsUsers> page = shiroUserService.findAllMerUser(searchParams, pageNumber, pageSize) ;
		map.put("rows", page.getContent());
		map.put("total", page.getTotalElements());
		return map;
	}
	
	/**
	 * 商户管理用户管理内添加公司下拉框列表
	 */
	@RequestMapping(value="/findAllUnselectCompany")
	@ResponseBody
	public List<TreeModel> findAllUnselectCompany(){
		return shiroUserService.findAllUnselectCompany();
	}
	
	/**
	 * 商户管理用户管理添加、编辑
	 */
	@RequestMapping(value="/persistenceShiroMerUserEdit")
	@ResponseBody
	public HashMap<String, Object> persistenceShiroMerUserEdit(BasicsUsers basicsUsers){		
		return shiroUserService.persistenceShiroMerUserEdit(basicsUsers);
	}
	
	/**
	 * 查询未创建商户管理员的公司数量
	 */
	@RequestMapping(value="/findCompanyCount")
	@ResponseBody
	public int findCompanyCount(){
		int count = shiroUserService.findCompanyCount();
		return count;
	}
	
	/**
	 * 根据user_id查找该用户信息
	 */
	@RequestMapping(value="/findUserInformation")
	@ResponseBody
	public Map<String, Object> findUserInformation(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<BasicsUsers> page = shiroUserService.findUserInformation(searchParams, pageNumber, pageSize) ;
		map.put("rows", page.getContent());
		map.put("total", page.getTotalElements());
		return map;
	}
	
	/**
	 * 添加用户时异步判断手机号是否重复
	 */
	@RequestMapping(value="/check")
	@ResponseBody
	public String check(String userMobile){		
		return shiroUserService.check(userMobile);
	}
	
	/**
	 * 判断用户名是否重复
	 */
	@RequestMapping(value="/checkName")
	@ResponseBody
	public String checkName(String userName){		
		return shiroUserService.checkName(userName);
	}
	
	/**
	 * 判断登录账号是否重复
	 */
	@RequestMapping(value="/checkAccount")
	@ResponseBody
	public String checkAccount(String userAccount){		
		return shiroUserService.checkAccount(userAccount);
	}
	
}
