package com.qjkj.qjcsp.core.quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 轮询得到超过预警数的分区的信息
 * 
 * @author yehx
 * @date 2016年1月16日 下午3:06:06
 */
public class AreaMinNumWaring {
	
/*	private static Logger logger=LoggerFactory.getLogger(AreaMinNumWaring.class);
	@Autowired
	private AreaWaringService areaWaringService;
	@Autowired
	private SmsService smsService;

	*//**
	 * 分区预警
	 * 
	 * @author yehx
	 * @date 2016年1月15日
	 * @return
	 *
	 *//*
	@Transactional
	public void areaWaringCheck() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,String> map1=new HashMap<String,String>();
		// 得到需要发送的预警信息
		List<MachineUseModelPo> lists = null ; 
		try {
			map = areaWaringService.getWaringInfo();
			lists=(List<MachineUseModelPo>) map.get("machineAreaWaringPolists");
			if (lists != null && lists.size() > 0) {
				for (int i = 0; i < lists.size(); i++) {
					//首先得到第一条预警信息
					MachineUseModelPo mup = lists.get(i);
					//首先得到的是这条预警信息中的goodsId
					List<Long> goodsIdsmup=mup.getGoodsIds();
					//根据设备id操作数据库表tbl_min_log,该设备下的菜品集合
					List<Long> goodsIdslog=areaWaringService.getGoodsIds(mup.getMachineId());
					//如果等于null，说明还没有发过短信预警信息,如果菜品id不相等，说明已经有补货过了，需要再次发送
					if(goodsIdslog==null|| !checkListIsEquals(goodsIdsmup,goodsIdslog)){
						StringBuffer sb=new StringBuffer();
						sb.append("您好,").append(mup.getAddress()).append(" ").append("设备").append(mup.getMachineId())
	 					.append("中的菜品 :").append(mup.getGoodsNames()).append(" 预警了！");
						//System.out.println(sb);
						Long machineId = mup.getMachineId();
						// 得到管理这台设备的补货员名称，id，和手机号
						List<PutGoodsUser> putGoodsUsers = areaWaringService.getPutGoodsUser(machineId);
						if(putGoodsUsers!=null && putGoodsUsers.size()>0){
							for(int j=0;j<putGoodsUsers.size();j++){
								//int userId=putGoodsUsers.get(j).getUserId();
								String userMobile=putGoodsUsers.get(j).getUserMobile();
								if(map1.containsKey(userMobile)){
									map1.put(userMobile, map1.get(userMobile)+" "+sb.toString());
								}else{
									map1.put(userMobile,sb.toString());
								}
							}
						}
						
					}
				}
			    
			}
		} catch (Exception e) {
			logger.error("分区预警出错="+e.getMessage());
			e.printStackTrace();

		}
		if(!map1.isEmpty()){
			try {
				for(MachineUseModelPo machineUseModelPo:lists){
					//将数据插入到tbl_min_log表中
					areaWaringService.insertTblMinLog(machineUseModelPo);
				}
				//调用发送短信的接口
				smsService.areaWarning(map1);
			} catch (Exception e) {
				logger.error("预警将数据插入到tbl_min_log表出错="+e.getMessage());
				e.printStackTrace();
				
			}
			
		}
		
	}*/
	/**
	 * 判断两个list是否相同或者被包含
	 * @author yehx
	 * @date 2016年1月20日
	 * @return
	 *
	 */
	public boolean checkListIsEquals(List<Long>listmup,List<Long>listlog){
		//首先判断两个list的长度是否相等
		if(listmup.size()>listlog.size()){
			return false;
		}
		
		//遍历listmup
		for(Long mupgoods:listmup){
			if(!listlog.contains(mupgoods)){
				return false;
			}
		}
		return true;
		
	}
	
}
