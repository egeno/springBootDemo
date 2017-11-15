package com.qjkj.qjcsp.service.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsAreaModel;
import com.qjkj.qjcsp.entity.TblGoodsRetailNum;
import com.qjkj.qjcsp.mapper.BasicsAreaModelMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineAreaModelMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailNumMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailTemporaryMapper;
import com.qjkj.qjcsp.mapper.TblMachineAreaModelMostSaleMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.util.DateFormat;
import com.qjkj.qjcsp.util.UserUtils;

/**
 *零售商品分配 
 *
 */
@Service
public class GoodsRetailService {

	@Autowired
	private BasicsAreaModelMapper basicsAreaModelMapper;
	
	@Autowired
	private BasicsMachineAreaModelMapper basicsMachineAreaModelMapper;
	
	@Autowired
	private TblGoodsRetailNumMapper tblGoodsRetailNumMapper;
	
	@Autowired
	private GoodsCommonRetailService goodsCommonRetailService;
	
	@Autowired
	private TblMachineAreaModelMostSaleMapper tblMachineAreaModelMostSaleMapper;
	
	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;
	
	@Autowired
	TblGoodsRetailTemporaryMapper tblGoodsRetailTemporaryMapper; 
	
	/**
	 * 获取模版列表
	 * @return
	 */
	public Map<String, Object> getAllAreaModelList(){
		Map<String, Object> returnData = new HashMap<String, Object>();
		UserUtils userUtils = new UserUtils();
		List<BasicsAreaModel> areaModelList = basicsAreaModelMapper.findAllModelByCompanyId(Long.valueOf(userUtils.getCompanyId()));
		if (areaModelList == null || areaModelList.size() < 1){
			returnData.put("code", "0");
			returnData.put("content", "请先添加模型");
		} else {
			returnData.put("code", "1");
			returnData.put("content", areaModelList);
		}
		return returnData;
	}
	
	/**
	 * 获取零售发布日期
	 * @return
	 */
	public Map<String, Object> getGoodsRetailDate(Long areaModelId){
		
		return goodsCommonRetailService.getReplenishmentTime(areaModelId);
	}
	
	/**
	 * 根据模型id获取设备零售数量
	 * @param areaModelId
	 * @return
	 */
	public Map<String, Object> getMachineRetailNum(Long areaModelId, String repTime){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> getMachineResult = getMachine(areaModelId);
		if (StringUtils.equals(getMachineResult.get("code").toString(), "0")){
			result.put("code", "0");
			result.put("content", null);
			result.put("otherInfo", getMachineResult.get("content").toString());
		} else{
			Date replenishmentTime = DateFormat.dateFormatYYYYMMDD(repTime);
			/*获取该模型下的所有设备*/
			List<Map<String, Object>> machineList = (List<Map<String, Object>>)getMachineResult.get("content");
			List<Map<String, Object>> machineAndGoodsRetailList = new ArrayList<Map<String,Object>>();
			StringBuffer otherInfo = new StringBuffer();
			/*遍历设备列表，获取设备对应的零售商品*/
			int mapSize=0; //页面加载弹框提示只显示5条信息   
			for (Map<String, Object> machine : machineList){
				Map<String, Object> machineTemp = new HashMap<String, Object>();
				/*零售商品初始化*/
				Map<String, Object> returnData = null;
				returnData = goodsCommonRetailService.getGoodsNumInPercentageTerm((Long)machine.get("machineId"), areaModelId, replenishmentTime);
				if (StringUtils.equals(returnData.get("code").toString(), "1")){
					/*获取该设备该模型下的零售商品*/
					List<Map<String, Object>> goodsRetailList = tblGoodsRetailNumMapper.queryRetailNumByMachineIdAndAreaModelId((Long)machine.get("machineId"), areaModelId, replenishmentTime);
					machineTemp.put("goodsRetailList", goodsRetailList);
				} else if (StringUtils.equals(returnData.get("code").toString(), "0")){
					if(mapSize<5){ //页面加载弹框提示只显示5条信息
						otherInfo.append("<font color='#FF0000'>"+machine.get("machineName")+"</font>没有数据："+returnData.get("content").toString()+"。</br>");
					}			
				}
				machineTemp.put("machineId", machine.get("machineId"));
				machineTemp.put("machineName", machine.get("machineName"));				
				machineAndGoodsRetailList.add(machineTemp);
				
				mapSize++;//页面加载弹框提示只显示5条信息   
			}
			result.put("code", "1");
			result.put("content", machineAndGoodsRetailList);
			result.put("otherInfo", otherInfo);
		}
		
		return result;
	}
	
	/**
	 * 根据模型id获取设备
	 * @param areaModelId
	 * @return
	 */
	public Map<String, Object> getMachine(Long areaModelId){
		Map<String, Object> returnData = new HashMap<String, Object>();
		/*获取该模型下的所有设备*/
		List<Map<String, Object>> machineList = basicsMachineAreaModelMapper.queryMachineByAreaModelId(areaModelId);
		if (machineList == null || machineList.size() < 1){
			returnData.put("code", "0");
			returnData.put("content", "该模型下暂无关联的设备");
		} else {
			returnData.put("code", "1");
			returnData.put("content", machineList);
		}
		
		return returnData;
	}
	
	/**
	 * 删除零售商品
	 * @param id
	 */
	public void delGoodsRetail(Long id){
		goodsCommonRetailService.delGoodsRetail(id);
	}
	
	/**
	 * 获取零售最高份数
	 * @param id
	 * @return
	 */
	public int getMostSaleNum(Long id){
		TblGoodsRetailNum good = tblGoodsRetailNumMapper.selectByPrimaryKey(id);
		/*最高可售份数*/
		Integer mostSaleNum = tblMachineAreaModelMostSaleMapper.getMostSaleNum(good.getMachineId(), good.getAreaModelId());
		/*预定分配格子数*/
		int orderCellNum = tblTemporaryRetailMapper.getOrderCellNum(good.getMachineId(), good.getAreaModelId(), good.getReplenishmentTime());
//		/* 设备有效份数 */
//		int  effectiveNum = tblTemporaryRetailMapper.getEffectiveNum(machineId);
//		if (mostSaleNum != null){
//			mostSaleNum = mostSaleNum - orderCellNum;
//		} else{
//			mostSaleNum = 0;
//		}
		return mostSaleNum;
	}
	
	/**
	 * 修改零售数量
	 * @param id
	 * @param newGoodsNum
	 * @return
	 */
	public int updateGoodsNum(Long id, int newGoodsNum){
		TblGoodsRetailNum good = tblGoodsRetailNumMapper.selectByPrimaryKey(id);
		/*零售数量变化*/
		int addCellNum = newGoodsNum - good.getGoodsNum();
		/*是否允许修改*/
		int isAllowSymbol = 0;
		if (addCellNum != 0){
			if (addCellNum > 0){
				isAllowSymbol = goodsCommonRetailService.isAllowModification(id, addCellNum);
				if (isAllowSymbol == 1){
					goodsCommonRetailService.addGoodsRetail(id, addCellNum);
				}
			} else {
				int supplyNum = tblGoodsRetailTemporaryMapper.getSupplyNumById(id);
				//补货后不可减少
				if(supplyNum > 0){
					isAllowSymbol = 4;
					return isAllowSymbol;
				}
				goodsCommonRetailService.reduceGoodsRetail(id, addCellNum);
				isAllowSymbol = 1;
			}
		}

		return isAllowSymbol;
	}
	
	
	public Map<String, Object> whetherToJump(Long machineId, String machineName, Long areaModelId, String repTime){
		Map<String, Object> returnData = goodsCommonRetailService.getGoodsNumInPercentageTerm(machineId, areaModelId, DateFormat.dateFormatYYYYMMDD(repTime));
		returnData.put("content", "<font color='#FF0000'>"+machineName+"</font>无法添加餐品："+returnData.get("content").toString()+"。</br>");
		
		return returnData;
	}
}