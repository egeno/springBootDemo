package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsRole;
import com.qjkj.qjcsp.entity.BasicsSpecialRole;

@MyBatisRepository
public interface BasicsRoleMapper {

	int deleteByRoleId(Long roleId);

	int insertRole(BasicsRole basicsRole);

	int updateRole(BasicsRole basicsRole);

	/**
	 * @function 用于逻辑删除，修改用户角色的isdel字段
	 * @param roleId
	 *            用户角色id号
	 * @return
	 */
	int updateRoleForDelete(Long roleId);

	/**
	 * @function 用于逻辑删除，修改用户角色的role_status字段
	 * @param roleId
	 *            用户角色id号
	 * @return
	 */
	int updateRoleForUsed(BasicsRole basicsRole);

	BasicsRole getBasicsRoleByRoleId(Long roleId);

	/**
	 * @function 获取商户管理员标志的用户角色记录,symbol="Y"
	 * @return
	 */
	BasicsRole getMerchantManagerRole();

	/**
	 * @function 根据商户公司id号获取该商户下的角色列表
	 * @param companyId
	 *            商户公司id号
	 * @return
	 */
	List<BasicsRole> findBasicsRoleByCompanyIdForMerchant(Long companyId);

	/**
	 * @function 根据商户公司id号获取该商户下的角色列表
	 * @param companyId
	 *            全家科技公司id号
	 * @return
	 */
	List<BasicsRole> findBasicsRoleByCompanyIdForPlatform(Long companyId);

	/**
	 * 根据roleId获取该角色SpecialRoleNum
	 * 
	 * @param roleId
	 * @return
	 */
	String selectSpecialRoleNum(long roleId);

	List<Map<String, Object>> getRoleName(@Param("companyId") Long companyId);

	Map<String, Object> getSpecialRoleNumByUserId(@Param("userId") Long userId);

	List<Map<String, Object>> findSpecialNumByMobile(String mobileNum);

	int updateIsdel(long companyId);

	List<BasicsRole> basicsFindRoles(Long companyId);

}