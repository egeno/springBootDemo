package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblCustomerApplyAddress;
import com.qjkj.qjcsp.entity.TblCustomerApplyAddressList;

@MyBatisRepository
public interface TblCustomerApplyAddressMapper {
    //int deleteByPrimaryKey(Long id);

    int insert(TblCustomerApplyAddress record);

    //int insertSelective(TblCustomerApplyAddress record);

    //TblCustomerApplyAddress selectByPrimaryKey(Long id);

    //int updateByPrimaryKeySelective(TblCustomerApplyAddress record);

    //int updateByPrimaryKey(TblCustomerApplyAddress record);
    /*导出Excel */
    List<Map<String, Object>> JoinCustomerListExport(Map<String, Object> param) ;
    int findCustomerCount(TblCustomerApplyAddressList tblCustomerApplyAddressList);
    List<TblCustomerApplyAddress> findCustomerList(TblCustomerApplyAddressList tblCustomerApplyAddressList);
    /*查询返回条数*/
    Long findByCount(Map<String, Object> param);
    
    List<TblCustomerApplyAddress> findCustomerAddress(Map<String, Object> param);
    
	TblCustomerApplyAddress selectForrepeat(@Param("customerMobile")String customerMobile, @Param("region")String region, @Param("detailAddress")String detailAddress);
}