package com.qjkj.qjcsp.service.stacistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.mapper.TblCustomerDiscountEveryMapper;
import com.qjkj.qjcsp.util.Servlets;
/***
 * 
 * @author rsq
 * 每单优惠活动统计services
 */
@Component
@Transactional
public class EveryOrderDiscountActivityService {
	
	@Autowired
	private TblCustomerDiscountEveryMapper tblCustomerDiscountEveryMapper;
    
	/**
	 * 查询所有信息
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> findFirstOrderPage(ServletRequest request, int pageNumber, int pageSize){
		//得到页面传过来的属性
		Map<String, Object> param =  Servlets.getParametersStartingWith(request, "search_");
		
		param.put("state", DataStatusEnum.NORMAL.getValue());
		Long total = tblCustomerDiscountEveryMapper.findByCount(param) ;

		param.put("offset",(pageNumber-1) * pageSize); //从第几条开始查询
		param.put("limit", pageSize); //每页条数
		param.put("order", "used_time");
		param.put("sort", Sort.Direction.DESC);//降序
		
				
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	    list=tblCustomerDiscountEveryMapper.findByList(param);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(list != null){
			map.put("total", total);
		}else{
			map.put("total", 0);
		}
	    
	    map.put("rows", list);
		return map;
	}
}
