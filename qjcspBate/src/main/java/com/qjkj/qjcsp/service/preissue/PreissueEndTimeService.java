package com.qjkj.qjcsp.service.preissue;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblPreissueEndTime;
import com.qjkj.qjcsp.mapper.TblPreissueEndTimeMapper;
import com.qjkj.qjcsp.util.Constants;

@Service
public class PreissueEndTimeService {

	@Autowired
	private TblPreissueEndTimeMapper tblPreissueEndTimeMapper;

	public List<Map<String, Object>> findPreissueEndTimeByCompanyId() {
		Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		List<Map<String, Object>> preissueEndTime = tblPreissueEndTimeMapper
				.selectPreissueEndTimeByCompanyId(companyId);
		return preissueEndTime;
	}

	public int checkNeedAddDay(Integer companyId) {
		Date preissueEndTime = tblPreissueEndTimeMapper.queryPreissueEndTimeByCompanyId(companyId);
		Date d1 = new Date(); // 第一个时间
		SimpleDateFormat f = new SimpleDateFormat("HHmmss"); // 格式化为 HHmmss
		int d1Number = Integer.parseInt(f.format(d1).toString()); // 将第一个时间格式化后转为int
		int d2Number = Integer.parseInt(f.format(preissueEndTime).toString()); // 将第二个时间格式化后转为int
		// 判断时间是否小于十点
		/*
		 * if (d2Number <= 100000) { // 设置为23：59：59 d2Number = 239999; }
		 */
		if (d1Number > d2Number) {
			return 1;
		}
		return 0;
	}

	public int findPreissueEndTimeNumByCompanyId() {
		Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		int num = tblPreissueEndTimeMapper.findPreissueEndTimeNumByCompanyId(companyId);
		return num;
	}

	public synchronized Map<String, Object> addPreissueEndTime(String preissueEndTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long companyId = (long) Constants.getCurrendUser().getCompanyId();
			List<Map<String, Object>> list = tblPreissueEndTimeMapper.selectPreissueEndTimeByCompanyId(companyId);
			if (list.size() == 0) {
				TblPreissueEndTime tblPreissueEndTime = new TblPreissueEndTime();
				tblPreissueEndTime.setCompanyId((long) Constants.getCurrendUser().getCompanyId());
				tblPreissueEndTime.setCreateUserId((long) Constants.getCurrendUser().getUserId());
				tblPreissueEndTime.setCreateTime(new Date());
				tblPreissueEndTime.setPreissueEndTimeStr(preissueEndTime);
				tblPreissueEndTimeMapper.insertSelective(tblPreissueEndTime);
				map.put("status", true);
				map.put("message", "添加成功！");
			} else {
				map.put("status", false);
				map.put("message", "预定截止时间已添加，请勿重复添加！");
			}
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "添加失败！");
		}
		return map;
	}

	public Map<String, Object> editPreissueEndTime(String preissueEndTime, String preissueEndTimeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TblPreissueEndTime tblPreissueEndTime = new TblPreissueEndTime();
			tblPreissueEndTime.setPreissueEndTimeId(Long.valueOf(preissueEndTimeId));
			tblPreissueEndTime.setPreissueEndTimeStr(preissueEndTime);
			tblPreissueEndTime.setModTime(new Date());
			tblPreissueEndTime.setModUserId((long) Constants.getCurrendUser().getUserId());
			tblPreissueEndTimeMapper.updateByPrimaryKeySelective(tblPreissueEndTime);
			map.put("status", true);
			map.put("message", "修改成功！");
		} catch (Exception e) {
			map.put("status", true);
			map.put("message", "修改失败！");
		}
		return map;
	}

	// 获取公司的预订截止时间
	public Map<String, Object> getPreissueEndTime() {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmss");
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		Date preissueEndTime = tblPreissueEndTimeMapper.queryPreissueEndTimeByCompanyId(companyId);
		if (preissueEndTime == null) {
			map.put("result", false);
			map.put("data", "您未设置预订截止时间，请设置预订截止时间后再进行发布");
		} else {
			map.put("result", true);
			map.put("data", dateFormat.format(preissueEndTime));
		}
		return map;
	}
}
