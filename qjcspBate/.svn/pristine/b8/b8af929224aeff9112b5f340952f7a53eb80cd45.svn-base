package com.qjkj.qjcsp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblPendingOrder;

@MyBatisRepository
public interface TblPendingOrderMapper {
    int deleteByPrimaryKey(Long pendingId);

    int insert(TblPendingOrder record);

    int insertSelective(TblPendingOrder record);

    TblPendingOrder selectByPrimaryKey(Long pendingId);

    int updateByPrimaryKeySelective(TblPendingOrder record);

    int updateByPrimaryKey(TblPendingOrder record);

    List<TblPendingOrder> getAllTblPendingOrderList();
    
    int deleteByOrderId(Long orderId);
    
    int deleteByOrderChildId(Long orderChildId);
    
    TblPendingOrder queryPendingOrderByOrderNum(String orderNum);
    
    int deletebyOrderNum(String orderNum);
    
    int changePaidOrderToTakenOrder(@Param("takenOrderList")List<TblPendingOrder> takenOrderList);
}