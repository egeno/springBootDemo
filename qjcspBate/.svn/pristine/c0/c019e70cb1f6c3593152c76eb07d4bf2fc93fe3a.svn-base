package com.qjkj.qjcsp.web.preissue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.service.preissue.PreissueEndTimeService;

@Controller
@RequestMapping("preissueEndTime/")
public class PreissueEndTimeController {

	@Autowired
	private PreissueEndTimeService preissueEndTimeService;

	@RequestMapping("toIndex")
	public String toIndex(HttpServletRequest request) {
		int num = preissueEndTimeService.findPreissueEndTimeNumByCompanyId();
		request.setAttribute("num", num);
		return "preissueEndTime/preissueEndTimeIndex";
	}

	@RequestMapping("toLoadData")
	@ResponseBody
	public Map<String, Object> toLoadData() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> data = preissueEndTimeService.findPreissueEndTimeByCompanyId();
		map.put("rows", data);
		map.put("total", data.size());
		return map;
	}

	@RequestMapping("addPreissueEndTimewin")
	public String addPreissueEndTimewin(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		request.setAttribute("time", sdf.format(new Date()));
		return "preissueEndTime/addPreissueEndTime";
	}

	@RequestMapping("editPreissueEndTimewin")
	public String editPreissueEndTimewin() {
		return "preissueEndTime/editPreissueEndTime";
	}

	@RequestMapping(value = "addPreissueEndTime", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addPreissueEndTime(
			@RequestParam(value = "preissueEndtime", required = false) String preissueEndtime) {
		return preissueEndTimeService.addPreissueEndTime(preissueEndtime);
	}

	@RequestMapping("editPreissueEndTime")
	@ResponseBody
	public Map<String, Object> editPreissueEndTime(
			@RequestParam(value = "preissueEndtime", required = false) String preissueEndtime,
			@RequestParam(value = "preissueEndTimeId", required = false) String preissueEndTimeId) {
		return preissueEndTimeService.editPreissueEndTime(preissueEndtime, preissueEndTimeId);
	}

	@RequestMapping("getPreissueEndTime")
	@ResponseBody
	public Map<String, Object> getPreissueEndTime() {
		Map<String, Object> map = new HashMap<String, Object>();
		map = preissueEndTimeService.getPreissueEndTime();
		return map;
	}
}
