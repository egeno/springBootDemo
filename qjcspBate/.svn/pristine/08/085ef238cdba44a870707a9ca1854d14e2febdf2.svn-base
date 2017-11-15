package com.qjkj.qjcsp.service.appUser;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblCustomer;
import com.qjkj.qjcsp.entity.TblCustomerInfo;
import com.qjkj.qjcsp.entity.TblCustomerIntegralLog;
import com.qjkj.qjcsp.entity.viewmodel.TblCustomerVo;
import com.qjkj.qjcsp.mapper.TblCustomerInfoMapper;
import com.qjkj.qjcsp.mapper.TblCustomerIntegralLogMapper;
import com.qjkj.qjcsp.mapper.TblCustomerMapper;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.ExportExcelUtil;
import com.qjkj.qjcsp.util.MD5Util;

@Component
@Transactional
public class AppUserService {
	public static final char delFlag = '0'; // 删除标识
	public static final char succFlag = '1';

	@Autowired
	private TblCustomerMapper tblCustomerMapper;
	@Autowired
	private TblCustomerInfoMapper tblCustomerInfoMapper;
	@Autowired
	private TblCustomerIntegralLogMapper customerIntegralLogMapper;

	/**
	 * 查找APP用户积分信息
	 */
	public Page<TblCustomerVo> findAppUserIntegral(Map<String, Object> map, int pageNumber, int pageSize) {
		Long total = tblCustomerMapper.getCount(map);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		map.put("offset", (pageNumber - 1) * pageSize);
		map.put("limit", pageSize);
		List<TblCustomer> list = new ArrayList<TblCustomer>();
		List<TblCustomerVo> listVo = new ArrayList<TblCustomerVo>();
		TblCustomerVo customerVo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (total != 0) {
			list = tblCustomerMapper.findAppUserIntegral(map);
			for (TblCustomer tblCustomer : list) {
				TblCustomerInfo customerInfo = tblCustomerInfoMapper.selectByPrimaryKey(tblCustomer.getCustomerId());
				customerVo = new TblCustomerVo();
				customerVo.setUniversityName(customerInfo.getUniversityName());
				customerVo.setCustomerId(tblCustomer.getCustomerId());
				customerVo.setCustomerName(tblCustomer.getCustomerName());
				customerVo.setCustomerMobile(tblCustomer.getCustomerMobile());
				if (tblCustomer.getCreateTime() != null) {
					customerVo.setCreateTimeStr(sdf.format(tblCustomer.getCreateTime()));
				}
				customerVo.setCustomerIntegral(tblCustomer.getCustomerIntegral());
				listVo.add(customerVo);
			}
		}
		Page<TblCustomerVo> page = new PageImpl<TblCustomerVo>(listVo, pageRequest, total);
		return page;
	}

	/**
	 * 积分修改
	 * 
	 * @param tblCustomerVo
	 * @return
	 */
	public boolean updateIntegral(TblCustomerVo tblCustomerVo) {
		TblCustomer tblCustomer = tblCustomerMapper.selectByPrimaryKey(tblCustomerVo.getCustomerId());
		boolean flag = false;
		if (tblCustomer != null) {
			long userId = Constants.getCurrendUser().getUserId();
			if (tblCustomerVo.getCustomerIntegral() != tblCustomer.getCustomerIntegral()) {
				TblCustomerIntegralLog tblCustomerIntegralLog = new TblCustomerIntegralLog();
				tblCustomerIntegralLog.setCustomerId(tblCustomerVo.getCustomerId());
				tblCustomerIntegralLog.setIntegral(tblCustomerVo.getCustomerIntegral());
				tblCustomerIntegralLog.setOldIntegral(tblCustomer.getCustomerIntegral());
				tblCustomerIntegralLog.setCreateTime(new Date());
				tblCustomerIntegralLog.setCreateUserId(userId);
				customerIntegralLogMapper.insert(tblCustomerIntegralLog);
			}
			tblCustomerVo.setModifyTime(new Date());
			tblCustomerVo.setModUserId(userId);
			int r = tblCustomerMapper.update(tblCustomerVo);
			if (r > 0) {
				flag = true;
			}
		}
		return flag;

	}

	/**
	 * 查找APP用户信息
	 */
	public Page<TblCustomerVo> findAPPUserInfo(Map<String, Object> map, int pageNumber, int pageSize) {
		Long total = tblCustomerMapper.getInfoByCount(map);
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		map.put("offset", (pageNumber - 1) * pageSize);
		map.put("limit", pageSize);
		List<TblCustomer> list = new ArrayList<TblCustomer>();
		List<TblCustomerVo> listVo = new ArrayList<TblCustomerVo>();
		TblCustomerVo customerVo = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (total != 0) {
			list = tblCustomerMapper.findAPPUserInfo(map);
			for (TblCustomer tblCustomer : list) {
				TblCustomerInfo customerInfo = tblCustomerInfoMapper.selectByPrimaryKey(tblCustomer.getCustomerId());
				customerVo = new TblCustomerVo();
				if (customerInfo == null) {
					customerVo.setCustomerId(tblCustomer.getCustomerId());
					customerVo.setCustomerName(tblCustomer.getCustomerName());
					customerVo.setCustomerPassword(tblCustomer.getCustomerPassword());
					customerVo.setPayPassword(tblCustomer.getPayPassword());
					customerVo.setCustomerMobile(tblCustomer.getCustomerMobile());
					customerVo.setCustomerEmail(tblCustomer.getCustomerEmail());
					customerVo.setRealName(tblCustomer.getRealName());
					customerVo.setCustomerSex(tblCustomer.getCustomerSex());
					customerVo.setCustomerQq(tblCustomer.getCustomerQq());
					customerVo.setCustomerWeixin(tblCustomer.getCustomerWeixin());
					customerVo.setCustomerMoney(tblCustomer.getCustomerMoney());
					customerVo.setCustomerIntegral(tblCustomer.getCustomerIntegral());
					customerVo.setCreateTimeStr(sdf.format(tblCustomer.getCreateTime()));
					customerVo.setModifyTimeStr(sdf.format(tblCustomer.getModifyTime()));
					customerVo.setCreateUserId(tblCustomer.getCreateUserId());
					customerVo.setModUserId(tblCustomer.getModUserId());
					customerVo.setCustomerIntegral(tblCustomer.getCustomerIntegral());
					listVo.add(customerVo);
				} else {
					customerVo.setUniversityName(customerInfo.getUniversityName());
					customerVo.setBuildingName(customerInfo.getBuildingName());
					customerVo.setGrade(customerInfo.getGrade());
					customerVo.setStudentNum(customerInfo.getStudentNum());
					customerVo.setNativePlace(customerInfo.getNativePlace());
					customerVo.setCustomerId(tblCustomer.getCustomerId());
					customerVo.setCustomerName(tblCustomer.getCustomerName());
					customerVo.setCustomerPassword(tblCustomer.getCustomerPassword());
					customerVo.setPayPassword(tblCustomer.getPayPassword());
					customerVo.setCustomerMobile(tblCustomer.getCustomerMobile());
					customerVo.setCustomerEmail(tblCustomer.getCustomerEmail());
					customerVo.setRealName(tblCustomer.getRealName());
					customerVo.setCustomerSex(tblCustomer.getCustomerSex());
					customerVo.setCustomerQq(tblCustomer.getCustomerQq());
					customerVo.setCustomerWeixin(tblCustomer.getCustomerWeixin());
					customerVo.setCustomerMoney(tblCustomer.getCustomerMoney());
					customerVo.setCustomerIntegral(tblCustomer.getCustomerIntegral());
					if (tblCustomer.getCreateTime() != null) {
						customerVo.setCreateTimeStr(sdf.format(tblCustomer.getCreateTime()));
					}
					if (tblCustomer.getModifyTime() != null) {
						customerVo.setModifyTimeStr(sdf.format(tblCustomer.getModifyTime()));
					}
					customerVo.setCreateUserId(tblCustomer.getCreateUserId());
					customerVo.setModUserId(tblCustomer.getModUserId());
					customerVo.setCustomerIntegral(tblCustomer.getCustomerIntegral());
					listVo.add(customerVo);
				}

			}
		}
		Page<TblCustomerVo> page = new PageImpl<TblCustomerVo>(listVo, pageRequest, total);
		return page;
	}

	/**
	 * 添加、编辑APP用户信息
	 */
	public boolean persistenceAPPInfoEdit(TblCustomerVo tblCustomerVo) {
		long userId = Constants.getCurrendUser().getUserId();
		TblCustomerInfo tblCustomerInfo = new TblCustomerInfo();
		// 添加
		if (null == tblCustomerVo.getCustomerId() || "".equals(tblCustomerVo.getCustomerId())) {
			tblCustomerVo.setIsdel("N");
			tblCustomerVo.setCreateTime(new Date());
			tblCustomerVo.setModifyTime(new Date());
			tblCustomerVo.setCreateUserId(userId);
			tblCustomerVo.setModUserId(userId);
			tblCustomerVo.setIsfirst("0");
			tblCustomerMapper.insertSelective(tblCustomerVo);
			tblCustomerInfo.setCustomerId(tblCustomerVo.getCustomerId());
			if (tblCustomerVo.getUniversityName() != null) {
				tblCustomerInfo.setUniversityName(tblCustomerVo.getUniversityName());
			}
			if (tblCustomerVo.getBuildingName() != null) {
				tblCustomerInfo.setBuildingName(tblCustomerVo.getBuildingName());
			}
			if (tblCustomerVo.getGrade() != null) {
				tblCustomerInfo.setGrade(tblCustomerVo.getGrade());
			}
			if (tblCustomerVo.getStudentNum() != null) {
				tblCustomerInfo.setStudentNum(tblCustomerVo.getStudentNum());
			}
			if (tblCustomerVo.getNativePlace() != null) {
				tblCustomerInfo.setNativePlace(tblCustomerVo.getNativePlace());
			}
			tblCustomerInfo.setCreateTime(new Date());
			tblCustomerInfo.setModifyTime(new Date());
			tblCustomerInfo.setCreateUserId(userId);
			tblCustomerInfo.setModUserId(userId);
			int t = tblCustomerInfoMapper.insertSelective(tblCustomerInfo);
			return t > 0;
		}
		// 编辑
		else {
			tblCustomerVo.setModifyTime(new Date());
			tblCustomerVo.setModUserId(userId);
			tblCustomerVo.setIsfirst("1");
			tblCustomerMapper.update(tblCustomerVo);
			tblCustomerInfo.setCustomerId(tblCustomerVo.getCustomerId());
			if (tblCustomerVo.getUniversityName() != null) {
				tblCustomerInfo.setUniversityName(tblCustomerVo.getUniversityName());
			}
			if (tblCustomerVo.getBuildingName() != null) {
				tblCustomerInfo.setBuildingName(tblCustomerVo.getBuildingName());
			}
			if (tblCustomerVo.getGrade() != null) {
				tblCustomerInfo.setGrade(tblCustomerVo.getGrade());
			}
			if (tblCustomerVo.getStudentNum() != null) {
				tblCustomerInfo.setStudentNum(tblCustomerVo.getStudentNum());
			}
			if (tblCustomerVo.getNativePlace() != null) {
				tblCustomerInfo.setNativePlace(tblCustomerVo.getNativePlace());
			}
			tblCustomerInfo.setModifyTime(new Date());
			tblCustomerInfo.setModUserId(userId);
			int r = tblCustomerInfoMapper.updateByPrimaryKeySelective(tblCustomerInfo);
			return r > 0;
		}
	}

	/**
	 * 删除用户信息
	 */
	public boolean deleteUserByCustomerId(Long customerId) {
		int r = tblCustomerMapper.updateIsdel(customerId);
		return r > 0;
	}

	/**
	 * 导出用户信息
	 * 
	 * @param request
	 * @param response
	 */
	public void exportSearchDaily(ServletRequest request, HttpServletResponse response) {
		Map<String, Object> param = new HashMap<String, Object>();
		String customerId = request.getParameter("customerId");
		try {
			customerId = java.net.URLDecoder.decode(customerId, "UTF-8");
			param.put("customerId", customerId);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param.put("customerName", request.getParameter("customerName"));
		param.put("sort", Sort.Direction.DESC);
		/* 根据条件查询订单 */
		List<Map<String, Object>> list = tblCustomerMapper.exportSearchDaily(param);

		// if (list != null && list.size() > 0){
		String title = "用户信息表";
		String[] titles = { "用户id", "用户姓名", "手机号", "性别", "用户余额", "用户积分", "职业", "籍贯", "年龄", "地址", "口味" };
		Integer[] sheets = { 150, 100, 150, 100, 150, 150, 100, 100, 100, 150, 100 };
		String[] keys = { "customerId", "customerName", "customerMobile", "customerSex", "customerMoney",
				"customerIntegral", "buildingName", "nativePlace", "studentNum", "universityName", "grade" };
		List<List<Integer>> rows = new ArrayList<List<Integer>>();
		ExportExcelUtil.exportExcel(response, title, sheets, titles, rows, keys, list);
		// }
	}
}
