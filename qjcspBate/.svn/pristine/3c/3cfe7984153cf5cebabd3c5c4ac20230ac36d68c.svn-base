package com.qjkj.qjcsp.service.retail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.BasicsTemplate;
import com.qjkj.qjcsp.entity.TblRetail;
import com.qjkj.qjcsp.entity.TblTemplateAreaModel;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsCategoryMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsTemplateMapper;
import com.qjkj.qjcsp.mapper.TblRetailMapper;
import com.qjkj.qjcsp.mapper.TblTemplateAreaModelMapper;
import com.qjkj.qjcsp.util.Constants;

@Service
@Transactional
public class RetailService {
	
	@Autowired
	private BasicsTemplateMapper basicsTemplateMapper;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	@Autowired
	private TblTemplateAreaModelMapper tblTemplateAreaModelMapper;
	@Autowired
	private TblRetailMapper tblRetailMapper;
	@Autowired
	private BasicsGoodsCategoryMapper basicsGoodsCategoryMapper;
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper; 
	
	
	/*public String insertTemplateName(String template) {
		String text = "";
		//判断模板名称是否存在，存在则返回前段“已存在”，否则插入数据
		int result = basicsTemplateMapper.exist(template);
		if(result > 0){
			text = "已存在";
			return text;
		}
		else{
			Integer userId = Constants.getCurrendUser().getUserId();
			Integer companyId = Constants.getCurrendUser().getCompanyId();
			BasicsTemplate bt = new BasicsTemplate();
			bt.setTemplateName(template);
			bt.setCreateUserId(userId.longValue());
			bt.setCompanyId(companyId.longValue());
			bt.setCreateTime(new Date());
			bt.setModUserId(userId.longValue());
			bt.setModTime(new Date());
			bt.setIsdel("N");
			basicsTemplateMapper.insert(bt);
			return text;
		}
		
	}*/

	/**
	 * @param changeId 页面上的templateName
	 * @param template 小文本框内的templateName
	 * @param getTemplateId 前面修改页面传过来的模板id
	 * @return
	 */
	public String changeTemplateName(String changeId,String template,String getTemplateId) {
		long userId = Constants.getCurrendUser().getUserId();
		long companyId = Constants.getCurrendUser().getCompanyId();
		String text = "";
		//判断是新建还是修改
		if("".equals(getTemplateId)){
			//新建
			int result = basicsTemplateMapper.newExist(template); //判断页面上传过来的templateName是否存在
			if(result > 0){
				text = "模板名已存在";
				return text;
			}
			else{
				BasicsTemplate bt = new BasicsTemplate();
				bt.setTemplateName(template);
				bt.setCreateUserId(userId);
				bt.setCompanyId(companyId);
				bt.setCreateTime(new Date());
				bt.setModUserId(userId);
				bt.setModTime(new Date());
				bt.setIsdel("N");
				basicsTemplateMapper.insert(bt); //插入模版
				
				/*//查找该商户下模型
				List<BasicsAreaModel> list =  basicsAreaModelMapper.selectAreaModelIdByCompanyId(companyId);
				插入模型模版关联
				TblTemplateAreaModel tblTemplateAreaModel = new TblTemplateAreaModel();
				for (BasicsAreaModel basicsAreaModel : list) {
					tblTemplateAreaModel.setAreaModelId(basicsAreaModel.getAreaModelId());
					tblTemplateAreaModel.setTemplateId(bt.getTemplateId());
					tblTemplateAreaModelMapper.insert(tblTemplateAreaModel);
				}*/
				text = "添加成功";
				return text;
			}
		}
		
		else{
			//修改
			//根据templateName和ID判断是否模板名称是否改变
			int result = basicsTemplateMapper.exist(template,getTemplateId);
			if(result > 0){
				text = "操作成功";
				return text;
			}
			else{
				int judge = basicsTemplateMapper.newExist(template); //判断页面上传过来的templateName是否存在
				if(judge > 0){
					text = "模板名已存在";
					return text;
				}
				BasicsTemplate bt = new BasicsTemplate();
				bt.setTemplateId(Long.valueOf(getTemplateId));
				bt.setTemplateName(template);
				bt.setModUserId(userId);
				bt.setModTime(new Date());
				basicsTemplateMapper.updateByPrimaryKeySelective(bt);
			}
			text = "操作成功";
			return text;
			
		}
		/*if("".equals(changeId)){
			
			else{
				Integer userId = Constants.getCurrendUser().getUserId();
				Integer companyId = Constants.getCurrendUser().getCompanyId();
				BasicsTemplate bt = new BasicsTemplate();
				bt.setTemplateName(template);
				bt.setCreateUserId(userId.longValue());
				bt.setCompanyId(companyId.longValue());
				bt.setCreateTime(new Date());
				bt.setModUserId(userId.longValue());
				bt.setModTime(new Date());
				bt.setIsdel("N");
				basicsTemplateMapper.insert(bt); //插入模版
				
				查找该商户下模型
				List<BasicsAreaModel> list =  basicsAreaModelMapper.selectAreaModelIdByCompanyId(companyId.longValue());
				插入模型模版关联
				TblTemplateAreaModel tblTemplateAreaModel = new TblTemplateAreaModel();
				for (BasicsAreaModel basicsAreaModel : list) {
					tblTemplateAreaModel.setAreaModelId(basicsAreaModel.getAreaModelId());
					tblTemplateAreaModel.setTemplateId(bt.getTemplateId());
					tblTemplateAreaModelMapper.insert(tblTemplateAreaModel);
				}
				
				return text;
			}
		}
		else{
			//判断模板名称是否存在，存在则返回前端“已存在”，否则插入数据
			int result = basicsTemplateMapper.exist(template,getTemplateId);
			if(result > 0){
				text = "已存在";
				return text;
			}
			else{
				Integer userId = Constants.getCurrendUser().getUserId();
				Integer companyId = Constants.getCurrendUser().getCompanyId();
				BasicsTemplate bt = new BasicsTemplate();
				bt.setTemplateName(changeId);
				bt.setCompanyId(companyId.longValue());
				bt.setModUserId(userId.longValue());
				bt.setModTime(new Date());
				Date date = new Date();
				basicsTemplateMapper.change(template,companyId,userId,date,changeId);
				return text;
			}
		}*/
		
		
		
	}

	/**
	 * 新建或修改时页面显示的模型和百分比
	 * @return
	 */
	public List<Map<String, Object>> modelList(int templateId) {
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		if(templateId == 0){
			/*查找该商户下模型*/
			List<Map<String, Object>> l =  basicsAreaModelMapper.selectAreaModel(companyId.longValue());
			for (Map<String, Object> map : l) {
				map.put("retail_percent", "0");
			}
			return l;
			
		}
		
		/*查找该商户下模版名称和百分比*/
		List<Map<String, Object>> list = basicsAreaModelMapper.selectTemplateNameAndPercent(companyId.longValue(),templateId);
		if(list.size() == 0){
			/*查找该商户下模型*/
			List<Map<String, Object>> l =  basicsAreaModelMapper.selectAreaModel(companyId.longValue());
			for (Map<String, Object> map : l) {
				map.put("retail_percent", "0");
			}
			return l;
		}else{
			//修改模板时，新添加模型后，也显示在修改模板信息时的模型列表中
			List<Map<String, Object>> l =  basicsAreaModelMapper.selectNewAddAreaModelInfo(companyId.longValue());
			list.addAll(l);
		}
		/*List<TblRetail> retailList = tblRetailMapper.selectByTemplateId(templateId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("areaModelList", areaModelList);
		map.put("retailList", retailList);*/
		/*List<BasicsAreaModel> list = new ArrayList<BasicsAreaModel>();
		list = basicsTemplateMapper.modelList(changeId);*/
		return list;
	}


	public List<Map<String, Object>> goodsCategory() {
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		//根据商户id查找该商户下商品类别
		List<Map<String, Object>> list = basicsGoodsCategoryMapper.selectGoodsCategoryName(companyId.longValue());
		return list;
	}


	public List<Map<String, Object>> goodsDetail(int goodsCategoryId, int areaModelId) {
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		//根据商户id查找该商户下商品类别
		List<Map<String, Object>> list = basicsGoodsMapper.selectGoodsDetailOld(companyId.longValue(),goodsCategoryId,areaModelId);
		return list;
	}


	public void saveIssuesList(List<TblRetail> issues){
		long userId = Constants.getCurrendUser().getUserId();
		//得到模板名称
		String templateName = issues.get(0).getTemplateName();
		//得到模板ID
		Integer templateId = basicsTemplateMapper.getTemplateId(templateName);
		//判断零售表内是否已存在记录
		int result = tblRetailMapper.judgeExist(templateId);
		List<Long> areaModelIds=new ArrayList<Long>();
		if(result > 0){
			//若已存在则删除
			/*tblRetailMapper.deleteByTemplateId(templateId);*/  //逻辑删除
			tblRetailMapper.realDeleteByTemplateId(templateId);  //真实删除
			//删除成功之后新增零售记录
			for (TblRetail tblRetail : issues) {
				if(tblRetail.getRetailPercent() == 0){
					continue;
				}
				tblRetail.setTemplateId(templateId.longValue());
				tblRetail.setCreateTime(new Date());
				tblRetail.setCreateUserId(userId);
				tblRetail.setModTime(new Date());
				tblRetail.setModUserId(userId);
				tblRetail.setIsdel("N");
				tblRetail.setRetailPercent(tblRetail.getRetailPercent()/100);
				tblRetailMapper.insert(tblRetail);
				areaModelIds.add(tblRetail.getAreaModelId());
			}
		}
		else{
			for (TblRetail tblRetail : issues) {
				if(tblRetail.getRetailPercent() == 0){
					continue;
				}
				tblRetail.setTemplateId(templateId.longValue());
				tblRetail.setCreateTime(new Date());
				tblRetail.setCreateUserId(userId);
				tblRetail.setModTime(new Date());
				tblRetail.setModUserId(userId);
				tblRetail.setIsdel("N");
				tblRetail.setRetailPercent(tblRetail.getRetailPercent()/100);
				tblRetailMapper.insert(tblRetail);
				areaModelIds.add(tblRetail.getAreaModelId());
			}
		}
		
		
		//先删除关联表中的数据
		tblTemplateAreaModelMapper.delByTemplateId(templateId.longValue());
		//往模板模型关联表中添加数据
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		//查找该商户下模型
		List<BasicsAreaModel> list =  basicsAreaModelMapper.selectAreaModelIdByCompanyId(companyId.longValue());
		//插入模型模版关联
		TblTemplateAreaModel tblTemplateAreaModel = new TblTemplateAreaModel();
		for (BasicsAreaModel basicsAreaModel : list) {
			tblTemplateAreaModel.setAreaModelId(basicsAreaModel.getAreaModelId());
			tblTemplateAreaModel.setTemplateId(templateId.longValue());
			tblTemplateAreaModel.setStatus(0);
			tblTemplateAreaModelMapper.insert(tblTemplateAreaModel);
		}
		//修改已设置用餐比率的关联数据状态为可用
		tblTemplateAreaModelMapper.updateStatusByTemplateIdAndModelId(templateId.longValue(),areaModelIds);
	}
	
	/**
	 * 修改模板时根据模板id获取零售列表
	 */
	public List<Map<String, Object>> check(String templateId, String areaModelId, String changeId) {
		List<Map<String, Object>> list = null;
		if("".equals(templateId)){
			list = tblRetailMapper.selectGoodsList(changeId,areaModelId);
		}
		else{
			list = tblRetailMapper.selectGoodsList(templateId,areaModelId);
		}
		return list;
	}
	
	public void delRetailByTemplateId(Long templateId){
		tblRetailMapper.deleteByTemplateId(templateId);
	}

	


	
}
