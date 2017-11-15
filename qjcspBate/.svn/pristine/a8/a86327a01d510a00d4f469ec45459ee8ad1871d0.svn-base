package com.qjkj.qjcsp.service.advertisement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblAdPictures;
import com.qjkj.qjcsp.mapper.TblAdPicturesMapper;
import com.qjkj.qjcsp.service.goods.GoodsInfoService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UserUtils;
/**
 * 后台广告图片功能业务层
 * 
 * @author carpeYe
 * @date 2015-01-28
 */
@Service
public class AdvertisementService {

	private static final Logger logger = LoggerFactory.getLogger(AdvertisementService.class);
	@Autowired
	private TblAdPicturesMapper tblAdPicturesMapper;
	/*
	 * 查询
	 */
	public Map<String, Object> dvertisementList(int pageNumber, int pageSize, String picUsed, String picName) {
		Map<String, Object> content = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("picUsed", picUsed);
		params.put("picName", picName);
		Long total = tblAdPicturesMapper.findAdCount(params);
		System.out.println("yejianhui" + total);
		params.put("offset", (pageNumber - 1) * pageSize);
		params.put("limit", pageSize);
		List<TblAdPictures> adPictures = tblAdPicturesMapper.findAdvertisementByParams(params);
		content.put("rows", adPictures);
		content.put("total", total);
		return content;
	}
	/*
	 * 删除
	 */
	public boolean delete(Long adPicId) {
		int i = tblAdPicturesMapper.deleteByPrimaryKey(adPicId);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * 增加
	 */
	public Map<String, Object> addAdvertisement(TblAdPictures tblAdPictures) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			tblAdPictures.setCreateTime(new Date());
			tblAdPictures.setCreateUserId((long) Constants.getCurrendUser().getUserId());
			tblAdPicturesMapper.insertSelective(tblAdPictures);
			map.put("message", "添加广告图片成功!");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			logger.error("添加广告图片发生异常!", e);
			map.put("message", "添加广告图片失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}
	
	public TblAdPictures findTblAdPictures(long adPicId) {
		return tblAdPicturesMapper.selectByPrimaryKey(adPicId);
	}
	/*
	 * 修改
	 */
	public Map<String, Object> editAdvertisement(TblAdPictures tblAdPictures) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			tblAdPictures.setModifyTime(new Date());
			tblAdPictures.setModUserId((long) Constants.getCurrendUser().getUserId());
			tblAdPicturesMapper.updateByPrimaryKeySelective(tblAdPictures);
			map.put("message", "修改广告图片成功!");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			logger.error("修改广告图片发生异常!", e);
			map.put("message", "修改广告图片失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}
}
