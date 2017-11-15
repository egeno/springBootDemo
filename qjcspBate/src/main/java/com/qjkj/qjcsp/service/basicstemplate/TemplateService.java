package com.qjkj.qjcsp.service.basicstemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qjkj.qjcsp.entity.TblGoodsRetailNum;
import com.qjkj.qjcsp.entity.TblMachineAreaModelMostSale;
import com.qjkj.qjcsp.entity.TblRetail;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsCategoryMapper;
import com.qjkj.qjcsp.mapper.BasicsGoodsMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsTemplateMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailNumMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailTemporaryMapper;
import com.qjkj.qjcsp.mapper.TblMachineAreaModelMostSaleMapper;
import com.qjkj.qjcsp.mapper.TblTemplateAreaModelMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.service.goods.GoodsCommonRetailService;
import com.qjkj.qjcsp.service.issue.retailManagerService;
import com.qjkj.qjcsp.service.machine.MachineAreaModelService;
import com.qjkj.qjcsp.service.machine.MachineTemplateRetailDateService;
import com.qjkj.qjcsp.service.retail.RetailService;
import com.qjkj.qjcsp.util.Constants;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.DateTimeUtil;
import com.qjkj.qjcsp.util.UserUtils;

@Service
@Transactional
public class TemplateService {
	@Autowired
	private BasicsTemplateMapper templateMapper;
	@Autowired
	private TemplateAreaModelService templateAreaModelService;
	@Autowired
	private MachineAreaModelService machineAreaModelService;
	@Autowired
	private MachineTemplateRetailDateService templateRetailDateService;
	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	@Autowired
	private BasicsGoodsCategoryMapper basicsGoodsCategoryMapper;
	@Autowired
	private BasicsGoodsMapper basicsGoodsMapper;
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private TblGoodsRetailNumMapper tblGoodsRetailNumMapper;
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;
	@Autowired
	private GoodsCommonRetailService goodsCommonRetailService;
	@Autowired
	private TblGoodsRetailTemporaryMapper tblGoodsRetailTemporaryMapper;
	@Autowired
	private TblMachineAreaModelMostSaleMapper tblMachineAreaModelMostSaleMapper;
	@Autowired
	private RetailService retailService;
	
	/**
	 * 获取模板id和模板名
	 */
	public List<Map<String, Object>> getTemplateList() {
		UserUtils userUtils = new UserUtils();
		Integer companyId = userUtils.getCompanyId();
		return templateMapper.getTemplateList(companyId);
	}

	/**
	 * 根据设备Id获取该设备可选的模板
	 */
	public List<Map<String, Object>> getOkTemplateList(Long machineId) {
		// 首先获取当前公司所有的模板
		List<Map<String, Object>> list = this.getTemplateList();
		List<Long> areaModelIds = machineAreaModelService.getAreaModelIdByMachineId(machineId);
		if (list.size() == 0) {
			return list;
		}
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Long templateId = (Long) map.get("templateId");
			List<Long> areaModelIds1 = templateAreaModelService.getAreaModelIdByTemplateId(templateId);
			if (areaModelIds.containsAll(areaModelIds1) && areaModelIds.size() == areaModelIds1.size()
					&& areaModelIds.size() != 0 && areaModelIds1.size() != 0) {
				returnList.add(map);
			}
		}
		return returnList;
	}

	public Map<String, Object> delTemplate(Long templateId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int count = templateRetailDateService.getCount(templateId);
		if (count > 0) {
			map.put("result", false);
			map.put("message", "删除失败，该模板已被设备套用，请解除套用后再进行删除~");
			return map;
		}
		templateMapper.delTemplate(templateId);
		templateAreaModelService.delByTemplateId(templateId);
		retailService.delRetailByTemplateId(templateId);
		map.put("result", true);
		map.put("message", "删除成功");
		return map;
	}
	
	public Map<String, Object> editTemplate(Long templateId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int count = templateRetailDateService.getCount(templateId);
		if (count > 0) {
			map.put("result", false);
			map.put("message", "无法修改，该模板已被设备套用，请解除套用后再进行修改~");
			return map;
		}
		map.put("result", true);
		return map;
	}
	
	/**
	 * 获取左边模型(早中晚、点心、夜宵)
	 */
	public List<Map<String, Object>> getAreaModelList(int machineId) {
		long companyId = Constants.getCurrendUser().getCompanyId();
		List<Map<String, Object>> list = basicsAreaModelMapper.selectAreaModelByCompanyId(companyId);
		return list;
	}

	public List<Map<String, Object>> goodsCategory() {
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		//根据商户id查找该商户下商品类别
		List<Map<String, Object>> list = basicsGoodsCategoryMapper.selectGoodsCategoryName(companyId.longValue());
		return list;
	}

	public Map<String, Object> goodsDetail(int goodsCategoryId, int mId, int areaModelId, String time) {
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		//获取该模型今日补货时间
		Map<String, Object> map = goodsCommonRetailService.getSupplyEndTime((long)areaModelId); 
		//获取补货时间
		Map<String, Object> map2 = goodsCommonRetailService.getReplenishmentTime((long)areaModelId); 
		Map<String, Object> returnData = new HashMap<String, Object>();
		if("0".equals(map2.get("code").toString())){
			return map2;
		}
		if("0".equals(map.get("code").toString())){
			return map;
		}
		//根据商户id查找该商户下商品类别
		List<Map<String, Object>> list = basicsGoodsMapper.selectGoodsDetail(companyId.longValue(), goodsCategoryId, mId, areaModelId, time);
		returnData.put("code", "1");
		returnData.put("content", list);
		return returnData;
	}

	public List<Map<String, Object>> getMachine() {
		Integer companyId = Constants.getCurrendUser().getCompanyId();
		List<Map<String, Object>> list = basicsMachineMapper.getMachineList(companyId);		
		return list;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean saveIssuesList(List<TblGoodsRetailNum> issues, String time) {
		TblGoodsRetailNum tblGoodsRetailNum = issues.get(0);
		Long machineId = tblGoodsRetailNum.getMachineId(); //获取machineId 
		/*Long goodsRetailNum = tblGoodsRetailNumMapper.getGoodsNum(machineId); //获取零售已添加数量
		int temporaryRetail = tblTemporaryRetailMapper.getRetailNum(machineId); // 获取预定的数量		
		int machineNum = basicsMachineMapper.getMachineNum(machineId);*/
		
		for (TblGoodsRetailNum tblGoodsRetailNum2 : issues) {
			//判断List内是否有0份
			if(tblGoodsRetailNum2.getGoodsNum() == 0){
				continue;
			}
			int result = goodsCommonRetailService.isAllowModification(machineId, tblGoodsRetailNum2.getAreaModelId(), DateFormat.dateFormatYYYYMMDD(time), tblGoodsRetailNum2.getGoodsNum());
			if(result == 1){
				tblGoodsRetailNum2.setReplenishmentTime(DateFormat.dateFormatYYYYMMDD(time));
				tblGoodsRetailNumMapper.insertSelective(tblGoodsRetailNum2);
				long id = tblGoodsRetailNum2.getId();
				goodsCommonRetailService.addNewGoodsRetail(id, tblGoodsRetailNum2.getGoodsNum());
			}
			else if(result == 2){
				String areaModelName = basicsAreaModelMapper.getAreaModelName(tblGoodsRetailNum2.getAreaModelId());
				throw new RuntimeException(areaModelName + "模型下当前数量超过设置的零售最高份数");
			}
			else if(result == 3){
				String areaModelName = basicsAreaModelMapper.getAreaModelName(tblGoodsRetailNum2.getAreaModelId());
				throw new RuntimeException(areaModelName + "模型下剩余可分配数量不足");
			}
		}
		return true;
		
	}

	/**
	 * 根据柜子Id、Name获取左边模型(早中晚、点心、夜宵)
	 */
	public List<Map<String, Object>> getAreaModelListByMahineIdName(String mId) {
		long companyId = Constants.getCurrendUser().getCompanyId();
		List<Map<String, Object>> list = basicsAreaModelMapper.getAreaModelListByMahineIdName(companyId, mId);
		return list;
	}
	
	
	/**
	 * 获取柜子可存放商品数量
	 */
	public int machineNum(String machineId, String areaModelId, String time) {
		long companyId = Constants.getCurrendUser().getCompanyId();
		
		int machineNum = basicsMachineMapper.getMachineNum2(machineId, companyId,areaModelId);
		if("".equals(areaModelId)){
			return machineNum;
		}
		else{
//			int mostSale = tblMachineAreaModelMostSaleMapper.getMostSaleNum(Long.valueOf(machineId), Long.valueOf(goodsCategoryId)); //获取设备设置最高份数
			TblMachineAreaModelMostSale tblMachineAreaModelMostSale = tblMachineAreaModelMostSaleMapper.getNewMostSaleNum(Long.valueOf(machineId), Long.valueOf(areaModelId));
			if(tblMachineAreaModelMostSale == null){
				return machineNum;
			}
			else if(tblMachineAreaModelMostSale.getMostSaleNum() == null){
				return machineNum;
			}
			int mostSale = tblMachineAreaModelMostSale.getMostSaleNum();
			int last = goodsCommonRetailService.calculateRemainingCellNum(Long.valueOf(machineId), Long.valueOf(areaModelId), DateFormat.dateFormatYYYYMMDD(time));
			if(mostSale < last){
				return mostSale;
			}
			else{
				if(last < 0){
					return 0;
				}
				return last;
			}
		}
	}
	/**
	 * 判断选中的菜品是否能添加到柜子中
	 * */
	public Map<String, Object> checkGoodsToMachine(String foodName,Long machineId){
		Map<String, Object> returnContent=new HashMap<String, Object>();
		int count=basicsGoodsMapper.checkGoodsToMachine(foodName,machineId,"3");
		if (count==0) {
			returnContent.put("status", false);
			returnContent.put("message", "添加商品失败，当前商品对应的商户没有关联当前设备");
		}else{
			returnContent.put("status", true);
		}
		return returnContent;
	}
	
	
}
