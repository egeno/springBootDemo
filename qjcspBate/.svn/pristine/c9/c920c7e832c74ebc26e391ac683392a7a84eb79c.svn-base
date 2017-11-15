package com.qjkj.qjcsp.service.order.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblPendingOrder;
import com.qjkj.qjcsp.entity.TblRecharge;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.mapper.TblPendingOrderMapper;
import com.qjkj.qjcsp.mapper.TblRechargeMapper;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.alipay.util.AlipayCore;
import com.qjkj.qjcsp.util.weixinpay.util.WeiXinpayCore;

@Service
public class OrderInSuspenseService {
    private static Logger logger = LoggerFactory.getLogger(OrderInSuspenseService.class);
    @Autowired
    private TblPendingOrderMapper tblPendingOrderMapper;

    @Autowired
    private TblRechargeMapper tblRechargeMapper;

    @Autowired
    private TblCustomerMapper tblCustomerMapper;

    @Autowired
    private OrderCommonService orderCommonService;

    @Autowired
    private OrderBackCallService orderBackCallService;


    /**
     * 定时轮询
     */
    public void pendingOrderInSuspense() {
        List<TblPendingOrder> tblPendingOrderList = tblPendingOrderMapper.getAllTblPendingOrderList();
        /*遍历待处理订单列表*/
        for (TblPendingOrder tpo : tblPendingOrderList) {
            if (StringUtils.equals(tpo.getPendingTypeNum(), "1")) {
				/*1：待付款订单*/
                pollingObligationOrder(tpo);
            } else if (StringUtils.equals(tpo.getPendingTypeNum(), "2")) {
				/*2：已取消待退款订单*/

            } else if (StringUtils.equals(tpo.getPendingTypeNum(), "3")) {
				/*3 ：已付款待取餐订单*/
                pollingTakenOrder(tpo);
            } else if (StringUtils.equals(tpo.getPendingTypeNum(), "4")) {
                pollingRechargeOrder(tpo);
            }
        }
    }

    /**
     * 轮询待付款订单
     *
     * @param tpo
     */
    private void pollingObligationOrder(TblPendingOrder tpo) {
		/*当前时间与支付截止时间比较结果*/
        boolean isBefore = orderCommonService.isBeforeLastPayTime(tpo.getLastPayTime());
        if (!isBefore) {
            logger.warn("订单超时 ： " + tpo.getOrderNum() +" lastPayTime :" + tpo.getLastPayTime() + " system time :" + new Date() );
        }
        cancelObligationOrder(tpo, isBefore);
    }

    /**
     * 轮询待取餐订单
     *
     * @param tpo
     */
    public void pollingTakenOrder(TblPendingOrder tpo) {
		/*判断下单方式，0：设备端，1、2、3：其他*/
//		if (!StringUtils.equals(tpo.getModeNum(), "0" )){
//			/*当前时间与取餐截止时间比较结果*/
//			if (orderCommonService.isBeforeEndTime(tpo.getEndTime()) == Boolean.FALSE){
//				cancelTakenTimeoutOrder(tpo);
//			}
//		}
        if (tpo.getEndTime() != null) {
			/*当前时间与取餐截止时间比较结果*/
            if (orderCommonService.isBeforeEndTime(tpo.getEndTime()) == Boolean.FALSE) {
                cancelTakenTimeoutOrder(tpo);
            }
        }
    }

    /**
     * 轮询余额充值订单
     *
     * @param tblPendingOrder
     */
    @Transactional(rollbackFor = Exception.class)
    private void pollingRechargeOrder(TblPendingOrder tblPendingOrder) {
		/* 当前时间与支付截止时间比较结果 */
        boolean isBefore = orderCommonService.isBeforeLastPayTime(tblPendingOrder.getLastPayTime());
        //TblRecharge tblRecharge = tblRechargeMapper.findTblRechargeByRechargeIdForUpdate(tblPendingOrder.getOrderId());
        TblRecharge tblRecharge = tblRechargeMapper.selectByPrimaryKey(tblPendingOrder.getOrderId());
        BigDecimal realMoney = tblRecharge.getRealMoney().multiply(new BigDecimal(100));
        if (isBefore == true && tblRecharge.getOrderStatus().equals(OrderStatus.NO_PAY.value)) {
            if (tblRecharge.getModeNum().equals("1")) {
                Map<String, String> map = AlipayCore.queryPay(tblRecharge.getOrderNum());
                if (StringUtils.equals(map.get("flag"), "SUCCESS")) {
                    if (new BigDecimal(map.get("total_amount")).compareTo(tblRecharge.getRealMoney()) == 0) {
                        tblRecharge.setPayOrderNum(map.get("tradeNo"));
                        updateTblCustomerAndTblRecharge(map, tblRecharge, tblPendingOrder);
                    }
                }
            } else {
                Map<String, String> map = WeiXinpayCore.queryPay(tblRecharge.getOrderNum(),
                        Constants.COMMIT_ORDER_APP);
                if (StringUtils.equals(map.get("flag"), "SUCCESS")) {
                    if (new BigDecimal(map.get("cash_fee")).compareTo(realMoney) == 0) {
                        tblRecharge.setPayOrderNum(map.get("tradeNo"));
                        updateTblCustomerAndTblRecharge(map, tblRecharge, tblPendingOrder);
                    }
                }
            }

        } else {
            tblPendingOrderMapper.deleteByPrimaryKey(tblPendingOrder.getPendingId());
            // TblRecharge
            // tblRecharge=tblRechargeMapper.selectByPrimaryKey(tblPendingOrder.getOrderId());
            tblRecharge.setOrderStatus(OrderStatus.CANCEL.value);
            tblRechargeMapper.updateByPrimaryKeySelective(tblRecharge);

        }
    }

    /**
     * 当支付宝或者微信返回支付成功时
     * 修改TblCustomer表的余额字段
     * 修改TblRecharge表订单order_status字段
     * 删除待处理订单表的数据
     */
    private void updateTblCustomerAndTblRecharge(Map<String, String> map, TblRecharge tblRecharge,
                                                 TblPendingOrder tblPendingOrder) {
        tblRecharge.setPayTime(new Date());
        tblRecharge.setOrderStatus(OrderStatus.CLEAR.value);
        tblRechargeMapper.updateByPrimaryKeySelective(tblRecharge);
        tblPendingOrderMapper.deleteByPrimaryKey(tblPendingOrder.getPendingId());
        TblCustomer tblCustomer = tblCustomerMapper.selectByPrimaryKey(tblRecharge.getCustomerId());
        BigDecimal balance = tblCustomer.getCustomerMoney();
        balance = balance.add(tblRecharge.getTotalMoney());
        tblCustomer.setCustomerMoney(balance);
        tblCustomerMapper.updateByPrimaryKeySelective(tblCustomer);
    }

    /**
     * 待付款订单处理
     *
     * @param tpo
     * @param isBefore
     */
    private void cancelObligationOrder(TblPendingOrder tpo, boolean isBefore) {
		/*查询支付平台中该订单号的支付结果*/
        boolean isSuccessful = Boolean.FALSE;
        Map<String, String> alipayResult = AlipayCore.queryPay(tpo.getOrderNum());
		/*该订单在支付宝中已支付*/
        if (alipayResult != null && StringUtils.equals(alipayResult.get("flag"), Constants.SYMBOL_SUCCESS)) {
            prepayOrderAlipay(tpo.getOrderId(), isBefore, tpo.getOrderNum(), alipayResult);
            isSuccessful = Boolean.TRUE;
        } else {
            Map<String, String> weixinpayResult = WeiXinpayCore.queryPay(tpo.getOrderNum(), tpo.getModeNum());
			/*该订单在微信中已支付*/
            if (weixinpayResult != null && StringUtils.equals(weixinpayResult.get("flag"), Constants.SYMBOL_SUCCESS)) {
                prepayOrderWeiXinpay(tpo.getOrderId(), isBefore, tpo.getOrderNum(), weixinpayResult);
                isSuccessful = Boolean.TRUE;
            }
        }
        //logger.warn("改订单改变状态前:"+"支付平台中该订单号的支付结果"+isSuccessful+"当前时间是否在支付截止时间之前"+isBefore);
		/*该订单不为已支付，且当前时间大于支付截止时间*/
        if (isSuccessful == Boolean.FALSE && isBefore == Boolean.FALSE) {
        	 logger.warn("轮询订单取消 ： " + tpo.getOrderNum() +"orderTime:"+tpo.getOrderTime()+" lastPayTime :" + tpo.getLastPayTime() + " system time :" + new Date() +"pengdingTypeNum:"+tpo.getPayOrderNum());
            logger.warn("cancelObligationOrder suc :" + isSuccessful + " isBefore :" + isBefore + " TblPendingOrder  orderNum:" +tpo.getOrderNum() );
			/*待付款订单类型，0：预定 1：零售*/
            if (StringUtils.equals(tpo.getOrderType(), "0")) {
                orderCommonService.cancelNoPaidOrder(tpo.getOrderId());
            } else if (StringUtils.equals(tpo.getOrderType(), "1")) {
                orderCommonService.cancelNoPaidOrderAndUnlockItem(tpo.getOrderId());
            }
        }
    }

    /**
     * 取餐超时订单处理
     *
     * @param tpo
     */
    private void cancelTakenTimeoutOrder(TblPendingOrder tpo) {
        orderCommonService.updateTakenTimeoutOrderAndUnlockItem(tpo.getOrderChildId());
    }

    /**
     * 支付宝待付款订单处理
     *
     * @param isBefore
     * @param orderNum
     * @param alipayResult
     */
    private void prepayOrderAlipay(Long orderId, boolean isBefore, String orderNum, Map<String, String> alipayResult) {
		/*当前时间小于支付截止时间*/
        if (isBefore == Boolean.TRUE) {
            orderBackCallService.notifyUpdateOrder(orderId, orderNum, alipayResult.get("tradeNo"), null, alipayResult.get("tradeDate"), 1);
        } else {
			/*当前时间大于支付截止时间(可能会修改)*/
            orderBackCallService.notifyUpdateOrder(orderId, orderNum, alipayResult.get("tradeNo"), null, alipayResult.get("tradeDate"), 1);
        }
    }

    /**
     * 微信待付款订单处理
     *
     * @param isBefore
     * @param orderNum
     * @param weixinpayResult
     */
    private void prepayOrderWeiXinpay(Long orderId, boolean isBefore, String orderNum, Map<String, String> weixinpayResult) {
		/*当前时间小于支付截止时间*/
        if (isBefore == Boolean.TRUE) {
            orderBackCallService.notifyUpdateOrder(orderId, orderNum, weixinpayResult.get("tradeNo"), null, weixinpayResult.get("tradeDate"), 2);
        } else {
			/*当前时间大于支付截止时间(可能会修改)*/
            orderBackCallService.notifyUpdateOrder(orderId, orderNum, weixinpayResult.get("tradeNo"), null, weixinpayResult.get("tradeDate"), 2);
        }
    }

    /**
     * 已取消待退款订单处理
     * @param isBefore
     * @param orderNum
     */
/*	public void refundOrder(Long orderId){
		TblOrder tblOrder = tblOrderMapper.selectByPrimaryKey(orderId);
		1：支付宝，2：微信支付
		if (StringUtils.equals(tblOrder.getPayMode(), "1")){
			alipayOrderCancel(tblOrder);
		}else if (StringUtils.equals(tblOrder.getPayMode(), "2")){
			weixinpayOrderCancel(tblOrder);
		}
	}*/

    /**
     * 支付宝退款
     * @param tblOrder
     */
/*	private void alipayOrderCancel(TblOrder tblOrder){
		Map<String, String> result = AlipayCore.cancelOrder(tblOrder.getOrderNum());
		if (result != null && StringUtils.equals(result.get("flag"), Constants.SYMBOL_SUCCESS)){
			orderCommonService.refundOrderAndUnlockItem(tblOrder.getDeviceCode(), tblOrder.getOrderNum());
		}
		
	}*/

    /**
     * 微信退款
     * @param tblOrder
     */
/*	private void weixinpayOrderCancel(TblOrder tblOrder){
		if (WeiXinpayCore.refund(tblOrder.getOrderNum(), tblOrder.getRealMoney()) == Boolean.TRUE){
			orderCommonService.refundOrderAndUnlockItem(tblOrder.getDeviceCode(), tblOrder.getOrderNum());
		}
	}*/

}
