package com.qjkj.qjcsp.service.wechatapi;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblWeixinLeague;
import com.qjkj.qjcsp.mapper.TblWeixinLeagueMapper;

import net.sf.json.JSONObject;

@Service
@Transactional
public class WeiXinLeagueService {
	@Autowired
	private TblWeixinLeagueMapper weixinLeagueMapper;

	public Map<String, Object> addWeiXinLeagueInfoWX(JSONObject res) {
		Map<String, Object> map = new HashMap<String, Object>();
		String name = res.getString("name");
		String mobileNum = res.getString("telPhone");
		String content = res.getString("content");
		boolean flag = false;
		Pattern regex = Pattern.compile("^1[3|4|5|7|8|9]\\d{9}$");
		Matcher matcher = regex.matcher(mobileNum);
		flag = matcher.matches();
		if (!flag) {
			map.put("returnCode", "0");
			map.put("returnContent", "手机号格式有误");
			return map;
		}
		int count = weixinLeagueMapper.selectByTelPhone(mobileNum);
		if (count > 0) {
			map.put("returnCode", "0");
			map.put("returnContent", "您填写的手机号已经申请过啦");
		} else {
			TblWeixinLeague tblWeixinLeague = new TblWeixinLeague();
			tblWeixinLeague.setName(name);
			tblWeixinLeague.setTelPhone(mobileNum);
			tblWeixinLeague.setContent(content);
			weixinLeagueMapper.insert(tblWeixinLeague);
			map.put("returnCode", "1");
			map.put("returnContent", "申请消息发送成功");
		}
		return map;
	}
}
