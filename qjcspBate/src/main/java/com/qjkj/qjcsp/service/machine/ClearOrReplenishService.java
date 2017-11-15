package com.qjkj.qjcsp.service.machine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qjkj.qjcsp.core.quartz.TimingPush;
import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.BasicsMachineCell;
import com.qjkj.qjcsp.entity.TblGoodsRetailTemporary;
import com.qjkj.qjcsp.entity.TblItemDetail;
import com.qjkj.qjcsp.entity.TblOperateRecordLog;
import com.qjkj.qjcsp.entity.TblTemporaryRetail;
import com.qjkj.qjcsp.entity.enums.ItemStatusEnum;
import com.qjkj.qjcsp.entity.enums.OperateTypeEnum;
import com.qjkj.qjcsp.mapper.BasicsMachineCellMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.QuartzMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailTemporaryMapper;
import com.qjkj.qjcsp.mapper.TblItemDetailMapper;
import com.qjkj.qjcsp.mapper.TblOperateRecordLogMapper;
import com.qjkj.qjcsp.mapper.TblTemporaryRetailMapper;
import com.qjkj.qjcsp.service.goods.GoodsCommonRetailService;
import com.qjkj.qjcsp.util.DateFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ClearOrReplenishService {

	@Autowired
	private BasicsMachineCellMapper basicsMachineCellMapper;

	@Autowired
	private TblItemDetailMapper tblItemDetailMapper;

	@Autowired
	private BasicsMachineMapper basicsMachineMapper;

	@Autowired
	private TblOperateRecordLogMapper tblOperateRecordLogMapper;

	@Autowired
	private TblTemporaryRetailMapper tblTemporaryRetailMapper;

	@Autowired
	private TblGoodsRetailTemporaryMapper tblGoodsRetailTemporaryMapper;
	
	@Autowired
	private GoodsCommonRetailService goodsCommonRetailService;
	
	@Autowired
	private QuartzMapper quartzMapper;

	public Map<String, Object> clearance(JSONObject res) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		String deviceCode = res.getString("deviceCode");
		String clearType = res.getString("clearType");
		String userId = res.getString("userId");
		JSONArray itemList = res.getJSONArray("itemList");
		if (StringUtils.isNoneBlank(deviceCode, clearType, userId)) {
			BasicsMachine basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
			/* 部分清货 */
			List<Map<String, Object>> list = null;
			if (clearType.equals("0")) {
				if (StringUtils.isNoneBlank(itemList.toString())) {
					list = itemList;
				}
			} /* 全部清货 */
			else {
				list = basicsMachineCellMapper.getNotEmptyCells(basicsMachine.getMachineId());
			}
			returnContent = UtilMethod(list, basicsMachine, userId);
		} else {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "请求参数异常！");
		}
		return returnContent;
	}

	@Transactional(rollbackFor = Exception.class)
	private Map<String, Object> UtilMethod(List<Map<String, Object>> itemList, BasicsMachine basicsMachine,
			String userId) {
		Map<String, Object> returnContent = new HashMap<String, Object>();
		String batchNum = UUID.randomUUID().toString();
		Date operateTime = new Date();
		try {
			/* 根据设备编号获取设备实体 */
			for (int i = 0; i < itemList.size(); i++) {
				/* 获取清货的格子Id */
				String cellId = itemList.get(i).get("cellId").toString();
				/* 获取清货的格子 */
				BasicsMachineCell cell = basicsMachineCellMapper.selectByPrimaryKey(cellId);
				/* 获取清货的单品Id */
				String itemId = cell.getItemId();
				/* 将格子的cellStatus设置成 0，将itemId设置成NULL */
				cell.setCellStatus("0");
				cell.setItemId(null);

				/*----------------------------------------*/
				/* 根据单品ID查找单品 */
				TblItemDetail tblItemDetail = tblItemDetailMapper.selectByPrimaryKey(itemId);
				/* 将tblItemDetaild数据设置成已清货 */
				tblItemDetail.setItemStatus(ItemStatusEnum.CLEAR.getValue());
				tblItemDetail.setCleanTime(operateTime);
				tblItemDetail.setClearBatchNum(batchNum);
				/*---------------------------------------*/

				/*-------------------------------*/
			

				/*-----------------*/
				/* 执行修改SQL */
				basicsMachineCellMapper.updateByPrimaryKey(cell);
				tblItemDetailMapper.updateByPrimaryKeySelective(tblItemDetail);
				
			}
			/* 创建清货记录 */
			TblOperateRecordLog tblOperateRecordLog = new TblOperateRecordLog();
			tblOperateRecordLog.setBatchNum(batchNum);
			tblOperateRecordLog.setMachineId(basicsMachine.getMachineId());
			tblOperateRecordLog.setUserId(Long.valueOf(userId));
			tblOperateRecordLog.setOperateTime(operateTime);
			tblOperateRecordLog.setOperateType(OperateTypeEnum.CLEAR.getValue());
			tblOperateRecordLogMapper.insertSelective(tblOperateRecordLog);
		} catch (Exception e) {
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "服务器异常！");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		returnContent.put("returnCode", "1");
		returnContent.put("returnContent", "清货成功！");
		return returnContent;
	}

	/**
	 * 补货
	 * @param res
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> replenishment(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
		
		if (res.has("deviceCode") && res.has("areaModelId") && res.has("itemList") && res.has("userId") && 
				StringUtils.isNoneBlank(res.getString("deviceCode"), res.getString("areaModelId"), res.getString("itemList"), res.getString("userId"))){
			String deviceCode = res.getString("deviceCode");
			/*根据设备编号获取设备*/
			BasicsMachine basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
			basicsMachine.setAreaModelId(res.getLong("areaModelId"));
			Long userId = res.getLong("userId");
			/*当前时间*/
			Date currentTime = new Date();
			Date currentTimeFormat = DateFormat.dateFormatYYYYMMDD(currentTime);
			/*新增补货记录*/
			String batchNum = insertReplenishmentLog(userId, basicsMachine.getMachineId(), currentTime);
			/*模型id与设备当前模型id不同时，更新模型id*/
			Long currentAreaModelId = switchAreaModel(basicsMachine, res.getLong("areaModelId"));
			JSONArray itemList = res.getJSONArray("itemList");
			String cellNums = "";
			/*遍历补货的单品列表*/
			for (int i=0; i<itemList.size(); i++){
				JSONObject item = itemList.getJSONObject(i);
				/*新增单品*/
				TblItemDetail tblItemDetail = new TblItemDetail();	
				String itemId = UUID.randomUUID().toString();
				tblItemDetail.setItemId(itemId);
				tblItemDetail.setAreaModelId(currentAreaModelId);
				tblItemDetail.setCompanyId(basicsMachine.getCompanyId());			
				tblItemDetail.setMachineId(basicsMachine.getMachineId());
				tblItemDetail.setSupplyBatchNum(batchNum);
				tblItemDetail.setStockTime(currentTime);
				/*0：预定商品，1：零售商品*/
				if (StringUtils.equals(item.getString("itemType"), "0")){
					int cellNum = item.getInt("cellNum");
					TblTemporaryRetail tblTemporaryRetail = tblTemporaryRetailMapper.getTemporaryRetailByReplenishment(basicsMachine.getMachineId(), 
							res.getLong("areaModelId"), cellNum, currentTimeFormat);
					tblItemDetail.setGoodsId(tblTemporaryRetail.getGoodsId());
					tblItemDetail.setCellId(tblTemporaryRetail.getCellId());
					/*预定*/
					tblItemDetail.setItemStatus(ItemStatusEnum.SOLD.getValue());
					tblItemDetail.setItemType("0");
					tblItemDetail.setOrderId(tblTemporaryRetail.getOrderId());
					tblItemDetail.setOrderChildId(tblTemporaryRetail.getOrderChildId());
					tblItemDetail.setOrderDetailId(tblTemporaryRetail.getOrderDetailId());
					/*更新格子表*/
					basicsMachineCellMapper.updateMachineCell(tblTemporaryRetail.getCellId(), itemId, "1");
					/*伪删除预定临时表*/
					tblTemporaryRetail.setIsdel("Y");
					tblTemporaryRetailMapper.updateByPrimaryKey(tblTemporaryRetail);
					
					//记录预定的格子信息
					cellNums= cellNums + String.valueOf(cellNum)+",";
				} else if(StringUtils.equals(item.getString("itemType"), "1")){
					/*零售*/
					/*获取零售临时表中的数据*/
					TblGoodsRetailTemporary tblGoodsRetailTemporary = 
							tblGoodsRetailTemporaryMapper.getTemporaryRetailByReplenishment(basicsMachine.getMachineId(), res.getLong("areaModelId"), item.getInt("cellNum"), currentTimeFormat);
					tblItemDetail.setGoodsId(tblGoodsRetailTemporary.getGoodsId());
					tblItemDetail.setCellId(tblGoodsRetailTemporary.getCellId());
					tblItemDetail.setItemStatus(ItemStatusEnum.UNSOLD.getValue());
					tblItemDetail.setItemType("1");
					basicsMachineCellMapper.updateMachineCell(tblGoodsRetailTemporary.getCellId(), itemId, "1");
					/*伪删除零售临时表*/
					tblGoodsRetailTemporary.setIsDel("Y");
					tblGoodsRetailTemporaryMapper.updateByPrimaryKeySelective(tblGoodsRetailTemporary);
				}
				//System.out.println(tblItemDetail.toString());
				tblItemDetailMapper.insertSelective(tblItemDetail);		
			}
			basicsMachineMapper.updateByPrimaryKeySelective(basicsMachine);
			//补货完成后app推送  没有格子号就不用推送  
			if(!"".equals(cellNums)){
				cellNums = cellNums.substring(0,cellNums.length()-1);
				List<String> customerIds = new ArrayList<String>();
				//得到补货的订单  
				List<Map<String, String>> list = quartzMapper.getReplenishmenOrder(cellNums, res.getLong("areaModelId"), 
						basicsMachine.getMachineId());
				
				for (int i = 0; i < list.size(); i++) {
					Map<String, String> map = list.get(i);
					Map<String, String> numMap = tblItemDetailMapper.selectBuyNumAndReplenishmentNum(map.get("orderChildNum"));
					
					//判断该订单完成补货
					if(numMap.get("gNum").equals(numMap.get("bNum"))){
						//要推送的客户id
						String customerId = String.valueOf(map.get("customerId"));
						customerIds.add(customerId);
					}
				}
				if(customerIds.size() > 0){
					//根据客户id发送推送
					TimingPush.testSendPush2(customerIds);
				}
				
			}
			
		}else {
			returnData.put("returnCode", "0");
			returnData.put("returnContent", "请求参数错误");
			
			return returnData;
		}
		
		returnData.put("returnCode", "1");
		returnData.put("returnContent", "成功");
		
		return returnData;
		
	}
	
	/**
	 * 判断当前模型预订是否结束
	 * @param res
	 * @return
	 */
	public Map<String, Object> whetherOrNotTheReservationIsOver(JSONObject res){
		Map<String, Object> returnData = new HashMap<String, Object>();
//		Map<String, Object> map = new HashMap<String, Object>();
		returnData=goodsCommonRetailService.isOverTheReservation(res);
//		if (res.has("areaModelId") && StringUtils.isNotBlank(res.getString("areaModelId"))){
//			if (res.getLong("areaModelId")) == true){
//				returnData.put("returnCode", "1");
//				returnData.put("returnContent", "允许补货");
//			} else{
//				returnData.put("returnCode", "0");
//				returnData.put("returnContent", "当前时间不在预定结束时间和补货截止时间之间！无法补货");
//			}
//		} else{
//			returnData.put("returnCode", "0");
//			returnData.put("returnContent", "请求参数错误");
//		}
		
		return returnData;
	}

	/**
	 * 补货记录
	 * 
	 * @param userId
	 * @param machineId
	 * @return 批次号
	 */
	private String insertReplenishmentLog(Long userId, Long machineId, Date currentTime) {
		TblOperateRecordLog tblOperateRecordLog = new TblOperateRecordLog();
		/* 批次号 */
		String batchNum = UUID.randomUUID().toString();
		tblOperateRecordLog.setBatchNum(batchNum);
		tblOperateRecordLog.setUserId(userId);
		tblOperateRecordLog.setMachineId(machineId);
		tblOperateRecordLog.setOperateType(OperateTypeEnum.REPLENISHMENT.getValue());
		tblOperateRecordLog.setOperateTime(currentTime);
		tblOperateRecordLogMapper.insertSelective(tblOperateRecordLog);
		return batchNum;
	}

	/**
	 * 切换模型
	 * 
	 * @param machineId
	 * @param areaModelId
	 */
	private Long switchAreaModel(BasicsMachine basicsMachine, Long areaModelId) {
		/* 设备当前模型id */
		Long currentAreaModelId = basicsMachineMapper.getAreaModelIdByMachineId(basicsMachine.getMachineId());
		if (areaModelId != currentAreaModelId) {
			basicsMachineMapper.updateAreaModelIdIfDifferent(basicsMachine.getMachineId(), areaModelId);
			currentAreaModelId = areaModelId;
		}

		return currentAreaModelId;
	}

	/**
	 * 当前模型清货时间点判断
	 * @param res
	 * @return
	 */
	public Map<String, Object> whetherOrNotTheClearanceTime(JSONObject res) {
		return goodsCommonRetailService.isOrNotClearanceTime(res);
	}
 
}
