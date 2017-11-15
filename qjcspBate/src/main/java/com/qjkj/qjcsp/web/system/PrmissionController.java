package com.qjkj.qjcsp.web.system;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsCompany;
import com.qjkj.qjcsp.entity.BasicsRole;
import com.qjkj.qjcsp.entity.BasicsRoleMenu;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.entity.viewmodel.TreeGrid;
import com.qjkj.qjcsp.service.company.CompanyService;
import com.qjkj.qjcsp.service.system.FunctionService;
import com.qjkj.qjcsp.service.system.RoleMenuService;
import com.qjkj.qjcsp.service.system.UserRoleService;
import com.qjkj.qjcsp.util.Constants;

/*
 * 类名:PrmissionController
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-19
 * 角色权限分配功能管理Controller
 */
@Controller
@RequestMapping(value="/permission")
public class PrmissionController {
	
	@Autowired
	private FunctionService functionService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleMenuService roleMenuService;
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 菜单管理跳转页
	 * 
	 */
	@RequestMapping(value = "/permissionAssignmentMain")
	public String findPermission(){
		return "/permission/permissionAssignmentMain";
	}
	
	/**
	 * 根据待定的角色id和当前用户登录信息查找权限列表
	 * @param request
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping(value = "/findFunctionListForPermission")
	@ResponseBody
	public List<TreeGrid> findFunctionListForPermission(ServletRequest request,
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "id", defaultValue = "1") int id)
	{
		//获取当前用户的所属公司信息
		BasicsCompany basicsCompany = companyService.selectByPrimaryKey((long)Constants.getCurrendUser().getCompanyId());
		String symbol = basicsCompany.getSymbol();
		
		if(Constants.PERSISTENCE_SYMBOL_Y.equals(symbol))//判断当前用户所属公司是否为全家科技
		{
			if(null != roleId && "".equals(roleId))
			{
				BasicsRole basicsRole = userRoleService.getBasicsRoleByRoleId(Long.valueOf(roleId));
				if(Constants.PERSISTENCE_SYMBOL_Y.equals(basicsRole.getSymbol()))//判断当前待分配权限的角色是否为商户管理员角色
				{
					return functionService.findFunctionListForPermission(Constants.MENU_CATEGORY_MERCHANT);//返回商户菜单
				}
			}
			return functionService.findFunctionListForPermission(Constants.MENU_CATEGORY_PLATFORM);//返回平台菜单
		}
		else
		{
			return functionService.findFunctionListForPermission(Constants.MENU_CATEGORY_MERCHANT);//返回商户菜单
		}
	}
	
	/**
	 * 按照角色查询权限
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getRolePermission")
	@ResponseBody
	public List<BasicsRoleMenu> findRolePermission(ServletRequest request,
			@RequestParam(value = "roleId") String roleId)
	{
		
		return roleMenuService.findBasicsRoleMenuByRoleId(Long.valueOf(roleId));
	}
	
	/**
	 * @保存角色权限分配
	 * @param menuIds 权限id
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping(value="/savePermission")
	@ResponseBody
	public ReturnJson savePermission(@RequestParam(value = "menuIds") String menuIds,
			@RequestParam(value = "roleId") String roleId)
	{
		ReturnJson json = new ReturnJson();
		if (roleMenuService.saveRoleMenuRel(roleId, menuIds))
		{
			json.setStatus(true);
			json.setMessage("分配成功！查看已分配权限请重新登录！");
		}else {
			json.setMessage("分配失败！");
		}
		
		return json;
	}
	
}
