package com.qjkj.qjcsp.web.merchant;



import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qjkj.qjcsp.entity.GProvince;
import com.qjkj.qjcsp.entity.viewmodel.CategoryTree;
import com.qjkj.qjcsp.entity.viewmodel.CompanyTree;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.service.merchant.MerchantInfoService;
import com.qjkj.qjcsp.util.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 商户管理列表
 * @author sx
 *
 */

@Controller
@RequestMapping(value="/merchant")
public class MerchantInfoController {
	private static Logger logger = LoggerFactory.getLogger(MerchantInfoController.class);
	private static final String PAGE_SIZE = "10";
	public static final String  succFlag = "1"; 
	
	@Autowired
	private MerchantInfoService merchantInfoService;
	
	@RequestMapping(value="/mainList")
	public String mainList() {
		return "/merchant/mainList";
	}
	
	@RequestMapping(value="/editMainListDlg")
	public String editMainListDlg() {
		return "/merchant/editMainListDlg";
	}
	
	/**
	 * 弹出框新增、编辑
	 */
	@RequestMapping(value = "/persistenceMerchantDlg")
	@ResponseBody
	public ReturnJson persistenceMerchantDlg(CompanyTree companyTree) {
		/*ReturnJson json = new ReturnJson();
		if (merchantInfoService.persistenceMerchantDlg(companyTree) == true) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		}  else{
			json.setMessage("请先禁用子分类！");
		}
		return json;*/
		
		return merchantInfoService.persistenceMerchantDlg(companyTree);
	}
	
	/**
	 * 按照节点查询商户信息
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/findAllMainList")
	@ResponseBody
	public List<CompanyTree> findAllMainList(@RequestParam(value = "id", required = false) Long pid) {
		return merchantInfoService.findAllMainList(pid);
	}
	
	/**
	 * 父公司名称下拉框查询所有公司信息
	 * @return
	 */
	@RequestMapping(value = "/findMainList")
	@ResponseBody
	public List<CompanyTree> findMainList() {
		return merchantInfoService.findMainList();
	}
	
	/**
	 * 逻辑删除商户
	 */
	@RequestMapping(value = "/delMerchant")
	@ResponseBody
	public ReturnJson deleteCategory(@RequestParam(value = "id", required = false) String pid) {
		ReturnJson json = new ReturnJson();
		if (merchantInfoService.delMerchant(pid) == true) {
			json.setStatus(true);
			json.setMessage(Constants.POST_DATA_SUCCESS);
		} else {
			json.setMessage("请先删除子分类！");
		}
		return json;
	}
	
	/**
	 * 查询公司列表
	 * @return
	 */
	@RequestMapping(value = "/findUniversityList")
	@ResponseBody
	public List<GProvince> findUniversityList(ServletRequest request,
			@RequestParam(value = "cityId", defaultValue ="") String cityId) {
		return merchantInfoService.findUniversityList(cityId);
	}
	/**
	 * 查询省份列表
	 */
	/*@RequestMapping(value="/findProvinceList")
	@ResponseBody
	public JSONArray findProvinceList(){
		return merchantInfoService.findProvinceList(); 
	}*/
	
	
	/**
	 * 公司模糊查询
	 */
	@RequestMapping(value="/fuzzyQuery")
	@ResponseBody
	public String getJSONCostList(ServletRequest request,@RequestParam(value = "searchName", defaultValue ="") String searchName) throws UnsupportedEncodingException, JSONException {
		
		List<HashMap<String, Object>> jsonArr = new ArrayList<HashMap<String, Object>>();
		List<GProvince> list = merchantInfoService.searchProvince(0, 14, searchName);
		for (GProvince c : list) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("id", c.getProvinceId());
			hm.put("text", c.getProvinceName());
			jsonArr.add(hm);
		}
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("rows", jsonArr);
		JSONObject jsonArray = JSONObject.fromObject(hm);
		return "ajax";
	}
	
}
