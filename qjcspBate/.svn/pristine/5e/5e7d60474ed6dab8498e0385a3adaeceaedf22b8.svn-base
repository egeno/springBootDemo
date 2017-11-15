package com.qjkj.qjcsp.web.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.BasicsMenu;
import com.qjkj.qjcsp.entity.viewmodel.MenuModel;
import com.qjkj.qjcsp.mapper.BasicsMenuMapper;
import com.qjkj.qjcsp.service.shiro.ShiroUserService;
import com.qjkj.qjcsp.util.Constants;

/*
 * 类名:UserPermissionController
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-29
 * 用户登录后的用户对应功能权限读取Controller
 */
@Controller
public class UserPermissionController {
	@Autowired
	private ShiroUserService shiroUserService;
	@Autowired
	private BasicsMenuMapper basicsMenuMapper;

	/**
	 * @function 根据当前登陆用户的用户类型返回对应的菜单权限
	 * 超级管理员：返回平台所有菜单权限
	 * 商户管理员：返回商户所有菜单权限
	 * 平台或商户普通用户：返回用户对应角色被授予的菜单权限
	 * @return
	 */
	@RequestMapping(value = "/getMenu")
	@ResponseBody
	public List<MenuModel> getMenu() 
	{
		//获取当前登陆用对应的菜单权限
		List<BasicsMenu> menuList = shiroUserService.getMenuListByLoginUser();
		List<MenuModel> menuModelList = new ArrayList<MenuModel>();
		String menuCategory = Constants.MENU_CATEGORY_PLATFORM;
		
		if(Constants.getCurrendUser().getUserType() != Constants.SYSTEM_USERTYPE_PLATFORM_ADMIN &&
				Constants.getCurrendUser().getUserType() != Constants.SYSTEM_USERTYPE_PLATFORM_COMMON_USER)
		{
			menuCategory = Constants.MENU_CATEGORY_MERCHANT;
		}
		
		//System.out.println("menuCategory:"+menuCategory);
		
		// 一级菜单
		for (BasicsMenu basicsMenu : menuList) {
			if (basicsMenu.getPid() == null) {
				MenuModel menuModel = new MenuModel();
				menuModel.setName(basicsMenu.getName());
				menuModel.setIconCls(basicsMenu.getIconCls());
				menuModel.setUrl(basicsMenu.getUrl());
				List<MenuModel> childList = new ArrayList<MenuModel>();
				// 二级菜单
				for (BasicsMenu basicsMenu2 : menuList) {
					MenuModel menuChildModel = new MenuModel();
					if (null != basicsMenu2.getPid() && basicsMenu.getMenuId().equals(basicsMenu2.getPid())) {
						menuChildModel.setName(basicsMenu2.getName());
						menuChildModel.setIconCls(basicsMenu2.getIconCls());
						menuChildModel.setUrl(basicsMenu2.getUrl());
						List<MenuModel> leafList = new ArrayList<MenuModel>();
						// 三级菜单
						List<BasicsMenu> treeListMenu = basicsMenuMapper.findAllFunctionListsForMenu(String.valueOf(basicsMenu2.getMenuId()),menuCategory);
						if (null != treeListMenu && treeListMenu.size() > 0) {
							for (BasicsMenu basicsMenu3 : treeListMenu) {
								MenuModel menuLeafModel = new MenuModel();
								menuLeafModel.setName(basicsMenu3.getName());
								menuLeafModel.setIconCls(basicsMenu3.getIconCls());
								menuLeafModel.setUrl(basicsMenu3.getUrl());
								leafList.add(menuLeafModel);
							}
							menuChildModel.setChild(leafList);
						}
						childList.add(menuChildModel);
					}
				}
				menuModel.setChild(childList);
				menuModelList.add(menuModel);
			}
		}
		return menuModelList;
	}
}
