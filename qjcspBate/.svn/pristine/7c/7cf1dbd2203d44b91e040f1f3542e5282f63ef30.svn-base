package com.qjkj.qjcsp.service.code;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.mapper.TblAppQrcodePicturesMapper;
/**
 * 二维码接口
 * @author Administrator
 *
 */
@Service
public class APPQrcodePicturesService {

	@Autowired
	private TblAppQrcodePicturesMapper tblAppQrcodePicturesMapper;
	
	public Map<String,Object> findQrcodePictures(){
		return tblAppQrcodePicturesMapper.findQrCode();
	}
}
