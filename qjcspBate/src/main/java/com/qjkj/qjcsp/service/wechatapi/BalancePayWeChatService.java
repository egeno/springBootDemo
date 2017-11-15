package com.qjkj.qjcsp.service.wechatapi;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.mapper.BalancePayMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.service.order.common.OrderBackCallService;
import com.qjkj.qjcsp.service.order.common.OrderCommonService;
import com.qjkj.qjcsp.util.VerifyCode;
//import com.qjkj.qjcsp.service.order.common.TblDayMealsLogService;

@Service
public class BalancePayWeChatService {
	@Autowired
	private BalancePayMapper balancePayMapper;
	@Autowired
	private TblOrderMapper tblOrderMapper;
	@Autowired
	private OrderBackCallService orderBackCallService;
	@Autowired
	private OrderCommonService orderCommonService;
//	@Autowired
//	private TblDayMealsLogService tblDayMealsLogService;
	
	@Transactional
	public Map<String,Object> balancePayWX(String orderNum,BigDecimal realMoney,Long customerId){
		//首先判断该订单是否已经取消了
		Map<String,Object> map=new HashMap<String,Object>();
		String orderStatus=balancePayMapper.getorderStauts(orderNum);
		if(orderStatus.equals("1")){
			 map.put("returnCode", "0");
			 map.put("returnContent", "该订单已支付");
			 return map;
		}
		
		if(orderStatus.equals("2")){
			 map.put("returnCode", "0");
			 map.put("returnContent", "订单支付超时,请在5分钟之内完成支付");
			 return map;
		}
		//首先判断该订单的用户是否余额够支付
		
//	    BigDecimal b=balancePayMapper.getUserRealMoney(orderNum);
		//首先判断该订单的支付金额与数据库中的实际支付金额是否一致
		Boolean flagRealMoney=checkRealMoneyIsTrue(realMoney,orderNum);
		if(!flagRealMoney){
			 map.put("returnCode", "0");
			 map.put("returnContent", "支付金额与实际支付的金额不匹配");
			 return map;
		}
		//判断余额是否充足
		Boolean flag=checkUserBalance(customerId,realMoney);
		if(!flag){
		   map.put("returnCode", "0");
		   map.put("returnContent", "余额不足");
		   return map;
		}else {
			//修改订单表的状态，待处理表的状态，用户表的余额
		    balancePayMapper.updateBalanceForStatus(orderNum,realMoney);
			//修改子订单状态
			TblOrder tblOrder = balancePayMapper.getTblOrder(orderNum);
			balancePayMapper.updateOrderChildStatus(tblOrder.getOrderId());
			
			//将数据插入到用户余额消费记录表中
			balancePayMapper.insertCustomerbalanceshoppinglog(realMoney,customerId,orderNum);
			
			//根据父订单id得所有的子订单id
			List<Long> list = balancePayMapper.selectOrderChildByOrderid(tblOrder.getOrderId());
			 for(int i = 0; i < list.size(); i++ ){
				 Long orderChildId = list.get(i);
				//首先随机生成取餐验证码
				String verifyCode=VerifyCode.createVerifyCode();
				//将验证码插入到该数据库中
				balancePayMapper.updateOrderChildCode(verifyCode,orderChildId);
			 }
			/*发送短信，删除轮询表的父订单，添加所有子订单到轮询表*/
			TblOrder to = tblOrderMapper.selectByOrderNum(orderNum);
			orderCommonService.changePaidOrderToTakenOrder(to);
			
			Map<String,Object> map1=new HashMap<String,Object>();
			map1.put("orderNum", orderNum);
			map.put("returnCode", "1");
			map.put("returnContent",map1);
			/*//调用生成一日三餐的红包接口
			tblDayMealsLogService.insertDayMealsLog(orderNum);
			map.put("returnCode", "1");
			Map<String,Object> map1=new HashMap<String,Object>();
			map1.put("orderNum", orderNum);
			map.put("returnContent",map1);
			//短信发送取餐验证码
			//首先随机生成取餐验证码
			String verifyCode=orderBackCallService.getVerifyCode();
			//将验证码插入到该数据库中
			balancePayMapper.updateCustomerVerifyCode(verifyCode,orderNum);
			TblOrder order=balancePayMapper.getTblOrder(orderNum);
			orderBackCallService.sendVerifyCode(order, verifyCode);
			*/
		}
		return map;
	}
	
	public Boolean checkUserBalance(Long customerId,BigDecimal realMoney){
		//首先判断支付金额是否为0
		if(realMoney.equals("0")){
			return true;
		}
		BigDecimal userMoney=balancePayMapper.getUserBalance(customerId);
		if(userMoney!=null){
			if((userMoney.subtract(realMoney)).doubleValue()>=0){
				return true;
			}else{
				return false;
			}
		}
		return false;
		
		
	}
	
	public int checkHasUserWX(Long customerId,String password){
		return balancePayMapper.checkHasUser(customerId,password);
	}
	
	public Boolean checkRealMoneyIsTrue(BigDecimal realMoney,String orderNum){
		 BigDecimal b=balancePayMapper.getUserRealMoney(orderNum);
		 if(b.compareTo(realMoney)==0){
			 return true;
		 }
		return false;
	}
	
	

}
