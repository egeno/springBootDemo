package com.qjkj.qjcsp.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qjkj.qjcsp.core.mapper.MyBatisRepository;

@MyBatisRepository
public interface QuartzMapper {
    //清货前15-20分钟提醒
	List<Map<String, Object>> clearanceRemind();
	
	//得到所有用户信息
	List<Map<String, Object>> getCustomer_All();	
	
	//当前时间在补货前40分钟的模板id及补货截止时间    预订
	List<Map<String, String>>  getCleanGoodsInfo();	
	//当前时间在补货前40分钟的模板id及补货截止时间   零售
	List<Map<String, String>>  getCleanGoodsInfoRetail();	
	
	
	//得到补货前40分钟的补货员手机号和设备地址
	List<Map<String, String>>  getReplenishmentMobileAndAddress(@Param("areaModelId")Long areaModelId);		
	//得到已买餐的客户信息  客户ID号
	List<Map<String,Object>> getCustomer_Id();
	//获取补货员的userID
	/*List<Map<String,Object>> getUser_Id();*/
	
	//得到补货的订单及客户信息
	List<Map<String,String>> getReplenishmenOrder(@Param("cellNums")String cellNums,@Param("areaModelId")Long areaModelId,
			                                      @Param("machineId")Long machineId);
	//得到补货的模型ID  中，休闲，晚模型  预订补货提醒
	List<Map<String,Object>> getAreaModelId();
	//零售补货提醒
	List<Map<String,Object>> getAreaModelIdRetail();
	
	//得到补货的模型ID  早模型   预订
	List<Map<String,Object>> getBreakfastAreaModelId();
	//早餐零售补货提醒
	List<Map<String,Object>> getBreakfastAreaRetail();
	//根据模型id得到模型相关联的商户，众包商，补货人员的id以及当前模型的零售，预定补货开始时间
	List<Map<String, String>> getMachineInfoByModeId(@Param("areaModelId")Long areaModelId);
		
    //得到当前模型的剩余补货数
	List<Map<String, String>> getNotReplenishmenNum(@Param("areaModelId")Long areaModelId);
	
	//根据商户id得到商户补货数
	int getReplenishmentNum(@Param("machineId")Long machineId,@Param("areaModelId")Long areaModelId,
			                @Param("userId")Long userId);
	
	
}
