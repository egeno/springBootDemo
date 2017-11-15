package com.qjkj.qjcsp.service.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsGoodsCategory;
import com.qjkj.qjcsp.entity.viewmodel.CategoryTree;
import com.qjkj.qjcsp.mapper.BasicsGoodsCategoryMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.UserUtils;

import net.sf.json.JSONObject;

@Component
@Transactional
public class GoodsCategoryService {

	@Autowired
	private BasicsGoodsCategoryMapper basicsGoodsCategoryMapper;
	
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;
	
	/**
	 * 查询所有分类信息
	 * 
	 */
	public List<CategoryTree> findCategoryAllList() {
		UserUtils userUtils = new UserUtils(); 
		Long companyId = (long) userUtils.getCompanyId();
		
		List<BasicsGoodsCategory> goodsCategoryList = basicsGoodsCategoryMapper.findCategoryAllList(companyId);
		List<CategoryTree> treeList = new ArrayList<CategoryTree>();

		for (BasicsGoodsCategory bgc : goodsCategoryList) {
			CategoryTree categoryTree = new CategoryTree();
			categoryTree.setId(bgc.getGoodsCategoryId());
			categoryTree.setName(bgc.getGoodsCategoryName());
			categoryTree.setPid(bgc.getParentGoodsCategoryId());
			categoryTree.setState(Constants.TREE_STATUS_OPEN);

			treeList.add(categoryTree);
		}

		return treeList;
	}
	
	/**
	 * 按照节点查询分类信息
	 * 
	 * @param pid
	 * @return
	 */
	public List<CategoryTree> findCategory(Long pid) {
		UserUtils userUtils = new UserUtils(); 
		Long companyId = (long) userUtils.getCompanyId();
		
		List<Map<String, Object>> goodsCategoryList = basicsGoodsCategoryMapper.findCategory(pid, companyId);
		List<CategoryTree> treeList = new ArrayList<CategoryTree>();

		for (Map<String, Object> bgc : goodsCategoryList) {
			CategoryTree categoryTree = new CategoryTree();
			categoryTree.setId((Long) bgc.get("goodsCategoryId"));
			categoryTree.setName((String) bgc.get("goodsCategoryName"));
			/*判断是否有pid*/
			if (pid == null) {
				categoryTree.setPid(null);
			}else {
				categoryTree.setPid((Long) bgc.get("parentGoodsCategoryId"));
			}
			/* 判断是否有父分类名称 */
			if (StringUtils.isNotBlank((String) bgc.get("parentGoodsCategoryName"))) {
				categoryTree.setpName((String) bgc.get("parentGoodsCategoryName"));
			}else {
				categoryTree.setpName("");
			}
			
			if (bgc.get("level") != null){
				categoryTree.setLevel(Short.valueOf(bgc.get("level")+""));
			}else {
				categoryTree.setLevel(null);
			} 
			
			categoryTree.setCreater((String) bgc.get("creater"));
			categoryTree.setCreatetime((Date) bgc.get("createTime"));
			categoryTree.setModifier((String) bgc.get("modifier"));
			categoryTree.setModifytime((Date) bgc.get("modifyTime"));
			categoryTree.setState(Constants.TREE_STATUS_CLOSED);
			categoryTree.setStatus((String) bgc.get("goodsCategoryStatus"));
			categoryTree.setCode((String) bgc.get("goodsCategoryCode"));

			treeList.add(categoryTree);
		}

		return treeList;
	}

	/**
	 * 商品分类添加或编辑
	 * 
	 * @param categoryTree
	 * @return
	 */
	public boolean persistenceCategoryDlg(CategoryTree categoryTree) {
		/*新增或更新成功*/
		boolean result = Boolean.TRUE;
		UserUtils userUtils = new UserUtils(); 
		Long userId = (long) userUtils.getUserId();
		Long companyId = (long) userUtils.getCompanyId();
		BasicsGoodsCategory bgc = new BasicsGoodsCategory();
		/* id为空，则为新增 */
		if (null == categoryTree.getId()) {
			bgc.setGoodsCategoryCode(categoryTree.getCode());
			bgc.setGoodsCategoryName(categoryTree.getName());
			/* 判断是否有pId */
			if (null != categoryTree.getPid()) {
				bgc.setParentGoodsCategoryId(categoryTree.getPid());
				bgc.setParentGoodsCategoryName(categoryTree.getpName());
			}
			bgc.setCompanyId(companyId);
			bgc.setGoodsCategoryStatus(categoryTree.getStatus());
			bgc.setIsdel("N");
			Date currentTime = new Date();

			bgc.setCreateTime(currentTime);
			bgc.setLastModTime(currentTime);
			bgc.setCreateUserId(userId);
			bgc.setModUserId(userId);

			basicsGoodsCategoryMapper.insertSelective(bgc);
		} else {
			/*判断分类状态是否由启用改为禁用*/
			if (canUpdate(categoryTree) == false) {
				result = Boolean.FALSE;
				return result;
			}
			bgc.setGoodsCategoryId(categoryTree.getId());
			bgc.setGoodsCategoryCode(categoryTree.getCode());
			bgc.setGoodsCategoryName(categoryTree.getName());
			bgc.setParentGoodsCategoryId(categoryTree.getPid());
			bgc.setParentGoodsCategoryName(categoryTree.getpName());
			bgc.setGoodsCategoryStatus(categoryTree.getStatus());
			Date currentTime = new Date();
			bgc.setLastModTime(currentTime);
			bgc.setModUserId(userId);

			basicsGoodsCategoryMapper.updateByPrimaryKeySelective(bgc);
		}
		return result;
	}
	/**
	 * 商品分类添加时类型编号是否重复
	 * 
	 * @param categoryTree
	 * @return boolean 
	 */
	public boolean isRepeatCategoryTreeCode(CategoryTree categoryTree) {
		/*添加时编号不能重复*/
		boolean result = Boolean.TRUE;
		UserUtils userUtils = new UserUtils(); 
		Long companyId = (long) userUtils.getCompanyId();
		List<BasicsGoodsCategory> lists=basicsGoodsCategoryMapper.findCategoryNumByCode(categoryTree.getCode(),companyId);
		if(lists!=null && lists.size()>0){
			result = Boolean.FALSE;
		}
		return result;
	}
	/**
	 * 逻辑删除商品分类
	 * 
	 * @param pid
	 * @return
	 */
	public short deleteCategory(String pid) {
		short flag = 0;
		Long goodsCategoryId = Long.valueOf(pid);
		/*判断是否有子分类*/
		if (basicsGoodsCategoryMapper.getChildCategoryCountbyPId(goodsCategoryId) == 0) {
			/*判断是否有商品在使用*/
			if (basicsGoodsMapper.getGoodsCountbyCategoryId(goodsCategoryId) == 0){
				 basicsGoodsCategoryMapper.deleteCategorybyId(goodsCategoryId);
			}else {
				flag = 1;
			}
		}else {
			flag = 2;
		} 

		return flag;
	}
	
	/**
	 * 判断分类状态是否由启用改为禁用
	 * 若存在子分类处于启用状态，则无法禁用父分类
	 * @param categoryTree
	 * @return
	 */
	private boolean canUpdate(CategoryTree categoryTree) {
		boolean flag = Boolean.TRUE;
		if ("0".equals(categoryTree.getStatus())) {
			String status = basicsGoodsCategoryMapper.selectByPrimaryKey(categoryTree.getId()).getGoodsCategoryStatus();
			if ("1".equals(status)) {
				List<BasicsGoodsCategory> bgcList = basicsGoodsCategoryMapper.queryChildCategoryStatusbyPId(categoryTree.getId());
				/*判断是否存在子分类处于启用状态*/
				if (null != bgcList && bgcList.size() > 0) {
					/*存在子分类处于启用状态，无法禁用父分类*/
					flag = Boolean.FALSE;
				}
			}
		}
		
		return flag;
	}
	
	
	
	/**
	 * 按公司查询该公司所提供的食物类型
	 ***/
	public List<Map<String, Object>> selectGoodsCategoryById() {
		Long companyId = (long) Constants.getCurrendUser().getCompanyId();
		return basicsGoodsCategoryMapper.selectGoodsCategoryById(companyId);
	}
	/*
	 * 2.2.8.	获取商品分类列表
	 */
	public Map<String, Object> selectGoodsCategoryByCompanyId(JSONObject res) {
		Map<String,Object> returnContent=new HashMap<String, Object>();
		String companyId = res.getString("companyId");
		if (StringUtils.isNoneBlank(companyId)) {
			List<Map<String, Object>> returnData = basicsGoodsCategoryMapper
					.selectGoodsCategoryByCompanyId(Long.valueOf(companyId));
			returnContent.put("returnContent", returnData);
			returnContent.put("returnCode", "1");
		} else {
			returnContent.put("returnContent", "请求参数异常");
			returnContent.put("returnCode", "0");
		}
		return returnContent;
	}
}
