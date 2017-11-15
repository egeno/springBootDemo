package com.qjkj.qjcsp.service.replenishmentprint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.mapper.TblReplenishmentPrintMapper;
import com.qjkj.qjcsp.util.UserUtils;

@Component
@Service
@Transactional
public class ReplenishmentPrintService {
	@Autowired
	private TblReplenishmentPrintMapper tblReplenishmentPrintlMapper;


	public Page<Map<String, Object>> selectGoodsSaleCount(Map<String, Object> param, int pageNumber, int pageSize) {
		//param.put("state", DataStatusEnum.NORMAL.getValue());

		 UserUtils userUtils = new UserUtils();
		 param.put("companyId", userUtils.getCompanyId());
		Long total = tblReplenishmentPrintlMapper.findByCount(param);

		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		//param.put("sort", Sort.Direction.DESC);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (total != 0) {
			list = tblReplenishmentPrintlMapper.findByList(param);
		}
		Page<Map<String, Object>> page = new PageImpl<Map<String, Object>>(list, pageRequest, total);

		return page;
	}


	public List<Map<String, Object>> replenishmentPrintListExport(Map<String, Object> searchParams) {
		Long total = tblReplenishmentPrintlMapper.findByCount(searchParams);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (total != 0) {
			list = tblReplenishmentPrintlMapper.findByList(searchParams);
		}
		return list;
	}


}
