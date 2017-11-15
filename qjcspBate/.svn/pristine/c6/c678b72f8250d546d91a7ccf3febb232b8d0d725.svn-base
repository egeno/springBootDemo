package com.qjkj.qjcsp.service.crowdsourced;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.GProvince;
import com.qjkj.qjcsp.entity.viewmodel.CrowdsourcedTree;
import com.qjkj.qjcsp.entity.viewmodel.ReturnJson;
import com.qjkj.qjcsp.mapper.BasicsCrowdsourcedMapper;
import com.qjkj.qjcsp.mapper.GProvinceMapper;
import com.qjkj.qjcsp.util.Constants;



@Component
@Transactional
public class CrowdsourcedService {

	public static final String  delFlag = "0"; //删除标识
	public static final String  succFlag = "1"; 
	@Autowired
	private BasicsCrowdsourcedMapper basicsCrowdsourcedMapper;
	
	@Autowired
	private GProvinceMapper gProvinceMapper;
	
	
	/**
	 * 查询所有分类信息
	 * 
	 */
	public List<CrowdsourcedTree> findMainList() {
		/*List<CrowdsourcedTree> basicsCompany = basicsCrowdsourcedMapper.findcrowdsourcedList();
		List<CrowdsourcedTree> treeList = new ArrayList<CrowdsourcedTree>();

		for (CrowdsourcedTree bc : basicsCompany) {
			CrowdsourcedTree CrowdsourcedTree = new CrowdsourcedTree();
			CrowdsourcedTree.setId(bc.getCompanyId());
			CrowdsourcedTree.setCompanyName(bc.getCompanyName());
			CrowdsourcedTree.setPid(bc.getParentCompanyId());
			CrowdsourcedTree.setState(Constants.TREE_STATUS_OPEN);

			treeList.add(CrowdsourcedTree);
		}*/

		return basicsCrowdsourcedMapper.findcrowdsourcedList();
	}
	
	/**
	 * 查询所有商户
	 */
	public List<CrowdsourcedTree> findAllMainList(Long pid) {
		/*List<CrowdsourcedTree> list = basicsCrowdsourcedMapper.findAllcrowdsourcedList(pid);
		List<CrowdsourcedTree> treeList = new ArrayList<CrowdsourcedTree>();

		for (BasicsCompany bc : list) {
			CrowdsourcedTree CrowdsourcedTree = new CrowdsourcedTree();
			CrowdsourcedTree.setId(bc.getCompanyId());
			CrowdsourcedTree.setCompanyName(bc.getCompanyName());
			判断是否有pid
			if (pid == null || "".equals(pid)) {
				CrowdsourcedTree.setPid(null);
			}else {
				CrowdsourcedTree.setPid(bc.getParentCompanyId());
			}
			 判断是否有父分类名称 
			if (bc.getParentCompanyName() != null) {
				CrowdsourcedTree.setpCompanyName(bc.getParentCompanyName() + "");
			} else {
				CrowdsourcedTree.setpCompanyName("");
			}
			CrowdsourcedTree.setCompanyCode(bc.getCompanyCode());
			CrowdsourcedTree.setCompanyAlias(bc.getCompanyAlias());
			CrowdsourcedTree.setCompanyPrefix(bc.getCompanyPrefix());
			CrowdsourcedTree.setProvCode(bc.getProvCode());
			CrowdsourcedTree.setProvince(bc.getProvince());
			CrowdsourcedTree.setCityCode(bc.getCityCode());
			CrowdsourcedTree.setCityName(bc.getCityName());
			CrowdsourcedTree.setAreaCode(bc.getAreaCode());
			CrowdsourcedTree.setAreaName(bc.getAreaName());
			CrowdsourcedTree.setAddress(bc.getAddress());
			CrowdsourcedTree.setCorporation(bc.getCorporation());
			CrowdsourcedTree.setCardNo(bc.getCardNo());
			CrowdsourcedTree.setBackNo(bc.getBackNo());
			CrowdsourcedTree.setBackName(bc.getBackName());
			CrowdsourcedTree.setCardName(bc.getCardName());
			CrowdsourcedTree.setTelautogram(bc.getTelautogram());
			CrowdsourcedTree.setTel(bc.getTel());
			CrowdsourcedTree.setEmail(bc.getEmail());
			CrowdsourcedTree.setLatitude(bc.getLatitude());
			CrowdsourcedTree.setLongitude(bc.getLongitude());
			CrowdsourcedTree.setBusinessHours(bc.getBusinessHours());
			CrowdsourcedTree.setCompanyStatus(bc.getCompanyStatus());
			CrowdsourcedTree.setCreatetime(bc.getcreateTime());
			CrowdsourcedTree.setLastModTime(bc.getLastModTime());
			CrowdsourcedTree.setCreateUserId(bc.getcreateUserId());
			CrowdsourcedTree.setModUserId(bc.getModUserId());
			CrowdsourcedTree.setState(Constants.TREE_STATUS_CLOSED);
			CrowdsourcedTree.setCreateUser(basicsUsersMapper.findUserAccount(bc.getcreateUserId()));
			CrowdsourcedTree.setModUser(basicsUsersMapper.findModUserAccount(bc.getModUserId()));

			treeList.add(CrowdsourcedTree);
		}*/
		List<CrowdsourcedTree> list = basicsCrowdsourcedMapper.findAllcrowdsourcedList(pid);
		List<CrowdsourcedTree> list1=new ArrayList<CrowdsourcedTree>();
		for(CrowdsourcedTree c:list){
			CrowdsourcedTree CrowdsourcedTree=basicsCrowdsourcedMapper.selectCitynamebycityid(c.getCityCode());
			c.setCityname(CrowdsourcedTree.getCityname());
			CrowdsourcedTree province=basicsCrowdsourcedMapper.selectProvincebycityid(c.getCityCode());
			c.setProvCode(province.getProvCode());
			c.setProvince(province.getProvince());
			list1.add(c);
		}
		return list1;
	}

	/**
	 * 商户管理添加或编辑
	 * 
	 * @param CrowdsourcedTree
	 * @return
	 */
	public ReturnJson persistenceMerchantDlg(CrowdsourcedTree CrowdsourcedTree) {
		ReturnJson json = new ReturnJson();
		//判断companyName在数据库表内是否存在
//		int judge = gProvinceMapper.judgeCompanyNameExist(CrowdsourcedTree.getUniversityname());
//		if(judge <= 0){
//			json.setMessage("该大学不存在");
//			return json;
//		}
//		
//		//判断该大学是否已添加
//		int count = basicsCrowdsourcedMapper.judgeCompanyExist(CrowdsourcedTree.getUniversityid());
//		if(count > 0){
//			json.setMessage("该大学已添加");
//			return json;
//		}
		
		/*新增或更新成功*/
//		int userId = Constants.getCurrendUser().getUserId();
//		boolean result = Boolean.TRUE;
//		/*Long userId = 1L;*/
//		BasicsCompany bc = new BasicsCompany();
		/* id为空，则为新增 */
//		if (null == CrowdsourcedTree.getId() || "".equals(CrowdsourcedTree.getId())) {
//			bc.setCompanyCode(CrowdsourcedTree.getCompanyCode());
//			bc.setCompanyName(CrowdsourcedTree.getCompanyName());
//			/* 判断是否有pId */
//			if (null != CrowdsourcedTree.getPid()) {
//				bc.setParentCompanyId(CrowdsourcedTree.getPid());
//				bc.setParentCompanyName(CrowdsourcedTree.getpCompanyName());
//			}
//			bc.setCompanyId(CrowdsourcedTree.getCompanyId());
//			bc.setCompanyAlias(CrowdsourcedTree.getCompanyAlias());
//			bc.setCompanyPrefix(CrowdsourcedTree.getCompanyPrefix());
//			bc.setParentCompanyName(CrowdsourcedTree.getpCompanyName());
//			bc.setProvCode(CrowdsourcedTree.getProvCode());
//			bc.setProvince(CrowdsourcedTree.getProvince());
//			bc.setCityCode(CrowdsourcedTree.getCityCode());
//			bc.setCityName(CrowdsourcedTree.getCityName());
//			bc.setAreaCode(CrowdsourcedTree.getAreaCode());
//			bc.setAreaName(CrowdsourcedTree.getAreaName());
//			bc.setAddress(CrowdsourcedTree.getAddress());
//			bc.setCorporation(CrowdsourcedTree.getCorporation());
//			bc.setCardNo(CrowdsourcedTree.getCardNo());
//			bc.setBackNo(CrowdsourcedTree.getBackNo());
//			bc.setBackName(CrowdsourcedTree.getBackName());
//			bc.setCardName(CrowdsourcedTree.getCardName());
//			bc.setTelautogram(CrowdsourcedTree.getTelautogram());
//			bc.setTel(CrowdsourcedTree.getTel());
//			bc.setEmail(CrowdsourcedTree.getEmail());
//			bc.setLongitude(CrowdsourcedTree.getLongitude());
//			bc.setLatitude(CrowdsourcedTree.getLatitude());
//			bc.setBusinessHours(CrowdsourcedTree.getBusinessHours());
//			bc.setCompanyStatus(CrowdsourcedTree.getCompanyStatus());
//			BasicsCompany basicsCompany = companyService.selectByPrimaryKey((long)Constants.getCurrendUser().getCompanyId());
//			bc.setIsdel("N");
//			Date currentTime = new Date();
//			bc.setcreateTime(currentTime);
//			bc.setLastModTime(currentTime);
//			bc.setcreateUserId((long) userId);
//			bc.setModUserId((long)userId);
		int count=basicsCrowdsourcedMapper.judgeCrowdsourcedExist(CrowdsourcedTree.getUniversityid());
		CrowdsourcedTree cityname=basicsCrowdsourcedMapper.selectCityidbycityname(CrowdsourcedTree.getCityname());
		CrowdsourcedTree.setCityCode(cityname.getCityCode());
		if(count>0){
			basicsCrowdsourcedMapper.updateCrowdsourced(CrowdsourcedTree);
		}else{
		
		basicsCrowdsourcedMapper.insertSelective(CrowdsourcedTree);}
//		} else {
//			/*判断分类状态是否由启用改为禁用*/
//			if (canUpdate(CrowdsourcedTree) == false) {
//				json.setMessage("请先禁用子分类！");
//				return json;
//			}
//			//修改父类的名称时，对应修改子类的名称
//			basicsCompanyMapper.updateParentCompanyName(CrowdsourcedTree.getId(),CrowdsourcedTree.getCompanyName());
//			bc.setId(CrowdsourcedTree.getId());
//			bc.setCompanyId(CrowdsourcedTree.getCompanyId());
//			bc.setCompanyCode(CrowdsourcedTree.getCompanyCode());
//			bc.setCompanyName(CrowdsourcedTree.getCompanyName());
//			bc.setParentCompanyId(CrowdsourcedTree.getPid());
//			bc.setParentCompanyName(CrowdsourcedTree.getpCompanyName());
//			bc.setCompanyAlias(CrowdsourcedTree.getCompanyAlias());
//			bc.setCompanyPrefix(CrowdsourcedTree.getCompanyPrefix());
//			bc.setParentCompanyName(CrowdsourcedTree.getpCompanyName());
//			bc.setProvCode(CrowdsourcedTree.getProvCode());
//			bc.setProvince(CrowdsourcedTree.getProvince());
//			bc.setCityCode(CrowdsourcedTree.getCityCode());
//			bc.setCityName(CrowdsourcedTree.getCityName());
//			bc.setAreaCode(CrowdsourcedTree.getAreaCode());
//			bc.setAreaName(CrowdsourcedTree.getAreaName());
//			bc.setAddress(CrowdsourcedTree.getAddress());
//			bc.setCorporation(CrowdsourcedTree.getCorporation());
//			bc.setCardNo(CrowdsourcedTree.getCardNo());
//			bc.setBackNo(CrowdsourcedTree.getBackNo());
//			bc.setBackName(CrowdsourcedTree.getBackName());
//			bc.setCardName(CrowdsourcedTree.getCardName());
//			bc.setTelautogram(CrowdsourcedTree.getTelautogram());
//			bc.setTel(CrowdsourcedTree.getTel());
//			bc.setEmail(CrowdsourcedTree.getEmail());
//			bc.setLongitude(CrowdsourcedTree.getLongitude());
//			bc.setLatitude(CrowdsourcedTree.getLatitude());
//			bc.setBusinessHours(CrowdsourcedTree.getBusinessHours());
//			bc.setCompanyStatus(CrowdsourcedTree.getCompanyStatus());
//			Date currentTime = new Date();
//			bc.setLastModTime(currentTime);
//			bc.setModUserId((long)userId);
//
//			basicsCompanyMapper.newUpdateByPrimaryKeySelective(bc);
//		}
		json.setStatus(true);
		json.setMessage(Constants.POST_DATA_SUCCESS);
		return json;
	}
	
	/**
	 * 判断分类状态是否由启用改为禁用
	 * 若存在子分类处于启用状态，则无法禁用父分类
	 * @param CrowdsourcedTree
	 * @return
	 */
//	private boolean canUpdate(CrowdsourcedTree CrowdsourcedTree) {
//		boolean flag = Boolean.TRUE;
//		if ("0".equals(CrowdsourcedTree.getCompanyStatus())) {
//			String status = basicsCompanyMapper.selectByPrimaryKey(CrowdsourcedTree.getId()).getCompanyStatus();
//			if ("1".equals(status)) {
//				List<BasicsCompany> bc = basicsCompanyMapper.queryChildCompanyStatusbyPId(CrowdsourcedTree.getId());
//				/*判断是否存在子分类处于启用状态*/
//				if (null != bc && bc.size() > 0) {
//					/*存在子分类处于启用状态，无法禁用父分类*/
//					flag = Boolean.FALSE;
//				}
//			}
//		}
//		
//		return flag;
//	}
	
	/**
	 * 逻辑删除商户
	 * @param pid
	 * @return
	 */
	public boolean delMerchant(String pid) {
        int updateOperSymbol = 0;
		Long universityid = Long.valueOf(pid);
//		List<CrowdsourcedTree> bcList = basicsCrowdsourcedMapper.queryChildCompanybyPId(companyId);
//		/*判断是否有子分类*/
//		if (null == bcList || bcList.size() < 1) {
			updateOperSymbol = basicsCrowdsourcedMapper.deleteCrowdsourcedbyId(universityid);
//		} 

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
			CrowdsourcedTree CrowdsourcedTree = new CrowdsourcedTree();
			CrowdsourcedTree.setId(bc.getCompanyId());
			CrowdsourcedTree.setCompanyName(bc.getCompanyName());
			CrowdsourcedTree.setPid(bc.getParentCompanyId());
			CrowdsourcedTree.setState(Constants.TREE_STATUS_OPEN);

			treeList.add(CrowdsourcedTree);
		}
		return gProvince;
	}*/
//	/**
//	 * 查询公司列表
//	 * @return
//	 */
//	public List<GProvince> findUniversityList(String cityName){
//		List<GProvince> list = gProvinceMapper.findUniversityList(cityName);
//		return list;
//	}
	/*
	//
	private JSONArray fromObject(){
		JSONArray jsonArray = JSONArray.fromObject(list);//转换成JSON格式
		return jsonArray;
	}*/
}
