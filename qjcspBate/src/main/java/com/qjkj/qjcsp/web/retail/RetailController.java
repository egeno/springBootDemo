package com.qjkj.qjcsp.web.retail;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.TblRetail;
import com.qjkj.qjcsp.service.retail.RetailService;

/**
 * 新建零售
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/retail")
public class RetailController {
	
	@Autowired
	private RetailService retailService;
	
	/**
	 * 零售新建模版跳转页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/retail")
	public String login() {
		return "/retail/retail";
	}
	
	
	/*String receiveTemplateId;
	String receiveTemplateName;*/
	@RequestMapping("/jump")
	public String receive(HttpServletRequest request,
			@RequestParam(value = "templateId", defaultValue = "") String templateId,
			@RequestParam(value = "templateName",defaultValue = "") String templateName) {
		try {
			templateName=URLDecoder.decode(templateName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("templateId", templateId);
		request.setAttribute("templateName", templateName);
		
		return "/retail/retail";
		/*receiveTemplateId = templateId;
		receiveTemplateName = templateName; */
	}
	
	
	
	/**
	 * 确定按钮
	 * @param request
	 * @param template
	 * @return
	 */
	/*@RequestMapping("/sure")
    @ResponseBody
	public String insertTemplateName(ServletRequest request,
			@RequestParam(value = "template", defaultValue = "") String template) {

		return retailService.insertTemplateName(template);
	}*/
	
	/**
	 * 确定按钮 
	 * @param request
	 * @param changeId 页面上的templateName
	 * @param template 小文本框内的templateName
	 * @param getTemplateId 前面修改页面传过来的模板id
	 * @return
	 */
	@RequestMapping("/change")
    @ResponseBody
	public String changeTemplateName(ServletRequest request,
			@RequestParam(value = "changeId", defaultValue = "") String changeId,
			@RequestParam(value = "template", defaultValue = "") String template,
			@RequestParam(value = "getTemplateId", defaultValue = "") String getTemplateId) {

		return retailService.changeTemplateName(changeId,template,getTemplateId);
	}
	
	
	/**
	 * 根据模版名称获取模型列表
	 */
	@RequestMapping("/modelList")
	@ResponseBody
	public List<Map<String, Object>> modelList(ServletRequest request,
			@RequestParam(value = "getTemplateId", defaultValue = "") String getTemplateId){
		int templateId;
		if("".equals(getTemplateId)){
			templateId = 0;
		}
		else{
			templateId = Integer.valueOf(getTemplateId);
		}
		return retailService.modelList(templateId);
	}
	
	/**
	 * 获取商品类别
	 */
	@RequestMapping("/goodsCategory")
	@ResponseBody
	public List<Map<String, Object>> goodsCategory(ServletRequest request){
		return retailService.goodsCategory();
	}
	
	/**
	 * 获取商品详情
	 */
	@RequestMapping("/goodsDetail")
	@ResponseBody
	public List<Map<String, Object>> goodsDetail(ServletRequest request,
			@RequestParam(value = "goodsCategoryId", defaultValue = "0") int goodsCategoryId,
			@RequestParam(value = "areaModelId", defaultValue = "0") int areaModelId){
		return retailService.goodsDetail(goodsCategoryId,areaModelId);
	}
	
	/**
	 * 确定提交按钮
	 */
	@RequestMapping(value="/saveIssueList")
	@ResponseBody
	public boolean saveIssueList(
			@RequestBody List<TblRetail> issues){//接收前台以json字符串形式传递的对象为Tblissue的List集合
		try {
			//添加数据
			retailService.saveIssuesList(issues);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/**
	 * 修改模板时根据模板id获取零售列表
	 */
	@RequestMapping("/check")
	@ResponseBody
	public List<Map<String, Object>> check(ServletRequest request,
			@RequestParam(value = "templateId", defaultValue = "") String templateId,
			@RequestParam(value = "areaModelId", defaultValue = "") String areaModelId,
			@RequestParam(value = "changeId", defaultValue = "") String changeId){
		return retailService.check(templateId, areaModelId, changeId);
	}

}
