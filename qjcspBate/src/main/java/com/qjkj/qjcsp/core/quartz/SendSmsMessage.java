package com.qjkj.qjcsp.core.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.QuartzMapper;
import com.qjkj.qjcsp.service.alisms.AliSmsService;
import com.qjkj.qjcsp.util.DateTimeUtil;
import com.qjkj.qjcsp.util.DateUtils;

@Service
public class SendSmsMessage {
	@Autowired
	private AliSmsService aliSmsService;
	@Autowired
	private QuartzMapper quartzMapper;
	@Autowired
	private BasicsUsersMapper basicsUsersMapper;

	// 清货前15-20分钟提醒
	public void preClearance() throws InterruptedException {
		List<Map<String, Object>> list = quartzMapper.clearanceRemind();

		for (int i = 0; list != null && i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String mobile = (String) map.get("mobile");
			String address = (String) map.get("address").toString();
			String identifyingCode = (String) map.get("identifyingCode");

			String endTime = (String) map.get("endTime");
			
			if (StringUtils.isNoneBlank(mobile, address, identifyingCode,
					endTime)) {
				aliSmsService.sendClearanceRemind(mobile, address,
						identifyingCode, endTime);
				// Thread.sleep(3000);
			}
		}
	}

	// 补货截止前半小时提醒
	public void replenishmentBefore() throws InterruptedException {
		// 预订
		List<Map<String, String>> list = quartzMapper.getCleanGoodsInfo();
		for (int i = 0; list != null && i < list.size(); i++) {
			Map<String, String> map = list.get(i);
			String endTime = map.get("endTime");
			Long areaModelId = Long.valueOf(map.get("areaModelId"));
			// 当前模型剩余补货数
			List<Map<String, String>> listNum = quartzMapper
					.getNotReplenishmenNum(areaModelId);
			for (int k = 0; listNum != null && k < listNum.size(); k++) {
				Map<String, String> mapNum = listNum.get(k);
				String notReplenishmentNum = mapNum.get("notReplenishmentNum");

				// 当前模型完成补货
				if (Integer.parseInt(notReplenishmentNum) > 0) {
					// 根据模型id得到该模型的商户，众包，补货人员，及相关模型的时间
					List<Map<String, String>> listInfo = quartzMapper
							.getMachineInfoByModeId(areaModelId);
					for (int j = 0; listInfo != null && j < listInfo.size(); j++) {
						Map<String, String> mapInfo = listInfo.get(j);
						String mobile = mapInfo.get("mobile");
						String retailSupplyEndTime = mapInfo
								.get("retailSupplyEndTime");
						String address = mapInfo.get("address");
						if (!"".equals(mobile)) {
							aliSmsService.sendReplenishmentRemind(mobile,
									address, retailSupplyEndTime);
						}
					}
					continue;
				}
			}
		}

		// 零售
		/*
		 * List<Map<String, String>> listLs =
		 * quartzMapper.getCleanGoodsInfoRetail(); for (int i = 0; list != null
		 * && i < listLs.size(); i++) { Map<String, String> map = listLs.get(i);
		 * String endTime = map.get("endTime"); Long areaModelId =
		 * Long.valueOf(map.get("areaModelId")); //当前模型剩余补货数 List<Map<String,
		 * String>> listNum = quartzMapper.getNotReplenishmenNum(areaModelId);
		 * for (int k = 0; listNum != null && k < listNum.size(); k++) {
		 * Map<String, String> mapNum = listNum.get(k); String
		 * notReplenishmentNum = mapNum.get("notReplenishmentNum");
		 * 
		 * //当前模型完成补货 if(Integer.parseInt(notReplenishmentNum) > 0){
		 * //根据模型id得到该模型的商户，众包，补货人员，及相关模型的时间 List<Map<String, String>> listInfo
		 * = quartzMapper.getMachineInfoByModeId(areaModelId); for (int j =
		 * 0;listInfo != null && j < listInfo.size(); j++) { Map<String, String>
		 * mapInfo = listInfo.get(j); String mobile = mapInfo.get("mobile");
		 * String retailSupplyEndTime = mapInfo.get("retailSupplyEndTime");
		 * //String retailSupplyEndTime =
		 * formatterHHmm.format(mapInfo.get("retailSupplyEndTime")) ; String
		 * address = mapInfo.get("address"); if(!"".equals(mobile)){
		 * aliSmsService.sendReplenishmentRemind(mobile, address,
		 * retailSupplyEndTime); } } continue; } } }
		 */
	}

	// 早餐提醒 暂时发个本公司员工 上线后发个所有客户
	public void sendBreakfastRemind() throws InterruptedException {
		// List<String> mobiles = new ArrayList<String>();
		// //mobiles.add("18676773168");

		// 发给平台所有用户
		List<Map<String, Object>> smsList = quartzMapper.getCustomer_All();
		for (int i = 0; smsList != null && i < smsList.size(); i++) {
			Map<String, Object> smsMap = smsList.get(i);
			String mobile = (String) smsMap.get("mobile");
			aliSmsService.sendBreakfastRemind(mobile);
			// Thread.sleep(1000);
		}
	}

	// 中餐提醒 暂时发个本公司员工 上线后发个所有客户
	public void sendLunchRemind() throws InterruptedException {
		// List<String> mobiles = new ArrayList<String>();
		// mobiles.add("18676773168");

		// 发给平台所有用户
		List<Map<String, Object>> smsList = quartzMapper.getCustomer_All();
		for (int i = 0; i < smsList.size(); i++) {
			Map<String, Object> smsMap = smsList.get(i);
			String mobile = (String) smsMap.get("mobile");
			aliSmsService.sendLunchRemind(mobile);
			// Thread.sleep(1000);
		}

	}

	// 补货开始时间提醒（中餐，休闲，晚餐）
	public void replenishmentRemind() {
		Date date = new Date();
		// 得到需要发消息的模型 预订
		List<Map<String, Object>> list = quartzMapper.getAreaModelId();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String areaModelId = (String) map.get("areaModelId");
			List<Map<String, String>> InfoMap = quartzMapper
					.getMachineInfoByModeId(Long.valueOf(areaModelId));
			for (int j = 0; InfoMap != null && j < InfoMap.size(); j++) {
				Map<String, String> mapInfo = InfoMap.get(j);
				String userId = mapInfo.get("userId");
				String mobile = mapInfo.get("mobile");
				String address = mapInfo.get("address");
				String machineId = mapInfo.get("machineId");
				String specialToleNum = mapInfo.get("specialToleNum");
				if ("2".equals(specialToleNum)) {
					// 众包 补货
					int replenishmentNum = quartzMapper.getReplenishmentNum(
							Long.valueOf(machineId), Long.valueOf(areaModelId),
							null);
					if (replenishmentNum > 0) {
						String retailStartTime = mapInfo.get("retailStartTime");
						String reserveStartTime = mapInfo
								.get("reserveStartTime");
						// 拼成完整的日期yyyy MM DD hh mm ss
						String retailStartTimeymdhms = DateUtils
								.formatString(DateTimeUtil
										.getTodayTimeByHHmm(retailStartTime));
						String reserveStartTimeymdhms = DateUtils
								.formatString(DateTimeUtil
										.getTodayTimeByHHmm(reserveStartTime));

						BasicsUsers user = basicsUsersMapper
								.selectByPrimaryKey(Long.valueOf(userId));
						if (user == null) {
							// 当前用不存在结束本次循环
							continue;
						}
						Date createTime = user.getcreateTime();

						Calendar c = Calendar.getInstance();
						c.setTime(createTime);
						c.add(Calendar.DATE, 3);
						// 创建后发三天
						if (date.before(c.getTime())) {
							if (!"".equals(mobile)) {
								aliSmsService
										.sendrReplenishmentStartTimeRemind(
												mobile, address,
												retailStartTimeymdhms,
												reserveStartTimeymdhms);
							}
						}
					}
				} else if ("3".equals(specialToleNum)) {
					// 商户补货
					int replenishmentNum = quartzMapper.getReplenishmentNum(
							Long.valueOf(machineId), Long.valueOf(areaModelId),
							Long.valueOf(userId));
					// 补货数大于0发短信
					if (replenishmentNum > 0) {
						// HHmmss
						String retailStartTime = mapInfo.get("retailStartTime");
						String reserveStartTime = mapInfo
								.get("reserveStartTime");
						// 拼成完整的日期yyyy MM DD hh mm ss
						String retailStartTimeymdhms = DateUtils
								.formatString(DateTimeUtil
										.getTodayTimeByHHmm(retailStartTime));
						String reserveStartTimeymdhms = DateUtils
								.formatString(DateTimeUtil
										.getTodayTimeByHHmm(reserveStartTime));

	   					BasicsUsers user = basicsUsersMapper
								.selectByPrimaryKey(Long.valueOf(userId));
						if (user == null) {
							// 当前用不存在结束本次循环
							continue;
						}
						Date createTime = user.getcreateTime();

						Calendar c = Calendar.getInstance();
						c.setTime(createTime);
						c.add(Calendar.DATE, 3);
						// 创建后发三天
						if (date.before(c.getTime())) {
							if (!"".equals(mobile)) {
								aliSmsService
										.sendrReplenishmentStartTimeRemind(
												mobile, address,
												retailStartTimeymdhms,
												reserveStartTimeymdhms);
							}
						}

					}

				}

			}
		}

		/*
		 * //零售 List<Map<String, Object>> listLs =
		 * quartzMapper.getAreaModelIdRetail(); for (int i = 0; i <
		 * listLs.size(); i++) { Map<String, Object> map = listLs.get(i); String
		 * areaModelId = (String) map.get("areaModelId"); List<Map<String,
		 * String>> InfoMap =
		 * quartzMapper.getMachineInfoByModeId(Long.valueOf(areaModelId)); for
		 * (int j = 0;InfoMap != null && j < InfoMap.size(); j++) { Map<String,
		 * String> mapInfo = InfoMap.get(j); String userId =
		 * mapInfo.get("userId"); String mobile = mapInfo.get("mobile"); String
		 * address = mapInfo.get("address"); //HHmmss String retailStartTime =
		 * mapInfo.get("retailStartTime"); String reserveStartTime =
		 * mapInfo.get("reserveStartTime"); //拼成完整的日期yyyy MM DD hh mm ss
		 * 
		 * //String retailStartTimeymdhms =
		 * DateUtils.formatString(DateTimeUtil.getTodayTimeByHHmmss
		 * (retailStartTime)); //String reserveStartTimeymdhms =
		 * DateUtils.formatString
		 * (DateTimeUtil.getTodayTimeByHHmmss(reserveStartTime)); String
		 * retailStartTimeymdhms =
		 * DateUtils.formatString(DateTimeUtil.getTodayTimeByHHmm
		 * (retailStartTime)); String reserveStartTimeymdhms =
		 * DateUtils.formatString
		 * (DateTimeUtil.getTodayTimeByHHmm(reserveStartTime));
		 * 
		 * BasicsUsers user =
		 * basicsUsersMapper.selectByPrimaryKey(Long.valueOf(userId)); if(user
		 * == null){ //当前用不存在结束本次循环 continue; } Date createTime =
		 * user.getcreateTime();
		 * 
		 * Calendar c = Calendar.getInstance(); c.setTime(createTime);
		 * c.add(Calendar.DATE, 3); //创建后发三天 if(date.before(c.getTime())){
		 * if(!"".equals(mobile)){
		 * aliSmsService.sendrReplenishmentStartTimeRemind(mobile, address,
		 * retailStartTimeymdhms, reserveStartTimeymdhms); } } } }
		 */
	}

	// 补货开始时间提醒（早餐）
	public void breakReplenishmentRemind() {
		Date date = new Date();
		// 预订
		List<Map<String, Object>> breakList = quartzMapper
				.getBreakfastAreaModelId();
		for (int i = 0; i < breakList.size(); i++) {
			Map<String, Object> map = breakList.get(i);
			String areaModelId = (String) map.get("areaModelId");
			List<Map<String, String>> InfoMap = quartzMapper
					.getMachineInfoByModeId(Long.valueOf(areaModelId));
			for (int j = 0; InfoMap != null && j < InfoMap.size(); j++) {
				Map<String, String> mapInfo = InfoMap.get(j);
				String userId = mapInfo.get("userId");
				String mobile = mapInfo.get("mobile");
				String address = mapInfo.get("address");
				String machineId = mapInfo.get("machineId");
				String specialToleNum = mapInfo.get("specialToleNum");
				if ("2".equals(specialToleNum)) {
					int replenishmentNum = quartzMapper.getReplenishmentNum(
							Long.valueOf(machineId), Long.valueOf(areaModelId),
							null);
					if (replenishmentNum > 0) {
						String retailStartTime = mapInfo.get("retailStartTime");
						String reserveStartTime = mapInfo
								.get("reserveStartTime");
						// 拼成完整的日期yyyy MM DD hh mm ss
						String retailStartTimeymdhms = DateUtils
								.formatString(getBackOneDay(retailStartTime));
						String reserveStartTimeymdhms = DateUtils
								.formatString(getBackOneDay(reserveStartTime));
						BasicsUsers user = basicsUsersMapper
								.selectByPrimaryKey(Long.valueOf(userId));
						if (user == null) {
							// 当前用不存在结束本次循环
							continue;
						}
						Date createTime = user.getcreateTime();
						Calendar c = Calendar.getInstance();
						c.setTime(createTime);
						c.add(Calendar.DATE, 3);
						// 创建后发三天
						if (date.before(c.getTime())) {
							if (!"".equals(mobile)) {
								aliSmsService
										.sendrReplenishmentStartTimeRemind(
												mobile, address,
												retailStartTimeymdhms,
												reserveStartTimeymdhms);
							}
						}

					}

				} else if ("3".equals(specialToleNum)) {
					int replenishmentNum = quartzMapper.getReplenishmentNum(
							Long.valueOf(machineId), Long.valueOf(areaModelId),
							Long.valueOf(userId));
					// 补货数大于0发短信
					if (replenishmentNum > 0) {
						// HHmmss
						String retailStartTime = mapInfo.get("retailStartTime");
						String reserveStartTime = mapInfo
								.get("reserveStartTime");
						// 拼成完整的日期yyyy MM DD hh mm ss
						String retailStartTimeymdhms = DateUtils
								.formatString(getBackOneDay(retailStartTime));
						String reserveStartTimeymdhms = DateUtils
								.formatString(getBackOneDay(reserveStartTime));
						BasicsUsers user = basicsUsersMapper
								.selectByPrimaryKey(Long.valueOf(userId));
						if (user == null) {
							// 当前用不存在结束本次循环
							continue;
						}
						Date createTime = user.getcreateTime();
						Calendar c = Calendar.getInstance();
						c.setTime(createTime);
						c.add(Calendar.DATE, 3);
						// 创建后发三天
						if (date.before(c.getTime())) {
							if (!"".equals(mobile)) {
								aliSmsService
										.sendrReplenishmentStartTimeRemind(
												mobile, address,
												retailStartTimeymdhms,
												reserveStartTimeymdhms);

							}
						}

					}

				}

			}
		}

		/*
		 * //零售 List<Map<String, Object>> breakListLs =
		 * quartzMapper.getBreakfastAreaRetail(); for (int i = 0; i <
		 * breakListLs.size(); i++) { Map<String, Object> map =
		 * breakListLs.get(i); String areaModelId = (String)
		 * map.get("areaModelId"); List<Map<String, String>> InfoMap =
		 * quartzMapper.getMachineInfoByModeId(Long.valueOf(areaModelId)); for
		 * (int j = 0;InfoMap != null && j < InfoMap.size(); j++) { Map<String,
		 * String> mapInfo = InfoMap.get(j); String userId =
		 * mapInfo.get("userId"); String mobile = mapInfo.get("mobile"); String
		 * address = mapInfo.get("address"); //HHmmss String retailStartTime =
		 * mapInfo.get("retailStartTime"); String reserveStartTime =
		 * mapInfo.get("reserveStartTime");
		 * 
		 * //拼成完整的日期yyyy MM DD hh mm ss String retailStartTimeymdhms =
		 * DateUtils.formatString(getBackOneDay(retailStartTime)); String
		 * reserveStartTimeymdhms =
		 * DateUtils.formatString(getBackOneDay(reserveStartTime)); BasicsUsers
		 * user = basicsUsersMapper.selectByPrimaryKey(Long.valueOf(userId));
		 * if(user == null){ //当前用不存在结束本次循环 continue; } Date createTime =
		 * user.getcreateTime();
		 * 
		 * Calendar c = Calendar.getInstance(); c.setTime(createTime);
		 * c.add(Calendar.DATE, 3); //创建后发三天 if(date.before(c.getTime())){
		 * if(!"".equals(mobile)){
		 * aliSmsService.sendrReplenishmentStartTimeRemind(mobile, address,
		 * retailStartTimeymdhms, reserveStartTimeymdhms);
		 * 
		 * } } } }
		 */

	}

	public Date getBackOneDay(String time) {
		Calendar b = Calendar.getInstance();
		// b.setTime(DateTimeUtil.getTodayTimeByHHmmss(time));
		b.setTime(DateTimeUtil.getTodayTimeByHHmm(time));
		b.add(Calendar.DATE, 1);
		return b.getTime();
	}
}
