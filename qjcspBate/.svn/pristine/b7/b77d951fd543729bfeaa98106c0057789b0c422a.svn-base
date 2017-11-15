package com.qjkj.qjcsp.service.university;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.mapper.TblIssueMapper;

import net.sf.json.JSONObject;
/**
 * 城市大学接口 
 */
@Service
public class UniversitiesListSerivice {

	@Autowired
	private TblIssueMapper tblIssueMapper;

	public Map<String, Object> getSurplusPreissueNum(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		/* 城市名称 */
		String cityName = null;
		//经度
		String longitude = null;
		//纬度
		String latitude = null;
		if (res.has("cityName"))
			cityName = res.getString("cityName");
		if (res.has("longitude"))
			longitude = res.getString("longitude");
		if (res.has("latitude"))
			latitude = res.getString("latitude");
		
		/*String areaModelId = null;
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
		}*/
		return returnContent;
	}
}
