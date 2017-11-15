package com.qjkj.qjcsp.service.merchant;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.qjkj.qjcsp.entity.Address;
import com.qjkj.qjcsp.entity.BasicsCompany;
import com.qjkj.qjcsp.entity.BasicsGoodsCategory;
import com.qjkj.qjcsp.entity.BasicsUsers;
import com.qjkj.qjcsp.entity.GProvince;
import com.qjkj.qjcsp.entity.viewmodel.CategoryTree;
import com.qjkj.qjcsp.entity.viewmodel.CompanyTree;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.mapper.BasicsCompanyMapper;
import com.qjkj.qjcsp.mapper.BasicsUsersMapper;
import com.qjkj.qjcsp.mapper.GProvinceMapper;
import com.qjkj.qjcsp.service.company.CompanyService;
import com.qjkj.qjcsp.util.Constants;

import net.sf.json.JSONArray;



@Component
@Transactional
public class MerchantInfoService {

	public static final String  delFlag = "0"; //删除标识
	public static final String  succFlag = "1"; 
	@Autowired
	private BasicsCompanyMapper basicsCompanyMapper;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BasicsUsersMapper basicsUsersMapper;
	
	@Autowired
	private GProvinceMapper gProvinceMapper;
	
	private List<Address> list;
	
	/**
	 * 查询所有分类信息
	 * 
	 */
	public List<CompanyTree> findMainList() {
		List<BasicsCompany> basicsCompany = basicsCompanyMapper.findMainList();
		List<CompanyTree> treeList = new ArrayList<CompanyTree>();

		for (BasicsCompany bc : basicsCompany) {
			CompanyTree companyTree = new CompanyTree();
			companyTree.setId(bc.getCompanyId());
			companyTree.setCompanyName(bc.getCompanyName());
			companyTree.setPid(bc.getParentCompanyId());
			companyTree.setState(Constants.TREE_STATUS_OPEN);

			treeList.add(companyTree);
		}

		return treeList;
	}
	
	/**
	 * 查询所有商户
	 */
	public List<CompanyTree> findAllMainList(Long pid) {
		List<BasicsCompany> list = basicsCompanyMapper.findAllMainList(pid);
		List<CompanyTree> treeList = new ArrayList<CompanyTree>();

		for (BasicsCompany bc : list) {
			CompanyTree companyTree = new CompanyTree();
			companyTree.setId(bc.getCompanyId());
			companyTree.setCompanyName(bc.getCompanyName());
			/*判断是否有pid*/
			if (pid == null || "".equals(pid)) {
				companyTree.setPid(null);
			}else {
				companyTree.setPid(bc.getParentCompanyId());
			}
			/* 判断是否有父分类名称 */
			if (bc.getParentCompanyName() != null) {
				companyTree.setpCompanyName(bc.getParentCompanyName() + "");
			} else {
				companyTree.setpCompanyName("");
			}
			companyTree.setCompanyCode(bc.getCompanyCode());
			companyTree.setCompanyAlias(bc.getCompanyAlias());
			companyTree.setCompanyPrefix(bc.getCompanyPrefix());
			companyTree.setProvCode(bc.getProvCode());
			companyTree.setProvince(bc.getProvince());
			companyTree.setCityCode(bc.getCityCode());
			companyTree.setCityName(bc.getCityName());
			companyTree.setAreaCode(bc.getAreaCode());
			companyTree.setAreaName(bc.getAreaName());
			companyTree.setAddress(bc.getAddress());
			companyTree.setCorporation(bc.getCorporation());
			companyTree.setCardNo(bc.getCardNo());
			companyTree.setBackNo(bc.getBackNo());
			companyTree.setBackName(bc.getBackName());
			companyTree.setCardName(bc.getCardName());
			companyTree.setTelautogram(bc.getTelautogram());
			companyTree.setTel(bc.getTel());
			companyTree.setEmail(bc.getEmail());
			companyTree.setLatitude(bc.getLatitude());
			companyTree.setLongitude(bc.getLongitude());
			companyTree.setBusinessHours(bc.getBusinessHours());
			companyTree.setCompanyStatus(bc.getCompanyStatus());
			companyTree.setCreatetime(bc.getcreateTime());
			companyTree.setLastModTime(bc.getLastModTime());
			/*companyTree.setCreateUserId(bc.getcreateUserId());
			companyTree.setModUserId(bc.getModUserId());*/
			companyTree.setState(Constants.TREE_STATUS_CLOSED);
			companyTree.setCreateUser(basicsUsersMapper.findUserAccount(bc.getcreateUserId()));
			companyTree.setModUser(basicsUsersMapper.findModUserAccount(bc.getModUserId()));

			treeList.add(companyTree);
		}

		return treeList;
	}

	/**
	 * 商户管理添加或编辑
	 * 
	 * @param companyTree
	 * @return
	 */
	public ReturnJson persistenceMerchantDlg(CompanyTree companyTree) {
		ReturnJson json = new ReturnJson();
		//判断companyName在数据库表内是否存在
		int judge = gProvinceMapper.judgeCompanyNameExist(companyTree.getCompanyName());
		if(judge <= 0){
			json.setMessage("该商户不存在");
		}
		/*新增或更新成功*/
		int userId = Constants.getCurrendUser().getUserId();
		boolean result = Boolean.TRUE;
		BasicsCompany bc = new BasicsCompany();
		/* id为空，则为新增 */
		if (null == companyTree.getId() || "".equals(companyTree.getId())) {
			//判断该商户是否已添加
			int count = basicsCompanyMapper.judgeCompanyExist(companyTree.getCompanyName());
			if(count > 0){
				json.setMessage("该商户已添加");
			} else{
				bc.setCompanyCode(companyTree.getCompanyCode());
				bc.setCompanyName(companyTree.getCompanyName());
				/* 判断是否有pId */
				if (null != companyTree.getPid()) {
					bc.setParentCompanyId(companyTree.getPid());
					bc.setParentCompanyName(companyTree.getpCompanyName());
				}
				bc.setCompanyAlias(companyTree.getCompanyAlias());
				bc.setCompanyPrefix(companyTree.getCompanyPrefix());
				bc.setParentCompanyName(companyTree.getpCompanyName());
				bc.setProvCode(companyTree.getProvCode());
				bc.setProvince(companyTree.getProvince());
				bc.setCityCode(companyTree.getCityCode());
				bc.setCityName(companyTree.getCityName());
				bc.setAreaCode(companyTree.getAreaCode());
				bc.setAreaName(companyTree.getAreaName());
				bc.setAddress(companyTree.getAddress());
				bc.setCorporation(companyTree.getCorporation());
				bc.setCardNo(companyTree.getCardNo());
				bc.setBackNo(companyTree.getBackNo());
				bc.setBackName(companyTree.getBackName());
				bc.setCardName(companyTree.getCardName());
				bc.setTelautogram(companyTree.getTelautogram());
				bc.setTel(companyTree.getTel());
				bc.setEmail(companyTree.getEmail());
				bc.setLongitude(companyTree.getLongitude());
				bc.setLatitude(companyTree.getLatitude());
				bc.setBusinessHours(companyTree.getBusinessHours());
				bc.setCompanyStatus(companyTree.getCompanyStatus());
				bc.setIsdel("N");
				Date currentTime = new Date();
				bc.setcreateTime(currentTime);
				bc.setLastModTime(currentTime);
				bc.setcreateUserId((long) userId);
				bc.setModUserId((long)userId);
				bc.setUniversityId(companyTree.getCompanyId());
				basicsCompanyMapper.insertSelective(bc);
				
				json.setMessage(Constants.POST_DATA_SUCCESS);
			}
		} else {
			/*判断分类状态是否由启用改为禁用*/
//			if (canUpdate(companyTree) == false) {
//				json.setMessage("请先禁用子分类！");
//			}
			//编辑判断该商户是否已添加
//			int count = basicsCompanyMapper.judgeCompany(companyTree.getCompanyName(), companyTree.getPid());
//			if(count > 0){
//				json.setMessage("该商户已添加");
//			}
			//修改父类的名称时，对应修改子类的名称
//			basicsCompanyMapper.updateParentCompanyName(companyTree.getId(),companyTree.getCompanyName());
			bc.setId(companyTree.getId());
			bc.setCompanyCode(companyTree.getCompanyCode());
			bc.setCompanyName(companyTree.getCompanyName());
			bc.setCompanyAlias(companyTree.getCompanyAlias());
			bc.setCompanyPrefix(companyTree.getCompanyPrefix());
			bc.setParentCompanyName(companyTree.getpCompanyName());
			bc.setProvCode(companyTree.getProvCode());
			bc.setProvince(companyTree.getProvince());
			bc.setCityCode(companyTree.getCityCode());
			bc.setCityName(companyTree.getCityName());
			bc.setAreaCode(companyTree.getAreaCode());
			bc.setAreaName(companyTree.getAreaName());
			bc.setAddress(companyTree.getAddress());
			bc.setCorporation(companyTree.getCorporation());
			bc.setCardNo(companyTree.getCardNo());
			bc.setBackNo(companyTree.getBackNo());
			bc.setBackName(companyTree.getBackName());
			bc.setCardName(companyTree.getCardName());
			bc.setTelautogram(companyTree.getTelautogram());
			bc.setTel(companyTree.getTel());
			bc.setEmail(companyTree.getEmail());
			bc.setLongitude(companyTree.getLongitude());
			bc.setLatitude(companyTree.getLatitude());
			bc.setBusinessHours(companyTree.getBusinessHours());
			bc.setCompanyStatus(companyTree.getCompanyStatus());
			Date currentTime = new Date();
			bc.setLastModTime(currentTime);
			bc.setModUserId((long)userId);
			bc.setUniversityId(companyTree.getCompanyId());
			basicsCompanyMapper.newUpdateByPrimaryKeySelective(bc);
		}
		json.setStatus(true);
		
		return json;
	}
	
	/**
	 * 判断分类状态是否由启用改为禁用
	 * 若存在子分类处于启用状态，则无法禁用父分类
	 * @param companyTree
	 * @return
	 */
	private boolean canUpdate(CompanyTree companyTree) {
		boolean flag = Boolean.TRUE;
		if ("0".equals(companyTree.getCompanyStatus())) {
			String status = basicsCompanyMapper.selectByPrimaryKey(companyTree.getId()).getCompanyStatus();
			if ("1".equals(status)) {
				List<BasicsCompany> bc = basicsCompanyMapper.queryChildCompanyStatusbyPId(companyTree.getId());
				/*判断是否存在子分类处于启用状态*/
				if (null != bc && bc.size() > 0) {
					/*存在子分类处于启用状态，无法禁用父分类*/
					flag = Boolean.FALSE;
				}
			}
		}
		
		return flag;
	}
	
	/**
	 * 逻辑删除商户
	 * @param pid
	 * @return
	 */
	public boolean delMerchant(String pid) {
		int updateOperSymbol = 0;
		Long companyId = Long.valueOf(pid);
		List<BasicsCompany> bcList = basicsCompanyMapper.queryChildCompanybyPId(companyId);
		/*判断是否有子分类*/
		if (null == bcList || bcList.size() < 1) {
			updateOperSymbol = basicsCompanyMapper.deleteCompanybyId(companyId);
		} 

		return (updateOperSymbol > 0) ? true : false;
	}

	public List<GProvince> searchProvince(int i, int j, String searchName) {
		List<GProvince> gProvince = gProvinceMapper.findProvinceList(i, j, searchName);
		return gProvince;
	}
	
	
	/*public List<GProvince> findProvinceList() {
		List<GProvince> gProvince = gProvinceMapper.findProvinceList();
		List<GProvince> treeList = new ArrayList<GProvince>();
		for (BasicsCompany bc : basicsCompany) {
			CompanyTree companyTree = new CompanyTree();
			companyTree.setId(bc.getCompanyId());
			companyTree.setCompanyName(bc.getCompanyName());
			companyTree.setPid(bc.getParentCompanyId());
			companyTree.setState(Constants.TREE_STATUS_OPEN);

			treeList.add(companyTree);
		}
		return gProvince;
	}*/
	/**
	 * 查询公司列表
	 * @return
	 */
	public List<GProvince> findUniversityList(String cityName){
		List<GProvince> list = gProvinceMapper.findUniversityList(cityName);
		return list;
	}
	/*
	//
	private JSONArray fromObject(){
		JSONArray jsonArray = JSONArray.fromObject(list);//转换成JSON格式
		return jsonArray;
	}*/
}
