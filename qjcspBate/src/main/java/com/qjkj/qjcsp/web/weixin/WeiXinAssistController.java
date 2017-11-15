package com.qjkj.qjcsp.web.weixin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qjkj.qjcsp.entity.BasicsGoods;
import com.qjkj.qjcsp.entity.TblWeixinAssist;
import com.qjkj.qjcsp.entity.viewmodel.BasicsGoodsVo;
import com.qjkj.qjcsp.service.weixin.WeiXinAssistService;
import com.qjkj.qjcsp.util.Servlets;
import com.qjkj.qjcsp.util.UploadImage;
import com.qjkj.qjcsp.web.BaseController;

@Controller
@RequestMapping(value = "weixinassist")
public class WeiXinAssistController extends BaseController{
	@Autowired
	private WeiXinAssistService weiXinAssistService;
	/**
	 * 互动信息跳转页
	 */
	@RequestMapping(value="/listAssistMain")
	public String listAssistMain(){
		return "/assist/listAssistMain" ;
	}

	/**
	 *互动添加跳转页 
	 *
	 */
	@RequestMapping(value="/addAssistMain")
	public String addAssistMain(){
		return "/assist/addAssistMain";
	}
	
	/**
	 *互动编辑跳转页 
	 */
	@RequestMapping(value="/editAssistMain")
	public String editAssistMain(ServletRequest request, Long id){
		TblWeixinAssist assist = weiXinAssistService.findAssist(request, id) ;
		request.setAttribute("assist", assist);
		return "/assist/editAssistMain";
	}
	/**
	 * 上传商品图标
	 * @param request
	 * @param response
	 * @param imageFile
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadImage")
	@ResponseBody
	public void uploadImage(ServletRequest request, ServletResponse response,
			@RequestParam(value="imageFile", required=false) MultipartFile imageFile) throws IOException {
		UploadImage.upload(request, response, imageFile, 360, 240, "assistImage");
	}
	/**
	 * 查询所有商品信息
	 * @param request
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findAssistPage")
	@ResponseBody
	public Map<String,Object> findAssistPage(ServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = PAGE_SIZE) int pageSize){
		
		Map<String,Object> map = new HashMap<String,Object>() ;
		
		Map<String, Object> searchParams =  Servlets.getParametersStartingWith(request, "search_");
		
		Page<TblWeixinAssist> lists = weiXinAssistService.findTblWeixinAssist(searchParams, pageNumber, pageSize) ;
		map.put("rows", lists.getContent());
		map.put("total", lists.getTotalElements());
		
		return map ;
	}
	
	/**
	 * 添加商品信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/addAssist")
	@ResponseBody
	public Map<String, Object> addAssist(ServletRequest request, TblWeixinAssist assist){
		return weiXinAssistService.addAssist(request, assist);
	}
	
	/**
	 * 编辑商品信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/editAssist")
	@ResponseBody
	public Map<String, Object> editAssist(ServletRequest request, TblWeixinAssist assist){
		return weiXinAssistService.editAssist(request, assist);
	}
	
	/**
	 * 逻辑删除商品信息
	 * @param request
	 * @param basicsGoods
	 * @return
	 */
	@RequestMapping(value="/delAssist")
	@ResponseBody
	public Map<String, Object> delGoods(ServletRequest request, Long id){
		return weiXinAssistService.delAssist(request, id);
	}
}
