package com.qjkj.qjcsp.service.issue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblIssue;
import com.qjkj.qjcsp.mapper.TblIssueMapper;
import com.qjkj.qjcsp.util.Constants;

@Service
@Transactional
@Component
public class IssueService {
	@Autowired
	private TblIssueMapper issueMapper;
	@Autowired
	private TemporaryRetailService temporaryRetailService;

	public void saveIssuesList(List<TblIssue> issues) {
		long companyId = Constants.getCurrendUser().getCompanyId();
		long userId = Constants.getCurrendUser().getUserId();
		for (TblIssue issue : issues) {
			if (issue.getAreaModelId() == null) {
				continue;
			}
			// 按goodsid和模型ID去查询是否已存在商品
			TblIssue tblIssue = issueMapper.selectByGidAndAmid(issue);
			// 如果不存在商品，则新增商品
			if (tblIssue == null) {
				issue.setCompanyId(companyId);
				issue.setCreateUserId(userId);
				issue.setCreateTime(new Date());
				issue.setModTime(new Date());
				issue.setModUserId(userId);
				issue.setIsdel("N");
				issueMapper.insertSelectiveFormatDate(issue);
			} else {
				// 判断是否假删除
				if (tblIssue.getIsdel().equals("Y")) {
					// 恢复假删除
					tblIssue.setIsdel("N");
					issueMapper.updateByPrimaryKey(tblIssue);
				}
			}
		}
	}

	public Map<String, Object> delIssue(Long issueId, Long goodsId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int count = temporaryRetailService.getCount(issueId, goodsId);
		if (count > 0) {
			map.put("result", false);
			map.put("message", "删除失败，商品已被预订！");
			return map;
		}
		TblIssue issue = new TblIssue();
		issue.setIssueId(Long.valueOf(issueId));
		issue.setModTime(new Date());
		issue.setModUserId((long) Constants.getCurrendUser().getUserId());
		issue.setIsdel("Y");
		int i = issueMapper.updateByPrimaryKeySelective(issue);
		if (i > 0) {
			map.put("result", true);
			map.put("message", "商品删除成功！");
		} else {
			map.put("result", false);
			map.put("message", "商品删除失败，请稍后再试！");
		}
		return map;
	}
}
