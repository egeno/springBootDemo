package com.qjkj.qjcsp.service.stacistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.mapper.TblCustomerDisActLogMapper;

@Component
@Transactional
public class FirstOrderDiscountActivityService {

	@Autowired
	private TblCustomerDisActLogMapper tblCustomerDisActLogMapper;
	
	/**
	 * 查询所有信息
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Map<String, Object>> findFirstOrderPage(Map<String,Object> param, int pageNumber, int pageSize){
		param.put("state", DataStatusEnum.NORMAL.getValue()) ;

		Long total = tblCustomerDisActLogMapper.findByCount(param) ;

		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		
		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order","used_time") ;
		param.put("sort",Sort.Direction.DESC) ;
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>() ;
		if (total != 0) {
			list =  tblCustomerDisActLogMapper.findByList(param);
		}
		Page<Map<String, Object>> page = new PageImpl<Map<String, Object>>(list,pageRequest, total);
		
		return page;
	}
}
