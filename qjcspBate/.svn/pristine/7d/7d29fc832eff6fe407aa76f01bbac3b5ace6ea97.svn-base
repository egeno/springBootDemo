package com.qjkj.qjcsp.service.wechatapi;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.BasicsMachineCell;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblOrderDetail;
import com.qjkj.qjcsp.entity.TblOrderPickDetail;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineCellMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.ModelCleanGoodsMapper;
import com.qjkj.qjcsp.mapper.TblCustomerInfoMapper;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.mapper.TblGoodsEvaluateMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.TblOrderPickDetailMapper;
import com.qjkj.qjcsp.mapper.order.app.OrderAppMapper;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.DateTimeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/4/13.
 */
@Service
@Transactional
public class OrderDetailWeChatService {

	private static Logger logger = LoggerFactory.getLogger(OrderDetailWeChatService.class);

	@Autowired
	private TblOrderMapper tblOrderMapper;

	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;

	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;

	@Autowired
	private BasicsMachineMapper basicsMachineMapper;

	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;

	@Autowired
	private TblCustomerMapper tblCustomerMapper;

	@Autowired
	private TblCustomerInfoMapper tblCustomerInfoMapper;
	
	@Autowired
	private OrderAppMapper orderAppMapper;
	
	@Autowired
	private ModelCleanGoodsMapper modelCleanGoodsMapper;

	@Autowired
	private TblItemDetailMapper tblItemDetailMapper;
	@Autowired
	private TblGoodsEvaluateMapper tblGoodsEvaluateMapper;
	@Autowired
	private TblOrderPickDetailMapper tblOrderPickDetailMapper;
	@Autowired
	private BasicsMachineCellMapper basicsMachineCellMapper;
	
	//获取当前系统时间的下一天
	public static Date getNextDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, +1); 
        date = calendar.getTime();  
        return date;  
    }  
	
	public static Date getNextHOUR(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
//        calendar.add(Calendar.HOUR_OF_DAY, -1); 
        date = calendar.getTime();  
        return date;  
    }  
	
	public Map<String, Object> getOrderDetailWX(String orderNum) {	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 03:00:00");
	//c.add(Calendar.HOUR, 3);
	//System.err.println(sdf1.format(OrderDetailService.getNextDay(new Date())));
	TblOrder tblOrder = tblOrderMapper.selectByOrderNum(orderNum); // selectOrderDetailsByOrderNum(orderNum);
	
	Map<String, Object> returnMap = new HashMap<String, Object>();
	// 判断tblOrder是否为空，如果为空 那么此订单号不存在，返回returnMap
	if (tblOrder == null) {
		return returnMap;
	}
	List<TblOrderDetail> tblOrderDetailList = null;
	Long orderId = tblOrder.getOrderId();	
	
//	System.err.println("orderId:"+orderId);
	//根据用户ID查询子订单详细
	List<TblOrderChild> orderChildList = tblOrderChildMapper.selectByOrderId(orderId);
//	Date date=new Date();
	
	if ("1".equals(tblOrder.getOrderStatus()) && "0".equals(tblOrder.getOrderType())) {
		tblOrder.setOrderStatus("11");
		String getorderStatus = "";
		// 将查询到的所有子订单状态组装成字符串
		for (int j = 0; j < orderChildList.size(); j++) {
			getorderStatus += orderChildList.get(j).getOrderChildStatus() + ",";
		}
		// 判断是否存在已取货并且不存在已付款状态
		/*if (getorderStatus.indexOf("3") != -1 && getorderStatus.indexOf("1") == -1) {
			// mapm.put("orderStatus", "7");
			tblOrder.setOrderStatus("7");
		}*/
		// 判断是否存在已退款并且不存在已付款状态
		if (getorderStatus.indexOf("9") != -1 && getorderStatus.indexOf("1,") == -1) {
			tblOrder.setOrderStatus("7");
		}
		// 判断是否存在取餐超时并且不存在待取餐状态
		if (getorderStatus.indexOf("4") != -1 && getorderStatus.indexOf("1,") == -1) {
			tblOrder.setOrderStatus("4");
		}
		if(getorderStatus.indexOf("10") != -1){
			tblOrder.setOrderStatus("10");
		}
		// 判断是否存在已取货并且不存在已付款状态 xlk
		if (getorderStatus.indexOf("3") != -1 && getorderStatus.indexOf("1,") == -1) {
			tblOrder.setOrderStatus("3");
		}
		//判断故障订单的所有菜品是否已取 xlk
		/*if(tblOrder.getOrderStatus().equals("10")){
			int count2=tblItemDetailMapper.selectCountByOrderId(orderId);
			if(count2==0){
				tblOrder.setOrderStatus("14");
			}
		}
		
		if(getorderStatus.indexOf("14") != -1&&getorderStatus.indexOf("1,") != -1){
			tblOrder.setOrderStatus("11");
		}*/
	} else if ("1".equals(tblOrder.getOrderStatus()) && "1".equals(tblOrder.getOrderType())) {
		/**
		 * 根据orderId获取所有子订单
		 */
		List<Map<String, Object>> listOrderChild = orderAppMapper.getOrderChildByOrderId(orderId);
		
		tblOrder.setOrderStatus((String) listOrderChild.get(0).get("orderChildStatus"));
		//判断故障订单的所有菜品是否已取 xlk
		/*if(tblOrder.getOrderStatus().equals("10")){
			int count2=tblItemDetailMapper.selectCountByOrderId(orderId);
			if(count2==0){
				tblOrder.setOrderStatus("14");
			}
		}*/
	}
	//判断故障订单的所有菜品是否已取 xlk
			if(tblOrder.getOrderStatus().equals("10")){
				//查询该订单下单品状态不等于3的数量
				int count2=tblItemDetailMapper.selectCountByOrderId(Long.valueOf(orderId));
				//如果所有菜品已取,订单状态为14(故障取餐,不能评价)
				if(count2==0){
					tblOrder.setOrderStatus("14");
					/*//根据orderNum查找该订单下全部记录与故障取餐记录的差
					int count=tblOrderPickDetailMapper.selectCountByOrderNum(orderNum);
					//如果差为0,订单状态为设备故障
					if(count==0){
						tblOrder.setOrderStatus("10");
					}*/
					//查找改订单下故障取餐的数量
					/*int count=tblOrderPickDetailMapper.selectAlarmCountByOrderNum(orderNum);
					if(count>0){
						tblOrder.setOrderStatus("10");
					}*/
				}
			}
	//判断订单的所有菜品是否已取 xlk
	if(tblOrder.getOrderStatus().equals("3")){
		int count2=tblItemDetailMapper.selectCountByOrderId(Long.valueOf(orderId));
		if(count2!=0){
			tblOrder.setOrderStatus("11");
		}
	}
	//判断改订单是否已评价 xlk
	int count1=tblGoodsEvaluateMapper.getCountByOrderId(Long.valueOf(orderId));
	if(count1>0){
		tblOrder.setOrderStatus("13");
	}
	List<Map> orderChilds = new ArrayList<Map>();
	Integer totalNum = 0;
	for (TblOrderChild tblOrderChild : orderChildList) {
		Long orderChildId = tblOrderChild.getOrderChildId();
		tblOrderDetailList = tblOrderDetailMapper.selectByOrderChildId(orderChildId);
		List<Map> orderDetails = new ArrayList<Map>();
		for (TblOrderDetail tblOrderDetail : tblOrderDetailList) {
			BigDecimal goodsNum = BigDecimal.valueOf(tblOrderDetail.getGoodsNum());
			BigDecimal price = tblOrderDetail.getPrice().divide(goodsNum, 2);
			Map<String, Object> detailMap = new HashMap<String, Object>();
			detailMap.put("orderDetailId", tblOrderDetail.getOrderDetailId().toString());
			detailMap.put("goodsId", tblOrderDetail.getGoodsId().toString());
			detailMap.put("goodsName", tblOrderDetail.getGoodsName().toString());
			detailMap.put("price", price.toString());
			// detailMap.put("price", tblOrderDetail.getPrice().toString());
			detailMap.put("dishAreaNum", tblOrderDetail.getGoodsNum().toString());
			detailMap.put("itemFee", tblOrderDetail.getPrice().toString());
			//显示顾客未取到菜品对应的格子号并显示故障 xlk 2016.08.28
			List<Map<String, Object>> cells = new ArrayList<Map<String,Object>>();
			//当子订单状态为故障时
			if("10".equals(tblOrderChild.getOrderChildStatus())){
				List<TblOrderPickDetail> tblOrderPickDetailList=tblOrderPickDetailMapper.selectListByOrderDetailId(tblOrderDetail.getOrderDetailId());
				for(TblOrderPickDetail tblOrderPickDetail:tblOrderPickDetailList){
					//当顾客没有拿到该菜品时
					if("1".equals(tblOrderPickDetail.getDeviceFaultSymbol())){
						Map<String, Object> cell=new HashMap<String, Object>();
						BasicsMachineCell basicsMachineCell=basicsMachineCellMapper.selectByPrimaryKey(tblOrderPickDetail.getCellId());
						cell.put("cellNum", basicsMachineCell.getCellNum()+"");
						cell.put("status", "故障");
						cells.add(cell);
					}
				}
			}
			detailMap.put("cells", cells);
			//显示顾客未取到菜品对应的格子号并显示故障 xlk 2016.08.28 end
			orderDetails.add(detailMap);
			//累加商品总数
			totalNum = totalNum+tblOrderDetail.getGoodsNum();
		}
		Map<String, Object> orderChildMaps = new HashMap<String, Object>();
		orderChildMaps.put("orderChildId", tblOrderChild.getOrderChildId().toString());
		orderChildMaps.put("orderChildNum", tblOrderChild.getOrderChildNum().toString());
		String code = tblOrderChild.getIdentifyingCode();
		if(code == null){
			code ="";
		}
		orderChildMaps.put("identifyingCode", code);
		//System.err.println("时间："+tblOrderChild.getSupplyEndTime());
		
		
		if ("0".equals(tblOrder.getOrderType())) {
			//预订截止时间
			/*wsk 注释的
			 * if("0".equals(tblOrderChild.getTimeType())){
				orderChildMaps.put("preissueEndTime",  sdf1.format(OrderDetailService.getNextDay(new Date())));				
			}
			else{
				String str1="yyyy-MM-dd ";
				String str2=tblOrderChild.getSupplyEndTime();
				System.err.println("0 ordertype 预订"+str2);
				SimpleDateFormat sdf2 = new SimpleDateFormat(str1+str2);				
				
				orderChildMaps.put("preissueEndTime",  sdf2.format(OrderDetailService.getNextHOUR(new Date())));
			}*/
			Date ReserveEndTime = modelCleanGoodsMapper.getTimeByModelId(tblOrderChild.getAreaModelId()).getReserveEndTime();
			orderChildMaps.put("preissueEndTime",sdf.format(tblOrderChild.getOrderPreissueLastRefundTime()));
			//查询子订单有没有补货
			Map<String, String> info = tblItemDetailMapper.selectBuyNumAndReplenishmentNum(tblOrderChild.getOrderChildNum());
			Integer count = Integer.parseInt(info.get("bNum").toString());
			orderChildMaps.put("isRefund", "0");
			if (count > 0) {
				orderChildMaps.put("isRefund", "1");
			}
			//orderChildMaps.put("preissueEndTime", sdf.format(tblOrderChild.getOrderPreissueLastRefundTime()));
            //System.err.println("时间："+sdf.format(tblOrderChild.getOrderPreissueLastRefundTime()));
		} else {
			Date ReserveEndTime = modelCleanGoodsMapper.getTimeByModelId(tblOrderChild.getAreaModelId()).getReserveEndTime();
			orderChildMaps.put("preissueEndTime",sdf.format(DateTimeUtil.getTodayTimeByHHmmss(ReserveEndTime)));
			orderChildMaps.put("isRefund", "0");
			/*wsk注解//早餐
			if("0".equals(tblOrderChild.getTimeType())){
				orderChildMaps.put("preissueEndTime",  sdf1.format(OrderDetailService.getNextDay(new Date())));				
			}
			else{//中休闲晚餐
				String str1="yyyy-MM-dd ";
				String str2=tblOrderChild.getSupplyEndTime().toString();
				System.err.println("！0 ordertype 常规"+str2);
				SimpleDateFormat sdf2 = new SimpleDateFormat(str1+str2);
				orderChildMaps.put("preissueEndTime",  sdf2.format(OrderDetailService.getNextHOUR(new Date())));
			}*/
			
			
			//sdf.format(tblOrderChild.getSupplyEndTime())
			//orderChildMaps.put("preissueEndTime", "");
			//System.err.println("时间："+sdf.format(tblOrderChild.getOrderPreissueLastRefundTime()));
		}
		//当子订单故障时,查询子订单对应的单品未取数量
		if(tblOrderChild.getOrderChildStatus().equals("10")){
			int count=tblItemDetailMapper.selectCountByOrderChildId(orderChildId);
			if(count==0){
				tblOrderChild.setOrderChildStatus("14");
				/*//根据orderNum查找该订单下全部记录与故障取餐记录的差
				int count2=tblOrderPickDetailMapper.selectCountByOrderChildId(orderChildId);
				//如果差为0,订单状态为设备故障
				if(count2==0){
					tblOrderChild.setOrderChildStatus("10");
				}*/
				//查找改订单下故障取餐的数量
				int count2=tblOrderPickDetailMapper.selectAlarmCountByOrderChildId(orderChildId);
				if(count2>0){	
					//tblOrder.setOrderStatus("10");
				tblOrderChild.setOrderChildStatus("10");
				}
			}
			}
		orderChildMaps.put("takenEndTime", sdf.format(tblOrderChild.getEndTime()));
		orderChildMaps.put("takenStartTime", sdf.format(tblOrderChild.getBeginTime()));
		orderChildMaps.put("orderChildStatus", tblOrderChild.getOrderChildStatus().toString());
		orderChildMaps.put("orderDetails", orderDetails);
		orderChildMaps.put("areaModelId", tblOrderChild.getAreaModelId().toString());
		BasicsAreaModel basicsAreaModel = basicsAreaModelMapper.selectByPrimaryKey(tblOrderChild.getAreaModelId());
		orderChildMaps.put("areaModelName", basicsAreaModel.getAreaModelName());
		orderChilds.add(orderChildMaps);
	}

	
	
	
	returnMap.put("machineId", tblOrder.getMachineId().toString());
	returnMap.put("orderNum", orderNum);
	returnMap.put("mobileNum", tblOrder.getMobile());
	returnMap.put("placeOrderTime", sdf.format(tblOrder.getOrderTime()));
	returnMap.put("nowTime", sdf.format(new Date()).toString());
	// 订单商品数量
//	returnMap.put("totalNum", tblOrderDetailList.size()+"");
	returnMap.put("totalNum", totalNum+"");
	returnMap.put("totalFee", tblOrder.getTotalMoney().toString());
	if (tblOrder.getDiscountMoney() != null) {
		returnMap.put("discountMoney", tblOrder.getDiscountMoney().toString());
	} else {
		returnMap.put("discountMoney", "0");
	}
	returnMap.put("realMoney", tblOrder.getRealMoney().toString());
	Long machineId = tblOrder.getMachineId();
	BasicsMachine basicsMachine = basicsMachineMapper.selectByMachineId(machineId);
	returnMap.put("orderType", tblOrder.getOrderType());
	returnMap.put("orderStatus", tblOrder.getOrderStatus());
	returnMap.put("address", basicsMachine.getAddress());
	returnMap.put("orderChilds", orderChilds);
	return returnMap;
	}	
	

	
	
	
	public Map<String, Object> getCustomerDetail(String customerId) {
		Map<String, String> map = tblCustomerMapper.selectByPrimaryKeyMap(Long.valueOf(customerId));
		Map<String, String> tblCustomerInfoMap = tblCustomerInfoMapper.selectByPrimaryKeyMap(Long.valueOf(customerId));

		Map<String, Object> returnData = new HashMap<String, Object>();

		// 手机用户存在
		if (map != null) {
			Map<String, String> returnContent = new HashMap<String, String>();

			returnContent.put("customerId", map.get("customerId"));
			if ("".equals(map.get("customerName")) || map.get("customerName") == null) {
				returnContent.put("nickName", "匿名用户");
			} else {
				returnContent.put("nickName", map.get("customerName"));
			}
			returnContent.put("mobileNum", map.get("customerMobile"));
			returnContent.put("integral", map.get("customerIntegral"));
			returnContent.put("isNewUser", map.get("isNewUser"));

			if (tblCustomerInfoMap != null) {
				returnContent.put("sex", map.get("customerSex"));
				returnContent.put("place", tblCustomerInfoMap.get("nativePlace"));
				returnContent.put("universityName", tblCustomerInfoMap.get("universityName"));
				returnContent.put("grade", tblCustomerInfoMap.get("grade"));
				returnContent.put("dormitory", tblCustomerInfoMap.get("buildingName"));
				returnContent.put("studentCard", tblCustomerInfoMap.get("studentNum"));
			}
			
			returnData.put("returnCode", "1");
			returnData.put("returnContent", returnContent);

			return returnData;
		} else {
			// 手机用户不存在
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "手机用户不存在");
			return returnData;
		}
	}

	/**
	 * 多条件查询商品预订数量
	 */
	public Map<String, Object> queryFoodsPreNum(String startTime, String endTime, Long machineId, Long areaModelId,
			String foodName, int pageNumber, int pageSize) {
		Long companyId = Long.parseLong(Constants.getCurrendUser().getCompanyId().toString());
		List<Map<String, Object>> list = tblOrderDetailMapper.queryFoodsPreNum(companyId, startTime, endTime, machineId,
				areaModelId, foodName, (pageNumber - 1) * pageSize, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", tblOrderDetailMapper.queryFoodsPreNumCount(companyId, startTime, endTime, machineId,
				areaModelId, foodName));
		return map;
	}
}
