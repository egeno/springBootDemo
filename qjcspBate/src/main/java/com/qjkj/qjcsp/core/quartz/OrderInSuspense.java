package com.qjkj.qjcsp.core.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.qjkj.qjcsp.service.order.common.OrderCommonService;
import com.qjkj.qjcsp.service.order.common.OrderInSuspenseService;


/**
 * 轮询待处理订单
 */
public class OrderInSuspense {

	@Autowired
	private OrderInSuspenseService orderInSuspenseService;
	@Autowired
	private OrderCommonService orderCommonService;
	/*
	public void executePollingOrder() throws Exception {  
	    ExecutorService exec = Executors.newFixedThreadPool(1);  
	      
	    Thread thread = new Thread(new Runnable() {  
	        public void run() {  
	            try {  
	            	orderInSuspense();
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    });  
	    exec.execute(thread);  
	    exec.shutdown();  
	       while (!exec.isTerminated()) {  
	           // 等待所有子线程结束，才退出主线程  
	       }          
	}
	*/  
	
	public void orderInSuspense() {
		orderInSuspenseService.pendingOrderInSuspense();
	}
	// System.out.println("订单轮询");
	
	
	
	//Gekko 借宝地一用，以下方法为每7天删除一次临时表内7天前的数据
	public void delExpireTemporary(){
		orderCommonService.delExpireTemporary();
		
	}
	
	
	
	
	
}