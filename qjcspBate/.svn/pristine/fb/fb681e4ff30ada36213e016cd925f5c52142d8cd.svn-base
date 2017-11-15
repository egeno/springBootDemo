package com.qjkj.qjcsp.service.basicsareamodel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.util.Constants;

@Service
@Transactional
@Component
public class AreaModelService {
	@Autowired
	private BasicsAreaModelMapper areaModelMapper;
	/**
	 * 按公司查询该公司所提供的餐类型
	 * ***/
	public List<Map<String, Object>> getAreaModelList(){
		Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		return areaModelMapper.selectAreaModelByCompanyId(companyId);
	}
}
