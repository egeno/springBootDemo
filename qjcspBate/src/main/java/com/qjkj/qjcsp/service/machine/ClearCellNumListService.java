package com.qjkj.qjcsp.service.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.GoodsMachineMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;

@Service
public class ClearCellNumListService {
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;

	@Autowired
	private GoodsMachineMapper goodsMachineMapper;

	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;

	public List<Map<String, Object>> getClearCellNumList(List<Map<String,Object>> goodsIds, String deviceCode) {
		BasicsMachine basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
		// 获取当前分区模型ID
		Long areaModelId = basicsMachine.getAreaModelId();
		// 获取设备ID
		Long machineId = basicsMachine.getMachineId();
		List<Map<String, Object>> returnContent = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < goodsIds.size(); i++) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			Object obj = goodsIds.get(i);
			Map<String, Object> map=(Map<String, Object>)obj;
			Long goodsId = Long.valueOf(map.get("goodsId").toString());
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(goodsId.toString()) && StringUtils.isNotEmpty(goodsId.toString())) {
				params.put("areaModelId", areaModelId);
				params.put("machineId", machineId);
				params.put("goodsId", goodsId);
				List<Map<String, Object>> cellList = new ArrayList<Map<String, Object>>();
				cellList = goodsMachineMapper.getClearCellNumList(params);
				BasicsGoods basicsGoods = basicsGoodsMapper.selectByPrimaryKey(goodsId);
				String goodsName = basicsGoods.getGoodsName();
				map1.put("goodsId", goodsId.toString());
				map1.put("goodsName", goodsName);
				map1.put("cellList", cellList);
				map1.put("goodsImage", basicsGoods.getGoodsIcon());

				String goodsSurpleNum = goodsMachineMapper.getGoodsSurpleNum(params).toString();
				map1.put("goodsSurpleNum", goodsSurpleNum);
				returnContent.add(map1);
			}

		}

		return returnContent;
	}

}
