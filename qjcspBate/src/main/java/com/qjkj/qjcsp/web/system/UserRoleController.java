package com.qjkj.qjcsp.web.system;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsCompany;
import com.qjkj.qjcsp.entity.BasicsRole;
import com.qjkj.qjcsp.entity.BasicsUserRole;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.service.company.CompanyService;
import com.qjkj.qjcsp.service.system.UserRoleService;
import com.qjkj.qjcsp.util.Constants;

/*
 * 类名:UserRoleController
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-19
 * 用户角色及用户角色分配管理Controller
 */
@Controller
@RequestMapping(value = "/userRole")
public class UserRoleController
{
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private CompanyService companyService;
	
	/**
	 * @function 平台用户角色分配主界面
	 * @return 
	 */
	@RequestMapping(value = "/userRoleConfig")
	public String userRoleConfig() {
		return "/userrole/userRoleConfigMain";
	}
	
	/**
	 * @function 商户用户角色分配主界面
	 * @return 
	 */
	@RequestMapping(value = "/userRoleConfigForMerchant")
	public String userRoleConfigForMerchant() {
		return "/userrole/userRoleConfigMainForMerchant";
	}
	
	/**
	 * @function 平台用户角色新增或编辑界面
	 * @return
	 */
	@RequestMapping(value="/roleEditDlg")
	public String roleEditDlg(){
		return "/userrole/roleEditDlg";
	}
	
	/**
	 * @function 商户用户角色新增或编辑界面 
	 * @return 
	 */
	@RequestMapping(value="/roleEditDlgForMerchant")
	public String roleEditDlgForMerchant(){
		return "/userrole/roleEditDlgForMerchant";
	}
	
	/**
	 * @function 平台用户角色分配角色查询
	 * @return
	 */
	@RequestMapping(value = "/basicsFindRoles")
	@ResponseBody
	public List<BasicsRole> basicsFindRoles() 
	{
		BasicsCompany basicsCompany = companyService.selectByPrimaryKey((long)Constants.getCurrendUser().getCompanyId());
		String symbol = basicsCompany.getSymbol();
		return userRoleService.basicsFindRoles(symbol, (long)Constants.getCurrendUser().getCompanyId());
	}
	
	/**
	 * @function 返回平台角色列表，除商户管理用户角色外
	 * @return
	 */
	@RequestMapping(value = "/findRoles")
	@ResponseBody
	public List<BasicsRole> findRoles() 
	{
		BasicsCompany basicsCompany = companyService.selectByPrimaryKey((long)Constants.getCurrendUser().getCompanyId());
		String symbol = basicsCompany.getSymbol();
		return userRoleService.findRoleListByRoleTypeAndCompanyId(symbol, (long)Constants.getCurrendUser().getCompanyId());
	}
	
	/**
	 * @function 返回角色管理角色列表，（供货人员和商户）
	 * @return
	 */
	@RequestMapping(value = "/findRolesList")
	@ResponseBody
	public List<BasicsRole> findRolesList() 
	{
		BasicsCompany basicsCompany = companyService.selectByPrimaryKey((long)Constants.getCurrendUser().getCompanyId());
		String symbol = basicsCompany.getSymbol();
		return userRoleService.findRolesList(symbol, (long)Constants.getCurrendUser().getCompanyId());
	}
	
	
	/**
	 * @function 根据平台/商户用户的用户id返回关联的用户角色id
	 * @param userId 平台/商户用户的用户id
	 * @return
	 */
	@RequestMapping(value = "/findBasicsUserRoleByUserId")
	@ResponseBody
	public List<BasicsUserRole> findBasicsUserRoleByUserId(@RequestParam(value = "userId", required = true) String userId) {
		return userRoleService.findUserRoleByUserId(userId);
	}
	
	/**
	 * @function 根据平台/商户角色的角色id删除角色
	 * @param roleId 平台/商户角色的角色id
	 * @return
	 */
	@RequestMapping(value = "/delRole")
	@ResponseBody
	public ReturnJson delRole(@RequestParam(value = "roleId", required = true) String roleId) {
		return userRoleService.delRole(roleId);
	}
	
	/**
	 * @function 保存用户角色分配
	 * @param userId 用户id
	 * @param roleIds 角色id
	 * @return
	 */
	@RequestMapping(value = "/saveUserRoleConfig")
	@ResponseBody
	public ReturnJson saveUserRoleConfig(@RequestParam(value = "userId", required = true) String userId,@RequestParam(value = "roleIds", required = true) String roleIds) {
		return userRoleService.saveUserRoleRel(userId, roleIds);
	}

	/**
	 * @function 保存平台新增或编辑的平台角色信息
	 * @param basicsRole 角色信息
	 * @return
	 */
	@RequestMapping(value = "/saveRole")
	@ResponseBody
	public HashMap<String, Object> saveRole(BasicsRole basicsRole) {
		return Constants.getMessage(userRoleService.saveRole(basicsRole));
	}
}
