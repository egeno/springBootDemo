package com.qjkj.qjcsp.web.issue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.TblIssue;
import com.qjkj.qjcsp.service.issue.IssueService;

@Controller
@RequestMapping("issue")
public class IssueController {

	@Autowired
	private IssueService issueService;

	@RequestMapping(value = "/saveIssueList")
	@ResponseBody
	public boolean saveIssueList(@RequestBody List<TblIssue> issues) {// 接收前台以json字符串形式传递的对象为Tblissue的List集合
		try {
			// 添加数据
			issueService.saveIssuesList(issues);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@RequestMapping(value = "/delIssue")
	@ResponseBody
	public Map<String, Object> delIssue(Long issueId, Long goodsId) {
		return issueService.delIssue(issueId, goodsId);
	}
}
