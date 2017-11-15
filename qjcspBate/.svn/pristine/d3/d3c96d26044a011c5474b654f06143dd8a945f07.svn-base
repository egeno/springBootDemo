package com.qjkj.qjcsp.service.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.GMachineZone;
import com.qjkj.qjcsp.entity.GUniversity;
import com.qjkj.qjcsp.mapper.GCityMapper;
import com.qjkj.qjcsp.mapper.GHotCityMapper;
import com.qjkj.qjcsp.mapper.GMachineZoneMapper;
import com.qjkj.qjcsp.mapper.GUniversityMapper;
import com.qjkj.qjcsp.util.CityInitialUtil;

import net.sf.json.JSONObject;

@Service
public class LocationAPPService {

	@Autowired
	private GCityMapper gCityMapper;
	
	@Autowired
	private GUniversityMapper gUniversityMapper;
	
	@Autowired
	private GHotCityMapper gHotCityMapper;
	
	@Autowired
	private GMachineZoneMapper gMachineZoneMapper;

	/**
	 * 获取所有城市列表
	 */
	public Map<String, Object> getAllCitiesList() {
		Map<String, Object> returnResult = new HashMap<String, Object>();
		String[] cityInitials = CityInitialUtil.cityInitials;
		// 查询所有城市列表
		List<Map<String, Object>> listCityIninials = gCityMapper.getAllCitiesList();
		// 定义returnContent
		List<Map<String, Object>> returnContent = new ArrayList<Map<String, Object>>();
		//存放返回数据的map
		Map<String, Object> returnMap=new HashMap<String, Object>();

		for (String cityInitial : cityInitials) {
			// 所有的同一首字母城市集合
			List<Map<String, Object>> cities = new ArrayList<Map<String, Object>>();

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("initial", cityInitial);
			for (int i = 0; i < listCityIninials.size(); i++) {
				Map<String, Object> map = listCityIninials.get(i);
				if (cityInitial.equals(map.get("en"))) {
					cities.add(map);
				}
			}
			map1.put("cities", cities);
			returnContent.add(map1);
		}
		returnMap.put("allCities", returnContent);
		
		// 获取热门城市列表
		List<Map<String, Object>> listHotCities = gCityMapper.getHotCitiesList();
		returnMap.put("hotCities", listHotCities);
		returnResult.put("returnCode", "1");
		returnResult.put("returnContent", returnMap);
		returnResult.put("requestMethod", "getAllCitiesList");
		return returnResult;
	}
	
	/**
	 * 根据城市名称获取所有大学列表
	 * @param res
	 * @return
	 */
	public Map<String, Object> getAllUniversitiesList(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Map<String, Object>> returnContent = new ArrayList<Map<String,Object>>();
		if (res.has("cityName") && StringUtils.isNotBlank(res.getString("cityName"))){
			String cityName = res.getString("cityName");
			List<Map<String, Object>> cityZoneList = gUniversityMapper.queryZoneByCityName(cityName);
			/*遍历该城市所有区域*/
			for (Map<String, Object> cityZone : cityZoneList){
				Map<String, Object> dataTemp = new HashMap<String, Object>();
				dataTemp.put("zoneName", cityZone.get("zoneName").toString());
				List<Map<String, Object>> gUniversities = gUniversityMapper.queryUniversitiesByZoneAndCity(cityZone.get("zoneName").toString(), cityZone.get("cityId").toString());
				dataTemp.put("universities", gUniversities);
				
				returnContent.add(dataTemp);
			}
			/*判断是否有用于定位的经纬度*/
			if (res.has("longitude") && res.has("latitude") && 
					StringUtils.isNoneBlank(res.getString("longitude"), res.getString("latitude"))){
				double longitude = res.getDouble("longitude");
				double latitude = res.getDouble("latitude");
				
				Map<String, Object> dataTemp = new HashMap<String, Object>();
				List<Map<String, Object>> universitiesByGPSList = gUniversityMapper.queryUniversitiesByGPSLocation(longitude, latitude);
				dataTemp.put("zoneName", "定位");
				dataTemp.put("universities", universitiesByGPSList);
				
				returnContent.add(0, dataTemp);
			}
			/*热门城市点击量*/
			addOrUpdateHotCity(cityName);
		}else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
			
			return returnData;
		}
		returnData.put("returnCode", "1");
		returnData.put("returnContent", returnContent);
		
		return returnData;
	}
	
	/**
	 * 根据大学Id获取所有设备列表
	 * @param res
	 * @return
	 */
	public Map<String, Object> getAllMachinesList(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> returnContent = new HashMap<String, Object>();
		if (res.has("universityId") && StringUtils.isNotBlank(res.getString("universityId"))){
			List<Map<String, Object>> machineZones = new ArrayList<Map<String,Object>>();
			Long universityId = res.getLong("universityId");
			List<GMachineZone> machineZoneList = gMachineZoneMapper.queryMachineZoneByUniId(universityId);
			/*判断该大学是否有设备区域*/
			if (machineZoneList !=null && machineZoneList.size()>0){
				/*遍历该大学所有设备区域*/
				for (GMachineZone machineZone : machineZoneList){
					Map<String, Object> dataTemp = new HashMap<String, Object>();
					List<Map<String, Object>> machineList = gMachineZoneMapper.queryMachineByMachineZoneId(machineZone.getMachineZoneId());
					/*判断该区域是否有设备*/
					if (machineList !=null && machineList.size()>0){
						dataTemp.put("machineZoneName", machineZone.getMachineZoneName());
						dataTemp.put("machines", machineList);

						machineZones.add(dataTemp);
					}
				}
				
				returnContent.put("cityName", gUniversityMapper.getCityNameByUniversityId(universityId));
				returnContent.put("machineZones", machineZones);
			}else {
				Map<String, Object> dataTemp = new HashMap<String, Object>();
				dataTemp.put("cityName", gUniversityMapper.getCityNameByUniversityId(universityId));
				returnData.put("returnCode", "1");
				returnData.put("returnContent", dataTemp);
				
				return returnData;
			}
		}else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
			
			return returnData;
		}
		returnData.put("returnCode", "1");
		returnData.put("returnContent", returnContent);
		
		return returnData;
	}
	
	/**
	 * 模糊搜索城市
	 * @param res
	 * @return
	 */
	public Map<String, Object> fuzzySearchCity(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Map<String, Object>> returnContent = new ArrayList<Map<String,Object>>();
		/*whetherToGroup表示是否分组，为安卓端调用*/
		if (res.has("whetherToGroup")){
			if (res.has("keywords") && StringUtils.isNotBlank(res.getString("keywords"))){
				/*获取所有字母*/
				String[] cityInitials = CityInitialUtil.cityInitials;
				/*根据关键字得到的查询结果*/
				List<Map<String, Object>> gCityList = gCityMapper.fuzzySearchCity(res.getString("keywords"));
				if (gCityList != null && gCityList.size()>0){
					for (String cityInitial : cityInitials){
						Map<String, Object> dataTemp = new HashMap<String, Object>();
						List<Map<String, Object>> cities = new ArrayList<Map<String,Object>>();
						for (Map<String, Object> gCity : gCityList){
							/*获取该城市大写首字母*/
							char initial = gCity.get("spellShort").toString().toUpperCase().charAt(0);
							if (StringUtils.equals(cityInitial, String.valueOf(initial))){
								cities.add(gCity);
							}
						}
						if (cities !=null && cities.size()>0){
							dataTemp.put("initial", cityInitial);
							dataTemp.put("cities", cities);
							
							returnContent.add(dataTemp);
						}
					}
				}
			}else {
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "请求参数错误");
				
				return returnData;			
			}
		}else {
			if (res.has("keywords") && StringUtils.isNotBlank(res.getString("keywords"))){
				returnContent = gCityMapper.fuzzySearchCity(res.getString("keywords"));
			}else {
				returnData.put("returnCode", "0");
				returnData.put("returnContent", "请求参数错误");
				
				return returnData;			
			}
		}
		/*判断有无匹配结果*/
		if (returnContent != null && returnContent.size()>0){
			returnData.put("returnCode", "1");
			returnData.put("returnContent", returnContent);
		}else {
			returnData.put("returnCode", "1");
			returnData.put("returnContent", "未查询到符合条件的结果");
		}
		
		return returnData;
	}
	
	/**
	 * 模糊搜索大学
	 * @param res
	 * @return
	 */
	public Map<String, Object> fuzzySearchUniversity(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Map<String, Object>> returnContent = new ArrayList<Map<String,Object>>();
		if (res.has("keywords") && StringUtils.isNotBlank(res.getString("keywords"))){
			returnContent = gUniversityMapper.fuzzySearchUniversity(res.getString("keywords"));
		}else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
			
			return returnData;			
		}
		/*判断有无匹配结果*/
		if (returnContent != null && returnContent.size()>0){
			returnData.put("returnCode", "1");
			returnData.put("returnContent", returnContent);
		}else {
			returnData.put("returnCode", "1");
			returnData.put("returnContent", "未查询到符合条件的结果");
		}
		
		return returnData;
	}
	
	/**
	 * 增加热门城市点击量
	 * @param cityName
	 */
	private void addOrUpdateHotCity(String cityName){
		Long cityId = gCityMapper.getCityIdByCityName(cityName);
		gHotCityMapper.addOrUpdateHotCity(cityId);
	}
}
