package com.qjkj.qjcsp.service.goods;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblOrderDetail;
import com.qjkj.qjcsp.mapper.TblRetailMapper;

import net.sf.json.JSONObject;

@Service
public class ReplenishmentService {

	@Autowired
	private TblRetailMapper tblRetailMapper;
	public Map<String,Object> getReplenishmentList(JSONObject res){
		String deviceCode=res.getString("deviceCode");
		String areaModelId=res.getString("areaModelId");
		return null;
	}
}
