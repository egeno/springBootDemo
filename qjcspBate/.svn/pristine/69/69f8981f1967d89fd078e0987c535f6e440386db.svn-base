package com.qjkj.qjcsp.service.wechatapi;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.OrderRefund;
import com.qjkj.qjcsp.entity.RefundSearch;
import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblOrderRefund;
import com.qjkj.qjcsp.entity.TblOrderRefundLog;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.TblOrderRefundLogMapper;
import com.qjkj.qjcsp.mapper.TblOrderRefundMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class OrderRefundWeChatService {

	@Autowired
	private TblOrderRefundMapper tblOrderRefundMapper;
	@Autowired
	private TblOrderMapper orderMapper;
	@Autowired
	private TblOrderChildMapper childMapper;
	@Autowired
	private TblOrderDetailMapper orderDetailMapper;
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;
	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;
	@Autowired
	private TblCustomerMapper tblCustomerMapper;
	@Autowired
	private TblOrderRefundLogMapper tblOrderRefundLogMapper;

	public List<TblOrderRefund> getCompanyIdAndName() {
		return tblOrderRefundMapper.getCompanyIdAndName();
	}

	public List<OrderRefund> findAllRefundList(RefundSearch params) {
		return tblOrderRefundMapper.findAllRefundList(params);
	}

	public long findRefundCount(RefundSearch refundSearch) {
		return tblOrderRefundMapper.findAllRefundCount(refundSearch);
	}

	public long findAllRefundFinanceCount(RefundSearch refundSearch) {
		return tblOrderRefundMapper.findAllRefundFinanceCount(refundSearch);
	}

	public void orderRefundOperatePass(OrderRefund orderRefund) {
		tblOrderRefundMapper.orderRefundOperatePass(orderRefund);
	}

	public void orderRefundOperateReject(OrderRefund orderRefund) {
		tblOrderRefundMapper.orderRefundOperateReject(orderRefund);
		tblOrderRefundMapper.updateOrderStatus(orderRefund);
	}

	public List<OrderRefund> findAllRefundFinanceList(RefundSearch refundSearch) {
		return tblOrderRefundMapper.findAllRefundFinanceList(refundSearch);
	}

	public void orderRefundFinanceConfirm(OrderRefund orderRefund) {
		tblOrderRefundMapper.orderRefundFinanceConfirm(orderRefund);

		Map<String, Object> map = new HashMap<String, Object>();
		map = tblOrderRefundMapper.selectOrderInfo(orderRefund);
		if (map.get("refundReasonOrderChildStatus").toString().equals(OrderStatus.TIMEOUT.value)) {
			String orderChildId = map.get("orderChildId").toString();
			// 判断是否含有支付方式
			if (map.containsKey("payMode")) {
				if (map.get("payMode").equals("3")) {
					// 修改订单状态，和用户的余额
					// tblOrderRefundMapper.updateOrderInfoAndCustomerBalance(map);
					// //将数据插入到退款订单表中//zanwucibiao
					/* 修改子订单的状态 */
					TblOrderChild tblOrderChild = new TblOrderChild();
					tblOrderChild.setOrderChildStatus(OrderStatus.FINANCE_PASS.value);
					tblOrderChild.setOrderChildId(Long.valueOf(orderChildId));
					tblOrderChildMapper.updateByPrimaryKeySelective(tblOrderChild);
					/* 退款金额添加到用户账号中 */
					TblCustomer tblCustomer = tblCustomerMapper
							.selectByPrimaryKey(Long.valueOf(map.get("customerId").toString()));
					tblCustomer.setCustomerMoney(
							tblCustomer.getCustomerMoney().add(new BigDecimal(map.get("refundMoney").toString())));
					tblCustomerMapper.updateByPrimaryKeySelective(tblCustomer);

					TblOrderRefundLog tblOrderRefundLog = new TblOrderRefundLog();
					tblOrderRefundLog.setCreateTime(new Date());
					tblOrderRefundLog.setCreateUserId(orderRefund.getFinanceUserId());
					tblOrderRefundLog.setCustomerId(Long.valueOf(map.get("customerId").toString()));
					tblOrderRefundLog.setOrderNum(map.get("orderChildNum").toString());
					tblOrderRefundLog.setCustomerMoney(tblCustomer.getCustomerMoney());
					tblOrderRefundLog.setRefundMoney(new BigDecimal(map.get("refundMoney").toString()));
					tblOrderRefundLogMapper.insertSelective(tblOrderRefundLog);
				} else {
					tblOrderRefundMapper.updateOrderStatusSuccess(orderRefund);
				}
			}
		} else if (map.get("refundReasonOrderChildStatus").toString().equals(OrderStatus.NO_TAKED.value)) {
			String orderChildId = map.get("orderChildId").toString();
			if (map.containsKey("payMode")) {
				if (map.get("payMode").equals("3")) {
					/* 修改子订单的状态 */
					TblOrderChild tblOrderChild = new TblOrderChild();
					tblOrderChild.setOrderChildStatus(OrderStatus.FINANCE_PASS.value);
					tblOrderChild.setOrderChildId(Long.valueOf(orderChildId));
					tblOrderChildMapper.updateByPrimaryKeySelective(tblOrderChild);
					/* 退款金额添加到用户账号中 */
					TblCustomer tblCustomer = tblCustomerMapper
							.selectByPrimaryKey(Long.valueOf(map.get("customerId").toString()));
					tblCustomer.setCustomerMoney(
							tblCustomer.getCustomerMoney().add(new BigDecimal(map.get("refundMoney").toString())));
					tblCustomerMapper.updateByPrimaryKeySelective(tblCustomer);

					TblOrderRefundLog tblOrderRefundLog = new TblOrderRefundLog();
					tblOrderRefundLog.setCreateTime(new Date());
					tblOrderRefundLog.setCreateUserId(orderRefund.getFinanceUserId());
					tblOrderRefundLog.setCustomerId(Long.valueOf(map.get("customerId").toString()));
					tblOrderRefundLog.setOrderNum(map.get("orderChildNum").toString());
					tblOrderRefundLog.setCustomerMoney(tblCustomer.getCustomerMoney());
					tblOrderRefundLog.setRefundMoney(new BigDecimal(map.get("refundMoney").toString()));
					tblOrderRefundLogMapper.insertSelective(tblOrderRefundLog);
					// 修改订单状态，和用户的余额
					/*
					 * tblOrderRefundMapper.updateOrderInfoAndCustomerBalance(
					 * map); // //将数据插入到退款订单表中//zanwucibiao
					 * map.put("createTime", orderRefund.getFinanceCheckTime());
					 * map.put("createUserId", orderRefund.getFinanceUserId());
					 */
					// tblOrderRefundMapper.insertTblOrderRefundLog(map);
				} else {
					tblOrderRefundMapper.updateOrderStatusSuccess(orderRefund);
				}
			}
			tblTemporaryRetailMapper.delByOrderChildId(Long.valueOf(orderChildId));
		}

	}

	// 如果因为设备故障需要对谁谁谁发放红包
	public void refundByMachine(OrderRefund orderRefund) {

	}

	/**
	 * 2.3.23. 用户发起订单退款申请
	 * 
	 * @param customerId:用户id
	 *            orderNum:订单号 orderChildNums:子订单号数组 refundComment:退款原因
	 */
	public Map<String, Object> customerOrderRefundApplyWX(JSONObject jsonObject) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		//SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat yyyyMMdddateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 判断参数是否正确
		if (!jsonObject.has("customerId") || !jsonObject.has("orderNum") || !jsonObject.has("orderChildNums")
				|| !jsonObject.has("refundComment")) {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "缺少请求参数");
			return returnContent;
		}
		String customerId = jsonObject.getString("customerId");
		String orderNum = jsonObject.getString("orderNum");
		String refundComment = jsonObject.getString("refundComment");
		// orderChildNums为空字符串表示退款全部，否则退款传过来的订单
		Object orderChildNums = jsonObject.get("orderChildNums");
		if (StringUtils.isAnyBlank(customerId, orderNum, refundComment)) {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "请求参数错误");
			return returnContent;
		}
		Map<String, Object> orderTypeAndOrderId = orderMapper.getOrderTypeAndorderId(Long.parseLong(customerId),
				orderNum);
		// 判断是预订
		if (orderTypeAndOrderId.get("orderType").equals("0")) {
			// 判断APP传递的orderChildNums是否为空字符串，如果是空字符串则通过orderID去查询所有的子订单
			if (orderChildNums.equals("")) {
				List<Map<String, Object>> list = childMapper
						.getOrderChildByOrderId(Long.parseLong(orderTypeAndOrderId.get("orderId").toString()));
				// 循环判断取餐截止时间是否超时
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					//只有不是已超时的订单才判断是否超过预订退款时间
					//if (!map.get("orderChildStatus").equals("4")&&!map.get("orderChildStatus").equals("10"))
					//目前不支持故障退款	
					if (!map.get("orderChildStatus").equals("4"))
					{
						try {
							//获取取餐截止时间
							Date date = dateFormat.parse(map.get("reserveEndTime").toString());
							Date nowDate = new Date();
							if (nowDate.after(date)) {
								returnContent.put("returnCode", "0");
								returnContent.put("returnContent", "已超过预定退款截止时间，不能进行退款");
								return returnContent;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				Long orderId = (Long) orderTypeAndOrderId.get("orderId");
				// 优惠金额
				BigDecimal disMoney = (BigDecimal) orderTypeAndOrderId.get("disMoney");
				List<Map<String, Object>> moneyAndChildid = orderDetailMapper.getAllMoneyByOrderId(orderId);

				// 循环扣除金额
				for (int j = 0; j < moneyAndChildid.size(); j++) {
					Map<String, Object> map = moneyAndChildid.get(j);
					BigDecimal childOrderMoney = (BigDecimal) map.get("refundMoney");
					BigDecimal lastMoney = childOrderMoney.subtract(disMoney);

					if (disMoney.compareTo(BigDecimal.valueOf(0)) > 0) {
						disMoney = disMoney.subtract(childOrderMoney);
						// 如果减去后的优惠金额仍然大于0，那么继续
						if (disMoney.compareTo(BigDecimal.valueOf(0)) > 0) {
							map.put("realRefundMoney", String.valueOf(0));
						} else {
							map.put("realRefundMoney", disMoney.abs());
						}
					} else {
						map.put("realRefundMoney", childOrderMoney);
					}
				}

				// 修改子订单和总订单的状态为待退款5
				// 循环添加数据到退款表
				orderMapper.updateOrderStatusByOrderId(orderId);
				for (int j = 0; j < moneyAndChildid.size(); j++) {
					Map<String, Object> map = moneyAndChildid.get(j);
					Long childorderId = (Long) map.get("childId");
					String childStatus = map.get("childStatus").toString();
					BigDecimal realRefundMoney = new BigDecimal("0");
					if (!map.get("realRefundMoney").equals("0")) {
						realRefundMoney = (BigDecimal) map.get("realRefundMoney");
					}
					childMapper.updateChildOrderStatusByChildorderId(realRefundMoney, childorderId);
					// 查询该子订单是否已存在退款表
					int count = tblOrderRefundMapper.getCountByChildOrderId(childorderId);
					if (count == 0) {
						// 添加数据到退款表
						OrderRefund orderRefund = new OrderRefund();
						orderRefund.setOrderId(Long.parseLong(orderTypeAndOrderId.get("orderId").toString()));
						orderRefund.setOrderNum(orderTypeAndOrderId.get("orderNum").toString());
						orderRefund.setOrderChildId(childorderId);
						orderRefund.setOrderChildNum(map.get("childNum").toString());
						orderRefund.setCustomerId(Long.parseLong(orderTypeAndOrderId.get("customerId").toString()));
						orderRefund.setMobile(orderTypeAndOrderId.get("mobile").toString());
						orderRefund.setMachineId(Long.parseLong(orderTypeAndOrderId.get("machineId").toString()));
						orderRefund.setCompanyId(Long.parseLong(orderTypeAndOrderId.get("companyId").toString()));
						orderRefund.setOrderTime(dateFormat.format(orderTypeAndOrderId.get("orderTime")));
						orderRefund.setRefundApplyTime(dateFormat.format(new Date()));
						orderRefund.setRefundComment(refundComment);
						orderRefund.setRefundReasonOrderChildStatus(childStatus);
						orderRefund.setBusinessCheckResult(Short.valueOf("5"));
						tblOrderRefundMapper.insertSelectiveOne(orderRefund);
					}
				}
			} else {// 只查询传递过来的orderNums子订单
				JSONArray array = jsonObject.getJSONArray("orderChildNums");
				List<String> orderChildNumsList = new ArrayList<String>();
				for (int i = 0; i < array.size(); i++) {
					String orderChildNum = JSONObject.fromObject(array.get(i)).getString("orderChildNum");
					orderChildNumsList.add(orderChildNum);
				}
				List<Map<String, Object>> list = childMapper.getOrderChildByOrderNum(orderChildNumsList);
				// 循环判断取餐截止时间是否超时
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = list.get(i);
					//只有不是已超时的订单才判断是否超过预订退款时间
					//if (!map.get("orderChildStatus").equals("4")&&!map.get("orderChildStatus").equals("10"))
					//目前不支持故障退款	
					if (!map.get("orderChildStatus").equals("4"))
					{
						try {
							Date date = dateFormat.parse(map.get("reserveEndTime").toString());
							Date nowDate = new Date();
							if (nowDate.after(date)) {
								returnContent.put("returnCode", "0");
								returnContent.put("returnContent", "已超过预定退款截止时间，不能进行退款");
								return returnContent;
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}

				List<Map<String, Object>> moneyAndChildid = orderDetailMapper
						.getAllMoneyByChildOrderId(orderChildNumsList);
				// 优惠金额
				BigDecimal disMoney = (BigDecimal) orderTypeAndOrderId.get("disMoney");
				// 循环扣除金额
				for (int j = 0; j < moneyAndChildid.size(); j++) {
					Map<String, Object> map = moneyAndChildid.get(j);
					BigDecimal childOrderMoney = (BigDecimal) map.get("refundMoney");
					BigDecimal lastMoney = childOrderMoney.subtract(disMoney);

					if (disMoney.compareTo(BigDecimal.valueOf(0)) > 0) {
						disMoney = disMoney.subtract(childOrderMoney);
						// 如果减去后的优惠金额仍然大于0，那么继续
						if (disMoney.compareTo(BigDecimal.valueOf(0)) > 0) {
							map.put("realRefundMoney", String.valueOf(0));
						} else {
							map.put("realRefundMoney", disMoney.abs());
						}
					} else {
						map.put("realRefundMoney", childOrderMoney);
					}
				}

				for (int j = 0; j < moneyAndChildid.size(); j++) {
					Map<String, Object> map = moneyAndChildid.get(j);
					Long childorderId = (Long) map.get("childId");
					String childStatus = map.get("childStatus").toString();
					BigDecimal realRefundMoney = new BigDecimal("0");
					if (!map.get("realRefundMoney").equals("0")) {
						realRefundMoney = (BigDecimal) map.get("realRefundMoney");
					}
					childMapper.updateChildOrderStatusByChildorderId(realRefundMoney, childorderId);
					// 查询该子订单是否已存在退款表
					int count = tblOrderRefundMapper.getCountByChildOrderId(childorderId);
					if (count == 0) {
						// 添加数据到退款表
						OrderRefund orderRefund = new OrderRefund();
						orderRefund.setOrderId(Long.parseLong(orderTypeAndOrderId.get("orderId").toString()));
						orderRefund.setOrderNum(orderTypeAndOrderId.get("orderNum").toString());
						orderRefund.setOrderChildId(childorderId);
						orderRefund.setOrderChildNum(map.get("childNum").toString());
						orderRefund.setCustomerId(Long.parseLong(orderTypeAndOrderId.get("customerId").toString()));
						orderRefund.setMobile(orderTypeAndOrderId.get("mobile").toString());
						orderRefund.setMachineId(Long.parseLong(orderTypeAndOrderId.get("machineId").toString()));
						orderRefund.setCompanyId(Long.parseLong(orderTypeAndOrderId.get("companyId").toString()));
						orderRefund.setOrderTime(dateFormat.format(orderTypeAndOrderId.get("orderTime")));
						orderRefund.setRefundApplyTime(dateFormat.format(new Date()));
						orderRefund.setRefundComment(refundComment);
						orderRefund.setRefundReasonOrderChildStatus(childStatus);
						orderRefund.setBusinessCheckResult(Short.valueOf("5"));
						tblOrderRefundMapper.insertSelectiveOne(orderRefund);
					}
				}
			}
		// 判断是否是零售   orderType为1就是零售
		} else if (orderTypeAndOrderId.get("orderType").equals("1")) {
			List<Map<String, Object>> list = childMapper
					.getOrderChildByOrderId(Long.parseLong(orderTypeAndOrderId.get("orderId").toString()));
			if (list.size() > 0) {
				Map<String, Object> map = list.get(0);
				Date date = (Date) map.get("endDate");
				Date nowDate = new Date();
				// 判断取餐时间是否超时
				if (nowDate.before(date)) {
					returnContent.put("returnCode", "0");
					returnContent.put("returnContent", "取餐时间未截止，不能进行退款");
					return returnContent;
				} else {
					Long orderId = (Long) orderTypeAndOrderId.get("orderId");
					// 优惠金额
					BigDecimal disMoney = (BigDecimal) orderTypeAndOrderId.get("disMoney");
					List<Map<String, Object>> moneyAndChildid = orderDetailMapper.getAllMoneyByOrderId(orderId);

					// 循环扣除金额
					for (int j = 0; j < moneyAndChildid.size(); j++) {
						Map<String, Object> map1 = moneyAndChildid.get(j);
						BigDecimal childOrderMoney = (BigDecimal) map1.get("refundMoney");
						BigDecimal lastMoney = childOrderMoney.subtract(disMoney);

						if (disMoney.compareTo(BigDecimal.valueOf(0)) > 0) {
							disMoney = disMoney.subtract(childOrderMoney);
							// 如果减去后的优惠金额仍然大于0，那么继续
							if (disMoney.compareTo(BigDecimal.valueOf(0)) > 0) {
								map1.put("realRefundMoney", String.valueOf(0));
							} else {
								map1.put("realRefundMoney", disMoney.abs());
							}
						} else {
							map1.put("realRefundMoney", childOrderMoney);
						}
					}
					// 修改子订单状态的退款金额
					Map<String, Object> map2 = moneyAndChildid.get(0);
					BigDecimal realRefundMoney = new BigDecimal("0");
					if (!map2.get("realRefundMoney").equals("0")) {
						realRefundMoney = (BigDecimal) map2.get("realRefundMoney");
					}
					Long childorderId = (Long) map2.get("childId");
					String childStatus = map2.get("childStatus").toString();
					// 修改子订单和总订单的状态为待退款5
					orderMapper.updateOrderStatusByOrderId(orderId);
					childMapper.updateChildOrderStatusByChildorderId(realRefundMoney, childorderId);
					// 查询该子订单是否已存在退款表
					int count = tblOrderRefundMapper.getCountByChildOrderId(childorderId);
					if (count == 0) {
						// 添加数据到退款表
						OrderRefund orderRefund = new OrderRefund();
						orderRefund.setOrderId(Long.parseLong(orderTypeAndOrderId.get("orderId").toString()));
						orderRefund.setOrderNum(orderTypeAndOrderId.get("orderNum").toString());
						orderRefund.setOrderChildId(childorderId);
						orderRefund.setOrderChildNum(map2.get("childNum").toString());
						orderRefund.setCustomerId(Long.parseLong(orderTypeAndOrderId.get("customerId").toString()));
						orderRefund.setMobile(orderTypeAndOrderId.get("mobile").toString());
						orderRefund.setMachineId(Long.parseLong(orderTypeAndOrderId.get("machineId").toString()));
						orderRefund.setCompanyId(Long.parseLong(orderTypeAndOrderId.get("companyId").toString()));
						orderRefund.setOrderTime(dateFormat.format(orderTypeAndOrderId.get("orderTime")));
						orderRefund.setRefundApplyTime(dateFormat.format(new Date()));
						orderRefund.setRefundComment(refundComment);
						orderRefund.setRefundReasonOrderChildStatus(childStatus);
						orderRefund.setBusinessCheckResult(Short.valueOf("5"));
						tblOrderRefundMapper.insertSelectiveOne(orderRefund);
					}
				}
			}
		}
		returnContent.put("returnCode", "1");
		returnContent.put("returnContent", "申请退款成功");
		return returnContent;
	}

	/**
	 * 运维导出
	 * 
	 * @author yehx
	 * @date 2016年1月25日
	 * @return
	 *
	 */
	public List<Map<String, Object>> findAllExportRefundBusinessList(RefundSearch refundSearch) {

		return tblOrderRefundMapper.findAllExportRefundBusinessList(refundSearch);

	};

	/**
	 * 财务导出
	 * 
	 * @author yehx
	 * @date 2016年1月25日
	 * @param refundBussinessSearch
	 * @return
	 *
	 */
	public List<Map<String, Object>> findAllExportRefundFinanceList(RefundSearch refundSearch) {
		return tblOrderRefundMapper.findAllExportRefundFinanceList(refundSearch);
	}

}
