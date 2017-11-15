package com.qjkj.qjcsp.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsMenu;
import com.qjkj.qjcsp.entity.viewmodel.TreeGrid;
import com.qjkj.qjcsp.entity.viewmodel.TreeGridModel;
import com.qjkj.qjcsp.entity.viewmodel.TreeModel;
import com.qjkj.qjcsp.mapper.BasicsMenuMapper;
import com.qjkj.qjcsp.mapper.BasicsRoleMenuMapper;
import com.qjkj.qjcsp.util.Constants;

/*
 * 类名:FunctionService
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-19
 * 系统菜单功能管理Service
 */
@Component
@Transactional
public class FunctionService {

	@Autowired
	private BasicsMenuMapper basicsMenuMapper;
	
	@Autowired
	private BasicsRoleMenuMapper basicsRoleMenuMapper;

	/**
	 * @function 返回所有菜单功能列表
	 * @return
	 */
	public List<TreeModel> findAllFunctionList() 
	{
		List<BasicsMenu> list = basicsMenuMapper.queryMenuList();
		List<TreeModel> tempList = new ArrayList<TreeModel>();
		
		//将原BasicsMenu格式转成TreeModel，以便进行树型结构显示
		for (BasicsMenu basicsMenu : list) {
			TreeModel treeModel = new TreeModel();
			treeModel.setId(basicsMenu.getMenuId().toString());
			treeModel.setPid(basicsMenu.getPid() == null ? "" : basicsMenu.getPid().toString());
			treeModel.setName(basicsMenu.getName());
			treeModel.setIconCls(basicsMenu.getIconCls());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			tempList.add(treeModel);
		}
		return tempList;
	}

	/**
	 * @function 保存功能菜单信息
	 * @param basicsMenu 功能菜单信息
	 * @return
	 */
	public boolean persistenceFunction(BasicsMenu basicsMenu) 
	{
		Integer userId = Constants.getCurrendUser().getUserId();
		
		if (null == basicsMenu.getMenuId() || "".equals(basicsMenu.getMenuId()))//新增保存
		{
			basicsMenu.setCreated(new Date());
			basicsMenu.setLastmod(new Date());
			basicsMenu.setCreater(userId);
			basicsMenu.setModifyer(userId);
			basicsMenu.setStatus(Constants.PERSISTENCE_STATUS);
			if (Constants.IS_FUNCTION.equals(basicsMenu.getType()))//菜单
			{
				basicsMenu.setState(Constants.TREE_STATUS_CLOSED);
			} 
			else//操作
			{
				basicsMenu.setState(Constants.TREE_STATUS_OPEN);
			}
			basicsMenuMapper.insertSelective(basicsMenu);
		}
		else//编辑保存
		{
			if (Constants.IS_FUNCTION.equals(basicsMenu.getType())) 
			{
				basicsMenu.setState(Constants.TREE_STATUS_CLOSED);
			} 
			else 
			{
				basicsMenu.setState(Constants.TREE_STATUS_OPEN);
			}
			basicsMenu.setLastmod(new Date());
			basicsMenu.setModifyer(userId);
			basicsMenuMapper.updateByPrimaryKeySelective(basicsMenu);
		}
		return true;
	}
	
	/**
	 * @function 根据功能菜单类别显示功能菜单列表，用于角色权限分配的显示
	 * @param menuCategory 功能菜单类别
	 * @return
	 */
	public List<TreeGrid> findFunctionListForPermission(String menuCategory)
	{
		List<BasicsMenu> menuList = basicsMenuMapper.findFunctionListForPermission(menuCategory);
		List<TreeGrid> treeList = new ArrayList<TreeGrid>();
		
		for (BasicsMenu function : menuList) 
		{
			TreeGrid treeGrid = new TreeGrid();
			BeanUtils.copyProperties(function, treeGrid);
			
			treeGrid.setState("open");
			treeGrid.setId(String.valueOf(function.getMenuId()));
			treeGrid.setPid(String.valueOf(function.getPid()));

			treeList.add(treeGrid);
		}

		return treeList;
	}
	
	/**
	 * @function 根据父节点id号，获取该节点下的所有子节点
	 * @param pid 父节点id号
	 * @return
	 */
	public List<TreeGridModel> findAllFunctionList(String pid) 
	{
		List<BasicsMenu> menuList = basicsMenuMapper.findAllFunctionLists(pid);
		List<TreeGridModel> treeList = new ArrayList<TreeGridModel>();
		
		for (BasicsMenu function : menuList) 
		{
			TreeGridModel treeGridModel = new TreeGridModel();
			BeanUtils.copyProperties(function, treeGridModel);
			
			if (pid == null || "".equals(pid)) 
			{
				treeGridModel.setPid(null);
			}
			treeList.add(treeGridModel);
		}

		return treeList;
	}

	/**
	 * @function 停用或启用某一功能菜单，包括其子节点
	 * @param pid 父节点id号
	 * @return
	 */
	public boolean updateFunction(String pid) {
		int updateOperSymbol = 0;
		BasicsMenu basicsMenu = basicsMenuMapper.selectByPrimaryKey(Integer.valueOf(pid));
		List<BasicsMenu> basicsMenuList = basicsMenuMapper.findAllFunctionLists(pid);
		
		if (null == basicsMenuList || basicsMenuList.size() < 1)//无 子节点
		{
			updateOperSymbol = basicsMenuMapper.updateIsUsed(basicsMenu);
		} 
		else//包含子节点
		{
			basicsMenu.setBasicsMenuList(basicsMenuList);
			updateOperSymbol = basicsMenuMapper.updateIsUseds(basicsMenu);
		}
		
		return (updateOperSymbol > 0)?true:false;
	}

	/**
	 * @function 删除某一功能菜单，包括其子节点
	 * @param pid 父节点id号
	 * @return
	 */
	public boolean delFunction(String pid) {
		int flag = 0;
		List<BasicsMenu> basicsMenuList = basicsMenuMapper.findFunctionListsByPId(pid);
		if (basicsMenuList != null && basicsMenuList.size() > 0) {
			if(basicsRoleMenuMapper.judgeIsExist((basicsMenuList)) > 0){
				flag = basicsRoleMenuMapper.deleteRoleMenuRelByMenuId(basicsMenuList);//删除关联
				if(flag > 0){
					flag = basicsMenuMapper.deleteByPrimaryKeyList(basicsMenuList);
				}
			}
			else{
				flag = basicsMenuMapper.deleteByPrimaryKeyList(basicsMenuList);
			}
			
		}
		return (flag > 0)?true:false;
	}

}
