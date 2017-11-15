package com.qjkj.qjcsp.service.code;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.TblAppQrcodePictures;
import com.qjkj.qjcsp.mapper.TblAppQrcodePicturesMapper;
import com.qjkj.qjcsp.service.advertisement.AdvertisementService;
import com.qjkj.qjcsp.util.Constants;
/**
 * 二维码业务层
 * @author Administrator
 *
 */
@Service
public class QrCodeService {
	private static final Logger logger = LoggerFactory.getLogger(QrCodeService.class);
	@Autowired
	private TblAppQrcodePicturesMapper tblAppQrcodePicturesMapper;
	/*
	 * 查询数据
	 */
	public TblAppQrcodePictures findQrcode() {
		return tblAppQrcodePicturesMapper.findTblAppQrcodePictures();
	}
	/*
	 * 删除ios字段的内容
	 */
	public boolean deleteIos() {
		try {
			TblAppQrcodePictures tblAppQrcodePictures = tblAppQrcodePicturesMapper.findTblAppQrcodePictures();
			tblAppQrcodePictures.setIosPic("");
			tblAppQrcodePictures.setModifyTime(new Date());
			tblAppQrcodePictures.setModUserId((long) Constants.getCurrendUser().getUserId());
			tblAppQrcodePicturesMapper.updateTblAppQrcodePictures(tblAppQrcodePictures);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/*
	 * 删除安卓字段的内容
	 */
	public boolean deleteAndroid() {
		try {
			TblAppQrcodePictures tblAppQrcodePictures = tblAppQrcodePicturesMapper.findTblAppQrcodePictures();
			tblAppQrcodePictures.setAndroidPic("");
			tblAppQrcodePictures.setModifyTime(new Date());
			tblAppQrcodePictures.setModUserId((long) Constants.getCurrendUser().getUserId());
			tblAppQrcodePicturesMapper.updateTblAppQrcodePictures(tblAppQrcodePictures);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/*
	 * 添加ios或安卓字段的内容
	 */
	public Map<String, Object> update(TblAppQrcodePictures tblAppQrcodePictures){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			tblAppQrcodePictures.setModifyTime(new Date());
			tblAppQrcodePictures.setModUserId((long)Constants.getCurrendUser().getUserId());
			tblAppQrcodePicturesMapper.updateTblAppQrcodePictures(tblAppQrcodePictures);
			map.put("message", "添加二维码成功!");
			map.put("status", Boolean.TRUE);
		} catch (Exception e) {
			logger.error("添加二维码发生异常!", e);
			map.put("message", "添加二维码失败,请联系管理人员!");
			map.put("status", Boolean.FALSE);
		}
		return map;
	}
}
