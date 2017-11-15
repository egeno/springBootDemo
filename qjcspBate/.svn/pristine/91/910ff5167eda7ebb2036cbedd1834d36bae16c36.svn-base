package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.TblOrderPickDetail;
@MyBatisRepository
public interface TblOrderPickDetailMapper {
    int deleteByPrimaryKey(Long orderPickId);

    int insert(TblOrderPickDetail record);

    int insertSelective(TblOrderPickDetail record);

    TblOrderPickDetail selectByPrimaryKey(Long orderPickId);

    int updateByPrimaryKeySelective(TblOrderPickDetail record);

    int updateByPrimaryKey(TblOrderPickDetail record);

    //根据itemId与orderDetailId查找数量
	int selectcountBytopd(TblOrderPickDetail topd);
//根据orderNum查找该订单下全部记录与故障取餐记录的差
	int selectCountByOrderNum(@Param("orderNum") String orderNum);
	//根据orderChildId查找该订单下全部记录与故障取餐记录的差
	int selectCountByOrderChildId(@Param("orderChildId") Long orderChildId);
	//根据orderNum查找该订单下故障取餐记录的数量
	int selectAlarmCountByOrderNum(@Param("orderNum") String orderNum);
	//根据orderChildId查找该订单下故障取餐记录的数量
	int selectAlarmCountByOrderChildId(@Param("orderChildId") Long orderChildId);
	//根据orderDetailId查找取餐明细集合
	List<TblOrderPickDetail> selectListByOrderDetailId(@Param("orderDetailId") Long orderDetailId);

	List<Map<String, Object>> selectAlarmCellNumByOrderDetailId(Long orderDetailId);
}