package com.qjkj.qjcsp.web.api.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.entity.WeekPo;
import com.qjkj.qjcsp.service.preissue.PreissueEndTimeService;
import com.qjkj.qjcsp.service.preissue.WeekPreissueGoodsService;
import com.qjkj.qjcsp.service.wechatapi.WeekPreissueGoodsWeChatService;
import com.qjkj.qjcsp.util.DateTimeUtil;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;

/**
 * 
 * 获取预定商品接口(APP)
 * 
 * @author carpeYe 2016-03-29
 */
@RestController
@RequestMapping("/api/wechat/")
public class WeekPreissueGoodsWeChatController {

	@Autowired
	private WeekPreissueGoodsWeChatService weekPreissueGoodsWeChatService;
	@Autowired
	private PreissueEndTimeService preissueEndTimeService;

	@RequestMapping("weekPreissueGoodsWX")
	public Map<String, Object> weekPreissueGoodsWX(HttpServletRequest req) {
		String postJson = RequestData.getRequestPostJson(req);
		JSONObject res = null;
		if (StringUtils.isNoneBlank(postJson)) {
			res = JSONObject.fromObject(postJson);
		}
		Map<String, Object> returnContent = weekPreissueGoodsWeChatService.weekPreissueGoodsWX(res);
		returnContent.put("requestMethod", "weekPreissueGoodsWX");
		return returnContent;
	}

	/**
	 * 获取预订日期列表
	 * */
	@RequestMapping("preissueWeekWX")
	@ResponseBody
	public Map<String,Object> preissueWeekWX(HttpServletRequest req){
		Map<String,Object> returnContent=new HashMap<String, Object>();
		try {
			String postJson = RequestData.getRequestPostJson(req);
			JSONObject json = JSONObject.fromObject(postJson);
			if(!json.has("companyId")){
				returnContent.put("returnCode", "0");
				returnContent.put("returnContent", "请求参数错误");
				returnContent.put("requestMethod", "preissueWeekWX");
				return returnContent;
			}
			String companyId=json.getString("companyId");
			if(StringUtils.isEmpty(companyId.trim())){
					returnContent.put("returnCode", "0");
					returnContent.put("returnContent", "请求参数错误");
					returnContent.put("requestMethod", "preissueWeekWX");
					return returnContent;
			}
			//判断是否需要增加一天
			//int num=preissueEndTimeService.checkNeedAddDay(Integer.parseInt(companyId));
			List<String> list=DateTimeUtil.getSevenDateList(-1);
			List<String> weekList=DateTimeUtil.getSevenWeekList(-1);
			List<Integer> weekNumlist=DateTimeUtil.getSevenWeekNumList(-1);
			List<Object> thisWeek=new ArrayList<Object>();
			List<Object> nextWeek=new ArrayList<Object>();
			List<Object> returnList=new ArrayList<Object>();
			Map<String,Object> thisWeekMap=new HashMap<String, Object>();
			Map<String,Object> nextWeekMap=new HashMap<String, Object>();
			boolean flag=true;
			//循环七天数据的list，分割成thisWeeK和nextWeek
			for(int i=0;i<list.size();i++){
				//初始化weekPo实体
				WeekPo weekPo=new WeekPo();  
				if(flag){
					//如果今天是周日，修改flag值。开始添加nextWeek数据
					if(weekNumlist.get(i)==7){
						flag=false;
					}
					weekPo.setDate(list.get(i));
					weekPo.setWeekDate(weekList.get(i));
					thisWeek.add(weekPo);
				}else{
					weekPo.setDate(list.get(i));
					weekPo.setWeekDate(weekList.get(i));
					nextWeek.add(weekPo);
				}
			}
			thisWeekMap.put("thisWeek", thisWeek);
			nextWeekMap.put("nextWeek", nextWeek);
			//如果nextWeek为空，设置nextWeek数据为thisWeek的数据
			/*if(nextWeek.size()==0){
				List<Object> newlist=new ArrayList<Object>();
				thisWeekMap.put("thisWeek", newlist);
				nextWeekMap.put("nextWeek", thisWeek);
			}*/
			returnList.add(thisWeekMap);
			returnList.add(nextWeekMap);
			returnContent.put("returnCode", "1");
			returnContent.put("returnContent", returnList);
			returnContent.put("requestMethod", "preissueWeekWX");
			return returnContent;
		} catch (Exception e) {
			// TODO: handle exception
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "获取数据失败，请稍后再试");
			returnContent.put("requestMethod", "preissueWeekWX");
			return returnContent;
		}
	}
}
