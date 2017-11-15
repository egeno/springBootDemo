package com.qjkj.qjcsp.service.code;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblIosVersion;
import com.qjkj.qjcsp.mapper.TblIosVersionMapper;

import net.sf.json.JSONObject;

/**
 * 2.3.26. IOS测试标志位
 * 
 * @author carpeYe 2016-03-24
 *
 */
@Service
public class IOSTestSymbolService {

	@Autowired
	private TblIosVersionMapper tblIosVersionMapper;

	public Map<String, Object> IOSTestVersion(JSONObject res) {
		Map<String, Object> content = new HashMap<String, Object>();
		if (res == null) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("IOSTestSymbol", "0");
			content.put("returnCode", "1");
			content.put("returnContent", data);
		} else {
			String versionKey = res.getString("versionKey");
			if (StringUtils.isNoneBlank(versionKey)) {
				TblIosVersion tblIosVersion = tblIosVersionMapper.selectIosVersionByVersionKey(versionKey);
				Map<String, Object> data = new HashMap<String, Object>();
				if (tblIosVersion != null) {
					data.put("IOSTestSymbol", tblIosVersion.getVersionValue());
				}
				content.put("returnCode", "1");
				content.put("returnContent", data);
			} else {
				content.put("returnCode", "0");
				content.put("returnContent", "请求参数异常");
			}
		}
		return content;
	}
}
