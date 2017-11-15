package com.qjkj.qjcsp.service.wechatapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblAdPictures;
import com.qjkj.qjcsp.mapper.TblAdPicturesMapper;

import net.sf.json.JSONObject;

@Service
@Transactional
public class WeChatAdPicturesService {

	@Autowired
	private TblAdPicturesMapper tblAdPicturesMapper;

	public Map<String, Object> getAppAdPicturesWX(JSONObject res) {
		Map<String, Object> content = new HashMap<String, Object>();
		Object adPicType = res.get("adPicType");
		if (adPicType != null && !"".equals(adPicType.toString())) {
			List<TblAdPictures> tblAdPictures = tblAdPicturesMapper.findAppAdPicturesByPicType(adPicType.toString());
			content.put("returnType", "1");
			content.put("returnContent", tblAdPictures);
		} else {
			content.put("returnType", "0");
			content.put("returnContent", "请求参数异常!");
		}
		return content;
	}
}
