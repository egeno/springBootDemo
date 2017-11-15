package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.BasicsMenu;
import com.qjkj.qjcsp.entity.BasicsUsers;

@MyBatisRepository
public interface ShiroUserMapper {

	Long findPlaUserByCount(Map<String, Object> param);

	List<BasicsUsers> findPlaUserByList(Map<String, Object> param);

	int insert(BasicsUsers basicsUsers);

	int update(BasicsUsers basicsUsers);

	int updateIsdel(Long userId);

	Long findComUserByCount(Map<String, Object> param);

	List<BasicsUsers> findComUserByList(Map<String, Object> param);

	Long findMerUserByCount(Map<String, Object> param);

	List<BasicsUsers> findMerUserByList(Map<String, Object> param);

	List<Map<String, Object>> findAllUnselectCompany();

	int findCompanyCount();

	Long findUserInformationByCount(Map<String, Object> param);

	List<BasicsUsers> findUserInformationByList(Map<String, Object> param);
	
	BasicsUsers shiroUserLoginValidate(BasicsUsers shiroUser);
	
	BasicsUsers findBasicsUserByUserId(int userId);
	
	List<BasicsMenu> getMenuListByUserId(int userId);
	
	short findUserType(Map<String, Object> param);

	int judgeExist(@Param("userType")Long userType, @Param("companyId")Long companyId);

	Long getCountByCompanyId(Map<String, Object> param);

	List<BasicsUsers> getListByCompanyId(Map<String, Object> param);
	
	int check(String userMobile);

	int checkName(String userName);

	int checkAccount(String userAccount);
	
	int checkEdit(@Param("userMobile")String userMobile, @Param("userId")long userId);
	
	int checkNameEdit(@Param("userName")String userName, @Param("userId")long userId);
	
	int checkAccountEdit(@Param("userAccount")String userAccount, @Param("userId")long userId);

	List<BasicsUsers> findAllMerComUser(Map<String, Object> param);

	Long findMerComUserByCount(Map<String, Object> param);
	
}
