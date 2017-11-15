package com.qjkj.qjcsp.web.template;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
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

import com.qjkj.qjcsp.entity.TblGoodsRetailNum;
import com.qjkj.qjcsp.entity.TblRetail;
import com.qjkj.qjcsp.service.basicstemplate.TemplateService;

@Controller
@RequestMapping(value="/newTemplate")
public class NewTemplateController {
	
	@Autowired
	private TemplateService templateService;
	
	@RequestMapping("/template")
	public String template() {
		return "template/cupboard";
	}
	@RequestMapping("/goodsRetail")
	public String goodsRetail(){
		return "goodsRetail/goodsRetailMain";
	}
	
	StringBuffer sb = new StringBuffer();
	
	String tempTime = "";
	
	/**
	 * 获取左边模型(早中晚、点心、夜宵)
	 */
	@RequestMapping("/getAreaModelList")
	@ResponseBody
	public List<Map<String, Object>> getAreaModelList(ServletRequest request,
			@RequestParam(value = "changeId", defaultValue = "0") int changeId) {
		return templateService.getAreaModelList(changeId);
	}
	
	/**
	 * 根据柜子Id、Name获取左边模型(早中晚、点心、夜宵)
	 */
	@RequestMapping("/getAreaModelListByMahineIdName")
	@ResponseBody
	public List<Map<String, Object>> getAreaModelListByMahineIdName(ServletRequest request,
			@RequestParam(value = "mId", defaultValue = "") String mId) {
		return templateService.getAreaModelListByMahineIdName(mId);
	}
	
	/**
	 * 获取商品类别
	 */
	@RequestMapping("/goodsCategory")
	@ResponseBody
	public List<Map<String, Object>> goodsCategory(ServletRequest request){
		return templateService.goodsCategory();
	}
	
	/**
	 * 获取商品详情
	 */
	@RequestMapping("/goodsDetail")
	@ResponseBody
	public Map<String, Object> goodsDetail(ServletRequest request,
			@RequestParam(value = "goodsCategoryId", defaultValue = "0") int goodsCategoryId,
			@RequestParam(value = "mId", defaultValue = "0") int mId,
			@RequestParam(value = "areaModelId", defaultValue = "0") int areaModelId){
		return templateService.goodsDetail(goodsCategoryId,mId,areaModelId,tempTime);
	}
	
	/**
	 * 获取机柜
	 */
	@RequestMapping("/getMachine")
	@ResponseBody
	public List<Map<String, Object>> getMachine(ServletRequest request){
		return templateService.getMachine();
	}
	
	/**
	 * 确定保存数据
	 */
	@RequestMapping(value="/saveIssueList")
	@ResponseBody
	public Map<String, Object> saveIssueList(
			@RequestBody List<TblGoodsRetailNum> issues){//接收前台以json字符串形式传递的对象为Tblissue的List集合
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//添加数据
			if(templateService.saveIssuesList(issues,tempTime) == true){
				map.put("message", "添加成功");
				map.put("judge", true);
			}
			else{
				map.put("message", "添加失败");
				map.put("judge", false);
			}
			return map;
		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("judge", false);
			return map;
		}
	}
	
	/**
	 * 获取柜子可存放商品数量
	 */
	@RequestMapping("/machineNum")
	@ResponseBody
	public int machineNum(ServletRequest request,
			@RequestParam(value = "machineId", defaultValue = "")String machineId,
			@RequestParam(value = "areaModelId", defaultValue = "")String areaModelId){
		
		return templateService.machineNum(machineId,areaModelId,tempTime);
	}
	
	/**
	 * 页面跳转
	 */
	@RequestMapping("/jump")
	public String receive(HttpServletRequest request,
			@RequestParam(value = "machineId", defaultValue = "") String machineId,
			@RequestParam(value = "machineName",defaultValue = "") String machineName,
			@RequestParam(value = "time",defaultValue = "") String time) {
		try {
			tempTime = time;
			/*sb.append(time);*/
			machineName=URLDecoder.decode(machineName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("machineId", machineId);
		request.setAttribute("machineName", machineName);
		
		return "/template/cupboard";
		/*receiveTemplateId = templateId;
		receiveTemplateName = templateName; */
	}
	
	/**
	 * 判断选中的菜品是否能添加到柜子中
	 */
	@RequestMapping("/checkGoodsToMachine")
	@ResponseBody
	public Map<String, Object> checkGoodsToMachine(ServletRequest request,
			@RequestParam(value = "foodName") String foodName,
			@RequestParam(value = "machineId") Long machineId){
		return templateService.checkGoodsToMachine(foodName,machineId);
	}
	
	
}
