package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.viewmodel.TblCustomerVo;

@MyBatisRepository
public interface TblCustomerMapper {
    int deleteByPrimaryKey(Long customerId);
   
    /*获取需要推送的用户ID集合（CustomerId）
     */
   /* List<Integer> selectCustomerId();*/
    
    int insert(TblCustomer record);

    int insertSelective(TblCustomer record);

    TblCustomer selectByPrimaryKey(Long customerId);
    
    TblCustomer selectTblCustomerByCustomerIdForUpdate(Long customerId);

    int updateByPrimaryKeySelective(TblCustomer record);

    int updateByPrimaryKey(TblCustomer record);

	TblCustomer selectByMobileNum(String mobileNum);

	TblCustomer selectByMobileNumCode(@Param("mobileNum")String mobileNum, @Param("verifyCode")String verifyCode);
	
	int selectByCustomerMobile(@Param("mobileNum")String mobileNum);

	Long getCount(Map<String, Object> map);

	List<TblCustomer> findAppUserIntegral(Map<String, Object> map);

	int update(TblCustomerVo tblCustomerVo);
	
    Map<String,String> findCustomerByCustomerId(Long customerId);
    
    Long getInfoByCount(Map<String, Object> param);

	List<TblCustomer> findAPPUserInfo(Map<String, Object> param);

	int updateIsdel(Long customerId);
	
	public Map<String,String> selectByPrimaryKeyMap(Long customerId);
	
	Integer findActivitySymbolByCustomerId(Long customerId);
	
	int updateDiscountUsed(@Param("customerId")Long customerId, @Param("discountUsedSymbol")String discountUsedSymbol);
	
	TblCustomer selectByCustomerIdCode(@Param("customerId")String customerId, @Param("code")String code);

	List<Map<String, Object>> exportSearchDaily(Map<String, Object> param);
	
	// wsk   查看客户反馈信息
	List<Map<String, Object>> findCustomerQuestion(Map<String, Object> map);
	
	// wsk   查看客户反馈信息条数
	int getQuestionnum(Map<String, Object> map);
	/**
	 * 按照微信端的openid查询是否存在用户
	 * */
	int findCustomerByCustomerWeiXin(@Param("openId")String openId);
	
	Map<String, Object> findCustomerInfoByCustomerWeiXin(@Param("openId")String openId);
	TblCustomer selectByOrderId(Long orderId);
}