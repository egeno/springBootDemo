package com.qjkj.qjcsp.service.discountactivity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblDayMealsLog;
import com.qjkj.qjcsp.mapper.TblDayMealsLogMapper;

import net.sf.json.JSONObject;

/**
 * 红包弹框业务层
 * 
 * @author carpeYe 2016-03-31
 *
 */
@Service
public class LuckyMoneyFrameService {

	@Autowired
	private TblDayMealsLogMapper tblDayMealsLogMapper;

	public Map<String, Object> getLuckyMoneyFrame(JSONObject res) {
		Map<String, Object> content = new HashMap<String, Object>();
		String customerId = res.getString("customerId");
		if (StringUtils.isNoneBlank(customerId)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			TblDayMealsLog tblDayMealsLog = tblDayMealsLogMapper.findDayMealActivityStatusByCustomerId(Long.valueOf(customerId));
			if (tblDayMealsLog != null) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("validLuckyMoney", "1");
				content.put("returnContent", data);
				content.put("returnCode", "1");
				tblDayMealsLog.setIsOk("Y");
				tblDayMealsLogMapper.updateByPrimaryKey(tblDayMealsLog);
			} else {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("validLuckyMoney", "0");
				content.put("returnContent", data);
				content.put("returnCode", "1");
			}
		} else {
			content.put("returnContent", "请求参数异常");
			content.put("returnCode", "0");
		}
		return content;
	}
}
