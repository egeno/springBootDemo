package com.qjkj.qjcsp.service.wechatapi;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.BasicsMachineCell;
import com.qjkj.qjcsp.entity.ModelCleanGoodsVo;
//import com.qjkj.qjcsp.entity.TblLuckyMoneyUsedLog;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblOrderDetail;
import com.qjkj.qjcsp.entity.TblPendingOrder;
import com.qjkj.qjcsp.entity.TblPreissueEndTime;
import com.qjkj.qjcsp.entity.TblTemporaryRetail;
import com.qjkj.qjcsp.entity.TblWeixinAccess;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.ModelCleanGoodsMapper;
import com.qjkj.qjcsp.mapper.PreissueOrderMapper;
import com.qjkj.qjcsp.mapper.StockInfoMapper;
//import com.qjkj.qjcsp.mapper.TblLuckyMoneyMapper;
//import com.qjkj.qjcsp.mapper.TblLuckyMoneyUsedLogMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.TblPendingOrderMapper;
import com.qjkj.qjcsp.mapper.TblPreissueEndTimeMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.mapper.TblWeixinAccessMapper;
import com.qjkj.qjcsp.service.appUser.FirstOrderDiscountService;
import com.qjkj.qjcsp.service.order.app.PreissueCommitOrderService;
import com.qjkj.qjcsp.service.order.common.OrderBackCallService;
import com.qjkj.qjcsp.service.order.common.OrderCommonService;
import com.qjkj.qjcsp.service.preissue.SurplusPreissueNumService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.DateUtils;
import com.qjkj.qjcsp.util.VerifyCode;
import com.qjkj.qjcsp.web.api.common.OrderCommonController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 预售订单提交
 * 
 * @param request
 * @return
 */

@Component
@Transactional
public class  PreissueCommitOrderWeChatService {
	private static Logger logger = LoggerFactory.getLogger(PreissueCommitOrderService.class);
	/*获取剩余预定份数的service*/
	@Autowired
    private SurplusPreissueNumService surplusPreissueNumService;
	@Autowired
    private PreissueOrderMapper preissueOrderMapper;
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private FirstOrderDiscountService firstOrderDiscountService;
	@Autowired
	private OrderCommonService orderCommonService;
	@Autowired
	private TblTemporaryRetailMapper temporaryRetailMapper;  
	@Autowired
	private StockInfoMapper stockInfoMapper;
	@Autowired
	private TblOrderMapper tblOrderMapper;
	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;
	@Autowired
	private OrderBackCallService orderBackCallService;
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;
	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;
	@Autowired
	private TblPendingOrderMapper tblPendingOrderMapper;
	@Autowired
	private TblPreissueEndTimeMapper tblPreissueEndTimeMapper;
	@Autowired
	private TblWeixinAccessMapper tblWeixinAccessMapper;
	@Autowired
	private BasicsAreaModelMapper asicsAreaModelMapper;
	@Autowired
	private ModelCleanGoodsMapper modelCleanGoodsMapper;
	
	private Map<String, Object> returnmap = null;
	private String  OdrStatus;
	
	public Map<String, Object> PreissueCommitOrderWX(JSONObject res) throws Exception {
		returnmap = null;
		Map<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("returnCode", "0");
		returnData.put("returnContent", "请求参数错误");
		
		/*设备硬件id*/
		String machineId = res.getString("machineId");
		/*总金额*/
		String totalAmount = res.getString("totalAmount");
		/*优惠金额*/
		String discountMoney = null;
		if (res.has("discountMoney")){
			discountMoney = res.getString("discountMoney");
		}
//		/*支付方式 0：闪付支付，1：支付宝支付，2：微信支付*/
//		String payMode = null;
//		if (res.has("payMode")){
//			payMode = res.getString("payMode");
//		}
		/*购买商品id和份数*/
		JSONArray childOrders = res.getJSONArray("childOrders");
		/*电话号码*/
		String mobileNum = null;
		if (res.has("mobileNum")){
			mobileNum = res.getString("mobileNum");
		}
		/*微信*/
		String openId = null;
		if (res.has("openId")){
			openId = res.getString("openId");
		}
		/*客户id号*/
		String customerId = null;
		if (res.has("customerId")){
			customerId = res.getString("customerId");
		}
		/*提交方式 0：设备端提交，1：APP端提交*/
		String commitMode = res.getString("commitMode");
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
		
	   
		/*调用app下单方法*/
		Map<String, Object> returnAPPData = null;
//		try {
			returnAPPData = commitOrderbyAPP(openId,childOrders, commitMode, machineId, totalAmount, 
					mobileNum, customerId, discountMoney, luckyMoneyId, luckyMoney, luckyMoneyType);
			returnData.put("returnCode", returnAPPData.get("returnCode"));
			returnData.put("returnContent", returnAPPData.get("returnContent"));
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("1");
//		}
		
	 return returnAPPData;	
	}
	/*app下单*/
	private synchronized Map<String, Object> commitOrderbyAPP(String openId,JSONArray childOrders, String commitMode, String machineId,
			String totalAmount, String mobileNum, String customerId, String discountMoney,
			String luckMoneyId, String luckyMoney, String luckyMoneyType) throws Exception{
		Map<String, Object> returnAPPData = new HashMap<String, Object>();
		Map<String, Object> returnkucun = null;
		List<Map<String, Object>> returnlist = new ArrayList<Map<String,Object>>();
		/*判断该用户未付款订单是否超过三个，是则无法下单*/
		int noPayOrderNum = preissueOrderMapper.getOrderCountbyCustomerId(Long.valueOf(customerId), OrderStatus.NO_PAY.value);
		if (noPayOrderNum >= 3){
			returnAPPData.put("returnCode", "0");
			returnAPPData.put("returnContent", "未付款订单不能超过三个");
			return returnAPPData;
		}
		/*得到machineId*/
		BasicsMachine basicsMachine = stockInfoMapper.findMachineByMachineId(machineId);
		JSONArray orderDetails = null; 
		for(int i = 0; i < childOrders.size(); i++){
			JSONObject orderItemJson = childOrders.getJSONObject(i);
			orderDetails= orderItemJson.getJSONArray("orderDetails");
			String takenStartTime = orderItemJson.getString("takenStartTime");//取餐开始时间
			String takenEndTime = orderItemJson.getString("takenEndTime");//取餐截止时间
			String areaModelId = orderItemJson.getString("areaModelId");//模型id
			Date date = new Date();
//			List <ModelCleanGoodsVo> cleanGoodsVo=modelCleanGoodsMapper.getTime(machineId, Long.parseLong(areaModelId));
			ModelCleanGoodsVo cleanGoodsVo = modelCleanGoodsMapper.getTimeByModelId(Long.valueOf(areaModelId));
			//Gekko 注：理论下单截止时间
			String supplyEndTime=DateUtils.getDateFormat("HH:mm:ss", cleanGoodsVo.getReserveEndTime());
			SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = DateUtils.getDateFormat("yyyy-MM-dd",format.parse(takenStartTime));
	   		String yyMMddHHmmss = yyyymmdd+" "+supplyEndTime;
	   		Calendar c = Calendar.getInstance();  
			c.setTime(DateUtils.formatDate(yyMMddHHmmss));
			c.add(Calendar.MINUTE, -5);
			//Gekko 理论下单截止时间
			Date reserveEndTime = c.getTime();
//			Date endTime = getRefundEndTime(takenStartTime,areaModelId,0);
			if(reserveEndTime == null){
				returnAPPData.put("returnCode","0");
	    		returnAPPData.put("returnContent","下单失败，当前模型不存在");
				return returnAPPData;
			}
			if(!date.before(reserveEndTime)){
				
				returnAPPData.put("returnCode","0");
	    		returnAPPData.put("returnContent","下单失败，当前时间不在预订时间内,下单时间为："+DateUtils.getDateFormat("yyyy-MM-dd HH:mm:ss", reserveEndTime));
				return returnAPPData;
			}
			
			JSONObject res = new JSONObject();
			res.put("machineId", machineId);
			res.put("selectDate", getStringDateyymmdd(takenStartTime));
			res.put("areaModelId", areaModelId);
			Map<String, Object> returnum = (Map<String, Object>) surplusPreissueNumService
					.getSurplusPreissueNum(res).get("returnContent");
			/*库存数量*/
			Integer num = Integer.valueOf((String) returnum.get("surplusNum"));
			Integer goodsNum = 0;
			for(int k = 0; k < orderDetails.size();k++){
				/*订单明细*/
		    	JSONObject  Details = orderDetails.getJSONObject(k);
				int nums = Integer.valueOf(Details.getString("goodsNum"));
				goodsNum +=nums;
			}
	    	if(num < goodsNum){
	    		returnkucun = new HashMap<String, Object>();
	    		returnkucun.put("areaModelId", areaModelId);
	    		returnkucun.put("takenStartTime", takenStartTime);
	    		returnkucun.put("takenEndTime", takenEndTime);
	    		returnkucun.put("surplusNum", num+"");
	    		returnlist.add(returnkucun);
	    	}
		}
		//库存不足
		if(returnlist !=null && returnlist.size() > 0 ){
			returnAPPData.put("returnCode","2");
			returnAPPData.put("returnContent",returnlist);
			return returnAPPData;
		}
		//start 判断零售价格是否改变，改变则返回 受改变的商品及价格（只返回临时改变价格的商品，其余不返回）
		boolean isChange=false;
		BigDecimal amount=BigDecimal.ZERO;
		List<Map<String, Object>> changeGoodsList = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < childOrders.size(); i++){
			JSONObject orderItemsJson = childOrders.getJSONObject(i);
			orderDetails= orderItemsJson.getJSONArray("orderDetails");
			for (int j=0; j<orderDetails.size(); j++){
				JSONObject orderItemJson = orderDetails.getJSONObject(j);
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
				if(retailPrice != null ){
					BigDecimal retailpic=new BigDecimal(retailPrice); 
					retailpic=retailpic.setScale(2, BigDecimal.ROUND_HALF_UP);
					
					if(retailpic.compareTo(bg.getRetailPrice())!=0){
						isChange=true;
						Map<String, Object> changeGood = new HashMap<String, Object>();
						changeGood.put("goodsId", goodsId);
						changeGood.put("retailPrice", bg.getRetailPrice()+"");
						if(!changeGoodsList.contains(changeGood)){
							changeGoodsList.add(changeGood);
						}
					}
				}
			}
		}
		if(isChange){
			returnAPPData.put("returnCode", "4");
			returnAPPData.put("returnContent", changeGoodsList);
			return returnAPPData;
		}
		//end 判断零售价格是否改变，改变则返回 受改变的商品及价格（只返回临时改变价格的商品，其余不返回）
		/*保存父订单*/
		Map<String, Object> savemap = saveOrder(basicsMachine.getCompanyId(),machineId, mobileNum, customerId, discountMoney, 
				amount.toString(), luckMoneyId, luckyMoney,commitMode);
		TblOrder tblOrder =null;
		for(int i = 0; i < childOrders.size(); i++){
			JSONObject orderItemJson = childOrders.getJSONObject(i);
			String takenStartTime = orderItemJson.getString("takenStartTime");//取餐开始时间
			String areaModelId = orderItemJson.getString("areaModelId");//模型id
			
			JSONObject res = new JSONObject();
			res.put("machineId", machineId);
			res.put("selectDate", getStringDateyymmdd(takenStartTime));
			res.put("areaModelId", areaModelId);
			Map<String, Object> returnum = (Map<String, Object>) surplusPreissueNumService
					.getSurplusPreissueNum(res).get("returnContent");
			/*父订单id*/
			String orderNum = "";
			if(savemap != null){
				orderNum= savemap.get("orderNum").toString();
				tblOrder=tblOrderMapper.selectByOrderNum(orderNum);
			}
			
			if(returnum != null ){
				if("0".equals(returnum.get("returnCode"))){
					logger.warn("returnCode为0时----获取预定剩余份数接口异常"+"  设备id"+machineId +"     时间" + getStringDateyymmdd(takenStartTime) +" 模型Id"+areaModelId);
				}
		        /*子订单添加*/
				TblOrderChild tblOrderChild = new TblOrderChild();
		     	String orderchildnum = "20"+getOrderNum();//子订单号
		        Long orderid =tblOrder.getOrderId();
				String takenEndTime = orderItemJson.getString("takenEndTime");//取餐截止时间
				String status = OrderStatus.NO_TAKED.value;
				/*订单状态等于已付款，生成取餐验证码*/
				if(OdrStatus.equals(status)){
					String verifyCode = VerifyCode.createVerifyCode();
					tblOrderChild.setIdentifyingCode(verifyCode);
				}
				//Gekko 注：退款截止时间
//				List <ModelCleanGoodsVo> cleanGoodsVo=modelCleanGoodsMapper.getTime(machineId, Long.parseLong(areaModelId));
				ModelCleanGoodsVo modelCleanGoodsVo = modelCleanGoodsMapper.getTimeByModelId(Long.valueOf(areaModelId));
				String supplyEndTime=DateUtils.getDateFormat("HH:mm:ss", modelCleanGoodsVo.getReserveEndTime());
				SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
				String yyyymmdd1 = DateUtils.getDateFormat("yyyy-MM-dd",format.parse(takenStartTime));
		   		String yyMMddHHmmss1 = yyyymmdd1+" "+supplyEndTime;
		   		Calendar c = Calendar.getInstance();  
				c.setTime(DateUtils.formatDate(yyMMddHHmmss1));
				c.add(Calendar.MINUTE, -30);
				c.add(Calendar.HOUR, -2);
		   		//退款截止时间
		   		Date endTime = c.getTime();
		   		
				tblOrderChild.setOrderChildNum(orderchildnum);
				tblOrderChild.setOrderId(orderid);
				tblOrderChild.setAreaModelId(Long.valueOf(areaModelId));
				tblOrderChild.setBeginTime(getStringDate(takenStartTime));
				tblOrderChild.setEndTime(getStringDate(takenEndTime));
				tblOrderChild.setOrderChildStatus(OdrStatus);
				tblOrderChild.setMachineId(Long.valueOf(machineId));
				tblOrderChild.setOrderTime(getStringDate(savemap.get("orderTime").toString()));
				tblOrderChild.setOrderPreissueLastRefundTime(endTime);
				tblOrderChild.setIsdel("N");
				tblOrderChildMapper.insert(tblOrderChild);
				
				orderItemJson.getString("orderDetails");
			    orderDetails = orderItemJson.getJSONArray("orderDetails");
			    
				TblTemporaryRetail tblTemporaryRetail = new TblTemporaryRetail();
				Map<String, Object> cellmap = new HashMap<String, Object>();
				cellmap.put("machineId", Long.valueOf(machineId));
				cellmap.put("areaModelId", Long.valueOf(areaModelId));
				cellmap.put("temporaryDate",getStringDateyymmdd(takenStartTime));
				
				/*得到所有空格子*/
				List<BasicsMachineCell> BasicsMachineCells = tblOrderChildMapper
						.getBasicsMachineCells(cellmap);
			    int index = 0; 
				for(int k = 0; k < orderDetails.size();k++){
			    	/*订单明细*/
			    	JSONObject  Details = orderDetails.getJSONObject(k);
			    	int nums = Integer.valueOf(Details.getString("goodsNum"));
			    	Long goodId = Long.valueOf(Details.getString("goodsId"));
			    	
			    	/*添加明细*/
			    	TblOrderDetail tblOrderDetail = saveOrderDetail(tblOrderChild.getOrderChildId(), getOrderNum(), basicsMachine,Details);
			    	/*一份占一个格子*/
			    	for(int j = 0; j < nums; j++){
			    		tblTemporaryRetail.setOrderId(tblOrder.getOrderId());
			    		tblTemporaryRetail.setMachineId(Long.valueOf(machineId));
				    	tblTemporaryRetail.setGoodsId(goodId);
				    	tblTemporaryRetail.setAreaModelId(Long.valueOf(areaModelId));
				    	tblTemporaryRetail.setOrderDetailId(tblOrderDetail.getOrderDetailId());
				    	
			    		BasicsMachineCell basicsMachineCell = BasicsMachineCells.get(index);
			    		tblTemporaryRetail.setCellId(basicsMachineCell.getCellId());
			    		tblTemporaryRetail.setOrderChildId(tblOrderChild.getOrderChildId());
			    		
			    		tblTemporaryRetail.setCellRow(basicsMachineCell.getRowNum());
			    		tblTemporaryRetail.setCellColumn(basicsMachineCell.getColNum());
				    	tblTemporaryRetail.setCellNum(basicsMachineCell.getCellNum());
				    	tblTemporaryRetail.setTemporaryDate(getStringDate(takenStartTime));
				    	tblTemporaryRetail.setIssueId(Long.valueOf(Details.getString("issueId")));
				    	tblTemporaryRetail.setIsdel("N");
				    	index++;
				    	temporaryRetailMapper.insertSelective(tblTemporaryRetail);
			    	}
			     }
			   }else{
				   logger.warn("获取预定剩余份数接口异常"+"  设备id"+machineId +"     时间" + getStringDateyymmdd(takenStartTime) +" 模型Id"+areaModelId);
			   }
				
			}
		
		 if(!"".equals(openId)){
		    	/*添加微信记录*/
		    	weiXin(openId,machineId);
		 }
		if(savemap.get("totalAmount").equals("0")){
			/*已支付,调用付款订单付款后处理*/
			orderCommonService.changePaidOrderToTakenOrder(tblOrder);
			returnAPPData.put("returnCode", "3");
		}else{
			
			returnAPPData.put("returnCode", "1");
		}
		Map<String, String> map =new HashMap<String, String>();
		map.put("orderNum", savemap.get("orderNum").toString());
		map.put("placeOrderTime", savemap.get("orderTime").toString());
		map.put("realPayMoney", savemap.get("totalAmount").toString());
		returnAPPData.put("returnContent", map);
		
		return returnAPPData;
	}
	/**
	 * 保存父订单
	 * 保存待付款订单列表
	 * @param deviceCode
	 * @param orderItemList
	 * @param totalAmount
	 * @param payMode
	 */
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveOrder(Long CompanyId,String machineId,String mobile,String customerId,
			String discountMoney,String totalAmount,String luckMoneyId, String luckyMoney,String commitMode){
		
		Map<String, Object> returnContent = new  HashMap<String, Object>();
		
		TblOrder  tblOrder = new TblOrder();
		/*待处理订单*/
		TblPendingOrder tblPendingOrder = new TblPendingOrder();
//		/*根据设备硬件id获取设备信息*/
//		BasicsMachine bm = basicsMachineMapper.getMachinebyDeviceCode(deviceCode);
		String orderNum = "10"+getOrderNum();
		returnContent.put("orderNum", orderNum);
		tblOrder.setOrderNum(orderNum);
		/*下单类型0为预售*/
		tblOrder.setOrderType("0");
		tblOrder.setMachineId(Long.valueOf(machineId));
		tblOrder.setCompanyId(CompanyId);
		tblOrder.setModeNum(commitMode);
		tblOrder.setMobile(mobile);
		tblOrder.setCustomerId(Long.valueOf(customerId));
		Date orderTime = new Date();
		tblOrder.setOrderTime(orderTime);
		returnContent.put("orderTime", DateFormat.dateFormatYMDHMS(orderTime));
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
			returnContent.put("totalAmount",totalAmount);
			/*使用红包*/
//			if (StringUtils.isNoneBlank(luckMoneyId, luckyMoney)){
//				BigDecimal discountPrice = new BigDecimal(luckyMoney);
//				BigDecimal realPrice = totalPrice.subtract(discountPrice);
//				/*判断实际金额是否小于等于0*/
//				if (realPrice.signum() <= 0){
//					tblOrder.setRealMoney(BigDecimal.ZERO);
//					isZero = Boolean.TRUE;
//					returnContent.put("totalAmount", "0");
//				}else {
//					tblOrder.setRealMoney(realPrice);
//					returnContent.put("totalAmount", realPrice.toString());
//				}
//				tblOrder.setDiscountMoney(discountPrice);
//				/*更新红包状态*/
//				tblLuckyMoneyMapper.updateIsUsedById(Long.valueOf(luckMoneyId), "1");
//				/*新增红包使用记录*/
//				TblLuckyMoneyUsedLog tblLuckyMoneyUsedLog = new TblLuckyMoneyUsedLog();
//				tblLuckyMoneyUsedLog.setOrderNum(orderNum);
//				tblLuckyMoneyUsedLog.setCustomerId(Long.valueOf(customerId));
//				tblLuckyMoneyUsedLog.setCustomerMobile(mobile);
//				tblLuckyMoneyUsedLog.setLuckyMoneyId(Long.valueOf(luckMoneyId));
//				tblLuckyMoneyUsedLog.setCreateTime(new Date());
//				
//				tblLuckyMoneyUsedLogMapper.insertSelective(tblLuckyMoneyUsedLog);
//			}else {
//				tblOrder.setRealMoney(totalPrice);
//				returnContent.put("totalAmount", totalPrice.toString());
//			}
		}
         if("".equals(discountMoney)){
        	 tblOrder.setRealMoney(totalPrice);
        	 tblOrder.setDiscountMoney(BigDecimal.ZERO);
         }
		tblOrder.setTotalMoney(totalPrice);

		/*支付截止时间*/
		Date lastPayTime = getDeviceLastPayTime(orderTime);
		tblOrder.setLastPayTime(lastPayTime);
		/*下单方式app下单*/
		tblPendingOrder.setModeNum(commitMode);
		
		/*若实际支付金额为0，则订单状态直接为已支付未取货*/
		if (isZero == Boolean.TRUE){
			tblOrder.setOrderStatus(OrderStatus.NO_TAKED.value);
			OdrStatus = OrderStatus.NO_TAKED.value;
			tblPendingOrder.setPendingTypeNum("3");
		}else {
			tblOrder.setOrderStatus(OrderStatus.NO_PAY.value);
			OdrStatus = OrderStatus.NO_PAY.value;
			tblPendingOrder.setPendingTypeNum("1");
		}
		
        /*保存父订单*/
		tblOrderMapper.insertSelective(tblOrder);
		
		/*保存待付款订单列表*/
		tblPendingOrder.setCompanyId(CompanyId);
		tblPendingOrder.setOrderId(tblOrder.getOrderId());
		tblPendingOrder.setOrderNum(orderNum);
		//支付订单号//
		tblPendingOrder.setOrderTime(orderTime);
		tblPendingOrder.setOrderType("0");
		tblPendingOrder.setLastPayTime(lastPayTime);
		
		tblPendingOrderMapper.insertSelective(tblPendingOrder);
		
		return returnContent;
	}
	/**
	 * 保存订单明细
	 */
	private TblOrderDetail saveOrderDetail(Long OrderChildId, String OrderDetailNum, BasicsMachine bm, 
			JSONObject orderItemJson){

			String goodsId = orderItemJson.getString("goodsId");
			String goodsNum = orderItemJson.getString("goodsNum");
			/*根据goodsId获取商品*/
			BasicsGoods bg = basicsGoodsMapper.selectByPrimaryKey(Long.valueOf(goodsId));
			/*保存订单明细*/
			TblOrderDetail tblOrderDetail = new TblOrderDetail();
			tblOrderDetail.setOrderChildId(OrderChildId);
			tblOrderDetail.setOrderDetailNum(OrderDetailNum);
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
//			/*锁定单品*/
//			lockItem(deviceCode, goodsId, goodsNum, orderTime, endTime, orderId, tblOrderDetail.getOrderDetailId(), orderNum);
		return tblOrderDetail;
	}
	/**
	 * 
	 * @param openId
	 */
	private void weiXin(String openId,String machineId){
		TblWeixinAccess record = tblWeixinAccessMapper.findByOpenId(openId);
		if(record != null){
			/*用户存在修改*/
			record.setMachineId(Long.valueOf(machineId));
			record.setAccessTime(new Date());
			record.setWeixinOpenId(openId);
			tblWeixinAccessMapper.updateByPrimaryKey(record);
		}else{ 
			/*不存在添加*/
			record = new TblWeixinAccess(); 
			record.setMachineId(Long.valueOf(machineId));
			record.setAccessTime(new Date());
			record.setWeixinOpenId(openId);
			tblWeixinAccessMapper.insert(record);
		}
		
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
	private Date getStringDate(String time) throws ParseException{
		SimpleDateFormat format =new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date date = null;
//		try {
			date = format.parse(time);
//		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return date;
	}
	private String getStringDateyymmdd(String time){
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		String strdate = null; 
		try {
			date = format.parse(time);
			strdate = format.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strdate;
	}
	private Date getDateyymmdd(String time){
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
			try {
				date = format.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return date;
	}
	public Map<String, Object> returnstocknum(){
		return	returnmap;
	}
	/*//获取预定截止时间
	public Date getRefundEndTime(String time,String machineId) throws ParseException{
		BasicsMachine basicsMachine = basicsMachineMapper.selectByMachineId(Long.valueOf(machineId));
		TblPreissueEndTime tblPreissueEndTime = tblPreissueEndTimeMapper
				.selectByCompanyId(Long.valueOf(basicsMachine.getCompanyId()));
		   
		SimpleDateFormat formathhmmss =new SimpleDateFormat("HH:mm:ss");
		
		String hhmmss = formathhmmss.format(tblPreissueEndTime.getPreissueEndTime());
		Date date = null;
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
   		Calendar c = Calendar.getInstance();  
		c.setTime(format.parse(time));
		c.add(Calendar.DATE, -1);
   		Date yyMMdd = c.getTime();
   		String yyMMddHHmmss = format.format(yyMMdd)+" "+hhmmss;
   		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   		date = format1.parse(yyMMddHHmmss);
		                
   		
   		return date;
		
	}*/
	//获取购买截止时间   flag 0：预定截止时间  1：退款截止时间 
	public Date getRefundEndTime(String time,String areaModelId,Integer flag) throws ParseException{
		
		BasicsAreaModel BasicsAreaModel = asicsAreaModelMapper.selectByPrimaryKey(Long.valueOf(areaModelId));
        if(BasicsAreaModel == null){
        	return null;
        }
        
        Date date = null;
        SimpleDateFormat formatyymmddhhmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //早餐
		if("0".equals(BasicsAreaModel.getTimeType())){
			String hhmmss = null;
			if(flag == 0){
				hhmmss = "02:55:00";
			}else{
				hhmmss = "03:00:00";
			}
			 
			SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			String yyyymmdd = format.format(format.parse(time));
	   		String yyMMddHHmmss = yyyymmdd+" "+hhmmss;
	   		date = formatyymmddhhmmss.parse(yyMMddHHmmss);
	   		
		}else{
			//1：中餐，2休闲，3晚餐   补货前一小时
	   		Calendar c = Calendar.getInstance();  
			c.setTime(formatyymmddhhmmss.parse(time));
			if(flag == 0){
				c.add(Calendar.MINUTE, -65);
			}else{
				c.add(Calendar.MINUTE, -60);
			}
			
			date = c.getTime();
			
		}
		
		                
   		
   		return date;
		
	}
}
