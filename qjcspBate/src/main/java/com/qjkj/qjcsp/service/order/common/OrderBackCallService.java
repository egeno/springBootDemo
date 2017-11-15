package com.qjkj.qjcsp.service.order.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.session.ExecutorType;
import org.quartz.ExecuteInJTATransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblPendingOrder;
import com.qjkj.qjcsp.entity.TblRecharge;
import com.qjkj.qjcsp.entity.TblTemporaryRetail;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.TblPendingOrderMapper;
import com.qjkj.qjcsp.mapper.TblRechargeMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.DateUtils;
import com.qjkj.qjcsp.util.VerifyCode;
import com.qjkj.qjcsp.util.alipay.util.AlipayCore;

/**
 * 支付回调
 * 
 * @author Administrator
 *
 */
@Service
public class OrderBackCallService {

	private static final Logger logger = LoggerFactory.getLogger(OrderBackCallService.class);

	@Autowired
	private TblOrderMapper tblOrderMapper;
	
	@Autowired
	private TblPendingOrderMapper tblPendingOrderMapper;
	
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	
	
	@Autowired
	private TblRechargeMapper tblRechargeMapper;
	
	@Autowired
	private TblCustomerMapper tblCustomerMapper;
	
	@Autowired
	private OrderCommonService orderCommonService;
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;

//	@Autowired
//	private TblDayMealsLogService tblDayMealsLogService;
	
	// 银行卡支付
	private static int PAY_MODE_BANKCARD = 0;
	// 支付宝支付
	private static int PAY_MODE_ALI = 1;
	// 微信支付
	private static int PAY_MODE_WEIXIN = 2;
	
	public void notify(String orderNo, String tradeNo, String tradeStatus, String gmtPayment,
			Integer payStyle){
		TblPendingOrder tblPendingOrder=tblPendingOrderMapper.queryPendingOrderByOrderNum(orderNo);
		// PendingTypeNum 4 充值订单
		if(tblPendingOrder!=null && "4".equals(tblPendingOrder.getPendingTypeNum())){//判断是否充值订单
			rechargeNotifyUpdateOrder( orderNo, tradeNo, tradeStatus, gmtPayment,
					 payStyle);
		}else{
			Long orderId = tblOrderMapper.getOrderIdbyOrderNum(orderNo);
			notifyUpdateOrder(orderId, orderNo, tradeNo,  tradeStatus,  gmtPayment,
					payStyle);
		}
		
	}
	
	
	/**
	 * 手机下单，微信回调成功后更新订单
	 * 
	 * @param orderNo
	 * @param tradeNo
	 * @param tradeStatus
	 * @param :total_fee
	 * @param :seller_email
	 * @param :buyer_email
	 */
	@Transactional(rollbackFor=Exception.class)
	public void notifyUpdateOrder(Long orderId, String orderNo, String tradeNo, String tradeStatus, String gmtPayment,
			Integer payStyle) {
		
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition(); 
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//		DataSourceTransactionManager txManager = new DataSourceTransactionManager(); 
//		
//		TransactionStatus status = txManager.getTransaction(def);
		try {
			// 若订单为已支付就不要发短信
			TblOrder order = tblOrderMapper.queryOrderByOrderId(orderId);
				if (order != null && order.getOrderStatus().equals(OrderStatus.NO_PAY.value)) {
					/*
					 * * 判断是否已经更新
					 */
					// 支付流水号
					order.setPayNumber(tradeNo);
					// 支付状态
					order.setOrderStatus(OrderStatus.NO_TAKED.value);
					// 支付方式
					boolean isFailed = Boolean.TRUE;
					if (payStyle == PAY_MODE_WEIXIN) {
						order.setPayMode(PAY_MODE_WEIXIN + "");
						try {
							order.setPayTime(new Date());
							isFailed = Boolean.FALSE;
						} catch (Exception e) {
							logger.debug("微信支付回调付款时间转换异常", e);
						} finally {
							if (isFailed) {
								order.setPayTime(new Date());
							}
						}
					} else {
						order.setPayMode(PAY_MODE_ALI + "");
						try {
							order.setPayTime(new Date());
							isFailed = Boolean.FALSE;
						} catch (Exception e) {
							logger.debug("支付宝支付回调付款时间转换异常", e);
						} finally {
							if (isFailed) {
								order.setPayTime(new Date());
							}
						}
					}
					tblOrderMapper.updateByPrimaryKeySelective(order);
					// 删除待付款订单，新增待取餐订单， 更新子订单
					orderCommonService.changePaidOrderToTakenOrder(order);
					//生成一日三餐的红包
					//tblDayMealsLogService.insertDayMealsLog(orderNo);

				}else{
					logger.warn("notifyUpdateOrder 回调方法 orderId ：" +orderId  + " orderNo :"+orderNo + " order status :" +order.getOrderStatus());

				}
		} catch (Exception e) {
//			 txManager.rollback(status);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			 logger.error("回调异常", e);
		}
//		txManager.commit(status);
	}
	@Transactional(rollbackFor=Exception.class)
	private void rechargeNotifyUpdateOrder(String orderNo, String tradeNo, String tradeStatus, String gmtPayment,
			Integer payStyle) {
		// System.out.println("叶建辉+++++"+gmtPayment);
		// 若订单为已支付就不要发短信
		TblRecharge tblRecharge = tblRechargeMapper.selectTblRechargeByOrderNum(orderNo);
		TblCustomer tblCustomer=tblCustomerMapper.selectByPrimaryKey(tblRecharge.getCustomerId());
			if (tblRecharge != null && tblRecharge.getOrderStatus().equals(OrderStatus.NO_PAY.value)) {
				/*
				 * * 判断是否已经更新
				 */
				// 支付流水号
				tblRecharge.setPayOrderNum(tradeNo);
				// 支付金额
				// order.setTotalMoney(BigDecimal.valueOf(Double.valueOf(totalFee)/100));
				// order.setRealMoney(BigDecimal.valueOf(Double.valueOf(totalFee)/100));
				// 支付状态
				tblRecharge.setOrderStatus(OrderStatus.CLEAR.value);
				// 支付方式
				boolean isFailed = Boolean.TRUE;
				if (payStyle == PAY_MODE_WEIXIN) {
					try {
						// 付款时间
						// Date gmtPaymentTime = sdf.parse(gmtPayment);
						// String paytimeTemp = sdf2.format(gmtPaymentTime);
						// Date paytime = sdf2.parse(paytimeTemp);
						tblRecharge.setPayTime(new Date());
						BigDecimal balance=tblCustomer.getCustomerMoney();
						balance=balance.add(tblRecharge.getTotalMoney());
						tblCustomer.setCustomerMoney(balance);
						isFailed = Boolean.FALSE;
					} catch (Exception e) {
						logger.debug("微信支付回调付款时间转换异常", e);
					} finally {
						if (isFailed) {
							tblRecharge.setPayTime(new Date());
						}
					}
				} else {
					try {
						// Date gmtPaymentTime = sdf2.parse(gmtPayment);
						tblRecharge.setPayTime(new Date());
						BigDecimal balance=tblCustomer.getCustomerMoney();
						balance=balance.add(tblRecharge.getTotalMoney());
						tblCustomer.setCustomerMoney(balance);
						// 调用支付宝查询订单的方法
						// Map<String, String> map=AlipayCore.queryPay(tradeNo);
						// 将Map中TradeDate的String 转换成date
						// order.setPayTime(sdf2.parse(map.get("tradeDate")));
						isFailed = Boolean.FALSE;
					} catch (Exception e) {
						logger.debug("支付宝支付回调付款时间转换异常", e);
					} finally {
						if (isFailed) {
							tblRecharge.setPayTime(new Date());
						}
					}
				}
				tblPendingOrderMapper.deletebyOrderNum(orderNo);
				tblRechargeMapper.updateByPrimaryKeySelective(tblRecharge);
				tblCustomerMapper.updateByPrimaryKeySelective(tblCustomer);			
			}
	}

}
