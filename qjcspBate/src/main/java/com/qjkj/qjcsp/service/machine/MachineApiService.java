package com.qjkj.qjcsp.service.machine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsAlarms;
import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsCompany;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.BasicsMachineCell;
import com.qjkj.qjcsp.entity.BasicsModelGoods;
import com.qjkj.qjcsp.entity.BasicsUserMachine;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.ModelCleanGoodsVo;
import com.qjkj.qjcsp.entity.TblAlarmInfo;
import com.qjkj.qjcsp.entity.TblItemDetail;
import com.qjkj.qjcsp.entity.TblOrder;
import com.qjkj.qjcsp.entity.TblOrderChild;
import com.qjkj.qjcsp.entity.TblOrderDetail;
import com.qjkj.qjcsp.entity.TblOrderPickDetail;
import com.qjkj.qjcsp.entity.enums.OrderStatus;
import com.qjkj.qjcsp.mapper.BasicsAlarmsMapper;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsCompanyMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineCellMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsModelGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsUserMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.ModelCleanGoodsMapper;
import com.qjkj.qjcsp.mapper.TblAlarmInfoMapper;
import com.qjkj.qjcsp.mapper.TblDeviceOrderAmountMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderChildMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblOrderMapper;
import com.qjkj.qjcsp.mapper.TblOrderPickDetailMapper;
import com.qjkj.qjcsp.mapper.TblPendingOrderMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.service.alisms.AliSmsService;
import com.qjkj.qjcsp.service.order.common.OrderCommonService;
import com.qjkj.qjcsp.service.order.common.OrderInSuspenseService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateTimeUtil;
import com.qjkj.qjcsp.util.DateUtils;
import com.qjkj.qjcsp.util.ValidateInteger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
@Transactional
@Service
public class MachineApiService {
	private static Logger logger = LoggerFactory.getLogger(MachineApiService.class);
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;

	@Autowired
	private TblAlarmInfoMapper tblAlarmInfoMapper;

	@Autowired
	private BasicsAlarmsMapper basicsAlarmsMapper;

	@Autowired
	private BasicsUserMachineMapper basicsUserMachineMapper;

	@Autowired
	private AliSmsService aliSmsService;

	@Autowired
	private BasicsUsersMapper basicsUsersMapper;

	@Autowired
	private TblDeviceOrderAmountMapper tblDeviceOrderAmountMapper;

	@Autowired
	private BasicsCompanyMapper basicsCompanyMapper;

	@Autowired
	private TblOrderPickDetailMapper tblOrderPickDetailMapper;

	@Autowired
	private OrderCommonService orderCommonService;

	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;

	@Autowired
	private BasicsMachineAreaModelMapper basicsMachineAreaModelMapper;

	@Autowired
	private TblOrderChildMapper tblOrderChildMapper;

	@Autowired
	private TblOrderMapper tblOrderMapper;

	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;

	@Autowired
	private TblItemDetailMapper tblItemDetailMapper;

	@Autowired
	private BasicsMachineCellMapper basicsMachineCellMapper;

	@Autowired
	private ModelCleanGoodsMapper modelCleanGoodsMapper;

	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;

	@Autowired
	private TblPendingOrderMapper tblPendingOrderMapper;

	/**
	 * 根据定位罗列设备列表
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Object> getMachinesByGPSLocation(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		String longitude = "";
		String latitude = "";
		if (res.has("longitude")) {
			/* 经度 */
			longitude = res.getString("longitude");
		}
		/* 纬度 */
		if (res.has("latitude")) {
			latitude = res.getString("latitude");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat yyyyMMdddateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if (StringUtils.isNoneBlank(longitude, latitude)) {
			if(!res.has("companyId")){
				res.put("companyId","");
			}
			List<Map<String, String>> deviceList = basicsMachineMapper.getMachinesByGPSLocation((Map) res);
			List<Map<String, String>> tempList = new ArrayList<Map<String, String>>();

			if (deviceList != null && deviceList.size() > 0) {
				for (Map<String, String> device : deviceList) {
					Map<String, String> deviceSalesInfo = basicsMachineMapper
							.getDeviceSalesInfo(Long.valueOf(device.get("machineId")), "0", OrderStatus.TAKED.value);

					List<Map<String, String>> areaModelList = basicsMachineMapper
							.getAreaModelId(Long.valueOf(device.get("machineId")));
					Map<String, String> CleanStartTime = null;
					// for (int i = 0; i < areaModelList.size(); i++) {
					try {
						for (Map<String, String> cleanTime : areaModelList) {
							// Map<String, String> map = areaModelList.get(i);
							// Long areaModelId =
							// Long.valueOf(map.get("areaModelId"));
							if (cleanTime != null) {
								CleanStartTime = basicsMachineMapper
										.getRetailCleanStartTime(Long.valueOf(cleanTime.get("areaModelId")));
							}
							// CleanStartTime=basicsMachineMapper.getRetailCleanStartTime(Long.valueOf(areaModelId));

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					// }
					// 获取清货时间 如果当前时间在清货时间之后就将有效份数设置为0
					Date date = null;
					String effectCapacity = "0";
					if (CleanStartTime != null) {
						try {
							Date nowDate = new Date();
							String ymd = DateUtils.getDateFormat("yyyy-MM-dd", nowDate);
							date = DateTimeUtil.getFrontFifteenMinutesDate(dateFormat.parse(ymd + " " + CleanStartTime.get("retailCleanStartTime").toString()));

							Map<String, String> map = basicsAreaModelMapper.getOperateTime(device.get("deviceCode"));
							String operateTime = null;
							int dayNum = 0;
							// 记录有没有补过货
							int flag = 0;
							if (map != null) {
								operateTime = map.get("operateTime");
								dayNum = getDayNum(new Date(), DateUtils.formatDate(operateTime));
								flag++;
							}
							if ("3".equals(CleanStartTime.get("timeType"))) {

								if (dayNum == 0 && flag > 0) {
									// Calendar cal=Calendar.getInstance();
									// cal.setTime(date);
									// cal.add(Calendar.DATE, 1);
									// date=cal.getTime();

									// 当天直接显示有效份数
									effectCapacity = deviceSalesInfo.get("effectCapacity");

								} else if (dayNum == 1) {
									// 当天的判断当前时间是不是在清货时间之后
									if (nowDate.after(date)) {
										effectCapacity = "0";
									} else {
										effectCapacity = deviceSalesInfo.get("effectCapacity");
									}

								} else if (dayNum >= 2) {
									effectCapacity = "0";
								}

							} else {
								if (dayNum == 0 && flag > 0) {
									// 当天的判断当前时间是不是在清货时间之后
									if (nowDate.after(date)) {
										effectCapacity = "0";
									} else {
										effectCapacity = deviceSalesInfo.get("effectCapacity");
									}
								} else if (dayNum > 0) {
									// 等于大于一天以上效分数为0
									effectCapacity = "0";
								}

							}

						} catch (ParseException e) {
							e.printStackTrace();
						}
					}

					device.put("effectCapacity", effectCapacity);
					//当设备故障的时候,有效份数为0 xlk
					boolean isAlarm=isAlarmMachine(Long.valueOf(device.get("machineId")));
					if(!isAlarm){
						// 有效份数为0
						device.put("effectCapacity", "0");
					}
					//当设备故障的时候,有效份数为0 xlk end
					// device.put("effectCapacity",
					// deviceSalesInfo.get("effectCapacity"));
					/* 月销售份数 */
					if (StringUtils.equals(Constants.VIRTUAL_MONTH_SALE_AMOUNT_SYMBOL, "1")) {
//						if (StringUtils.equals(deviceSalesInfo.get("monthSaleAmount"), "0")) {
							int orderAmount = tblDeviceOrderAmountMapper
									.selectOrderAmountByMachineId(Long.valueOf(device.get("machineId")));
//							device.put("monthSaleAmount", orderAmount + "");
//						} else {
							device.put("monthSaleAmount",
									(Integer.valueOf(deviceSalesInfo.get("monthSaleAmount")) + orderAmount) + "");
//						}
					} else {
						device.put("monthSaleAmount", deviceSalesInfo.get("monthSaleAmount"));
					}
					/* 分值 */
					device.put("grade", "5");

					tempList.add(device);
				}
				returnData.put("returnCode", "1");
				returnData.put("returnContent", tempList);
			} else {
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "该商户暂无设备");
			}
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}

		return returnData;
	}

	/**
	 * 获取用户附近的的设备
	 */
	public Map<String, Object> getPreissueMachineList(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		/* 经度 */
		String longitude = res.getString("longitude");
		/* 纬度 */
		String latitude = res.getString("latitude");
		/* 获取用户Id */
		String customerId = res.getString("customerId");
		try {
			if (StringUtils.isNoneBlank(longitude, latitude, customerId)) {
				List<Map<String, Object>> returnData = basicsMachineMapper.getPreissueMachineList((Map) res);
				List<Map<String, Object>> historyMachineList = basicsMachineMapper
						.getPreissueHistoryMachineList((Map) res);
				returnContent.put("returnCode", "1");
				returnContent.put("nearbyMachineList", returnData);
				returnContent.put("historyMachineList", historyMachineList);
			} else {
				returnContent.put("returnCode", "0");
				returnContent.put("nearbyMachineList", "请求参数异常");
			}
		} catch (Exception e) {
			returnContent.put("returnCode", "0");
			returnContent.put("nearbyMachineList", "服务器异常");
		}
		return returnContent;
	}

	/*
	 * 设备报警
	 */
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Map<String, Object> alarmDevice(String deviceCode, String alarmCode, String orderChildId, String operator,
			String noExec) {
		long machineId = 0;
		List<String> alarmName = new ArrayList<String>();
		Map<String, Object> returnData = new HashMap<String, Object>();
		TblAlarmInfo tblAlarmInfo = new TblAlarmInfo();
		tblAlarmInfo.setAlarmCode(alarmCode);
		tblAlarmInfo.setCreateTime(new Date());
		tblAlarmInfo.setOperationState("0");
		tblAlarmInfo.setState("0");
		tblAlarmInfo.setCheckTime(new Date());
		if (!StringUtils.isBlank(operator)) {
			tblAlarmInfo.setCheckUserId(Long.valueOf(operator));
		}
		if (basicsMachineMapper.getMachineIdByDeviceCode(deviceCode) == null) {
			tblAlarmInfo.setMachineId(machineId);
		} else {
			tblAlarmInfo.setMachineId(basicsMachineMapper.getMachineIdByDeviceCode(deviceCode));
		}
		if (orderChildId != null&&!orderChildId.equals("null")) {
			int count=tblAlarmInfoMapper.selectCountByOrderChildId(Long.valueOf(orderChildId));
			if(count>0){
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "该子订单已经故障");
				return returnData;
			}
			else{
			tblAlarmInfo.setOrderChildId(Long.valueOf(orderChildId));}
		}
		tblAlarmInfo.setNoExec(noExec);
		// 更新订单状态为10
		tblOrderChildMapper.updateAlarmOrder(orderChildId);
		// //删除轮询表
		// tblPendingOrderMapper.deleteByOrderChildId(Long.valueOf(orderChildId));
		// 保存故障信息
		int result = tblAlarmInfoMapper.insertSelective(tblAlarmInfo);
		if (result > 0) {
			// 根据故障码获取故障名称
			String[] aCode = StringUtils.split(alarmCode, "|");
			for (String code : aCode) {
				if (!code.equals("") && ValidateInteger.isInteger(code)) {
					BasicsAlarms basicsAlarms = basicsAlarmsMapper.selectByPrimaryKey(code);
					String aName = basicsAlarms.getAlarmName();
					if (!aName.isEmpty()) {
						alarmName.add(aName);
					}
				}
			}

			List<BasicsUserMachine> basicsUserMachine = null;
			BasicsMachine basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
			if (basicsMachine == null) {
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "无该设备");
				return returnData;
			}
			String addr = basicsMachine.getAddress() + "。设备名称:" + basicsMachine.getMachineName();
			basicsUserMachine = basicsUserMachineMapper.getMaitenance(basicsMachine.getMachineId());
			if (alarmCode != null && basicsUserMachine != null && addr != null) {
				for (BasicsUserMachine bum : basicsUserMachine) {
					if ("1".equals(bum.getSpecialRoleNum())) {
						long userId = bum.getUserId();
						BasicsUsers basicsUsers = basicsUsersMapper
								.selectByPrimaryKey(Long.valueOf(String.valueOf(userId)));
						if (basicsUsers != null) {
//							String cellPhone = basicsUsers.getUserMobile();
//							boolean smsResult = aliSmsService.alarmDevice(cellPhone, addr, alarmName.toString());
							boolean smsResult=false;
							String cellPhone=basicsUsers.getUserMobile();
							if(cellPhone!=null && cellPhone!=""){
								smsResult = aliSmsService.alarmDevice(cellPhone, addr, alarmName.toString());
							}

							if (smsResult) {
								returnData.put("returnContent", "");
								returnData.put("returnCode", "1");
							} else {
								returnData.put("returnContent", "发送报警验证码失败");
								returnData.put("returnCode", "0");
							}
						}
					} else {
						returnData.put("returnCode", "0");
						returnData.put("returnContent", "该设备未关联维修员");
						// return returnData;
					}
				}
			} else {
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "请求参数错误");
			}
		}
		// 故障信息保存失败
		else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "故障信息保存失败");
		}
		return returnData;
	}

	/**
	 * 获取设备温度
	 * 
	 * @param res
	 * @return
	 */
	public Map<String, Object> getTemperature(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		/* 设备硬件id */
		String deviceCode = res.getString("deviceCode");
		if (StringUtils.isNotBlank(deviceCode)) {
			Map<String, Object> returnContent = basicsMachineMapper.getTemperaturebyDeviceCode(deviceCode);
			if (returnContent != null) {
				returnData.put("returnCode", "1");
				returnData.put("returnContent", returnContent);
			} else {
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "该设备不存在");
			}
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}

		return returnData;
	}

	/**
	 * 根据设备id号获取商家详细信息
	 * 
	 * @param machineId
	 * @return
	 */
	public Map<String, Object> getCompanyDetailByMachineId(String machineId) {
		BasicsMachine basicsMachine = basicsMachineMapper.selectByPrimaryKey(Long.valueOf(machineId));

		Map<String, Object> returnData = new HashMap<String, Object>();

		// 设备存在商家
		if (basicsMachine != null) {
			BasicsCompany basicsCompany = basicsCompanyMapper
					.selectByPrimaryKey(Long.valueOf(basicsMachine.getCompanyId()));
			Map<String, String> returnContent = new HashMap<String, String>();

			returnContent.put("companyId", String.valueOf(basicsCompany.getCompanyId()));
			returnContent.put("companyName", basicsCompany.getCompanyName());
			returnContent.put("businessHours", basicsCompany.getBusinessHours());
			returnContent.put("companyTel", basicsCompany.getTel());
			returnContent.put("address", basicsMachine.getAddress());

			returnData.put("returnCode", "1");
			returnData.put("returnContent", returnContent);

		}
		// 设备无商家
		else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "该设备无商家");

		}
		return returnData;
	}

	public BasicsMachine findMachineByDeviceCode(String deviceCode) {
		return basicsMachineMapper.selectByDeviceCode(deviceCode);
	}

	/**
	 * 取餐记录
	 * 
	 * @param res
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> takingGoodsRecording(JSONObject res) {
		Map<String, Object> returnData = new HashMap<String, Object>();

		String deviceCode = null;
		if (res.has("deviceCode")) {
			deviceCode = res.getString("deviceCode");
		}
		String takedItems = null;
		if (res.has("takedItems")) {
			takedItems = res.getString("takedItems");
		}
		String orderNum = null;
		if (res.has("orderNum")) {
			orderNum = res.getString("orderNum");
		}
		String deviceFaultSymbol = null;
		if (res.has("deviceFaultSymbol")) {
			deviceFaultSymbol = res.getString("deviceFaultSymbol");
		}

		if (StringUtils.isNoneBlank(deviceCode, takedItems, orderNum) && deviceFaultSymbol != null) {
			/* 根据设备编号获取设备 */
			BasicsMachine bm = basicsMachineMapper.selectByDeviceCode(deviceCode);
			deviceFaultSymbol = StringUtils.equals(deviceFaultSymbol, "1") ? "1" : "0";
			JSONArray items = res.getJSONArray("takedItems");
			Long orderChildId = null;
			Date pickTime = new Date();
			/* 遍历取出的商品列表 */
			for (int i = 0; i < items.size(); i++) {
				JSONObject item = items.getJSONObject(i);
				/* 只取一次子订单号 */
				if (i == 0) {
					orderChildId = item.getLong("orderChildId");
				}
				/* 新增取餐记录 */
				TblOrderPickDetail topd = new TblOrderPickDetail();
				topd.setOrderDetailId(item.getLong("orderDetailId"));
				topd.setOrderChildId(item.getLong("orderChildId"));
				topd.setOrderNum(orderNum);
				topd.setMachineId(bm.getMachineId());
				topd.setCompanyId(bm.getCompanyId());
				topd.setItemId(item.getString("itemId"));
				topd.setCellId(item.getString("cellId"));
				topd.setGoodsId(item.getLong("goodsId"));
				topd.setPickTime(pickTime);
				topd.setDeviceFaultSymbol(deviceFaultSymbol);
				/*
				 * 当订单为预定订单时 2016/06/15 18:18 -------------------------------yjh
				 */
				TblOrderChild tblOrderChild = tblOrderChildMapper.selectByPrimaryKey(orderChildId);
				TblOrder tblOrder = tblOrderMapper.selectByPrimaryKey(tblOrderChild.getOrderId());
				if (tblOrder.getOrderType().equals("0")) {
					String reserveEndTimeStr = modelCleanGoodsMapper
							.getReserveEndTimeByAreaModelId(tblOrderChild.getAreaModelId());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String nowDate = sdf.format(new Date());
					Date reserveEndTime = null;
					try {
						reserveEndTime = sdf1.parse(nowDate + " " + reserveEndTimeStr);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if (tblOrderChild.getEndTime().getTime() - reserveEndTime.getTime() < 0) {
						tblTemporaryRetailMapper.delByOrderChildId(orderChildId);
					}
				}
				/*---------------------------------------------------------yjh*/
				//根据itemId与orderDetailId查找数量
				int count=tblOrderPickDetailMapper.selectcountBytopd(topd);
				//如果该单品已经在取餐记录表里面,则不添加
				if(count<1){
				tblOrderPickDetailMapper.insertSelective(topd);
				}else{
					logger.warn("该单品已经在取餐记录表里面,则不添加"+"OrderDetailId:"+topd.getOrderDetailId()+"itemId...:"+topd.getItemId());
				}
				orderCommonService.updateTakenItem(item.getString("itemId"));
			}
			/* 更新订单更新单品、格子 */
			orderCommonService.updateTakenOrderAndUpdateItem(orderChildId);

			returnData.put("returnCode", "1");
			returnData.put("returnContent", "成功");
		} else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}

		return returnData;
	}

	/**
	 * 2.1.6.我的机柜
	 */
	public Map<String, Object> getMyMachineList(JSONObject res) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		if (!res.has("userId") || !res.has("specialRoleNum")) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "缺少请求参数");
			return returnJson;
		}
		if (StringUtils.isAnyBlank(res.getString("userId"), res.getString("specialRoleNum"))) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "请求参数错误");
			return returnJson;
		}
		Long userId = res.getLong("userId");
		Long specialRoleNum = res.getLong("specialRoleNum");
		Map<String, Object> userInfo = basicsUsersMapper.getUserInfo(userId);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (specialRoleNum == 1 || specialRoleNum == 2) {
			// 查询维修或补货人员的相关柜子
			list = basicsMachineMapper.queryMachineInfoByUserId(userId);
		} else {
			// 查询商户或众包商人员的相关柜子
			list = basicsMachineMapper.queryMachineInfoByUserIdTwo(userId, specialRoleNum);
		}
		for (Map<String, Object> map : list) {
			Long machineId = Long.parseLong(map.get("machineId").toString());
			map.put("cellValidNum", String.valueOf(basicsMachineMapper.getCellValidNumByMachineId(machineId)));
			if (specialRoleNum == 3) {
				map.put("cellNotNullNum",
						String.valueOf(basicsMachineMapper.getCellNotNullNumByUserName(machineId,
								Long.valueOf(userInfo.get("companyId").toString()),
								userInfo.get("userName").toString())));
			} else {
				map.put("cellNotNullNum", String.valueOf(basicsMachineMapper.getCellNotNullNum(machineId)));
			}
		}
		returnJson.put("returnCode", "1");
		returnJson.put("returnContent", list);
		return returnJson;
	}

	public Map<String, Object> getMachineList(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		String userId = res.getString("userId");
		String specialRoleNum = res.getString("specialRoleNum");
		if (StringUtils.isNoneBlank(userId, specialRoleNum)) {
			BasicsUsers basicsUsers = basicsUsersMapper.selectByPrimaryKey(Long.valueOf(userId));
			List<Map<String, Object>> machineList = basicsMachineMapper.findMachineListByCompanyId(
					basicsUsers.getCompanyId(), Long.parseLong(userId), Long.parseLong(specialRoleNum));
			returnContent.put("returnCode", "1");
			returnContent.put("returnContent", machineList);
		} else {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "1");
		}
		return returnContent;
	}

	public Map<String, Object> getAreaModelList(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		String userId = res.getString("userId");
		String specialRoleNum = res.getString("specialRoleNum");
		if (StringUtils.isNoneBlank(userId, specialRoleNum)) {
			if (specialRoleNum.equals("1")) {
				List<Map<String, Object>> machines = basicsUserMachineMapper
						.findMachineUserByUserId(Long.valueOf(userId));
				List<Map<String, Object>> areaModels = basicsMachineAreaModelMapper
						.findAreaModelsByMachineIds(machines);
				returnContent.put("returnCode", "1");
				returnContent = AreaModelListByDate(areaModels, returnContent);
			} else {
				BasicsUsers basicsUsers = basicsUsersMapper.selectByPrimaryKey(Long.valueOf(userId));
				List<Map<String, Object>> areaModelList = basicsAreaModelMapper
						.selectAreaModelsByCompanyId(basicsUsers.getCompanyId());
				returnContent.put("returnCode", "1");
				returnContent = AreaModelListByDate(areaModelList, returnContent);
			}
		} else {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "1");
		}
		return returnContent;
	}

	/**
	 * 2.1.14 辅助方法 根据模型列表分类时间段是否当前模型
	 * 
	 * @param returnContent
	 */
	public Map<String, Object> AreaModelListByDate(List<Map<String, Object>> areaModels,
			Map<String, Object> returnContent) {
		Map<String, String> dates = new HashMap<String, String>();
		List<Map<String, Object>> times = new ArrayList<Map<String, Object>>();
		Set<String> datetimes = new TreeSet<String>();
		for (Map<String, Object> map : areaModels) {
			datetimes.add(map.get("timeType").toString());
		}
		String nowtimeType = "-1";
		Map<String, Object> time1 = new HashMap<String, Object>();
		time1.put("timeTypeId", "-1");
		time1.put("timeName", "全部");
		times.add(time1);
		Map<String, Object> time = null;
		for (String string : datetimes) {
			time = new HashMap<String, Object>();
			if ("0".equals(string)) {
				time.put("timeTypeId", "0");
				time.put("timeName", "早餐");
				dates.put("0", "3:00-10:00");
			}
			if ("1".equals(string)) {
				time.put("timeTypeId", "1");
				time.put("timeName", "午餐");
				dates.put("1", "10:00-14:30");
			}
			if ("2".equals(string)) {
				time.put("timeTypeId", "2");
				time.put("timeName", "休闲");
				dates.put("2", "14:30-18:00");
			}
			if ("3".equals(string)) {
				time.put("timeTypeId", "3");
				time.put("timeName", "晚餐");
				dates.put("3", "18:00-21:00");
			}
			times.add(time);
		}
		for (String string : dates.keySet()) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String currentDate = sdf.format(new Date());
			String dateStr = dates.get(string);
			String[] dateArr = dateStr.split("-");
			try {
				Date currDate = sdf.parse(currentDate);// 当前时间
				Date startDate = sdf.parse(dateArr[0]);// 每节开始时间
				Date endDate = sdf.parse(dateArr[1]);// 每节结束时间
				if (currDate.after(startDate) && currDate.before(endDate)) {
					nowtimeType = string;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		returnContent.put("returnContent", times);
		returnContent.put("nowTimeTypeId", nowtimeType);
		return returnContent;
	}

	/**
	 * 2.1.7.根据设备ID获取设备库存信息以及设备状况
	 */
	public Map<String, Object> getMachineRertory(JSONObject res) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		if (!res.has("userId") || !res.has("specialRoleNum") || !res.has("machineId")) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "缺少请求参数");
			return returnJson;
		}
		if (StringUtils.isAnyBlank(res.getString("userId"), res.getString("specialRoleNum"),
				res.getString("machineId"))) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "请求参数错误");
			return returnJson;
		}
		Long userId = res.getLong("userId");
		Long specialRoleNum = res.getLong("specialRoleNum");
		Long machineId = res.getLong("machineId");
		// 获取用户的用户名和公司id
		Map<String, Object> userInfo = basicsUsersMapper.getUserInfo(userId);
		if (userInfo == null) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "用户不存在");
			return returnJson;
		}
		Map<String, Object> machineInfo = basicsMachineMapper.findMachineInfoByIds(
				Long.valueOf(userInfo.get("companyId").toString()), machineId, userInfo.get("userName").toString(),
				userId, specialRoleNum);
		if (machineInfo == null) {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "设备不存在");
			return returnJson;
		}
		// 获取商品库存信息
		List<Map<String, Object>> foodList = basicsMachineMapper.getFoodList(userInfo.get("userName").toString(),
				machineId, specialRoleNum);
		machineInfo.put("goodsList", foodList);
		// 查询出商品故障信息code
		List<String> alarmCode = basicsMachineMapper.getAlarmCode(machineId);
		// 查出设备的相关数据，并合并故障信息
		Map<String, Object> getMachineInfo = basicsMachineMapper.selectMachineInfo(machineId);
		/*
		 * if(getMachineInfo==null){ getMachineInfo=new HashMap<String,
		 * Object>(); }
		 */
		if (alarmCode.size() > 0) {
			StringBuffer alarmCodes = new StringBuffer("");
			for (int i = 0; i < alarmCode.size(); i++) {
				alarmCodes.append(alarmCode.get(i));
				// alarmCodes += alarmCode.get(i);
			}
			// List<Long> codes = new ArrayList<Long>();
			String[] codes = StringUtils.split(alarmCodes.toString(), ",");
			/*
			 * for (int i = 0; i < alarmCode.split(",").length; i++) {
			 * codes.add(Long.parseLong(alarmCode.split(",")[i])); }
			 */
			// 按故障code查询出对应的故障信息
			List<String> list = basicsMachineMapper.getAlarmName(codes);
			String alarmName = "";
			for (int i = 0; i < list.size(); i++) {
				alarmName += list.get(i) + "；";
			}
			getMachineInfo.put("equipmentFailureInfo", alarmName.substring(0, alarmName.length() - 1));
		} else {
			getMachineInfo.put("equipmentFailureInfo", "");
		}
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		list2.add(getMachineInfo);
		machineInfo.put("equipmentFailureInfos", list2);
		returnJson.put("returnCode", "1");
		returnJson.put("returnContent", machineInfo);
		return returnJson;
	}

	/**
	 * 2.1.15.消息提示
	 */
	public Map<String, Object> getMessage(JSONObject res) {
		Map<String, Object> returnJson = new HashMap<String, Object>();
		if (res.has("userId") && res.has("specialRoleNum")
				&& StringUtils.isNoneBlank(res.getString("userId"), res.getString("specialRoleNum"))) {
			Long userId = res.getLong("userId");
			Integer specialRoleNum = res.getInt("specialRoleNum");
			Map<String, Object> userInfo = basicsUsersMapper.getUserInfo(userId);
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			// 维修人员，查询出他所需要维修的所有设备
			if (specialRoleNum == 1) {
				List<String> list = basicsMachineMapper.getAlarmMachineForMP(userId);
				if (list.size() > 0) {
					StringBuffer buffer = new StringBuffer("");
					for (String string : list) {
						buffer.append(string + "、");
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("messageType", "2");
					map.put("messageContent", buffer.substring(0, buffer.length() - 1) + " 等设备故障了，请您及时前往维修啦~~~");
					returnList.add(map);
					returnList.get(0).put("messageContent", "消息:" + returnList.get(0).get("messageContent"));
				}
			} else if (specialRoleNum == 4) {// 众包商
				// 查询出故障的设备
				List<String> list = basicsMachineMapper.getAlarmMachineIsNForMerchant(userId,
						Long.parseLong(userInfo.get("companyId").toString()));
				if (list.size() > 0) {
					StringBuffer buffer = new StringBuffer("");
					for (String string : list) {
						buffer.append(string + "、");
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("messageType", "2");
					map.put("messageContent", buffer.substring(0, buffer.length() - 1) + " 等设备故障啦T-T");
					returnList.add(map);
				}
				// 查询出正在维修中的设备
				List<String> list1 = basicsMachineMapper.getAlarmMachineIsCForMerchant(userId,
						Long.parseLong(userInfo.get("companyId").toString()));
				if (list1.size() > 0) {
					StringBuffer buffer1 = new StringBuffer("");
					for (String string : list1) {
						buffer1.append(string + "、");
					}
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("messageType", "2");
					map1.put("messageContent", buffer1.substring(0, buffer1.length() - 1) + " 等设备正在维修中~~");
					returnList.add(map1);
				}
				// 查询出是否需要清货和是否需要补货
				List<Map<String, Object>> isClean = basicsMachineMapper
						.isCleanGoods(Long.parseLong(userInfo.get("companyId").toString()), "");
				if (isClean.size() > 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("messageType", "1");
					StringBuffer content = new StringBuffer("清货时间到，请及时清货!");
					for (Map<String, Object> cleanInfo : isClean) {
						content.append("“" + cleanInfo.get("areaModelName") + "”请在 "
								+ dateFormat.format(cleanInfo.get("cleanStartTime")) + " 开始清货。");
					}
					map.put("messageContent", content);
					returnList.add(map);
				}
				// 查询出是否需要清货和是否需要补货
				List<Map<String, Object>> isSupply = basicsMachineMapper
						.issupplyGoods(Long.parseLong(userInfo.get("companyId").toString()), "");
				if (isSupply.size() > 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("messageType", "0");
					StringBuffer content = new StringBuffer("补货时间到，请及时补货!");
					for (Map<String, Object> supplyInfo : isSupply) {
						content.append("“" + supplyInfo.get("areaModelName") + "”请在 "
								+ dateFormat.format(supplyInfo.get("supplyEndTime")) + " 前完成补货。");
					}
					map.put("messageContent", content);
					returnList.add(map);
				}
				// 循环消息，给第一条消息加上消息两个字
				for (int i = 0; i < returnList.size(); i++) {
					if (i == 0) {
						returnList.get(i).put("messageContent", "消息:" + returnList.get(i).get("messageContent"));
					} else {
						returnList.get(i).put("messageContent", "         " + returnList.get(i).get("messageContent"));
					}
				}
			} else if (specialRoleNum == 3 || specialRoleNum == 2) {// 商户角色和补货
				// 查询出故障的设备
				List<String> list = basicsMachineMapper.getAlarmMachineIsN(userId,
						Long.parseLong(userInfo.get("companyId").toString()), userInfo.get("userName").toString());
				if (list.size() > 0) {
					StringBuffer buffer = new StringBuffer("");
					for (String string : list) {
						buffer.append(string + "、");
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("messageType", "2");
					map.put("messageContent", buffer.substring(0, buffer.length() - 1) + " 等设备故障啦T-T");
					returnList.add(map);
				}
				// 查询出正在维修中的设备
				List<String> list1 = basicsMachineMapper.getAlarmMachineIsC(userId,
						Long.parseLong(userInfo.get("companyId").toString()), userInfo.get("userName").toString());
				if (list1.size() > 0) {
					StringBuffer buffer1 = new StringBuffer("");
					for (String string : list1) {
						buffer1.append(string + "、");
					}
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("messageType", "2");
					map1.put("messageContent", buffer1.substring(0, buffer1.length() - 1) + " 等设备正在维修中~~");
					returnList.add(map1);
				}
				// 查询出是否需要清货和是否需要补货
				List<Map<String, Object>> isClean = basicsMachineMapper.isCleanGoods(
						Long.parseLong(userInfo.get("companyId").toString()), userInfo.get("userName").toString());
				if (isClean.size() > 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("messageType", "1");
					StringBuffer content = new StringBuffer("清货时间到，请及时清货!");
					for (Map<String, Object> cleanInfo : isClean) {
						content.append("“" + cleanInfo.get("areaModelName") + "”请在 "
								+ dateFormat.format(cleanInfo.get("cleanStartTime")) + " 开始清货。");
					}
					map.put("messageContent", content);
					returnList.add(map);
				}
				// 查询出是否需要清货和是否需要补货
				List<Map<String, Object>> isSupply = basicsMachineMapper.issupplyGoods(
						Long.parseLong(userInfo.get("companyId").toString()), userInfo.get("userName").toString());
				if (isSupply.size() > 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("messageType", "0");
					StringBuffer content = new StringBuffer("补货时间到，请及时补货!");
					for (Map<String, Object> supplyInfo : isSupply) {
						content.append("“" + supplyInfo.get("areaModelName") + "”请在 "
								+ dateFormat.format(supplyInfo.get("supplyEndTime")) + " 前完成补货。");
					}
					map.put("messageContent", content);
					returnList.add(map);
				}
				// 循环消息，给第一条消息加上消息两个字
				for (int i = 0; i < returnList.size(); i++) {
					if (i == 0) {
						returnList.get(i).put("messageContent", "消息:" + returnList.get(i).get("messageContent"));
					} else {
						returnList.get(i).put("messageContent", "         " + returnList.get(i).get("messageContent"));
					}
				}
			} else {
				returnJson.put("returnCode", "0");
				returnJson.put("returnContent", "角色类型不存在");
				return returnJson;
			}
			returnJson.put("returnCode", "1");
			returnJson.put("returnContent", returnList);
		} else {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "请求参数错误");
		}
		return returnJson;
	}

	/**
	 * 2.3.50.设备故障对应的格子
	 */
	public Map<String, Object> getAlarmCellNumByDeviceCode(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		Map<String, Object> map1=new HashMap<String, Object>();
		String deviceCode = res.getString("deviceCode");
		BasicsMachine basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
		//根据设备ID查出该设备最后一个故障对应的orderChildId和orderNum
		map1=tblOrderMapper.selectOrderByMachineId(basicsMachine.getMachineId());
		//根据子订单ID,查出故障格子
		List<Map<String, Object>> map =basicsMachineCellMapper.getAlarmCellNumByDeviceCode(Long.valueOf(map1.get("orderChildId").toString()));
		for (Map<String, Object> map2 : map) {
			map2.put("orderNum", map1.get("orderNum"));
			map2.put("orderChildId", map1.get("orderChildId"));
		}
		returnContent.put("returnCode", 1);
		returnContent.put("returnContent", map);
		return returnContent;
	}

	public int getDayNum(Date currentDate, Date operateTime) {
		Calendar d1 = new GregorianCalendar();
		d1.setTime(currentDate);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(operateTime);
		int days = d1.get(Calendar.DAY_OF_YEAR) - d2.get(Calendar.DAY_OF_YEAR);

		return days;
	}
	/**
	 * 2.3.51.设备故障时,开门取餐,验证码对应的格子
	 */
	public Map<String, Object> getAlarmCellNumByidCode(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		Map<String, Object> map1=new HashMap<String, Object>();
		String idCode = res.getString("idCode");
		//根据取餐验证码,查找未取餐和故障的orderChildId和orderNum
		map1=tblOrderMapper.selectOrderByIdCode(idCode);
		
		

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map<String, Object> returnData = new HashMap<String, Object>();
		/*BasicsMachine basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
		if(basicsMachine == null){ //找不到设备
			returnData.put("returnCode", "2");
			returnData.put("returnContent", "无该设备");
			return returnData;
		}*/
		TblOrderChild tblOrderChild = tblOrderChildMapper.selectByDeviceCode(map1.get("machineId").toString(), idCode);
		/*System.err.println(df.format(tblOrderChild.getBeginTime()));
		System.err.println(df.format(tblOrderChild.getEndTime()));
		System.err.println(tblOrderChild.getOrderChildNum());*/
		if (tblOrderChild != null) {
			Date currentTime = new Date();
			if (currentTime.before(tblOrderChild.getEndTime())) { //当前时间大于取餐截止时间
				
				if (StringUtils.equals(tblOrderChild.getOrderChildStatus(), OrderStatus.NO_TAKED.value)) { //订单是已支付未取货状态
					List<Map<String, Object>> orderDetail = tblOrderDetailMapper.findDetailByChildNumAndMachineId(tblOrderChild.getOrderChildNum(), map1.get("machineId").toString());
					
					for (Map<String,Object> map2 : orderDetail) {
						List<Map<String,Object>> itemList= tblItemDetailMapper.findItemDetailBydeviceCodeAndOrderNum(Long.valueOf(map2.get("orderDetailId").toString()),Long.valueOf(map1.get("machineId").toString()));
						if(itemList.size() == 0){
							//重复取餐
							List<TblItemDetail> tblItemDetail = tblItemDetailMapper.selectRepeatOrder(Long.valueOf(map1.get("machineId").toString()), idCode);
							if(tblItemDetail == null){
								returnData.put("returnCode", 2);
								returnData.put("returnContent", "订单异常");
								return returnData;
							}
							for (TblItemDetail tblItemDetail2 : tblItemDetail) {
								if(tblItemDetail2.getTakenTime() != null){
									returnData.put("returnCode", 2);
									returnData.put("returnContent", "重复取餐");
									return returnData;
								}
							}
							
							//当前设备没有可以取的餐品
							Date date = new Date();
							if(tblOrderChild.getBeginTime().after(date)){
								returnData.put("returnCode", 2);
								returnData.put("returnContent", "取餐时间为" + df.format(tblOrderChild.getBeginTime()) + "至" + df.format(tblOrderChild.getEndTime()));
								return returnData;
							}
							else{
								//子订单状态：1是已支付待取餐，3是已取餐
								//0是预定/1是零售，零售itemDetail表内不会为空
								if("1".equals(tblOrderChild.getOrderChildStatus())){
									returnData.put("returnCode", 2);
									returnData.put("returnContent", "该订单补货未完成，无法取餐，请联系工作人员");
									/*returnData.put("returnCode", "2");
									returnData.put("returnContent", "取餐时间为" + df.format(tblOrderChild.getBeginTime()) + "至" + df.format(tblOrderChild.getEndTime()));*/
									return returnData;
								}
								if("3".equals(tblOrderChild.getOrderChildStatus())){
									returnData.put("returnCode", 2);
									returnData.put("returnContent", "该订单已取");
									return returnData;
								}
							}
						}
						else{
							Map<String, String> numMap = tblItemDetailMapper.selectBuyNumAndReplenishmentNum(tblOrderChild.getOrderChildNum());
							if(!numMap.get("gNum").equals(numMap.get("bNum"))){
								returnData.put("returnCode", 0);
								returnData.put("returnContent", "该订单补货未完成，无法取餐，请联系工作人员");
								return returnData;
							}
							
							map2.put("itemList", itemList);
						}
					}
					//根据取餐验证码,查找未取餐和故障对应的格子号
					List<Map<String, Object>> map =basicsMachineCellMapper.getAlarmCellNumByidCode(idCode);
					for (Map<String, Object> map2 : map) {
						map2.put("orderNum", map1.get("orderNum"));
						map2.put("orderChildId", map1.get("orderChildId"));
					}
					returnData.put("returnCode", 1);
					returnData.put("returnContent", map);
					
				} else if (StringUtils.equals(tblOrderChild.getOrderChildStatus(), OrderStatus.TAKED.value)){
					returnData.put("returnCode", 2);
					returnData.put("returnContent", "该订单已取");
				}
				else{
					returnData.put("returnCode", 2);
					returnData.put("returnContent", "该订单状态异常");
				}
			} else {
				returnData.put("returnCode", 2);
				returnData.put("returnContent", "取餐超时");
			}
		} else {
			returnData.put("returnCode", 2);
			returnData.put("returnContent", "验证码有误");
		}

		return returnData;
	
	}
	
	/*public Map<String, Object> machineTakeByAlarm(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		String orderNum=res.getString("orderNum");
		List<Map<String, Object>> list=tblOrderChildMapper.queryOrderChildbyOrderNum(orderNum);
		for(Map<String, Object> map:list){
			orderCommonService.unlockItemTakenTimeout(Long.valueOf(map.get("orderChildId").toString()));
		}
		returnContent.put("returnCode", 1);
		return returnContent;
	}*/
	/**
	 * 判断当前设备故障状态  故障:false 正常:true  (公共方法 APP接口与微信接口都可调用)
	 */
	public boolean isAlarmMachine(Long machineId){
		Boolean flag = Boolean.FALSE;
		//根据设备ID,查找该设备最后一条故障信息
		TblAlarmInfo tblAlarmInfo=tblAlarmInfoMapper.getAlarmCellNumByDeviceCode(machineId);
		if(tblAlarmInfo==null||"Y".equals(tblAlarmInfo.getIsdel())){
			flag=Boolean.TRUE;
		}
		return flag;
	}
}