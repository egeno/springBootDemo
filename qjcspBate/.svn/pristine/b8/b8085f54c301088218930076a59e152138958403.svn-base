package com.qjkj.qjcsp.service.wechatapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.util.DateUtils;

@Service
public class MachineModelNameAllWeChatService {
	@Autowired
	BasicsMachineMapper basicsMachineMapper;
	/*
	 * 得到当前设备id下的所有模型
	 */
	public Map<String, Object> getModelAllWX(String machineId){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BasicsMachine basicsMachine = basicsMachineMapper.selectByMachineId(Long.valueOf(machineId));
		if(basicsMachine != null){
//			List<Map<String, Object>> list = basicsMachineMapper
//					.selectModelAllById((Long.valueOf(machineId)));
//			for (int i = 0; i < list.size(); i++) {
//				Map<String, Object> map = list.get(i);
//				map.put("index", i+"");
//			}
			returnMap.put("returnCode", "1");
			 List<Map<String, Object>> list=modelSort(machineId);
			 if(list.size()==0){
				 returnMap.put("returnCode", "2");
					returnMap.put("returnContent", "模型已经取消关联");
					return returnMap;
			 }
			returnMap.put("returnContent", list);
			
		}else{
			returnMap.put("returnCode", "2");
			returnMap.put("returnContent", "设备已被删除");
			
		}
		return returnMap;
	}
	public List<Map<String, Object>> modelSort(String machineId){
		   List<Map<String, Object>> list = basicsMachineMapper
					.selectModelAllById((Long.valueOf(machineId)));
		   if(list.size()==0){
			   return list;
			 }
		    Date date = new Date(); 
		    SimpleDateFormat hms= new SimpleDateFormat("HH:mm:ss");
		    Integer flag = 0;
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				Date datehms;
				
				try {
					datehms = hms.parse(hms.format(date));
					String takenStartTime = map.get("takenStartTime")+"";
					String timeType = map.get("timeType")+"";
					//Gekko 注：判断是否为当前时间模型
					SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
					String yyyymmdd = DateUtils.getDateFormat("yyyy-MM-dd",date);
					Date reserveEndTime=DateUtils.formatDate(yyyymmdd+" "+map.get("reserveEndTime").toString());
					if(date.before(reserveEndTime)){
						if(flag == 0){
							map.put("isDefault", "1");
						}else{
							map.put("isDefault", "0");
						}
						flag++;
					}else{
						map.put("isDefault", "0");
					}
					map.put("index", i+"");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//没有找到当前时间后的时间默认为第一个
			if(flag == 0){
				Map<String, Object> map = list.get(0);
				map.put("isDefault", "1");
			}
		   return list;
	   }
   //得到早，中，休闲，晚的预定截止时间
//   public Date getModelReserveDate(String timeType,String takenStartTime){
//	   Date date = null;
//	   SimpleDateFormat format =new SimpleDateFormat("HH:mm:ss");
//	   try {
//		   if("0".equals(timeType)){
//				 String hhmmss = "02:55:00";
//				
//					date = format.parse(hhmmss);
//				
//		   		
//			}else{
//				//1：中餐，2休闲，3晚餐   补货前一小时
//		   		Calendar c = Calendar.getInstance();  
//				c.setTime(format.parse(takenStartTime));
//				c.add(Calendar.MINUTE, -65);
//				
//				date = c.getTime();
//				
//			}
//	   } catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//	   }
//	   
//	   return date;
//   }
}
