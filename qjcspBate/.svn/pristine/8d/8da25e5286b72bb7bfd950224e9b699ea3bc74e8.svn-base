package com.qjkj.qjcsp.service.company;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsCompany;
import com.qjkj.qjcsp.entity.CompanyPo;
import com.qjkj.qjcsp.mapper.BasicsCompanyMapper;

@Component
@Transactional
public class CompanyService {
	private static Logger logger = LoggerFactory.getLogger(CompanyService.class);

	@Autowired
	private BasicsCompanyMapper basicsCompanyMapper;

	public BasicsCompany selectByPrimaryKey(Long companyId) {
		return basicsCompanyMapper.selectByPrimaryKey(companyId);
	}

	/**
	 * 得到公司id和姓名
	 * 
	 * @author yehx
	 * @date 2016年1月22日
	 * @return
	 *
	 */
	public List<CompanyPo> getCompanyIdAndName() {

		return basicsCompanyMapper.getCompanyIdAndName();

	};

	public List<Map<String, Object>> getAllCompany() {
		return basicsCompanyMapper.getAllCompany();
	}
}
