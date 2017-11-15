package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;
import com.qjkj.qjcsp.entity.GoodsCommentInfo;

@MyBatisRepository
public interface GoodsCommentMapper {

	/**
	 * 2.3.7.	根据设备id号获取有评论内容的设备菜品评论
	 * @author yehx
	 * @date 2016年1月4日
	 * @param machineId
	 * @return
	 *
	 */
	public List<Map<String,Object>> getGoodsCommentsWithContentByMachineId(Long machineId);
	
	/**
	 * 根据设备id得到该设备的菜品评价分值
	 * @author yehx
	 * @date 2016年1月5日
	 * @param machineId
	 * @return
	 *
	 */
	public  Long getMachineGoodsAvgSore(Long machineId);
	/**
	 * 菜品id号和设备id号获取该设备下的菜品评价数量
	 * @author yehx
	 * @date 2016年1月5日
	 * @param map
	 * @return
	 *
	 */
	public  Long getMachineGoodsGradeCount(Map map);
	/**
	 * 根据设备id和菜品id得到菜品的评价信息
	 * @author yehx
	 * @date 2016年1月5日
	 * @param map
	 * @return
	 *
	 */
	public List<Map<String,Object>> getGoodsCommentsWithMachineIdGoodsId(Map map);
	/**
	 * 2.3.10.	根据菜品id号和设备id号获取该设备下有评论内容的菜品的商品评价数量
	 * @author yehx
	 * @date 2016年1月5日
	 * @param map
	 * @return
	 *
	 */
	public  Long getMachineGoodsGradeCountHasComment(Map map);
	/**
	 *  2.3.10.	根据菜品id号和设备id号获取该设备下有评论内容的菜品的商品评价信息
	 * @author yehx
	 * @date 2016年1月5日
	 * @param map
	 * @return
	 *
	 */
	public  List<Map<String,Object>>  getGoodsCommentsWithMachineIdGoodsIdHasComment(Map map);
	
}
