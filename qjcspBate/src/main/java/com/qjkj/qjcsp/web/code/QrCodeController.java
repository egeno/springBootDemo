package com.qjkj.qjcsp.web.code;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qjkj.qjcsp.entity.TblAdPictures;
import com.qjkj.qjcsp.entity.TblAppQrcodePictures;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.mapper.TblAppQrcodePicturesMapper;
import com.qjkj.qjcsp.service.code.QrCodeService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UploadImage;
import com.qjkj.qjcsp.web.BaseController;

/**
 * 二维码上传和删除
 * 
 * @author carpeYe
 *
 */
@Controller
@RequestMapping("code")
public class QrCodeController extends BaseController {

	@Autowired
	private QrCodeService qrCodeService;

	/**
	 * 二维码首页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/Qrcode")
	public String getCode(HttpServletRequest request) {
		request.setAttribute("code", qrCodeService.findQrcode());
		return "code/qrcode";
	}

	/*
	 * 删除IOS二维码
	 */
	@RequestMapping("/iosdelete")
	@ResponseBody
	public ReturnJson deleteIos() {
		ReturnJson json = new ReturnJson();
		if (qrCodeService.deleteIos() == true) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setMessage("数据删除异常！");
		}
		return json;
	}

	/*
	 * 删除安卓二维码
	 */
	@RequestMapping("/androiddelete")
	@ResponseBody
	public ReturnJson deleteAndroid() {
		ReturnJson json = new ReturnJson();
		if (qrCodeService.deleteAndroid() == true) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setMessage("数据删除异常！");
		}
		return json;
	}

	/*
	 * 上传图片
	 */
	@RequestMapping(value = "/uploadImage")
	@ResponseBody
	public void uploadImage(ServletRequest request, ServletResponse response,
			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

		UploadImage.upload(request, response, imageFile, null, null, "codeImage");
	}

	/*
	 * iosadd 页面表单提交保存到数据库
	 */
	@RequestMapping("/addIos")
	@ResponseBody
	public Map<String, Object> addIos(TblAppQrcodePictures tblAdPictures) {
		return qrCodeService.update(tblAdPictures);
	}

	/*
	 * androidadd 页面表单提交保存到数据库
	 */
	@RequestMapping("/addAndroid")
	@ResponseBody
	public Map<String, Object> addAndroid(TblAppQrcodePictures tblAdPictures) {
		return qrCodeService.update(tblAdPictures);
	}

	/*
	 * 点击ios上传按钮跳转到ios上传页面
	 * 
	 * @return
	 */
	@RequestMapping("/addAndroidCode")
	public String addAndroidCode() {
		return "code/androidadd";
	}

	/*
	 * 点击安卓上传按钮条船到安卓上传页面
	 */
	@RequestMapping("/addIosCode")
	public String addIosCode() {
		return "code/iosadd";
	}
}
