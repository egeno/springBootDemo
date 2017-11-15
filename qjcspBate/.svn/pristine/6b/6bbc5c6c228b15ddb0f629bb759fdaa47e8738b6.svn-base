package com.qjkj.qjcsp.service.preissue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.TblIssue;
import com.qjkj.qjcsp.entity.TblPreissueEndTime;
import com.qjkj.qjcsp.entity.TblTemporaryRetail;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.TblIssueMapper;
import com.qjkj.qjcsp.mapper.TblPreissueEndTimeMapper;
import com.qjkj.qjcsp.util.Constants;

/**
 * 预定商品发布
 * 
 * @author carpeYe 2016-03-21
 *
 */
@Service
public class PreissueService {

	@Autowired
	private TblIssueMapper tblIssueMapper;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	@Autowired
	private TblPreissueEndTimeMapper tblPreissueEndTimeMapper;
	/*
	 * 查找发布商品列表
	 */
	public List<List<Map<String, Object>>> loadDataIndex(String date) {
		return findAllIssue(getAreaModelIdByCompanyId(), date);
	}

	/*
	 * 删除发布商品
	 */
	public ReturnJson deleteIssue(String issueId, String userSelectDate) {
		ReturnJson json = new ReturnJson();
		Integer num = tblIssueMapper.findPreissueByIssueIdAndPreissueDate(Long.valueOf(issueId));
		if (num == 0) {
			TblIssue issue = new TblIssue();
			issue.setIssueId(Long.valueOf(issueId));
			issue.setModTime(new Date());
			issue.setModUserId((long) Constants.getCurrendUser().getUserId());
			issue.setIsdel("Y");
			int i = tblIssueMapper.updateByPrimaryKeySelective(issue);
			if (i > 0) {
				json.setStatus(true);
				json.setMessage("发布商品删除成功！");
			} else {
				json.setStatus(false);
				json.setMessage("发布商品删除失败！");
			}
		} else {
			json.setStatus(false);
			json.setMessage("发布商品已被预订！");
		}
		return json;
	}

	/*
	 * 修改发布商品数量
	 */
	public ReturnJson updateIssueNum(String issueId, String issueNum) {
		ReturnJson json = new ReturnJson();
		TblIssue issue = new TblIssue();
		issue.setIssueId(Long.valueOf(issueId));
		issue.setModTime(new Date());
		issue.setModUserId((long) Constants.getCurrendUser().getUserId());
		issue.setIssueNum(Integer.valueOf(issueNum));
		int i = tblIssueMapper.updateByPrimaryKeySelective(issue);
		if (i > 0) {
			json.setStatus(true);
			json.setMessage("发布商品数量成功！");
		} else {
			json.setStatus(false);
			json.setMessage("发布商品数量失败！");
		}
		return json;
	}

	private List<BasicsAreaModel> getAreaModelIdByCompanyId() {
		Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		List<BasicsAreaModel> areaModels = basicsAreaModelMapper.selectAreaModelIdByCompanyId(companyId);
		return areaModels;
	}

	private List<List<Map<String, Object>>> findAllIssue(List<BasicsAreaModel> areaModels, String date) {
		List<List<Map<String, Object>>> issues = new ArrayList<List<Map<String, Object>>>();
		for (BasicsAreaModel basicsAreaModel : areaModels) {
			List<Map<String, Object>> issue = tblIssueMapper.selectIssueByDateAndCompany(date,
					basicsAreaModel.getAreaModelId());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaModelName", basicsAreaModel.getAreaModelName());
			issue.add(map);
			issues.add(issue);
		}
		return issues;
	}

	public List<Map<String, Object>> getIsSelectGoodsList(String selectDate, Long areaModelId) {
		Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		return tblIssueMapper.selectIsSelectIssueByDate(companyId, selectDate, areaModelId);
	}
	
	public Date getPreissueEndTime(){
		Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		TblPreissueEndTime tblPreissueEndTime= tblPreissueEndTimeMapper.selectByCompanyId(companyId);
		if(tblPreissueEndTime!=null){
			return tblPreissueEndTime.getPreissueEndTime();
		}
		return null;
	}

	/*
	 * private String StringFormat(Date date) { SimpleDateFormat sdf = new
	 * SimpleDateFormat("yyyy-MM-dd"); return sdf.format(date); }
	 */
}
