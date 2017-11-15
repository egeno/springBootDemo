package com.qjkj.qjcsp.service.goodsmachine;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.OrderRefund;
import com.qjkj.qjcsp.entity.RefundSearch;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblOrderDetail;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.GoodsMachineMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.TblOrderRefundMapper;

import net.sf.json.JSONObject;

@Service
@Transactional
public class GoodsMachineService {

	public static Logger logger = LoggerFactory.getLogger(GoodsMachineService.class);
	@Autowired
	private GoodsMachineMapper goodsMachineMapper;

	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;

	@Autowired
	private TblOrderMapper tblOrderMapper;

	@Autowired
	private TblOrderDetailMapper TblOrderDetailMapper;

	@Autowired
	private TblOrderRefundMapper tblOrderRefundMapper;

	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;

	@Autowired
	private BasicsUsersMapper basicsUsersMapper;

	@Autowired
	private BasicsMachineMapper basicsMachineMapper;

	@Autowired
	private TblItemDetailMapper itemDetailMapper;

	public Map<String, Object> getGoodsCommentsWithContentByMachineId(Long machineId) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		Long comGrade = goodsMachineMapper.getMachineGoodsAvgSore(machineId);
		List<Map<String, Object>> goodsCommentList = goodsMachineMapper
				.getGoodsCommentsWithContentByMachineId(machineId);

		Map<String, Object> map = new HashMap<String, Object>();
		if (goodsCommentList.size() > 0) {
			map.put("comGrade", String.valueOf(comGrade));
			map.put("goodsCommentList", goodsCommentList);
			returnJson.put("returnCode", "1");
			returnJson.put("returnContent", map);

			return returnJson;
		} else {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "请求参数是异常的");
			return returnJson;
		}
	}

	public Map<String, Object> getGoodsDetailByGoodsId(String machineId, String goodsId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		BasicsGoods basicsGoods = basicsGoodsMapper.selectByPrimaryKey(Long.valueOf(goodsId));

		// 生成当前时间
		// Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date());
		// Date endTime = cal.getTime();

		int num = goodsMachineMapper.selectEffectiveNum(Long.valueOf(goodsId));

		Object score = goodsMachineMapper.selectEvaluateScore(Long.valueOf(machineId), Long.valueOf(goodsId));

		Map<String, String> returnContent = new HashMap<String, String>();
		// 新增
		if (basicsGoods != null) {
			returnContent.put("goodsId", String.valueOf(basicsGoods.getGoodsId()));
			returnContent.put("goodsName", basicsGoods.getGoodsName());
			returnContent.put("price", String.valueOf(basicsGoods.getRetailPrice()));
			returnContent.put("goodsIcon", basicsGoods.getGoodsIcon());
			returnContent.put("comGrade", String.valueOf(score));
			returnContent.put("effectCapacity", String.valueOf(num));
			returnData.put("returnCode", "1");
			returnData.put("returnContent", returnContent);
			return returnData;
		}
		// 未找到菜品
		else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "提交失败");
			return returnData;
		}

	}

	// 获取退款明细
	public Map<String, Object> customerOrderRefundDetail(String orderNum) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		TblOrder tblOrder = tblOrderMapper.selectByOrderNum(orderNum);
		// 优惠金额
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		BigDecimal discountMoney = tblOrder.getDiscountMoney();

		// 实际金额
		BigDecimal realRealRefundMoney = BigDecimal.valueOf(0.0);
		// 已经排序过后的tblorderchild
		List<TblOrderChild> tblOrderChilds = goodsMachineMapper.selectByOrderIdOrderByBeginTime(tblOrder.getOrderId());
		// 如果订单类型是预定的
		// if (tblOrder.getOrderType().equals("0")) {

		Date date = new Date();
		List<Map> orderChildNums = new ArrayList<Map>();
		List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		for (TblOrderChild tblOrderChild : tblOrderChilds) {
			Map<String, Object> map4 = new HashMap<String, Object>();
			List<Map<String, Object>> itemList2 = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			// 先把折扣价格去掉 算出实际的金额
			List<TblOrderDetail> tblOrderDetails = TblOrderDetailMapper
					.selectByOrderChildId(tblOrderChild.getOrderChildId());
			// 计算出tblOrderChild的理论价格
			BigDecimal childPrice = BigDecimal.valueOf(0.0);
			List<Map<String, Object>> orderDetails = new ArrayList<Map<String, Object>>();

			for (TblOrderDetail tblOrderDetail : tblOrderDetails) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				// childPrice = childPrice
				// .add(tblOrderDetail.getPrice().multiply(BigDecimal.valueOf(tblOrderDetail.getGoodsNum())));
				childPrice = childPrice.add(tblOrderDetail.getPrice());
				// 比较当前时间是否晚于最后退款时间
				map2.put("goodsId", tblOrderDetail.getGoodsId().toString());
				map2.put("goodsName", tblOrderDetail.getGoodsName().toString());
				// BigDecimal goodsPrice = tblOrderDetail.getPrice()
				// .multiply(BigDecimal.valueOf(tblOrderDetail.getGoodsNum()));
				BigDecimal goodsPrice = tblOrderDetail.getPrice();
				map2.put("itemFee", goodsPrice.toString());
				map2.put("dishAreaNum", tblOrderDetail.getGoodsNum().toString());
				orderDetails.add(map2);
			}
			map.put("orderDetails", orderDetails);
			// 理论价格已经算出，用优惠金额减去理论价格
			if (discountMoney.compareTo(BigDecimal.valueOf(0)) > 0) {
				discountMoney = discountMoney.subtract(childPrice);
				// 如果减去后的优惠金额仍然大于0，那么继续
				if (discountMoney.compareTo(BigDecimal.valueOf(0)) > 0) {
					map.put("realRefundMoney", String.valueOf(0));
					// realRealRefundMoney =
					// realRealRefundMoney.add(BigDecimal.valueOf(0.0));
				} else {
					map.put("realRefundMoney", discountMoney.abs().toString());
					// realRealRefundMoney =
					// realRealRefundMoney.add(discountMoney.abs());
				}
			} else {
				map.put("realRefundMoney", childPrice.toString());
				// realRealRefundMoney =
				// realRealRefundMoney.add(childPrice);
			}
			map.put("orderChildId", tblOrderChild.getOrderChildId().toString());
			map.put("orderChildNum", tblOrderChild.getOrderChildNum());
			map.put("takenStartTime", sdf.format(tblOrderChild.getBeginTime()));
			RefundSearch refundSearch = new RefundSearch();
			refundSearch.setOrderNum(orderNum);
			refundSearch.setLimit(0);
			refundSearch.setOffset(10);
			List<OrderRefund> orderRefunds = tblOrderRefundMapper.findAllRefundList(refundSearch);
			//
			int i = 0;
			map.put("orderStatus", tblOrderChild.getOrderChildStatus());
			Long areaModelId = tblOrderChild.getAreaModelId();

			BasicsAreaModel basicsAreaModel = basicsAreaModelMapper.selectByPrimaryKey(areaModelId);

			map.put("areaModelName", basicsAreaModel.getAreaModelName());

			// 判断是否有退款时间，退款状态
			for (OrderRefund orderRefund : orderRefunds) {
				if (orderRefund.getOrderChildId().equals(tblOrderChild.getOrderChildId())) {
					if (StringUtils.isNotBlank(orderRefund.getRefundApplyTime())
							&& StringUtils.isNotEmpty(orderRefund.getRefundApplyTime())) {
						map.put("refundTime", orderRefund.getFinanceCheckTime());
					} else {
						map.put("refundTime", "");
					}
					if (StringUtils.isNotBlank(orderRefund.getRefundStatus())
							&& StringUtils.isNotEmpty(orderRefund.getRefundStatus())) {
						map.put("refundStatus", orderRefund.getRefundStatus());
					} else {
						map.put("refundStatus", "");
					}
					// if
					// (StringUtils.isNotBlank(orderRefund.getFinanceCheckComment())
					// &&
					// StringUtils.isNotEmpty(orderRefund.getFinanceCheckComment()))
					// {
					// map.put("refundComment",
					// orderRefund.getFinanceCheckComment());
					// } else {
					// map.put("refundComment", "");
					// }
					i++;

				}
			}
			if (i == 0) {
				map.put("refundTime", "");
				map.put("refundStatus", "");
				// map.put("refundComment", "");
			}
			// 只有满足可退款条件的子订单才会被添加到list中

//			if (tblOrderChild.getOrderChildStatus().equals("1") || tblOrderChild.getOrderChildStatus().equals("4")||tblOrderChild.getOrderChildStatus().equals("10"))
// 目前不支持故障退款
			if (tblOrderChild.getOrderChildStatus().equals("1") || tblOrderChild.getOrderChildStatus().equals("4"))
			{
				Map<String, String> info = itemDetailMapper
						.selectBuyNumAndReplenishmentNum(tblOrderChild.getOrderChildNum());
				Integer count = Integer.parseInt(info.get("bNum").toString());
				if (tblOrder.getOrderType().equals("0")) {
					//判断是否没有补货
					if (count == 0) {
						if (tblOrderChild.getOrderPreissueLastRefundTime().after(date)) {
							realRealRefundMoney = realRealRefundMoney
									.add(BigDecimal.valueOf(Double.valueOf((map.get("realRefundMoney").toString()))));
							itemList.add(map);
							map3.put("itemList", itemList);
							orderChildNums.add(map3);
						} else {
//							if (tblOrderChild.getOrderChildStatus().equals("1") || tblOrderChild.getOrderChildStatus().equals("4")||tblOrderChild.getOrderChildStatus().equals("10"))
							// 目前不支持故障退款
							if (tblOrderChild.getOrderChildStatus().equals("1") || tblOrderChild.getOrderChildStatus().equals("4")) 
							{
								itemList2.add(map);
								map4.put("itemList", itemList2);
								orderChildNums.add(map4);
							}
						}
					}
				} else {
//if (tblOrderChild.getOrderChildStatus().equals("1") || tblOrderChild.getOrderChildStatus().equals("4")||tblOrderChild.getOrderChildStatus().equals("10"))
					// 目前不支持故障退款
					if (tblOrderChild.getOrderChildStatus().equals("1") || tblOrderChild.getOrderChildStatus().equals("4"))
					{
						realRealRefundMoney = realRealRefundMoney
								.add(BigDecimal.valueOf(Double.valueOf((map.get("realRefundMoney").toString()))));
						itemList2.add(map);
						map4.put("itemList", itemList2);
						orderChildNums.add(map4);
					}
				}
			}
		}
		returnContent.put("orderId", tblOrder.getOrderId().toString());
		returnContent.put("orderNum", tblOrder.getOrderNum());
		returnContent.put("orderType", tblOrder.getOrderType());
		returnContent.put("realRealRefundMoney", realRealRefundMoney.toString());
		returnContent.put("orderChildNums", orderChildNums);
		return returnContent;

	}

	public Map<String, Object> getGoodsCommentsWithContentByPGM(Long machineId, Long goodsId) {
		Map<String, Object> returnJson = new HashMap<String, Object>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("machineId", machineId);
		params.put("goodsId", goodsId);
		Long gradeCount = goodsMachineMapper.getGoodsCommentsWithContentByPGM(params);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gradeCount", gradeCount.toString());
		List<Map<String, Object>> goodsCommentList = goodsMachineMapper
				.getGoodsCommentsWithMachineIdGoodsIdHasComment(params);
		map.put("goodsCommentList", goodsCommentList);
		returnJson.put("returnCode", "1");
		returnJson.put("returnContent", map);

		return returnJson;

	}

	/**
	 * 获取清货
	 * 
	 * @param res
	 * @return
	 */
	public Map<String, Object> getGoodsClear(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (res.has("userId") && res.has("specialRoleNum")
				&& StringUtils.isNoneBlank(res.getString("userId"), res.getString("specialRoleNum"))) {
			Long userId = res.getLong("userId");
			String specialRoleNum = res.getString("specialRoleNum");

			returnData = getMachineGoodsList(userId, specialRoleNum);
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}

		return returnData;
	}

	/**
	 * 获取设备以及商品列表
	 * 
	 * @param userId
	 * @param specialRoleNum
	 * @return
	 */
	private Map<String, Object> getMachineGoodsList(Long userId, String specialRoleNum) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		/* 待优化 */
		/* 1:维修人员 2：补货人员、3：商户、4：众包商 */
		List<Map<String, Object>> machineList = null;
		if (StringUtils.equals(specialRoleNum, "2") || StringUtils.equals(specialRoleNum, "3")
				|| StringUtils.equals(specialRoleNum, "1")) {
			machineList = goodsMachineMapper.queryMachineByUser(userId, specialRoleNum);
		} else if (StringUtils.equals(specialRoleNum, "4")) {
			machineList = goodsMachineMapper.queryMachineByMerSys(userId);
		}
		boolean flag = Boolean.FALSE;
		if (StringUtils.equals(specialRoleNum, "3")) {
			flag = Boolean.TRUE;
		}
		if (machineList != null && machineList.size() > 0) {
			List<Map<String, Object>> returnContent = new ArrayList<Map<String, Object>>();
			/* 遍历设备列表 */
			for (Map<String, Object> machine : machineList) {
				Map<String, Object> content = new HashMap<String, Object>();
				List<Map<String, Object>> goodsList = null;
				if (flag) {
					/* 商户 */
					goodsList = goodsMachineMapper.queryGoodsListByMer(userId, (Long) machine.get("machineId"));
				} else {
					/* 维修人员,补货人员、众包商 */
					goodsList = goodsMachineMapper.queryGoodsList((Long) machine.get("machineId"));
				}
				if (goodsList != null && goodsList.size() > 0) {
					content.put("machineName", machine.get("machineName").toString());
					content.put("address", machine.get("address").toString());
					content.put("goodsList", goodsList);

					returnContent.add(content);
				}
			}
			if (returnContent.size() > 0) {
				returnData.put("returnCode", "1");
				returnData.put("returnContent", returnContent);
			} else {
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "无可清货的商品");
			}
		} else {
			returnData.put("returnCode", "2");
			returnData.put("returnContent", "无关联设备");
		}

		return returnData;
	}

	/**
	 * 2.1.16.	获取设备餐品对应的格子号
	 */
	public Map<String, Object> getGridNums(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String temporaryDate = null; 
		if(res.has("temporaryDate")){
			temporaryDate = res.getString("temporaryDate");
		}
		if (res.has("machineId") && res.has("timeType")
				&& res.has("goodsId")&&res.has("userId")) {
			Long goodsId = res.getLong("goodsId");
			Long machineId = res.getLong("machineId");
			Long timeType = res.getLong("timeType");
			Long userId = res.getLong("userId");
			BasicsUsers basicsUsers = basicsUsersMapper.selectByPrimaryKey(Long.valueOf(userId));
			List<Map<String, String>> returnList = new ArrayList<Map<String,String>>();
			returnList = goodsMachineMapper.GridNums(goodsId,machineId,timeType,basicsUsers.getCompanyId());
			/*List<Map<String, Object>> returnList = new ArrayList<Map<String,Object>>();
			returnList = goodsMachineMapper.GridNums(goodsId,machineId,areaModelId);*/
			returnData.put("returnCode", "1");
			returnData.put("returnContent", returnList);
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}
		return returnData;
	}
	/**
	 * 2.1.12.未补货
	 */
	public Map<String, Object> getGoodsReplenishment(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (res.has("userId") && res.has("specialRoleNum") && res.has("timeType")
				&& StringUtils.isNoneBlank(res.getString("userId"), res.getString("specialRoleNum"))) {
			Long userId = res.getLong("userId");
			Long specialRoleNum = res.getLong("specialRoleNum");
			String timeType = res.getString("timeType");
			// 获取用户的相关数据
			BasicsUsers basicsUsers = basicsUsersMapper.selectByPrimaryKey(userId);
			// 获取属于用户的设备List
			List<Map<String, Object>> machineList = basicsMachineMapper
					.findMachineListByCompanyId(basicsUsers.getCompanyId(), userId, specialRoleNum);
			// 获取设备信息
			List<Map<String, Object>> machineInfo = goodsMachineMapper.getMachineInfo(basicsUsers.getCompanyId(),
					specialRoleNum, basicsUsers.getUserName(), machineList);
			for (int i = 0; i < machineInfo.size(); i++) {
				Map<String, Object> map = machineInfo.get(i);
				// 获取设备的商品信息
				List<Map<String, Object>> listInfo = goodsMachineMapper.getGoodsReplenishment(
						Long.parseLong(map.get("machineId").toString()), timeType, specialRoleNum,
						basicsUsers.getUserName());
//				map.remove("machineId");
				map.put("goodsList", listInfo);
			}
			returnData.put("returnCode", "1");
			returnData.put("returnContent", machineInfo);
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}
		return returnData;
	}
}
