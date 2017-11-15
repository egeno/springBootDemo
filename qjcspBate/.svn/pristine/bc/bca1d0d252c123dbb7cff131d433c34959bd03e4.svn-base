package com.qjkj.qjcsp.service.preissue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.ModelCleanGoodsVo;
import com.qjkj.qjcsp.entity.TblGoodsEvaluate;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.ModelCleanGoodsMapper;
import com.qjkj.qjcsp.mapper.TblGoodsEvaluateMapper;
import com.qjkj.qjcsp.mapper.TblIssueMapper;
import com.qjkj.qjcsp.service.machine.MachineApiService;
import com.qjkj.qjcsp.util.DateUtils;

import net.sf.json.JSONObject;

/**
 * 获取预定商品接口(APP)
 * 
 * @author carpeYe 2016-03-29
 *
 */
@Service
public class WeekPreissueGoodsService {
	@Autowired
	private MachineApiService machineApiService; 
	@Autowired
	private TblIssueMapper tblIssueMapper;
	@Autowired
	private ModelCleanGoodsMapper modelCleanGoodsMapper;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	@Autowired
	private	BasicsUsersMapper basicsUsersMapper;
	@Autowired
	private TblGoodsEvaluateMapper tblGoodsEvaluateMapper;

	public Map<String, Object> weekPreissueGoods(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		if (res != null) {
			String companyId = res.getString("companyId");
			String areaModelId = res.getString("areaModelId");
			String selectDate = res.getString("selectDate");
			String machineId = res.getString("machineId");
			if (StringUtils.isNoneBlank(companyId, areaModelId, selectDate, machineId)) {
				//Gekko 注：修改为预定截止时间
//				String supplyEndTime = tblIssueMapper.findSupplyEndTimeByAreaModelId(Long.valueOf(areaModelId));
				
//				List <ModelCleanGoodsVo> cleanGoodsVo=modelCleanGoodsMapper.getTime(machineId, Long.parseLong(areaModelId));
				BasicsAreaModel basicsAreaModel= basicsAreaModelMapper.selectByPrimaryKey(Long.valueOf(areaModelId));
				if(basicsAreaModel==null){
					returnContent.put("returnContent", "你选择的模型与模型未找到！");
					returnContent.put("returnCode", "1");
					return returnContent;
				}
				ModelCleanGoodsVo modelCleanGoodsVo = modelCleanGoodsMapper.getTimeByModelId(Long.valueOf(areaModelId));
				String supplyEndTime=DateUtils.getDateFormat("HH:mm:ss", modelCleanGoodsVo.getReserveEndTime());
				//Gekko 注：修改为预定截止时间
				if (supplyEndTime != null&&basicsAreaModel.getTimeType()!=null) {				
//					if(basicsAreaModel.getTimeType()=="0"){
//						//Gekko 注：修改为预定截止时间
//						Date supplyEndTimes = DateUtils.formatDate(selectDate+" 02:55:00");
						Date supplyEndTimes = DateUtils.formatDate(selectDate+" "+supplyEndTime);
						Date nowTime = org.apache.commons.lang3.time.DateUtils.addMinutes(new Date(), -5);
//						if (supplyEndTimes.getTime() - nowTime.getTime() > 3300000) { 这个判断有疑问
						if(supplyEndTimes.getTime() - nowTime.getTime()>0){
							//Gekko 注：根据设备id查询出商户列表
							List<BasicsUsers> users= basicsUsersMapper.findUsersByMachineId(Long.valueOf(machineId),"3");
							List<String> userNames=new ArrayList<String>();
							for (BasicsUsers basicsUsers : users) {
								userNames.add(basicsUsers.getUserName());
							}
							if(userNames.size()>0){
								
								List<Map<String, Object>> issues = tblIssueMapper.selectIssueByDateAndStore(userNames,Long.valueOf(companyId),
										selectDate, Long.valueOf(areaModelId));
								 //遍历，查评价分数 xlk
								for(Map<String,Object> map2:issues){
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
								
								Map<String, Object> returnData = new HashMap<String, Object>();
								returnData.put("issueList", issues);
								Integer num = tblIssueMapper.findSurplusPreissueNum(Long.valueOf(machineId), selectDate,
										Long.valueOf(areaModelId));
								returnData.put("surplusNum", num + "");
								//当设备故障的时候,剩余可预定份数为0,issueList为空 xlk
								boolean isAlarm=machineApiService.isAlarmMachine(Long.valueOf(machineId));
								if(!isAlarm){
									// 剩余可预定份数为0
									returnData.put("surplusNum", "0");
									//issueList为空
									issues=new ArrayList<Map<String,Object>>();
									returnData.put("issueList", issues);
								}
								//当设备故障的时候,剩余可预定份数为0,issueList为空 xlk end								
								returnContent.put("returnContent", returnData);
								returnContent.put("returnCode", "1");
							}else{
								returnContent.put("returnContent", "该设备未关联商户！");
								returnContent.put("returnCode", "1");
							}
							
						} else {
							returnContent.put("returnContent", "已经过了预定时间！");
							returnContent.put("returnCode", "1");
						}
//					}else{
//						//Gekko 注：修改为预定截止时间
//						Date supplyEndTimes = DateUtils.formatDate(selectDate+" "+supplyEndTime);
//						Date nowTime = new Date();
//						if (supplyEndTimes.getTime() - nowTime.getTime() > 3300000) {
//							List<Map<String, Object>> issues = tblIssueMapper.selectIssueByDateAndStore(userNames,
//						selectDate, Long.valueOf(areaModelId));
//							Map<String, Object> returnData = new HashMap<String, Object>();
//							returnData.put("issueList", issues);
//							Integer num = tblIssueMapper.findSurplusPreissueNum(Long.valueOf(machineId), selectDate,
//									Long.valueOf(areaModelId));
//							returnData.put("surplusNum", num + "");
//							returnContent.put("returnContent", returnData);
//							returnContent.put("returnCode", "1");
//
//							List<Map<String, Object>> issues = tblIssueMapper.selectIssueByDate(Long.valueOf(companyId),
//									selectDate, Long.valueOf(areaModelId));
//							Map<String, Object> returnData = new HashMap<String, Object>();
//							returnData.put("issueList", issues);
//							Integer num = tblIssueMapper.findSurplusPreissueNum(Long.valueOf(machineId), selectDate,
//									Long.valueOf(areaModelId));
//							returnData.put("surplusNum", num + "");
//							returnContent.put("returnContent", returnData);
//							returnContent.put("returnCode", "1");
//						} else {
//							returnContent.put("returnContent", "已经过了预定时间！");
//							returnContent.put("returnCode", "2");
//						}
//					}
						
				}else{
					returnContent.put("returnContent", "你选择的模型未设置补货时间！");
					returnContent.put("returnCode", "1");
				}
			} else {
				returnContent.put("returnContent", "请求参数异常！");
				returnContent.put("returnCode", "0");
			}
		}return returnContent;
}

/*
 * private List<List<Map<String,Object>>> findWeekPreissueGoods(Long companyId){
 * List<List<Map<String,Object>>> content=new
 * ArrayList<List<Map<String,Object>>>(); Date date=new Date(); for (int i = 0;
 * i < 7; i++) { Map<String,Object> dates=FormateDate(date,i);
 * List<Map<String,Object>> issues=tblIssueMapper.selectIssueByDate(companyId,
 * (String)dates.get("date")); issues.add(0, dates); content.add(issues); }
 * return content; }
 * 
 * private static Map<String, Object> FormateDate(Date date,Integer addDate) {
 * SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); Map<String, Object>
 * map = new HashMap<String, Object>(); Calendar
 * calendar=Calendar.getInstance(); calendar.setTime(date);
 * calendar.add(Calendar.DATE, addDate); String
 * newDate=sdf.format(calendar.getTime()); int
 * weekDay=calendar.get(Calendar.DAY_OF_WEEK); String dayOfWeek=""; switch
 * (weekDay) { case Calendar.MONDAY: dayOfWeek="星期一"; break; case
 * Calendar.TUESDAY: dayOfWeek="星期二"; break; case Calendar.WEDNESDAY:
 * dayOfWeek="星期三"; break; case Calendar.THURSDAY: dayOfWeek="星期四"; break; case
 * Calendar.FRIDAY: dayOfWeek="星期五"; break; case Calendar.SATURDAY:
 * dayOfWeek="星期六"; break; default: dayOfWeek="星期日"; break; }
 * map.put("date",newDate); map.put("weekDay",dayOfWeek); return map; }
 */

}
