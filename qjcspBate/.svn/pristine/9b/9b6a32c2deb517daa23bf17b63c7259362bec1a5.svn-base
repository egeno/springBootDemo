package com.qjkj.qjcsp.web.api.device;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qjkj.qjcsp.service.goods.GoodsCategoryService;
import com.qjkj.qjcsp.util.RequestData;

import net.sf.json.JSONObject;
/**
 * 2.2.8.	获取商品分类列表
 * @author carpeYe
 *
 */
@RestController
@RequestMapping("api/device/")
public class CategoryController {

	@Autowired
	private GoodsCategoryService goodsCateGoryService;
	@RequestMapping("getCategoryList")
	public Map<String,Object> getCategoryList(HttpServletRequest request){
		String str=RequestData.getRequestPostJson(request);
	    JSONObject json=JSONObject.fromObject(str);
	    Map<String,Object> map=goodsCateGoryService.selectGoodsCategoryByCompanyId(json);
	    map.put("requestMethod", "getCategoryList");
	    return map;
	}
}
