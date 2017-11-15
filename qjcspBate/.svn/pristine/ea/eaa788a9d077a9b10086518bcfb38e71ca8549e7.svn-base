package com.qjkj.qjcsp.mapper;

import java.util.List;
import java.util.Map;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;

@MyBatisRepository
public interface StatisticsMapper {
	
	//每日柜子/商品供销统计列表
	Long findAllSupllyCount(Map<String,Object> param);//总数
	List<Map<String,Object>> findAllSupllyByModel(Map<String,Object> param);//以模型分组总计
	//每日柜子供销统计列表
	List<Map<String,Object>> findAllSupllyByMachine(Map<String,Object> param);//以设备分组总计
	List<Map<String,Object>> findAllSupllyByMachineAndModel(Map<String,Object> param);//以设备和模型分组记录
	//每日商品供销统计列表
	List<Map<String,Object>> findAllSupllyByGoods(Map<String,Object> param);//以设备分组总计
	List<Map<String,Object>> findAllSupllyByGoodsAndModel(Map<String,Object> param);//以设备和模型分组记录
	
	List<Map<String,Object>> findEverydaySales(Map<String,Object> map);
}
