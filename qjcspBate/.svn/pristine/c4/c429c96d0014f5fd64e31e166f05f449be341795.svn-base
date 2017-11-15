package com.qjkj.qjcsp.service.machine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblMachineTemplateRetailDate;
import com.qjkj.qjcsp.entity.TblMachineTemplateRetailDateLog;
import com.qjkj.qjcsp.mapper.TblMachineTemplateRetailDateLogMapper;
import com.qjkj.qjcsp.mapper.TblMachineTemplateRetailDateMapper;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateTimeUtil;
import com.qjkj.qjcsp.util.DateUtils;

@Service
@Transactional
public class MachineTemplateRetailDateService {

	@Autowired
	private TblMachineTemplateRetailDateMapper templateRetailDateMapper;
	@Autowired
	private TblMachineTemplateRetailDateLogMapper machineTemplateRetailDateLogMapper;

	/**
	 * 获取本周的每天对应的模板数据
	 */
	public List<Map<String, Object>> getTemplateInfo() {
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// List<String> list = DateUtils.getThisWeekDate();
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		List<Map<String, Object>> maps = templateRetailDateMapper.getTemplateInfo(companyId);
		/*
		 * for (int i = 0; i < maps.size(); i++) { Map<String, Object> map =
		 * maps.get(i); Date date = (Date) map.get("retailDate"); int dayNum =
		 * DateTimeUtil.getWeekNumOfDate(date); map.put("dayOfWeek", dayNum);
		 * map.put("retailDate", dateFormat.format(date)); }
		 */
		return maps;
	}

	/**
	 * 根据ID获取模板数据
	 */
	public List<Map<String, Object>> getTemplateInfoById(Long templateId) {
		List<Map<String, Object>> maps = templateRetailDateMapper.getTemplateInfoById(templateId);
		return maps;
	}

	/**
	 * 编辑设备套用模板数据
	 */
	public void updateTemplateRetailDate(List<TblMachineTemplateRetailDate> machineTemplateRetailDates) {
		for (TblMachineTemplateRetailDate tblMachineTemplateRetailDate : machineTemplateRetailDates) {
			if ("".equals(tblMachineTemplateRetailDate.getTemplateId())
					|| tblMachineTemplateRetailDate.getTemplateId() == null) {
				Long id = templateRetailDateMapper.getByMachineIdAndRetailDate(
						tblMachineTemplateRetailDate.getMachineId(), tblMachineTemplateRetailDate.getRetailDate());
				// 删除
				templateRetailDateMapper.deleteByMachineIdAndRetailDate(tblMachineTemplateRetailDate.getMachineId(),
						tblMachineTemplateRetailDate.getRetailDate());
				if (id != null) {
					// 添加数据到日志表
					TblMachineTemplateRetailDateLog record = new TblMachineTemplateRetailDateLog();
					record.setOperateTime(new Date());
					record.setMachineTemplateRetailDateId(id);
					record.setOperateType("1");
					record.setOperateUserId(Long.parseLong(Constants.getCurrendUser().getUserId().toString()));
					machineTemplateRetailDateLogMapper.insertSelective(record);
				}
			} else {
				//删除
				templateRetailDateMapper.deleteByMachineIdAndRetailDate(tblMachineTemplateRetailDate.getMachineId(),
						tblMachineTemplateRetailDate.getRetailDate());
				/*// 查询是否存在
				int count = templateRetailDateMapper.getCountByMachineAndRetailDate(
						tblMachineTemplateRetailDate.getMachineId(), tblMachineTemplateRetailDate.getRetailDate());
				if (count > 0) {
					// 修改
					templateRetailDateMapper.updateTemplateByMachineAndRetailDate(
							tblMachineTemplateRetailDate.getMachineId(), tblMachineTemplateRetailDate.getRetailDate(),
							tblMachineTemplateRetailDate.getTemplateId());
					// 添加数据到日志表
					TblMachineTemplateRetailDateLog record = new TblMachineTemplateRetailDateLog();
					record.setOperateTime(new Date());
					record.setMachineTemplateRetailDateId(templateRetailDateMapper.getByMachineIdAndRetailDate(
							tblMachineTemplateRetailDate.getMachineId(), tblMachineTemplateRetailDate.getRetailDate()));
					record.setOperateType("2");
					record.setOperateUserId(Long.parseLong(Constants.getCurrendUser().getUserId().toString()));
					machineTemplateRetailDateLogMapper.insertSelective(record);
				} else {*/
					// 新增
					TblMachineTemplateRetailDate machineTemplateRetailDate = new TblMachineTemplateRetailDate();
					machineTemplateRetailDate.setCreateTime(new Date());
					machineTemplateRetailDate
							.setCreateUserId(Long.parseLong(Constants.getCurrendUser().getUserId().toString()));
					machineTemplateRetailDate.setModTime(new Date());
					machineTemplateRetailDate
							.setModUserId(Long.parseLong(Constants.getCurrendUser().getUserId().toString()));
					machineTemplateRetailDate.setMachineId(tblMachineTemplateRetailDate.getMachineId());
					machineTemplateRetailDate.setTemplateId(tblMachineTemplateRetailDate.getTemplateId());
					machineTemplateRetailDate.setRetailDate(tblMachineTemplateRetailDate.getRetailDate());
					templateRetailDateMapper.insertSelective(machineTemplateRetailDate);
					// 添加数据到日志表
					TblMachineTemplateRetailDateLog record = new TblMachineTemplateRetailDateLog();
					record.setOperateTime(new Date());
					record.setMachineTemplateRetailDateId(machineTemplateRetailDate.getId());
					record.setOperateType("0");
					record.setOperateUserId(Long.parseLong(Constants.getCurrendUser().getUserId().toString()));
					machineTemplateRetailDateLogMapper.insertSelective(record);
				//}
			}
		}
	}

	public int getCount(Long templateId) {
		return templateRetailDateMapper.getCount(templateId);
	}
}
