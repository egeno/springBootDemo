package com.qjkj.qjcsp.service.wechatapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.BasicsCompanyMapper;

import net.sf.json.JSONObject;

@Component
@Transactional
public class CompanyWeChatApiService {

	@Autowired
	private BasicsCompanyMapper basicsCompanyMapper;
	
	/**
	 * 根据地名获取商户列表
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Object> getCompanyListByLocationWX(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		/*搜索地方*/
		String locationName = res.getString("locationName");
		
		if(StringUtils.isNotBlank(locationName)){
			List<Map<String, Object>> companyList = basicsCompanyMapper.getCompanyListByLocation((Map) res);
			if (companyList != null && companyList.size() > 0){
				returnData.put("returnCode", "1");
				returnData.put("returnContent", companyList);
			}else {
				returnData.put("returnCode", "2");
				returnData.put("returnContent", "没有符合条件的商户");
			}
		}else{
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
		}
		return returnData;
	}
}
