package com.qjkj.qjcsp.service.version;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblAndroidVersion;
import com.qjkj.qjcsp.mapper.TblAndroidVersionMapper;

import net.sf.json.JSONObject;

@Service
public class VersionAppService {

	@Autowired
	private TblAndroidVersionMapper tblAndroidVersionMapper;

	/**
	 * 安卓端查询是否有新版本
	 */
	public Map<String, Object> getVersionByAndroid(JSONObject res) {
		Map<String, Object> returnResult = new HashMap<String, Object>();
		//存放返回数据的map
		Map<String, Object> returnMap=new HashMap<String, Object>();
		//查询版本号 更新信息 更新链接
		TblAndroidVersion version=tblAndroidVersionMapper.getVersionByAndroid(res.get("appType").toString());
		if (version!=null) {
			returnMap.put("updateContent", version.getUpdateContent());
			returnMap.put("version", version.getVersion());
			returnMap.put("updateUrl", version.getUpdateUrl());
		}
		returnResult.put("returnCode", "1");
		returnResult.put("returnContent", returnMap);
		returnResult.put("requestMethod", "getVersionByAndroid");
		return returnResult;
	}
}
