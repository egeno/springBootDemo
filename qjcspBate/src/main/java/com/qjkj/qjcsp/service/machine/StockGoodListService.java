package com.qjkj.qjcsp.service.machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.entity.TblGoodsRetailNum;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;
import com.qjkj.qjcsp.mapper.BasicsUserRoleMapper;
import com.qjkj.qjcsp.mapper.StokGoodListMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailNumMapper;
import com.qjkj.qjcsp.mapper.TblGoodsRetailTemporaryMapper;

@Service
public class StockGoodListService {
	
	@Autowired
	private StokGoodListMapper stokGoodListMapper;
	
	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	
	@Autowired
	private TblGoodsRetailNumMapper tblGoodsRetailNumMapper;
	
	@Autowired
	private TblGoodsRetailTemporaryMapper tblGoodsRetailTemporaryMapper;
	
	@Autowired
	private BasicsUserRoleMapper basicsUserRoleMapper; 
	
	public Map<String, Object> getStockGoodList(JSONObject json){
		Map<String, Object> returnmap = new HashMap<String, Object>(); 
		String deviceCode = null;
		if(json.has("deviceCode")){
			deviceCode = json.getString("deviceCode");
		}
		String areaModelId = null;
		if(json.has("areaModelId")){
			areaModelId = json.getString("areaModelId");
		}
		String userId = null;
		if(json.has("userId")){
			userId = json.getString("userId");
		}
		
		if(StringUtils.isNoneBlank(deviceCode,userId)){
			BasicsMachine basicsMachine = basicsMachineMapper.selectByDeviceCode(deviceCode);
//			 List<TblGoodsRetailNum>  tblGoodsRetailNumlist = tblGoodsRetailNumMapper.selectByMachintIdAndAreaModelId(basicsMachine.getMachineId(),Long.valueOf(areaModelId));
//			/*判断零售是否分配格子*/
//			if(tblGoodsRetailNumlist == null){
//				//分配格子后才能得到零售格子信息
//				
//				
//			}
			if(areaModelId == null){
				areaModelId = basicsMachine.getAreaModelId()+"";
			}
			//判断当前userId 是否是商户
			String specialRoleNum = basicsUserRoleMapper.getSpecialRoleNum(Long.valueOf(userId),basicsMachine.getMachineId());
			Long lUserId  = Long.valueOf(userId);
//			if(num == null || num < 1){ //补货人员 
//				lUserId = null;
//			}
			if("2".equals(specialRoleNum)){
				lUserId = null;
			}
			
			List<Map<String, String>> cells = stokGoodListMapper.selectCellBymachineId(basicsMachine.getMachineId());
			/*预售list*/
			List<Map<String, Object>> reserveList = stokGoodListMapper
					.selectStokGoodList(basicsMachine.getMachineId(),Long.valueOf(areaModelId),lUserId);
			/*零售list*/
			List<Map<String, Object>> retailList = tblGoodsRetailTemporaryMapper
					.selectRetailTemporaryList(basicsMachine.getMachineId(),Long.valueOf(areaModelId),lUserId);
			/*遍历格子列表*/
			for (int i = cells.size()-1; i >= 0; i--) {
				Map<String, String> cellsMap = cells.get(i);
				String cellNum = cellsMap.get("cellNum");
				String cellStatus = cellsMap.get("cellStatus");
				if (StringUtils.equals(cellStatus, "1")){
					if(lUserId != null){
						//商户处理
						int cellsum = stokGoodListMapper.getCellByCellNum(basicsMachine.getMachineId(), Long.valueOf(cellNum), lUserId);
					    if(cellsum == 0){
					    	cells.remove(i);
					    }else{
					    	/*已补货处理*/
		 					cellsMap.put("cellStatus", "3");
					    }
					}else{
						/*已补货处理*/
	 					cellsMap.put("cellStatus", "3");
					}
				}else if (StringUtils.equals(cellStatus, "0")){
					/*未分配或未补货处理*/
					boolean flag = Boolean.TRUE;
					/*预定*/
					for (int j = 0; j < reserveList.size(); j++) {
						Map<String, Object> reserveMap = reserveList.get(j);
						String cellNumls = reserveMap.get("cellNum").toString();
						/*格子序号相等*/
						if(cellNum.equals(cellNumls)){
							cellsMap.put("cellStatus", "1");
							cellsMap.put("itemType", "0");
							
							flag = Boolean.FALSE;
							break;
						}
					}
					/*零售*/
					if (flag){
						for (int k = 0; k < retailList.size(); k++) {
							Map<String, Object> retailMap = retailList.get(k);
							String cellNumyd = retailMap.get("cellNum").toString();
							/*格子号相等*/
							if(cellNum.equals(cellNumyd)){
								cellsMap.put("cellStatus", "1");
								cellsMap.put("itemType", "1");
								
								flag = Boolean.FALSE;
								break;
							}
						}
					}
					//商户登录，为空的格子不显示
					if(lUserId != null){
						//当前设备没有预定也没有零售
						if(flag){
							cells.remove(i);
						}
					}
					//商户处理，为空的格子不显示
//					if(lUserId != null && !"1".equals(cellsMap.get("cellStatus")) && !"3".equals(cellsMap.get("cellStatus")) ){
//						cells.remove(i);
//					}
				}else if(StringUtils.equals(cellStatus, "2") && lUserId != null){
					cells.remove(i);
				}
			}
			
			returnmap.put("returnCode", "1");
			returnmap.put("returnContent", cells);
		}else{
			returnmap.put("returnCode", "0");
			returnmap.put("returnContent", "请求参数错误");
		}
		
		return returnmap;
	}
}
