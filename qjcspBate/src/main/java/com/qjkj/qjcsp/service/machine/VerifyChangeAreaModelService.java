package com.qjkj.qjcsp.service.machine;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qjkj.qjcsp.entity.BasicsMachine;
import com.qjkj.qjcsp.mapper.BasicsMachineCellMapper;
import com.qjkj.qjcsp.mapper.BasicsMachineMapper;

import net.sf.json.JSONObject;
/**
 * 2.2.11.	验证切换模型
 * @author Administrator
 *
 */
@Service
public class VerifyChangeAreaModelService {

	@Autowired
	private BasicsMachineMapper basicsMachineMapper;
	@Autowired
	private BasicsMachineCellMapper basicsMachineCellMapper;
	public Map<String,Object> verifyChangeAreaModel(JSONObject res){
		
		Map<String,Object> returnContent=new HashMap<String, Object>();
		/*获取设备编号*/
		String deviceCode=res.getString("deviceCode");
		/*获取分区模型Id*/
		String areaModelId=res.getString("areaModelId");
		if(StringUtils.isNoneBlank(deviceCode,areaModelId)){
			/*根据设备编号获取设备*/
			BasicsMachine basicsMachine=basicsMachineMapper.selectByDeviceCode(deviceCode);
			Long currentAreaModelId=basicsMachine.getAreaModelId();
			if(currentAreaModelId!=Long.valueOf(areaModelId)){
				if(currentAreaModelId==null){
					returnContent.put("returnCode", "1");
					returnContent.put("returnContent", "当前模型等于null");
				}else{
					Integer cellCount=basicsMachineCellMapper.findCellCountByCellStatusIsNullByMachineId(basicsMachine.getMachineId());
					if(cellCount==0){
						returnContent.put("returnCode", "1");
						returnContent.put("returnContent", "模型可以切换");
					}else{
						returnContent.put("returnCode", "0");
						returnContent.put("returnContent", "当前设备当前模型不可切换");
					}
				}
			}else{
				returnContent.put("returnCode", "1");
				returnContent.put("returnContent", "当前模型等于要切换的模型");
			}
			
		}else{
			returnContent.put("returnCode", "0");
			returnContent.put("returnContent", "请求参数异常");
		}
		return returnContent;
	} 
}
