package com.qjkj.qjcsp.service.system;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsRoleMenu;
import com.qjkj.qjcsp.entity.BasicsRoleMenuLog;
import com.qjkj.qjcsp.mapper.BasicsRoleMenuLogMapper;
import com.qjkj.qjcsp.mapper.BasicsRoleMenuMapper;
import com.qjkj.qjcsp.util.Constants;

/*
 * 类名:RoleMenuService
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-19
 * 角色权限分配管理Service
 */
@Component
@Transactional
public class RoleMenuService {

	@Autowired
	private BasicsRoleMenuMapper basicsRoleMenuMapper;
	
	@Autowired
	private BasicsRoleMenuLogMapper basicsRoleMenuLogMapper;
	
	/**
	 * @function 根据角色id获取角色对应的菜单列表
	 * @param roleId
	 * @return
	 */
	public List<BasicsRoleMenu> findBasicsRoleMenuByRoleId(Long roleId)
	{
		return basicsRoleMenuMapper.findBasicsRoleMenuByRoleId(roleId);
	}
	
	/**
	 * @param roleId 角色id
	 * @param menuIds 待分配的角色菜单id号组成的字符串，以','分隔
	 * @return
	 */
	public boolean saveRoleMenuRel(String roleId, String menuIds)
	{
		basicsRoleMenuMapper.deleteRoleMenuRelByRoleId(Long.valueOf(roleId));
		basicsRoleMenuLogMapper.updateOperation0(roleId);
		if (!"".equals(menuIds) && menuIds.length() != 0)
		{
			String [] menuIdArray = menuIds.split(",");
			BasicsRoleMenu basicsRoleMenu = null;
			BasicsRoleMenuLog basicsRoleMenuLog = null;
			basicsRoleMenuLog = new BasicsRoleMenuLog();
			
			for (String menuId : menuIdArray)
			{
				if(null != menuId && !"".equals(menuId) && !" ".equals(menuId))
				{
					basicsRoleMenu = new BasicsRoleMenu();
					basicsRoleMenu.setRoleId(Long.valueOf(roleId));
					basicsRoleMenu.setMenuId(Integer.valueOf(menuId));
					basicsRoleMenuMapper.insert(basicsRoleMenu);
					
					if(basicsRoleMenuLogMapper.getCount(roleId,menuId) > 0){
						basicsRoleMenuLog.setRoleId(basicsRoleMenu.getRoleId());
						basicsRoleMenuLog.setMenuId(basicsRoleMenu.getMenuId());
						basicsRoleMenuLog.setOperationTime(new Date());
						basicsRoleMenuLog.setOperation("1");
						basicsRoleMenuLog.setOperationUserId((long)Constants.getCurrendUser().getUserId());
						basicsRoleMenuLogMapper.updateOperation1(basicsRoleMenuLog);
					}
					else{
						basicsRoleMenuLog.setRoleId(basicsRoleMenu.getRoleId());
						basicsRoleMenuLog.setMenuId(basicsRoleMenu.getMenuId());
						basicsRoleMenuLog.setOperationTime(new Date());
						basicsRoleMenuLog.setOperation("1");
						basicsRoleMenuLog.setOperationUserId((long)Constants.getCurrendUser().getUserId());
						basicsRoleMenuLogMapper.insertSelective(basicsRoleMenuLog);
					}
				}
			}
		}
		
		return true;
	}
}
