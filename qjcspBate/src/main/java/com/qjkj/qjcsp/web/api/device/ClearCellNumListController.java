package com.qjkj.qjcsp.web.api.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.machine.ClearCellNumListService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("api/device/")
public class ClearCellNumListController {

	public static Logger logger = LoggerFactory.getLogger(ClearCellNumListController.class);
	@Autowired
	private ClearCellNumListService clearCellNumListService;

	// 获取清货的格子列表
	@RequestMapping(value = "getClearCellNumList", method = RequestMethod.POST)
	public Map<String, Object> getClearCellNumList(ServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			String str = RequestData.getRequestPostJson(request);
			JSONObject json = JSONObject.fromObject(str);
			String deviceCode = json.get("deviceCode").toString();
			JSONArray goodsIds1 = json.getJSONArray("goodsIds");
			
			List<Map<String,Object>> goodsIds = JSONArray.toList(goodsIds1,Map.class);
			//String goodsId = json.get("goodsId").toString();
			// if (StringUtils.isNotEmpty(goodsId) &&
			// StringUtils.isNotEmpty(goodsId) &&
			// StringUtils.isNotEmpty(deviceCode)
			// && StringUtils.isNotBlank(deviceCode)) {

			List<Map<String,Object>> returnContent = new ArrayList<Map<String,Object>>();
			returnContent=clearCellNumListService.getClearCellNumList(goodsIds, deviceCode);
			
			map.put("returnCode", "1");
			map.put("returnContent", returnContent);
			// } else {
			// map.put("returnCode", "0");
			// map.put("returnContent", "请求参数错误");
			// }
		} catch (Exception e) {
			logger.error("请求参数错误", e);
			map.put("returnCode", "0");
			map.put("returnContent", "服务器错误");
		}
		return map;
	}

}
