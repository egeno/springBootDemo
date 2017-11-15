package com.qjkj.qjcsp.service.wechatapi;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsJoininfo;
import com.qjkj.qjcsp.entity.enums.joinType;
import com.qjkj.qjcsp.mapper.BasicsJoininfoMapper;

import net.sf.json.JSONObject;

@Service
@Transactional
public class BasicsJoininfoService {

	@Autowired
	private BasicsJoininfoMapper basicsJoininfoMapper;

	public Map<String, Object> addJoin(JSONObject res) {
		Map<String, Object> map = new HashMap<String, Object>();
		String getJoinType = res.getString("joinType");
		if (StringUtils.equals(getJoinType, joinType.company_Type.value)) {
			return addCompanyJoin(res);
		} else if (StringUtils.equals(getJoinType, joinType.lease_Type.value)) {
			return addLeaseJoin(res);
		} else if (StringUtils.equals(getJoinType, joinType.cooperation_Type.value)) {
			return addCooperationJoin(res);
		} else if (StringUtils.equals(getJoinType, joinType.agent_Type.value)) {
			return addAgentJoin(res);
		} else if (StringUtils.equals(getJoinType, joinType.merchant_Type.value)) {
			return addMerchantJoin(res);
		} else if (StringUtils.equals(getJoinType, joinType.crowdsourcing_Type.value)) {
			return addCrowdsourcingJoin(res);
		} else {
			map.put("returnCode", "0");
			map.put("returnContent", "请选择正确的加盟类型");
			return map;
		}
	}

	private boolean checkMobile(String mobile) {
		boolean flag = false;
		Pattern regex = Pattern.compile("^1[3|4|5|7|8|9]\\d{9}$");
		Matcher matcher = regex.matcher(mobile);
		flag = matcher.matches();
		if (!flag) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 2.1.14.公司柜申请
	 */
	private Map<String, Object> addCompanyJoin(JSONObject res) {
		String applicant = res.getString("applicant");
		String applicantTel = res.getString("applicantTel");
		String joinType = res.getString("joinType");
		String content = res.getString("content");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isNoneBlank(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写手机号");
			return map;
		}
		if (checkMobile(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写正确的手机号");
			return map;
		}
		if (basicsJoininfoMapper.checkIsSend(applicantTel, joinType) > 0) {
			map.put("returnCode", "0");
			map.put("returnContent", "您已经申请过公司柜了");
			return map;
		}
		BasicsJoininfo joininfo = new BasicsJoininfo();
		joininfo.setApplicant(applicant);
		joininfo.setApplicantTel(applicantTel);
		joininfo.setJoinType(joinType);
		joininfo.setContent(content);
		basicsJoininfoMapper.insertSelective(joininfo);
		map.put("returnCode", "1");
		map.put("returnContent", "申请公司柜信息发送成功");
		return map;
	}

	/**
	 * 2.1.15.设备租赁申请
	 */
	private Map<String, Object> addLeaseJoin(JSONObject res) {
		String applicant = res.getString("applicant");
		String applicantTel = res.getString("applicantTel");
		String joinType = res.getString("joinType");
		String content = res.getString("content");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isNoneBlank(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写手机号");
			return map;
		}
		if (checkMobile(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写正确的手机号");
			return map;
		}
		if (basicsJoininfoMapper.checkIsSend(applicantTel, joinType) > 0) {
			map.put("returnCode", "0");
			map.put("returnContent", "您已经申请过设备租赁了");
			return map;
		}
		BasicsJoininfo joininfo = new BasicsJoininfo();
		joininfo.setApplicant(applicant);
		joininfo.setApplicantTel(applicantTel);
		joininfo.setJoinType(joinType);
		joininfo.setContent(content);
		basicsJoininfoMapper.insertSelective(joininfo);
		map.put("returnCode", "1");
		map.put("returnContent", "申请设备租赁信息发送成功");
		return map;
	}

	/**
	 * 2.1.16.设备合作申请
	 */
	private Map<String, Object> addCooperationJoin(JSONObject res) {
		String applicant = res.getString("applicant");
		String applicantTel = res.getString("applicantTel");
		String joinType = res.getString("joinType");
		String content = res.getString("content");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isNoneBlank(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写手机号");
			return map;
		}
		if (checkMobile(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写正确的手机号");
			return map;
		}
		if (basicsJoininfoMapper.checkIsSend(applicantTel, joinType) > 0) {
			map.put("returnCode", "0");
			map.put("returnContent", "您已经申请过设备合作了");
			return map;
		}
		BasicsJoininfo joininfo = new BasicsJoininfo();
		joininfo.setApplicant(applicant);
		joininfo.setApplicantTel(applicantTel);
		joininfo.setJoinType(joinType);
		joininfo.setContent(content);
		basicsJoininfoMapper.insertSelective(joininfo);
		map.put("returnCode", "1");
		map.put("returnContent", "申请设备合作信息发送成功");
		return map;
	}

	/**
	 * 2.1.17.区域代理申请
	 */
	private Map<String, Object> addAgentJoin(JSONObject res) {
		String applicant = res.getString("applicant");
		String applicantTel = res.getString("applicantTel");
		String joinType = res.getString("joinType");
		String content = res.getString("content");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isNoneBlank(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写手机号");
			return map;
		}
		if (checkMobile(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写正确的手机号");
			return map;
		}
		if (basicsJoininfoMapper.checkIsSend(applicantTel, joinType) > 0) {
			map.put("returnCode", "0");
			map.put("returnContent", "您已经申请过区域代理了");
			return map;
		}
		BasicsJoininfo joininfo = new BasicsJoininfo();
		joininfo.setApplicant(applicant);
		joininfo.setApplicantTel(applicantTel);
		joininfo.setJoinType(joinType);
		joininfo.setContent(content);
		basicsJoininfoMapper.insertSelective(joininfo);
		map.put("returnCode", "1");
		map.put("returnContent", "申请区域代理信息发送成功");
		return map;
	}

	/**
	 * 2.1.18.商户接入申请
	 */
	private Map<String, Object> addMerchantJoin(JSONObject res) {
		String applicant = res.getString("applicant");
		String applicantTel = res.getString("applicantTel");
		String joinType = res.getString("joinType");
		String content = res.getString("content");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isNoneBlank(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写手机号");
			return map;
		}
		if (checkMobile(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写正确的手机号");
			return map;
		}
		if (basicsJoininfoMapper.checkIsSend(applicantTel, joinType) > 0) {
			map.put("returnCode", "0");
			map.put("returnContent", "您已经申请过商户接入了");
			return map;
		}
		BasicsJoininfo joininfo = new BasicsJoininfo();
		joininfo.setApplicant(applicant);
		joininfo.setApplicantTel(applicantTel);
		joininfo.setJoinType(joinType);
		joininfo.setContent(content);
		basicsJoininfoMapper.insertSelective(joininfo);
		map.put("returnCode", "1");
		map.put("returnContent", "申请商户接入信息发送成功");
		return map;
	}

	/**
	 * 2.1.19.众包合作申请
	 */
	private Map<String, Object> addCrowdsourcingJoin(JSONObject res) {
		String applicant = res.getString("applicant");
		String applicantTel = res.getString("applicantTel");
		String joinType = res.getString("joinType");
		String content = res.getString("content");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isNoneBlank(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写手机号");
			return map;
		}
		if (checkMobile(applicantTel)) {
			map.put("returnCode", "0");
			map.put("returnContent", "请填写正确的手机号");
			return map;
		}
		if (basicsJoininfoMapper.checkIsSend(applicantTel, joinType) > 0) {
			map.put("returnCode", "0");
			map.put("returnContent", "您已经申请过众包合作了");
			return map;
		}
		BasicsJoininfo joininfo = new BasicsJoininfo();
		joininfo.setApplicant(applicant);
		joininfo.setApplicantTel(applicantTel);
		joininfo.setJoinType(joinType);
		joininfo.setContent(content);
		basicsJoininfoMapper.insertSelective(joininfo);
		map.put("returnCode", "1");
		map.put("returnContent", "申请众包合作信息发送成功");
		return map;
	}
}
