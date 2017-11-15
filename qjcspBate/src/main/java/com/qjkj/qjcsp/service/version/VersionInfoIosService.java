package com.qjkj.qjcsp.service.version;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblIosVersion;
import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.mapper.TblIosVersionMapper;

/**
 * @author rsq
 * 版本管理service
 */
@Service
public class VersionInfoIosService {
	private static final Logger logger = LoggerFactory.getLogger(VersionInfoAndroidService.class);
	@Autowired
	private TblIosVersionMapper tblIosVersionMapper;
	
	SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
	/**
	 * 查询所有ios版本信息
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<TblIosVersion> findAllIosVersionPage(Map<String,Object> param, int pageNumber, int pageSize){
		 param.put("state", DataStatusEnum.NORMAL.getValue()) ;		 
		 PageRequest pageRequest = new PageRequest(pageNumber, pageSize);
		 //UserUtils userUtils = new UserUtils(); 
		 //param.put("companyId", userUtils.getCompanyId());
		 param.put("offset", (pageNumber - 1) * pageSize);
		 param.put("limit", pageSize);
		 Long total = tblIosVersionMapper.findByCount(param) ;
		 List<TblIosVersion> list = new ArrayList<TblIosVersion>() ;
			
		 list = tblIosVersionMapper.findAllTblIosVersion(param) ;
			
		 Page<TblIosVersion> page = new PageImpl<TblIosVersion>(list,pageRequest,total);
		return page;
	}
	
	/**
	 * 添加版本信息
	 * @param request
	 * @param tblAndroidVersion
	 * @return
	 */
	public  Map<String, Object> addVersion(ServletRequest request, TblIosVersion tblIosVersion) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		try {
			//UserUtils userUtils = new UserUtils(); 
			//tblAndroidVersion.setIsValid("1");
			/*TblIosVersion.setModifyTime(dateFormater.format(new Date()));
			TblIosVersion.setCreateTime(dateFormater.format(new Date()));*/
			tblIosVersionMapper.insertSelective(tblIosVersion);
			map.put("message","添加版本成功!") ;
			map.put("status",Boolean.TRUE) ;
		} catch (Exception e) {
			logger.error("添加版本发生异常!",e);
			map.put("message","添加版本失败,请联系管理人员!") ;
			map.put("status",Boolean.FALSE) ;
		}
		return map;
	}
	
	
	/**
	 * 根据版本id查找版本信息
	 * @param request
	 * @param id
	 * @return
	 */
	public TblIosVersion findIosVersion(ServletRequest request, Integer id) {
		TblIosVersion bg = tblIosVersionMapper.selectByPrimaryKey(id);		
		return bg;
	}
	
	/**
	 * 编辑版本信息
	 * @param request
	 * @param tblAndroidVersion
	 * @return
	 */
	public  Map<String, Object> editIosVersion(ServletRequest request, TblIosVersion tblIosVersion) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		try {					 
			tblIosVersion.setModifyTime(dateFormater.format(new Date()));
			tblIosVersionMapper.updateByPrimaryKeySelective(tblIosVersion);
			map.put("message","更新版本成功!") ;
			map.put("status",Boolean.TRUE) ;
		} catch (Exception e) {
			logger.error("更新版本发生异常!",e);
			map.put("message","更新版本失败,请联系管理人员!") ;
			map.put("status",Boolean.FALSE) ;
		}
		
		return map;
	}
	
	
	
	/**
	 * 逻辑删除版本信息
	 * @param request
	 * @param id
	 * @return
	 */
	public  Map<String, Object> delVersions(ServletRequest request, Integer id) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		try {
			/*
			 * 根据ID删除版本信息
			 */
			tblIosVersionMapper.deleteByPrimaryKey(id);
			map.put("message","删除版本成功!") ;
			map.put("status",Boolean.TRUE) ;

		} catch (Exception e) {
			logger.error("删除版本发生异常!",e);
			map.put("message","删除版本失败,请联系管理人员!") ;
			map.put("status",Boolean.FALSE) ;
		}
		
		return map;
	}
	
	
}
