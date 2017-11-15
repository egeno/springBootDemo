package com.qjkj.qjcsp.service.shiro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsCompany;
import com.qjkj.qjcsp.entity.BasicsMenu;
import com.qjkj.qjcsp.entity.BasicsRole;
import com.qjkj.qjcsp.entity.BasicsSpecialRole;
import com.qjkj.qjcsp.entity.BasicsUserRole;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.viewmodel.TreeModel;
import com.qjkj.qjcsp.mapper.BasicsMenuMapper;
import com.qjkj.qjcsp.mapper.BasicsRoleMapper;
import com.qjkj.qjcsp.mapper.BasicsSpecialRoleMapper;
import com.qjkj.qjcsp.mapper.BasicsUserMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUserRoleMapper;
import com.qjkj.qjcsp.mapper.ShiroUserMapper;
import com.qjkj.qjcsp.service.company.CompanyService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.MD5Util;

@Component
@Transactional
public class ShiroUserService {
	
	public static final String  delFlag = "0"; //删除标识
	public static final String  succFlag = "1";
	public static final String  isDelFlag = "N";
	@Autowired
	private ShiroUserMapper shiroUserMapper;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private BasicsMenuMapper basicsMenuMapper;
	@Autowired
	private BasicsSpecialRoleMapper basicsSpecialRoleMapper;
	@Autowired
	private BasicsRoleMapper basicsRoleMapper;
	@Autowired
	private BasicsUserMachineMapper basicsUserMachineMapper;
	@Autowired
	private BasicsUserRoleMapper basicsUserRoleMapper;
	/**
	 * @function 根据用户id返回用户对象
	 * @param userId 用户id
	 * @return
	 */
	public BasicsUsers findBasicsUserByUserId(int userId)
	{
		return shiroUserMapper.findBasicsUserByUserId(userId);
	}
	
	/**
	 * @function 根据用户id返回用户对应的权限菜单
	 * @param userId 用户id
	 * @return
	 */
	public List<BasicsMenu> getMenuListByUserId(int userId)
	{
		return shiroUserMapper.getMenuListByUserId(userId);
	}
	
	/**
	 * @根据当前登陆用户返回用户对应的权限菜单
	 * @return
	 */
	public List<BasicsMenu> getMenuListByLoginUser()
	{
		int userId = Constants.getCurrendUser().getUserId();
		List<BasicsMenu> menuList = new ArrayList<BasicsMenu>();
		BasicsUsers basicsUsers = findBasicsUserByUserId(userId);
		
		if(null != basicsUsers)
		{
			if(Constants.SYSTEM_USERTYPE_PLATFORM_ADMIN == basicsUsers.getUserType())//超级管理员
			{
				//返回所有平台或公用的菜单权限
				menuList = basicsMenuMapper.findFunctionListForPermission(Constants.MENU_CATEGORY_PLATFORM);
			}
			else if(Constants.SYSTEM_USERTYPE_MERCHANT_ADMIN == basicsUsers.getUserType())//用户拥有商户管理员角色
			{
				//返回所有商户或公用的菜单权限
				menuList = basicsMenuMapper.findFunctionListForPermission(Constants.MENU_CATEGORY_MERCHANT);
			}
			else//普通用户
			{
				menuList = getMenuListByUserId(userId);
			}
		}
		
		
		
		return menuList;
	}
	/**
	 * 查找所有平台用户
	 */
	public Page<BasicsUsers> findAllPlaUser(Map<String, Object> param, int pageNumber, int pageSize){
		Long total = shiroUserMapper.findPlaUserByCount(param);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order", "ID");
		param.put("sort", Sort.Direction.DESC);

		List<BasicsUsers> list = new ArrayList<BasicsUsers>();
		if (total != 0) {
			list = shiroUserMapper.findPlaUserByList(param);
		}
		Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list, pageRequest, total);

		return page;
	}

	/**
	 * 平台用户管理编辑
	 */
	public HashMap<String, Object> persistenceShiroPlaUserEdit(BasicsUsers basicsUsers) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		long userId = Constants.getCurrendUser().getUserId();
		//添加
		if (null==basicsUsers.getUserId() || "".equals(basicsUsers.getUserId()))
		{
			if(shiroUserMapper.check(basicsUsers.getUserMobile()) > 0){
				map.put("message", "手机号码不能重复！");
				return map;
			}
			if(shiroUserMapper.checkAccount(basicsUsers.getUserAccount()) > 0){
				map.put("message", "登录账号不能重复！");
				return map;
			}
			if(shiroUserMapper.checkName(basicsUsers.getUserName()) > 0){
				map.put("message", "用户名不能重复！");
				return map;
			}
			basicsUsers.setcreateTime(new Date());
			basicsUsers.setLastModTime(new Date());
			basicsUsers.setUserStatus(succFlag);
			basicsUsers.setIsdel(isDelFlag);
			basicsUsers.setcreateUserId(userId);
			basicsUsers.setModUserId(userId);
			basicsUsers.setUserType((short) 2);
			basicsUsers.setCompanyId((long) 1);
			basicsUsers.setPassword(MD5Util.getMD5String(basicsUsers.getPassword()));
			int r = shiroUserMapper.insert(basicsUsers);
			if(r > 0){
				map.put("status", true);
				map.put("message", "数据更新成功！");
				return map;
			}
			else{
				map.put("message", "提交失败了！");
				return map;
			}
		}
		//编辑
		else {
			if(shiroUserMapper.checkEdit(basicsUsers.getUserMobile(), basicsUsers.getUserId()) > 0){
				map.put("message", "手机号码不能重复！");
				return map;
			}
			if(shiroUserMapper.checkAccountEdit(basicsUsers.getUserAccount(), basicsUsers.getUserId()) > 0){
				map.put("message", "登录账号不能重复！");
				return map;
			}
			if(shiroUserMapper.checkNameEdit(basicsUsers.getUserName(), basicsUsers.getUserId()) > 0){
				map.put("message", "用户名不能重复！");
				return map;
			}
			basicsUsers.setLastModTime(new Date());
			basicsUsers.setModUserId(userId);
//			basicsUsers.setPassword(MD5Util.getMD5String(basicsUsers.getPassword()));
			int r = shiroUserMapper.update(basicsUsers);
			if(r > 0){
				map.put("status", true);
				map.put("message", "数据更新成功！");
				return map;
			}
			else{
				map.put("message", "提交失败了！");
				return map;
			}
		}
	}
	
	/**
	 * 商户普通用户管理添加、编辑
	 */
	public HashMap<String, Object> persistenceShiroComUserEdit(BasicsUsers basicsUsers) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		long companyId = Constants.getCurrendUser().getCompanyId();
		long userId = Constants.getCurrendUser().getUserId();
		//添加
		if (null==basicsUsers.getUserId() || "".equals(basicsUsers.getUserId()))
		{
			if(shiroUserMapper.check(basicsUsers.getUserMobile()) > 0){
				map.put("message", "手机号码不能重复！");
				return map;
			}
			if(shiroUserMapper.checkAccount(basicsUsers.getUserAccount()) > 0){
				map.put("message", "登录账号不能重复！");
				return map;
			}
			if(shiroUserMapper.checkName(basicsUsers.getUserName()) > 0){
				map.put("message", "用户名不能重复！");
				return map;
			}
			basicsUsers.setcreateTime(new Date());
			basicsUsers.setLastModTime(new Date());
			basicsUsers.setUserStatus(succFlag);
			basicsUsers.setIsdel(isDelFlag);
			basicsUsers.setcreateUserId(userId);
			basicsUsers.setModUserId(userId);
			basicsUsers.setUserType((short) 4);
			basicsUsers.setCompanyId(companyId);
			basicsUsers.setPassword(MD5Util.getMD5String(basicsUsers.getPassword()));
			int r = shiroUserMapper.insert(basicsUsers);
			if(r > 0){
				map.put("status", true);
				map.put("message", "数据更新成功！");
				return map;
			}
			else{
				map.put("message", "提交失败了！");
				return map;
			}
		}
		//编辑
		else {
			if(shiroUserMapper.checkEdit(basicsUsers.getUserMobile(), basicsUsers.getUserId()) > 0){
				map.put("message", "手机号码不能重复！");
				return map;
			}
			if(shiroUserMapper.checkAccountEdit(basicsUsers.getUserAccount(), basicsUsers.getUserId()) > 0){
				map.put("message", "登录账号不能重复！");
				return map;
			}
			if(shiroUserMapper.checkNameEdit(basicsUsers.getUserName(), basicsUsers.getUserId()) > 0){
				map.put("message", "用户名不能重复！");
				return map;
			}
			basicsUsers.setLastModTime(new Date());
			basicsUsers.setModUserId(userId);
//			basicsUsers.setPassword(MD5Util.getMD5String(basicsUsers.getPassword()));
			int r = shiroUserMapper.update(basicsUsers);
			if(r > 0){
				map.put("status", true);
				map.put("message", "数据更新成功！");
				return map;
			}
			else{
				map.put("message", "提交失败了！");
				return map;
			}
		}
	}
	
	/**
	 * 商户管理用户管理添加、编辑
	 */
	public HashMap<String, Object> persistenceShiroMerUserEdit(BasicsUsers basicsUsers) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		long userId = Constants.getCurrendUser().getUserId();
		int result = 0;
		//添加
		if (null==basicsUsers.getUserId() || "".equals(basicsUsers.getUserId()))
		{	
			if(shiroUserMapper.check(basicsUsers.getUserMobile()) > 0){
				map.put("message", "手机号码不能重复！");
				return map;
			}
			if(shiroUserMapper.checkAccount(basicsUsers.getUserAccount()) > 0){
				map.put("message", "登录账号不能重复！");
				return map;
			}
			if(shiroUserMapper.checkName(basicsUsers.getUserName()) > 0){
				map.put("message", "用户名不能重复！");
				return map;
			}
			basicsUsers.setPassword(MD5Util.getMD5String(basicsUsers.getPassword()));
			basicsUsers.setcreateTime(new Date());
			basicsUsers.setLastModTime(new Date());
			basicsUsers.setUserStatus(succFlag);
			basicsUsers.setIsdel(isDelFlag);
			basicsUsers.setcreateUserId(userId);
			basicsUsers.setModUserId(userId);
			basicsUsers.setUserType((short) 3);
			int r = shiroUserMapper.insert(basicsUsers);
			long userType = 3;
			int count = shiroUserMapper.judgeExist(userType,basicsUsers.getCompanyId());
			if(r > 0){
				if(count > 0){
					BasicsRole basicsRole = new BasicsRole();
					List<BasicsSpecialRole> list = basicsSpecialRoleMapper.selectAll();
					for (BasicsSpecialRole basicsSpecialRole : list) {
						if("维修人员".equals(basicsSpecialRole.getSpecialRoleName())){
							continue;
						}
						basicsRole.setRoleName(basicsSpecialRole.getSpecialRoleName());
						basicsRole.setRoleMemo(basicsSpecialRole.getSpecialRoleMemo());
						basicsRole.setSpecialRoleNum(basicsSpecialRole.getSpecialRoleNum());
						basicsRole.setCompanyId(basicsUsers.getCompanyId());
						basicsRole.setRoleStatus("1");
						basicsRole.setSymbol("N");
						basicsRole.setIsdel("N");
						basicsRole.setcreateTime(new Date());
						basicsRole.setLastModTime(new Date());
						basicsRole.setcreateUserId(userId);
						basicsRole.setModUserId(userId);
						result = result + basicsRoleMapper.insertRole(basicsRole);
					}
					/*if(result > (list.size()-1)){*/
						map.put("status", true);
						map.put("message", "数据更新成功！");
						return map;
					/*}*/
				}
				else{
					map.put("status", true);
					map.put("message", "数据更新成功！");
					return map;
				}
			}
			else{
				map.put("status", true);
				map.put("message", "数据更新成功！");
				return map;
			}
		}
		//编辑
		else {
			if(shiroUserMapper.checkEdit(basicsUsers.getUserMobile(), basicsUsers.getUserId()) > 0){
				map.put("message", "手机号码不能重复！");
				return map;
			}
			if(shiroUserMapper.checkAccountEdit(basicsUsers.getUserAccount(), basicsUsers.getUserId()) > 0){
				map.put("message", "登录账号不能重复！");
				return map;
			}
			if(shiroUserMapper.checkNameEdit(basicsUsers.getUserName(), basicsUsers.getUserId()) > 0){
				map.put("message", "用户名不能重复！");
				return map;
			}
			basicsUsers.setLastModTime(new Date());
			basicsUsers.setModUserId(userId);
			int r = shiroUserMapper.update(basicsUsers);
			if(r > 0){
				map.put("status", true);
				map.put("message", "数据更新成功！");
				return map;
			}
			else{
				map.put("message", "提交失败了！");
				return map;
			}
		}
	}
	
	/**
	 * 查找所有商户普通用户
	 */
	public Page<BasicsUsers> findAllComUser(Map<String, Object> param, int pageNumber, int pageSize){
		/*BasicsCompany basicsCompany = companyService.selectByPrimaryKey((long)Constants.getCurrendUser().getCompanyId());
		String symbol = basicsCompany.getSymbol();
		//判断登录帐号是否是全家
		if("Y".equals(symbol)){
			long companyId = Constants.getCurrendUser().getCompanyId();
			param.put("companyId", companyId);
			Long total = shiroUserMapper.findComUserByCount(param);
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			
			param.put("offset", (pageNumber - 1) * pageSize);
			param.put("limit", pageSize);
			param.put("order", "ID");
			param.put("sort", Sort.Direction.DESC);

			List<BasicsUsers> list = new ArrayList<BasicsUsers>();
			if (total != 0) {
				list = shiroUserMapper.findComUserByList(param);
			}
			Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list, pageRequest, total);

			return page;
		}
		//不是全家则显示该商户下的普通用户
		else{*/
			long companyId = Constants.getCurrendUser().getCompanyId();
			param.put("companyId", companyId);
			Long total = shiroUserMapper.findComUserByCount(param);
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

			param.put("offset", (pageNumber - 1) * pageSize);
			param.put("limit", pageSize);
			param.put("order", "ID");
			param.put("sort", Sort.Direction.DESC);

			List<BasicsUsers> list = new ArrayList<BasicsUsers>();
			if (total != 0) {
				list = shiroUserMapper.findComUserByList(param);
			}
			Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list, pageRequest, total);

			return page;
		/*}*/
	}
	
	/**
	 * 商户角色管理下用户查询
	 */
	public Page<BasicsUsers> findAllMerComUser(Map<String, Object> param, int pageNumber, int pageSize){
		/*BasicsCompany basicsCompany = companyService.selectByPrimaryKey((long)Constants.getCurrendUser().getCompanyId());
		String symbol = basicsCompany.getSymbol();
		//判断登录帐号是否是全家
		if("Y".equals(symbol)){
			long companyId = Constants.getCurrendUser().getCompanyId();
			param.put("companyId", companyId);
			Long total = shiroUserMapper.findComUserByCount(param);
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
			
			param.put("offset", (pageNumber - 1) * pageSize);
			param.put("limit", pageSize);
			param.put("order", "ID");
			param.put("sort", Sort.Direction.DESC);

			List<BasicsUsers> list = new ArrayList<BasicsUsers>();
			if (total != 0) {
				list = shiroUserMapper.findComUserByList(param);
			}
			Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list, pageRequest, total);

			return page;
		}
		//不是全家则显示该商户下的普通用户
		else{*/
			long companyId = Constants.getCurrendUser().getCompanyId();
			param.put("companyId", companyId);
			Long total = shiroUserMapper.findMerComUserByCount(param);
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

			param.put("offset", (pageNumber - 1) * pageSize);
			param.put("limit", pageSize);
			param.put("order", "ID");
			param.put("sort", Sort.Direction.DESC);

			List<BasicsUsers> list = new ArrayList<BasicsUsers>();
			if (total != 0) {
				list = shiroUserMapper.findAllMerComUser(param);
			}
			Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list, pageRequest, total);

			return page;
		/*}*/
	}
	
	/**
	 * 查找所有商户管理用户
	 */
	public Page<BasicsUsers> findAllMerUser(Map<String, Object> param, int pageNumber, int pageSize){
		Long total = shiroUserMapper.findMerUserByCount(param);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order", "ID");
		param.put("sort", Sort.Direction.DESC);

		List<BasicsUsers> list = new ArrayList<BasicsUsers>();
		if (total != 0) {
			list = shiroUserMapper.findMerUserByList(param);
		}
		Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list, pageRequest, total);

		return page;
	}
	
	/**
	 * 根据user_id查找该用户信息
	 */
	public Page<BasicsUsers> findUserInformation(Map<String, Object> param, int pageNumber, int pageSize){
		BasicsCompany basicsCompany = companyService.selectByPrimaryKey((long)Constants.getCurrendUser().getCompanyId());
		String symbol = basicsCompany.getSymbol();
		if("Y".equals(symbol)){
			Integer userId = Constants.getCurrendUser().getUserId();
			param.put("userId", userId);
			//根据Id查找userType
			short userType = shiroUserMapper.findUserType(param);
			//判断是否是超级管理员
			if(userType == 1){
				param.remove("userId");
			}
			Long total = shiroUserMapper.findUserInformationByCount(param);
			PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

			param.put("offset", (pageNumber - 1) * pageSize);
			param.put("limit", pageSize);
			param.put("order", "ID");
			param.put("sort", Sort.Direction.DESC);

			List<BasicsUsers> list = new ArrayList<BasicsUsers>();
			if (total != 0) {
				list = shiroUserMapper.findUserInformationByList(param);
			}
			Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list, pageRequest, total);
			return page;
		}
		else{
			
			Integer userId = Constants.getCurrendUser().getUserId();
			param.put("userId", userId);
			int userType = shiroUserMapper.findUserType(param);
			if(userType == 3){
				param.put("companyId", basicsCompany.getCompanyId());
				Long total = shiroUserMapper.getCountByCompanyId(param);
				PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

				param.put("offset", (pageNumber - 1) * pageSize);
				param.put("limit", pageSize);
				param.put("order", "ID");
				param.put("sort", Sort.Direction.DESC);

				List<BasicsUsers> list = new ArrayList<BasicsUsers>();
				if (total != 0) {
					list = shiroUserMapper.getListByCompanyId(param);
				}
				Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list, pageRequest, total);

				return page;
			}
			else{
				Long total = shiroUserMapper.findUserInformationByCount(param);
				PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

				param.put("offset", (pageNumber - 1) * pageSize);
				param.put("limit", pageSize);
				param.put("order", "ID");
				param.put("sort", Sort.Direction.DESC);

				List<BasicsUsers> list = new ArrayList<BasicsUsers>();
				if (total != 0) {
					list = shiroUserMapper.findUserInformationByList(param);
				}
				Page<BasicsUsers> page = new PageImpl<BasicsUsers>(list, pageRequest, total);

				return page;
			}
		}
	}

	/**
	 * 商户管理用户管理内添加公司下拉框列表
	 */
	public List<TreeModel> findAllUnselectCompany(){
		List<TreeModel> list=new ArrayList<TreeModel>();
		List<Map<String, Object>> tempList  = shiroUserMapper.findAllUnselectCompany();

		for (Map<String, Object> device : tempList)
		{
			TreeModel treeModel=new TreeModel();
			treeModel.setId(device.get("company_id")+Constants.NULL_STRING);
			treeModel.setName(device.get("company_name")+Constants.NULL_STRING);
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			list.add(treeModel);
		}
		return list;
	}
	
	/**
	 * 判断当前待删除用户是否为当前登录用户
	 * @param userId 待删除用户id
	 * @return
	 */
	public boolean isDeleteUserAsLoginUser(Long userId)
	{
		return Constants.getCurrendUser().getUserId() == userId.intValue();
	}
	
	/**
	 * 商户管理用户管理删除
	 * @param userId 待删除用户id
	 */
	public boolean deleteUserByuserId(Long userId){
		long companyId = Constants.getCurrendUser().getCompanyId();
		//删除与角色的关联
		basicsUserRoleMapper.deleteUserRoleRelByUserId(userId);
		//basicsRoleMapper.updateIsdel(companyId);
		int r = shiroUserMapper.updateIsdel(userId);
		return r >0;
	}

	/**
	 * 查询未创建商户管理员的公司数量
	 */
	public int findCompanyCount() {
		int count = shiroUserMapper.findCompanyCount();
		return count;
	}
	
	public BasicsUsers shiroUserLoginValidate(BasicsUsers shiroUser)
	{
		return shiroUserMapper.shiroUserLoginValidate(shiroUser);
	}
	
	/**
	 * 密码重置
	 */
	public boolean resetPassword2(BasicsUsers basicsUsers) {	
		long userId = Constants.getCurrendUser().getUserId();
		basicsUsers.setLastModTime(new Date());
		basicsUsers.setModUserId(userId);
		basicsUsers.setPassword(MD5Util.getMD5String(basicsUsers.getPassword()));
		int r = shiroUserMapper.update(basicsUsers);
		return r > 0;
	}
	
	/**
	 * 添加用户时异步判断手机号是否重复
	 */
	public String check(String userMobile) {	
		String result = "";
		if(userMobile.equals("")){
			return result;
		}
		else{
			int r = shiroUserMapper.check(userMobile);
			if(r>0){
				result = "该手机号已存在";
				return result;
			}
			else{
				return result;
			}
		}
	}
	
	/**
	 * 删除用户时判断是否关联有设备
	 */
	public boolean whetherContextDevice(Long userId) {
		List<BasicsUserRole> list = basicsUserRoleMapper.findBasicsUserRoleByUserId(Long.valueOf(userId));
		
		for (BasicsUserRole basicsUserRole : list) {
			String result = basicsRoleMapper.selectSpecialRoleNum(basicsUserRole.getRoleId());
			if("1".equals(result) || "2".equals(result)){
				int count = basicsUserMachineMapper.judgeByUserIdSpecialRoleNum(String.valueOf(userId),result);
				if(count > 0){
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * 判断用户名是否重复
	 */
	public String checkName(String userName) {	
		String result = "";
		if(userName.equals("")){
			return result;
		}
		else{
			int r = shiroUserMapper.checkName(userName);
			if(r>0){
				result = "该用户名已存在";
				return result;
			}
			else{
				return result;
			}
		}
	}
	
	/**
	 * 判断登录账号是否重复
	 */
	public String checkAccount(String userAccount) {
		String result = "";
		if(userAccount.equals("")){
			return result;
		}
		else{
			int r = shiroUserMapper.checkAccount(userAccount);
			if(r>0){
				result = "该登录账号已存在";
				return result;
			}
			else{
				return result;
			}
		}
	}
}
