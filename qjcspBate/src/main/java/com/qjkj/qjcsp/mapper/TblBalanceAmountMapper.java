package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblBalanceAmount;
@MyBatisRepository
public interface TblBalanceAmountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TblBalanceAmount record);

    int insertSelective(TblBalanceAmount record);

    TblBalanceAmount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TblBalanceAmount record);

    int updateByPrimaryKey(TblBalanceAmount record);
    
    //结算相关
    TblBalanceAmount selectByCustomerId(@Param("customerId")Long customerId);
    
    int insertList(@Param("list")List<TblBalanceAmount> list);

    public List<Map<String, Object>> findAllBalanceAmountList(@RequestParam("rechargeId") String rechargeId);
    //结算相关end
}