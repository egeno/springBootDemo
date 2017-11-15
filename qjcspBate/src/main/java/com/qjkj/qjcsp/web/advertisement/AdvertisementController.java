package com.qjkj.qjcsp.web.advertisement;

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
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.service.advertisement.AdvertisementService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UploadImage;
import com.qjkj.qjcsp.web.BaseController;

/**
 * 后台广告图片功能
 * 
 * @author carpeYe
 * @date 2015-01-28
 */
@Controller
@RequestMapping("advertisement")
public class AdvertisementController extends BaseController {

	@Autowired
	private AdvertisementService advertisementService;

	/*
	 * 跳转到广告图片功能
	 */
	@RequestMapping("/listAdvertisement")
	public String advertisementPage(HttpServletRequest request) {
		return "advertisement/advertisementMain";
	}

	/*
	 * 展示后台查询的广告图片数据
	 * 
	 * @param request
	 * 
	 * @param pageNumber
	 * 
	 * @param pageSize
	 * 
	 * @param picUsed 广告图片用途
	 * 
	 * @param picName 广告图片内容
	 * 
	 * @return
	 */
	@RequestMapping("/advertisement")
	@ResponseBody
	public Map<String, Object> advertisement(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "picUsed", defaultValue = "-1") String picUsed,
			@RequestParam(value = "picName", defaultValue = "") String picName) {
		return advertisementService.dvertisementList(pageNumber, pageSize, picUsed, picName);
	}

	/*
	 * 删除广告图片
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public ReturnJson deleteAreaById(@RequestParam(value = "adPicId", required = false) long adPicId) {
		ReturnJson json = new ReturnJson();
		if (advertisementService.delete(adPicId) == true) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setMessage("数据删除异常！");
		}
		return json;
	}

	/*
	 * 跳转到广告图片的添加页面
	 */
	@RequestMapping("/addPage")
	public String addPage() {
		return "advertisement/advertisementAdd";
	}

	@RequestMapping(value = "/uploadImage")
	@ResponseBody
	public void uploadImage(ServletRequest request, ServletResponse response,
			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

		UploadImage.upload(request, response, imageFile, null, null, "advertisementImage");
	}

	/*
	 * 接收将前台要添加发数据调用业务层
	 */
	@RequestMapping("/addAdvertisement")
	@ResponseBody
	public Map<String, Object> addAdvertisement(TblAdPictures tblAdPictures) {
		return advertisementService.addAdvertisement(tblAdPictures);
	}

	/*
	 * 跳转到广告修改页面
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, long adPicId) {
		TblAdPictures tblAdPictures = advertisementService.findTblAdPictures(adPicId);
		request.setAttribute("tblAdPictures", tblAdPictures);
		return "advertisement/advertisementModify";
	}

	/*
	 * 接收前台要修改的数据并调用业务层
	 */
	@RequestMapping("/editAdvertisement")
	@ResponseBody
	public Map<String, Object> editAdvertisement(TblAdPictures tblAdPicturess) {
		return advertisementService.editAdvertisement(tblAdPicturess);
	}
}
