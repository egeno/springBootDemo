package com.qjkj.qjcsp.service.weixin;

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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.TblWeixinAssist;
import com.qjkj.qjcsp.entity.enums.DataStatusEnum;
import com.qjkj.qjcsp.mapper.TblWeixinAssistMapper;
import com.qjkj.qjcsp.util.UserUtils;

@Service
public class WeiXinAssistService {
private static Logger logger = LoggerFactory.getLogger(WeiXinWapService.class);
	
	@Autowired
	private TblWeixinAssistMapper tblWeixinAssistMapper;
	
	
	/**
	 * 查询所有互动信息
	 * 
	 * @param param
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<TblWeixinAssist> findTblWeixinAssist(Map<String, Object> param, int pageNumber, int pageSize) {
		param.put("state", DataStatusEnum.NORMAL.getValue());

		UserUtils userUtils = new UserUtils();
		param.put("companyId", userUtils.getCompanyId());
		Long total = tblWeixinAssistMapper.findByCount(param);

		PageRequest pageRequest = new PageRequest(pageNumber, pageSize);

		param.put("offset", (pageNumber - 1) * pageSize);
		param.put("limit", pageSize);
		param.put("order", "goods_id");
		param.put("sort", Sort.Direction.DESC);

		List<TblWeixinAssist> list = new ArrayList<TblWeixinAssist>();
		if (total != 0) {
			list = tblWeixinAssistMapper.findByList(param);
		}
		Page<TblWeixinAssist> page = new PageImpl<TblWeixinAssist>(list, pageRequest, total);

		return page;
	}
	/**
	 * 添加互动信息
	 * 
	 * @param request
	 * @param weixinAssist
	 * @return
	 */
	public Map<String, Object> addAssist(ServletRequest request, TblWeixinAssist weixinAssist) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer count = tblWeixinAssistMapper.checkAssistIsExisted(weixinAssist.getCode(),null);
			
			if (count > 0) {
				map.put("message", "互动信息code重复，请换个互动信息code吧~");
				map.put("status", Boolean.FALSE);			
			}else{
				tblWeixinAssistMapper.insertSelective(weixinAssist);
				map.put("message", "添加互动信息成功!");
				map.put("status", Boolean.TRUE);
			}
		} catch (Exception e) {
			logger.error("添加互动信息发生异常!", e);
			map.put("message", "添加互动信息失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}

		return map;
	}

	/**
	 * 编辑互动信息
	 * 
	 * @param request
	 * @param weixinAssist
	 * @return
	 */
	public Map<String, Object> editAssist(ServletRequest request, TblWeixinAssist weixinAssist) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer count = tblWeixinAssistMapper.checkAssistIsExisted(weixinAssist.getCode(),weixinAssist.getId());
			if (count > 0) {
				map.put("message", "互动信息名称重复，请换个互动信息名称吧~");
				map.put("status", Boolean.FALSE);
			} else {
				tblWeixinAssistMapper.updateByPrimaryKeySelective(weixinAssist);
				map.put("message", "更新互动信息成功!");
				map.put("status", Boolean.TRUE);
			}
		} catch (Exception e) {
			logger.error("更新互动信息发生异常!", e);
			map.put("message", "更新互动信息失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}

		return map;
	}

	/**
	 * 删除互动信息
	 * 
	 * @param request
	 * @param goodsId
	 * @return
	 */
	public Map<String, Object> delAssist(ServletRequest request, Long assistId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			/* 判断互动信息是否有分区选中 */
			tblWeixinAssistMapper.deleteByPrimaryKey(assistId);
			map.put("message", "删除互动信息成功!");
			map.put("status", Boolean.TRUE);

		} catch (Exception e) {
			logger.error("删除互动信息发生异常!", e);
			map.put("message", "删除互动信息失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}

		return map;
	}
	
	/**
	 * 根据互动id查找商品信息
	 * 
	 * @param request
	 * @param goodsId
	 * @return
	 */
	public TblWeixinAssist findAssist(ServletRequest request, Long id) {
		TblWeixinAssist weixinAssist = tblWeixinAssistMapper.selectByPrimaryKey(id);
		return weixinAssist;
	}
}
