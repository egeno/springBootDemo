package com.qjkj.qjcsp.web.address;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.address.AddressService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="address")
public class AddressController{

	@Autowired
	private AddressService addressService;
	
	/**
	 * 获取所有省份
	 * @return
	 */
	@RequestMapping(value="/findProvinceAllList")
	@ResponseBody
	public JSONArray findProvinceAllList(){
		return addressService.findProvinceAllList(); 
	}
	
	/**
	 * 获取所有城市
	 * @return
	 */
	@RequestMapping(value="/findCityAllList")
	@ResponseBody
	public JSONArray findCityAllList(ServletRequest request,@RequestParam(value = "id", defaultValue ="") String id){
		return addressService.findCityAllList(id); 
	}
	
}
