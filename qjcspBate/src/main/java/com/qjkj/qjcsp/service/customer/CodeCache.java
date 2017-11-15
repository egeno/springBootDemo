package com.qjkj.qjcsp.service.customer;

import java.awt.color.CMMException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.stereotype.Component;
@Component
public class CodeCache {
	//private ConcurrentHashMap<String, Map<String, Date>> cache = new ConcurrentHashMap<String, Map<String,Date>>();
	//private Map<Date,Object> cache=new TreeMap<Date,Object>();
	private Map<Date,Object> cache=new ConcurrentSkipListMap<Date, Object>();
	private volatile Long checkTime; // 上次 检查的时间戳
	public Map<Date, Object> put (Date dayTime ,Map maps){
		checkTime=dayTime.getTime();
		final Date dateTime=new Date();
		long  time=(dateTime.getTime()-checkTime)/(60*60*1000);
		if(time>=1){
			cache.put(dayTime,maps);
			Thread thread = new Thread( new Runnable() {
				public void run() {										
						//是否需要清理
					for(Date key:cache.keySet()){
					  Date keys=key;
					  if(dateTime.after(keys)){
						cache.remove(key);
     					//checkTime = System.currentTimeMillis();
					   }
					  }
					}					
			});
			thread.start();
			return cache;
		}else{
			cache.put(dayTime,maps);
			return cache;
		}
		
		/*if(checkTime == null){
			cache.put(dayTime,maps);
			return cache;
		}else{			
			cache.put(dayTime,maps);
			//cache.put(key,map);		
			// 检查 当前操作 与 checktime 相差时间 如果 大于 1小时 清理 
		    Date date=new Date();		
			long  time=(date.getTime()-checkTime)/(60*60*1000);
			//清理時間大于一小時
			if(time>=1){
				Thread thread = new Thread( new Runnable() {
					//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//String strTime=df.format(date);
					public void run() {										
							//是否需要清理
						for(Date key:cache.keySet()){
						  Date keys=key;
						  if(date.after(keys)){
							cache.remove(key);
	     					checkTime = System.currentTimeMillis();
						   }
						  }
						}					
				});
				thread.start();
			}
			return cache;
			
		}*/
		//保存用户相关信息	
	}		
	public Long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
	}
	
}
