package com.qjkj.qjcsp.service.machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.BasicsMachineCellMapper;

import net.sf.json.JSONObject;

@Service
public class MachineCellService {
	@Autowired
	private BasicsMachineCellMapper basicsMachineCellMapper;

	/**
	 * 2.2.7. 获取补货格子列表
	 * 
	 * @param deviceCode:设备Id
	 *            cellsNum：商品数量
	 */
	public Map<String, Object> getOperateCellsNum(JSONObject jsonObject) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!jsonObject.has("deviceCode") || !jsonObject.has("cellsNum")) {
			map.put("returnCode", "0");
			map.put("returnContent", "请求参数错误");
			return map;
		}
		String deviceCode = jsonObject.getString("deviceCode");
		String cellsNum = jsonObject.getString("cellsNum");
		if (!StringUtils.isAnyBlank(deviceCode, cellsNum)) {
			Integer cellnum = Integer.parseInt(cellsNum);
			List<Map<String, Object>> list = basicsMachineCellMapper.getOperateCellsNum(deviceCode, cellnum);
			if (list.size() < cellnum) {
				map.put("returnCode", "0");
				map.put("returnContent", "可用格子数量不足");
				return map;
			}
			map.put("returnCode", "1");
			map.put("returnContent", list);
		} else {
			map.put("returnCode", "0");
			map.put("returnContent", "请求参数错误");
			return map;
		}
		return map;
	}

	/**
	 * 根据设备ID获取设备的可用格子数量
	 */
	public Integer getMachineCellNum(Long machineId) {
		return basicsMachineCellMapper.getMachineCellNum(machineId);
	}
}
