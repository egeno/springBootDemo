package com.qjkj.qjcsp.service.goods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.ModelCleanGoodsVo;
import com.qjkj.qjcsp.entity.TblGoodsRetailNum;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineCellMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUserRoleMapper;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.ModelCleanGoodsMapper;
import com.qjkj.qjcsp.mapper.StokGoodListMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailNumMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailTemporaryMapper;
import com.qjkj.qjcsp.mapper.TblIssueMapper;
import com.qjkj.qjcsp.mapper.TblMachineAreaModelMostSaleMapper;
import com.qjkj.qjcsp.mapper.TblMachineTemplateRetailDateMapper;
import com.qjkj.qjcsp.mapper.TblRetailMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.DateTimeUtil;
import com.qjkj.qjcsp.util.DateUtils;

import net.sf.json.JSONObject;

/**
 * 
 * 零售商品相关通用业务方法
 */
@Service
public class GoodsCommonRetailService {

	@Autowired
	private TblGoodsRetailNumMapper tblGoodsRetailNumMapper;

	@Autowired
	private TblGoodsRetailTemporaryMapper tblGoodsRetailTemporaryMapper;

	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;

	@Autowired
	private TblMachineAreaModelMostSaleMapper tblMachineAreaModelMostSaleMapper;

	@Autowired
	private BasicsMachineMapper basicsMachineMapper;

	@Autowired
	private TblRetailMapper tblRetailMapper;
	
	@Autowired 
	private BasicsAreaModelMapper basicsAreaModelMapper;

	// @Autowired
	// private TblPreissueEndTimeMapper tblPreissueEndTimeMapper;

	@Autowired
	private ModelCleanGoodsMapper modelCleanGoodsMapper;

	@Autowired
	private TblMachineTemplateRetailDateMapper tblMachineTemplateRetailDateMapper;

	@Autowired
	private BasicsMachineCellMapper basicsMachineCellMapper;

	@Autowired
	private BasicsUserRoleMapper basicsUserRoleMapper;

	@Autowired
	private StokGoodListMapper stokGoodListMapper;
	
	@Autowired
    private BasicsUsersMapper basicsUsersMapper;
	
	@Autowired
    private TblIssueMapper tblIssueMapper;
	// private final String breakfastPreissueEndTime = "03:00:00";//预定截止时间

	/**
	 * 删除零售
	 * 
	 * @param id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delGoodsRetail(Long id) {
		/* 逻辑删除零售信息 */
		tblGoodsRetailNumMapper.deleteById(id);
		/* 删除零售分配到的格子信息 */
		tblGoodsRetailTemporaryMapper.delByGoodsRetailNumId(id);
	}

	/**
	 * 是否允许修改零售数量
	 * 
	 * @param id
	 *            零售id
	 * @param addCellNum
	 *            新增数量
	 * @return isAllowSymbol 1：允许修改；2：当前数量超过设置的零售最高份数；3：剩余可分配数量不足
	 */
	public int isAllowModification(Long id, int addCellNum) {
		/* 1：允许修改；2：当前数量超过设置的最高份数；3：剩余可分配数量不足 */
		int isAllowSymbol = 1;
		TblGoodsRetailNum good = tblGoodsRetailNumMapper.selectByPrimaryKey(id);
		/* 剩余格子数 */
		int remainingCellNum = calculateRemainingCellNum(good.getMachineId(), good.getAreaModelId(),
				good.getReplenishmentTime());
		/* 是否超过最高份数 */
		boolean isMoreThanSymbol = isMoreThanMostSaleNum(good.getMachineId(), good.getAreaModelId(),
				good.getReplenishmentTime(), addCellNum);
		if (isMoreThanSymbol == Boolean.TRUE) {
			isAllowSymbol = 2;
		} else {
			if (addCellNum > remainingCellNum) {
				isAllowSymbol = 3;
			}
		}

		return isAllowSymbol;
	}

	/**
	 * 是否允许修改零售数量
	 * 
	 * @param machineId
	 *            设备id
	 * @param areaModelId
	 *            模型id
	 * @param replenishmentTime
	 *            补货时间 格式："yyyy-MM-dd"
	 * @param addCellNum
	 *            新增数量
	 * @return isAllowSymbol 1：允许修改；2：当前数量超过设置的最高份数；3：剩余可分配数量不足
	 */
	public int isAllowModification(Long machineId, Long areaModelId, Date replenishmentTime, int addCellNum) {
		/* 1：允许修改；2：当前数量超过设置的最高份数；3：剩余可分配数量不足 */
		int isAllowSymbol = 1;
		/* 剩余格子数 */
		int remainingCellNum = calculateRemainingCellNum(machineId, areaModelId, replenishmentTime);
		/* 是否超过最高份数 */
		boolean isMoreThanSymbol = isMoreThanMostSaleNum(machineId, areaModelId, replenishmentTime, addCellNum);
		if (isMoreThanSymbol == Boolean.TRUE) {
			isAllowSymbol = 2;
		} else {
			if (addCellNum > remainingCellNum) {
				isAllowSymbol = 3;
			}
		}

		return isAllowSymbol;
	}

	/**
	 * 零售数量更新
	 * 
	 * @param id
	 *            零售id
	 * @param addCellNum
	 *            新增数量，可以为负数
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addGoodsRetail(Long id, int addCellNum) {
		/* 获取零售信息 */
		TblGoodsRetailNum good = tblGoodsRetailNumMapper.selectByPrimaryKey(id);
		/* 获取分配到的格子 */
		List<Map<String, Object>> allocatedMachineCellList = tblGoodsRetailTemporaryMapper.getAllocatedMachineCell(
				good.getMachineId(), good.getAreaModelId(), good.getReplenishmentTime(), addCellNum);
		/* 未找到格子 */
		if (allocatedMachineCellList != null) {
			tblGoodsRetailTemporaryMapper.addGoodsRetailList(good.getId(), good.getMachineId(), good.getAreaModelId(),
					good.getReplenishmentTime(), good.getGoodsId(), allocatedMachineCellList);
			/* 更新零售信息 */
			tblGoodsRetailNumMapper.updateGoodsNumById(id, addCellNum);
		}
	}

	/**
	 * 零售数量增加
	 * 
	 * @param id
	 *            零售id
	 * @param addCellNum
	 *            新增数量，可以为负数
	 */
	public void addNewGoodsRetail(Long id, int addCellNum) {
		/* 获取零售信息 */
		TblGoodsRetailNum good = tblGoodsRetailNumMapper.selectByPrimaryKey(id);
		/* 获取分配到的格子 */
		allocatedGoodsRetail(good.getMachineId(), good.getAreaModelId(), good.getReplenishmentTime(), good.getId(),
				good.getGoodsId(), addCellNum);
	}

	/**
	 * 为零售商品分配格子
	 * 
	 * @param machineId
	 *            设备id
	 * @param areaModelId
	 *            模型id
	 * @param replenishmentTime
	 *            补货时间
	 * @param id
	 *            零售商品信息自增id
	 * @param goodId
	 *            商品id
	 * @param addCellNum
	 *            新增数量
	 */
	@Transactional(rollbackFor = Exception.class)
	private void allocatedGoodsRetail(Long machineId, Long areaModelId, Date replenishmentTime, Long id, Long goodsId,
			int addCellNum) {
		List<Map<String, Object>> allocatedMachineCellList = tblGoodsRetailTemporaryMapper
				.getAllocatedMachineCell(machineId, areaModelId, replenishmentTime, addCellNum);
		tblGoodsRetailTemporaryMapper.addGoodsRetailList(id, machineId, areaModelId, replenishmentTime, goodsId,
				allocatedMachineCellList);
	}

	/**
	 * 零售数量减少
	 * 
	 * @param id
	 *            零售id
	 * @param addCellNum
	 *            新增数量，可以为负数
	 */
	@Transactional(rollbackFor = Exception.class)
	public void reduceGoodsRetail(Long id, int addCellNum) {
		tblGoodsRetailTemporaryMapper.reduceGoodsRetail(id, -addCellNum);
		/* 更新零售信息 */
		tblGoodsRetailNumMapper.updateGoodsNumById(id, addCellNum);
	}

	/**
	 * 计算剩余格子数
	 * 
	 * @param machineId
	 * @param areaModelId
	 * @param replenishmentTime
	 * @return
	 */
	public int calculateRemainingCellNum(Long machineId, Long areaModelId, Date replenishmentTime) {
		/* 最终零售格子数 */
		int remainingCellNum = 0;
		
		/* 零售最高可售份数 */
		int totalCellNum = tblMachineAreaModelMostSaleMapper.getMostSaleNum(machineId, areaModelId);
		/* 零售分配格子数 */
		int retailCellNum = tblGoodsRetailNumMapper.getRetailCellNum(machineId, areaModelId, replenishmentTime);
		/* 预定分配格子数 */
		int orderCellNum = tblTemporaryRetailMapper.getOrderCellNum(machineId, areaModelId, replenishmentTime);
		/* 设备有效份数 */
		int  effectiveNum = tblTemporaryRetailMapper.getEffectiveNum(machineId);
		
		/* 剩余格子数 */
		int surplusNum=0;
		surplusNum=	effectiveNum-orderCellNum;
		if( surplusNum <= totalCellNum){
			/*预定后剩下的格子数比零售最高分数小就把剩下的格子数作为零售最高份数*/
			remainingCellNum = surplusNum - retailCellNum;
		}else{
			remainingCellNum = totalCellNum- retailCellNum;
		}
//		int remainingCellNum = totalCellNum - retailCellNum - orderCellNum;
     if(remainingCellNum < 0){
    	 remainingCellNum = 0;
     }
		return remainingCellNum;
	}

	/**
	 * 数量是否超过最高份数
	 * 
	 * @param machineId
	 * @param areaModelId
	 * @param replenishmentTime
	 * @param addCellNum
	 * @return
	 */
	private boolean isMoreThanMostSaleNum(Long machineId, Long areaModelId, Date replenishmentTime, int addCellNum) {
		boolean isMoreThanSymbol = Boolean.FALSE;
		/* 剩余格子数 */
		int remainingCellNum = calculateRemainingCellNum(machineId, areaModelId, replenishmentTime);
		if ((remainingCellNum - addCellNum) < 0) {
			isMoreThanSymbol = Boolean.TRUE;
		}

		return isMoreThanSymbol;
	}

	/**
	 * 根据百分比计算零售商品
	 * 
	 * @param machineId
	 * @param areaModelId
	 * @param replenishmentTime
	 * @return
	 */
	public Map<String, Object> getGoodsNumInPercentageTerm(Long machineId, Long areaModelId, Date replenishmentTime) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		int count = tblGoodsRetailNumMapper.getCountByMachineIdAndAreaModelId(machineId, areaModelId,
				replenishmentTime);
		/* 未初始化 */
		if (count == 0) {
			/* 获取最高份数 */
			Integer mostSaleNum = tblMachineAreaModelMostSaleMapper.getMostSaleNum(machineId, areaModelId);
			if (mostSaleNum == null) {
				returnData.put("code", "0");
				returnData.put("content", "请先到模版设置中为当前设备添加该模型最高份数");
			} else {
				/* 根据日期得到周几 */
				int retailDate = DateTimeUtil.getDayOfWeek(replenishmentTime);
				Long templateId = tblMachineTemplateRetailDateMapper.getTemplateIdByMachineIdAndRetailDate(machineId,
						retailDate);
				
				/*只生成设备关联商户的菜*/
				//根据设备id查询出商户列表
				List<BasicsUsers> users= basicsUsersMapper.findUsersByMachineId(Long.valueOf(machineId),"3");
				List<String> userNames=null;
				if (users != null && users.size() > 0) {
					userNames = new ArrayList<String>();
					for (BasicsUsers basicsUsers : users) {
						userNames.add(basicsUsers.getUserName());
					}
				}
				//根据商户名字得到相关的菜
				List<Long> list = tblIssueMapper.selectIssueByNames(userNames);
				List<Long> goodIds = new ArrayList<Long>();
				for (Long goodId : list) {
					goodIds.add(goodId);
				}
				/*只生成设备关联商户的菜*/
				
				/* 根据模版id和模型id获取零售商品百分比 */
				List<Map<String, Object>> retailList = tblRetailMapper.queryRetailByTemplateIdAndModelId(templateId,
						areaModelId,goodIds);
				if (retailList == null || retailList.size() < 1) {
					returnData.put("code", "0");
					returnData.put("content", "当前设备未添加周" + retailDate + "模版，或当前设备未关联菜品对应的商户");
				} else {
					/* 初始化 */ 
					returnData = initializeGoodsRetail(machineId, areaModelId, replenishmentTime, retailList);
				}
			}
		} else {
			returnData.put("code", "1");
			returnData.put("content", "已经完成初始化，不做处理");
		}

		return returnData;
	}

	/**
	 * 初始化零售商品
	 * 
	 * @param machineId
	 * @param areaModelId
	 * @param replenishmentTime
	 * @param retailList
	 */
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> initializeGoodsRetail(Long machineId, Long areaModelId, Date replenishmentTime,
			List<Map<String, Object>> retailList) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		/* 剩余格子数 */
		int remainingCellNum = calculateRemainingCellNum(machineId, areaModelId, replenishmentTime);
		if (remainingCellNum == 0) {
			returnData.put("code", "0");
			returnData.put("content", "全部格子都已被预定，无法添加零售商品");
		} else {
			int count = 0;
			int sum = 0;
			/* 遍历零售商品百分比列表 */
			for (int i = 0; i < retailList.size(); i++) {
				Map<String, Object> retail = retailList.get(i);
				TblGoodsRetailNum tblGoodsRetailNum = new TblGoodsRetailNum();
				/* 计算该商品数量，向下取整 */
				short goodsNum = ((Double) Math.floor(remainingCellNum * ((Double) retail.get("retailPercent"))))
						.shortValue();
				/* 当前已分配格子总和 */
				sum += goodsNum;
				if (goodsNum != 0 && sum <= remainingCellNum) {
					tblGoodsRetailNum.setMachineId(machineId);
					tblGoodsRetailNum.setGoodsId((Long) retail.get("goodsId"));
					tblGoodsRetailNum.setGoodsName(retail.get("goodsName").toString());
					tblGoodsRetailNum.setGoodsNum(goodsNum);
					tblGoodsRetailNum.setAreaModelId(areaModelId);
					tblGoodsRetailNum.setReplenishmentTime(replenishmentTime);

					tblGoodsRetailNumMapper.insertSelective(tblGoodsRetailNum);
					/* 分配格子 */
					allocatedGoodsRetail(machineId, areaModelId, replenishmentTime, tblGoodsRetailNum.getId(),
							(Long) retail.get("goodsId"), Integer.valueOf(goodsNum));

					count++;
				}
			}
			if (count != 0) {
				returnData.put("code", "1");
				returnData.put("content", "已完成初始化");
			} else {
				returnData.put("code", "0");
				returnData.put("content", "剩余格子数量不足");
			}
		}

		return returnData;
	}

	/**
	 * 获取补货时间
	 * 
	 * @param areaModelId
	 * @return
	 */
	// public Map<String, Object> getReplenishmentTime(Long areaModelId){
	// Map<String, Object> returnData = new HashMap<String, Object>();
	// /*当前时间*/
	// Date currentTime = new Date();
	// /*获取该模型补货时间*/
	// Date completeSupplyEndTime = null;
	// returnData = getSupplyEndTime(areaModelId);
	// if (StringUtils.equals(returnData.get("code").toString(), "0")){
	// return returnData;
	// } else {
	// completeSupplyEndTime = (Date)returnData.get("content");
	// }
	// /*若当前时间在补货时间之前，则返回今日时间*/
	// if (completeSupplyEndTime.after(currentTime)){
	// returnData.put("content", DateTimeUtil.yyyyMMdd.format(currentTime));
	//
	// return returnData;
	// }
	// /*获取预定截止时间*/
	// UserUtils user = new UserUtils();
	// Date preissueEndTime =
	// tblPreissueEndTimeMapper.queryPreissueEndTimeByCompanyId(user.getCompanyId());
	// if (preissueEndTime == null){
	// returnData.put("code", "0");
	// returnData.put("content", "预定截止时间未设置，请联系管理员");
	//
	// return returnData;
	// }
	// /*预定完整截止时间*/
	// Date orderEndTime =
	// DateTimeUtil.getCompleteTimeByHHmmss(preissueEndTime);
	// /*若当前时间在预定截止时间之后，则返回明日时间*/
	// if (orderEndTime.after(currentTime)){
	// returnData.put("code", "0");
	// returnData.put("content", "预定尚未结束，无法查看明日采购清单");
	// } else {
	// returnData.put("content", DateTimeUtil.getTomorrowYYYYMMDD());
	// }
	//
	// return returnData;
	// }
	public Map<String, Object> getReplenishmentTime(Long areaModelId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		/* 当前时间 */
		Date currentTime = new Date();
		/* 预定完整截止时间 */
		Date todayOrderEndTime = null;
		String timeType = basicsAreaModelMapper.getTimeTypeByAreaModelId(areaModelId);
		ModelCleanGoodsVo modelCleanGoodsVo = modelCleanGoodsMapper.getTimeByModelId(areaModelId);
		if (modelCleanGoodsVo == null || modelCleanGoodsVo.getReserveEndTime() == null) {
			returnData.put("code", "0");
			returnData.put("content", "请设置模型的相关时间");
			return returnData;
		}
		todayOrderEndTime = DateTimeUtil.getTodayTimeByHHmmss(modelCleanGoodsVo.getReserveEndTime());
		// if (StringUtils.equals(timeType,
		// AreaModelTimeTypeEnum.BREAKFAST.getValue())){
		// todayOrderEndTime =
		// DateTimeUtil.getTodayTimeByHHmmss(breakfastPreissueEndTime);
		// } else {
		// todayOrderEndTime =
		// DateTimeUtil.getTodayTimeAnHourBeforeByHHmmss(modelCleanGoodsMapper.getSupplyEndTimeByAreaModelId(areaModelId));
		// }
		if (currentTime.after(todayOrderEndTime)) {
			/* 当前时间在当日预定截止时间之后 */
			Date completeSupplyEndTime = null;
			/* 获取该模型补货时间 */
			returnData = getSupplyEndTime(areaModelId);
			if (StringUtils.equals(returnData.get("code").toString(), "0")) {
				return returnData;
			} else {
				completeSupplyEndTime = (Date) returnData.get("content");
			}
			/* 判断当前时间与补货时间前后关系 */
			if (completeSupplyEndTime.after(currentTime)) {
				returnData.put("content", DateTimeUtil.yyyyMMdd.format(currentTime));
			} else {
				returnData.put("code", "0");
				returnData.put("content", "补货时间已过, 无法修改查看今日采购清单");
			}
		} else {
			/* 当前时间在当日预定截止时间之前 */
			returnData.put("code", "0");
			returnData.put("content", "预定尚未结束，无法查看今日采购清单");
		}

		return returnData;
	}

	/**
	 * 获取该模型今日补货时间
	 * 
	 * @param areaModelId
	 * @return
	 */
	public Map<String, Object> getSupplyEndTime(Long areaModelId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Date supplyEndTime = null;
		/* 获取该模型补货时间列表 */
		List<ModelCleanGoodsVo> cleanGoodsList = modelCleanGoodsMapper.queryModelCleanGoodsByModelId(areaModelId);
		if (cleanGoodsList == null || cleanGoodsList.size() < 1) {
			// System.out.println("请先设置该模型的清货补货时间");
			returnData.put("code", "0");
			returnData.put("content", "请先设置该模型的清货补货时间");

			return returnData;
		} else {
			/* 判断是每天设置还是每周设置 */
			if (cleanGoodsList.size() == 1 && StringUtils.equals(cleanGoodsList.get(0).getType(), "allDay")) {
				supplyEndTime = cleanGoodsList.get(0).getSupplyEndTime();
			} else {
				/* 获取今日是星期几 */
				String weekOfDate = DateTimeUtil.getWeekOfDate(new Date());
				/* 遍历补货时间列表获取今日的补货时间 */
				for (ModelCleanGoodsVo modelCleanGoodsVo : cleanGoodsList) {
					String dateStr = modelCleanGoodsVo.getDateStr();
					if (dateStr.contains(weekOfDate) == Boolean.TRUE) {
						supplyEndTime = modelCleanGoodsVo.getReserveSupplyEndTime();
						break;
					}
				}
			}
		}
		/* 完整的补货时间 */
		Date completeSupplyEndTime = null;
		if (supplyEndTime == null) {
			// System.out.println("请先设置该模型今日的清货补货时间");
			returnData.put("code", "0");
			returnData.put("content", "请先设置该模型今日的清货补货时间");

			return returnData;
		} else {
			String completeSupplyEndTimeStr = DateTimeUtil.getCurrentymd() + " "
					+ DateTimeUtil.HHmmss.format(supplyEndTime);
			completeSupplyEndTime = DateTimeUtil.stringToDate(completeSupplyEndTimeStr, "yyyy-MM-dd HH:mm:ss");
			// System.out.println(completeSupplyEndTime);
		}
		returnData.put("code", "1");
		returnData.put("content", completeSupplyEndTime);

		return returnData;
	}

	/**
	 * 判断该模型预订是否结束
	 * 
	 * @param areaModelId
	 * @return
	 */
	public Map<String, Object> isOverTheReservation(JSONObject json) {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		String deviceCode = null;
		if (json.has("deviceCode")) {
			deviceCode = json.getString("deviceCode");
		}
		String areaModelId = null;
		if (json.has("areaModelId")) {
			areaModelId = json.getString("areaModelId");
		}
		String userId = null;
		if (json.has("userId")) {
			userId = json.getString("userId");
		}
		BasicsMachine basicsMachine = null;
		if (StringUtils.isNoneBlank(deviceCode, userId)) {
			basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
			if (areaModelId == null) {
				areaModelId = basicsMachine.getAreaModelId() + "";
			}
		} else {
			returnmap.put("returnCode", "0");
			returnmap.put("returnContent", "请求参数错误");
			return returnmap;
		}
		//当前时间
		Date currentTime = new Date();
		/* 预定完整截止时间 */
		//Date todayOrderEndTime = null;
		Map<String, String> timeMap = modelCleanGoodsMapper
				.getSupplyEndTimeAndCleanStartTimeByAreaModelId(Long.valueOf(areaModelId));
		// String timeType =
		// basicsAreaModelMapper.getTimeTypeByAreaModelId(Long.valueOf(areaModelId));
		// if (StringUtils.equals(timeType,
		// AreaModelTimeTypeEnum.BREAKFAST.getValue())){
		// todayOrderEndTime =
		// DateTimeUtil.getTodayTimeByHHmmss(modelCleanGoodsMapper.getTimeByModelId(Long.valueOf(areaModelId)).getReserveEndTime());
		// } else {
		// todayOrderEndTime =
		// DateTimeUtil.getTodayTimeAnHourBeforeByHHmmss(timeMap.get("supplyEndTime"));
		// }
//		if (!currentTime.before(DateTimeUtil.getCompleteTimeByHHmmss(getHms(timeMap.get("supplyEndTime"))))) {
		/* 当前模型预定补货开始时间和零售补货截止时间之间可以补货 */
		if (!(currentTime.after(DateTimeUtil.getCompleteTimeByHHmmss(getHms(timeMap.get("reserveReplenishmentStartTimeStr")))) && currentTime.before(DateTimeUtil.getCompleteTimeByHHmmss(getHms(timeMap.get("retailSupplyEndTimeStr")))))) {
			returnmap.put("returnCode", "0");
			returnmap.put("returnContent", "不在有效补货时间范围内！补货时间为"+timeMap.get("reserveReplenishmentStartTimeStr")+" - "+ timeMap.get("retailSupplyEndTimeStr") );
			return returnmap;
		}

		// 判断当前userId 是否是商户
		String specialRoleNum = basicsUserRoleMapper.getSpecialRoleNum(Long.valueOf(userId),
				basicsMachine.getMachineId());
		Long lUserId = Long.valueOf(userId);
		if ("2".equals(specialRoleNum)) {
			lUserId = null;
		}
		/* 预售list */
		List<Map<String, Object>> reserveList = stokGoodListMapper.selectStokGoodList(basicsMachine.getMachineId(),
				Long.valueOf(areaModelId), lUserId);
		/* 零售list */
		List<Map<String, Object>> retailList = tblGoodsRetailTemporaryMapper
				.selectRetailTemporaryList(basicsMachine.getMachineId(), Long.valueOf(areaModelId), lUserId);
		Integer num = 0;
		if (reserveList != null && reserveList.size() > 0) {
			num++;
		}
		if (retailList != null && retailList.size() > 0) {
			num++;
		}
		if (num == 0) {
			returnmap.put("returnCode", "0");
			returnmap.put("returnContent", "无可补商品");
			return returnmap;
		}
		returnmap.put("returnCode", "1");
		returnmap.put("returnContent", "允许补货");
		return returnmap;
	}

	public Date getHms(String time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public Map<String, Object> isOrNotClearanceTime(JSONObject json) {
		Map<String, Object> returnmap = new HashMap<String, Object>();
		String areaModelId = null;
		String deviceCode = null;
		if (json.has("areaModelId") && json.has("deviceCode")) {
			areaModelId = json.getString("areaModelId");
			deviceCode = json.getString("deviceCode");
		}else{
			returnmap.put("returnCode", "0");
			returnmap.put("returnContent", "请求参数错误");
			return returnmap;	
		}
		Date currentTime = new Date();
		// 暂使用 零售清货时间，
		//DateUtils.formatString 1.先把Date 完整时间转成 String hms  2.把String hms 转成 Date hms  
		//1.DateUtils.formatString()  2.DateUtils.formatDate();
		Date CleanStartTime = modelCleanGoodsMapper.getTimeByModelId(Long.valueOf(areaModelId)).getRetailCleanStartTime();
		Date todayOrderEndTime = DateUtils.formatDatehms(DateUtils.getDateFormat("HH:mm:ss",CleanStartTime));
		Date currentTimeHms = DateUtils.formatDatehms(DateUtils.getDateFormat("HH:mm:ss",currentTime));
		if(todayOrderEndTime == null || currentTimeHms == null){
			returnmap.put("returnCode", "0");
			returnmap.put("returnContent", "数据转换异常");
			return returnmap;
		}
		
		BasicsAreaModel  AreaModel = basicsAreaModelMapper.selectByPrimaryKey(Long.valueOf(areaModelId));
		
		//晚餐
		if("3".equals(AreaModel.getTimeType())){
			//晚
			
			Map<String, String> map = basicsAreaModelMapper.getOperateTime(deviceCode);
			if(map == null){
				returnmap.put("returnCode", "0");
				returnmap.put("returnContent", "无需清货");
				return returnmap;
			}
			String operateTime = map.get("operateTime");
			int dayNum = getDayNum(currentTime,DateUtils.formatDate(operateTime));
			if(dayNum == 0){
				returnmap.put("returnCode", "0");
				String ymd = DateTimeUtil.getTomorrowYYYYMMDD();
				String hm = DateUtils.getDateFormat("HH:mm", todayOrderEndTime);
				returnmap.put("returnContent", "不在清货时间段内，清货时间为："+ymd+" "+hm);
				return returnmap;
			}else if(dayNum == 1){
				if (todayOrderEndTime != null && currentTimeHms.after(todayOrderEndTime)) {
					returnmap.put("returnCode", "1");
					returnmap.put("returnContent", "已到清货时间，允许清货");
					return returnmap;
				}else{
					returnmap.put("returnCode", "0");
					returnmap.put("returnContent", "不在清货时间段内，清货时间为："+DateUtils.getDateFormat("HH:mm:ss",todayOrderEndTime));
					return returnmap;
				}
			}else if(dayNum >= 2){
				returnmap.put("returnCode", "1");
				returnmap.put("returnContent", "已到清货时间，允许清货");
				return returnmap;
			}
			
		}else{
			//早，中，休
			if (todayOrderEndTime != null && currentTimeHms.after(todayOrderEndTime)) {
				returnmap.put("returnCode", "1");
				returnmap.put("returnContent", "已到清货时间，允许清货");
			}else{
				Map<String, String> map = basicsAreaModelMapper.getOperateTime(deviceCode);
				if(map == null){
					returnmap.put("returnCode", "0");
					returnmap.put("returnContent", "无需清货");
					return returnmap;
				}
				String operateTime = map.get("operateTime");
				if(getDayNum(new Date(),DateUtils.formatDate(operateTime)) >= 1){
					returnmap.put("returnCode", "1");
					returnmap.put("returnContent", "已到清货时间，允许清货");
				}else{
					returnmap.put("returnCode", "0");
					returnmap.put("returnContent", "不在清货时间段内，清货时间为："+DateUtils.getDateFormat("HH:mm:ss",todayOrderEndTime));
				}
			}
			
			
		}
		return returnmap;
	}
	public int getDayNum(Date currentDate,Date operateTime){
		Calendar d1 = new GregorianCalendar();  
        d1.setTime(currentDate);  
        Calendar d2 = new GregorianCalendar();  
        d2.setTime(operateTime);  
        int days = d1.get(Calendar.DAY_OF_YEAR)- d2.get(Calendar.DAY_OF_YEAR);
        
		return days;
	}

}
