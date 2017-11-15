package com.qjkj.qjcsp.service.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsRole;
import com.qjkj.qjcsp.entity.BasicsSpecialRole;
import com.qjkj.qjcsp.entity.BasicsUserRole;
import com.qjkj.qjcsp.entity.BasicsUserRoleLog;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.mapper.BasicsRoleMapper;
import com.qjkj.qjcsp.mapper.BasicsUserMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUserRoleLogMapper;
import com.qjkj.qjcsp.mapper.BasicsUserRoleMapper;
import com.qjkj.qjcsp.util.Constants;

/*
 * 类名:UserRoleService
 * 创建者:yjg
 * 版本号：V1.0
 * 日期：2015-12-19
 * 用户角色分配、角色管理Service
 */
@Component
@Transactional
public class UserRoleService {

	@Autowired
	private BasicsRoleMapper basicsRoleMapper;
	@Autowired
	private BasicsUserRoleMapper basicsUserRoleMapper;
	@Autowired
	private BasicsUserRoleLogMapper basicsUserRoleLogMapper;
	@Autowired
	private BasicsUserMachineMapper basicsUserMachineMapper;

	/**
	 * @function 根据用户角色id返回用户角色对象
	 * @param roleId
	 *            用户角色id
	 * @return
	 */
	public BasicsRole getBasicsRoleByRoleId(Long roleId) {
		return basicsRoleMapper.getBasicsRoleByRoleId(roleId);
	}

	/**
	 * @function 根据用户id获取用户对应的用户角色列表
	 * @param userId
	 * @return
	 */
	public List<BasicsUserRole> findUserRoleByUserId(String userId) {
		return basicsUserRoleMapper.findBasicsUserRoleByUserId(Long.valueOf(userId));
	}

	/**
	 * @function 获取商户管理员角色信息
	 * @return
	 */
	public BasicsRole getMerchantManagerRole() {
		return basicsRoleMapper.getMerchantManagerRole();
	}

	/**
	 * @return 返回角色管理角色列表，（供货人员和商户）
	 */
	public List<BasicsRole> findRolesList(String symbol, Long companyId) {
		// 如果symbol为'Y'时，则返回平台角色，否则返回该商户下自建的用户角色
		return ("Y".equals(symbol)) ? basicsRoleMapper.findBasicsRoleByCompanyIdForPlatform(companyId)
				: basicsRoleMapper.findBasicsRoleByCompanyIdForMerchant(companyId);
	}

	/**
	 * @function 根据商户公司标志和商户公司id返回对应的角色列表
	 * @param symbol
	 *            商户公司是否全家科技标志，'Y'为是，'N'为普通商户
	 * @param companyId
	 * @return
	 */
	public List<BasicsRole> findRoleListByRoleTypeAndCompanyId(String symbol, Long companyId) {
		// 如果symbol为'Y'时，则返回平台角色，否则返回该商户下自建的用户角色
		return ("Y".equals(symbol)) ? basicsRoleMapper.findBasicsRoleByCompanyIdForPlatform(companyId)
				: basicsRoleMapper.findBasicsRoleByCompanyIdForMerchant(companyId);
	}

	/**
	 * 平台用户角色分配角色查询
	 */
	public List<BasicsRole> basicsFindRoles(String symbol, Long companyId) {
		// 如果symbol为'Y'时，则返回平台角色，否则返回该商户下自建的用户角色
		return ("Y".equals(symbol)) ? basicsRoleMapper.basicsFindRoles(companyId)
				: basicsRoleMapper.findBasicsRoleByCompanyIdForMerchant(companyId);
	}

	/**
	 * @function 保存角色信息
	 * @param basicsRole
	 * @return
	 */
	public boolean saveRole(BasicsRole basicsRole) {
		int userId = Constants.getCurrendUser().getUserId();
		int companyId = Constants.getCurrendUser().getCompanyId();

		if (null == basicsRole.getRoleId() || "".equals(basicsRole.getRoleId())) // 新角色新增
		{
			basicsRole.setCompanyId((long) companyId);
			basicsRole.setSymbol("N");
			basicsRole.setIsdel("N");
			basicsRole.setcreateTime(new Date());
			basicsRole.setLastModTime(new Date());
			basicsRole.setcreateUserId((long) userId);
			basicsRole.setModUserId((long) userId);
			basicsRole.setSpecialRoleNum("0");
			basicsRoleMapper.insertRole(basicsRole);
			return true;
		} else// 原角色修改
		{
			// 判断是否是特殊角色
			String result = basicsRoleMapper.selectSpecialRoleNum(basicsRole.getRoleId());
			if ("0".equals(result)) {
				basicsRole.setLastModTime(new Date());
				basicsRole.setModUserId((long) userId);
				basicsRoleMapper.updateRole(basicsRole);
				return true;
			} else {
				return false;
			}

		}

		/*
		 * // 判断是否是特殊角色 String result =
		 * basicsRoleMapper.selectSpecialRoleNum(basicsRole.getRoleId());
		 * if("0".equals(result)){ int userId =
		 * Constants.getCurrendUser().getUserId(); int companyId =
		 * Constants.getCurrendUser().getCompanyId();
		 * 
		 * if (null == basicsRole.getRoleId() ||
		 * "".equals(basicsRole.getRoleId())) // 新角色新增 {
		 * basicsRole.setCompanyId((long) companyId); basicsRole.setSymbol("N");
		 * basicsRole.setIsdel("N"); basicsRole.setcreateTime(new Date());
		 * basicsRole.setLastModTime(new Date());
		 * basicsRole.setcreateUserId((long) userId);
		 * basicsRole.setModUserId((long) userId);
		 * basicsRole.setSpecialRoleNum("0");
		 * basicsRoleMapper.insertRole(basicsRole); } else// 原角色修改 {
		 * basicsRole.setLastModTime(new Date()); basicsRole.setModUserId((long)
		 * userId);
		 * 
		 * basicsRoleMapper.updateRole(basicsRole); } return true; } else{
		 * return false; }
		 */

	}

	/**
	 * @param userId
	 *            用户id
	 * @param roleIds
	 *            待分配的用户角色id号组成的字符串，以','分隔
	 * @return
	 */
	public ReturnJson saveUserRoleRel(String userId, String roleIds) {
		ReturnJson json = new ReturnJson();
		// 分割角色ID
		String[] roleIdArray = roleIds.split(",");
		// 判断roleIds是否为空
		if (!"".equals(roleIds)) {
			// sb用来存储遍历出来的specialRoleNum
			StringBuffer sb = new StringBuffer();
			// 遍历
			for (String string : roleIdArray) {
				BasicsRole b = basicsRoleMapper.getBasicsRoleByRoleId(Long.valueOf(string));
				sb.append(b.getSpecialRoleNum());
			}
			// 得到所有的specialRoleNum转化为char数组
			char[] c = sb.toString().toCharArray();
			// count2用来记录specialRoleNum = 2的供货
			int count2 = 0;
			// count3用来记录specialRoleNum = 3的商户
			int count3 = 0;
			for (char d : c) {
				if (d == '2') {
					count2++;
				}
				if (d == '3') {
					count3++;
				}
			}
			// 判断count2和count3是否同时为1
			if (count2 == 1 && count3 == 1) {
				json.setMessage("商户和供货人员角色只能选择一个");
				return json;
			}
		}

		// 根据roleId把所有的log记录的operation置为0
		int operationUserId = Constants.getCurrendUser().getUserId();
		basicsUserRoleLogMapper.updateOperation0(userId, operationUserId);

		List<BasicsUserRole> list = basicsUserRoleMapper.findBasicsUserRoleByUserId(Long.valueOf(userId));

		for (BasicsUserRole basicsUserRole : list) {
			String result = basicsRoleMapper.selectSpecialRoleNum(basicsUserRole.getRoleId());
			if ("1".equals(result)) {
				for (String string : roleIdArray) {
					if (!string.equals(String.valueOf(basicsUserRole.getRoleId()))) {
						int count = basicsUserMachineMapper.judgeByUserIdSpecialRoleNum(userId, result);
						if (count > 0) {
							json.setMessage("请先取消维修员与设备关联");
							return json;
						}
					}
				}

			} else if ("2".equals(result)) {
				for (String string : roleIdArray) {
					if (!string.equals(String.valueOf(basicsUserRole.getRoleId()))) {
						int count = basicsUserMachineMapper.judgeByUserIdSpecialRoleNum(userId, result);
						if (count > 0) {
							json.setMessage("请先取消供货员与设备关联");
							return json;
						}
					}
				}
			}
		}

		basicsUserRoleMapper.deleteUserRoleRelByUserId(Long.valueOf(userId));
		if (!"".equals(roleIds) && roleIds.length() != 0) {
			for (String roleId : roleIdArray) {
				if (null != roleId && !"".equals(roleId) && !" ".equals(roleId)) {
					BasicsUserRole basicsUserRole = new BasicsUserRole();

					basicsUserRole.setUserId(Long.valueOf(userId));
					basicsUserRole.setRoleId(Long.valueOf(roleId));
					if (basicsUserRoleMapper.insert(basicsUserRole) > 0) {
						log(basicsUserRole.getUserId(), basicsUserRole.getRoleId(), "1");
						json.setStatus(true);
						json.setMessage(Constants.POST_DATA_SUCCESS);
					}
				}
			}
		} else {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		}

		return json;
	}

	private void log(Long userId, Long roleId, String operation) {
		// 写入日志
		BasicsUserRoleLog basicsUserRoleLog = new BasicsUserRoleLog();

		// 以前有记录，则把该记录operation置为1
		if (basicsUserRoleLogMapper.getCount(userId, roleId) > 0) {
			basicsUserRoleLog.setUserId(userId);
			basicsUserRoleLog.setRoleId(roleId);
			basicsUserRoleLog.setOperation("1");
			basicsUserRoleLog.setOperationTime(new Date());
			basicsUserRoleLog.setOperationUserId((long) Constants.getCurrendUser().getUserId());
			basicsUserRoleLogMapper.updateOperation1(basicsUserRoleLog);
		}
		// 没有记录，则增加一条log
		else {
			basicsUserRoleLog.setUserId(userId);
			basicsUserRoleLog.setRoleId(roleId);
			basicsUserRoleLog.setOperation("1");
			basicsUserRoleLog.setOperationTime(new Date());
			basicsUserRoleLog.setOperationUserId((long) Constants.getCurrendUser().getUserId());
			basicsUserRoleLogMapper.insertSelective(basicsUserRoleLog);
		}
	}

	/**
	 * @function 删除角色(只更新角色记录的删除标志 )
	 * @param roleId
	 * @return
	 */
	public ReturnJson delRole(String roleId) {
		ReturnJson json = new ReturnJson();
		// 判断是否是维修补货角色
		String result = basicsRoleMapper.selectSpecialRoleNum(Long.valueOf(roleId));
		if ("0".equals(result)) {
			// 判断是否被使用，被使用不能删除
			List<Long> basicsUserRoles = basicsUserRoleMapper.findBasicsUserRoleByRoleId(Long.valueOf(roleId));
			if (basicsUserRoles == null || (basicsUserRoles != null && basicsUserRoles.size() == 0)) {
				basicsRoleMapper.updateRoleForDelete(Long.valueOf(roleId));
				// 如果有关联同时删除她和用户的失效的关联
				basicsUserRoleMapper.deleteUserRoleRelByRoleId(Long.valueOf(roleId));
				json.setStatus(true);
				json.setMessage(Constants.POST_DATA_SUCCESS);
			} else {
				json.setMessage("操作失败，该角色使用中...");
			}
		} else {
			json.setMessage("该角色无法删除");
		}
		return json;
	}

	/**
	 * @function 启用或停用角色
	 * @param basicsRole
	 * @return
	 */
	public boolean updateRoleForUsed(BasicsRole basicsRole) {
		return (basicsRoleMapper.updateRoleForUsed(basicsRole) > 0) ? true : false;
	}

	public List<Map<String, Object>> getRoleName(Long companyId) {
		return basicsRoleMapper.getRoleName(companyId);
	}
}
