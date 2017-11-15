package com.qjkj.qjcsp.service.wechatapi;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblWeixinAccess;
import com.qjkj.qjcsp.mapper.TblWeixinAccessMapper;

import net.sf.json.JSONObject;

@Service
public class WeiXinAccessWeChatService {

	@Autowired
	private TblWeixinAccessMapper tblWeixinAccessMapper;
	
	public Map<String,Object> getHistoryMachineIdWX(JSONObject res){
		Map<String,Object> returnContent=new HashMap<String, Object>();
		String openId=res.getString("openId");
		if(StringUtils.isNoneBlank(openId)){
			Map<String,Object> tblWeixinAccess=tblWeixinAccessMapper.selectByOpenId(openId);
			if(tblWeixinAccess!=null){
				returnContent.put("returnContent", tblWeixinAccess);
				returnContent.put("returnCode", "1");
			}else{
				returnContent.put("returnContent", "");
				returnContent.put("returnCode", "1");
			}
		}else{
			returnContent.put("returnContent", "请求参数异常！");
			returnContent.put("returnCode", "0");
		}
		return returnContent;
	}
}
