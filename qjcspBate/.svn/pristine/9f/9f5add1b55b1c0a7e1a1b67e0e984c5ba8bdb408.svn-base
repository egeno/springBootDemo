package com.qjkj.qjcsp.service.wechatapi;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.mapper.TblIssueMapper;

import net.sf.json.JSONObject;
/**
 * 
 * @author carpeYe 2016-04-13
 *   2.3.40.	获取剩余预定份数
 */
@Service
public class SurplusPreissueNumWeChatService {

	@Autowired
	private TblIssueMapper tblIssueMapper;

	public Map<String, Object> getSurplusPreissueNumWX(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		/* 设备ID */
		String machineId = null;
		if (res.has("machineId"))
			machineId = res.getString("machineId");
		/* 日期 */
		String selectDate = null;
		if (res.has("selectDate"))
			selectDate = res.getString("selectDate");
		/* 分区模型Id */
		String areaModelId = null;
		if (res.has("areaModelId"))
			areaModelId = res.getString("areaModelId");
		if (StringUtils.isNoneBlank(machineId, selectDate, areaModelId)) {
			Integer surplusPreissueNum = tblIssueMapper.findSurplusPreissueNum(Long.valueOf(machineId), selectDate,
					Long.valueOf(areaModelId));
			Map<String,Object> returnData=new HashMap<String, Object>();
			returnData.put("surplusNum", surplusPreissueNum+"");
			returnContent.put("returnCode", "1");
			returnContent.put("returnContent",returnData);
		} else {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "请求参数异常");
		}
		return returnContent;
	}
}
