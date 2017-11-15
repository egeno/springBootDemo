package com.qjkj.qjcsp.service.wechatapi;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblOrderDetail;
import com.qjkj.qjcsp.entity.TblPendingOrder;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineCellMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.ModelCleanGoodsMapper;
import com.qjkj.qjcsp.mapper.PreissueOrderMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.TblPendingOrderMapper;
import com.qjkj.qjcsp.service.appUser.FirstOrderDiscountService;
import com.qjkj.qjcsp.service.order.common.OrderCommonService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.VerifyCode;
import com.qjkj.qjcsp.util.alipay.util.AlipayCore;
import com.qjkj.qjcsp.util.weixinpay.util.WeiXinpayCore;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
@Transactional
public class OrderCommitWeChatApiService {
	
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	
	@Autowired
	private BasicsMachineCellMapper basicsMachineCellMapper;
	
	@Autowired
	private TblOrderMapper tblOrderMapper; 
	
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;
	
	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;
	
	@Autowired
	private TblItemDetailMapper tblItemDetailMapper;
	
	@Autowired
	private TblPendingOrderMapper tblPendingOrderMapper;
	
	@Autowired
	private FirstOrderDiscountService firstOrderDiscountService;
	
	@Autowired
	private PreissueOrderMapper preissueOrderMapper;
	
	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;
	
	@Autowired
	private OrderCommonService orderCommonService;
	
	@Autowired
	private ModelCleanGoodsMapper modelCleanGoodsMapper;
	/**
	 * 订单提交
	 * 
	 * @param request
	 * @return
	 */
	/*public Map<String, Object> commitOrder(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("returnCode", "0");
		returnData.put("returnContent", "请求参数错误");
		
		//设备硬件id
		String deviceCode = res.getString("deviceCode");
		//总金额
		String totalAmount = res.getString("totalAmount");
		//购买商品id和份数
		JSONArray orderItemList = res.getJSONArray("orderItemList");
		//提交方式 0：设备端提交，1：ios提交，2：安卓提交
		String commitMode = res.getString("commitMode");
		//支付方式 0：闪付支付，1：支付宝支付，2：微信支付
		String payMode = null;
		if (res.has("payMode")){
			payMode = res.getString("payMode");
		}
		//电话号码
		String mobileNum = null;
		if (res.has("mobileNum")){
			mobileNum = res.getString("mobileNum");
		}
		//客户id号
		String customerId = null;
		if (res.has("customerId")){
			customerId = res.getString("customerId");
		}
		//取餐开始时间
		String takenStartTime = null;
		if (res.has("takenStartTime")){
			takenStartTime = res.getString("takenStartTime");
		}
		//取餐截止时间
		String takenEndTime = null;
		if (res.has("takenEndTime")){
			takenEndTime = res.getString("takenEndTime");
		}
		//优惠金额
		String discountMoney = null;
		if (res.has("discountMoney")){
			discountMoney = res.getString("discountMoney");
		}
		
		//获取订单类型
		String orderType = res.getString("orderType");
		//判断是预定还是零售(0表示预定，1表示零售)
		if(StringUtils.equals(Constants.COMMIT_ORDER_DEVICE, orderType)){
			//获取模型Id
			String areaModelId = null;
			if (res.has("areaModelId")){
				areaModelId = res.getString("areaModelId");
			}
			Map<String, Object> returnAPPReserve = createReserveOrder(commitMode, deviceCode, orderItemList, totalAmount, 
					mobileNum, customerId, takenStartTime, takenEndTime, orderType, areaModelId);
			
			returnData.put("returnCode", returnAPPReserve.get("returnCode"));
			returnData.put("returnContent", returnAPPReserve.get("returnContent"));
			return returnData;
		}
		
		if (StringUtils.isNoneBlank(deviceCode, totalAmount, commitMode) && 
				orderItemList != null && orderItemList.size() > 0){
			判断设备端下单或APP端下单
			if (StringUtils.equals(Constants.COMMIT_ORDER_DEVICE, commitMode)){
				设备端
				if (StringUtils.isNoneBlank(payMode)){
					Map<String, Object> returnDeviceData = commitOrderbyDevice(commitMode, deviceCode, orderItemList, totalAmount ,payMode);
					returnData.put("returnCode", returnDeviceData.get("returnCode"));
					returnData.put("returnContent", returnDeviceData.get("returnContent"));
				}
			}else {
				APP端
				if (StringUtils.isNoneBlank(mobileNum, customerId, takenStartTime, takenEndTime)){
					Map<String, Object> returnAPPData = commitOrderbyAPP(commitMode, deviceCode, orderItemList, totalAmount, 
							mobileNum, customerId, takenStartTime, takenEndTime, discountMoney);
					returnData.put("returnCode", returnAPPData.get("returnCode"));
					returnData.put("returnContent", returnAPPData.get("returnContent"));
				}
			}
		}
		
		return returnData;
	}*/

	/**
	 * 设备端提交订单
	 * @param deviceCode
	 * @param orderItemList
	 * @param totalAmount
	 * @param payMode
	 * @return
	 *//*
	private Map<String, Object> commitOrderbyDevice(String commitMode, String deviceCode, JSONArray orderItemList, 
			String totalAmount, String payMode){
		Map<String, Object> returnDeviceData = new HashMap<String, Object>();
		Date pickTime = new Date();
		List<Map<String, Object>> noEnoughGoodsList = getNoEnoughGoodsList(deviceCode, orderItemList, null,
				DateFormat.dateFormatYMDHMS(pickTime));
		判断有效商品不足列表是否为空
		if (noEnoughGoodsList != null && noEnoughGoodsList.size() > 0){
			returnDeviceData.put("returnCode", "2");
			returnDeviceData.put("returnContent", noEnoughGoodsList);
		}else {
			保存订单以及订单明细、锁定单品，保存待付款订单列表
			Map<String, Object> returnDeviceOrAPPData = saveOrderAndOrderDetail(commitMode, deviceCode, orderItemList, totalAmount, payMode, null, null , null, null, null);
			提交订单到支付平台 0：闪付支付，1：支付宝支付，2：微信支付
			Map<String, String> returnContentData = (Map)returnDeviceOrAPPData.get("returnContent");
			Map<String, String> returnContent = new HashMap<String, String>();
			if (StringUtils.equals("1", payMode)){
				returnContent = AlipayCore.prePay(returnContentData.get("orderNum"), 
						returnContentData.get("totalAmount"), returnContentData.get("placeOrderTime"), "扫码支付");
				returnContent.put("payMode", "1");
			}else if (StringUtils.equals("2", payMode)){
				returnContent = WeiXinpayCore.prePay(returnContentData.get("orderNum"), 
						returnContentData.get("totalAmount"), returnContentData.get("placeOrderTime"), "扫码支付");
				returnContent.put("payMode", "2");
			}else if (StringUtils.equals("0", payMode)){
				returnContent.put("orderNum", returnContentData.get("orderNum"));
				returnContent.put("placeOrderTime", returnContentData.get("placeOrderTime"));
				returnContent.put("payMode", "0");
			}
			
			returnDeviceData.put("returnCode", "1");
			returnDeviceData.put("returnContent", returnContent);
		}
		
		return returnDeviceData;
	}
	
	*//**
	 * APP端提交订单
	 * @param deviceCode
	 * @param orderItemList
	 * @param totalAmount
	 * @param pickTime
	 * @param mobileNum
	 * @param customerId
	 * @return
	 *//*
	private Map<String, Object> commitOrderbyAPP(String commitMode, String deviceCode, JSONArray orderItemList, 
			String totalAmount, String mobileNum, String customerId, String takenStartTime, String takenEndTime, String discountMoney){
		Map<String, Object> returnAPPData = new HashMap<String, Object>();
		判断该用户未付款订单是否超过三个，是则无法下单
		int noPayOrderNum = tblOrderMapper.getOrderCountbyCustomerId(Long.valueOf(customerId), OrderStatus.NO_PAY.value);
		if (noPayOrderNum >= 3){
			returnAPPData.put("returnCode", "0");
			returnAPPData.put("returnContent", "未付款订单不能超过三个");
			
			return returnAPPData;
		}
		
		Date beginTime = DateFormat.dateFormatYYYYMMDDHHMMSS(takenStartTime);
		Date endTime = DateFormat.dateFormatYYYYMMDDHHMMSS(takenEndTime);
		Date currentTime = new Date();
		判断当前时间是否在取餐时间段内
		if (currentTime.after(endTime) || beginTime.after(currentTime)){
			returnAPPData.put("returnCode", "0");
			returnAPPData.put("returnContent", "当前时间不在取餐时间段内，下单失败");
			
			return returnAPPData;
		}
		
		List<Map<String, Object>> noEnoughGoodsList = getNoEnoughGoodsList(deviceCode, orderItemList, takenStartTime, takenEndTime);
		判断有效商品不足列表是否为空
		if (noEnoughGoodsList != null && noEnoughGoodsList.size() > 0){
			returnAPPData.put("returnCode", "2");
			returnAPPData.put("returnContent", noEnoughGoodsList);
		}else {
			保存订单以及订单明细、锁定单品，保存待付款订单列表
			Map<String, Object> returnDeviceOrAPPData = saveOrderAndOrderDetail(commitMode, deviceCode, orderItemList, totalAmount, null, beginTime, endTime, mobileNum, customerId, discountMoney);

			return returnDeviceOrAPPData;
		}
		
		return returnAPPData;
	}
	
	
	*//**
	 * 获取有效商品列表
	 * @param deviceCode
	 * @param orderItemList
	 * @param pickTime
	 * @return
	 *//*
	private List<Map<String, Object>> getNoEnoughGoodsList(String deviceCode, JSONArray orderItemList, String beginTime, String endTime){
		Map<String, Object> postData = new HashMap<String, Object>();
		postData.put("deviceCode", deviceCode);
		有效商品不足列表
		List<Map<String, Object>> noEnoughGoodsList = new ArrayList<Map<String, Object>>();
		判断当前有效商品份数是否充足
		for (int i=0; i<orderItemList.size(); i++){
			JSONObject orderItemJson = orderItemList.getJSONObject(i);
			String goodsId = orderItemJson.getString("goodsId");
			postData.put("goodsId", goodsId);
//			postData.put("pickTime", endTime);
			有效份数
			int usefulGoodsNum = basicsMachineCellMapper.getUsefulGoodsNum(postData);
			String goodsNum = orderItemJson.getString("goodsNum");
			if (Integer.valueOf(goodsNum) > usefulGoodsNum){
				Map<String, Object> noEnoughGoods = new HashMap<String, Object>();
				noEnoughGoods.put("goodsId", goodsId);
				noEnoughGoods.put("usefulGoodsNum", usefulGoodsNum+"");
				
				noEnoughGoodsList.add(noEnoughGoods);
			}
		}
		
		return noEnoughGoodsList;
	}
	
	
	*
	/**
	 * 生成订单号
	 * @return
	 *//*
	private String getOrderNum() {
		return new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + RandomStringUtils.randomNumeric(3);
	}
	
	*//**
	 * 保存订单明细、锁定单品
	 * @param deviceCode
	 * @param orderNum
	 * @param bm
	 * @param orderItemList
	 * @param orderTime
	 *//*
	private void saveOrderDetail(String deviceCode, Long orderId, String orderNum, BasicsMachine bm, 
			JSONArray orderItemList, Date orderTime, Date endTime){

		for (int i=0; i<orderItemList.size(); i++){
			JSONObject orderItemJson = orderItemList.getJSONObject(i);
			String goodsId = orderItemJson.getString("goodsId");
			String goodsNum = orderItemJson.getString("goodsNum");
			根据goodsId获取商品
			BasicsGoods bg = basicsGoodsMapper.selectByPrimaryKey(Long.valueOf(goodsId));
			保存订单明细
			TblOrderDetail tblOrderDetail = new TblOrderDetail();
			tblOrderDetail.setOrderId(orderId);
			tblOrderDetail.setOrderNum(orderNum);
			tblOrderDetail.setMachineId(bm.getMachineId());
			tblOrderDetail.setDeviceCode(deviceCode);
			tblOrderDetail.setCompanyId(bm.getCompanyId());
			tblOrderDetail.setOrderTime(orderTime);
			String orderDetailNum = getOrderNum();
			tblOrderDetail.setOrderDetailNum(orderDetailNum);
			tblOrderDetail.setGoodsId(bg.getGoodsId());
			tblOrderDetail.setGoodsName(bg.getGoodsName());
			tblOrderDetail.setGoodsNum(Integer.valueOf(goodsNum));
			BigDecimal num = new BigDecimal(goodsNum);
			BigDecimal retailPrice = bg.getRetailPrice();
			tblOrderDetail.setPrice(retailPrice.multiply(num));
			
			tblOrderDetailMapper.insertSelective(tblOrderDetail);
			锁定单品
			lockItem(deviceCode, goodsId, goodsNum, orderTime, endTime, orderId, tblOrderDetail.getOrderDetailId(), orderNum);
		}
		
	}
	
	*//**
	 * 保存订单明细
	 * @param deviceCode
	 * @param orderNum
	 * @param bm
	 * @param orderItemList
	 * @param orderTime
	 *//*
	private void saveOrderDetail2(String deviceCode, Long orderId, String orderNum, BasicsMachine bm, 
			JSONArray orderItemList, Date orderTime, Date endTime){

		for (int i=0; i<orderItemList.size(); i++){
			JSONObject orderItemJson = orderItemList.getJSONObject(i);
			String goodsId = orderItemJson.getString("goodsId");
			String goodsNum = orderItemJson.getString("goodsNum");
			根据goodsId获取商品
			BasicsGoods bg = basicsGoodsMapper.selectByPrimaryKey(Long.valueOf(goodsId));
			保存订单明细
			TblOrderDetail tblOrderDetail = new TblOrderDetail();
			tblOrderDetail.setOrderId(orderId);
			tblOrderDetail.setOrderNum(orderNum);
			tblOrderDetail.setMachineId(bm.getMachineId());
			tblOrderDetail.setDeviceCode(deviceCode);
			tblOrderDetail.setCompanyId(bm.getCompanyId());
			tblOrderDetail.setOrderTime(orderTime);
			String orderDetailNum = getOrderNum();
			tblOrderDetail.setOrderDetailNum(orderDetailNum);
			tblOrderDetail.setGoodsId(bg.getGoodsId());
			tblOrderDetail.setGoodsName(bg.getGoodsName());
			tblOrderDetail.setGoodsNum(Integer.valueOf(goodsNum));
			BigDecimal num = new BigDecimal(goodsNum);
			BigDecimal retailPrice = bg.getRetailPrice();
			tblOrderDetail.setPrice(retailPrice.multiply(num));
			
			tblOrderDetailMapper.insertSelective(tblOrderDetail);
			
		}
		
	}
	
	*//**
	 * 锁定单品
	 * @param deviceCode
	 * @param goodsId
	 * @param goodsNum
	 * @param orderTime
	 * @param endTime
	 * @param orderId
	 * @param orderDetailId
	 * @param orderNum
	 *//*
	private void lockItem(String deviceCode, String goodsId, String goodsNum, Date orderTime, 
			Date endTime, Long orderId, Long orderDetailId, String orderNum){

		Map<String, Object> postData = new HashMap<String, Object>();
		postData.put("deviceCode", deviceCode);
		postData.put("goodsId", goodsId);
		postData.put("goodsNum", Integer.valueOf(goodsNum));
		if (endTime != null){
			postData.put("pickTime", endTime);
		}else {
			postData.put("pickTime", orderTime);
		}
		
		List<String> itemIdList = basicsMachineCellMapper.getUsefulGoodsItemId(postData);
		tblItemDetailMapper.updateItemDetailbyItemIdList(itemIdList, "1", orderId, orderDetailId, orderNum, orderTime);
	}
	
	*//**
	 * 生成预定订单
	 * @param orderTime 
	 * @return 
	 *//*
	private Map<String, Object> createReserveOrder(String commitMode, String deviceCode, JSONArray orderItemList, 
			String totalAmount, String mobileNum, String customerId, String takenStartTime, String takenEndTime, String orderType, String areaModelId){
		
		Date beginTime = DateFormat.dateFormatYYYYMMDDHHMMSS(takenStartTime);
		Date endTime = DateFormat.dateFormatYYYYMMDDHHMMSS(takenEndTime);
		
		保存订单以及订单明细、保存待付款订单列表
		Map<String, Object> returnAPPReserve = saveReserveOrderAndOrderDetail(commitMode, deviceCode, orderItemList, totalAmount, null, beginTime, endTime, mobileNum, customerId, orderType, areaModelId);
		
		return returnAPPReserve;
		
	}
	
	*//**
	 * 生成订单保存订单明细
	 * 保存待付款订单列表
	 *//*
	private Map<String, Object> saveReserveOrderAndOrderDetail(String commitMode, String deviceCode, JSONArray orderItemList, 
			String totalAmount, String payMode, Date beginTime, Date endTime, String mobileNum, String customerId, String orderType, String areaModelId){
		
		Map<String, Object> returnDeviceOrAPPData = new HashMap<String, Object>();
		Map<String, String> returnContent = new  HashMap<String, String>();
		根据设备硬件id获取设备信息
		BasicsMachine bm = basicsMachineMapper.getMachinebyDeviceCode(deviceCode);
		待处理订单
		TblPendingOrder tblPendingOrder = new TblPendingOrder();
		保存订单
		TblOrder tblOrder = new TblOrder();
		String orderNum = getOrderNum();
		tblOrder.setOrderNum(orderNum);
		tblOrder.setModeNum(commitMode);
		tblOrder.setMachineId(bm.getMachineId());
		tblOrder.setDeviceCode(deviceCode);
		tblOrder.setCompanyId(bm.getCompanyId());
		Date orderTime = new Date();
		tblOrder.setOrderTime(orderTime);
		BigDecimal totalPrice = new BigDecimal(totalAmount);
		tblOrder.setTotalMoney(totalPrice);
		tblOrder.setRealMoney(totalPrice);
		tblOrder.setOrderType(orderType);
		tblOrder.setAreaModelId(Long.valueOf(areaModelId));

		支付截止时间
		Date lastPayTime = getDeviceLastPayTime(orderTime);
		tblOrder.setLastPayTime(lastPayTime);

		if (StringUtils.isNoneBlank(payMode)){
			tblOrder.setPayMode(payMode);
			
			tblPendingOrder.setModeNum("0");
		}
		
		if (StringUtils.isNoneBlank(mobileNum, customerId)){
			tblOrder.setCustomerId(Long.valueOf(customerId));
			tblOrder.setMobile(mobileNum);
			tblOrder.setBeginTime(beginTime);
			tblOrder.setEndTime(endTime);
			
			tblPendingOrder.setModeNum("1");
			tblPendingOrder.setBeginTime(beginTime);
			tblPendingOrder.setEndTime(endTime);
		}
		
		tblOrder.setOrderStatus(OrderStatus.NO_PAY.value);
		tblPendingOrder.setPendingTypeNum("1");

		//支付订单号//
		//支付状态//
		tblOrder.setIsdel("N");
		
		tblOrderMapper.insertSelective(tblOrder);
		保存订单明细
		saveOrderDetail2(deviceCode, tblOrder.getOrderId(), orderNum, bm, orderItemList, orderTime, endTime);
		保存待付款订单列表
		tblPendingOrder.setCompanyId(bm.getCompanyId());
		tblPendingOrder.setOrderId(tblOrder.getOrderId());
		tblPendingOrder.setOrderNum(orderNum);
		//支付订单号//
		tblPendingOrder.setOrderTime(orderTime);
		tblPendingOrder.setLastPayTime(lastPayTime);
		tblPendingOrderMapper.insertSelective(tblPendingOrder);
		
		returnContent.put("orderType", orderType);
		returnContent.put("orderNum", orderNum);
		returnContent.put("placeOrderTime", DateFormat.dateFormatYMDHMS(orderTime));
		
		returnDeviceOrAPPData.put("returnCode", "1");
		returnDeviceOrAPPData.put("returnContent", returnContent);

		return returnDeviceOrAPPData;
	}*/
	/**
	 * 零售订单提交
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Object> commitOrderWX(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("returnCode", "0");
		returnData.put("returnContent", "请求参数错误");
		
		/*总金额*/
		String totalAmount = res.getString("totalAmount");
		/*购买商品id和份数*/
		JSONArray orderItemList = res.getJSONArray("orderItemList");
		/*提交方式 0：设备端提交，1：APP端提交*/
		String commitMode = res.getString("commitMode");
		
		/*设备硬件id*/ 
		String deviceCode = null;
		if (res.has("deviceCode")){
			deviceCode = res.getString("deviceCode");
		}
		/*设备id*/
		String machineId = null;
		if (res.has("machineId")){
			machineId = res.getString("machineId");
		}
		
		/*支付方式 0：闪付支付，1：支付宝支付，2：微信支付*/
		String payMode = null;
		if (res.has("payMode")){
			payMode = res.getString("payMode");
		}
		/*电话号码*/
		String mobileNum = null;
		if (res.has("mobileNum")){
			mobileNum = res.getString("mobileNum");
		}
		/*客户id号*/
		String customerId = null;
		if (res.has("customerId")){
			customerId = res.getString("customerId");
		}
		/*取餐开始时间*/
		String takenStartTime = null;
		if (res.has("takenStartTime")){
			takenStartTime = res.getString("takenStartTime");
		}
		/*取餐截止时间*/
		String takenEndTime = null;
		if (res.has("takenEndTime")){
			takenEndTime = res.getString("takenEndTime");
		}
		/*优惠金额*/
		String discountMoney = null;
		if (res.has("discountMoney")){
			discountMoney = res.getString("discountMoney");
		}
		/*红包id*/
		String luckyMoneyId = null;
		if (res.has("luckyMoneyId")){
			luckyMoneyId = res.getString("luckyMoneyId");
		}
		/*红包金额*/
		String luckyMoney = null;
		if (res.has("luckyMoney")){
			luckyMoney = res.getString("luckyMoney");
		}
		/*红包类型*/
		String luckyMoneyType = null;
		if (res.has("luckyMoneyType")){
			luckyMoneyType = res.getString("luckyMoneyType");
		}
		/*订单类型*/
		String orderType = null;
		if (res.has("orderType")){
			orderType = res.getString("orderType");
		}
		if (StringUtils.isNoneBlank(totalAmount, commitMode) && 
				orderItemList != null && orderItemList.size() > 0){
			BasicsMachine basicsMachine =null;
			if(deviceCode == null){
			    basicsMachine = basicsMachineMapper.selectByPrimaryKey(Long.valueOf(machineId));
				deviceCode = basicsMachine.getDeviceCode();
			}else {
			     basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
			}
			/*判断设备端下单或APP端下单*/
			if (StringUtils.equals(Constants.COMMIT_ORDER_DEVICE, commitMode)){
				/*设备端*/
				if (StringUtils.isNoneBlank(payMode)){
					Map<String, Object> returnDeviceData = commitOrderbyDevice(deviceCode,orderType,commitMode, basicsMachine.getMachineId(), orderItemList, totalAmount ,payMode,discountMoney);
					returnData.put("returnCode", returnDeviceData.get("returnCode"));
					returnData.put("returnContent", returnDeviceData.get("returnContent"));
				}
			}else {
				/*APP端*/
				if (StringUtils.isNoneBlank(mobileNum, customerId, takenStartTime, takenEndTime)){
					Map<String, Object> returnAPPData = commitOrderbyAPP(orderType,orderItemList, commitMode,deviceCode,basicsMachine.getMachineId(), totalAmount, 
							mobileNum, customerId, takenStartTime, takenEndTime, discountMoney, luckyMoneyId, luckyMoney, luckyMoneyType);
					returnData.put("returnCode", returnAPPData.get("returnCode"));
					returnData.put("returnContent", returnAPPData.get("returnContent"));
				}
			}
		}
		
		return returnData;
	}
	/**
	 * 设备端提交订单
	 * @param deviceCode
	 * @param orderItemList
	 * @param totalAmount
	 * @param payMode
	 * @return
	 */
	private Map<String, Object> commitOrderbyDevice(String deviceCode,String orderType,String commitMode, Long machineId, 
			JSONArray orderItemList,String totalAmount, String payMode,String discountMoney){
		Map<String, Object> returnDeviceData = new HashMap<String, Object>();
		Date pickTime = new Date();
		List<Map<String, Object>> noEnoughGoodsList = getNoEnoughGoodsList(machineId,orderItemList, null,
				DateFormat.dateFormatYMDHMS(pickTime));
		/*判断有效商品不足列表是否为空*/
		if (noEnoughGoodsList != null && noEnoughGoodsList.size() > 0){
			returnDeviceData.put("returnCode", "2");
			returnDeviceData.put("returnContent", noEnoughGoodsList);
		}else {
			//start 判断零售价格是否改变，改变则返回 受改变的商品及价格（只返回临时改变价格的商品，其余不返回）
			boolean isChange=false;
			BigDecimal amount=BigDecimal.ZERO;
			List<Map<String, Object>> changeGoodsList = new ArrayList<Map<String, Object>>();
			for (int i=0; i<orderItemList.size(); i++){
				JSONObject orderItemJson = orderItemList.getJSONObject(i);
				String goodsId = orderItemJson.getString("goodsId");
				String goodsNum = orderItemJson.getString("goodsNum");
				String retailPrice = null;
				if(orderItemJson.has("retailPrice")){
					retailPrice = orderItemJson.getString("retailPrice");
				}
				
				/*根据goodsId获取商品*/
				BasicsGoods bg = basicsGoodsMapper.selectByPrimaryKey(Long.valueOf(goodsId));
				BigDecimal num = new BigDecimal(goodsNum);
				//累加订单总金额
				amount=amount.add(bg.getRetailPrice().multiply(num));
				if(retailPrice != null){
					BigDecimal retailpic=new BigDecimal(retailPrice); 
					retailpic=retailpic.setScale(2, BigDecimal.ROUND_HALF_UP); 
				
					if(retailpic.compareTo(bg.getRetailPrice())!=0 ){
						isChange=true;
						Map<String, Object> changeGood = new HashMap<String, Object>();
						changeGood.put("goodsId", goodsId);
						changeGood.put("retailPrice", bg.getRetailPrice()+"");
						changeGoodsList.add(changeGood);
					}
				}
				
			}
			if(isChange){
				//end 判断零售价格是否改变，改变则返回 受改变的商品及价格（只返回临时改变价格的商品，其余不返回）
				returnDeviceData.put("returnCode", "4");
				returnDeviceData.put("returnContent", changeGoodsList);
				return returnDeviceData;
			}
			//end  判断零售价格是否改变，改变则返回 受改变的商品及价格（只返回临时改变价格的商品，其余不返回）
			/*保存订单以及订单明细、锁定单品，保存待付款订单列表*/
			Map<String, Object> returnDeviceOrAPPData = saveOrderAndOrderDetail(machineId,orderType,commitMode,deviceCode,orderItemList, amount.toString(), 
					payMode, null, null , null, null, discountMoney, null, null);
			/*提交订单到支付平台 0：闪付支付，1：支付宝支付，2：微信支付*/
			Map<String, String> returnContentData = (Map)returnDeviceOrAPPData.get("returnContent");
			Map<String, String> returnContent = new HashMap<String, String>();
			if (StringUtils.equals("1", payMode)){
				returnContent = AlipayCore.prePay(returnContentData.get("orderNum"), 
						returnContentData.get("totalAmount"), returnContentData.get("placeOrderTime"), "扫码支付");
				if (returnContent == null){
					returnDeviceData.put("returnCode", "0");
					returnDeviceData.put("returnContent", "向支付宝提交订单失败，可能是服务器时间有误！");
					
					return returnDeviceData;
				}
				returnContent.put("payMode", "1");
			}else if (StringUtils.equals("2", payMode)){
				returnContent = WeiXinpayCore.prePay(returnContentData.get("orderNum"), 
						returnContentData.get("totalAmount"), returnContentData.get("placeOrderTime"), "扫码支付");
				if (returnContent == null){
					returnDeviceData.put("returnCode", "0");
					returnDeviceData.put("returnContent", "向微信提交订单失败，可能是服务器时间有误！");
					
					return returnDeviceData;
				}
				returnContent.put("payMode", "2");
			}else if (StringUtils.equals("0", payMode)){
				returnContent.put("orderNum", returnContentData.get("orderNum"));
				returnContent.put("placeOrderTime", returnContentData.get("placeOrderTime"));
				returnContent.put("payMode", "0");
			}
			
			returnDeviceData.put("returnCode", "1");
			returnDeviceData.put("returnContent", returnContent);
		}
		
		return returnDeviceData;
	}
	/**
	 * APP端提交订单
	 * @param deviceCode
	 * @param orderItemList
	 * @param totalAmount
	 * @param pickTime
	 * @param mobileNum
	 * @param customerId
	 * @return
	 */
	private Map<String, Object> commitOrderbyAPP(String orderType,JSONArray orderItemList, String commitMode,String deviceCode,Long machineId,
			String totalAmount, String mobileNum, String customerId, String takenStartTime, String takenEndTime, String discountMoney,
			String luckMoneyId, String luckyMoney, String luckyMoneyType){
		Map<String, Object> returnAPPData = new HashMap<String, Object>();
		/*判断该用户未付款订单是否超过三个，是则无法下单*/
		int noPayOrderNum = preissueOrderMapper.getOrderCountbyCustomerId(Long.valueOf(customerId), OrderStatus.NO_PAY.value);
		if (noPayOrderNum >= 3){
			returnAPPData.put("returnCode", "0");
			returnAPPData.put("returnContent", "未付款订单不能超过三个");
			
			return returnAPPData;
		}
		
		Date beginTime = DateFormat.dateFormatYYYYMMDDHHMMSS(takenStartTime);
		Date endTime = DateFormat.dateFormatYYYYMMDDHHMMSS(takenEndTime);
		Date currentTime = new Date();
		/*判断当前时间是否在有效时间段内*/
		if (isNotWithinTheValidTime(beginTime, endTime, currentTime) == true){
			returnAPPData.put("returnCode", "0");
			Calendar cal = Calendar.getInstance();
			cal.setTime(endTime);
			/*下单截止时间在取餐截止时间前15分钟*/
			cal.add(cal.MINUTE, -15);
			returnAPPData.put("returnContent", "当前时间不在有效时间段内，下单时间为"+DateFormat.dateFormatYMDHM(beginTime)+"，"+DateFormat.dateFormatYMDHM(cal.getTime())+"之间");
			
			return returnAPPData;
		}
		
		
		List<Map<String, Object>> noEnoughGoodsList = getNoEnoughGoodsList(machineId, orderItemList, takenStartTime, takenEndTime);
		/*判断有效商品不足列表是否为空*/
		if (noEnoughGoodsList != null && noEnoughGoodsList.size() > 0){
			returnAPPData.put("returnCode", "2");
			returnAPPData.put("returnContent", noEnoughGoodsList);
		}else {
			//start 判断零售价格是否改变，改变则返回 受改变的商品及价格（只返回临时改变价格的商品，其余不返回）
			boolean isChange=false;
			BigDecimal amount=BigDecimal.ZERO;
			List<Map<String, Object>> changeGoodsList = new ArrayList<Map<String, Object>>();
			for (int i=0; i<orderItemList.size(); i++){
				JSONObject orderItemJson = orderItemList.getJSONObject(i);
				String goodsId = orderItemJson.getString("goodsId");
				String goodsNum = orderItemJson.getString("goodsNum");
				
				String retailPrice = null;
				
				if(orderItemJson.has("retailPrice")){
					retailPrice = orderItemJson.getString("retailPrice");
				}
				  
				/*根据goodsId获取商品*/
				BasicsGoods bg = basicsGoodsMapper.selectByPrimaryKey(Long.valueOf(goodsId));
				BigDecimal num = new BigDecimal(goodsNum);
				//累加订单总金额
				amount=amount.add(bg.getRetailPrice().multiply(num));
				
				if(retailPrice != null){
					BigDecimal retailpic=new BigDecimal(retailPrice); 
					retailpic=retailpic.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					if(retailpic.compareTo(bg.getRetailPrice())!=0){
						isChange=true;
						Map<String, Object> changeGood = new HashMap<String, Object>();
						changeGood.put("goodsId", goodsId);
						changeGood.put("retailPrice", bg.getRetailPrice()+"");
						changeGoodsList.add(changeGood);
					}
				}
				
			}
			if(isChange){
				returnAPPData.put("returnCode", "4");
				returnAPPData.put("returnContent", changeGoodsList);
				//end 判断零售价格是否改变，改变则返回 受改变的商品及价格（只返回临时改变价格的商品，其余不返回）
			}else{
				/*保存订单以及订单明细、锁定单品，保存待付款订单列表*/
				Map<String, Object> returnDeviceOrAPPData = saveOrderAndOrderDetail(machineId,orderType,commitMode,deviceCode, orderItemList, amount.toString(), null, 
						beginTime, endTime, mobileNum, customerId, discountMoney, luckMoneyId, luckyMoney);
	
				return returnDeviceOrAPPData;
			}
		}
		
		return returnAPPData;
	}
	
	/**
	 * 保存订单以及订单明细、锁定单品
	 * 保存待付款订单列表
	 * @param deviceCode
	 * @param orderItemList
	 * @param totalAmount
	 * @param payMode
	 */
	@Transactional(rollbackFor = Exception.class)
	private Map<String, Object> saveOrderAndOrderDetail(Long machineId,String orderType,String commitMode, String deviceCode, JSONArray orderItemList, 
		String totalAmount, String payMode, Date beginTime, Date endTime, String mobileNum, String customerId, String discountMoney,
		String luckMoneyId, String luckyMoney){
			
		Map<String, Object> returnDeviceOrAPPData = new HashMap<String, Object>();
		Map<String, String> returnContent = new  HashMap<String, String>();
		/*根据设备硬件id获取设备信息*/
		BasicsMachine bm = basicsMachineMapper.selectByDeviceCode(deviceCode);
		/*待处理订单*/
		TblPendingOrder tblPendingOrder = new TblPendingOrder();
		/*保存订单*/
		TblOrder tblOrder = new TblOrder();
		String orderNum = "10"+getOrderNum();
		tblOrder.setOrderNum(orderNum);
		/*下单类型1为零售*/   
		tblOrder.setOrderType("1");
//		tblOrder.setMobile(mobileNum);
//		tblOrder.setCustomerId(Long.valueOf(customerId));
		tblOrder.setModeNum(commitMode);
		tblOrder.setMachineId(bm.getMachineId());
		tblOrder.setCompanyId(bm.getCompanyId());
		Date orderTime = new Date();
		tblOrder.setOrderTime(orderTime);
		BigDecimal totalPrice = new BigDecimal(totalAmount);
		/*实际支付金额是否为0*/
		boolean isZero = Boolean.FALSE;
		/*0：首单优惠活动 1：每单减免活动 2：无优惠*/
		int  orderDiscountType = 2;
		/*参与首单优惠不能使用红包*/ 
		if (StringUtils.isNotBlank(discountMoney) && !StringUtils.equals(discountMoney, "0")){
			Object discountType = ((Map<String, Object>) firstOrderDiscountService.firstOrderAndOneOrder(customerId).get("returnContent")).get("discountType");
			if ( discountType != null && StringUtils.equals("0", discountType.toString()) ){
				orderDiscountType = 0;
			}else if ( discountType != null && StringUtils.equals("1", discountType.toString()) ){
				orderDiscountType = 1;
			}
			
			BigDecimal discountPrice = new BigDecimal(discountMoney);
			BigDecimal realPrice = totalPrice.subtract(discountPrice);
			/*判断实际金额是否小于等于0*/
			if (realPrice.signum() <= 0){
				tblOrder.setRealMoney(BigDecimal.ZERO);
				isZero = Boolean.TRUE;
				returnContent.put("totalAmount", "0");
			}else {
				tblOrder.setRealMoney(realPrice);
				returnContent.put("totalAmount", realPrice.toString());
			}
			tblOrder.setDiscountMoney(discountPrice);
			
		}else {
			
				/*使用红包*/
				if (StringUtils.isNoneBlank(luckMoneyId, luckyMoney)){
//					BigDecimal discountPrice = new BigDecimal(luckyMoney);
//					BigDecimal realPrice = totalPrice.subtract(discountPrice);
//					/*判断实际金额是否小于等于0*/
//					if (realPrice.signum() <= 0){
//						tblOrder.setRealMoney(BigDecimal.ZERO);
//						isZero = Boolean.TRUE;
//						returnContent.put("totalAmount", "0");
//					}else {
//						tblOrder.setRealMoney(realPrice);
//						returnContent.put("totalAmount", realPrice.toString());
//					}
//					tblOrder.setDiscountMoney(discountPrice);
//					/*更新红包状态*/
//					tblLuckyMoneyMapper.updateIsUsedById(Long.valueOf(luckMoneyId), "1");
//					/*新增红包使用记录*/
//					TblLuckyMoneyUsedLog tblLuckyMoneyUsedLog = new TblLuckyMoneyUsedLog();
//					tblLuckyMoneyUsedLog.setOrderNum(orderNum);
//					tblLuckyMoneyUsedLog.setCustomerId(Long.valueOf(customerId));
//					tblLuckyMoneyUsedLog.setCustomerMobile(mobileNum);
//					tblLuckyMoneyUsedLog.setLuckyMoneyId(Long.valueOf(luckMoneyId));
//					tblLuckyMoneyUsedLog.setCreateTime(new Date());
//					
//					tblLuckyMoneyUsedLogMapper.insertSelective(tblLuckyMoneyUsedLog);
				}else {
					tblOrder.setRealMoney(totalPrice);
					returnContent.put("totalAmount", totalPrice.toString());
				}
		}
		if("".equals(discountMoney)){
        	 tblOrder.setRealMoney(totalPrice);
        	 tblOrder.setDiscountMoney(BigDecimal.ZERO);
         }
		tblOrder.setTotalMoney(totalPrice);

		/*支付截止时间*/
		Date lastPayTime = getDeviceLastPayTime(orderTime);
		tblOrder.setLastPayTime(lastPayTime);

		if (StringUtils.isNoneBlank(payMode)){
			tblOrder.setPayMode(payMode);
			
//			tblPendingOrder.setModeNum(payMode);
		}
		
		if (StringUtils.isNoneBlank(mobileNum, customerId)){
			tblOrder.setCustomerId(Long.valueOf(customerId));
			tblOrder.setMobile(mobileNum);
//			tblOrder.setBeginTime(beginTime);
//			tblOrder.setEndTime(endTime);
			tblPendingOrder.setModeNum(commitMode);
			
			tblPendingOrder.setBeginTime(beginTime);
			tblPendingOrder.setEndTime(endTime);
		}else{
			
			//Gekko 设备下单 轮询表 取餐截至时间当前时间加5分钟
//			if(endTime!=null){
//				if (orderTime.before(endTime)) {
//					long nm = 1000 * 60;// 一分钟的毫秒数
//					long diff = (endTime.getTime() - beginTime.getTime()) % (1000 * 60);
//					if (diff < 5L) {
//						Calendar cal = Calendar.getInstance();
//						cal.setTime(orderTime);
//						cal.add(cal.MINUTE, 5);
//						endTime = cal.getTime();
//					}
//				}
//			}else{
//				
//			Calendar cal1 = Calendar.getInstance();
//			cal1.setTime(orderTime);
//			cal1.add(cal1.MINUTE, 5);
//			endTime=cal1.getTime();
//			tblPendingOrder.setBeginTime(orderTime);
//			tblPendingOrder.setEndTime(endTime);
//			}
			//Gekko 设备下单 轮询表 取餐截至时间无限制
			tblPendingOrder.setBeginTime(orderTime);
			tblPendingOrder.setEndTime(null);
		}
		TblOrderChild tblOrderChild = new TblOrderChild();
		/*取货验证码*/
		String verifyCode = null;
		/*若实际支付金额为0，则订单状态直接为已支付未取货, 并加入待取货列表*/
		if (isZero == Boolean.TRUE){
			verifyCode = VerifyCode.createVerifyCode();
			tblOrderChild.setIdentifyingCode(verifyCode);
			tblOrder.setOrderStatus(OrderStatus.NO_TAKED.value);
			tblOrderChild.setOrderChildStatus(OrderStatus.NO_TAKED.value);
			tblPendingOrder.setPendingTypeNum("3");
		}else {
			tblOrder.setOrderStatus(OrderStatus.NO_PAY.value);
			tblOrderChild.setOrderChildStatus(OrderStatus.NO_PAY.value);
			tblPendingOrder.setPendingTypeNum("1");
		}
		

		//支付订单号//
		//支付状态//
		tblOrder.setIsdel("N");
		
		tblOrderMapper.insertSelective(tblOrder);
		
		BasicsMachine basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
		/*子订单添加*/
		String orderchildnum = "20"+getOrderNum();//子订单号
		tblOrderChild.setOrderChildNum(orderchildnum);
		tblOrderChild.setMachineId(machineId);
		tblOrderChild.setOrderTime(orderTime);
		tblOrderChild.setAreaModelId(basicsMachine.getAreaModelId());
		tblOrderChild.setOrderId(tblOrder.getOrderId());
		if (beginTime != null){
			tblOrderChild.setBeginTime(beginTime);
		} else{
			tblOrderChild.setBeginTime(orderTime);
		}
		if (endTime != null){
			tblOrderChild.setEndTime(endTime);
		} else{
			endTime = modelCleanGoodsMapper.getEndTimeByAreaModelId(basicsMachine.getAreaModelId());
			tblOrderChild.setEndTime(endTime);
		}

		tblOrderChild.setIsdel("N");
		
		tblOrderChildMapper.insert(tblOrderChild);
		/*保存订单明细、锁定单品*/
		saveOrderDetail(machineId,deviceCode, tblOrderChild.getOrderChildId(),tblOrderChild.getOrderChildId(),tblOrder.getOrderId() , bm, orderItemList, orderTime, endTime);
		/*保存待付款订单列表*/
		tblPendingOrder.setCompanyId(bm.getCompanyId());
		tblPendingOrder.setOrderId(tblOrder.getOrderId());
		tblPendingOrder.setOrderNum(orderNum);
		tblPendingOrder.setModeNum(commitMode);
		tblPendingOrder.setOrderType("1");
		//支付订单号
		tblPendingOrder.setOrderTime(orderTime);
		tblPendingOrder.setLastPayTime(lastPayTime);
		tblPendingOrderMapper.insertSelective(tblPendingOrder);

		returnContent.put("orderNum", orderNum);
		returnContent.put("placeOrderTime", DateFormat.dateFormatYMDHMS(orderTime));
		
		returnDeviceOrAPPData.put("returnCode", "1");
		returnDeviceOrAPPData.put("returnContent", returnContent);
		
		if (StringUtils.isNotBlank(discountMoney) && !StringUtils.equals(discountMoney, "0")){
			if (orderDiscountType == 0){
//				/*更新优惠使用标识为已使用*/
//				tblCustomerMapper.updateDiscountUsed(Long.valueOf(customerId), "1");
//				/*新增首次优惠使用记录*/
//				TblCustomerDisActLog tblCustomerDisActLog = new TblCustomerDisActLog();
//				tblCustomerDisActLog.setCustomerId(Long.valueOf(customerId));
//				tblCustomerDisActLog.setOrderNum(orderNum);
//				tblCustomerDisActLog.setUsedTime(orderTime);
//				tblCustomerDisActLog.setDiscountMoney(new BigDecimal(discountMoney));
//				
//				tblCustomerDisActLogMapper.insertSelective(tblCustomerDisActLog);
			}else if (orderDiscountType == 1){
				/*新增每单优惠活动使用记录*/
//				TblCustomerOrderDisLog tblCustomerOrderDisLog = new TblCustomerOrderDisLog();
//				tblCustomerOrderDisLog.setCustomerId(Long.valueOf(customerId));
//				tblCustomerOrderDisLog.setOrderNum(orderNum);
//				tblCustomerOrderDisLog.setUsedTime(orderTime);
//				tblCustomerOrderDisLog.setDiscountMoney(new BigDecimal(discountMoney));
//				
//				tblCustomerOrderDisLogMapper.insertSelective(tblCustomerOrderDisLog);
			}
		}
		/*支付金额为0时直接发送取货短信*/
		if (isZero == Boolean.TRUE){
			/*已支付,调用付款订单付款后处理*/
			orderCommonService.changePaidOrderToTakenOrder(tblOrder);
			returnDeviceOrAPPData.put("returnCode", "3");
			/*发送取货短信*/
//				orderBackCallService.sendVerifyCode(tblOrder, verifyCode);
		}
		
		return returnDeviceOrAPPData;
	}
		/**
		 * 保存订单明细、锁定单品
		 * @param deviceCode
		 * @param orderNum
		 * @param bm
		 * @param orderItemList
		 * @param orderTime
		 */
		private void saveOrderDetail(Long machineId,String deviceCode, Long saveOrderDetail,Long orderChildId,Long orderId, BasicsMachine bm, 
				JSONArray orderItemList, Date orderTime, Date endTime){

			for (int i=0; i<orderItemList.size(); i++){
				JSONObject orderItemJson = orderItemList.getJSONObject(i);
				String goodsId = orderItemJson.getString("goodsId");
				String goodsNum = orderItemJson.getString("goodsNum");
				/*根据goodsId获取商品*/
				BasicsGoods bg = basicsGoodsMapper.selectByPrimaryKey(Long.valueOf(goodsId));
				/*保存订单明细*/
				TblOrderDetail tblOrderDetail = new TblOrderDetail();
				tblOrderDetail.setOrderChildId(orderChildId);
				tblOrderDetail.setMachineId(bm.getMachineId());
				tblOrderDetail.setCompanyId(bm.getCompanyId());
				String orderDetailNum = getOrderNum();
				tblOrderDetail.setOrderDetailNum(orderDetailNum);
				tblOrderDetail.setGoodsId(bg.getGoodsId());
				tblOrderDetail.setGoodsName(bg.getGoodsName());
				tblOrderDetail.setGoodsNum(Integer.valueOf(goodsNum));
				BigDecimal num = new BigDecimal(goodsNum);
				BigDecimal retailPrice = bg.getRetailPrice();
				BigDecimal costPrice = bg.getCostPrice();
				tblOrderDetail.setPrice(retailPrice.multiply(num));
				tblOrderDetail.setCostPrice(costPrice);
				tblOrderDetail.setProfit(retailPrice.subtract(costPrice).multiply(num));
				
				tblOrderDetailMapper.insertSelective(tblOrderDetail);
				/*锁定单品*/
				lockItem(machineId,deviceCode, goodsId, goodsNum, orderTime, endTime, orderChildId, tblOrderDetail.getOrderDetailId(), orderId);
			}
			
		}
		/**
		 * 锁定单品
		 * @param deviceCode
		 * @param goodsId
		 * @param goodsNum
		 * @param orderTime
		 * @param endTime
		 * @param orderId
		 * @param orderDetailId
		 * @param orderNum
		 */
		private void lockItem(Long machineId,String deviceCode, String goodsId, String goodsNum, Date orderTime, 
				Date endTime, Long orderChildId, Long orderDetailId, Long orderId){

			Map<String, Object> postData = new HashMap<String, Object>();
//			postData.put("deviceCode", deviceCode);
			postData.put("machineId", machineId);
			postData.put("goodsId", goodsId);
			postData.put("goodsNum", Integer.valueOf(goodsNum));
	/*		if (endTime != null){
				postData.put("pickTime", endTime);
			}else {
				postData.put("pickTime", orderTime);
			}*/
			
			List<String> itemIdList = basicsMachineCellMapper.getUsefulGoodsItemId(postData);
			tblItemDetailMapper.updateItemDetailbyItemIdList(itemIdList, "1", orderId,orderChildId,orderDetailId,orderTime);
		}
	/**
	 * 获取有效商品列表
	 * @param deviceCode
	 * @param orderItemList
	 * @param pickTime
	 * @return
	 */
	private List<Map<String, Object>> getNoEnoughGoodsList(Long machineId, JSONArray orderItemList, String beginTime, String endTime){
		Map<String, Object> postData = new HashMap<String, Object>();
		postData.put("machineId", machineId);
		/*有效商品不足列表*/
		List<Map<String, Object>> noEnoughGoodsList = new ArrayList<Map<String, Object>>();
		/*判断当前有效商品份数是否充足*/
		for (int i=0; i<orderItemList.size(); i++){
			JSONObject orderItemJson = orderItemList.getJSONObject(i);
			String goodsId = orderItemJson.getString("goodsId");
			postData.put("goodsId", goodsId);
//				postData.put("pickTime", endTime);
			/*有效份数*/
			int usefulGoodsNum = basicsMachineCellMapper.getUsefulGoodsNum(postData);
			String goodsNum = orderItemJson.getString("goodsNum");
			if (Integer.valueOf(goodsNum) > usefulGoodsNum){
				Map<String, Object> noEnoughGoods = new HashMap<String, Object>();
				noEnoughGoods.put("goodsId", goodsId);
				noEnoughGoods.put("usefulGoodsNum", usefulGoodsNum+"");
				
				noEnoughGoodsList.add(noEnoughGoods);
			}
		}
		
		return noEnoughGoodsList;
	}
	/**
	 * 生成订单号
	 * @return
	 */
	private String getOrderNum() {
		return new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()) + RandomStringUtils.randomNumeric(3);
	}
	/**
	 * 计算支付截止时间
	 * @param orderTime
	 * @return
	 */
	private Date getDeviceLastPayTime(Date orderTime){
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderTime);
		cal.add(cal.MINUTE, Constants.DEVICE_LAST_PAY_TIME_INTERVAL);
		
		return cal.getTime();
	}
	/**
	 * 判断下单时间是否在有效时间段内
	 * @param beginTime
	 * @param endTime
	 * @param currentTime
	 * @return
	 */
	private boolean isNotWithinTheValidTime(Date beginTime, Date endTime, Date currentTime){
		boolean flag = Boolean.FALSE;
		Calendar cal = Calendar.getInstance();
		cal.setTime(endTime);
		/*下单截止时间在取餐截止时间前15分钟*/
		cal.add(cal.MINUTE, -15);
		if (currentTime.after(cal.getTime()) || beginTime.after(currentTime)){
			flag = Boolean.TRUE;
		}
		
		return flag;
	}
}
