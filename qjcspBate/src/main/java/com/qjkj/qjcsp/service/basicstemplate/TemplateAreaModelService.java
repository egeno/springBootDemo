package com.qjkj.qjcsp.service.basicstemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.mapper.TblTemplateAreaModelMapper;

@Service
@Transactional
public class TemplateAreaModelService {
	@Autowired
	private TblTemplateAreaModelMapper templateAreaModelMapper;

	/**
	 * 根据模板Id获取绑定的模型IdList
	 */
	public List<Long> getAreaModelIdByTemplateId(Long templateId) {
		return templateAreaModelMapper.getAreaModelIdByTemplateId(templateId);
	}
	
	public int delByTemplateId(Long templateId){
		return templateAreaModelMapper.delByTemplateId(templateId);
	}
}
