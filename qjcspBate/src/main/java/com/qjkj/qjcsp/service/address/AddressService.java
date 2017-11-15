package com.qjkj.qjcsp.service.address;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.mapper.AddressMapper;
import net.sf.json.JSONArray;
import com.qjkj.qjcsp.entity.Address;

@Service
public class AddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	private List<Address> list;
	/**
	 * 获取所有省份
	 * @return
	 */
	public JSONArray findProvinceAllList(){
		list  = addressMapper.findProvinceAllList();
		return fromObject();
	}
	
	/**
	 * 获取所有城市
	 * @return
	 */
	public JSONArray findCityAllList(String id){
		list  = addressMapper.findCityAllList(Long.valueOf(id));
		return fromObject();
	}
	
	private JSONArray fromObject(){
		JSONArray jsonArray = JSONArray.fromObject(list);//转换成JSON格式
		return jsonArray;
	}
}