package com.qjkj.qjcsp.web.preissue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.enums.areaModelEnum;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.service.preissue.PreissueService;
import com.qjkj.qjcsp.util.DateUtils;

@Controller
@RequestMapping("preissue")
public class PreIssueController {

	@Autowired
	private PreissueService preissueService;

	@RequestMapping("/preIssueIndex")
	public String preIssueIndex() {
		return "preissue/index";
	}

	
	/*
	 * 查询发布商品
	 */
	@RequestMapping("/loadDataIndex")
	@ResponseBody
	public List<List<Map<String, Object>>> loadDataIndex(@RequestParam("date") String date) {
		return preissueService.loadDataIndex(date);
	}

	/*
	 * 删除发布商品
	 */
	@RequestMapping("/deleteIssue")
	@ResponseBody
	public ReturnJson deleteIssue(@RequestParam(value = "issueId") String issueId,
			@RequestParam(value = "userSelectDate") String userSelectDate) {
		return preissueService.deleteIssue(issueId, userSelectDate);
	}

	/*
	 * 修改发布商品
	 */
	@RequestMapping("/updateIssue")
	@ResponseBody
	public ReturnJson updateIssue(@RequestParam(value = "issueId") String issueId,
			@RequestParam(value = "issueNum") String issueNum) {
		return preissueService.updateIssueNum(issueId, issueNum);
	}

	/**
	 * 获取已发布的指定模型预订商品集合
	 */
	@RequestMapping("/getIsSelectGoodsList")
	@ResponseBody
	public List<Map<String, Object>> getIsSelectGoodsList(@RequestParam(value = "selectDate") String selectDate,
			@RequestParam(value = "areaModelId") Long areaModelId) {
		return preissueService.getIsSelectGoodsList(selectDate, areaModelId);
	}
	@RequestMapping("/getCurrentTime")
	@ResponseBody
	public ReturnJson getCurrentTime(){
		ReturnJson returnJson=new ReturnJson();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
		Date preissueEndTimes=preissueService.getPreissueEndTime();
		if(preissueEndTimes!=null){
		String preissueEndTime=sdf1.format(new Date())+" "+sdf2.format(preissueEndTimes);
		returnJson.setTitle(preissueEndTime);
		returnJson.setMessage(sdf.format(new Date()));
		returnJson.setStatus(true);
		}else{
			returnJson.setTitle("预定截止未设置，无法发布预定商品！");
			returnJson.setMessage(sdf.format(new Date()));
			returnJson.setStatus(false);
		}
		return returnJson;
	}
}
