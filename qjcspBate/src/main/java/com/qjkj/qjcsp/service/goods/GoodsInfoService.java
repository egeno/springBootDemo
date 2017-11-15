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

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.ModelCleanGoodsVo;
import com.qjkj.qjcsp.entity.TblGoodsEvaluate;
import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.entity.viewmodel.BasicsGoodsVo;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsRoleMapper;
import com.qjkj.qjcsp.mapper.ModelCleanGoodsMapper;
import com.qjkj.qjcsp.mapper.TblGoodsAreaModelMapper;
import com.qjkj.qjcsp.mapper.TblGoodsEvaluateMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailTemporaryMapper;
import com.qjkj.qjcsp.mapper.TblOrderDetailMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.service.machine.MachineApiService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.DateTimeUtil;
import com.qjkj.qjcsp.util.DateUtils;
import com.qjkj.qjcsp.util.UserUtils;

import net.sf.json.JSONObject;

@Component
@Transactional
public class GoodsInfoService {

	private static final Logger logger = LoggerFactory.getLogger(GoodsInfoService.class);
	@Autowired
	private MachineApiService machineApiService;

	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;

	@Autowired
	private BasicsMachineMapper basicsMachineMapper;

	@Autowired
	private ModelCleanGoodsMapper modelCleanGoodsMapper;

	@Autowired
	private BasicsRoleMapper basicsRoleMapper;

	@Autowired
	private ModelCleanGoodsMapper cleanGoodsMapper;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	@Autowired
	private TblOrderDetailMapper tblOrderDetailMapper;
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;
	@Autowired
	private TblGoodsRetailTemporaryMapper tblGoodsRetailTemporaryMapper;
	@Autowired
	private TblGoodsAreaModelMapper tblGoodsAreaModelMapper;
	@Autowired
	private TblGoodsEvaluateMapper tblGoodsEvaluateMapper;

	/**
	 * 查询所有商品信息
	 * 
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<BasicsGoodsVo> findGoodsPage(Map<String, Object> param, int pageNumber, int pageSize) {
		param.put("state", DataStatusEnum.NORMAL.getValue());

		UserUtils userUtils = new UserUtils();
		param.put("companyId", userUtils.getCompanyId());
		Long total = basicsGoodsMapper.findByCount(param);

		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order", "goods_id");
		param.put("sort", Sort.Direction.DESC);

		List<BasicsGoodsVo> list = new ArrayList<BasicsGoodsVo>();
		if (total != 0) {
			list = basicsGoodsMapper.findByList(param);
		}
		Page<BasicsGoodsVo> page = new PageImpl<BasicsGoodsVo>(list, pageRequest, total);

		return page;
	}

	/**
	 * 添加商品信息
	 * 
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	public Map<String, Object> addGoods(ServletRequest request, BasicsGoods basicsGoods) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer count = basicsGoodsMapper.checkGoodIsExisted(basicsGoods.getGoodsName(), null);
			
			if (count > 0) {
				map.put("message", "商品名称重复，请换个商品名称吧~");
				map.put("status", Boolean.FALSE);			
			}else if(basicsGoods.getCostPrice().compareTo(basicsGoods.getRetailPrice())==1 ){
				map.put("message", "成本价不能大于零售价，请重新定价！");
				map.put("status", Boolean.FALSE);
			}else {
				UserUtils userUtils = new UserUtils();
				basicsGoods.setCreateUserId((long) userUtils.getUserId());
				basicsGoods.setCreateTime(new Date());
				basicsGoods.setModUserId((long) userUtils.getUserId());
				basicsGoods.setLastModTime(new Date());
				basicsGoods.setCompanyId((long) userUtils.getCompanyId());
				basicsGoodsMapper.insertSelective(basicsGoods);
				map.put("message", "添加商品成功!");
				map.put("status", Boolean.TRUE);
			}
		} catch (Exception e) {
			logger.error("添加商品发生异常!", e);
			map.put("message", "添加商品失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}

		return map;
	}

	/**
	 * 编辑商品信息
	 * 
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	public Map<String, Object> editGoods(ServletRequest request, BasicsGoods basicsGoods) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer count = basicsGoodsMapper.checkGoodIsExisted(basicsGoods.getGoodsName(), basicsGoods.getGoodsId());
			if (count > 0) {
				map.put("message", "商品名称重复，请换个商品名称吧~");
				map.put("status", Boolean.FALSE);
			} else if(basicsGoods.getCostPrice().compareTo(basicsGoods.getRetailPrice())==1 ){
				map.put("message", "成本价不能大于零售价，请重新定价！");
				map.put("status", Boolean.FALSE);
			} else {
				/* 判断商品是否有分区选中, 且商品修改为停用 */
				UserUtils userUtils = new UserUtils();
				basicsGoods.setModUserId((long) userUtils.getUserId());
				basicsGoods.setLastModTime(new Date());
				basicsGoodsMapper.updateByPrimaryKeySelective(basicsGoods);
				map.put("message", "更新商品成功!");
				map.put("status", Boolean.TRUE);
			}
		} catch (Exception e) {
			logger.error("更新商品发生异常!", e);
			map.put("message", "更新商品失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}

		return map;
	}

	/**
	 * 逻辑删除商品信息
	 * 
	 * @param request
	 * @param goodsId
	 * @return
	 */
	public Map<String, Object> delGoods(ServletRequest request, Long goodsId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer count = tblOrderDetailMapper.orderDetailCount(goodsId);
			if (count > 0) {
				map.put("message", "删除商品失败,该商品已被用户购买!");
				map.put("status", Boolean.FALSE);
				return map;
			}
			Integer goodsRetailTemporaryCount = tblGoodsRetailTemporaryMapper.goodsRetailTemporaryCount(goodsId);
			Integer temporaryRetailCount = tblTemporaryRetailMapper.temporaryRetailCount(goodsId);
			if (goodsRetailTemporaryCount > 0 || temporaryRetailCount > 0) {
				map.put("message", "删除商品失败,该商品已被补货!");
				map.put("status", Boolean.FALSE);
				return map;
			}

			/* 判断商品是否有分区选中 */
			basicsGoodsMapper.deleteBasicsGoodsbyId(goodsId);
			tblGoodsAreaModelMapper.deleteGoodsModelByGoodsId(goodsId);
			map.put("message", "删除商品成功!");
			map.put("status", Boolean.TRUE);

		} catch (Exception e) {
			logger.error("删除商品发生异常!", e);
			map.put("message", "删除商品失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}

		return map;
	}

	/**
	 * 根据商品id查找商品信息
	 * 
	 * @param request
	 * @param goodsId
	 * @return
	 */
	public BasicsGoods findBasicsGoods(ServletRequest request, Long goodsId) {
		BasicsGoods bg = basicsGoodsMapper.selectByPrimaryKey(goodsId);

		return bg;
	}

	/**
	 * 获取指定设备的可售菜品信息
	 * 
	 * @param req
	 * @return
	 */
	/*
	 * public Map<String, Object> getSaleGoodsList(JSONObject req) { Map<String,
	 * Object> map = new HashMap<String, Object>(); Map<String, Object>
	 * returnJson = new HashMap<String, Object>(); // 获取设备硬件id String deviceCode
	 * = req.getString("deviceCode"); // 获取取餐时间 Object pickTimes =
	 * req.get("pickTime"); SimpleDateFormat sdf = new SimpleDateFormat(
	 * "yyyy-MM-dd HH:mm:ss"); Date pickTime = null; if
	 * (StringUtils.isNoneBlank(deviceCode)) { // 如果设备端传的是取餐时间为null 服务器这边自己创建时间
	 * if (pickTimes != null && !"".equals(pickTimes)) { String time =
	 * pickTimes.toString(); try { pickTime = sdf.parse(time); } catch
	 * (ParseException e) { e.printStackTrace(); } } else { pickTime = new
	 * Date(); } map.put("deviceCode", deviceCode); map.put("pickTime",
	 * pickTime); try { List<Map> list =
	 * basicsGoodsMapper.findGoodsVOByDeviceCodeAndPickTime(map);
	 * returnJson.put("returnCode", "1"); returnJson.put("returnContent", list);
	 * } catch (Exception e) { logger.error("指定设备查询可售商品异常", e);
	 * returnJson.put("returnCode", "0"); returnJson.put("returnContent",
	 * e.getMessage()); } } else { returnJson.put("returnCode", "0");
	 * returnJson.put("returnContent", "请求参数错误"); } return returnJson; }
	 */
	/**
	 * 获取指定设备指定模型的可售菜品信息
	 * 
	 * @param req
	 * @return
	 */
	public Map<String, Object> getSalingGoodsList(JSONObject req) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> returnJson = new HashMap<String, Object>();
		BasicsMachine basicsMachine = null;
		// 0代表设备访问
		int parameter = 0;
		String machineId = null;
		if (req.has("machineId")) {
			machineId = req.getString("machineId");
			parameter = 1;
			basicsMachine = basicsMachineMapper.selectByMachineId(Long.valueOf(machineId));
		}

		// 获取设备硬件id
		String deviceCode = null;
		if (req.has("deviceCode")) {
			deviceCode = req.getString("deviceCode");
			machineId = basicsMachineMapper.selectByDeviceCode(deviceCode).getMachineId() + "";
		}

		// 获取取餐时间
		String orderTimes = null;
		if (req.has("orderTime"))
			orderTimes = req.getString("orderTime");
		// 获取模型ID号
		Object modelId2 = req.get("areaModelId");
		long modelId;
		if (modelId2 != null && !"".equals(modelId2)) {
			modelId = Long.parseLong(modelId2.toString());
		} else {
			if (basicsMachineMapper.getAreaModelIdByMachineId(Long.valueOf(machineId)) == null) {
				returnJson.put("returnCode", "2");
				returnJson.put("returnContent", "该设备未分配模型");
				return returnJson;
			} else {
				modelId = basicsMachineMapper.getAreaModelIdByMachineId(Long.valueOf(machineId));
			}
		}
		map.put("modelId", modelId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String orderTime = null;
		String week = "";
		if (StringUtils.isNoneBlank(machineId)) {
			BasicsMachine machine = basicsMachineMapper.selectByPrimaryKey(Long.valueOf(machineId));
			/*
			 * Map<String, String> cleanMap =
			 * cleanGoodsMapper.getSupplyEndTimeAndCleanStartTimeByAreaModelId(
			 * machine.getAreaModelId()); //当前时间在清货时间之后菜品信息不显示 if(cleanMap ==
			 * null){ returnJson.put("returnCode", "0");
			 * returnJson.put("returnContent", "当前设备无模型"); return returnJson; }
			 * if(gethms(new Date(),cleanMap.get("cleanStartTime").toString())){
			 * returnJson.put("returnCode", "3");
			 * returnJson.put("returnContent", "无可售菜品"); return returnJson; }
			 */
			// 如果设备端传的是取餐时间为null 服务器这边自己创建时间
			if (orderTimes != null && !"".equals(orderTimes)) {

				try {
					orderTime = orderTimes;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				orderTime = sdf.format(new Date());
			}
			try {
				week = DateTimeUtil.getWeekOfDate(sdf.parse(orderTime));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// map.put("deviceCode", deviceCode);
			map.put("orderTime", orderTime);
			map.put("machineId", machineId);
			try {
				List<Map<String,Object>> list = basicsGoodsMapper.findGoodsVOByDeviceCodeAndPickTime2(map);
			  //遍历，查评价分数 xlk
				for(Map<String,Object> map2:list){
					List<TblGoodsEvaluate> tblGoodsEvaluateList = tblGoodsEvaluateMapper.selectBygoodsId(Long.valueOf(map2.get("goodsId").toString()));
					double goodsEvaluateScoreAvg = 0;
					int tblGoodsEvaluateListsize = tblGoodsEvaluateList.size();
					if (tblGoodsEvaluateListsize > 0) {
						for (int i = 0; i < tblGoodsEvaluateListsize; i++) {
							goodsEvaluateScoreAvg = goodsEvaluateScoreAvg + tblGoodsEvaluateList.get(i).getGoodsEvaluateScore();
						}
						// 评价星级向上取整
						goodsEvaluateScoreAvg = Math.ceil(goodsEvaluateScoreAvg / tblGoodsEvaluateListsize);
					}
					
					map2.put("goodsGrade", goodsEvaluateScoreAvg+"");
				}
				List<ModelCleanGoodsVo> l = modelCleanGoodsMapper.getTime(machineId, modelId);
				Map<String, Object> map2 = new HashMap<String, Object>();
				Map<String, Object> map3 = new HashMap<String, Object>();
				String timeType = basicsAreaModelMapper.getTimeTypeByAreaModelId(modelId);
				Date oTime = sdf.parse(orderTime);
				if (l.size() == 1) {
					ModelCleanGoodsVo modelCleanGoodsVo = l.get(0);
					// Gekko 注：修改为零售取餐时间和结束时间
					if ("allDay".equals(modelCleanGoodsVo.getType())) {
						if (modelCleanGoodsVo.getRetailTakeStartTime()
								.after(modelCleanGoodsVo.getRetailTakeEndTime())) {
							Date endtime = DateUtils.formatDate(df.format(oTime) + " "
									+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
							if (oTime.after(endtime)) {
								Calendar cal = Calendar.getInstance();
								cal.setTime(oTime);
								cal.add(cal.DATE, 1);
								map2.put("takenEndTime", df.format(cal.getTime()) + " "
										+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
								map3.put("takenStartTime", df.format(oTime) + " "
										+ sdf.format(modelCleanGoodsVo.getRetailTakeStartTime()).substring(11));
							} else {
								Calendar cal = Calendar.getInstance();
								cal.setTime(oTime);
								cal.add(cal.DATE, -1);
								map2.put("takenEndTime", df.format(oTime) + " "
										+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
								map3.put("takenStartTime", df.format(cal.getTime()) + " "
										+ sdf.format(modelCleanGoodsVo.getRetailTakeStartTime()).substring(11));
							}
						} else {
							map2.put("takenEndTime", df.format(oTime) + " "
									+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
							map3.put("takenStartTime", df.format(oTime) + " "
									+ sdf.format(modelCleanGoodsVo.getRetailTakeStartTime()).substring(11));
						}
					} else {
						String[] array = modelCleanGoodsVo.getDateStr().split(",");
						int temp = 0;
						for (String string : array) {
							if (string.equals(week)) {
								temp = 1;
								// Gekko 注：修改为零售取餐时间和结束时间
								// 判断是否晚餐 是：结束时间
								if (modelCleanGoodsVo.getRetailTakeStartTime()
										.after(modelCleanGoodsVo.getRetailTakeEndTime())) {
									Date endtime = DateUtils.formatDate(df.format(oTime) + " "
											+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
									if (oTime.after(endtime)) {
										Calendar cal = Calendar.getInstance();
										cal.setTime(oTime);
										cal.add(cal.DATE, 1);
										map2.put("takenEndTime", df.format(cal.getTime()) + " "
												+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
										map3.put("takenStartTime", df.format(oTime) + " "
												+ sdf.format(modelCleanGoodsVo.getRetailTakeStartTime()).substring(11));
									} else {
										Calendar cal = Calendar.getInstance();
										cal.setTime(oTime);
										cal.add(cal.DATE, -1);
										map2.put("takenEndTime", df.format(oTime) + " "
												+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
										map3.put("takenStartTime", df.format(cal.getTime()) + " "
												+ sdf.format(modelCleanGoodsVo.getRetailTakeStartTime()).substring(11));
									}
								} else {
									map2.put("takenEndTime", df.format(oTime) + " "
											+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
									map3.put("takenStartTime", df.format(oTime) + " "
											+ sdf.format(modelCleanGoodsVo.getRetailTakeStartTime()).substring(11));
								}

							}
						}
						if (temp == 0) {
							returnJson.put("returnCode", "2");
							returnJson.put("returnContent", "预售日期有误");
							return returnJson;
						}
					}
				} else {
					int count = 0;
					outterLoop: for (ModelCleanGoodsVo modelCleanGoodsVo : l) {
						String[] array = modelCleanGoodsVo.getDateStr().split(",");
						for (String string : array) {
							if (string.equals(week)) {
								count = 1;
								// Gekko 注：修改为零售取餐时间和结束时间
								if ("3".equals(timeType)) {
									Calendar cal = Calendar.getInstance();
									cal.setTime(sdf.parse(orderTime));
									cal.add(cal.DATE, 1);
									map2.put("takenEndTime", df.format(cal.getTime()) + " "
											+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
								} else {
									map2.put("takenEndTime", df.format(sdf.parse(orderTime)) + " "
											+ sdf.format(modelCleanGoodsVo.getRetailTakeEndTime()).substring(11));
								}
								map3.put("takenStartTime", df.format(sdf.parse(orderTime)) + " "
										+ sdf.format(modelCleanGoodsVo.getRetailTakeStartTime()).substring(11));
								break outterLoop;
							}
						}
					}
					if (count == 0) {
						returnJson.put("returnCode", "2");
						returnJson.put("returnContent", "预售日期有误");
						return returnJson;
					}

				}
				// list2.add(map2);
				// list2.add(map3);
				// list2.add(list);
				Map<String, Object> returnContent = new HashMap<String, Object>();
				returnContent.put("takenEndTime", map2.get("takenEndTime"));
				returnContent.put("takenStartTime", map3.get("takenStartTime"));

				if (parameter == 0) {
					returnContent.put("goodsList", list);
				} else {
					// app访问
					String takenStartTime = (String) map3.get("takenStartTime");
					Date takenStartTimeDate = DateFormat.dateFormatYYYYMMDDHHMMSS(takenStartTime);
					// Date date = new Date();
					Long AreaModelId = basicsMachine.getAreaModelId();

					if (AreaModelId == null) {
						list = new ArrayList<Map<String,Object>>();
						returnContent.put("goodsList", list);
						returnJson.put("returnCode", "1");
						returnJson.put("returnContent", returnContent);
						return returnJson;
					}

					Date currentTime = new Date();
					Date CleanStartTime = modelCleanGoodsMapper.getTimeByModelId(AreaModelId).getRetailCleanStartTime();
					Date todayOrderEndTime = DateUtils
							.formatDatehms(DateUtils.getDateFormat("HH:mm:ss", DateTimeUtil.getFrontFifteenMinutesDate(CleanStartTime)));
					Date currentTimeHms = DateUtils.formatDatehms(DateUtils.getDateFormat("HH:mm:ss", currentTime));
					if (todayOrderEndTime == null || currentTimeHms == null) {
						returnJson.put("returnCode", "0");
						returnJson.put("returnContent", "数据转换异常");
						return returnJson;
					}

					BasicsAreaModel AreaModel = basicsAreaModelMapper.selectByPrimaryKey(AreaModelId);

					// 得到当前设备最后一次的补货时间
					Map<String, String> mapTime = basicsAreaModelMapper.getOperateTime(basicsMachine.getDeviceCode());
					if (mapTime == null) {
						list = new ArrayList<Map<String,Object>>();
						returnContent.put("goodsList", list);
						returnJson.put("returnCode", "1");
						returnJson.put("returnContent", returnContent);
						return returnJson;
					}
					String operateTime = mapTime.get("operateTime");
					// 晚餐
					if ("3".equals(AreaModel.getTimeType())) {
						// 晚餐当前时间和最后一次补货时间是
						// 1.同一天 不用判段
						// 2.大一天 如果当前时间大于清货时间就不显示
						// 3.大多天 直接不显示商品
						if (getDayNum(currentTime, DateUtils.formatDate(operateTime)) >= 2) {
							// 不显示商品列表
							list = new ArrayList<Map<String,Object>>();
						} else if (getDayNum(currentTime, DateUtils.formatDate(operateTime)) == 1) {
							if (todayOrderEndTime != null && currentTimeHms.after(todayOrderEndTime)) {
								// 不显示商品列表
								list = new ArrayList<Map<String,Object>>();
							}
						}
						// else{
						// if (todayOrderEndTime != null &&
						// currentTimeHms.after(todayOrderEndTime)){
						// //不显示商品列表
						// list = new ArrayList<Map>();
						// }
						// }
					} else {
						// 早，中，休
						if (getDayNum(new Date(), DateUtils.formatDate(operateTime)) >= 1) {
							// 不显示商品列表
							list = new ArrayList<Map<String,Object>>();
						} else {
							if (todayOrderEndTime != null && currentTimeHms.after(todayOrderEndTime)) {
								// 不显示商品列表
								list = new ArrayList<Map<String,Object>>();
							}
						}
					}
					returnContent.put("goodsList", list);
				}
				//当设备故障的时候,不显示菜品 xlk
				boolean isAlarm=machineApiService.isAlarmMachine(Long.valueOf(machineId));
				if(!isAlarm){
					// 不显示商品列表
					list = new ArrayList<Map<String,Object>>();
					returnContent.put("goodsList", list);
				}
				// 当设备故障的时候,不显示菜品 xlk end
				returnJson.put("returnCode", "1");
				returnJson.put("returnContent", returnContent);
			} catch (Exception e) {
				logger.error("指定设备查询可售商品异常", e);
				returnJson.put("returnCode", "0");
				returnJson.put("returnContent", e.getMessage());
				return returnJson;
			}
		} else {
			returnJson.put("returnCode", "0");
			returnJson.put("returnContent", "请求参数错误");
		}
		return returnJson;
	}

	/***
	 * 查询商品发布的商品
	 ***/
	public List<Map<String, Object>> getGoodsList(BasicsGoods goods) {
		Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		goods.setCompanyId(companyId);
		return basicsGoodsMapper.getGoodsList(goods);
	}

	public List<Map<String, Object>> getAllGoodsList(Long goodsCategoryId) {
		Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		return basicsGoodsMapper.getAllGoodsList(companyId, goodsCategoryId);
	}

	/**
	 * 获取清货商品列表
	 */
	public Map<String, Object> getClearGoodsList(String deviceCode, Long userId) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		Map<String, Object> map = basicsRoleMapper.getSpecialRoleNumByUserId(userId);
		if (map.get("specialRoleNum").equals("3")) {
			returnContent.put("returnCode", "1");
			returnContent.put("returnContent",
					basicsGoodsMapper.getClearGoodsList(deviceCode, map.get("userName").toString()));
		} else {
			returnContent.put("returnCode", "1");
			returnContent.put("returnContent", basicsGoodsMapper.getClearGoodsList(deviceCode, ""));
		}
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
	/*
	 * public boolean gethms(Date date,String cleanStartTime){ boolean flag =
	 * Boolean.FALSE; Date returnDate = null; SimpleDateFormat sdf = new
	 * SimpleDateFormat("HH:mm:ss"); try { returnDate =
	 * sdf.parse(sdf.format(date));
	 * if(returnDate.after(sdf.parse(cleanStartTime))){ flag = Boolean.TRUE; } }
	 * catch (ParseException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return flag; }
	 */

}
