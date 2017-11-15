package com.qjkj.qjcsp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblItemDetail;

@MyBatisRepository
public interface TblItemDetailMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(TblItemDetail record);

    int insertSelective(TblItemDetail record);

    TblItemDetail selectByPrimaryKey(String itemId);

    int updateByPrimaryKeySelective(TblItemDetail record);

    int updateByPrimaryKey(TblItemDetail record);
    
	long findItemDetailCountByAreaModelId(long areaModelId);
	
	 /*锁定单品*/
    int updateItemDetailbyItemIdList(@Param("itemIdList") List<String> itemIdList, @Param("itemStatus") String itemStatus,
    		@Param("orderId") Long orderId,@Param("orderChildId") Long orderChildId,@Param("orderDetailId") Long orderDetailId,
    		@Param("orderTime") Date orderTime);
	
    int unlockItemByOrderId(@Param("orderId")Long orderId, @Param("itemStatus")String itemStatus);
    
    int unlockItemByOrderChildId(@Param("orderChildId")Long orderChildId, @Param("itemStatus")String itemStatus);
    
    int updateItemByOrderChildId(@Param("orderChildId")Long orderChildId, @Param("itemStatus")String itemStatus, @Param("cellStatus")String cellStatus);
    
    List<Map<String,Object>> findItemDetailBydeviceCodeAndOrderNum(@Param("orderDetailId")Long orderDetailId,@Param("machineId")Long machineId);

    List<TblItemDetail> selectRepeatOrder(@Param("machineId")Long machineId, @Param("idCode")String idCode);
	
	Map<String, String> selectBuyNumAndReplenishmentNum(@Param("orderChildNum") String orderChildNum);

	List<TblItemDetail> selectByOrderDetailId(@Param("orderDetailId") Long orderDetailId);

	void updateItemByitemId(@Param("itemId")String itemId, @Param("itemStatus")String itemStatus, @Param("cellStatus")String cellStatus);

	//根据订单号查询改订单下的单品未取的数量
	int selectCountByOrderId(Long orderId);
	//根据子订单号查询改订单下的单品未取的数量
	int selectCountByOrderChildId(Long orderChildId);
}