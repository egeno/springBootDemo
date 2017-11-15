package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.OrderRefund;
import com.qjkj.qjcsp.entity.RefundSearch;
import com.qjkj.qjcsp.entity.TblOrderRefund;
import com.qjkj.qjcsp.entity.order.RefundBussinessSearch;
import com.qjkj.qjcsp.service.refund.OrderRefundService;

@MyBatisRepository
public interface TblOrderRefundMapper {
    int deleteByPrimaryKey(Long orderRefundId);

    int insert(TblOrderRefund record);

    int insertSelective(TblOrderRefund record);
    
    int insertSelectiveOne(OrderRefund refund);

    TblOrderRefund selectByPrimaryKey(Long orderRefundId);

    int updateByPrimaryKeySelective(TblOrderRefund record);

    int updateByPrimaryKey(TblOrderRefund record);
    
    List<TblOrderRefund> getCompanyIdAndName();
    //运维人员预审列表查询
    List<OrderRefund> findAllRefundList(RefundSearch params);

    Long findAllRefundCount(RefundSearch refundSearch);
    
    Long findAllRefundFinanceCount(RefundSearch refundSearch);
    
    void orderRefundOperatePass(OrderRefund orderRefund);
    
    void orderRefundOperateReject(OrderRefund orderRefund);
    
    void updateOrderStatus(OrderRefund orderRefund);
    //财务人员预审列表查询
    List<OrderRefund> findAllRefundFinanceList(RefundSearch refundSearch);
    
    void orderRefundFinanceConfirm(OrderRefund orderRefund);
    
    void updateOrderStatusSuccess(OrderRefund orderRefund);
    
    Map<String, Object> selectOrderInfo(OrderRefund orderRefund);
    
    void updateOrderInfoAndCustomerBalance(Map<String, Object> map);
    
    int getCountByChildOrderId(@Param("childOrderId")Long childorderId);
    
    /**
     * 运维倒出
     * @param refundSearch
     * @return
     */
    public List<Map<String,Object>> findAllExportRefundBusinessList(RefundSearch refundSearch);
    
    /**
     * 财务导出
     * @param refundSearch
     * @return
     */
	public List<Map<String,Object>> findAllExportRefundFinanceList(RefundSearch refundSearch);
}